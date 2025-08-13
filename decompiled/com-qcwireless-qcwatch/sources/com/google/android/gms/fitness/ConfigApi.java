package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public interface ConfigApi {
    @Deprecated
    PendingResult<DataTypeResult> createCustomDataType(GoogleApiClient googleApiClient, DataTypeCreateRequest dataTypeCreateRequest);

    PendingResult<Status> disableFit(GoogleApiClient googleApiClient);

    @Deprecated
    PendingResult<DataTypeResult> readDataType(GoogleApiClient googleApiClient, String str);
}
