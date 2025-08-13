package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcf;
import com.google.android.gms.internal.fitness.zzcg;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzaf();
    private final DataType zza;
    private final zzcg zzb;

    zzae(DataType dataType, IBinder iBinder) {
        this.zza = dataType;
        this.zzb = iBinder == null ? null : zzcf.zzb(iBinder);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        zzcg zzcgVar = this.zzb;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcgVar == null ? null : zzcgVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzae(DataType dataType, zzcg zzcgVar) {
        this.zza = dataType;
        this.zzb = zzcgVar;
    }
}
