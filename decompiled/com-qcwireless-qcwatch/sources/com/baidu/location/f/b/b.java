package com.baidu.location.f.b;

import android.content.Context;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class b implements a {
    public static int a(Object obj, String str) throws Exception {
        Method declaredMethod = obj.getClass().getDeclaredMethod(str, null);
        if (!declaredMethod.isAccessible()) {
            declaredMethod.setAccessible(true);
        }
        return ((Integer) declaredMethod.invoke(obj, null)).intValue();
    }

    public static String a(Context context) {
        String strB = b(context);
        if (strB == null) {
            return null;
        }
        return strB + "/baidu/tempdata";
    }

    public static boolean a(String str) {
        try {
            return Class.forName(str) != null;
        } catch (Throwable th) {
            if (!c) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(android.content.Context r4) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            r2 = 0
            if (r0 > r1) goto L21
            java.lang.String r0 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L1d
            java.lang.String r3 = "mounted"
            boolean r0 = r0.equals(r3)     // Catch: java.lang.Exception -> L1d
            if (r0 == 0) goto L21
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Exception -> L1d
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.Exception -> L1d
            goto L22
        L1d:
            r0 = move-exception
            r0.printStackTrace()
        L21:
            r0 = r2
        L22:
            if (r0 != 0) goto L36
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 <= r1) goto L36
            if (r4 == 0) goto L36
            java.lang.String r0 = android.os.Environment.DIRECTORY_MOVIES     // Catch: java.lang.Exception -> L35
            java.io.File r4 = r4.getExternalFilesDir(r0)     // Catch: java.lang.Exception -> L35
            java.lang.String r0 = r4.getAbsolutePath()     // Catch: java.lang.Exception -> L35
            goto L36
        L35:
            r0 = r2
        L36:
            if (r0 == 0) goto L5d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L58
            r4.<init>()     // Catch: java.lang.Exception -> L58
            r4.append(r0)     // Catch: java.lang.Exception -> L58
            java.lang.String r1 = "/baidu/tempdata"
            r4.append(r1)     // Catch: java.lang.Exception -> L58
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L58
            java.io.File r1 = new java.io.File     // Catch: java.lang.Exception -> L58
            r1.<init>(r4)     // Catch: java.lang.Exception -> L58
            boolean r4 = r1.exists()     // Catch: java.lang.Exception -> L58
            if (r4 != 0) goto L5d
            r1.mkdirs()     // Catch: java.lang.Exception -> L58
            goto L5d
        L58:
            r4 = move-exception
            r4.printStackTrace()
            goto L5e
        L5d:
            r2 = r0
        L5e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.f.b.b.b(android.content.Context):java.lang.String");
    }
}
