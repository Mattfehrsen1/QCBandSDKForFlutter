package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class SynchronizedCaptureSessionStateCallbacks extends SynchronizedCaptureSession.StateCallback {
    private final List<SynchronizedCaptureSession.StateCallback> mCallbacks;

    static SynchronizedCaptureSession.StateCallback createComboCallback(SynchronizedCaptureSession.StateCallback... callbacks) {
        return new SynchronizedCaptureSessionStateCallbacks(Arrays.asList(callbacks));
    }

    SynchronizedCaptureSessionStateCallbacks(List<SynchronizedCaptureSession.StateCallback> callbacks) {
        ArrayList arrayList = new ArrayList();
        this.mCallbacks = arrayList;
        arrayList.addAll(callbacks);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onSurfacePrepared(SynchronizedCaptureSession session, Surface surface) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onSurfacePrepared(session, surface);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onReady(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onReady(session);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onActive(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onActive(session);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onCaptureQueueEmpty(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onCaptureQueueEmpty(session);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigured(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onConfigured(session);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigureFailed(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onConfigureFailed(session);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onClosed(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onClosed(session);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    void onSessionFinished(SynchronizedCaptureSession session) {
        Iterator<SynchronizedCaptureSession.StateCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onSessionFinished(session);
        }
    }

    static class Adapter extends SynchronizedCaptureSession.StateCallback {
        private final CameraCaptureSession.StateCallback mCameraCaptureSessionStateCallback;

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        void onSessionFinished(SynchronizedCaptureSession session) {
        }

        Adapter(CameraCaptureSession.StateCallback cameraCaptureSessionStateCallback) {
            this.mCameraCaptureSessionStateCallback = cameraCaptureSessionStateCallback;
        }

        Adapter(List<CameraCaptureSession.StateCallback> callbackList) {
            this(CameraCaptureSessionStateCallbacks.createComboCallback(callbackList));
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onSurfacePrepared(SynchronizedCaptureSession session, Surface surface) {
            this.mCameraCaptureSessionStateCallback.onSurfacePrepared(session.toCameraCaptureSessionCompat().toCameraCaptureSession(), surface);
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onReady(SynchronizedCaptureSession session) {
            this.mCameraCaptureSessionStateCallback.onReady(session.toCameraCaptureSessionCompat().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onActive(SynchronizedCaptureSession session) {
            this.mCameraCaptureSessionStateCallback.onActive(session.toCameraCaptureSessionCompat().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onCaptureQueueEmpty(SynchronizedCaptureSession session) {
            this.mCameraCaptureSessionStateCallback.onCaptureQueueEmpty(session.toCameraCaptureSessionCompat().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onConfigured(SynchronizedCaptureSession session) {
            this.mCameraCaptureSessionStateCallback.onConfigured(session.toCameraCaptureSessionCompat().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onConfigureFailed(SynchronizedCaptureSession session) {
            this.mCameraCaptureSessionStateCallback.onConfigureFailed(session.toCameraCaptureSessionCompat().toCameraCaptureSession());
        }

        @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
        public void onClosed(SynchronizedCaptureSession session) {
            this.mCameraCaptureSessionStateCallback.onClosed(session.toCameraCaptureSessionCompat().toCameraCaptureSession());
        }
    }
}
