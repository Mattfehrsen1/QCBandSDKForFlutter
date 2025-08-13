package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzar extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzar> CREATOR = new zzas();
    private final PendingIntent zza;
    private final zzcp zzb;

    zzar(PendingIntent pendingIntent, IBinder iBinder) {
        this.zza = pendingIntent;
        this.zzb = iBinder == null ? null : zzco.zzb(iBinder);
    }

    public final boolean equals(Object obj) {
        return this == obj || ((obj instanceof zzar) && Objects.equal(this.zza, ((zzar) obj).zza));
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.zza).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        zzcp zzcpVar = this.zzb;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzar(PendingIntent pendingIntent, zzcp zzcpVar) {
        this.zza = pendingIntent;
        this.zzb = zzcpVar;
    }
}
