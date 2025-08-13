package com.baidu.location.indoor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.location.indoor.v;
import java.util.Iterator;

/* loaded from: classes.dex */
class w implements SensorEventListener {
    final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        String strA;
        int type = sensorEvent.sensor.getType();
        if (type != 1) {
            if (type != 11) {
                return;
            }
            SensorManager.getRotationMatrixFromVector(this.a.i, (float[]) sensorEvent.values.clone());
            SensorManager.getOrientation(this.a.i, this.a.h);
            float[] fArr = new float[3];
            double degrees = Math.toDegrees(this.a.h[0]);
            if (degrees < 0.0d) {
                fArr[2] = ((float) (degrees + 360.0d)) % 360.0f;
            } else {
                fArr[2] = (float) degrees;
            }
            this.a.j = fArr[2];
            fArr[0] = (float) Math.toDegrees(this.a.h[1]);
            fArr[1] = (float) Math.toDegrees(this.a.h[2]);
            if (this.a.g && com.baidu.location.indoor.mapversion.a.c()) {
                com.baidu.location.indoor.mapversion.a.a(5, fArr, System.currentTimeMillis());
                return;
            }
            return;
        }
        float[] fArr2 = (float[]) sensorEvent.values.clone();
        this.a.m = System.currentTimeMillis();
        if (!this.a.g || !com.baidu.location.indoor.mapversion.a.c() || (strA = com.baidu.location.indoor.mapversion.a.a(1, fArr2, System.currentTimeMillis())) == null || strA.length() <= 1) {
            return;
        }
        float fD = com.baidu.location.indoor.mapversion.a.d();
        if (fD > 0.01f) {
            v.b(this.a);
        }
        try {
            synchronized (this.a.b) {
                Iterator it = this.a.b.iterator();
                while (it.hasNext()) {
                    ((v.b) it.next()).a(fD, this.a.j, this.a.j, System.currentTimeMillis(), strA);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
