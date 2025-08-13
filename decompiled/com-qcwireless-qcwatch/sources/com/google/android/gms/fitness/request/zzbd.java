package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzbd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbd> CREATOR = new zzbe();
    private final Subscription zza;
    private final boolean zzb;
    private final zzcp zzc;

    zzbd(Subscription subscription, boolean z, IBinder iBinder) {
        this.zza = subscription;
        this.zzb = z;
        this.zzc = iBinder == null ? null : zzco.zzb(iBinder);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("subscription", this.zza).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzb);
        zzcp zzcpVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzbd(Subscription subscription, boolean z, zzcp zzcpVar) {
        this.zza = subscription;
        this.zzb = false;
        this.zzc = zzcpVar;
    }
}
