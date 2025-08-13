package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/DegreeSwitchReq.class */
public class DegreeSwitchReq extends MixtureReq {
    private DegreeSwitchReq() {
        super((byte) 25);
    }

    public static DegreeSwitchReq getReadInstance() {
        return new DegreeSwitchReq() { // from class: com.oudmon.ble.base.communication.req.DegreeSwitchReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static DegreeSwitchReq getWriteInstance(final boolean enable, final boolean isCelsius) {
        return new DegreeSwitchReq() { // from class: com.oudmon.ble.base.communication.req.DegreeSwitchReq.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                byte[] bArr = new byte[3];
                bArr[0] = 2;
                bArr[1] = (byte) (enable ? 1 : 2);
                bArr[2] = (byte) (isCelsius ? 1 : 2);
                this.subData = bArr;
            }
        };
    }
}
