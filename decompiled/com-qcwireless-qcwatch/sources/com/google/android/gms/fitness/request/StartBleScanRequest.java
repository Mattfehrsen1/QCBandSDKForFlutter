package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class StartBleScanRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzba();
    private final List zza;
    private final zzab zzb;
    private final int zzc;
    private final zzcp zzd;
    private final BleScanCallback zze;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private BleScanCallback zzb;
        private DataType[] zza = new DataType[0];
        private int zzc = 10;

        public StartBleScanRequest build() {
            Preconditions.checkState(this.zzb != null, "Must set BleScanCallback");
            return new StartBleScanRequest(ArrayUtils.toArrayList(this.zza), this.zzb, this.zzc, (zzaz) null);
        }

        public Builder setBleScanCallback(BleScanCallback bleScanCallback) {
            this.zzb = bleScanCallback;
            return this;
        }

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zza = dataTypeArr;
            return this;
        }

        public Builder setTimeoutSecs(int i) {
            Preconditions.checkArgument(i > 0, "Stop time must be greater than zero");
            Preconditions.checkArgument(i <= 60, "Stop time must be less than 1 minute");
            this.zzc = i;
            return this;
        }
    }

    StartBleScanRequest(List list, IBinder iBinder, int i, IBinder iBinder2) {
        zzab zzzVar;
        this.zza = list;
        if (iBinder == null) {
            zzzVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            zzzVar = iInterfaceQueryLocalInterface instanceof zzab ? (zzab) iInterfaceQueryLocalInterface : new zzz(iBinder);
        }
        this.zzb = zzzVar;
        this.zzc = i;
        this.zzd = iBinder2 == null ? null : zzco.zzb(iBinder2);
        this.zze = null;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zza);
    }

    public int getTimeoutSecs() {
        return this.zzc;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zza).add("timeoutSecs", Integer.valueOf(this.zzc)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        zzab zzabVar = this.zzb;
        SafeParcelWriter.writeIBinder(parcel, 2, zzabVar == null ? null : zzabVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 3, getTimeoutSecs());
        zzcp zzcpVar = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcpVar != null ? zzcpVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final BleScanCallback zza() {
        return this.zze;
    }

    /* synthetic */ StartBleScanRequest(List list, BleScanCallback bleScanCallback, int i, zzaz zzazVar) {
        this.zza = list;
        this.zzb = null;
        this.zzc = i;
        this.zzd = null;
        this.zze = bleScanCallback;
    }

    public StartBleScanRequest(List list, zzab zzabVar, int i, zzcp zzcpVar) {
        this.zza = list;
        this.zzb = zzabVar;
        this.zzc = i;
        this.zzd = zzcpVar;
        this.zze = null;
    }
}
