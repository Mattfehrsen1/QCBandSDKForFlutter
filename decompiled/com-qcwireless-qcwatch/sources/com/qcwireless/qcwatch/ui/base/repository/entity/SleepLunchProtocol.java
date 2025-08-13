package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepLunchProtocol.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\nHÆ\u0003J\t\u0010*\u001a\u00020\fHÆ\u0003JO\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010,\u001a\u00020\n2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0007HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00060"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepLunchProtocol;", "", "deviceAddress", "", "dateStr", "detail", "lunchSt", "", "lunchEt", "sync", "", "lastSyncTime", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZJ)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDetail", "setDetail", "getDeviceAddress", "setDeviceAddress", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getLunchEt", "()I", "setLunchEt", "(I)V", "getLunchSt", "setLunchSt", "getSync", "()Z", "setSync", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SleepLunchProtocol {
    private String dateStr;
    private String detail;
    private String deviceAddress;
    private long lastSyncTime;
    private int lunchEt;
    private int lunchSt;
    private boolean sync;

    /* renamed from: component1, reason: from getter */
    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDateStr() {
        return this.dateStr;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    /* renamed from: component4, reason: from getter */
    public final int getLunchSt() {
        return this.lunchSt;
    }

    /* renamed from: component5, reason: from getter */
    public final int getLunchEt() {
        return this.lunchEt;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component7, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final SleepLunchProtocol copy(String deviceAddress, String dateStr, String detail, int lunchSt, int lunchEt, boolean sync, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(detail, "detail");
        return new SleepLunchProtocol(deviceAddress, dateStr, detail, lunchSt, lunchEt, sync, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepLunchProtocol)) {
            return false;
        }
        SleepLunchProtocol sleepLunchProtocol = (SleepLunchProtocol) other;
        return Intrinsics.areEqual(this.deviceAddress, sleepLunchProtocol.deviceAddress) && Intrinsics.areEqual(this.dateStr, sleepLunchProtocol.dateStr) && Intrinsics.areEqual(this.detail, sleepLunchProtocol.detail) && this.lunchSt == sleepLunchProtocol.lunchSt && this.lunchEt == sleepLunchProtocol.lunchEt && this.sync == sleepLunchProtocol.sync && this.lastSyncTime == sleepLunchProtocol.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.detail.hashCode()) * 31) + this.lunchSt) * 31) + this.lunchEt) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "SleepLunchProtocol(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", detail=" + this.detail + ", lunchSt=" + this.lunchSt + ", lunchEt=" + this.lunchEt + ", sync=" + this.sync + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public SleepLunchProtocol(String deviceAddress, String dateStr, String detail, int i, int i2, boolean z, long j) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.detail = detail;
        this.lunchSt = i;
        this.lunchEt = i2;
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

    public final String getDetail() {
        return this.detail;
    }

    public final void setDetail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.detail = str;
    }

    public final int getLunchSt() {
        return this.lunchSt;
    }

    public final void setLunchSt(int i) {
        this.lunchSt = i;
    }

    public final int getLunchEt() {
        return this.lunchEt;
    }

    public final void setLunchEt(int i) {
        this.lunchEt = i;
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
