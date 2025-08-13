package com.tbuonomo.viewpagerdotsindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WormDotsIndicator.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J\b\u0010$\u001a\u00020\u001bH\u0016J\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u0007J\u000e\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u0013J\u000e\u0010)\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\u0007J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010+\u001a\u00020\u000bH\u0002J\b\u0010,\u001a\u00020\u001bH\u0002R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006-"}, d2 = {"Lcom/tbuonomo/viewpagerdotsindicator/WormDotsIndicator;", "Lcom/tbuonomo/viewpagerdotsindicator/BaseDotsIndicator;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "dotIndicatorColor", "dotIndicatorLayout", "Landroid/view/View;", "dotIndicatorView", "Landroid/widget/ImageView;", "dotIndicatorWidthSpring", "Landroidx/dynamicanimation/animation/SpringAnimation;", "dotIndicatorXSpring", "dotsStrokeColor", "dotsStrokeWidth", "", "strokeDotsLinearLayout", "Landroid/widget/LinearLayout;", "type", "Lcom/tbuonomo/viewpagerdotsindicator/BaseDotsIndicator$Type;", "getType", "()Lcom/tbuonomo/viewpagerdotsindicator/BaseDotsIndicator$Type;", "addDot", "", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "buildDot", "Landroid/view/ViewGroup;", "stroke", "", "buildOnPageChangedListener", "Lcom/tbuonomo/viewpagerdotsindicator/OnPageChangeListenerHelper;", "refreshDotColor", "removeDot", "setDotIndicatorColor", TypedValues.Custom.S_COLOR, "setDotsStrokeWidth", "width", "setStrokeDotsIndicatorColor", "setUpDotBackground", "dotImageView", "setUpDotIndicator", "viewpagerdotsindicator_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WormDotsIndicator extends BaseDotsIndicator {
    private int dotIndicatorColor;
    private View dotIndicatorLayout;
    private ImageView dotIndicatorView;
    private SpringAnimation dotIndicatorWidthSpring;
    private SpringAnimation dotIndicatorXSpring;
    private int dotsStrokeColor;
    private float dotsStrokeWidth;
    private final LinearLayout strokeDotsLinearLayout;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WormDotsIndicator(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WormDotsIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ WormDotsIndicator(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WormDotsIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        LinearLayout linearLayout = new LinearLayout(context);
        this.strokeDotsLinearLayout = linearLayout;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int iDpToPx = dpToPx(24);
        setPadding(iDpToPx, 0, iDpToPx, 0);
        setClipToPadding(false);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        addView(linearLayout);
        this.dotsStrokeWidth = dpToPxF(2.0f);
        int themePrimaryColor = ExtensionsKt.getThemePrimaryColor(context);
        this.dotIndicatorColor = themePrimaryColor;
        this.dotsStrokeColor = themePrimaryColor;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.WormDotsIndicator);
            Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "getContext().obtainStyle…leable.WormDotsIndicator)");
            this.dotIndicatorColor = typedArrayObtainStyledAttributes.getColor(R.styleable.WormDotsIndicator_dotsColor, this.dotIndicatorColor);
            this.dotsStrokeColor = typedArrayObtainStyledAttributes.getColor(R.styleable.WormDotsIndicator_dotsStrokeColor, this.dotIndicatorColor);
            this.dotsStrokeWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.WormDotsIndicator_dotsStrokeWidth, this.dotsStrokeWidth);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (isInEditMode()) {
            addDots(5);
            addView(buildDot(false));
        }
        setUpDotIndicator();
    }

    private final void setUpDotIndicator() {
        BaseDotsIndicator.Pager pager = getPager();
        if (pager != null && pager.isEmpty()) {
            return;
        }
        ImageView imageView = this.dotIndicatorView;
        if (imageView != null && indexOfChild(imageView) != -1) {
            removeView(this.dotIndicatorView);
        }
        ViewGroup viewGroupBuildDot = buildDot(false);
        this.dotIndicatorLayout = viewGroupBuildDot;
        Intrinsics.checkNotNull(viewGroupBuildDot);
        this.dotIndicatorView = (ImageView) viewGroupBuildDot.findViewById(R.id.worm_dot);
        addView(this.dotIndicatorLayout);
        this.dotIndicatorXSpring = new SpringAnimation(this.dotIndicatorLayout, SpringAnimation.TRANSLATION_X);
        SpringForce springForce = new SpringForce(0.0f);
        springForce.setDampingRatio(1.0f);
        springForce.setStiffness(300.0f);
        SpringAnimation springAnimation = this.dotIndicatorXSpring;
        Intrinsics.checkNotNull(springAnimation);
        springAnimation.setSpring(springForce);
        this.dotIndicatorWidthSpring = new SpringAnimation(this.dotIndicatorLayout, new FloatPropertyCompat<View>() { // from class: com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator$setUpDotIndicator$floatPropertyCompat$1
            {
                super("DotsWidth");
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(View object) {
                Intrinsics.checkNotNullParameter(object, "object");
                Intrinsics.checkNotNull(this.this$0.dotIndicatorView);
                return r2.getLayoutParams().width;
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(View object, float value) {
                Intrinsics.checkNotNullParameter(object, "object");
                ImageView imageView2 = this.this$0.dotIndicatorView;
                Intrinsics.checkNotNull(imageView2);
                imageView2.getLayoutParams().width = (int) value;
                ImageView imageView3 = this.this$0.dotIndicatorView;
                Intrinsics.checkNotNull(imageView3);
                imageView3.requestLayout();
            }
        });
        SpringForce springForce2 = new SpringForce(0.0f);
        springForce2.setDampingRatio(1.0f);
        springForce2.setStiffness(300.0f);
        SpringAnimation springAnimation2 = this.dotIndicatorWidthSpring;
        Intrinsics.checkNotNull(springAnimation2);
        springAnimation2.setSpring(springForce2);
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public void addDot(final int index) {
        ViewGroup viewGroupBuildDot = buildDot(true);
        viewGroupBuildDot.setOnClickListener(new View.OnClickListener() { // from class: com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WormDotsIndicator.m1095addDot$lambda0(this.f$0, index, view);
            }
        });
        ArrayList<ImageView> arrayList = this.dots;
        View viewFindViewById = viewGroupBuildDot.findViewById(R.id.worm_dot);
        Objects.requireNonNull(viewFindViewById, "null cannot be cast to non-null type android.widget.ImageView");
        arrayList.add((ImageView) viewFindViewById);
        this.strokeDotsLinearLayout.addView(viewGroupBuildDot);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addDot$lambda-0, reason: not valid java name */
    public static final void m1095addDot$lambda0(WormDotsIndicator this$0, int i, View view) {
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

    private final ViewGroup buildDot(boolean stroke) {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.worm_dot_layout, (ViewGroup) this, false);
        Objects.requireNonNull(viewInflate, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup viewGroup = (ViewGroup) viewInflate;
        if (Build.VERSION.SDK_INT >= 17) {
            viewGroup.setLayoutDirection(0);
        }
        View dotImageView = viewGroup.findViewById(R.id.worm_dot);
        dotImageView.setBackgroundResource(stroke ? R.drawable.worm_dot_stroke_background : R.drawable.worm_dot_background);
        ViewGroup.LayoutParams layoutParams = dotImageView.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.height = (int) getDotsSize();
        layoutParams2.width = layoutParams2.height;
        layoutParams2.addRule(15, -1);
        layoutParams2.setMargins((int) getDotsSpacing(), 0, (int) getDotsSpacing(), 0);
        Intrinsics.checkNotNullExpressionValue(dotImageView, "dotImageView");
        setUpDotBackground(stroke, dotImageView);
        return viewGroup;
    }

    private final void setUpDotBackground(boolean stroke, View dotImageView) {
        Drawable background = dotImageView.getBackground();
        Objects.requireNonNull(background, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        GradientDrawable gradientDrawable = (GradientDrawable) background;
        if (stroke) {
            gradientDrawable.setStroke((int) this.dotsStrokeWidth, this.dotsStrokeColor);
        } else {
            gradientDrawable.setColor(this.dotIndicatorColor);
        }
        gradientDrawable.setCornerRadius(getDotsCornerRadius());
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public void refreshDotColor(int index) {
        ImageView imageView = this.dots.get(index);
        Intrinsics.checkNotNullExpressionValue(imageView, "dots[index]");
        setUpDotBackground(true, imageView);
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public void removeDot() {
        this.strokeDotsLinearLayout.removeViewAt(r0.getChildCount() - 1);
        this.dots.remove(this.dots.size() - 1);
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public OnPageChangeListenerHelper buildOnPageChangedListener() {
        return new OnPageChangeListenerHelper() { // from class: com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator.buildOnPageChangedListener.1
            @Override // com.tbuonomo.viewpagerdotsindicator.OnPageChangeListenerHelper
            public void resetPosition$viewpagerdotsindicator_release(int position) {
            }

            @Override // com.tbuonomo.viewpagerdotsindicator.OnPageChangeListenerHelper
            public int getPageCount$viewpagerdotsindicator_release() {
                return WormDotsIndicator.this.dots.size();
            }

            @Override // com.tbuonomo.viewpagerdotsindicator.OnPageChangeListenerHelper
            public void onPageScrolled$viewpagerdotsindicator_release(int selectedPosition, int nextPosition, float positionOffset) {
                float dotsSize;
                ViewParent parent = WormDotsIndicator.this.dots.get(selectedPosition).getParent();
                Objects.requireNonNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                float left = ((ViewGroup) parent).getLeft();
                ArrayList<ImageView> arrayList = WormDotsIndicator.this.dots;
                if (nextPosition != -1) {
                    selectedPosition = nextPosition;
                }
                ViewParent parent2 = arrayList.get(selectedPosition).getParent();
                Objects.requireNonNull(parent2, "null cannot be cast to non-null type android.view.ViewGroup");
                float left2 = ((ViewGroup) parent2).getLeft();
                if (0.0f <= positionOffset && positionOffset <= 0.1f) {
                    dotsSize = WormDotsIndicator.this.getDotsSize();
                } else {
                    if (0.1f <= positionOffset && positionOffset <= 0.9f) {
                        dotsSize = (left2 - left) + WormDotsIndicator.this.getDotsSize();
                    } else {
                        left = left2;
                        dotsSize = WormDotsIndicator.this.getDotsSize();
                    }
                }
                SpringAnimation springAnimation = WormDotsIndicator.this.dotIndicatorXSpring;
                if (springAnimation != null) {
                    springAnimation.animateToFinalPosition(left);
                }
                SpringAnimation springAnimation2 = WormDotsIndicator.this.dotIndicatorWidthSpring;
                if (springAnimation2 != null) {
                    springAnimation2.animateToFinalPosition(dotsSize);
                }
            }
        };
    }

    @Override // com.tbuonomo.viewpagerdotsindicator.BaseDotsIndicator
    public BaseDotsIndicator.Type getType() {
        return BaseDotsIndicator.Type.WORM;
    }

    public final void setDotIndicatorColor(int color) {
        ImageView imageView = this.dotIndicatorView;
        if (imageView != null) {
            this.dotIndicatorColor = color;
            Intrinsics.checkNotNull(imageView);
            setUpDotBackground(false, imageView);
        }
    }

    public final void setStrokeDotsIndicatorColor(int color) {
        this.dotsStrokeColor = color;
        Iterator<ImageView> it = this.dots.iterator();
        while (it.hasNext()) {
            ImageView v = it.next();
            Intrinsics.checkNotNullExpressionValue(v, "v");
            setUpDotBackground(true, v);
        }
    }

    public final void setDotsStrokeWidth(float width) {
        this.dotsStrokeWidth = width;
        Iterator<ImageView> it = this.dots.iterator();
        while (it.hasNext()) {
            ImageView v = it.next();
            Intrinsics.checkNotNullExpressionValue(v, "v");
            setUpDotBackground(true, v);
        }
    }
}
