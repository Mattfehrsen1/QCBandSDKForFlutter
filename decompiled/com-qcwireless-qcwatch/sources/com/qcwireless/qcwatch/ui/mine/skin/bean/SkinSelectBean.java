package com.qcwireless.qcwatch.ui.mine.skin.bean;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;

/* compiled from: SkinSelectBean.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/skin/bean/SkinSelectBean;", "", "type", "", "check", "", "(IZ)V", "getCheck", "()Z", "setCheck", "(Z)V", "getType", "()I", "setType", "(I)V", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SkinSelectBean {
    private boolean check;
    private int type;

    public static /* synthetic */ SkinSelectBean copy$default(SkinSelectBean skinSelectBean, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = skinSelectBean.type;
        }
        if ((i2 & 2) != 0) {
            z = skinSelectBean.check;
        }
        return skinSelectBean.copy(i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getCheck() {
        return this.check;
    }

    public final SkinSelectBean copy(int type, boolean check) {
        return new SkinSelectBean(type, check);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkinSelectBean)) {
            return false;
        }
        SkinSelectBean skinSelectBean = (SkinSelectBean) other;
        return this.type == skinSelectBean.type && this.check == skinSelectBean.check;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int i = this.type * 31;
        boolean z = this.check;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return i + i2;
    }

    public String toString() {
        return "SkinSelectBean(type=" + this.type + ", check=" + this.check + ')';
    }

    public SkinSelectBean(int i, boolean z) {
        this.type = i;
        this.check = z;
    }

    public final boolean getCheck() {
        return this.check;
    }

    public final int getType() {
        return this.type;
    }

    public final void setCheck(boolean z) {
        this.check = z;
    }

    public final void setType(int i) {
        this.type = i;
    }
}
