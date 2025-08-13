package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
interface SynchronizedCaptureSession {

    public static abstract class StateCallback {
        void onActive(SynchronizedCaptureSession session) {
        }

        void onCaptureQueueEmpty(SynchronizedCaptureSession session) {
        }

        void onClosed(SynchronizedCaptureSession session) {
        }

        void onConfigureFailed(SynchronizedCaptureSession session) {
        }

        void onConfigured(SynchronizedCaptureSession session) {
        }

        void onReady(SynchronizedCaptureSession session) {
        }

        void onSessionFinished(SynchronizedCaptureSession session) {
        }

        void onSurfacePrepared(SynchronizedCaptureSession session, Surface surface) {
        }
    }

    void abortCaptures() throws CameraAccessException;

    int captureBurstRequests(List<CaptureRequest> requests, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    int captureBurstRequests(List<CaptureRequest> requests, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    int captureSingleRequest(CaptureRequest request, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    int captureSingleRequest(CaptureRequest request, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    void close();

    void finishClose();

    CameraDevice getDevice();

    StateCallback getStateCallback();

    ListenableFuture<Void> getSynchronizedBlocker(String feature);

    int setRepeatingBurstRequests(List<CaptureRequest> requests, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    int setRepeatingBurstRequests(List<CaptureRequest> requests, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    int setSingleRepeatingRequest(CaptureRequest request, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    int setSingleRepeatingRequest(CaptureRequest request, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException;

    void stopRepeating() throws CameraAccessException;

    CameraCaptureSessionCompat toCameraCaptureSessionCompat();
}
