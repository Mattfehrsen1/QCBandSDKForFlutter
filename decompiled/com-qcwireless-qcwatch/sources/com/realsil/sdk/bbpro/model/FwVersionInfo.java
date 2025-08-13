package com.realsil.sdk.bbpro.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes3.dex */
public class FwVersionInfo implements Parcelable {
    public static final Parcelable.Creator<FwVersionInfo> CREATOR = new Parcelable.Creator<FwVersionInfo>() { // from class: com.realsil.sdk.bbpro.model.FwVersionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FwVersionInfo createFromParcel(Parcel parcel) {
            return new FwVersionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FwVersionInfo[] newArray(int i) {
            return new FwVersionInfo[i];
        }
    };
    public byte[] a;
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public byte[] f;

    public FwVersionInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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
            return;
        }
        parcel.writeByteArray(this.a);
        parcel.writeByteArray(this.b);
        parcel.writeByteArray(this.c);
        parcel.writeByteArray(this.d);
        parcel.writeByteArray(this.e);
        parcel.writeByteArray(this.f);
    }

    public FwVersionInfo(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        int length = bArr.length;
        if (length >= 6) {
            byteBufferWrap.get(this.a);
        }
        if (length >= 10) {
            byteBufferWrap.get(this.b);
        }
        if (length >= 26) {
            byteBufferWrap.get(this.c);
        }
        if (length >= 32) {
            byteBufferWrap.get(this.d);
        }
        if (length >= 36) {
            byteBufferWrap.get(this.e);
        }
        if (length >= 40) {
            byteBufferWrap.get(this.f);
        }
    }

    public FwVersionInfo(Parcel parcel) {
        if (Build.VERSION.SDK_INT >= 33) {
            this.a = parcel.readBlob();
            this.b = parcel.readBlob();
            this.c = parcel.readBlob();
            this.d = parcel.readBlob();
            this.e = parcel.readBlob();
            this.f = parcel.readBlob();
            return;
        }
        this.a = parcel.createByteArray();
        this.b = parcel.createByteArray();
        this.c = parcel.createByteArray();
        this.d = parcel.createByteArray();
        this.e = parcel.createByteArray();
        this.f = parcel.createByteArray();
    }
}
