package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
final class zzgn {
    static String zza(zzeb zzebVar) {
        StringBuilder sb = new StringBuilder(zzebVar.zzd());
        for (int i = 0; i < zzebVar.zzd(); i++) {
            byte bZza = zzebVar.zza(i);
            if (bZza == 34) {
                sb.append("\\\"");
            } else if (bZza == 39) {
                sb.append("\\'");
            } else if (bZza != 92) {
                switch (bZza) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bZza < 32 || bZza > 126) {
                            sb.append('\\');
                            sb.append((char) (((bZza >>> 6) & 3) + 48));
                            sb.append((char) (((bZza >>> 3) & 7) + 48));
                            sb.append((char) ((bZza & 7) + 48));
                            break;
                        } else {
                            sb.append((char) bZza);
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
