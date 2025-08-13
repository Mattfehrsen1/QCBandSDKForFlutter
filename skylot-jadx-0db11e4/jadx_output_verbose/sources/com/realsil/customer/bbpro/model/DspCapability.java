package com.realsil.customer.bbpro.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/bbpro/model/DspCapability.class */
public class DspCapability implements Parcelable {
    public static final int ENABLE = 1;
    public static final int DISABLE = 0;
    public static final Parcelable.Creator<DspCapability> CREATOR = new Parcelable.Creator<DspCapability>() { // from class: com.realsil.customer.bbpro.model.DspCapability.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DspCapability createFromParcel(Parcel parcel) {
            return new DspCapability(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DspCapability[] newArray(int i) {
            return new DspCapability[i];
        }
    };
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public int Z;

    public DspCapability(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (bArr.length >= 1) {
            byte b = bArr[0];
            this.a = b & 1;
            this.b = (b & 2) >> 1;
            this.c = (b & 4) >> 2;
            this.d = (b & 8) >> 3;
            this.e = (b & 16) >> 4;
            this.f = (b & 32) >> 5;
        }
        if (bArr.length >= 2) {
            byte b2 = bArr[1];
            this.g = b2 & 1;
            this.h = (b2 & 2) >> 1;
            this.i = (b2 & 4) >> 2;
            this.j = (b2 & 8) >> 3;
            this.k = (b2 & 16) >> 4;
            this.l = (b2 & 32) >> 5;
            this.m = (b2 & 64) >> 6;
            this.n = (b2 & 128) >> 7;
        }
        if (bArr.length >= 3) {
            byte b3 = bArr[2];
            this.o = b3 & 1;
            this.p = (b3 & 2) >> 1;
            this.q = (b3 & 4) >> 2;
            this.r = (b3 & 8) >> 3;
            this.s = (b3 & 16) >> 4;
            this.t = (b3 & 32) >> 5;
            this.u = (b3 & 64) >> 6;
            this.v = (b3 & 128) >> 7;
        }
        if (bArr.length >= 4) {
            byte b4 = bArr[3];
            this.w = b4 & 1;
            this.x = (b4 & 2) >> 1;
            this.y = (b4 & 4) >> 2;
            this.z = (b4 & 8) >> 3;
            this.A = (b4 & 16) >> 4;
            this.B = (b4 & 32) >> 5;
            this.C = (b4 & 64) >> 6;
            this.D = (b4 & 128) >> 7;
        }
        if (bArr.length >= 5) {
            byte b5 = bArr[4];
            this.E = b5 & 1;
            this.F = (b5 & 2) >> 1;
            this.G = (b5 & 4) >> 2;
            this.H = (b5 & 8) >> 3;
            this.I = (b5 & 16) >> 4;
            this.J = (b5 & 32) >> 5;
            this.K = (b5 & 64) >> 6;
            this.L = (b5 & 128) >> 7;
        }
        if (bArr.length >= 6) {
            byte b6 = bArr[5];
            this.M = b6 & 1;
            this.N = (b6 & 2) >> 1;
            this.O = (b6 & 4) >> 2;
            this.P = (b6 & 8) >> 3;
            this.Q = (b6 & 16) >> 4;
            this.R = (b6 & 32) >> 5;
            this.S = (b6 & 64) >> 6;
            this.T = (b6 & 128) >> 7;
        }
        if (bArr.length >= 7) {
            byte b7 = bArr[6];
            this.U = b7 & 1;
            this.V = (b7 & 2) >> 1;
            this.W = (b7 & 4) >> 2;
            this.X = (b7 & 8) >> 3;
            this.Y = (b7 & 16) >> 4;
            this.Z = (b7 & 32) >> 5;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
        parcel.writeInt(this.z);
        parcel.writeInt(this.A);
        parcel.writeInt(this.B);
        parcel.writeInt(this.C);
        parcel.writeInt(this.D);
        parcel.writeInt(this.E);
        parcel.writeInt(this.F);
        parcel.writeInt(this.G);
        parcel.writeInt(this.H);
        parcel.writeInt(this.I);
        parcel.writeInt(this.J);
        parcel.writeInt(this.K);
        parcel.writeInt(this.L);
        parcel.writeInt(this.M);
        parcel.writeInt(this.N);
        parcel.writeInt(this.O);
        parcel.writeInt(this.P);
        parcel.writeInt(this.Q);
        parcel.writeInt(this.R);
        parcel.writeInt(this.S);
        parcel.writeInt(this.T);
        parcel.writeInt(this.U);
        parcel.writeInt(this.V);
        parcel.writeInt(this.W);
        parcel.writeInt(this.X);
        parcel.writeInt(this.Y);
        parcel.writeInt(this.Z);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DspCapability:");
        sb.append("\n========================================================================================================");
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.a), Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f)));
        sb.append("\n--------------------------------------------------------------------------------------------------------");
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(this.i), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l), Integer.valueOf(this.m), Integer.valueOf(this.n)));
        sb.append("\n--------------------------------------------------------------------------------------------------------");
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.o), Integer.valueOf(this.p), Integer.valueOf(this.q), Integer.valueOf(this.r), Integer.valueOf(this.s), Integer.valueOf(this.t), Integer.valueOf(this.u), Integer.valueOf(this.v)));
        sb.append("\n--------------------------------------------------------------------------------------------------------");
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.w), Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.z), Integer.valueOf(this.A), Integer.valueOf(this.B), Integer.valueOf(this.C)));
        sb.append("\n--------------------------------------------------------------------------------------------------------");
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.E), Integer.valueOf(this.F), Integer.valueOf(this.G), Integer.valueOf(this.H), Integer.valueOf(this.I), Integer.valueOf(this.J), Integer.valueOf(this.K), Integer.valueOf(this.L)));
        sb.append("\n--------------------------------------------------------------------------------------------------------");
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.M), Integer.valueOf(this.N), Integer.valueOf(this.O), Integer.valueOf(this.P), Integer.valueOf(this.Q), Integer.valueOf(this.R)));
        sb.append("\n--------------------------------------------------------------------------------------------------------");
        sb.append(String.format(locale, "\n|%d｜%d｜%d｜%d｜%d｜%d｜", Integer.valueOf(this.U), Integer.valueOf(this.V), Integer.valueOf(this.W), Integer.valueOf(this.X), Integer.valueOf(this.Y), Integer.valueOf(this.Z)));
        sb.append("\n========================================================================================================");
        return sb.toString();
    }

    public boolean isLeNameSupported() {
        return this.a == 1;
    }

    public boolean isBrEdrNameSupported() {
        return this.b == 1;
    }

    public boolean isLanguageSupported() {
        return this.c == 1;
    }

    public boolean isBatterySupported() {
        return this.d == 1;
    }

    public boolean isOtaSupported() {
        return this.e == 1;
    }

    public boolean isRwsChannelSupported() {
        return this.f == 1;
    }

    public boolean isTtsSupported() {
        return this.g == 1;
    }

    public boolean isRwsSupported() {
        return this.h == 1;
    }

    public boolean isDspAptSupported() {
        return this.i == 1;
    }

    public boolean isAptEqSupported() {
        return this.q == 1;
    }

    public boolean isVpRingtoneSupported() {
        return this.r == 1;
    }

    public boolean isLlAptSupported() {
        return this.m == 1;
    }

    public boolean isEqSupported() {
        return this.j == 1;
    }

    public boolean isVadSupported() {
        return this.k == 1;
    }

    public boolean isAncSupported() {
        return this.l == 1;
    }

    public boolean isAncEqSupported() {
        return this.p == 1;
    }

    public boolean isLlAptVolumeHeSupported() {
        return this.o == 1;
    }

    public boolean isListeningModeCycleSupported() {
        return this.n == 1;
    }

    public boolean isAncsSupported() {
        return this.E == 1;
    }

    public boolean isVibrationSupported() {
        return this.F == 1;
    }

    public boolean isMfbSupported() {
        return this.G == 1;
    }

    public boolean isGamingModeSupported() {
        return this.H == 1;
    }

    public boolean isGamingModeEqSupported() {
        return this.I == 1;
    }

    public boolean isKeyMapSupported() {
        return this.J == 1;
    }

    public boolean isKeyMappingResetSupported() {
        return this.D == 1;
    }

    public boolean isAptEqBudSettingsSupported() {
        return this.s == 1;
    }

    public boolean isHASupported() {
        return this.K == 1 && this.i == 1;
    }

    public boolean isLocalPlaybackSupported() {
        return this.L == 1;
    }

    public boolean isMultiLinkSupported() {
        return this.t == 1;
    }

    public boolean isDurianSupported() {
        return this.u == 1;
    }

    public boolean isAptNrSupported() {
        return this.v == 1;
    }

    public boolean isLlaptScenarioGroupSettingsSupported() {
        return this.w == 1;
    }

    public boolean isEarDetectionSupported() {
        return this.x == 1;
    }

    public boolean isAptPowerOnDelayTimeSupported() {
        return this.y == 1;
    }

    public boolean isAncEqConfigureSupported() {
        return this.z == 1;
    }

    public boolean isBudInfoReqSupported() {
        return this.A == 1;
    }

    public boolean isListeningModeReportSupported() {
        return this.B == 1;
    }

    public boolean isAptVolumeForceSyncSupported() {
        return this.C == 1;
    }

    public boolean isLockButtonSupported() {
        return this.M == 1;
    }

    public boolean isDurianMasterSupported() {
        return this.N == 1;
    }

    public boolean isAncScenarioGroupSettingsSupported() {
        return this.O == 1;
    }

    public boolean isRwsKeymapConfigureSupported() {
        return this.P == 1;
    }

    public boolean isEqPersistenceSupported() {
        return this.Q == 1;
    }

    public boolean isKeyMappingResetByBudSupported() {
        return this.R == 1;
    }

    public boolean isDeviceIdSupported() {
        return this.S == 1;
    }

    public boolean isAptVolumeGainSaveFlashSupported() {
        return this.T == 1;
    }

    public boolean isSppCaptureV2Supported() {
        return this.U == 1;
    }

    public boolean isAncAndDspAptListeningModeSupported() {
        return this.V == 1;
    }

    public boolean isSpatialAudioSupported() {
        return this.W == 1;
    }

    public boolean isUiOtaVersionSupported() {
        return this.X == 1;
    }

    public boolean isSpecialAncScenarioSupported() {
        return this.Y == 1;
    }

    public boolean isDsp3BinScenarioToggleSupported() {
        return this.Z == 1;
    }

    public DspCapability(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        this.w = parcel.readInt();
        this.x = parcel.readInt();
        this.y = parcel.readInt();
        this.z = parcel.readInt();
        this.A = parcel.readInt();
        this.B = parcel.readInt();
        this.C = parcel.readInt();
        this.D = parcel.readInt();
        this.E = parcel.readInt();
        this.F = parcel.readInt();
        this.G = parcel.readInt();
        this.H = parcel.readInt();
        this.I = parcel.readInt();
        this.J = parcel.readInt();
        this.K = parcel.readInt();
        this.L = parcel.readInt();
        this.M = parcel.readInt();
        this.N = parcel.readInt();
        this.O = parcel.readInt();
        this.P = parcel.readInt();
        this.Q = parcel.readInt();
        this.R = parcel.readInt();
        this.S = parcel.readInt();
        this.T = parcel.readInt();
        this.U = parcel.readInt();
        this.V = parcel.readInt();
        this.W = parcel.readInt();
        this.X = parcel.readInt();
        this.Y = parcel.readInt();
        this.Z = parcel.readInt();
    }
}
