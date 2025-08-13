package com.qcwireless.qcwatch.ui.base.repository.entity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GpsDetail.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\nHÆ\u0003JE\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0005HÖ\u0001J\t\u0010,\u001a\u00020\nHÖ\u0001R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006-"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "", "startTime", "", TypedValues.TransitionType.S_DURATION, "", "distance", "", "calorie", "locations", "", "dateStr", "(JIFFLjava/lang/String;Ljava/lang/String;)V", "getCalorie", "()F", "setCalorie", "(F)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDistance", "setDistance", "getDuration", "()I", "setDuration", "(I)V", "getLocations", "setLocations", "getStartTime", "()J", "setStartTime", "(J)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class GpsDetail {
    private float calorie;
    private String dateStr;
    private float distance;
    private int duration;
    private String locations;
    private long startTime;

    /* renamed from: component1, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    /* renamed from: component3, reason: from getter */
    public final float getDistance() {
        return this.distance;
    }

    /* renamed from: component4, reason: from getter */
    public final float getCalorie() {
        return this.calorie;
    }

    /* renamed from: component5, reason: from getter */
    public final String getLocations() {
        return this.locations;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    public final GpsDetail copy(long startTime, int duration, float distance, float calorie, String locations, String dateStr) {
        Intrinsics.checkNotNullParameter(locations, "locations");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return new GpsDetail(startTime, duration, distance, calorie, locations, dateStr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GpsDetail)) {
            return false;
        }
        GpsDetail gpsDetail = (GpsDetail) other;
        return this.startTime == gpsDetail.startTime && this.duration == gpsDetail.duration && Intrinsics.areEqual((Object) Float.valueOf(this.distance), (Object) Float.valueOf(gpsDetail.distance)) && Intrinsics.areEqual((Object) Float.valueOf(this.calorie), (Object) Float.valueOf(gpsDetail.calorie)) && Intrinsics.areEqual(this.locations, gpsDetail.locations) && Intrinsics.areEqual(this.dateStr, gpsDetail.dateStr);
    }

    public int hashCode() {
        return (((((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.startTime) * 31) + this.duration) * 31) + Float.floatToIntBits(this.distance)) * 31) + Float.floatToIntBits(this.calorie)) * 31) + this.locations.hashCode()) * 31) + this.dateStr.hashCode();
    }

    public String toString() {
        return "GpsDetail(startTime=" + this.startTime + ", duration=" + this.duration + ", distance=" + this.distance + ", calorie=" + this.calorie + ", locations=" + this.locations + ", dateStr=" + this.dateStr + ')';
    }

    public GpsDetail(long j, int i, float f, float f2, String locations, String dateStr) {
        Intrinsics.checkNotNullParameter(locations, "locations");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        this.startTime = j;
        this.duration = i;
        this.distance = f;
        this.calorie = f2;
        this.locations = locations;
        this.dateStr = dateStr;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final float getDistance() {
        return this.distance;
    }

    public final void setDistance(float f) {
        this.distance = f;
    }

    public final float getCalorie() {
        return this.calorie;
    }

    public final void setCalorie(float f) {
        this.calorie = f;
    }

    public final String getLocations() {
        return this.locations;
    }

    public final void setLocations(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.locations = str;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final void setDateStr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dateStr = str;
    }
}
