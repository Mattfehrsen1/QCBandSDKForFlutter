package com.qcwireless.qcwatch.ui.base.bean.request.weather;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeatherRequest.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/weather/WeatherRequest;", "", "longitude", "", "latitude", "language", "", "city", "(FFLjava/lang/String;Ljava/lang/String;)V", "getCity", "()Ljava/lang/String;", "getLanguage", "getLatitude", "()F", "getLongitude", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WeatherRequest {
    private final String city;
    private final String language;
    private final float latitude;
    private final float longitude;

    public static /* synthetic */ WeatherRequest copy$default(WeatherRequest weatherRequest, float f, float f2, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = weatherRequest.longitude;
        }
        if ((i & 2) != 0) {
            f2 = weatherRequest.latitude;
        }
        if ((i & 4) != 0) {
            str = weatherRequest.language;
        }
        if ((i & 8) != 0) {
            str2 = weatherRequest.city;
        }
        return weatherRequest.copy(f, f2, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final float getLongitude() {
        return this.longitude;
    }

    /* renamed from: component2, reason: from getter */
    public final float getLatitude() {
        return this.latitude;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    public final WeatherRequest copy(float longitude, float latitude, String language, String city) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(city, "city");
        return new WeatherRequest(longitude, latitude, language, city);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeatherRequest)) {
            return false;
        }
        WeatherRequest weatherRequest = (WeatherRequest) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this.longitude), (Object) Float.valueOf(weatherRequest.longitude)) && Intrinsics.areEqual((Object) Float.valueOf(this.latitude), (Object) Float.valueOf(weatherRequest.latitude)) && Intrinsics.areEqual(this.language, weatherRequest.language) && Intrinsics.areEqual(this.city, weatherRequest.city);
    }

    public int hashCode() {
        return (((((Float.floatToIntBits(this.longitude) * 31) + Float.floatToIntBits(this.latitude)) * 31) + this.language.hashCode()) * 31) + this.city.hashCode();
    }

    public String toString() {
        return "WeatherRequest(longitude=" + this.longitude + ", latitude=" + this.latitude + ", language=" + this.language + ", city=" + this.city + ')';
    }

    public WeatherRequest(float f, float f2, String language, String city) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(city, "city");
        this.longitude = f;
        this.latitude = f2;
        this.language = language;
        this.city = city;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final float getLatitude() {
        return this.latitude;
    }

    public final float getLongitude() {
        return this.longitude;
    }
}
