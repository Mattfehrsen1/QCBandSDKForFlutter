package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BlePressure;
import com.oudmon.ble.base.communication.utils.DataParseUtils;
import com.oudmon.ble.base.util.DataTransferUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/ReadBlePressureRsp.class */
public class ReadBlePressureRsp extends BaseRspCmd {
    private int mCount = 0;
    private List<BlePressure> mValueList = new ArrayList();
    private Calendar mCalendar = Calendar.getInstance();

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        Log.i("Jxr35", "ReadBlePressureRsp -> acceptData -> data: " + DataTransferUtils.getHexString(data));
        byte[] time = Arrays.copyOfRange(data, 0, 4);
        if ("ffffffff".equalsIgnoreCase(DataTransferUtils.getHexString(time))) {
            this.mCount = 0;
            return false;
        }
        this.mCount++;
        long timeStamp = DataParseUtils.byteArrayToInt(time);
        long timeOffset = this.mCalendar.get(15) / 1000;
        long timeStamp2 = timeStamp - timeOffset;
        int dbp = data[4] & 255;
        int sbp = data[5] & 255;
        this.mValueList.add(new BlePressure(timeStamp2, sbp, dbp));
        if (this.mCount >= 50) {
            this.mCount = 0;
            return false;
        }
        return true;
    }

    public List<BlePressure> getValueList() {
        return this.mValueList;
    }
}
