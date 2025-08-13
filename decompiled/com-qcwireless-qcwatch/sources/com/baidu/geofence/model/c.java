package com.baidu.geofence.model;

import android.location.Location;
import com.baidu.location.BDLocation;
import com.baidu.location.indoor.u;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class c {
    public static boolean a(DPoint dPoint, List<DPoint> list) {
        int size = list.size();
        DPoint dPoint2 = list.get(0);
        int i = 1;
        int i2 = 0;
        while (i <= size) {
            if (dPoint.equals(dPoint2)) {
                return true;
            }
            DPoint dPoint3 = list.get(i % size);
            if (dPoint.getLatitude() >= Math.min(dPoint2.getLatitude(), dPoint3.getLatitude()) && dPoint.getLatitude() <= Math.max(dPoint2.getLatitude(), dPoint3.getLatitude())) {
                if (dPoint.getLatitude() <= Math.min(dPoint2.getLatitude(), dPoint3.getLatitude()) || dPoint.getLatitude() >= Math.max(dPoint2.getLatitude(), dPoint3.getLatitude())) {
                    if (dPoint.getLatitude() == dPoint3.getLatitude() && dPoint.getLongitude() <= dPoint3.getLongitude()) {
                        DPoint dPoint4 = list.get((i + 1) % size);
                        i2 = (dPoint.getLatitude() < Math.min(dPoint2.getLatitude(), dPoint4.getLatitude()) || dPoint.getLatitude() > Math.max(dPoint2.getLatitude(), dPoint4.getLatitude())) ? i2 + 2 : i2 + 1;
                    }
                } else if (dPoint.getLongitude() > Math.max(dPoint2.getLongitude(), dPoint3.getLongitude())) {
                    continue;
                } else {
                    if (dPoint2.getLatitude() == dPoint3.getLatitude() && dPoint.getLongitude() >= Math.min(dPoint2.getLongitude(), dPoint3.getLongitude())) {
                        return true;
                    }
                    if (dPoint2.getLongitude() != dPoint3.getLongitude()) {
                        double latitude = (((dPoint.getLatitude() - dPoint2.getLatitude()) * (dPoint3.getLongitude() - dPoint2.getLongitude())) / (dPoint3.getLatitude() - dPoint2.getLatitude())) + dPoint2.getLongitude();
                        if (Math.abs(dPoint.getLongitude() - latitude) < 2.0E-10d) {
                            return true;
                        }
                        if (dPoint.getLongitude() < latitude) {
                        }
                    } else if (dPoint2.getLongitude() == dPoint.getLongitude()) {
                        return true;
                    }
                }
            }
            i++;
            dPoint2 = dPoint3;
        }
        return i2 % 2 != 0;
    }

    public static boolean a(BDLocation bDLocation, ArrayList<DPoint> arrayList) {
        double d = 0.0d;
        double latitude = 0.0d;
        double longitude = 0.0d;
        for (int i = 0; i < arrayList.size(); i++) {
            latitude += arrayList.get(i).getLatitude();
            longitude += arrayList.get(i).getLongitude();
        }
        DPoint dPoint = new DPoint(latitude / arrayList.size(), longitude / arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            double dA = u.a(arrayList.get(i2).getLatitude(), arrayList.get(i2).getLongitude(), dPoint.getLatitude(), dPoint.getLongitude());
            if (dA > d) {
                d = dA;
            }
        }
        float[] fArr = new float[2];
        Location.distanceBetween(bDLocation.getLatitude(), bDLocation.getLongitude(), dPoint.getLatitude(), dPoint.getLongitude(), fArr);
        return ((double) fArr[0]) < d + 500.0d;
    }
}
