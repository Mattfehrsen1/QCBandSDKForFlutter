package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class MapValue extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MapValue> CREATOR = new zzw();
    private final int zza;
    private final float zzb;

    public MapValue(int i, float f) {
        this.zza = i;
        this.zzb = f;
    }

    public static MapValue zzb(float f) {
        return new MapValue(2, f);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MapValue)) {
            return false;
        }
        MapValue mapValue = (MapValue) obj;
        int i = this.zza;
        if (i == mapValue.zza) {
            if (i != 2) {
                return this.zzb == mapValue.zzb;
            }
            if (zza() == mapValue.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.zzb;
    }

    public final String toString() {
        return this.zza != 2 ? "unknown" : Float.toString(zza());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeFloat(parcel, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final float zza() {
        Preconditions.checkState(this.zza == 2, "Value is not in float format");
        return this.zzb;
    }
}
