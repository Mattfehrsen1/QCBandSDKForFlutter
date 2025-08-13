package com.qcwireless.qcwatch.ui.base.bean.device;

import com.google.android.gms.fitness.FitnessActivities;
import com.king.zxing.util.CodeUtils;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DrinkBean.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/device/DrinkBean;", "", "title", "", "time", "", "switch", "", "(Ljava/lang/String;IZ)V", "getSwitch", "()Z", "setSwitch", "(Z)V", "getTime", "()I", "setTime", "(I)V", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DrinkBean {
    private boolean switch;
    private int time;
    private String title;

    public DrinkBean() {
        this(null, 0, false, 7, null);
    }

    public static /* synthetic */ DrinkBean copy$default(DrinkBean drinkBean, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = drinkBean.title;
        }
        if ((i2 & 2) != 0) {
            i = drinkBean.time;
        }
        if ((i2 & 4) != 0) {
            z = drinkBean.switch;
        }
        return drinkBean.copy(str, i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component2, reason: from getter */
    public final int getTime() {
        return this.time;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getSwitch() {
        return this.switch;
    }

    public final DrinkBean copy(String title, int time, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new DrinkBean(title, time, z);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DrinkBean)) {
            return false;
        }
        DrinkBean drinkBean = (DrinkBean) other;
        return Intrinsics.areEqual(this.title, drinkBean.title) && this.time == drinkBean.time && this.switch == drinkBean.switch;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.title.hashCode() * 31) + this.time) * 31;
        boolean z = this.switch;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "DrinkBean(title=" + this.title + ", time=" + this.time + ", switch=" + this.switch + ')';
    }

    public DrinkBean(String title, int i, boolean z) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.time = i;
        this.switch = z;
    }

    public /* synthetic */ DrinkBean(String str, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? CodeUtils.DEFAULT_REQ_WIDTH : i, (i2 & 4) != 0 ? false : z);
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.title = str;
    }

    public final int getTime() {
        return this.time;
    }

    public final void setTime(int i) {
        this.time = i;
    }

    public final boolean getSwitch() {
        return this.switch;
    }

    public final void setSwitch(boolean z) {
        this.switch = z;
    }
}
