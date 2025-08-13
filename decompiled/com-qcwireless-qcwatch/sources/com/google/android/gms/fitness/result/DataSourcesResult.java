package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataSourcesResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataSourcesResult> CREATOR = new zzd();
    private final List zza;
    private final Status zzb;

    public DataSourcesResult(List list, Status status) {
        this.zza = Collections.unmodifiableList(list);
        this.zzb = status;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof DataSourcesResult)) {
                return false;
            }
            DataSourcesResult dataSourcesResult = (DataSourcesResult) obj;
            if (!this.zzb.equals(dataSourcesResult.zzb) || !Objects.equal(this.zza, dataSourcesResult.zza)) {
                return false;
            }
        }
        return true;
    }

    public List<DataSource> getDataSources() {
        return this.zza;
    }

    public List<DataSource> getDataSources(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (DataSource dataSource : this.zza) {
            if (dataSource.getDataType().equals(dataType)) {
                arrayList.add(dataSource);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zza);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzb).add("dataSources", this.zza).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataSources(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
