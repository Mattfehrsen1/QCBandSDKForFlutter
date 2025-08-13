package com.realsil.sdk.bbpro.j;

import com.realsil.sdk.bbpro.model.FwVersionInfo;
import com.realsil.sdk.bbpro.model.ImageVersionInfo;
import com.realsil.sdk.bbpro.model.OtaFwVersionInfo;

/* loaded from: classes3.dex */
public final class b {
    public byte a;
    public FwVersionInfo b;
    public FwVersionInfo c;
    public OtaFwVersionInfo d;
    public OtaFwVersionInfo e;
    public ImageVersionInfo f;
    public ImageVersionInfo g;

    public b(byte b) {
        this.a = b;
    }

    public static b a(byte[] bArr) {
        if ((bArr != null ? bArr.length : 0) < 1) {
            return null;
        }
        byte b = bArr[0];
        b bVar = new b(b);
        if (b == 0) {
            bVar.b = new FwVersionInfo(bArr);
        } else if (b == 1) {
            bVar.c = new FwVersionInfo(bArr);
        } else if (b == 2) {
            bVar.d = new OtaFwVersionInfo(bArr);
        } else if (b == 3) {
            bVar.e = new OtaFwVersionInfo(bArr);
        } else if (b == 4) {
            bVar.f = new ImageVersionInfo(bArr);
        } else if (b == 5) {
            bVar.g = new ImageVersionInfo(bArr);
        }
        return bVar;
    }

    public String toString() {
        ImageVersionInfo imageVersionInfo;
        StringBuilder sb = new StringBuilder();
        sb.append("FwVersionInfo{");
        sb.append(String.format("type=0x%02X", Byte.valueOf(this.a)));
        byte b = this.a;
        if (b != 0 && b != 1 && b != 2 && b != 3) {
            if (b == 4) {
                ImageVersionInfo imageVersionInfo2 = this.f;
                if (imageVersionInfo2 != null) {
                    sb.append(imageVersionInfo2.getFormattedVersion());
                }
            } else if (b == 5 && (imageVersionInfo = this.g) != null) {
                sb.append(imageVersionInfo.getFormattedVersion());
            }
        }
        sb.append("\n}");
        return sb.toString();
    }
}
