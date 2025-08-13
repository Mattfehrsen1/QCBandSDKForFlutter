package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataReadResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
    private final List zza;
    private final Status zzb;
    private final List zzc;
    private int zzd;
    private final List zze;

    DataReadResult(List list, Status status, List list2, int i, List list3) {
        this.zzb = status;
        this.zzd = i;
        this.zze = list3;
        this.zza = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.zza.add(new DataSet((RawDataSet) it.next(), list3));
        }
        this.zzc = new ArrayList(list2.size());
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zzc.add(new Bucket((RawBucket) it2.next(), list3));
        }
    }

    private static void zzc(DataSet dataSet, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DataSet dataSet2 = (DataSet) it.next();
            if (dataSet2.getDataSource().equals(dataSet.getDataSource())) {
                Iterator<T> it2 = dataSet.getDataPoints().iterator();
                while (it2.hasNext()) {
                    dataSet2.zzb((DataPoint) it2.next());
                }
                return;
            }
        }
        list.add(dataSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataReadResult)) {
            return false;
        }
        DataReadResult dataReadResult = (DataReadResult) obj;
        return this.zzb.equals(dataReadResult.zzb) && Objects.equal(this.zza, dataReadResult.zza) && Objects.equal(this.zzc, dataReadResult.zzc);
    }

    public List<Bucket> getBuckets() {
        return this.zzc;
    }

    public DataSet getDataSet(DataSource dataSource) {
        for (DataSet dataSet : this.zza) {
            if (dataSource.equals(dataSet.getDataSource())) {
                return dataSet;
            }
        }
        return DataSet.builder(dataSource).build();
    }

    public List<DataSet> getDataSets() {
        return this.zza;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zza, this.zzc);
    }

    public String toString() {
        Object obj;
        Object obj2;
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzb);
        if (this.zza.size() > 5) {
            obj = this.zza.size() + " data sets";
        } else {
            obj = this.zza;
        }
        Objects.ToStringHelper toStringHelperAdd2 = toStringHelperAdd.add("dataSets", obj);
        if (this.zzc.size() > 5) {
            obj2 = this.zzc.size() + " buckets";
        } else {
            obj2 = this.zzc;
        }
        return toStringHelperAdd2.add("buckets", obj2).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        ArrayList arrayList = new ArrayList(this.zza.size());
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataSet((DataSet) it.next(), this.zze));
        }
        SafeParcelWriter.writeList(parcel, 1, arrayList, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        ArrayList arrayList2 = new ArrayList(this.zzc.size());
        Iterator it2 = this.zzc.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new RawBucket((Bucket) it2.next(), this.zze));
        }
        SafeParcelWriter.writeList(parcel, 3, arrayList2, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzd);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zzd;
    }

    public final void zzb(DataReadResult dataReadResult) {
        Iterator<DataSet> it = dataReadResult.getDataSets().iterator();
        while (it.hasNext()) {
            zzc(it.next(), this.zza);
        }
        for (Bucket bucket : dataReadResult.getBuckets()) {
            Iterator it2 = this.zzc.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    this.zzc.add(bucket);
                    break;
                }
                Bucket bucket2 = (Bucket) it2.next();
                if (bucket2.zzc(bucket)) {
                    Iterator<DataSet> it3 = bucket.getDataSets().iterator();
                    while (it3.hasNext()) {
                        zzc(it3.next(), bucket2.getDataSets());
                    }
                }
            }
        }
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zza) {
            if (dataType.equals(dataSet.getDataType())) {
                return dataSet;
            }
        }
        DataSource.Builder builder = new DataSource.Builder();
        builder.setType(1);
        builder.setDataType(dataType);
        return DataSet.builder(builder.build()).build();
    }

    public DataReadResult(List list, List list2, Status status) {
        this.zza = list;
        this.zzb = status;
        this.zzc = list2;
        this.zzd = 1;
        this.zze = new ArrayList();
    }
}
