package com.haibin.calendarview;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseView extends View implements View.OnClickListener, View.OnLongClickListener {
    static final int TEXT_SIZE = 14;
    boolean isClick;
    protected Paint mCurDayLunarTextPaint;
    protected Paint mCurDayTextPaint;
    protected Paint mCurMonthLunarTextPaint;
    protected Paint mCurMonthTextPaint;
    int mCurrentItem;
    CalendarViewDelegate mDelegate;
    protected int mItemHeight;
    protected int mItemWidth;
    protected List<Calendar> mItems;
    protected Paint mOtherMonthLunarTextPaint;
    protected Paint mOtherMonthTextPaint;
    CalendarLayout mParentLayout;
    protected Paint mSchemeLunarTextPaint;
    protected Paint mSchemePaint;
    protected Paint mSchemeTextPaint;
    protected Paint mSelectTextPaint;
    protected Paint mSelectedLunarTextPaint;
    protected Paint mSelectedPaint;
    protected float mTextBaseLine;
    int mWeekStartWidth;
    protected float mX;
    protected float mY;

    protected void initPaint() {
    }

    protected abstract void onDestroy();

    protected void onPreviewHook() {
    }

    abstract void updateCurrentDate();

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurMonthTextPaint = new Paint();
        this.mOtherMonthTextPaint = new Paint();
        this.mCurMonthLunarTextPaint = new Paint();
        this.mSelectedLunarTextPaint = new Paint();
        this.mOtherMonthLunarTextPaint = new Paint();
        this.mSchemeLunarTextPaint = new Paint();
        this.mSchemePaint = new Paint();
        this.mSelectedPaint = new Paint();
        this.mSchemeTextPaint = new Paint();
        this.mSelectTextPaint = new Paint();
        this.mCurDayTextPaint = new Paint();
        this.mCurDayLunarTextPaint = new Paint();
        this.isClick = true;
        this.mCurrentItem = -1;
        initPaint(context);
    }

    private void initPaint(Context context) {
        this.mCurMonthTextPaint.setAntiAlias(true);
        this.mCurMonthTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mCurMonthTextPaint.setColor(-15658735);
        this.mCurMonthTextPaint.setFakeBoldText(true);
        this.mCurMonthTextPaint.setTextSize(CalendarUtil.dipToPx(context, 14.0f));
        this.mOtherMonthTextPaint.setAntiAlias(true);
        this.mOtherMonthTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mOtherMonthTextPaint.setColor(-1973791);
        this.mOtherMonthTextPaint.setFakeBoldText(true);
        this.mOtherMonthTextPaint.setTextSize(CalendarUtil.dipToPx(context, 14.0f));
        this.mCurMonthLunarTextPaint.setAntiAlias(true);
        this.mCurMonthLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mSelectedLunarTextPaint.setAntiAlias(true);
        this.mSelectedLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mOtherMonthLunarTextPaint.setAntiAlias(true);
        this.mOtherMonthLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mSchemeLunarTextPaint.setAntiAlias(true);
        this.mSchemeLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mSchemeTextPaint.setAntiAlias(true);
        this.mSchemeTextPaint.setStyle(Paint.Style.FILL);
        this.mSchemeTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mSchemeTextPaint.setColor(-1223853);
        this.mSchemeTextPaint.setFakeBoldText(true);
        this.mSchemeTextPaint.setTextSize(CalendarUtil.dipToPx(context, 14.0f));
        this.mSelectTextPaint.setAntiAlias(true);
        this.mSelectTextPaint.setStyle(Paint.Style.FILL);
        this.mSelectTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mSelectTextPaint.setColor(-1223853);
        this.mSelectTextPaint.setFakeBoldText(true);
        this.mSelectTextPaint.setTextSize(CalendarUtil.dipToPx(context, 14.0f));
        this.mSchemePaint.setAntiAlias(true);
        this.mSchemePaint.setStyle(Paint.Style.FILL);
        this.mSchemePaint.setStrokeWidth(2.0f);
        this.mSchemePaint.setColor(-1052689);
        this.mCurDayTextPaint.setAntiAlias(true);
        this.mCurDayTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mCurDayTextPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mCurDayTextPaint.setFakeBoldText(true);
        this.mCurDayTextPaint.setTextSize(CalendarUtil.dipToPx(context, 14.0f));
        this.mCurDayLunarTextPaint.setAntiAlias(true);
        this.mCurDayLunarTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mCurDayLunarTextPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mCurDayLunarTextPaint.setFakeBoldText(true);
        this.mCurDayLunarTextPaint.setTextSize(CalendarUtil.dipToPx(context, 14.0f));
        this.mSelectedPaint.setAntiAlias(true);
        this.mSelectedPaint.setStyle(Paint.Style.FILL);
        this.mSelectedPaint.setStrokeWidth(2.0f);
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    final void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        this.mWeekStartWidth = calendarViewDelegate.getWeekStart();
        updateStyle();
        updateItemHeight();
        initPaint();
    }

    final void updateStyle() {
        CalendarViewDelegate calendarViewDelegate = this.mDelegate;
        if (calendarViewDelegate == null) {
            return;
        }
        this.mCurDayTextPaint.setColor(calendarViewDelegate.getCurDayTextColor());
        this.mCurDayLunarTextPaint.setColor(this.mDelegate.getCurDayLunarTextColor());
        this.mCurMonthTextPaint.setColor(this.mDelegate.getCurrentMonthTextColor());
        this.mOtherMonthTextPaint.setColor(this.mDelegate.getOtherMonthTextColor());
        this.mCurMonthLunarTextPaint.setColor(this.mDelegate.getCurrentMonthLunarTextColor());
        this.mSelectedLunarTextPaint.setColor(this.mDelegate.getSelectedLunarTextColor());
        this.mSelectTextPaint.setColor(this.mDelegate.getSelectedTextColor());
        this.mOtherMonthLunarTextPaint.setColor(this.mDelegate.getOtherMonthLunarTextColor());
        this.mSchemeLunarTextPaint.setColor(this.mDelegate.getSchemeLunarTextColor());
        this.mSchemePaint.setColor(this.mDelegate.getSchemeThemeColor());
        this.mSchemeTextPaint.setColor(this.mDelegate.getSchemeTextColor());
        this.mCurMonthTextPaint.setTextSize(this.mDelegate.getDayTextSize());
        this.mOtherMonthTextPaint.setTextSize(this.mDelegate.getDayTextSize());
        this.mCurDayTextPaint.setTextSize(this.mDelegate.getDayTextSize());
        this.mSchemeTextPaint.setTextSize(this.mDelegate.getDayTextSize());
        this.mSelectTextPaint.setTextSize(this.mDelegate.getDayTextSize());
        this.mCurMonthLunarTextPaint.setTextSize(this.mDelegate.getLunarTextSize());
        this.mSelectedLunarTextPaint.setTextSize(this.mDelegate.getLunarTextSize());
        this.mCurDayLunarTextPaint.setTextSize(this.mDelegate.getLunarTextSize());
        this.mOtherMonthLunarTextPaint.setTextSize(this.mDelegate.getLunarTextSize());
        this.mSchemeLunarTextPaint.setTextSize(this.mDelegate.getLunarTextSize());
        this.mSelectedPaint.setStyle(Paint.Style.FILL);
        this.mSelectedPaint.setColor(this.mDelegate.getSelectedThemeColor());
    }

    void updateItemHeight() {
        this.mItemHeight = this.mDelegate.getCalendarItemHeight();
        Paint.FontMetrics fontMetrics = this.mCurMonthTextPaint.getFontMetrics();
        this.mTextBaseLine = ((this.mItemHeight / 2) - fontMetrics.descent) + ((fontMetrics.bottom - fontMetrics.top) / 2.0f);
    }

    final void removeSchemes() {
        for (Calendar calendar : this.mItems) {
            calendar.setScheme("");
            calendar.setSchemeColor(0);
            calendar.setSchemes(null);
        }
    }

    final void addSchemesFromMap() {
        if (this.mDelegate.mSchemeDatesMap == null || this.mDelegate.mSchemeDatesMap.size() == 0) {
            return;
        }
        for (Calendar calendar : this.mItems) {
            if (this.mDelegate.mSchemeDatesMap.containsKey(calendar.toString())) {
                Calendar calendar2 = this.mDelegate.mSchemeDatesMap.get(calendar.toString());
                if (calendar2 != null) {
                    calendar.setScheme(TextUtils.isEmpty(calendar2.getScheme()) ? this.mDelegate.getSchemeText() : calendar2.getScheme());
                    calendar.setSchemeColor(calendar2.getSchemeColor());
                    calendar.setSchemes(calendar2.getSchemes());
                }
            } else {
                calendar.setScheme("");
                calendar.setSchemeColor(0);
                calendar.setSchemes(null);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 1) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
            this.isClick = true;
        } else if (action != 1) {
            if (action == 2 && this.isClick) {
                this.isClick = Math.abs(motionEvent.getY() - this.mY) <= 50.0f;
            }
        } else {
            this.mX = motionEvent.getX();
            this.mY = motionEvent.getY();
        }
        return super.onTouchEvent(motionEvent);
    }

    protected boolean isSelected(Calendar calendar) {
        List<Calendar> list = this.mItems;
        return list != null && list.indexOf(calendar) == this.mCurrentItem;
    }

    final void update() {
        if (this.mDelegate.mSchemeDatesMap == null || this.mDelegate.mSchemeDatesMap.size() == 0) {
            removeSchemes();
            invalidate();
        } else {
            addSchemesFromMap();
            invalidate();
        }
    }

    protected final boolean onCalendarIntercept(Calendar calendar) {
        return this.mDelegate.mCalendarInterceptListener != null && this.mDelegate.mCalendarInterceptListener.onCalendarIntercept(calendar);
    }

    protected final boolean isInRange(Calendar calendar) {
        CalendarViewDelegate calendarViewDelegate = this.mDelegate;
        return calendarViewDelegate != null && CalendarUtil.isCalendarInRange(calendar, calendarViewDelegate);
    }

    protected int getWeekStartWith() {
        CalendarViewDelegate calendarViewDelegate = this.mDelegate;
        if (calendarViewDelegate != null) {
            return calendarViewDelegate.getWeekStart();
        }
        return 1;
    }

    protected int getCalendarPaddingLeft() {
        CalendarViewDelegate calendarViewDelegate = this.mDelegate;
        if (calendarViewDelegate != null) {
            return calendarViewDelegate.getCalendarPaddingLeft();
        }
        return 0;
    }

    protected int getCalendarPaddingRight() {
        CalendarViewDelegate calendarViewDelegate = this.mDelegate;
        if (calendarViewDelegate != null) {
            return calendarViewDelegate.getCalendarPaddingRight();
        }
        return 0;
    }
}
