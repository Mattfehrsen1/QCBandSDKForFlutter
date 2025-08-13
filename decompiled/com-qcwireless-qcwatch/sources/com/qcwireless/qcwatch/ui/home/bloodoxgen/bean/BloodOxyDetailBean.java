package com.qcwireless.qcwatch.ui.home.bloodoxgen.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BloodOxyDetailBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodoxgen/bean/BloodOxyDetailBean;", "", "seconds", "", "minValue", "maxValue", "(III)V", "getMaxValue", "()I", "setMaxValue", "(I)V", "getMinValue", "setMinValue", "getSeconds", "setSeconds", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BloodOxyDetailBean {
    private int maxValue;
    private int minValue;
    private int seconds;

    public BloodOxyDetailBean() {
        this(0, 0, 0, 7, null);
    }

    public static /* synthetic */ BloodOxyDetailBean copy$default(BloodOxyDetailBean bloodOxyDetailBean, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bloodOxyDetailBean.seconds;
        }
        if ((i4 & 2) != 0) {
            i2 = bloodOxyDetailBean.minValue;
        }
        if ((i4 & 4) != 0) {
            i3 = bloodOxyDetailBean.maxValue;
        }
        return bloodOxyDetailBean.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSeconds() {
        return this.seconds;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMinValue() {
        return this.minValue;
    }

    /* renamed from: component3, reason: from getter */
    public final int getMaxValue() {
        return this.maxValue;
    }

    public final BloodOxyDetailBean copy(int seconds, int minValue, int maxValue) {
        return new BloodOxyDetailBean(seconds, minValue, maxValue);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodOxyDetailBean)) {
            return false;
        }
        BloodOxyDetailBean bloodOxyDetailBean = (BloodOxyDetailBean) other;
        return this.seconds == bloodOxyDetailBean.seconds && this.minValue == bloodOxyDetailBean.minValue && this.maxValue == bloodOxyDetailBean.maxValue;
    }

    public int hashCode() {
        return (((this.seconds * 31) + this.minValue) * 31) + this.maxValue;
    }

    public String toString() {
        return "BloodOxyDetailBean(seconds=" + this.seconds + ", minValue=" + this.minValue + ", maxValue=" + this.maxValue + ')';
    }

    public BloodOxyDetailBean(int i, int i2, int i3) {
        this.seconds = i;
        this.minValue = i2;
        this.maxValue = i3;
    }

    public /* synthetic */ BloodOxyDetailBean(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final void setSeconds(int i) {
        this.seconds = i;
    }

    public final int getMinValue() {
        return this.minValue;
    }

    public final void setMinValue(int i) {
        this.minValue = i;
    }

    public final int getMaxValue() {
        return this.maxValue;
    }

    public final void setMaxValue(int i) {
        this.maxValue = i;
    }
}
