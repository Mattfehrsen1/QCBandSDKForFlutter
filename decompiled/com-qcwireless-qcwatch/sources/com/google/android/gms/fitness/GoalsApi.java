package com.google.android.gms.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.GoalsReadRequest;
import com.google.android.gms.fitness.result.GoalsResult;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
@Deprecated
/* loaded from: classes2.dex */
public interface GoalsApi {
    PendingResult<GoalsResult> readCurrentGoals(GoogleApiClient googleApiClient, GoalsReadRequest goalsReadRequest);
}
