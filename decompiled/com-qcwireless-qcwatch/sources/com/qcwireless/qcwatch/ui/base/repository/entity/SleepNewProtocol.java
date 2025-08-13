package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepNewProtocol.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\nHÆ\u0003J\t\u0010*\u001a\u00020\fHÆ\u0003JO\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010,\u001a\u00020\n2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0007HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00060"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepNewProtocol;", "", "deviceAddress", "", "dateStr", "detail", "st", "", "et", "sync", "", "lastSyncTime", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZJ)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDetail", "setDetail", "getDeviceAddress", "setDeviceAddress", "getEt", "()I", "setEt", "(I)V", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getSt", "setSt", "getSync", "()Z", "setSync", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SleepNewProtocol {
    private String dateStr;
    private String detail;
    private String deviceAddress;
    private int et;
    private long lastSyncTime;
    private int st;
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
    public final int getSt() {
        return this.st;
    }

    /* renamed from: component5, reason: from getter */
    public final int getEt() {
        return this.et;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component7, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final SleepNewProtocol copy(String deviceAddress, String dateStr, String detail, int st, int et, boolean sync, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(detail, "detail");
        return new SleepNewProtocol(deviceAddress, dateStr, detail, st, et, sync, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepNewProtocol)) {
            return false;
        }
        SleepNewProtocol sleepNewProtocol = (SleepNewProtocol) other;
        return Intrinsics.areEqual(this.deviceAddress, sleepNewProtocol.deviceAddress) && Intrinsics.areEqual(this.dateStr, sleepNewProtocol.dateStr) && Intrinsics.areEqual(this.detail, sleepNewProtocol.detail) && this.st == sleepNewProtocol.st && this.et == sleepNewProtocol.et && this.sync == sleepNewProtocol.sync && this.lastSyncTime == sleepNewProtocol.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.detail.hashCode()) * 31) + this.st) * 31) + this.et) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "SleepNewProtocol(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", detail=" + this.detail + ", st=" + this.st + ", et=" + this.et + ", sync=" + this.sync + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public SleepNewProtocol(String deviceAddress, String dateStr, String detail, int i, int i2, boolean z, long j) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(detail, "detail");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.detail = detail;
        this.st = i;
        this.et = i2;
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

    public final int getSt() {
        return this.st;
    }

    public final void setSt(int i) {
        this.st = i;
    }

    public final int getEt() {
        return this.et;
    }

    public final void setEt(int i) {
        this.et = i;
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
