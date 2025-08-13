package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import java.util.List;
import kotlin.Metadata;

/* compiled from: SleepDetailRequest.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SleepDetailRequest;", "", "()V", "datas", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SleepDetailRequest$SleepDetailItem;", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "deviceId", "", "getDeviceId", "()Ljava/lang/String;", "setDeviceId", "(Ljava/lang/String;)V", "deviceType", "getDeviceType", "setDeviceType", "uid", "", "getUid", "()J", "setUid", "(J)V", "SleepDetailItem", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SleepDetailRequest {
    private List<SleepDetailItem> datas;
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

    public final List<SleepDetailItem> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<SleepDetailItem> list) {
        this.datas = list;
    }

    /* compiled from: SleepDetailRequest.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\u0018\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SleepDetailRequest$SleepDetailItem;", "", "()V", "date", "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "indexs", "", "", "getIndexs", "()Ljava/util/List;", "setIndexs", "(Ljava/util/List;)V", "intervar", "getIntervar", "()I", "setIntervar", "(I)V", "qualitys", "getQualitys", "setQualitys", "totalActiveTime", "getTotalActiveTime", "setTotalActiveTime", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SleepDetailItem {
        private String date;
        private List<Integer> indexs;
        private int intervar;
        private List<Integer> qualitys;
        private int totalActiveTime;

        public final String getDate() {
            return this.date;
        }

        public final void setDate(String str) {
            this.date = str;
        }

        public final int getIntervar() {
            return this.intervar;
        }

        public final void setIntervar(int i) {
            this.intervar = i;
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

        public final List<Integer> getQualitys() {
            return this.qualitys;
        }

        public final void setQualitys(List<Integer> list) {
            this.qualitys = list;
        }
    }
}
