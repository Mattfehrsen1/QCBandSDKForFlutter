package com.baidu.location.c;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* loaded from: classes.dex */
public class d {
    private static d d;
    private boolean a = false;
    private String b = null;
    private b c = null;
    private int e = -1;
    private a f;

    public interface a {
        void a(boolean z);
    }

    public class b extends BroadcastReceiver {
        public b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0071 A[Catch: Exception -> 0x0091, TryCatch #0 {Exception -> 0x0091, blocks: (B:3:0x0005, B:5:0x000d, B:8:0x0034, B:16:0x004b, B:27:0x0079, B:29:0x0081, B:24:0x0064, B:25:0x006b, B:26:0x0071, B:17:0x0051, B:18:0x0055, B:19:0x0059, B:9:0x003d), top: B:33:0x0005 }] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0081 A[Catch: Exception -> 0x0091, TRY_LEAVE, TryCatch #0 {Exception -> 0x0091, blocks: (B:3:0x0005, B:5:0x000d, B:8:0x0034, B:16:0x004b, B:27:0x0079, B:29:0x0081, B:24:0x0064, B:25:0x006b, B:26:0x0071, B:17:0x0051, B:18:0x0055, B:19:0x0059, B:9:0x003d), top: B:33:0x0005 }] */
        /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onReceive(android.content.Context r6, android.content.Intent r7) {
            /*
                r5 = this;
                java.lang.String r6 = r7.getAction()
                r0 = 0
                java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
                boolean r6 = r6.equals(r1)     // Catch: java.lang.Exception -> L91
                if (r6 == 0) goto L96
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                r1 = 0
                com.baidu.location.c.d.a(r6, r1)     // Catch: java.lang.Exception -> L91
                java.lang.String r6 = "status"
                int r6 = r7.getIntExtra(r6, r1)     // Catch: java.lang.Exception -> L91
                java.lang.String r2 = "plugged"
                int r1 = r7.getIntExtra(r2, r1)     // Catch: java.lang.Exception -> L91
                java.lang.String r2 = "level"
                r3 = -1
                int r2 = r7.getIntExtra(r2, r3)     // Catch: java.lang.Exception -> L91
                java.lang.String r4 = "scale"
                int r7 = r7.getIntExtra(r4, r3)     // Catch: java.lang.Exception -> L91
                if (r2 <= 0) goto L3d
                if (r7 <= 0) goto L3d
                com.baidu.location.c.d r3 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                int r2 = r2 * 100
                int r2 = r2 / r7
                com.baidu.location.c.d.a(r3, r2)     // Catch: java.lang.Exception -> L91
                goto L42
            L3d:
                com.baidu.location.c.d r7 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                com.baidu.location.c.d.a(r7, r3)     // Catch: java.lang.Exception -> L91
            L42:
                r7 = 2
                if (r6 == r7) goto L59
                r2 = 3
                if (r6 == r2) goto L51
                r2 = 4
                if (r6 == r2) goto L51
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                com.baidu.location.c.d.a(r6, r0)     // Catch: java.lang.Exception -> L91
                goto L5e
            L51:
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                java.lang.String r2 = "3"
            L55:
                com.baidu.location.c.d.a(r6, r2)     // Catch: java.lang.Exception -> L91
                goto L5e
            L59:
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                java.lang.String r2 = "4"
                goto L55
            L5e:
                r6 = 1
                if (r1 == r6) goto L71
                if (r1 == r7) goto L64
                goto L79
            L64:
                com.baidu.location.c.d r7 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                java.lang.String r1 = "5"
                com.baidu.location.c.d.a(r7, r1)     // Catch: java.lang.Exception -> L91
            L6b:
                com.baidu.location.c.d r7 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                com.baidu.location.c.d.a(r7, r6)     // Catch: java.lang.Exception -> L91
                goto L79
            L71:
                com.baidu.location.c.d r7 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                java.lang.String r1 = "6"
                com.baidu.location.c.d.a(r7, r1)     // Catch: java.lang.Exception -> L91
                goto L6b
            L79:
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                com.baidu.location.c.d$a r6 = com.baidu.location.c.d.a(r6)     // Catch: java.lang.Exception -> L91
                if (r6 == 0) goto L96
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                com.baidu.location.c.d$a r6 = com.baidu.location.c.d.a(r6)     // Catch: java.lang.Exception -> L91
                com.baidu.location.c.d r7 = com.baidu.location.c.d.this     // Catch: java.lang.Exception -> L91
                boolean r7 = com.baidu.location.c.d.b(r7)     // Catch: java.lang.Exception -> L91
                r6.a(r7)     // Catch: java.lang.Exception -> L91
                goto L96
            L91:
                com.baidu.location.c.d r6 = com.baidu.location.c.d.this
                com.baidu.location.c.d.a(r6, r0)
            L96:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.c.d.b.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    private d() {
    }

    public static synchronized d a() {
        if (d == null) {
            d = new d();
        }
        return d;
    }

    public void b() {
        this.c = new b();
        try {
            com.baidu.location.f.getServiceContext().registerReceiver(this.c, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (Exception unused) {
        }
    }

    public void c() {
        if (this.c != null) {
            try {
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.c);
            } catch (Exception unused) {
            }
        }
        this.c = null;
        this.f = null;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.a;
    }

    public int f() {
        return this.e;
    }
}
