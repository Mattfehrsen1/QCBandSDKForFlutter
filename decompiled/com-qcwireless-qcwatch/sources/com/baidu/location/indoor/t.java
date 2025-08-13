package com.baidu.location.indoor;

import android.location.Location;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class t {
    private List<Location> a;
    private String b;
    private Location c = null;

    t(String str, Location[] locationArr) {
        if (locationArr == null || locationArr.length <= 0) {
            return;
        }
        a(locationArr);
        this.b = str;
    }

    private void a(Location[] locationArr) {
        if (locationArr == null || locationArr.length <= 0) {
            return;
        }
        if (this.a == null) {
            this.a = new ArrayList();
        }
        double latitude = 0.0d;
        double longitude = 0.0d;
        for (int i = 0; i < locationArr.length; i++) {
            latitude += locationArr[i].getLatitude();
            longitude += locationArr[i].getLongitude();
            this.a.add(locationArr[i]);
        }
        if (this.c == null) {
            Location location = new Location("gps");
            this.c = location;
            location.setLatitude(latitude / locationArr.length);
            this.c.setLongitude(longitude / locationArr.length);
        }
    }

    public String a() {
        return this.b;
    }
}
