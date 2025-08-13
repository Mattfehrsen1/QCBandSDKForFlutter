package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcl;
import com.google.android.gms.internal.fitness.zzcm;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzav extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzav> CREATOR = new zzaw();
    private final String zza;
    private final String zzb;
    private final zzcm zzc;

    zzav(String str, String str2, IBinder iBinder) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = iBinder == null ? null : zzcl.zzb(iBinder);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzav)) {
            return false;
        }
        zzav zzavVar = (zzav) obj;
        return Objects.equal(this.zza, zzavVar.zza) && Objects.equal(this.zzb, zzavVar.zzb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.zza).add("identifier", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        zzcm zzcmVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcmVar == null ? null : zzcmVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzav(String str, String str2, zzcm zzcmVar) {
        this.zza = null;
        this.zzb = str2;
        this.zzc = zzcmVar;
    }
}
