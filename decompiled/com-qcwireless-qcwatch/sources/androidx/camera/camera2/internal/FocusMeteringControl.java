package androidx.camera.camera2.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import android.util.Rational;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
class FocusMeteringControl {
    private static final String TAG = "FocusMeteringControl";
    private ScheduledFuture<?> mAutoCancelHandle;
    private final Camera2CameraControlImpl mCameraControl;
    final Executor mExecutor;
    private final ScheduledExecutorService mScheduler;
    private volatile boolean mIsActive = false;
    private boolean mIsInAfAutoMode = false;
    Integer mCurrentAfState = 0;
    long mFocusTimeoutCounter = 0;
    boolean mIsAutoFocusCompleted = false;
    boolean mIsFocusSuccessful = false;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForFocus = null;
    private Camera2CameraControlImpl.CaptureResultListener mSessionListenerForCancel = null;
    private MeteringRectangle[] mAfRects = new MeteringRectangle[0];
    private MeteringRectangle[] mAeRects = new MeteringRectangle[0];
    private MeteringRectangle[] mAwbRects = new MeteringRectangle[0];
    MeteringRectangle[] mDefaultAfRects = new MeteringRectangle[0];
    MeteringRectangle[] mDefaultAeRects = new MeteringRectangle[0];
    MeteringRectangle[] mDefaultAwbRects = new MeteringRectangle[0];
    CallbackToFutureAdapter.Completer<FocusMeteringResult> mRunningActionCompleter = null;
    CallbackToFutureAdapter.Completer<Void> mRunningCancelCompleter = null;

    private int getDefaultTemplate() {
        return 1;
    }

    FocusMeteringControl(Camera2CameraControlImpl cameraControl, ScheduledExecutorService scheduler, Executor executor) {
        this.mCameraControl = cameraControl;
        this.mExecutor = executor;
        this.mScheduler = scheduler;
    }

