package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.util.ArrayMap;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class CameraManagerCompat {
    private final Map<String, CameraCharacteristicsCompat> mCameraCharacteristicsMap = new ArrayMap(4);
    private final CameraManagerCompatImpl mImpl;

    private CameraManagerCompat(CameraManagerCompatImpl impl) {
        this.mImpl = impl;
    }

    public static CameraManagerCompat from(Context context) {
        return from(context, MainThreadAsyncHandler.getInstance());
    }

    public static CameraManagerCompat from(Context context, Handler compatHandler) {
        return new CameraManagerCompat(CameraManagerCompatImpl.CC.from(context, compatHandler));
    }

    public static CameraManagerCompat from(final CameraManagerCompatImpl impl) {
        return new CameraManagerCompat(impl);
    }

    public String[] getCameraIdList() throws CameraAccessExceptionCompat {
        return this.mImpl.getCameraIdList();
    }

    public void registerAvailabilityCallback(Executor executor, CameraManager.AvailabilityCallback callback) {
        this.mImpl.registerAvailabilityCallback(executor, callback);
    }

    public void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback callback) {
        this.mImpl.unregisterAvailabilityCallback(callback);
    }

    public CameraCharacteristicsCompat getCameraCharacteristicsCompat(String cameraId) throws CameraAccessExceptionCompat {
        CameraCharacteristicsCompat cameraCharacteristicsCompat;
        synchronized (this.mCameraCharacteristicsMap) {
            cameraCharacteristicsCompat = this.mCameraCharacteristicsMap.get(cameraId);
            if (cameraCharacteristicsCompat == null) {
                cameraCharacteristicsCompat = CameraCharacteristicsCompat.toCameraCharacteristicsCompat(this.mImpl.getCameraCharacteristics(cameraId));
                this.mCameraCharacteristicsMap.put(cameraId, cameraCharacteristicsCompat);
            }
        }
        return cameraCharacteristicsCompat;
    }

    public void openCamera(String cameraId, Executor executor, CameraDevice.StateCallback callback) throws CameraAccessExceptionCompat {
        this.mImpl.openCamera(cameraId, executor, callback);
    }

    public CameraManager unwrap() {
        return this.mImpl.getCameraManager();
    }

    public interface CameraManagerCompatImpl {
        CameraCharacteristics getCameraCharacteristics(String cameraId) throws CameraAccessExceptionCompat;

        String[] getCameraIdList() throws CameraAccessExceptionCompat;

        CameraManager getCameraManager();

        void openCamera(String cameraId, Executor executor, CameraDevice.StateCallback callback) throws CameraAccessExceptionCompat;

        void registerAvailabilityCallback(Executor executor, CameraManager.AvailabilityCallback callback);

        void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback callback);

        /* renamed from: androidx.camera.camera2.internal.compat.CameraManagerCompat$CameraManagerCompatImpl$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static CameraManagerCompatImpl from(Context context, Handler compatHandler) {
                if (Build.VERSION.SDK_INT >= 29) {
                    return new CameraManagerCompatApi29Impl(context);
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    return CameraManagerCompatApi28Impl.create(context);
                }
                return CameraManagerCompatBaseImpl.create(context, compatHandler);
            }
        }
    }

    static final class AvailabilityCallbackExecutorWrapper extends CameraManager.AvailabilityCallback {
        private final Executor mExecutor;
        final CameraManager.AvailabilityCallback mWrappedCallback;
        private final Object mLock = new Object();
        private boolean mDisabled = false;

        AvailabilityCallbackExecutorWrapper(Executor executor, CameraManager.AvailabilityCallback wrappedCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = wrappedCallback;
        }

        void setDisabled() {
            synchronized (this.mLock) {
                this.mDisabled = true;
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAccessPrioritiesChanged() {
            synchronized (this.mLock) {
                if (!this.mDisabled) {
                    this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.CameraManagerCompat.AvailabilityCallbackExecutorWrapper.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AvailabilityCallbackExecutorWrapper.this.mWrappedCallback.onCameraAccessPrioritiesChanged();
                        }
                    });
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraAvailable(final String cameraId) {
            synchronized (this.mLock) {
                if (!this.mDisabled) {
                    this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.CameraManagerCompat.AvailabilityCallbackExecutorWrapper.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AvailabilityCallbackExecutorWrapper.this.mWrappedCallback.onCameraAvailable(cameraId);
                        }
                    });
                }
            }
        }

        @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
        public void onCameraUnavailable(final String cameraId) {
            synchronized (this.mLock) {
                if (!this.mDisabled) {
                    this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.compat.CameraManagerCompat.AvailabilityCallbackExecutorWrapper.3
                        @Override // java.lang.Runnable
                        public void run() {
                            AvailabilityCallbackExecutorWrapper.this.mWrappedCallback.onCameraUnavailable(cameraId);
                        }
                    });
                }
            }
        }
    }
}
