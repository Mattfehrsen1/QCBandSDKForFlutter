package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.result.BleDevicesResult;
import com.google.android.gms.internal.fitness.zzcy;
import com.google.android.gms.internal.fitness.zzet;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public class BleClient extends GoogleApi<Api.ApiOptions.HasGoogleSignInAccountOptions> {
    private static final BleApi zza;

    static {
        zza = PlatformVersion.isAtLeastJellyBeanMR2() ? new zzcy() : new zzet();
    }

    BleClient(Activity activity, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(activity, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) com.google.android.gms.internal.fitness.zzm.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> claimBleDevice(BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zza.claimBleDevice(asGoogleApiClient(), bleDevice));
    }

    public Task<List<BleDevice>> listClaimedBleDevices() {
        return PendingResultUtil.toTask(zza.listClaimedBleDevices(asGoogleApiClient()), new PendingResultUtil.ResultConverter() { // from class: com.google.android.gms.fitness.zza
            @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
            public final Object convert(Result result) {
                return ((BleDevicesResult) result).getClaimedBleDevices();
            }
        });
    }

    public Task<Void> startBleScan(List<DataType> list, int i, BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzet.zza));
        }
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(bleScanCallback, "BleScanCallback");
        return doRegisterEventListener(RegistrationMethods.builder().withHolder(listenerHolderRegisterListener).register(new zzc(this, listenerHolderRegisterListener, list, i)).unregister(new zzb(this, listenerHolderRegisterListener)).build());
    }

    public Task<Boolean> stopBleScan(BleScanCallback bleScanCallback) {
        return !PlatformVersion.isAtLeastJellyBeanMR2() ? Tasks.forException(new ApiException(zzet.zza)) : doUnregisterEventListener(ListenerHolders.createListenerKey(bleScanCallback, "BleScanCallback"));
    }

    public Task<Void> unclaimBleDevice(BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zza.unclaimBleDevice(asGoogleApiClient(), bleDevice));
    }

    protected BleClient(Context context, Api.ApiOptions.HasGoogleSignInAccountOptions hasGoogleSignInAccountOptions) {
        super(context, (Api<Api.ApiOptions.HasGoogleSignInAccountOptions>) com.google.android.gms.internal.fitness.zzm.zzg, hasGoogleSignInAccountOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> claimBleDevice(String str) {
        return PendingResultUtil.toVoidTask(zza.claimBleDevice(asGoogleApiClient(), str));
    }

    public Task<Void> unclaimBleDevice(String str) {
        return PendingResultUtil.toVoidTask(zza.unclaimBleDevice(asGoogleApiClient(), str));
    }
}
