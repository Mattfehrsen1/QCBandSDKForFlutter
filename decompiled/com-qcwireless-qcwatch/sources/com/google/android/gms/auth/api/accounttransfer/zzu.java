package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzu implements Parcelable.Creator<zzt> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzt createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        zzv zzvVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, header);
                hashSet.add(1);
            } else if (fieldId == 2) {
                zzvVar = (zzv) SafeParcelReader.createParcelable(parcel, header, zzv.CREATOR);
                hashSet.add(2);
            } else if (fieldId == 3) {
                strCreateString = SafeParcelReader.createString(parcel, header);
                hashSet.add(3);
            } else if (fieldId == 4) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
                hashSet.add(4);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                strCreateString3 = SafeParcelReader.createString(parcel, header);
                hashSet.add(5);
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzt(hashSet, i, zzvVar, strCreateString, strCreateString2, strCreateString3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzt[] newArray(int i) {
        return new zzt[i];
    }
}
