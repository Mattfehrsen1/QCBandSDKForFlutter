package com.qcwireless.qcwatch.ui.home.bloodsugar.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BloodSugarDetailBean.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bloodsugar/bean/BloodSugarDetailBean;", "", "seconds", "", "minValue", "", "maxValue", "(IFF)V", "getMaxValue", "()F", "setMaxValue", "(F)V", "getMinValue", "setMinValue", "getSeconds", "()I", "setSeconds", "(I)V", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BloodSugarDetailBean {
    private float maxValue;
    private float minValue;
    private int seconds;

    public BloodSugarDetailBean() {
        this(0, 0.0f, 0.0f, 7, null);
    }

    public static /* synthetic */ BloodSugarDetailBean copy$default(BloodSugarDetailBean bloodSugarDetailBean, int i, float f, float f2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bloodSugarDetailBean.seconds;
        }
        if ((i2 & 2) != 0) {
            f = bloodSugarDetailBean.minValue;
        }
        if ((i2 & 4) != 0) {
            f2 = bloodSugarDetailBean.maxValue;
        }
        return bloodSugarDetailBean.copy(i, f, f2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSeconds() {
        return this.seconds;
    }

    /* renamed from: component2, reason: from getter */
    public final float getMinValue() {
        return this.minValue;
    }

    /* renamed from: component3, reason: from getter */
    public final float getMaxValue() {
        return this.maxValue;
    }

    public final BloodSugarDetailBean copy(int seconds, float minValue, float maxValue) {
        return new BloodSugarDetailBean(seconds, minValue, maxValue);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodSugarDetailBean)) {
            return false;
        }
        BloodSugarDetailBean bloodSugarDetailBean = (BloodSugarDetailBean) other;
        return this.seconds == bloodSugarDetailBean.seconds && Intrinsics.areEqual((Object) Float.valueOf(this.minValue), (Object) Float.valueOf(bloodSugarDetailBean.minValue)) && Intrinsics.areEqual((Object) Float.valueOf(this.maxValue), (Object) Float.valueOf(bloodSugarDetailBean.maxValue));
    }

    public int hashCode() {
        return (((this.seconds * 31) + Float.floatToIntBits(this.minValue)) * 31) + Float.floatToIntBits(this.maxValue);
    }

    public String toString() {
        return "BloodSugarDetailBean(seconds=" + this.seconds + ", minValue=" + this.minValue + ", maxValue=" + this.maxValue + ')';
    }

    public BloodSugarDetailBean(int i, float f, float f2) {
        this.seconds = i;
        this.minValue = f;
        this.maxValue = f2;
    }

    public /* synthetic */ BloodSugarDetailBean(int i, float f, float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? 0.0f : f, (i2 & 4) != 0 ? 0.0f : f2);
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final void setSeconds(int i) {
        this.seconds = i;
    }

    public final float getMinValue() {
        return this.minValue;
    }

    public final void setMinValue(float f) {
        this.minValue = f;
    }

    public final float getMaxValue() {
        return this.maxValue;
    }

    public final void setMaxValue(float f) {
        this.maxValue = f;
    }
}
