package androidx.camera.core.impl;

import androidx.camera.core.CameraInfo;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public interface CameraInfoInternal extends CameraInfo {
    void addSessionCaptureCallback(Executor executor, CameraCaptureCallback callback);

    String getCameraId();

    Quirks getCameraQuirks();

    Integer getLensFacing();

    void removeSessionCaptureCallback(CameraCaptureCallback callback);
}
