package com.realsil.sdk.bbpro.profile;

import com.realsil.sdk.bbpro.core.transportlayer.Command;

/* loaded from: classes3.dex */
public abstract class AppReq {
    public abstract Command encode();

    public abstract Command encode(int i);

    public abstract short getCommandId();

    public short getEventId() {
        return (short) 0;
    }
}
