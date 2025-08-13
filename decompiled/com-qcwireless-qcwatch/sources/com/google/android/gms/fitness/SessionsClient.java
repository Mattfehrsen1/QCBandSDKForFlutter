package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResponse;
import com.google.android.gms.fitness.result.SessionStopResult;
import com.google.android.gms.internal.fitness.zzbh;
import com.google.android.gms.internal.fitness.zzep;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class SessionsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final SessionsApi zza = new zzep();

    SessionsClient(Activity activity, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) zzbh.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> insertSession(SessionInsertRequest sessionInsertRequest) {
        return PendingResultUtil.toVoidTask(zza.insertSession(asGoogleApiClient(), sessionInsertRequest));
    }

    public Task<SessionReadResponse> readSession(SessionReadRequest sessionReadRequest) {
        return PendingResultUtil.toResponseTask(zza.readSession(asGoogleApiClient(), sessionReadRequest), new SessionReadResponse());
    }

    public Task<Void> registerForSessions(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zza.registerForSessions(asGoogleApiClient(), pendingIntent));
    }

    public Task<Void> startSession(Session session) {
        return PendingResultUtil.toVoidTask(zza.startSession(asGoogleApiClient(), session));
    }

    public Task<List<Session>> stopSession(String str) {
        return PendingResultUtil.toTask(zza.stopSession(asGoogleApiClient(), str), new PendingResultUtil.ResultConverter() { // from class: com.google.android.gms.fitness.zzq
            @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
            public final Object convert(Result result) {
                return ((SessionStopResult) result).getSessions();
            }
        });
    }

    public Task<Void> unregisterForSessions(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zza.unregisterForSessions(asGoogleApiClient(), pendingIntent));
    }

    protected SessionsClient(Context context, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) zzbh.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
