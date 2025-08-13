package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import java.util.Locale;
import org.koin.core.instance.DefinitionInstance;

/* loaded from: classes3.dex */
public class EqIndex {
    public static final int INVALID_INDEX = 255;
    public static final boolean VDBG = false;
    public AudioEq audioEq;
    public int eqBud;
    public byte[] eqData;
    public byte[] eqInfo;
    public int eqType;
    public int index;
    public int mode;
    public boolean modified;
    public String nickName;
    public byte sampleRate;

    public EqIndex(int i, String str, boolean z, byte b, byte[] bArr) {
        this.eqBud = 2;
        this.eqType = 0;
        this.index = i;
        this.nickName = str;
        this.modified = z;
        this.sampleRate = b;
        this.eqData = bArr;
    }

    public static EqIndex a(byte[] bArr) {
        int i;
        if (bArr == null || bArr.length < 3) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        int i2 = bArr[1] & 255;
        byte b2 = bArr[2];
        if (bArr.length < i2 + 2 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 3, bArr2, 0, i);
        return new EqIndex(b, String.format(Locale.US, "EQ %d", Byte.valueOf(b)), true, b2, bArr2);
    }

    public static EqIndex b(byte[] bArr) {
        int i;
        if (bArr == null || bArr.length < 6) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        int i2 = ((bArr[4] << 8) | (bArr[3] & 255)) & 65535;
        byte b4 = bArr[5];
        if (bArr.length < i2 + 5 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=0X%04x(%d)", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 6, bArr2, 0, i);
        return new EqIndex(b, String.format(Locale.US, "EQ %d", Byte.valueOf(b)), true, b4, bArr2);
    }

    public static EqIndex c(byte[] bArr) {
        int i;
        if (bArr == null || bArr.length < 7) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        String str = b2 == 1 ? String.format(Locale.US, "Gaming Mode EQ %d", Byte.valueOf(b)) : b2 == 2 ? String.format(Locale.US, "ANC Mode EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "Normal Mode EQ %d", Byte.valueOf(b));
        int i2 = ((bArr[5] << 8) | (bArr[4] & 255)) & 65535;
        byte b5 = bArr[6];
        if (bArr.length < i2 + 6 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=0X%04x(%d)", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 7, bArr2, 0, i);
        return new EqIndex(b2, b, str, true, b5, bArr2);
    }

    public static EqIndex d(byte[] bArr) {
        int i;
        AudioEq audioEq;
        if (bArr == null || bArr.length < 7) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        String str = b2 == 1 ? String.format(Locale.US, "Gaming EQ %d", Byte.valueOf(b)) : b2 == 2 ? String.format(Locale.US, "ANC EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "Normal EQ %d", Byte.valueOf(b));
        int i2 = ((bArr[5] << 8) | (bArr[4] & 255)) & 65535;
        byte b5 = bArr[6];
        if (bArr.length < i2 + 6 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=0X%04x(%d)", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 7, bArr2, 0, i);
        EqParameterInfo eqParameterInfo = EqParameterInfo.parse(bArr2);
        if (eqParameterInfo != null) {
            ZLogger.v(eqParameterInfo.toString());
            audioEq = new AudioEq(eqParameterInfo.stageNum, eqParameterInfo.globalGain, eqParameterInfo.samplingFreqIndex, eqParameterInfo.accuracy, eqParameterInfo.freq, eqParameterInfo.q, eqParameterInfo.gains, eqParameterInfo.biquadType, null);
        } else {
            ZLogger.d("invalid EqParameterInfo");
            audioEq = new AudioEq();
        }
        return new EqIndex(0, b2, b, str, true, b5, null, bArr2, audioEq);
    }

    public static EqIndex e(byte[] bArr) {
        int i;
        AudioEq audioEq;
        if (bArr == null || bArr.length < 8) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        String str = b2 == 1 ? String.format(Locale.US, "Gaming EQ %d", Byte.valueOf(b)) : b2 == 2 ? String.format(Locale.US, "ANC EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "Normal EQ %d", Byte.valueOf(b));
        int i2 = ((bArr[5] << 8) | (bArr[4] & 255)) & 65535;
        byte b5 = bArr[6];
        if (bArr.length < i2 + 6 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=0x%04x(%d)", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 7, bArr2, 0, i);
        EqParameterInfo v11 = EqParameterInfo.parseV11(bArr2);
        if (v11 != null) {
            audioEq = new AudioEq(v11.stageNum, v11.globalGain, v11.samplingFreqIndex, v11.accuracy, v11.freq, v11.q, v11.gains, v11.biquadType, null);
        } else {
            ZLogger.d("invalid EqParameterInfo");
            audioEq = new AudioEq();
        }
        return new EqIndex(0, b2, b, str, true, b5, null, bArr2, audioEq);
    }

    public static EqIndex f(byte[] bArr) {
        int i;
        AudioEq audioEq;
        if (bArr == null || bArr.length < 8) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        String str = b2 == 0 ? b3 == 1 ? String.format(Locale.US, "Gaming EQ %d", Byte.valueOf(b)) : b3 == 2 ? String.format(Locale.US, "ANC EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "Normal EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "APT EQ %d", Byte.valueOf(b));
        int i2 = ((bArr[5] & 255) | (bArr[6] << 8)) & 65535;
        byte b6 = bArr[7];
        if (bArr.length < i2 + 6 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=0x%04x(%d)", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 8, bArr2, 0, i);
        EqParameterInfo v11 = EqParameterInfo.parseV11(bArr2);
        if (v11 != null) {
            audioEq = new AudioEq(v11.stageNum, v11.globalGain, v11.samplingFreqIndex, v11.accuracy, v11.freq, v11.q, v11.gains, v11.biquadType, null);
        } else {
            ZLogger.v("invalid EqParameterInfo");
            audioEq = new AudioEq();
        }
        return new EqIndex(b2, b3, b, str, true, b6, null, bArr2, audioEq);
    }

    public static EqIndex g(byte[] bArr) {
        int i;
        byte[] bArr2;
        AudioEq audioEq;
        if (bArr == null || bArr.length < 8) {
            ZLogger.v("invalid packet");
            return null;
        }
        byte b = bArr[0];
        byte b2 = bArr[1];
        byte b3 = bArr[2];
        byte b4 = bArr[3];
        byte b5 = bArr[4];
        byte b6 = bArr[5];
        String str = b3 == 0 ? b4 == 1 ? String.format(Locale.US, "Gaming EQ %d", Byte.valueOf(b)) : b4 == 2 ? String.format(Locale.US, "ANC EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "Normal EQ %d", Byte.valueOf(b)) : String.format(Locale.US, "APT EQ %d", Byte.valueOf(b));
        int i2 = ((bArr[7] << 8) | (bArr[6] & 255)) & 65535;
        byte b7 = bArr[8];
        if (bArr.length < i2 + 6 || i2 - 1 <= 0) {
            ZLogger.d(String.format(Locale.US, "invalid eqData, packet.length=%d, eqDataLen=0x%04x(%d)", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i2)));
            return null;
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 9, bArr3, 0, i);
        EqParameterInfo v11 = EqParameterInfo.parseV11(bArr3);
        if (v11 != null) {
            bArr2 = bArr3;
            audioEq = new AudioEq(v11.stageNum, v11.globalGain, v11.samplingFreqIndex, v11.accuracy, v11.freq, v11.q, v11.gains, v11.biquadType, null);
        } else {
            bArr2 = bArr3;
            ZLogger.v("invalid EqParameterInfo");
            audioEq = new AudioEq();
        }
        return new EqIndex(b2, b3, b4, b, str, true, b7, null, bArr2, audioEq);
    }

    public static EqIndex parse(int i, byte[] bArr) {
        return (i == 2 || i == 3) ? b(bArr) : (i == 4 || i == 5) ? c(bArr) : i == 256 ? d(bArr) : i == 257 ? e(bArr) : i == 258 ? f(bArr) : i >= 512 ? g(bArr) : a(bArr);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EqIndex)) {
            return false;
        }
        EqIndex eqIndex = (EqIndex) obj;
        return this.eqType == eqIndex.eqType && this.mode == eqIndex.mode && this.index == eqIndex.index;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EqIndex {");
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\neqType=%d, eqMode=%d, index=%d, nickname=%s, sampleRate=0x%02X", Integer.valueOf(this.eqType), Integer.valueOf(this.mode), Integer.valueOf(this.index), this.nickName, Byte.valueOf(this.sampleRate)));
        byte[] bArr = this.eqData;
        if (bArr != null) {
            sb.append(String.format(locale, "\neqData=(%d)%s", Integer.valueOf(bArr.length), DataConverter.bytes2Hex(this.eqData)));
        }
        byte[] bArr2 = this.eqInfo;
        if (bArr2 != null) {
            sb.append(String.format(locale, "\neqInfo=(%d)%s", Integer.valueOf(bArr2.length), DataConverter.bytes2Hex(this.eqInfo)));
        }
        if (this.audioEq != null) {
            sb.append(DefinitionInstance.ERROR_SEPARATOR + this.audioEq.toString());
        }
        sb.append("\n}");
        return sb.toString();
    }

    public EqIndex(int i, int i2, String str, boolean z, byte b, byte[] bArr) {
        this.eqBud = 2;
        this.eqType = 0;
        this.mode = i;
        this.index = i2;
        this.nickName = str;
        this.modified = z;
        this.sampleRate = b;
        this.eqData = bArr;
    }

    public EqIndex(int i, int i2, int i3, String str, boolean z, byte b, byte[] bArr, byte[] bArr2, AudioEq audioEq) {
        this(2, i, i2, i3, str, z, b, bArr, bArr2, audioEq);
    }

    public EqIndex(int i, int i2, int i3, int i4, String str, boolean z, byte b, byte[] bArr, byte[] bArr2, AudioEq audioEq) {
        this.eqBud = i;
        this.eqType = i2;
        this.mode = i3;
        this.index = i4;
        this.nickName = str;
        this.modified = z;
        this.sampleRate = b;
        this.eqData = bArr;
        this.eqInfo = bArr2;
        this.audioEq = audioEq;
    }
}
