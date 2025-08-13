package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class CameraManagerCompatApi28Impl extends CameraManagerCompatBaseImpl {
    CameraManagerCompatApi28Impl(Context context) {
        super(context, null);
    }

    static CameraManagerCompatApi28Impl create(Context context) {
        return new CameraManagerCompatApi28Impl(context);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompatBaseImpl, androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void registerAvailabilityCallback(Executor executor, CameraManager.AvailabilityCallback callback) {
        this.mCameraManager.registerAvailabilityCallback(executor, callback);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompatBaseImpl, androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback callback) {
        this.mCameraManager.unregisterAvailabilityCallback(callback);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompatBaseImpl, androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public void openCamera(String cameraId, Executor executor, CameraDevice.StateCallback callback) throws CameraAccessException, CameraAccessExceptionCompat {
        try {
            this.mCameraManager.openCamera(cameraId, executor, callback);
        } catch (CameraAccessException e) {
            throw CameraAccessExceptionCompat.toCameraAccessExceptionCompat(e);
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (SecurityException e3) {
        } catch (RuntimeException e4) {
            if (isDndFailCase(e4)) {
                throwDndException(e4);
            }
            throw e4;
        }
    }

    @Override // androidx.camera.camera2.internal.compat.CameraManagerCompatBaseImpl, androidx.camera.camera2.internal.compat.CameraManagerCompat.CameraManagerCompatImpl
    public CameraCharacteristics getCameraCharacteristics(String cameraId) throws CameraAccessExceptionCompat {
        try {
            return super.getCameraCharacteristics(cameraId);
        } catch (RuntimeException e) {
            if (isDndFailCase(e)) {
                throwDndException(e);
            }
            throw e;
        }
    }

    private void throwDndException(Throwable cause) throws CameraAccessExceptionCompat {
        throw new CameraAccessExceptionCompat(CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB, cause);
    }

    private boolean isDndFailCase(Throwable throwable) {
        return Build.VERSION.SDK_INT == 28 && isDndRuntimeException(throwable);
    }

    private static boolean isDndRuntimeException(Throwable throwable) {
        StackTraceElement[] stackTrace;
        if (!throwable.getClass().equals(RuntimeException.class) || (stackTrace = throwable.getStackTrace()) == null || stackTrace.length < 0) {
            return false;
        }
        return "_enableShutterSound".equals(stackTrace[0].getMethodName());
    }
}
