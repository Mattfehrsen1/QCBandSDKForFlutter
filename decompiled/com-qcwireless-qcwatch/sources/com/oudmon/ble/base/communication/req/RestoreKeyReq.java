package com.oudmon.ble.base.communication.req;

import com.realsil.sdk.bbpro.params.Mmi;

/* loaded from: classes3.dex */
public class RestoreKeyReq extends SimpleKeyReq {
    public RestoreKeyReq(byte b) {
        super(b);
    }

    @Override // com.oudmon.ble.base.communication.req.SimpleKeyReq, com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        return new byte[]{Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE, Mmi.AU_MMI_SWITCH_NEXT_VOICE_PROMPT_LANGUAGE};
    }
}
