package com.google.android.gms.fitness;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.fitness.zzab;
import com.google.android.gms.internal.fitness.zzaj;
import com.google.android.gms.internal.fitness.zzar;
import com.google.android.gms.internal.fitness.zzaz;
import com.google.android.gms.internal.fitness.zzbh;
import com.google.android.gms.internal.fitness.zzcy;
import com.google.android.gms.internal.fitness.zzde;
import com.google.android.gms.internal.fitness.zzdh;
import com.google.android.gms.internal.fitness.zzds;
import com.google.android.gms.internal.fitness.zzea;
import com.google.android.gms.internal.fitness.zzee;
import com.google.android.gms.internal.fitness.zzep;
import com.google.android.gms.internal.fitness.zzet;
import com.google.android.gms.internal.fitness.zzu;
import io.reactivex.annotations.SchedulerSupport;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class Fitness {
    public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
    public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
    public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";

    @Deprecated
    public static final Void API = null;

    @Deprecated
    public static final BleApi BleApi;
    public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";
    public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
    public static final Scope SCOPE_ACTIVITY_READ;
    public static final Scope SCOPE_ACTIVITY_READ_WRITE;
    public static final Scope SCOPE_BODY_READ;
    public static final Scope SCOPE_BODY_READ_WRITE;
    public static final Scope SCOPE_LOCATION_READ;
    public static final Scope SCOPE_LOCATION_READ_WRITE;
    public static final Scope SCOPE_NUTRITION_READ;
    public static final Scope SCOPE_NUTRITION_READ_WRITE;
    public static final Scope zza;
    public static final Scope zzb;
    public static final Scope zzc;
    public static final Scope zzd;
    public static final Scope zze;
    public static final Scope zzf;
    public static final GoogleSignInAccount zzg;

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> SENSORS_API = zzaz.zzf;

    @Deprecated
    public static final SensorsApi SensorsApi = new zzee();

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> RECORDING_API = zzar.zzf;

    @Deprecated
    public static final RecordingApi RecordingApi = new zzea();

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> SESSIONS_API = zzbh.zzf;

    @Deprecated
    public static final SessionsApi SessionsApi = new zzep();

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> HISTORY_API = zzaj.zzf;

    @Deprecated
    public static final HistoryApi HistoryApi = new zzds();

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> GOALS_API = zzab.zzf;

    @Deprecated
    public static final GoalsApi GoalsApi = new zzdh();

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> CONFIG_API = zzu.zzf;

    @Deprecated
    public static final ConfigApi ConfigApi = new zzde();

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> BLE_API = com.google.android.gms.internal.fitness.zzm.zzf;

    static {
        BleApi = Build.VERSION.SDK_INT >= 18 ? new zzcy() : new zzet();
        SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");
        SCOPE_ACTIVITY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
        SCOPE_LOCATION_READ = new Scope("https://www.googleapis.com/auth/fitness.location.read");
        SCOPE_LOCATION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.location.write");
        SCOPE_BODY_READ = new Scope("https://www.googleapis.com/auth/fitness.body.read");
        SCOPE_BODY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.body.write");
        SCOPE_NUTRITION_READ = new Scope("https://www.googleapis.com/auth/fitness.nutrition.read");
        SCOPE_NUTRITION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
        zza = new Scope("https://www.googleapis.com/auth/fitness.heart_rate.read");
        zzb = new Scope("https://www.googleapis.com/auth/fitness.heart_rate.write");
        zzc = new Scope("https://www.googleapis.com/auth/fitness.respiratory_rate.read");
        zzd = new Scope("https://www.googleapis.com/auth/fitness.respiratory_rate.write");
        zze = new Scope("https://www.googleapis.com/auth/fitness.sleep.read");
        zzf = new Scope("https://www.googleapis.com/auth/fitness.sleep.write");
        zzg = GoogleSignInAccount.fromAccount(new Account(SchedulerSupport.NONE, "com.google"));
    }

    private Fitness() {
    }

    @Deprecated
    public static BleClient getBleClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    @Deprecated
    public static ConfigClient getConfigClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    public static long getEndTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_END_TIME, -1L);
        if (longExtra == -1) {
            return -1L;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    @Deprecated
    public static GoalsClient getGoalsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    @Deprecated
    public static HistoryClient getHistoryClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    public static RecordingClient getRecordingClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    @Deprecated
    public static SensorsClient getSensorsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    @Deprecated
    public static SessionsClient getSessionsClient(Activity activity, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(activity, (Api.ApiOptions.HasGoogleSignInAccountOptions) new zzi(activity, googleSignInAccount));
    }

    public static long getStartTime(Intent intent, TimeUnit timeUnit) {
        long longExtra = intent.getLongExtra(EXTRA_START_TIME, -1L);
        if (longExtra == -1) {
            return -1L;
        }
        return timeUnit.convert(longExtra, TimeUnit.MILLISECONDS);
    }

    @Deprecated
    public static BleClient getBleClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new BleClient(context, new zzi(context, googleSignInAccount));
    }

    @Deprecated
    public static ConfigClient getConfigClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new ConfigClient(context, new zzi(context, googleSignInAccount));
    }

    @Deprecated
    public static GoalsClient getGoalsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new GoalsClient(context, new zzi(context, googleSignInAccount));
    }

    @Deprecated
    public static HistoryClient getHistoryClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new HistoryClient(context, new zzi(context, googleSignInAccount));
    }

    public static RecordingClient getRecordingClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new RecordingClient(context, new zzi(context, googleSignInAccount));
    }

    @Deprecated
    public static SensorsClient getSensorsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SensorsClient(context, new zzi(context, googleSignInAccount));
    }

    @Deprecated
    public static SessionsClient getSessionsClient(Context context, GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount);
        return new SessionsClient(context, new zzi(context, googleSignInAccount));
    }
}
