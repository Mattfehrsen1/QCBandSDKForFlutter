package com.qcwireless.qcwatch.ui.base.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.qcwireless.qcwatch.ui.mine.login.LoginActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* compiled from: QSwitchButtonView.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002:\u0001WB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010=\u001a\u00020>H\u0016J\b\u0010?\u001a\u00020>H\u0002J\u0012\u0010@\u001a\u00020\u001e2\b\u0010A\u001a\u0004\u0018\u00010'H\u0002J\u0010\u0010B\u001a\u00020>2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u001a\u0010C\u001a\u00020>2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010D\u001a\u00020>2\u0006\u0010E\u001a\u00020FH\u0014J\u0018\u0010G\u001a\u00020>2\u0006\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\nH\u0014J\u0010\u0010J\u001a\u00020!2\u0006\u0010K\u001a\u00020LH\u0016J\u0016\u0010M\u001a\u00020>2\u0006\u0010N\u001a\u00020\n2\u0006\u0010O\u001a\u00020!J\u0010\u0010P\u001a\u00020>2\b\u0010Q\u001a\u0004\u0018\u00010$J\u001b\u0010R\u001a\u00020>2\u000e\u0010S\u001a\n\u0012\u0004\u0012\u000204\u0018\u000103¢\u0006\u0002\u0010TJ\b\u0010U\u001a\u00020>H\u0002J\b\u0010V\u001a\u00020>H\u0002R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u00102\u001a\n\u0012\u0004\u0012\u000204\u0018\u000103X\u0082\u000e¢\u0006\u0004\n\u0002\u00105R\u000e\u00106\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSwitchButtonView;", "Landroid/view/View;", "Lskin/support/widget/SkinCompatSupportable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "activity", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "getActivity", "()Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "setActivity", "(Lcom/qcwireless/qcwatch/ui/base/BaseActivity;)V", "mAnimation", "Landroid/animation/ValueAnimator;", "mColorStroke", "mColorStrokeBlank", "mColorText", "mColorTextCur", "mColorTextUnSelect", "mCount", "mCurIndex", "mDownIndex", "mDuration", "mFactor", "", "mHeight", "mIsDragging", "", "mLastIndex", "mOnTabSelectedListener", "Lcom/qcwireless/qcwatch/ui/base/view/QSwitchButtonView$OnTabSelectedListener;", "mPadding", "mPaintA", "Landroid/graphics/Paint;", "mPaintB", "mPaintTitle", "mPaintTitleCur", "mRect", "Landroid/graphics/Rect;", "mRectF", "Landroid/graphics/RectF;", "mRectRadius", "mReservedPadding", "mTextSize", "mTitles", "", "", "[Ljava/lang/String;", "mTouchSlop", "mTouchX", "mTouchY", "mWidth", "mWidthB", "textColorCurrId", "textColorId", "applySkin", "", "applyTextColor", "getTextHeight", "p", "init", "initTypedArray", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onTouchEvent", "ev", "Landroid/view/MotionEvent;", "select", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "withAnim", "setOnTabSelectedListener", "l", "setTitle", "title", "([Ljava/lang/String;)V", "startAnim", "stopAnim", "OnTabSelectedListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QSwitchButtonView extends View implements SkinCompatSupportable {
    private BaseActivity activity;
    private ValueAnimator mAnimation;
    private int mColorStroke;
    private int mColorStrokeBlank;
    private int mColorText;
    private int mColorTextCur;
    private int mColorTextUnSelect;
    private int mCount;
    private int mCurIndex;
    private int mDownIndex;
    private int mDuration;
    private float mFactor;
    private int mHeight;
    private boolean mIsDragging;
    private int mLastIndex;
    private OnTabSelectedListener mOnTabSelectedListener;
    private float mPadding;
    private Paint mPaintA;
    private Paint mPaintB;
    private Paint mPaintTitle;
    private Paint mPaintTitleCur;
    private Rect mRect;
    private RectF mRectF;
    private float mRectRadius;
    private float mReservedPadding;
    private int mTextSize;
    private String[] mTitles;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private int mWidth;
    private float mWidthB;
    private int textColorCurrId;
    private int textColorId;

    /* compiled from: QSwitchButtonView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/QSwitchButtonView$OnTabSelectedListener;", "", "onTabSelected", "", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnTabSelectedListener {
        void onTabSelected(int index);
    }

    public final BaseActivity getActivity() {
        return this.activity;
    }

    public final void setActivity(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSwitchButtonView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initTypedArray(context, null);
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSwitchButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        initTypedArray(context, attrs);
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSwitchButtonView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        initTypedArray(context, attrs);
        init(context);
    }

    private final void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.QWitchButton);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…R.styleable.QWitchButton)");
        this.mTextSize = (int) typedArrayObtainStyledAttributes.getDimension(6, TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics()));
        this.mColorText = typedArrayObtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
        this.textColorId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        this.mColorTextUnSelect = typedArrayObtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
        this.mColorStroke = this.mColorText;
        this.mColorTextCur = typedArrayObtainStyledAttributes.getColor(1, -1);
        this.textColorCurrId = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        this.mColorStrokeBlank = this.mColorTextCur;
        this.mPadding = typedArrayObtainStyledAttributes.getDimension(3, 0.0f);
        this.mReservedPadding = typedArrayObtainStyledAttributes.getDimension(4, -1.0f);
        this.mDuration = typedArrayObtainStyledAttributes.getInteger(2, 250);
        typedArrayObtainStyledAttributes.recycle();
    }

    private final void init(Context context) {
        int length;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        String[] strArr = this.mTitles;
        if (strArr != null) {
            Intrinsics.checkNotNull(strArr);
            length = strArr.length;
        } else {
            length = 0;
        }
        this.mCount = length;
        this.mRect = new Rect();
        this.mRectF = new RectF();
        Paint paint = new Paint(1);
        this.mPaintA = paint;
        Intrinsics.checkNotNull(paint);
        paint.setColor(this.mColorStroke);
        Paint paint2 = new Paint(1);
        this.mPaintB = paint2;
        Intrinsics.checkNotNull(paint2);
        paint2.setColor(this.mColorStrokeBlank);
        Paint paint3 = new Paint(1);
        this.mPaintTitle = paint3;
        Intrinsics.checkNotNull(paint3);
        paint3.setTextSize(this.mTextSize);
        Paint paint4 = this.mPaintTitle;
        Intrinsics.checkNotNull(paint4);
        paint4.setTextAlign(Paint.Align.CENTER);
        Paint paint5 = new Paint(1);
        this.mPaintTitleCur = paint5;
        Intrinsics.checkNotNull(paint5);
        paint5.setTextSize(this.mTextSize);
        Paint paint6 = this.mPaintTitleCur;
        Intrinsics.checkNotNull(paint6);
        paint6.setTextAlign(Paint.Align.CENTER);
        Paint paint7 = this.mPaintTitle;
        Intrinsics.checkNotNull(paint7);
        paint7.setColor(this.mColorText);
        Paint paint8 = this.mPaintTitleCur;
        Intrinsics.checkNotNull(paint8);
        paint8.setColor(this.mColorTextCur);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mAnimation = valueAnimatorOfFloat;
        Intrinsics.checkNotNull(valueAnimatorOfFloat);
        valueAnimatorOfFloat.setDuration(this.mDuration);
        ValueAnimator valueAnimator = this.mAnimation;
        Intrinsics.checkNotNull(valueAnimator);
        valueAnimator.setInterpolator(new LinearInterpolator());
        ValueAnimator valueAnimator2 = this.mAnimation;
        Intrinsics.checkNotNull(valueAnimator2);
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                QSwitchButtonView.m327init$lambda0(this.f$0, valueAnimator3);
            }
        });
        ValueAnimator valueAnimator3 = this.mAnimation;
        Intrinsics.checkNotNull(valueAnimator3);
        valueAnimator3.addListener(new Animator.AnimatorListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QSwitchButtonView.init.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                Intrinsics.checkNotNullParameter(animator, "animator");
                if (!(QSwitchButtonView.this.mFactor == 1.0f) || QSwitchButtonView.this.mOnTabSelectedListener == null) {
                    return;
                }
                OnTabSelectedListener onTabSelectedListener = QSwitchButtonView.this.mOnTabSelectedListener;
                Intrinsics.checkNotNull(onTabSelectedListener);
                onTabSelectedListener.onTabSelected(QSwitchButtonView.this.mCurIndex);
            }
        });
        applyTextColor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: init$lambda-0, reason: not valid java name */
    public static final void m327init$lambda0(QSwitchButtonView this$0, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animation, "animation");
        Object animatedValue = animation.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        this$0.mFactor = ((Float) animatedValue).floatValue();
        this$0.invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        if (this.mCount <= 0) {
            return;
        }
        Rect rect = this.mRect;
        Intrinsics.checkNotNull(rect);
        rect.set(0, 0, this.mWidth, this.mHeight);
        RectF rectF = this.mRectF;
        Intrinsics.checkNotNull(rectF);
        Rect rect2 = this.mRect;
        Intrinsics.checkNotNull(rect2);
        rectF.set(rect2);
        Rect rect3 = this.mRect;
        Intrinsics.checkNotNull(rect3);
        float f = this.mPadding;
        rect3.set((int) f, (int) f, (int) (this.mWidth - f), (int) (this.mHeight - f));
        RectF rectF2 = this.mRectF;
        Intrinsics.checkNotNull(rectF2);
        Rect rect4 = this.mRect;
        Intrinsics.checkNotNull(rect4);
        rectF2.set(rect4);
        RectF rectF3 = this.mRectF;
        Intrinsics.checkNotNull(rectF3);
        float f2 = this.mRectRadius;
        Paint paint = this.mPaintB;
        Intrinsics.checkNotNull(paint);
        canvas.drawRoundRect(rectF3, f2, f2, paint);
        float f3 = this.mReservedPadding;
        float f4 = this.mWidthB;
        float f5 = (this.mLastIndex * f4) + f3;
        float f6 = f5 + (((f3 + (f4 * this.mCurIndex)) - f5) * this.mFactor);
        Rect rect5 = this.mRect;
        Intrinsics.checkNotNull(rect5);
        float f7 = this.mReservedPadding;
        rect5.set((int) (f6 - f7), 0, (int) (this.mWidthB + f6 + f7), this.mHeight);
        RectF rectF4 = this.mRectF;
        Intrinsics.checkNotNull(rectF4);
        Rect rect6 = this.mRect;
        Intrinsics.checkNotNull(rect6);
        rectF4.set(rect6);
        RectF rectF5 = this.mRectF;
        Intrinsics.checkNotNull(rectF5);
        float f8 = this.mRectRadius;
        Paint paint2 = this.mPaintA;
        Intrinsics.checkNotNull(paint2);
        canvas.drawRoundRect(rectF5, f8, f8, paint2);
        int textHeight = (this.mHeight + ((int) getTextHeight(this.mPaintTitle))) / 2;
        int i = this.mCount;
        for (int i2 = 0; i2 < i; i2++) {
            float f9 = this.mReservedPadding;
            float f10 = this.mWidthB;
            float f11 = 2;
            float f12 = (i2 * f10) + f9 + (f10 / f11);
            float f13 = ((f10 / f11) + f6) - f9;
            if (f13 < 0.0f) {
                f13 = 0.0f;
            }
            int i3 = (int) (f13 / f10);
            if (i3 == i2 && (i3 == this.mCurIndex || i3 == this.mLastIndex)) {
                String[] strArr = this.mTitles;
                Intrinsics.checkNotNull(strArr);
                Paint paint3 = this.mPaintTitleCur;
                Intrinsics.checkNotNull(paint3);
                canvas.drawText(strArr[i2], f12, textHeight, paint3);
            } else {
                Paint paint4 = this.mPaintTitle;
                if (paint4 != null) {
                    paint4.setColor(this.mColorTextUnSelect);
                }
                String[] strArr2 = this.mTitles;
                Intrinsics.checkNotNull(strArr2);
                Paint paint5 = this.mPaintTitle;
                Intrinsics.checkNotNull(paint5);
                canvas.drawText(strArr2[i2], f12, textHeight, paint5);
            }
        }
    }

    private final void applyTextColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.textColorId);
        if (iCheckResourceId != 0) {
            int color = SkinCompatResources.getColor(getContext(), iCheckResourceId);
            Paint paint = this.mPaintTitle;
            Intrinsics.checkNotNull(paint);
            paint.setColor(color);
            Paint paint2 = this.mPaintA;
            Intrinsics.checkNotNull(paint2);
            paint2.setColor(color);
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.textColorCurrId);
        if (iCheckResourceId2 != 0) {
            int color2 = SkinCompatResources.getColor(getContext(), iCheckResourceId2);
            Paint paint3 = this.mPaintTitleCur;
            Intrinsics.checkNotNull(paint3);
            paint3.setColor(color2);
            Paint paint4 = this.mPaintB;
            Intrinsics.checkNotNull(paint4);
            paint4.setColor(color2);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        this.mHeight = size;
        float f = 2;
        float f2 = (size + 0.5f) / f;
        this.mRectRadius = f2;
        float f3 = this.mReservedPadding;
        if (f3 == -1.0f) {
            f3 = 0.85f * f2;
        }
        this.mReservedPadding = f3;
        int i = this.mWidth;
        this.mWidthB = (i - (f3 * f)) / (this.mCount > 0 ? r0 : 1);
        setMeasuredDimension(i, size);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        String string;
        Intrinsics.checkNotNullParameter(ev, "ev");
        if (this.mCount <= 0) {
            return false;
        }
        float f = this.mFactor;
        if (!(f == 0.0f)) {
            if (!(f == 1.0f)) {
                return false;
            }
        }
        float x = ev.getX();
        float y = ev.getY();
        int action = ev.getAction();
        if (action == 0) {
            this.mTouchX = x;
            this.mTouchY = y;
            int i = (int) ((x - this.mReservedPadding) / this.mWidthB);
            this.mDownIndex = i;
            if (i > 0) {
                try {
                    if (!UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                        this.mDownIndex = 0;
                        BaseActivity baseActivity = this.activity;
                        if (baseActivity != null && (string = baseActivity.getString(R.string.qc_text_245)) != null) {
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                        BaseActivity baseActivity2 = this.activity;
                        if (baseActivity2 != null) {
                            BaseActivity baseActivity3 = baseActivity2;
                            ArrayList<Pair> arrayList = new ArrayList();
                            Intent intent = new Intent(baseActivity3, (Class<?>) LoginActivity.class);
                            for (Pair pair : arrayList) {
                                if (pair != null) {
                                    String str = (String) pair.getFirst();
                                    Object second = pair.getSecond();
                                    if (second instanceof Integer) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                                    } else if (second instanceof Byte) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                                    } else if (second instanceof Character) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                                    } else if (second instanceof Short) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                                    } else if (second instanceof Boolean) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                    } else if (second instanceof Long) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                                    } else if (second instanceof Float) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                                    } else if (second instanceof Double) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                                    } else if (second instanceof String) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                                    } else if (second instanceof CharSequence) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                                    } else if (second instanceof Parcelable) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                    } else if (second instanceof Object[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                    } else if (second instanceof ArrayList) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                    } else if (second instanceof Serializable) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                    } else if (second instanceof boolean[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                                    } else if (second instanceof byte[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                                    } else if (second instanceof short[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                                    } else if (second instanceof char[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                                    } else if (second instanceof int[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                                    } else if (second instanceof long[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                                    } else if (second instanceof float[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                                    } else if (second instanceof double[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                                    } else if (second instanceof Bundle) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                                    } else if (second instanceof Intent) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                    } else {
                                        Unit unit = Unit.INSTANCE;
                                    }
                                }
                            }
                            baseActivity3.startActivity(intent);
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int iCoerceAtLeast = RangesKt.coerceAtLeast(this.mDownIndex, 0);
            this.mDownIndex = iCoerceAtLeast;
            int iCoerceAtMost = RangesKt.coerceAtMost(iCoerceAtLeast, this.mCount - 1);
            this.mDownIndex = iCoerceAtMost;
            this.mIsDragging = false;
            return iCoerceAtMost != this.mCurIndex;
        }
        if (action != 1) {
            if (action == 2) {
                if (!this.mIsDragging && (Math.abs(x - this.mTouchX) > this.mTouchSlop || Math.abs(y - this.mTouchY) > this.mTouchSlop)) {
                    this.mIsDragging = true;
                }
                return !this.mIsDragging;
            }
            if (action != 3) {
                return super.onTouchEvent(ev);
            }
        }
        if (!this.mIsDragging && x >= 0.0f && x <= this.mWidth && y >= 0.0f && y <= this.mHeight) {
            int iCoerceAtMost2 = RangesKt.coerceAtMost(RangesKt.coerceAtLeast((int) ((x - this.mReservedPadding) / this.mWidthB), 0), this.mCount - 1);
            int i2 = this.mDownIndex;
            if (iCoerceAtMost2 == i2) {
                this.mLastIndex = this.mCurIndex;
                this.mCurIndex = i2;
                startAnim();
                return true;
            }
        }
        return false;
    }

    private final float getTextHeight(Paint p) {
        Intrinsics.checkNotNull(p);
        Paint.FontMetrics fontMetrics = p.getFontMetrics();
        double d = 2;
        return (float) ((Math.ceil(fontMetrics.descent - fontMetrics.top) + d) / d);
    }

    private final void startAnim() {
        stopAnim();
        ValueAnimator valueAnimator = this.mAnimation;
        if (valueAnimator != null) {
            Intrinsics.checkNotNull(valueAnimator);
            valueAnimator.start();
        }
    }

    private final void stopAnim() {
        ValueAnimator valueAnimator = this.mAnimation;
        if (valueAnimator != null) {
            Intrinsics.checkNotNull(valueAnimator);
            valueAnimator.cancel();
        }
    }

    public final void setTitle(String[] title) {
        if (title != null) {
            if (title.length == 0) {
                return;
            }
            this.mTitles = title;
            Intrinsics.checkNotNull(title);
            int length = title.length;
            this.mCount = length;
            this.mWidthB = (this.mWidth - (this.mReservedPadding * 2)) / (length > 0 ? length : 1);
            postInvalidate();
        }
    }

    public final void select(int index, boolean withAnim) {
        this.mLastIndex = this.mCurIndex;
        this.mCurIndex = index;
        if (withAnim) {
            startAnim();
        } else {
            this.mFactor = 1.0f;
            invalidate();
        }
    }

    public final void setOnTabSelectedListener(OnTabSelectedListener l) {
        this.mOnTabSelectedListener = l;
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyTextColor();
    }
}
