package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzi implements zzk<List<AccountChangeEvent>> {
    final /* synthetic */ AccountChangeEventsRequest zza;

    zzi(AccountChangeEventsRequest accountChangeEventsRequest) {
        this.zza = accountChangeEventsRequest;
    }

    @Override // com.google.android.gms.auth.zzk
    public final /* bridge */ /* synthetic */ List<AccountChangeEvent> zza(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        AccountChangeEventsResponse accountChangeEventsResponseZzh = com.google.android.gms.internal.auth.zze.zzb(iBinder).zzh(this.zza);
        zzl.zzd(accountChangeEventsResponseZzh);
        return accountChangeEventsResponseZzh.getEvents();
    }
}
