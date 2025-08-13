package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.CameraManagerCompat;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class CameraManagerCompatBaseImpl implements CameraManagerCompat.CameraManagerCompatImpl {
    final CameraManager mCameraManager;
    final Object mObject;

    CameraManagerCompatBaseImpl(Context context, Object cameraManagerParams) {
        this.mCameraManager = (CameraManager) context.getSystemService("camera");
        this.mObject = cameraManagerParams;
    }

    static CameraManagerCompatBaseImpl create(Context context, Handler compatHandler) {
        return new CameraManagerCompatBaseImpl(context, new CameraManagerCompatParamsApi21(compatHandler));
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public String[] getCameraIdList() throws CameraAccessExceptionCompat {
        try {
            return this.mCameraManager.getCameraIdList();
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void registerAvailabilityCallback(Executor executor, CameraManager.AvailabilityCallback callback) {
        if (executor == null) {
            throw new IllegalArgumentException("executor was null");
        }
        CameraManagerCompat.AvailabilityCallbackExecutorWrapper availabilityCallbackExecutorWrapper = null;
        CameraManagerCompatParamsApi21 cameraManagerCompatParamsApi21 = (CameraManagerCompatParamsApi21) this.mObject;
        if (callback != null) {
            synchronized (cameraManagerCompatParamsApi21.mWrapperMap) {
                availabilityCallbackExecutorWrapper = cameraManagerCompatParamsApi21.mWrapperMap.get(callback);
                if (availabilityCallbackExecutorWrapper == null) {
                    availabilityCallbackExecutorWrapper = new CameraManagerCompat.AvailabilityCallbackExecutorWrapper(executor, callback);
                    cameraManagerCompatParamsApi21.mWrapperMap.put(callback, availabilityCallbackExecutorWrapper);
                }
            }
        }
        this.mCameraManager.registerAvailabilityCallback(availabilityCallbackExecutorWrapper, cameraManagerCompatParamsApi21.mCompatHandler);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback callback) {
        CameraManagerCompat.AvailabilityCallbackExecutorWrapper availabilityCallbackExecutorWrapperRemove;
        if (callback != null) {
            CameraManagerCompatParamsApi21 cameraManagerCompatParamsApi21 = (CameraManagerCompatParamsApi21) this.mObject;
            synchronized (cameraManagerCompatParamsApi21.mWrapperMap) {
                availabilityCallbackExecutorWrapperRemove = cameraManagerCompatParamsApi21.mWrapperMap.remove(callback);
            }
        } else {
            availabilityCallbackExecutorWrapperRemove = null;
        }
        if (availabilityCallbackExecutorWrapperRemove != null) {
            availabilityCallbackExecutorWrapperRemove.setDisabled();
        }
        this.mCameraManager.unregisterAvailabilityCallback(availabilityCallbackExecutorWrapperRemove);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public CameraCharacteristics getCameraCharacteristics(String cameraId) throws CameraAccessExceptionCompat {
        try {
            return this.mCameraManager.getCameraCharacteristics(cameraId);
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void openCamera(String cameraId, Executor executor, CameraDevice.StateCallback callback) throws CameraAccessException, CameraAccessExceptionCompat {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(callback);
        try {
            this.mCameraManager.openCamera(cameraId, new CameraDeviceCompat.StateCallbackExecutorWrapper(executor, callback), ((CameraManagerCompatParamsApi21) this.mObject).mCompatHandler);
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public CameraManager getCameraManager() {
        return this.mCameraManager;
    }

    static final class CameraManagerCompatParamsApi21 {
        final Handler mCompatHandler;
        final Map<CameraManager.AvailabilityCallback, CameraManagerCompat.AvailabilityCallbackExecutorWrapper> mWrapperMap = new HashMap();

        CameraManagerCompatParamsApi21(Handler compatHandler) {
            this.mCompatHandler = compatHandler;
        }
    }
}
