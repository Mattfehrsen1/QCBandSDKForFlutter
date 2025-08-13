package com.google.android.gms.internal.auth;

import com.oudmon.ble.base.communication.Constants;
import java.io.IOException;
import kotlin.jvm.internal.ByteCompanionObject;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzdq {
    static int zza(byte[] bArr, int i, zzdp zzdpVar) throws zzew {
        int iZzj = zzj(bArr, i, zzdpVar);
        int i2 = zzdpVar.zza;
        if (i2 < 0) {
            throw zzew.zzc();
        }
        if (i2 > bArr.length - iZzj) {
            throw zzew.zzf();
        }
        if (i2 == 0) {
            zzdpVar.zzc = zzeb.zzb;
            return iZzj;
        }
        zzdpVar.zzc = zzeb.zzk(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzgb zzgbVar, byte[] bArr, int i, int i2, int i3, zzdp zzdpVar) throws IOException {
        zzft zzftVar = (zzft) zzgbVar;
        Object objZzd = zzftVar.zzd();
        int iZzb = zzftVar.zzb(objZzd, bArr, i, i2, i3, zzdpVar);
        zzftVar.zze(objZzd);
        zzdpVar.zzc = objZzd;
        return iZzb;
    }

    static int zzd(zzgb zzgbVar, byte[] bArr, int i, int i2, zzdp zzdpVar) throws IOException {
        int iZzk = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZzk = zzk(i3, bArr, iZzk, zzdpVar);
            i3 = zzdpVar.zza;
        }
        int i4 = iZzk;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzew.zzf();
        }
        Object objZzd = zzgbVar.zzd();
        int i5 = i3 + i4;
        zzgbVar.zzg(objZzd, bArr, i4, i5, zzdpVar);
        zzgbVar.zze(objZzd);
        zzdpVar.zzc = objZzd;
        return i5;
    }

    static int zze(zzgb<?> zzgbVar, int i, byte[] bArr, int i2, int i3, zzeu<?> zzeuVar, zzdp zzdpVar) throws IOException {
        int iZzd = zzd(zzgbVar, bArr, i2, i3, zzdpVar);
        zzeuVar.add(zzdpVar.zzc);
        while (iZzd < i3) {
            int iZzj = zzj(bArr, iZzd, zzdpVar);
            if (i != zzdpVar.zza) {
                break;
            }
            iZzd = zzd(zzgbVar, bArr, iZzj, i3, zzdpVar);
            zzeuVar.add(zzdpVar.zzc);
        }
        return iZzd;
    }

    static int zzf(byte[] bArr, int i, zzeu<?> zzeuVar, zzdp zzdpVar) throws IOException {
        zzer zzerVar = (zzer) zzeuVar;
        int iZzj = zzj(bArr, i, zzdpVar);
        int i2 = zzdpVar.zza + iZzj;
        while (iZzj < i2) {
            iZzj = zzj(bArr, iZzj, zzdpVar);
            zzerVar.zze(zzdpVar.zza);
        }
        if (iZzj == i2) {
            return iZzj;
        }
        throw zzew.zzf();
    }

    static int zzg(byte[] bArr, int i, zzdp zzdpVar) throws zzew {
        int iZzj = zzj(bArr, i, zzdpVar);
        int i2 = zzdpVar.zza;
        if (i2 < 0) {
            throw zzew.zzc();
        }
        if (i2 == 0) {
            zzdpVar.zzc = "";
            return iZzj;
        }
        zzdpVar.zzc = new String(bArr, iZzj, i2, zzev.zza);
        return iZzj + i2;
    }

    static int zzh(byte[] bArr, int i, zzdp zzdpVar) throws zzew {
        int iZzj = zzj(bArr, i, zzdpVar);
        int i2 = zzdpVar.zza;
        if (i2 < 0) {
            throw zzew.zzc();
        }
        if (i2 == 0) {
            zzdpVar.zzc = "";
            return iZzj;
        }
        zzdpVar.zzc = zzhd.zzb(bArr, iZzj, i2);
        return iZzj + i2;
    }

    static int zzi(int i, byte[] bArr, int i2, int i3, zzgq zzgqVar, zzdp zzdpVar) throws zzew {
        if ((i >>> 3) == 0) {
            throw zzew.zza();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzm = zzm(bArr, i2, zzdpVar);
            zzgqVar.zzf(i, Long.valueOf(zzdpVar.zzb));
            return iZzm;
        }
        if (i4 == 1) {
            zzgqVar.zzf(i, Long.valueOf(zzn(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzj = zzj(bArr, i2, zzdpVar);
            int i5 = zzdpVar.zza;
            if (i5 < 0) {
                throw zzew.zzc();
            }
            if (i5 > bArr.length - iZzj) {
                throw zzew.zzf();
            }
            if (i5 == 0) {
                zzgqVar.zzf(i, zzeb.zzb);
            } else {
                zzgqVar.zzf(i, zzeb.zzk(bArr, iZzj, i5));
            }
            return iZzj + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw zzew.zza();
            }
            zzgqVar.zzf(i, Integer.valueOf(zzb(bArr, i2)));
            return i2 + 4;
        }
        int i6 = (i & (-8)) | 4;
        zzgq zzgqVarZzc = zzgq.zzc();
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZzj2 = zzj(bArr, i2, zzdpVar);
            int i8 = zzdpVar.zza;
            if (i8 == i6) {
                i7 = i8;
                i2 = iZzj2;
                break;
            }
            i7 = i8;
            i2 = zzi(i8, bArr, iZzj2, i3, zzgqVarZzc, zzdpVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzew.zzd();
        }
        zzgqVar.zzf(i, zzgqVarZzc);
        return i2;
    }

    static int zzj(byte[] bArr, int i, zzdp zzdpVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzk(b, bArr, i2, zzdpVar);
        }
        zzdpVar.zza = b;
        return i2;
    }

    static int zzk(int i, byte[] bArr, int i2, zzdp zzdpVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzdpVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzdpVar.zza = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzdpVar.zza = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzdpVar.zza = i9 | (b4 << Constants.CMD_GET_MUSIC_SWITCH);
            return i10;
        }
        int i11 = i9 | ((b4 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzdpVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zzl(int i, byte[] bArr, int i2, int i3, zzeu<?> zzeuVar, zzdp zzdpVar) {
        zzer zzerVar = (zzer) zzeuVar;
        int iZzj = zzj(bArr, i2, zzdpVar);
        zzerVar.zze(zzdpVar.zza);
        while (iZzj < i3) {
            int iZzj2 = zzj(bArr, iZzj, zzdpVar);
            if (i != zzdpVar.zza) {
                break;
            }
            iZzj = zzj(bArr, iZzj2, zzdpVar);
            zzerVar.zze(zzdpVar.zza);
        }
        return iZzj;
    }

    static int zzm(byte[] bArr, int i, zzdp zzdpVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzdpVar.zzb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (r10 & ByteCompanionObject.MAX_VALUE) << i4;
            b = bArr[i3];
            i3 = i5;
        }
        zzdpVar.zzb = j2;
        return i3;
    }

    static long zzn(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }
}
