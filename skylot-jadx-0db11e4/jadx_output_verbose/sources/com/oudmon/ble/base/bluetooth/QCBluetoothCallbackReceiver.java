package com.oudmon.ble.base.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/QCBluetoothCallbackReceiver.class */
public class QCBluetoothCallbackReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case "com.qc.sdk.ble.gatt_connected":
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BleAction.EXTRA_DEVICE);
                connectStatue(device, true);
                break;
            case "com.qc.sdk.ble.gatt_disconnected":
            case "com.qc.sdk.ble.BLE_NO_CALLBACK":
                connectStatue(null, false);
                break;
            case "com.qc.characteristic_write_qc":
                byte[] data = intent.getByteArrayExtra(BleAction.EXTRA_DATA);
                onCommandSend(data);
                break;
            case "com.qc.characteristic_changed_qc":
                String address = intent.getStringExtra(BleAction.EXTRA_ADDR);
                String uuid = intent.getStringExtra(BleAction.EXTRA_CHARACTER_UUID);
                byte[] data2 = intent.getByteArrayExtra(BleAction.EXTRA_VALUE);
                onCharacteristicChange(address, uuid, data2);
                break;
            case "com.qc.sdk.ble.service_discovered":
                onServiceDiscovered();
                break;
        }
    }

    public void connectStatue(BluetoothDevice device, boolean connected) {
        if (device != null) {
            DeviceManager.getInstance().setDeviceName(device.getName());
            DeviceManager.getInstance().setDeviceAddress(device.getAddress());
        }
    }

    public void onServiceDiscovered() {
    }

    public void onCommandSend(byte[] data) {
    }

    public void onCharacteristicChange(String address, String uuid, byte[] data) {
        Log.i(Constants.TAG, "device->app：" + DataTransferUtils.getHexString(data));
        if (data != null && data.length == Constants.CMD_DATA_LENGTH && QCDataParser.checkCrc(data) && !QCDataParser.parserAndDispatchReqData(data)) {
            QCDataParser.parserAndDispatchNotifyData(BleOperateManager.getInstance().getNotifySparseArray(), data);
        }
    }
}
