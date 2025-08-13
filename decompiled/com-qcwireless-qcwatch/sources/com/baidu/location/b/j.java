package com.baidu.location.b;

import android.os.Bundle;

/* loaded from: classes.dex */
public class j {
    private static Object a = new Object();
    private static j b;
    private int c = -1;

    public static j a() {
        j jVar;
        synchronized (a) {
            if (b == null) {
                b = new j();
            }
            jVar = b;
        }
        return jVar;
    }

    public void a(int i, int i2, String str) {
        if (i2 != this.c) {
            this.c = i2;
            Bundle bundle = new Bundle();
            bundle.putInt("loctype", i);
            bundle.putInt("diagtype", i2);
            bundle.putByteArray("diagmessage", str.getBytes());
            c.a().a(bundle, 303);
        }
    }

    public void b() {
        this.c = -1;
    }
}
