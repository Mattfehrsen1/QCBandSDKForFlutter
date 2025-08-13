package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes2.dex */
public final class zzbi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzbf zzbfVar = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        PendingIntent pendingIntent = null;
        IBinder iBinder3 = null;
        String strCreateString = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    zzbfVar = (zzbf) SafeParcelReader.createParcelable(parcel, header, zzbf.CREATOR);
                    break;
                case 3:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 4:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    break;
                case 5:
                    iBinder2 = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 6:
                    iBinder3 = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 7:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 8:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbh(i, zzbfVar, iBinder, iBinder2, pendingIntent, iBinder3, strCreateString);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbh[i];
    }
}
