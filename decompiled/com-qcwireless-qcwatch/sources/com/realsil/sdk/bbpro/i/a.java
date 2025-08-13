package com.realsil.sdk.bbpro.i;

import com.realsil.sdk.core.utility.DataConverter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class a {
    public byte a;
    public byte[] b;

    public a(byte b, byte[] bArr) {
        this.a = b;
        this.b = bArr;
    }

    public int a() {
        byte[] bArr;
        if (3 != this.a || (bArr = this.b) == null || bArr.length < 4) {
            return 0;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferWrap.getShort();
    }

    public String b() {
        byte[] bArr;
        return (4 != this.a || (bArr = this.b) == null || bArr.length <= 0) ? "" : new String(bArr, 0, bArr.length, StandardCharsets.UTF_8).trim();
    }

    public String c() {
        byte[] bArr;
        return (2 != this.a || (bArr = this.b) == null || bArr.length <= 0) ? "" : new String(bArr, 0, bArr.length, StandardCharsets.UTF_8).trim();
    }

    public String d() {
        byte[] bArr;
        return (5 != this.a || (bArr = this.b) == null || bArr.length <= 0) ? "" : new String(bArr, 0, bArr.length, StandardCharsets.UTF_8).trim();
    }

    public String e() {
        byte[] bArr;
        return (this.a != 0 || (bArr = this.b) == null || bArr.length <= 0) ? "" : new String(bArr, 0, bArr.length, StandardCharsets.UTF_8).trim();
    }

    public String f() {
        byte[] bArr;
        return (1 != this.a || (bArr = this.b) == null || bArr.length <= 0) ? "" : new String(bArr, 0, bArr.length, StandardCharsets.UTF_8).trim();
    }

    public int g() {
        byte[] bArr = this.b;
        if (bArr == null || bArr.length < 4) {
            return 0;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferWrap.getShort(2);
    }

    public String h() {
        byte[] bArr;
        return (6 != this.a || (bArr = this.b) == null || bArr.length <= 0) ? "" : new String(bArr, 0, bArr.length, StandardCharsets.UTF_8).trim();
    }

    public byte i() {
        return this.a;
    }

    public String toString() {
        return String.format(Locale.US, "CfgSettingsInfo{ type=0x%04X, cfgData=%s }", Byte.valueOf(this.a), DataConverter.bytes2Hex(this.b));
    }

    public static a a(byte[] bArr) {
        byte[] bArrB;
        if (bArr != null && bArr.length >= 1) {
            int length = bArr.length;
            byte b = bArr[0];
            if (b == 0) {
                if (length < 2) {
                    return null;
                }
                int i = bArr[1] & 255;
                if (length < i + 2) {
                    return null;
                }
                bArrB = new byte[i];
                System.arraycopy(bArr, 2, bArrB, 0, i);
            } else if (1 == b) {
                if (length < 2) {
                    return null;
                }
                int i2 = bArr[1] & 255;
                if (length < i2 + 2) {
                    return null;
                }
                bArrB = new byte[i2];
                System.arraycopy(bArr, 2, bArrB, 0, i2);
            } else if (2 == b) {
                if (length < 2) {
                    return null;
                }
                int i3 = bArr[1] & 255;
                if (length < i3 + 2) {
                    return null;
                }
                bArrB = new byte[i3];
                System.arraycopy(bArr, 2, bArrB, 0, i3);
            } else if (3 == b) {
                if (length < 5) {
                    return null;
                }
                bArrB = new byte[4];
                System.arraycopy(bArr, 1, bArrB, 0, 4);
            } else if (4 == b) {
                if (length < 2) {
                    return null;
                }
                int i4 = length - 1;
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, 1, bArr2, 0, i4);
                bArrB = b(bArr2);
            } else if (5 == b) {
                if (length < 2) {
                    return null;
                }
                int i5 = length - 1;
                byte[] bArr3 = new byte[i5];
                System.arraycopy(bArr, 1, bArr3, 0, i5);
                bArrB = b(bArr3);
            } else {
                if (6 != b || length < 2) {
                    return null;
                }
                int i6 = length - 1;
                byte[] bArr4 = new byte[i6];
                System.arraycopy(bArr, 1, bArr4, 0, i6);
                bArrB = b(bArr4);
            }
            return new a(b, bArrB);
        }
        return null;
    }

    public static byte[] b(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length && bArr[i2] != 0; i2++) {
            i++;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }
}
