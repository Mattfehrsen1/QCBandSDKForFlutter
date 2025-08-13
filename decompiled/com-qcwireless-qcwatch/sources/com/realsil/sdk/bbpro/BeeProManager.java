package com.realsil.sdk.bbpro;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import com.realsil.sdk.bbpro.core.BeeError;
import com.realsil.sdk.bbpro.internal.BaseBeeProManager;
import com.realsil.sdk.bbpro.model.DeviceInfo;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.List;

/* loaded from: classes3.dex */
public class BeeProManager extends BaseBeeProManager {
    public static volatile BeeProManager G;

    public BeeProManager(Context context) {
        super(context);
    }

    public static synchronized BeeProManager getInstance(Context context) {
        if (G == null) {
            synchronized (BeeProManager.class) {
                if (G == null) {
                    G = new BeeProManager(context.getApplicationContext());
                }
            }
        }
        return G;
    }

    public final BluetoothDevice a(String str) {
        BluetoothAdapter bluetoothAdapter;
        if (TextUtils.isEmpty(str) || (bluetoothAdapter = this.a) == null) {
            return null;
        }
        try {
            return bluetoothAdapter.getRemoteDevice(str);
        } catch (Exception e) {
            ZLogger.e(e.toString());
            return null;
        }
    }

    public BeeError connect(int i, String str) {
        return connect(a(str));
    }

    @Override // com.realsil.sdk.bbpro.internal.BaseBeeProManager
    public void destroy() {
        super.destroy();
        G = null;
    }

    public BeeError disconnect(int i) {
        return disconnect();
    }

    public BluetoothDevice getConnectedDevice() {
        return getConnectedDevice(1);
    }

    public String getDeviceLeAddr() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo != null ? deviceInfo.getLeAddr() : "";
    }

    public String getDeviceLeName() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo != null ? deviceInfo.getLeName() : "";
    }

    public String getDeviceName() {
        DeviceInfo deviceInfo = getDeviceInfo();
        return deviceInfo != null ? deviceInfo.getBrEdrName() : "";
    }

    public boolean isAudioPassthroughEnabled() {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (deviceInfo != null) {
            return deviceInfo.isAptEnabled();
        }
        return false;
    }

    public boolean isConnected(int i) {
        return isConnected();
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
