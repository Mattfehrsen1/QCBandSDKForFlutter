package com.realsil.sdk.bbpro.equalizer;

import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* loaded from: classes3.dex */
public class EqInfo {
    public static final boolean DEFAULT_EQ_STATE = true;
    public static final int DEFAULT_SUPPORTED_SAMPLE_RATE = 8;
    public static final int INVALID_INDEX = 255;
    public byte activeSampleRate;
    public int gamingModeActiveIndex;
    public int gamingModeEntryNumber;
    public int micAptEqActiveIndex;
    public int micAptEqEntryNumber;
    public int micAptIndexMap;
    public int micEqSaveNum;
    public int normalModeActiveIndex;
    public int normalModeEntryNumber;
    public int spkAncEqActiveIndex;
    public int spkAncEqEntryNumber;
    public int spkAncIndexMap;
    public int spkEqSaveNum;
    public int spkGamingIndexMap;
    public int spkNormalIndexMap;
    public int supportedSampleRate;
    public boolean enabled = true;
    public int spkActiveMode = 0;
    public boolean normalModeEqEnabled = true;
    public boolean gamingModeEnabled = true;
    public boolean gamingModeEqEnabled = true;
    public int micActiveMode = 0;

    public byte getActiveSampleRate() {
        return this.activeSampleRate;
    }

    public int getGamingModeActiveIndex() {
        return this.gamingModeActiveIndex;
    }

    public int getGamingModeEntryNumber() {
        return this.gamingModeEntryNumber;
    }

    public int getMicActiveMode() {
        return this.micActiveMode;
    }

    public int getMicAptEqActiveIndex() {
        return this.micAptEqActiveIndex;
    }

    public int getMicAptEqEntryNumber() {
        return this.micAptEqEntryNumber;
    }

    public int getMicAptIndexMap() {
        return this.micAptIndexMap;
    }

    public int getMicEqSaveNum() {
        return this.micEqSaveNum;
    }

    public int getNormalModeActiveIndex() {
        return this.normalModeActiveIndex;
    }

    public int getNormalModeEntryNumber() {
        return this.normalModeEntryNumber;
    }

    public int getSpkActiveMode() {
        return this.spkActiveMode;
    }

    public int getSpkAncEqActiveIndex() {
        return this.spkAncEqActiveIndex;
    }

    public int getSpkAncEqEntryNumber() {
        return this.spkAncEqEntryNumber;
    }

    public int getSpkAncIndexMap() {
        return this.spkAncIndexMap;
    }

    public int getSpkEqSaveNum() {
        return this.spkEqSaveNum;
    }

    public int getSpkGamingIndexMap() {
        return this.spkGamingIndexMap;
    }

    public int getSpkNormalIndexMap() {
        return this.spkNormalIndexMap;
    }

    public int getSupportedSampleRate() {
        return this.supportedSampleRate;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isGamingModeEnabled() {
        return this.gamingModeEnabled;
    }

    public boolean isGamingModeEqEnabled() {
        return this.gamingModeEqEnabled;
    }

    public boolean isNormalModeEqEnabled() {
        return this.normalModeEqEnabled;
    }

    public boolean parseEqConfigure(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            byte b = bArr[0];
            if (b == 0) {
                if (bArr.length >= 2) {
                    setEqEnabled(bArr[1] == 1);
                }
                return true;
            }
            if (b == 1) {
                updateEqConfigure1(bArr);
                return true;
            }
            ZLogger.v(String.format(Locale.US, "Unknown query type: 0x%02X", Byte.valueOf(b)));
        }
        return false;
    }

    public void setEqActiveIndex(int i, int i2, int i3) {
        if (i != 0) {
            this.micAptEqActiveIndex = i3;
            return;
        }
        if (i2 == 0) {
            this.normalModeActiveIndex = i3;
        } else if (i2 == 1) {
            this.gamingModeActiveIndex = i3;
        } else if (i2 == 2) {
            this.spkAncEqActiveIndex = i3;
        }
    }

