package com.qcwireless.qcwatch.ui.base.repository.entity;

import com.baidu.location.LocationConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HRVManualEntity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/HRVManualEntity;", "", "mac", "", "dateStr", "pressure", "", LocationConst.HDYawConst.KEY_HD_YAW_TIMESTAMP, "(Ljava/lang/String;Ljava/lang/String;II)V", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "getMac", "setMac", "getPressure", "()I", "setPressure", "(I)V", "getTimestamp", "setTimestamp", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HRVManualEntity {
    private String dateStr;
    private String mac;
    private int pressure;
    private int timestamp;

    public HRVManualEntity(String mac, String dateStr, int i, int i2) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        Intrinsics.checkNotNullParameter(dateStr, "dateStr");
        this.mac = mac;
        this.dateStr = dateStr;
        this.pressure = i;
        this.timestamp = i2;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mac = str;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final void setDateStr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dateStr = str;
    }

    public final int getPressure() {
        return this.pressure;
    }

    public final void setPressure(int i) {
        this.pressure = i;
    }

    public final int getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(int i) {
        this.timestamp = i;
    }
}
