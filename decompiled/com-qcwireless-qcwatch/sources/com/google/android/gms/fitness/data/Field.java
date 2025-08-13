package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class Field extends AbstractSafeParcelable {
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    public static final String NUTRIENT_CALCIUM = "calcium";
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    public static final String NUTRIENT_IRON = "iron";
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    public static final String NUTRIENT_POTASSIUM = "potassium";
    public static final String NUTRIENT_PROTEIN = "protein";
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    public static final String NUTRIENT_SODIUM = "sodium";
    public static final String NUTRIENT_SUGAR = "sugar";
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    private final String zzA;
    private final int zzB;
    private final Boolean zzC;
    public static final Parcelable.Creator<Field> CREATOR = new zzq();
    public static final Field FIELD_ACTIVITY = zzd("activity");
    public static final Field FIELD_SLEEP_SEGMENT_TYPE = zzd("sleep_segment_type");
    public static final Field FIELD_CONFIDENCE = zzb("confidence");
    public static final Field FIELD_STEPS = zzd("steps");

    @Deprecated
    public static final Field FIELD_STEP_LENGTH = zzb("step_length");
    public static final Field FIELD_DURATION = zzd(TypedValues.TransitionType.S_DURATION);
    public static final Field zza = zzf(TypedValues.TransitionType.S_DURATION);
    public static final Field zzb = zzc("activity_duration.ascending");
    public static final Field zzc = zzc("activity_duration.descending");
    public static final Field FIELD_BPM = zzb("bpm");
    public static final Field zzd = zzb("respiratory_rate");
    public static final Field FIELD_LATITUDE = zzb("latitude");
    public static final Field FIELD_LONGITUDE = zzb("longitude");
    public static final Field FIELD_ACCURACY = zzb("accuracy");
    public static final Field FIELD_ALTITUDE = zze("altitude");
    public static final Field FIELD_DISTANCE = zzb("distance");
    public static final Field FIELD_HEIGHT = zzb("height");
    public static final Field FIELD_WEIGHT = zzb("weight");
    public static final Field FIELD_PERCENTAGE = zzb("percentage");
    public static final Field FIELD_SPEED = zzb("speed");
    public static final Field FIELD_RPM = zzb("rpm");
    public static final Field zze = zza("google.android.fitness.GoalV2");
    public static final Field zzf = zza("google.android.fitness.Device");
    public static final Field FIELD_REVOLUTIONS = zzd("revolutions");
    public static final String NUTRIENT_CALORIES = "calories";
    public static final Field FIELD_CALORIES = zzb(NUTRIENT_CALORIES);
    public static final Field FIELD_WATTS = zzb("watts");
    public static final Field FIELD_VOLUME = zzb("volume");
    public static final Field FIELD_MEAL_TYPE = zzf("meal_type");
    public static final Field FIELD_FOOD_ITEM = new Field("food_item", 3, true);
    public static final Field FIELD_NUTRIENTS = zzc("nutrients");
    public static final Field FIELD_EXERCISE = zzg("exercise");
    public static final Field FIELD_REPETITIONS = zzf("repetitions");
    public static final Field FIELD_RESISTANCE = zze("resistance");
    public static final Field FIELD_RESISTANCE_TYPE = zzf("resistance_type");
    public static final Field FIELD_NUM_SEGMENTS = zzd("num_segments");
    public static final Field FIELD_AVERAGE = zzb("average");
    public static final Field FIELD_MAX = zzb("max");
    public static final Field FIELD_MIN = zzb("min");
    public static final Field FIELD_LOW_LATITUDE = zzb("low_latitude");
    public static final Field FIELD_LOW_LONGITUDE = zzb("low_longitude");
    public static final Field FIELD_HIGH_LATITUDE = zzb("high_latitude");
    public static final Field FIELD_HIGH_LONGITUDE = zzb("high_longitude");
    public static final Field FIELD_OCCURRENCES = zzd("occurrences");
    public static final Field zzg = zzd("sensor_type");
    public static final Field zzh = new Field("timestamps", 5, null);
    public static final Field zzi = new Field("sensor_values", 6, null);
    public static final Field FIELD_INTENSITY = zzb("intensity");
    public static final Field zzj = zzc("activity_confidence");
    public static final Field zzk = zzb("probability");
    public static final Field zzl = zza("google.android.fitness.SleepAttributes");
    public static final Field zzm = zza("google.android.fitness.SleepSchedule");

    @Deprecated
    public static final Field FIELD_CIRCUMFERENCE = zzb("circumference");
    public static final Field zzn = zza("google.android.fitness.PacedWalkingAttributes");
    public static final Field zzo = zzg("zone_id");
    public static final Field zzp = zzb("met");
    public static final Field zzq = zzb("internal_device_temperature");
    public static final Field zzr = zzb("skin_temperature");
    public static final Field zzs = zzd("custom_heart_rate_zone_status");
    public static final Field FIELD_MIN_INT = zzd("min_int");
    public static final Field FIELD_MAX_INT = zzd("max_int");
    public static final Field zzt = zzf("lightly_active_duration");
    public static final Field zzu = zzf("moderately_active_duration");
    public static final Field zzv = zzf("very_active_duration");
    public static final Field zzw = zza("google.android.fitness.SedentaryTime");
    public static final Field zzx = zza("google.android.fitness.MomentaryStressAlgorithm");
    public static final Field zzy = zzd("magnet_presence");
    public static final Field zzz = zza("google.android.fitness.MomentaryStressAlgorithmWindows");

    public Field(String str, int i, Boolean bool) {
        this.zzA = (String) Preconditions.checkNotNull(str);
        this.zzB = i;
        this.zzC = bool;
    }

    public static Field zza(String str) {
        return new Field(str, 7, null);
    }

    public static Field zzb(String str) {
        return new Field(str, 2, null);
    }

    public static Field zzc(String str) {
        return new Field(str, 4, null);
    }

    public static Field zzd(String str) {
        return new Field(str, 1, null);
    }

    public static Field zze(String str) {
        return new Field(str, 2, true);
    }

    public static Field zzf(String str) {
        return new Field(str, 1, true);
    }

    public static Field zzg(String str) {
        return new Field(str, 3, null);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        return this.zzA.equals(field.zzA) && this.zzB == field.zzB;
    }

    public int getFormat() {
        return this.zzB;
    }

    public String getName() {
        return this.zzA;
    }

    public int hashCode() {
        return this.zzA.hashCode();
    }

    public Boolean isOptional() {
        return this.zzC;
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.zzA;
        objArr[1] = this.zzB == 1 ? "i" : "f";
        return String.format("%s(%s)", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, getFormat());
        SafeParcelWriter.writeBooleanObject(parcel, 3, isOptional(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
