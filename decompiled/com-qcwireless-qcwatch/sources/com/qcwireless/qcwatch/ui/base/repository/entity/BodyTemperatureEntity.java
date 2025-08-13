package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BodyTemperatureEntity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b+\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0002\u0010\u000fJ\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003J\t\u0010/\u001a\u00020\fHÆ\u0003J\t\u00100\u001a\u00020\nHÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003JY\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u0006HÆ\u0001J\u0013\u00103\u001a\u00020\f2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\nHÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001e\u0010\u000e\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\r\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019¨\u00067"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/BodyTemperatureEntity;", "", "deviceAddress", "", "dateStr", "unixTime", "", "temperature", "", "min", "", "sync", "", "manualFlag", "lastSyncTime", "(Ljava/lang/String;Ljava/lang/String;JFIZIJ)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeviceAddress", "setDeviceAddress", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getManualFlag", "()I", "setManualFlag", "(I)V", "getMin", "setMin", "getSync", "()Z", "setSync", "(Z)V", "getTemperature", "()F", "setTemperature", "(F)V", "getUnixTime", "setUnixTime", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BodyTemperatureEntity {
    private String dateStr;
    private String deviceAddress;
    private long lastSyncTime;
    private int manualFlag;
    private int min;
    private boolean sync;
    private float temperature;
    private long unixTime;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    /* renamed from: component3, reason: from getter */
    public final long getUnixTime() {
        return this.unixTime;
    }

    /* renamed from: component4, reason: from getter */
    public final float getTemperature() {
        return this.temperature;
    }

    /* renamed from: component5, reason: from getter */
    public final int getMin() {
        return this.min;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component7, reason: from getter */
    public final int getManualFlag() {
        return this.manualFlag;
    }

    /* renamed from: component8, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final BodyTemperatureEntity copy(String deviceAddress, String dateStr, long unixTime, float temperature, int min, boolean sync, int manualFlag, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        return new BodyTemperatureEntity(deviceAddress, dateStr, unixTime, temperature, min, sync, manualFlag, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BodyTemperatureEntity)) {
            return false;
        }
        BodyTemperatureEntity bodyTemperatureEntity = (BodyTemperatureEntity) other;
        return Intrinsics.areEqual(this.deviceAddress, bodyTemperatureEntity.deviceAddress) && Intrinsics.areEqual(this.dateStr, bodyTemperatureEntity.dateStr) && this.unixTime == bodyTemperatureEntity.unixTime && Intrinsics.areEqual((Object) Float.valueOf(this.temperature), (Object) Float.valueOf(bodyTemperatureEntity.temperature)) && this.min == bodyTemperatureEntity.min && this.sync == bodyTemperatureEntity.sync && this.manualFlag == bodyTemperatureEntity.manualFlag && this.lastSyncTime == bodyTemperatureEntity.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.unixTime)) * 31) + Float.floatToIntBits(this.temperature)) * 31) + this.min) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((((iHashCode + i) * 31) + this.manualFlag) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "BodyTemperatureEntity(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", unixTime=" + this.unixTime + ", temperature=" + this.temperature + ", min=" + this.min + ", sync=" + this.sync + ", manualFlag=" + this.manualFlag + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public BodyTemperatureEntity(String deviceAddress, String dateStr, long j, float f, int i, boolean z, int i2, long j2) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.unixTime = j;
        this.temperature = f;
        this.min = i;
        this.sync = z;
        this.manualFlag = i2;
        this.lastSyncTime = j2;
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

    public final long getUnixTime() {
        return this.unixTime;
    }

    public final void setUnixTime(long j) {
        this.unixTime = j;
    }

    public final float getTemperature() {
        return this.temperature;
    }

    public final void setTemperature(float f) {
        this.temperature = f;
    }

    public final int getMin() {
        return this.min;
    }

    public final void setMin(int i) {
        this.min = i;
    }

    public final boolean getSync() {
        return this.sync;
    }

    public final void setSync(boolean z) {
        this.sync = z;
    }

    public final int getManualFlag() {
        return this.manualFlag;
    }

    public final void setManualFlag(int i) {
        this.manualFlag = i;
    }

    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final void setLastSyncTime(long j) {
        this.lastSyncTime = j;
    }
}
