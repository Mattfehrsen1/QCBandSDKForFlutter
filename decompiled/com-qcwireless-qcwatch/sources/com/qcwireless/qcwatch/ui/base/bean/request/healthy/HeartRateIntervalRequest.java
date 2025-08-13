package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HeartRateIntervalRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HeartRateIntervalRequest;", "", "()V", "datas", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HeartRateIntervalRequest$IntervalHeartRateDetail;", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "uid", "", "getUid", "()J", "setUid", "(J)V", "IntervalHeartRateDetail", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class HeartRateIntervalRequest {
    private List<IntervalHeartRateDetail> datas;
    private long uid;

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final List<IntervalHeartRateDetail> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<IntervalHeartRateDetail> list) {
        this.datas = list;
    }

    /* compiled from: HeartRateIntervalRequest.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/HeartRateIntervalRequest$IntervalHeartRateDetail;", "", "()V", "date", "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "deviceId", "getDeviceId", "setDeviceId", "deviceType", "getDeviceType", "setDeviceType", "indexs", "", "", "getIndexs", "()Ljava/util/List;", "setIndexs", "(Ljava/util/List;)V", "interval", "getInterval", "()I", "setInterval", "(I)V", "values", "getValues", "setValues", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class IntervalHeartRateDetail {
        private String date;
        private String deviceId;
        private String deviceType;
        private int interval;
        private List<Integer> indexs = new ArrayList();
        private List<Integer> values = new ArrayList();

        public final String getDate() {
            return this.date;
        }

        public final void setDate(String str) {
            this.date = str;
        }

        public final int getInterval() {
            return this.interval;
        }

        public final void setInterval(int i) {
            this.interval = i;
        }

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
    }
}
