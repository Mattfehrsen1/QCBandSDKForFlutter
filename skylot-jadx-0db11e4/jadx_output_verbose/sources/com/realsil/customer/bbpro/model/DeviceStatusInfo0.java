package com.realsil.customer.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.realsil.customer.bbpro.i.b;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/DeviceStatusInfo0.class */
public class DeviceStatusInfo0 implements Parcelable {
    public static final byte BUD_TYPE_STEREO = 0;
    public static final byte BUD_TYPE_RWS = 1;
    public static final byte BUD_SIDE_LEFT = 1;
    public static final byte BUD_SIDE_RIGHT = 2;
    public static final Parcelable.Creator<DeviceStatusInfo0> CREATOR = new Parcelable.Creator<DeviceStatusInfo0>() { // from class: com.realsil.customer.bbpro.model.DeviceStatusInfo0.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceStatusInfo0 createFromParcel(Parcel parcel) {
            return new DeviceStatusInfo0(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceStatusInfo0[] newArray(int i) {
            return new DeviceStatusInfo0[i];
        }
    };
    public byte a;
    public byte b;
    public byte c;
    public byte d;
    public byte e;
    public int f;
    public byte g;
    public byte h;
    public int i;

    public DeviceStatusInfo0() {
        this.a = (byte) 0;
        this.b = (byte) 0;
        this.c = (byte) 0;
        this.d = (byte) 0;
        this.e = (byte) 0;
        this.f = 0;
        this.g = (byte) 0;
        this.h = (byte) 0;
        this.i = 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.a);
        parcel.writeByte(this.b);
        parcel.writeByte(this.c);
        parcel.writeByte(this.d);
        parcel.writeByte(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.g);
        parcel.writeByte(this.h);
        parcel.writeInt(this.i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setRwsState(byte b) {
        this.c = b;
    }

    public void setRwsDefaultRole(byte b) {
        this.a = b;
    }

    public void setRwsBudSide(byte b) {
        this.b = b;
    }

    public void setRwsPrimaryDefaultChannel(byte b) {
        this.d = b;
    }

    public void setRwsPrimaryChannel(byte b) {
        this.e = b;
    }

    public void setPrimaryBatStatus(int i) {
        this.f = i;
    }

    public void setSecondaryRwsChannel(byte b) {
        this.g = b;
    }

    public void setSecondaryDefaultRwsChannel(byte b) {
        this.h = b;
    }

    public void setSecondaryBatStatus(int i) {
        this.i = i;
    }

    public RwsInfo toRwsInfo(int i, int i2) {
        RwsInfo rwsInfo = new RwsInfo();
        if (b.a(i, i2) || b.a(i)) {
            rwsInfo.isRws = this.a != 0;
            if (this.b == 1) {
                rwsInfo.activeBud = 2;
                rwsInfo.leftConnected = this.c == 1;
                rwsInfo.leftActiveChannel = this.g;
                rwsInfo.leftBatteryValue = this.i;
                rwsInfo.rightConnected = true;
                rwsInfo.rightActiveChannel = this.e;
                rwsInfo.rightBatteryValue = this.f;
            } else {
                rwsInfo.activeBud = 1;
                rwsInfo.leftConnected = true;
                rwsInfo.leftActiveChannel = this.e;
                rwsInfo.leftBatteryValue = this.f;
                rwsInfo.rightConnected = this.c == 1;
                rwsInfo.rightActiveChannel = this.g;
                rwsInfo.rightBatteryValue = this.i;
            }
        } else {
            byte b = this.a;
            if (b == 1) {
                rwsInfo.isRws = true;
                byte b2 = this.d;
                if (b2 == 1) {
                    rwsInfo.activeBud = 1;
                    rwsInfo.leftConnected = true;
                    rwsInfo.leftActiveChannel = this.e;
                    rwsInfo.leftBatteryValue = this.f;
                    rwsInfo.rightConnected = this.c == 1;
                    rwsInfo.rightActiveChannel = this.g;
                    rwsInfo.rightBatteryValue = this.i;
                } else if (b2 == 2) {
                    rwsInfo.activeBud = 2;
                    rwsInfo.leftConnected = this.c == 1;
                    rwsInfo.leftActiveChannel = this.g;
                    rwsInfo.leftBatteryValue = this.i;
                    rwsInfo.rightConnected = true;
                    rwsInfo.rightActiveChannel = this.e;
                    rwsInfo.rightBatteryValue = this.f;
                } else {
                    rwsInfo.activeBud = 1;
                    rwsInfo.leftConnected = true;
                    rwsInfo.leftActiveChannel = this.e;
                    rwsInfo.leftBatteryValue = this.f;
                    rwsInfo.rightConnected = this.c == 1;
                    rwsInfo.rightActiveChannel = this.g;
                    rwsInfo.rightBatteryValue = this.i;
                }
            } else if (b == 2) {
                rwsInfo.isRws = true;
                byte b3 = this.h;
                if (b3 == 1) {
                    rwsInfo.activeBud = 1;
                    rwsInfo.leftConnected = true;
                    rwsInfo.leftActiveChannel = this.g;
                    rwsInfo.leftBatteryValue = this.f;
                    rwsInfo.rightConnected = this.c == 1;
                    rwsInfo.rightActiveChannel = this.e;
                    rwsInfo.rightBatteryValue = this.i;
                } else if (b3 == 2) {
                    rwsInfo.activeBud = 2;
                    rwsInfo.leftConnected = this.c == 1;
                    rwsInfo.leftActiveChannel = this.e;
                    rwsInfo.leftBatteryValue = this.i;
                    rwsInfo.rightConnected = true;
                    rwsInfo.rightActiveChannel = this.g;
                    rwsInfo.rightBatteryValue = this.f;
                } else {
                    rwsInfo.activeBud = 1;
                    rwsInfo.leftConnected = true;
                    rwsInfo.leftActiveChannel = this.g;
                    rwsInfo.leftBatteryValue = this.f;
                    rwsInfo.rightConnected = this.c == 1;
                    rwsInfo.rightActiveChannel = this.e;
                    rwsInfo.rightBatteryValue = this.i;
                }
            } else {
                rwsInfo.isRws = false;
                rwsInfo.leftConnected = true;
                rwsInfo.leftActiveChannel = this.e;
                rwsInfo.leftBatteryValue = this.f;
            }
        }
        return rwsInfo;
    }

    public DeviceStatusInfo0(Parcel parcel) {
        this.a = (byte) 0;
        this.b = (byte) 0;
        this.c = (byte) 0;
        this.d = (byte) 0;
        this.e = (byte) 0;
        this.f = 0;
        this.g = (byte) 0;
        this.h = (byte) 0;
        this.i = 0;
        this.a = parcel.readByte();
        this.b = parcel.readByte();
        this.c = parcel.readByte();
        this.d = parcel.readByte();
        this.e = parcel.readByte();
        this.f = parcel.readInt();
        this.g = parcel.readByte();
        this.h = parcel.readByte();
        this.i = parcel.readInt();
    }
}
