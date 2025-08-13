package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class RawBucket extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RawBucket> CREATOR = new zzy();
    public final long zza;
    public final long zzb;
    public final Session zzc;
    public final int zzd;
    public final List zze;
    public final int zzf;

    public RawBucket(long j, long j2, Session session, int i, List list, int i2) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = session;
        this.zzd = i;
        this.zze = list;
        this.zzf = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawBucket)) {
            return false;
        }
        RawBucket rawBucket = (RawBucket) obj;
        return this.zza == rawBucket.zza && this.zzb == rawBucket.zzb && this.zzd == rawBucket.zzd && Objects.equal(this.zze, rawBucket.zze) && this.zzf == rawBucket.zzf;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Long.valueOf(this.zzb), Integer.valueOf(this.zzf));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zza)).add("endTime", Long.valueOf(this.zzb)).add("activity", Integer.valueOf(this.zzd)).add("dataSets", this.zze).add("bucketType", Integer.valueOf(this.zzf)).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public RawBucket(Bucket bucket, List list) {
        this.zza = bucket.getStartTime(TimeUnit.MILLISECONDS);
        this.zzb = bucket.getEndTime(TimeUnit.MILLISECONDS);
        this.zzc = bucket.getSession();
        this.zzd = bucket.zza();
        this.zzf = bucket.getBucketType();
        List<DataSet> dataSets = bucket.getDataSets();
        this.zze = new ArrayList(dataSets.size());
        Iterator<DataSet> it = dataSets.iterator();
        while (it.hasNext()) {
            this.zze.add(new RawDataSet(it.next(), list));
        }
    }
}
