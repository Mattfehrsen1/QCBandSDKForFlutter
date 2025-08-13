package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.util.ArrayMap;
import android.util.Rational;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.AeFpsRange;
import androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public class Camera2CameraControlImpl implements CameraControlInternal {
    private static final String TAG = "Camera2CameraControlImp";
    private final AeFpsRange mAeFpsRange;
    private final AutoFlashAEModeDisabler mAutoFlashAEModeDisabler;
    private final Camera2CameraControl mCamera2CameraControl;
    private final CameraCaptureCallbackSet mCameraCaptureCallbackSet;
    private final CameraCharacteristicsCompat mCameraCharacteristics;
    private final CameraControlInternal.ControlUpdateCallback mControlUpdateCallback;
    final Executor mExecutor;
    private final ExposureControl mExposureControl;
    private volatile int mFlashMode;
    private final FocusMeteringControl mFocusMeteringControl;
    private volatile boolean mIsTorchOn;
    private final Object mLock;
    volatile Rational mPreviewAspectRatio;
    final CameraControlSessionCallback mSessionCallback;
    private final SessionConfig.Builder mSessionConfigBuilder;
    private final TorchControl mTorchControl;
    private int mUseCount;
    private final ZoomControl mZoomControl;

    public interface CaptureResultListener {
        boolean onCaptureResult(TotalCaptureResult captureResult);
    }

    static /* synthetic */ void lambda$addInteropConfig$1() {
    }

    static /* synthetic */ void lambda$clearInteropConfig$2() {
    }

    int getDefaultTemplate() {
        return 1;
    }

    Camera2CameraControlImpl(CameraCharacteristicsCompat cameraCharacteristics, ScheduledExecutorService scheduler, Executor executor, CameraControlInternal.ControlUpdateCallback controlUpdateCallback) {
        this(cameraCharacteristics, scheduler, executor, controlUpdateCallback, new Quirks(new ArrayList()));
    }

    Camera2CameraControlImpl(CameraCharacteristicsCompat cameraCharacteristics, ScheduledExecutorService scheduler, Executor executor, CameraControlInternal.ControlUpdateCallback controlUpdateCallback, final Quirks cameraQuirks) {
        this.mLock = new Object();
        SessionConfig.Builder builder = new SessionConfig.Builder();
        this.mSessionConfigBuilder = builder;
        this.mPreviewAspectRatio = null;
        this.mUseCount = 0;
        this.mIsTorchOn = false;
        this.mFlashMode = 2;
        this.mAutoFlashAEModeDisabler = new AutoFlashAEModeDisabler();
        CameraCaptureCallbackSet cameraCaptureCallbackSet = new CameraCaptureCallbackSet();
        this.mCameraCaptureCallbackSet = cameraCaptureCallbackSet;
        this.mCameraCharacteristics = cameraCharacteristics;
        this.mControlUpdateCallback = controlUpdateCallback;
        this.mExecutor = executor;
        CameraControlSessionCallback cameraControlSessionCallback = new CameraControlSessionCallback(executor);
        this.mSessionCallback = cameraControlSessionCallback;
        builder.setTemplateType(getDefaultTemplate());
        builder.addRepeatingCameraCaptureCallback(CaptureCallbackContainer.create(cameraControlSessionCallback));
        builder.addRepeatingCameraCaptureCallback(cameraCaptureCallbackSet);
        this.mExposureControl = new ExposureControl(this, cameraCharacteristics, executor);
        this.mFocusMeteringControl = new FocusMeteringControl(this, scheduler, executor);
        this.mZoomControl = new ZoomControl(this, cameraCharacteristics, executor);
        this.mTorchControl = new TorchControl(this, cameraCharacteristics, executor);
        this.mAeFpsRange = new AeFpsRange(cameraQuirks);
        this.mCamera2CameraControl = new Camera2CameraControl(this, executor);
        executor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m5xc707fb02();
            }
        });
        updateSessionConfig();
    }

    /* renamed from: lambda$new$0$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ void m5xc707fb02() {
        addCaptureResultListener(this.mCamera2CameraControl.getCaptureRequestListener());
    }

    void incrementUseCount() {
        synchronized (this.mLock) {
            this.mUseCount++;
        }
    }

    void decrementUseCount() {
        synchronized (this.mLock) {
            int i = this.mUseCount;
            if (i == 0) {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
            this.mUseCount = i - 1;
        }
    }

    int getUseCount() {
        int i;
        synchronized (this.mLock) {
            i = this.mUseCount;
        }
        return i;
    }

    public ZoomControl getZoomControl() {
        return this.mZoomControl;
    }

    public TorchControl getTorchControl() {
        return this.mTorchControl;
    }

    public ExposureControl getExposureControl() {
        return this.mExposureControl;
    }

    public Camera2CameraControl getCamera2CameraControl() {
        return this.mCamera2CameraControl;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void addInteropConfig(Config config) {
        this.mCamera2CameraControl.addCaptureRequestOptions(CaptureRequestOptions.Builder.from(config).build()).addListener(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.lambda$addInteropConfig$1();
            }
        }, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void clearInteropConfig() {
        this.mCamera2CameraControl.clearCaptureRequestOptions().addListener(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Camera2CameraControlImpl.lambda$clearInteropConfig$2();
            }
        }, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public Config getInteropConfig() {
        return this.mCamera2CameraControl.getCamera2ImplConfig();
    }

    void setActive(boolean isActive) {
        this.mFocusMeteringControl.setActive(isActive);
        this.mZoomControl.setActive(isActive);
        this.mTorchControl.setActive(isActive);
        this.mExposureControl.setActive(isActive);
        this.mCamera2CameraControl.setActive(isActive);
    }

    public void setPreviewAspectRatio(Rational previewAspectRatio) {
        this.mPreviewAspectRatio = previewAspectRatio;
    }

    public void setDefaultRequestBuilder(CaptureRequest.Builder builder) {
        this.mFocusMeteringControl.setDefaultRequestBuilder(builder);
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction action) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mFocusMeteringControl.startFocusAndMetering(action, this.mPreviewAspectRatio));
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<Void> cancelFocusAndMetering() {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mFocusMeteringControl.cancelFocusAndMetering());
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<Void> setZoomRatio(float ratio) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mZoomControl.setZoomRatio(ratio));
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<Void> setLinearZoom(float linearZoom) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mZoomControl.setLinearZoom(linearZoom));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public int getFlashMode() {
        return this.mFlashMode;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void setFlashMode(int flashMode) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
        } else {
            this.mFlashMode = flashMode;
            updateSessionConfig();
        }
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<Void> enableTorch(final boolean torch) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(this.mTorchControl.enableTorch(torch));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public ListenableFuture<CameraCaptureResult> triggerAf() {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda3
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m11xa3c17ca3(completer);
            }
        }));
    }

    /* renamed from: lambda$triggerAf$3$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ void m10x930bafe2(CallbackToFutureAdapter.Completer completer) {
        this.mFocusMeteringControl.triggerAf(completer);
    }

    /* renamed from: lambda$triggerAf$4$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ Object m11xa3c17ca3(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m10x930bafe2(completer);
            }
        });
        return "triggerAf";
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public ListenableFuture<CameraCaptureResult> triggerAePrecapture() {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m9x9d77fc7(completer);
            }
        }));
    }

    /* renamed from: lambda$triggerAePrecapture$5$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ void m8xf921b306(CallbackToFutureAdapter.Completer completer) {
        this.mFocusMeteringControl.triggerAePrecapture(completer);
    }

    /* renamed from: lambda$triggerAePrecapture$6$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ Object m9x9d77fc7(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8xf921b306(completer);
            }
        });
        return "triggerAePrecapture";
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void cancelAfAeTrigger(final boolean cancelAfTrigger, final boolean cancelAePrecaptureTrigger) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
        } else {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m4x6128635e(cancelAfTrigger, cancelAePrecaptureTrigger);
                }
            });
        }
    }

    /* renamed from: lambda$cancelAfAeTrigger$7$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ void m4x6128635e(boolean z, boolean z2) {
        this.mFocusMeteringControl.cancelAfAeTrigger(z, z2);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal, androidx.camera.core.CameraControl
    public ListenableFuture<Integer> setExposureCompensationIndex(int exposure) {
        if (!isControlInUse()) {
            return Futures.immediateFailedFuture(new CameraControl.OperationCanceledException("Camera is not active."));
        }
        return this.mExposureControl.setExposureCompensationIndex(exposure);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void submitCaptureRequests(final List<CaptureConfig> captureConfigs) {
        if (!isControlInUse()) {
            Logger.w(TAG, "Camera is not active.");
        } else {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m7x9385e79c(captureConfigs);
                }
            });
        }
    }

    private boolean isControlInUse() {
        return getUseCount() > 0;
    }

    public void updateSessionConfig() {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateSessionConfigSynchronous();
            }
        });
    }

    void updateSessionConfigSynchronous() {
        this.mSessionConfigBuilder.setImplementationOptions(getSessionOptions());
        Object captureRequestTag = this.mCamera2CameraControl.getCamera2ImplConfig().getCaptureRequestTag(null);
        if (captureRequestTag != null && (captureRequestTag instanceof Integer)) {
            this.mSessionConfigBuilder.addTag(Camera2CameraControl.TAG_KEY, (Integer) captureRequestTag);
        }
        this.mControlUpdateCallback.onCameraControlUpdateSessionConfig(this.mSessionConfigBuilder.build());
    }

    Rect getCropSensorRegion() {
        return this.mZoomControl.getCropSensorRegion();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public Rect getSensorRect() {
        return (Rect) Preconditions.checkNotNull((Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE));
    }

    void removeCaptureResultListener(CaptureResultListener listener) {
        this.mSessionCallback.removeListener(listener);
    }

    void addCaptureResultListener(CaptureResultListener listener) {
        this.mSessionCallback.addListener(listener);
    }

    void addSessionCameraCaptureCallback(final Executor executor, final CameraCaptureCallback cameraCaptureCallback) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m3x59790e3c(executor, cameraCaptureCallback);
            }
        });
    }

    /* renamed from: lambda$addSessionCameraCaptureCallback$9$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ void m3x59790e3c(Executor executor, CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.addCaptureCallback(executor, cameraCaptureCallback);
    }

    void removeSessionCameraCaptureCallback(final CameraCaptureCallback cameraCaptureCallback) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6xe431be65(cameraCaptureCallback);
            }
        });
    }

    /* renamed from: lambda$removeSessionCameraCaptureCallback$10$androidx-camera-camera2-internal-Camera2CameraControlImpl, reason: not valid java name */
    public /* synthetic */ void m6xe431be65(CameraCaptureCallback cameraCaptureCallback) {
        this.mCameraCaptureCallbackSet.removeCaptureCallback(cameraCaptureCallback);
    }

    void enableTorchInternal(boolean torch) {
        this.mIsTorchOn = torch;
        if (!torch) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(getDefaultTemplate());
            builder.setUseRepeatingSurface(true);
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(getSupportedAeMode(1)));
            builder2.setCaptureRequestOption(CaptureRequest.FLASH_MODE, 0);
            builder.addImplementationOptions(builder2.build());
            m7x9385e79c(Collections.singletonList(builder.build()));
        }
        updateSessionConfigSynchronous();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: submitCaptureRequestsInternal, reason: merged with bridge method [inline-methods] */
    public void m7x9385e79c(final List<CaptureConfig> captureConfigs) {
        this.mControlUpdateCallback.onCameraControlCaptureRequests(captureConfigs);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0070 A[LOOP:0: B:12:0x006a->B:14:0x0070, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    androidx.camera.core.impl.Config getSessionOptions() {
        /*
            r7 = this;
            androidx.camera.camera2.impl.Camera2ImplConfig$Builder r0 = new androidx.camera.camera2.impl.Camera2ImplConfig$Builder
            r0.<init>()
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.CONTROL_MODE
            r2 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r0.setCaptureRequestOption(r1, r3)
            androidx.camera.camera2.internal.FocusMeteringControl r1 = r7.mFocusMeteringControl
            r1.addFocusMeteringOptions(r0)
            androidx.camera.camera2.internal.compat.workaround.AeFpsRange r1 = r7.mAeFpsRange
            r1.addAeFpsRangeOptions(r0)
            androidx.camera.camera2.internal.ZoomControl r1 = r7.mZoomControl
            r1.addZoomOption(r0)
            boolean r1 = r7.mIsTorchOn
            r3 = 2
            if (r1 == 0) goto L2d
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.FLASH_MODE
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r0.setCaptureRequestOption(r1, r3)
            goto L33
        L2d:
            int r1 = r7.mFlashMode
            if (r1 == 0) goto L37
            if (r1 == r2) goto L35
        L33:
            r1 = 1
            goto L3d
        L35:
            r1 = 3
            goto L3d
        L37:
            androidx.camera.camera2.internal.compat.workaround.AutoFlashAEModeDisabler r1 = r7.mAutoFlashAEModeDisabler
            int r1 = r1.getCorrectedAeMode(r3)
        L3d:
            android.hardware.camera2.CaptureRequest$Key r3 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE
            int r1 = r7.getSupportedAeMode(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.setCaptureRequestOption(r3, r1)
            android.hardware.camera2.CaptureRequest$Key r1 = android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE
            int r2 = r7.getSupportedAwbMode(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.setCaptureRequestOption(r1, r2)
            androidx.camera.camera2.internal.ExposureControl r1 = r7.mExposureControl
            r1.setCaptureRequestOption(r0)
            androidx.camera.camera2.interop.Camera2CameraControl r1 = r7.mCamera2CameraControl
            androidx.camera.camera2.impl.Camera2ImplConfig r1 = r1.getCamera2ImplConfig()
            java.util.Set r2 = r1.listOptions()
            java.util.Iterator r2 = r2.iterator()
        L6a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L84
            java.lang.Object r3 = r2.next()
            androidx.camera.core.impl.Config$Option r3 = (androidx.camera.core.impl.Config.Option) r3
            androidx.camera.core.impl.MutableConfig r4 = r0.getMutableConfig()
            androidx.camera.core.impl.Config$OptionPriority r5 = androidx.camera.core.impl.Config.OptionPriority.ALWAYS_OVERRIDE
            java.lang.Object r6 = r1.retrieveOption(r3)
            r4.insertOption(r3, r5, r6)
            goto L6a
        L84:
            androidx.camera.camera2.impl.Camera2ImplConfig r0 = r0.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.camera2.internal.Camera2CameraControlImpl.getSessionOptions():androidx.camera.core.impl.Config");
    }

    int getSupportedAfMode(int preferredMode) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        if (isModeInList(preferredMode, iArr)) {
            return preferredMode;
        }
        if (isModeInList(4, iArr)) {
            return 4;
        }
        return isModeInList(1, iArr) ? 1 : 0;
    }

    private int getSupportedAeMode(int preferredMode) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return isModeInList(preferredMode, iArr) ? preferredMode : isModeInList(1, iArr) ? 1 : 0;
    }

    private int getSupportedAwbMode(int preferredMode) {
        int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
        if (iArr == null) {
            return 0;
        }
        return isModeInList(preferredMode, iArr) ? preferredMode : isModeInList(1, iArr) ? 1 : 0;
    }

    private boolean isModeInList(int mode, int[] modeList) {
        for (int i : modeList) {
            if (mode == i) {
                return true;
            }
        }
        return false;
    }

    int getMaxAfRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    int getMaxAeRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    int getMaxAwbRegionCount() {
        Integer num = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    static final class CameraControlSessionCallback extends CameraCaptureSession.CaptureCallback {
        private final Executor mExecutor;
        final Set<CaptureResultListener> mResultListeners = new HashSet();

        CameraControlSessionCallback(Executor executor) {
            this.mExecutor = executor;
        }

        void addListener(CaptureResultListener listener) {
            this.mResultListeners.add(listener);
        }

        void removeListener(CaptureResultListener listener) {
            this.mResultListeners.remove(listener);
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, final TotalCaptureResult result) {
            this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$CameraControlSessionCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m12xf57ef947(result);
                }
            });
        }

        /* renamed from: lambda$onCaptureCompleted$0$androidx-camera-camera2-internal-Camera2CameraControlImpl$CameraControlSessionCallback, reason: not valid java name */
        public /* synthetic */ void m12xf57ef947(TotalCaptureResult totalCaptureResult) {
            HashSet hashSet = new HashSet();
            for (CaptureResultListener captureResultListener : this.mResultListeners) {
                if (captureResultListener.onCaptureResult(totalCaptureResult)) {
                    hashSet.add(captureResultListener);
                }
            }
            if (hashSet.isEmpty()) {
                return;
            }
            this.mResultListeners.removeAll(hashSet);
        }
    }

    static final class CameraCaptureCallbackSet extends CameraCaptureCallback {
        Set<CameraCaptureCallback> mCallbacks = new HashSet();
        Map<CameraCaptureCallback, Executor> mCallbackExecutors = new ArrayMap();

        CameraCaptureCallbackSet() {
        }

        void addCaptureCallback(Executor executor, CameraCaptureCallback callback) {
            this.mCallbacks.add(callback);
            this.mCallbackExecutors.put(callback, executor);
        }

        void removeCaptureCallback(CameraCaptureCallback callback) {
            this.mCallbacks.remove(callback);
            this.mCallbackExecutors.remove(callback);
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(final CameraCaptureResult cameraCaptureResult) {
            for (final CameraCaptureCallback cameraCaptureCallback : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(cameraCaptureCallback).execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            cameraCaptureCallback.onCaptureCompleted(cameraCaptureResult);
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCompleted.", e);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(final CameraCaptureFailure failure) {
            for (final CameraCaptureCallback cameraCaptureCallback : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(cameraCaptureCallback).execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            cameraCaptureCallback.onCaptureFailed(failure);
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureFailed.", e);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            for (final CameraCaptureCallback cameraCaptureCallback : this.mCallbacks) {
                try {
                    this.mCallbackExecutors.get(cameraCaptureCallback).execute(new Runnable() { // from class: androidx.camera.camera2.internal.Camera2CameraControlImpl$CameraCaptureCallbackSet$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            cameraCaptureCallback.onCaptureCancelled();
                        }
                    });
                } catch (RejectedExecutionException e) {
                    Logger.e(Camera2CameraControlImpl.TAG, "Executor rejected to invoke onCaptureCancelled.", e);
                }
            }
        }
    }
}
