package com.google.android.gms.internal.auth;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@17.1.4 */
/* loaded from: classes2.dex */
public final class zzew extends IOException {
    private zzfq zza;

    public zzew(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.zza = null;
    }

    static zzew zza() {
        return new zzew("Protocol message contained an invalid tag (zero).");
    }

    static zzew zzb() {
        return new zzew("Protocol message had invalid UTF-8.");
    }

    static zzew zzc() {
        return new zzew("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzew zzd() {
        return new zzew("Failed to parse the message.");
    }

    static zzew zzf() {
        return new zzew("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzew zze(zzfq zzfqVar) {
        this.zza = zzfqVar;
        return this;
    }

    public zzew(String str) {
        super(str);
        this.zza = null;
    }
}
