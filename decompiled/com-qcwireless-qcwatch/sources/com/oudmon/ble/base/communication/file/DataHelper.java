package com.oudmon.ble.base.communication.file;

import android.util.Log;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import com.qcwireless.qc_utils.date.DateUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class DataHelper {
    private static final String TAG = "DataHelper";
    private static DataHelper mInstance;

    public static DataHelper getInstance() {
        if (mInstance == null) {
            synchronized (DataHelper.class) {
                if (mInstance == null) {
                    mInstance = new DataHelper();
                }
            }
        }
        return mInstance;
    }

    static List<PlateEntity> parsePlate(byte[] bArr) {
        Log.i(TAG, "=========================== Parse Plate Start ============================");
        ArrayList arrayList = new ArrayList();
        Log.i(TAG, "length: " + ((int) bArr[0]));
        int i = 1;
        while (i < bArr.length) {
            try {
                int i2 = i + 1;
                int i3 = bArr[i] & 255;
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                byte[] bArr2 = new byte[i5];
                System.arraycopy(bArr, i4, bArr2, 0, i5);
                int i6 = i5 + i4;
                arrayList.add(new PlateEntity(i3 == 1, new String(bArr2)));
                i = i6;
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "=========================== Parse Plate Error ============================");
            }
        }
        Log.i(TAG, "=========================== Parse Plate End ============================" + arrayList.size());
        return arrayList;
    }

    static TemperatureEntity parseTemperature(byte[] bArr) {
        Log.i(TAG, "=========================== ParseTemperature Start ============================");
        TemperatureEntity temperatureEntity = new TemperatureEntity();
        try {
            temperatureEntity.mIndex = bArr[0];
            int i = 1;
            temperatureEntity.mTimeSpan = bArr[1];
            if (bArr[1] != 0) {
                i = 1440 / temperatureEntity.mTimeSpan;
            }
            temperatureEntity.mValues = new float[i];
            int i2 = 0;
            for (int i3 = 2; i3 < bArr.length; i3++) {
                int i4 = bArr[i3] & 255;
                if (i4 > 128) {
                    int i5 = i4 - 128;
                    int i6 = 0;
                    while (i6 < i5) {
                        temperatureEntity.mValues[i2] = 0.0f;
                        i6++;
                        i2++;
                    }
                } else {
                    temperatureEntity.mValues[i2] = ((bArr[i3] * 1.0f) / 10.0f) + 32.0f;
                    i2++;
                }
            }
            Log.i(TAG, "temperature: " + temperatureEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "=========================== ParseTemperature End ============================");
        return temperatureEntity;
    }

    static List<TemperatureOnceEntity> parseTemperatureOnce(byte[] bArr) {
        Log.i(TAG, "=========================== ParseTemperatureOnce Start ============================");
        ArrayList arrayList = new ArrayList();
        try {
            DateUtil dateUtil = new DateUtil();
            dateUtil.addDay(-bArr[0]);
            int i = 1;
            while (i < bArr.length) {
                TemperatureOnceEntity temperatureOnceEntity = new TemperatureOnceEntity();
                temperatureOnceEntity.mTime = dateUtil.getZeroTime() + (DataTransferUtils.bytesToShort(bArr, i) * 60);
                int i2 = i + 2 + 1;
                temperatureOnceEntity.mValue = ((bArr[r2] * 1.0f) / 10.0f) + 32.0f;
                arrayList.add(temperatureOnceEntity);
                i = i2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        XLog.i("=========================== ParseTemperatureOnce End ============================");
        return arrayList;
    }
}
