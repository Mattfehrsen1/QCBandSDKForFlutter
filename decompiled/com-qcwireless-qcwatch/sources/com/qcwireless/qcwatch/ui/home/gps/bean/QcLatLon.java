package com.qcwireless.qcwatch.ui.home.gps.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QcLatLon.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/bean/QcLatLon;", "", "latitude", "", "longitude", "pace", "speed", "(DDDD)V", "getLatitude", "()D", "setLatitude", "(D)V", "getLongitude", "setLongitude", "getPace", "setPace", "getSpeed", "setSpeed", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class QcLatLon {
    private double latitude;
    private double longitude;
    private double pace;
    private double speed;

    /* renamed from: component1, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component3, reason: from getter */
    public final double getPace() {
        return this.pace;
    }

    /* renamed from: component4, reason: from getter */
    public final double getSpeed() {
        return this.speed;
    }

    public final QcLatLon copy(double latitude, double longitude, double pace, double speed) {
        return new QcLatLon(latitude, longitude, pace, speed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QcLatLon)) {
            return false;
        }
        QcLatLon qcLatLon = (QcLatLon) other;
        return Intrinsics.areEqual((Object) Double.valueOf(this.latitude), (Object) Double.valueOf(qcLatLon.latitude)) && Intrinsics.areEqual((Object) Double.valueOf(this.longitude), (Object) Double.valueOf(qcLatLon.longitude)) && Intrinsics.areEqual((Object) Double.valueOf(this.pace), (Object) Double.valueOf(qcLatLon.pace)) && Intrinsics.areEqual((Object) Double.valueOf(this.speed), (Object) Double.valueOf(qcLatLon.speed));
    }

    public int hashCode() {
        return (((((Gps$$ExternalSyntheticBackport0.m(this.latitude) * 31) + Gps$$ExternalSyntheticBackport0.m(this.longitude)) * 31) + Gps$$ExternalSyntheticBackport0.m(this.pace)) * 31) + Gps$$ExternalSyntheticBackport0.m(this.speed);
    }

    public String toString() {
        return "QcLatLon(latitude=" + this.latitude + ", longitude=" + this.longitude + ", pace=" + this.pace + ", speed=" + this.speed + ')';
    }

    public QcLatLon(double d, double d2, double d3, double d4) {
        this.latitude = d;
        this.longitude = d2;
        this.pace = d3;
        this.speed = d4;
    }

    public /* synthetic */ QcLatLon(double d, double d2, double d3, double d4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(d, d2, (i & 4) != 0 ? 99.0d : d3, (i & 8) != 0 ? 0.0d : d4);
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final double getPace() {
        return this.pace;
    }

    public final double getSpeed() {
        return this.speed;
    }

    public final void setLatitude(double d) {
        this.latitude = d;
    }

    public final void setLongitude(double d) {
        this.longitude = d;
    }

    public final void setPace(double d) {
        this.pace = d;
    }

    public final void setSpeed(double d) {
        this.speed = d;
    }
}
