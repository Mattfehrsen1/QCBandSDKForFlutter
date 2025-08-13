package androidx.camera.core.impl;

import android.content.Context;
import android.util.Size;
import androidx.camera.core.InitializationException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public interface CameraDeviceSurfaceManager {

    public interface Provider {
        CameraDeviceSurfaceManager newInstance(Context context, Object cameraManager, Set<String> availableCameraIds) throws InitializationException;
    }

    boolean checkSupported(String cameraId, List<SurfaceConfig> surfaceConfigList);

    Map<UseCaseConfig<?>, Size> getSuggestedResolutions(String cameraId, List<SurfaceConfig> existingSurfaces, List<UseCaseConfig<?>> newUseCaseConfigs);

    SurfaceConfig transformSurfaceConfig(String cameraId, int imageFormat, Size size);
}
