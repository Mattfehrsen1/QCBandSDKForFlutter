package com.realsil.sdk.bbpro.vp;

import java.util.Locale;

/* loaded from: classes3.dex */
public final class VpToneVolumeLevelSetRsp {
    public byte leftCurVolumeLevel;
    public int leftMaxVolumeLevel;
    public int leftMinVolumeLevel;
    public byte rightCurVolumeLevel;
    public int rightMaxVolumeLevel;
    public int rightMinVolumeLevel;
    public boolean rwsSyncEnabled;
    public boolean rwsSyncSupported;
    public int status;

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

    /* JADX WARN: Removed duplicated region for block: B:13:0x0034 A[PHI: r0
      0x0034: PHI (r0v2 boolean) = (r0v1 boolean), (r0v1 boolean), (r0v4 boolean) binds: [B:7:0x0025, B:9:0x002a, B:11:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.realsil.sdk.bbpro.vp.VpToneVolumeLevelSetRsp parse(byte[] r13) {
        /*
            if (r13 == 0) goto L42
            int r0 = r13.length
            r1 = 7
            if (r0 >= r1) goto L7
            goto L42
        L7:
            r0 = 0
            r3 = r13[r0]
            r2 = 1
            r4 = r13[r2]
            r5 = 255(0xff, float:3.57E-43)
            r4 = r4 & r5
            r6 = 2
            r6 = r13[r6]
            r6 = r6 & r5
            r7 = 3
            r7 = r13[r7]
            r8 = 4
            r8 = r13[r8]
            r8 = r8 & r5
            r9 = 5
            r9 = r13[r9]
            r9 = r9 & r5
            r10 = 6
            r10 = r13[r10]
            int r11 = r13.length
            r12 = 8
            if (r11 < r12) goto L34
            r11 = r13[r1]
            r11 = r11 & r5
            if (r11 == r5) goto L34
            r13 = r13[r1]
            r13 = r13 & r2
            r0 = 1
            if (r13 != r2) goto L34
            r11 = 1
            goto L35
        L34:
            r11 = 0
        L35:
            com.realsil.sdk.bbpro.vp.VpToneVolumeLevelSetRsp r13 = new com.realsil.sdk.bbpro.vp.VpToneVolumeLevelSetRsp
            r2 = r13
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        L42:
            java.lang.String r13 = "invalid packet"
            com.realsil.sdk.core.logger.ZLogger.v(r13)
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.bbpro.vp.VpToneVolumeLevelSetRsp.parse(byte[]):com.realsil.sdk.bbpro.vp.VpToneVolumeLevelSetRsp");
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

    public VpVolumeInfo toVpVolumeInfo() {
        return new VpVolumeInfo(this.leftMinVolumeLevel, this.leftMaxVolumeLevel, this.leftCurVolumeLevel, this.rightMinVolumeLevel, this.rightMaxVolumeLevel, this.rightCurVolumeLevel, this.rwsSyncSupported, this.rwsSyncEnabled);
    }
}
