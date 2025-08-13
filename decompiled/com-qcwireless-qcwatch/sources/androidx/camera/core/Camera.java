package androidx.camera.core;

import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public interface Camera {
    CameraControl getCameraControl();

    CameraInfo getCameraInfo();

    LinkedHashSet<CameraInternal> getCameraInternals();

    CameraConfig getExtendedConfig();

    void setExtendedConfig(CameraConfig cameraConfig) throws CameraUseCaseAdapter.CameraException;
}
