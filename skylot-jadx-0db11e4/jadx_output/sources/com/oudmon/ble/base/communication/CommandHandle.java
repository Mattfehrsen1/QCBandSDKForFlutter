package com.oudmon.ble.base.communication;

import android.util.Log;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.req.BaseReqCmd;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.request.LocalWriteRequest;
import com.oudmon.ble.base.request.ReadRequest;
import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/CommandHandle.class */
public class CommandHandle {
    private static final String TAG = "CommandHandle";
    private static CommandHandle odmHandle;

    public static CommandHandle getInstance() {
        if (odmHandle == null) {
            synchronized (CommandHandle.class) {
                if (odmHandle == null) {
                    odmHandle = new CommandHandle();
                }
            }
        }
        return odmHandle;
    }

    private CommandHandle() {
    }

    private <T extends BaseRspCmd> LocalWriteRequest<T> getWriteRequest(byte[] data) {
        LocalWriteRequest<T> writeRequest = new LocalWriteRequest<>(Constants.UUID_SERVICE, Constants.UUID_WRITE);
        Log.i(Constants.TAG, "app->device：" + DataTransferUtils.getHexString(data));
        writeRequest.setValue(data);
        return writeRequest;
    }

    public ReadRequest getReadHwRequest() {
        return new ReadRequest(Constants.SERVICE_DEVICE_INFO, Constants.CHAR_HW_REVISION);
    }

    public ReadRequest getReadFmRequest() {
        return new ReadRequest(Constants.SERVICE_DEVICE_INFO, Constants.CHAR_FIRMWARE_REVISION);
    }

    public void executeReqCmd(BaseReqCmd reqCmd, ICommandResponse iOpResponse) {
        if (!BleOperateManager.getInstance().isConnected()) {
            return;
        }
        LocalWriteRequest localWriteRequest = getWriteRequest(reqCmd.getData());
        int notifyKey = localWriteRequest.getValue()[0] & (Constants.FLAG_MASK_ERROR ^ (-1));
        localWriteRequest.setiOpResponse(iOpResponse);
        if (iOpResponse != null) {
            BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().put(Integer.valueOf(notifyKey), localWriteRequest);
        }
        BleOperateManager.getInstance().execute(localWriteRequest);
    }

    public void executeReqCmdNoCallback(BaseReqCmd reqCmd) {
        if (!BleOperateManager.getInstance().isConnected()) {
            return;
        }
        LocalWriteRequest localWriteRequest = getWriteRequest(reqCmd.getData());
        BleOperateManager.getInstance().execute(localWriteRequest);
    }

    public void execReadCmd(ReadRequest reqCmd) {
        BleOperateManager.getInstance().execute(reqCmd);
    }
}
