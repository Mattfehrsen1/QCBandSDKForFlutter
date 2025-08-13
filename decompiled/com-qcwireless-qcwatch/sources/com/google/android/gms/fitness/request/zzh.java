package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbj;
import com.google.android.gms.internal.fitness.zzbk;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    private final zzbk zza;
    private final DataType zzb;
    private final boolean zzc;

    zzh(IBinder iBinder, DataType dataType, boolean z) {
        this.zza = zzbj.zzb(iBinder);
        this.zzb = dataType;
        this.zzc = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zza.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String toString() {
        Object[] objArr = new Object[1];
        DataType dataType = this.zzb;
        objArr[0] = dataType == null ? "null" : dataType.zzc();
        return String.format("DailyTotalRequest{%s}", objArr);
    }

    public zzh(zzbk zzbkVar, DataType dataType, boolean z) {
        this.zza = zzbkVar;
        this.zzb = dataType;
        this.zzc = z;
    }
}
