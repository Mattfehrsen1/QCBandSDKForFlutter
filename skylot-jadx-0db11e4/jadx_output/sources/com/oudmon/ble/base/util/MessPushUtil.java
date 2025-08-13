package com.oudmon.ble.base.util;

import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.PushMsgUintReq;
import com.oudmon.ble.base.communication.rsp.SimpleStatusRsp;
import com.realsil.customer.bbpro.equalizer.EqConstants;
import java.io.UnsupportedEncodingException;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/MessPushUtil.class */
public class MessPushUtil {
    public static void pushMsg(int type, String message) throws UnsupportedEncodingException {
        byte[] msg;
        if (message == null || message.length() == 0) {
            return;
        }
        if (message.length() > 128) {
            message = message.substring(0, EqConstants.CodeIndex.BUILD_IN_EQ_4);
        }
        try {
            byte[] result = message.getBytes("UTF-8");
            int groupTotal = result.length / 11;
            if (groupTotal == 0) {
                groupTotal = 1;
            } else if (result.length % 11 > 0) {
                groupTotal++;
            }
            for (int i = 0; i < groupTotal; i++) {
                if (i + 1 != groupTotal) {
                    msg = new byte[]{result[i * 11], result[(i * 11) + 1], result[(i * 11) + 2], result[(i * 11) + 3], result[(i * 11) + 4], result[(i * 11) + 5], result[(i * 11) + 6], result[(i * 11) + 7], result[(i * 11) + 8], result[(i * 11) + 9], result[(i * 11) + 10]};
                } else {
                    int len = result.length - (i * 11);
                    msg = new byte[11];
                    for (int index = 0; index < len; index++) {
                        msg[index] = result[(i * 11) + index];
                    }
                }
                CommandHandle.getInstance().executeReqCmd(new PushMsgUintReq((byte) type, groupTotal, i + 1, msg), new ICommandResponse<SimpleStatusRsp>() { // from class: com.oudmon.ble.base.util.MessPushUtil.1
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public void onDataResponse(SimpleStatusRsp resultEntity) {
                        if (resultEntity.getStatus() == 0) {
                        }
                    }
                });
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
