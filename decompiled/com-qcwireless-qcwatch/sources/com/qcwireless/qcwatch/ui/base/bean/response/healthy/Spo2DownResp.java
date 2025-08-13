package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: Spo2DownResp.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u0011X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/Spo2DownResp;", "", "()V", "dateStr", "", "getDateStr", "()Ljava/lang/String;", "deviceAddress", "getDeviceAddress", "maxValue", "", "", "getMaxValue", "()Ljava/util/List;", "minValue", "getMinValue", "uid", "", "getUid", "()J", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Spo2DownResp {
    private final long uid;
    private final String deviceAddress = "";
    private final String dateStr = "";
    private final List<Integer> minValue = new ArrayList();
    private final List<Integer> maxValue = new ArrayList();

    public final long getUid() {
        return this.uid;
    }

    public final String getDeviceAddress() {
        return this.deviceAddress;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final List<Integer> getMinValue() {
        return this.minValue;
    }

    public final List<Integer> getMaxValue() {
        return this.maxValue;
    }
}
