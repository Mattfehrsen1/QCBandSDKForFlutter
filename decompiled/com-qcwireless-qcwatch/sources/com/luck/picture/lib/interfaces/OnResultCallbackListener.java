package com.luck.picture.lib.interfaces;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public interface OnResultCallbackListener<T> {
    void onCancel();

    void onResult(ArrayList<T> arrayList);
}
