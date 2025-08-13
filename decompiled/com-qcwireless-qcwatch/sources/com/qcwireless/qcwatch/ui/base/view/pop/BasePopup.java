package com.qcwireless.qcwatch.ui.base.view.pop;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.PopupWindow;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BasePopup.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020!J\u001d\u0010\"\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$2\u0006\u0010%\u001a\u00020\r¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020$J\u0006\u0010(\u001a\u00020)J\b\u0010*\u001a\u00020\u001bH\u0002J\u000e\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020$J\u000e\u0010-\u001a\u00020\r2\u0006\u0010,\u001a\u00020$J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020$H\u0002J\u0006\u0010/\u001a\u00020\u001dJ\u001c\u00100\u001a\u00020\u00152\b\u00101\u001a\u0004\u0018\u00010$2\b\u00102\u001a\u0004\u0018\u000103H\u0017J\b\u00104\u001a\u00020!H\u0002J\u000e\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020\rJ\b\u00107\u001a\u00020!H\u0002J\u000e\u00108\u001a\u00020\u00002\u0006\u0010,\u001a\u00020$J\u000e\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u0013J\u000e\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0015J\u000e\u0010=\u001a\u00020!2\u0006\u0010,\u001a\u00020$J\u000e\u0010>\u001a\u00020!2\u0006\u0010?\u001a\u00020\rJ\u0018\u0010@\u001a\u00020!2\u0006\u0010,\u001a\u00020$2\u0006\u0010?\u001a\u00020\rH\u0016J\b\u0010A\u001a\u00020!H\u0016J\u0010\u0010A\u001a\u00020!2\u0006\u0010?\u001a\u00020\rH\u0016J\u0010\u0010B\u001a\u00020!2\u0006\u0010,\u001a\u00020$H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/BasePopup;", "Landroid/view/View$OnTouchListener;", "popParams", "Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;", "(Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;)V", "animDuration", "", "bgDimAnimator", "Landroid/animation/ValueAnimator;", "clearBgDimAnimator", "clickLocation", "", "defaultMargin", "", "getDefaultMargin", "()I", "setDefaultMargin", "(I)V", "dim", "", "isBgDim", "", "mPopup", "Landroid/widget/PopupWindow;", "getPopParams", "()Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopParams;", "popupContentViewSize", "", "window", "Landroid/view/Window;", "windowAttr", "Landroid/view/WindowManager$LayoutParams;", "dismiss", "", "findViewById", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "resId", "(I)Landroid/view/View;", "getContentView", "getContext", "Landroid/content/Context;", "getPopupContentViewSize", "getPopupShowLocation", "view", "getShowDirection", "getViewLocation", "getWindow", "onTouch", "v", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "resetDim", "setAnim", "anim", "setBgDim", "setContentView", "setDimValue", "dimValue", "setIsBgDim", TypedValues.Custom.S_BOOLEAN, "showAsDropDown", "showAtDirection", "direction", "showAtDirectionByView", "showAtFingerLocation", "showAtView", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BasePopup implements View.OnTouchListener {
    private long animDuration;
    private ValueAnimator bgDimAnimator;
    private ValueAnimator clearBgDimAnimator;
    private final float[] clickLocation;
    private int defaultMargin;
    private float dim;
    private boolean isBgDim;
    private final PopupWindow mPopup;
    private final WPopParams popParams;
    private int[] popupContentViewSize;
    private Window window;
    private WindowManager.LayoutParams windowAttr;

    public BasePopup(WPopParams popParams) {
        Intrinsics.checkNotNullParameter(popParams, "popParams");
        this.popParams = popParams;
        this.dim = 0.4f;
        Window window = popParams.getActivity().getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "popParams.activity.window");
        this.window = window;
        WindowManager.LayoutParams attributes = window.getAttributes();
        Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
        this.windowAttr = attributes;
        this.animDuration = 200L;
        this.clickLocation = new float[2];
        this.window.addFlags(2);
        PopupWindow popupWindow = new PopupWindow(popParams.getWidth(), popParams.getHeight());
        this.mPopup = popupWindow;
        popupWindow.setFocusable(true);
        popupWindow.setContentView(popParams.getActivity().getLayoutInflater().inflate(popParams.getLayoutRes(), (ViewGroup) null));
        this.dim = popParams.getDimValue();
        this.isBgDim = popParams.isDim();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup$$ExternalSyntheticLambda5
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                BasePopup.m329_init_$lambda0(this.f$0);
            }
        });
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, this.dim);
        Intrinsics.checkNotNullExpressionValue(valueAnimatorOfFloat, "ofFloat(1f, dim)");
        this.bgDimAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.animDuration);
        this.bgDimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BasePopup.m330_init_$lambda1(this.f$0, valueAnimator);
            }
        });
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(this.dim, 1.0f);
        Intrinsics.checkNotNullExpressionValue(valueAnimatorOfFloat2, "ofFloat(dim, 1f)");
        this.clearBgDimAnimator = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(this.animDuration);
        this.clearBgDimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BasePopup.m331_init_$lambda2(this.f$0, valueAnimator);
            }
        });
        if (popParams.getLongClickView() != null) {
            View longClickView = popParams.getLongClickView();
            Intrinsics.checkNotNull(longClickView);
            if (longClickView instanceof AbsListView) {
                View longClickView2 = popParams.getLongClickView();
                Intrinsics.checkNotNull(longClickView2);
                ((AbsListView) longClickView2).setOnTouchListener(new View.OnTouchListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return BasePopup.m332_init_$lambda3(this.f$0, view, motionEvent);
                    }
                });
            } else {
                View longClickView3 = popParams.getLongClickView();
                Intrinsics.checkNotNull(longClickView3);
                if (longClickView3 instanceof RecyclerView) {
                    View longClickView4 = popParams.getLongClickView();
                    Intrinsics.checkNotNull(longClickView4);
                    ((RecyclerView) longClickView4).addOnItemTouchListener(new RecyclerView.OnItemTouchListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup.5
                        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                        public void onRequestDisallowInterceptTouchEvent(boolean p0) {
                        }

                        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                        public void onTouchEvent(RecyclerView p0, MotionEvent p1) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            Intrinsics.checkNotNullParameter(p1, "p1");
                        }

                        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
                        public boolean onInterceptTouchEvent(RecyclerView p0, MotionEvent event) {
                            Intrinsics.checkNotNullParameter(p0, "p0");
                            Intrinsics.checkNotNullParameter(event, "event");
                            if (event.getAction() == 0) {
                                BasePopup.this.clickLocation[0] = event.getRawX();
                                BasePopup.this.clickLocation[1] = event.getRawY();
                            }
                            return false;
                        }
                    });
                } else {
                    View longClickView5 = popParams.getLongClickView();
                    Intrinsics.checkNotNull(longClickView5);
                    longClickView5.setOnTouchListener(this);
                }
            }
        }
        popupWindow.setAnimationStyle(popParams.getAnimRes());
        if (!popParams.getCancelable()) {
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setBackgroundDrawable(null);
            getContentView().setFocusable(true);
            getContentView().setFocusableInTouchMode(true);
            getContentView().setOnKeyListener(new View.OnKeyListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup$$ExternalSyntheticLambda2
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return BasePopup.m333_init_$lambda4(this.f$0, view, i, keyEvent);
                }
            });
            popupWindow.setTouchInterceptor(new View.OnTouchListener() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup$$ExternalSyntheticLambda3
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return BasePopup.m334_init_$lambda5(this.f$0, view, motionEvent);
                }
            });
        } else {
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        }
        this.defaultMargin = Utils.INSTANCE.dp2px(getContext(), 4);
    }

    public final WPopParams getPopParams() {
        return this.popParams;
    }

    public final int getDefaultMargin() {
        return this.defaultMargin;
    }

    public final void setDefaultMargin(int i) {
        this.defaultMargin = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m329_init_$lambda0(BasePopup this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m330_init_$lambda1(BasePopup this$0, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animation, "animation");
        WindowManager.LayoutParams layoutParams = this$0.windowAttr;
        Object animatedValue = animation.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        layoutParams.alpha = ((Float) animatedValue).floatValue();
        this$0.window.setAttributes(this$0.windowAttr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m331_init_$lambda2(BasePopup this$0, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animation, "animation");
        WindowManager.LayoutParams layoutParams = this$0.windowAttr;
        Object animatedValue = animation.getAnimatedValue();
        Objects.requireNonNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        layoutParams.alpha = ((Float) animatedValue).floatValue();
        this$0.window.setAttributes(this$0.windowAttr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-3, reason: not valid java name */
    public static final boolean m332_init_$lambda3(BasePopup this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (motionEvent.getAction() == 0) {
            this$0.clickLocation[0] = motionEvent.getRawX();
            this$0.clickLocation[1] = motionEvent.getRawY();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-4, reason: not valid java name */
    public static final boolean m333_init_$lambda4(BasePopup this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i != 4) {
            return false;
        }
        this$0.dismiss();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        if (r0 >= r10[1]) goto L16;
     */
    /* renamed from: _init_$lambda-5, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean m334_init_$lambda5(com.qcwireless.qcwatch.ui.base.view.pop.BasePopup r9, android.view.View r10, android.view.MotionEvent r11) {
        /*
            java.lang.String r10 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r10)
            float r10 = r11.getX()
            int r10 = (int) r10
            float r0 = r11.getY()
            int r0 = (int) r0
            int r1 = r11.getAction()
            java.lang.String r2 = ",mHeight="
            java.lang.String r3 = "onTouch outside:mWidth="
            java.lang.String r4 = "ContentValues"
            r5 = 0
            r6 = 1
            r7 = 0
            java.lang.String r8 = "popupContentViewSize"
            if (r1 != 0) goto L6d
            if (r10 < 0) goto L3f
            int[] r1 = r9.popupContentViewSize
            if (r1 != 0) goto L2d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r1 = r7
        L2d:
            r1 = r1[r5]
            if (r10 >= r1) goto L3f
            if (r0 < 0) goto L3f
            int[] r10 = r9.popupContentViewSize
            if (r10 != 0) goto L3b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r10 = r7
        L3b:
            r10 = r10[r6]
            if (r0 < r10) goto L6d
        L3f:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            int[] r11 = r9.popupContentViewSize
            if (r11 != 0) goto L4f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r11 = r7
        L4f:
            r11 = r11[r5]
            r10.append(r11)
            r10.append(r2)
            int[] r9 = r9.popupContentViewSize
            if (r9 != 0) goto L5f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            goto L60
        L5f:
            r7 = r9
        L60:
            r9 = r7[r6]
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            android.util.Log.d(r4, r9)
            return r6
        L6d:
            int r10 = r11.getAction()
            r11 = 4
            if (r10 != r11) goto La2
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            int[] r11 = r9.popupContentViewSize
            if (r11 != 0) goto L84
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r11 = r7
        L84:
            r11 = r11[r5]
            r10.append(r11)
            r10.append(r2)
            int[] r9 = r9.popupContentViewSize
            if (r9 != 0) goto L94
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            goto L95
        L94:
            r7 = r9
        L95:
            r9 = r7[r6]
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            android.util.Log.d(r4, r9)
            return r6
        La2:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup.m334_init_$lambda5(com.qcwireless.qcwatch.ui.base.view.pop.BasePopup, android.view.View, android.view.MotionEvent):boolean");
    }

    public final BasePopup setContentView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.mPopup.setContentView(view);
        return this;
    }

    public final BasePopup setDimValue(float dimValue) {
        this.dim = dimValue;
        return this;
    }

    public final BasePopup setIsBgDim(boolean z) {
        this.isBgDim = z;
        return this;
    }

    public final void dismiss() {
        if (this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
        resetDim();
    }

    public final void showAsDropDown(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        setBgDim();
        this.mPopup.showAsDropDown(view);
    }

    public final View getContentView() {
        View contentView = this.mPopup.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "mPopup.contentView");
        return contentView;
    }

    public final Window getWindow() {
        return this.window;
    }

    public final Context getContext() {
        Context context = this.window.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "window.context");
        return context;
    }

    public void showAtView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        setBgDim();
        int[] popupShowLocation = getPopupShowLocation(view);
        this.mPopup.showAtLocation(view, 0, popupShowLocation[0], popupShowLocation[1]);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int[] getPopupShowLocation(android.view.View r12) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup.getPopupShowLocation(android.view.View):int[]");
    }

    public final int getShowDirection(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int[] viewLocation = getViewLocation(view);
        int measuredHeight = view.getMeasuredHeight();
        int[] windowSize = Utils.INSTANCE.getWindowSize(this.popParams.getActivity());
        int i = measuredHeight / 2;
        int i2 = getPopupContentViewSize()[0] / 2;
        return (windowSize[1] - (viewLocation[1] + i) <= i2 && viewLocation[1] + i > i2) ? -4 : -3;
    }

    public void showAtDirectionByView(View view, int direction) {
        Intrinsics.checkNotNullParameter(view, "view");
        setBgDim();
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        view.getLocationInWindow(iArr2);
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int[] popupContentViewSize = getPopupContentViewSize();
        this.popupContentViewSize = popupContentViewSize;
        int[] iArr3 = null;
        if (direction == -4) {
            int i = iArr2[0] + (measuredWidth / 2);
            if (popupContentViewSize == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                popupContentViewSize = null;
            }
            iArr[0] = i - (popupContentViewSize[0] / 2);
            int i2 = iArr2[1];
            int[] iArr4 = this.popupContentViewSize;
            if (iArr4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
            } else {
                iArr3 = iArr4;
            }
            iArr[1] = (i2 - iArr3[1]) - Utils.INSTANCE.dp2px(getContext(), this.popParams.getCommonPopMargin());
        } else if (direction == -3) {
            int i3 = iArr2[0] + (measuredWidth / 2);
            if (popupContentViewSize == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                popupContentViewSize = null;
            }
            iArr[0] = i3 - (popupContentViewSize[0] / 2);
            iArr[1] = iArr2[1] + measuredHeight + Utils.INSTANCE.dp2px(getContext(), this.popParams.getCommonPopMargin());
        } else if (direction == -2) {
            iArr[0] = iArr2[0] + measuredWidth + Utils.INSTANCE.dp2px(getContext(), this.popParams.getCommonPopMargin());
            int i4 = iArr2[1] + (measuredHeight / 2);
            int[] iArr5 = this.popupContentViewSize;
            if (iArr5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
            } else {
                iArr3 = iArr5;
            }
            iArr[1] = i4 - (iArr3[1] / 2);
        } else if (direction == -1) {
            int i5 = iArr2[0];
            if (popupContentViewSize == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                popupContentViewSize = null;
            }
            iArr[0] = (i5 - popupContentViewSize[0]) - Utils.INSTANCE.dp2px(getContext(), this.popParams.getCommonPopMargin());
            int i6 = iArr2[1] + (measuredHeight / 2);
            int[] iArr6 = this.popupContentViewSize;
            if (iArr6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
            } else {
                iArr3 = iArr6;
            }
            iArr[1] = i6 - (iArr3[1] / 2);
        }
        this.mPopup.showAtLocation(view, 0, iArr[0], iArr[1]);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showAtFingerLocation() {
        /*
            Method dump skipped, instructions count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.view.pop.BasePopup.showAtFingerLocation():void");
    }

    public void showAtFingerLocation(int direction) {
        int[] popupContentViewSize = getPopupContentViewSize();
        this.popupContentViewSize = popupContentViewSize;
        int[] iArr = new int[2];
        int[] iArr2 = null;
        switch (direction) {
            case -8:
                float[] fArr = this.clickLocation;
                int i = (int) fArr[0];
                int i2 = this.defaultMargin;
                iArr[0] = i + i2;
                iArr[1] = ((int) fArr[1]) + i2;
                break;
            case -7:
                float[] fArr2 = this.clickLocation;
                iArr[0] = ((int) fArr2[0]) + this.defaultMargin;
                float f = fArr2[1];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                iArr[1] = ((int) (f - popupContentViewSize[1])) - this.defaultMargin;
                break;
            case -6:
                float f2 = this.clickLocation[0];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                int i3 = (int) (f2 - popupContentViewSize[0]);
                int i4 = this.defaultMargin;
                iArr[0] = i3 - i4;
                iArr[1] = ((int) this.clickLocation[1]) + i4;
                break;
            case -5:
                float f3 = this.clickLocation[0];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                iArr[0] = ((int) (f3 - popupContentViewSize[0])) - this.defaultMargin;
                float f4 = this.clickLocation[1];
                int[] iArr3 = this.popupContentViewSize;
                if (iArr3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                } else {
                    iArr2 = iArr3;
                }
                iArr[1] = ((int) (f4 - iArr2[1])) - this.defaultMargin;
                break;
            case -4:
                float f5 = this.clickLocation[0];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                iArr[0] = (int) (f5 - (popupContentViewSize[0] / 2));
                float f6 = this.clickLocation[1];
                int[] iArr4 = this.popupContentViewSize;
                if (iArr4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                } else {
                    iArr2 = iArr4;
                }
                iArr[1] = ((int) (f6 - iArr2[1])) - this.defaultMargin;
                break;
            case -3:
                float f7 = this.clickLocation[0];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                iArr[0] = (int) (f7 - (popupContentViewSize[0] / 2));
                iArr[1] = ((int) this.clickLocation[1]) + this.defaultMargin;
                break;
            case -2:
                float[] fArr3 = this.clickLocation;
                iArr[0] = ((int) fArr3[0]) + this.defaultMargin;
                float f8 = fArr3[1];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                iArr[1] = (int) (f8 - (popupContentViewSize[1] / 2));
                break;
            case -1:
                float f9 = this.clickLocation[0];
                if (popupContentViewSize == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                    popupContentViewSize = null;
                }
                iArr[0] = ((int) (f9 - popupContentViewSize[0])) - this.defaultMargin;
                float f10 = this.clickLocation[1];
                int[] iArr5 = this.popupContentViewSize;
                if (iArr5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                } else {
                    iArr2 = iArr5;
                }
                iArr[1] = (int) (f10 - (iArr2[1] / 2));
                break;
        }
        this.mPopup.showAtLocation(getContentView(), 0, iArr[0], iArr[1]);
    }

    public final void showAtDirection(int direction) {
        setBgDim();
        int[] iArr = new int[2];
        this.popupContentViewSize = getPopupContentViewSize();
        int[] windowSize = Utils.INSTANCE.getWindowSize(this.popParams.getActivity());
        if (direction == -4) {
            iArr[0] = 0;
            iArr[1] = 0;
        } else if (direction == -3) {
            iArr[0] = 0;
            int i = windowSize[1];
            int[] iArr2 = this.popupContentViewSize;
            if (iArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("popupContentViewSize");
                iArr2 = null;
            }
            iArr[1] = i - iArr2[1];
        }
        this.mPopup.setAnimationStyle(WPopupAnim.INSTANCE.getANIM_SCALE_Y());
        this.mPopup.showAtLocation(getContentView().getRootView(), 0, iArr[0], iArr[1]);
    }

    private final int[] getViewLocation(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr;
    }

    private final int[] getPopupContentViewSize() {
        getContentView().measure(0, 0);
        int measuredHeight = getContentView().getMeasuredHeight();
        int measuredWidth = getContentView().getMeasuredWidth() + this.defaultMargin;
        if (this.popParams.getWidth() == -1) {
            measuredWidth = Utils.INSTANCE.getWindowSize(this.popParams.getActivity())[0];
        }
        return new int[]{measuredWidth, measuredHeight};
    }

    public final void setAnim(int anim) {
        this.popParams.setAnimRes(anim);
        this.mPopup.setAnimationStyle(this.popParams.getAnimRes());
    }

    private final void setBgDim() {
        if (this.isBgDim) {
            this.bgDimAnimator.start();
        }
    }

    private final void resetDim() {
        if (this.isBgDim) {
            this.clearBgDimAnimator.start();
        }
    }

    public final <T extends View> T findViewById(int resId) {
        T t = (T) getContentView().findViewById(resId);
        Intrinsics.checkNotNullExpressionValue(t, "getContentView().findViewById(resId)");
        return t;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkNotNull(event);
        if (event.getAction() == 0) {
            this.clickLocation[0] = event.getRawX();
            this.clickLocation[1] = event.getRawY();
            Log.d("112233", this.clickLocation[0] + " --- " + this.clickLocation[1]);
        }
        return false;
    }
}
