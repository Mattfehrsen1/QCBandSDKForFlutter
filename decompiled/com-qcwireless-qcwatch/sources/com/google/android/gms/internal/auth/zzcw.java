package com.google.android.gms.internal.auth;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzcw extends zzcz {
    final /* synthetic */ zzhl zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcw(zzcx zzcxVar, String str, Object obj, boolean z, zzhl zzhlVar, byte[] bArr) {
        super(zzcxVar, "getTokenRefactor__blocked_packages", obj, true, null);
        this.zza = zzhlVar;
    }

    @Override // com.google.android.gms.internal.auth.zzcz
    final Object zza(Object obj) {
        try {
            return zzhi.zzl(Base64.decode((String) obj, 3));
        } catch (IOException | IllegalArgumentException unused) {
            String strZzc = super.zzc();
            String str = (String) obj;
            StringBuilder sb = new StringBuilder(String.valueOf(strZzc).length() + 27 + str.length());
            sb.append("Invalid byte[] value for ");
            sb.append(strZzc);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
