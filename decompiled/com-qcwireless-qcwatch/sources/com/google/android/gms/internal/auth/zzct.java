package com.google.android.gms.internal.auth;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzct extends zzcz<Long> {
    zzct(zzcx zzcxVar, String str, Long l, boolean z) {
        super(zzcxVar, str, l, true, null);
    }

    @Override // com.google.android.gms.internal.auth.zzcz
    final /* bridge */ /* synthetic */ Long zza(Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (NumberFormatException unused) {
            String strZzc = super.zzc();
            String str = (String) obj;
            StringBuilder sb = new StringBuilder(String.valueOf(strZzc).length() + 25 + str.length());
            sb.append("Invalid long value for ");
            sb.append(strZzc);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
