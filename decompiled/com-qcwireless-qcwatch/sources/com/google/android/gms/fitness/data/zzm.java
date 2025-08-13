package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzm implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataSource dataSource = null;
        DataType dataType = null;
        long j = 0;
        long j2 = 0;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                j = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 2) {
                j2 = SafeParcelReader.readLong(parcel, header);
            } else if (fieldId == 3) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 4) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, header, DataSource.CREATOR);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                dataType = (DataType) SafeParcelReader.createParcelable(parcel, header, DataType.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataUpdateNotification(j, j2, i, dataSource, dataType);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new DataUpdateNotification[i];
    }
}
