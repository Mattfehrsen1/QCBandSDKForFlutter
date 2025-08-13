package com.qcwireless.qcwatch.ui.base.repository.entity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.data.Field;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SportPlusDetail.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b2\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\bHÆ\u0003J\t\u00105\u001a\u00020\u0011HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\t\u00108\u001a\u00020\bHÆ\u0003J\t\u00109\u001a\u00020\bHÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\t\u0010;\u001a\u00020\u000bHÆ\u0003J\t\u0010<\u001a\u00020\bHÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003Jw\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010?\u001a\u00020\u00112\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020\bHÖ\u0001J\t\u0010B\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR\u001e\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R\u001e\u0010\u000e\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001c\"\u0004\b&\u0010\u001eR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010\r\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0014\"\u0004\b.\u0010\u0016R\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u0006C"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SportPlusDetail;", "", "deviceAddress", "", "dateStr", "startTime", "", "sportType", "", TypedValues.TransitionType.S_DURATION, "distance", "", Field.NUTRIENT_CALORIES, "steps", "rateValue", "avgRate", "sync", "", "(Ljava/lang/String;Ljava/lang/String;JIIFFILjava/lang/String;IZ)V", "getAvgRate", "()I", "setAvgRate", "(I)V", "getCalories", "()F", "setCalories", "(F)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeviceAddress", "setDeviceAddress", "getDistance", "setDistance", "getDuration", "setDuration", "getRateValue", "setRateValue", "getSportType", "setSportType", "getStartTime", "()J", "setStartTime", "(J)V", "getSteps", "setSteps", "getSync", "()Z", "setSync", "(Z)V", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SportPlusDetail {
    private int avgRate;
    private float calories;
    private String dateStr;
    private String deviceAddress;
    private float distance;
    private int duration;
    private String rateValue;
    private int sportType;
    private long startTime;
    private int steps;
    private boolean sync;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component10, reason: from getter */
    public final int getAvgRate() {
        return this.avgRate;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    /* renamed from: component3, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component4, reason: from getter */
    public final int getSportType() {
        return this.sportType;
    }

    /* renamed from: component5, reason: from getter */
    public final int getDuration() {
        return this.duration;
    }

    /* renamed from: component6, reason: from getter */
    public final float getDistance() {
        return this.distance;
    }

    /* renamed from: component7, reason: from getter */
    public final float getCalories() {
        return this.calories;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSteps() {
        return this.steps;
    }

    /* renamed from: component9, reason: from getter */
    public final String getRateValue() {
        return this.rateValue;
    }

    public final SportPlusDetail copy(String deviceAddress, String dateStr, long startTime, int sportType, int duration, float distance, float calories, int steps, String rateValue, int avgRate, boolean sync) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(rateValue, "rateValue");
        return new SportPlusDetail(deviceAddress, dateStr, startTime, sportType, duration, distance, calories, steps, rateValue, avgRate, sync);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SportPlusDetail)) {
            return false;
        }
        SportPlusDetail sportPlusDetail = (SportPlusDetail) other;
        return Intrinsics.areEqual(this.deviceAddress, sportPlusDetail.deviceAddress) && Intrinsics.areEqual(this.dateStr, sportPlusDetail.dateStr) && this.startTime == sportPlusDetail.startTime && this.sportType == sportPlusDetail.sportType && this.duration == sportPlusDetail.duration && Intrinsics.areEqual((Object) Float.valueOf(this.distance), (Object) Float.valueOf(sportPlusDetail.distance)) && Intrinsics.areEqual((Object) Float.valueOf(this.calories), (Object) Float.valueOf(sportPlusDetail.calories)) && this.steps == sportPlusDetail.steps && Intrinsics.areEqual(this.rateValue, sportPlusDetail.rateValue) && this.avgRate == sportPlusDetail.avgRate && this.sync == sportPlusDetail.sync;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.startTime)) * 31) + this.sportType) * 31) + this.duration) * 31) + Float.floatToIntBits(this.distance)) * 31) + Float.floatToIntBits(this.calories)) * 31) + this.steps) * 31) + this.rateValue.hashCode()) * 31) + this.avgRate) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "SportPlusDetail(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", startTime=" + this.startTime + ", sportType=" + this.sportType + ", duration=" + this.duration + ", distance=" + this.distance + ", calories=" + this.calories + ", steps=" + this.steps + ", rateValue=" + this.rateValue + ", avgRate=" + this.avgRate + ", sync=" + this.sync + ')';
    }

    public SportPlusDetail(String deviceAddress, String dateStr, long j, int i, int i2, float f, float f2, int i3, String rateValue, int i4, boolean z) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(rateValue, "rateValue");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.startTime = j;
        this.sportType = i;
        this.duration = i2;
        this.distance = f;
        this.calories = f2;
        this.steps = i3;
        this.rateValue = rateValue;
        this.avgRate = i4;
        this.sync = z;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddress = str;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final void setDateStr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dateStr = str;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final int getSportType() {
        return this.sportType;
    }

    public final void setSportType(int i) {
        this.sportType = i;
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

    public final float getCalories() {
        return this.calories;
    }

    public final void setCalories(float f) {
        this.calories = f;
    }

    public final int getSteps() {
        return this.steps;
    }

    public final void setSteps(int i) {
        this.steps = i;
    }

    public final String getRateValue() {
        return this.rateValue;
    }

    public final void setRateValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.rateValue = str;
    }

    public final int getAvgRate() {
        return this.avgRate;
    }

    public final void setAvgRate(int i) {
        this.avgRate = i;
    }

    public final boolean getSync() {
        return this.sync;
    }

    public final void setSync(boolean z) {
        this.sync = z;
    }
}
