package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class RawDataSet extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RawDataSet> CREATOR = new zzaa();
    public final int zza;
    public final List zzb;

    public RawDataSet(int i, List list) {
        this.zza = i;
        this.zzb = list;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataSet)) {
            return false;
        }
        RawDataSet rawDataSet = (RawDataSet) obj;
        return this.zza == rawDataSet.zza && Objects.equal(this.zzb, rawDataSet.zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza));
    }

    public final String toString() {
        return String.format("RawDataSet{%s@[%s]}", Integer.valueOf(this.zza), this.zzb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public RawDataSet(DataSet dataSet, List list) {
        this.zzb = dataSet.zza(list);
        this.zza = com.google.android.gms.internal.fitness.zzd.zza(dataSet.getDataSource(), list);
    }
}
