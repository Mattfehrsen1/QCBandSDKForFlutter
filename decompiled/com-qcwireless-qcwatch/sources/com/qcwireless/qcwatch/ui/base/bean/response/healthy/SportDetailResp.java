package com.qcwireless.qcwatch.ui.base.bean.response.healthy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.liulishuo.okdownload.core.breakpoint.BreakpointSQLiteKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SportDetailResp.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0006R\u001a\u0010,\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0006\"\u0004\b.\u0010\bR\u001c\u0010/\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000f\"\u0004\b1\u0010\u0011R\u001a\u00102\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010'\"\u0004\b4\u0010)R\u001a\u00105\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0006\"\u0004\b7\u0010\bR\u001a\u00108\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010'\"\u0004\b:\u0010)¨\u0006;"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/healthy/SportDetailResp;", "", "()V", "SportCount", "", "getSportCount", "()I", "setSportCount", "(I)V", "calorie", "getCalorie", "setCalorie", "deviceId", "", "getDeviceId", "()Ljava/lang/String;", "setDeviceId", "(Ljava/lang/String;)V", "deviceType", "getDeviceType", "setDeviceType", "distance", "getDistance", "setDistance", TypedValues.TransitionType.S_DURATION, "", "getDuration", "()F", "setDuration", "(F)V", "heartrates", "", "getHeartrates", "()Ljava/util/List;", "setHeartrates", "(Ljava/util/List;)V", BreakpointSQLiteKey.ID, "", "getId", "()J", "setId", "(J)V", "mRateAvg", "getMRateAvg", "rawType", "getRawType", "setRawType", "sportMode", "getSportMode", "setSportMode", "startTime", "getStartTime", "setStartTime", "step", "getStep", "setStep", "uid", "getUid", "setUid", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportDetailResp {
    private int SportCount;
    private int calorie;
    private String deviceId;
    private String deviceType;
    private int distance;
    private float duration;
    private long id;
    private int rawType;
    private String sportMode;
    private long startTime;
    private int step;
    private long uid;
    private final int mRateAvg = 90;
    private List<Integer> heartrates = new ArrayList();

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final long getId() {
        return this.id;
    }

    public final void setId(long j) {
        this.id = j;
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

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final String getSportMode() {
        return this.sportMode;
    }

    public final void setSportMode(String str) {
        this.sportMode = str;
    }

    public final int getRawType() {
        return this.rawType;
    }

    public final void setRawType(int i) {
        this.rawType = i;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final int getCalorie() {
        return this.calorie;
    }

    public final void setCalorie(int i) {
        this.calorie = i;
    }

    public final int getStep() {
        return this.step;
    }

    public final void setStep(int i) {
        this.step = i;
    }

    public final float getDuration() {
        return this.duration;
    }

    public final void setDuration(float f) {
        this.duration = f;
    }

    public final int getSportCount() {
        return this.SportCount;
    }

    public final void setSportCount(int i) {
        this.SportCount = i;
    }

    public final int getMRateAvg() {
        return this.mRateAvg;
    }

    public final List<Integer> getHeartrates() {
        return this.heartrates;
    }

    public final void setHeartrates(List<Integer> list) {
        this.heartrates = list;
    }
}
