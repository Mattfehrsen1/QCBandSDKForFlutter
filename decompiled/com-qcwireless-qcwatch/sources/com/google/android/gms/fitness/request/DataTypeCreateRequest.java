package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.fitness.zzbs;
import com.google.android.gms.internal.fitness.zzbt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class DataTypeCreateRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzo();
    private final String zza;
    private final List zzb;
    private final zzbt zzc;

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, zzbt zzbtVar) {
        this(dataTypeCreateRequest.zza, dataTypeCreateRequest.zzb, zzbtVar);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataTypeCreateRequest)) {
            return false;
        }
        DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
        return Objects.equal(this.zza, dataTypeCreateRequest.zza) && Objects.equal(this.zzb, dataTypeCreateRequest.zzb);
    }

    public List<Field> getFields() {
        return this.zzb;
    }

    public String getName() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.zza).add("fields", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        zzbt zzbtVar = this.zzc;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbtVar == null ? null : zzbtVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private String zza;
        private final List zzb = new ArrayList();

        public Builder addField(Field field) {
            if (!this.zzb.contains(field)) {
                this.zzb.add(field);
            }
            return this;
        }

        public DataTypeCreateRequest build() {
            Preconditions.checkState(this.zza != null, "Must set the name");
            Preconditions.checkState(!this.zzb.isEmpty(), "Must specify the data fields");
            return new DataTypeCreateRequest(this.zza, this.zzb, (zzbt) null);
        }

        public Builder setName(String str) {
            this.zza = str;
            return this;
        }

        public Builder addField(String str, int i) {
            boolean z = false;
            if (str != null && !str.isEmpty()) {
                z = true;
            }
            Preconditions.checkArgument(z, "Invalid name specified");
            return addField(new Field(str, i, null));
        }
    }

    DataTypeCreateRequest(String str, List list, IBinder iBinder) {
        this.zza = str;
        this.zzb = Collections.unmodifiableList(list);
        this.zzc = iBinder == null ? null : zzbs.zzb(iBinder);
    }

    public DataTypeCreateRequest(String str, List list, zzbt zzbtVar) {
        this.zza = str;
        this.zzb = Collections.unmodifiableList(list);
        this.zzc = zzbtVar;
    }
}
