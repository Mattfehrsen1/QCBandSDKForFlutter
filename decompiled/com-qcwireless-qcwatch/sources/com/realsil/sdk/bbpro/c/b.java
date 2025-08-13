package com.realsil.sdk.bbpro.c;

import androidx.core.view.MotionEventCompat;
import com.realsil.sdk.bbpro.core.transportlayer.EventPacket;

/* loaded from: classes3.dex */
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

    @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
    public boolean parse(byte[] bArr) {
        if (!super.parse(bArr)) {
            return false;
        }
        int i = this.paramsLen;
        if (i > 1) {
            byte[] bArr2 = this.mEventParams;
            this.a = (short) ((bArr2[0] & 255) | ((bArr2[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
        }
        if (i > 1) {
            byte[] bArr3 = this.mEventParams;
            this.b = (short) (((bArr3[3] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (bArr3[2] & 255));
        }
        return true;
    }

    @Override // com.realsil.sdk.bbpro.core.transportlayer.EventPacket
    public String toString() {
        return String.format("currentIndex=0x%04X, supportedIndex=0x%04X ", Short.valueOf(this.a), Short.valueOf(this.b));
    }
}
