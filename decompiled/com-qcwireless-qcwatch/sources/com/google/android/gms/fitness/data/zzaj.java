package com.google.android.gms.fitness.data;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzaj {
    public static final double zza = 10.0d / TimeUnit.SECONDS.toNanos(1);
    public static final double zzb = 2000.0d / TimeUnit.HOURS.toNanos(1);
    public static final double zzc = 100.0d / TimeUnit.SECONDS.toNanos(1);
    public static final Set zzd = Collections.unmodifiableSet(new HashSet(Arrays.asList("altitude", TypedValues.TransitionType.S_DURATION, "food_item", "meal_type", "repetitions", "resistance", "resistance_type")));
    private static final zzaj zze = new zzaj();
    private final Map zzf;
    private final Map zzg;

    private zzaj() {
        HashMap map = new HashMap();
        map.put("latitude", new zzai(-90.0d, 90.0d, null));
        map.put("longitude", new zzai(-180.0d, 180.0d, null));
        map.put("accuracy", new zzai(0.0d, 10000.0d, null));
        map.put("bpm", new zzai(0.0d, 1000.0d, null));
        map.put("altitude", new zzai(-100000.0d, 100000.0d, null));
        map.put("percentage", new zzai(0.0d, 100.0d, null));
        map.put("confidence", new zzai(0.0d, 100.0d, null));
        map.put(TypedValues.TransitionType.S_DURATION, new zzai(0.0d, 9.223372036854776E18d, null));
        map.put("height", new zzai(0.0d, 3.0d, null));
        map.put("weight", new zzai(0.0d, 1000.0d, null));
        map.put("speed", new zzai(0.0d, 11000.0d, null));
        this.zzg = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put("com.google.step_count.delta", zzd("steps", new zzai(0.0d, zza, null)));
        map2.put("com.google.calories.expended", zzd(Field.NUTRIENT_CALORIES, new zzai(0.0d, zzb, null)));
        map2.put("com.google.distance.delta", zzd("distance", new zzai(0.0d, zzc, null)));
        this.zzf = Collections.unmodifiableMap(map2);
    }

    public static zzaj zzc() {
        return zze;
    }

    private static Map zzd(Object obj, Object obj2) {
        HashMap map = new HashMap();
        map.put(obj, obj2);
        return map;
    }

    public final zzai zza(String str) {
        return (zzai) this.zzg.get(str);
    }

    public final zzai zzb(String str, String str2) {
        Map map = (Map) this.zzf.get(str);
        if (map != null) {
            return (zzai) map.get(str2);
        }
        return null;
    }
}
