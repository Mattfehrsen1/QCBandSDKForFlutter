package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzby;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzj implements zzk<Boolean> {
    final /* synthetic */ String zza;

    zzj(String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.auth.zzk
    public final /* bridge */ /* synthetic */ Boolean zza(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundleZzg = com.google.android.gms.internal.auth.zze.zzb(iBinder).zzg(this.zza);
        zzl.zzd(bundleZzg);
        String string = bundleZzg.getString("Error");
        Intent intent = (Intent) bundleZzg.getParcelable("userRecoveryIntent");
        zzby zzbyVarZza = zzby.zza(string);
        if (zzby.SUCCESS.equals(zzbyVarZza)) {
            return true;
        }
        if (!zzby.zzb(zzbyVarZza)) {
            throw new GoogleAuthException(string);
        }
        Logger logger = zzl.zzd;
        String strValueOf = String.valueOf(zzbyVarZza);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 31);
        sb.append("isUserRecoverableError status: ");
        sb.append(strValueOf);
        logger.w("GoogleAuthUtil", sb.toString());
        throw new UserRecoverableAuthException(string, intent);
    }
}
