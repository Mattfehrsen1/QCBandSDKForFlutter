package com.oudmon.ble.base.communication.responseImpl;

import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/responseImpl/DeviceSportNotifyListener.class */
public class DeviceSportNotifyListener implements ICommandResponse<DeviceNotifyRsp> {
    private ConcurrentHashMap<Integer, ICommandResponse<DeviceNotifyRsp>> respList = new ConcurrentHashMap<>();

    public void setOutRspIOdmOpResponse(int key, ICommandResponse<DeviceNotifyRsp> outRspIOdmOpResponse) {
        this.respList.put(Integer.valueOf(key), outRspIOdmOpResponse);
        if (this.respList.get(100) != null) {
            removeOtherCallbacks();
        }
    }

    public void removeCallback(int key) {
        this.respList.remove(Integer.valueOf(key));
    }

    public void removeOtherCallbacks() {
        this.respList.remove(78);
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(DeviceNotifyRsp resultEntity) {
        for (ICommandResponse<DeviceNotifyRsp> outRspIOdmOpResponse : this.respList.values()) {
            outRspIOdmOpResponse.onDataResponse(resultEntity);
        }
    }
}
