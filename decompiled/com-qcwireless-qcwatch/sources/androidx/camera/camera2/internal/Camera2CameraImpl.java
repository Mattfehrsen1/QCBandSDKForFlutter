package androidx.camera.camera2.internal;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.Logger;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.LiveDataObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseAttachState;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.work.WorkRequest;
import com.google.common.util.concurrent.ListenableFuture;
import com.king.zxing.util.CodeUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
final class Camera2CameraImpl implements CameraInternal {
    private static final int ERROR_NONE = 0;
    private static final String TAG = "Camera2CameraImpl";
    private final CameraAvailability mCameraAvailability;
    private final Camera2CameraControlImpl mCameraControlInternal;
    SessionConfig mCameraControlSessionConfig;
    CameraDevice mCameraDevice;
    int mCameraDeviceError;
    final Camera2CameraInfoImpl mCameraInfoInternal;
    private final CameraManagerCompat mCameraManager;
    private final CameraStateRegistry mCameraStateRegistry;
    CaptureSession mCaptureSession;
    private final SynchronizedCaptureSessionOpener.Builder mCaptureSessionOpenerBuilder;
    private final CaptureSessionRepository mCaptureSessionRepository;
    final Set<CaptureSession> mConfiguringForClose;
    private final Executor mExecutor;
    private MeteringRepeatingSession mMeteringRepeatingSession;
    private final Set<String> mNotifyStateAttachedSet;
    private final LiveDataObservable<CameraInternal.State> mObservableState;
    final AtomicInteger mReleaseRequestCount;
    final Map<CaptureSession, ListenableFuture<Void>> mReleasedCaptureSessions;
    volatile InternalState mState = InternalState.INITIALIZED;
    private final StateCallback mStateCallback;
    private final UseCaseAttachState mUseCaseAttachState;
    ListenableFuture<Void> mUserReleaseFuture;
    CallbackToFutureAdapter.Completer<Void> mUserReleaseNotifier;

    enum InternalState {
        INITIALIZED,
        PENDING_OPEN,
        OPENING,
        OPENED,
        CLOSING,
        REOPENING,
        RELEASING,
        RELEASED
    }

