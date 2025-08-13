package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.view.Surface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class CameraBurstCaptureCallback extends CameraCaptureSession.CaptureCallback {
    CaptureSequenceCallback mCaptureSequenceCallback = null;
    final Map<CaptureRequest, List<CameraCaptureSession.CaptureCallback>> mCallbackMap = new HashMap();

    interface CaptureSequenceCallback {
        void onCaptureSequenceCompletedOrAborted(CameraCaptureSession session, int sequenceId, boolean isAborted);
    }

    CameraBurstCaptureCallback() {
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureBufferLost(CameraCaptureSession session, CaptureRequest request, Surface surface, long frame) {
        Iterator<CameraCaptureSession.CaptureCallback> it = getCallbacks(request).iterator();
        while (it.hasNext()) {
            it.next().onCaptureBufferLost(session, request, surface, frame);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
        Iterator<CameraCaptureSession.CaptureCallback> it = getCallbacks(request).iterator();
        while (it.hasNext()) {
            it.next().onCaptureCompleted(session, request, result);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureFailed(CameraCaptureSession session, CaptureRequest request, CaptureFailure failure) {
        Iterator<CameraCaptureSession.CaptureCallback> it = getCallbacks(request).iterator();
        while (it.hasNext()) {
            it.next().onCaptureFailed(session, request, failure);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
        Iterator<CameraCaptureSession.CaptureCallback> it = getCallbacks(request).iterator();
        while (it.hasNext()) {
            it.next().onCaptureProgressed(session, request, partialResult);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
        Iterator<CameraCaptureSession.CaptureCallback> it = getCallbacks(request).iterator();
        while (it.hasNext()) {
            it.next().onCaptureStarted(session, request, timestamp, frameNumber);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
        CaptureSequenceCallback captureSequenceCallback = this.mCaptureSequenceCallback;
        if (captureSequenceCallback != null) {
            captureSequenceCallback.onCaptureSequenceCompletedOrAborted(session, sequenceId, true);
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frameNumber) {
        CaptureSequenceCallback captureSequenceCallback = this.mCaptureSequenceCallback;
        if (captureSequenceCallback != null) {
            captureSequenceCallback.onCaptureSequenceCompletedOrAborted(session, sequenceId, false);
        }
    }

    private List<CameraCaptureSession.CaptureCallback> getCallbacks(CaptureRequest request) {
        List<CameraCaptureSession.CaptureCallback> list = this.mCallbackMap.get(request);
        return list != null ? list : Collections.emptyList();
    }

    void addCamera2Callbacks(CaptureRequest captureRequest, List<CameraCaptureSession.CaptureCallback> captureCallbacks) {
        List<CameraCaptureSession.CaptureCallback> list = this.mCallbackMap.get(captureRequest);
        if (list != null) {
            ArrayList arrayList = new ArrayList(captureCallbacks.size() + list.size());
            arrayList.addAll(captureCallbacks);
            arrayList.addAll(list);
            this.mCallbackMap.put(captureRequest, arrayList);
            return;
        }
        this.mCallbackMap.put(captureRequest, captureCallbacks);
    }

    public void setCaptureSequenceCallback(CaptureSequenceCallback callback) {
        this.mCaptureSequenceCallback = callback;
    }
}
