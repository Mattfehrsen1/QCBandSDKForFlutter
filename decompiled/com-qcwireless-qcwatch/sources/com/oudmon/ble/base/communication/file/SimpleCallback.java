package com.oudmon.ble.base.communication.file;

import android.util.Log;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SimpleCallback implements ICallback {
    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onDeletePlate() {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onDeletePlateError(int i) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdatePlate(List<PlateEntity> list) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdatePlateError(int i) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onRequestAGPS() {
        Log.i(ICallback.TAG, "onRequestAGPS..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdateTemperature(TemperatureEntity temperatureEntity) {
        Log.i(ICallback.TAG, "onUpdateTemperature..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdateTemperatureList(List<TemperatureOnceEntity> list) {
        Log.i(ICallback.TAG, "onUpdateTemperatureList..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onFileNames(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        XLog.i(arrayList);
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onProgress(int i) {
        Log.i(ICallback.TAG, "onProgress..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onComplete() {
        Log.i(ICallback.TAG, "onComplete..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onActionResult(int i, int i2) {
        Log.i(ICallback.TAG, "onActionResult..");
    }
}
