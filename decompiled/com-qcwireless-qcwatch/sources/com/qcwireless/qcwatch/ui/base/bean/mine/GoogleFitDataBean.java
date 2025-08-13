package com.qcwireless.qcwatch.ui.base.bean.mine;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;

/* compiled from: GoogleFitDataBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/mine/GoogleFitDataBean;", "", "()V", "calorie", "", "getCalorie", "()I", "setCalorie", "(I)V", "step", "getStep", "setStep", "time", "getTime", "setTime", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GoogleFitDataBean {
    private int calorie;
    private int step;
    private int time;

    public final int getStep() {
        return this.step;
    }

    public final void setStep(int i) {
        this.step = i;
    }

    public final int getCalorie() {
        return this.calorie;
    }

    public final void setCalorie(int i) {
        this.calorie = i;
    }

    public final int getTime() {
        return this.time;
    }

    public final void setTime(int i) {
        this.time = i;
    }
}
