package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    private final Session zza;
    private final DataSet zzb;

    public zzad(Session session, DataSet dataSet) {
        this.zza = session;
        this.zzb = dataSet;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzadVar = (zzad) obj;
        return Objects.equal(this.zza, zzadVar.zza) && Objects.equal(this.zzb, zzadVar.zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zza).add("dataSet", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final DataSet zza() {
        return this.zzb;
    }

    public final Session zzb() {
        return this.zza;
    }
}
