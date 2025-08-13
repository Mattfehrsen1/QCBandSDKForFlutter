package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import com.google.android.gms.fitness.data.Field;
import java.util.List;
import kotlin.Metadata;

/* compiled from: StepDetailRequest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/StepDetailRequest;", "", "()V", "datas", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/StepDetailRequest$StepDetailItem;", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "deviceId", "", "getDeviceId", "()Ljava/lang/String;", "setDeviceId", "(Ljava/lang/String;)V", "deviceType", "getDeviceType", "setDeviceType", "uid", "", "getUid", "()J", "setUid", "(J)V", "StepDetailItem", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StepDetailRequest {
    private List<StepDetailItem> datas;
    private String deviceId;
    private String deviceType;
    private long uid;

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
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

    public final List<StepDetailItem> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<StepDetailItem> list) {
        this.datas = list;
    }

    /* compiled from: StepDetailRequest.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0007\"\u0004\b\u001d\u0010\tR\u001a\u0010\u001e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001a¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/StepDetailRequest$StepDetailItem;", "", "()V", Field.NUTRIENT_CALORIES, "", "", "getCalories", "()Ljava/util/List;", "setCalories", "(Ljava/util/List;)V", "counts", "getCounts", "setCounts", "date", "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "indexs", "getIndexs", "setIndexs", "interval", "getInterval", "()I", "setInterval", "(I)V", "miles", "getMiles", "setMiles", "totalActiveTime", "getTotalActiveTime", "setTotalActiveTime", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class StepDetailItem {
        private List<Integer> calories;
        private List<Integer> counts;
        private String date;
        private List<Integer> indexs;
        private int interval;
        private List<Integer> miles;
        private int totalActiveTime;

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

        public final int getTotalActiveTime() {
            return this.totalActiveTime;
        }

        public final void setTotalActiveTime(int i) {
            this.totalActiveTime = i;
        }

        public final List<Integer> getIndexs() {
            return this.indexs;
        }

        public final void setIndexs(List<Integer> list) {
            this.indexs = list;
        }

        public final List<Integer> getCounts() {
            return this.counts;
        }

        public final void setCounts(List<Integer> list) {
            this.counts = list;
        }

        public final List<Integer> getMiles() {
            return this.miles;
        }

        public final void setMiles(List<Integer> list) {
            this.miles = list;
        }

        public final List<Integer> getCalories() {
            return this.calories;
        }

        public final void setCalories(List<Integer> list) {
            this.calories = list;
        }
    }
}
