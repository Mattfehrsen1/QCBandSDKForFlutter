package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class SessionStopResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzi();
    private final Status zza;
    private final List zzb;

    public SessionStopResult(Status status, List list) {
        this.zza = status;
        this.zzb = Collections.unmodifiableList(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionStopResult)) {
            return false;
        }
        SessionStopResult sessionStopResult = (SessionStopResult) obj;
        return this.zza.equals(sessionStopResult.zza) && Objects.equal(this.zzb, sessionStopResult.zzb);
    }

    public List<Session> getSessions() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zza).add("sessions", this.zzb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getSessions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
