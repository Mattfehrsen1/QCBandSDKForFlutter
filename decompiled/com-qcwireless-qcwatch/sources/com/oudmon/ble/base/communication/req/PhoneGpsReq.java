package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: classes3.dex */
public class PhoneGpsReq extends MixtureReq {
    private PhoneGpsReq() {
        super((byte) 116);
    }

    public static PhoneGpsReq getGpsStatus(byte b) {
        return new PhoneGpsReq(b) { // from class: com.oudmon.ble.base.communication.req.PhoneGpsReq.1
            final /* synthetic */ byte val$status;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$status = b;
                this.subData = new byte[]{b, 0};
            }
        };
    }

    public static PhoneGpsReq setPhoneDataReq(int i, int i2) {
        return new PhoneGpsReq(i, i2) { // from class: com.oudmon.ble.base.communication.req.PhoneGpsReq.2
            final /* synthetic */ int val$calorie;
            final /* synthetic */ int val$distance;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$distance = i;
                this.val$calorie = i2;
                byte[] bArrIntToByte = ByteUtil.intToByte(i, 4);
                this.subData = ByteUtil.concat(ByteUtil.concat(new byte[]{5, 0}, bArrIntToByte), ByteUtil.intToByte(i2, 4));
            }
        };
    }
}
