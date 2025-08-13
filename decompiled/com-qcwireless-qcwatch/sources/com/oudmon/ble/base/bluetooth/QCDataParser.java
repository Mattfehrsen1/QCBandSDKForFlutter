package com.oudmon.ble.base.bluetooth;

import android.util.SparseArray;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.request.LocalWriteRequest;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class QCDataParser {
    private static SparseArray<BaseRspCmd> tempRspDataSparseArray = new SparseArray<>();

    public static boolean checkCrc(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return bArr[bArr.length - 1] == ((byte) (i & 255));
    }

    public static boolean parserAndDispatchReqData(byte[] bArr) {
        ICommandResponse iCommandResponse;
        int i = bArr[0] & (~Constants.FLAG_MASK_ERROR);
        byte b = bArr[0];
        int i2 = Constants.FLAG_MASK_ERROR;
        LocalWriteRequest localWriteRequest = BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().get(Integer.valueOf(i));
        if (localWriteRequest != null && (iCommandResponse = localWriteRequest.getiOpResponse()) != null) {
            BaseRspCmd baseRspCmdCreateBean = tempRspDataSparseArray.get(i);
            if (baseRspCmdCreateBean == null) {
                try {
                    baseRspCmdCreateBean = BeanFactory.createBean(i, localWriteRequest.getType());
                } catch (Exception e) {
                    e.printStackTrace();
                    BleOperateManager.getInstance().getLocalWriteRequestConcurrentHashMap().clear();
                }
            }
            if (baseRspCmdCreateBean != null) {
                if (baseRspCmdCreateBean.acceptData(Arrays.copyOfRange(bArr, 1, bArr.length - 1))) {
                    tempRspDataSparseArray.put(i, baseRspCmdCreateBean);
                    return true;
                }
                iCommandResponse.onDataResponse(baseRspCmdCreateBean);
                tempRspDataSparseArray.delete(i);
                return true;
            }
        }
        return false;
    }

    public static boolean parserAndDispatchNotifyData(SparseArray<ICommandResponse> sparseArray, byte[] bArr) {
        int i = bArr[0] & (~Constants.FLAG_MASK_ERROR);
        byte b = bArr[0];
        int i2 = Constants.FLAG_MASK_ERROR;
        ICommandResponse iCommandResponse = sparseArray.get(i);
        XLog.i("notifyKey: " + DataTransferUtils.getHexString(DataTransferUtils.intToBytes(i)));
        if (iCommandResponse != null) {
            BaseRspCmd baseRspCmdCreateBean = tempRspDataSparseArray.get(i);
            if (baseRspCmdCreateBean == null) {
                baseRspCmdCreateBean = BeanFactory.createBean(i, 0);
            }
            if (baseRspCmdCreateBean != null) {
                if (baseRspCmdCreateBean.acceptData(Arrays.copyOfRange(bArr, 1, bArr.length - 1))) {
                    tempRspDataSparseArray.put(i, baseRspCmdCreateBean);
                    return true;
                }
                iCommandResponse.onDataResponse(baseRspCmdCreateBean);
                tempRspDataSparseArray.delete(i);
                return true;
            }
        }
        return false;
    }
}
