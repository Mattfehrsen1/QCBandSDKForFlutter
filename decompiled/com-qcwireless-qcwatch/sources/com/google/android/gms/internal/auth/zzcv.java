package com.google.android.gms.internal.auth;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzcv extends zzcz<Double> {
    zzcv(zzcx zzcxVar, String str, Double d, boolean z) {
        super(zzcxVar, str, d, true, null);
    }

    @Override // com.google.android.gms.internal.auth.zzcz
    final /* bridge */ /* synthetic */ Double zza(Object obj) {
        try {
            return Double.valueOf(Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            String strZzc = super.zzc();
            String str = (String) obj;
            StringBuilder sb = new StringBuilder(String.valueOf(strZzc).length() + 27 + str.length());
            sb.append("Invalid double value for ");
            sb.append(strZzc);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
