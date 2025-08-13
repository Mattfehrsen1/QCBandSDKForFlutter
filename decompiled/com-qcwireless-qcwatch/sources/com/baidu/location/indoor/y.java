package com.baidu.location.indoor;

import android.location.Location;
import android.os.Handler;
import com.baidu.location.BDLocation;

/* loaded from: classes.dex */
public class y {
    private a a;
    private BDLocation c;
    private long b = 450;
    private b d = null;
    private b e = null;
    private b f = new b();
    private b g = new b();
    private b h = new b();
    private b i = new b();
    private BDLocation j = null;
    private long k = -1;
    private boolean l = false;
    private Handler m = new Handler();
    private Runnable n = new z(this);
    private Runnable o = new aa(this);

    public interface a {
        void a(BDLocation bDLocation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b {
        public double a;
        public double b;

        public b() {
            this.a = 0.0d;
            this.b = 0.0d;
        }

        public b(double d, double d2) {
            this.a = d;
            this.b = d2;
        }

        public b(b bVar) {
            this.a = bVar.a;
            this.b = bVar.b;
        }

        public b a(double d) {
            return y.this.new b(this.a * d, this.b * d);
        }

        public b a(b bVar) {
            return y.this.new b(this.a - bVar.a, this.b - bVar.b);
        }

        public b b(b bVar) {
            return y.this.new b(this.a + bVar.a, this.b + bVar.b);
        }

        public boolean b(double d) {
            double dAbs = Math.abs(this.a);
            double dAbs2 = Math.abs(this.b);
            return dAbs > 0.0d && dAbs < d && dAbs2 > 0.0d && dAbs2 < d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public b a(b bVar) {
        b bVar2 = this.d;
        if (bVar2 == null || bVar == null) {
            return null;
        }
        b bVarA = bVar2.a(bVar);
        this.i = this.i.b(bVarA);
        b bVarA2 = this.h.a(this.f);
        this.f = new b(this.h);
        this.h = new b(bVarA);
        b bVarA3 = bVarA.a(0.2d);
        b bVarA4 = this.i.a(0.01d);
        return bVarA3.b(bVarA4).b(bVarA2.a(-0.02d));
    }

    public void a() {
        if (this.l) {
            this.l = false;
            this.m.removeCallbacks(this.o);
            b();
        }
    }

    public void a(long j) {
        this.b = j;
    }

    public synchronized void a(BDLocation bDLocation) {
        double latitude = bDLocation.getLatitude();
        double longitude = bDLocation.getLongitude();
        this.c = bDLocation;
        this.d = new b(latitude, longitude);
        if (this.e == null) {
            this.e = new b(latitude, longitude);
        }
        BDLocation bDLocation2 = this.j;
        if (bDLocation2 == null) {
            this.j = new BDLocation(bDLocation);
        } else {
            double latitude2 = bDLocation2.getLatitude();
            double longitude2 = this.j.getLongitude();
            double latitude3 = bDLocation.getLatitude();
            double longitude3 = bDLocation.getLongitude();
            float[] fArr = new float[2];
            Location.distanceBetween(latitude2, longitude2, latitude3, longitude3, fArr);
            if (fArr[0] > 10.0f) {
                this.j.setLatitude(latitude3);
                this.j.setLongitude(longitude3);
            } else {
                this.j.setLatitude((latitude2 + latitude3) / 2.0d);
                this.j.setLongitude((longitude2 + longitude3) / 2.0d);
            }
        }
    }

    public void b() {
        this.k = -1L;
        this.e = null;
        this.d = null;
        this.f = new b();
        this.g = new b();
        this.h = new b();
        this.i = new b();
    }

    public boolean c() {
        return this.l;
    }
}
