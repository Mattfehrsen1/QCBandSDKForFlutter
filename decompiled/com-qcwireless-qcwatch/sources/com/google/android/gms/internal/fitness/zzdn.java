package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
final class zzdn extends zzae {
    final /* synthetic */ DataReadRequest zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdn(zzds zzdsVar, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        super(googleApiClient);
        this.zza = dataReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        List<DataType> dataTypes = this.zza.getDataTypes();
        List<DataSource> dataSources = this.zza.getDataSources();
        ArrayList arrayList = new ArrayList();
        Iterator<DataSource> it = dataSources.iterator();
        while (it.hasNext()) {
            arrayList.add(DataSet.builder(it.next()).build());
        }
        for (DataType dataType : dataTypes) {
            DataSource.Builder builder = new DataSource.Builder();
            builder.setType(1);
            builder.setDataType(dataType);
            builder.setStreamName("Default");
            arrayList.add(DataSet.builder(builder.build()).build());
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzca) ((zzaj) anyClient).getService()).zzg(new DataReadRequest(this.zza, new zzdr(this, null)));
    }
}
