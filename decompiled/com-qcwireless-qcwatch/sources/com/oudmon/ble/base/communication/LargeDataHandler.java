package com.oudmon.ble.base.communication;

import com.bumptech.glide.load.Key;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.bigData.AlarmNewEntity;
import com.oudmon.ble.base.communication.bigData.CustomWatchFaceEntity;
import com.oudmon.ble.base.communication.bigData.bean.BaseBean;
import com.oudmon.ble.base.communication.bigData.bean.ClassicBluetooth;
import com.oudmon.ble.base.communication.bigData.bean.ContactBean;
import com.oudmon.ble.base.communication.bigData.bean.ECardEntity;
import com.oudmon.ble.base.communication.bigData.bean.ManualHeartRate;
import com.oudmon.ble.base.communication.bigData.bean.SmsQuickBean;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataBaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataClassicBluetoothResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataQrCodeResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataSmsQuickResponse;
import com.oudmon.ble.base.communication.rsp.SleepNewProtoResp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class LargeDataHandler {
    public static final byte ACTION_AVATAR_Device = 74;
    public static final byte ACTION_Alarm = 44;
    public static final byte ACTION_BT_MAC_Protocol = 46;
    public static final byte ACTION_Blood_Oxygen = 42;
    public static final byte ACTION_Blood_Sugar = 71;
    public static final byte ACTION_Contact = 45;
    public static final byte ACTION_Contacts_New = 41;
    public static final byte ACTION_Custom_WatchFace = 58;
    public static final byte ACTION_E_CARD_Protocol = 47;
    public static final byte ACTION_GPS_Navigation = 72;
    public static final byte ACTION_Location_Protocol = 32;
    public static final byte ACTION_ManualHeartRate_Protocol = 40;
    public static final byte ACTION_New_Sleep_Protocol = 39;
    public static final byte ACTION_New_Sleep_Protocol_Lunch = 62;
    public static final byte ACTION_SMS_QUICK = 76;
    private static LargeDataHandler mInstance;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY, new EnableNotifyRequest.ListenerCallback() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.1
        @Override // com.oudmon.ble.base.request.EnableNotifyRequest.ListenerCallback
        public void enable(boolean z) {
            if (z) {
                return;
            }
            XLog.i("enable:" + z);
            LargeDataHandler.this.initEnable();
        }
    });
    private ConcurrentHashMap<Integer, ILargeDataResponse> respMap = new ConcurrentHashMap<>();
    private int mPackageLength = JPackageManager.getInstance().getLength();

    private LargeDataHandler() {
    }

    public static LargeDataHandler getInstance() {
        if (mInstance == null) {
            synchronized (LargeDataHandler.class) {
                if (mInstance == null) {
                    mInstance = new LargeDataHandler();
                }
            }
        }
        return mInstance;
    }

    public void initEnable() {
        this.enableNotifyRequest.setEnable(true);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
    }

    public void disEnable() {
        this.enableNotifyRequest.setEnable(false);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
    }

    public void syncBloodOxygen(int i, ILargeDataResponse iLargeDataResponse) throws InterruptedException {
        this.respMap.put(42, iLargeDataResponse);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(42, new byte[]{(byte) i}), JPackageManager.getInstance().getLength()));
    }

    public void syncBloodSugar(int i, ILargeDataResponse iLargeDataResponse) throws InterruptedException {
        this.respMap.put(71, iLargeDataResponse);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(71, new byte[]{(byte) i}), JPackageManager.getInstance().getLength()));
    }

    public void readCustomWatch(ILargeDataResponse iLargeDataResponse) throws InterruptedException {
        this.respMap.put(58, iLargeDataResponse);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(58, new byte[]{1}), JPackageManager.getInstance().getLength()));
    }

    public void syncContact(byte[] bArr) throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(45, bArr), JPackageManager.getInstance().getLength()));
    }

    public void setDeviceNickName(String str) throws InterruptedException {
        if (str.isEmpty()) {
            BleThreadManager.getInstance().addData(new BleDataBean(addHeader(74, new byte[]{1, 1, 0}), JPackageManager.getInstance().getLength()));
            return;
        }
        byte[] bytes = str.getBytes(Charset.forName("unicode"));
        if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
            int i = 0;
            while (i < bytes.length) {
                int i2 = i + 2;
                int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i, i2));
                bytes[i] = (byte) ByteUtil.hiword(iBytesToInt);
                bytes[i + 1] = (byte) ByteUtil.loword(iBytesToInt);
                i = i2;
            }
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(74, ByteUtil.concat(new byte[]{1, 1, 0}, bytes)), JPackageManager.getInstance().getLength()));
    }

    public void gpsNavigationRunning(int i, int i2, String str) throws InterruptedException {
        byte[] bytes = str.getBytes(Charset.forName("unicode"));
        if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
            int i3 = 0;
            while (i3 < bytes.length) {
                int i4 = i3 + 2;
                int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i3, i4));
                bytes[i3] = (byte) ByteUtil.hiword(iBytesToInt);
                bytes[i3 + 1] = (byte) ByteUtil.loword(iBytesToInt);
                i3 = i4;
            }
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(72, ByteUtil.concat(new byte[]{1, (byte) (bytes.length + 2), (byte) i, (byte) i2}, bytes)), JPackageManager.getInstance().getLength()));
    }

    public void gpsNavigationStatus(int i) throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(72, new byte[]{(byte) i, 0}), JPackageManager.getInstance().getLength()));
    }

    public void writeSmsQuick(List<SmsQuickBean> list) throws InterruptedException {
        byte[] bArrConcat = {2};
        byte[] bArr = new byte[2];
        for (int i = 0; i < list.size(); i++) {
            bArr[0] = (byte) i;
            byte[] bytes = list.get(i).getText().getBytes(Charset.forName("unicode"));
            if (!ByteUtil.byteArrayToString(bytes).startsWith("fffe")) {
                int i2 = 0;
                while (i2 < bytes.length) {
                    int i3 = i2 + 2;
                    int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bytes, i2, i3));
                    bytes[i2] = (byte) ByteUtil.hiword(iBytesToInt);
                    bytes[i2 + 1] = (byte) ByteUtil.loword(iBytesToInt);
                    i2 = i3;
                }
            }
            bArr[1] = (byte) bytes.length;
            bArrConcat = ByteUtil.concat(bArrConcat, ByteUtil.concat(bArr, bytes));
        }
        XLog.i(ByteUtil.byteArrayToString(bArrConcat));
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(76, bArrConcat), this.mPackageLength));
    }

    public void readSmsQuick(final ILargeDataSmsQuickResponse iLargeDataSmsQuickResponse) throws InterruptedException {
        this.respMap.put(76, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.2
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i, byte[] bArr) {
                if (i != 76 || bArr.length <= 10) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                XLog.i(ByteUtil.byteArrayToString(bArr));
                LargeDataHandler.this.parseSmsQuick(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8)), bArr, 7, ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) + 2, iLargeDataSmsQuickResponse, arrayList);
            }
        });
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(76, new byte[]{1}), this.mPackageLength));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseSmsQuick(int i, byte[] bArr, int i2, int i3, ILargeDataSmsQuickResponse iLargeDataSmsQuickResponse, List<SmsQuickBean> list) {
        SmsQuickBean smsQuickBean = new SmsQuickBean();
        smsQuickBean.setIndex(i);
        int i4 = i2 + i3;
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i2, i4);
        smsQuickBean.setText(unicodeByteToStr(Arrays.copyOfRange(bArrCopyOfRange, 2, bArrCopyOfRange.length)));
        list.add(smsQuickBean);
        int i5 = i + 1;
        if (i5 < 4) {
            parseSmsQuick(i5, bArr, i4, bArr[i4 + 1] + 2, iLargeDataSmsQuickResponse, list);
        } else {
            iLargeDataSmsQuickResponse.smsQuick(list);
        }
    }

    public String unicodeByteToStr(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 2;
            sb.append((char) ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, i, i2)));
            i = i2;
        }
        return sb.toString();
    }

    public void syncContactMore(List<ContactBean> list, final ILargeDataBaseResponse iLargeDataBaseResponse) throws InterruptedException {
        int size = list.size();
        byte[] bArrConcat = new byte[0];
        final LinkedList linkedList = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            ContactBean contactBean = list.get(i);
            String contactName = contactBean.getContactName();
            if (contactName.getBytes().length > 32) {
                contactName = getWholeText(contactName, 32);
            }
            byte[] bytes = contactName.getBytes();
            byte[] bArrConcat2 = ByteUtil.concat(bArrConcat, ByteUtil.concat(ByteUtil.intToByte((byte) bytes.length, 1), bytes));
            String phoneNumber = contactBean.getPhoneNumber();
            if (phoneNumber.getBytes().length > 18) {
                phoneNumber = getWholeText(phoneNumber, 18);
            }
            byte[] bytes2 = phoneNumber.getBytes();
            bArrConcat = ByteUtil.concat(bArrConcat2, ByteUtil.concat(ByteUtil.intToByte(bytes2.length, 1), bytes2));
            if (bArrConcat.length > 950) {
                linkedList.add(bArrConcat);
                bArrConcat = new byte[0];
            }
        }
        linkedList.add(bArrConcat);
        final int size2 = linkedList.size();
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(41, new byte[]{(byte) (size2 + 1), 0, (byte) ByteUtil.loword(size), (byte) ByteUtil.hiword(size)}), JPackageManager.getInstance().getLength()));
        final int[] iArr = {0};
        this.respMap.put(41, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.3
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i2, byte[] bArr) throws InterruptedException {
                if (i2 == 41 && ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)) == 0) {
                    if (linkedList.size() == 0) {
                        XLog.i("下发完了联系人");
                        iLargeDataBaseResponse.resp(new BaseBean(0));
                        return;
                    }
                    int[] iArr2 = iArr;
                    iArr2[0] = iArr2[0] + 1;
                    byte[] bArrAddHeader = LargeDataHandler.this.addHeader(41, ByteUtil.concat(new byte[]{(byte) (size2 + 1), (byte) iArr2[0]}, (byte[]) linkedList.removeFirst()));
                    for (int length = 0; length < bArrAddHeader.length; length += JPackageManager.getInstance().getLength()) {
                        int iMin = Math.min(JPackageManager.getInstance().getLength(), bArrAddHeader.length - length);
                        byte[] bArr2 = new byte[iMin];
                        System.arraycopy(bArrAddHeader, length, bArr2, 0, iMin);
                        BleThreadManager.getInstance().addData(new BleDataBean(bArr2, JPackageManager.getInstance().getLength()));
                    }
                }
            }
        });
    }

    public void packageLength() {
        this.mPackageLength = JPackageManager.getInstance().getLength();
    }

    public void syncSleepList(int i, final ILargeDataSleepResponse iLargeDataSleepResponse, final ILargeDataSleepResponse iLargeDataSleepResponse2) throws InterruptedException {
        this.respMap.put(39, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.4
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i2, byte[] bArr) {
                if (i2 == 39) {
                    if (ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)) > 0) {
                        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4));
                        LargeDataHandler.this.parseDaySleep(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8)), bArr, 7, ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) + 2, iBytesToInt, iLargeDataSleepResponse);
                        return;
                    }
                    ILargeDataSleepResponse iLargeDataSleepResponse3 = iLargeDataSleepResponse;
                    if (iLargeDataSleepResponse3 != null) {
                        iLargeDataSleepResponse3.sleepData(null);
                    }
                }
            }
        });
        this.respMap.put(62, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.5
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i2, byte[] bArr) {
                XLog.i(ByteUtil.byteArrayToString(bArr));
                if (i2 == 62) {
                    if (ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)) > 0) {
                        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 2, 4));
                        LargeDataHandler.this.parseDaySleepLunch(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8)), bArr, 7, ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) + 2, iBytesToInt, iLargeDataSleepResponse2);
                        return;
                    }
                    ILargeDataSleepResponse iLargeDataSleepResponse3 = iLargeDataSleepResponse2;
                    if (iLargeDataSleepResponse3 != null) {
                        iLargeDataSleepResponse3.sleepData(null);
                    }
                }
            }
        });
        byte[] bArr = new byte[2];
        if (i == 0) {
            bArr[0] = 0;
        } else {
            bArr[0] = -1;
        }
        bArr[1] = 1;
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(39, bArr), this.mPackageLength));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDaySleepLunch(int i, byte[] bArr, int i2, int i3, int i4, ILargeDataSleepResponse iLargeDataSleepResponse) {
        try {
            SleepNewProtoResp sleepNewProtoResp = new SleepNewProtoResp();
            sleepNewProtoResp.setLunchBreak(true);
            DateUtil dateUtil = new DateUtil();
            dateUtil.addDay(-i);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i2, i2 + i3);
            int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArrCopyOfRange, 2, 4));
            sleepNewProtoResp.setLunchEt((int) (dateUtil.getZeroTime() + (ByteUtil.bytesToInt(Arrays.copyOfRange(bArrCopyOfRange, 4, 6)) * 60)));
            sleepNewProtoResp.setLunchSt((int) (dateUtil.getZeroTime() + (iBytesToInt * 60)));
            ArrayList arrayList = new ArrayList();
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCopyOfRange, 6, bArrCopyOfRange.length);
            for (int i5 = 0; i5 < bArrCopyOfRange2.length; i5++) {
                if (i5 % 2 == 1) {
                    SleepNewProtoResp.DetailBean detailBean = new SleepNewProtoResp.DetailBean();
                    detailBean.setT(bArrCopyOfRange2[i5 - 1]);
                    if (bArrCopyOfRange2[i5] < 0) {
                        detailBean.setD(ByteUtil.byteToInt(bArrCopyOfRange2[i5]));
                    } else {
                        detailBean.setD(bArrCopyOfRange2[i5]);
                    }
                    arrayList.add(detailBean);
                }
            }
            sleepNewProtoResp.setList(arrayList);
            if (iLargeDataSleepResponse != null) {
                iLargeDataSleepResponse.sleepData(sleepNewProtoResp);
            }
            if (bArrCopyOfRange.length + i2 < i4) {
                try {
                    parseDaySleepLunch(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, bArrCopyOfRange.length + i2, bArrCopyOfRange.length + i2 + 1)), bArr, i2 + bArrCopyOfRange.length, 2 + ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, bArrCopyOfRange.length + i2 + 1, bArrCopyOfRange.length + i2 + 2)), i4, iLargeDataSleepResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDaySleep(int i, byte[] bArr, int i2, int i3, int i4, ILargeDataSleepResponse iLargeDataSleepResponse) {
        try {
            SleepNewProtoResp sleepNewProtoResp = new SleepNewProtoResp();
            DateUtil dateUtil = new DateUtil();
            dateUtil.addDay(-i);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i2, i2 + i3);
            XLog.i(ByteUtil.byteArrayToString(bArrCopyOfRange));
            int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArrCopyOfRange, 2, 4));
            sleepNewProtoResp.setEt((int) (dateUtil.getZeroTime() + (ByteUtil.bytesToInt(Arrays.copyOfRange(bArrCopyOfRange, 4, 6)) * 60)));
            if (iBytesToInt >= 1080) {
                dateUtil.addDay(-1);
            }
            sleepNewProtoResp.setSt((int) (dateUtil.getZeroTime() + (iBytesToInt * 60)));
            ArrayList arrayList = new ArrayList();
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCopyOfRange, 6, bArrCopyOfRange.length);
            for (int i5 = 0; i5 < bArrCopyOfRange2.length; i5++) {
                if (i5 % 2 == 1) {
                    SleepNewProtoResp.DetailBean detailBean = new SleepNewProtoResp.DetailBean();
                    detailBean.setT(bArrCopyOfRange2[i5 - 1]);
                    if (bArrCopyOfRange2[i5] < 0) {
                        detailBean.setD(ByteUtil.byteToInt(bArrCopyOfRange2[i5]));
                    } else {
                        detailBean.setD(bArrCopyOfRange2[i5]);
                    }
                    arrayList.add(detailBean);
                }
            }
            sleepNewProtoResp.setList(arrayList);
            if (iLargeDataSleepResponse != null) {
                iLargeDataSleepResponse.sleepData(sleepNewProtoResp);
            }
            if (bArrCopyOfRange.length + i2 < i4) {
                try {
                    parseDaySleep(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, bArrCopyOfRange.length + i2, bArrCopyOfRange.length + i2 + 1)), bArr, i2 + bArrCopyOfRange.length, ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, bArrCopyOfRange.length + i2 + 1, bArrCopyOfRange.length + i2 + 2)) + 2, i4, iLargeDataSleepResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void syncManualHeartRateList(int i, final ILargeDataManualHeartRateResponse iLargeDataManualHeartRateResponse) throws InterruptedException {
        this.respMap.put(40, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.6
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i2, byte[] bArr) {
                if (i2 == 40) {
                    ManualHeartRate manualHeartRate = new ManualHeartRate();
                    manualHeartRate.setIndex(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)));
                    ArrayList arrayList = new ArrayList();
                    byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 7, bArr.length);
                    for (int i3 = 0; i3 < bArrCopyOfRange.length / 3; i3++) {
                        ManualHeartRate.DetailBean detailBean = new ManualHeartRate.DetailBean();
                        int i4 = i3 * 3;
                        int i5 = i4 + 2;
                        detailBean.setM(ByteUtil.bytesToInt(Arrays.copyOfRange(bArrCopyOfRange, i4, i5)));
                        detailBean.setV(ByteUtil.bytesToInt(Arrays.copyOfRange(bArrCopyOfRange, i5, i4 + 3)));
                        arrayList.add(detailBean);
                    }
                    manualHeartRate.setData(arrayList);
                    ILargeDataManualHeartRateResponse iLargeDataManualHeartRateResponse2 = iLargeDataManualHeartRateResponse;
                    if (iLargeDataManualHeartRateResponse2 != null) {
                        iLargeDataManualHeartRateResponse2.manualHeart(manualHeartRate);
                    }
                }
            }
        });
        byte[] bArr = new byte[1];
        if (i != 0) {
            bArr[0] = -1;
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(40, bArr), this.mPackageLength));
    }

    public void syncClassicBluetooth(final ILargeDataClassicBluetoothResponse iLargeDataClassicBluetoothResponse) throws InterruptedException {
        this.respMap.put(46, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.7
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i, byte[] bArr) {
                if (i == 46) {
                    ClassicBluetooth classicBluetooth = new ClassicBluetooth();
                    classicBluetooth.setDeviceMac(LargeDataHandler.this.bytesToMac(Arrays.copyOfRange(bArr, 6, 12)));
                    classicBluetooth.setDeviceName(new String(Arrays.copyOfRange(bArr, 13, bArr[12] + 13), StandardCharsets.UTF_8));
                    ILargeDataClassicBluetoothResponse iLargeDataClassicBluetoothResponse2 = iLargeDataClassicBluetoothResponse;
                    if (iLargeDataClassicBluetoothResponse2 != null) {
                        iLargeDataClassicBluetoothResponse2.classicBluetooth(classicBluetooth);
                    }
                }
            }
        });
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(46, new byte[1]), this.mPackageLength));
    }

    public void writeCustomWatch(CustomWatchFaceEntity customWatchFaceEntity) throws InterruptedException {
        byte[] bArrConcat = {2};
        for (int i = 0; i < customWatchFaceEntity.getData().size(); i++) {
            CustomWatchFaceEntity.CustomElement customElement = customWatchFaceEntity.getData().get(i);
            bArrConcat = ByteUtil.concat(bArrConcat, new byte[]{(byte) customElement.getType(), (byte) ByteUtil.loword(customElement.getX()), (byte) ByteUtil.hiword(customElement.getX()), (byte) ByteUtil.loword(customElement.getY()), (byte) ByteUtil.hiword(customElement.getY()), (byte) customElement.getR(), (byte) customElement.getG(), (byte) customElement.getB()});
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(58, bArrConcat), this.mPackageLength));
    }

    public void writeAlarm(AlarmNewEntity alarmNewEntity) throws InterruptedException {
        try {
            byte[] bArrConcat = {2, (byte) alarmNewEntity.getTotal()};
            for (AlarmNewEntity.AlarmBean alarmBean : alarmNewEntity.getData()) {
                bArrConcat = ByteUtil.concat(bArrConcat, ByteUtil.concat(new byte[]{(byte) alarmBean.getAlarmLength(), (byte) alarmBean.getRepeatAndEnable(), (byte) ByteUtil.loword(alarmBean.getMin()), (byte) ByteUtil.hiword(alarmBean.getMin())}, alarmBean.getContent().getBytes(Key.STRING_CHARSET_NAME)));
            }
            BleThreadManager.getInstance().addData(new BleDataBean(addHeader(44, bArrConcat), this.mPackageLength));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void readAlarm(ILargeDataResponse iLargeDataResponse) throws InterruptedException {
        this.respMap.put(44, iLargeDataResponse);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(44, new byte[]{1}), this.mPackageLength));
    }

    public void writeQrCode(ECardEntity eCardEntity) throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(47, ByteUtil.concat(new byte[]{2, (byte) eCardEntity.getType(), (byte) eCardEntity.getUrl().getBytes(StandardCharsets.UTF_8).length}, eCardEntity.getUrl().getBytes(StandardCharsets.UTF_8))), this.mPackageLength));
    }

    public void writeQrCodeWithType() throws InterruptedException {
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(47, ByteUtil.concat(new byte[]{2, -1, 0}, "".getBytes(StandardCharsets.UTF_8))), this.mPackageLength));
    }

    public void deviceRequestLocation(final ILargeDataResponse iLargeDataResponse) {
        this.respMap.put(32, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.8
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i, byte[] bArr) {
                ILargeDataResponse iLargeDataResponse2;
                if (i == 32 && ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7)) == 1 && (iLargeDataResponse2 = iLargeDataResponse) != null) {
                    iLargeDataResponse2.parseData(32, bArr);
                }
            }
        });
    }

    public void writeLocation(double d, double d2, String str) {
        int i = (int) d2;
        double d3 = (d2 - i) * 60.0d;
        int i2 = (int) d3;
        int i3 = (int) d;
        double d4 = (d - i3) * 60.0d;
        int i4 = (int) d4;
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(32, ByteUtil.concat(new byte[]{2, (byte) ByteUtil.loword(i), (byte) ByteUtil.hiword(i), (byte) Math.abs(i2), (byte) ((int) ((float) Math.abs((d3 - i2) * 60.0d))), (byte) (((r12 - r13) * 10000.0f) / 100.0f), (byte) ByteUtil.loword(i3), (byte) ByteUtil.hiword(i3), (byte) Math.abs(i4), (byte) ((int) ((float) Math.abs((d4 - i4) * 60.0d))), (byte) (((r10 - r11) * 10000.0f) / 100.0f)}, str.getBytes(StandardCharsets.UTF_8))), this.mPackageLength));
    }

    public void readQrCode(final int i, final ILargeDataQrCodeResponse iLargeDataQrCodeResponse) throws InterruptedException {
        this.respMap.put(47, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.9
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int i2, byte[] bArr) {
                if (i2 == 47) {
                    try {
                        XLog.i(ByteUtil.byteArrayToString(bArr));
                        int iBytesToInt = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 6, 7));
                        if (iBytesToInt == 1) {
                            ECardEntity eCardEntity = new ECardEntity();
                            eCardEntity.setType(i);
                            eCardEntity.setDeviceError(2);
                            eCardEntity.setReadOrWrite(iBytesToInt);
                            eCardEntity.setUrl(new String(Arrays.copyOfRange(bArr, 9, bArr[8] + 9), StandardCharsets.UTF_8));
                            ILargeDataQrCodeResponse iLargeDataQrCodeResponse2 = iLargeDataQrCodeResponse;
                            if (iLargeDataQrCodeResponse2 != null) {
                                iLargeDataQrCodeResponse2.qrCode(eCardEntity);
                            }
                        } else if (iBytesToInt == 2) {
                            int iBytesToInt2 = ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 7, 8));
                            if (iBytesToInt2 == 255) {
                                if (iLargeDataQrCodeResponse != null) {
                                    ECardEntity eCardEntity2 = new ECardEntity();
                                    eCardEntity2.setReadOrWrite(iBytesToInt);
                                    if (ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) == 0) {
                                        eCardEntity2.setType(255);
                                        eCardEntity2.setSupport(true);
                                        eCardEntity2.setDeviceError(2);
                                        eCardEntity2.setReadOrWrite(iBytesToInt);
                                        iLargeDataQrCodeResponse.qrCode(eCardEntity2);
                                    } else {
                                        eCardEntity2.setType(255);
                                        eCardEntity2.setSupport(false);
                                        eCardEntity2.setDeviceError(2);
                                        eCardEntity2.setReadOrWrite(iBytesToInt);
                                        iLargeDataQrCodeResponse.qrCode(eCardEntity2);
                                    }
                                }
                            } else if (ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 9)) == 1) {
                                if (iLargeDataQrCodeResponse != null) {
                                    ECardEntity eCardEntity3 = new ECardEntity();
                                    eCardEntity3.setType(iBytesToInt2);
                                    eCardEntity3.setDeviceError(1);
                                    eCardEntity3.setReadOrWrite(iBytesToInt);
                                    iLargeDataQrCodeResponse.qrCode(eCardEntity3);
                                }
                            } else if (iLargeDataQrCodeResponse != null) {
                                ECardEntity eCardEntity4 = new ECardEntity();
                                eCardEntity4.setType(iBytesToInt2);
                                eCardEntity4.setReadOrWrite(iBytesToInt);
                                eCardEntity4.setDeviceError(0);
                                iLargeDataQrCodeResponse.qrCode(eCardEntity4);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        if (i != 255) {
            BleThreadManager.getInstance().addData(new BleDataBean(addHeader(47, new byte[]{1, (byte) i}), this.mPackageLength));
        }
    }

    public ConcurrentHashMap<Integer, ILargeDataResponse> getRespMap() {
        return this.respMap;
    }

    public void cleanMap() {
        this.respMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] addHeader(int i, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr == null ? 0 : bArr.length) + 6];
        bArr2[0] = -68;
        bArr2[1] = (byte) i;
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) bArr.length), 0, bArr2, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(bArr)), 0, bArr2, 4, 2);
            System.arraycopy(bArr, 0, bArr2, 6, bArr.length);
        } else {
            bArr2[4] = -1;
            bArr2[5] = -1;
        }
        return bArr2;
    }

    private WriteRequest getWriteRequest(byte[] bArr) {
        XLog.i("getWriteRequest: data=" + DataTransferUtils.getHexString(bArr));
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(bArr);
        return noRspInstance;
    }

    public String bytesToMac(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & 255).toUpperCase();
            if (upperCase.length() == 1) {
                upperCase = "0" + upperCase;
            }
            sb.append(upperCase);
            sb.append(":");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String getWholeText(String str, int i) {
        if (str == null) {
            return str;
        }
        try {
            if (str.getBytes("utf-8").length <= i) {
                return str;
            }
            char[] charArray = str.toCharArray();
            int length = charArray.length;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = 0;
                    break;
                }
                char c = charArray[i2];
                i3 = (c < 0 || c > 127) ? (c < 128 || c > 2047) ? i3 + 3 : i3 + 2 : i3 + 1;
                if (i3 > i) {
                    break;
                }
                i2++;
            }
            return String.valueOf(charArray, 0, i2);
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }
}
