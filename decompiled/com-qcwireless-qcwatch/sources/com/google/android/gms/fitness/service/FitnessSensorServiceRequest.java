package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzu;
import com.google.android.gms.fitness.data.zzv;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class FitnessSensorServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new zzc();
    public static final int UNSPECIFIED = -1;
    private final DataSource zza;
    private final zzv zzb;
    private final long zzc;
    private final long zzd;

    FitnessSensorServiceRequest(DataSource dataSource, IBinder iBinder, long j, long j2) {
        this.zza = dataSource;
        this.zzb = zzu.zzb(iBinder);
        this.zzc = j;
        this.zzd = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FitnessSensorServiceRequest)) {
            return false;
        }
        FitnessSensorServiceRequest fitnessSensorServiceRequest = (FitnessSensorServiceRequest) obj;
        return Objects.equal(this.zza, fitnessSensorServiceRequest.zza) && this.zzc == fitnessSensorServiceRequest.zzc && this.zzd == fitnessSensorServiceRequest.zzd;
    }

    public long getBatchInterval(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzd, TimeUnit.MICROSECONDS);
    }

    public DataSource getDataSource() {
        return this.zza;
    }

    public SensorEventDispatcher getDispatcher() {
        return new zzd(this.zzb);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        long j = this.zzc;
        if (j == -1) {
            return -1L;
        }
        return timeUnit.convert(j, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, Long.valueOf(this.zzc), Long.valueOf(this.zzd));
    }

    public String toString() {
        return String.format("FitnessSensorServiceRequest{%s}", this.zza);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzb.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