    void setDefaultRequestBuilder(CaptureRequest.Builder builder) {
        this.mDefaultAfRects = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AF_REGIONS);
        this.mDefaultAeRects = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AE_REGIONS);
        this.mDefaultAwbRects = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AWB_REGIONS);
    }

    void setActive(boolean isActive) {
        if (isActive == this.mIsActive) {
            return;
        }
        this.mIsActive = isActive;
        if (this.mIsActive) {
            return;
        }
        cancelFocusAndMeteringWithoutAsyncResult();
    }

    void addFocusMeteringOptions(Camera2ImplConfig.Builder configBuilder) {
        configBuilder.setCaptureRequestOption(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(this.mCameraControl.getSupportedAfMode(this.mIsInAfAutoMode ? 1 : 4)));
        if (this.mAfRects.length != 0) {
            configBuilder.setCaptureRequestOption(CaptureRequest.CONTROL_AF_REGIONS, this.mAfRects);
        }
        if (this.mAeRects.length != 0) {
            configBuilder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_REGIONS, this.mAeRects);
        }
        if (this.mAwbRects.length != 0) {
            configBuilder.setCaptureRequestOption(CaptureRequest.CONTROL_AWB_REGIONS, this.mAwbRects);
        }
    }

    private static boolean isValid(final MeteringPoint pt) {
        return pt.getX() >= 0.0f && pt.getX() <= 1.0f && pt.getY() >= 0.0f && pt.getY() <= 1.0f;
    }

    private static PointF getFovAdjustedPoint(MeteringPoint meteringPoint, Rational cropRegionAspectRatio, Rational defaultAspectRatio) {
        if (meteringPoint.getSurfaceAspectRatio() != null) {
            defaultAspectRatio = meteringPoint.getSurfaceAspectRatio();
        }
        PointF pointF = new PointF(meteringPoint.getX(), meteringPoint.getY());
        if (!defaultAspectRatio.equals(cropRegionAspectRatio)) {
            if (defaultAspectRatio.compareTo(cropRegionAspectRatio) > 0) {
                float fDoubleValue = (float) (defaultAspectRatio.doubleValue() / cropRegionAspectRatio.doubleValue());
                pointF.y = (((float) ((fDoubleValue - 1.0d) / 2.0d)) + pointF.y) * (1.0f / fDoubleValue);
            } else {
                float fDoubleValue2 = (float) (cropRegionAspectRatio.doubleValue() / defaultAspectRatio.doubleValue());
                pointF.x = (((float) ((fDoubleValue2 - 1.0d) / 2.0d)) + pointF.x) * (1.0f / fDoubleValue2);
            }
        }
        return pointF;
    }

    private static MeteringRectangle getMeteringRect(MeteringPoint meteringPoint, PointF adjustedPoint, Rect cropRegion) {
        int iWidth = (int) (cropRegion.left + (adjustedPoint.x * cropRegion.width()));
        int iHeight = (int) (cropRegion.top + (adjustedPoint.y * cropRegion.height()));
        int size = ((int) (meteringPoint.getSize() * cropRegion.width())) / 2;
        int size2 = ((int) (meteringPoint.getSize() * cropRegion.height())) / 2;
        Rect rect = new Rect(iWidth - size, iHeight - size2, iWidth + size, iHeight + size2);
        rect.left = rangeLimit(rect.left, cropRegion.right, cropRegion.left);
        rect.right = rangeLimit(rect.right, cropRegion.right, cropRegion.left);
        rect.top = rangeLimit(rect.top, cropRegion.bottom, cropRegion.top);
        rect.bottom = rangeLimit(rect.bottom, cropRegion.bottom, cropRegion.top);
        return new MeteringRectangle(rect, 1000);
    }

    private static int rangeLimit(int val, int max, int min) {
        return Math.min(Math.max(val, min), max);
    }

    ListenableFuture<FocusMeteringResult> startFocusAndMetering(final FocusMeteringAction action, final Rational defaultAspectRatio) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda3
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m39xb5d4fd7e(action, defaultAspectRatio, completer);
            }
        });
    }

    /* renamed from: lambda$startFocusAndMetering$1$androidx-camera-camera2-internal-FocusMeteringControl, reason: not valid java name */
    public /* synthetic */ Object m39xb5d4fd7e(final FocusMeteringAction focusMeteringAction, final Rational rational, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m38x8c80a83d(completer, focusMeteringAction, rational);
            }
        });
        return "startFocusAndMetering";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: startFocusAndMeteringInternal, reason: merged with bridge method [inline-methods] */
    public void m38x8c80a83d(CallbackToFutureAdapter.Completer<FocusMeteringResult> completer, FocusMeteringAction action, Rational defaultAspectRatio) {
        if (!this.mIsActive) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        if (action.getMeteringPointsAf().isEmpty() && action.getMeteringPointsAe().isEmpty() && action.getMeteringPointsAwb().isEmpty()) {
            completer.setException(new IllegalArgumentException("No AF/AE/AWB MeteringPoints are added."));
            return;
        }
        int iMin = Math.min(action.getMeteringPointsAf().size(), this.mCameraControl.getMaxAfRegionCount());
        int iMin2 = Math.min(action.getMeteringPointsAe().size(), this.mCameraControl.getMaxAeRegionCount());
        int iMin3 = Math.min(action.getMeteringPointsAwb().size(), this.mCameraControl.getMaxAwbRegionCount());
        if (iMin + iMin2 + iMin3 <= 0) {
            completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints is supported on this camera."));
            return;
        }
        ArrayList<MeteringPoint> arrayList = new ArrayList();
        ArrayList<MeteringPoint> arrayList2 = new ArrayList();
        ArrayList<MeteringPoint> arrayList3 = new ArrayList();
        if (iMin > 0) {
            arrayList.addAll(action.getMeteringPointsAf().subList(0, iMin));
        }
        if (iMin2 > 0) {
            arrayList2.addAll(action.getMeteringPointsAe().subList(0, iMin2));
        }
        if (iMin3 > 0) {
            arrayList3.addAll(action.getMeteringPointsAwb().subList(0, iMin3));
        }
        Rect cropSensorRegion = this.mCameraControl.getCropSensorRegion();
        Rational rational = new Rational(cropSensorRegion.width(), cropSensorRegion.height());
        if (defaultAspectRatio == null) {
            defaultAspectRatio = rational;
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        for (MeteringPoint meteringPoint : arrayList) {
            if (isValid(meteringPoint)) {
                MeteringRectangle meteringRect = getMeteringRect(meteringPoint, getFovAdjustedPoint(meteringPoint, rational, defaultAspectRatio), cropSensorRegion);
                if (meteringRect.getWidth() != 0 && meteringRect.getHeight() != 0) {
                    arrayList4.add(meteringRect);
                }
            }
        }
        for (MeteringPoint meteringPoint2 : arrayList2) {
            if (isValid(meteringPoint2)) {
                MeteringRectangle meteringRect2 = getMeteringRect(meteringPoint2, getFovAdjustedPoint(meteringPoint2, rational, defaultAspectRatio), cropSensorRegion);
                if (meteringRect2.getWidth() != 0 && meteringRect2.getHeight() != 0) {
                    arrayList5.add(meteringRect2);
                }
            }
        }
        for (MeteringPoint meteringPoint3 : arrayList3) {
            if (isValid(meteringPoint3)) {
                MeteringRectangle meteringRect3 = getMeteringRect(meteringPoint3, getFovAdjustedPoint(meteringPoint3, rational, defaultAspectRatio), cropSensorRegion);
                if (meteringRect3.getWidth() != 0 && meteringRect3.getHeight() != 0) {
                    arrayList6.add(meteringRect3);
                }
            }
        }
        if (arrayList4.isEmpty() && arrayList5.isEmpty() && arrayList6.isEmpty()) {
            completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints are valid."));
            return;
        }
        failActionFuture("Cancelled by another startFocusAndMetering()");
        failCancelFuture("Cancelled by another startFocusAndMetering()");
        disableAutoCancel();
        this.mRunningActionCompleter = completer;
        executeMeteringAction((MeteringRectangle[]) arrayList4.toArray(new MeteringRectangle[0]), (MeteringRectangle[]) arrayList5.toArray(new MeteringRectangle[0]), (MeteringRectangle[]) arrayList6.toArray(new MeteringRectangle[0]), action);
    }

    void triggerAf(final CallbackToFutureAdapter.Completer<CameraCaptureResult> completer) {
        if (!this.mIsActive) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        CaptureConfig.Builder builder = new CaptureConfig.Builder();
        builder.setTemplateType(getDefaultTemplate());
        builder.setUseRepeatingSurface(true);
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        builder.addImplementationOptions(builder2.build());
        builder.addCameraCaptureCallback(new CameraCaptureCallback() { // from class: androidx.camera.camera2.internal.FocusMeteringControl.1
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.set(cameraCaptureResult);
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureFailed(CameraCaptureFailure failure) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControlInternal.CameraControlException(failure));
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCancelled() {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControl.OperationCanceledException("Camera is closed"));
                }
            }
        });
        this.mCameraControl.m7x9385e79c(Collections.singletonList(builder.build()));
    }

    void triggerAePrecapture(final CallbackToFutureAdapter.Completer<CameraCaptureResult> completer) {
        if (!this.mIsActive) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        CaptureConfig.Builder builder = new CaptureConfig.Builder();
        builder.setTemplateType(getDefaultTemplate());
        builder.setUseRepeatingSurface(true);
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
        builder.addImplementationOptions(builder2.build());
        builder.addCameraCaptureCallback(new CameraCaptureCallback() { // from class: androidx.camera.camera2.internal.FocusMeteringControl.2
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.set(cameraCaptureResult);
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureFailed(CameraCaptureFailure failure) {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControlInternal.CameraControlException(failure));
                }
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCancelled() {
                CallbackToFutureAdapter.Completer completer2 = completer;
                if (completer2 != null) {
                    completer2.setException(new CameraControl.OperationCanceledException("Camera is closed"));
                }
            }
        });
        this.mCameraControl.m7x9385e79c(Collections.singletonList(builder.build()));
    }

    void cancelAfAeTrigger(final boolean cancelAfTrigger, final boolean cancelAePrecaptureTrigger) {
        if (this.mIsActive) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setUseRepeatingSurface(true);
            builder.setTemplateType(getDefaultTemplate());
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            if (cancelAfTrigger) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            }
            if (Build.VERSION.SDK_INT >= 23 && cancelAePrecaptureTrigger) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
            }
            builder.addImplementationOptions(builder2.build());
            this.mCameraControl.m7x9385e79c(Collections.singletonList(builder.build()));
        }
    }

    private void disableAutoCancel() {
        ScheduledFuture<?> scheduledFuture = this.mAutoCancelHandle;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.mAutoCancelHandle = null;
        }
    }

    private static int getRegionCount(MeteringRectangle[] regions) {
        if (regions == null) {
            return 0;
        }
        return regions.length;
    }

    private static boolean hasEqualRegions(MeteringRectangle[] regions1, MeteringRectangle[] regions2) {
        if (getRegionCount(regions1) == 0 && getRegionCount(regions2) == 0) {
            return true;
        }
        if (getRegionCount(regions1) != getRegionCount(regions2)) {
            return false;
        }
        if (regions1 != null && regions2 != null) {
            for (int i = 0; i < regions1.length; i++) {
                if (!regions1[i].equals(regions2[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isAfModeSupported() {
        return this.mCameraControl.getSupportedAfMode(1) == 1;
    }

    private void completeActionFuture(boolean isFocusSuccessful) {
        CallbackToFutureAdapter.Completer<FocusMeteringResult> completer = this.mRunningActionCompleter;
        if (completer != null) {
            completer.set(FocusMeteringResult.create(isFocusSuccessful));
            this.mRunningActionCompleter = null;
        }
    }

    private void failActionFuture(String message) {
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForFocus);
        CallbackToFutureAdapter.Completer<FocusMeteringResult> completer = this.mRunningActionCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(message));
            this.mRunningActionCompleter = null;
        }
    }

    private void failCancelFuture(String message) {
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForCancel);
        CallbackToFutureAdapter.Completer<Void> completer = this.mRunningCancelCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(message));
            this.mRunningCancelCompleter = null;
        }
    }

    private void completeCancelFuture() {
        CallbackToFutureAdapter.Completer<Void> completer = this.mRunningCancelCompleter;
        if (completer != null) {
            completer.set(null);
            this.mRunningCancelCompleter = null;
        }
    }

    private void executeMeteringAction(final MeteringRectangle[] afRects, final MeteringRectangle[] aeRects, final MeteringRectangle[] awbRects, FocusMeteringAction focusMeteringAction) {
        this.mCameraControl.removeCaptureResultListener(this.mSessionListenerForFocus);
        disableAutoCancel();
        this.mAfRects = afRects;
        this.mAeRects = aeRects;
        this.mAwbRects = awbRects;
        if (shouldTriggerAF()) {
            this.mIsInAfAutoMode = true;
            this.mIsAutoFocusCompleted = false;
            this.mIsFocusSuccessful = false;
            this.mCameraControl.updateSessionConfigSynchronous();
            triggerAf(null);
        } else {
            this.mIsInAfAutoMode = false;
            this.mIsAutoFocusCompleted = true;
            this.mIsFocusSuccessful = false;
            this.mCameraControl.updateSessionConfigSynchronous();
        }
        this.mCurrentAfState = 0;
        final boolean zIsAfModeSupported = isAfModeSupported();
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda1
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                return this.f$0.m35xfc1c23a9(zIsAfModeSupported, afRects, aeRects, awbRects, totalCaptureResult);
            }
        };
        this.mSessionListenerForFocus = captureResultListener;
        this.mCameraControl.addCaptureResultListener(captureResultListener);
        if (focusMeteringAction.isAutoCancelEnabled()) {
            final long j = this.mFocusTimeoutCounter + 1;
            this.mFocusTimeoutCounter = j;
            this.mAutoCancelHandle = this.mScheduler.schedule(new Runnable() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m37x4ec4ce2b(j);
                }
            }, focusMeteringAction.getAutoCancelDurationInMillis(), TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: lambda$executeMeteringAction$2$androidx-camera-camera2-internal-FocusMeteringControl, reason: not valid java name */
    public /* synthetic */ boolean m35xfc1c23a9(boolean z, MeteringRectangle[] meteringRectangleArr, MeteringRectangle[] meteringRectangleArr2, MeteringRectangle[] meteringRectangleArr3, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        if (shouldTriggerAF()) {
            if (!z || num == null) {
                this.mIsFocusSuccessful = true;
                this.mIsAutoFocusCompleted = true;
            } else if (this.mCurrentAfState.intValue() == 3) {
                if (num.intValue() == 4) {
                    this.mIsFocusSuccessful = true;
                    this.mIsAutoFocusCompleted = true;
                } else if (num.intValue() == 5) {
                    this.mIsFocusSuccessful = false;
                    this.mIsAutoFocusCompleted = true;
                }
            }
        }
        if (this.mIsAutoFocusCompleted && totalCaptureResult.getRequest() != null) {
            if (meteringRectangleArr.length == 0) {
                meteringRectangleArr = this.mDefaultAfRects;
            }
            if (meteringRectangleArr2.length == 0) {
                meteringRectangleArr2 = this.mDefaultAeRects;
            }
            if (meteringRectangleArr3.length == 0) {
                meteringRectangleArr3 = this.mDefaultAwbRects;
            }
            CaptureRequest request = totalCaptureResult.getRequest();
            if (hasEqualRegions((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AF_REGIONS), meteringRectangleArr) && hasEqualRegions((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AE_REGIONS), meteringRectangleArr2) && hasEqualRegions((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AWB_REGIONS), meteringRectangleArr3)) {
                completeActionFuture(this.mIsFocusSuccessful);
                return true;
            }
        }
        if (!this.mCurrentAfState.equals(num) && num != null) {
            this.mCurrentAfState = num;
        }
        return false;
    }

    /* renamed from: lambda$executeMeteringAction$4$androidx-camera-camera2-internal-FocusMeteringControl, reason: not valid java name */
    public /* synthetic */ void m37x4ec4ce2b(final long j) {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m36x257078ea(j);
            }
        });
    }

    /* renamed from: lambda$executeMeteringAction$3$androidx-camera-camera2-internal-FocusMeteringControl, reason: not valid java name */
    public /* synthetic */ void m36x257078ea(long j) {
        if (j == this.mFocusTimeoutCounter) {
            cancelFocusAndMeteringWithoutAsyncResult();
        }
    }

    private boolean shouldTriggerAF() {
        return this.mAfRects.length > 0;
    }

    ListenableFuture<Void> cancelFocusAndMetering() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m33xb24c9a75(completer);
            }
        });
    }

    /* renamed from: lambda$cancelFocusAndMetering$6$androidx-camera-camera2-internal-FocusMeteringControl, reason: not valid java name */
    public /* synthetic */ Object m33xb24c9a75(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m32x88f84534(completer);
            }
        });
        return "cancelFocusAndMetering";
    }

    void cancelFocusAndMeteringWithoutAsyncResult() {
        m32x88f84534(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: cancelFocusAndMeteringInternal, reason: merged with bridge method [inline-methods] */
    public void m32x88f84534(CallbackToFutureAdapter.Completer<Void> completer) {
        failCancelFuture("Cancelled by another cancelFocusAndMetering()");
        failActionFuture("Cancelled by cancelFocusAndMetering()");
        this.mRunningCancelCompleter = completer;
        disableAutoCancel();
        if (this.mRunningCancelCompleter != null) {
            final int supportedAfMode = this.mCameraControl.getSupportedAfMode(4);
            Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.FocusMeteringControl$$ExternalSyntheticLambda0
                @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
                public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                    return this.f$0.m34x2811a093(supportedAfMode, totalCaptureResult);
                }
            };
            this.mSessionListenerForCancel = captureResultListener;
            this.mCameraControl.addCaptureResultListener(captureResultListener);
        }
        if (shouldTriggerAF()) {
            cancelAfAeTrigger(true, false);
        }
        this.mAfRects = new MeteringRectangle[0];
        this.mAeRects = new MeteringRectangle[0];
        this.mAwbRects = new MeteringRectangle[0];
        this.mIsInAfAutoMode = false;
        this.mCameraControl.updateSessionConfigSynchronous();
    }

    /* renamed from: lambda$cancelFocusAndMeteringInternal$7$androidx-camera-camera2-internal-FocusMeteringControl, reason: not valid java name */
    public /* synthetic */ boolean m34x2811a093(int i, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE);
        CaptureRequest request = totalCaptureResult.getRequest();
        MeteringRectangle[] meteringRectangleArr = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AF_REGIONS);
        MeteringRectangle[] meteringRectangleArr2 = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AE_REGIONS);
        MeteringRectangle[] meteringRectangleArr3 = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AWB_REGIONS);
        if (num.intValue() != i || !hasEqualRegions(meteringRectangleArr, this.mDefaultAfRects) || !hasEqualRegions(meteringRectangleArr2, this.mDefaultAeRects) || !hasEqualRegions(meteringRectangleArr3, this.mDefaultAwbRects)) {
            return false;
        }
        completeCancelFuture();
        return true;
    }
}
