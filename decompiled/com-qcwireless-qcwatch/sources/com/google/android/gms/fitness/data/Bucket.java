package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzfv;
import io.reactivex.annotations.SchedulerSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class Bucket extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    private final long zza;
    private final long zzb;
    private final Session zzc;
    private final int zzd;
    private final List zze;
    private final int zzf;

    Bucket(long j, long j2, Session session, int i, List list, int i2) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = session;
        this.zzd = i;
        this.zze = list;
        this.zzf = i2;
    }

    public static String zzb(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "bug" : "intervals" : "segment" : "type" : "session" : "time" : SchedulerSupport.NONE;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        return this.zza == bucket.zza && this.zzb == bucket.zzb && this.zzd == bucket.zzd && Objects.equal(this.zze, bucket.zze) && this.zzf == bucket.zzf;
    }

    public String getActivity() {
        return zzfv.zzb(this.zzd);
    }

    public int getBucketType() {
        return this.zzf;
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zze) {
            if (dataSet.getDataType().equals(dataType)) {
                return dataSet;
            }
        }
        return null;
    }

    public List<DataSet> getDataSets() {
        return this.zze;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzb, TimeUnit.MILLISECONDS);
    }

    public Session getSession() {
        return this.zzc;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zza, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Long.valueOf(this.zzb), Integer.valueOf(this.zzd), Integer.valueOf(this.zzf));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zza)).add("endTime", Long.valueOf(this.zzb)).add("activity", Integer.valueOf(this.zzd)).add("dataSets", this.zze).add("bucketType", zzb(this.zzf)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeParcelable(parcel, 3, getSession(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataSets(), false);
        SafeParcelWriter.writeInt(parcel, 6, getBucketType());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zzd;
    }

    public final boolean zzc(Bucket bucket) {
        return this.zza == bucket.zza && this.zzb == bucket.zzb && this.zzd == bucket.zzd && this.zzf == bucket.zzf;
    }

    public Bucket(RawBucket rawBucket, List list) {
        long j = rawBucket.zza;
        long j2 = rawBucket.zzb;
        Session session = rawBucket.zzc;
        int i = rawBucket.zzd;
        List list2 = rawBucket.zze;
        ArrayList arrayList = new ArrayList(list2.size());
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new DataSet((RawDataSet) it.next(), list));
        }
        this(j, j2, session, i, arrayList, rawBucket.zzf);
    }
}
