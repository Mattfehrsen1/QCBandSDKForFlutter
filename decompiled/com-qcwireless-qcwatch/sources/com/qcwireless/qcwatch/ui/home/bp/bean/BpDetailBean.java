package com.qcwireless.qcwatch.ui.home.bp.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BpDetailBean.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\b\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/bp/bean/BpDetailBean;", "", "min", "", "sbp", "dbp", "(III)V", "getDbp", "()I", "getMin", "setMin", "(I)V", "getSbp", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class BpDetailBean {
    private final int dbp;
    private int min;
    private final int sbp;

    public BpDetailBean() {
        this(0, 0, 0, 7, null);
    }

    public static /* synthetic */ BpDetailBean copy$default(BpDetailBean bpDetailBean, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = bpDetailBean.min;
        }
        if ((i4 & 2) != 0) {
            i2 = bpDetailBean.sbp;
        }
        if ((i4 & 4) != 0) {
            i3 = bpDetailBean.dbp;
        }
        return bpDetailBean.copy(i, i2, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMin() {
        return this.min;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSbp() {
        return this.sbp;
    }

    /* renamed from: component3, reason: from getter */
    public final int getDbp() {
        return this.dbp;
    }

    public final BpDetailBean copy(int min, int sbp, int dbp) {
        return new BpDetailBean(min, sbp, dbp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BpDetailBean)) {
            return false;
        }
        BpDetailBean bpDetailBean = (BpDetailBean) other;
        return this.min == bpDetailBean.min && this.sbp == bpDetailBean.sbp && this.dbp == bpDetailBean.dbp;
    }

    public int hashCode() {
        return (((this.min * 31) + this.sbp) * 31) + this.dbp;
    }

    public String toString() {
        return "BpDetailBean(min=" + this.min + ", sbp=" + this.sbp + ", dbp=" + this.dbp + ')';
    }

    public BpDetailBean(int i, int i2, int i3) {
        this.min = i;
        this.sbp = i2;
        this.dbp = i3;
    }

    public /* synthetic */ BpDetailBean(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public final int getMin() {
        return this.min;
    }

    public final void setMin(int i) {
        this.min = i;
    }

    public final int getSbp() {
        return this.sbp;
    }

    public final int getDbp() {
        return this.dbp;
    }
}
