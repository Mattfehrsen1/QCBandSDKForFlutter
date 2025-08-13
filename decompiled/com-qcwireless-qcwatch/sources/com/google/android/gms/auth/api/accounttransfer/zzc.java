package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzaz;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzc extends zzm {
    final /* synthetic */ zzaz zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzc(AccountTransferClient accountTransferClient, int i, zzaz zzazVar) {
        super(1606);
        this.zza = zzazVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.zzk
    protected final void zza(zzau zzauVar) throws RemoteException {
        zzauVar.zzh(this.zzc, this.zza);
    }
}
