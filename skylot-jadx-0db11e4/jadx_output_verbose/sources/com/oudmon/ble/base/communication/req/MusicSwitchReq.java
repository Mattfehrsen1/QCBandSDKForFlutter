package com.oudmon.ble.base.communication.req;

import java.nio.charset.StandardCharsets;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/MusicSwitchReq.class */
public class MusicSwitchReq extends BaseReqCmd {
    protected byte[] data;

    private MusicSwitchReq() {
        super((byte) 28);
    }

    public static MusicSwitchReq getReadInstance() {
        return new MusicSwitchReq() { // from class: com.oudmon.ble.base.communication.req.MusicSwitchReq.1
            {
                this.data = new byte[]{1};
            }
        };
    }

    public static MusicSwitchReq getWriteInstance(final boolean enable) {
        return new MusicSwitchReq() { // from class: com.oudmon.ble.base.communication.req.MusicSwitchReq.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                byte[] bArr = new byte[2];
                bArr[0] = 2;
                bArr[1] = (byte) (enable ? 1 : 2);
                this.data = bArr;
            }
        };
    }

    public static MusicSwitchReq getNewWriteInstance(final boolean playing, final int progress, final int volume, final String name) {
        return new MusicSwitchReq() { // from class: com.oudmon.ble.base.communication.req.MusicSwitchReq.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
                byte[] data = new byte[nameBytes.length + 3];
                data[0] = (byte) (playing ? 0 : 1);
                data[1] = (byte) progress;
                data[2] = (byte) volume;
                System.arraycopy(nameBytes, 0, data, 3, nameBytes.length);
                this.data = data;
            }
        };
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return this.data;
    }
}
