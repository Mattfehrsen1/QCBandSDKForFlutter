package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class RawDataPoint extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RawDataPoint> CREATOR = new zzz();
    private final long zza;
    private final long zzb;
    private final Value[] zzc;
    private final int zzd;
    private final int zze;
    private final long zzf;

    public RawDataPoint(long j, long j2, Value[] valueArr, int i, int i2, long j3) {
        this.zza = j;
        this.zzb = j2;
        this.zzd = i;
        this.zze = i2;
        this.zzf = j3;
        this.zzc = valueArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataPoint)) {
            return false;
        }
        RawDataPoint rawDataPoint = (RawDataPoint) obj;
        return this.zza == rawDataPoint.zza && this.zzb == rawDataPoint.zzb && Arrays.equals(this.zzc, rawDataPoint.zzc) && this.zzd == rawDataPoint.zzd && this.zze == rawDataPoint.zze && this.zzf == rawDataPoint.zzf;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Long.valueOf(this.zzb));
    }

    public final String toString() {
        return String.format(Locale.US, "RawDataPoint{%s@[%s, %s](%d,%d)}", Arrays.toString(this.zzc), Long.valueOf(this.zzb), Long.valueOf(this.zza), Integer.valueOf(this.zzd), Integer.valueOf(this.zze));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.writeLong(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zzd;
    }

    public final int zzb() {
        return this.zze;
    }

    public final long zzc() {
        return this.zza;
    }

    public final long zzd() {
        return this.zzf;
    }

    public final long zze() {
        return this.zzb;
    }

    public final Value[] zzf() {
        return this.zzc;
    }

    RawDataPoint(DataPoint dataPoint, List list) {
        this.zza = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
        this.zzb = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
        this.zzc = dataPoint.zze();
        this.zzd = com.google.android.gms.internal.fitness.zzd.zza(dataPoint.getDataSource(), list);
        this.zze = com.google.android.gms.internal.fitness.zzd.zza(dataPoint.zzb(), list);
        this.zzf = dataPoint.zza();
    }
}
