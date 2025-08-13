package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataSource extends AbstractSafeParcelable {
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private final DataType zzc;
    private final int zzd;
    private final Device zze;
    private final zzb zzf;
    private final String zzg;
    private final String zzh;
    private static final String zza = "RAW".toLowerCase(Locale.ROOT);
    private static final String zzb = "DERIVED".toLowerCase(Locale.ROOT);
    public static final Parcelable.Creator<DataSource> CREATOR = new zzj();

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static final class Builder {
        private DataType zza;
        private Device zzc;
        private zzb zzd;
        private int zzb = -1;
        private String zze = "";

        public DataSource build() {
            Preconditions.checkState(this.zza != null, "Must set data type");
            Preconditions.checkState(this.zzb >= 0, "Must set data source type");
            return new DataSource(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        public Builder setAppPackageName(Context context) {
            setAppPackageName(context.getPackageName());
            return this;
        }

        public Builder setDataType(DataType dataType) {
            this.zza = dataType;
            return this;
        }

        public Builder setDevice(Device device) {
            this.zzc = device;
            return this;
        }

        public Builder setStreamName(String str) {
            Preconditions.checkArgument(str != null, "Must specify a valid stream name");
            this.zze = str;
            return this;
        }

        public Builder setType(int i) {
            this.zzb = i;
            return this;
        }

        public Builder setAppPackageName(String str) {
            this.zzd = zzb.zza(str);
            return this;
        }
    }

    public DataSource(DataType dataType, int i, Device device, zzb zzbVar, String str) {
        this.zzc = dataType;
        this.zzd = i;
        this.zze = device;
        this.zzf = zzbVar;
        this.zzg = str;
        StringBuilder sb = new StringBuilder();
        sb.append(zzc(i));
        sb.append(":");
        sb.append(dataType.getName());
        if (zzbVar != null) {
            sb.append(":");
            sb.append(zzbVar.zzb());
        }
        if (device != null) {
            sb.append(":");
            sb.append(device.zza());
        }
        if (str != null) {
            sb.append(":");
            sb.append(str);
        }
        this.zzh = sb.toString();
    }

    public static DataSource extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    private static String zzc(int i) {
        return i != 0 ? i != 1 ? zzb : zzb : zza;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataSource) {
            return this.zzh.equals(((DataSource) obj).zzh);
        }
        return false;
    }

    public String getAppPackageName() {
        zzb zzbVar = this.zzf;
        if (zzbVar == null) {
            return null;
        }
        return zzbVar.zzb();
    }

    public DataType getDataType() {
        return this.zzc;
    }

    public Device getDevice() {
        return this.zze;
    }

    public String getStreamIdentifier() {
        return this.zzh;
    }

    public String getStreamName() {
        return this.zzg;
    }

    public int getType() {
        return this.zzd;
    }

    public int hashCode() {
        return this.zzh.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(zzc(this.zzd));
        if (this.zzf != null) {
            sb.append(":");
            sb.append(this.zzf);
        }
        if (this.zze != null) {
            sb.append(":");
            sb.append(this.zze);
        }
        if (this.zzg != null) {
            sb.append(":");
            sb.append(this.zzg);
        }
        sb.append(":");
        sb.append(this.zzc);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataType(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDevice(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzf, i, false);
        SafeParcelWriter.writeString(parcel, 6, getStreamName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final zzb zza() {
        return this.zzf;
    }

    public final String zzb() {
        String str;
        int i = this.zzd;
        String str2 = i != 0 ? i != 1 ? "?" : "d" : "r";
        String strZzc = this.zzc.zzc();
        zzb zzbVar = this.zzf;
        String strConcat = zzbVar == null ? "" : zzbVar.equals(zzb.zza) ? ":gms" : ":".concat(String.valueOf(this.zzf.zzb()));
        Device device = this.zze;
        if (device != null) {
            str = ":" + device.getModel() + ":" + device.getUid();
        } else {
            str = "";
        }
        String str3 = this.zzg;
        return str2 + ":" + strZzc + strConcat + str + (str3 != null ? ":".concat(str3) : "");
    }
}
