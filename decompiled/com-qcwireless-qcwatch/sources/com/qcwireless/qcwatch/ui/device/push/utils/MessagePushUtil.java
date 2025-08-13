package com.qcwireless.qcwatch.ui.device.push.utils;

import androidx.core.app.NotificationCompat;
import com.bumptech.glide.load.Key;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.MessagePushBean;
import com.oudmon.ble.base.communication.req.PushMsgUintReq;
import com.oudmon.ble.base.communication.rsp.SimpleStatusRsp;
import com.oudmon.ble.base.util.ThreadUtils;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class MessagePushUtil {
    private static long lastSaveTime;
    private static ConcurrentHashMap<String, MessagePushBean> currMap = new ConcurrentHashMap<>(20);
    private static ConcurrentHashMap<String, MessagePushBean> currMapOthers = new ConcurrentHashMap<>(100);
    private static String callMsgPre = "110";
    private static long lastCallTime = 0;
    private static long lastInComing = 0;
    private static int inType = -1;
    private static String inMessage = "";
    private static ThreadUtils.TimeTask task = new ThreadUtils.TimeTask() { // from class: com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil.1
        @Override // com.oudmon.ble.base.util.ThreadUtils.TimeTask
        protected void task() throws UnsupportedEncodingException {
            byte[] bArr;
            if (MessagePushUtil.inMessage.length() > 128) {
                String unused = MessagePushUtil.inMessage = MessagePushUtil.inMessage.substring(0, 128);
            }
            try {
                byte[] bytes = MessagePushUtil.inMessage.getBytes(Key.STRING_CHARSET_NAME);
                int length = bytes.length / 11;
                if (length == 0) {
                    length = 1;
                } else if (bytes.length % 11 > 0) {
                    length++;
                }
                int i = 0;
                while (i < length) {
                    int i2 = i + 1;
                    if (i2 != length) {
                        int i3 = i * 11;
                        bArr = new byte[]{bytes[i3], bytes[i3 + 1], bytes[i3 + 2], bytes[i3 + 3], bytes[i3 + 4], bytes[i3 + 5], bytes[i3 + 6], bytes[i3 + 7], bytes[i3 + 8], bytes[i3 + 9], bytes[i3 + 10]};
                    } else {
                        int i4 = i * 11;
                        int length2 = bytes.length - i4;
                        byte[] bArr2 = new byte[11];
                        for (int i5 = 0; i5 < length2; i5++) {
                            bArr2[i5] = bytes[i4 + i5];
                        }
                        bArr = bArr2;
                    }
                    CommandHandle.getInstance().executeReqCmd(new PushMsgUintReq((byte) MessagePushUtil.inType, length, i2, bArr), new ICommandResponse<SimpleStatusRsp>() { // from class: com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil.1.1
                        @Override // com.oudmon.ble.base.communication.ICommandResponse
                        public void onDataResponse(SimpleStatusRsp resultEntity) {
                            if (resultEntity.getStatus() == 0) {
                                XLog.i("send msg success");
                            }
                        }
                    });
                    i = i2;
                }
            } catch (UnsupportedEncodingException unused2) {
            }
        }
    };
    private static String preMessage = "QwatchPro";
    private static String preMessageFromTitle = "QwatchPro";

    public static void pushCallMsg(final int type, String message) {
        XLog.i(type + message);
        if (System.currentTimeMillis() - lastCallTime >= TrackingService.Constant.FASTEST_UPDATE_INTERVAL || type == 4) {
            inType = type;
            inMessage = message;
            if (type == 0) {
                lastInComing = System.currentTimeMillis();
            }
            if (type == 4) {
                if (((int) (System.currentTimeMillis() - lastInComing)) <= 600) {
                    ThreadUtils.cancel(task);
                    return;
                } else {
                    ThreadUtils.postDelay(task, 10L);
                    return;
                }
            }
            lastCallTime = System.currentTimeMillis();
            ThreadUtils.postDelay(task, 600L);
        }
    }

    public static void pushMsg(final int type, String message) throws UnsupportedEncodingException {
        byte[] bArr;
        if (type != 0 && type != 1 && type != 4) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                MessagePushBean messagePushBean = currMapOthers.get(message + "");
                if (messagePushBean != null) {
                    String message2 = messagePushBean.getMessage();
                    if (jCurrentTimeMillis <= messagePushBean.getTime() && message2.equalsIgnoreCase(message) && !message2.contains(NotificationCompat.CATEGORY_CALL)) {
                        return;
                    }
                }
                currMapOthers.put(message, new MessagePushBean(message, System.currentTimeMillis() + 120000));
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (message != null) {
            try {
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (message.length() != 0) {
                if (" ".equalsIgnoreCase(message)) {
                    return;
                }
                XLog.i("pushMsg.. type: " + type + ", message: " + message);
                if (message.length() > 256) {
                    message = message.substring(0, 256);
                }
                try {
                    byte[] bytes = message.getBytes(Key.STRING_CHARSET_NAME);
                    int length = bytes.length / 11;
                    if (length == 0) {
                        length = 1;
                    } else if (bytes.length % 11 > 0) {
                        length++;
                    }
                    int i = 0;
                    while (i < length) {
                        int i2 = i + 1;
                        if (i2 != length) {
                            int i3 = i * 11;
                            bArr = new byte[]{bytes[i3], bytes[i3 + 1], bytes[i3 + 2], bytes[i3 + 3], bytes[i3 + 4], bytes[i3 + 5], bytes[i3 + 6], bytes[i3 + 7], bytes[i3 + 8], bytes[i3 + 9], bytes[i3 + 10]};
                        } else {
                            int i4 = i * 11;
                            int length2 = bytes.length - i4;
                            byte[] bArr2 = new byte[11];
                            for (int i5 = 0; i5 < length2; i5++) {
                                bArr2[i5] = bytes[i4 + i5];
                            }
                            bArr = bArr2;
                        }
                        CommandHandle.getInstance().executeReqCmd(new PushMsgUintReq((byte) type, length, i2, bArr), new ICommandResponse<SimpleStatusRsp>() { // from class: com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil.2
                            @Override // com.oudmon.ble.base.communication.ICommandResponse
                            public void onDataResponse(SimpleStatusRsp resultEntity) {
                                if (resultEntity.getStatus() == 0) {
                                    XLog.i("send msg success");
                                }
                            }
                        });
                        i = i2;
                    }
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public static synchronized void pushSmsMsg(final int type, String message) {
        byte[] bArr;
        int iEditDistance;
        int iEditDistance2;
        XLog.i(message);
        XLog.i(preMessage);
        XLog.i(preMessageFromTitle);
        try {
            iEditDistance = EditDistance(message, preMessage);
            iEditDistance2 = EditDistance(message, preMessageFromTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (iEditDistance <= 2 || iEditDistance2 <= 2) {
            XLog.i("消息一样");
            return;
        }
        preMessage = message;
        if (message.length() > 256) {
            message = message.substring(0, 256);
        }
        try {
            byte[] bytes = message.getBytes(Key.STRING_CHARSET_NAME);
            XLog.i("短信内容:" + message);
            int length = bytes.length / 11;
            if (length == 0) {
                length = 1;
            } else if (bytes.length % 11 > 0) {
                length++;
            }
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                if (i2 != length) {
                    int i3 = i * 11;
                    bArr = new byte[]{bytes[i3], bytes[i3 + 1], bytes[i3 + 2], bytes[i3 + 3], bytes[i3 + 4], bytes[i3 + 5], bytes[i3 + 6], bytes[i3 + 7], bytes[i3 + 8], bytes[i3 + 9], bytes[i3 + 10]};
                } else {
                    int i4 = i * 11;
                    int length2 = bytes.length - i4;
                    byte[] bArr2 = new byte[11];
                    for (int i5 = 0; i5 < length2; i5++) {
                        bArr2[i5] = bytes[i4 + i5];
                    }
                    bArr = bArr2;
                }
                CommandHandle.getInstance().executeReqCmd(new PushMsgUintReq((byte) type, length, i2, bArr), new ICommandResponse<SimpleStatusRsp>() { // from class: com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil.3
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public void onDataResponse(SimpleStatusRsp resultEntity) {
                        if (resultEntity.getStatus() == 0) {
                            XLog.i("send msg success");
                        }
                    }
                });
                i = i2;
            }
            return;
        } catch (UnsupportedEncodingException unused) {
            return;
        }
    }

    public static synchronized void pushSmsMsgFromTitle(final int type, String message) {
        byte[] bArr;
        int iEditDistance;
        int iEditDistance2;
        XLog.i(message);
        XLog.i(preMessageFromTitle);
        XLog.i(preMessage);
        try {
            iEditDistance = EditDistance(message, preMessageFromTitle);
            iEditDistance2 = EditDistance(message, preMessage);
            XLog.i(Integer.valueOf(iEditDistance));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (iEditDistance <= 2 || iEditDistance2 <= 2) {
            XLog.i("消息一样");
            return;
        }
        preMessageFromTitle = message;
        if (message.length() > 256) {
            message = message.substring(0, 256);
        }
        try {
            byte[] bytes = message.getBytes(Key.STRING_CHARSET_NAME);
            int length = bytes.length / 11;
            if (length == 0) {
                length = 1;
            } else if (bytes.length % 11 > 0) {
                length++;
            }
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                if (i2 != length) {
                    int i3 = i * 11;
                    bArr = new byte[]{bytes[i3], bytes[i3 + 1], bytes[i3 + 2], bytes[i3 + 3], bytes[i3 + 4], bytes[i3 + 5], bytes[i3 + 6], bytes[i3 + 7], bytes[i3 + 8], bytes[i3 + 9], bytes[i3 + 10]};
                } else {
                    int i4 = i * 11;
                    int length2 = bytes.length - i4;
                    byte[] bArr2 = new byte[11];
                    for (int i5 = 0; i5 < length2; i5++) {
                        bArr2[i5] = bytes[i4 + i5];
                    }
                    bArr = bArr2;
                }
                CommandHandle.getInstance().executeReqCmd(new PushMsgUintReq((byte) type, length, i2, bArr), new ICommandResponse<SimpleStatusRsp>() { // from class: com.qcwireless.qcwatch.ui.device.push.utils.MessagePushUtil.4
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public void onDataResponse(SimpleStatusRsp resultEntity) {
                        if (resultEntity.getStatus() == 0) {
                            XLog.i("send msg success");
                        }
                    }
                });
                i = i2;
            }
            return;
        } catch (UnsupportedEncodingException unused) {
            return;
        }
    }

    public static int EditDistance(String source, String target) {
        try {
            String strReplaceAll = source.replaceAll(" ", "");
            String strReplaceAll2 = target.replaceAll(" ", "");
            char[] charArray = strReplaceAll.toCharArray();
            char[] charArray2 = strReplaceAll2.toCharArray();
            int length = charArray.length;
            int length2 = charArray2.length;
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, length + 1, length2 + 1);
            for (int i = 0; i <= length; i++) {
                iArr[i][0] = i;
            }
            for (int i2 = 0; i2 <= length2; i2++) {
                iArr[0][i2] = i2;
            }
            for (int i3 = 1; i3 <= length; i3++) {
                for (int i4 = 1; i4 <= length2; i4++) {
                    int i5 = i3 - 1;
                    int i6 = i4 - 1;
                    if (charArray[i5] == charArray2[i6]) {
                        iArr[i3][i4] = iArr[i5][i6];
                    } else {
                        int i7 = iArr[i3][i6] + 1;
                        int i8 = iArr[i5][i4] + 1;
                        int i9 = iArr[i5][i6] + 1;
                        iArr[i3][i4] = Math.min(i7, i8) > Math.min(i8, i9) ? Math.min(i8, i9) : Math.min(i7, i8);
                    }
                }
            }
            return iArr[length][length2];
        } catch (Exception e) {
            e.printStackTrace();
            return 10;
        }
    }
}
