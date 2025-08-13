package com.qcwireless.qcwatch.ui.home.pressure.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: PressureDetailBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/pressure/bean/PressureDetailBean;", "", "seconds", "", "value", "(II)V", "getSeconds", "()I", "setSeconds", "(I)V", "getValue", "setValue", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class PressureDetailBean {
    private int seconds;
    private int value;

    /* JADX WARN: Illegal instructions before constructor call */
    public PressureDetailBean() {
        int i = 0;
        this(i, i, 3, null);
    }

    public static /* synthetic */ PressureDetailBean copy$default(PressureDetailBean pressureDetailBean, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = pressureDetailBean.seconds;
        }
        if ((i3 & 2) != 0) {
            i2 = pressureDetailBean.value;
        }
        return pressureDetailBean.copy(i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getSeconds() {
        return this.seconds;
    }

    /* renamed from: component2, reason: from getter */
    public final int getValue() {
        return this.value;
    }

    public final PressureDetailBean copy(int seconds, int value) {
        return new PressureDetailBean(seconds, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PressureDetailBean)) {
            return false;
        }
        PressureDetailBean pressureDetailBean = (PressureDetailBean) other;
        return this.seconds == pressureDetailBean.seconds && this.value == pressureDetailBean.value;
    }

    public int hashCode() {
        return (this.seconds * 31) + this.value;
    }

    public String toString() {
        return "PressureDetailBean(seconds=" + this.seconds + ", value=" + this.value + ')';
    }

    public PressureDetailBean(int i, int i2) {
        this.seconds = i;
        this.value = i2;
    }

    public /* synthetic */ PressureDetailBean(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final void setSeconds(int i) {
        this.seconds = i;
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        this.value = i;
    }
}
