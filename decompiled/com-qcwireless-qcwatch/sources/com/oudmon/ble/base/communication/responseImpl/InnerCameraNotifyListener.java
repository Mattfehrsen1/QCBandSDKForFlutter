package com.oudmon.ble.base.communication.responseImpl;

import android.content.Context;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.CameraNotifyRsp;

/* loaded from: classes3.dex */
public class InnerCameraNotifyListener implements ICommandResponse<CameraNotifyRsp> {
    private Context mContext;
    private ICommandResponse<CameraNotifyRsp> outRspIOdmOpResponse;

    public InnerCameraNotifyListener(Context context) {
        this.mContext = context;
    }

    public ICommandResponse<CameraNotifyRsp> getOutRspIOdmOpResponse() {
        return this.outRspIOdmOpResponse;
    }

    public void setOutRspIOdmOpResponse(ICommandResponse<CameraNotifyRsp> iCommandResponse) {
        this.outRspIOdmOpResponse = iCommandResponse;
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(CameraNotifyRsp cameraNotifyRsp) {
        ICommandResponse<CameraNotifyRsp> iCommandResponse = this.outRspIOdmOpResponse;
        if (iCommandResponse != null) {
            iCommandResponse.onDataResponse(cameraNotifyRsp);
        } else if (cameraNotifyRsp.getStatus() == 0) {
            cameraNotifyRsp.getAction();
        }
    }
}
