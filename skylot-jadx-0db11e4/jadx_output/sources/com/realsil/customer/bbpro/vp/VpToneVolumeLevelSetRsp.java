package com.realsil.customer.bbpro.vp;

import com.realsil.customer.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/vp/VpToneVolumeLevelSetRsp.class */
public final class VpToneVolumeLevelSetRsp {
    public int status;
    public int leftMinVolumeLevel;
    public int leftMaxVolumeLevel;
    public byte leftCurVolumeLevel;
    public int rightMinVolumeLevel;
    public int rightMaxVolumeLevel;
    public byte rightCurVolumeLevel;
    public boolean rwsSyncSupported;
    public boolean rwsSyncEnabled;

    public VpToneVolumeLevelSetRsp(int i, int i2, int i3, byte b, int i4, int i5, byte b2, boolean z, boolean z2) {
        this.status = i;
        this.leftMinVolumeLevel = i2;
        this.leftMaxVolumeLevel = i3;
        this.leftCurVolumeLevel = b;
        this.rightMinVolumeLevel = i4;
        this.rightMaxVolumeLevel = i5;
        this.rightCurVolumeLevel = b2;
        this.rwsSyncSupported = z;
        this.rwsSyncEnabled = z2;
    }

    public static VpToneVolumeLevelSetRsp parse(byte[] bArr) {
        if (bArr == null || bArr.length < 7) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        int i = bArr[1] & 255;
        int i2 = bArr[2] & 255;
        byte b2 = bArr[3];
        int i3 = bArr[4] & 255;
        int i4 = bArr[5] & 255;
        byte b3 = bArr[6];
        boolean z = false;
        boolean z2 = false;
        if (bArr.length >= 8) {
            byte b4 = bArr[7];
            if ((b4 & 255) != 255) {
                z = true;
                z2 = (b4 & 1) == 1;
            }
        }
        return new VpToneVolumeLevelSetRsp(b, i, i2, b2, i3, i4, b3, z, z2);
    }

    public VpVolumeInfo toVpVolumeInfo() {
        return new VpVolumeInfo(this.leftMinVolumeLevel, this.leftMaxVolumeLevel, this.leftCurVolumeLevel, this.rightMinVolumeLevel, this.rightMaxVolumeLevel, this.rightCurVolumeLevel, this.rwsSyncSupported, this.rwsSyncEnabled);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Locale locale = Locale.US;
        sb.append(String.format(locale, "VpToneVolumeLevelSetRsp { status=0x%02X", Integer.valueOf(this.status)));
        if (this.rwsSyncSupported) {
            sb.append(String.format("\n\trwsSyncEnabled=%b", Boolean.valueOf(this.rwsSyncEnabled)));
        } else {
            sb.append(String.format("\n\trwsSyncSupported=%b", Boolean.FALSE));
        }
        sb.append(String.format(locale, "\n\tL=%d/(%d~%d)", Byte.valueOf(this.leftCurVolumeLevel), Integer.valueOf(this.leftMinVolumeLevel), Integer.valueOf(this.leftMaxVolumeLevel)));
        sb.append(String.format(locale, "\n\tR=%d/(%d~%d)", Byte.valueOf(this.rightCurVolumeLevel), Integer.valueOf(this.rightMinVolumeLevel), Integer.valueOf(this.rightMaxVolumeLevel)));
        sb.append("\n}");
        return sb.toString();
    }
}
