package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbp;
import com.google.android.gms.internal.fitness.zzbq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataSourcesRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzn();
    private final List zza;
    private final List zzb;
    private final boolean zzc;
    private final zzbq zzd;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private List zza = new ArrayList();
        private List zzb = Arrays.asList(0, 1);

        public DataSourcesRequest build() {
            Preconditions.checkState(!this.zza.isEmpty(), "Must add at least one data type");
            Preconditions.checkState(!this.zzb.isEmpty(), "Must add at least one data source type");
            return new DataSourcesRequest(this.zza, this.zzb, false, (zzbq) null);
        }

        public Builder setDataSourceTypes(int... iArr) {
            this.zzb = new ArrayList();
            for (int i : iArr) {
                this.zzb.add(Integer.valueOf(i));
            }
            return this;
        }

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zza = Arrays.asList(dataTypeArr);
            return this;
        }
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, zzbq zzbqVar) {
        this(dataSourcesRequest.zza, dataSourcesRequest.zzb, dataSourcesRequest.zzc, zzbqVar);
    }

    public List<DataType> getDataTypes() {
        return this.zza;
    }

    public String toString() {
        Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add("dataTypes", this.zza).add("sourceTypes", this.zzb);
        if (this.zzc) {
            toStringHelperAdd.add("includeDbOnlySources", "true");
        }
        return toStringHelperAdd.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzc);
        zzbq zzbqVar = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbqVar == null ? null : zzbqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    DataSourcesRequest(List list, List list2, boolean z, IBinder iBinder) {
        this.zza = list;
        this.zzb = list2;
        this.zzc = z;
        this.zzd = iBinder == null ? null : zzbp.zzc(iBinder);
    }

    public DataSourcesRequest(List list, List list2, boolean z, zzbq zzbqVar) {
        this.zza = list;
        this.zzb = list2;
        this.zzc = z;
        this.zzd = zzbqVar;
    }
}
