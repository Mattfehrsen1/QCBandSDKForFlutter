package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepTotalHistory.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003Jm\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006HÆ\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\u0006HÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001e\u0010\f\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001e\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001e\u0010\t\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001e\u0010\u000b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001e\u0010\r\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u00067"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepTotalHistory;", "", "deviceAddress", "", "dateStr", "totalSleep", "", "deepSleep", "lightSleep", "rapidSleep", "awake", "startTime", "endTime", "unixTime", "(Ljava/lang/String;Ljava/lang/String;IIIIIIII)V", "getAwake", "()I", "setAwake", "(I)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeepSleep", "setDeepSleep", "getDeviceAddress", "setDeviceAddress", "getEndTime", "setEndTime", "getLightSleep", "setLightSleep", "getRapidSleep", "setRapidSleep", "getStartTime", "setStartTime", "getTotalSleep", "setTotalSleep", "getUnixTime", "setUnixTime", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SleepTotalHistory {
    private int awake;
    private String dateStr;
    private int deepSleep;
    private String deviceAddress;
    private int endTime;
    private int lightSleep;
    private int rapidSleep;
    private int startTime;
    private int totalSleep;
    private int unixTime;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component10, reason: from getter */
    public final int getUnixTime() {
        return this.unixTime;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    /* renamed from: component3, reason: from getter */
    public final int getTotalSleep() {
        return this.totalSleep;
    }

    /* renamed from: component4, reason: from getter */
    public final int getDeepSleep() {
        return this.deepSleep;
    }

    /* renamed from: component5, reason: from getter */
    public final int getLightSleep() {
        return this.lightSleep;
    }

    /* renamed from: component6, reason: from getter */
    public final int getRapidSleep() {
        return this.rapidSleep;
    }

    /* renamed from: component7, reason: from getter */
    public final int getAwake() {
        return this.awake;
    }

    /* renamed from: component8, reason: from getter */
    public final int getStartTime() {
        return this.startTime;
    }

    /* renamed from: component9, reason: from getter */
    public final int getEndTime() {
        return this.endTime;
    }

    public final SleepTotalHistory copy(String deviceAddress, String dateStr, int totalSleep, int deepSleep, int lightSleep, int rapidSleep, int awake, int startTime, int endTime, int unixTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return new SleepTotalHistory(deviceAddress, dateStr, totalSleep, deepSleep, lightSleep, rapidSleep, awake, startTime, endTime, unixTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepTotalHistory)) {
            return false;
        }
        SleepTotalHistory sleepTotalHistory = (SleepTotalHistory) other;
        return Intrinsics.areEqual(this.deviceAddress, sleepTotalHistory.deviceAddress) && Intrinsics.areEqual(this.dateStr, sleepTotalHistory.dateStr) && this.totalSleep == sleepTotalHistory.totalSleep && this.deepSleep == sleepTotalHistory.deepSleep && this.lightSleep == sleepTotalHistory.lightSleep && this.rapidSleep == sleepTotalHistory.rapidSleep && this.awake == sleepTotalHistory.awake && this.startTime == sleepTotalHistory.startTime && this.endTime == sleepTotalHistory.endTime && this.unixTime == sleepTotalHistory.unixTime;
    }

    public int hashCode() {
        return (((((((((((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.totalSleep) * 31) + this.deepSleep) * 31) + this.lightSleep) * 31) + this.rapidSleep) * 31) + this.awake) * 31) + this.startTime) * 31) + this.endTime) * 31) + this.unixTime;
    }

    public String toString() {
        return "SleepTotalHistory(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", totalSleep=" + this.totalSleep + ", deepSleep=" + this.deepSleep + ", lightSleep=" + this.lightSleep + ", rapidSleep=" + this.rapidSleep + ", awake=" + this.awake + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", unixTime=" + this.unixTime + ')';
    }

    public SleepTotalHistory(String deviceAddress, String dateStr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.totalSleep = i;
        this.deepSleep = i2;
        this.lightSleep = i3;
        this.rapidSleep = i4;
        this.awake = i5;
        this.startTime = i6;
        this.endTime = i7;
        this.unixTime = i8;
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

    public final int getTotalSleep() {
        return this.totalSleep;
    }

    public final void setTotalSleep(int i) {
        this.totalSleep = i;
    }

    public final int getDeepSleep() {
        return this.deepSleep;
    }

    public final void setDeepSleep(int i) {
        this.deepSleep = i;
    }

    public final int getLightSleep() {
        return this.lightSleep;
    }

    public final void setLightSleep(int i) {
        this.lightSleep = i;
    }

    public final int getRapidSleep() {
        return this.rapidSleep;
    }

    public final void setRapidSleep(int i) {
        this.rapidSleep = i;
    }

    public final int getAwake() {
        return this.awake;
    }

    public final void setAwake(int i) {
        this.awake = i;
    }

    public final int getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(int i) {
        this.startTime = i;
    }

    public final int getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(int i) {
        this.endTime = i;
    }

    public final int getUnixTime() {
        return this.unixTime;
    }

    public final void setUnixTime(int i) {
        this.unixTime = i;
    }
}
