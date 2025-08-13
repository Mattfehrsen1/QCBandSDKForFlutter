package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/MuslimTargetReq.class */
public class MuslimTargetReq extends MixtureReq {
    private byte index;

    public MuslimTargetReq() {
        super((byte) 123);
        this.subData = new byte[]{1, 1};
    }

    public MuslimTargetReq(int goal) {
        super((byte) 123);
        byte[] a = {2, 1};
        byte[] b = ByteUtil.concat(a, ByteUtil.intToByte(goal, 4));
        this.subData = b;
    }

    public MuslimTargetReq(int goal, boolean isEnable) {
        super((byte) 123);
        byte[] a = {2, 1};
        byte[] b = ByteUtil.concat(a, ByteUtil.intToByte(goal, 4));
        byte[] c = isEnable ? new byte[]{1} : new byte[]{2};
        this.subData = ByteUtil.concat(b, c);
    }
}
