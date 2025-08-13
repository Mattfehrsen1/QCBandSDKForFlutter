package com.oudmon.ble.base.communication.req;

/* loaded from: classes3.dex */
public class PushMsgUintReq extends BaseReqCmd {
    public static final byte TYPE_FACEBOOK = 5;
    public static final byte TYPE_Line = 9;
    public static final byte TYPE_PHONE_ACTION = 4;
    public static final byte TYPE_PHONE_RING = 0;
    public static final byte TYPE_QQ = 2;
    public static final byte TYPE_SKYPE = 8;
    public static final byte TYPE_SMS = 1;
    public static final byte TYPE_TWITTER = 7;
    public static final byte TYPE_WECHAT = 3;
    public static final byte TYPE_WHATSAPP = 6;
    private byte[] subData;

    public PushMsgUintReq(byte b, int i, int i2, byte[] bArr) {
        super((byte) 114);
        byte[] bArr2 = new byte[bArr.length + 3];
        this.subData = bArr2;
        bArr2[0] = b;
        bArr2[1] = (byte) i;
        bArr2[2] = (byte) i2;
        System.arraycopy(bArr, 0, bArr2, 3, bArr.length);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.subData;
    }
}
