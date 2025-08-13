package com.oudmon.ble.base.communication.req;

import android.util.Log;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.BLEDataFormatUtils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/SetTimeReq.class */
public class SetTimeReq extends BaseReqCmd {
    private byte mLanguage;
    private byte[] mData;
    private Map<String, Integer> mLocaleMap;

    @Deprecated
    public SetTimeReq() {
        super((byte) 1);
        this.mLanguage = (byte) 0;
        this.mData = new byte[7];
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

    public SetTimeReq(int offset) {
        super((byte) 1);
        this.mLanguage = (byte) 0;
        this.mData = new byte[7];
        this.mLocaleMap = new HashMap();
        initMap();
        setLanguage();
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, offset);
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
        this.mLocaleMap.put("fr", 4);
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
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        this.mData[6] = this.mLanguage;
        return this.mData;
    }

    public void setLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language.startsWith("zh")) {
            language = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
        }
        Integer value = this.mLocaleMap.get(language);
        int result = value == null ? 1 : value.intValue();
        Log.i(Constants.TAG, "SetTimeReq -> mLanguage: " + language + ", value: " + value + ", result: " + result);
        this.mLanguage = (byte) result;
    }
}
