package com.baidu.location.h.b;

import com.baidu.location.h.b.a;

/* loaded from: classes.dex */
public class b {
    private static int a = -1;
    private static int b = -1;
    private static int c = -1;
    private static int d = -1;
    private static int e = -1;
    private static int f = -1;

    private static class a {
        private static final b a = new b(null);
    }

    /* renamed from: com.baidu.location.h.b.b$b, reason: collision with other inner class name */
    public enum EnumC0024b {
        SUBWAY_STATIC,
        TRAFFIC_STATUS,
        VDR_INDOOR_SPEED_STATUS,
        INDOOR_POI_DATA_STATUS,
        OUTDOOR_POI_DATA_STATUS,
        GPS_CHECKER_STATUS
    }

    private b() {
    }

    /* synthetic */ b(c cVar) {
        this();
    }

    public static b a() {
        return a.a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public String a(EnumC0024b enumC0024b) {
        switch (d.a[enumC0024b.ordinal()]) {
            case 1:
                if (a == 0) {
                    return a.e.b;
                }
                return null;
            case 2:
                if (b == 0) {
                    return a.f.b;
                }
                return null;
            case 3:
                if (c == 0) {
                    return a.c.b;
                }
                return null;
            case 4:
                if (d == 0) {
                    return a.b.a;
                }
                return null;
            case 5:
                if (e == 0) {
                    return a.d.a;
                }
                return null;
            case 6:
                if (f == 0) {
                    return a.C0023a.a;
                }
                return null;
            default:
                return null;
        }
    }
}
