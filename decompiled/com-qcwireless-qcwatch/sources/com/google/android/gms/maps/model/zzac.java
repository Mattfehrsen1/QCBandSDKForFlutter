package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public final class zzac implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        boolean z = false;
        float f = 0.0f;
        boolean z2 = true;
        float f2 = 0.0f;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            } else if (fieldId == 3) {
                z = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId == 4) {
                f = SafeParcelReader.readFloat(parcel, header);
            } else if (fieldId == 5) {
                z2 = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 6) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                f2 = SafeParcelReader.readFloat(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new TileOverlayOptions(iBinder, z, f, z2, f2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new TileOverlayOptions[i];
    }
}
