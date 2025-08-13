package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzco;
import com.google.android.gms.internal.fitness.zzcp;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataUpdateListenerRegistrationRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataUpdateListenerRegistrationRequest> CREATOR = new zzr();
    private final DataSource zza;
    private final DataType zzb;
    private final PendingIntent zzc;
    private final zzcp zzd;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private DataSource zza;
        private DataType zzb;
        private PendingIntent zzc;

        public DataUpdateListenerRegistrationRequest build() {
            boolean z = true;
            if (this.zza == null && this.zzb == null) {
                z = false;
            }
            Preconditions.checkState(z, "Set either dataSource or dataTYpe");
            Preconditions.checkNotNull(this.zzc, "pendingIntent must be set");
            return new DataUpdateListenerRegistrationRequest(this.zza, this.zzb, this.zzc, null);
        }

        public Builder setDataSource(DataSource dataSource) {
            Preconditions.checkNotNull(dataSource);
            this.zza = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            Preconditions.checkNotNull(dataType);
            this.zzb = dataType;
            return this;
        }

        public Builder setPendingIntent(PendingIntent pendingIntent) {
            Preconditions.checkNotNull(pendingIntent);
            this.zzc = pendingIntent;
            return this;
        }
    }

    public DataUpdateListenerRegistrationRequest(DataSource dataSource, DataType dataType, PendingIntent pendingIntent, IBinder iBinder) {
        this.zza = dataSource;
        this.zzb = dataType;
        this.zzc = pendingIntent;
        this.zzd = iBinder == null ? null : zzco.zzb(iBinder);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataUpdateListenerRegistrationRequest)) {
            return false;
        }
        DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest = (DataUpdateListenerRegistrationRequest) obj;
        return Objects.equal(this.zza, dataUpdateListenerRegistrationRequest.zza) && Objects.equal(this.zzb, dataUpdateListenerRegistrationRequest.zzb) && Objects.equal(this.zzc, dataUpdateListenerRegistrationRequest.zzc);
    }

    public DataSource getDataSource() {
        return this.zza;
    }

    public DataType getDataType() {
        return this.zzb;
    }

    public PendingIntent getIntent() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataSource", this.zza).add("dataType", this.zzb).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzc).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getDataType(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIntent(), i, false);
        zzcp zzcpVar = this.zzd;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcpVar == null ? null : zzcpVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public DataUpdateListenerRegistrationRequest(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest, IBinder iBinder) {
        this(dataUpdateListenerRegistrationRequest.zza, dataUpdateListenerRegistrationRequest.zzb, dataUpdateListenerRegistrationRequest.zzc, iBinder);
    }
}
