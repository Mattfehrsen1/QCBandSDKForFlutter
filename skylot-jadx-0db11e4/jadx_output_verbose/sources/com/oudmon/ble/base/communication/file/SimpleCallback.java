package com.oudmon.ble.base.communication.file;

import android.util.Log;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureEntity;
import com.oudmon.ble.base.communication.dfu_temperature.TemperatureOnceEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/file/SimpleCallback.class */
public class SimpleCallback implements ICallback {
    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onRequestAGPS() {
        Log.i(ICallback.TAG, "onRequestAGPS..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdatePlate(List<PlateEntity> array) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdatePlateError(int code) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onDeletePlate() {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onDeletePlateError(int code) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdateTemperature(TemperatureEntity data) {
        Log.i(ICallback.TAG, "onUpdateTemperature..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onUpdateTemperatureList(List<TemperatureOnceEntity> array) {
        Log.i(ICallback.TAG, "onUpdateTemperatureList..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onFileNames(ArrayList<String> fileNames) {
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onProgress(int percent) {
        Log.i(ICallback.TAG, "onProgress..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onComplete() {
        Log.i(ICallback.TAG, "onComplete..");
    }

    @Override // com.oudmon.ble.base.communication.file.ICallback
    public void onActionResult(int type, int errCode) {
        Log.i(ICallback.TAG, "onActionResult..");
    }
}
