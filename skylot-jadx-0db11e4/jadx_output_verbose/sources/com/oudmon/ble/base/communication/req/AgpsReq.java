package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/AgpsReq.class */
public class AgpsReq extends MixtureReq {
    private AgpsReq() {
        super((byte) 48);
    }

    public static AgpsReq getReadInstance() {
        return new AgpsReq() { // from class: com.oudmon.ble.base.communication.req.AgpsReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static AgpsReq getWriteInstance(final boolean enable) {
        return new AgpsReq() { // from class: com.oudmon.ble.base.communication.req.AgpsReq.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                byte[] bArr = new byte[2];
                bArr[0] = 2;
                bArr[1] = (byte) (enable ? 1 : 0);
                this.subData = bArr;
            }
        };
    }
}
