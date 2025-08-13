package com.realsil.customer.bbpro;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import com.realsil.customer.bbpro.core.BeeError;
import com.realsil.customer.bbpro.internal.BaseBeeProManager;
import com.realsil.customer.bbpro.model.DeviceInfo;
import com.realsil.customer.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.customer.core.bluetooth.utils.BluetoothHelper;
import com.realsil.customer.core.logger.ZLogger;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/BeeProManager.class */
public class BeeProManager extends BaseBeeProManager {
    public static volatile BeeProManager G;

    public BeeProManager(Context context) {
        super(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.realsil.customer.bbpro.BeeProManager>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static synchronized BeeProManager getInstance(Context context) {
        if (G == null) {
            ?? r0 = BeeProManager.class;
            synchronized (r0) {
                if (G == null) {
                    G = new BeeProManager(context.getApplicationContext());
                }
                r0 = r0;
            }
        }
        return G;
    }

    public BluetoothDevice getConnectedDevice() {
        return getConnectedDevice(1);
    }

    public BeeError connect(int i, String str) {
        return connect(a(str));
    }

    public BeeError disconnect(int i) {
        return disconnect();
    }

    public boolean isConnected(int i) {
        return isConnected();
    }

    public String getDeviceLeAddr() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo != null ? deviceInfo.getLeAddr() : "";
    }

    public String getDeviceName() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo != null ? deviceInfo.getBrEdrName() : "";
    }

    public String getDeviceLeName() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo != null ? deviceInfo.getLeName() : "";
    }

    public boolean isAudioPassthroughEnabled() {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (deviceInfo != null) {
            return deviceInfo.isAptEnabled();
        }
        return false;
    }

    public boolean isEqSettingsEnabled() {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (deviceInfo != null) {
            return deviceInfo.isEqSettingsEnabled();
        }
        return false;
    }

    public boolean isOtaOverSppEnabled() {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (deviceInfo != null) {
            return deviceInfo.isSppOtaSupported();
        }
        return false;
    }

    @Override // com.realsil.customer.bbpro.internal.BaseBeeProManager
    public void destroy() {
        super.destroy();
        G = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.bluetooth.BluetoothAdapter] */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.bluetooth.BluetoothDevice] */
    public final BluetoothDevice a(String str) {
        BluetoothAdapter bluetoothAdapter;
        if (TextUtils.isEmpty(str) || (bluetoothAdapter = this.a) == null) {
            return null;
        }
        Object remoteDevice = bluetoothAdapter;
        BluetoothDevice bluetoothDevice = null;
        try {
            remoteDevice = remoteDevice.getRemoteDevice(str);
            bluetoothDevice = remoteDevice;
        } catch (Exception unused) {
            ZLogger.e(remoteDevice.toString());
        }
        return bluetoothDevice;
    }

    public BluetoothDevice getConnectedDevice(int i) {
        BluetoothDevice curDevice = getCurDevice();
        if (curDevice != null) {
            return curDevice;
        }
        List<BluetoothDevice> connectedBluetoothDevices = BluetoothHelper.getConnectedBluetoothDevices(i);
        if (connectedBluetoothDevices.size() <= 0) {
            ZLogger.d("no connected device exist.");
            return null;
        }
        for (BluetoothDevice bluetoothDevice : connectedBluetoothDevices) {
            if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public BeeError connect(int i, BluetoothDevice bluetoothDevice) {
        return connect(bluetoothDevice);
    }
}
