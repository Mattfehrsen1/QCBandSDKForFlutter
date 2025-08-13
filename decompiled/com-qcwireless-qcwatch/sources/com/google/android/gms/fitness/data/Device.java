package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class Device extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Device> CREATOR = new zzn();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final int zzd;
    private final int zze;

    public Device(String str, String str2, String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    public static Device getLocalDevice(Context context) {
        int iZza = zzo.zza(context);
        return new Device(Build.MANUFACTURER, Build.MODEL, zzo.zzb(context), iZza, 2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.zza, device.zza) && Objects.equal(this.zzb, device.zzb) && Objects.equal(this.zzc, device.zzc) && this.zzd == device.zzd && this.zze == device.zze;
    }

    public String getManufacturer() {
        return this.zza;
    }

    public String getModel() {
        return this.zzb;
    }

    public int getType() {
        return this.zzd;
    }

    public String getUid() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, Integer.valueOf(this.zzd));
    }

    public String toString() {
        return String.format("Device{%s:%s:%s}", zza(), Integer.valueOf(this.zzd), Integer.valueOf(this.zze));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getManufacturer(), false);
        SafeParcelWriter.writeString(parcel, 2, getModel(), false);
        SafeParcelWriter.writeString(parcel, 4, getUid(), false);
        SafeParcelWriter.writeInt(parcel, 5, getType());
        SafeParcelWriter.writeInt(parcel, 6, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    final String zza() {
        return String.format("%s:%s:%s", this.zza, this.zzb, this.zzc);
    }

    public Device(String str, String str2, String str3, int i, int i2) {
        this.zza = (String) Preconditions.checkNotNull(str);
        this.zzb = (String) Preconditions.checkNotNull(str2);
        if (str3 == null) {
            throw new IllegalStateException("Device UID is null.");
        }
        this.zzc = str3;
        this.zzd = i;
        this.zze = i2;
    }
}
