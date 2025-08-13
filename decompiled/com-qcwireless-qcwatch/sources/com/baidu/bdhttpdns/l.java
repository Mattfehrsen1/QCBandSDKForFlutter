package com.baidu.bdhttpdns;

import android.util.Log;

/* loaded from: classes.dex */
final class l {
    private static boolean a = false;

    static void a(String str, Object... objArr) {
        if (a) {
            Log.v("BDHttpDns", String.format(str, objArr));
        }
    }

    static void a(boolean z) {
        a = z;
    }
}
