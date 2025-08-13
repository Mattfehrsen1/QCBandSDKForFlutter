package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzc implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Status status = null;
        ArrayList arrayListCreateTypedList = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                SafeParcelReader.readList(parcel, header, arrayList, getClass().getClassLoader());
            } else if (fieldId == 2) {
                status = (Status) SafeParcelReader.createParcelable(parcel, header, Status.CREATOR);
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, header, arrayList2, getClass().getClassLoader());
            } else if (fieldId == 5) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataSource.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataReadResult(arrayList, status, arrayList2, i, arrayListCreateTypedList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataReadResult[i];
    }
}
