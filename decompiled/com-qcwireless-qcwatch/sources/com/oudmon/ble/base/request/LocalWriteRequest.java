package com.oudmon.ble.base.request;

import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import java.util.UUID;

/* loaded from: classes3.dex */
public class LocalWriteRequest<T extends BaseRspCmd> extends WriteRequest {
    private ICommandResponse<T> iOpResponse;
    private int type;

    public LocalWriteRequest(UUID uuid, UUID uuid2) {
        super(uuid, uuid2);
    }

    public ICommandResponse<T> getiOpResponse() {
        return this.iOpResponse;
    }

    public void setiOpResponse(ICommandResponse<T> iCommandResponse) {
        this.iOpResponse = iCommandResponse;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
