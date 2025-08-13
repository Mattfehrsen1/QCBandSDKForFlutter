package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class Camera2CaptureCallbacks {
    private Camera2CaptureCallbacks() {
    }

    public static CameraCaptureSession.CaptureCallback createNoOpCallback() {
        return new NoOpSessionCaptureCallback();
    }

    static CameraCaptureSession.CaptureCallback createComboCallback(List<CameraCaptureSession.CaptureCallback> callbacks) {
        return new ComboSessionCaptureCallback(callbacks);
    }

    public static CameraCaptureSession.CaptureCallback createComboCallback(CameraCaptureSession.CaptureCallback... callbacks) {
        return createComboCallback((List<CameraCaptureSession.CaptureCallback>) Arrays.asList(callbacks));
    }

    static final class NoOpSessionCaptureCallback extends CameraCaptureSession.CaptureCallback {
        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureBufferLost(CameraCaptureSession session, CaptureRequest request, Surface surface, long frame) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frame) {
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frame) {
        }

        NoOpSessionCaptureCallback() {
        }
    }

    private static final class ComboSessionCaptureCallback extends CameraCaptureSession.CaptureCallback {
        private final List<CameraCaptureSession.CaptureCallback> mCallbacks = new ArrayList();

        ComboSessionCaptureCallback(List<CameraCaptureSession.CaptureCallback> callbacks) {
            for (CameraCaptureSession.CaptureCallback captureCallback : callbacks) {
                if (!(captureCallback instanceof NoOpSessionCaptureCallback)) {
                    this.mCallbacks.add(captureCallback);
                }
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureBufferLost(CameraCaptureSession session, CaptureRequest request, Surface surface, long frame) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureBufferLost(session, request, surface, frame);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureCompleted(session, request, result);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureFailed(session, request, failure);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureProgressed(session, request, partialResult);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureSequenceAborted(session, sequenceId);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frame) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureSequenceCompleted(session, sequenceId, frame);
            }
        }

        @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frame) {
            Iterator<CameraCaptureSession.CaptureCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCaptureStarted(session, request, timestamp, frame);
            }
        }
    }
}
