package com.qcwireless.qcwatch.ui.base.view.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

/* loaded from: classes3.dex */
public class ChartAnimator {
    private ValueAnimator.AnimatorUpdateListener mListener;
    protected float mPhaseY = 1.0f;
    protected float mPhaseX = 1.0f;

    public ChartAnimator(ValueAnimator.AnimatorUpdateListener listener) {
        this.mListener = listener;
    }

    public void animateY(int durationMillis) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "phaseY", 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(durationMillis);
        objectAnimatorOfFloat.addUpdateListener(this.mListener);
        objectAnimatorOfFloat.start();
    }

    public void animateX(int durationMillis) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "phaseX", 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(durationMillis);
        objectAnimatorOfFloat.addUpdateListener(this.mListener);
        objectAnimatorOfFloat.start();
    }

    public float getPhaseY() {
        return this.mPhaseY;
    }

    public void setPhaseY(float phase) {
        this.mPhaseY = phase;
    }

    public void setPhaseX(float phase) {
        this.mPhaseX = phase;
    }

    public float getPhaseX() {
        return this.mPhaseX;
    }
}
