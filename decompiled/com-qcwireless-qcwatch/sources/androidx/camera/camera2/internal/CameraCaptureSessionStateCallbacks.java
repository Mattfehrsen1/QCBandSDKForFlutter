package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CameraCaptureSessionStateCallbacks {
    private CameraCaptureSessionStateCallbacks() {
    }

    public static CameraCaptureSession.StateCallback createNoOpCallback() {
        return new NoOpSessionStateCallback();
    }

    public static CameraCaptureSession.StateCallback createComboCallback(List<CameraCaptureSession.StateCallback> callbacks) {
        if (callbacks.isEmpty()) {
            return createNoOpCallback();
        }
        if (callbacks.size() == 1) {
            return callbacks.get(0);
        }
        return new ComboSessionStateCallback(callbacks);
    }

    public static CameraCaptureSession.StateCallback createComboCallback(CameraCaptureSession.StateCallback... callbacks) {
        return createComboCallback((List<CameraCaptureSession.StateCallback>) Arrays.asList(callbacks));
    }

    static final class NoOpSessionStateCallback extends CameraCaptureSession.StateCallback {
        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(CameraCaptureSession session) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onCaptureQueueEmpty(CameraCaptureSession session) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession session) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession session) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession session) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(CameraCaptureSession session) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onSurfacePrepared(CameraCaptureSession session, Surface surface) {
        }

        NoOpSessionStateCallback() {
        }
    }

    static final class ComboSessionStateCallback extends CameraCaptureSession.StateCallback {
        private final List<CameraCaptureSession.StateCallback> mCallbacks = new ArrayList();

        ComboSessionStateCallback(List<CameraCaptureSession.StateCallback> callbacks) {
            for (CameraCaptureSession.StateCallback stateCallback : callbacks) {
                if (!(stateCallback instanceof NoOpSessionStateCallback)) {
                    this.mCallbacks.add(stateCallback);
                }
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigured(CameraCaptureSession session) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onConfigured(session);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onActive(CameraCaptureSession session) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onActive(session);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onClosed(CameraCaptureSession session) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onClosed(session);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onReady(CameraCaptureSession session) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onReady(session);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onCaptureQueueEmpty(CameraCaptureSession session) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureQueueEmpty(session);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onSurfacePrepared(CameraCaptureSession session, Surface surface) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onSurfacePrepared(session, surface);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
        public void onConfigureFailed(CameraCaptureSession session) {
            Iterator<CameraCaptureSession.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onConfigureFailed(session);
            }
        }
    }
}
