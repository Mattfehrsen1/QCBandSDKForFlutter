package com.baidu.location.b;

import android.location.Location;
import android.os.Bundle;
import com.baidu.location.h.b.b;
import com.king.zxing.util.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class n {
    private static String b = "NULL";
    private boolean a = false;
    private long c = -1;
    private int d = -1;

    private static class a {
        private static final n a = new n();
    }

    private int a(String str) {
        return h.a(str);
    }

    public static n a() {
        return a.a;
    }

    private String b(Location location) {
        StringBuilder sb = new StringBuilder();
        sb.append(location.getAccuracy());
        sb.append(LogUtils.VERTICAL);
        sb.append(com.baidu.location.f.e.a);
        sb.append(LogUtils.VERTICAL);
        Bundle extras = location.getExtras();
        if (extras == null || !extras.containsKey("meanCn0")) {
            sb.append(-1);
        } else {
            sb.append(extras.get("meanCn0"));
        }
        sb.append(LogUtils.VERTICAL);
        if (extras == null || !extras.containsKey("SourceType")) {
            sb.append(-1);
        } else {
            sb.append(extras.get("SourceType"));
        }
        sb.append(LogUtils.VERTICAL);
        sb.append(b);
        return sb.toString();
    }

    private void d() {
        String strA = com.baidu.location.h.b.b.a().a(b.EnumC0024b.GPS_CHECKER_STATUS);
        if (strA != null && h.a(strA, e.a().ca) == 0) {
            this.a = true;
        }
    }

    public int a(Location location) {
        if (location == null) {
            return -1;
        }
        this.c = System.currentTimeMillis() / 1000;
        if (!b()) {
            d();
        }
        int iA = b() ? a(b(location)) : -1;
        this.d = iA;
        return iA;
    }

    public void a(ArrayList<ArrayList<Float>> arrayList) {
        String string;
        StringBuilder sb = new StringBuilder();
        if (arrayList.size() == 0) {
            string = "NULL";
        } else {
            Iterator<ArrayList<Float>> it = arrayList.iterator();
            boolean z = true;
            while (it.hasNext()) {
                ArrayList<Float> next = it.next();
                if (next.size() == 6) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(";");
                    }
                    sb.append(String.format("%.1f,", next.get(2)));
                    sb.append(String.format("%.0f", next.get(3)));
                }
            }
            string = sb.toString();
        }
        b = string;
    }

    public boolean b() {
        return this.a;
    }

    public void c() {
        h.b();
        this.a = false;
        b = "";
        this.c = -1L;
        this.d = -1;
    }
}
