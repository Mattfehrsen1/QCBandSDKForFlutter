package com.qcwireless.qcwatch.ui.base.bean.weather;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyLocationBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/weather/MyLocationBean;", "", "longitude", "", "latitude", "language", "", "city", "address", "(FFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getCity", "getLanguage", "getLatitude", "()F", "getLongitude", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class MyLocationBean {
    private final String address;
    private final String city;
    private final String language;
    private final float latitude;
    private final float longitude;

    public static /* synthetic */ MyLocationBean copy$default(MyLocationBean myLocationBean, float f, float f2, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            f = myLocationBean.longitude;
        }
        if ((i & 2) != 0) {
            f2 = myLocationBean.latitude;
        }
        float f3 = f2;
        if ((i & 4) != 0) {
            str = myLocationBean.language;
        }
        String str4 = str;
        if ((i & 8) != 0) {
            str2 = myLocationBean.city;
        }
        String str5 = str2;
        if ((i & 16) != 0) {
            str3 = myLocationBean.address;
        }
        return myLocationBean.copy(f, f3, str4, str5, str3);
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

    /* renamed from: component5, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    public final MyLocationBean copy(float longitude, float latitude, String language, String city, String address) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(city, "city");
        Intrinsics.checkNotNullParameter(address, "address");
        return new MyLocationBean(longitude, latitude, language, city, address);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MyLocationBean)) {
            return false;
        }
        MyLocationBean myLocationBean = (MyLocationBean) other;
        return Intrinsics.areEqual((Object) Float.valueOf(this.longitude), (Object) Float.valueOf(myLocationBean.longitude)) && Intrinsics.areEqual((Object) Float.valueOf(this.latitude), (Object) Float.valueOf(myLocationBean.latitude)) && Intrinsics.areEqual(this.language, myLocationBean.language) && Intrinsics.areEqual(this.city, myLocationBean.city) && Intrinsics.areEqual(this.address, myLocationBean.address);
    }

    public int hashCode() {
        return (((((((Float.floatToIntBits(this.longitude) * 31) + Float.floatToIntBits(this.latitude)) * 31) + this.language.hashCode()) * 31) + this.city.hashCode()) * 31) + this.address.hashCode();
    }

    public String toString() {
        return "MyLocationBean(longitude=" + this.longitude + ", latitude=" + this.latitude + ", language=" + this.language + ", city=" + this.city + ", address=" + this.address + ')';
    }

    public MyLocationBean(float f, float f2, String language, String city, String address) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(city, "city");
        Intrinsics.checkNotNullParameter(address, "address");
        this.longitude = f;
        this.latitude = f2;
        this.language = language;
        this.city = city;
        this.address = address;
    }

    public final String getAddress() {
        return this.address;
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