    public void setEqBasicInfo(int i, c cVar) {
        if (i < 512) {
            int i2 = cVar.g;
            if (i2 == 0) {
                this.normalModeEqEnabled = cVar.c;
                this.normalModeEntryNumber = cVar.a;
                this.normalModeActiveIndex = cVar.b;
                return;
            } else if (i2 == 1) {
                this.gamingModeEqEnabled = cVar.c;
                this.gamingModeEntryNumber = cVar.a;
                this.gamingModeActiveIndex = cVar.b;
                return;
            } else {
                if (i2 == 2) {
                    this.spkAncEqEntryNumber = cVar.a;
                    this.spkAncEqActiveIndex = cVar.b;
                    return;
                }
                return;
            }
        }
        boolean z = cVar.c;
        this.enabled = z;
        this.activeSampleRate = cVar.d;
        this.supportedSampleRate = cVar.e;
        this.spkEqSaveNum = cVar.f;
        this.spkActiveMode = cVar.g;
        this.normalModeEqEnabled = z;
        this.normalModeEntryNumber = cVar.h;
        this.spkNormalIndexMap = cVar.i;
        this.normalModeActiveIndex = cVar.j;
        this.gamingModeEnabled = z;
        this.gamingModeEntryNumber = cVar.k;
        this.spkGamingIndexMap = cVar.l;
        this.gamingModeActiveIndex = cVar.m;
        this.spkAncEqEntryNumber = cVar.n;
        this.spkAncIndexMap = cVar.o;
        this.spkAncEqActiveIndex = cVar.p;
        this.micEqSaveNum = cVar.q;
        this.micActiveMode = cVar.r;
        this.micAptEqEntryNumber = cVar.s;
        this.micAptIndexMap = cVar.t;
        this.micAptEqActiveIndex = cVar.u;
    }

    public void setEqEnabled(boolean z) {
        this.gamingModeEqEnabled = z;
        this.normalModeEqEnabled = z;
    }

    public void setGamingModeEnabled(boolean z) {
        this.gamingModeEnabled = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EqInfo{");
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\nenabled=%b, supportedSampleRate=%d, activeSampleRate=%d", Boolean.valueOf(this.enabled), Integer.valueOf(this.supportedSampleRate), Byte.valueOf(this.activeSampleRate)));
        sb.append("\n\tSpk:");
        sb.append(String.format(locale, "\n\t\teqSaveNum=%d, activeMode=%d", Integer.valueOf(this.spkEqSaveNum), Integer.valueOf(this.spkActiveMode)));
        sb.append(String.format(locale, "\n\t\tnormal: eq=%b, entryNumber=%d, spkNormalIndexMap=%d, activeIndex=%d", Boolean.valueOf(this.normalModeEqEnabled), Integer.valueOf(this.normalModeEntryNumber), Integer.valueOf(this.spkNormalIndexMap), Integer.valueOf(this.normalModeActiveIndex)));
        sb.append(String.format(locale, "\n\t\tgaming: state=%b, eq=%b, entryNumber=%d, spkGamingIndexMap=%d, activeIndex=%d", Boolean.valueOf(this.gamingModeEnabled), Boolean.valueOf(this.gamingModeEqEnabled), Integer.valueOf(this.gamingModeEntryNumber), Integer.valueOf(this.spkGamingIndexMap), Integer.valueOf(this.gamingModeActiveIndex)));
        sb.append(String.format(locale, "\n\t\tanc: entryNumber=%d, spkAncIndexMap=%d, activeIndex=%d", Integer.valueOf(this.spkAncEqEntryNumber), Integer.valueOf(this.spkAncIndexMap), Integer.valueOf(this.spkAncEqActiveIndex)));
        sb.append("\n\tMic:");
        sb.append(String.format(locale, "\n\t\teqSaveNum=%d, activeMode=%d", Integer.valueOf(this.micEqSaveNum), Integer.valueOf(this.micActiveMode)));
        sb.append(String.format(locale, "\n\t\tapt:entryNumber=%d, micAptIndexMap=%d, activeIndex=%d", Integer.valueOf(this.micAptEqEntryNumber), Integer.valueOf(this.micAptIndexMap), Integer.valueOf(this.micAptEqActiveIndex)));
        sb.append("\n}");
        return sb.toString();
    }

    public void updateEqConfigure1(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (bArr.length >= 2) {
            this.normalModeEntryNumber = bArr[1];
        }
        if (bArr.length >= 3) {
            this.gamingModeEntryNumber = bArr[2];
        }
        if (bArr.length >= 4) {
            this.micAptEqEntryNumber = bArr[3];
        }
        if (bArr.length >= 5) {
            this.spkAncEqEntryNumber = bArr[4];
        }
    }
}
