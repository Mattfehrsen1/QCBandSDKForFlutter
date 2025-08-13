package com.oudmon.ble.base.communication.file;

import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/file/ICallback.class */
public interface ICallback {
    public static final String TAG = "ICallback";

    void onRequestAGPS();

    void onUpdatePlate(List<PlateEntity> list);

    void onUpdatePlateError(int i);

    void onDeletePlate();

    void onDeletePlateError(int i);

    void onUpdateTemperature(TemperatureEntity temperatureEntity);

    void onUpdateTemperatureList(List<TemperatureOnceEntity> list);

    void onFileNames(ArrayList<String> arrayList);

    void onProgress(int i);

    void onComplete();

    void onActionResult(int i, int i2);
}
