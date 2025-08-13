package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class MenstruationReq extends MixtureReq {
    public MenstruationReq(byte b) {
        super(Constants.CMD_MENSTRUATION);
        this.subData = new byte[]{1};
    }

    public MenstruationReq(boolean z, int i, int i2, int i3, int i4, boolean z2, int i5, int i6, int i7, int i8) {
        super(Constants.CMD_MENSTRUATION);
        this.subData = new byte[]{2, z ? (byte) 1 : (byte) 0, (byte) (i & 255), (byte) (i2 & 255), (byte) (i3 & 255), (byte) (i4 & 255), z2 ? (byte) 1 : (byte) 0, (byte) (i5 & 255), (byte) (i6 & 255), (byte) (i7 & 255), (byte) (i8 & 255)};
    }
}
