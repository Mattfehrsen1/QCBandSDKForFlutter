package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public interface SessionsApi {

    /* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
    public static class ViewIntentBuilder {
        private final Context zza;
        private Session zzb;
        private String zzc;
        private boolean zzd = false;

        public ViewIntentBuilder(Context context) {
            this.zza = context;
        }

        public Intent build() {
            Intent intent;
            ResolveInfo resolveInfoResolveActivity;
            Preconditions.checkState(this.zzb != null, "Session must be set");
            Intent intent2 = new Intent(Fitness.ACTION_VIEW);
            intent2.setType(Session.getMimeType(this.zzb.getActivity()));
            SafeParcelableSerializer.serializeToIntentExtra(this.zzb, intent2, Session.EXTRA_SESSION);
            if (!this.zzd) {
                this.zzc = this.zzb.getAppPackageName();
            }
            String str = this.zzc;
            if (str == null || (resolveInfoResolveActivity = this.zza.getPackageManager().resolveActivity((intent = new Intent(intent2).setPackage(str)), 0)) == null) {
                return intent2;
            }
            intent.setComponent(new ComponentName(str, resolveInfoResolveActivity.activityInfo.name));
            return intent;
        }

        public ViewIntentBuilder setPreferredApplication(String str) {
            this.zzc = str;
            this.zzd = true;
            return this;
        }

        public ViewIntentBuilder setSession(Session session) {
            this.zzb = session;
            return this;
        }
    }

    PendingResult<Status> insertSession(GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest);

    PendingResult<SessionReadResult> readSession(GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest);

    PendingResult<Status> registerForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    PendingResult<Status> startSession(GoogleApiClient googleApiClient, Session session);

    PendingResult<SessionStopResult> stopSession(GoogleApiClient googleApiClient, String str);

    PendingResult<Status> unregisterForSessions(GoogleApiClient googleApiClient, PendingIntent pendingIntent);
}
