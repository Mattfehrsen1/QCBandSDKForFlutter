package com.google.android.gms.internal.auth;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzhi extends zzeq<zzhi, zzhh> implements zzfr {
    private static final zzhi zzb;
    private zzeu<String> zzd = zzeq.zzd();

    static {
        zzhi zzhiVar = new zzhi();
        zzb = zzhiVar;
        zzeq.zzi(zzhi.class, zzhiVar);
    }

    private zzhi() {
    }

    public static zzhi zzl(byte[] bArr) throws zzew {
        return (zzhi) zzeq.zzb(zzb, bArr);
    }

    @Override // com.google.android.gms.internal.auth.zzeq
    protected final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzg(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        }
        if (i2 == 3) {
            return new zzhi();
        }
        zzhg zzhgVar = null;
        if (i2 == 4) {
            return new zzhh(zzhgVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }

    public final List<String> zzm() {
        return this.zzd;
    }
}
