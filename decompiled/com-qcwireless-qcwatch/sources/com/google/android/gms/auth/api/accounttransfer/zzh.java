package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzbb;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzh extends zzm {
    final /* synthetic */ zzbb zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzh(AccountTransferClient accountTransferClient, int i, zzbb zzbbVar) {
        super(1609);
        this.zza = zzbbVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzk
    protected final void zza(zzau zzauVar) throws RemoteException {
        zzauVar.zze(this.zzc, this.zza);
    }
}
