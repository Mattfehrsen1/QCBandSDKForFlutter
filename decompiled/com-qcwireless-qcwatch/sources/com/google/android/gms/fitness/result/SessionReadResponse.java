package com.google.android.gms.fitness.result;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public class SessionReadResponse extends Response<SessionReadResult> {
    public List<DataSet> getDataSet(Session session) {
        return getResult().getDataSet(session);
    }

    public List<Session> getSessions() {
        return getResult().getSessions();
    }

    public Status getStatus() {
        return getResult().getStatus();
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        return getResult().getDataSet(session, dataType);
    }
}
