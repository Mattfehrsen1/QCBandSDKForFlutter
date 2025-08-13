package com.qcwireless.qcwatch.base.ktx;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewExt.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\bø\u0001\u0000\u001aP\u0010\b\u001a\u00020\t*\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0014\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0015"}, d2 = {"doOnLayout", "", "Landroid/view/View;", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "rotate", "Landroid/animation/ObjectAnimator;", TypedValues.TransitionType.S_DURATION, "", "repeatCount", "", "clockwise", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "startDelay", "onAnimationEnd", "Lkotlin/Function0;", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ViewExtKt {
    public static final ObjectAnimator rotate(final View view, long j, int i, boolean z, final Lifecycle lifecycle, long j2, final Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.clearAnimation();
        if (view.isLaidOut() && !view.isLayoutRequested()) {
            view.setPivotX(view.getWidth() / 2.0f);
            view.setPivotY(view.getHeight() / 2.0f);
        } else {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.qcwireless.qcwatch.base.ktx.ViewExtKt$rotate$$inlined$doOnLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top2, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    v.removeOnLayoutChangeListener(this);
                    view.setPivotX(r1.getWidth() / 2.0f);
                    view.setPivotY(r1.getHeight() / 2.0f);
                }
            });
        }
        Property property = View.ROTATION;
        float[] fArr = new float[2];
        fArr[0] = z ? 0.0f : 360.0f;
        fArr[1] = z ? 360.0f : 0.0f;
        final ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        rotationAnimator.setDuration(j);
        rotationAnimator.setRepeatCount(i);
        rotationAnimator.setStartDelay(j2);
        rotationAnimator.setInterpolator(new LinearInterpolator());
        if (i != -1 && function0 != null) {
            rotationAnimator.addListener(new Animator.AnimatorListener() { // from class: com.qcwireless.qcwatch.base.ktx.ViewExtKt$rotate$rotationAnimator$1$1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    Intrinsics.checkNotNullParameter(animation, "animation");
                    function0.invoke();
                }
            });
        }
        if (lifecycle != null) {
            lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: com.qcwireless.qcwatch.base.ktx.ViewExtKt.rotate.2
                @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
                public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
                    DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
                public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
                    DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
                public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
                    DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
                public /* synthetic */ void onStart(LifecycleOwner lifecycleOwner) {
                    DefaultLifecycleObserver.CC.$default$onStart(this, lifecycleOwner);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
                public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
                    DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
                }

                @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
                public void onDestroy(LifecycleOwner owner) {
                    Intrinsics.checkNotNullParameter(owner, "owner");
                    rotationAnimator.cancel();
                    view.clearAnimation();
                    lifecycle.removeObserver(this);
                }
            });
        }
        rotationAnimator.start();
        Intrinsics.checkNotNullExpressionValue(rotationAnimator, "rotationAnimator");
        return rotationAnimator;
    }

    public static final void doOnLayout(View view, final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        if (view.isLaidOut() && !view.isLayoutRequested()) {
            action.invoke(view);
        } else {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.qcwireless.qcwatch.base.ktx.ViewExtKt.doOnLayout.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View v, int left, int top2, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    v.removeOnLayoutChangeListener(this);
                    action.invoke(v);
                }
            });
        }
    }
}
