package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SleepDetailResp.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#¨\u0006'"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SleepDetailResp;", "", "()V", "date", "", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "deviceId", "getDeviceId", "setDeviceId", "deviceType", "getDeviceType", "setDeviceType", "indexs", "", "", "getIndexs", "()Ljava/util/List;", "setIndexs", "(Ljava/util/List;)V", "intervar", "getIntervar", "()I", "setIntervar", "(I)V", "qualitys", "getQualitys", "setQualitys", "uid", "", "getUid", "()J", "setUid", "(J)V", "updateTime", "getUpdateTime", "setUpdateTime", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SleepDetailResp {
    private String date;
    private String deviceId;
    private String deviceType;
    private int intervar;
    private long uid;
    private long updateTime;
    private List<Integer> indexs = new ArrayList();
    private List<Integer> qualitys = new ArrayList();

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

    public final List<Integer> getIndexs() {
        return this.indexs;
    }

    public final void setIndexs(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.indexs = list;
    }

    public final List<Integer> getQualitys() {
        return this.qualitys;
    }

    public final void setQualitys(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.qualitys = list;
    }

    public final long getUpdateTime() {
        return this.updateTime;
    }

    public final void setUpdateTime(long j) {
        this.updateTime = j;
    }
}
