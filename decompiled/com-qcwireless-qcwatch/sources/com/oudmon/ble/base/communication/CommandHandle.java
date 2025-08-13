package com.oudmon.ble.base.communication;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.req.BaseReqCmd;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.request.LocalWriteRequest;
import com.oudmon.ble.base.request.ReadRequest;

/* loaded from: classes3.dex */
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

    private <T extends BaseRspCmd> LocalWriteRequest<T> getWriteRequest(byte[] bArr) {
        LocalWriteRequest<T> localWriteRequest = new LocalWriteRequest<>(Constants.UUID_SERVICE, Constants.UUID_WRITE);
        localWriteRequest.setValue(bArr);
        return localWriteRequest;
    }

    public ReadRequest getReadHwRequest() {
        return new ReadRequest(Constants.SERVICE_DEVICE_INFO, Constants.CHAR_HW_REVISION);
    }

    public ReadRequest getReadFmRequest() {
        return new ReadRequest(Constants.SERVICE_DEVICE_INFO, Constants.CHAR_FIRMWARE_REVISION);
    }

    public void executeReqCmd(BaseReqCmd baseReqCmd, ICommandResponse iCommandResponse) {
        if (!BleOperateManager.getInstance().isConnected()) {
            XLog.i("设备已经断开：" + ByteUtil.byteArrayToString(baseReqCmd.getData()));
            return;
        }
        LocalWriteRequest writeRequest = getWriteRequest(baseReqCmd.getData());
        int i = writeRequest.getValue()[0] & (~Constants.FLAG_MASK_ERROR);
        writeRequest.setiOpResponse(iCommandResponse);
        if (iCommandResponse != null) {
            BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().put(Integer.valueOf(i), writeRequest);
        }
        BleOperateManager.getInstance().execute(writeRequest);
    }

    public void removeExistsKey(int i) {
        BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().remove(Integer.valueOf(i));
    }

    public void executeReqCmdNoCallback(BaseReqCmd baseReqCmd) {
        if (!BleOperateManager.getInstance().isConnected()) {
            XLog.i("设备已经断开");
        } else {
            BleOperateManager.getInstance().execute(getWriteRequest(baseReqCmd.getData()));
        }
    }

    public void execReadCmd(ReadRequest readRequest) {
        BleOperateManager.getInstance().execute(readRequest);
    }
}
