package com.realsil.customer.bbpro.c;

import com.realsil.customer.bbpro.core.transportlayer.EventPacket;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/c/b.class */
public class b extends EventPacket {
    public short a;
    public short b;

    public static b a(byte[] bArr) {
        b bVar = new b();
        if (bVar.parse(bArr)) {
            return bVar;
        }
        return null;
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.EventPacket
    public boolean parse(byte[] bArr) {
        if (!super.parse(bArr)) {
            return false;
        }
        int i = this.paramsLen;
        if (i > 1) {
            byte[] bArr2 = this.mEventParams;
            this.a = (short) ((bArr2[0] & 255) | ((bArr2[1] << 8) & 65280));
        }
        if (i <= 1) {
            return true;
        }
        byte[] bArr3 = this.mEventParams;
        this.b = (short) ((bArr3[2] & 255) | ((bArr3[3] << 8) & 65280));
        return true;
    }

    @Override // com.realsil.customer.bbpro.core.transportlayer.EventPacket
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("currentIndex=0x%04X, supportedIndex=0x%04X ", Short.valueOf(this.a), Short.valueOf(this.b)));
        return sb.toString();
    }
}
