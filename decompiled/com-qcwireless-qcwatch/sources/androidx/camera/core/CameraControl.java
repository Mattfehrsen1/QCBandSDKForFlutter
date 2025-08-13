package androidx.camera.core;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes.dex */
public interface CameraControl {
    ListenableFuture<Void> cancelFocusAndMetering();

    ListenableFuture<Void> enableTorch(boolean torch);

    ListenableFuture<Integer> setExposureCompensationIndex(int value);

    ListenableFuture<Void> setLinearZoom(float linearZoom);

    ListenableFuture<Void> setZoomRatio(float ratio);

    ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction action);

    public static final class OperationCanceledException extends Exception {
        public OperationCanceledException(String message) {
            super(message);
        }

        public OperationCanceledException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
