package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodPressureEntity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\"\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003JE\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0007HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018¨\u0006,"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/BloodPressureEntity;", "", "deviceAddress", "", "unixTime", "", "sbp", "", "dbp", "sync", "", "lastSyncTime", "(Ljava/lang/String;JIIZJ)V", "getDbp", "()I", "setDbp", "(I)V", "getDeviceAddress", "()Ljava/lang/String;", "setDeviceAddress", "(Ljava/lang/String;)V", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getSbp", "setSbp", "getSync", "()Z", "setSync", "(Z)V", "getUnixTime", "setUnixTime", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BloodPressureEntity {
    private int dbp;
    private String deviceAddress;
    private long lastSyncTime;
    private int sbp;
    private boolean sync;
    private long unixTime;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final long getUnixTime() {
        return this.unixTime;
    }

    /* renamed from: component3, reason: from getter */
    public final int getSbp() {
        return this.sbp;
    }

    /* renamed from: component4, reason: from getter */
    public final int getDbp() {
        return this.dbp;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component6, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final BloodPressureEntity copy(String deviceAddress, long unixTime, int sbp, int dbp, boolean sync, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        return new BloodPressureEntity(deviceAddress, unixTime, sbp, dbp, sync, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodPressureEntity)) {
            return false;
        }
        BloodPressureEntity bloodPressureEntity = (BloodPressureEntity) other;
        return Intrinsics.areEqual(this.deviceAddress, bloodPressureEntity.deviceAddress) && this.unixTime == bloodPressureEntity.unixTime && this.sbp == bloodPressureEntity.sbp && this.dbp == bloodPressureEntity.dbp && this.sync == bloodPressureEntity.sync && this.lastSyncTime == bloodPressureEntity.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((this.deviceAddress.hashCode() * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.unixTime)) * 31) + this.sbp) * 31) + this.dbp) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "BloodPressureEntity(deviceAddress=" + this.deviceAddress + ", unixTime=" + this.unixTime + ", sbp=" + this.sbp + ", dbp=" + this.dbp + ", sync=" + this.sync + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public BloodPressureEntity(String deviceAddress, long j, int i, int i2, boolean z, long j2) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        this.deviceAddress = deviceAddress;
        this.unixTime = j;
        this.sbp = i;
        this.dbp = i2;
        this.sync = z;
        this.lastSyncTime = j2;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.deviceAddress = str;
    }

    public final long getUnixTime() {
        return this.unixTime;
    }

    public final void setUnixTime(long j) {
        this.unixTime = j;
    }

    public final int getSbp() {
        return this.sbp;
    }

    public final void setSbp(int i) {
        this.sbp = i;
    }

    public final int getDbp() {
        return this.dbp;
    }

    public final void setDbp(int i) {
        this.dbp = i;
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
