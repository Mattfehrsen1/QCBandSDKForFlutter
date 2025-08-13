package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes2.dex */
public class StampStyle extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StampStyle> CREATOR = new zzo();
    protected final BitmapDescriptor zza;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    static abstract class Builder<T extends Builder<T>> {
        BitmapDescriptor zza;

        Builder() {
        }

        protected abstract T self();

        public T stamp(BitmapDescriptor bitmapDescriptor) {
            this.zza = bitmapDescriptor;
            return (T) self();
        }
    }

    StampStyle(IBinder iBinder) {
        this.zza = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
    }

    public BitmapDescriptor getStamp() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zza.zza().asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    protected StampStyle(BitmapDescriptor bitmapDescriptor) {
        this.zza = bitmapDescriptor;
    }
}
