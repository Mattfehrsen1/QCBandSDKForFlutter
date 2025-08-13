package com.zhangke.websocket.util;

import android.content.Context;
import android.os.Build;

/* loaded from: classes4.dex */
public class PermissionUtil {
    public static boolean checkPermission(Context context, String str) {
        return Build.VERSION.SDK_INT < 23 || context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
