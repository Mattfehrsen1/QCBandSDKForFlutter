package com.oudmon.ble.base.communication.rsp;

import android.util.Log;
import com.oudmon.ble.base.communication.entity.BlePressure;
import com.oudmon.ble.base.communication.utils.DataParseUtils;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/* loaded from: classes3.dex */
public class ReadBlePressureRsp extends BaseRspCmd {
    private int mCount = 0;
    private List<BlePressure> mValueList = new ArrayList();
    private Calendar mCalendar = Calendar.getInstance();

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] bArr) {
        Log.i("Jxr35", "ReadBlePressureRsp -> acceptData -> data: " + DataTransferUtils.getHexString(bArr));
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, 4);
        if ("ffffffff".equalsIgnoreCase(DataTransferUtils.getHexString(bArrCopyOfRange))) {
            this.mCount = 0;
            return false;
        }
        this.mCount++;
        long jByteArrayToInt = DataParseUtils.byteArrayToInt(bArrCopyOfRange);
        int timeZone = (int) (getTimeZone() * 3600.0f);
        long j = jByteArrayToInt - timeZone;
        Log.i("Jxr35", "timeStamp: " + j + ", timeOffset: " + timeZone);
        this.mValueList.add(new BlePressure(j, bArr[5] & 255, bArr[4] & 255));
        if (this.mCount < 50) {
            return true;
        }
        this.mCount = 0;
        return false;
    }

    public List<BlePressure> getValueList() {
        return this.mValueList;
    }

    public static float getTimeZone() {
        return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 3600000.0f;
    }
}
