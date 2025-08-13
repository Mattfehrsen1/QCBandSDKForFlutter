package androidx.camera.core.impl;

import android.graphics.Rect;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* loaded from: classes.dex */
public interface CameraControlInternal extends CameraControl {
    public static final CameraControlInternal DEFAULT_EMPTY_INSTANCE = new CameraControlInternal() { // from class: androidx.camera.core.impl.CameraControlInternal.1
        @Override // androidx.camera.core.impl.CameraControlInternal
        public void addInteropConfig(Config config) {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void cancelAfAeTrigger(boolean cancelAfTrigger, boolean cancelAePrecaptureTrigger) {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void clearInteropConfig() {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public int getFlashMode() {
            return 2;
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public Config getInteropConfig() {
            return null;
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void setFlashMode(int flashMode) {
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public void submitCaptureRequests(List<CaptureConfig> captureConfigs) {
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<Void> enableTorch(boolean torch) {
            return Futures.immediateFuture(null);
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public ListenableFuture<CameraCaptureResult> triggerAf() {
            return Futures.immediateFuture(CameraCaptureResult.EmptyCameraCaptureResult.create());
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public ListenableFuture<CameraCaptureResult> triggerAePrecapture() {
            return Futures.immediateFuture(CameraCaptureResult.EmptyCameraCaptureResult.create());
        }

        @Override // androidx.camera.core.impl.CameraControlInternal, androidx.camera.core.CameraControl
        public ListenableFuture<Integer> setExposureCompensationIndex(int exposure) {
            return Futures.immediateFuture(0);
        }

        @Override // androidx.camera.core.impl.CameraControlInternal
        public Rect getSensorRect() {
            return new Rect();
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction action) {
            return Futures.immediateFuture(FocusMeteringResult.emptyInstance());
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<Void> cancelFocusAndMetering() {
            return Futures.immediateFuture(null);
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<Void> setZoomRatio(float ratio) {
            return Futures.immediateFuture(null);
        }

        @Override // androidx.camera.core.CameraControl
        public ListenableFuture<Void> setLinearZoom(float linearZoom) {
            return Futures.immediateFuture(null);
        }
    };

    public interface ControlUpdateCallback {
        void onCameraControlCaptureRequests(List<CaptureConfig> captureConfigs);

        void onCameraControlUpdateSessionConfig(SessionConfig sessionConfig);
    }

    void addInteropConfig(Config config);

    void cancelAfAeTrigger(boolean cancelAfTrigger, boolean cancelAePrecaptureTrigger);

    void clearInteropConfig();

    int getFlashMode();

    Config getInteropConfig();

    Rect getSensorRect();

    @Override // androidx.camera.core.CameraControl
    ListenableFuture<Integer> setExposureCompensationIndex(int exposure);

    void setFlashMode(int flashMode);

    void submitCaptureRequests(List<CaptureConfig> captureConfigs);

    ListenableFuture<CameraCaptureResult> triggerAePrecapture();

    ListenableFuture<CameraCaptureResult> triggerAf();

    public static final class CameraControlException extends Exception {
        private CameraCaptureFailure mCameraCaptureFailure;

        public CameraControlException(CameraCaptureFailure failure) {
            this.mCameraCaptureFailure = failure;
        }

        public CameraControlException(CameraCaptureFailure failure, Throwable cause) {
            super(cause);
            this.mCameraCaptureFailure = failure;
        }

        public CameraCaptureFailure getCameraCaptureFailure() {
            return this.mCameraCaptureFailure;
        }
    }
}
