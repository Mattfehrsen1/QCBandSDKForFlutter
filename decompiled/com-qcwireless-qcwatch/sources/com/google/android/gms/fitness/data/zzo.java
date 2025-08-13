package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzo {
    private static String zza = null;
    private static int zzb = -1;

    public static int zza(Context context) {
        int i = zzb;
        if (i != -1) {
            return i;
        }
        if (DeviceProperties.isWearable(context)) {
            zzb = 3;
            return 3;
        }
        if (DeviceProperties.isTv(context) || DeviceProperties.isAuto(context)) {
            zzb = 0;
            return 0;
        }
        if (DeviceProperties.isTablet(context.getResources()) && !zzc(context)) {
            zzb = 2;
            return 2;
        }
        if (TextUtils.isEmpty(Build.PRODUCT) || !Build.PRODUCT.startsWith("glass_")) {
            zzb = 1;
            return 1;
        }
        zzb = 6;
        return 6;
    }

    public static String zzb(Context context) {
        String str = zza;
        if (str != null) {
            return str;
        }
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        zza = string;
        return string;
    }

    private static boolean zzc(Context context) {
        try {
            return ((TelephonyManager) Preconditions.checkNotNull((TelephonyManager) context.getSystemService("phone"))).getPhoneType() != 0;
        } catch (Resources.NotFoundException e) {
            Log.e("Fitness", "Unable to determine type of device, assuming phone.", e);
            return true;
        }
    }
}