    static String getErrorMessage(int errorCode) {
        return errorCode != 0 ? errorCode != 1 ? errorCode != 2 ? errorCode != 3 ? errorCode != 4 ? errorCode != 5 ? "UNKNOWN ERROR" : "ERROR_CAMERA_SERVICE" : "ERROR_CAMERA_DEVICE" : "ERROR_CAMERA_DISABLED" : "ERROR_MAX_CAMERAS_IN_USE" : "ERROR_CAMERA_IN_USE" : "ERROR_NONE";
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public /* synthetic */ CameraControl getCameraControl() {
        return getCameraControlInternal();
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public /* synthetic */ CameraInfo getCameraInfo() {
        return getCameraInfoInternal();
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public /* synthetic */ LinkedHashSet getCameraInternals() {
        return CameraInternal.CC.$default$getCameraInternals(this);
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public /* synthetic */ CameraConfig getExtendedConfig() {
        return CameraConfigs.emptyConfig();
    }

    @Override // androidx.camera.core.impl.CameraInternal, androidx.camera.core.Camera
    public /* synthetic */ void setExtendedConfig(CameraConfig cameraConfig) throws CameraUseCaseAdapter.CameraException {
        CameraInternal.CC.$default$setExtendedConfig(this, cameraConfig);
    }

    Camera2CameraImpl(CameraManagerCompat cameraManager, String cameraId, Camera2CameraInfoImpl cameraInfoImpl, CameraStateRegistry cameraStateRegistry, Executor executor, Handler schedulerHandler) throws CameraUnavailableException {
        LiveDataObservable<CameraInternal.State> liveDataObservable = new LiveDataObservable<>();
        this.mObservableState = liveDataObservable;
        this.mCameraDeviceError = 0;
        this.mCameraControlSessionConfig = SessionConfig.defaultEmptySessionConfig();
        this.mReleaseRequestCount = new AtomicInteger(0);
        this.mReleasedCaptureSessions = new LinkedHashMap();
        this.mConfiguringForClose = new HashSet();
        this.mNotifyStateAttachedSet = new HashSet();
        this.mCameraManager = cameraManager;
        this.mCameraStateRegistry = cameraStateRegistry;
        ScheduledExecutorService scheduledExecutorServiceNewHandlerExecutor = CameraXExecutors.newHandlerExecutor(schedulerHandler);
        Executor executorNewSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.mExecutor = executorNewSequentialExecutor;
        this.mStateCallback = new StateCallback(executorNewSequentialExecutor, scheduledExecutorServiceNewHandlerExecutor);
        this.mUseCaseAttachState = new UseCaseAttachState(cameraId);
        liveDataObservable.postValue(CameraInternal.State.CLOSED);
        CaptureSessionRepository captureSessionRepository = new CaptureSessionRepository(executorNewSequentialExecutor);
        this.mCaptureSessionRepository = captureSessionRepository;
        this.mCaptureSession = new CaptureSession();
        try {
            Camera2CameraControlImpl camera2CameraControlImpl = new Camera2CameraControlImpl(cameraManager.getCameraCharacteristicsCompat(cameraId), scheduledExecutorServiceNewHandlerExecutor, executorNewSequentialExecutor, new ControlUpdateListenerInternal(), cameraInfoImpl.getCameraQuirks());
            this.mCameraControlInternal = camera2CameraControlImpl;
            this.mCameraInfoInternal = cameraInfoImpl;
            cameraInfoImpl.linkWithCameraControl(camera2CameraControlImpl);
            this.mCaptureSessionOpenerBuilder = new SynchronizedCaptureSessionOpener.Builder(executorNewSequentialExecutor, scheduledExecutorServiceNewHandlerExecutor, schedulerHandler, captureSessionRepository, cameraInfoImpl.getSupportedHardwareLevel());
            CameraAvailability cameraAvailability = new CameraAvailability(cameraId);
            this.mCameraAvailability = cameraAvailability;
            cameraStateRegistry.registerCamera(this, executorNewSequentialExecutor, cameraAvailability);
            cameraManager.registerAvailabilityCallback(executorNewSequentialExecutor, cameraAvailability);
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void open() {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.openInternal();
            }
        });
    }

    /* renamed from: androidx.camera.camera2.internal.Camera2CameraImpl$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState;

        static {
            int[] iArr = new int[InternalState.values().length];
            $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState = iArr;
            try {
                iArr[InternalState.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.CLOSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.OPENED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.REOPENING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.PENDING_OPEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.RELEASING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[InternalState.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openInternal() {
        int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[this.mState.ordinal()];
        if (i == 1) {
            openCameraDevice(false);
            return;
        }
        if (i == 2) {
            setState(InternalState.REOPENING);
            if (isSessionCloseComplete() || this.mCameraDeviceError != 0) {
                return;
            }
            Preconditions.checkState(this.mCameraDevice != null, "Camera Device should be open if session close is not complete");
            setState(InternalState.OPENED);
            openCaptureSession();
            return;
        }
        debugLog("open() ignored due to being in state: " + this.mState);
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void close() {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.closeInternal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInternal() {
        debugLog("Closing camera.");
        int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[this.mState.ordinal()];
        if (i == 3) {
            setState(InternalState.CLOSING);
            closeCamera(false);
            return;
        }
        if (i == 4 || i == 5) {
            boolean zCancelScheduledReopen = this.mStateCallback.cancelScheduledReopen();
            setState(InternalState.CLOSING);
            if (zCancelScheduledReopen) {
                Preconditions.checkState(isSessionCloseComplete());
                finishClose();
                return;
            }
            return;
        }
        if (i == 6) {
            Preconditions.checkState(this.mCameraDevice == null);
            setState(InternalState.INITIALIZED);
        } else {
            debugLog("close() ignored due to being in state: " + this.mState);
        }
    }

    private void configAndClose(boolean abortInFlightCaptures) {
        final CaptureSession captureSession = new CaptureSession();
        this.mConfiguringForClose.add(captureSession);
        resetCaptureSession(abortInFlightCaptures);
        final SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.setDefaultBufferSize(CodeUtils.DEFAULT_REQ_HEIGHT, CodeUtils.DEFAULT_REQ_WIDTH);
        final Surface surface = new Surface(surfaceTexture);
        final Runnable runnable = new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraImpl.lambda$configAndClose$0(surface, surfaceTexture);
            }
        };
        SessionConfig.Builder builder = new SessionConfig.Builder();
        final ImmediateSurface immediateSurface = new ImmediateSurface(surface);
        builder.addNonRepeatingSurface(immediateSurface);
        builder.setTemplateType(1);
        debugLog("Start configAndClose.");
        captureSession.open(builder.build(), (CameraDevice) Preconditions.checkNotNull(this.mCameraDevice), this.mCaptureSessionOpenerBuilder.build()).addListener(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m15x2a0cb35d(captureSession, immediateSurface, runnable);
            }
        }, this.mExecutor);
    }

    static /* synthetic */ void lambda$configAndClose$0(Surface surface, SurfaceTexture surfaceTexture) {
        surface.release();
        surfaceTexture.release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: releaseNoOpSession, reason: merged with bridge method [inline-methods] */
    public void m15x2a0cb35d(CaptureSession noOpSession, DeferrableSurface deferrableSurface, Runnable closeAndCleanupRunner) {
        this.mConfiguringForClose.remove(noOpSession);
        ListenableFuture<Void> listenableFutureReleaseSession = releaseSession(noOpSession, false);
        deferrableSurface.close();
        Futures.successfulAsList(Arrays.asList(listenableFutureReleaseSession, deferrableSurface.getTerminationFuture())).addListener(closeAndCleanupRunner, CameraXExecutors.directExecutor());
    }

    boolean isSessionCloseComplete() {
        return this.mReleasedCaptureSessions.isEmpty() && this.mConfiguringForClose.isEmpty();
    }

    void finishClose() {
        Preconditions.checkState(this.mState == InternalState.RELEASING || this.mState == InternalState.CLOSING);
        Preconditions.checkState(this.mReleasedCaptureSessions.isEmpty());
        this.mCameraDevice = null;
        if (this.mState == InternalState.CLOSING) {
            setState(InternalState.INITIALIZED);
            return;
        }
        this.mCameraManager.unregisterAvailabilityCallback(this.mCameraAvailability);
        setState(InternalState.RELEASED);
        CallbackToFutureAdapter.Completer<Void> completer = this.mUserReleaseNotifier;
        if (completer != null) {
            completer.set(null);
            this.mUserReleaseNotifier = null;
        }
    }

    void closeCamera(boolean abortInFlightCaptures) {
        Preconditions.checkState(this.mState == InternalState.CLOSING || this.mState == InternalState.RELEASING || (this.mState == InternalState.REOPENING && this.mCameraDeviceError != 0), "closeCamera should only be called in a CLOSING, RELEASING or REOPENING (with error) state. Current state: " + this.mState + " (error: " + getErrorMessage(this.mCameraDeviceError) + ")");
        if (Build.VERSION.SDK_INT > 23 && Build.VERSION.SDK_INT < 29 && isLegacyDevice() && this.mCameraDeviceError == 0) {
            configAndClose(abortInFlightCaptures);
        } else {
            resetCaptureSession(abortInFlightCaptures);
        }
        this.mCaptureSession.cancelIssuedCaptureRequests();
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public ListenableFuture<Void> release() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda7
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m25xc715f071(completer);
            }
        });
    }

    /* renamed from: lambda$release$3$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ Object m25xc715f071(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m24x993d5612(completer);
            }
        });
        return "Release[request=" + this.mReleaseRequestCount.getAndIncrement() + "]";
    }

    /* renamed from: lambda$release$2$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m24x993d5612(CallbackToFutureAdapter.Completer completer) {
        Futures.propagate(releaseInternal(), completer);
    }

    private ListenableFuture<Void> releaseInternal() {
        ListenableFuture<Void> orCreateUserReleaseFuture = getOrCreateUserReleaseFuture();
        switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[this.mState.ordinal()]) {
            case 1:
            case 6:
                Preconditions.checkState(this.mCameraDevice == null);
                setState(InternalState.RELEASING);
                Preconditions.checkState(isSessionCloseComplete());
                finishClose();
                return orCreateUserReleaseFuture;
            case 2:
            case 4:
            case 5:
            case 7:
                boolean zCancelScheduledReopen = this.mStateCallback.cancelScheduledReopen();
                setState(InternalState.RELEASING);
                if (zCancelScheduledReopen) {
                    Preconditions.checkState(isSessionCloseComplete());
                    finishClose();
                }
                return orCreateUserReleaseFuture;
            case 3:
                setState(InternalState.RELEASING);
                closeCamera(false);
                return orCreateUserReleaseFuture;
            default:
                debugLog("release() ignored due to being in state: " + this.mState);
                return orCreateUserReleaseFuture;
        }
    }

    private ListenableFuture<Void> getOrCreateUserReleaseFuture() {
        if (this.mUserReleaseFuture == null) {
            if (this.mState != InternalState.RELEASED) {
                this.mUserReleaseFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda0
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        return this.f$0.m17x42886673(completer);
                    }
                });
            } else {
                this.mUserReleaseFuture = Futures.immediateFuture(null);
            }
        }
        return this.mUserReleaseFuture;
    }

    /* renamed from: lambda$getOrCreateUserReleaseFuture$4$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ Object m17x42886673(CallbackToFutureAdapter.Completer completer) throws Exception {
        Preconditions.checkState(this.mUserReleaseNotifier == null, "Camera can only be released once, so release completer should be null on creation.");
        this.mUserReleaseNotifier = completer;
        return "Release[camera=" + this + "]";
    }

    ListenableFuture<Void> releaseSession(final CaptureSession captureSession, boolean abortInFlightCaptures) {
        captureSession.close();
        ListenableFuture<Void> listenableFutureRelease = captureSession.release(abortInFlightCaptures);
        debugLog("Releasing session in state " + this.mState.name());
        this.mReleasedCaptureSessions.put(captureSession, listenableFutureRelease);
        Futures.addCallback(listenableFutureRelease, new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable t) {
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(Void result) {
                Camera2CameraImpl.this.mReleasedCaptureSessions.remove(captureSession);
                int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
                if (i != 2) {
                    if (i != 5) {
                        if (i != 7) {
                            return;
                        }
                    } else if (Camera2CameraImpl.this.mCameraDeviceError == 0) {
                        return;
                    }
                }
                if (!Camera2CameraImpl.this.isSessionCloseComplete() || Camera2CameraImpl.this.mCameraDevice == null) {
                    return;
                }
                Camera2CameraImpl.this.mCameraDevice.close();
                Camera2CameraImpl.this.mCameraDevice = null;
            }
        }, CameraXExecutors.directExecutor());
        return listenableFutureRelease;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public Observable<CameraInternal.State> getCameraState() {
        return this.mObservableState;
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseActive(final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m20xeff284f8(useCase);
            }
        });
    }

    /* renamed from: lambda$onUseCaseActive$5$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m20xeff284f8(UseCase useCase) {
        debugLog("Use case " + useCase + " ACTIVE");
        try {
            this.mUseCaseAttachState.setUseCaseActive(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
            this.mUseCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
            updateCaptureSessionConfig();
        } catch (NullPointerException unused) {
            debugLog("Failed to set already detached use case active");
        }
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseInactive(final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m21x8da1a1d2(useCase);
            }
        });
    }

    /* renamed from: lambda$onUseCaseInactive$6$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m21x8da1a1d2(UseCase useCase) {
        debugLog("Use case " + useCase + " INACTIVE");
        this.mUseCaseAttachState.setUseCaseInactive(useCase.getName() + useCase.hashCode());
        updateCaptureSessionConfig();
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseUpdated(final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m23xe8973177(useCase);
            }
        });
    }

    /* renamed from: lambda$onUseCaseUpdated$7$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m23xe8973177(UseCase useCase) {
        debugLog("Use case " + useCase + " UPDATED");
        this.mUseCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
        updateCaptureSessionConfig();
    }

    @Override // androidx.camera.core.UseCase.StateChangeCallback
    public void onUseCaseReset(final UseCase useCase) {
        Preconditions.checkNotNull(useCase);
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m22x7c3de682(useCase);
            }
        });
    }

    /* renamed from: lambda$onUseCaseReset$8$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m22x7c3de682(UseCase useCase) {
        debugLog("Use case " + useCase + " RESET");
        this.mUseCaseAttachState.updateUseCase(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
        resetCaptureSession(false);
        updateCaptureSessionConfig();
        if (this.mState == InternalState.OPENED) {
            openCaptureSession();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean isUseCaseAttached(final UseCase useCase) {
        try {
            return ((Boolean) CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda8
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m18x9bdb441b(useCase, completer);
                }
            }).get()).booleanValue();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to check if use case is attached.", e);
        }
    }

    /* renamed from: lambda$isUseCaseAttached$10$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ Object m18x9bdb441b(final UseCase useCase, final CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m19x93ceab21(completer, useCase);
                }
            });
            return "isUseCaseAttached";
        } catch (RejectedExecutionException unused) {
            completer.setException(new RuntimeException("Unable to check if use case is attached. Camera executor shut down."));
            return "isUseCaseAttached";
        }
    }

    /* renamed from: lambda$isUseCaseAttached$9$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m19x93ceab21(CallbackToFutureAdapter.Completer completer, UseCase useCase) {
        completer.set(Boolean.valueOf(this.mUseCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())));
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void attachUseCases(final Collection<UseCase> useCases) {
        if (useCases.isEmpty()) {
            return;
        }
        this.mCameraControlInternal.incrementUseCount();
        notifyStateAttachedToUseCases(new ArrayList(useCases));
        try {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m14x65960ee4(useCases);
                }
            });
        } catch (RejectedExecutionException e) {
            debugLog("Unable to attach use cases.", e);
            this.mCameraControlInternal.decrementUseCount();
        }
    }

    /* renamed from: lambda$attachUseCases$11$androidx-camera-camera2-internal-Camera2CameraImpl, reason: not valid java name */
    public /* synthetic */ void m14x65960ee4(Collection collection) {
        try {
            tryAttachUseCases(collection);
        } finally {
            this.mCameraControlInternal.decrementUseCount();
        }
    }

    private void tryAttachUseCases(Collection<UseCase> toAdd) {
        boolean zIsEmpty = this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty();
        ArrayList arrayList = new ArrayList();
        for (UseCase useCase : toAdd) {
            if (!this.mUseCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())) {
                try {
                    this.mUseCaseAttachState.setUseCaseAttached(useCase.getName() + useCase.hashCode(), useCase.getSessionConfig());
                    arrayList.add(useCase);
                } catch (NullPointerException unused) {
                    debugLog("Failed to attach a detached use case");
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        debugLog("Use cases [" + TextUtils.join(", ", arrayList) + "] now ATTACHED");
        if (zIsEmpty) {
            this.mCameraControlInternal.setActive(true);
            this.mCameraControlInternal.incrementUseCount();
        }
        addOrRemoveMeteringRepeatingUseCase();
        updateCaptureSessionConfig();
        resetCaptureSession(false);
        if (this.mState == InternalState.OPENED) {
            openCaptureSession();
        } else {
            openInternal();
        }
        updateCameraControlPreviewAspectRatio(arrayList);
    }

    private void notifyStateAttachedToUseCases(List<UseCase> useCases) {
        for (UseCase useCase : useCases) {
            if (!this.mNotifyStateAttachedSet.contains(useCase.getName() + useCase.hashCode())) {
                this.mNotifyStateAttachedSet.add(useCase.getName() + useCase.hashCode());
                useCase.onStateAttached();
            }
        }
    }

    private void notifyStateDetachedToUseCases(List<UseCase> useCases) {
        for (UseCase useCase : useCases) {
            if (this.mNotifyStateAttachedSet.contains(useCase.getName() + useCase.hashCode())) {
                useCase.onStateDetached();
                this.mNotifyStateAttachedSet.remove(useCase.getName() + useCase.hashCode());
            }
        }
    }

    private void updateCameraControlPreviewAspectRatio(Collection<UseCase> useCases) {
        for (UseCase useCase : useCases) {
            if (useCase instanceof Preview) {
                Size attachedSurfaceResolution = useCase.getAttachedSurfaceResolution();
                if (attachedSurfaceResolution != null) {
                    this.mCameraControlInternal.setPreviewAspectRatio(new Rational(attachedSurfaceResolution.getWidth(), attachedSurfaceResolution.getHeight()));
                    return;
                }
                return;
            }
        }
    }

    private void clearCameraControlPreviewAspectRatio(Collection<UseCase> removedUseCases) {
        Iterator<UseCase> it = removedUseCases.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof Preview) {
                this.mCameraControlInternal.setPreviewAspectRatio(null);
                return;
            }
        }
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public void detachUseCases(final Collection<UseCase> useCases) {
        if (useCases.isEmpty()) {
            return;
        }
        notifyStateDetachedToUseCases(new ArrayList(useCases));
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m16x88625e11(useCases);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: tryDetachUseCases, reason: merged with bridge method [inline-methods] */
    public void m16x88625e11(Collection<UseCase> toRemove) {
        ArrayList arrayList = new ArrayList();
        for (UseCase useCase : toRemove) {
            if (this.mUseCaseAttachState.isUseCaseAttached(useCase.getName() + useCase.hashCode())) {
                this.mUseCaseAttachState.removeUseCase(useCase.getName() + useCase.hashCode());
                arrayList.add(useCase);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        debugLog("Use cases [" + TextUtils.join(", ", arrayList) + "] now DETACHED for camera");
        clearCameraControlPreviewAspectRatio(arrayList);
        addOrRemoveMeteringRepeatingUseCase();
        if (this.mUseCaseAttachState.getAttachedSessionConfigs().isEmpty()) {
            this.mCameraControlInternal.decrementUseCount();
            resetCaptureSession(false);
            this.mCameraControlInternal.setActive(false);
            this.mCaptureSession = new CaptureSession();
            closeInternal();
            return;
        }
        updateCaptureSessionConfig();
        resetCaptureSession(false);
        if (this.mState == InternalState.OPENED) {
            openCaptureSession();
        }
    }

    private void addOrRemoveMeteringRepeatingUseCase() {
        SessionConfig sessionConfigBuild = this.mUseCaseAttachState.getAttachedBuilder().build();
        CaptureConfig repeatingCaptureConfig = sessionConfigBuild.getRepeatingCaptureConfig();
        int size = repeatingCaptureConfig.getSurfaces().size();
        int size2 = sessionConfigBuild.getSurfaces().size();
        if (sessionConfigBuild.getSurfaces().isEmpty()) {
            return;
        }
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            if (this.mMeteringRepeatingSession == null) {
                this.mMeteringRepeatingSession = new MeteringRepeatingSession(this.mCameraInfoInternal.getCameraCharacteristicsCompat());
            }
            addMeteringRepeating();
        } else {
            if (size2 == 1 && size == 1) {
                removeMeteringRepeating();
                return;
            }
            if (size >= 2) {
                removeMeteringRepeating();
                return;
            }
            Logger.d(TAG, "mMeteringRepeating is ATTACHED, SessionConfig Surfaces: " + size2 + ", CaptureConfig Surfaces: " + size);
        }
    }

    private void removeMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            this.mUseCaseAttachState.setUseCaseDetached(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode());
            this.mUseCaseAttachState.setUseCaseInactive(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode());
            this.mMeteringRepeatingSession.clear();
            this.mMeteringRepeatingSession = null;
        }
    }

    private void addMeteringRepeating() {
        if (this.mMeteringRepeatingSession != null) {
            this.mUseCaseAttachState.setUseCaseAttached(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode(), this.mMeteringRepeatingSession.getSessionConfig());
            this.mUseCaseAttachState.setUseCaseActive(this.mMeteringRepeatingSession.getName() + this.mMeteringRepeatingSession.hashCode(), this.mMeteringRepeatingSession.getSessionConfig());
        }
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraInfoInternal getCameraInfoInternal() {
        return this.mCameraInfoInternal;
    }

    void openCameraDevice(boolean fromScheduledCameraReopen) {
        if (!fromScheduledCameraReopen) {
            this.mStateCallback.resetReopenMonitor();
        }
        this.mStateCallback.cancelScheduledReopen();
        if (!this.mCameraAvailability.isCameraAvailable() || !this.mCameraStateRegistry.tryOpenCamera(this)) {
            debugLog("No cameras available. Waiting for available camera before opening camera.");
            setState(InternalState.PENDING_OPEN);
            return;
        }
        setState(InternalState.OPENING);
        debugLog("Opening camera.");
        try {
            this.mCameraManager.openCamera(this.mCameraInfoInternal.getCameraId(), this.mExecutor, createDeviceStateCallback());
        } catch (CameraAccessExceptionCompat e) {
            debugLog("Unable to open camera due to " + e.getMessage());
            if (e.getReason() != 10001) {
                return;
            }
            setState(InternalState.INITIALIZED);
        } catch (SecurityException e2) {
            debugLog("Unable to open camera due to " + e2.getMessage());
            setState(InternalState.REOPENING);
            this.mStateCallback.scheduleCameraReopen();
        }
    }

    void updateCaptureSessionConfig() {
        SessionConfig.ValidatingBuilder activeAndAttachedBuilder = this.mUseCaseAttachState.getActiveAndAttachedBuilder();
        if (activeAndAttachedBuilder.isValid()) {
            activeAndAttachedBuilder.add(this.mCameraControlSessionConfig);
            this.mCaptureSession.setSessionConfig(activeAndAttachedBuilder.build());
            return;
        }
        this.mCaptureSession.setSessionConfig(this.mCameraControlSessionConfig);
    }

    void openCaptureSession() {
        Preconditions.checkState(this.mState == InternalState.OPENED);
        SessionConfig.ValidatingBuilder attachedBuilder = this.mUseCaseAttachState.getAttachedBuilder();
        if (!attachedBuilder.isValid()) {
            debugLog("Unable to create capture session due to conflicting configurations");
        } else {
            Futures.addCallback(this.mCaptureSession.open(attachedBuilder.build(), (CameraDevice) Preconditions.checkNotNull(this.mCameraDevice), this.mCaptureSessionOpenerBuilder.build()), new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl.2
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(Void result) {
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable t) {
                    if (t instanceof CameraAccessException) {
                        Camera2CameraImpl.this.debugLog("Unable to configure camera due to " + t.getMessage());
                        return;
                    }
                    if (t instanceof CancellationException) {
                        Camera2CameraImpl.this.debugLog("Unable to configure camera cancelled");
                        return;
                    }
                    if (t instanceof DeferrableSurface.SurfaceClosedException) {
                        SessionConfig sessionConfigFindSessionConfigForSurface = Camera2CameraImpl.this.findSessionConfigForSurface(((DeferrableSurface.SurfaceClosedException) t).getDeferrableSurface());
                        if (sessionConfigFindSessionConfigForSurface != null) {
                            Camera2CameraImpl.this.postSurfaceClosedError(sessionConfigFindSessionConfigForSurface);
                            return;
                        }
                        return;
                    }
                    if (t instanceof TimeoutException) {
                        Logger.e(Camera2CameraImpl.TAG, "Unable to configure camera " + Camera2CameraImpl.this.mCameraInfoInternal.getCameraId() + ", timeout!");
                        return;
                    }
                    throw new RuntimeException(t);
                }
            }, this.mExecutor);
        }
    }

    private boolean isLegacyDevice() {
        return ((Camera2CameraInfoImpl) getCameraInfoInternal()).getSupportedHardwareLevel() == 2;
    }

    SessionConfig findSessionConfigForSurface(DeferrableSurface surface) {
        for (SessionConfig sessionConfig : this.mUseCaseAttachState.getAttachedSessionConfigs()) {
            if (sessionConfig.getSurfaces().contains(surface)) {
                return sessionConfig;
            }
        }
        return null;
    }

    void postSurfaceClosedError(final SessionConfig sessionConfig) {
        ScheduledExecutorService scheduledExecutorServiceMainThreadExecutor = CameraXExecutors.mainThreadExecutor();
        List<SessionConfig.ErrorListener> errorListeners = sessionConfig.getErrorListeners();
        if (errorListeners.isEmpty()) {
            return;
        }
        final SessionConfig.ErrorListener errorListener = errorListeners.get(0);
        debugLog("Posting surface closed", new Throwable());
        scheduledExecutorServiceMainThreadExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                errorListener.onError(sessionConfig, SessionConfig.SessionError.SESSION_ERROR_SURFACE_NEEDS_RESET);
            }
        });
    }

    void resetCaptureSession(boolean abortInFlightCaptures) {
        Preconditions.checkState(this.mCaptureSession != null);
        debugLog("Resetting Capture Session");
        CaptureSession captureSession = this.mCaptureSession;
        SessionConfig sessionConfig = captureSession.getSessionConfig();
        List<CaptureConfig> captureConfigs = captureSession.getCaptureConfigs();
        CaptureSession captureSession2 = new CaptureSession();
        this.mCaptureSession = captureSession2;
        captureSession2.setSessionConfig(sessionConfig);
        this.mCaptureSession.issueCaptureRequests(captureConfigs);
        releaseSession(captureSession, abortInFlightCaptures);
    }

    private CameraDevice.StateCallback createDeviceStateCallback() {
        ArrayList arrayList = new ArrayList(this.mUseCaseAttachState.getAttachedBuilder().build().getDeviceStateCallbacks());
        arrayList.add(this.mCaptureSessionRepository.getCameraStateCallback());
        arrayList.add(this.mStateCallback);
        return CameraDeviceStateCallbacks.createComboCallback(arrayList);
    }

    private boolean checkAndAttachRepeatingSurface(CaptureConfig.Builder captureConfigBuilder) {
        if (!captureConfigBuilder.getSurfaces().isEmpty()) {
            Logger.w(TAG, "The capture config builder already has surface inside.");
            return false;
        }
        Iterator<SessionConfig> it = this.mUseCaseAttachState.getActiveAndAttachedSessionConfigs().iterator();
        while (it.hasNext()) {
            List<DeferrableSurface> surfaces = it.next().getRepeatingCaptureConfig().getSurfaces();
            if (!surfaces.isEmpty()) {
                Iterator<DeferrableSurface> it2 = surfaces.iterator();
                while (it2.hasNext()) {
                    captureConfigBuilder.addSurface(it2.next());
                }
            }
        }
        if (!captureConfigBuilder.getSurfaces().isEmpty()) {
            return true;
        }
        Logger.w(TAG, "Unable to find a repeating surface to attach to CaptureConfig");
        return false;
    }

    @Override // androidx.camera.core.impl.CameraInternal
    public CameraControlInternal getCameraControlInternal() {
        return this.mCameraControlInternal;
    }

    void submitCaptureRequests(List<CaptureConfig> captureConfigs) {
        ArrayList arrayList = new ArrayList();
        for (CaptureConfig captureConfig : captureConfigs) {
            CaptureConfig.Builder builderFrom = CaptureConfig.Builder.from(captureConfig);
            if (!captureConfig.getSurfaces().isEmpty() || !captureConfig.isUseRepeatingSurface() || checkAndAttachRepeatingSurface(builderFrom)) {
                arrayList.add(builderFrom.build());
            }
        }
        debugLog("Issue capture request");
        this.mCaptureSession.issueCaptureRequests(arrayList);
    }

    public String toString() {
        return String.format(Locale.US, "Camera@%x[id=%s]", Integer.valueOf(hashCode()), this.mCameraInfoInternal.getCameraId());
    }

    void debugLog(String msg) {
        debugLog(msg, null);
    }

    private void debugLog(String msg, Throwable throwable) {
        Logger.d(TAG, String.format("{%s} %s", toString(), msg), throwable);
    }

    void setState(InternalState state) {
        CameraInternal.State state2;
        debugLog("Transitioning camera internal state: " + this.mState + " --> " + state);
        this.mState = state;
        switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[state.ordinal()]) {
            case 1:
                state2 = CameraInternal.State.CLOSED;
                break;
            case 2:
                state2 = CameraInternal.State.CLOSING;
                break;
            case 3:
                state2 = CameraInternal.State.OPEN;
                break;
            case 4:
            case 5:
                state2 = CameraInternal.State.OPENING;
                break;
            case 6:
                state2 = CameraInternal.State.PENDING_OPEN;
                break;
            case 7:
                state2 = CameraInternal.State.RELEASING;
                break;
            case 8:
                state2 = CameraInternal.State.RELEASED;
                break;
            default:
                throw new IllegalStateException("Unknown state: " + state);
        }
        this.mCameraStateRegistry.markCameraState(this, state2);
        this.mObservableState.postValue(state2);
    }

    final class StateCallback extends CameraDevice.StateCallback {
        static final int REOPEN_DELAY_MS = 700;
        private final CameraReopenMonitor mCameraReopenMonitor = new CameraReopenMonitor();
        private final Executor mExecutor;
        ScheduledFuture<?> mScheduledReopenHandle;
        private ScheduledReopen mScheduledReopenRunnable;
        private final ScheduledExecutorService mScheduler;

        StateCallback(Executor executor, ScheduledExecutorService scheduler) {
            this.mExecutor = executor;
            this.mScheduler = scheduler;
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) throws CameraAccessException {
            Camera2CameraImpl.this.debugLog("CameraDevice.onOpened()");
            Camera2CameraImpl.this.mCameraDevice = cameraDevice;
            Camera2CameraImpl.this.updateDefaultRequestBuilderToCameraControl(cameraDevice);
            Camera2CameraImpl.this.mCameraDeviceError = 0;
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
            if (i == 2 || i == 7) {
                Preconditions.checkState(Camera2CameraImpl.this.isSessionCloseComplete());
                Camera2CameraImpl.this.mCameraDevice.close();
                Camera2CameraImpl.this.mCameraDevice = null;
            } else if (i == 4 || i == 5) {
                Camera2CameraImpl.this.setState(InternalState.OPENED);
                Camera2CameraImpl.this.openCaptureSession();
            } else {
                throw new IllegalStateException("onOpened() should not be possible from state: " + Camera2CameraImpl.this.mState);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onClosed()");
            Preconditions.checkState(Camera2CameraImpl.this.mCameraDevice == null, "Unexpected onClose callback on camera device: " + cameraDevice);
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
            if (i != 2) {
                if (i == 5) {
                    if (Camera2CameraImpl.this.mCameraDeviceError != 0) {
                        Camera2CameraImpl.this.debugLog("Camera closed due to error: " + Camera2CameraImpl.getErrorMessage(Camera2CameraImpl.this.mCameraDeviceError));
                        scheduleCameraReopen();
                        return;
                    }
                    Camera2CameraImpl.this.openCameraDevice(false);
                    return;
                }
                if (i != 7) {
                    throw new IllegalStateException("Camera closed while in state: " + Camera2CameraImpl.this.mState);
                }
            }
            Preconditions.checkState(Camera2CameraImpl.this.isSessionCloseComplete());
            Camera2CameraImpl.this.finishClose();
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            Camera2CameraImpl.this.debugLog("CameraDevice.onDisconnected()");
            onError(cameraDevice, 1);
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int error) {
            Camera2CameraImpl.this.mCameraDevice = cameraDevice;
            Camera2CameraImpl.this.mCameraDeviceError = error;
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$Camera2CameraImpl$InternalState[Camera2CameraImpl.this.mState.ordinal()];
            if (i != 2) {
                if (i == 3 || i == 4 || i == 5) {
                    Logger.d(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will attempt recovering from error.", cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(error), Camera2CameraImpl.this.mState.name()));
                    handleErrorOnOpen(cameraDevice, error);
                    return;
                } else if (i != 7) {
                    throw new IllegalStateException("onError() should not be possible from state: " + Camera2CameraImpl.this.mState);
                }
            }
            Logger.e(Camera2CameraImpl.TAG, String.format("CameraDevice.onError(): %s failed with %s while in %s state. Will finish closing camera.", cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(error), Camera2CameraImpl.this.mState.name()));
            Camera2CameraImpl.this.closeCamera(false);
        }

        private void handleErrorOnOpen(CameraDevice cameraDevice, int error) {
            Preconditions.checkState(Camera2CameraImpl.this.mState == InternalState.OPENING || Camera2CameraImpl.this.mState == InternalState.OPENED || Camera2CameraImpl.this.mState == InternalState.REOPENING, "Attempt to handle open error from non open state: " + Camera2CameraImpl.this.mState);
            if (error == 1 || error == 2 || error == 4) {
                Logger.d(Camera2CameraImpl.TAG, String.format("Attempt to reopen camera[%s] after error[%s]", cameraDevice.getId(), Camera2CameraImpl.getErrorMessage(error)));
                reopenCameraAfterError();
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Error observed on open (or opening) camera device " + cameraDevice.getId() + ": " + Camera2CameraImpl.getErrorMessage(error) + " closing camera.");
            Camera2CameraImpl.this.setState(InternalState.CLOSING);
            Camera2CameraImpl.this.closeCamera(false);
        }

        private void reopenCameraAfterError() {
            Preconditions.checkState(Camera2CameraImpl.this.mCameraDeviceError != 0, "Can only reopen camera device after error if the camera device is actually in an error state.");
            Camera2CameraImpl.this.setState(InternalState.REOPENING);
            Camera2CameraImpl.this.closeCamera(false);
        }

        void scheduleCameraReopen() {
            Preconditions.checkState(this.mScheduledReopenRunnable == null);
            Preconditions.checkState(this.mScheduledReopenHandle == null);
            if (this.mCameraReopenMonitor.canScheduleCameraReopen()) {
                this.mScheduledReopenRunnable = new ScheduledReopen(this.mExecutor);
                Camera2CameraImpl.this.debugLog("Attempting camera re-open in 700ms: " + this.mScheduledReopenRunnable);
                this.mScheduledReopenHandle = this.mScheduler.schedule(this.mScheduledReopenRunnable, 700L, TimeUnit.MILLISECONDS);
                return;
            }
            Logger.e(Camera2CameraImpl.TAG, "Camera reopening attempted for 10000ms without success.");
            Camera2CameraImpl.this.setState(InternalState.INITIALIZED);
        }

        boolean cancelScheduledReopen() {
            if (this.mScheduledReopenHandle == null) {
                return false;
            }
            Camera2CameraImpl.this.debugLog("Cancelling scheduled re-open: " + this.mScheduledReopenRunnable);
            this.mScheduledReopenRunnable.cancel();
            this.mScheduledReopenRunnable = null;
            this.mScheduledReopenHandle.cancel(false);
            this.mScheduledReopenHandle = null;
            return true;
        }

        void resetReopenMonitor() {
            this.mCameraReopenMonitor.reset();
        }

        class ScheduledReopen implements Runnable {
            private boolean mCancelled = false;
            private Executor mExecutor;

            ScheduledReopen(Executor executor) {
                this.mExecutor = executor;
            }

            void cancel() {
                this.mCancelled = true;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraImpl$StateCallback$ScheduledReopen$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m26x10b58748();
                    }
                });
            }

            /* renamed from: lambda$run$0$androidx-camera-camera2-internal-Camera2CameraImpl$StateCallback$ScheduledReopen, reason: not valid java name */
            public /* synthetic */ void m26x10b58748() {
                if (this.mCancelled) {
                    return;
                }
                Preconditions.checkState(Camera2CameraImpl.this.mState == InternalState.REOPENING);
                Camera2CameraImpl.this.openCameraDevice(true);
            }
        }

        class CameraReopenMonitor {
            static final int INVALID_TIME = -1;
            static final int REOPEN_LIMIT_MS = 10000;
            private long mFirstReopenTime = -1;

            CameraReopenMonitor() {
            }

            boolean canScheduleCameraReopen() {
                long jUptimeMillis = SystemClock.uptimeMillis();
                long j = this.mFirstReopenTime;
                if (j == -1) {
                    this.mFirstReopenTime = jUptimeMillis;
                    return true;
                }
                if (!(jUptimeMillis - j >= WorkRequest.MIN_BACKOFF_MILLIS)) {
                    return true;
                }
                reset();
                return false;
            }

            void reset() {
                this.mFirstReopenTime = -1L;
            }
        }
    }

    void updateDefaultRequestBuilderToCameraControl(CameraDevice cameraDevice) throws CameraAccessException {
        try {
            this.mCameraControlInternal.setDefaultRequestBuilder(cameraDevice.createCaptureRequest(this.mCameraControlInternal.getDefaultTemplate()));
        } catch (CameraAccessException e) {
            Logger.e(TAG, "fail to create capture request.", e);
        }
    }

    final class CameraAvailability extends CameraManager.AvailabilityCallback implements CameraStateRegistry.OnOpenAvailableListener {
        private boolean mCameraAvailable = true;
        private final String mCameraId;

        CameraAvailability(String cameraId) {
            this.mCameraId = cameraId;
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(String cameraId) {
            if (this.mCameraId.equals(cameraId)) {
                this.mCameraAvailable = true;
                if (Camera2CameraImpl.this.mState == InternalState.PENDING_OPEN) {
                    Camera2CameraImpl.this.openCameraDevice(false);
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(String cameraId) {
            if (this.mCameraId.equals(cameraId)) {
                this.mCameraAvailable = false;
            }
        }

        @Override // androidx.camera.core.impl.CameraStateRegistry.OnOpenAvailableListener
        public void onOpenAvailable() {
            if (Camera2CameraImpl.this.mState == InternalState.PENDING_OPEN) {
                Camera2CameraImpl.this.openCameraDevice(false);
            }
        }

        boolean isCameraAvailable() {
            return this.mCameraAvailable;
        }
    }

    final class ControlUpdateListenerInternal implements CameraControlInternal.ControlUpdateCallback {
        ControlUpdateListenerInternal() {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal.ControlUpdateCallback
        public void onCameraControlUpdateSessionConfig(SessionConfig sessionConfig) {
            Camera2CameraImpl.this.mCameraControlSessionConfig = (SessionConfig) Preconditions.checkNotNull(sessionConfig);
            Camera2CameraImpl.this.updateCaptureSessionConfig();
        }

        @Override // androidx.camera.core.impl.CameraControlInternal.ControlUpdateCallback
        public void onCameraControlCaptureRequests(List<CaptureConfig> captureConfigs) {
            Camera2CameraImpl.this.submitCaptureRequests((List) Preconditions.checkNotNull(captureConfigs));
        }
    }
}
