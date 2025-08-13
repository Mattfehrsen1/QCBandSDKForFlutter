package com.google.android.gms.fitness;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.fitness.zzaz;
import com.google.android.gms.internal.fitness.zzee;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class SensorsClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final SensorsApi zza = new zzee();

    SensorsClient(Activity activity, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) zzaz.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> add(SensorRequest sensorRequest, PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zza.add(asGoogleApiClient(), sensorRequest, pendingIntent));
    }

    public Task<List<DataSource>> findDataSources(DataSourcesRequest dataSourcesRequest) {
        return PendingResultUtil.toTask(zza.findDataSources(asGoogleApiClient(), dataSourcesRequest), new PendingResultUtil.ResultConverter() { // from class: com.google.android.gms.fitness.zzn
            @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
            public final Object convert(Result result) {
                return ((DataSourcesResult) result).getDataSources();
            }
        });
    }

    public Task<Void> remove(PendingIntent pendingIntent) {
        return PendingResultUtil.toVoidTask(zza.remove(asGoogleApiClient(), pendingIntent));
    }

    protected SensorsClient(Context context, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) zzaz.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> add(SensorRequest sensorRequest, OnDataPointListener onDataPointListener) {
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(onDataPointListener, "OnDataPointListener");
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(listenerHolderRegisterListener).register(new zzp(this, listenerHolderRegisterListener, sensorRequest)).unregister(new zzo(this, listenerHolderRegisterListener)).build());
    }

    public Task<Boolean> remove(OnDataPointListener onDataPointListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(onDataPointListener, "OnDataPointListener"));
    }
}
