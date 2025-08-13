package com.qcwireless.qcwatch.ui.base.bean.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DeviceFunctionList.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceFunctionList;", "", "manualHeart", "", "(Z)V", "getManualHeart", "()Z", "setManualHeart", "component1", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DeviceFunctionList {
    private boolean manualHeart;

    public DeviceFunctionList() {
        this(false, 1, null);
    }

    public static /* synthetic */ DeviceFunctionList copy$default(DeviceFunctionList deviceFunctionList, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = deviceFunctionList.manualHeart;
        }
        return deviceFunctionList.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getManualHeart() {
        return this.manualHeart;
    }

    public final DeviceFunctionList copy(boolean manualHeart) {
        return new DeviceFunctionList(manualHeart);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DeviceFunctionList) && this.manualHeart == ((DeviceFunctionList) other).manualHeart;
    }

    public int hashCode() {
        boolean z = this.manualHeart;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "DeviceFunctionList(manualHeart=" + this.manualHeart + ')';
    }

    public DeviceFunctionList(boolean z) {
        this.manualHeart = z;
    }

    public /* synthetic */ DeviceFunctionList(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean getManualHeart() {
        return this.manualHeart;
    }

    public final void setManualHeart(boolean z) {
        this.manualHeart = z;
    }
}
