package com.oudmon.ble.base.util;

import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import com.oudmon.ble.base.bean.SleepDetail;
import com.oudmon.ble.base.bean.SleepDisplay;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.entity.BleSleepDetails;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.req.ReadSleepDetailsReq;
import com.oudmon.ble.base.communication.rsp.ReadSleepDetailsRsp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/util/SleepAnalyzerUtils.class */
public class SleepAnalyzerUtils {
    private static SleepAnalyzerUtils mInstance;
    int maximumSize = 7;
    private LinkedHashMap<String, SleepDetail> sleepMap = new LinkedHashMap<String, SleepDetail>() { // from class: com.oudmon.ble.base.util.SleepAnalyzerUtils.1
        private static final long serialVersionUID = 1;

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, SleepDetail> eldest) {
            return size() > SleepAnalyzerUtils.this.maximumSize;
        }
    };

    public static SleepAnalyzerUtils getInstance() {
        if (mInstance == null) {
            synchronized (FileHandle.class) {
                if (mInstance == null) {
                    mInstance = new SleepAnalyzerUtils();
                }
            }
        }
        return mInstance;
    }

    public void syncSleepReturnSleepDisplay(String address, int dayIndex, ISleepCallback sleepCallback) {
        DateUtil d1 = new DateUtil();
        d1.addDay(-(dayIndex + 1));
        theDayBefore(address, d1, dayIndex + 1, sleepCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void today(final String address, final DateUtil dateUtil, int dayIndex, final ISleepCallback sleepCallback) {
        CommandHandle.getInstance().executeReqCmd(new ReadSleepDetailsReq(dayIndex, 0, 95), new ICommandResponse<ReadSleepDetailsRsp>() { // from class: com.oudmon.ble.base.util.SleepAnalyzerUtils.2
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(ReadSleepDetailsRsp resultEntity) {
                SleepDetail sleepDetail = SleepAnalyzerUtils.this.parseSleepDetail(address, resultEntity);
                if (sleepDetail != null) {
                    dateUtil.addDay(-1);
                    if (sleepCallback != null) {
                        sleepCallback.sleepDisplay(SleepAnalyzerUtils.this.getNewDisplayModel(sleepDetail, (SleepDetail) SleepAnalyzerUtils.this.sleepMap.get(dateUtil.getY_M_D())));
                        return;
                    }
                    return;
                }
                if (sleepCallback != null) {
                    sleepCallback.sleepDisplay(null);
                }
            }
        });
    }

    private void theDayBefore(final String address, final DateUtil dateUtil, final int dayIndex, final ISleepCallback sleepCallback) {
        CommandHandle.getInstance().executeReqCmd(new ReadSleepDetailsReq(dayIndex, 0, 95), new ICommandResponse<ReadSleepDetailsRsp>() { // from class: com.oudmon.ble.base.util.SleepAnalyzerUtils.3
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public void onDataResponse(ReadSleepDetailsRsp resultEntity) {
                SleepDetail sleepDetail = SleepAnalyzerUtils.this.parseSleepDetail(address, resultEntity);
                if (sleepDetail != null) {
                    SleepAnalyzerUtils.this.sleepMap.put(dateUtil.getY_M_D(), sleepDetail);
                } else {
                    SleepAnalyzerUtils.this.sleepMap.remove(dateUtil.getY_M_D());
                }
                dateUtil.addDay(1);
                SleepAnalyzerUtils.this.today(address, dateUtil, dayIndex - 1, sleepCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SleepDetail parseSleepDetail(String address, ReadSleepDetailsRsp readSleepDetailsRsp) {
        List<BleSleepDetails> list = readSleepDetailsRsp.getBleSleepDetailses();
        if (list.size() > 0) {
            DateUtil dateUtil = new DateUtil(list.get(0).getYear(), list.get(0).getMonth(), list.get(0).getDay());
            if (dateUtil.getUnixTimestamp() > new DateUtil().getUnixTimestamp()) {
                return null;
            }
            SleepDetail sleepDetail = toSleepDetails(address, list);
            return sleepDetail;
        }
        return null;
    }

    private SleepDetail merge(SleepDetail oldEntity, SleepDetail newEntity) {
        SleepDetail e = new SleepDetail();
        e.setDeviceAddress(oldEntity.getDeviceAddress());
        e.setInterval(oldEntity.getInterval());
        e.setDateStr(oldEntity.getDateStr());
        int[] oldIndexs = StringUtils.stringToIntArray(oldEntity.getIndex_str());
        int[] oldQualitys = StringUtils.stringToIntArray(oldEntity.getQuality());
        int[] newIndexs = StringUtils.stringToIntArray(newEntity.getIndex_str());
        int[] newQualitys = StringUtils.stringToIntArray(newEntity.getQuality());
        SparseIntArray data = new SparseIntArray();
        for (int i = 0; i < oldIndexs.length; i++) {
            data.put(oldIndexs[i], oldQualitys[i]);
        }
        for (int i2 = 0; i2 < newIndexs.length; i2++) {
            data.put(newIndexs[i2], newQualitys[i2]);
        }
        int[] indexs = new int[data.size()];
        for (int k = 0; k < data.size(); k++) {
            indexs[k] = data.keyAt(k);
        }
        Arrays.sort(indexs);
        int[] qualitys = new int[data.size()];
        for (int i3 = 0; i3 < indexs.length; i3++) {
            qualitys[i3] = data.get(indexs[i3]);
        }
        e.setIndex_str(StringUtils.intArrayToString(indexs));
        e.setQuality(StringUtils.intArrayToString(qualitys));
        return e;
    }

    private SleepDetail toSleepDetails(String address, List<BleSleepDetails> list) {
        SleepDetail sleepDetail = new SleepDetail();
        sleepDetail.setDeviceAddress(address);
        sleepDetail.setInterval(900);
        DateUtil date = new DateUtil(list.get(0).getYear(), list.get(0).getMonth(), list.get(0).getDay());
        sleepDetail.setDateStr(date.getY_M_D());
        sleepDetail.setUnixTime(date.getUnixTimestamp());
        SparseIntArray data = new SparseIntArray();
        for (BleSleepDetails d : list) {
            int quality = 0;
            int[] singleQuality = d.getSleepQualities();
            if (singleQuality[1] < 10 && singleQuality[1] > 0) {
                int newQuality = (singleQuality[1] * 100000) + (singleQuality[2] * 1000) + singleQuality[3];
                quality = newQuality;
            }
            data.put(d.getTimeIndex(), quality);
        }
        int[] indexs = new int[data.size()];
        for (int k = 0; k < data.size(); k++) {
            indexs[k] = data.keyAt(k);
        }
        Arrays.sort(indexs);
        sleepDetail.setIndex_str(StringUtils.intArrayToString(indexs));
        int[] qualitys = new int[data.size()];
        for (int i = 0; i < indexs.length; i++) {
            qualitys[i] = data.get(indexs[i]);
        }
        sleepDetail.setQuality(StringUtils.intArrayToString(qualitys));
        return sleepDetail;
    }

    public SleepDisplay getNewDisplayModel(SleepDetail currentDay, SleepDetail theDayBefore) {
        boolean sameDevice;
        if (currentDay == null) {
            currentDay = new SleepDetail();
        }
        if (theDayBefore == null) {
            theDayBefore = new SleepDetail();
        }
        Log.i("sleep", currentDay.toString());
        Log.i("sleep", theDayBefore.toString());
        if (TextUtils.isEmpty(currentDay.getIndex_str()) && TextUtils.isEmpty(currentDay.getQuality())) {
            theDayBefore = new SleepDetail();
        }
        String currDevice = currentDay.getDeviceAddress();
        String yesterdayDevice = theDayBefore.getDeviceAddress();
        if (!TextUtils.isEmpty(currDevice) && !TextUtils.isEmpty(yesterdayDevice) && currDevice.equalsIgnoreCase(yesterdayDevice)) {
            sameDevice = true;
        } else {
            sameDevice = false;
        }
        List<Integer> indexList = new ArrayList<>();
        List<Integer> qualityList = new ArrayList<>();
        int[] currentIndex = StringUtils.stringToIntArray(currentDay.getIndex_str());
        int[] currentDayQualitys = StringUtils.stringToIntArray(currentDay.getQuality());
        if (sameDevice) {
            int[] beforeIndex = StringUtils.stringToIntArray(theDayBefore.getIndex_str());
            int[] beforeDayQualitys = StringUtils.stringToIntArray(theDayBefore.getQuality());
            for (int index : beforeIndex) {
                indexList.add(Integer.valueOf(index));
            }
            for (int i : beforeDayQualitys) {
                qualityList.add(Integer.valueOf(i));
            }
        }
        SleepDisplay sleepDisplay = new SleepDisplay();
        List<SleepDisplay.SleepDataBean> list = new ArrayList<>();
        List<SleepDisplay.SleepDataBean> marge = new ArrayList<>();
        long lastTimeStamp = 0;
        int totalSleepDuration = 0;
        int deepSleepDuration = 0;
        int shallowSleepDuration = 0;
        int awakeSleepDuration = 0;
        boolean end = false;
        for (int index2 : currentIndex) {
            indexList.add(Integer.valueOf(index2 + 96));
        }
        for (int i2 : currentDayQualitys) {
            qualityList.add(Integer.valueOf(i2));
        }
        List<Integer> newIndexList = new ArrayList<>();
        List<Integer> newQualityList = new ArrayList<>();
        if (indexList.size() > 0) {
            for (int i3 = indexList.get(0).intValue(); i3 <= indexList.get(indexList.size() - 1).intValue(); i3++) {
                newIndexList.add(Integer.valueOf(i3));
                if (indexList.contains(Integer.valueOf(i3))) {
                    int position = indexList.indexOf(Integer.valueOf(i3));
                    newQualityList.add(qualityList.get(position));
                } else {
                    newQualityList.add(600000);
                }
            }
        }
        List<Integer> values = null;
        for (int i4 = 0; i4 < newIndexList.size(); i4++) {
            if (newIndexList.get(i4).intValue() >= 71 && newIndexList.get(i4).intValue() <= 167) {
                int type = newQualityList.get(i4).intValue() / 100000;
                int quality = newQualityList.get(i4).intValue() % 1000;
                int sleepIndex = (newQualityList.get(i4).intValue() % 100000) / 1000;
                if (type == 1 && !end) {
                    Log.i("sleep", "处理头部，sleepIndex = " + sleepIndex + ", quality = " + quality);
                    int sleepTime = (newIndexList.get(i4).intValue() * 15 * 60) + ((15 - sleepIndex) * 60);
                    if (sleepTime >= 86400) {
                        sleepTime -= 86400;
                    }
                    SleepDisplay.SleepDataBean start = new SleepDisplay.SleepDataBean();
                    Date date = DateUtil.String2Date(DateUtil.dFyyyyMMdd1, currentDay.getDateStr());
                    DateUtil startDate = new DateUtil(date);
                    if (sleepTime >= 64800) {
                        startDate.addDay(-1);
                    }
                    long startUnixTime = startDate.getZeroTime() + sleepTime;
                    start.setSleepStart(startUnixTime);
                    sleepDisplay.setSleepTime((int) startUnixTime);
                    values = new ArrayList<>();
                    values.add(Integer.valueOf(quality <= 50 ? 1 : 2));
                    totalSleepDuration += sleepIndex * 60;
                    if (quality <= 50) {
                        deepSleepDuration += sleepIndex * 60;
                        start.setSleepEnd(startUnixTime + (sleepIndex * 60));
                        start.setType(1);
                    } else {
                        shallowSleepDuration += sleepIndex * 60;
                        start.setSleepEnd(startUnixTime + (sleepIndex * 60));
                        start.setType(2);
                    }
                    lastTimeStamp = start.getSleepEnd();
                    list.add(start);
                } else if (type == 2 && values != null && !end) {
                    Log.i("sleep", "处理尾部，sleepIndex = " + sleepIndex + ", quality = " + quality);
                    int wakeTime = (newIndexList.get(i4).intValue() * 15 * 60) + (sleepIndex * 60);
                    if (wakeTime >= 86400) {
                        wakeTime -= 86400;
                    }
                    SleepDisplay.SleepDataBean sleepEnd = new SleepDisplay.SleepDataBean();
                    Date date2 = DateUtil.String2Date(DateUtil.dFyyyyMMdd1, currentDay.getDateStr());
                    DateUtil endDate = new DateUtil(date2);
                    long endTime = endDate.getZeroTime() + wakeTime;
                    sleepEnd.setSleepEnd(endTime);
                    sleepDisplay.setWakeTime((int) endTime);
                    end = true;
                    values.add(Integer.valueOf(quality <= 50 ? 1 : 2));
                    totalSleepDuration += sleepIndex * 60;
                    if (quality <= 50) {
                        deepSleepDuration += sleepIndex * 60;
                        sleepEnd.setType(1);
                    } else {
                        shallowSleepDuration += sleepIndex * 60;
                        sleepEnd.setType(2);
                    }
                    sleepEnd.setSleepStart(sleepEnd.getSleepEnd() - (sleepIndex * 60));
                    list.add(sleepEnd);
                } else if ((type == 3 || type == 4 || type == 5) && values != null && !end) {
                    Log.i("sleep", "处理中间，sleepIndex = " + sleepIndex + ", quality = " + quality);
                    SleepDisplay.SleepDataBean otherSleep = new SleepDisplay.SleepDataBean();
                    totalSleepDuration += sleepIndex * 60;
                    if (quality <= 50) {
                        deepSleepDuration += sleepIndex * 60;
                        otherSleep.setType(1);
                    } else if (quality == 128) {
                        awakeSleepDuration += sleepIndex * 60;
                    } else {
                        shallowSleepDuration += sleepIndex * 60;
                        otherSleep.setType(2);
                    }
                    otherSleep.setSleepStart(lastTimeStamp);
                    otherSleep.setSleepEnd(lastTimeStamp + (sleepIndex * 60));
                    if (sleepIndex < 15 || quality == 128) {
                        values.add(0);
                        otherSleep.setType(3);
                    } else {
                        values.add(Integer.valueOf(quality <= 50 ? 1 : 2));
                    }
                    lastTimeStamp = otherSleep.getSleepEnd();
                    list.add(otherSleep);
                } else {
                    if (values == null && i4 >= 143) {
                        Log.i("sleep", "没有睡觉");
                        SleepDisplay sleep = new SleepDisplay();
                        sleep.setList(new ArrayList());
                        return sleep;
                    }
                    if (type == 6 && values != null && !end) {
                        values.add(0);
                        SleepDisplay.SleepDataBean awake = new SleepDisplay.SleepDataBean();
                        awake.setSleepStart(lastTimeStamp);
                        awake.setSleepEnd(lastTimeStamp + (sleepIndex * 60));
                        awake.setType(3);
                        lastTimeStamp = awake.getSleepEnd();
                    }
                }
            }
        }
        sleepDisplay.setTotalSleepDuration(totalSleepDuration);
        sleepDisplay.setDeepSleepDuration(deepSleepDuration);
        sleepDisplay.setShallowSleepDuration(shallowSleepDuration);
        sleepDisplay.setAwakeDuration(awakeSleepDuration);
        sleepDisplay.setAddress(currDevice);
        if (totalSleepDuration <= 30) {
            sleepDisplay = new SleepDisplay();
        }
        SleepDisplay.SleepDataBean bean = new SleepDisplay.SleepDataBean();
        for (int index3 = 0; index3 < list.size(); index3++) {
            SleepDisplay.SleepDataBean item = list.get(index3);
            if (index3 == 0) {
                bean = item;
            }
            if (index3 + 1 < list.size()) {
                if (item.getType() == list.get(index3 + 1).getType()) {
                    bean.setSleepEnd(list.get(index3 + 1).getSleepEnd());
                } else {
                    marge.add(bean);
                    bean = list.get(index3 + 1);
                }
            } else {
                if (item.getType() == bean.getType()) {
                    bean.setSleepEnd(item.getSleepEnd());
                } else {
                    bean = item;
                }
                marge.add(bean);
            }
        }
        sleepDisplay.setList(marge);
        return sleepDisplay;
    }
}
