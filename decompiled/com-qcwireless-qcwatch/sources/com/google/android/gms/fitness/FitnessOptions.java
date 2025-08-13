package com.google.android.gms.fitness;

import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class FitnessOptions implements GoogleSignInOptionsExtension {
    public static final int ACCESS_READ = 0;
    public static final int ACCESS_WRITE = 1;
    private final Set zza;

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static final class Builder {
        private final Set zza = new HashSet();

        private Builder() {
        }

        public Builder accessSleepSessions(int i) {
            boolean z;
            if (i == 0) {
                z = true;
            } else if (i == 1) {
                i = 1;
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            if (i == 0) {
                this.zza.add(new Scope("https://www.googleapis.com/auth/fitness.sleep.read"));
            } else if (i == 1) {
                this.zza.add(new Scope("https://www.googleapis.com/auth/fitness.sleep.write"));
            }
            return this;
        }

        public Builder addDataType(DataType dataType) {
            addDataType(dataType, 0);
            return this;
        }

        public FitnessOptions build() {
            return new FitnessOptions(this, null);
        }

        /* synthetic */ Builder(zzg zzgVar) {
        }

        public Builder addDataType(DataType dataType, int i) {
            boolean z;
            if (i == 0) {
                z = true;
            } else if (i == 1) {
                i = 1;
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            String strZza = dataType.zza();
            String strZzb = dataType.zzb();
            if (i == 0) {
                if (strZza != null) {
                    this.zza.add(new Scope(strZza));
                }
            } else if (i == 1 && strZzb != null) {
                this.zza.add(new Scope(strZzb));
            }
            return this;
        }

        public Builder accessActivitySessions(int i) {
            if (i == 0) {
                this.zza.add(new Scope("https://www.googleapis.com/auth/fitness.activity.read"));
            } else {
                if (i != 1) {
                    throw new IllegalArgumentException("valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
                }
                this.zza.add(new Scope("https://www.googleapis.com/auth/fitness.activity.write"));
            }
            return this;
        }
    }

    /* synthetic */ FitnessOptions(Builder builder, zzh zzhVar) {
        this.zza = builder.zza;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessOptions) {
            return this.zza.equals(((FitnessOptions) obj).zza);
        }
        return false;
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
    public int getExtensionType() {
        return 3;
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
    public List<Scope> getImpliedScopes() {
        return new ArrayList(this.zza);
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    @Override // com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
    public Bundle toBundle() {
        return new Bundle();
    }
}
