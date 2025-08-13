package androidx.camera.camera2.internal;

import android.content.Context;
import android.media.CamcorderProfile;
import android.util.Size;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.core.util.Preconditions;
import com.king.zxing.util.CodeUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class Camera2DeviceSurfaceManager implements CameraDeviceSurfaceManager {
    private static final String TAG = "Camera2DeviceSurfaceManager";
    private final CamcorderProfileHelper mCamcorderProfileHelper;
    private final Map<String, SupportedSurfaceCombination> mCameraSupportedSurfaceCombinationMap;

    public Camera2DeviceSurfaceManager(Context context, Object cameraManager, Set<String> availableCameraIds) throws CameraUnavailableException {
        this(context, new CamcorderProfileHelper() { // from class: androidx.camera.camera2.internal.Camera2DeviceSurfaceManager$$ExternalSyntheticLambda0
            @Override // androidx.camera.camera2.internal.CamcorderProfileHelper
            public final boolean hasProfile(int i, int i2) {
                return CamcorderProfile.hasProfile(i, i2);
            }
        }, cameraManager, availableCameraIds);
    }

    Camera2DeviceSurfaceManager(Context context, CamcorderProfileHelper camcorderProfileHelper, Object cameraManager, Set<String> availableCameraIds) throws CameraUnavailableException {
        CameraManagerCompat cameraManagerCompatFrom;
        this.mCameraSupportedSurfaceCombinationMap = new HashMap();
        Preconditions.checkNotNull(camcorderProfileHelper);
        this.mCamcorderProfileHelper = camcorderProfileHelper;
        if (cameraManager instanceof CameraManagerCompat) {
            cameraManagerCompatFrom = (CameraManagerCompat) cameraManager;
        } else {
            cameraManagerCompatFrom = CameraManagerCompat.from(context);
        }
        init(context, cameraManagerCompatFrom, availableCameraIds);
    }

    private void init(Context context, CameraManagerCompat cameraManager, Set<String> availableCameraIds) throws CameraUnavailableException {
        Preconditions.checkNotNull(context);
        for (String str : availableCameraIds) {
            this.mCameraSupportedSurfaceCombinationMap.put(str, new SupportedSurfaceCombination(context, str, cameraManager, this.mCamcorderProfileHelper));
        }
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    public boolean checkSupported(String cameraId, List<SurfaceConfig> surfaceConfigList) {
        if (surfaceConfigList == null || surfaceConfigList.isEmpty()) {
            return true;
        }
        SupportedSurfaceCombination supportedSurfaceCombination = this.mCameraSupportedSurfaceCombinationMap.get(cameraId);
        if (supportedSurfaceCombination != null) {
            return supportedSurfaceCombination.checkSupported(surfaceConfigList);
        }
        return false;
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    public SurfaceConfig transformSurfaceConfig(String cameraId, int imageFormat, Size size) {
        SupportedSurfaceCombination supportedSurfaceCombination = this.mCameraSupportedSurfaceCombinationMap.get(cameraId);
        if (supportedSurfaceCombination != null) {
            return supportedSurfaceCombination.transformSurfaceConfig(imageFormat, size);
        }
        return null;
    }

    @Override // androidx.camera.core.impl.CameraDeviceSurfaceManager
    public Map<UseCaseConfig<?>, Size> getSuggestedResolutions(String cameraId, List<SurfaceConfig> existingSurfaces, List<UseCaseConfig<?>> newUseCaseConfigs) {
        Preconditions.checkArgument(!newUseCaseConfigs.isEmpty(), "No new use cases to be bound.");
        ArrayList arrayList = new ArrayList(existingSurfaces);
        Iterator<UseCaseConfig<?>> it = newUseCaseConfigs.iterator();
        while (it.hasNext()) {
            arrayList.add(transformSurfaceConfig(cameraId, it.next().getInputFormat(), new Size(CodeUtils.DEFAULT_REQ_HEIGHT, CodeUtils.DEFAULT_REQ_WIDTH)));
        }
        SupportedSurfaceCombination supportedSurfaceCombination = this.mCameraSupportedSurfaceCombinationMap.get(cameraId);
        if (supportedSurfaceCombination == null) {
            throw new IllegalArgumentException("No such camera id in supported combination list: " + cameraId);
        }
        if (!supportedSurfaceCombination.checkSupported(arrayList)) {
            throw new IllegalArgumentException("No supported surface combination is found for camera device - Id : " + cameraId + ".  May be attempting to bind too many use cases. Existing surfaces: " + existingSurfaces + " New configs: " + newUseCaseConfigs);
        }
        return supportedSurfaceCombination.getSuggestedResolutions(existingSurfaces, newUseCaseConfigs);
    }
}
