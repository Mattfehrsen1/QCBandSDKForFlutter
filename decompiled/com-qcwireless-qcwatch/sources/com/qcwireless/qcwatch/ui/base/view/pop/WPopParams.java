package com.qcwireless.qcwatch.ui.base.view.pop;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopup;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WPopParams.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b6\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\t\u0010V\u001a\u00020\u0007HÆ\u0003J\t\u0010W\u001a\u00020\tHÆ\u0003J\t\u0010X\u001a\u00020\u0007HÆ\u0003J\t\u0010Y\u001a\u00020\u0003HÆ\u0003J\t\u0010Z\u001a\u00020\u0003HÆ\u0003JO\u0010[\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\\\u001a\u00020\u00072\b\u0010]\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010^\u001a\u00020\u0003HÖ\u0001J\t\u0010_\u001a\u00020`HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014R\u001a\u0010\u001f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0012\"\u0004\b!\u0010\u0014R\u001a\u0010\"\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R\u001a\u0010%\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0012\"\u0004\b'\u0010\u0014R\u001a\u0010(\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010\u0014R\u001a\u0010+\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0012\"\u0004\b-\u0010\u0014R\u001a\u0010.\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0012\"\u0004\b0\u0010\u0014R\u001a\u00101\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0012\"\u0004\b3\u0010\u0014R\u001a\u00104\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0012\"\u0004\b6\u0010\u0014R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0012\"\u0004\b<\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0016\"\u0004\b=\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0012R\u001c\u0010?\u001a\u0004\u0018\u00010@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\"\u0010E\u001a\n\u0012\u0004\u0012\u00020G\u0018\u00010FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001c\u0010L\u001a\u0004\u0018\u00010MX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0012\"\u0004\bS\u0010\u0014¨\u0006a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;", "", "layoutRes", "", "activity", "Landroid/app/Activity;", "isDim", "", "dimValue", "", "cancelable", "width", "height", "(ILandroid/app/Activity;ZFZII)V", "getActivity", "()Landroid/app/Activity;", "animRes", "getAnimRes", "()I", "setAnimRes", "(I)V", "getCancelable", "()Z", "setCancelable", "(Z)V", "commonDraablePadding", "getCommonDraablePadding", "setCommonDraablePadding", "commonIconDirection", "getCommonIconDirection", "setCommonIconDirection", "commonItemTextColor", "getCommonItemTextColor", "setCommonItemTextColor", "commonItemTextSize", "getCommonItemTextSize", "setCommonItemTextSize", "commonPopMargin", "getCommonPopMargin", "setCommonPopMargin", "commonPopupBgColor", "getCommonPopupBgColor", "setCommonPopupBgColor", "commonPopupDividerColor", "getCommonPopupDividerColor", "setCommonPopupDividerColor", "commonPopupDividerMargin", "getCommonPopupDividerMargin", "setCommonPopupDividerMargin", "commonPopupDividerSize", "getCommonPopupDividerSize", "setCommonPopupDividerSize", "commonPopupOrientation", "getCommonPopupOrientation", "setCommonPopupOrientation", "getDimValue", "()F", "setDimValue", "(F)V", "getHeight", "setHeight", "setDim", "getLayoutRes", "longClickView", "Landroid/view/View;", "getLongClickView", "()Landroid/view/View;", "setLongClickView", "(Landroid/view/View;)V", "mCommonData", "", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupModel;", "getMCommonData", "()Ljava/util/List;", "setMCommonData", "(Ljava/util/List;)V", "mWItemClickListener", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder$OnItemClickListener;", "getMWItemClickListener", "()Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder$OnItemClickListener;", "setMWItemClickListener", "(Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder$OnItemClickListener;)V", "getWidth", "setWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class WPopParams {
    private final Activity activity;
    private int animRes;
    private boolean cancelable;
    private int commonDraablePadding;
    private int commonIconDirection;
    private int commonItemTextColor;
    private int commonItemTextSize;
    private int commonPopMargin;
    private int commonPopupBgColor;
    private int commonPopupDividerColor;
    private int commonPopupDividerMargin;
    private int commonPopupDividerSize;
    private int commonPopupOrientation;
    private float dimValue;
    private int height;
    private boolean isDim;
    private final int layoutRes;
    private View longClickView;
    private List<WPopupModel> mCommonData;
    private WPopup.Builder.OnItemClickListener mWItemClickListener;
    private int width;

    public static /* synthetic */ WPopParams copy$default(WPopParams wPopParams, int i, Activity activity, boolean z, float f, boolean z2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = wPopParams.layoutRes;
        }
        if ((i4 & 2) != 0) {
            activity = wPopParams.activity;
        }
        Activity activity2 = activity;
        if ((i4 & 4) != 0) {
            z = wPopParams.isDim;
        }
        boolean z3 = z;
        if ((i4 & 8) != 0) {
            f = wPopParams.dimValue;
        }
        float f2 = f;
        if ((i4 & 16) != 0) {
            z2 = wPopParams.cancelable;
        }
        boolean z4 = z2;
        if ((i4 & 32) != 0) {
            i2 = wPopParams.width;
        }
        int i5 = i2;
        if ((i4 & 64) != 0) {
            i3 = wPopParams.height;
        }
        return wPopParams.copy(i, activity2, z3, f2, z4, i5, i3);
    }

    /* renamed from: component1, reason: from getter */
    public final int getLayoutRes() {
        return this.layoutRes;
    }

    /* renamed from: component2, reason: from getter */
    public final Activity getActivity() {
        return this.activity;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsDim() {
        return this.isDim;
    }

    /* renamed from: component4, reason: from getter */
    public final float getDimValue() {
        return this.dimValue;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getCancelable() {
        return this.cancelable;
    }

    /* renamed from: component6, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component7, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    public final WPopParams copy(int layoutRes, Activity activity, boolean isDim, float dimValue, boolean cancelable, int width, int height) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return new WPopParams(layoutRes, activity, isDim, dimValue, cancelable, width, height);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WPopParams)) {
            return false;
        }
        WPopParams wPopParams = (WPopParams) other;
        return this.layoutRes == wPopParams.layoutRes && Intrinsics.areEqual(this.activity, wPopParams.activity) && this.isDim == wPopParams.isDim && Intrinsics.areEqual((Object) Float.valueOf(this.dimValue), (Object) Float.valueOf(wPopParams.dimValue)) && this.cancelable == wPopParams.cancelable && this.width == wPopParams.width && this.height == wPopParams.height;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.layoutRes * 31) + this.activity.hashCode()) * 31;
        boolean z = this.isDim;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int iFloatToIntBits = (((iHashCode + i) * 31) + Float.floatToIntBits(this.dimValue)) * 31;
        boolean z2 = this.cancelable;
        return ((((iFloatToIntBits + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.width) * 31) + this.height;
    }

    public String toString() {
        return "WPopParams(layoutRes=" + this.layoutRes + ", activity=" + this.activity + ", isDim=" + this.isDim + ", dimValue=" + this.dimValue + ", cancelable=" + this.cancelable + ", width=" + this.width + ", height=" + this.height + ')';
    }

    public WPopParams(int i, Activity activity, boolean z, float f, boolean z2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.layoutRes = i;
        this.activity = activity;
        this.isDim = z;
        this.dimValue = f;
        this.cancelable = z2;
        this.width = i2;
        this.height = i3;
        this.commonPopupOrientation = 1;
        this.commonPopupDividerColor = -1;
        this.commonPopupDividerSize = 1;
        this.commonPopupDividerMargin = 10;
        this.commonPopupBgColor = Color.parseColor("#A5000000");
        this.commonItemTextColor = Color.parseColor("#ffffff");
        this.commonItemTextSize = 14;
        this.commonPopMargin = 1;
        this.commonIconDirection = -1;
        this.commonDraablePadding = 5;
        this.animRes = WPopupAnim.INSTANCE.getANIM_ALPHA();
    }

    public /* synthetic */ WPopParams(int i, Activity activity, boolean z, float f, boolean z2, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, activity, (i4 & 4) != 0 ? false : z, (i4 & 8) != 0 ? 0.4f : f, (i4 & 16) != 0 ? true : z2, (i4 & 32) != 0 ? -2 : i2, (i4 & 64) != 0 ? -2 : i3);
    }

    public final int getLayoutRes() {
        return this.layoutRes;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public final boolean isDim() {
        return this.isDim;
    }

    public final void setDim(boolean z) {
        this.isDim = z;
    }

    public final float getDimValue() {
        return this.dimValue;
    }

    public final void setDimValue(float f) {
        this.dimValue = f;
    }

    public final boolean getCancelable() {
        return this.cancelable;
    }

    public final void setCancelable(boolean z) {
        this.cancelable = z;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final List<WPopupModel> getMCommonData() {
        return this.mCommonData;
    }

    public final void setMCommonData(List<WPopupModel> list) {
        this.mCommonData = list;
    }

    public final WPopup.Builder.OnItemClickListener getMWItemClickListener() {
        return this.mWItemClickListener;
    }

    public final void setMWItemClickListener(WPopup.Builder.OnItemClickListener onItemClickListener) {
        this.mWItemClickListener = onItemClickListener;
    }

    public final int getCommonPopupOrientation() {
        return this.commonPopupOrientation;
    }

    public final void setCommonPopupOrientation(int i) {
        this.commonPopupOrientation = i;
    }

    public final int getCommonPopupDividerColor() {
        return this.commonPopupDividerColor;
    }

    public final void setCommonPopupDividerColor(int i) {
        this.commonPopupDividerColor = i;
    }

    public final int getCommonPopupDividerSize() {
        return this.commonPopupDividerSize;
    }

    public final void setCommonPopupDividerSize(int i) {
        this.commonPopupDividerSize = i;
    }

    public final int getCommonPopupDividerMargin() {
        return this.commonPopupDividerMargin;
    }

    public final void setCommonPopupDividerMargin(int i) {
        this.commonPopupDividerMargin = i;
    }

    public final int getCommonPopupBgColor() {
        return this.commonPopupBgColor;
    }

    public final void setCommonPopupBgColor(int i) {
        this.commonPopupBgColor = i;
    }

    public final int getCommonItemTextColor() {
        return this.commonItemTextColor;
    }

    public final void setCommonItemTextColor(int i) {
        this.commonItemTextColor = i;
    }

    public final int getCommonItemTextSize() {
        return this.commonItemTextSize;
    }

    public final void setCommonItemTextSize(int i) {
        this.commonItemTextSize = i;
    }

    public final int getCommonPopMargin() {
        return this.commonPopMargin;
    }

    public final void setCommonPopMargin(int i) {
        this.commonPopMargin = i;
    }

    public final int getCommonIconDirection() {
        return this.commonIconDirection;
    }

    public final void setCommonIconDirection(int i) {
        this.commonIconDirection = i;
    }

    public final int getCommonDraablePadding() {
        return this.commonDraablePadding;
    }

    public final void setCommonDraablePadding(int i) {
        this.commonDraablePadding = i;
    }

    public final View getLongClickView() {
        return this.longClickView;
    }

    public final void setLongClickView(View view) {
        this.longClickView = view;
    }

    public final int getAnimRes() {
        return this.animRes;
    }

    public final void setAnimRes(int i) {
        this.animRes = i;
    }
}
