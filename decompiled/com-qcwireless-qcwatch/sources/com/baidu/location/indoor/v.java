package com.baidu.location.indoor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class v {
    private SensorManager c;
    private int d;
    private Sensor e;
    private Sensor f;
    private Set<b> b = Collections.synchronizedSet(new HashSet());
    private boolean g = true;
    private float[] h = new float[3];
    private float[] i = new float[9];
    private float j = -1.0f;
    private int k = 0;
    private String l = null;
    private long m = 0;
    private long n = 3000;
    private ArrayList<Long> o = new ArrayList<>();
    public SensorEventListener a = new w(this);

    private static class a {
        private static v a = new v();
    }

    public interface b {
        void a(double d, double d2, double d3, long j, String str);
    }

    public v() {
        a(com.baidu.location.f.getServiceContext(), 1);
    }

    public static v a() {
        return a.a;
    }

    private void a(Context context, int i) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            this.c = sensorManager;
            this.d = i;
            this.e = sensorManager.getDefaultSensor(1);
            this.f = this.c.getDefaultSensor(11);
            e();
        } catch (Exception unused) {
        }
    }

    static /* synthetic */ int b(v vVar) {
        int i = vVar.k;
        vVar.k = i + 1;
        return i;
    }

    private void e() {
        int iIntValue;
        try {
            List<Sensor> sensorList = this.c.getSensorList(-1);
            HashMap map = new HashMap();
            map.put(1, 0);
            map.put(10, 1);
            map.put(9, 2);
            map.put(4, 3);
            map.put(2, 4);
            map.put(11, 5);
            map.put(6, 6);
            if (Build.VERSION.SDK_INT >= 18) {
                map.put(14, 7);
                map.put(16, 8);
            }
            int size = map.size();
            char[] cArr = new char[size];
            for (int i = 0; i < size; i++) {
                cArr[i] = '0';
            }
            Iterator<Sensor> it = sensorList.iterator();
            while (it.hasNext()) {
                int type = it.next().getType();
                if (map.get(Integer.valueOf(type)) != null && (iIntValue = ((Integer) map.get(Integer.valueOf(type))).intValue()) < size) {
                    cArr[iIntValue] = '1';
                }
            }
            this.l = new String(cArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[Catch: all -> 0x0045, DONT_GENERATE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000b, B:7:0x0010, B:9:0x0019, B:12:0x001e, B:15:0x002a, B:17:0x0030, B:18:0x0033, B:20:0x0037, B:22:0x0041, B:14:0x0028, B:23:0x0043), top: B:28:0x0003, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0037 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.baidu.location.indoor.v.b r6) {
        /*
            r5 = this;
            java.util.Set<com.baidu.location.indoor.v$b> r0 = r5.b
            monitor-enter(r0)
            java.util.Set<com.baidu.location.indoor.v$b> r1 = r5.b     // Catch: java.lang.Throwable -> L45
            boolean r1 = r1.contains(r6)     // Catch: java.lang.Throwable -> L45
            if (r1 != 0) goto L10
            java.util.Set<com.baidu.location.indoor.v$b> r1 = r5.b     // Catch: java.lang.Throwable -> L45
            r1.add(r6)     // Catch: java.lang.Throwable -> L45
        L10:
            java.util.Set<com.baidu.location.indoor.v$b> r6 = r5.b     // Catch: java.lang.Throwable -> L45
            int r6 = r6.size()     // Catch: java.lang.Throwable -> L45
            r1 = 1
            if (r6 != r1) goto L43
            android.hardware.Sensor r6 = r5.e     // Catch: java.lang.Throwable -> L45
            r1 = 0
            if (r6 == 0) goto L33
            android.hardware.SensorManager r2 = r5.c     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L45
            android.hardware.SensorEventListener r3 = r5.a     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L45
            int r4 = r5.d     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L45
            r2.registerListener(r3, r6, r4)     // Catch: java.lang.Exception -> L28 java.lang.Throwable -> L45
            goto L2a
        L28:
            r5.g = r1     // Catch: java.lang.Throwable -> L45
        L2a:
            boolean r6 = com.baidu.location.indoor.mapversion.a.c()     // Catch: java.lang.Throwable -> L45
            if (r6 == 0) goto L33
            com.baidu.location.indoor.mapversion.a.a()     // Catch: java.lang.Throwable -> L45
        L33:
            android.hardware.Sensor r6 = r5.f     // Catch: java.lang.Throwable -> L45
            if (r6 == 0) goto L43
            android.hardware.SensorManager r2 = r5.c     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L45
            android.hardware.SensorEventListener r3 = r5.a     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L45
            int r4 = r5.d     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L45
            r2.registerListener(r3, r6, r4)     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L45
            goto L43
        L41:
            r5.g = r1     // Catch: java.lang.Throwable -> L45
        L43:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            return
        L45:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L45
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.indoor.v.a(com.baidu.location.indoor.v$b):void");
    }

    public synchronized int b() {
        return this.k;
    }

    public void b(b bVar) {
        synchronized (this.b) {
            if (this.b.contains(bVar)) {
                this.b.remove(bVar);
            }
            if (this.b.size() == 0) {
                try {
                    this.c.unregisterListener(this.a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (com.baidu.location.indoor.mapversion.a.c()) {
                    com.baidu.location.indoor.mapversion.a.b();
                }
            }
        }
    }

    public double c() {
        return this.j;
    }

    protected String d() {
        return this.l;
    }
}
