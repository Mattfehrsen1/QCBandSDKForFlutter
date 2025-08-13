package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataUpdateRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataUpdateRequest> CREATOR = new zzu();
    private final long zza;
    private final long zzb;
    private final DataSet zzc;
    private final zzcp zzd;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private long zza;
        private long zzb;
        private DataSet zzc;

        public DataUpdateRequest build() {
            Preconditions.checkNotZero(this.zza, "Must set a non-zero value for startTimeMillis/startTime");
            Preconditions.checkNotZero(this.zzb, "Must set a non-zero value for endTimeMillis/endTime");
            Preconditions.checkNotNull(this.zzc, "Must set the data set");
            for (DataPoint dataPoint : this.zzc.getDataPoints()) {
                long startTime = dataPoint.getStartTime(TimeUnit.MILLISECONDS);
                long endTime = dataPoint.getEndTime(TimeUnit.MILLISECONDS);
                Preconditions.checkState(startTime <= endTime && (startTime == 0 || startTime >= this.zza) && ((startTime == 0 || startTime <= this.zzb) && endTime <= this.zzb && endTime >= this.zza), "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", Long.valueOf(startTime), Long.valueOf(endTime), Long.valueOf(this.zza), Long.valueOf(this.zzb));
            }
            return new DataUpdateRequest(this.zza, this.zzb, this.zzc, null);
        }

        public Builder setDataSet(DataSet dataSet) {
            Preconditions.checkNotNull(dataSet, "Must set the data set");
            this.zzc = dataSet;
            return this;
        }

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 >= j, "Invalid end time :%d", Long.valueOf(j2));
            this.zza = timeUnit.toMillis(j);
            this.zzb = timeUnit.toMillis(j2);
            return this;
        }
    }

    public DataUpdateRequest(long j, long j2, DataSet dataSet, IBinder iBinder) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = dataSet;
        this.zzd = iBinder == null ? null : zzco.zzb(iBinder);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateRequest)) {
            return false;
        }
        DataUpdateRequest dataUpdateRequest = (DataUpdateRequest) obj;
        return this.zza == dataUpdateRequest.zza && this.zzb == dataUpdateRequest.zzb && Objects.equal(this.zzc, dataUpdateRequest.zzc);
    }

    public DataSet getDataSet() {
        return this.zzc;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzb, TimeUnit.MILLISECONDS);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zza, TimeUnit.MILLISECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Long.valueOf(this.zzb), this.zzc);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zza)).add("endTimeMillis", Long.valueOf(this.zzb)).add("dataSet", this.zzc).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataSet(), i, false);
        zzcp zzcpVar = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final long zza() {
        return this.zzb;
    }

    public final long zzb() {
        return this.zza;
    }

    public DataUpdateRequest(DataUpdateRequest dataUpdateRequest, IBinder iBinder) {
        this(dataUpdateRequest.zza, dataUpdateRequest.zzb, dataUpdateRequest.getDataSet(), iBinder);
    }
}
