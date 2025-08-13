package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CameraCaptureCallbacks {
    private CameraCaptureCallbacks() {
    }

    public static CameraCaptureCallback createNoOpCallback() {
        return new NoOpCameraCaptureCallback();
    }

    static CameraCaptureCallback createComboCallback(List<CameraCaptureCallback> callbacks) {
        if (callbacks.isEmpty()) {
            return createNoOpCallback();
        }
        if (callbacks.size() == 1) {
            return callbacks.get(0);
        }
        return new ComboCameraCaptureCallback(callbacks);
    }

    public static CameraCaptureCallback createComboCallback(CameraCaptureCallback... callbacks) {
        return createComboCallback((List<CameraCaptureCallback>) Arrays.asList(callbacks));
    }

    static final class NoOpCameraCaptureCallback extends CameraCaptureCallback {
        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(CameraCaptureFailure failure) {
        }

        NoOpCameraCaptureCallback() {
        }
    }

    public static final class ComboCameraCaptureCallback extends CameraCaptureCallback {
        private final List<CameraCaptureCallback> mCallbacks = new ArrayList();

        ComboCameraCaptureCallback(List<CameraCaptureCallback> callbacks) {
            for (CameraCaptureCallback cameraCaptureCallback : callbacks) {
                if (!(cameraCaptureCallback instanceof NoOpCameraCaptureCallback)) {
                    this.mCallbacks.add(cameraCaptureCallback);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(CameraCaptureResult result) {
            Iterator<CameraCaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureCompleted(result);
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(CameraCaptureFailure failure) {
            Iterator<CameraCaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureFailed(failure);
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            Iterator<CameraCaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureCancelled();
            }
        }

        public List<CameraCaptureCallback> getCallbacks() {
            return this.mCallbacks;
        }
    }
}
