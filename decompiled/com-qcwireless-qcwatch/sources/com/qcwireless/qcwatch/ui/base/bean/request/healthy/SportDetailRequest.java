package com.qcwireless.qcwatch.ui.base.bean.request.healthy;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SportDetailRequest.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SportDetailRequest;", "", "()V", "datas", "", "Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SportDetailRequest$SportDetailItem;", "getDatas", "()Ljava/util/List;", "setDatas", "(Ljava/util/List;)V", "uid", "", "getUid", "()J", "setUid", "(J)V", "SportDetailItem", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportDetailRequest {
    private List<SportDetailItem> datas = new ArrayList();
    private long uid;

    public final long getUid() {
        return this.uid;
    }

    public final void setUid(long j) {
        this.uid = j;
    }

    public final List<SportDetailItem> getDatas() {
        return this.datas;
    }

    public final void setDatas(List<SportDetailItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.datas = list;
    }

    /* compiled from: SportDetailRequest.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u001a\u0010*\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u001c\u0010-\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\f\"\u0004\b/\u0010\u000eR\u001a\u00100\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001aR\u001a\u00103\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&¨\u00066"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/request/healthy/SportDetailRequest$SportDetailItem;", "", "()V", "calorie", "", "getCalorie", "()F", "setCalorie", "(F)V", "deviceId", "", "getDeviceId", "()Ljava/lang/String;", "setDeviceId", "(Ljava/lang/String;)V", "deviceType", "getDeviceType", "setDeviceType", "distance", "getDistance", "setDistance", TypedValues.TransitionType.S_DURATION, "", "getDuration", "()J", "setDuration", "(J)V", "heartrates", "", "", "getHeartrates", "()Ljava/util/List;", "setHeartrates", "(Ljava/util/List;)V", "mRateAvg", "getMRateAvg", "()I", "setMRateAvg", "(I)V", "rawType", "getRawType", "setRawType", "sportCount", "getSportCount", "setSportCount", "sportMode", "getSportMode", "setSportMode", "startTime", "getStartTime", "setStartTime", "step", "getStep", "setStep", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class SportDetailItem {
        private float calorie;
        private String deviceId;
        private String deviceType;
        private float distance;
        private long duration;
        private List<Integer> heartrates = new ArrayList();
        private int mRateAvg = 90;
        private int rawType;
        private int sportCount;
        private String sportMode;
        private long startTime;
        private int step;

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

        public final long getStartTime() {
            return this.startTime;
        }

        public final void setStartTime(long j) {
            this.startTime = j;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final void setDuration(long j) {
            this.duration = j;
        }

        public final int getStep() {
            return this.step;
        }

        public final void setStep(int i) {
            this.step = i;
        }

        public final float getDistance() {
            return this.distance;
        }

        public final void setDistance(float f) {
            this.distance = f;
        }

        public final float getCalorie() {
            return this.calorie;
        }

        public final void setCalorie(float f) {
            this.calorie = f;
        }

        public final int getSportCount() {
            return this.sportCount;
        }

        public final void setSportCount(int i) {
            this.sportCount = i;
        }

        public final List<Integer> getHeartrates() {
            return this.heartrates;
        }

        public final void setHeartrates(List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.heartrates = list;
        }

        public final String getDeviceId() {
            return this.deviceId;
        }

        public final void setDeviceId(String str) {
            this.deviceId = str;
        }

        public final int getMRateAvg() {
            return this.mRateAvg;
        }

        public final void setMRateAvg(int i) {
            this.mRateAvg = i;
        }

        public final String getDeviceType() {
            return this.deviceType;
        }

        public final void setDeviceType(String str) {
            this.deviceType = str;
        }
    }
}
