package com.google.android.gms.fitness.result;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class DataReadResponse extends Response<DataReadResult> {
    public List<Bucket> getBuckets() {
        return getResult().getBuckets();
    }

    public DataSet getDataSet(DataSource dataSource) {
        return getResult().getDataSet(dataSource);
    }

    public List<DataSet> getDataSets() {
        return getResult().getDataSets();
    }

    public Status getStatus() {
        return getResult().getStatus();
    }

    public DataSet getDataSet(DataType dataType) {
        return getResult().getDataSet(dataType);
    }
}
