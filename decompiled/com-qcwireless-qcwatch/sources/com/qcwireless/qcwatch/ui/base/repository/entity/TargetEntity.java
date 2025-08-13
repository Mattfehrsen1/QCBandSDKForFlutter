package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TargetEntity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003JE\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0005HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001e\u0010\n\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001e\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006*"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;", "", "deviceAddress", "", "goalSteps", "", "goalCalorie", "", "goalDistance", "goalSportTime", "goalSleepTime", "(Ljava/lang/String;IFFFF)V", "getDeviceAddress", "()Ljava/lang/String;", "setDeviceAddress", "(Ljava/lang/String;)V", "getGoalCalorie", "()F", "setGoalCalorie", "(F)V", "getGoalDistance", "setGoalDistance", "getGoalSleepTime", "setGoalSleepTime", "getGoalSportTime", "setGoalSportTime", "getGoalSteps", "()I", "setGoalSteps", "(I)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TargetEntity {
    private String deviceAddress;
    private float goalCalorie;
    private float goalDistance;
    private float goalSleepTime;
    private float goalSportTime;
    private int goalSteps;

    public static /* synthetic */ TargetEntity copy$default(TargetEntity targetEntity, String str, int i, float f, float f2, float f3, float f4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = targetEntity.deviceAddress;
        }
        if ((i2 & 2) != 0) {
            i = targetEntity.goalSteps;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            f = targetEntity.goalCalorie;
        }
        float f5 = f;
        if ((i2 & 8) != 0) {
            f2 = targetEntity.goalDistance;
        }
        float f6 = f2;
        if ((i2 & 16) != 0) {
            f3 = targetEntity.goalSportTime;
        }
        float f7 = f3;
        if ((i2 & 32) != 0) {
            f4 = targetEntity.goalSleepTime;
        }
        return targetEntity.copy(str, i3, f5, f6, f7, f4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final int getGoalSteps() {
        return this.goalSteps;
    }

    /* renamed from: component3, reason: from getter */
    public final float getGoalCalorie() {
        return this.goalCalorie;
    }

    /* renamed from: component4, reason: from getter */
    public final float getGoalDistance() {
        return this.goalDistance;
    }

    /* renamed from: component5, reason: from getter */
    public final float getGoalSportTime() {
        return this.goalSportTime;
    }

    /* renamed from: component6, reason: from getter */
    public final float getGoalSleepTime() {
        return this.goalSleepTime;
    }

    public final TargetEntity copy(String deviceAddress, int goalSteps, float goalCalorie, float goalDistance, float goalSportTime, float goalSleepTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        return new TargetEntity(deviceAddress, goalSteps, goalCalorie, goalDistance, goalSportTime, goalSleepTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TargetEntity)) {
            return false;
        }
        TargetEntity targetEntity = (TargetEntity) other;
        return Intrinsics.areEqual(this.deviceAddress, targetEntity.deviceAddress) && this.goalSteps == targetEntity.goalSteps && Intrinsics.areEqual((Object) Float.valueOf(this.goalCalorie), (Object) Float.valueOf(targetEntity.goalCalorie)) && Intrinsics.areEqual((Object) Float.valueOf(this.goalDistance), (Object) Float.valueOf(targetEntity.goalDistance)) && Intrinsics.areEqual((Object) Float.valueOf(this.goalSportTime), (Object) Float.valueOf(targetEntity.goalSportTime)) && Intrinsics.areEqual((Object) Float.valueOf(this.goalSleepTime), (Object) Float.valueOf(targetEntity.goalSleepTime));
    }

    public int hashCode() {
        return (((((((((this.deviceAddress.hashCode() * 31) + this.goalSteps) * 31) + Float.floatToIntBits(this.goalCalorie)) * 31) + Float.floatToIntBits(this.goalDistance)) * 31) + Float.floatToIntBits(this.goalSportTime)) * 31) + Float.floatToIntBits(this.goalSleepTime);
    }

    public String toString() {
        return "TargetEntity(deviceAddress=" + this.deviceAddress + ", goalSteps=" + this.goalSteps + ", goalCalorie=" + this.goalCalorie + ", goalDistance=" + this.goalDistance + ", goalSportTime=" + this.goalSportTime + ", goalSleepTime=" + this.goalSleepTime + ')';
    }

    public TargetEntity(String deviceAddress, int i, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        this.deviceAddress = deviceAddress;
        this.goalSteps = i;
        this.goalCalorie = f;
        this.goalDistance = f2;
        this.goalSportTime = f3;
        this.goalSleepTime = f4;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddress = str;
    }

    public final int getGoalSteps() {
        return this.goalSteps;
    }

    public final void setGoalSteps(int i) {
        this.goalSteps = i;
    }

    public final float getGoalCalorie() {
        return this.goalCalorie;
    }

    public final void setGoalCalorie(float f) {
        this.goalCalorie = f;
    }

    public final float getGoalDistance() {
        return this.goalDistance;
    }

    public final void setGoalDistance(float f) {
        this.goalDistance = f;
    }

    public final float getGoalSportTime() {
        return this.goalSportTime;
    }

    public final void setGoalSportTime(float f) {
        this.goalSportTime = f;
    }

    public final float getGoalSleepTime() {
        return this.goalSleepTime;
    }

    public final void setGoalSleepTime(float f) {
        this.goalSleepTime = f;
    }
}
