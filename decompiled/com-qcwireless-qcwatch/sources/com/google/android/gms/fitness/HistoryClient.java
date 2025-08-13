package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.internal.fitness.zzaj;
import com.google.android.gms.internal.fitness.zzds;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class HistoryClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    public static final /* synthetic */ int zza = 0;
    private static final HistoryApi zzb = new zzds();

    HistoryClient(Activity activity, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) zzaj.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> deleteData(DataDeleteRequest dataDeleteRequest) {
        return PendingResultUtil.toVoidTask(zzb.deleteData(asGoogleApiClient(), dataDeleteRequest));
    }

    public Task<Void> insertData(DataSet dataSet) {
        return PendingResultUtil.toVoidTask(zzb.insertData(asGoogleApiClient(), dataSet));
    }

    public Task<DataSet> readDailyTotal(DataType dataType) {
        return PendingResultUtil.toTask(zzb.readDailyTotal(asGoogleApiClient(), dataType), new PendingResultUtil.ResultConverter() { // from class: com.google.android.gms.fitness.zzk
            @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
            public final Object convert(Result result) {
                int i = HistoryClient.zza;
                return (DataSet) Preconditions.checkNotNull(((DailyTotalResult) result).getTotal());
            }
        });
    }

    public Task<DataSet> readDailyTotalFromLocalDevice(DataType dataType) {
        return PendingResultUtil.toTask(zzb.readDailyTotalFromLocalDevice(asGoogleApiClient(), dataType), new PendingResultUtil.ResultConverter() { // from class: com.google.android.gms.fitness.zzl
            @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
            public final Object convert(Result result) {
                int i = HistoryClient.zza;
                return (DataSet) Preconditions.checkNotNull(((DailyTotalResult) result).getTotal());
            }
        });
    }

    public Task<DataReadResponse> readData(DataReadRequest dataReadRequest) {
        return PendingResultUtil.toResponseTask(zzb.readData(asGoogleApiClient(), dataReadRequest), new DataReadResponse());
    }

    public Task<Void> registerDataUpdateListener(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        return PendingResultUtil.toVoidTask(zzb.registerDataUpdateListener(asGoogleApiClient(), dataUpdateListenerRegistrationRequest));
    }

    public Task<Void> unregisterDataUpdateListener(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zzb.unregisterDataUpdateListener(asGoogleApiClient(), pendingIntent));
    }

    public Task<Void> updateData(DataUpdateRequest dataUpdateRequest) {
        return PendingResultUtil.toVoidTask(zzb.updateData(asGoogleApiClient(), dataUpdateRequest));
    }

    protected HistoryClient(Context context, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) zzaj.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
