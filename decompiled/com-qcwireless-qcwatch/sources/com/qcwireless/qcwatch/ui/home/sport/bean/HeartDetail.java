package com.qcwireless.qcwatch.ui.home.sport.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.bean.request.collection.CollectionRequest$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* compiled from: SportDetail.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/bean/HeartDetail;", "", "time", "", "heartValue", "", "(JI)V", "getHeartValue", "()I", "setHeartValue", "(I)V", "getTime", "()J", "setTime", "(J)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class HeartDetail {
    private int heartValue;
    private long time;

    public static /* synthetic */ HeartDetail copy$default(HeartDetail heartDetail, long j, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = heartDetail.time;
        }
        if ((i2 & 2) != 0) {
            i = heartDetail.heartValue;
        }
        return heartDetail.copy(j, i);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTime() {
        return this.time;
    }

    /* renamed from: component2, reason: from getter */
    public final int getHeartValue() {
        return this.heartValue;
    }

    public final HeartDetail copy(long time, int heartValue) {
        return new HeartDetail(time, heartValue);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HeartDetail)) {
            return false;
        }
        HeartDetail heartDetail = (HeartDetail) other;
        return this.time == heartDetail.time && this.heartValue == heartDetail.heartValue;
    }

    public int hashCode() {
        return (CollectionRequest$$ExternalSyntheticBackport0.m(this.time) * 31) + this.heartValue;
    }

    public String toString() {
        return "HeartDetail(time=" + this.time + ", heartValue=" + this.heartValue + ')';
    }

    public HeartDetail(long j, int i) {
        this.time = j;
        this.heartValue = i;
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long j) {
        this.time = j;
    }

    public final int getHeartValue() {
        return this.heartValue;
    }

    public final void setHeartValue(int i) {
        this.heartValue = i;
    }
}
