package com.baidu.location.f;

import com.baidu.location.f.k;

/* loaded from: classes.dex */
/* synthetic */ class l {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[k.a.values().length];
        a = iArr;
        try {
            iArr[k.a.ONLY_CELL_MODE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[k.a.ONLY_WIFI_MODE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[k.a.GET_ALL_DATA.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}
