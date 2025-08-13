package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HRVDetail.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b(\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\nHÆ\u0003J\t\u0010-\u001a\u00020\fHÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003JY\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\nHÆ\u0001J\u0013\u00100\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0006HÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\r\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u00064"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/HRVDetail;", "", "deviceAddress", "", "dateStr", "intervar", "", "index_str", "value", "unixTime", "", "sync", "", "lastSyncTime", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;JZJ)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeviceAddress", "setDeviceAddress", "getIndex_str", "setIndex_str", "getIntervar", "()I", "setIntervar", "(I)V", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getSync", "()Z", "setSync", "(Z)V", "getUnixTime", "setUnixTime", "getValue", "setValue", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class HRVDetail {
    private String dateStr;
    private String deviceAddress;
    private String index_str;
    private int intervar;
    private long lastSyncTime;
    private boolean sync;
    private long unixTime;
    private String value;

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
    public final String getValue() {
        return this.value;
    }

    /* renamed from: component6, reason: from getter */
    public final long getUnixTime() {
        return this.unixTime;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getSync() {
        return this.sync;
    }

    /* renamed from: component8, reason: from getter */
    public final long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final HRVDetail copy(String deviceAddress, String dateStr, int intervar, String index_str, String value, long unixTime, boolean sync, long lastSyncTime) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(index_str, "index_str");
        Intrinsics.checkNotNullParameter(value, "value");
        return new HRVDetail(deviceAddress, dateStr, intervar, index_str, value, unixTime, sync, lastSyncTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HRVDetail)) {
            return false;
        }
        HRVDetail hRVDetail = (HRVDetail) other;
        return Intrinsics.areEqual(this.deviceAddress, hRVDetail.deviceAddress) && Intrinsics.areEqual(this.dateStr, hRVDetail.dateStr) && this.intervar == hRVDetail.intervar && Intrinsics.areEqual(this.index_str, hRVDetail.index_str) && Intrinsics.areEqual(this.value, hRVDetail.value) && this.unixTime == hRVDetail.unixTime && this.sync == hRVDetail.sync && this.lastSyncTime == hRVDetail.lastSyncTime;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((this.deviceAddress.hashCode() * 31) + this.dateStr.hashCode()) * 31) + this.intervar) * 31) + this.index_str.hashCode()) * 31) + this.value.hashCode()) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.unixTime)) * 31;
        boolean z = this.sync;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((iHashCode + i) * 31) + CollectionRequest$$ExternalSyntheticBackport0.m(this.lastSyncTime);
    }

    public String toString() {
        return "HRVDetail(deviceAddress=" + this.deviceAddress + ", dateStr=" + this.dateStr + ", intervar=" + this.intervar + ", index_str=" + this.index_str + ", value=" + this.value + ", unixTime=" + this.unixTime + ", sync=" + this.sync + ", lastSyncTime=" + this.lastSyncTime + ')';
    }

    public HRVDetail(String deviceAddress, String dateStr, int i, String index_str, String value, long j, boolean z, long j2) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(index_str, "index_str");
        Intrinsics.checkNotNullParameter(value, "value");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.intervar = i;
        this.index_str = index_str;
        this.value = value;
        this.unixTime = j;
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

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    public final long getUnixTime() {
        return this.unixTime;
    }

    public final void setUnixTime(long j) {
        this.unixTime = j;
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
