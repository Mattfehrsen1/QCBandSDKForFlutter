package androidx.camera.camera2.internal;

import android.content.Context;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraStateRegistry;
import androidx.camera.core.impl.CameraThreadConfig;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class Camera2CameraFactory implements CameraFactory {
    private static final int DEFAULT_ALLOWED_CONCURRENT_OPEN_CAMERAS = 1;
    private final List<String> mAvailableCameraIds;
    private final CameraManagerCompat mCameraManager;
    private final CameraThreadConfig mThreadConfig;
    private final Map<String, Camera2CameraInfoImpl> mCameraInfos = new HashMap();
    private final CameraStateRegistry mCameraStateRegistry = new CameraStateRegistry(1);

    public Camera2CameraFactory(Context context, CameraThreadConfig threadConfig, CameraSelector availableCamerasSelector) throws InitializationException {
        this.mThreadConfig = threadConfig;
        this.mCameraManager = CameraManagerCompat.from(context, threadConfig.getSchedulerHandler());
        this.mAvailableCameraIds = CameraSelectionOptimizer.getSelectedAvailableCameraIds(this, availableCamerasSelector);
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public CameraInternal getCamera(String cameraId) throws CameraUnavailableException {
        if (!this.mAvailableCameraIds.contains(cameraId)) {
            throw new IllegalArgumentException("The given camera id is not on the available camera id list.");
        }
        return new Camera2CameraImpl(this.mCameraManager, cameraId, getCameraInfo(cameraId), this.mCameraStateRegistry, this.mThreadConfig.getCameraExecutor(), this.mThreadConfig.getSchedulerHandler());
    }

    Camera2CameraInfoImpl getCameraInfo(String cameraId) throws CameraUnavailableException {
        try {
            Camera2CameraInfoImpl camera2CameraInfoImpl = this.mCameraInfos.get(cameraId);
            if (camera2CameraInfoImpl != null) {
                return camera2CameraInfoImpl;
            }
            Camera2CameraInfoImpl camera2CameraInfoImpl2 = new Camera2CameraInfoImpl(cameraId, this.mCameraManager.getCameraCharacteristicsCompat(cameraId));
            this.mCameraInfos.put(cameraId, camera2CameraInfoImpl2);
            return camera2CameraInfoImpl2;
        } catch (CameraAccessExceptionCompat e) {
            throw CameraUnavailableExceptionHelper.createFrom(e);
        }
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public Set<String> getAvailableCameraIds() {
        return new LinkedHashSet(this.mAvailableCameraIds);
    }

    @Override // androidx.camera.core.impl.CameraFactory
    public CameraManagerCompat getCameraManager() {
        return this.mCameraManager;
    }
}
