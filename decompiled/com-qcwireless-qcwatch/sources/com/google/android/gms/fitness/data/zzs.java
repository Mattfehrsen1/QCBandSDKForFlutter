package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzs implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        long j = 0;
        Goal.Recurrence recurrence = null;
        Goal.MetricObjective metricObjective = null;
        Goal.DurationObjective durationObjective = null;
        Goal.FrequencyObjective frequencyObjective = null;
        int i = 0;
        long j2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 2:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 3:
                    SafeParcelReader.readList(parcel, header, arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    recurrence = (Goal.Recurrence) SafeParcelReader.createParcelable(parcel, header, Goal.Recurrence.CREATOR);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 6:
                    metricObjective = (Goal.MetricObjective) SafeParcelReader.createParcelable(parcel, header, Goal.MetricObjective.CREATOR);
                    break;
                case 7:
                    durationObjective = (Goal.DurationObjective) SafeParcelReader.createParcelable(parcel, header, Goal.DurationObjective.CREATOR);
                    break;
                case 8:
                    frequencyObjective = (Goal.FrequencyObjective) SafeParcelReader.createParcelable(parcel, header, Goal.FrequencyObjective.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Goal(j2, j, arrayList, recurrence, i, metricObjective, durationObjective, frequencyObjective);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Goal[i];
    }
}
