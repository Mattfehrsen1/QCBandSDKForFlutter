package com.realsil.customer.bbpro.c;

import com.realsil.customer.core.logger.ZLogger;
import com.realsil.customer.core.utility.DataConverter;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/c/a.class */
public class a {
    public int a;
    public byte b;
    public byte c;
    public byte[] d;
    public int e;

    public boolean b(byte[] bArr) {
        if (bArr == null || bArr.length < 3) {
            ZLogger.w("invalid AudioEq:" + DataConverter.bytes2Hex(bArr));
            return false;
        }
        this.a = bArr[0] & 255;
        this.b = bArr[1];
        this.c = bArr[2];
        int length = bArr.length - 3;
        this.e = length;
        if (length <= 0) {
            return true;
        }
        byte[] bArr2 = new byte[length];
        this.d = bArr2;
        System.arraycopy(bArr, 3, bArr2, 0, length);
        return true;
    }

    public byte a() {
        return this.b;
    }

    public byte[] c() {
        return this.d;
    }

    public static a a(byte[] bArr) {
        a aVar = new a();
        if (aVar.b(bArr)) {
            return aVar;
        }
        return null;
    }

    public byte[] c(byte[] bArr) {
        if (bArr == null) {
            return this.d;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[this.e + length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        System.arraycopy(this.d, 0, bArr2, length, this.e);
        return bArr2;
    }

    public byte b() {
        return this.c;
    }
}
