package com.oudmon.ble.base.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class QCBluetoothCallbackReceiver extends BroadcastReceiver {
    public void onCommandSend(byte[] bArr) {
    }

    public void onServiceDiscovered() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "com.qc.pro.sdk.ble.service_discovered":
                onServiceDiscovered();
                break;
            case "com.qc.pro.sdk.ble.gatt_connected":
                connectStatue((BluetoothDevice) intent.getParcelableExtra(BleAction.EXTRA_DEVICE), true);
                break;
            case "com.qc.pro.sdk.ble.BLE_NO_CALLBACK":
            case "com.qc.pro.sdk.ble.gatt_disconnected":
                connectStatue(null, false);
                break;
            case "com.qc.pro.characteristic_write_qc":
                onCommandSend(intent.getByteArrayExtra(BleAction.EXTRA_DATA));
                break;
            case "com.qc.pro.characteristic_changed_qc":
                onCharacteristicChange(intent.getStringExtra(BleAction.EXTRA_ADDR), intent.getStringExtra(BleAction.EXTRA_CHARACTER_UUID), intent.getByteArrayExtra(BleAction.EXTRA_VALUE));
                break;
        }
    }

    public void connectStatue(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice != null) {
            DeviceManager.getInstance().setDeviceName(bluetoothDevice.getName());
            DeviceManager.getInstance().setDeviceAddress(bluetoothDevice.getAddress());
        }
    }

    public void onCharacteristicChange(String str, String str2, byte[] bArr) {
        if (bArr == null || bArr.length != Constants.CMD_DATA_LENGTH || !QCDataParser.checkCrc(bArr) || QCDataParser.parserAndDispatchReqData(bArr)) {
            return;
        }
        QCDataParser.parserAndDispatchNotifyData(BleOperateManager.getInstance().getNotifySparseArray(), bArr);
    }
}
