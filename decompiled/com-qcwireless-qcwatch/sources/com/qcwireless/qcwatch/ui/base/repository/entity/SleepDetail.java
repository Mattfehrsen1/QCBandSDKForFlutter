package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepDetail.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\nHÆ\u0003J\t\u0010*\u001a\u00020\fHÆ\u0003JO\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010,\u001a\u00020\n2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0006HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u00060"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/SleepDetail;", "", "deviceAddress", "", "dateStr", "intervar", "", "index_str", "quality", "sync", "", "lastSyncTime", "", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZJ)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeviceAddress", "setDeviceAddress", "getIndex_str", "setIndex_str", "getIntervar", "()I", "setIntervar", "(I)V", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getQuality", "setQuality", "getSync", "()Z", "setSync", "(Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SleepDetail {
    private String dateStr;
    private String deviceAddress;
    private String index_str;
    private int intervar;
    private long lastSyncTime;
    private String quality;
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
    public final int getIntervar() {
        return this.intervar;
    }

    /* renamed from: component4, reason: from getter */
    public final String getIndex_str() {
        return this.index_str;
    }

    /* renamed from: component5, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component7, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final SleepDetail copy(String deviceAddress, String dateStr, int intervar, String index_str, String quality, boolean sync, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(index_str, "index_str");
        Intrinsics.checkNotNullParameter(quality, "quality");
        return new SleepDetail(deviceAddress, dateStr, intervar, index_str, quality, sync, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SleepDetail)) {
            return false;
        }
        SleepDetail sleepDetail = (SleepDetail) other;
        return Intrinsics.areEqual(this.deviceAddress, sleepDetail.deviceAddress) && Intrinsics.areEqual(this.dateStr, sleepDetail.dateStr) && this.intervar == sleepDetail.intervar && Intrinsics.areEqual(this.index_str, sleepDetail.index_str) && Intrinsics.areEqual(this.quality, sleepDetail.quality) && this.sync == sleepDetail.sync && this.lastSyncTime == sleepDetail.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.intervar) * 31) + this.index_str.hashCode()) * 31) + this.quality.hashCode()) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "SleepDetail(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", intervar=" + this.intervar + ", index_str=" + this.index_str + ", quality=" + this.quality + ", sync=" + this.sync + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public SleepDetail(String deviceAddress, String dateStr, int i, String index_str, String quality, boolean z, long j) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(index_str, "index_str");
        Intrinsics.checkNotNullParameter(quality, "quality");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.intervar = i;
        this.index_str = index_str;
        this.quality = quality;
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

    public final String getIndex_str() {
        return this.index_str;
    }

    public final void setIndex_str(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.index_str = str;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final void setQuality(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.quality = str;
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
