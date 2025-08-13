package com.oudmon.ble.base.communication.req;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/BrightnessSettingsReq.class */
public class BrightnessSettingsReq extends MixtureReq {
    private BrightnessSettingsReq() {
        super((byte) 27);
    }

    public static BrightnessSettingsReq getReadInstance() {
        return new BrightnessSettingsReq() { // from class: com.oudmon.ble.base.communication.req.BrightnessSettingsReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static BrightnessSettingsReq getWriteInstance(final int level) {
        return new BrightnessSettingsReq() { // from class: com.oudmon.ble.base.communication.req.BrightnessSettingsReq.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.subData = new byte[]{2, (byte) level};
            }
        };
    }
}
