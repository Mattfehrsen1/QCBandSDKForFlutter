package com.google.android.gms.internal.auth;

import java.nio.charset.Charset;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
class zzdz extends zzdy {
    protected final byte[] zza;

    zzdz(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeb) || zzd() != ((zzeb) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zzdz)) {
            return obj.equals(this);
        }
        zzdz zzdzVar = (zzdz) obj;
        int iZzj = zzj();
        int iZzj2 = zzdzVar.zzj();
        if (iZzj != 0 && iZzj2 != 0 && iZzj != iZzj2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzdzVar.zzd()) {
            int iZzd2 = zzd();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(iZzd);
            sb.append(iZzd2);
            throw new IllegalArgumentException(sb.toString());
        }
        if (iZzd > zzdzVar.zzd()) {
            int iZzd3 = zzdzVar.zzd();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(iZzd);
            sb2.append(", ");
            sb2.append(iZzd3);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (!(zzdzVar instanceof zzdz)) {
            return zzdzVar.zzf(0, iZzd).equals(zzf(0, iZzd));
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzdzVar.zza;
        zzdzVar.zzc();
        int i = 0;
        int i2 = 0;
        while (i < iZzd) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    protected final int zze(int i, int i2, int i3) {
        return zzev.zzd(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    public final zzeb zzf(int i, int i2) {
        int iZzi = zzi(0, i2, zzd());
        return iZzi == 0 ? zzeb.zzb : new zzdw(this.zza, 0, iZzi);
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.auth.zzeb
    public final boolean zzh() {
        return zzhd.zzd(this.zza, 0, zzd());
    }
}
