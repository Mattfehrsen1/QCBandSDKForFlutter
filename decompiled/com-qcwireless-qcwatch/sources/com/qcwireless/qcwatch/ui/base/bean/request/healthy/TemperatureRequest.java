package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.util.List;
import kotlin.Metadata;

/* compiled from: TemperatureRequest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureRequest;", "", "()V", "data", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureRequest$TemperatureDetail;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "deviceAddress", "", "getDeviceAddress", "()Ljava/lang/String;", "setDeviceAddress", "(Ljava/lang/String;)V", "deviceName", "getDeviceName", "setDeviceName", "uid", "", "getUid", "()J", "setUid", "(J)V", "TemperatureDetail", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemperatureRequest {
    private List<TemperatureDetail> data;
    private String deviceAddress;
    private String deviceName;
    private long uid;

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

    public final List<TemperatureDetail> getData() {
        return this.data;
    }

    public final void setData(List<TemperatureDetail> list) {
        this.data = list;
    }

    /* compiled from: TemperatureRequest.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/TemperatureRequest$TemperatureDetail;", "", "()V", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", "", "getContent", "()Ljava/util/List;", "setContent", "(Ljava/util/List;)V", "dateStr", "", "getDateStr", "()Ljava/lang/String;", "setDateStr", "(Ljava/lang/String;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TemperatureDetail {
        private List<Float> content;
        private String dateStr;

        public final String getDateStr() {
            return this.dateStr;
        }

        public final void setDateStr(String str) {
            this.dateStr = str;
        }

        public final List<Float> getContent() {
            return this.content;
        }

        public final void setContent(List<Float> list) {
            this.content = list;
        }
    }
}
