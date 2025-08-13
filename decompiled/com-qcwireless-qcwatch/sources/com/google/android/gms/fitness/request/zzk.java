package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();
    private final DataSet zza;
    private final zzcp zzb;
    private final boolean zzc;

    zzk(DataSet dataSet, IBinder iBinder, boolean z) {
        this.zza = dataSet;
        this.zzb = iBinder == null ? null : zzco.zzb(iBinder);
        this.zzc = z;
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof zzk) && Objects.equal(this.zza, ((zzk) obj).zza));
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("dataSet", this.zza).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        zzcp zzcpVar = this.zzb;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzk(DataSet dataSet, zzcp zzcpVar, boolean z) {
        this.zza = dataSet;
        this.zzb = zzcpVar;
        this.zzc = false;
    }
}
