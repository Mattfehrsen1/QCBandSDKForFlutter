package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.HexDumpUtils;
import com.google.android.gms.internal.fitness.zzfv;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class Value extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Value> CREATOR = new zzak();
    private final int zza;
    private boolean zzb;
    private float zzc;
    private String zzd;
    private Map zze;
    private int[] zzf;
    private float[] zzg;
    private byte[] zzh;

    Value(int i, boolean z, float f, String str, Bundle bundle, int[] iArr, float[] fArr, byte[] bArr) {
        ArrayMap arrayMap;
        this.zza = i;
        this.zzb = z;
        this.zzc = f;
        this.zzd = str;
        if (bundle == null) {
            arrayMap = null;
        } else {
            bundle.setClassLoader((ClassLoader) Preconditions.checkNotNull(MapValue.class.getClassLoader()));
            arrayMap = new ArrayMap(bundle.size());
            for (String str2 : bundle.keySet()) {
                arrayMap.put(str2, (MapValue) Preconditions.checkNotNull((MapValue) bundle.getParcelable(str2)));
            }
        }
        this.zze = arrayMap;
        this.zzf = iArr;
        this.zzg = fArr;
        this.zzh = bArr;
    }

    public String asActivity() {
        return zzfv.zzb(asInt());
    }

    public float asFloat() {
        Preconditions.checkState(this.zza == 2, "Value is not in float format");
        return this.zzc;
    }

    public int asInt() {
        Preconditions.checkState(this.zza == 1, "Value is not in int format");
        return Float.floatToRawIntBits(this.zzc);
    }

    public String asString() {
        Preconditions.checkState(this.zza == 3, "Value is not in string format");
        String str = this.zzd;
        return str == null ? "" : str;
    }

    @Deprecated
    public void clearKey(String str) {
        Preconditions.checkState(this.zza == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        Map map = this.zze;
        if (map != null) {
            map.remove(str);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value = (Value) obj;
        int i = this.zza;
        if (i == value.zza && this.zzb == value.zzb) {
            if (i != 1) {
                return i != 3 ? i != 4 ? i != 5 ? i != 6 ? i != 7 ? this.zzc == value.zzc : Arrays.equals(this.zzh, value.zzh) : Arrays.equals(this.zzg, value.zzg) : Arrays.equals(this.zzf, value.zzf) : Objects.equal(this.zze, value.zze) : Objects.equal(this.zzd, value.zzd);
            }
            if (asInt() == value.asInt()) {
                return true;
            }
        }
        return false;
    }

    public int getFormat() {
        return this.zza;
    }

    public Float getKeyValue(String str) {
        Preconditions.checkState(this.zza == 4, "Value is not in float map format");
        Map map = this.zze;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return Float.valueOf(((MapValue) this.zze.get(str)).zza());
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, this.zzh);
    }

    public boolean isSet() {
        return this.zzb;
    }

    @Deprecated
    public void setActivity(String str) {
        setInt(zzfv.zza(str));
    }

    @Deprecated
    public void setFloat(float f) {
        Preconditions.checkState(this.zza == 2, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.zzb = true;
        this.zzc = f;
    }

    @Deprecated
    public void setInt(int i) {
        Preconditions.checkState(this.zza == 1, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.zzb = true;
        this.zzc = Float.intBitsToFloat(i);
    }

    @Deprecated
    public void setKeyValue(String str, float f) {
        Preconditions.checkState(this.zza == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzb = true;
        if (this.zze == null) {
            this.zze = new HashMap();
        }
        this.zze.put(str, MapValue.zzb(f));
    }

    @Deprecated
    public void setString(String str) {
        Preconditions.checkState(this.zza == 3, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
        this.zzb = true;
        this.zzd = str;
    }

    public String toString() {
        if (!this.zzb) {
            return "unset";
        }
        switch (this.zza) {
            case 3:
                String str = this.zzd;
                if (str == null) {
                }
                break;
            case 4:
                Map map = this.zze;
                if (map != null) {
                    break;
                }
                break;
            case 7:
                byte[] bArr = this.zzh;
                if (bArr != null && (r0 = HexDumpUtils.dump(bArr, 0, bArr.length, false)) != null) {
                }
                break;
        }
        return "";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getFormat());
        SafeParcelWriter.writeBoolean(parcel, 2, isSet());
        SafeParcelWriter.writeFloat(parcel, 3, this.zzc);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        Map map = this.zze;
        if (map == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle(map.size());
            for (Map.Entry entry : this.zze.entrySet()) {
                bundle2.putParcelable((String) entry.getKey(), (Parcelable) entry.getValue());
            }
            bundle = bundle2;
        }
        SafeParcelWriter.writeBundle(parcel, 5, bundle, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeFloatArray(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Deprecated
    public final void zza(Map map) {
        Preconditions.checkState(this.zza == 4, "Attempting to set a float map value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzb = true;
        HashMap map2 = new HashMap();
        for (Map.Entry entry : map.entrySet()) {
            map2.put((String) entry.getKey(), MapValue.zzb(((Float) entry.getValue()).floatValue()));
        }
        this.zze = map2;
    }
}
