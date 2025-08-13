package com.google.android.gms.internal.auth;

import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public abstract class zzbz extends FastSafeParcelableJsonResponse {
    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final byte[] toByteArray() {
        try {
            return toString().getBytes(Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            Log.e("AUTH", "Error serializing object.", e);
            return null;
        }
    }
}
