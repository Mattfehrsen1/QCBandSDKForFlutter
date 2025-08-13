package com.qcwireless.qcwatch.ui.base.view.recycleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public class SwipeItemLayout extends ViewGroup {
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.qcwireless.qcwatch.ui.base.view.recycleview.SwipeItemLayout.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float t) {
            float f = t - 1.0f;
            return (f * f * f * f * f) + 1.0f;
        }
    };
    private boolean mInLayout;
    private boolean mIsLaidOut;
    private ViewGroup mMainView;
    private int mMaxScrollOffset;
    private int mScrollOffset;
    private ScrollRunnable mScrollRunnable;
    private ViewGroup mSideView;
    private Mode mTouchMode;

    enum Mode {
        RESET,
        DRAG,
        FLING,
        TAP
    }

    public SwipeItemLayout(Context context) {
        this(context, null);
    }

    public SwipeItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTouchMode = Mode.RESET;
        this.mScrollOffset = 0;
        this.mIsLaidOut = false;
        this.mScrollRunnable = new ScrollRunnable(context);
    }

    public boolean isOpen() {
        return this.mScrollOffset != 0;
    }

    Mode getTouchMode() {
        return this.mTouchMode;
    }

    /* renamed from: com.qcwireless.qcwatch.ui.base.view.recycleview.SwipeItemLayout$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$qcwireless$qcwatch$ui$base$view$recycleview$SwipeItemLayout$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$qcwireless$qcwatch$ui$base$view$recycleview$SwipeItemLayout$Mode = iArr;
            try {
                iArr[Mode.FLING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$view$recycleview$SwipeItemLayout$Mode[Mode.RESET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    void setTouchMode(Mode mode) {
        if (AnonymousClass2.$SwitchMap$com$qcwireless$qcwatch$ui$base$view$recycleview$SwipeItemLayout$Mode[this.mTouchMode.ordinal()] == 1) {
            this.mScrollRunnable.abort();
        }
        this.mTouchMode = mode;
    }

    public void open() {
        if (this.mScrollOffset != (-this.mMaxScrollOffset)) {
            if (this.mTouchMode == Mode.FLING && this.mScrollRunnable.isScrollToLeft()) {
                return;
            }
            if (this.mTouchMode == Mode.FLING) {
                this.mScrollRunnable.abort();
            }
            this.mScrollRunnable.startScroll(this.mScrollOffset, -this.mMaxScrollOffset);
        }
    }

    public void close() {
        if (this.mScrollOffset != 0) {
            if (this.mTouchMode != Mode.FLING || this.mScrollRunnable.isScrollToLeft()) {
                if (this.mTouchMode == Mode.FLING) {
                    this.mScrollRunnable.abort();
                }
                this.mScrollRunnable.startScroll(this.mScrollOffset, 0);
            }
        }
    }

    void fling(int xVel) {
        this.mScrollRunnable.startFling(this.mScrollOffset, xVel);
    }

    void revise() {
        if (this.mScrollOffset < (-this.mMaxScrollOffset) / 2) {
            open();
        } else {
            close();
        }
    }

    boolean trackMotionScroll(int deltaX) {
        boolean z = false;
        if (deltaX == 0) {
            return false;
        }
        int iMax = this.mScrollOffset + deltaX;
        if ((deltaX > 0 && iMax > 0) || (deltaX < 0 && iMax < (-this.mMaxScrollOffset))) {
            iMax = Math.max(Math.min(iMax, 0), -this.mMaxScrollOffset);
            z = true;
        }
        offsetChildrenLeftAndRight(iMax - this.mScrollOffset);
        this.mScrollOffset = iMax;
        return z;
    }

    private boolean ensureChildren() {
        if (getChildCount() != 2) {
            return false;
        }
        View childAt = getChildAt(0);
        if (!(childAt instanceof ViewGroup)) {
            return false;
        }
        this.mMainView = (ViewGroup) childAt;
        View childAt2 = getChildAt(1);
        if (!(childAt2 instanceof ViewGroup)) {
            return false;
        }
        this.mSideView = (ViewGroup) childAt2;
        return true;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!ensureChildren()) {
            throw new RuntimeException("SwipeItemLayout的子视图不符合规定");
        }
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mMainView.getLayoutParams();
        int i = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
        int i2 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        measureChildWithMargins(this.mMainView, widthMeasureSpec, i + paddingLeft, heightMeasureSpec, i2 + paddingTop);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, this.mMainView.getMeasuredWidth() + i + paddingLeft);
        } else if (mode == 0) {
            size = this.mMainView.getMeasuredWidth() + i + paddingLeft;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, this.mMainView.getMeasuredHeight() + i2 + paddingTop);
        } else if (mode2 == 0) {
            size2 = this.mMainView.getMeasuredHeight() + i2 + paddingTop;
        }
        setMeasuredDimension(size, size2);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mSideView.getLayoutParams();
        this.mSideView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - (marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin)) - paddingTop, 1073741824));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (!ensureChildren()) {
            throw new RuntimeException("SwipeItemLayout的子视图不符合规定");
        }
        this.mInLayout = true;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mMainView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mSideView.getLayoutParams();
        int i = paddingLeft + marginLayoutParams.leftMargin;
        int i2 = marginLayoutParams.topMargin + paddingTop;
        int width = getWidth() - (paddingRight + marginLayoutParams.rightMargin);
        this.mMainView.layout(i, i2, width, getHeight() - (marginLayoutParams.bottomMargin + paddingBottom));
        int i3 = width + marginLayoutParams2.leftMargin;
        this.mSideView.layout(i3, paddingTop + marginLayoutParams2.topMargin, marginLayoutParams2.leftMargin + i3 + marginLayoutParams2.rightMargin + this.mSideView.getMeasuredWidth(), getHeight() - (marginLayoutParams2.bottomMargin + paddingBottom));
        int width2 = this.mSideView.getWidth() + marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin;
        this.mMaxScrollOffset = width2;
        int i4 = this.mScrollOffset < (-width2) / 2 ? -width2 : 0;
        this.mScrollOffset = i4;
        offsetChildrenLeftAndRight(i4);
        this.mInLayout = false;
        this.mIsLaidOut = true;
    }

    void offsetChildrenLeftAndRight(int delta) {
        ViewCompat.offsetLeftAndRight(this.mMainView, delta);
        ViewCompat.offsetLeftAndRight(this.mSideView, delta);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInLayout) {
            return;
        }
        super.requestLayout();
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof ViewGroup.MarginLayoutParams ? p : new ViewGroup.MarginLayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return (p instanceof ViewGroup.MarginLayoutParams) && super.checkLayoutParams(p);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = this.mScrollOffset;
        if (i != 0 && this.mIsLaidOut) {
            offsetChildrenLeftAndRight(-i);
            this.mScrollOffset = 0;
        } else {
            this.mScrollOffset = 0;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int i = this.mScrollOffset;
        if (i != 0 && this.mIsLaidOut) {
            offsetChildrenLeftAndRight(-i);
            this.mScrollOffset = 0;
        } else {
            this.mScrollOffset = 0;
        }
        removeCallbacks(this.mScrollRunnable);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        View viewFindTopChildUnder;
        int actionMasked = ev.getActionMasked();
        if (actionMasked != 0) {
            return actionMasked == 1 && (viewFindTopChildUnder = findTopChildUnder(this, (int) ev.getX(), (int) ev.getY())) != null && viewFindTopChildUnder == this.mMainView && this.mTouchMode == Mode.TAP && this.mScrollOffset != 0;
        }
        View viewFindTopChildUnder2 = findTopChildUnder(this, (int) ev.getX(), (int) ev.getY());
        return (viewFindTopChildUnder2 == null || viewFindTopChildUnder2 != this.mMainView || this.mScrollOffset == 0) ? false : true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        View viewFindTopChildUnder;
        int actionMasked = ev.getActionMasked();
        if (actionMasked == 0) {
            View viewFindTopChildUnder2 = findTopChildUnder(this, (int) ev.getX(), (int) ev.getY());
            return (viewFindTopChildUnder2 == null || viewFindTopChildUnder2 != this.mMainView || this.mScrollOffset == 0) ? false : true;
        }
        if (actionMasked != 1 || (viewFindTopChildUnder = findTopChildUnder(this, (int) ev.getX(), (int) ev.getY())) == null || viewFindTopChildUnder != this.mMainView || this.mTouchMode != Mode.TAP || this.mScrollOffset == 0) {
            return false;
        }
        close();
        return true;
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (getVisibility() != 0) {
            this.mScrollOffset = 0;
            invalidate();
        }
    }

    class ScrollRunnable implements Runnable {
        private static final int FLING_DURATION = 200;
        private int mMinVelocity;
        private Scroller mScroller;
        private boolean mAbort = false;
        private boolean mScrollToLeft = false;

        ScrollRunnable(Context context) {
            this.mScroller = new Scroller(context, SwipeItemLayout.sInterpolator);
            this.mMinVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        }

        void startScroll(int startX, int endX) {
            if (startX != endX) {
                SwipeItemLayout.this.setTouchMode(Mode.FLING);
                this.mAbort = false;
                this.mScrollToLeft = endX < startX;
                this.mScroller.startScroll(startX, 0, endX - startX, 0, 400);
                ViewCompat.postOnAnimation(SwipeItemLayout.this, this);
            }
        }

        void startFling(int startX, int xVel) {
            int i = this.mMinVelocity;
            if (xVel <= i || startX == 0) {
                if (xVel >= (-i) || startX == (-SwipeItemLayout.this.mMaxScrollOffset)) {
                    startScroll(startX, startX <= (-SwipeItemLayout.this.mMaxScrollOffset) / 2 ? -SwipeItemLayout.this.mMaxScrollOffset : 0);
                    return;
                } else {
                    startScroll(startX, -SwipeItemLayout.this.mMaxScrollOffset);
                    return;
                }
            }
            startScroll(startX, 0);
        }

        void abort() {
            if (this.mAbort) {
                return;
            }
            this.mAbort = true;
            if (this.mScroller.isFinished()) {
                return;
            }
            this.mScroller.abortAnimation();
            SwipeItemLayout.this.removeCallbacks(this);
        }

        boolean isScrollToLeft() {
            return this.mScrollToLeft;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mAbort) {
                return;
            }
            boolean zComputeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            SwipeItemLayout swipeItemLayout = SwipeItemLayout.this;
            boolean zTrackMotionScroll = swipeItemLayout.trackMotionScroll(currX - swipeItemLayout.mScrollOffset);
            if (zComputeScrollOffset && !zTrackMotionScroll) {
                ViewCompat.postOnAnimation(SwipeItemLayout.this, this);
                return;
            }
            if (zTrackMotionScroll) {
                SwipeItemLayout.this.removeCallbacks(this);
                if (!this.mScroller.isFinished()) {
                    this.mScroller.abortAnimation();
                }
                SwipeItemLayout.this.setTouchMode(Mode.RESET);
            }
            if (zComputeScrollOffset) {
                return;
            }
            SwipeItemLayout.this.setTouchMode(Mode.RESET);
            if (SwipeItemLayout.this.mScrollOffset != 0) {
                if (Math.abs(SwipeItemLayout.this.mScrollOffset) <= SwipeItemLayout.this.mMaxScrollOffset / 2) {
                    SwipeItemLayout.this.mScrollOffset = 0;
                } else {
                    SwipeItemLayout swipeItemLayout2 = SwipeItemLayout.this;
                    swipeItemLayout2.mScrollOffset = -swipeItemLayout2.mMaxScrollOffset;
                }
                ViewCompat.postOnAnimation(SwipeItemLayout.this, this);
            }
        }
    }

    public static class OnSwipeItemTouchListener implements RecyclerView.OnItemTouchListener {
        private int mActivePointerId;
        private SwipeItemLayout mCaptureItem;
        private boolean mDealByParent;
        private boolean mIsProbeParent;
        private float mLastMotionX;
        private float mLastMotionY;
        private int mMaximumVelocity;
        private int mTouchSlop;
        private VelocityTracker mVelocityTracker;

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

        public OnSwipeItemTouchListener(Context context) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
            this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mActivePointerId = -1;
            this.mDealByParent = false;
            this.mIsProbeParent = false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent ev) {
            SwipeItemLayout swipeItemLayout;
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4;
            ViewParent parent;
            SwipeItemLayout swipeItemLayout2;
            boolean z5 = false;
            if (this.mIsProbeParent) {
                return false;
            }
            int actionMasked = ev.getActionMasked();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(ev);
            if (actionMasked == 0) {
                this.mActivePointerId = ev.getPointerId(0);
                float x = ev.getX();
                float y = ev.getY();
                this.mLastMotionX = x;
                this.mLastMotionY = y;
                View viewFindTopChildUnder = SwipeItemLayout.findTopChildUnder(rv, (int) x, (int) y);
                if (viewFindTopChildUnder == null || !(viewFindTopChildUnder instanceof SwipeItemLayout)) {
                    swipeItemLayout = null;
                    z = true;
                } else {
                    swipeItemLayout = (SwipeItemLayout) viewFindTopChildUnder;
                    z = false;
                }
                if (!z && ((swipeItemLayout2 = this.mCaptureItem) == null || swipeItemLayout2 != swipeItemLayout)) {
                    z = true;
                }
                if (!z) {
                    if (this.mCaptureItem.getTouchMode() == Mode.FLING) {
                        this.mCaptureItem.setTouchMode(Mode.DRAG);
                        z4 = true;
                        z3 = true;
                    } else {
                        this.mCaptureItem.setTouchMode(Mode.TAP);
                        z4 = this.mCaptureItem.isOpen();
                        z3 = false;
                    }
                    if (z4 && (parent = rv.getParent()) != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    SwipeItemLayout swipeItemLayout3 = this.mCaptureItem;
                    if (swipeItemLayout3 == null || !swipeItemLayout3.isOpen()) {
                        z2 = false;
                    } else {
                        this.mCaptureItem.close();
                        this.mCaptureItem = null;
                        z2 = true;
                    }
                    if (swipeItemLayout != null) {
                        this.mCaptureItem = swipeItemLayout;
                        swipeItemLayout.setTouchMode(Mode.TAP);
                    } else {
                        this.mCaptureItem = null;
                    }
                    z3 = z2;
                }
                this.mIsProbeParent = true;
                boolean zOnInterceptTouchEvent = rv.onInterceptTouchEvent(ev);
                this.mDealByParent = zOnInterceptTouchEvent;
                this.mIsProbeParent = false;
                if (zOnInterceptTouchEvent) {
                    return false;
                }
                return z3;
            }
            if (actionMasked == 1) {
                SwipeItemLayout swipeItemLayout4 = this.mCaptureItem;
                if (swipeItemLayout4 != null && swipeItemLayout4.getTouchMode() == Mode.DRAG) {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                    this.mCaptureItem.fling((int) velocityTracker.getXVelocity(this.mActivePointerId));
                    z5 = true;
                }
                cancel();
                return z5;
            }
            if (actionMasked != 2) {
                if (actionMasked == 3) {
                    SwipeItemLayout swipeItemLayout5 = this.mCaptureItem;
                    if (swipeItemLayout5 != null) {
                        swipeItemLayout5.revise();
                    }
                    cancel();
                    return false;
                }
                if (actionMasked == 5) {
                    int actionIndex = ev.getActionIndex();
                    this.mActivePointerId = ev.getPointerId(actionIndex);
                    this.mLastMotionX = ev.getX(actionIndex);
                    this.mLastMotionY = ev.getY(actionIndex);
                    return false;
                }
                if (actionMasked != 6) {
                    return false;
                }
                int actionIndex2 = ev.getActionIndex();
                if (ev.getPointerId(actionIndex2) != this.mActivePointerId) {
                    return false;
                }
                int i = actionIndex2 != 0 ? 0 : 1;
                this.mActivePointerId = ev.getPointerId(i);
                this.mLastMotionX = ev.getX(i);
                this.mLastMotionY = ev.getY(i);
                return false;
            }
            int iFindPointerIndex = ev.findPointerIndex(this.mActivePointerId);
            if (iFindPointerIndex == -1) {
                return false;
            }
            if (this.mDealByParent) {
                SwipeItemLayout swipeItemLayout6 = this.mCaptureItem;
                if (swipeItemLayout6 != null && swipeItemLayout6.isOpen()) {
                    this.mCaptureItem.close();
                }
                return false;
            }
            int x2 = (int) (ev.getX(iFindPointerIndex) + 0.5f);
            float f = x2;
            int i2 = (int) (f - this.mLastMotionX);
            float y2 = (int) (((int) ev.getY(iFindPointerIndex)) + 0.5f);
            int i3 = (int) (y2 - this.mLastMotionY);
            int iAbs = Math.abs(i2);
            int iAbs2 = Math.abs(i3);
            SwipeItemLayout swipeItemLayout7 = this.mCaptureItem;
            if (swipeItemLayout7 == null || this.mDealByParent) {
                return false;
            }
            if (swipeItemLayout7.getTouchMode() == Mode.TAP) {
                if (iAbs > this.mTouchSlop && iAbs > iAbs2) {
                    this.mCaptureItem.setTouchMode(Mode.DRAG);
                    rv.getParent().requestDisallowInterceptTouchEvent(true);
                    int i4 = this.mTouchSlop;
                    i2 = i2 > 0 ? i2 - i4 : i2 + i4;
                } else {
                    this.mIsProbeParent = true;
                    boolean zOnInterceptTouchEvent2 = rv.onInterceptTouchEvent(ev);
                    this.mIsProbeParent = false;
                    if (zOnInterceptTouchEvent2) {
                        this.mDealByParent = true;
                        this.mCaptureItem.close();
                    }
                }
            }
            if (this.mCaptureItem.getTouchMode() != Mode.DRAG) {
                return false;
            }
            this.mLastMotionX = f;
            this.mLastMotionY = y2;
            this.mCaptureItem.trackMotionScroll(i2);
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView rv, MotionEvent ev) {
            int actionMasked = ev.getActionMasked();
            int actionIndex = ev.getActionIndex();
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(ev);
            if (actionMasked == 1) {
                SwipeItemLayout swipeItemLayout = this.mCaptureItem;
                if (swipeItemLayout != null && swipeItemLayout.getTouchMode() == Mode.DRAG) {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                    this.mCaptureItem.fling((int) velocityTracker.getXVelocity(this.mActivePointerId));
                }
                cancel();
                return;
            }
            if (actionMasked == 2) {
                int iFindPointerIndex = ev.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex == -1) {
                    return;
                }
                float x = ev.getX(iFindPointerIndex);
                float y = (int) ev.getY(iFindPointerIndex);
                int i = (int) (x - this.mLastMotionX);
                SwipeItemLayout swipeItemLayout2 = this.mCaptureItem;
                if (swipeItemLayout2 == null || swipeItemLayout2.getTouchMode() != Mode.DRAG) {
                    return;
                }
                this.mLastMotionX = x;
                this.mLastMotionY = y;
                this.mCaptureItem.trackMotionScroll(i);
                return;
            }
            if (actionMasked == 3) {
                SwipeItemLayout swipeItemLayout3 = this.mCaptureItem;
                if (swipeItemLayout3 != null) {
                    swipeItemLayout3.revise();
                }
                cancel();
                return;
            }
            if (actionMasked == 5) {
                this.mActivePointerId = ev.getPointerId(actionIndex);
                this.mLastMotionX = ev.getX(actionIndex);
                this.mLastMotionY = ev.getY(actionIndex);
            } else if (actionMasked == 6 && ev.getPointerId(actionIndex) == this.mActivePointerId) {
                int i2 = actionIndex != 0 ? 0 : 1;
                this.mActivePointerId = ev.getPointerId(i2);
                this.mLastMotionX = ev.getX(i2);
                this.mLastMotionY = ev.getY(i2);
            }
        }

        void cancel() {
            this.mDealByParent = false;
            this.mActivePointerId = -1;
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
            }
        }
    }

    static View findTopChildUnder(ViewGroup parent, int x, int y) {
        for (int childCount = parent.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = parent.getChildAt(childCount);
            if (x >= childAt.getLeft() && x < childAt.getRight() && y >= childAt.getTop() && y < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public static void closeAllItems(RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            if (childAt instanceof SwipeItemLayout) {
                SwipeItemLayout swipeItemLayout = (SwipeItemLayout) childAt;
                if (swipeItemLayout.isOpen()) {
                    swipeItemLayout.close();
                }
            }
        }
    }
}
