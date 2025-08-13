package com.qcwireless.qcwatch.ui.base.repository.entity;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MenstruationEntity.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0019\b\u0007\u0018\u00002\u00020\u0001B7\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0002\u0010\u000bR\u001e\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000fR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/entity/MenstruationEntity;", "", "monthDate", "", "year", "", "month", "startTime", "", "endTime", TypedValues.TransitionType.S_DURATION, "(Ljava/lang/String;IIJJJ)V", "getDuration", "()J", "setDuration", "(J)V", "getEndTime", "setEndTime", "mid", "getMid", "()I", "setMid", "(I)V", "getMonth", "setMonth", "getMonthDate", "()Ljava/lang/String;", "setMonthDate", "(Ljava/lang/String;)V", "getStartTime", "setStartTime", "getYear", "setYear", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MenstruationEntity {
    private long duration;
    private long endTime;
    private int mid;
    private int month;
    private String monthDate;
    private long startTime;
    private int year;

    public final int getMid() {
        return this.mid;
    }

    public final void setMid(int i) {
        this.mid = i;
    }

    public final String getMonthDate() {
        return this.monthDate;
    }

    public final void setMonthDate(String str) {
        this.monthDate = str;
    }

    public final int getYear() {
        return this.year;
    }

    public final void setYear(int i) {
        this.year = i;
    }

    public final int getMonth() {
        return this.month;
    }

    public final void setMonth(int i) {
        this.month = i;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(long j) {
        this.endTime = j;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long j) {
        this.duration = j;
    }

    public MenstruationEntity(String monthDate, int i, int i2, long j, long j2, long j3) {
        Intrinsics.checkNotNullParameter(monthDate, "monthDate");
        this.monthDate = monthDate;
        this.year = i;
        this.month = i2;
        this.startTime = j;
        this.endTime = j2;
        this.duration = j3;
    }
}
