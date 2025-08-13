package com.realsil.customer.bbpro.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.realsil.customer.core.utility.DataConverter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/ImageVersionInfo.class */
public class ImageVersionInfo implements Parcelable {
    public static final Parcelable.Creator<ImageVersionInfo> CREATOR = new Parcelable.Creator<ImageVersionInfo>() { // from class: com.realsil.customer.bbpro.model.ImageVersionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageVersionInfo createFromParcel(Parcel parcel) {
            return new ImageVersionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageVersionInfo[] newArray(int i) {
            return new ImageVersionInfo[i];
        }
    };
    public byte[] a;

    public ImageVersionInfo() {
        this.a = new byte[4];
    }

    public byte[] getImageVersion() {
        return this.a;
    }

    public String getFormattedVersion() {
        byte[] bArr = this.a;
        return (bArr == null || bArr.length < 4) ? DataConverter.bytes2Hex(bArr) : String.format(Locale.US, "%d.%d.%d.%d", Byte.valueOf(bArr[0]), Byte.valueOf(this.a[1]), Byte.valueOf(this.a[2]), Byte.valueOf(this.a[3]));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 33) {
            parcel.writeBlob(this.a);
        } else {
            parcel.writeByteArray(this.a);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ImageVersionInfo(@NonNull byte[] bArr) {
        this.a = new byte[4];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferWrap.get();
        if (bArr.length >= 4) {
            byteBufferWrap.get(this.a);
        }
    }

    public ImageVersionInfo(Parcel parcel) {
        this.a = new byte[4];
        if (Build.VERSION.SDK_INT >= 33) {
            this.a = parcel.readBlob();
        } else {
            this.a = parcel.createByteArray();
        }
    }
}
