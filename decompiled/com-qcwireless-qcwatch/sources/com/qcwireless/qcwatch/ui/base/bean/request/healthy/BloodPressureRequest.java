package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import java.util.List;
import kotlin.Metadata;

/* compiled from: BloodPressureRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodPressureRequest;", "", "()V", "datas", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodPressureRequest$BpDetail;", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "lastSyncId", "", "getLastSyncId", "()J", "setLastSyncId", "(J)V", "uid", "getUid", "setUid", "BpDetail", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodPressureRequest {
    private List<BpDetail> datas;
    private long lastSyncId;
    private long uid;

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final long getLastSyncId() {
        return this.lastSyncId;
    }

    public final void setLastSyncId(long j) {
        this.lastSyncId = j;
    }

    public final List<BpDetail> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<BpDetail> list) {
        this.datas = list;
    }

    /* compiled from: BloodPressureRequest.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodPressureRequest$BpDetail;", "", "()V", "dbp", "", "getDbp", "()I", "setDbp", "(I)V", "deviceId", "", "getDeviceId", "()Ljava/lang/String;", "setDeviceId", "(Ljava/lang/String;)V", "deviceType", "getDeviceType", "setDeviceType", "sbp", "getSbp", "setSbp", "time", "", "getTime", "()J", "setTime", "(J)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class BpDetail {
        private int dbp;
        private String deviceId;
        private String deviceType;
        private int sbp;
        private long time;

        public final long getTime() {
            return this.time;
        }

        public final void setTime(long j) {
            this.time = j;
        }

        public final int getDbp() {
            return this.dbp;
        }

        public final void setDbp(int i) {
            this.dbp = i;
        }

        public final int getSbp() {
            return this.sbp;
        }

        public final void setSbp(int i) {
            this.sbp = i;
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
