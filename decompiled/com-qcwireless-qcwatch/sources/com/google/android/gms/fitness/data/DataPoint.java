package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzfv;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class DataPoint extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DataPoint> CREATOR = new zzg();
    private final DataSource zza;
    private long zzb;
    private long zzc;
    private final Value[] zzd;
    private DataSource zze;
    private final long zzf;

    private DataPoint(DataSource dataSource) {
        this.zza = (DataSource) Preconditions.checkNotNull(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.zzd = new Value[fields.size()];
        Iterator<Field> it = fields.iterator();
        int i = 0;
        while (it.hasNext()) {
            this.zzd[i] = new Value(it.next().getFormat(), false, 0.0f, null, null, null, null, null);
            i++;
        }
        this.zzf = 0L;
    }

    public static Builder builder(DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new Builder(dataSource, null);
    }

    @Deprecated
    public static DataPoint create(DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    public static DataPoint extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    private static DataSource zzf(List list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return (DataSource) list.get(i);
    }

    private final void zzg(int i) {
        List<Field> fields = getDataType().getFields();
        int size = fields.size();
        Preconditions.checkArgument(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataPoint)) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return Objects.equal(this.zza, dataPoint.zza) && this.zzb == dataPoint.zzb && this.zzc == dataPoint.zzc && Arrays.equals(this.zzd, dataPoint.zzd) && Objects.equal(getOriginalDataSource(), dataPoint.getOriginalDataSource());
    }

    public DataSource getDataSource() {
        return this.zza;
    }

    public DataType getDataType() {
        return this.zza.getDataType();
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzb, TimeUnit.NANOSECONDS);
    }

    public DataSource getOriginalDataSource() {
        DataSource dataSource = this.zze;
        return dataSource != null ? dataSource : this.zza;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzc, TimeUnit.NANOSECONDS);
    }

    public long getTimestamp(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzb, TimeUnit.NANOSECONDS);
    }

    public Value getValue(Field field) {
        return this.zzd[getDataType().indexOf(field)];
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, Long.valueOf(this.zzb), Long.valueOf(this.zzc));
    }

    @Deprecated
    public DataPoint setFloatValues(float... fArr) {
        zzg(fArr.length);
        for (int i = 0; i < fArr.length; i++) {
            this.zzd[i].setFloat(fArr[i]);
        }
        return this;
    }

    @Deprecated
    public DataPoint setIntValues(int... iArr) {
        zzg(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            this.zzd[i].setInt(iArr[i]);
        }
        return this;
    }

    @Deprecated
    public DataPoint setTimeInterval(long j, long j2, TimeUnit timeUnit) {
        this.zzc = timeUnit.toNanos(j);
        this.zzb = timeUnit.toNanos(j2);
        return this;
    }

    @Deprecated
    public DataPoint setTimestamp(long j, TimeUnit timeUnit) {
        this.zzb = timeUnit.toNanos(j);
        return this;
    }

    public String toString() {
        Object[] objArr = new Object[6];
        objArr[0] = Arrays.toString(this.zzd);
        objArr[1] = Long.valueOf(this.zzc);
        objArr[2] = Long.valueOf(this.zzb);
        objArr[3] = Long.valueOf(this.zzf);
        objArr[4] = this.zza.zzb();
        DataSource dataSource = this.zze;
        objArr[5] = dataSource != null ? dataSource.zzb() : "N/A";
        return String.format("DataPoint{%s@[%s, %s,raw=%s](%s %s)}", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeLong(parcel, 4, this.zzc);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final long zza() {
        return this.zzf;
    }

    public final DataSource zzb() {
        return this.zze;
    }

    public final Value zzc(int i) {
        DataType dataType = getDataType();
        Preconditions.checkArgument(i >= 0 && i < dataType.getFields().size(), "fieldIndex %s is out of range for %s", Integer.valueOf(i), dataType);
        return this.zzd[i];
    }

    public final void zzd() {
        Preconditions.checkArgument(getDataType().getName().equals(getDataSource().getDataType().getName()), "Conflicting data types found %s vs %s", getDataType(), getDataType());
        Preconditions.checkArgument(this.zzb > 0, "Data point does not have the timestamp set: %s", this);
        Preconditions.checkArgument(this.zzc <= this.zzb, "Data point with start time greater than end time found: %s", this);
    }

    public final Value[] zze() {
        return this.zzd;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private final DataPoint zza;
        private boolean zzb = false;

        /* synthetic */ Builder(DataSource dataSource, zzf zzfVar) {
            this.zza = DataPoint.create(dataSource);
        }

        public DataPoint build() {
            Preconditions.checkState(!this.zzb, "DataPoint#build should not be called multiple times.");
            this.zzb = true;
            return this.zza;
        }

        public Builder setActivityField(Field field, String str) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.getValue(field).setInt(zzfv.zza(str));
            return this;
        }

        public Builder setField(Field field, float f) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.getValue(field).setFloat(f);
            return this;
        }

        public Builder setFloatValues(float... fArr) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.setFloatValues(fArr);
            return this;
        }

        public Builder setIntValues(int... iArr) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.setIntValues(iArr);
            return this;
        }

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.setTimeInterval(j, j2, timeUnit);
            return this;
        }

        public Builder setTimestamp(long j, TimeUnit timeUnit) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.setTimestamp(j, timeUnit);
            return this;
        }

        public Builder setField(Field field, int i) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.getValue(field).setInt(i);
            return this;
        }

        public Builder setField(Field field, String str) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.getValue(field).setString(str);
            return this;
        }

        public Builder setField(Field field, Map<String, Float> map) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.getValue(field).zza(map);
            return this;
        }
    }

    public DataPoint(DataSource dataSource, long j, long j2, Value[] valueArr, DataSource dataSource2, long j3) {
        this.zza = dataSource;
        this.zze = dataSource2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = valueArr;
        this.zzf = j3;
    }

    DataPoint(List list, RawDataPoint rawDataPoint) {
        this((DataSource) Preconditions.checkNotNull(zzf(list, rawDataPoint.zza())), rawDataPoint.zzc(), rawDataPoint.zze(), rawDataPoint.zzf(), zzf(list, rawDataPoint.zzb()), rawDataPoint.zzd());
    }
}
