package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class SessionInsertRequest extends AbstractSafeParcelable {
    private final Session zzb;
    private final List zzc;
    private final List zzd;
    private final zzcp zze;
    private static final TimeUnit zza = TimeUnit.MILLISECONDS;
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new zzap();

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private Session zza;
        private final List zzb = new ArrayList();
        private final List zzc = new ArrayList();
        private final List zzd = new ArrayList();

        private final void zza(DataPoint dataPoint) {
            long startTime = this.zza.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.zza.getEndTime(TimeUnit.NANOSECONDS);
            long startTime2 = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
            long endTime2 = dataPoint.getEndTime(TimeUnit.NANOSECONDS);
            if (startTime2 == 0 || endTime2 == 0) {
                return;
            }
            if (endTime2 > endTime) {
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                TimeUnit timeUnit2 = SessionInsertRequest.zza;
                endTime2 = timeUnit.convert(timeUnit2.convert(endTime2, timeUnit), timeUnit2);
            }
            long j = endTime2;
            Preconditions.checkState(startTime2 >= startTime && j <= endTime, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime), Long.valueOf(endTime));
            if (j != dataPoint.getEndTime(TimeUnit.NANOSECONDS)) {
                Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(j), SessionInsertRequest.zza));
                dataPoint.setTimeInterval(startTime2, j, TimeUnit.NANOSECONDS);
            }
        }

        private final void zzb(DataPoint dataPoint) {
            long startTime = this.zza.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.zza.getEndTime(TimeUnit.NANOSECONDS);
            long timestamp = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
            if (timestamp != 0) {
                if (timestamp < startTime || timestamp > endTime) {
                    TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    TimeUnit timeUnit2 = SessionInsertRequest.zza;
                    timestamp = timeUnit.convert(timeUnit2.convert(timestamp, timeUnit), timeUnit2);
                }
                Preconditions.checkState(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint.getTimestamp(TimeUnit.NANOSECONDS) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(timestamp), SessionInsertRequest.zza));
                    dataPoint.setTimestamp(timestamp, TimeUnit.NANOSECONDS);
                }
            }
        }

        public Builder addAggregateDataPoint(DataPoint dataPoint) {
            Preconditions.checkArgument(dataPoint != null, "Must specify a valid aggregate data point.");
            DataSource dataSource = dataPoint.getDataSource();
            Preconditions.checkState(!this.zzd.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            DataSet.zzc(dataPoint);
            this.zzd.add(dataSource);
            this.zzc.add(dataPoint);
            return this;
        }

        public Builder addDataSet(DataSet dataSet) {
            Preconditions.checkArgument(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            Preconditions.checkState(!this.zzd.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            Preconditions.checkArgument(!dataSet.getDataPoints().isEmpty(), "No data points specified in the input data set.");
            this.zzd.add(dataSource);
            this.zzb.add(dataSet);
            return this;
        }

        public SessionInsertRequest build() {
            Preconditions.checkState(this.zza != null, "Must specify a valid session.");
            Preconditions.checkState(this.zza.getEndTime(TimeUnit.MILLISECONDS) != 0, "Must specify a valid end time, cannot insert a continuing session.");
            Iterator it = this.zzb.iterator();
            while (it.hasNext()) {
                for (DataPoint dataPoint : ((DataSet) it.next()).getDataPoints()) {
                    zzb(dataPoint);
                    zza(dataPoint);
                }
            }
            for (DataPoint dataPoint2 : this.zzc) {
                zzb(dataPoint2);
                zza(dataPoint2);
            }
            return new SessionInsertRequest(this.zza, this.zzb, this.zzc, (zzcp) null);
        }

        public Builder setSession(Session session) {
            this.zza = session;
            return this;
        }
    }

    SessionInsertRequest(Session session, List list, List list2, IBinder iBinder) {
        this.zzb = session;
        this.zzc = Collections.unmodifiableList(list);
        this.zzd = Collections.unmodifiableList(list2);
        this.zze = iBinder == null ? null : zzco.zzb(iBinder);
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof SessionInsertRequest)) {
                return false;
            }
            SessionInsertRequest sessionInsertRequest = (SessionInsertRequest) obj;
            if (!Objects.equal(this.zzb, sessionInsertRequest.zzb) || !Objects.equal(this.zzc, sessionInsertRequest.zzc) || !Objects.equal(this.zzd, sessionInsertRequest.zzd)) {
                return false;
            }
        }
        return true;
    }

    public List<DataPoint> getAggregateDataPoints() {
        return this.zzd;
    }

    public List<DataSet> getDataSets() {
        return this.zzc;
    }

    public Session getSession() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zzc, this.zzd);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("session", this.zzb).add("dataSets", this.zzc).add("aggregateDataPoints", this.zzd).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSession(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSets(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, getAggregateDataPoints(), false);
        zzcp zzcpVar = this.zze;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public SessionInsertRequest(Session session, List list, List list2, zzcp zzcpVar) {
        this.zzb = session;
        this.zzc = Collections.unmodifiableList(list);
        this.zzd = Collections.unmodifiableList(list2);
        this.zze = zzcpVar;
    }

    public SessionInsertRequest(SessionInsertRequest sessionInsertRequest, zzcp zzcpVar) {
        this(sessionInsertRequest.zzb, sessionInsertRequest.zzc, sessionInsertRequest.zzd, zzcpVar);
    }
}
