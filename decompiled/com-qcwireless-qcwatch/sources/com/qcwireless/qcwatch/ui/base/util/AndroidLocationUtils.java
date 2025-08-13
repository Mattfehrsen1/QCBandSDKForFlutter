package com.qcwireless.qcwatch.ui.base.util;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidLocationUtils.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/util/AndroidLocationUtils;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getLocation", "", "context", "Landroid/content/Context;", "callback", "Lcom/qcwireless/qcwatch/ui/base/util/Callback;", "getLocationByNetwork", "getLocationListener", "Landroid/location/LocationListener;", "isLocationProviderEnabled", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidLocationUtils {
    public static final AndroidLocationUtils INSTANCE = new AndroidLocationUtils();
    private static final String TAG = "LocationUtils";

    private AndroidLocationUtils() {
    }

    public final void getLocation(final Context context, final Callback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final LocationListener locationListener = getLocationListener(callback);
        try {
            ThreadExtKt.ktxRunOnUi(this, new Function1<AndroidLocationUtils, Unit>() { // from class: com.qcwireless.qcwatch.ui.base.util.AndroidLocationUtils.getLocation.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AndroidLocationUtils androidLocationUtils) {
                    invoke2(androidLocationUtils);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AndroidLocationUtils ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    Object systemService = context.getSystemService("location");
                    Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
                    LocationManager locationManager = (LocationManager) systemService;
                    if (!locationManager.isProviderEnabled("gps")) {
                        ktxRunOnUi.getLocationByNetwork(context, callback);
                    } else {
                        locationManager.requestLocationUpdates("gps", 3000L, 100.0f, locationListener);
                    }
                }
            });
        } catch (Exception e) {
            XLog.e(e.getMessage());
        }
    }

    private final LocationListener getLocationListener(final Callback callback) {
        return new LocationListener() { // from class: com.qcwireless.qcwatch.ui.base.util.AndroidLocationUtils.getLocationListener.1
            @Override // android.location.LocationListener
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Intrinsics.checkNotNullParameter(provider, "provider");
                Intrinsics.checkNotNullParameter(extras, "extras");
                XLog.e("11111onStatusChanged");
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String provider) {
                Intrinsics.checkNotNullParameter(provider, "provider");
                XLog.e("11111onProviderEnabled");
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String provider) {
                Intrinsics.checkNotNullParameter(provider, "provider");
                XLog.e("1111onProviderDisabled");
            }

            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                Intrinsics.checkNotNullParameter(location, "location");
                XLog.e("1111111111");
                callback.onLocationChanged(location);
            }
        };
    }

    public final boolean isLocationProviderEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("location");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager locationManager = (LocationManager) systemService;
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getLocationByNetwork(Context context, Callback callback) {
        LocationListener locationListener = getLocationListener(callback);
        Object systemService = context.getSystemService("location");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager locationManager = (LocationManager) systemService;
        try {
            if (locationManager.isProviderEnabled("network")) {
                locationManager.requestLocationUpdates("network", 3000L, 10.0f, locationListener);
            }
        } catch (Exception e) {
            XLog.e(e.getMessage());
        }
    }
}
