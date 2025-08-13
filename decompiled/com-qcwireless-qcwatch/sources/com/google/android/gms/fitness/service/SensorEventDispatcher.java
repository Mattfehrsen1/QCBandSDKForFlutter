package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.fitness.data.DataPoint;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-fitness@@21.1.0 */
/* loaded from: classes2.dex */
public interface SensorEventDispatcher {
    void publish(DataPoint dataPoint) throws RemoteException;

    void publish(List<DataPoint> list) throws RemoteException;
}
