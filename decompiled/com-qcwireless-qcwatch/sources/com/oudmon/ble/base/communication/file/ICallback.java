package com.oudmon.ble.base.communication.file;

import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public interface ICallback {
    public static final String TAG = "ICallback";

    void onActionResult(int i, int i2);

    void onComplete();

    void onDeletePlate();

    void onDeletePlateError(int i);

    void onFileNames(ArrayList<String> arrayList);

    void onProgress(int i);

    void onRequestAGPS();

    void onUpdatePlate(List<PlateEntity> list);

    void onUpdatePlateError(int i);

    void onUpdateTemperature(TemperatureEntity temperatureEntity);

    void onUpdateTemperatureList(List<TemperatureOnceEntity> list);
}
