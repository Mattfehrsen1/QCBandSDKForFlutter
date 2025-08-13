package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class BrightnessSettingsReq extends MixtureReq {
    private BrightnessSettingsReq() {
        super(Constants.CMD_GET_BRIGHTNESS);
    }

    public static BrightnessSettingsReq getReadInstance() {
        return new BrightnessSettingsReq() { // from class: com.oudmon.ble.base.communication.req.BrightnessSettingsReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static BrightnessSettingsReq getWriteInstance(int i) {
        return new BrightnessSettingsReq(i) { // from class: com.oudmon.ble.base.communication.req.BrightnessSettingsReq.2
            final /* synthetic */ int val$level;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$level = i;
                this.subData = new byte[]{2, (byte) i};
            }
        };
    }
}
