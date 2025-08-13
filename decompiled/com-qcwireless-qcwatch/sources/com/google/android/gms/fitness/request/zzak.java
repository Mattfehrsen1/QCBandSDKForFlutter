package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzak> CREATOR = new zzal();
    private final DataSource zza;
    private final DataType zzb;
    private final com.google.android.gms.fitness.data.zzv zzc;
    private final long zzd;
    private final long zze;
    private final PendingIntent zzf;
    private final long zzg;
    private final int zzh;
    private final long zzi;
    private final List zzj;
    private final zzcp zzk;

    zzak(DataSource dataSource, DataType dataType, IBinder iBinder, long j, long j2, PendingIntent pendingIntent, long j3, int i, long j4, IBinder iBinder2) {
        this.zza = dataSource;
        this.zzb = dataType;
        this.zzc = iBinder == null ? null : com.google.android.gms.fitness.data.zzu.zzb(iBinder);
        this.zzd = j;
        this.zzg = j3;
        this.zze = j2;
        this.zzf = pendingIntent;
        this.zzh = i;
        this.zzj = Collections.emptyList();
        this.zzi = j4;
        this.zzk = iBinder2 != null ? zzco.zzb(iBinder2) : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzak)) {
            return false;
        }
        zzak zzakVar = (zzak) obj;
        return Objects.equal(this.zza, zzakVar.zza) && Objects.equal(this.zzb, zzakVar.zzb) && Objects.equal(this.zzc, zzakVar.zzc) && this.zzd == zzakVar.zzd && this.zzg == zzakVar.zzg && this.zze == zzakVar.zze && this.zzh == zzakVar.zzh;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, Long.valueOf(this.zzd), Long.valueOf(this.zzg), Long.valueOf(this.zze), Integer.valueOf(this.zzh));
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.zzb, this.zza, Long.valueOf(this.zzd), Long.valueOf(this.zzg), Long.valueOf(this.zze));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        com.google.android.gms.fitness.data.zzv zzvVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzvVar == null ? null : zzvVar.asBinder(), false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzd);
        SafeParcelWriter.writeLong(parcel, 7, this.zze);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzf, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzg);
        SafeParcelWriter.writeInt(parcel, 10, this.zzh);
        SafeParcelWriter.writeLong(parcel, 12, this.zzi);
        zzcp zzcpVar = this.zzk;
        SafeParcelWriter.writeIBinder(parcel, 13, zzcpVar != null ? zzcpVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzak(SensorRequest sensorRequest, com.google.android.gms.fitness.data.zzv zzvVar, PendingIntent pendingIntent, zzcp zzcpVar) {
        DataSource dataSource = sensorRequest.getDataSource();
        DataType dataType = sensorRequest.getDataType();
        long samplingRate = sensorRequest.getSamplingRate(TimeUnit.MICROSECONDS);
        long fastestRate = sensorRequest.getFastestRate(TimeUnit.MICROSECONDS);
        long maxDeliveryLatency = sensorRequest.getMaxDeliveryLatency(TimeUnit.MICROSECONDS);
        int accuracyMode = sensorRequest.getAccuracyMode();
        List listEmptyList = Collections.emptyList();
        long jZza = sensorRequest.zza();
        this.zza = dataSource;
        this.zzb = dataType;
        this.zzc = zzvVar;
        this.zzf = pendingIntent;
        this.zzd = samplingRate;
        this.zzg = fastestRate;
        this.zze = maxDeliveryLatency;
        this.zzh = accuracyMode;
        this.zzj = listEmptyList;
        this.zzi = jZza;
        this.zzk = zzcpVar;
    }
}
