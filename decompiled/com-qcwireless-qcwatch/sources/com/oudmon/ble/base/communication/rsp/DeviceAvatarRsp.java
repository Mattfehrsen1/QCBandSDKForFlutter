package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class DeviceAvatarRsp extends BaseRspCmd {
    private int avatarHeight;
    private int avatarWidth;
    private int screenType;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        this.screenType = bArr[0];
        this.avatarWidth = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 1, 3));
        this.avatarHeight = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 3, 5));
        return false;
    }

    public int getScreenType() {
        return this.screenType;
    }

    public int getAvatarWidth() {
        return this.avatarWidth;
    }

    public int getAvatarHeight() {
        return this.avatarHeight;
    }
}
