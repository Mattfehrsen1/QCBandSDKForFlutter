package com.qcwireless.qcwatch.ui.base.repository.entity;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodOxygenEntity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001e\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/BloodOxygenEntity;", "", "deviceAddress", "", "dateStr", "minArray", "maxArray", "unixTime", "", "sync", "", "lastSyncTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZJ)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getDeviceAddress", "setDeviceAddress", "getLastSyncTime", "()J", "setLastSyncTime", "(J)V", "getMaxArray", "setMaxArray", "getMinArray", "setMinArray", "getSync", "()Z", "setSync", "(Z)V", "getUnixTime", "setUnixTime", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodOxygenEntity {
    private String dateStr;
    private String deviceAddress;
    private long lastSyncTime;
    private String maxArray;
    private String minArray;
    private boolean sync;
    private long unixTime;

    public BloodOxygenEntity(String deviceAddress, String dateStr, String minArray, String maxArray, long j, boolean z, long j2) {
        Intrinsics.checkNotNullParameter(deviceAddress, "deviceAddress");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        Intrinsics.checkNotNullParameter(minArray, "minArray");
        Intrinsics.checkNotNullParameter(maxArray, "maxArray");
        this.deviceAddress = deviceAddress;
        this.dateStr = dateStr;
        this.minArray = minArray;
        this.maxArray = maxArray;
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

    public final String getMinArray() {
        return this.minArray;
    }

    public final void setMinArray(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.minArray = str;
    }

    public final String getMaxArray() {
        return this.maxArray;
    }

    public final void setMaxArray(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.maxArray = str;
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
