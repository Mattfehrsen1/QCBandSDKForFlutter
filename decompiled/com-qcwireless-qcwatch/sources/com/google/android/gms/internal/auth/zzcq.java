package com.google.android.gms.internal.auth;

import android.net.Uri;
import androidx.collection.ArrayMap;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzcq {
    private static final ArrayMap<String, Uri> zza = new ArrayMap<>();

    public static synchronized Uri zza(String str) {
        Uri uri;
        ArrayMap<String, Uri> arrayMap = zza;
        uri = arrayMap.get("com.google.android.gms.auth_account");
        if (uri == null) {
            String strValueOf = String.valueOf(Uri.encode("com.google.android.gms.auth_account"));
            uri = Uri.parse(strValueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(strValueOf) : new String("content://com.google.android.gms.phenotype/"));
            arrayMap.put("com.google.android.gms.auth_account", uri);
        }
        return uri;
    }
}
