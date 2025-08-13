package com.realsil.customer.bbpro.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/OtaFwVersionInfo.class */
public class OtaFwVersionInfo implements Parcelable {
    public static final Parcelable.Creator<OtaFwVersionInfo> CREATOR = new Parcelable.Creator<OtaFwVersionInfo>() { // from class: com.realsil.customer.bbpro.model.OtaFwVersionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaFwVersionInfo createFromParcel(Parcel parcel) {
            return new OtaFwVersionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaFwVersionInfo[] newArray(int i) {
            return new OtaFwVersionInfo[i];
        }
    };
    public byte[] a;
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public byte[] g;
    public byte[] h;
    public byte[] i;
    public byte[] j;

    public OtaFwVersionInfo(@NonNull byte[] bArr) {
        this.a = new byte[4];
        this.b = new byte[4];
        this.c = new byte[4];
        this.d = new byte[4];
        this.e = new byte[4];
        this.f = new byte[4];
        this.g = new byte[4];
        this.h = new byte[4];
        this.i = new byte[4];
        this.j = new byte[12];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int length = bArr.length;
        if (length >= 5) {
            byteBufferWrap.get(this.a);
        }
        if (length >= 9) {
            byteBufferWrap.get(this.b);
        }
        if (length >= 13) {
            byteBufferWrap.get(this.c);
        }
        if (length >= 17) {
            byteBufferWrap.get(this.d);
        }
        if (length >= 21) {
            byteBufferWrap.get(this.e);
        }
        if (length >= 25) {
            byteBufferWrap.get(this.f);
        }
        if (length >= 29) {
            byteBufferWrap.get(this.g);
        }
        if (length >= 33) {
            byteBufferWrap.get(this.h);
        }
        if (length >= 37) {
            byteBufferWrap.get(this.i);
        }
        if (length >= 49) {
            byteBufferWrap.get(this.j);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 33) {
            parcel.writeBlob(this.a);
            parcel.writeBlob(this.b);
            parcel.writeBlob(this.c);
            parcel.writeBlob(this.d);
            parcel.writeBlob(this.e);
            parcel.writeBlob(this.f);
            parcel.writeBlob(this.g);
            parcel.writeBlob(this.h);
            parcel.writeBlob(this.i);
            parcel.writeBlob(this.j);
            return;
        }
        parcel.writeByteArray(this.a);
        parcel.writeByteArray(this.b);
        parcel.writeByteArray(this.c);
        parcel.writeByteArray(this.d);
        parcel.writeByteArray(this.e);
        parcel.writeByteArray(this.f);
        parcel.writeByteArray(this.g);
        parcel.writeByteArray(this.h);
        parcel.writeByteArray(this.i);
        parcel.writeByteArray(this.j);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OtaFwVersionInfo(Parcel parcel) {
        this.a = new byte[4];
        this.b = new byte[4];
        this.c = new byte[4];
        this.d = new byte[4];
        this.e = new byte[4];
        this.f = new byte[4];
        this.g = new byte[4];
        this.h = new byte[4];
        this.i = new byte[4];
        this.j = new byte[12];
        if (Build.VERSION.SDK_INT >= 33) {
            this.a = parcel.readBlob();
            this.b = parcel.readBlob();
            this.c = parcel.readBlob();
            this.d = parcel.readBlob();
            this.e = parcel.readBlob();
            this.f = parcel.readBlob();
            this.g = parcel.readBlob();
            this.h = parcel.readBlob();
            this.i = parcel.readBlob();
            this.j = parcel.readBlob();
            return;
        }
        this.a = parcel.createByteArray();
        this.b = parcel.createByteArray();
        this.c = parcel.createByteArray();
        this.d = parcel.createByteArray();
        this.e = parcel.createByteArray();
        this.f = parcel.createByteArray();
        this.g = parcel.createByteArray();
        this.h = parcel.createByteArray();
        this.i = parcel.createByteArray();
        this.j = parcel.createByteArray();
    }
}
