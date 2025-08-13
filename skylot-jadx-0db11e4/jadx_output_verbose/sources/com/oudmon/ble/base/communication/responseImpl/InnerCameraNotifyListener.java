package com.oudmon.ble.base.communication.responseImpl;

import android.content.Context;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.CameraNotifyRsp;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/responseImpl/InnerCameraNotifyListener.class */
public class InnerCameraNotifyListener implements ICommandResponse<CameraNotifyRsp> {
    private Context mContext;
    private ICommandResponse<CameraNotifyRsp> outRspIOdmOpResponse;

    public InnerCameraNotifyListener(Context context) {
        this.mContext = context;
    }

    public ICommandResponse<CameraNotifyRsp> getOutRspIOdmOpResponse() {
        return this.outRspIOdmOpResponse;
    }

    public void setOutRspIOdmOpResponse(ICommandResponse<CameraNotifyRsp> outRspIOdmOpResponse) {
        this.outRspIOdmOpResponse = outRspIOdmOpResponse;
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(CameraNotifyRsp resultEntity) {
        if (this.outRspIOdmOpResponse != null) {
            this.outRspIOdmOpResponse.onDataResponse(resultEntity);
        } else {
            if (resultEntity.getStatus() != 0 || resultEntity.getAction() == 1) {
            }
        }
    }
}
