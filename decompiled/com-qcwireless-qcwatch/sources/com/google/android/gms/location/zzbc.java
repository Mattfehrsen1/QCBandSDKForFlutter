package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes2.dex */
public final class zzbc {
    public static int zza(int i) {
        boolean z;
        if (i == 0 || i == 1) {
            z = true;
        } else if (i == 2) {
            i = 2;
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "granularity %d must be a Granularity.GRANULARITY_* constants", Integer.valueOf(i));
        return i;
    }

    public static String zzb(int i) {
        if (i == 0) {
            return "GRANULARITY_PERMISSION_LEVEL";
        }
        if (i == 1) {
            return "GRANULARITY_COARSE";
        }
        if (i == 2) {
            return "GRANULARITY_FINE";
        }
        throw new IllegalArgumentException();
    }
}
