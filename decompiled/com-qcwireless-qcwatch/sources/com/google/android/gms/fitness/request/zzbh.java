package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbh> CREATOR = new zzbi();
    private final DataType zza;
    private final DataSource zzb;
    private final zzcp zzc;

    public zzbh(DataType dataType, DataSource dataSource, zzcp zzcpVar) {
        Preconditions.checkArgument((dataType == null) != (dataSource == null), "Must specify exactly one of dataType and dataSource.");
        this.zza = dataType;
        this.zzb = dataSource;
        this.zzc = zzcpVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbh)) {
            return false;
        }
        zzbh zzbhVar = (zzbh) obj;
        return Objects.equal(this.zzb, zzbhVar.zzb) && Objects.equal(this.zza, zzbhVar.zza);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb, this.zza);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        zzcp zzcpVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
