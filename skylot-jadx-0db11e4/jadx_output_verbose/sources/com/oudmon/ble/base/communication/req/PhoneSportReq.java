package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/PhoneSportReq.class */
public class PhoneSportReq extends MixtureReq {
    private PhoneSportReq() {
        super((byte) 119);
    }

    public static PhoneSportReq getSportStatus(final byte status, final byte sportType) {
        return new PhoneSportReq() { // from class: com.oudmon.ble.base.communication.req.PhoneSportReq.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.subData = new byte[]{status, sportType};
            }
        };
    }
}
