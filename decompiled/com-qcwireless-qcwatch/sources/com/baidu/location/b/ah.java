package com.baidu.location.b;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* loaded from: classes.dex */
public class ah implements SensorEventListener {
    private float[] a;
    private SensorManager b;
    private float c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;

    private static class a {
        private static final ah a = new ah();
    }

    private ah() {
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
    }

    public static ah a() {
        return a.a;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public synchronized void b() {
        Sensor defaultSensor;
        if (this.g) {
            return;
        }
        if (this.d || f()) {
            if (this.b == null) {
                this.b = (SensorManager) com.baidu.location.f.getServiceContext().getSystemService("sensor");
            }
            SensorManager sensorManager = this.b;
            if (sensorManager != null && (defaultSensor = sensorManager.getDefaultSensor(11)) != null && (this.d || f())) {
                this.b.registerListener(this, defaultSensor, 3);
            }
            this.g = true;
        }
    }

    public void b(boolean z) {
        this.e = z;
    }

    public synchronized void c() {
        if (this.g) {
            if (f()) {
                return;
            }
            SensorManager sensorManager = this.b;
            if (sensorManager != null) {
                sensorManager.unregisterListener(this);
                this.b = null;
            }
            this.g = false;
        }
    }

    public boolean d() {
        return this.d;
    }

    public float e() {
        return this.c;
    }

    public boolean f() {
        return this.f;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != 11) {
            return;
        }
        float[] fArr = (float[]) sensorEvent.values.clone();
        this.a = fArr;
        if (fArr != null) {
            float[] fArr2 = new float[9];
            try {
                SensorManager.getRotationMatrixFromVector(fArr2, fArr);
                SensorManager.getOrientation(fArr2, new float[3]);
                float degrees = (float) Math.toDegrees(r5[0]);
                this.c = degrees;
                if (degrees < 0.0f) {
                    degrees += 360.0f;
                }
                this.c = (float) Math.floor(degrees);
            } catch (Exception unused) {
                this.c = 0.0f;
            }
        }
    }
}
