package com.oudmon.ble.base.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/QCBluetoothCallbackCloneReceiver.class */
public class QCBluetoothCallbackCloneReceiver extends BroadcastReceiver {
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
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra(BleAction.EXTRA_DEVICE);
                connectStatue(device2, false);
                break;
            case "com.qc.characteristic_write_qc":
                byte[] data = intent.getByteArrayExtra(BleAction.EXTRA_DATA);
                onCommandSend(data);
                break;
            case "com.qc.sdk.ble.characteristic_read":
                String uuid = intent.getStringExtra(BleAction.EXTRA_CHARACTER_UUID);
                byte[] data2 = intent.getByteArrayExtra(BleAction.EXTRA_VALUE);
                onCharacteristicRead(uuid, data2);
                break;
            case "com.qc.characteristic_changed_qc":
                String address = intent.getStringExtra(BleAction.EXTRA_ADDR);
                String uuid2 = intent.getStringExtra(BleAction.EXTRA_CHARACTER_UUID);
                byte[] data3 = intent.getByteArrayExtra(BleAction.EXTRA_VALUE);
                onCharacteristicChange(address, uuid2, data3);
                break;
            case "com.qc.sdk.ble.service_discovered":
                onServiceDiscovered();
                break;
        }
    }

    public void connectStatue(BluetoothDevice device, boolean connected) {
    }

    public void onCharacteristicRead(String uuid, byte[] data) {
    }

    public void onServiceDiscovered() {
    }

    public void onCommandSend(byte[] data) {
    }

    public void onCharacteristicChangeFilter(String address, String uuid, byte[] data) {
    }

    public void onCharacteristicChange(String address, String uuid, byte[] data) {
    }
}
