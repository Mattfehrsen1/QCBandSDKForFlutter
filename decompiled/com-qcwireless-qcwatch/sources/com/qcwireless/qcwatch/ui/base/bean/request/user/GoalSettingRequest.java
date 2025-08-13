package com.qcwireless.qcwatch.ui.base.bean.request.user;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: GoalSettingRequest.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006&"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;", "", "uid", "", "weight", "", "steps", "mile", "calorie", "sportTime", "deepSleep", FitnessActivities.SLEEP, "(JIIIIIII)V", "getCalorie", "()I", "getDeepSleep", "getMile", "getSleep", "getSportTime", "getSteps", "getUid", "()J", "getWeight", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class GoalSettingRequest {
    private final int calorie;
    private final int deepSleep;
    private final int mile;
    private final int sleep;
    private final int sportTime;
    private final int steps;
    private final long uid;
    private final int weight;

    /* renamed from: component1, reason: from getter */
    public final long getUid() {
        return this.uid;
    }

    /* renamed from: component2, reason: from getter */
    public final int getWeight() {
        return this.weight;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSteps() {
        return this.steps;
    }

    /* renamed from: component4, reason: from getter */
    public final int getMile() {
        return this.mile;
    }

    /* renamed from: component5, reason: from getter */
    public final int getCalorie() {
        return this.calorie;
    }

    /* renamed from: component6, reason: from getter */
    public final int getSportTime() {
        return this.sportTime;
    }

    /* renamed from: component7, reason: from getter */
    public final int getDeepSleep() {
        return this.deepSleep;
    }

    /* renamed from: component8, reason: from getter */
    public final int getSleep() {
        return this.sleep;
    }

    public final GoalSettingRequest copy(long uid, int weight, int steps, int mile, int calorie, int sportTime, int deepSleep, int sleep) {
        return new GoalSettingRequest(uid, weight, steps, mile, calorie, sportTime, deepSleep, sleep);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GoalSettingRequest)) {
            return false;
        }
        GoalSettingRequest goalSettingRequest = (GoalSettingRequest) other;
        return this.uid == goalSettingRequest.uid && this.weight == goalSettingRequest.weight && this.steps == goalSettingRequest.steps && this.mile == goalSettingRequest.mile && this.calorie == goalSettingRequest.calorie && this.sportTime == goalSettingRequest.sportTime && this.deepSleep == goalSettingRequest.deepSleep && this.sleep == goalSettingRequest.sleep;
    }

    public int hashCode() {
        return (((((((((((((CollectionRequest$$ExternalSyntheticBackport0.m(this.uid) * 31) + this.weight) * 31) + this.steps) * 31) + this.mile) * 31) + this.calorie) * 31) + this.sportTime) * 31) + this.deepSleep) * 31) + this.sleep;
    }

    public String toString() {
        return "GoalSettingRequest(uid=" + this.uid + ", weight=" + this.weight + ", steps=" + this.steps + ", mile=" + this.mile + ", calorie=" + this.calorie + ", sportTime=" + this.sportTime + ", deepSleep=" + this.deepSleep + ", sleep=" + this.sleep + ')';
    }

    public GoalSettingRequest(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.uid = j;
        this.weight = i;
        this.steps = i2;
        this.mile = i3;
        this.calorie = i4;
        this.sportTime = i5;
        this.deepSleep = i6;
        this.sleep = i7;
    }

    public /* synthetic */ GoalSettingRequest(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i8 & 2) != 0 ? 0 : i, i2, i3, i4, i5, (i8 & 64) != 0 ? 0 : i6, i7);
    }

    public final long getUid() {
        return this.uid;
    }

    public final int getWeight() {
        return this.weight;
    }

    public final int getSteps() {
        return this.steps;
    }

    public final int getMile() {
        return this.mile;
    }

    public final int getCalorie() {
        return this.calorie;
    }

    public final int getSportTime() {
        return this.sportTime;
    }

    public final int getDeepSleep() {
        return this.deepSleep;
    }

    public final int getSleep() {
        return this.sleep;
    }
}
