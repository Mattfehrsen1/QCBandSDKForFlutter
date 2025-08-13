package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzfv;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class Goal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Goal> CREATOR = new zzs();
    public static final int OBJECTIVE_TYPE_DURATION = 2;
    public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
    public static final int OBJECTIVE_TYPE_METRIC = 1;
    private final long zza;
    private final long zzb;
    private final List zzc;
    private final Recurrence zzd;
    private final int zze;
    private final MetricObjective zzf;
    private final DurationObjective zzg;
    private final FrequencyObjective zzh;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class DurationObjective extends AbstractSafeParcelable {
        public static final Parcelable.Creator<DurationObjective> CREATOR = new zzp();
        private final long zza;

        DurationObjective(long j) {
            this.zza = j;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof DurationObjective) && this.zza == ((DurationObjective) obj).zza;
        }

        public long getDuration(TimeUnit timeUnit) {
            return timeUnit.convert(this.zza, TimeUnit.NANOSECONDS);
        }

        public int hashCode() {
            return (int) this.zza;
        }

        public String toString() {
            return Objects.toStringHelper(this).add(TypedValues.TransitionType.S_DURATION, Long.valueOf(this.zza)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeLong(parcel, 1, this.zza);
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }

        public DurationObjective(long j, TimeUnit timeUnit) {
            this.zza = timeUnit.toNanos(j);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class FrequencyObjective extends AbstractSafeParcelable {
        public static final Parcelable.Creator<FrequencyObjective> CREATOR = new zzr();
        private final int zza;

        public FrequencyObjective(int i) {
            this.zza = i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof FrequencyObjective) && this.zza == ((FrequencyObjective) obj).zza;
        }

        public int getFrequency() {
            return this.zza;
        }

        public int hashCode() {
            return this.zza;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("frequency", Integer.valueOf(this.zza)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getFrequency());
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class MetricObjective extends AbstractSafeParcelable {
        public static final Parcelable.Creator<MetricObjective> CREATOR = new zzx();
        private final String zza;
        private final double zzb;
        private final double zzc;

        public MetricObjective(String str, double d) {
            this(str, d, 0.0d);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricObjective)) {
                return false;
            }
            MetricObjective metricObjective = (MetricObjective) obj;
            return Objects.equal(this.zza, metricObjective.zza) && this.zzb == metricObjective.zzb && this.zzc == metricObjective.zzc;
        }

        public String getDataTypeName() {
            return this.zza;
        }

        public double getValue() {
            return this.zzb;
        }

        public int hashCode() {
            return this.zza.hashCode();
        }

        public String toString() {
            return Objects.toStringHelper(this).add("dataTypeName", this.zza).add("value", Double.valueOf(this.zzb)).add("initialValue", Double.valueOf(this.zzc)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getDataTypeName(), false);
            SafeParcelWriter.writeDouble(parcel, 2, getValue());
            SafeParcelWriter.writeDouble(parcel, 3, this.zzc);
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }

        public MetricObjective(String str, double d, double d2) {
            this.zza = str;
            this.zzb = d;
            this.zzc = d2;
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class MismatchedGoalException extends IllegalStateException {
        public MismatchedGoalException(String str) {
            super(str);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectiveType {
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Recurrence extends AbstractSafeParcelable {
        public static final Parcelable.Creator<Recurrence> CREATOR = new zzab();
        public static final int UNIT_DAY = 1;
        public static final int UNIT_MONTH = 3;
        public static final int UNIT_WEEK = 2;
        private final int zza;
        private final int zzb;

        /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
        @Retention(RetentionPolicy.SOURCE)
        public @interface RecurrenceUnit {
        }

        public Recurrence(int i, int i2) {
            this.zza = i;
            boolean z = false;
            if (i2 > 0 && i2 <= 3) {
                z = true;
            }
            Preconditions.checkState(z);
            this.zzb = i2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            return this.zza == recurrence.zza && this.zzb == recurrence.zzb;
        }

        public int getCount() {
            return this.zza;
        }

        public int getUnit() {
            return this.zzb;
        }

        public int hashCode() {
            return this.zzb;
        }

        public String toString() {
            String str;
            Objects.ToStringHelper toStringHelperAdd = Objects.toStringHelper(this).add("count", Integer.valueOf(this.zza));
            int i = this.zzb;
            if (i == 1) {
                str = "day";
            } else if (i == 2) {
                str = "week";
            } else {
                if (i != 3) {
                    throw new IllegalArgumentException("invalid unit value");
                }
                str = "month";
            }
            return toStringHelperAdd.add("unit", str).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getCount());
            SafeParcelWriter.writeInt(parcel, 2, getUnit());
            SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        }
    }

    Goal(long j, long j2, List list, Recurrence recurrence, int i, MetricObjective metricObjective, DurationObjective durationObjective, FrequencyObjective frequencyObjective) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = list;
        this.zzd = recurrence;
        this.zze = i;
        this.zzf = metricObjective;
        this.zzg = durationObjective;
        this.zzh = frequencyObjective;
    }

    private static String zza(int i) {
        if (i == 0) {
            return "unknown";
        }
        if (i == 1) {
            return "metric";
        }
        if (i == 2) {
            return TypedValues.TransitionType.S_DURATION;
        }
        if (i == 3) {
            return "frequency";
        }
        throw new IllegalArgumentException("invalid objective type value");
    }

    private final void zzb(int i) {
        int i2 = this.zze;
        if (i != i2) {
            throw new MismatchedGoalException(String.format("%s goal does not have %s objective", zza(i2), zza(i)));
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) obj;
        return this.zza == goal.zza && this.zzb == goal.zzb && Objects.equal(this.zzc, goal.zzc) && Objects.equal(this.zzd, goal.zzd) && this.zze == goal.zze && Objects.equal(this.zzf, goal.zzf) && Objects.equal(this.zzg, goal.zzg) && Objects.equal(this.zzh, goal.zzh);
    }

    public String getActivityName() {
        if (this.zzc.isEmpty() || this.zzc.size() > 1) {
            return null;
        }
        return zzfv.zzb(((Integer) this.zzc.get(0)).intValue());
    }

    public long getCreateTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zza, TimeUnit.NANOSECONDS);
    }

    public DurationObjective getDurationObjective() {
        zzb(2);
        return this.zzg;
    }

    public long getEndTime(Calendar calendar, TimeUnit timeUnit) {
        if (this.zzd == null) {
            return timeUnit.convert(this.zzb, TimeUnit.NANOSECONDS);
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calendar.getTime());
        int i = this.zzd.zzb;
        if (i == 1) {
            calendar2.add(5, 1);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        if (i == 2) {
            calendar2.add(4, 1);
            calendar2.set(7, 2);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        if (i == 3) {
            calendar2.add(2, 1);
            calendar2.set(5, 1);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        throw new IllegalArgumentException("Invalid unit " + this.zzd.zzb);
    }

    public FrequencyObjective getFrequencyObjective() {
        zzb(3);
        return this.zzh;
    }

    public MetricObjective getMetricObjective() {
        zzb(1);
        return this.zzf;
    }

    public int getObjectiveType() {
        return this.zze;
    }

    public Recurrence getRecurrence() {
        return this.zzd;
    }

    public long getStartTime(Calendar calendar, TimeUnit timeUnit) {
        if (this.zzd == null) {
            return timeUnit.convert(this.zza, TimeUnit.NANOSECONDS);
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calendar.getTime());
        int i = this.zzd.zzb;
        if (i == 1) {
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        if (i == 2) {
            calendar2.set(7, 2);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        if (i == 3) {
            calendar2.set(5, 1);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        throw new IllegalArgumentException("Invalid unit " + this.zzd.zzb);
    }

    public int hashCode() {
        return this.zze;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("activity", getActivityName()).add("recurrence", this.zzd).add("metricObjective", this.zzf).add("durationObjective", this.zzg).add("frequencyObjective", this.zzh).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeList(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getRecurrence(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getObjectiveType());
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
