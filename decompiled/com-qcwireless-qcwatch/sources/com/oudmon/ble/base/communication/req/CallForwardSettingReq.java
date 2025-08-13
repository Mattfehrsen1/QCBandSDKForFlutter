package com.oudmon.ble.base.communication.req;

import com.bumptech.glide.load.Key;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public class CallForwardSettingReq extends MixtureReq {
    private CallForwardSettingReq() {
        super((byte) 51);
        this.subData = new byte[]{1};
    }

    private CallForwardSettingReq(boolean z, int i, String str) throws UnsupportedEncodingException {
        super((byte) 51);
        byte b = z ? (byte) i : (byte) 0;
        try {
            byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
            this.subData = ByteUtil.concat(new byte[]{2, b, (byte) bytes.length}, bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static CallForwardSettingReq getReadInstance() {
        return new CallForwardSettingReq();
    }

    public static CallForwardSettingReq getWriteInstance(boolean z, int i, String str) {
        return new CallForwardSettingReq(z, i, str);
    }
}
