package com.baidu.bdhttpdns;

import android.content.BroadcastReceiver;
import android.content.Context;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class BDNetworkStateChangeReceiver extends BroadcastReceiver {
    private static boolean f = true;
    private static boolean g = true;
    private boolean b = false;
    private boolean c = true;
    private boolean d = true;
    private String e = "";
    ExecutorService a = Executors.newFixedThreadPool(1);

    class a implements Callable<Object> {
        a() {
        }

        /* JADX WARN: Can't wrap try/catch for region: R(13:0|2|(2:35|3)|(2:37|4)|(2:27|9)|31|13|29|14|(2:33|19)|23|24|(1:(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        
            r5 = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
        
            r0 = com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.f = false;
            r1 = r5;
         */
        /* JADX WARN: Removed duplicated region for block: B:27:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.util.concurrent.Callable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object call() throws java.net.SocketException {
            /*
                r6 = this;
                java.net.InetSocketAddress r0 = new java.net.InetSocketAddress
                java.lang.String r1 = "2001:4860:4860::8888"
                r2 = 443(0x1bb, float:6.21E-43)
                r0.<init>(r1, r2)
                java.net.InetSocketAddress r1 = new java.net.InetSocketAddress
                java.lang.String r2 = "180.76.76.76"
                r3 = 80
                r1.<init>(r2, r3)
                r2 = 0
                r3 = 1
                r4 = 0
                java.net.DatagramSocket r5 = new java.net.DatagramSocket     // Catch: java.net.SocketException -> L21
                r5.<init>()     // Catch: java.net.SocketException -> L21
                r5.connect(r1)     // Catch: java.net.SocketException -> L22
                com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.c(r3)     // Catch: java.net.SocketException -> L22
                goto L25
            L21:
                r5 = r2
            L22:
                com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.c(r4)
            L25:
                if (r5 == 0) goto L2f
                r5.close()     // Catch: java.lang.Exception -> L2b
                goto L2f
            L2b:
                r1 = move-exception
                r1.printStackTrace()
            L2f:
                java.net.DatagramSocket r1 = new java.net.DatagramSocket     // Catch: java.net.SocketException -> L3c
                r1.<init>()     // Catch: java.net.SocketException -> L3c
                r1.connect(r0)     // Catch: java.net.SocketException -> L3b
                com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.d(r3)     // Catch: java.net.SocketException -> L3b
                goto L40
            L3b:
                r5 = r1
            L3c:
                com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.d(r4)
                r1 = r5
            L40:
                if (r1 == 0) goto L4a
                r1.close()     // Catch: java.lang.Exception -> L46
                goto L4a
            L46:
                r0 = move-exception
                r0.printStackTrace()
            L4a:
                r0 = 2
                java.lang.Object[] r0 = new java.lang.Object[r0]
                boolean r1 = com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.a()
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                r0[r4] = r1
                boolean r1 = com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.b()
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                r0[r3] = r1
                java.lang.String r1 = "isIPv4Reachable(%s), isIPv6Reachable(%s)"
                com.baidu.bdhttpdns.l.a(r1, r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.a.call():java.lang.Object");
        }
    }

    private void a(Context context) {
        l.a("Network change, clearCache(%b) httpDnsPrefetch(%b)", Boolean.valueOf(this.c), Boolean.valueOf(this.d));
        i iVarA = i.a();
        iVarA.b();
        BDHttpDns service = BDHttpDns.getService(context);
        refreshIpReachable();
        ArrayList<String> arrayListB = service.a().b();
        if (this.c) {
            service.a().a();
            service.b().a();
        }
        if (!this.d || arrayListB == null || arrayListB.isEmpty()) {
            return;
        }
        iVarA.a(arrayListB, new k(context));
    }

    public static boolean isIPv4Reachable() {
        return g;
    }

    public static boolean isIPv6Reachable() {
        return f;
    }

    void a(boolean z) {
        this.c = z;
    }

    void b(boolean z) {
        this.d = z;
    }

    public boolean isIPv6Only() {
        return !g && f;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:10|(1:(6:21|41|22|(1:25)|36|37)(1:19))(1:14)|15|41|22|(1:25)|36|37) */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
    
        r6 = r0;
        r0 = r9;
        r9 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0061, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0064, code lost:
    
        a(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
    
        r8.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006c, code lost:
    
        r0 = r9;
     */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            java.lang.String r9 = ""
            boolean r0 = r7.b
            r1 = 1
            if (r0 != 0) goto La
            r7.b = r1
            return
        La:
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r8.getSystemService(r0)     // Catch: java.lang.RuntimeException -> L60
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch: java.lang.RuntimeException -> L60
            if (r0 != 0) goto L18
            r7.a(r8)     // Catch: java.lang.RuntimeException -> L60
            return
        L18:
            android.net.NetworkInfo r2 = r0.getNetworkInfo(r1)     // Catch: java.lang.RuntimeException -> L60
            r3 = 0
            android.net.NetworkInfo r0 = r0.getNetworkInfo(r3)     // Catch: java.lang.RuntimeException -> L60
            if (r2 == 0) goto L34
            android.net.NetworkInfo$State r4 = r2.getState()     // Catch: java.lang.RuntimeException -> L60
            android.net.NetworkInfo$State r5 = android.net.NetworkInfo.State.CONNECTED     // Catch: java.lang.RuntimeException -> L60
            if (r4 != r5) goto L34
            java.lang.String r0 = r2.getExtraInfo()     // Catch: java.lang.RuntimeException -> L60
        L2f:
            java.lang.String r0 = r0.toString()     // Catch: java.lang.RuntimeException -> L60
            goto L44
        L34:
            if (r0 == 0) goto L43
            android.net.NetworkInfo$State r2 = r0.getState()     // Catch: java.lang.RuntimeException -> L60
            android.net.NetworkInfo$State r4 = android.net.NetworkInfo.State.CONNECTED     // Catch: java.lang.RuntimeException -> L60
            if (r2 != r4) goto L43
            java.lang.String r0 = r0.getExtraInfo()     // Catch: java.lang.RuntimeException -> L60
            goto L2f
        L43:
            r0 = r9
        L44:
            java.lang.String r2 = r7.e     // Catch: java.lang.RuntimeException -> L5b
            boolean r2 = r2.equals(r0)     // Catch: java.lang.RuntimeException -> L5b
            if (r2 != 0) goto L6d
            if (r0 == r9) goto L6d
            java.lang.String r9 = "Current net type: %s."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.RuntimeException -> L5b
            r1[r3] = r0     // Catch: java.lang.RuntimeException -> L5b
            com.baidu.bdhttpdns.l.a(r9, r1)     // Catch: java.lang.RuntimeException -> L5b
            r7.a(r8)     // Catch: java.lang.RuntimeException -> L5b
            goto L6d
        L5b:
            r9 = move-exception
            r6 = r0
            r0 = r9
            r9 = r6
            goto L61
        L60:
            r0 = move-exception
        L61:
            r0.printStackTrace()
            r7.a(r8)     // Catch: java.lang.Exception -> L68
            goto L6c
        L68:
            r8 = move-exception
            r8.printStackTrace()
        L6c:
            r0 = r9
        L6d:
            r7.e = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.bdhttpdns.BDNetworkStateChangeReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public void refreshIpReachable() {
        this.a.submit(new a());
    }
}
