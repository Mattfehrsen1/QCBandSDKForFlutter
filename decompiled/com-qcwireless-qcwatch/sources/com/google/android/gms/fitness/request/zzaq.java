package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzaq implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        ArrayList arrayListCreateTypedList = null;
        ArrayList arrayListCreateTypedList2 = null;
        ArrayList<String> arrayListCreateStringList = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 2:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 5:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataType.CREATOR);
                    break;
                case 6:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, DataSource.CREATOR);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 8:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 9:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 10:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 11:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 12:
                    z3 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 13:
                    z4 = SafeParcelReader.readBoolean(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SessionReadRequest(strCreateString, strCreateString2, j, j2, arrayListCreateTypedList, arrayListCreateTypedList2, z, z2, arrayListCreateStringList, iBinder, z3, z4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new SessionReadRequest[i];
    }
}
