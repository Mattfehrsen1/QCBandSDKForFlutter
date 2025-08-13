package com.google.android.gms.fitness.data;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzai {
    private final double zza;
    private final double zzb;

    /* synthetic */ zzai(double d, double d2, zzah zzahVar) {
        this.zza = d;
        this.zzb = d2;
    }

    public final boolean zza(double d) {
        return d >= this.zza && d <= this.zzb;
    }
}
