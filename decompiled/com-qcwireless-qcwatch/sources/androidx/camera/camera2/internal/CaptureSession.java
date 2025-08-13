package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.CameraEventCallbacks;
import androidx.camera.camera2.internal.CameraBurstCaptureCallback;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionStateCallbacks;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.camera2.internal.compat.workaround.StillCaptureFlow;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;

/* loaded from: classes.dex */
final class CaptureSession {
    private static final String TAG = "CaptureSession";
    private static final long TIMEOUT_GET_SURFACE_IN_MS = 5000;
    CallbackToFutureAdapter.Completer<Void> mReleaseCompleter;
    ListenableFuture<Void> mReleaseFuture;
    volatile SessionConfig mSessionConfig;
    State mState;
    SynchronizedCaptureSession mSynchronizedCaptureSession;
    SynchronizedCaptureSessionOpener mSynchronizedCaptureSessionOpener;
    final Object mStateLock = new Object();
    private final List<CaptureConfig> mCaptureConfigs = new ArrayList();
    private final CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() { // from class: androidx.camera.camera2.internal.CaptureSession.1
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
        }
    };
    volatile Config mCameraEventOnRepeatingOptions = OptionsBundle.emptyBundle();
    CameraEventCallbacks mCameraEventCallbacks = CameraEventCallbacks.createEmptyCallback();
    private Map<DeferrableSurface, Surface> mConfiguredSurfaceMap = new HashMap();
    List<DeferrableSurface> mConfiguredDeferrableSurfaces = Collections.emptyList();
    final StillCaptureFlow mStillCaptureFlow = new StillCaptureFlow();
    private final StateCallback mCaptureSessionStateCallback = new StateCallback();

    enum State {
        UNINITIALIZED,
        INITIALIZED,
        GET_SURFACE,
        OPENING,
        OPENED,
        CLOSED,
        RELEASING,
        RELEASED
    }

    CaptureSession() {
        this.mState = State.UNINITIALIZED;
        this.mState = State.INITIALIZED;
    }

    SessionConfig getSessionConfig() {
        SessionConfig sessionConfig;
        synchronized (this.mStateLock) {
            sessionConfig = this.mSessionConfig;
        }
        return sessionConfig;
    }

    /* renamed from: androidx.camera.camera2.internal.CaptureSession$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State = iArr;
            try {
                iArr[State.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.GET_SURFACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.OPENING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.OPENED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.CLOSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.RELEASING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[State.RELEASED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    void setSessionConfig(SessionConfig sessionConfig) {
        synchronized (this.mStateLock) {
            switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()]) {
                case 1:
                    throw new IllegalStateException("setSessionConfig() should not be possible in state: " + this.mState);
                case 2:
                case 3:
                case 4:
                    this.mSessionConfig = sessionConfig;
                    break;
                case 5:
                    this.mSessionConfig = sessionConfig;
                    if (!this.mConfiguredSurfaceMap.keySet().containsAll(sessionConfig.getSurfaces())) {
                        Logger.e(TAG, "Does not have the proper configured lists");
                        return;
                    } else {
                        Logger.d(TAG, "Attempting to submit CaptureRequest after setting");
                        issueRepeatingCaptureRequests();
                        break;
                    }
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Session configuration cannot be set on a closed/released session.");
            }
        }
    }

    ListenableFuture<Void> open(final SessionConfig sessionConfig, final CameraDevice cameraDevice, SynchronizedCaptureSessionOpener opener) {
        synchronized (this.mStateLock) {
            if (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()] == 2) {
                this.mState = State.GET_SURFACE;
                ArrayList arrayList = new ArrayList(sessionConfig.getSurfaces());
                this.mConfiguredDeferrableSurfaces = arrayList;
                this.mSynchronizedCaptureSessionOpener = opener;
                FutureChain futureChainTransformAsync = FutureChain.from(opener.startWithDeferrableSurface(arrayList, 5000L)).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda1
                    @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                    public final ListenableFuture apply(Object obj) {
                        return this.f$0.m28lambda$open$0$androidxcameracamera2internalCaptureSession(sessionConfig, cameraDevice, (List) obj);
                    }
                }, this.mSynchronizedCaptureSessionOpener.getExecutor());
                Futures.addCallback(futureChainTransformAsync, new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.CaptureSession.2
                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onSuccess(Void result) {
                    }

                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onFailure(Throwable t) {
                        CaptureSession.this.mSynchronizedCaptureSessionOpener.stop();
                        synchronized (CaptureSession.this.mStateLock) {
                            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()];
                            if ((i == 4 || i == 6 || i == 7) && !(t instanceof CancellationException)) {
                                Logger.w(CaptureSession.TAG, "Opening session with fail " + CaptureSession.this.mState, t);
                                CaptureSession.this.finishClose();
                            }
                        }
                    }
                }, this.mSynchronizedCaptureSessionOpener.getExecutor());
                return Futures.nonCancellationPropagating(futureChainTransformAsync);
            }
            Logger.e(TAG, "Open not allowed in state: " + this.mState);
            return Futures.immediateFailedFuture(new IllegalStateException("open() should not allow the state: " + this.mState));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: openCaptureSession, reason: merged with bridge method [inline-methods] */
    public ListenableFuture<Void> m28lambda$open$0$androidxcameracamera2internalCaptureSession(List<Surface> configuredSurfaces, SessionConfig sessionConfig, CameraDevice cameraDevice) {
        synchronized (this.mStateLock) {
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()];
            if (i != 1 && i != 2) {
                if (i == 3) {
                    this.mConfiguredSurfaceMap.clear();
                    for (int i2 = 0; i2 < configuredSurfaces.size(); i2++) {
                        this.mConfiguredSurfaceMap.put(this.mConfiguredDeferrableSurfaces.get(i2), configuredSurfaces.get(i2));
                    }
                    ArrayList arrayList = new ArrayList(new HashSet(configuredSurfaces));
                    this.mState = State.OPENING;
                    Logger.d(TAG, "Opening capture session.");
                    SynchronizedCaptureSession.StateCallback stateCallbackCreateComboCallback = SynchronizedCaptureSessionStateCallbacks.createComboCallback(this.mCaptureSessionStateCallback, new SynchronizedCaptureSessionStateCallbacks.Adapter(sessionConfig.getSessionStateCallbacks()));
                    CameraEventCallbacks cameraEventCallback = new Camera2ImplConfig(sessionConfig.getImplementationOptions()).getCameraEventCallback(CameraEventCallbacks.createEmptyCallback());
                    this.mCameraEventCallbacks = cameraEventCallback;
                    List<CaptureConfig> listOnPresetSession = cameraEventCallback.createComboCallback().onPresetSession();
                    CaptureConfig.Builder builderFrom = CaptureConfig.Builder.from(sessionConfig.getRepeatingCaptureConfig());
                    Iterator<CaptureConfig> it = listOnPresetSession.iterator();
                    while (it.hasNext()) {
                        builderFrom.addImplementationOptions(it.next().getImplementationOptions());
                    }
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(new OutputConfigurationCompat((Surface) it2.next()));
                    }
                    SessionConfigurationCompat sessionConfigurationCompatCreateSessionConfigurationCompat = this.mSynchronizedCaptureSessionOpener.createSessionConfigurationCompat(0, arrayList2, stateCallbackCreateComboCallback);
                    try {
                        CaptureRequest captureRequestBuildWithoutTarget = Camera2CaptureRequestBuilder.buildWithoutTarget(builderFrom.build(), cameraDevice);
                        if (captureRequestBuildWithoutTarget != null) {
                            sessionConfigurationCompatCreateSessionConfigurationCompat.setSessionParameters(captureRequestBuildWithoutTarget);
                        }
                        return this.mSynchronizedCaptureSessionOpener.openCaptureSession(cameraDevice, sessionConfigurationCompatCreateSessionConfigurationCompat, this.mConfiguredDeferrableSurfaces);
                    } catch (CameraAccessException e) {
                        return Futures.immediateFailedFuture(e);
                    }
                }
                if (i != 5) {
                    return Futures.immediateFailedFuture(new CancellationException("openCaptureSession() not execute in state: " + this.mState));
                }
            }
            return Futures.immediateFailedFuture(new IllegalStateException("openCaptureSession() should not be possible in state: " + this.mState));
        }
    }

    void close() {
        synchronized (this.mStateLock) {
            int i = AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()];
            if (i == 1) {
                throw new IllegalStateException("close() should not be possible in state: " + this.mState);
            }
            if (i == 2) {
                this.mState = State.RELEASED;
            } else if (i == 3) {
                Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                this.mSynchronizedCaptureSessionOpener.stop();
                this.mState = State.RELEASED;
            } else if (i == 4) {
                Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                this.mSynchronizedCaptureSessionOpener.stop();
                this.mState = State.CLOSED;
                this.mSessionConfig = null;
            } else if (i == 5) {
                if (this.mSessionConfig != null) {
                    List<CaptureConfig> listOnDisableSession = this.mCameraEventCallbacks.createComboCallback().onDisableSession();
                    if (!listOnDisableSession.isEmpty()) {
                        try {
                            issueCaptureRequests(setupConfiguredSurface(listOnDisableSession));
                        } catch (IllegalStateException e) {
                            Logger.e(TAG, "Unable to issue the request before close the capture session", e);
                        }
                    }
                }
                Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                this.mSynchronizedCaptureSessionOpener.stop();
                this.mState = State.CLOSED;
                this.mSessionConfig = null;
            }
        }
    }

    ListenableFuture<Void> release(boolean abortInFlightCaptures) {
        synchronized (this.mStateLock) {
            switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()]) {
                case 1:
                    throw new IllegalStateException("release() should not be possible in state: " + this.mState);
                case 3:
                    Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                    this.mSynchronizedCaptureSessionOpener.stop();
                case 2:
                    this.mState = State.RELEASED;
                    return Futures.immediateFuture(null);
                case 5:
                case 6:
                    SynchronizedCaptureSession synchronizedCaptureSession = this.mSynchronizedCaptureSession;
                    if (synchronizedCaptureSession != null) {
                        if (abortInFlightCaptures) {
                            try {
                                synchronizedCaptureSession.abortCaptures();
                            } catch (CameraAccessException e) {
                                Logger.e(TAG, "Unable to abort captures.", e);
                            }
                        }
                        this.mSynchronizedCaptureSession.close();
                    }
                case 4:
                    this.mState = State.RELEASING;
                    Preconditions.checkNotNull(this.mSynchronizedCaptureSessionOpener, "The Opener shouldn't null in state:" + this.mState);
                    if (this.mSynchronizedCaptureSessionOpener.stop()) {
                        finishClose();
                        return Futures.immediateFuture(null);
                    }
                case 7:
                    if (this.mReleaseFuture == null) {
                        this.mReleaseFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda2
                            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                                return this.f$0.m29lambda$release$1$androidxcameracamera2internalCaptureSession(completer);
                            }
                        });
                    }
                    return this.mReleaseFuture;
                default:
                    return Futures.immediateFuture(null);
            }
        }
    }

    /* renamed from: lambda$release$1$androidx-camera-camera2-internal-CaptureSession, reason: not valid java name */
    public /* synthetic */ Object m29lambda$release$1$androidxcameracamera2internalCaptureSession(CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.mStateLock) {
            Preconditions.checkState(this.mReleaseCompleter == null, "Release completer expected to be null");
            this.mReleaseCompleter = completer;
            str = "Release[session=" + this + "]";
        }
        return str;
    }

    void issueCaptureRequests(List<CaptureConfig> captureConfigs) {
        synchronized (this.mStateLock) {
            switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[this.mState.ordinal()]) {
                case 1:
                    throw new IllegalStateException("issueCaptureRequests() should not be possible in state: " + this.mState);
                case 2:
                case 3:
                case 4:
                    this.mCaptureConfigs.addAll(captureConfigs);
                    break;
                case 5:
                    this.mCaptureConfigs.addAll(captureConfigs);
                    issuePendingCaptureRequest();
                    break;
                case 6:
                case 7:
                case 8:
                    throw new IllegalStateException("Cannot issue capture request on a closed/released session.");
            }
        }
    }

    List<CaptureConfig> getCaptureConfigs() {
        List<CaptureConfig> listUnmodifiableList;
        synchronized (this.mStateLock) {
            listUnmodifiableList = Collections.unmodifiableList(this.mCaptureConfigs);
        }
        return listUnmodifiableList;
    }

    State getState() {
        State state;
        synchronized (this.mStateLock) {
            state = this.mState;
        }
        return state;
    }

    void finishClose() {
        if (this.mState == State.RELEASED) {
            Logger.d(TAG, "Skipping finishClose due to being state RELEASED.");
            return;
        }
        this.mState = State.RELEASED;
        this.mSynchronizedCaptureSession = null;
        CallbackToFutureAdapter.Completer<Void> completer = this.mReleaseCompleter;
        if (completer != null) {
            completer.set(null);
            this.mReleaseCompleter = null;
        }
    }

    void issueRepeatingCaptureRequests() {
        if (this.mSessionConfig == null) {
            Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no configuration case.");
            return;
        }
        CaptureConfig repeatingCaptureConfig = this.mSessionConfig.getRepeatingCaptureConfig();
        if (repeatingCaptureConfig.getSurfaces().isEmpty()) {
            Logger.d(TAG, "Skipping issueRepeatingCaptureRequests for no surface.");
            try {
                this.mSynchronizedCaptureSession.stopRepeating();
                return;
            } catch (CameraAccessException e) {
                Logger.e(TAG, "Unable to access camera: " + e.getMessage());
                Thread.dumpStack();
                return;
            }
        }
        try {
            Logger.d(TAG, "Issuing request for session.");
            CaptureConfig.Builder builderFrom = CaptureConfig.Builder.from(repeatingCaptureConfig);
            this.mCameraEventOnRepeatingOptions = mergeOptions(this.mCameraEventCallbacks.createComboCallback().onRepeating());
            builderFrom.addImplementationOptions(this.mCameraEventOnRepeatingOptions);
            CaptureRequest captureRequestBuild = Camera2CaptureRequestBuilder.build(builderFrom.build(), this.mSynchronizedCaptureSession.getDevice(), this.mConfiguredSurfaceMap);
            if (captureRequestBuild == null) {
                Logger.d(TAG, "Skipping issuing empty request for session.");
            } else {
                this.mSynchronizedCaptureSession.setSingleRepeatingRequest(captureRequestBuild, createCamera2CaptureCallback(repeatingCaptureConfig.getCameraCaptureCallbacks(), this.mCaptureCallback));
            }
        } catch (CameraAccessException e2) {
            Logger.e(TAG, "Unable to access camera: " + e2.getMessage());
            Thread.dumpStack();
        }
    }

    void issuePendingCaptureRequest() {
        if (this.mCaptureConfigs.isEmpty()) {
            return;
        }
        try {
            issueBurstCaptureRequest(this.mCaptureConfigs);
        } finally {
            this.mCaptureConfigs.clear();
        }
    }

    void issueBurstCaptureRequest(List<CaptureConfig> captureConfigs) {
        boolean z;
        if (captureConfigs.isEmpty()) {
            return;
        }
        try {
            CameraBurstCaptureCallback cameraBurstCaptureCallback = new CameraBurstCaptureCallback();
            ArrayList arrayList = new ArrayList();
            Logger.d(TAG, "Issuing capture request.");
            boolean z2 = false;
            for (CaptureConfig captureConfig : captureConfigs) {
                if (captureConfig.getSurfaces().isEmpty()) {
                    Logger.d(TAG, "Skipping issuing empty capture request.");
                } else {
                    Iterator<DeferrableSurface> it = captureConfig.getSurfaces().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        }
                        DeferrableSurface next = it.next();
                        if (!this.mConfiguredSurfaceMap.containsKey(next)) {
                            Logger.d(TAG, "Skipping capture request with invalid surface: " + next);
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        if (captureConfig.getTemplateType() == 2) {
                            z2 = true;
                        }
                        CaptureConfig.Builder builderFrom = CaptureConfig.Builder.from(captureConfig);
                        if (this.mSessionConfig != null) {
                            builderFrom.addImplementationOptions(this.mSessionConfig.getRepeatingCaptureConfig().getImplementationOptions());
                        }
                        builderFrom.addImplementationOptions(this.mCameraEventOnRepeatingOptions);
                        builderFrom.addImplementationOptions(captureConfig.getImplementationOptions());
                        CaptureRequest captureRequestBuild = Camera2CaptureRequestBuilder.build(builderFrom.build(), this.mSynchronizedCaptureSession.getDevice(), this.mConfiguredSurfaceMap);
                        if (captureRequestBuild == null) {
                            Logger.d(TAG, "Skipping issuing request without surface.");
                            return;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<CameraCaptureCallback> it2 = captureConfig.getCameraCaptureCallbacks().iterator();
                        while (it2.hasNext()) {
                            CaptureCallbackConverter.toCaptureCallback(it2.next(), arrayList2);
                        }
                        cameraBurstCaptureCallback.addCamera2Callbacks(captureRequestBuild, arrayList2);
                        arrayList.add(captureRequestBuild);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                if (this.mStillCaptureFlow.shouldStopRepeatingBeforeCapture(arrayList, z2)) {
                    this.mSynchronizedCaptureSession.stopRepeating();
                    cameraBurstCaptureCallback.setCaptureSequenceCallback(new CameraBurstCaptureCallback.CaptureSequenceCallback() { // from class: androidx.camera.camera2.internal.CaptureSession$$ExternalSyntheticLambda0
                        @Override // androidx.camera.camera2.internal.CameraBurstCaptureCallback.CaptureSequenceCallback
                        public final void onCaptureSequenceCompletedOrAborted(CameraCaptureSession cameraCaptureSession, int i, boolean z3) {
                            this.f$0.m27xaccd0633(cameraCaptureSession, i, z3);
                        }
                    });
                }
                this.mSynchronizedCaptureSession.captureBurstRequests(arrayList, cameraBurstCaptureCallback);
                return;
            }
            Logger.d(TAG, "Skipping issuing burst request due to no valid request elements");
        } catch (CameraAccessException e) {
            Logger.e(TAG, "Unable to access camera: " + e.getMessage());
            Thread.dumpStack();
        }
    }

    /* renamed from: lambda$issueBurstCaptureRequest$2$androidx-camera-camera2-internal-CaptureSession, reason: not valid java name */
    public /* synthetic */ void m27xaccd0633(CameraCaptureSession cameraCaptureSession, int i, boolean z) {
        synchronized (this.mStateLock) {
            if (this.mState == State.OPENED) {
                issueRepeatingCaptureRequests();
            }
        }
    }

    void cancelIssuedCaptureRequests() {
        if (this.mCaptureConfigs.isEmpty()) {
            return;
        }
        Iterator<CaptureConfig> it = this.mCaptureConfigs.iterator();
        while (it.hasNext()) {
            Iterator<CameraCaptureCallback> it2 = it.next().getCameraCaptureCallbacks().iterator();
            while (it2.hasNext()) {
                it2.next().onCaptureCancelled();
            }
        }
        this.mCaptureConfigs.clear();
    }

    private CameraCaptureSession.CaptureCallback createCamera2CaptureCallback(List<CameraCaptureCallback> cameraCaptureCallbacks, CameraCaptureSession.CaptureCallback... additionalCallbacks) {
        ArrayList arrayList = new ArrayList(cameraCaptureCallbacks.size() + additionalCallbacks.length);
        Iterator<CameraCaptureCallback> it = cameraCaptureCallbacks.iterator();
        while (it.hasNext()) {
            arrayList.add(CaptureCallbackConverter.toCaptureCallback(it.next()));
        }
        Collections.addAll(arrayList, additionalCallbacks);
        return Camera2CaptureCallbacks.createComboCallback(arrayList);
    }

    private static Config mergeOptions(List<CaptureConfig> captureConfigList) {
        MutableOptionsBundle mutableOptionsBundleCreate = MutableOptionsBundle.create();
        Iterator<CaptureConfig> it = captureConfigList.iterator();
        while (it.hasNext()) {
            Config implementationOptions = it.next().getImplementationOptions();
            for (Config.Option<?> option : implementationOptions.listOptions()) {
                Object objRetrieveOption = implementationOptions.retrieveOption(option, null);
                if (mutableOptionsBundleCreate.containsOption(option)) {
                    Object objRetrieveOption2 = mutableOptionsBundleCreate.retrieveOption(option, null);
                    if (!Objects.equals(objRetrieveOption2, objRetrieveOption)) {
                        Logger.d(TAG, "Detect conflicting option " + option.getId() + " : " + objRetrieveOption + " != " + objRetrieveOption2);
                    }
                } else {
                    mutableOptionsBundleCreate.insertOption(option, objRetrieveOption);
                }
            }
        }
        return mutableOptionsBundleCreate;
    }

    final class StateCallback extends SynchronizedCaptureSession.StateCallback {
        StateCallback() {
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onConfigured(SynchronizedCaptureSession session) {
            synchronized (CaptureSession.this.mStateLock) {
                switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigured() should not be possible in state: " + CaptureSession.this.mState);
                    case 4:
                        CaptureSession.this.mState = State.OPENED;
                        CaptureSession.this.mSynchronizedCaptureSession = session;
                        if (CaptureSession.this.mSessionConfig != null) {
                            List<CaptureConfig> listOnEnableSession = CaptureSession.this.mCameraEventCallbacks.createComboCallback().onEnableSession();
                            if (!listOnEnableSession.isEmpty()) {
                                CaptureSession captureSession = CaptureSession.this;
                                captureSession.issueBurstCaptureRequest(captureSession.setupConfiguredSurface(listOnEnableSession));
                            }
                        }
                        Logger.d(CaptureSession.TAG, "Attempting to send capture request onConfigured");
                        CaptureSession.this.issueRepeatingCaptureRequests();
                        CaptureSession.this.issuePendingCaptureRequest();
                        break;
                    case 6:
                        CaptureSession.this.mSynchronizedCaptureSession = session;
                        break;
                    case 7:
                        session.close();
                        break;
                }
                Logger.d(CaptureSession.TAG, "CameraCaptureSession.onConfigured() mState=" + CaptureSession.this.mState);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onReady(SynchronizedCaptureSession session) {
            synchronized (CaptureSession.this.mStateLock) {
                if (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()] == 1) {
                    throw new IllegalStateException("onReady() should not be possible in state: " + CaptureSession.this.mState);
                }
                Logger.d(CaptureSession.TAG, "CameraCaptureSession.onReady() " + CaptureSession.this.mState);
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onSessionFinished(SynchronizedCaptureSession session) {
            synchronized (CaptureSession.this.mStateLock) {
                if (CaptureSession.this.mState == State.UNINITIALIZED) {
                    throw new IllegalStateException("onSessionFinished() should not be possible in state: " + CaptureSession.this.mState);
                }
                Logger.d(CaptureSession.TAG, "onSessionFinished()");
                CaptureSession.this.finishClose();
            }
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onConfigureFailed(SynchronizedCaptureSession session) {
            synchronized (CaptureSession.this.mStateLock) {
                switch (AnonymousClass3.$SwitchMap$androidx$camera$camera2$internal$CaptureSession$State[CaptureSession.this.mState.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 8:
                        throw new IllegalStateException("onConfigureFailed() should not be possible in state: " + CaptureSession.this.mState);
                    case 4:
                    case 6:
                    case 7:
                        CaptureSession.this.finishClose();
                        break;
                }
                Logger.e(CaptureSession.TAG, "CameraCaptureSession.onConfigureFailed() " + CaptureSession.this.mState);
            }
        }
    }

    List<CaptureConfig> setupConfiguredSurface(List<CaptureConfig> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<CaptureConfig> it = list.iterator();
        while (it.hasNext()) {
            CaptureConfig.Builder builderFrom = CaptureConfig.Builder.from(it.next());
            builderFrom.setTemplateType(1);
            Iterator<DeferrableSurface> it2 = this.mSessionConfig.getRepeatingCaptureConfig().getSurfaces().iterator();
            while (it2.hasNext()) {
                builderFrom.addSurface(it2.next());
            }
            arrayList.add(builderFrom.build());
        }
        return arrayList;
    }
}
