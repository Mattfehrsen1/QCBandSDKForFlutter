package com.google.android.gms.internal.auth;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzcu extends zzcz<Boolean> {
    zzcu(zzcx zzcxVar, String str, Boolean bool, boolean z) {
        super(zzcxVar, str, bool, true, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.auth.zzcz
    final /* bridge */ /* synthetic */ Boolean zza(Object obj) {
        if (zzcb.zzc.matcher(obj).matches()) {
            return true;
        }
        if (zzcb.zzd.matcher(obj).matches()) {
            return false;
        }
        String strZzc = super.zzc();
        String str = (String) obj;
        StringBuilder sb = new StringBuilder(String.valueOf(strZzc).length() + 28 + str.length());
        sb.append("Invalid boolean value for ");
        sb.append(strZzc);
        sb.append(": ");
        sb.append(str);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
