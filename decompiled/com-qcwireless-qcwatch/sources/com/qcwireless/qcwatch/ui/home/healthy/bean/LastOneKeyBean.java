package com.qcwireless.qcwatch.ui.home.healthy.bean;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;

/* compiled from: LastOneKeyBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyBean;", "", "()V", "bo", "", "getBo", "()I", "setBo", "(I)V", "dbp", "getDbp", "setDbp", "fatigue", "getFatigue", "setFatigue", "hr", "getHr", "setHr", "lastTime", "", "getLastTime", "()J", "setLastTime", "(J)V", "sbp", "getSbp", "setSbp", "score", "getScore", "setScore", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LastOneKeyBean {
    private int bo;
    private int dbp;
    private int fatigue = 50;
    private int hr;
    private long lastTime;
    private int sbp;
    private int score;

    public final long getLastTime() {
        return this.lastTime;
    }

    public final void setLastTime(long j) {
        this.lastTime = j;
    }

    public final int getSbp() {
        return this.sbp;
    }

    public final void setSbp(int i) {
        this.sbp = i;
    }

    public final int getDbp() {
        return this.dbp;
    }

    public final void setDbp(int i) {
        this.dbp = i;
    }

    public final int getHr() {
        return this.hr;
    }

    public final void setHr(int i) {
        this.hr = i;
    }

    public final int getBo() {
        return this.bo;
    }

    public final void setBo(int i) {
        this.bo = i;
    }

    public final int getFatigue() {
        return this.fatigue;
    }

    public final void setFatigue(int i) {
        this.fatigue = i;
    }

    public final int getScore() {
        return this.score;
    }

    public final void setScore(int i) {
        this.score = i;
    }
}
