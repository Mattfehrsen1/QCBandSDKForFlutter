package com.qcwireless.qcwatch.ui.home.gps.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.text.TextUtils;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.home.gps.bean.Gps;
import com.qcwireless.qcwatch.ui.home.gps.bean.QcLatLon;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: GpsUtils.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u001a\u0018\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001\u001a\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u001a\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a\u001a\u0018\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0001\u001a\u0018\u0010\u001e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001\u001a+\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\"2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0001¢\u0006\u0002\u0010$\u001a\u0018\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020)\u001a\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0001\u001a\u001e\u0010-\u001a\u00020)2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001\u001a\u0016\u0010.\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001\u001a\u0016\u0010/\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001\u001a\u0016\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0001\u001a\u0016\u00103\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u0001\u001a\u0016\u00104\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0001\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0004\b\b\u0010\u0005\"\u001a\u0010\t\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0003\"\u0004\b\u000b\u0010\u0005¨\u00065"}, d2 = {"a", "", "getA", "()D", "setA", "(D)V", "ee", "getEe", "setEe", "pi", "getPi", "setPi", "bd09_To_Gcj02", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/Gps;", "bd_lat", "bd_lon", "calculatePolyLineLength", "", "polyline", "", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLon;", "fromText", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "text", "", "gcj02_To_Bd09", "gg_lat", "gg_lon", "gcj_To_Gps84", "lat", "lon", "getAddress", "", "lnt", "(Landroid/content/Context;DD)[Ljava/lang/String;", "getFormattedStopWatchTIme", "ms", "", "includeMillis", "", "getPaceColor", "", "pace", "outOfChina", "transform", "transform2", "transformLat", "x", "y", "transformLon", "transformMust", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsUtilsKt {
    private static double a = 6378245.0d;
    private static double ee = 0.006693421622965943d;
    private static double pi = 3.141592653589793d;

    public static final double getPi() {
        return pi;
    }

    public static final void setPi(double d) {
        pi = d;
    }

    public static final double getA() {
        return a;
    }

    public static final void setA(double d) {
        a = d;
    }

    public static final double getEe() {
        return ee;
    }

    public static final void setEe(double d) {
        ee = d;
    }

    public static final Gps gcj_To_Gps84(double d, double d2) {
        Gps gpsTransform = transform(d, d2);
        double d3 = 2;
        return new Gps((d * d3) - gpsTransform.getWgLat(), (d2 * d3) - gpsTransform.getWgLon());
    }

    public static final Gps gcj02_To_Bd09(double d, double d2) {
        double dSqrt = Math.sqrt((d2 * d2) + (d * d)) + (Math.sin(pi * d) * 2.0E-5d);
        double dAtan2 = Math.atan2(d, d2) + (Math.cos(d2 * pi) * 3.0E-6d);
        return new Gps((dSqrt * Math.sin(dAtan2)) + 0.006d, (Math.cos(dAtan2) * dSqrt) + 0.0065d);
    }

    public static final Gps bd09_To_Gcj02(double d, double d2) {
        double d3 = d2 - 0.0065d;
        double d4 = d - 0.006d;
        double dSqrt = Math.sqrt((d3 * d3) + (d4 * d4)) - (Math.sin(pi * d4) * 2.0E-5d);
        double dAtan2 = Math.atan2(d4, d3) - (Math.cos(d3 * pi) * 3.0E-6d);
        return new Gps(dSqrt * Math.sin(dAtan2), Math.cos(dAtan2) * dSqrt);
    }

    public static final boolean outOfChina(Context context, double d, double d2) {
        Intrinsics.checkNotNullParameter(context, "context");
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "language");
        return !StringsKt.contains$default((CharSequence) language, (CharSequence) "zh", false, 2, (Object) null);
    }

    public static final Gps transformMust(double d, double d2) {
        double d3 = d2 - 105.0d;
        double d4 = d - 35.0d;
        double dTransformLat = transformLat(d3, d4);
        double dTransformLon = transformLon(d3, d4);
        double d5 = (d / 180.0d) * pi;
        double dSin = Math.sin(d5);
        double d6 = 1;
        double d7 = d6 - ((ee * dSin) * dSin);
        double dSqrt = Math.sqrt(d7);
        double d8 = a;
        return new Gps(d + ((dTransformLat * 180.0d) / ((((d6 - ee) * d8) / (d7 * dSqrt)) * pi)), d2 + ((dTransformLon * 180.0d) / (((d8 / dSqrt) * Math.cos(d5)) * pi)));
    }

    public static final Gps transform(double d, double d2) {
        if (outOfChina(QCApplication.INSTANCE.getCONTEXT(), d, d2)) {
            return new Gps(d, d2);
        }
        double d3 = d2 - 105.0d;
        double d4 = d - 35.0d;
        double dTransformLat = transformLat(d3, d4);
        double dTransformLon = transformLon(d3, d4);
        double d5 = (d / 180.0d) * pi;
        double dSin = Math.sin(d5);
        double d6 = 1;
        double d7 = d6 - ((ee * dSin) * dSin);
        double dSqrt = Math.sqrt(d7);
        double d8 = a;
        return new Gps(d + ((dTransformLat * 180.0d) / ((((d6 - ee) * d8) / (d7 * dSqrt)) * pi)), d2 + ((dTransformLon * 180.0d) / (((d8 / dSqrt) * Math.cos(d5)) * pi)));
    }

    public static final Gps transform2(double d, double d2) {
        String language = Locale.getDefault().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "language");
        if (StringsKt.contains$default((CharSequence) language, (CharSequence) "zh", false, 2, (Object) null)) {
            double d3 = d2 - 105.0d;
            double d4 = d - 35.0d;
            double dTransformLat = transformLat(d3, d4);
            double dTransformLon = transformLon(d3, d4);
            double d5 = (d / 180.0d) * pi;
            double dSin = Math.sin(d5);
            double d6 = 1;
            double d7 = d6 - ((ee * dSin) * dSin);
            double dSqrt = Math.sqrt(d7);
            double d8 = a;
            return new Gps(d + ((dTransformLat * 180.0d) / ((((d6 - ee) * d8) / (d7 * dSqrt)) * pi)), d2 + ((dTransformLon * 180.0d) / (((d8 / dSqrt) * Math.cos(d5)) * pi)));
        }
        return new Gps(d, d2);
    }

    public static final double transformLat(double d, double d2) {
        double d3 = d * 2.0d;
        return (-100.0d) + d3 + (d2 * 3.0d) + (d2 * 0.2d * d2) + (0.1d * d * d2) + (Math.sqrt(Math.abs(d)) * 0.2d) + ((((Math.sin((d * 6.0d) * pi) * 20.0d) + (Math.sin(d3 * pi) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(pi * d2) * 20.0d) + (Math.sin((d2 / 3.0d) * pi) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * pi) * 160.0d) + (320 * Math.sin((d2 * pi) / 30.0d))) * 2.0d) / 3.0d);
    }

    public static final double transformLon(double d, double d2) {
        double d3 = d * 0.1d;
        return d + 300.0d + (d2 * 2.0d) + (d3 * d) + (d3 * d2) + (Math.sqrt(Math.abs(d)) * 0.1d) + ((((Math.sin((6.0d * d) * pi) * 20.0d) + (Math.sin((d * 2.0d) * pi) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(pi * d) * 20.0d) + (Math.sin((d / 3.0d) * pi) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d / 12.0d) * pi) * 150.0d) + (Math.sin((d / 30.0d) * pi) * 300.0d)) * 2.0d) / 3.0d);
    }

    public static final String[] getAddress(Context context, double d, double d2) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        String[] strArr = new String[3];
        try {
            Geocoder geocoder = new Geocoder(context, Locale.US);
            Geocoder.isPresent();
            List<Address> fromLocation = geocoder.getFromLocation(d, d2, 4);
            if (fromLocation != null && fromLocation.size() > 0) {
                int size = fromLocation.size();
                for (int i = 0; i < size; i++) {
                    Address address = fromLocation.get(i);
                    String locality = address.getLocality();
                    if (TextUtils.isEmpty(locality)) {
                        locality = address.getSubAdminArea();
                    }
                    if (TextUtils.isEmpty(locality)) {
                        String addressLine = address.getAddressLine(0);
                        Intrinsics.checkNotNullExpressionValue(addressLine, "address.getAddressLine(0)");
                        Object[] array = new Regex(",").split(addressLine, 0).toArray(new String[0]);
                        if (array != null) {
                            String[] strArr2 = (String[]) array;
                            if (strArr2.length >= 4) {
                                locality = strArr2[strArr2.length - 4];
                            }
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                        }
                    }
                    if (TextUtils.isEmpty(locality)) {
                        locality = address.getSubLocality();
                    }
                    strArr[1] = locality;
                    strArr[0] = address.getCountryCode();
                    strArr[2] = address.getThoroughfare();
                    if (!TextUtils.isEmpty(locality)) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strArr;
    }

    public static /* synthetic */ String getFormattedStopWatchTIme$default(long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return getFormattedStopWatchTIme(j, z);
    }

    public static final String getFormattedStopWatchTIme(long j, boolean z) {
        long hours = TimeUnit.MILLISECONDS.toHours(j);
        long millis = j - TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long millis2 = millis - TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis2);
        if (!z) {
            StringBuilder sb = new StringBuilder();
            sb.append(hours < 10 ? "0" : "");
            sb.append(hours);
            sb.append(':');
            sb.append(minutes < 10 ? "0" : "");
            sb.append(minutes);
            sb.append(':');
            sb.append(seconds >= 10 ? "" : "0");
            sb.append(seconds);
            return sb.toString();
        }
        long millis3 = (millis2 - TimeUnit.SECONDS.toMillis(seconds)) / 10;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(hours < 10 ? "0" : "");
        sb2.append(hours);
        sb2.append(':');
        sb2.append(minutes < 10 ? "0" : "");
        sb2.append(minutes);
        sb2.append(':');
        sb2.append(seconds < 10 ? "0" : "");
        sb2.append(seconds);
        sb2.append(':');
        sb2.append(millis3 >= 10 ? "" : "0");
        sb2.append(millis3);
        return sb2.toString();
    }

    public static final float calculatePolyLineLength(List<QcLatLon> polyline) {
        Intrinsics.checkNotNullParameter(polyline, "polyline");
        int size = polyline.size() - 2;
        float f = 0.0f;
        if (size >= 0) {
            int i = 0;
            while (true) {
                QcLatLon qcLatLon = polyline.get(i);
                int i2 = i + 1;
                QcLatLon qcLatLon2 = polyline.get(i2);
                float[] fArr = new float[1];
                Location.distanceBetween(qcLatLon.getLatitude(), qcLatLon.getLongitude(), qcLatLon2.getLatitude(), qcLatLon2.getLongitude(), fArr);
                f += fArr[0];
                if (i == size) {
                    break;
                }
                i = i2;
            }
        }
        return f;
    }

    public static final int getPaceColor(double d) {
        double d2 = d * 2.5d;
        if (d2 >= 15.0d) {
            return SupportMenu.CATEGORY_MASK;
        }
        if (d2 < 15.0d && d2 >= 11.0d) {
            return Color.argb(255, 255, 97 - ((int) (97 * ((d2 - 11) / 4))), 0);
        }
        if (d2 >= 11.0d || d2 < 7.0d) {
            return -16711936;
        }
        return Color.argb(255, 255, 255 - ((int) (158 * ((d2 - 7) / 4))), 0);
    }

    public static final Bitmap fromText(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        Paint paint = new Paint();
        paint.setTextSize(30.0f);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        Intrinsics.checkNotNullExpressionValue(paint.getFontMetricsInt(), "paint.fontMetricsInt");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(100, 100, Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, R.mipmap.gps_flag);
        canvas.drawBitmap(bitmapDecodeResource, (Rect) null, new Rect(0, 0, bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight()), paint);
        canvas.drawText(text, 0.0f, (r1.leading - r1.ascent) * 2, paint);
        canvas.save();
        return bitmapCreateBitmap;
    }
}
