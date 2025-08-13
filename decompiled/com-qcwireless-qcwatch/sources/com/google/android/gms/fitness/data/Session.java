package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fitness.zzfv;
import com.google.android.gms.internal.fitness.zzfw;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class Session extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Session> CREATOR = new zzac();
    public static final String EXTRA_SESSION = "vnd.google.fitness.session";
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
    private final long zza;
    private final long zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final int zzf;
    private final zzb zzg;
    private final Long zzh;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class Builder {
        private String zzd;
        private Long zzg;
        private long zza = 0;
        private long zzb = 0;
        private String zzc = null;
        private String zze = "";
        private int zzf = 4;

        public Session build() {
            Preconditions.checkState(this.zza > 0, "Start time should be specified.");
            long j = this.zzb;
            Preconditions.checkState(j == 0 || j > this.zza, "End time should be later than start time.");
            if (this.zzd == null) {
                String str = this.zzc;
                if (str == null) {
                    str = "";
                }
                this.zzd = str + this.zza;
            }
            return new Session(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, null, this.zzg);
        }

        public Builder setActiveTime(long j, TimeUnit timeUnit) {
            this.zzg = Long.valueOf(timeUnit.toMillis(j));
            return this;
        }

        public Builder setActivity(String str) {
            int iZza = zzfv.zza(str);
            zzfw zzfwVarZza = zzfw.zza(iZza, zzfw.UNKNOWN);
            Preconditions.checkArgument(!(zzfwVarZza.zzb() && !zzfwVarZza.equals(zzfw.SLEEP)), "Unsupported session activity type %s.", Integer.valueOf(iZza));
            this.zzf = iZza;
            return this;
        }

        public Builder setDescription(String str) {
            Preconditions.checkArgument(str.length() <= 1000, "Session description cannot exceed %d characters", 1000);
            this.zze = str;
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            Preconditions.checkState(j >= 0, "End time should be positive.");
            this.zzb = timeUnit.toMillis(j);
            return this;
        }

        public Builder setIdentifier(String str) {
            boolean z = false;
            if (str != null && TextUtils.getTrimmedLength(str) > 0) {
                z = true;
            }
            Preconditions.checkArgument(z);
            this.zzd = str;
            return this;
        }

        public Builder setName(String str) {
            Preconditions.checkArgument(str.length() <= 100, "Session name cannot exceed %d characters", 100);
            this.zzc = str;
            return this;
        }

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            Preconditions.checkState(j > 0, "Start time should be positive.");
            this.zza = timeUnit.toMillis(j);
            return this;
        }
    }

    public Session(long j, long j2, String str, String str2, String str3, int i, zzb zzbVar, Long l) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = str;
        this.zzd = str2;
        this.zze = str3;
        this.zzf = i;
        this.zzg = zzbVar;
        this.zzh = l;
    }

    public static Session extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (Session) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_SESSION, CREATOR);
    }

    public static String getMimeType(String str) {
        return MIME_TYPE_PREFIX.concat(String.valueOf(str));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Session)) {
            return false;
        }
        Session session = (Session) obj;
        return this.zza == session.zza && this.zzb == session.zzb && Objects.equal(this.zzc, session.zzc) && Objects.equal(this.zzd, session.zzd) && Objects.equal(this.zze, session.zze) && Objects.equal(this.zzg, session.zzg) && this.zzf == session.zzf;
    }

    public long getActiveTime(TimeUnit timeUnit) {
        Long l = this.zzh;
        if (l != null) {
            return timeUnit.convert(l.longValue(), TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Active time is not set");
    }

    public String getActivity() {
        return zzfv.zzb(this.zzf);
    }

    public String getAppPackageName() {
        zzb zzbVar = this.zzg;
        if (zzbVar == null) {
            return null;
        }
        return zzbVar.zzb();
    }

    public String getDescription() {
        return this.zze;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzb, TimeUnit.MILLISECONDS);
    }

    public String getIdentifier() {
        return this.zzd;
    }

    public String getName() {
        return this.zzc;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zza, TimeUnit.MILLISECONDS);
    }

    public boolean hasActiveTime() {
        return this.zzh != null;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Long.valueOf(this.zzb), this.zzd);
    }

    public boolean isOngoing() {
        return this.zzb == 0;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zza)).add("endTime", Long.valueOf(this.zzb)).add("name", this.zzc).add("identifier", this.zzd).add("description", this.zze).add("activity", Integer.valueOf(this.zzf)).add("application", this.zzg).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeString(parcel, 4, getIdentifier(), false);
        SafeParcelWriter.writeString(parcel, 5, getDescription(), false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLongObject(parcel, 9, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
