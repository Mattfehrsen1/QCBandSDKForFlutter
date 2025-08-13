package com.qcwireless.qcwatch.ui.home.sleep.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import com.qcwireless.qcwatch.ui.base.view.QSleepBarChart;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepViewBean.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u0006HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0006HÆ\u0003J\t\u0010+\u001a\u00020\fHÆ\u0003J\t\u0010,\u001a\u00020\fHÆ\u0003J_\u0010-\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00101\u001a\u00020\u0006HÖ\u0001J\t\u00102\u001a\u000203HÖ\u0001R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010\n\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012¨\u00064"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sleep/bean/SleepViewBean;", "", "data", "", "Lcom/qcwireless/qcwatch/ui/base/view/QSleepBarChart$SleepDataBean;", "deepSleep", "", "rapidSleep", "lightSleep", "awakeSleep", "totalSleep", "startTime", "", "endTime", "(Ljava/util/List;IIIIIJJ)V", "getAwakeSleep", "()I", "setAwakeSleep", "(I)V", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "getDeepSleep", "setDeepSleep", "getEndTime", "()J", "setEndTime", "(J)V", "getLightSleep", "setLightSleep", "getRapidSleep", "setRapidSleep", "getStartTime", "setStartTime", "getTotalSleep", "setTotalSleep", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SleepViewBean {
    private int awakeSleep;
    private List<? extends QSleepBarChart.SleepDataBean> data;
    private int deepSleep;
    private long endTime;
    private int lightSleep;
    private int rapidSleep;
    private long startTime;
    private int totalSleep;

    public SleepViewBean() {
        this(null, 0, 0, 0, 0, 0, 0L, 0L, 255, null);
    }

    public final List<QSleepBarChart.SleepDataBean> component1() {
        return this.data;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDeepSleep() {
        return this.deepSleep;
    }

    /* renamed from: component3, reason: from getter */
    public final int getRapidSleep() {
        return this.rapidSleep;
    }

    /* renamed from: component4, reason: from getter */
    public final int getLightSleep() {
        return this.lightSleep;
    }

    /* renamed from: component5, reason: from getter */
    public final int getAwakeSleep() {
        return this.awakeSleep;
    }

    /* renamed from: component6, reason: from getter */
    public final int getTotalSleep() {
        return this.totalSleep;
    }

    /* renamed from: component7, reason: from getter */
    public final long getStartTime() {
        return this.startTime;
    }

    /* renamed from: component8, reason: from getter */
    public final long getEndTime() {
        return this.endTime;
    }

    public final SleepViewBean copy(List<? extends QSleepBarChart.SleepDataBean> data, int deepSleep, int rapidSleep, int lightSleep, int awakeSleep, int totalSleep, long startTime, long endTime) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new SleepViewBean(data, deepSleep, rapidSleep, lightSleep, awakeSleep, totalSleep, startTime, endTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepViewBean)) {
            return false;
        }
        SleepViewBean sleepViewBean = (SleepViewBean) other;
        return Intrinsics.areEqual(this.data, sleepViewBean.data) && this.deepSleep == sleepViewBean.deepSleep && this.rapidSleep == sleepViewBean.rapidSleep && this.lightSleep == sleepViewBean.lightSleep && this.awakeSleep == sleepViewBean.awakeSleep && this.totalSleep == sleepViewBean.totalSleep && this.startTime == sleepViewBean.startTime && this.endTime == sleepViewBean.endTime;
    }

    public int hashCode() {
        return (((((((((((((this.data.hashCode() * 31) + this.deepSleep) * 31) + this.rapidSleep) * 31) + this.lightSleep) * 31) + this.awakeSleep) * 31) + this.totalSleep) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.startTime)) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.endTime);
    }

    public String toString() {
        return "SleepViewBean(data=" + this.data + ", deepSleep=" + this.deepSleep + ", rapidSleep=" + this.rapidSleep + ", lightSleep=" + this.lightSleep + ", awakeSleep=" + this.awakeSleep + ", totalSleep=" + this.totalSleep + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ')';
    }

    public SleepViewBean(List<? extends QSleepBarChart.SleepDataBean> data, int i, int i2, int i3, int i4, int i5, long j, long j2) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.deepSleep = i;
        this.rapidSleep = i2;
        this.lightSleep = i3;
        this.awakeSleep = i4;
        this.totalSleep = i5;
        this.startTime = j;
        this.endTime = j2;
    }

    public /* synthetic */ SleepViewBean(List list, int i, int i2, int i3, int i4, int i5, long j, long j2, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? new ArrayList() : list, (i6 & 2) != 0 ? 0 : i, (i6 & 4) != 0 ? 0 : i2, (i6 & 8) != 0 ? 0 : i3, (i6 & 16) != 0 ? 0 : i4, (i6 & 32) == 0 ? i5 : 0, (i6 & 64) != 0 ? 0L : j, (i6 & 128) == 0 ? j2 : 0L);
    }

    public final List<QSleepBarChart.SleepDataBean> getData() {
        return this.data;
    }

    public final void setData(List<? extends QSleepBarChart.SleepDataBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    public final int getDeepSleep() {
        return this.deepSleep;
    }

    public final void setDeepSleep(int i) {
        this.deepSleep = i;
    }

    public final int getRapidSleep() {
        return this.rapidSleep;
    }

    public final void setRapidSleep(int i) {
        this.rapidSleep = i;
    }

    public final int getLightSleep() {
        return this.lightSleep;
    }

    public final void setLightSleep(int i) {
        this.lightSleep = i;
    }

    public final int getAwakeSleep() {
        return this.awakeSleep;
    }

    public final void setAwakeSleep(int i) {
        this.awakeSleep = i;
    }

    public final int getTotalSleep() {
        return this.totalSleep;
    }

    public final void setTotalSleep(int i) {
        this.totalSleep = i;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(long j) {
        this.endTime = j;
    }
}
