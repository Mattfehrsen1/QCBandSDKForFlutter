package com.oudmon.ble.base.communication.file;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public interface IEbookCallback {
    public static final String TAG = "IEbookCallback";

    void onActionResult(int i);

    void onComplete();

    void onDeleteSuccess(int i);

    void onFileNames(ArrayList<String> arrayList);

    void onProgress(float f);
}
