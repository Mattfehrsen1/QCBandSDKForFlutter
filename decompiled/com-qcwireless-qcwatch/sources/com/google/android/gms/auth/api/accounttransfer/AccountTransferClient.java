package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.auth.zzap;
import com.google.android.gms.internal.auth.zzaq;
import com.google.android.gms.internal.auth.zzav;
import com.google.android.gms.internal.auth.zzax;
import com.google.android.gms.internal.auth.zzaz;
import com.google.android.gms.internal.auth.zzbb;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public class AccountTransferClient extends GoogleApi<zzq> {
    public static final /* synthetic */ int zza = 0;
    private static final Api.ClientKey<zzap> zzb;
    private static final Api.AbstractClientBuilder<zzap, zzq> zzc;
    private static final Api<zzq> zzd;

    static {
        Api.ClientKey<zzap> clientKey = new Api.ClientKey<>();
        zzb = clientKey;
        zzb zzbVar = new zzb();
        zzc = zzbVar;
        zzd = new Api<>("AccountTransfer.ACCOUNT_TRANSFER_API", zzbVar, clientKey);
    }

    AccountTransferClient(Activity activity, zzq zzqVar) {
        super(activity, zzd, zzq.zza, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    public Task<DeviceMetaData> getDeviceMetaData(String str) {
        Preconditions.checkNotNull(str);
        return doRead(new zzg(this, 1608, new zzaq(str)));
    }

    public Task<Void> notifyCompletion(String str, int i) {
        Preconditions.checkNotNull(str);
        return doWrite(new zzi(this, 1610, new zzav(str, i)));
    }

    public Task<byte[]> retrieveData(String str) {
        Preconditions.checkNotNull(str);
        return doRead(new zze(this, 1607, new zzax(str)));
    }

    public Task<Void> sendData(String str, byte[] bArr) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(bArr);
        return doWrite(new zzc(this, 1606, new zzaz(str, bArr)));
    }

    public Task<Void> showUserChallenge(String str, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(pendingIntent);
        return doWrite(new zzh(this, 1609, new zzbb(str, pendingIntent)));
    }

    AccountTransferClient(Context context, zzq zzqVar) {
        super(context, zzd, zzq.zza, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }
}
