package com.oudmon.ble.base.communication.req;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import com.qcwireless.qcwatch.base.view.Localization;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes3.dex */
public class SetTimeReq extends BaseReqCmd {
    private byte[] mData;
    private byte mLanguage;
    private Map<String, Integer> mLocaleMap;

    @Deprecated
    public SetTimeReq() {
        super((byte) 1);
        this.mLanguage = (byte) 0;
        this.mData = new byte[8];
        this.mLocaleMap = new HashMap();
        initMap();
        setLanguage();
        Calendar calendar = Calendar.getInstance();
        this.mData[0] = BLEDataFormatUtils.decimalToBCD(calendar.get(1) % 2000);
        this.mData[1] = BLEDataFormatUtils.decimalToBCD(calendar.get(2) + 1);
        this.mData[2] = BLEDataFormatUtils.decimalToBCD(calendar.get(5));
        this.mData[3] = BLEDataFormatUtils.decimalToBCD(calendar.get(11));
        this.mData[4] = BLEDataFormatUtils.decimalToBCD(calendar.get(12));
        this.mData[5] = BLEDataFormatUtils.decimalToBCD(calendar.get(13));
    }

    public SetTimeReq(int i) {
        super((byte) 1);
        this.mLanguage = (byte) 0;
        this.mData = new byte[8];
        this.mLocaleMap = new HashMap();
        initMap();
        setLanguage();
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, i);
        this.mData[0] = BLEDataFormatUtils.decimalToBCD(calendar.get(1) % 2000);
        this.mData[1] = BLEDataFormatUtils.decimalToBCD(calendar.get(2) + 1);
        this.mData[2] = BLEDataFormatUtils.decimalToBCD(calendar.get(5));
        this.mData[3] = BLEDataFormatUtils.decimalToBCD(calendar.get(11));
        this.mData[4] = BLEDataFormatUtils.decimalToBCD(calendar.get(12));
        this.mData[5] = BLEDataFormatUtils.decimalToBCD(calendar.get(13));
    }

    private void initMap() {
        this.mLocaleMap.put("zh_CN", 0);
        this.mLocaleMap.put("en", 1);
        this.mLocaleMap.put("zh_HK", 2);
        this.mLocaleMap.put("zh_TW", 2);
        this.mLocaleMap.put("el", 3);
        this.mLocaleMap.put(Localization.language, 4);
        this.mLocaleMap.put("de", 5);
        this.mLocaleMap.put("it", 6);
        this.mLocaleMap.put("es", 7);
        this.mLocaleMap.put("nl", 8);
        this.mLocaleMap.put("pt", 9);
        this.mLocaleMap.put("ru", 10);
        this.mLocaleMap.put("tr", 11);
        this.mLocaleMap.put("ja", 12);
        this.mLocaleMap.put("ko", 13);
        this.mLocaleMap.put("pl", 14);
        this.mLocaleMap.put("ro", 15);
        this.mLocaleMap.put("ar", 16);
        this.mLocaleMap.put("th", 17);
        this.mLocaleMap.put("vi", 18);
        this.mLocaleMap.put("in", 19);
        this.mLocaleMap.put("hi", 20);
        this.mLocaleMap.put("cs", 21);
        this.mLocaleMap.put("sk", 22);
        this.mLocaleMap.put("hu", 23);
        this.mLocaleMap.put("iw", 24);
        this.mLocaleMap.put("hr", 25);
        this.mLocaleMap.put("sl", 26);
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        this.mData[6] = this.mLanguage;
        XLog.i(Float.valueOf(getTimeZone()));
        byte[] bArr = this.mData;
        bArr[7] = (byte) ((((r0 + 24.0f) % 24.0f) * 2.0f) + 1.0f);
        return bArr;
    }

    public static float getTimeZone() {
        return (TimeZone.getDefault().getOffset(System.currentTimeMillis()) * 1.0f) / 3600000.0f;
    }

    public void setLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language.startsWith("zh")) {
            language = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry().toUpperCase();
        }
        Integer num = this.mLocaleMap.get(language);
        int iIntValue = num == null ? 1 : num.intValue();
        XLog.i("SetTimeReq -> mLanguage: " + language + ", value: " + num + ", result: " + iIntValue);
        this.mLanguage = (byte) iIntValue;
    }
}
