package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import java.util.List;
import kotlin.Metadata;

/* compiled from: BloodOxygenRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodOxygenRequest;", "", "()V", "datas", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodOxygenRequest$BO2Detail;", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "lastSyncId", "", "getLastSyncId", "()J", "setLastSyncId", "(J)V", "uid", "getUid", "setUid", "BO2Detail", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BloodOxygenRequest {
    private List<BO2Detail> datas;
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

    public final List<BO2Detail> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<BO2Detail> list) {
        this.datas = list;
    }

    /* compiled from: BloodOxygenRequest.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/BloodOxygenRequest$BO2Detail;", "", "()V", "dateString", "", "getDateString", "()Ljava/lang/String;", "setDateString", "(Ljava/lang/String;)V", "deviceAddress", "getDeviceAddress", "setDeviceAddress", "maxValue", "", "", "getMaxValue", "()Ljava/util/List;", "setMaxValue", "(Ljava/util/List;)V", "minValue", "getMinValue", "setMinValue", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class BO2Detail {
        private String dateString;
        private String deviceAddress;
        private List<Integer> maxValue;
        private List<Integer> minValue;

        public final String getDeviceAddress() {
            return this.deviceAddress;
        }

        public final void setDeviceAddress(String str) {
            this.deviceAddress = str;
        }

        public final String getDateString() {
            return this.dateString;
        }

        public final void setDateString(String str) {
            this.dateString = str;
        }

        public final List<Integer> getMinValue() {
            return this.minValue;
        }

        public final void setMinValue(List<Integer> list) {
            this.minValue = list;
        }

        public final List<Integer> getMaxValue() {
            return this.maxValue;
        }

        public final void setMaxValue(List<Integer> list) {
            this.maxValue = list;
        }
    }
}
