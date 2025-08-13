package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class DataType extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;
    public static final DataType AGGREGATE_CALORIES_EXPENDED;
    public static final DataType AGGREGATE_DISTANCE_DELTA;
    public static final DataType AGGREGATE_HEART_POINTS;
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
    public static final DataType AGGREGATE_HEIGHT_SUMMARY;
    public static final DataType AGGREGATE_HYDRATION;
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
    public static final DataType AGGREGATE_MOVE_MINUTES;
    public static final DataType AGGREGATE_NUTRITION_SUMMARY;
    public static final DataType AGGREGATE_POWER_SUMMARY;
    public static final DataType AGGREGATE_SPEED_SUMMARY;
    public static final DataType AGGREGATE_STEP_COUNT_DELTA;
    public static final DataType AGGREGATE_WEIGHT_SUMMARY;
    public static final Parcelable.Creator<DataType> CREATOR = new zzk();
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    public static final DataType TYPE_ACTIVITY_SEGMENT;
    public static final DataType TYPE_BASAL_METABOLIC_RATE;
    public static final DataType TYPE_BODY_FAT_PERCENTAGE;
    public static final DataType TYPE_CALORIES_EXPENDED;
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE;
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE;
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION;
    public static final DataType TYPE_CYCLING_WHEEL_RPM;
    public static final DataType TYPE_DISTANCE_DELTA;
    public static final DataType TYPE_HEART_POINTS;
    public static final DataType TYPE_HEART_RATE_BPM;
    public static final DataType TYPE_HEIGHT;
    public static final DataType TYPE_HYDRATION;
    public static final DataType TYPE_LOCATION_SAMPLE;

    @Deprecated
    public static final DataType TYPE_LOCATION_TRACK;
    public static final DataType TYPE_MOVE_MINUTES;
    public static final DataType TYPE_NUTRITION;
    public static final DataType TYPE_POWER_SAMPLE;
    public static final DataType TYPE_SLEEP_SEGMENT;
    public static final DataType TYPE_SPEED;
    public static final DataType TYPE_STEP_COUNT_CADENCE;
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
    public static final DataType TYPE_STEP_COUNT_DELTA;
    public static final DataType TYPE_WEIGHT;
    public static final DataType TYPE_WORKOUT_EXERCISE;
    public static final DataType zza;
    public static final DataType zzb;
    public static final DataType zzc;
    public static final DataType zzd;
    public static final DataType zze;
    public static final DataType zzf;
    public static final DataType zzg;
    public static final DataType zzh;
    public static final DataType zzi;
    public static final DataType zzj;
    public static final DataType zzk;
    public static final DataType zzl;
    public static final DataType zzm;
    public static final DataType zzn;
    public static final DataType zzo;
    public static final DataType zzp;
    public static final DataType zzq;
    public static final DataType zzr;
    public static final DataType zzs;
    private final String zzt;
    private final List zzu;
    private final String zzv;
    private final String zzw;

    static {
        DataType dataType = new DataType("com.google.step_count.delta", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_STEPS);
        TYPE_STEP_COUNT_DELTA = dataType;
        TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_STEPS);
        TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_RPM);
        zza = new DataType("com.google.internal.goal", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zze);
        TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_ACTIVITY);
        TYPE_SLEEP_SEGMENT = new DataType("com.google.sleep.segment", 2, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.FIELD_SLEEP_SEGMENT_TYPE);
        DataType dataType2 = new DataType("com.google.calories.expended", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_CALORIES);
        TYPE_CALORIES_EXPENDED = dataType2;
        TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_CALORIES);
        TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_WATTS);
        zzb = new DataType("com.google.sensor.events", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzg, Field.zzh, Field.zzi);
        TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", 1, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.FIELD_BPM);
        zzc = new DataType("com.google.respiratory_rate", 1, "https://www.googleapis.com/auth/fitness.respiratory_rate.read", "https://www.googleapis.com/auth/fitness.respiratory_rate.write", Field.zzd);
        TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
        TYPE_LOCATION_TRACK = new DataType("com.google.location.track", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
        DataType dataType3 = new DataType("com.google.distance.delta", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_DISTANCE);
        TYPE_DISTANCE_DELTA = dataType3;
        TYPE_SPEED = new DataType("com.google.speed", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_SPEED);
        TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_REVOLUTIONS);
        TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_RPM);
        TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_REVOLUTIONS);
        TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_RPM);
        TYPE_HEIGHT = new DataType("com.google.height", 1, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_HEIGHT);
        TYPE_WEIGHT = new DataType("com.google.weight", 1, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_WEIGHT);
        TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", 1, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_PERCENTAGE);
        TYPE_NUTRITION = new DataType("com.google.nutrition", 1, "https://www.googleapis.com/auth/fitness.nutrition.read", "https://www.googleapis.com/auth/fitness.nutrition.write", Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM);
        DataType dataType4 = new DataType("com.google.hydration", 1, "https://www.googleapis.com/auth/fitness.nutrition.read", "https://www.googleapis.com/auth/fitness.nutrition.write", Field.FIELD_VOLUME);
        TYPE_HYDRATION = dataType4;
        TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.zza, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);
        DataType dataType5 = new DataType("com.google.active_minutes", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_DURATION);
        TYPE_MOVE_MINUTES = dataType5;
        AGGREGATE_MOVE_MINUTES = dataType5;
        zzd = new DataType("com.google.device_on_body", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzk);
        AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
        AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_STEP_COUNT_DELTA = dataType;
        AGGREGATE_DISTANCE_DELTA = dataType3;
        AGGREGATE_CALORIES_EXPENDED = dataType2;
        TYPE_HEART_POINTS = new DataType("com.google.heart_minutes", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_INTENSITY);
        AGGREGATE_HEART_POINTS = new DataType("com.google.heart_minutes.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_INTENSITY, Field.FIELD_DURATION);
        AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", 2, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
        AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", 2, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", 2, "https://www.googleapis.com/auth/fitness.body.read", "https://www.googleapis.com/auth/fitness.body.write", Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", 2, "https://www.googleapis.com/auth/fitness.nutrition.read", "https://www.googleapis.com/auth/fitness.nutrition.write", Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE);
        AGGREGATE_HYDRATION = dataType4;
        zze = new DataType("com.google.activity.samples", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzj);
        zzf = new DataType("com.google.internal.sleep_attributes", 2, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.zzl);
        zzg = new DataType("com.google.internal.sleep_schedule", 1, "https://www.googleapis.com/auth/fitness.sleep.read", "https://www.googleapis.com/auth/fitness.sleep.write", Field.zzm);
        zzh = new DataType("com.google.internal.paced_walking_attributes", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzn);
        zzi = new DataType("com.google.time_zone_change", 1, "https://www.googleapis.com/auth/fitness.location.read", "https://www.googleapis.com/auth/fitness.location.write", Field.zzo);
        zzj = new DataType("com.google.internal.met", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzp);
        zzk = new DataType("com.google.internal.internal_device_temperature", 2, "https://www.googleapis.com/auth/fitness.body_temperature.read", "https://www.googleapis.com/auth/fitness.body_temperature.write", Field.zzq);
        zzl = new DataType("com.google.internal.skin_temperature", 2, "https://www.googleapis.com/auth/fitness.body_temperature.read", "https://www.googleapis.com/auth/fitness.body_temperature.write", Field.zzr);
        Field field = Field.FIELD_MIN_INT;
        zzm = new DataType("com.google.internal.custom_heart_rate_zone", 1, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.zzs, field, field);
        zzn = new DataType("com.google.internal.active_minutes_combined", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzt, Field.zzu, Field.zzv);
        zzo = new DataType("com.google.internal.sedentary_time", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzw);
        zzp = new DataType("com.google.internal.custom_max_heart_rate", 1, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.FIELD_MAX_INT);
        zzq = new DataType("com.google.internal.momentary_stress_algorithm", 1, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.zzx);
        zzr = new DataType("com.google.internal.magnetic_field_presence", 2, "https://www.googleapis.com/auth/fitness.activity.read", "https://www.googleapis.com/auth/fitness.activity.write", Field.zzy);
        zzs = new DataType("com.google.internal.momentary_stress_algorithm_windows", 2, "https://www.googleapis.com/auth/fitness.heart_rate.read", "https://www.googleapis.com/auth/fitness.heart_rate.write", Field.zzz);
    }

    public DataType(String str, int i, String str2, String str3, Field... fieldArr) {
        this.zzt = str;
        this.zzu = Collections.unmodifiableList(Arrays.asList(fieldArr));
        this.zzv = str2;
        this.zzw = str3;
    }

    @Deprecated
    public static List<DataType> getAggregatesForInput(DataType dataType) {
        DataType aggregateType = dataType.getAggregateType();
        return aggregateType == null ? Collections.emptyList() : Collections.singletonList(aggregateType);
    }

    public static String getMimeType(DataType dataType) {
        return MIME_TYPE_PREFIX.concat(String.valueOf(dataType.getName()));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        return this.zzt.equals(dataType.zzt) && this.zzu.equals(dataType.zzu);
    }

    public DataType getAggregateType() {
        return (DataType) zza.zza.get(this);
    }

    public List<Field> getFields() {
        return this.zzu;
    }

    public String getName() {
        return this.zzt;
    }

    public int hashCode() {
        return this.zzt.hashCode();
    }

    public int indexOf(Field field) {
        int iIndexOf = this.zzu.indexOf(field);
        Preconditions.checkArgument(iIndexOf >= 0, "%s not a field of %s", field, this);
        return iIndexOf;
    }

    public String toString() {
        return String.format("DataType{%s%s}", this.zzt, this.zzu);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzv, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zza() {
        return this.zzv;
    }

    public final String zzb() {
        return this.zzw;
    }

    public final String zzc() {
        return this.zzt.startsWith("com.google.") ? this.zzt.substring(11) : this.zzt;
    }

    DataType(String str, List list, String str2, String str3) {
        this.zzt = str;
        this.zzu = Collections.unmodifiableList(list);
        this.zzv = str2;
        this.zzw = str3;
    }
}
