package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzbs;
import com.google.android.gms.internal.fitness.zzbt;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzp> CREATOR = new zzq();
    private final String zza;
    private final zzbt zzb;

    zzp(String str, IBinder iBinder) {
        this.zza = str;
        this.zzb = zzbs.zzb(iBinder);
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof zzp) && Objects.equal(this.zza, ((zzp) obj).zza));
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.zza).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzb.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzp(String str, zzbt zzbtVar) {
        this.zza = str;
        this.zzb = zzbtVar;
    }
}
