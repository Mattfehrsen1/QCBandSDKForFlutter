package com.qcwireless.qcwatch.ui.base.util;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import com.elvishew.xlog.XLog;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes3.dex */
public class LocationUtils {
    private static final String TAG = "FloatBall";
    static LocationUtils locationUtils;
    private LocationManager locationManager;

    private enum permission {
        ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION
    }

    public static boolean isGPSEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager != null && locationManager.isProviderEnabled("gps");
    }

    public static LocationUtils getInstance() {
        if (locationUtils == null) {
            locationUtils = new LocationUtils();
        }
        return locationUtils;
    }

    public String getLocations(Context context) {
        String str = "";
        if (!isOPenGPS(context)) {
            return "";
        }
        checkPermission(context, permission.ACCESS_COARSE_LOCATION);
        try {
            this.locationManager = (LocationManager) context.getSystemService("location");
            Criteria criteria = new Criteria();
            criteria.setAccuracy(2);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(1);
            String bestProvider = this.locationManager.getBestProvider(criteria, true);
            XLog.i("Location Provider is " + bestProvider);
            Location lastKnownLocation = this.locationManager.getLastKnownLocation(bestProvider);
            if (lastKnownLocation == null) {
                return "";
            }
            String str2 = "" + getLocationAddress(context, lastKnownLocation);
            try {
                if (!str2.equals("")) {
                    return str2;
                }
                return str2 + "" + convertAddress(context, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            } catch (SecurityException e) {
                e = e;
                str = str2;
                e.printStackTrace();
                return str;
            } catch (Exception e2) {
                e = e2;
                str = str2;
                e.printStackTrace();
                return str;
            }
        } catch (SecurityException e3) {
            e = e3;
        } catch (Exception e4) {
            e = e4;
        }
    }

    public String convertAddress(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.US);
        StringBuilder sb = new StringBuilder();
        try {
            List<Address> fromLocation = geocoder.getFromLocation(latitude, longitude, 1);
            if (!fromLocation.isEmpty()) {
                Address address = fromLocation.get(0);
                sb.append(address.getLocality());
                sb.append(address.getThoroughfare());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private boolean checkPermission(Context context, permission permName) {
        StringBuilder sb = new StringBuilder();
        sb.append("android.permission.");
        sb.append(permName.toString());
        return context.checkCallingOrSelfPermission(sb.toString()) == 0;
    }

    private String getLocationAddress(Context mContext, Location location) {
        String addressLine;
        try {
            Address address = new Geocoder(mContext, Locale.CHINESE).getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
            Log.w("wqs", "远程获取定位全部为: " + address.toString());
            if (address.getAddressLine(0) != null && !address.getAddressLine(0).equals("")) {
                addressLine = address.getAddressLine(0);
                Log.w("wqs", "获取成功第一种: " + addressLine);
            } else if (address.getFeatureName() != null && !address.getFeatureName().equals("")) {
                addressLine = address.getLocality() + address.getFeatureName();
                Log.w("wqs", "获取成功第二种: " + addressLine);
            } else {
                if (address.getMaxAddressLineIndex() >= 2) {
                    addressLine = address.getAddressLine(1) + address.getAddressLine(2);
                } else {
                    addressLine = address.getAddressLine(1);
                }
                Log.w("wqs", "获取成功第三种: " + addressLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            addressLine = "";
        }
        return addressLine.contains("null") ? addressLine.replaceAll("null", "") : addressLine;
    }

    private boolean isOPenGPS(final Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }
}
