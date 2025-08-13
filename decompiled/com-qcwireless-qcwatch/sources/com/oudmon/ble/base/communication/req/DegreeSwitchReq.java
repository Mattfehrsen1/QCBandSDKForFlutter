package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class DegreeSwitchReq extends MixtureReq {
    private DegreeSwitchReq() {
        super(Constants.CMD_GET_DEGREE_SWITCH);
    }

    public static DegreeSwitchReq getReadInstance() {
        return new DegreeSwitchReq() { // from class: com.oudmon.ble.base.communication.req.DegreeSwitchReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static DegreeSwitchReq getWriteInstance(boolean z, boolean z2) {
        return new DegreeSwitchReq(z, z2) { // from class: com.oudmon.ble.base.communication.req.DegreeSwitchReq.2
            final /* synthetic */ boolean val$enable;
            final /* synthetic */ boolean val$isCelsius;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$enable = z;
                this.val$isCelsius = z2;
                byte[] bArr = new byte[3];
                bArr[0] = 2;
                bArr[1] = (byte) (z ? 1 : 2);
                bArr[2] = (byte) (z2 ? 1 : 2);
                this.subData = bArr;
            }
        };
    }
}
