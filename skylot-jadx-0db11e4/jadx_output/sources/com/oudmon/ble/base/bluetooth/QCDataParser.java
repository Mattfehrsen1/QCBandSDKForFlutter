package com.oudmon.ble.base.bluetooth;

import android.util.SparseArray;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.request.LocalWriteRequest;
import java.util.Arrays;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/bluetooth/QCDataParser.class */
public class QCDataParser {
    private static SparseArray<BaseRspCmd> tempRspDataSparseArray = new SparseArray<>();

    public static boolean checkCrc(byte[] data) {
        int crc = 0;
        for (int i = 0; i < data.length - 1; i++) {
            crc += data[i];
        }
        return data[data.length - 1] == ((byte) (crc & 255));
    }

    public static boolean parserAndDispatchReqData(byte[] data) {
        ICommandResponse iOpResponse;
        int notifyKey = data[0] & (Constants.FLAG_MASK_ERROR ^ (-1));
        int i = data[0] & Constants.FLAG_MASK_ERROR;
        LocalWriteRequest localWriteRequest = BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().get(Integer.valueOf(notifyKey));
        if (localWriteRequest != null && (iOpResponse = localWriteRequest.getiOpResponse()) != null) {
            BaseRspCmd tempBaseRspCmd = tempRspDataSparseArray.get(notifyKey);
            if (tempBaseRspCmd == null) {
                try {
                    tempBaseRspCmd = BeanFactory.createBean(notifyKey, localWriteRequest.getType());
                } catch (Exception e) {
                    e.printStackTrace();
                    BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().clear();
                }
            }
            if (tempBaseRspCmd != null) {
                boolean needNext = tempBaseRspCmd.acceptData(Arrays.copyOfRange(data, 1, data.length - 1));
                if (needNext) {
                    tempRspDataSparseArray.put(notifyKey, tempBaseRspCmd);
                    return true;
                }
                iOpResponse.onDataResponse(tempBaseRspCmd);
                tempRspDataSparseArray.delete(notifyKey);
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean parserAndDispatchNotifyData(SparseArray<ICommandResponse> sparseArray, byte[] data) {
        int notifyKey = data[0] & (Constants.FLAG_MASK_ERROR ^ (-1));
        int i = data[0] & Constants.FLAG_MASK_ERROR;
        ICommandResponse iOpResponse = sparseArray.get(notifyKey);
        if (iOpResponse != null) {
            BaseRspCmd tempBaseRspCmd = tempRspDataSparseArray.get(notifyKey);
            if (tempBaseRspCmd == null) {
                tempBaseRspCmd = BeanFactory.createBean(notifyKey, 0);
            }
            if (tempBaseRspCmd != null) {
                boolean needNext = tempBaseRspCmd.acceptData(Arrays.copyOfRange(data, 1, data.length - 1));
                if (needNext) {
                    tempRspDataSparseArray.put(notifyKey, tempBaseRspCmd);
                    return true;
                }
                iOpResponse.onDataResponse(tempBaseRspCmd);
                tempRspDataSparseArray.delete(notifyKey);
                return true;
            }
            return false;
        }
        return false;
    }
}
