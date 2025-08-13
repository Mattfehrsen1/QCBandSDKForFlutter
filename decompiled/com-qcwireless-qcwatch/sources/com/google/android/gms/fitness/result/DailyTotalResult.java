package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DailyTotalResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
    private final Status zza;
    private final DataSet zzb;

    public DailyTotalResult(Status status, DataSet dataSet) {
        this.zza = status;
        this.zzb = dataSet;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DailyTotalResult)) {
            return false;
        }
        DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
        return this.zza.equals(dailyTotalResult.zza) && Objects.equal(this.zzb, dailyTotalResult.zzb);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zza;
    }

    public DataSet getTotal() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zza).add("dataPoint", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getTotal(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
