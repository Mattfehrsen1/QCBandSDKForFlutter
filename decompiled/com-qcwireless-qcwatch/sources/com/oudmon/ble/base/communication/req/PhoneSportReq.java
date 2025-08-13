package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class PhoneSportReq extends MixtureReq {
    private PhoneSportReq() {
        super((byte) 119);
    }

    public static PhoneSportReq getSportStatus(byte b, byte b2) {
        return new PhoneSportReq(b, b2) { // from class: com.oudmon.ble.base.communication.req.PhoneSportReq.1
            final /* synthetic */ byte val$sportType;
            final /* synthetic */ byte val$status;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$status = b;
                this.val$sportType = b2;
                this.subData = new byte[]{b, b2};
            }
        };
    }
}
