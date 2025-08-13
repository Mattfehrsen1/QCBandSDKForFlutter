package com.oudmon.ble.base.communication;

import android.util.Log;
import com.oudmon.ble.base.bean.SleepDisplay;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.queue.BleDataBean;
import com.oudmon.ble.base.bluetooth.queue.BleThreadManager;
import com.oudmon.ble.base.communication.bigData.AlarmNewEntity;
import com.oudmon.ble.base.communication.bigData.BloodOxygenEntity;
import com.oudmon.ble.base.communication.bigData.CustomWatchFaceEntity;
import com.oudmon.ble.base.communication.bigData.IBloodOxygenCallback;
import com.oudmon.ble.base.communication.bigData.ILargeDataCallback;
import com.oudmon.ble.base.communication.bigData.ILargeSettingForLongDataResponse;
import com.oudmon.ble.base.communication.bigData.IReadAlarmCallback;
import com.oudmon.ble.base.communication.bigData.bean.BaseBean;
import com.oudmon.ble.base.communication.bigData.bean.ClassicBluetooth;
import com.oudmon.ble.base.communication.bigData.bean.ContactBean;
import com.oudmon.ble.base.communication.bigData.bean.ManualBloodOxygen;
import com.oudmon.ble.base.communication.bigData.bean.ManualHeartRate;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataBaseResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataClassicBluetoothResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualBloodOxygenResponse;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataManualHeartRateResponse;
import com.oudmon.ble.base.communication.rsp.CustomizeDialResp;
import com.oudmon.ble.base.communication.rsp.LongSitResp;
import com.oudmon.ble.base.communication.rsp.SleepNewProtoResp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DataTransferUtils;
import com.oudmon.ble.base.util.DateUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/LargeDataHandler.class */
public class LargeDataHandler {
    public static final byte ACTION_Blood_Oxygen = 42;
    public static final byte ACTION_Alarm = 44;
    public static final byte ACTION_Custom_WatchFace = 58;
    public static final byte ACTION_New_Sleep_Protocol = 39;
    public static final byte ACTION_New_Sleep_Protocol_Lunch = 62;
    public static final byte ACTION_ManualHeartRate_Protocol = 40;
    public static final byte ACTION_BT_MAC_Protocol = 46;
    public static final byte ACTION_E_CARD_Protocol = 47;
    public static final byte ACTION_Location_Protocol = 32;
    public static final byte ACTION_Contacts_New = 41;
    public static final byte ACTION_Contact = 45;
    public static final byte ACTION_Manual_Oxygen = 73;
    public static final byte ACTION_LONG_SIT = 91;
    private static final UUID SERIAL_PORT_SERVICE = UUID.fromString("de5bf728-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_NOTIFY = UUID.fromString("de5bf729-d711-4e47-af26-65e3012a5dc7");
    private static final UUID SERIAL_PORT_CHARACTER_WRITE = UUID.fromString("de5bf72a-d711-4e47-af26-65e3012a5dc7");
    private static LargeDataHandler mInstance;
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_NOTIFY);
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

