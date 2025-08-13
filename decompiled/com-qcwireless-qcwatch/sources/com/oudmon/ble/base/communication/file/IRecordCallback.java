package com.oudmon.ble.base.communication.file;

import com.oudmon.ble.base.communication.entity.RecordEntity;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public interface IRecordCallback {
    public static final String TAG = "RecordEntity";

    void onActionResult(int i);

    void onComplete();

    void onFileNames(ArrayList<RecordEntity> arrayList);

    void onProgress(float f);

    void onReceiver(byte[] bArr);
}
