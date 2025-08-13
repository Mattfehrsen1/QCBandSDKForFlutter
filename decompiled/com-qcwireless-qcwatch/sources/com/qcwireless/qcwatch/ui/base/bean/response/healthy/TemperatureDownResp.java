package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemperatureDownResp.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/TemperatureDownResp;", "", "()V", "dateStr", "", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "deviceAddress", "getDeviceAddress", "setDeviceAddress", "deviceName", "getDeviceName", "setDeviceName", "temperatures", "", "", "getTemperatures", "()Ljava/util/List;", "setTemperatures", "(Ljava/util/List;)V", "uid", "", "getUid", "()J", "setUid", "(J)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureDownResp {
    private String deviceAddress;
    private String deviceName;
    private long uid;
    private String dateStr = "";
    private List<Float> temperatures = new ArrayList();

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final void setDeviceAddress(String str) {
        this.deviceAddress = str;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        this.deviceName = str;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final void setDateStr(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dateStr = str;
    }

    public final List<Float> getTemperatures() {
        return this.temperatures;
    }

    public final void setTemperatures(List<Float> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.temperatures = list;
    }
}
