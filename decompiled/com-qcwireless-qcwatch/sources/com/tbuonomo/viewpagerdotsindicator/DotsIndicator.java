package com.tbuonomo.viewpagerdotsindicator;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator;
import java.util.Objects;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DotsIndicator.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u00020\u001d2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010#\u001a\u00020\u001dH\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u0007H\u0007R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lcom/tbuonomo/viewpagerdotsindicator/DotsIndicator;", "Lcom/tbuonomo/viewpagerdotsindicator/BaseDotsIndicator;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "argbEvaluator", "Landroid/animation/ArgbEvaluator;", "dotsElevation", "", "dotsWidthFactor", "linearLayout", "Landroid/widget/LinearLayout;", "progressMode", "", "value", "selectedDotColor", "getSelectedDotColor", "()I", "setSelectedDotColor", "(I)V", "type", "Lcom/tbuonomo/viewpagerdotsindicator/BaseDotsIndicator$Type;", "getType", "()Lcom/tbuonomo/viewpagerdotsindicator/BaseDotsIndicator$Type;", "addDot", "", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "buildOnPageChangedListener", "Lcom/tbuonomo/viewpagerdotsindicator/OnPageChangeListenerHelper;", "init", "refreshDotColor", "removeDot", "setSelectedPointColor", TypedValues.Custom.S_COLOR, "Companion", "viewpagerdotsindicator_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class DotsIndicator extends BaseDotsIndicator {
    public static final float DEFAULT_WIDTH_FACTOR = 2.5f;
    private final ArgbEvaluator argbEvaluator;
    private float dotsElevation;
    private float dotsWidthFactor;
    private LinearLayout linearLayout;
    private boolean progressMode;
    private int selectedDotColor;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DotsIndicator(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DotsIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DotsIndicator(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DotsIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.argbEvaluator = new ArgbEvaluator();
        init(attributeSet);
    }

    public final int getSelectedDotColor() {
        return this.selectedDotColor;
    }

    public final void setSelectedDotColor(int i) {
        this.selectedDotColor = i;
        refreshDotsColors();
    }

    private final void init(AttributeSet attrs) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        this.linearLayout = linearLayout;
        linearLayout.setOrientation(0);
        LinearLayout linearLayout2 = this.linearLayout;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayout");
            linearLayout2 = null;
        }
        addView(linearLayout2, -2, -2);
        this.dotsWidthFactor = 2.5f;
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.DotsIndicator);
            Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr….styleable.DotsIndicator)");
            setSelectedDotColor(typedArrayObtainStyledAttributes.getColor(R.styleable.DotsIndicator_selectedDotColor, BaseDotsIndicator.DEFAULT_POINT_COLOR));
            float f = typedArrayObtainStyledAttributes.getFloat(R.styleable.DotsIndicator_dotsWidthFactor, 2.5f);
            this.dotsWidthFactor = f;
            if (f < 1.0f) {
                Log.w("DotsIndicator", "The dotsWidthFactor can't be set under 1.0f, please set an higher value");
                this.dotsWidthFactor = 1.0f;
            }
            this.progressMode = typedArrayObtainStyledAttributes.getBoolean(R.styleable.DotsIndicator_progressMode, false);
            this.dotsElevation = typedArrayObtainStyledAttributes.getDimension(R.styleable.DotsIndicator_dotsElevation, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (isInEditMode()) {
            addDots(5);
            refreshDots();
        }
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public void addDot(final int index) {
        View dot = LayoutInflater.from(getContext()).inflate(R.layout.dot_layout, (ViewGroup) this, false);
        ImageView imageView = (ImageView) dot.findViewById(R.id.dot);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        if (Build.VERSION.SDK_INT >= 17) {
            dot.setLayoutDirection(0);
        }
        layoutParams2.height = (int) getDotsSize();
        layoutParams2.width = layoutParams2.height;
        layoutParams2.setMargins((int) getDotsSpacing(), 0, (int) getDotsSpacing(), 0);
        DotsGradientDrawable dotsGradientDrawable = new DotsGradientDrawable();
        dotsGradientDrawable.setCornerRadius(getDotsCornerRadius());
        if (isInEditMode()) {
            dotsGradientDrawable.setColor(index == 0 ? this.selectedDotColor : getDotsColor());
        } else {
            BaseDotsIndicator.Pager pager = getPager();
            Intrinsics.checkNotNull(pager);
            dotsGradientDrawable.setColor(pager.getCurrentItem() == index ? this.selectedDotColor : getDotsColor());
        }
        Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
        ExtensionsKt.setBackgroundCompat(imageView, dotsGradientDrawable);
        dot.setOnClickListener(new View.OnClickListener() { // from class: com.tbuonomo.viewpagerdotsindicator.DotsIndicator$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DotsIndicator.m1092addDot$lambda0(this.f$0, index, view);
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Intrinsics.checkNotNullExpressionValue(dot, "dot");
            ExtensionsKt.setPaddingHorizontal(dot, (int) (this.dotsElevation * 0.8f));
            ExtensionsKt.setPaddingVertical(dot, (int) (this.dotsElevation * 2));
            imageView.setElevation(this.dotsElevation);
        }
        this.dots.add(imageView);
        LinearLayout linearLayout = this.linearLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayout");
            linearLayout = null;
        }
        linearLayout.addView(dot);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addDot$lambda-0, reason: not valid java name */
    public static final void m1092addDot$lambda0(DotsIndicator this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getDotsClickable()) {
            BaseDotsIndicator.Pager pager = this$0.getPager();
            if (i < (pager != null ? pager.getCount() : 0)) {
                BaseDotsIndicator.Pager pager2 = this$0.getPager();
                Intrinsics.checkNotNull(pager2);
                pager2.setCurrentItem(i, true);
            }
        }
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public void removeDot() {
        LinearLayout linearLayout = this.linearLayout;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayout");
            linearLayout = null;
        }
        LinearLayout linearLayout3 = this.linearLayout;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayout");
        } else {
            linearLayout2 = linearLayout3;
        }
        linearLayout.removeViewAt(linearLayout2.getChildCount() - 1);
        this.dots.remove(this.dots.size() - 1);
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public OnPageChangeListenerHelper buildOnPageChangedListener() {
        return new OnPageChangeListenerHelper() { // from class: com.tbuonomo.viewpagerdotsindicator.DotsIndicator.buildOnPageChangedListener.1
            /* JADX WARN: Removed duplicated region for block: B:11:0x0104  */
            @Override // com.tbuonomo.viewpagerdotsindicator.OnPageChangeListenerHelper
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onPageScrolled$viewpagerdotsindicator_release(int r7, int r8, float r9) {
                /*
                    Method dump skipped, instructions count: 269
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tbuonomo.viewpagerdotsindicator.DotsIndicator.AnonymousClass1.onPageScrolled$viewpagerdotsindicator_release(int, int, float):void");
            }

            @Override // com.tbuonomo.viewpagerdotsindicator.OnPageChangeListenerHelper
            public void resetPosition$viewpagerdotsindicator_release(int position) {
                ImageView imageView = DotsIndicator.this.dots.get(position);
                Intrinsics.checkNotNullExpressionValue(imageView, "dots[position]");
                ExtensionsKt.setWidth(imageView, (int) DotsIndicator.this.getDotsSize());
                DotsIndicator.this.refreshDotColor(position);
            }

            @Override // com.tbuonomo.viewpagerdotsindicator.OnPageChangeListenerHelper
            public int getPageCount$viewpagerdotsindicator_release() {
                return DotsIndicator.this.dots.size();
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void refreshDotColor(int r4) {
        /*
            r3 = this;
            java.util.ArrayList<android.widget.ImageView> r0 = r3.dots
            java.lang.Object r0 = r0.get(r4)
            java.lang.String r1 = "dots[index]"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            android.graphics.drawable.Drawable r1 = r0.getBackground()
            boolean r2 = r1 instanceof com.tbuonomo.viewpagerdotsindicator.DotsGradientDrawable
            if (r2 == 0) goto L19
            com.tbuonomo.viewpagerdotsindicator.DotsGradientDrawable r1 = (com.tbuonomo.viewpagerdotsindicator.DotsGradientDrawable) r1
            goto L1a
        L19:
            r1 = 0
        L1a:
            if (r1 == 0) goto L48
            com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator$Pager r2 = r3.getPager()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r2 = r2.getCurrentItem()
            if (r4 == r2) goto L43
            boolean r2 = r3.progressMode
            if (r2 == 0) goto L3b
            com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator$Pager r2 = r3.getPager()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r2 = r2.getCurrentItem()
            if (r4 >= r2) goto L3b
            goto L43
        L3b:
            int r4 = r3.getDotsColor()
            r1.setColor(r4)
            goto L48
        L43:
            int r4 = r3.selectedDotColor
            r1.setColor(r4)
        L48:
            r4 = r0
            android.view.View r4 = (android.view.View) r4
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            com.tbuonomo.viewpagerdotsindicator.ExtensionsKt.setBackgroundCompat(r4, r1)
            r0.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tbuonomo.viewpagerdotsindicator.DotsIndicator.refreshDotColor(int):void");
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public BaseDotsIndicator.Type getType() {
        return BaseDotsIndicator.Type.DEFAULT;
    }

    @Deprecated(message = "Use setSelectedDotColor() instead", replaceWith = @ReplaceWith(expression = "setSelectedDotColor()", imports = {}))
    public final void setSelectedPointColor(int color) {
        setSelectedDotColor(color);
    }
}
