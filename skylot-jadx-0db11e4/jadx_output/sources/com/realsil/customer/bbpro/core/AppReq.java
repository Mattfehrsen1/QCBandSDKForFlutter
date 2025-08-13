package com.realsil.customer.bbpro.core;

import com.realsil.customer.bbpro.core.transportlayer.Command;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/core/AppReq.class */
public abstract class AppReq {
    public abstract short getCommandId();

    public short getEventId() {
        return (short) 0;
    }

    public abstract Command encode(int i);

    public abstract Command encode();
}
