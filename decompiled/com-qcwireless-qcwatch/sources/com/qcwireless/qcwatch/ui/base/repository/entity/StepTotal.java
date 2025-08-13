package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StepTotal.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0006HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001e\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e¨\u0006'"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepTotal;", "", "deviceAddress", "", "dateStr", "step", "", "distance", "carolie", "unixTime", "(Ljava/lang/String;Ljava/lang/String;IIII)V", "getCarolie", "()I", "setCarolie", "(I)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeviceAddress", "setDeviceAddress", "getDistance", "setDistance", "getStep", "setStep", "getUnixTime", "setUnixTime", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class StepTotal {
    private int carolie;
    private String dateStr;
    private String deviceAddress;
    private int distance;
    private int step;
    private int unixTime;

    public static /* synthetic */ StepTotal copy$default(StepTotal stepTotal, String str, String str2, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            str = stepTotal.deviceAddress;
        }
        if ((i5 & 2) != 0) {
            str2 = stepTotal.dateStr;
        }
        String str3 = str2;
        if ((i5 & 4) != 0) {
            i = stepTotal.step;
        }
        int i6 = i;
        if ((i5 & 8) != 0) {
            i2 = stepTotal.distance;
        }
        int i7 = i2;
        if ((i5 & 16) != 0) {
            i3 = stepTotal.carolie;
        }
        int i8 = i3;
        if ((i5 & 32) != 0) {
            i4 = stepTotal.unixTime;
        }
        return stepTotal.copy(str, str3, i6, i7, i8, i4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    /* renamed from: component3, reason: from getter */
    public final int getStep() {
        return this.step;
    }

    /* renamed from: component4, reason: from getter */
    public final int getDistance() {
        return this.distance;
    }

    /* renamed from: component5, reason: from getter */
    public final int getCarolie() {
        return this.carolie;
    }

    /* renamed from: component6, reason: from getter */
    public final int getUnixTime() {
        return this.unixTime;
    }

    public final StepTotal copy(String deviceAddress, String dateStr, int step, int distance, int carolie, int unixTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return new StepTotal(deviceAddress, dateStr, step, distance, carolie, unixTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StepTotal)) {
            return false;
        }
        StepTotal stepTotal = (StepTotal) other;
        return Intrinsics.areEqual(this.deviceAddress, stepTotal.deviceAddress) && Intrinsics.areEqual(this.dateStr, stepTotal.dateStr) && this.step == stepTotal.step && this.distance == stepTotal.distance && this.carolie == stepTotal.carolie && this.unixTime == stepTotal.unixTime;
    }

    public int hashCode() {
        return (((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.step) * 31) + this.distance) * 31) + this.carolie) * 31) + this.unixTime;
    }

    public String toString() {
        return "StepTotal(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", step=" + this.step + ", distance=" + this.distance + ", carolie=" + this.carolie + ", unixTime=" + this.unixTime + ')';
    }

    public StepTotal(String deviceAddress, String dateStr, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.step = i;
        this.distance = i2;
        this.carolie = i3;
        this.unixTime = i4;
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

    public final int getStep() {
        return this.step;
    }

    public final void setStep(int i) {
        this.step = i;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final int getCarolie() {
        return this.carolie;
    }

    public final void setCarolie(int i) {
        this.carolie = i;
    }

    public final int getUnixTime() {
        return this.unixTime;
    }

    public final void setUnixTime(int i) {
        this.unixTime = i;
    }
}
