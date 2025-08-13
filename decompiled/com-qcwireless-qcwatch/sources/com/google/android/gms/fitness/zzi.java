package com.google.android.gms.fitness;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzi implements Api.ApiOptions.HasGoogleSignInAccountOptions {
    private final GoogleSignInAccount zza;

    public zzi(Context context, GoogleSignInAccount googleSignInAccount) {
        if ("<<default account>>".equals(googleSignInAccount.getEmail()) && PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google")) {
            this.zza = null;
        } else {
            this.zza = googleSignInAccount;
        }
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof zzi) && Objects.equal(((zzi) obj).zza, this.zza));
    }

    @Override // com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
    public final GoogleSignInAccount getGoogleSignInAccount() {
        return this.zza;
    }

    public final int hashCode() {
        GoogleSignInAccount googleSignInAccount = this.zza;
        if (googleSignInAccount != null) {
            return googleSignInAccount.hashCode();
        }
        return 0;
    }
}
