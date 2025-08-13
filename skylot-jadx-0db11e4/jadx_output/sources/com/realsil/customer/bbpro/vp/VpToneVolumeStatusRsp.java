package com.realsil.customer.bbpro.vp;

import com.realsil.customer.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/VpToneVolumeStatusRsp.class */
public final class VpToneVolumeStatusRsp {
    public int leftMinVolumeLevel;
    public int leftMaxVolumeLevel;
    public byte leftCurVolumeLevel;
    public int rightMinVolumeLevel;
    public int rightMaxVolumeLevel;
    public byte rightCurVolumeLevel;
    public boolean rwsSyncSupported;
    public boolean rwsSyncEnabled;

    public VpToneVolumeStatusRsp(int i, int i2, byte b, int i3, int i4, byte b2, boolean z, boolean z2) {
        this.leftMinVolumeLevel = i;
        this.leftMaxVolumeLevel = i2;
        this.leftCurVolumeLevel = b;
        this.rightMinVolumeLevel = i3;
        this.rightMaxVolumeLevel = i4;
        this.rightCurVolumeLevel = b2;
        this.rwsSyncSupported = z;
        this.rwsSyncEnabled = z2;
    }

    public static VpToneVolumeStatusRsp parse(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            ZLogger.v("invalid packet");
            return null;
        }
        int i = bArr[0] & 255;
        int i2 = bArr[1] & 255;
        byte b = bArr[2];
        int i3 = bArr[3] & 255;
        int i4 = bArr[4] & 255;
        byte b2 = bArr[5];
        boolean z = false;
        boolean z2 = false;
        if (bArr.length >= 7) {
            byte b3 = bArr[6];
            if ((b3 & 255) != 255) {
                z = true;
                z2 = (b3 & 1) == 1;
            }
        }
        return new VpToneVolumeStatusRsp(i, i2, b, i3, i4, b2, z, z2);
    }

    public VpVolumeInfo toVpVolumeInfo() {
        return new VpVolumeInfo(this.leftMinVolumeLevel, this.leftMaxVolumeLevel, this.leftCurVolumeLevel, this.rightMinVolumeLevel, this.rightMaxVolumeLevel, this.rightCurVolumeLevel, this.rwsSyncSupported, this.rwsSyncEnabled);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VpToneVolumeStatusRsp {");
        if (this.rwsSyncSupported) {
            sb.append(String.format("\n\trwsSyncEnabled=%b", Boolean.valueOf(this.rwsSyncEnabled)));
        } else {
            sb.append(String.format("\n\trwsSyncSupported=%b", Boolean.FALSE));
        }
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tL=%d/(%d~%d)", Byte.valueOf(this.leftCurVolumeLevel), Integer.valueOf(this.leftMinVolumeLevel), Integer.valueOf(this.leftMaxVolumeLevel)));
        sb.append(String.format(locale, "\n\tR=%d/(%d~%d)", Byte.valueOf(this.rightCurVolumeLevel), Integer.valueOf(this.rightMinVolumeLevel), Integer.valueOf(this.rightMaxVolumeLevel)));
        sb.append("\n}");
        return sb.toString();
    }
}
