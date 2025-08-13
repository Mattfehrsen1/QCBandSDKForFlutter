package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HeartRateResp.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 ¨\u0006%"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp;", "", "()V", "data", "Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp$HeartDetail;", "getData", "()Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp$HeartDetail;", "setData", "(Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp$HeartDetail;)V", "date", "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "deviceId", "getDeviceId", "setDeviceId", "deviceType", "getDeviceType", "setDeviceType", "interval", "", "getInterval", "()I", "setInterval", "(I)V", "uid", "", "getUid", "()J", "setUid", "(J)V", "updateTime", "getUpdateTime", "setUpdateTime", "HeartDetail", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartRateResp {
    private HeartDetail data;
    private String date;
    private String deviceId;
    private String deviceType;
    private int interval;
    private long uid;
    private long updateTime;

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        this.date = str;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }

    public final void setUpdateTime(long j) {
        this.updateTime = j;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(String str) {
        this.deviceType = str;
    }

    public final HeartDetail getData() {
        return this.data;
    }

    public final void setData(HeartDetail heartDetail) {
        this.data = heartDetail;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final void setInterval(int i) {
        this.interval = i;
    }

    /* compiled from: HeartRateResp.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/HeartRateResp$HeartDetail;", "", "()V", "indexs", "", "", "getIndexs", "()Ljava/util/List;", "setIndexs", "(Ljava/util/List;)V", "values", "getValues", "setValues", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class HeartDetail {
        private List<Integer> indexs = new ArrayList();
        private List<Integer> values = new ArrayList();

        public final List<Integer> getIndexs() {
            return this.indexs;
        }

        public final void setIndexs(List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.indexs = list;
        }

        public final List<Integer> getValues() {
            return this.values;
        }

        public final void setValues(List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.values = list;
        }
    }
}
