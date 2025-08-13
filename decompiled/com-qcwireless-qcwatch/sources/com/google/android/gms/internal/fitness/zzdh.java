package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.GoalsApi;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public final class zzdh implements GoalsApi {
    @Override // com.google.android.gms.fitness.GoalsApi
    public final PendingResult<GoalsResult> readCurrentGoals(GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest) {
        return googleApiClient.enqueue(new zzdg(this, googleApiClient, goalsReadRequest));
    }
}
