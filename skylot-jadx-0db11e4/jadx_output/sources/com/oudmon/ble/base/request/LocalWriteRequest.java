package com.oudmon.ble.base.request;

import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import java.util.UUID;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/request/LocalWriteRequest.class */
public class LocalWriteRequest<T extends BaseRspCmd> extends WriteRequest {
    private ICommandResponse<T> iOpResponse;
    private int type;

    public LocalWriteRequest(UUID serviceUuid, UUID charUuid) {
        super(serviceUuid, charUuid);
    }

    public ICommandResponse<T> getiOpResponse() {
        return this.iOpResponse;
    }

    public void setiOpResponse(ICommandResponse<T> iOpResponse) {
        this.iOpResponse = iOpResponse;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
