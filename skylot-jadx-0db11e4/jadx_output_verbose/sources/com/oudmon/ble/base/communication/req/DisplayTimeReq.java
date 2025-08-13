package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DisplayTimeReq.class */
public class DisplayTimeReq extends MixtureReq {
    private DisplayTimeReq() {
        super((byte) 31);
    }

    public static DisplayTimeReq getReadInstance() {
        return new DisplayTimeReq() { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static DisplayTimeReq getWriteInstance(final int displayTime, final int displayType, final int alpha, final int total, final int curr) {
        return new DisplayTimeReq() { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.subData = new byte[]{2, (byte) displayTime, (byte) displayType, (byte) alpha, 0, (byte) total, (byte) curr};
            }
        };
    }

    public static DisplayTimeReq getWriteInstanceNew(final int displayTime, final int displayType, final int alpha, final int total, final int curr, final boolean open) {
        return new DisplayTimeReq() { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                byte[] bArr = new byte[11];
                bArr[0] = 2;
                bArr[1] = (byte) displayTime;
                bArr[2] = (byte) displayType;
                bArr[3] = (byte) alpha;
                bArr[4] = 0;
                bArr[5] = (byte) total;
                bArr[6] = (byte) curr;
                bArr[7] = 5;
                bArr[8] = 30;
                bArr[9] = 5;
                bArr[10] = (byte) (open ? 2 : 1);
                this.subData = bArr;
            }
        };
    }

    public static DisplayTimeReq getDeleteInstance() {
        return new DisplayTimeReq() { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.4
            {
                this.subData = new byte[]{3};
            }
        };
    }
}
