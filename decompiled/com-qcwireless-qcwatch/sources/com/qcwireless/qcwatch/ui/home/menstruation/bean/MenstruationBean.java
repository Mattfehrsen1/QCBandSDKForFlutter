package com.qcwireless.qcwatch.ui.home.menstruation.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: MenstruationBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b(\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010-\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\u0005HÖ\u0001J\t\u00100\u001a\u000201HÖ\u0001R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!¨\u00062"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/menstruation/bean/MenstruationBean;", "", "menstruationSwitch", "", "during", "", "cycle", "lastTime", "", "menstruationAlarm", "alarm1", "alarm2", "alarmMin", "(ZIIJZIII)V", "getAlarm1", "()I", "setAlarm1", "(I)V", "getAlarm2", "setAlarm2", "getAlarmMin", "setAlarmMin", "getCycle", "setCycle", "getDuring", "setDuring", "getLastTime", "()J", "setLastTime", "(J)V", "getMenstruationAlarm", "()Z", "setMenstruationAlarm", "(Z)V", "getMenstruationSwitch", "setMenstruationSwitch", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class MenstruationBean {
    private int alarm1;
    private int alarm2;
    private int alarmMin;
    private int cycle;
    private int during;
    private long lastTime;
    private boolean menstruationAlarm;
    private boolean menstruationSwitch;

    public MenstruationBean() {
        this(false, 0, 0, 0L, false, 0, 0, 0, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getMenstruationSwitch() {
        return this.menstruationSwitch;
    }

    /* renamed from: component2, reason: from getter */
    public final int getDuring() {
        return this.during;
    }

    /* renamed from: component3, reason: from getter */
    public final int getCycle() {
        return this.cycle;
    }

    /* renamed from: component4, reason: from getter */
    public final long getLastTime() {
        return this.lastTime;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getMenstruationAlarm() {
        return this.menstruationAlarm;
    }

    /* renamed from: component6, reason: from getter */
    public final int getAlarm1() {
        return this.alarm1;
    }

    /* renamed from: component7, reason: from getter */
    public final int getAlarm2() {
        return this.alarm2;
    }

    /* renamed from: component8, reason: from getter */
    public final int getAlarmMin() {
        return this.alarmMin;
    }

    public final MenstruationBean copy(boolean menstruationSwitch, int during, int cycle, long lastTime, boolean menstruationAlarm, int alarm1, int alarm2, int alarmMin) {
        return new MenstruationBean(menstruationSwitch, during, cycle, lastTime, menstruationAlarm, alarm1, alarm2, alarmMin);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MenstruationBean)) {
            return false;
        }
        MenstruationBean menstruationBean = (MenstruationBean) other;
        return this.menstruationSwitch == menstruationBean.menstruationSwitch && this.during == menstruationBean.during && this.cycle == menstruationBean.cycle && this.lastTime == menstruationBean.lastTime && this.menstruationAlarm == menstruationBean.menstruationAlarm && this.alarm1 == menstruationBean.alarm1 && this.alarm2 == menstruationBean.alarm2 && this.alarmMin == menstruationBean.alarmMin;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    public int hashCode() {
        boolean z = this.menstruationSwitch;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int iM = ((((((r0 * 31) + this.during) * 31) + this.cycle) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastTime)) * 31;
        boolean z2 = this.menstruationAlarm;
        return ((((((iM + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.alarm1) * 31) + this.alarm2) * 31) + this.alarmMin;
    }

    public String toString() {
        return "MenstruationBean(menstruationSwitch=" + this.menstruationSwitch + ", during=" + this.during + ", cycle=" + this.cycle + ", lastTime=" + this.lastTime + ", menstruationAlarm=" + this.menstruationAlarm + ", alarm1=" + this.alarm1 + ", alarm2=" + this.alarm2 + ", alarmMin=" + this.alarmMin + ')';
    }

    public MenstruationBean(boolean z, int i, int i2, long j, boolean z2, int i3, int i4, int i5) {
        this.menstruationSwitch = z;
        this.during = i;
        this.cycle = i2;
        this.lastTime = j;
        this.menstruationAlarm = z2;
        this.alarm1 = i3;
        this.alarm2 = i4;
        this.alarmMin = i5;
    }

    public final boolean getMenstruationSwitch() {
        return this.menstruationSwitch;
    }

    public final void setMenstruationSwitch(boolean z) {
        this.menstruationSwitch = z;
    }

    public final int getDuring() {
        return this.during;
    }

    public final void setDuring(int i) {
        this.during = i;
    }

    public final int getCycle() {
        return this.cycle;
    }

    public final void setCycle(int i) {
        this.cycle = i;
    }

    public /* synthetic */ MenstruationBean(boolean z, int i, int i2, long j, boolean z2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? false : z, (i6 & 2) != 0 ? 6 : i, (i6 & 4) != 0 ? 28 : i2, (i6 & 8) != 0 ? new DateUtil().getUnixTimestamp() : j, (i6 & 16) == 0 ? z2 : false, (i6 & 32) != 0 ? 2 : i3, (i6 & 64) == 0 ? i4 : 2, (i6 & 128) != 0 ? 995 : i5);
    }

    public final long getLastTime() {
        return this.lastTime;
    }

    public final void setLastTime(long j) {
        this.lastTime = j;
    }

    public final boolean getMenstruationAlarm() {
        return this.menstruationAlarm;
    }

    public final void setMenstruationAlarm(boolean z) {
        this.menstruationAlarm = z;
    }

    public final int getAlarm1() {
        return this.alarm1;
    }

    public final void setAlarm1(int i) {
        this.alarm1 = i;
    }

    public final int getAlarm2() {
        return this.alarm2;
    }

    public final void setAlarm2(int i) {
        this.alarm2 = i;
    }

    public final int getAlarmMin() {
        return this.alarmMin;
    }

    public final void setAlarmMin(int i) {
        this.alarmMin = i;
    }
}
