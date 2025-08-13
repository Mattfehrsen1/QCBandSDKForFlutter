package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class ListSubscriptionsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new zzg();
    private final List zza;
    private final Status zzb;

    public ListSubscriptionsResult(List list, Status status) {
        this.zza = list;
        this.zzb = status;
    }

    public static ListSubscriptionsResult zza(Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListSubscriptionsResult)) {
            return false;
        }
        ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
        return this.zzb.equals(listSubscriptionsResult.zzb) && Objects.equal(this.zza, listSubscriptionsResult.zza);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzb;
    }

    public List<Subscription> getSubscriptions() {
        return this.zza;
    }

    public List<Subscription> getSubscriptions(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : this.zza) {
            if (dataType.equals(subscription.zza())) {
                arrayList.add(subscription);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, this.zza);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.zzb).add("subscriptions", this.zza).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSubscriptions(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
