package com.qcwireless.qcwatch.ui.home.temperature.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemperatureDetailBean.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/temperature/bean/TemperatureDetailBean;", "", "mins", "", "value", "", "(IF)V", "getMins", "()I", "setMins", "(I)V", "getValue", "()F", "setValue", "(F)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TemperatureDetailBean {
    private int mins;
    private float value;

    public static /* synthetic */ TemperatureDetailBean copy$default(TemperatureDetailBean temperatureDetailBean, int i, float f, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = temperatureDetailBean.mins;
        }
        if ((i2 & 2) != 0) {
            f = temperatureDetailBean.value;
        }
        return temperatureDetailBean.copy(i, f);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMins() {
        return this.mins;
    }

    /* renamed from: component2, reason: from getter */
    public final float getValue() {
        return this.value;
    }

    public final TemperatureDetailBean copy(int mins, float value) {
        return new TemperatureDetailBean(mins, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemperatureDetailBean)) {
            return false;
        }
        TemperatureDetailBean temperatureDetailBean = (TemperatureDetailBean) other;
        return this.mins == temperatureDetailBean.mins && Intrinsics.areEqual((Object) Float.valueOf(this.value), (Object) Float.valueOf(temperatureDetailBean.value));
    }

    public int hashCode() {
        return (this.mins * 31) + Float.floatToIntBits(this.value);
    }

    public String toString() {
        return "TemperatureDetailBean(mins=" + this.mins + ", value=" + this.value + ')';
    }

    public TemperatureDetailBean(int i, float f) {
        this.mins = i;
        this.value = f;
    }

    public final int getMins() {
        return this.mins;
    }

    public final void setMins(int i) {
        this.mins = i;
    }

    public final float getValue() {
        return this.value;
    }

    public final void setValue(float f) {
        this.value = f;
    }
}
