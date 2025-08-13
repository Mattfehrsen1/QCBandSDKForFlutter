package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class DataTypeResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new zze();
    private final Status zza;
    private final DataType zzb;

    public DataTypeResult(Status status, DataType dataType) {
        this.zza = status;
        this.zzb = dataType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataTypeResult)) {
            return false;
        }
        DataTypeResult dataTypeResult = (DataTypeResult) obj;
        return this.zza.equals(dataTypeResult.zza) && Objects.equal(this.zzb, dataTypeResult.zzb);
    }

    public DataType getDataType() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zza).add("dataType", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
