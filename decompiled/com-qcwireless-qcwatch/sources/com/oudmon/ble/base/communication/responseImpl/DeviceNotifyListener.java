package com.oudmon.ble.base.communication.responseImpl;

import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.DeviceNotifyRsp;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class DeviceNotifyListener implements ICommandResponse<DeviceNotifyRsp> {
    private ConcurrentHashMap<Integer, ICommandResponse<DeviceNotifyRsp>> respList = new ConcurrentHashMap<>();

    public void setOutRspIOdmOpResponse(int i, ICommandResponse<DeviceNotifyRsp> iCommandResponse) {
        this.respList.put(Integer.valueOf(i), iCommandResponse);
        if (this.respList.get(100) != null) {
            removeOtherCallbacks();
        }
    }

    public void removeCallback(int i) {
        this.respList.remove(Integer.valueOf(i));
    }

    public void removeOtherCallbacks() {
        this.respList.remove(1);
        this.respList.remove(2);
        this.respList.remove(3);
        this.respList.remove(5);
        this.respList.remove(7);
        this.respList.remove(9);
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(DeviceNotifyRsp deviceNotifyRsp) {
        Iterator<ICommandResponse<DeviceNotifyRsp>> it = this.respList.values().iterator();
        while (it.hasNext()) {
            it.next().onDataResponse(deviceNotifyRsp);
        }
    }
}
