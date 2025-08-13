package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraCharacteristics;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class CameraCharacteristicsCompat {
    private final CameraCharacteristics mCameraCharacteristics;
    private final Map<CameraCharacteristics.Key<?>, Object> mValuesCache = new HashMap();

    private CameraCharacteristicsCompat(CameraCharacteristics cameraCharacteristics) {
        this.mCameraCharacteristics = cameraCharacteristics;
    }

    public static CameraCharacteristicsCompat toCameraCharacteristicsCompat(CameraCharacteristics characteristics) {
        return new CameraCharacteristicsCompat(characteristics);
    }

    public <T> T get(CameraCharacteristics.Key<T> key) {
        synchronized (this) {
            T t = (T) this.mValuesCache.get(key);
            if (t != null) {
                return t;
            }
            T t2 = (T) this.mCameraCharacteristics.get(key);
            if (t2 != null) {
                this.mValuesCache.put(key, t2);
            }
            return t2;
        }
    }

    public CameraCharacteristics toCameraCharacteristics() {
        return this.mCameraCharacteristics;
    }
}
