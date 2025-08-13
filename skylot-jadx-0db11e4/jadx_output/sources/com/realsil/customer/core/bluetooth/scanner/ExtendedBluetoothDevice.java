package com.realsil.customer.core.bluetooth.scanner;

import android.bluetooth.BluetoothDevice;
import com.realsil.customer.core.bluetooth.utils.BluetoothUuid;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/ExtendedBluetoothDevice.class */
public class ExtendedBluetoothDevice {
    public static final int NO_RSSI = -1000;
    public static final boolean DEVICE_IS_BONDED = true;
    public static final boolean DEVICE_NOT_BONDED = false;
    public BluetoothDevice device;
    public String name;
    public int rssi;
    public boolean isBonded;
    public boolean isConnected;
    public int a;
    public byte[] scanRecord;
    public SpecScanRecord specScanRecord;
    public boolean b;
    public long timestamp;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/bluetooth/scanner/ExtendedBluetoothDevice$AddressComparator.class */
    public static class AddressComparator {
        public String address;

        public boolean equals(Object obj) {
            return obj instanceof ExtendedBluetoothDevice ? this.address.equals(((ExtendedBluetoothDevice) obj).device.getAddress()) : super.equals(obj);
        }
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str) {
        this(bluetoothDevice, str, NO_RSSI, false, false, null);
    }

    public boolean equals(Object obj) {
        return obj instanceof ExtendedBluetoothDevice ? this.device.getAddress().equals(((ExtendedBluetoothDevice) obj).device.getAddress()) : super.equals(obj);
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getRssi() {
        return this.rssi;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public boolean isBonded() {
        return this.isBonded;
    }

    public void setBonded(boolean z) {
        this.isBonded = z;
    }

    public boolean isConnect() {
        return this.isConnected;
    }

    public void setConnect(boolean z) {
        this.isConnected = z;
    }

    public int getConnectState() {
        return this.a;
    }

    public void setConnectState(int i) {
        this.a = i;
        this.isConnected = i == 2;
    }

    public byte[] getScanRecord() {
        return this.scanRecord;
    }

    public void setScanRecord(byte[] bArr) {
        this.scanRecord = bArr;
        SpecScanRecord fromBytes = SpecScanRecord.parseFromBytes(bArr);
        this.specScanRecord = fromBytes;
        if (fromBytes == null || fromBytes.getServiceUuids() == null) {
            return;
        }
        this.b = this.specScanRecord.getServiceUuids().contains(BluetoothUuid.HOGP);
    }

    public SpecScanRecord getSpecScanRecord() {
        return this.specScanRecord;
    }

    public boolean isHogp() {
        return this.b;
    }

    public void setHogp(boolean z) {
        this.b = z;
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i) {
        this(bluetoothDevice, str, i, false, false, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i, boolean z, boolean z2) {
        this(bluetoothDevice, str, i, z, z2, null);
    }

    public ExtendedBluetoothDevice(BluetoothDevice bluetoothDevice, String str, int i, boolean z, boolean z2, byte[] bArr) {
        this.device = bluetoothDevice;
        this.name = str;
        this.rssi = i;
        this.isBonded = z;
        this.isConnected = z2;
        setScanRecord(bArr);
    }
}