    public void syncBloodOxygen(ILargeDataResponse listener) throws InterruptedException {
        this.respMap.put(42, listener);
        byte[] data = {-1};
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(42, data), this.mPackageLength));
    }

    public void readCustomWatch(ILargeDataResponse listener) throws InterruptedException {
        this.respMap.put(58, listener);
        byte[] cmdData = {1};
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(58, cmdData), this.mPackageLength));
    }

    public void writeCustomWatch(CustomWatchFaceEntity entity) throws InterruptedException {
        byte[] cmdData = {2};
        for (int i = 0; i < entity.getData().size(); i++) {
            CustomWatchFaceEntity.CustomElement element = entity.getData().get(i);
            byte[] data = {(byte) element.getType(), (byte) ByteUtil.loword(element.getX()), (byte) ByteUtil.hiword(element.getX()), (byte) ByteUtil.loword(element.getY()), (byte) ByteUtil.hiword(element.getY()), (byte) element.getR(), (byte) element.getG(), (byte) element.getB()};
            cmdData = ByteUtil.concat(cmdData, data);
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(58, cmdData), this.mPackageLength));
    }

    public void writeAlarm(AlarmNewEntity entity) throws InterruptedException, UnsupportedEncodingException {
        try {
            byte[] cmdData = {2, (byte) entity.getTotal()};
            for (AlarmNewEntity.AlarmBean bean : entity.getData()) {
                byte[] data = {(byte) bean.getAlarmLength(), (byte) bean.getRepeatAndEnable(), (byte) ByteUtil.loword(bean.getMin()), (byte) ByteUtil.hiword(bean.getMin())};
                byte[] content = bean.getContent().getBytes("UTF-8");
                cmdData = ByteUtil.concat(cmdData, ByteUtil.concat(data, content));
            }
            BleThreadManager.getInstance().addData(new BleDataBean(addHeader(44, cmdData), this.mPackageLength));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void readAlarm(ILargeDataResponse listener) throws InterruptedException {
        this.respMap.put(44, listener);
        byte[] cmdData = {1};
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(44, cmdData), this.mPackageLength));
    }

    public void readCustomizeDial(final ILargeDataCallback<CustomizeDialResp> respILargeDataCallback) throws InterruptedException {
        readCustomWatch(new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.1
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 58) {
                    CustomizeDialResp resp = new CustomizeDialResp();
                    resp.setTimeLeft(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 10)));
                    resp.setTimeTop(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 10, 12)));
                    resp.setBatteryLeft(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 16, 18)));
                    resp.setBatteryTop(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 18, 20)));
                    resp.setDataLeft(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 24, 26)));
                    resp.setDataTop(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 26, 28)));
                    resp.setR(ByteUtil.byteToInt(data[12]));
                    resp.setG(ByteUtil.byteToInt(data[13]));
                    resp.setB(ByteUtil.byteToInt(data[14]));
                    respILargeDataCallback.largeDataResp(resp);
                }
            }
        });
    }

    public void syncContact(List<ContactBean> contacts) throws InterruptedException {
        byte[] data = new byte[0];
        for (int i = 0; i < contacts.size(); i++) {
            ContactBean item = contacts.get(i);
            String name = item.getContactName();
            if (name.getBytes().length > 32) {
                name = getWholeText(name, 32);
            }
            byte[] strData = name.getBytes();
            byte strLength = (byte) strData.length;
            byte[] byteArray = ByteUtil.concat(ByteUtil.intToByte(strLength, 1), strData);
            byte[] data2 = ByteUtil.concat(data, byteArray);
            String number = item.getPhoneNumber();
            if (number.getBytes().length > 18) {
                number = getWholeText(number, 18);
            }
            byte[] phoneData = number.getBytes();
            int phoneLength = phoneData.length;
            byte[] phoneArray = ByteUtil.concat(ByteUtil.intToByte(phoneLength, 1), phoneData);
            data = ByteUtil.concat(data2, phoneArray);
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(45, data), JPackageManager.getInstance().getLength()));
    }

    public void syncContactMore(List<ContactBean> contacts, final ILargeDataBaseResponse response) throws InterruptedException {
        int totalCount = contacts.size();
        byte[] data = new byte[0];
        final LinkedList<byte[]> linkedList = new LinkedList<>();
        for (int i = 0; i < contacts.size(); i++) {
            ContactBean item = contacts.get(i);
            String name = item.getContactName();
            if (name.getBytes().length > 32) {
                name = getWholeText(name, 32);
            }
            byte[] strData = name.getBytes();
            byte strLength = (byte) strData.length;
            byte[] byteArray = ByteUtil.concat(ByteUtil.intToByte(strLength, 1), strData);
            byte[] data2 = ByteUtil.concat(data, byteArray);
            String number = item.getPhoneNumber();
            if (number.getBytes().length > 18) {
                number = getWholeText(number, 18);
            }
            byte[] phoneData = number.getBytes();
            int phoneLength = phoneData.length;
            byte[] phoneArray = ByteUtil.concat(ByteUtil.intToByte(phoneLength, 1), phoneData);
            data = ByteUtil.concat(data2, phoneArray);
            if (data.length > 950) {
                linkedList.add(data);
                data = new byte[0];
            }
        }
        linkedList.add(data);
        final int totalPackage = linkedList.size();
        byte[] totalBytes = {(byte) (totalPackage + 1), 0, (byte) ByteUtil.loword(totalCount), (byte) ByteUtil.hiword(totalCount)};
        byte[] addHeaderData = addHeader(41, totalBytes);
        BleThreadManager.getInstance().addData(new BleDataBean(addHeaderData, JPackageManager.getInstance().getLength()));
        final int[] currIndex = {0};
        this.respMap.put(41, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.2
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data3) throws InterruptedException {
                if (cmdType == 41) {
                    int success = ByteUtil.bytesToInt(Arrays.copyOfRange(data3, 6, 7));
                    if (success == 0) {
                        if (linkedList.size() == 0) {
                            response.resp(new BaseBean(0));
                            return;
                        }
                        int[] iArr = currIndex;
                        iArr[0] = iArr[0] + 1;
                        byte[] totalBytes2 = {(byte) (totalPackage + 1), (byte) currIndex[0]};
                        byte[] oneContacts = (byte[]) linkedList.removeFirst();
                        byte[] cmdData = ByteUtil.concat(totalBytes2, oneContacts);
                        byte[] addHeaderData2 = LargeDataHandler.this.addHeader(41, cmdData);
                        int length = 0;
                        while (true) {
                            int i2 = length;
                            if (i2 < addHeaderData2.length) {
                                byte[] bufferSend = new byte[Math.min(JPackageManager.getInstance().getLength(), addHeaderData2.length - i2)];
                                System.arraycopy(addHeaderData2, i2, bufferSend, 0, bufferSend.length);
                                BleThreadManager.getInstance().addData(new BleDataBean(bufferSend, JPackageManager.getInstance().getLength()));
                                length = i2 + JPackageManager.getInstance().getLength();
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    public void readAlarmWithCallback(final IReadAlarmCallback callback) throws InterruptedException {
        getInstance().readAlarm(new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.3
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                int read = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                if (read == 1) {
                    List<AlarmNewEntity.AlarmBean> list = new ArrayList<>();
                    AlarmNewEntity alarmEntity = new AlarmNewEntity();
                    alarmEntity.setTotal(ByteUtil.bytesToInt(Arrays.copyOfRange(data, 7, 8)));
                    if (alarmEntity.getTotal() > 0) {
                        LargeDataHandler.this.parseAlarm(alarmEntity.getTotal(), list, data, ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 9)), 8);
                        for (int i = 0; i < list.size(); i++) {
                            AlarmNewEntity.AlarmBean item = list.get(i);
                            if (item.getContent() == null) {
                                item.setContent("");
                            }
                        }
                        alarmEntity.setData(list);
                        if (callback != null) {
                            callback.readAlarm(alarmEntity);
                            return;
                        }
                        return;
                    }
                    callback.readAlarm(new AlarmNewEntity());
                }
            }
        });
    }

    public void syncBloodOxygenWithCallback(final IBloodOxygenCallback callback) throws InterruptedException {
        getInstance().syncBloodOxygen(new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.4
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 42) {
                    List<BloodOxygenEntity> arrayList = new ArrayList<>();
                    int dataLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 2, 4)) / 49;
                    for (int i = 0; i < dataLength; i++) {
                        BloodOxygenEntity bloodOxygenEntity = new BloodOxygenEntity();
                        byte[] day1 = Arrays.copyOfRange(data, 6 + (i * 49), 6 + ((i + 1) * 49));
                        DateUtil date = new DateUtil();
                        List<Integer> maxList = new ArrayList<>();
                        List<Integer> minList = new ArrayList<>();
                        for (int j = 0; j < day1.length; j++) {
                            if (j == 0) {
                                date.addDay(-day1[0]);
                            } else if (j % 2 == 0) {
                                minList.add(Integer.valueOf(day1[j]));
                            } else {
                                maxList.add(Integer.valueOf(day1[j]));
                            }
                        }
                        bloodOxygenEntity.setDateStr(date.getY_M_D());
                        bloodOxygenEntity.setUnix_time(date.getZeroTime());
                        bloodOxygenEntity.setMinArray(minList);
                        bloodOxygenEntity.setMaxArray(maxList);
                        arrayList.add(bloodOxygenEntity);
                    }
                    if (arrayList.size() > 0) {
                        if (callback != null) {
                            callback.readBloodOxygen(arrayList);
                        }
                    } else if (callback != null) {
                        callback.readBloodOxygen(null);
                    }
                }
            }
        });
    }

    public void syncLongSitList(int offset, final ILargeSettingForLongDataResponse response) throws InterruptedException {
        this.respMap.put(91, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.5
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 91) {
                    int totalDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                    if (totalDay > 0) {
                        int dataLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 2, 4));
                        int currDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 7, 8));
                        int dayLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 9));
                        LargeDataHandler.parseDaySitForLong(currDay, data, 7, dayLength + 2, dataLength, response);
                        return;
                    }
                    if (response != null) {
                        response.sleepData(null);
                    }
                }
            }
        });
        byte[] cmdData = new byte[1];
        if (offset == 0) {
            cmdData[0] = 0;
        } else {
            cmdData[0] = -1;
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(91, cmdData), this.mPackageLength));
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void parseDaySitForLong(int currDay, byte[] data, int start, int offset, int totalLength, ILargeSettingForLongDataResponse response) {
        try {
            LongSitResp longSitResp = new LongSitResp();
            longSitResp.setIndex(currDay);
            byte[] tempData = Arrays.copyOfRange(data, start, start + offset);
            List<LongSitResp.DetailBean> tempList = new ArrayList<>();
            byte[] detailSleep = Arrays.copyOfRange(tempData, 2, tempData.length);
            for (int i = 0; i < detailSleep.length; i++) {
                if (i % 2 == 1) {
                    LongSitResp.DetailBean bean = new LongSitResp.DetailBean();
                    bean.setT(detailSleep[i - 1]);
                    bean.setD(ByteUtil.byteToInt(detailSleep[i]));
                    tempList.add(bean);
                }
            }
            longSitResp.setList(tempList);
            if (response != null) {
                response.sleepData(longSitResp);
            }
            if (start + tempData.length < totalLength) {
                try {
                    int day = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length, start + tempData.length + 1));
                    int newOffset = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length + 1, start + tempData.length + 2));
                    parseDaySitForLong(day, data, start + tempData.length, newOffset + 2, totalLength, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void syncManualBloodOxygenList(int offset, final ILargeDataManualBloodOxygenResponse response) throws InterruptedException {
        this.respMap.put(73, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.6
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 73) {
                    ManualBloodOxygen manualBloodOxygen = new ManualBloodOxygen();
                    int dayOfSet = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                    manualBloodOxygen.setIndex(dayOfSet);
                    List<ManualBloodOxygen.DetailBean> list = new ArrayList<>();
                    byte[] manual = Arrays.copyOfRange(data, 7, data.length);
                    for (int i = 0; i < manual.length / 3; i++) {
                        ManualBloodOxygen.DetailBean bean = new ManualBloodOxygen.DetailBean();
                        bean.setM(ByteUtil.bytesToInt(Arrays.copyOfRange(manual, i * 3, (3 * i) + 2)));
                        bean.setV(ByteUtil.bytesToInt(Arrays.copyOfRange(manual, (i * 3) + 2, (i * 3) + 3)));
                        list.add(bean);
                    }
                    manualBloodOxygen.setData(list);
                    if (response != null) {
                        response.manualBloodOxygen(manualBloodOxygen);
                    }
                }
            }
        });
        byte[] cmdData = new byte[1];
        if (offset != 0) {
            cmdData[0] = -1;
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(73, cmdData), this.mPackageLength));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAlarm(int total, List<AlarmNewEntity.AlarmBean> list, byte[] data, int alarmLength, int alarmStart) {
        AlarmNewEntity.AlarmBean bean = new AlarmNewEntity.AlarmBean();
        bean.setAlarmLength(alarmLength);
        bean.setRepeatAndEnable(ByteUtil.bytesToInt(Arrays.copyOfRange(data, alarmStart + 1, alarmStart + 2)));
        bean.setMin(ByteUtil.bytesToInt(Arrays.copyOfRange(data, alarmStart + 2, alarmStart + 4)));
        if (alarmLength > 4) {
            bean.setContent(new String(Arrays.copyOfRange(data, alarmStart + 4, alarmStart + alarmLength), Charset.defaultCharset()));
        }
        list.add(bean);
        if (list.size() < total) {
            parseAlarm(total, list, data, ByteUtil.bytesToInt(Arrays.copyOfRange(data, alarmStart + alarmLength, alarmStart + alarmLength + 1)), alarmStart + alarmLength);
        }
    }

    public void syncSleepList(int offset, final ILargeDataSleepResponse response, final ILargeDataLaunchSleepResponse lunchSleepResponse) throws InterruptedException {
        this.respMap.put(39, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.7
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 39) {
                    int totalDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                    if (totalDay > 0) {
                        int dataLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 2, 4));
                        int currDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 7, 8));
                        int dayLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 9));
                        LargeDataHandler.this.parseDaySleep(currDay, data, 7, dayLength + 2, dataLength, response, totalDay);
                        return;
                    }
                    if (response != null) {
                        response.sleepData(null);
                    }
                }
            }
        });
        this.respMap.put(62, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.8
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 62) {
                    int totalDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                    if (totalDay > 0) {
                        int dataLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 2, 4));
                        int currDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 7, 8));
                        int dayLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 9));
                        LargeDataHandler.this.parseDaySleepLunch(currDay, data, 7, dayLength + 2, dataLength, lunchSleepResponse);
                        return;
                    }
                    if (lunchSleepResponse != null) {
                        lunchSleepResponse.sleepData(null);
                    }
                }
            }
        });
        byte[] cmdData = new byte[2];
        if (offset == 0) {
            cmdData[0] = 0;
        } else {
            cmdData[0] = -1;
        }
        cmdData[1] = 1;
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(39, cmdData), this.mPackageLength));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDaySleepLunch(int currDay, byte[] data, int start, int offset, int totalLength, ILargeDataLaunchSleepResponse response) {
        try {
            SleepNewProtoResp sleep = new SleepNewProtoResp();
            sleep.setLunchBreak(true);
            DateUtil dateUtil = new DateUtil();
            dateUtil.addDay(-currDay);
            byte[] tempData = Arrays.copyOfRange(data, start, start + offset);
            int startSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(tempData, 2, 4));
            int endSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(tempData, 4, 6));
            sleep.setLunchEt((int) (dateUtil.getZeroTime() + (endSleep * 60)));
            sleep.setLunchSt((int) (dateUtil.getZeroTime() + (startSleep * 60)));
            List<SleepNewProtoResp.DetailBean> tempList = new ArrayList<>();
            byte[] detailSleep = Arrays.copyOfRange(tempData, 6, tempData.length);
            for (int i = 0; i < detailSleep.length; i++) {
                if (i % 2 == 1) {
                    SleepNewProtoResp.DetailBean bean = new SleepNewProtoResp.DetailBean();
                    bean.setT(detailSleep[i - 1]);
                    if (detailSleep[i] < 0) {
                        bean.setD(ByteUtil.byteToInt(detailSleep[i]));
                    } else {
                        bean.setD(detailSleep[i]);
                    }
                    tempList.add(bean);
                }
            }
            sleep.setList(tempList);
            if (response != null) {
                response.sleepData(sleep);
            }
            if (start + tempData.length < totalLength) {
                try {
                    int day = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length, start + tempData.length + 1));
                    int newOffset = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length + 1, start + tempData.length + 2));
                    parseDaySleepLunch(day, data, start + tempData.length, newOffset + 2, totalLength, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void syncSleepListIndianDemand(int offset, final ILargeDataSleepResponse response) throws InterruptedException {
        this.respMap.put(39, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.9
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 39) {
                    int totalDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                    if (totalDay > 0) {
                        int dataLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 2, 4));
                        int currDay = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 7, 8));
                        int dayLength = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 8, 9));
                        LargeDataHandler.this.parseDaySleepIndian(currDay, data, 7, dayLength + 2, dataLength, response, totalDay);
                        return;
                    }
                    if (response != null) {
                        response.sleepData(null);
                    }
                }
            }
        });
        byte[] cmdData = new byte[1];
        if (offset == 0) {
            cmdData[0] = 0;
        } else {
            cmdData[0] = -1;
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(39, cmdData), this.mPackageLength));
    }

    public void syncManualHeartRateList(int offset, final ILargeDataManualHeartRateResponse response) throws InterruptedException {
        this.respMap.put(40, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.10
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 40) {
                    ManualHeartRate manualHeartRate = new ManualHeartRate();
                    int dayOfSet = ByteUtil.bytesToInt(Arrays.copyOfRange(data, 6, 7));
                    manualHeartRate.setIndex(dayOfSet);
                    List<ManualHeartRate.DetailBean> list = new ArrayList<>();
                    byte[] manual = Arrays.copyOfRange(data, 7, data.length);
                    for (int i = 0; i < manual.length / 3; i++) {
                        ManualHeartRate.DetailBean bean = new ManualHeartRate.DetailBean();
                        bean.setM(ByteUtil.bytesToInt(Arrays.copyOfRange(manual, i * 3, (3 * i) + 2)));
                        bean.setV(ByteUtil.bytesToInt(Arrays.copyOfRange(manual, (i * 3) + 2, (i * 3) + 3)));
                        list.add(bean);
                    }
                    manualHeartRate.setData(list);
                    if (response != null) {
                        response.manualHeart(manualHeartRate);
                    }
                }
            }
        });
        byte[] cmdData = new byte[1];
        if (offset != 0) {
            cmdData[0] = -1;
        }
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(40, cmdData), this.mPackageLength));
    }

    public void syncClassicBluetooth(final ILargeDataClassicBluetoothResponse response) throws InterruptedException {
        this.respMap.put(46, new ILargeDataResponse() { // from class: com.oudmon.ble.base.communication.LargeDataHandler.11
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public void parseData(int cmdType, byte[] data) {
                if (cmdType == 46) {
                    ClassicBluetooth bluetooth = new ClassicBluetooth();
                    bluetooth.setDeviceMac(LargeDataHandler.this.bytesToMac(Arrays.copyOfRange(data, 6, 12)));
                    bluetooth.setDeviceName(new String(Arrays.copyOfRange(data, 13, 13 + data[12]), StandardCharsets.UTF_8));
                    if (response != null) {
                        response.classicBluetooth(bluetooth);
                    }
                }
            }
        });
        byte[] cmdData = new byte[1];
        BleThreadManager.getInstance().addData(new BleDataBean(addHeader(46, cmdData), this.mPackageLength));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDaySleep(int currDay, byte[] data, int start, int offset, int totalLength, ILargeDataSleepResponse response, int totalDay) {
        SleepNewProtoResp sleep = new SleepNewProtoResp();
        DateUtil dateUtil = new DateUtil();
        dateUtil.addDay(-currDay);
        byte[] tempData = Arrays.copyOfRange(data, start, start + offset);
        int startSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(tempData, 2, 4));
        int endSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(tempData, 4, 6));
        sleep.setEt((int) (dateUtil.getZeroTime() + (endSleep * 60)));
        if (startSleep > 1080) {
            dateUtil.addDay(-1);
        }
        sleep.setSt((int) (dateUtil.getZeroTime() + (startSleep * 60)));
        List<SleepNewProtoResp.DetailBean> tempList = new ArrayList<>();
        byte[] detailSleep = Arrays.copyOfRange(tempData, 6, tempData.length);
        for (int i = 0; i < detailSleep.length; i++) {
            if (i % 2 == 1) {
                SleepNewProtoResp.DetailBean bean = new SleepNewProtoResp.DetailBean();
                bean.setT(detailSleep[i - 1]);
                if (detailSleep[i] < 0) {
                    bean.setD(ByteUtil.byteToInt(detailSleep[i]));
                } else {
                    bean.setD(detailSleep[i]);
                }
                tempList.add(bean);
            }
        }
        sleep.setList(tempList);
        if (response != null) {
            SleepDisplay sleepDisplay = new SleepDisplay();
            int deepSleep = 0;
            int lightSleep = 0;
            int awakeSleep = 0;
            int rapidSleep = 0;
            List<SleepDisplay.SleepDataBean> listData = new ArrayList<>();
            List<SleepNewProtoResp.DetailBean> merge = new ArrayList<>();
            SleepNewProtoResp.DetailBean oneBean = new SleepNewProtoResp.DetailBean();
            for (int i2 = 0; i2 < sleep.getList().size(); i2++) {
                SleepNewProtoResp.DetailBean bean2 = sleep.getList().get(i2);
                if (i2 == 0) {
                    oneBean = bean2;
                }
                if (i2 + 1 < sleep.getList().size()) {
                    if (bean2.getT() == sleep.getList().get(i2 + 1).getT()) {
                        oneBean.setD(sleep.getList().get(i2 + 1).getD() + sleep.getList().get(i2).getD());
                    } else {
                        merge.add(oneBean);
                        oneBean = sleep.getList().get(i2 + 1);
                    }
                } else {
                    merge.add(oneBean);
                }
            }
            for (int i3 = 0; i3 < merge.size(); i3++) {
                SleepNewProtoResp.DetailBean bean3 = merge.get(i3);
                if (bean3.getT() == 2) {
                    lightSleep += bean3.getD();
                } else if (bean3.getT() == 3) {
                    deepSleep += bean3.getD();
                } else if (bean3.getT() == 5) {
                    awakeSleep += bean3.getD();
                } else if (bean3.getT() == 4) {
                    rapidSleep += bean3.getD();
                }
                SleepDisplay.SleepDataBean sleepDataBean = new SleepDisplay.SleepDataBean();
                if (bean3.getT() == 3) {
                    sleepDataBean.setType(1);
                } else if (bean3.getT() == 2) {
                    sleepDataBean.setType(2);
                } else if (bean3.getT() == 5) {
                    sleepDataBean.setType(3);
                } else if (bean3.getT() == 0 || bean3.getT() == 1) {
                    sleepDataBean.setType(5);
                } else if (bean3.getT() == 4) {
                    sleepDataBean.setType(4);
                }
                if (i3 == 0) {
                    sleepDataBean.setSleepStart(sleep.getSt());
                    sleepDataBean.setSleepEnd(sleep.getSt() + (bean3.getD() * 60));
                } else {
                    SleepDisplay.SleepDataBean pre = listData.get(i3 - 1);
                    sleepDataBean.setSleepStart(pre.getSleepEnd());
                    sleepDataBean.setSleepEnd(pre.getSleepEnd() + (bean3.getD() * 60));
                }
                listData.add(sleepDataBean);
            }
            int deepSleep2 = deepSleep * 60;
            int lightSleep2 = lightSleep * 60;
            int awakeSleep2 = awakeSleep * 60;
            int rapidSleep2 = rapidSleep * 60;
            sleepDisplay.setList(listData);
            sleepDisplay.setDeepSleepDuration(deepSleep2);
            sleepDisplay.setShallowSleepDuration(lightSleep2);
            sleepDisplay.setAwakeDuration(awakeSleep2);
            sleepDisplay.setRapidDuration(rapidSleep2);
            sleepDisplay.setTotalSleepDuration(deepSleep2 + awakeSleep2 + lightSleep2 + rapidSleep2);
            sleepDisplay.setSleepTime(sleep.getSt());
            sleepDisplay.setWakeTime(sleep.getEt());
            sleepDisplay.setWakeTime(sleep.getEt());
            sleepDisplay.setTotalDays(totalDay);
            response.sleepData(sleepDisplay);
        }
        if (start + tempData.length < totalLength) {
            try {
                int day = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length, start + tempData.length + 1));
                int newOffset = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length + 1, start + tempData.length + 2));
                parseDaySleep(day, data, start + tempData.length, newOffset + 2, totalLength, response, totalDay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDaySleepIndian(int currDay, byte[] data, int start, int offset, int totalLength, ILargeDataSleepResponse response, int totalDay) {
        SleepNewProtoResp sleep = new SleepNewProtoResp();
        DateUtil dateUtil = new DateUtil();
        dateUtil.addDay(-currDay);
        byte[] tempData = Arrays.copyOfRange(data, start, start + offset);
        int startSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(tempData, 2, 4));
        int endSleep = ByteUtil.bytesToInt(Arrays.copyOfRange(tempData, 4, 6));
        sleep.setEt((int) (dateUtil.getZeroTime() + (endSleep * 60)));
        if (startSleep > 1080) {
            dateUtil.addDay(-1);
        }
        sleep.setSt((int) (dateUtil.getZeroTime() + (startSleep * 60)));
        List<SleepNewProtoResp.DetailBean> tempList = new ArrayList<>();
        byte[] detailSleep = Arrays.copyOfRange(tempData, 6, tempData.length);
        for (int i = 0; i < detailSleep.length; i++) {
            if (i % 2 == 1) {
                SleepNewProtoResp.DetailBean bean = new SleepNewProtoResp.DetailBean();
                bean.setT(detailSleep[i - 1]);
                if (detailSleep[i] < 0) {
                    bean.setD(ByteUtil.byteToInt(detailSleep[i]));
                } else {
                    bean.setD(detailSleep[i]);
                }
                tempList.add(bean);
            }
        }
        sleep.setList(tempList);
        if (response != null) {
            SleepDisplay sleepDisplay = new SleepDisplay();
            int deepSleep = 0;
            int lightSleep = 0;
            int awakeSleep = 0;
            int rapidSleep = 0;
            List<SleepDisplay.SleepDataBean> listData = new ArrayList<>();
            List<SleepNewProtoResp.DetailBean> merge = new ArrayList<>();
            SleepNewProtoResp.DetailBean oneBean = new SleepNewProtoResp.DetailBean();
            for (int i2 = 0; i2 < sleep.getList().size(); i2++) {
                SleepNewProtoResp.DetailBean bean2 = sleep.getList().get(i2);
                if (i2 == 0) {
                    oneBean = bean2;
                }
                if (i2 + 1 < sleep.getList().size()) {
                    if (bean2.getT() == sleep.getList().get(i2 + 1).getT()) {
                        oneBean.setD(sleep.getList().get(i2 + 1).getD() + sleep.getList().get(i2).getD());
                    } else {
                        merge.add(oneBean);
                        oneBean = sleep.getList().get(i2 + 1);
                    }
                } else {
                    merge.add(oneBean);
                }
            }
            for (int i3 = 0; i3 < merge.size(); i3++) {
                SleepNewProtoResp.DetailBean bean3 = merge.get(i3);
                if (bean3.getT() == 2) {
                    lightSleep += bean3.getD();
                } else if (bean3.getT() == 3) {
                    deepSleep += bean3.getD();
                } else if (bean3.getT() == 5) {
                    awakeSleep += bean3.getD();
                } else if (bean3.getT() == 4) {
                    rapidSleep += bean3.getD();
                }
                SleepDisplay.SleepDataBean sleepDataBean = new SleepDisplay.SleepDataBean();
                if (bean3.getT() == 3) {
                    sleepDataBean.setType(1);
                } else if (bean3.getT() == 2) {
                    sleepDataBean.setType(2);
                } else if (bean3.getT() == 5) {
                    sleepDataBean.setType(3);
                } else if (bean3.getT() == 0 || bean3.getT() == 1) {
                    sleepDataBean.setType(5);
                } else if (bean3.getT() == 4) {
                    sleepDataBean.setType(4);
                }
                if (i3 == 0) {
                    sleepDataBean.setSleepStart(sleep.getSt());
                    sleepDataBean.setSleepEnd(sleep.getSt() + (bean3.getD() * 60));
                } else {
                    SleepDisplay.SleepDataBean pre = listData.get(i3 - 1);
                    sleepDataBean.setSleepStart(pre.getSleepEnd());
                    sleepDataBean.setSleepEnd(pre.getSleepEnd() + (bean3.getD() * 60));
                }
                listData.add(sleepDataBean);
            }
            List<SleepDisplay.SleepDataBean> last = new ArrayList<>();
            for (SleepDisplay.SleepDataBean listDatum : listData) {
                if (listDatum.getType() != 5) {
                    last.add(listDatum);
                }
            }
            int deepSleep2 = deepSleep * 60;
            int lightSleep2 = lightSleep * 60;
            int awakeSleep2 = awakeSleep * 60;
            int rapidSleep2 = rapidSleep * 60;
            sleepDisplay.setList(last);
            sleepDisplay.setDeepSleepDuration(deepSleep2);
            sleepDisplay.setShallowSleepDuration(lightSleep2);
            sleepDisplay.setAwakeDuration(awakeSleep2);
            sleepDisplay.setRapidDuration(rapidSleep2);
            sleepDisplay.setTotalSleepDuration(deepSleep2 + awakeSleep2 + lightSleep2 + rapidSleep2);
            sleepDisplay.setSleepTime(sleep.getSt());
            sleepDisplay.setWakeTime(sleep.getEt());
            sleepDisplay.setWakeTime(sleep.getEt());
            sleepDisplay.setTotalDays(totalDay);
            response.sleepData(sleepDisplay);
        }
        if (start + tempData.length < totalLength) {
            try {
                int day = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length, start + tempData.length + 1));
                int newOffset = ByteUtil.bytesToInt(Arrays.copyOfRange(data, start + tempData.length + 1, start + tempData.length + 2));
                parseDaySleepIndian(day, data, start + tempData.length, newOffset + 2, totalLength, response, totalDay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String bytesToMac(byte[] copyOfRange) {
        StringBuilder sb = new StringBuilder();
        for (byte b : copyOfRange) {
            String i = Integer.toHexString(b & 255).toUpperCase();
            sb.append(i.length() == 1 ? "0" + i : i).append(":");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public ConcurrentHashMap<Integer, ILargeDataResponse> getRespMap() {
        return this.respMap;
    }

    public void cleanMap() {
        this.respMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] addHeader(int cmdId, byte[] data) {
        byte[] pocket = new byte[(data == null ? 0 : data.length) + 6];
        pocket[0] = -68;
        pocket[1] = (byte) cmdId;
        if (data != null && data.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) data.length), 0, pocket, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(data)), 0, pocket, 4, 2);
            System.arraycopy(data, 0, pocket, 6, data.length);
        } else {
            pocket[4] = -1;
            pocket[5] = -1;
        }
        return pocket;
    }

    private WriteRequest getWriteRequest(byte[] data) {
        Log.i(Constants.TAG, "getWriteRequest: data=" + DataTransferUtils.getHexString(data));
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(SERIAL_PORT_SERVICE, SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(data);
        return noRspInstance;
    }

    public String getWholeText(String text, int byteCount) {
        if (text != null) {
            try {
                if (text.getBytes("utf-8").length > byteCount) {
                    char[] tempChars = text.toCharArray();
                    int sumByte = 0;
                    int charIndex = 0;
                    int i = 0;
                    int len = tempChars.length;
                    while (true) {
                        if (i >= len) {
                            break;
                        }
                        char itemChar = tempChars[i];
                        if (itemChar >= 0 && itemChar <= 127) {
                            sumByte++;
                        } else if (itemChar >= 128 && itemChar <= 2047) {
                            sumByte += 2;
                        } else {
                            sumByte += 3;
                        }
                        if (sumByte <= byteCount) {
                            i++;
                        } else {
                            charIndex = i;
                            break;
                        }
                    }
                    return String.valueOf(tempChars, 0, charIndex);
                }
            } catch (UnsupportedEncodingException e) {
            }
        }
        return text;
    }
}
