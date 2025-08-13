package com.google.android.gms.auth.account;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzg implements PendingResultUtil.ResultConverter<WorkAccountApi.AddAccountResult, Account> {
    zzg(WorkAccountClient workAccountClient) {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
    public final /* bridge */ /* synthetic */ Account convert(Result result) {
        return ((WorkAccountApi.AddAccountResult) result).getAccount();
    }
}
