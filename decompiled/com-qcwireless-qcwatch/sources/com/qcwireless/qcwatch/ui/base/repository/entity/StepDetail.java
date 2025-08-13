package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.fitness.data.Field;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StepDetail.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b-\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u000fHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0006HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003Jm\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u00108\u001a\u00020\r2\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\u0006HÖ\u0001J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 ¨\u0006<"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/StepDetail;", "", "deviceAddress", "", "dateStr", "intervar", "", "totalActiveTime", "index_str", "counts", "miles", Field.NUTRIENT_CALORIES, "sync", "", "lastSyncTime", "", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJ)V", "getCalories", "()Ljava/lang/String;", "setCalories", "(Ljava/lang/String;)V", "getCounts", "setCounts", "getDateStr", "setDateStr", "getDeviceAddress", "setDeviceAddress", "getIndex_str", "setIndex_str", "getIntervar", "()I", "setIntervar", "(I)V", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getMiles", "setMiles", "getSync", "()Z", "setSync", "(Z)V", "getTotalActiveTime", "setTotalActiveTime", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class StepDetail {
    private String calories;
    private String counts;
    private String dateStr;
    private String deviceAddress;
    private String index_str;
    private int intervar;
    private long lastSyncTime;
    private String miles;
    private boolean sync;
    private int totalActiveTime;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component10, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    /* renamed from: component3, reason: from getter */
    public final int getIntervar() {
        return this.intervar;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTotalActiveTime() {
        return this.totalActiveTime;
    }

    /* renamed from: component5, reason: from getter */
    public final String getIndex_str() {
        return this.index_str;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCounts() {
        return this.counts;
    }

    /* renamed from: component7, reason: from getter */
    public final String getMiles() {
        return this.miles;
    }

    /* renamed from: component8, reason: from getter */
    public final String getCalories() {
        return this.calories;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    public final StepDetail copy(String deviceAddress, String dateStr, int intervar, int totalActiveTime, String index_str, String counts, String miles, String calories, boolean sync, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(index_str, "index_str");
        Intrinsics.checkNotNullParameter(counts, "counts");
        Intrinsics.checkNotNullParameter(miles, "miles");
        Intrinsics.checkNotNullParameter(calories, "calories");
        return new StepDetail(deviceAddress, dateStr, intervar, totalActiveTime, index_str, counts, miles, calories, sync, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StepDetail)) {
            return false;
        }
        StepDetail stepDetail = (StepDetail) other;
        return Intrinsics.areEqual(this.deviceAddress, stepDetail.deviceAddress) && Intrinsics.areEqual(this.dateStr, stepDetail.dateStr) && this.intervar == stepDetail.intervar && this.totalActiveTime == stepDetail.totalActiveTime && Intrinsics.areEqual(this.index_str, stepDetail.index_str) && Intrinsics.areEqual(this.counts, stepDetail.counts) && Intrinsics.areEqual(this.miles, stepDetail.miles) && Intrinsics.areEqual(this.calories, stepDetail.calories) && this.sync == stepDetail.sync && this.lastSyncTime == stepDetail.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.intervar) * 31) + this.totalActiveTime) * 31) + this.index_str.hashCode()) * 31) + this.counts.hashCode()) * 31) + this.miles.hashCode()) * 31) + this.calories.hashCode()) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "StepDetail(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", intervar=" + this.intervar + ", totalActiveTime=" + this.totalActiveTime + ", index_str=" + this.index_str + ", counts=" + this.counts + ", miles=" + this.miles + ", calories=" + this.calories + ", sync=" + this.sync + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public StepDetail(String deviceAddress, String dateStr, int i, int i2, String index_str, String counts, String miles, String calories, boolean z, long j) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(index_str, "index_str");
        Intrinsics.checkNotNullParameter(counts, "counts");
        Intrinsics.checkNotNullParameter(miles, "miles");
        Intrinsics.checkNotNullParameter(calories, "calories");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.intervar = i;
        this.totalActiveTime = i2;
        this.index_str = index_str;
        this.counts = counts;
        this.miles = miles;
        this.calories = calories;
        this.sync = z;
        this.lastSyncTime = j;
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

    public final int getIntervar() {
        return this.intervar;
    }

    public final void setIntervar(int i) {
        this.intervar = i;
    }

    public final int getTotalActiveTime() {
        return this.totalActiveTime;
    }

    public final void setTotalActiveTime(int i) {
        this.totalActiveTime = i;
    }

    public final String getIndex_str() {
        return this.index_str;
    }

    public final void setIndex_str(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.index_str = str;
    }

    public final String getCounts() {
        return this.counts;
    }

    public final void setCounts(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.counts = str;
    }

    public final String getMiles() {
        return this.miles;
    }

    public final void setMiles(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.miles = str;
    }

    public final String getCalories() {
        return this.calories;
    }

    public final void setCalories(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.calories = str;
    }

    public final boolean getSync() {
        return this.sync;
    }

    public final void setSync(boolean z) {
        this.sync = z;
    }

    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final void setLastSyncTime(long j) {
        this.lastSyncTime = j;
    }
}
