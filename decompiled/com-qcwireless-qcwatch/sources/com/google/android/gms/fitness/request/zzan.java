package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzan> CREATOR = new zzao();
    private final com.google.android.gms.fitness.data.zzv zza;
    private final PendingIntent zzb;
    private final zzcp zzc;

    zzan(IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2) {
        this.zza = iBinder == null ? null : com.google.android.gms.fitness.data.zzu.zzb(iBinder);
        this.zzb = pendingIntent;
        this.zzc = iBinder2 != null ? zzco.zzb(iBinder2) : null;
    }

    public final String toString() {
        return String.format("SensorUnregistrationRequest{%s}", this.zza);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        com.google.android.gms.fitness.data.zzv zzvVar = this.zza;
        SafeParcelWriter.writeIBinder(parcel, 1, zzvVar == null ? null : zzvVar.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        zzcp zzcpVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcpVar != null ? zzcpVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzan(com.google.android.gms.fitness.data.zzv zzvVar, PendingIntent pendingIntent, zzcp zzcpVar) {
        this.zza = zzvVar;
        this.zzb = pendingIntent;
        this.zzc = zzcpVar;
    }
}
