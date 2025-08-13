package com.oudmon.ble.base.bluetooth.queue;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/queue/BleDataBean.class */
public class BleDataBean {
    private byte[] data;
    private int subLength;

    public BleDataBean(byte[] data, int subLength) {
        this.data = data;
        this.subLength = subLength;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getSubLength() {
        return this.subLength;
    }

    public void setSubLength(int subLength) {
        this.subLength = subLength;
    }
}
