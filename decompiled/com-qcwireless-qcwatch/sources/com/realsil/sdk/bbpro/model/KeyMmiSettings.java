package com.realsil.sdk.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class KeyMmiSettings implements Parcelable {
    public static final byte BUD_PHYSICAL_DEFAULT_CHANNEL = 0;
    public static final byte BUD_PHYSICAL_L_CHANNEL = 1;
    public static final byte BUD_PHYSICAL_R_CHANNEL = 2;
    public static final Parcelable.Creator<KeyMmiSettings> CREATOR = new Parcelable.Creator<KeyMmiSettings>() { // from class: com.realsil.sdk.bbpro.model.KeyMmiSettings.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyMmiSettings createFromParcel(Parcel parcel) {
            return new KeyMmiSettings(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyMmiSettings[] newArray(int i) {
            return new KeyMmiSettings[i];
        }
    };
    public static final byte KEY_CALL_STATUS_CALL_IDLE = 0;
    public static final byte KEY_CALL_STATUS_NOT_CALL_IDLE = 1;
    public static final byte KEY_CLICK_LONG_PRESS = 4;
    public static final byte KEY_CLICK_MULTI_2 = 2;
    public static final byte KEY_CLICK_MULTI_3 = 3;
    public static final byte KEY_CLICK_SINGLE = 1;
    public static final byte KEY_CLICK_UTRAL_LONG_PRESS = 5;
    public byte a;
    public byte b;
    public byte c;
    public byte d;

    public KeyMmiSettings() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte getBud() {
        return this.a;
    }

    public byte getClickType() {
        return this.c;
    }

    public byte getMmiIndex() {
        return this.d;
    }

    public byte getScenario() {
        return this.b;
    }

    public void setBud(byte b) {
        this.a = b;
    }

    public void setClickType(byte b) {
        this.c = b;
    }

    public void setMmiIndex(byte b) {
        this.d = b;
    }

    public void setScenario(byte b) {
        this.b = b;
    }

    public String toString() {
        return String.format("bud=0x%02X,call=0x%02X,clickType=0x%02X,mmiIndex=0x%02X\n", Byte.valueOf(this.a), Byte.valueOf(this.b), Byte.valueOf(this.c), Byte.valueOf(this.d));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a);
        parcel.writeByte(this.b);
        parcel.writeByte(this.c);
        parcel.writeByte(this.d);
    }

    public KeyMmiSettings(Parcel parcel) {
        this.a = parcel.readByte();
        this.b = parcel.readByte();
        this.c = parcel.readByte();
        this.d = parcel.readByte();
    }
}
