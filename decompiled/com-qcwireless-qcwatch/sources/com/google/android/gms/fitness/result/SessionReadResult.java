package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class SessionReadResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzh();
    private final List zza;
    private final List zzb;
    private final Status zzc;

    public SessionReadResult(List list, List list2, Status status) {
        this.zza = list;
        this.zzb = Collections.unmodifiableList(list2);
        this.zzc = status;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionReadResult)) {
            return false;
        }
        SessionReadResult sessionReadResult = (SessionReadResult) obj;
        return this.zzc.equals(sessionReadResult.zzc) && Objects.equal(this.zza, sessionReadResult.zza) && Objects.equal(this.zzb, sessionReadResult.zzb);
    }

    public List<DataSet> getDataSet(Session session) {
        Preconditions.checkArgument(this.zza.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzad zzadVar : this.zzb) {
            if (Objects.equal(session, zzadVar.zzb())) {
                arrayList.add(zzadVar.zza());
            }
        }
        return arrayList;
    }

    public List<Session> getSessions() {
        return this.zza;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzc, this.zza, this.zzb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzc).add("sessions", this.zza).add("sessionDataSets", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSessions(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        Preconditions.checkArgument(this.zza.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzad zzadVar : this.zzb) {
            if (Objects.equal(session, zzadVar.zzb()) && dataType.equals(zzadVar.zza().getDataType())) {
                arrayList.add(zzadVar.zza());
            }
        }
        return arrayList;
    }
}
