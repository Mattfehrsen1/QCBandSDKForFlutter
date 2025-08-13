package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class CameraDeviceStateCallbacks {
    private CameraDeviceStateCallbacks() {
    }

    public static CameraDevice.StateCallback createNoOpCallback() {
        return new NoOpDeviceStateCallback();
    }

    public static CameraDevice.StateCallback createComboCallback(List<CameraDevice.StateCallback> callbacks) {
        if (callbacks.isEmpty()) {
            return createNoOpCallback();
        }
        if (callbacks.size() == 1) {
            return callbacks.get(0);
        }
        return new ComboDeviceStateCallback(callbacks);
    }

    public static CameraDevice.StateCallback createComboCallback(CameraDevice.StateCallback... callbacks) {
        return createComboCallback((List<CameraDevice.StateCallback>) Arrays.asList(callbacks));
    }

    static final class NoOpDeviceStateCallback extends CameraDevice.StateCallback {
        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int error) {
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
        }

        NoOpDeviceStateCallback() {
        }
    }

    private static final class ComboDeviceStateCallback extends CameraDevice.StateCallback {
        private final List<CameraDevice.StateCallback> mCallbacks = new ArrayList();

        ComboDeviceStateCallback(List<CameraDevice.StateCallback> callbacks) {
            for (CameraDevice.StateCallback stateCallback : callbacks) {
                if (!(stateCallback instanceof NoOpDeviceStateCallback)) {
                    this.mCallbacks.add(stateCallback);
                }
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onOpened(CameraDevice cameraDevice) {
            Iterator<CameraDevice.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onOpened(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onClosed(CameraDevice cameraDevice) {
            Iterator<CameraDevice.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onClosed(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onDisconnected(CameraDevice cameraDevice) {
            Iterator<CameraDevice.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDisconnected(cameraDevice);
            }
        }

        @Override // android.hardware.camera2.CameraDevice.StateCallback
        public void onError(CameraDevice cameraDevice, int error) {
            Iterator<CameraDevice.StateCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onError(cameraDevice, error);
            }
        }
    }
}
