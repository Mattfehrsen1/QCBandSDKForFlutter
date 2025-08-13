package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: StepDetailResp.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0006R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0006R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0006R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0006R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000bR\u0014\u0010\u001d\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0014\u0010\u001f\u001a\u00020 X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020 X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"¨\u0006%"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/StepDetailResp;", "", "()V", "appId", "", "getAppId", "()Ljava/lang/String;", Field.NUTRIENT_CALORIES, "", "", "getCalories", "()Ljava/util/List;", "counts", "getCounts", "date", "getDate", "dateStr", "getDateStr", "deviceId", "getDeviceId", "deviceType", "getDeviceType", "indexs", "getIndexs", "interval", "getInterval", "()I", "miles", "getMiles", "totalActiveTime", "getTotalActiveTime", "uid", "", "getUid", "()J", "update_time", "getUpdate_time", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StepDetailResp {
    private final String appId;
    private final String date;
    private final String dateStr;
    private final String deviceId;
    private final String deviceType;
    private final int interval;
    private final int totalActiveTime;
    private final long uid;
    private final long update_time;
    private final List<Integer> calories = new ArrayList();
    private final List<Integer> counts = new ArrayList();
    private final List<Integer> miles = new ArrayList();
    private final List<Integer> indexs = new ArrayList();

    public final long getUid() {
        return this.uid;
    }

    public final String getDate() {
        return this.date;
    }

    public final String getAppId() {
        return this.appId;
    }

    public final List<Integer> getCalories() {
        return this.calories;
    }

    public final List<Integer> getCounts() {
        return this.counts;
    }

    public final List<Integer> getMiles() {
        return this.miles;
    }

    public final List<Integer> getIndexs() {
        return this.indexs;
    }

    public final String getDateStr() {
        return this.dateStr;
    }

    public final int getTotalActiveTime() {
        return this.totalActiveTime;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final long getUpdate_time() {
        return this.update_time;
    }
}
