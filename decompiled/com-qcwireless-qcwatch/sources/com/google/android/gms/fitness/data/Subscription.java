package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class Subscription extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Subscription> CREATOR = new zzag();
    private final DataSource zza;
    private final DataType zzb;
    private final long zzc;
    private final int zzd;
    private final int zze;

    Subscription(DataSource dataSource, DataType dataType, long j, int i, int i2) {
        this.zza = dataSource;
        this.zzb = dataType;
        this.zzc = j;
        this.zzd = i;
        this.zze = i2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return Objects.equal(this.zza, subscription.zza) && Objects.equal(this.zzb, subscription.zzb) && this.zzc == subscription.zzc && this.zzd == subscription.zzd && this.zze == subscription.zze;
    }

    public DataSource getDataSource() {
        return this.zza;
    }

    public DataType getDataType() {
        return this.zzb;
    }

    public int hashCode() {
        DataSource dataSource = this.zza;
        return Objects.hashCode(dataSource, dataSource, Long.valueOf(this.zzc), Integer.valueOf(this.zzd), Integer.valueOf(this.zze));
    }

    public String toDebugString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        DataSource dataSource = this.zza;
        objArr[0] = dataSource == null ? this.zzb.getName() : dataSource.zzb();
        objArr[1] = Integer.valueOf(this.zze);
        return String.format(locale, "Subscription{%s}, subscriptionType{%d}", objArr);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zza).add("dataType", this.zzb).add("samplingIntervalMicros", Long.valueOf(this.zzc)).add("accuracyMode", Integer.valueOf(this.zzd)).add("subscriptionType", Integer.valueOf(this.zze)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final DataType zza() {
        DataType dataType = this.zzb;
        return dataType == null ? this.zza.getDataType() : dataType;
    }
}
