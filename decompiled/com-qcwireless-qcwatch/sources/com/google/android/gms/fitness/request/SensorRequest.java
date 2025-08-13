package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class SensorRequest {
    public static final int ACCURACY_MODE_DEFAULT = 2;
    public static final int ACCURACY_MODE_HIGH = 3;
    public static final int ACCURACY_MODE_LOW = 1;
    private final DataSource zza;
    private final DataType zzb;
    private final long zzc;
    private final long zzd;
    private final long zze;
    private final int zzf;
    private final long zzg;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private DataSource zza;
        private DataType zzb;
        private long zzc = -1;
        private long zzd = 0;
        private long zze = 0;
        private boolean zzf = false;
        private int zzg = 2;
        private long zzh = Long.MAX_VALUE;

        public SensorRequest build() {
            DataSource dataSource;
            Preconditions.checkState((this.zza == null && this.zzb == null) ? false : true, "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzb;
            Preconditions.checkState(dataType == null || (dataSource = this.zza) == null || dataType.equals(dataSource.getDataType()), "Specified data type is incompatible with specified data source");
            return new SensorRequest(this, null);
        }

        public Builder setAccuracyMode(int i) {
            if (i != 1 && i != 3) {
                i = 2;
            }
            this.zzg = i;
            return this;
        }

        public Builder setDataSource(DataSource dataSource) {
            this.zza = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.zzb = dataType;
            return this;
        }

        public Builder setFastestRate(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative interval");
            this.zzf = true;
            this.zzd = timeUnit.toMicros(i);
            return this;
        }

        public Builder setMaxDeliveryLatency(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(i >= 0, "Cannot use a negative delivery interval");
            this.zze = timeUnit.toMicros(i);
            return this;
        }

        public Builder setSamplingRate(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Cannot use a negative sampling interval");
            long micros = timeUnit.toMicros(j);
            this.zzc = micros;
            if (!this.zzf) {
                this.zzd = micros / 2;
            }
            return this;
        }

        public Builder setTimeout(long j, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid time out value specified: %d", Long.valueOf(j));
            Preconditions.checkArgument(timeUnit != null, "Invalid time unit specified");
            this.zzh = timeUnit.toMicros(j);
            return this;
        }
    }

    /* synthetic */ SensorRequest(Builder builder, zzam zzamVar) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = builder.zzc;
        this.zzd = builder.zzd;
        this.zze = builder.zze;
        this.zzf = builder.zzg;
        this.zzg = builder.zzh;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorRequest)) {
            return false;
        }
        SensorRequest sensorRequest = (SensorRequest) obj;
        return Objects.equal(this.zza, sensorRequest.zza) && Objects.equal(this.zzb, sensorRequest.zzb) && this.zzc == sensorRequest.zzc && this.zzd == sensorRequest.zzd && this.zze == sensorRequest.zze && this.zzf == sensorRequest.zzf && this.zzg == sensorRequest.zzg;
    }

    public int getAccuracyMode() {
        return this.zzf;
    }

    public DataSource getDataSource() {
        return this.zza;
    }

    public DataType getDataType() {
        return this.zzb;
    }

    public long getFastestRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzd, TimeUnit.MICROSECONDS);
    }

    public long getMaxDeliveryLatency(TimeUnit timeUnit) {
        return timeUnit.convert(this.zze, TimeUnit.MICROSECONDS);
    }

    public long getSamplingRate(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzc, TimeUnit.MICROSECONDS);
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, Long.valueOf(this.zzc), Long.valueOf(this.zzd), Long.valueOf(this.zze), Integer.valueOf(this.zzf), Long.valueOf(this.zzg));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zza).add("dataType", this.zzb).add("samplingRateMicros", Long.valueOf(this.zzc)).add("deliveryLatencyMicros", Long.valueOf(this.zze)).add("timeOutMicros", Long.valueOf(this.zzg)).toString();
    }

    public final long zza() {
        return this.zzg;
    }
}
