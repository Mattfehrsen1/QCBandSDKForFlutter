package com.qcwireless.qcwatch.ui.base.view.pop;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration;
import com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WPopup.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0002J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\nH\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup;", "Lcom/qcwireless/qcwatch/ui/base/view/pop/BasePopup;", "popParams", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;", "(Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;)V", "commonAdapter", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupAdapter;", "commonRootLayout", "Landroid/widget/LinearLayout;", "oldDirection", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "triangle", "Landroid/widget/ImageView;", "setTriangle", "", "view", "Landroid/view/View;", "showDirection", "showAtDirectionByView", "direction", "showAtFingerLocation", "showAtView", "Builder", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WPopup extends BasePopup {
    private WPopupAdapter commonAdapter;
    private final LinearLayout commonRootLayout;
    private int oldDirection;
    private RecyclerView recyclerView;
    private ImageView triangle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WPopup(WPopParams popParams) {
        super(popParams);
        Intrinsics.checkNotNullParameter(popParams, "popParams");
        WPopupAdapter wPopupAdapter = new WPopupAdapter(this);
        this.commonAdapter = wPopupAdapter;
        this.oldDirection = -100;
        List<WPopupModel> mCommonData = popParams.getMCommonData();
        Intrinsics.checkNotNull(mCommonData);
        wPopupAdapter.setData(mCommonData);
        if (popParams.getMWItemClickListener() != null) {
            WPopupAdapter wPopupAdapter2 = this.commonAdapter;
            Builder.OnItemClickListener mWItemClickListener = popParams.getMWItemClickListener();
            Intrinsics.checkNotNull(mWItemClickListener);
            wPopupAdapter2.setItemClickListener(mWItemClickListener);
        } else {
            Log.e("WPopup", "No item clickListener.");
        }
        this.commonAdapter.setDirection(popParams.getCommonIconDirection());
        this.commonAdapter.setTextColor(popParams.getCommonItemTextColor());
        this.commonAdapter.setTextSize(popParams.getCommonItemTextSize());
        this.commonAdapter.setDrawablePadding(popParams.getCommonDraablePadding());
        View viewFindViewById = getContentView().findViewById(R.id.mRvCommon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "getContentView().findViewById(R.id.mRvCommon)");
        this.recyclerView = (RecyclerView) viewFindViewById;
        View viewFindViewById2 = getContentView().findViewById(R.id.mCommonRootLayout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "getContentView().findVie…d(R.id.mCommonRootLayout)");
        this.commonRootLayout = (LinearLayout) viewFindViewById2;
        this.recyclerView.setAdapter(this.commonAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), popParams.getCommonPopupOrientation(), false));
        int commonPopupOrientation = popParams.getCommonPopupOrientation();
        if (commonPopupOrientation == 0) {
            this.recyclerView.addItemDecoration(new VerticalDividerItemDecoration.Builder(getContext()).margin(popParams.getCommonPopupDividerMargin()).size(popParams.getCommonPopupDividerSize()).color(popParams.getCommonPopupDividerColor()).build());
        } else {
            if (commonPopupOrientation != 1) {
                return;
            }
            this.recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext()).margin(popParams.getCommonPopupDividerMargin()).size(popParams.getCommonPopupDividerSize()).color(popParams.getCommonPopupDividerColor()).build());
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.BasePopup
    public void showAtView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        getShowDirection(view);
        super.showAtView(view);
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.BasePopup
    public void showAtFingerLocation(int direction) {
        switch (direction) {
            case -8:
            case -6:
            case -3:
                View longClickView = getPopParams().getLongClickView();
                Intrinsics.checkNotNull(longClickView);
                setTriangle(longClickView, -3);
                break;
            case -7:
            case -5:
            case -4:
                View longClickView2 = getPopParams().getLongClickView();
                Intrinsics.checkNotNull(longClickView2);
                setTriangle(longClickView2, -4);
                break;
        }
        super.showAtFingerLocation(direction);
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.BasePopup
    public void showAtDirectionByView(View view, int direction) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (direction == -4 || direction == -3) {
            setTriangle(view, direction);
        } else {
            ImageView imageView = this.triangle;
            if (imageView != null) {
                this.commonRootLayout.removeView(imageView);
                this.triangle = null;
            }
            this.oldDirection = direction;
        }
        super.showAtDirectionByView(view, direction);
    }

    private final void setTriangle(View view, int showDirection) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int measuredWidth = ((iArr[0] + (view.getMeasuredWidth() / 2)) - getPopupShowLocation(view)[0]) - getDefaultMargin();
        XLog.i(Integer.valueOf(measuredWidth));
        if (showDirection != this.oldDirection) {
            ImageView imageView = this.triangle;
            if (imageView != null) {
                this.commonRootLayout.removeView(imageView);
                this.triangle = null;
            }
            if (this.triangle == null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.leftMargin = measuredWidth;
                ImageView imageView2 = new ImageView(view.getContext());
                this.triangle = imageView2;
                if (showDirection == -4) {
                    Intrinsics.checkNotNull(imageView2);
                    imageView2.setBackgroundResource(R.drawable.triangle_down);
                    this.commonRootLayout.addView(this.triangle, 1, layoutParams);
                } else {
                    Intrinsics.checkNotNull(imageView2);
                    imageView2.setBackgroundResource(R.drawable.triangle_up);
                    this.commonRootLayout.addView(this.triangle, 0, layoutParams);
                }
            }
            this.oldDirection = showDirection;
        } else {
            ImageView imageView3 = this.triangle;
            if (imageView3 != null) {
                Intrinsics.checkNotNull(imageView3);
                ViewGroup.LayoutParams layoutParams2 = imageView3.getLayoutParams();
                Objects.requireNonNull(layoutParams2, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) layoutParams2;
                layoutParams3.leftMargin = measuredWidth;
                ImageView imageView4 = this.triangle;
                Intrinsics.checkNotNull(imageView4);
                imageView4.setLayoutParams(layoutParams3);
            }
        }
        ImageView imageView5 = this.triangle;
        Intrinsics.checkNotNull(imageView5);
        Drawable background = imageView5.getBackground();
        Objects.requireNonNull(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable drawable = ((LayerDrawable) background).getDrawable(0);
        Objects.requireNonNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.RotateDrawable");
        Drawable drawable2 = ((RotateDrawable) drawable).getDrawable();
        Objects.requireNonNull(drawable2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        ((GradientDrawable) drawable2).setColor(getPopParams().getCommonPopupBgColor());
        Drawable background2 = this.recyclerView.getBackground();
        Objects.requireNonNull(background2, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        ((GradientDrawable) background2).setColor(getPopParams().getCommonPopupBgColor());
    }

    /* compiled from: WPopup.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 /2\u00020\u0001:\u0002/0B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0012\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000bJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u000bJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u000bJ\u000e\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u000eJ\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000bJ\u000e\u0010)\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000bJ\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000bJ\u000e\u0010.\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "popParams", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;", "create", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup;", "setAnim", "anim", "", "setCancelable", "cancelable", "", "setClickView", "view", "Landroid/view/View;", "setData", "commonData", "", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupModel;", "setDimValue", "dimValue", "", "setDividerColor", TypedValues.Custom.S_COLOR, "setDividerMargin", "margin", "setDividerSize", "size", "setDrawablePadding", "padding", "setIconDirection", "d", "setIsDim", "isDim", "setOnItemClickListener", "itemClickListener", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder$OnItemClickListener;", "setPopupBgColor", "setPopupMargin", "setPopupOrientation", "orientation", "", "setTextColor", "setTextSize", "Companion", "OnItemClickListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        public static final String HORIZONTAL = "HORIZONTAL";
        public static final String VERTICAL = "VERTICAL";
        private final WPopParams popParams;

        /* compiled from: WPopup.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopup$Builder$OnItemClickListener;", "", "onItemClick", "", "view", "Landroid/view/View;", "position", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public interface OnItemClickListener {
            void onItemClick(View view, int position);
        }

        public Builder(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            this.popParams = new WPopParams(R.layout.pop_common, activity, false, 0.0f, false, 0, 0, 124, null);
        }

        public final Builder setData(List<WPopupModel> commonData) {
            Intrinsics.checkNotNullParameter(commonData, "commonData");
            this.popParams.setMCommonData(commonData);
            return this;
        }

        public final Builder setOnItemClickListener(OnItemClickListener itemClickListener) {
            Intrinsics.checkNotNullParameter(itemClickListener, "itemClickListener");
            this.popParams.setMWItemClickListener(itemClickListener);
            return this;
        }

        public final Builder setPopupOrientation(String orientation) {
            Intrinsics.checkNotNullParameter(orientation, "orientation");
            if (Intrinsics.areEqual(orientation, VERTICAL)) {
                this.popParams.setCommonPopupOrientation(1);
            } else if (Intrinsics.areEqual(orientation, HORIZONTAL)) {
                this.popParams.setCommonPopupOrientation(0);
            }
            return this;
        }

        public final Builder setDividerColor(int color) {
            this.popParams.setCommonPopupDividerColor(color);
            return this;
        }

        public final Builder setDividerSize(int size) {
            this.popParams.setCommonPopupDividerSize(size);
            return this;
        }

        public final Builder setDividerMargin(int margin) {
            this.popParams.setCommonPopupDividerMargin(margin);
            return this;
        }

        public final Builder setIsDim(boolean isDim) {
            this.popParams.setDim(isDim);
            return this;
        }

        public final Builder setDimValue(float dimValue) {
            this.popParams.setDimValue(dimValue);
            return this;
        }

        public final Builder setPopupBgColor(int color) {
            this.popParams.setCommonPopupBgColor(color);
            return this;
        }

        public final Builder setPopupMargin(int margin) {
            this.popParams.setCommonPopMargin(margin);
            return this;
        }

        public final Builder setClickView(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.popParams.setLongClickView(view);
            return this;
        }

        public final Builder setAnim(int anim) {
            this.popParams.setAnimRes(anim);
            return this;
        }

        public final Builder setIconDirection(int d) {
            this.popParams.setCommonIconDirection(d);
            return this;
        }

        public final Builder setCancelable(boolean cancelable) {
            this.popParams.setCancelable(cancelable);
            return this;
        }

        public final Builder setTextColor(int color) {
            this.popParams.setCommonItemTextColor(color);
            return this;
        }

        public final Builder setTextSize(int size) {
            this.popParams.setCommonItemTextSize(size);
            return this;
        }

        public final Builder setDrawablePadding(int padding) {
            this.popParams.setCommonDraablePadding(padding);
            return this;
        }

        public final WPopup create() {
            return new WPopup(this.popParams);
        }
    }
}
