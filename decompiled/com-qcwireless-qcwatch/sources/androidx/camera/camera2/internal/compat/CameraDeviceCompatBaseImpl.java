package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class CameraDeviceCompatBaseImpl implements CameraDeviceCompat.CameraDeviceCompatImpl {
    final CameraDevice mCameraDevice;
    final Object mImplParams;

    CameraDeviceCompatBaseImpl(CameraDevice cameraDevice, Object implParams) {
        this.mCameraDevice = (CameraDevice) Preconditions.checkNotNull(cameraDevice);
        this.mImplParams = implParams;
    }

    static CameraDeviceCompatBaseImpl create(CameraDevice cameraDevice, Handler compatHandler) {
        return new CameraDeviceCompatBaseImpl(cameraDevice, new CameraDeviceCompatParamsApi21(compatHandler));
    }

    static List<Surface> unpackSurfaces(List<OutputConfigurationCompat> outputConfigs) {
        ArrayList arrayList = new ArrayList(outputConfigs.size());
        Iterator<OutputConfigurationCompat> it = outputConfigs.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSurface());
        }
        return arrayList;
    }

    static void checkPreconditions(CameraDevice device, SessionConfigurationCompat config) {
        Preconditions.checkNotNull(device);
        Preconditions.checkNotNull(config);
        Preconditions.checkNotNull(config.getStateCallback());
        List<OutputConfigurationCompat> outputConfigurations = config.getOutputConfigurations();
        if (outputConfigurations == null) {
            throw new IllegalArgumentException("Invalid output configurations");
        }
        if (config.getExecutor() == null) {
            throw new IllegalArgumentException("Invalid executor");
        }
        checkPhysicalCameraIdValid(device, outputConfigurations);
    }

    private static void checkPhysicalCameraIdValid(CameraDevice device, List<OutputConfigurationCompat> outputConfigs) {
        String id = device.getId();
        Iterator<OutputConfigurationCompat> it = outputConfigs.iterator();
        while (it.hasNext()) {
            String physicalCameraId = it.next().getPhysicalCameraId();
            if (physicalCameraId != null && !physicalCameraId.isEmpty()) {
                Logger.w("CameraDeviceCompat", "Camera " + id + ": Camera doesn't support physicalCameraId " + physicalCameraId + ". Ignoring.");
            }
        }
    }

    void createBaseCaptureSession(CameraDevice device, List<Surface> surfaces, CameraCaptureSession.StateCallback cb, Handler handler) throws CameraAccessException {
        device.createCaptureSession(surfaces, cb, handler);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraDeviceCompat.CameraDeviceCompatImpl
    public void createCaptureSession(SessionConfigurationCompat config) throws CameraAccessException {
        checkPreconditions(this.mCameraDevice, config);
        if (config.getInputConfiguration() != null) {
            throw new IllegalArgumentException("Reprocessing sessions not supported until API 23");
        }
        if (config.getSessionType() == 1) {
            throw new IllegalArgumentException("High speed capture sessions not supported until API 23");
        }
        CameraCaptureSessionCompat.StateCallbackExecutorWrapper stateCallbackExecutorWrapper = new CameraCaptureSessionCompat.StateCallbackExecutorWrapper(config.getExecutor(), config.getStateCallback());
        createBaseCaptureSession(this.mCameraDevice, unpackSurfaces(config.getOutputConfigurations()), stateCallbackExecutorWrapper, ((CameraDeviceCompatParamsApi21) this.mImplParams).mCompatHandler);
    }

    @Override // androidx.camera.camera2.internal.compat.CameraDeviceCompat.CameraDeviceCompatImpl
    public CameraDevice unwrap() {
        return this.mCameraDevice;
    }

    static class CameraDeviceCompatParamsApi21 {
        final Handler mCompatHandler;

        CameraDeviceCompatParamsApi21(Handler compatHandler) {
            this.mCompatHandler = compatHandler;
        }
    }
}
