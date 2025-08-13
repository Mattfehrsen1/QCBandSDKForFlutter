package com.baidu.location.e;

import com.baidu.location.BDLocation;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
class j implements Callable<BDLocation> {
    final /* synthetic */ String[] a;
    final /* synthetic */ i b;

    j(i iVar, String[] strArr) {
        this.b = iVar;
        this.a = strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008c A[EXC_TOP_SPLITTER, PHI: r0 r2
      0x008c: PHI (r0v10 android.database.Cursor) = (r0v6 android.database.Cursor), (r0v9 android.database.Cursor), (r0v15 android.database.Cursor) binds: [B:26:0x005e, B:16:0x004e, B:42:0x008a] A[DONT_GENERATE, DONT_INLINE]
      0x008c: PHI (r2v6 com.baidu.location.BDLocation) = (r2v13 com.baidu.location.BDLocation), (r2v5 com.baidu.location.BDLocation), (r2v15 com.baidu.location.BDLocation) binds: [B:26:0x005e, B:16:0x004e, B:42:0x008a] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.baidu.location.BDLocation call() throws java.lang.Throwable {
        /*
            r11 = this;
            com.baidu.location.BDLocation r0 = new com.baidu.location.BDLocation
            r0.<init>()
            java.lang.String[] r1 = r11.a
            int r1 = r1.length
            if (r1 <= 0) goto L9f
            com.baidu.location.e.i r0 = r11.b
            com.baidu.location.e.f r0 = com.baidu.location.e.i.a(r0)
            java.lang.String[] r0 = r0.o()
            r1 = 0
            r2 = 0
            r4 = r2
            r3 = 0
        L18:
            int r5 = r0.length
            if (r3 >= r5) goto L31
            android.content.Context r4 = com.baidu.location.e.i.p()     // Catch: java.lang.Exception -> L2a
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch: java.lang.Exception -> L2a
            r5 = r0[r3]     // Catch: java.lang.Exception -> L2a
            android.content.pm.ProviderInfo r4 = r4.resolveContentProvider(r5, r1)     // Catch: java.lang.Exception -> L2a
            goto L2b
        L2a:
            r4 = r2
        L2b:
            if (r4 == 0) goto L2e
            goto L31
        L2e:
            int r3 = r3 + 1
            goto L18
        L31:
            if (r4 == 0) goto L61
            android.content.Context r0 = com.baidu.location.e.i.p()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5d
            android.content.ContentResolver r5 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5d
            java.lang.String r0 = r4.authority     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5d
            android.net.Uri r6 = com.baidu.location.e.i.b(r0)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5d
            java.lang.String[] r7 = r11.a     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5d
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r0 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5d
            com.baidu.location.BDLocation r2 = com.baidu.location.e.k.a(r0)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L54
            if (r0 == 0) goto L8f
        L50:
            goto L8c
        L51:
            r1 = move-exception
            r2 = r0
            goto L57
        L54:
            goto L5e
        L56:
            r1 = move-exception
        L57:
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.lang.Exception -> L5c
        L5c:
            throw r1
        L5d:
            r0 = r2
        L5e:
            if (r0 == 0) goto L8f
            goto L50
        L61:
            com.baidu.location.e.k$a r0 = new com.baidu.location.e.k$a
            java.lang.String[] r1 = r11.a
            r0.<init>(r1)
            com.baidu.location.e.i r1 = r11.b     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            com.baidu.location.e.c r1 = com.baidu.location.e.i.b(r1)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            android.database.Cursor r0 = r1.a(r0)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            com.baidu.location.BDLocation r1 = com.baidu.location.e.k.a(r0)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            if (r0 == 0) goto L7b
            r0.close()     // Catch: java.lang.Exception -> L7b
        L7b:
            r0 = r1
            goto L90
        L7d:
            r1 = move-exception
            r2 = r0
            goto L83
        L80:
            goto L8a
        L82:
            r1 = move-exception
        L83:
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.lang.Exception -> L88
        L88:
            throw r1
        L89:
            r0 = r2
        L8a:
            if (r0 == 0) goto L8f
        L8c:
            r0.close()     // Catch: java.lang.Exception -> L8f
        L8f:
            r0 = r2
        L90:
            if (r0 == 0) goto L9f
            int r1 = r0.getLocType()
            r2 = 67
            if (r1 == r2) goto L9f
            r1 = 66
            r0.setLocType(r1)
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.e.j.call():com.baidu.location.BDLocation");
    }
}
