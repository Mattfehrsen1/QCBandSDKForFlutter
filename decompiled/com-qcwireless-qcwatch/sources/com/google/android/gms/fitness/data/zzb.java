package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzb extends AbstractSafeParcelable {
    private final String zzb;
    public static final zzb zza = new zzb("com.google.android.gms");
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();

    public zzb(String str) {
        this.zzb = (String) Preconditions.checkNotNull(str);
    }

    public static zzb zza(String str) {
        return "com.google.android.gms".equals(str) ? zza : new zzb(str);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzb) {
            return this.zzb.equals(((zzb) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        return String.format("Application{%s}", this.zzb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zzb() {
        return this.zzb;
    }
}
