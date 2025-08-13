package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzfw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class DataSet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DataSet> CREATOR = new zzi();
    private final int zza;
    private final DataSource zzb;
    private final List zzc;
    private final List zzd;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private final DataSet zza;
        private boolean zzb = false;

        /* synthetic */ Builder(DataSource dataSource, zzh zzhVar) {
            this.zza = DataSet.create(dataSource);
        }

        public Builder add(DataPoint dataPoint) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.add(dataPoint);
            return this;
        }

        public Builder addAll(Iterable<DataPoint> iterable) {
            Preconditions.checkState(!this.zzb, "Builder should not be mutated after calling #build.");
            this.zza.addAll(iterable);
            return this;
        }

        public DataSet build() {
            Preconditions.checkState(!this.zzb, "DataSet#build() should only be called once.");
            this.zzb = true;
            return this.zza;
        }
    }

    DataSet(int i, DataSource dataSource, List list, List list2) {
        this.zza = i;
        this.zzb = dataSource;
        this.zzc = new ArrayList(list.size());
        this.zzd = i < 2 ? Collections.singletonList(dataSource) : list2;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.zzc.add(new DataPoint(this.zzd, (RawDataPoint) it.next()));
        }
    }

    public static Builder builder(DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new Builder(dataSource, null);
    }

    public static DataSet create(DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new DataSet(dataSource);
    }

    public static void zzc(DataPoint dataPoint) {
        double dAsFloat;
        String strConcat = "DataPoint out of range";
        if (zzl.zza(dataPoint.getDataType().getName()) == null) {
            strConcat = null;
        } else {
            DataType dataType = dataPoint.getDataType();
            int i = 0;
            while (true) {
                if (i < dataType.getFields().size()) {
                    String name = dataType.getFields().get(i).getName();
                    if (!dataPoint.zzc(i).isSet()) {
                        if (!Boolean.TRUE.equals(dataType.getFields().get(i).isOptional()) && !zzaj.zzd.contains(name)) {
                            strConcat = String.valueOf(name).concat(" not set");
                            break;
                        }
                        i++;
                    } else {
                        double format = dataType.getFields().get(i).getFormat();
                        if (format == 1.0d) {
                            dAsFloat = dataPoint.zzc(i).asInt();
                        } else if (format == 2.0d) {
                            dAsFloat = dataPoint.zzc(i).asFloat();
                        } else {
                            continue;
                            i++;
                        }
                        zzai zzaiVarZza = zzaj.zzc().zza(name);
                        if (zzaiVarZza != null && !zzaiVarZza.zza(dAsFloat)) {
                            strConcat = "Field out of range";
                            break;
                        }
                        zzai zzaiVarZzb = zzaj.zzc().zzb(dataType.getName(), name);
                        if (zzaiVarZzb != null) {
                            long endTime = dataPoint.getEndTime(TimeUnit.NANOSECONDS) - dataPoint.getStartTime(TimeUnit.NANOSECONDS);
                            if (endTime == 0) {
                                if (dAsFloat == 0.0d) {
                                }
                            } else if (!zzaiVarZzb.zza(dAsFloat / endTime)) {
                                break;
                            }
                        } else {
                            continue;
                        }
                        i++;
                    }
                } else if ("com.google.activity.segment".equals(dataPoint.getDataSource().getDataType().getName())) {
                    Value value = dataPoint.getValue(Field.FIELD_ACTIVITY);
                    if (value == null) {
                        strConcat = "activity is not set";
                    } else if (zzfw.zza(value.asInt(), zzfw.UNKNOWN).zzb()) {
                        strConcat = "Sleep types are not a valid activity for com.google.activity.segment";
                    }
                }
            }
        }
        if (strConcat == null) {
            return;
        }
        Log.w("Fitness", "Invalid data point: ".concat(String.valueOf(String.valueOf(dataPoint))));
        throw new IllegalArgumentException(strConcat);
    }

    @Deprecated
    public void add(DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        Preconditions.checkArgument(dataSource.getStreamIdentifier().equals(this.zzb.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.zzb);
        dataPoint.zzd();
        zzc(dataPoint);
        zzb(dataPoint);
    }

    @Deprecated
    public void addAll(Iterable<DataPoint> iterable) {
        Iterator<DataPoint> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public DataPoint createDataPoint() {
        return DataPoint.create(this.zzb);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSet)) {
            return false;
        }
        DataSet dataSet = (DataSet) obj;
        return Objects.equal(this.zzb, dataSet.zzb) && Objects.equal(this.zzc, dataSet.zzc);
    }

    public List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.zzc);
    }

    public DataSource getDataSource() {
        return this.zzb;
    }

    public DataType getDataType() {
        return this.zzb.getDataType();
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb);
    }

    public boolean isEmpty() {
        return this.zzc.isEmpty();
    }

    public String toString() {
        List listZza = zza(this.zzd);
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.zzb.zzb();
        Object obj = listZza;
        if (this.zzc.size() >= 10) {
            obj = String.format(Locale.US, "%d data points, first 5: %s", Integer.valueOf(this.zzc.size()), listZza.subList(0, 5));
        }
        objArr[1] = obj;
        return String.format(locale, "DataSet{%s %s}", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeList(parcel, 3, zza(this.zzd), false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final List zza(List list) {
        ArrayList arrayList = new ArrayList(this.zzc.size());
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataPoint((DataPoint) it.next(), list));
        }
        return arrayList;
    }

    @Deprecated
    public final void zzb(DataPoint dataPoint) {
        this.zzc.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource == null || this.zzd.contains(originalDataSource)) {
            return;
        }
        this.zzd.add(originalDataSource);
    }

    public DataSet(DataSource dataSource) {
        this.zza = 3;
        DataSource dataSource2 = (DataSource) Preconditions.checkNotNull(dataSource);
        this.zzb = dataSource2;
        this.zzc = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.zzd = arrayList;
        arrayList.add(dataSource2);
    }

    public DataSet(RawDataSet rawDataSet, List list) {
        this.zza = 3;
        this.zzb = (DataSource) list.get(rawDataSet.zza);
        this.zzd = list;
        List list2 = rawDataSet.zzb;
        this.zzc = new ArrayList(list2.size());
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            this.zzc.add(new DataPoint(this.zzd, (RawDataPoint) it.next()));
        }
    }
}
