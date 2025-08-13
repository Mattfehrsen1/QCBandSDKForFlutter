package com.oudmon.ble.base.communication.file;

import android.util.Log;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import com.oudmon.ble.base.util.DataTransferUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/file/DataHelper.class */
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

    static List<PlateEntity> parsePlate(byte[] data) {
        Log.i(TAG, "=========================== Parse Plate Start ============================");
        List<PlateEntity> mPlateArray = new ArrayList<>();
        Log.i(TAG, "length: " + ((int) data[0]));
        int index = 1;
        while (index < data.length) {
            try {
                int i = index;
                int index2 = index + 1;
                int delete = data[i] & 255;
                int index3 = index2 + 1;
                int nameLength = data[index2] & 255;
                byte[] byteName = new byte[nameLength];
                System.arraycopy(data, index3, byteName, 0, nameLength);
                index = index3 + nameLength;
                String name = new String(byteName);
                mPlateArray.add(new PlateEntity(delete == 1, name));
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "=========================== Parse Plate Error ============================");
            }
        }
        Log.i(TAG, "=========================== Parse Plate End ============================" + mPlateArray.size());
        return mPlateArray;
    }

    static TemperatureEntity parseTemperature(byte[] data) {
        Log.i(TAG, "=========================== ParseTemperature Start ============================");
        TemperatureEntity temperature = new TemperatureEntity();
        try {
            temperature.mIndex = data[0];
            temperature.mTimeSpan = data[1];
            temperature.mValues = new float[data[1] == 0 ? 1 : 1440 / temperature.mTimeSpan];
            int i = 0;
            for (int index = 2; index < data.length; index++) {
                int temp = data[index] & 255;
                float val = (temp * 1.0f) / 10.0f;
                int i2 = i;
                i++;
                temperature.mValues[i2] = val + 20.0f;
            }
            Log.i(TAG, "temperature: " + temperature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "=========================== ParseTemperature End ============================");
        return temperature;
    }

    static List<TemperatureOnceEntity> parseTemperatureOnce(byte[] data) {
        Log.i(TAG, "=========================== ParseTemperatureOnce Start ============================");
        List<TemperatureOnceEntity> mArrays = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, 0);
            calendar.set(13, 0);
            calendar.set(12, 0);
            calendar.set(14, 0);
            int index = 1;
            while (index < data.length) {
                TemperatureOnceEntity temperature = new TemperatureOnceEntity();
                temperature.mTime = calendar.getTimeInMillis() + (DataTransferUtils.bytesToShort(data, index) * 60000);
                index = index + 2 + 1;
                float val = (data[r9] * 1.0f) / 10.0f;
                temperature.mValue = val + 20.0f;
                Log.i(TAG, "mTime: " + temperature.mTime + ", value: " + temperature.mValue);
                mArrays.add(temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "=========================== ParseTemperatureOnce End ============================");
        Log.i(TAG, "mArrays: " + mArrays);
        return mArrays;
    }
}
