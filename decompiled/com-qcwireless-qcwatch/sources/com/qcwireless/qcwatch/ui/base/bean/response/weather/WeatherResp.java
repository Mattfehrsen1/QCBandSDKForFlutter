package com.qcwireless.qcwatch.ui.base.bean.response.weather;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WeatherResp.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b&\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003J[\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010/\u001a\u00020\r2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\u0005HÖ\u0001J\t\u00102\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0010\"\u0004\b\u001d\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00063"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/weather/WeatherResp;", "", "timeStamp", "", "digitType", "", "type", "", "lowTemp", "highTemp", "rainProd", "humidity", "isBringUmbrella", "", "(JILjava/lang/String;IIIIZ)V", "getDigitType", "()I", "setDigitType", "(I)V", "getHighTemp", "setHighTemp", "getHumidity", "setHumidity", "()Z", "setBringUmbrella", "(Z)V", "getLowTemp", "setLowTemp", "getRainProd", "setRainProd", "getTimeStamp", "()J", "setTimeStamp", "(J)V", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WeatherResp {
    private int digitType;
    private int highTemp;
    private int humidity;
    private boolean isBringUmbrella;
    private int lowTemp;
    private int rainProd;
    private long timeStamp;
    private String type;

    public WeatherResp() {
        this(0L, 0, null, 0, 0, 0, 0, false, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimeStamp() {
        return this.timeStamp;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDigitType() {
        return this.digitType;
    }

    /* renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final int getLowTemp() {
        return this.lowTemp;
    }

    /* renamed from: component5, reason: from getter */
    public final int getHighTemp() {
        return this.highTemp;
    }

    /* renamed from: component6, reason: from getter */
    public final int getRainProd() {
        return this.rainProd;
    }

    /* renamed from: component7, reason: from getter */
    public final int getHumidity() {
        return this.humidity;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getIsBringUmbrella() {
        return this.isBringUmbrella;
    }

    public final WeatherResp copy(long timeStamp, int digitType, String type, int lowTemp, int highTemp, int rainProd, int humidity, boolean isBringUmbrella) {
        return new WeatherResp(timeStamp, digitType, type, lowTemp, highTemp, rainProd, humidity, isBringUmbrella);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeatherResp)) {
            return false;
        }
        WeatherResp weatherResp = (WeatherResp) other;
        return this.timeStamp == weatherResp.timeStamp && this.digitType == weatherResp.digitType && Intrinsics.areEqual(this.type, weatherResp.type) && this.lowTemp == weatherResp.lowTemp && this.highTemp == weatherResp.highTemp && this.rainProd == weatherResp.rainProd && this.humidity == weatherResp.humidity && this.isBringUmbrella == weatherResp.isBringUmbrella;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iM = ((CollectionRequest$$ExternalSyntheticBackport0.m(this.timeStamp) * 31) + this.digitType) * 31;
        String str = this.type;
        int iHashCode = (((((((((iM + (str == null ? 0 : str.hashCode())) * 31) + this.lowTemp) * 31) + this.highTemp) * 31) + this.rainProd) * 31) + this.humidity) * 31;
        boolean z = this.isBringUmbrella;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "WeatherResp(timeStamp=" + this.timeStamp + ", digitType=" + this.digitType + ", type=" + this.type + ", lowTemp=" + this.lowTemp + ", highTemp=" + this.highTemp + ", rainProd=" + this.rainProd + ", humidity=" + this.humidity + ", isBringUmbrella=" + this.isBringUmbrella + ')';
    }

    public WeatherResp(long j, int i, String str, int i2, int i3, int i4, int i5, boolean z) {
        this.timeStamp = j;
        this.digitType = i;
        this.type = str;
        this.lowTemp = i2;
        this.highTemp = i3;
        this.rainProd = i4;
        this.humidity = i5;
        this.isBringUmbrella = z;
    }

    public /* synthetic */ WeatherResp(long j, int i, String str, int i2, int i3, int i4, int i5, boolean z, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0L : j, (i6 & 2) != 0 ? 0 : i, (i6 & 4) != 0 ? null : str, (i6 & 8) != 0 ? 0 : i2, (i6 & 16) != 0 ? 0 : i3, (i6 & 32) != 0 ? 0 : i4, (i6 & 64) != 0 ? 0 : i5, (i6 & 128) == 0 ? z : false);
    }

    public final long getTimeStamp() {
        return this.timeStamp;
    }

    public final void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public final int getDigitType() {
        return this.digitType;
    }

    public final void setDigitType(int i) {
        this.digitType = i;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final int getLowTemp() {
        return this.lowTemp;
    }

    public final void setLowTemp(int i) {
        this.lowTemp = i;
    }

    public final int getHighTemp() {
        return this.highTemp;
    }

    public final void setHighTemp(int i) {
        this.highTemp = i;
    }

    public final int getRainProd() {
        return this.rainProd;
    }

    public final void setRainProd(int i) {
        this.rainProd = i;
    }

    public final int getHumidity() {
        return this.humidity;
    }

    public final void setHumidity(int i) {
        this.humidity = i;
    }

    public final boolean isBringUmbrella() {
        return this.isBringUmbrella;
    }

    public final void setBringUmbrella(boolean z) {
        this.isBringUmbrella = z;
    }
}
