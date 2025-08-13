package com.qcwireless.qcwatch.ui.base.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.Key;
import com.nineoldandroids.view.ViewHelper;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.ShapeLoadingView;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class LoadingView extends LinearLayout {
    private static final int ANIMATION_DURATION = 500;
    private static final float FACTOR = 1.2f;
    private static float mDistance = 200.0f;
    private int mDelay;
    private AnimatorSet mDownAnimatorSet;
    private Runnable mFreeFallRunnable;
    private ImageView mIndicationIm;
    private String mLoadText;
    private TextView mLoadTextView;
    private ShapeLoadingView mShapeLoadingView;
    private boolean mStopped;
    private int mTextAppearance;
    private AnimatorSet mUpAnimatorSet;

    public LoadingView(Context context) {
        super(context);
        this.mStopped = false;
        this.mFreeFallRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.view.LoadingView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewHelper.setRotation(LoadingView.this.mShapeLoadingView, 180.0f);
                ViewHelper.setTranslationY(LoadingView.this.mShapeLoadingView, 0.0f);
                ViewHelper.setScaleX(LoadingView.this.mIndicationIm, 0.2f);
                LoadingView.this.mStopped = false;
                LoadingView.this.freeFall();
            }
        };
        init(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStopped = false;
        this.mFreeFallRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.view.LoadingView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewHelper.setRotation(LoadingView.this.mShapeLoadingView, 180.0f);
                ViewHelper.setTranslationY(LoadingView.this.mShapeLoadingView, 0.0f);
                ViewHelper.setScaleX(LoadingView.this.mIndicationIm, 0.2f);
                LoadingView.this.mStopped = false;
                LoadingView.this.freeFall();
            }
        };
        init(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mStopped = false;
        this.mFreeFallRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.view.LoadingView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewHelper.setRotation(LoadingView.this.mShapeLoadingView, 180.0f);
                ViewHelper.setTranslationY(LoadingView.this.mShapeLoadingView, 0.0f);
                ViewHelper.setScaleX(LoadingView.this.mIndicationIm, 0.2f);
                LoadingView.this.mStopped = false;
                LoadingView.this.freeFall();
            }
        };
        init(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mStopped = false;
        this.mFreeFallRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.base.view.LoadingView.1
            @Override // java.lang.Runnable
            public void run() {
                ViewHelper.setRotation(LoadingView.this.mShapeLoadingView, 180.0f);
                ViewHelper.setTranslationY(LoadingView.this.mShapeLoadingView, 0.0f);
                ViewHelper.setScaleX(LoadingView.this.mIndicationIm, 0.2f);
                LoadingView.this.mStopped = false;
                LoadingView.this.freeFall();
            }
        };
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(1);
        mDistance = dip2px(context, 54.0f);
        LayoutInflater.from(context).inflate(R.layout.load_view, (ViewGroup) this, true);
        this.mShapeLoadingView = (ShapeLoadingView) findViewById(R.id.shapeLoadingView);
        this.mIndicationIm = (ImageView) findViewById(R.id.indication);
        this.mLoadTextView = (TextView) findViewById(R.id.promptTV);
        ViewHelper.setScaleX(this.mIndicationIm, 0.2f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        String string = typedArrayObtainStyledAttributes.getString(1);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        this.mDelay = typedArrayObtainStyledAttributes.getInteger(0, 80);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != -1) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mLoadTextView.setTextAppearance(resourceId);
            } else {
                this.mLoadTextView.setTextAppearance(getContext(), resourceId);
            }
        }
        setLoadingText(string);
    }

    private int dip2px(Context context, float dipValue) {
        return (int) ((dipValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getVisibility() == 0) {
            startLoading(this.mDelay);
        }
    }

    private void startLoading(long delay) {
        AnimatorSet animatorSet = this.mDownAnimatorSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            removeCallbacks(this.mFreeFallRunnable);
            if (delay > 0) {
                postDelayed(this.mFreeFallRunnable, delay);
            } else {
                post(this.mFreeFallRunnable);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoading();
    }

    private void stopLoading() {
        this.mStopped = true;
        AnimatorSet animatorSet = this.mUpAnimatorSet;
        if (animatorSet != null) {
            if (animatorSet.isRunning()) {
                this.mUpAnimatorSet.cancel();
            }
            this.mUpAnimatorSet.removeAllListeners();
            Iterator<Animator> it = this.mUpAnimatorSet.getChildAnimations().iterator();
            while (it.hasNext()) {
                it.next().removeAllListeners();
            }
            this.mUpAnimatorSet = null;
        }
        AnimatorSet animatorSet2 = this.mDownAnimatorSet;
        if (animatorSet2 != null) {
            if (animatorSet2.isRunning()) {
                this.mDownAnimatorSet.cancel();
            }
            this.mDownAnimatorSet.removeAllListeners();
            Iterator<Animator> it2 = this.mDownAnimatorSet.getChildAnimations().iterator();
            while (it2.hasNext()) {
                it2.next().removeAllListeners();
            }
            this.mDownAnimatorSet = null;
        }
        removeCallbacks(this.mFreeFallRunnable);
    }

    @Override // android.view.View
    public void setVisibility(int visibility) {
        setVisibility(visibility, this.mDelay);
    }

    public void setVisibility(int visibility, int delay) {
        super.setVisibility(visibility);
        if (visibility == 0) {
            startLoading(delay);
        } else {
            stopLoading();
        }
    }

    public void setDelay(int delay) {
        this.mDelay = delay;
    }

    public int getDelay() {
        return this.mDelay;
    }

    public void setLoadingText(CharSequence loadingText) {
        if (TextUtils.isEmpty(loadingText)) {
            this.mLoadTextView.setVisibility(8);
        } else {
            this.mLoadTextView.setVisibility(0);
        }
        this.mLoadTextView.setText(loadingText);
    }

    public CharSequence getLoadingText() {
        return this.mLoadTextView.getText();
    }

    public void upThrow() {
        if (this.mUpAnimatorSet == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mShapeLoadingView, "translationY", mDistance, 0.0f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mIndicationIm, "scaleX", 1.0f, 0.2f);
            ObjectAnimator objectAnimatorOfFloat3 = null;
            int i = AnonymousClass4.$SwitchMap$com$qcwireless$qcwatch$ui$base$view$ShapeLoadingView$Shape[this.mShapeLoadingView.getShape().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.mShapeLoadingView, Key.ROTATION, 0.0f, 180.0f);
            }
            AnimatorSet animatorSet = new AnimatorSet();
            this.mUpAnimatorSet = animatorSet;
            animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat3, objectAnimatorOfFloat2);
            this.mUpAnimatorSet.setDuration(500L);
            this.mUpAnimatorSet.setInterpolator(new DecelerateInterpolator(FACTOR));
            this.mUpAnimatorSet.addListener(new Animator.AnimatorListener() { // from class: com.qcwireless.qcwatch.ui.base.view.LoadingView.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    if (LoadingView.this.mStopped) {
                        return;
                    }
                    LoadingView.this.freeFall();
                }
            });
        }
        this.mUpAnimatorSet.start();
    }

    /* renamed from: com.qcwireless.qcwatch.ui.base.view.LoadingView$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$qcwireless$qcwatch$ui$base$view$ShapeLoadingView$Shape;

        static {
            int[] iArr = new int[ShapeLoadingView.Shape.values().length];
            $SwitchMap$com$qcwireless$qcwatch$ui$base$view$ShapeLoadingView$Shape = iArr;
            try {
                iArr[ShapeLoadingView.Shape.SHAPE_RECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$view$ShapeLoadingView$Shape[ShapeLoadingView.Shape.SHAPE_CIRCLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$view$ShapeLoadingView$Shape[ShapeLoadingView.Shape.SHAPE_TRIANGLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public void freeFall() {
        if (this.mDownAnimatorSet == null) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mShapeLoadingView, "translationY", 0.0f, mDistance);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.mIndicationIm, "scaleX", 0.2f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            this.mDownAnimatorSet = animatorSet;
            animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
            this.mDownAnimatorSet.setDuration(500L);
            this.mDownAnimatorSet.setInterpolator(new AccelerateInterpolator(FACTOR));
            this.mDownAnimatorSet.addListener(new Animator.AnimatorListener() { // from class: com.qcwireless.qcwatch.ui.base.view.LoadingView.3
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    if (LoadingView.this.mStopped) {
                        return;
                    }
                    LoadingView.this.mShapeLoadingView.changeShape();
                    LoadingView.this.upThrow();
                }
            });
        }
        this.mDownAnimatorSet.start();
    }
}
