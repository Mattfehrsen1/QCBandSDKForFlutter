package com.baidu.location.h.a;

import android.os.Handler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class a {
    private final Map<Integer, Object> a = new ConcurrentHashMap();
    private Handler b;

    /* renamed from: com.baidu.location.h.a.a$a, reason: collision with other inner class name */
    private static class C0022a {
        private static a a = new a();
    }

    public static a a() {
        return C0022a.a;
    }

    public void b() {
        this.b = new Handler();
    }
}
