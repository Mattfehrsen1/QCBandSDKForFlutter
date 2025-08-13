package com.haibin.calendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.internal.view.SupportMenu;
import com.haibin.calendarview.CalendarView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class CalendarViewDelegate {
    static final int FIRST_DAY_OF_MONTH = 0;
    static final int LAST_MONTH_VIEW_SELECT_DAY = 1;
    static final int LAST_MONTH_VIEW_SELECT_DAY_IGNORE_CURRENT = 2;
    private static final int MAX_YEAR = 2099;
    static final int MIN_YEAR = 1900;
    static final int MODE_ALL_MONTH = 0;
    static final int MODE_FIT_MONTH = 2;
    static final int MODE_ONLY_CURRENT_MONTH = 1;
    static final int SELECT_MODE_DEFAULT = 0;
    static final int SELECT_MODE_MULTI = 3;
    static final int SELECT_MODE_RANGE = 2;
    static final int SELECT_MODE_SINGLE = 1;
    static final int WEEK_START_WITH_MON = 2;
    static final int WEEK_START_WITH_SAT = 7;
    static final int WEEK_START_WITH_SUN = 1;
    private boolean isFullScreenCalendar;
    boolean isShowYearSelectedLayout;
    CalendarView.OnCalendarInterceptListener mCalendarInterceptListener;
    private int mCalendarItemHeight;
    CalendarView.OnCalendarLongClickListener mCalendarLongClickListener;
    CalendarView.OnCalendarMultiSelectListener mCalendarMultiSelectListener;
    private int mCalendarPadding;
    private int mCalendarPaddingLeft;
    private int mCalendarPaddingRight;
    CalendarView.OnCalendarRangeSelectListener mCalendarRangeSelectListener;
    CalendarView.OnCalendarSelectListener mCalendarSelectListener;
    CalendarView.OnClickCalendarPaddingListener mClickCalendarPaddingListener;
    private int mCurDayLunarTextColor;
    private int mCurDayTextColor;
    private int mCurMonthLunarTextColor;
    private Calendar mCurrentDate;
    private int mCurrentMonthTextColor;
    int mCurrentMonthViewItem;
    private int mDayTextSize;
    private int mDefaultCalendarSelectDay;
    Calendar mIndexCalendar;
    CalendarView.OnInnerDateSelectedListener mInnerListener;
    private int mLunarTextSize;
    private int mMaxMultiSelectSize;
    private int mMaxSelectRange;
    private int mMaxYear;
    private int mMaxYearDay;
    private int mMaxYearMonth;
    private int mMinSelectRange;
    private int mMinYear;
    private int mMinYearDay;
    private int mMinYearMonth;
    CalendarView.OnMonthChangeListener mMonthChangeListener;
    private Class<?> mMonthViewClass;
    private String mMonthViewClassPath;
    private boolean mMonthViewScrollable;
    private int mMonthViewShowMode;
    private int mOtherMonthLunarTextColor;
    private int mOtherMonthTextColor;
    Map<String, Calendar> mSchemeDatesMap;
    private int mSchemeLunarTextColor;
    private String mSchemeText;
    private int mSchemeTextColor;
    private int mSchemeThemeColor;
    private int mSelectMode;
    Calendar mSelectedCalendar;
    Map<String, Calendar> mSelectedCalendars = new HashMap();
    Calendar mSelectedEndRangeCalendar;
    private int mSelectedLunarTextColor;
    Calendar mSelectedStartRangeCalendar;
    private int mSelectedTextColor;
    private int mSelectedThemeColor;
    CalendarView.OnViewChangeListener mViewChangeListener;
    private int mWeekBackground;
    private Class<?> mWeekBarClass;
    private String mWeekBarClassPath;
    private int mWeekBarHeight;
    CalendarView.OnWeekChangeListener mWeekChangeListener;
    private int mWeekLineBackground;
    private int mWeekLineMargin;
    private int mWeekStart;
    private int mWeekTextColor;
    private int mWeekTextSize;
    private Class<?> mWeekViewClass;
    private String mWeekViewClassPath;
    private boolean mWeekViewScrollable;
    CalendarView.OnYearChangeListener mYearChangeListener;
    private int mYearViewBackground;
    CalendarView.OnYearViewChangeListener mYearViewChangeListener;
    private Class<?> mYearViewClass;
    private String mYearViewClassPath;
    private int mYearViewCurDayTextColor;
    private int mYearViewDayTextColor;
    private int mYearViewDayTextSize;
    private int mYearViewMonthHeight;
    private int mYearViewMonthPaddingBottom;
    private int mYearViewMonthPaddingLeft;
    private int mYearViewMonthPaddingRight;
    private int mYearViewMonthPaddingTop;
    private int mYearViewMonthTextColor;
    private int mYearViewMonthTextSize;
    private int mYearViewPadding;
    private int mYearViewPaddingLeft;
    private int mYearViewPaddingRight;
    private int mYearViewSchemeTextColor;
    private boolean mYearViewScrollable;
    private int mYearViewSelectTextColor;
    private int mYearViewWeekHeight;
    private int mYearViewWeekTextColor;
    private int mYearViewWeekTextSize;
    private boolean preventLongPressedSelected;

    CalendarViewDelegate(Context context, AttributeSet attributeSet) throws ClassNotFoundException {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CalendarView);
        LunarCalendar.init(context);
        this.mCalendarPadding = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_calendar_padding, 0.0f);
        this.mCalendarPaddingLeft = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_calendar_padding_left, 0.0f);
        this.mCalendarPaddingRight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_calendar_padding_right, 0.0f);
        int i = this.mCalendarPadding;
        if (i != 0) {
            this.mCalendarPaddingLeft = i;
            this.mCalendarPaddingRight = i;
        }
        this.mSchemeTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_scheme_text_color, -1);
        this.mSchemeLunarTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_scheme_lunar_text_color, -1973791);
        this.mSchemeThemeColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_scheme_theme_color, 1355796431);
        this.mMonthViewClassPath = typedArrayObtainStyledAttributes.getString(R.styleable.CalendarView_month_view);
        this.mYearViewClassPath = typedArrayObtainStyledAttributes.getString(R.styleable.CalendarView_year_view);
        this.mWeekViewClassPath = typedArrayObtainStyledAttributes.getString(R.styleable.CalendarView_week_view);
        this.mWeekBarClassPath = typedArrayObtainStyledAttributes.getString(R.styleable.CalendarView_week_bar_view);
        this.mWeekTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_week_text_size, CalendarUtil.dipToPx(context, 12.0f));
        this.mWeekBarHeight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_week_bar_height, CalendarUtil.dipToPx(context, 40.0f));
        this.mWeekLineMargin = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_week_line_margin, CalendarUtil.dipToPx(context, 0.0f));
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.CalendarView_scheme_text);
        this.mSchemeText = string;
        if (TextUtils.isEmpty(string)) {
            this.mSchemeText = "记";
        }
        this.mMonthViewScrollable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CalendarView_month_view_scrollable, true);
        this.mWeekViewScrollable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CalendarView_week_view_scrollable, true);
        this.mYearViewScrollable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CalendarView_year_view_scrollable, true);
        this.mDefaultCalendarSelectDay = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_month_view_auto_select_day, 0);
        this.mMonthViewShowMode = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_month_view_show_mode, 0);
        this.mWeekStart = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_week_start_with, 1);
        this.mSelectMode = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_select_mode, 0);
        this.mMaxMultiSelectSize = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_max_multi_select_size, Integer.MAX_VALUE);
        this.mMinSelectRange = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_min_select_range, -1);
        int i2 = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_max_select_range, -1);
        this.mMaxSelectRange = i2;
        setSelectRange(this.mMinSelectRange, i2);
        this.mWeekBackground = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_week_background, -1);
        this.mWeekLineBackground = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_week_line_background, 0);
        this.mYearViewBackground = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_background, -1);
        this.mWeekTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_week_text_color, -13421773);
        this.mCurDayTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_current_day_text_color, SupportMenu.CATEGORY_MASK);
        this.mCurDayLunarTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_current_day_lunar_text_color, SupportMenu.CATEGORY_MASK);
        this.mSelectedThemeColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_selected_theme_color, 1355796431);
        this.mSelectedTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_selected_text_color, -15658735);
        this.mSelectedLunarTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_selected_lunar_text_color, -15658735);
        this.mCurrentMonthTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_current_month_text_color, -15658735);
        this.mOtherMonthTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_other_month_text_color, -1973791);
        this.mCurMonthLunarTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_current_month_lunar_text_color, -1973791);
        this.mOtherMonthLunarTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_other_month_lunar_text_color, -1973791);
        this.mMinYear = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_min_year, 1971);
        this.mMaxYear = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_max_year, 2055);
        this.mMinYearMonth = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_min_year_month, 1);
        this.mMaxYearMonth = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_max_year_month, 12);
        this.mMinYearDay = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_min_year_day, 1);
        this.mMaxYearDay = typedArrayObtainStyledAttributes.getInt(R.styleable.CalendarView_max_year_day, -1);
        this.mDayTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_day_text_size, CalendarUtil.dipToPx(context, 16.0f));
        this.mLunarTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_lunar_text_size, CalendarUtil.dipToPx(context, 10.0f));
        this.mCalendarItemHeight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_calendar_height, CalendarUtil.dipToPx(context, 56.0f));
        this.isFullScreenCalendar = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CalendarView_calendar_match_parent, false);
        this.mYearViewMonthTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_year_view_month_text_size, CalendarUtil.dipToPx(context, 18.0f));
        this.mYearViewDayTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_year_view_day_text_size, CalendarUtil.dipToPx(context, 7.0f));
        this.mYearViewMonthTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_month_text_color, -15658735);
        this.mYearViewDayTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_day_text_color, -15658735);
        this.mYearViewSchemeTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_scheme_color, this.mSchemeThemeColor);
        this.mYearViewWeekTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_week_text_color, -13421773);
        this.mYearViewCurDayTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_current_day_text_color, this.mCurDayTextColor);
        this.mYearViewSelectTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CalendarView_year_view_select_text_color, -13421773);
        this.mYearViewWeekTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_year_view_week_text_size, CalendarUtil.dipToPx(context, 8.0f));
        this.mYearViewMonthHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_year_view_month_height, CalendarUtil.dipToPx(context, 32.0f));
        this.mYearViewWeekHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CalendarView_year_view_week_height, CalendarUtil.dipToPx(context, 0.0f));
        this.mYearViewPadding = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_padding, CalendarUtil.dipToPx(context, 12.0f));
        this.mYearViewPaddingLeft = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_padding_left, CalendarUtil.dipToPx(context, 12.0f));
        this.mYearViewPaddingRight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_padding_right, CalendarUtil.dipToPx(context, 12.0f));
        int i3 = this.mYearViewPadding;
        if (i3 != 0) {
            this.mYearViewPaddingLeft = i3;
            this.mYearViewPaddingRight = i3;
        }
        this.mYearViewMonthPaddingTop = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_month_padding_top, CalendarUtil.dipToPx(context, 4.0f));
        this.mYearViewMonthPaddingBottom = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_month_padding_bottom, CalendarUtil.dipToPx(context, 4.0f));
        this.mYearViewMonthPaddingLeft = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_month_padding_left, CalendarUtil.dipToPx(context, 4.0f));
        this.mYearViewMonthPaddingRight = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.CalendarView_year_view_month_padding_right, CalendarUtil.dipToPx(context, 4.0f));
        if (this.mMinYear <= MIN_YEAR) {
            this.mMinYear = MIN_YEAR;
        }
        if (this.mMaxYear >= MAX_YEAR) {
            this.mMaxYear = MAX_YEAR;
        }
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    private void init() throws ClassNotFoundException {
        Class<?> cls;
        Class<?> cls2;
        this.mCurrentDate = new Calendar();
        Date date = new Date();
        this.mCurrentDate.setYear(CalendarUtil.getDate("yyyy", date));
        this.mCurrentDate.setMonth(CalendarUtil.getDate("MM", date));
        this.mCurrentDate.setDay(CalendarUtil.getDate("dd", date));
        this.mCurrentDate.setCurrentDay(true);
        LunarCalendar.setupLunarCalendar(this.mCurrentDate);
        setRange(this.mMinYear, this.mMinYearMonth, this.mMaxYear, this.mMaxYearMonth);
        try {
            if (TextUtils.isEmpty(this.mWeekBarClassPath)) {
                cls2 = WeekBar.class;
                this.mWeekBarClass = cls2;
            } else {
                cls2 = Class.forName(this.mWeekBarClassPath);
            }
            this.mWeekBarClass = cls2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (TextUtils.isEmpty(this.mYearViewClassPath)) {
                cls = DefaultYearView.class;
                this.mYearViewClass = cls;
            } else {
                cls = Class.forName(this.mYearViewClassPath);
            }
            this.mYearViewClass = cls;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.mMonthViewClass = TextUtils.isEmpty(this.mMonthViewClassPath) ? DefaultMonthView.class : Class.forName(this.mMonthViewClassPath);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            this.mWeekViewClass = TextUtils.isEmpty(this.mWeekViewClassPath) ? DefaultWeekView.class : Class.forName(this.mWeekViewClassPath);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private void setRange(int i, int i2, int i3, int i4) {
        this.mMinYear = i;
        this.mMinYearMonth = i2;
        this.mMaxYear = i3;
        this.mMaxYearMonth = i4;
        if (i3 < this.mCurrentDate.getYear()) {
            this.mMaxYear = this.mCurrentDate.getYear();
        }
        if (this.mMaxYearDay == -1) {
            this.mMaxYearDay = CalendarUtil.getMonthDaysCount(this.mMaxYear, this.mMaxYearMonth);
        }
        this.mCurrentMonthViewItem = (((this.mCurrentDate.getYear() - this.mMinYear) * 12) + this.mCurrentDate.getMonth()) - this.mMinYearMonth;
    }

    void setRange(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mMinYear = i;
        this.mMinYearMonth = i2;
        this.mMinYearDay = i3;
        this.mMaxYear = i4;
        this.mMaxYearMonth = i5;
        this.mMaxYearDay = i6;
        if (i6 == -1) {
            this.mMaxYearDay = CalendarUtil.getMonthDaysCount(i4, i5);
        }
        this.mCurrentMonthViewItem = (((this.mCurrentDate.getYear() - this.mMinYear) * 12) + this.mCurrentDate.getMonth()) - this.mMinYearMonth;
    }

    String getSchemeText() {
        return this.mSchemeText;
    }

    int getCurDayTextColor() {
        return this.mCurDayTextColor;
    }

    int getCurDayLunarTextColor() {
        return this.mCurDayLunarTextColor;
    }

    int getWeekTextColor() {
        return this.mWeekTextColor;
    }

    int getSchemeTextColor() {
        return this.mSchemeTextColor;
    }

    int getSchemeLunarTextColor() {
        return this.mSchemeLunarTextColor;
    }

    int getOtherMonthTextColor() {
        return this.mOtherMonthTextColor;
    }

    int getCurrentMonthTextColor() {
        return this.mCurrentMonthTextColor;
    }

    int getSelectedTextColor() {
        return this.mSelectedTextColor;
    }

    int getSelectedLunarTextColor() {
        return this.mSelectedLunarTextColor;
    }

    int getCurrentMonthLunarTextColor() {
        return this.mCurMonthLunarTextColor;
    }

    int getOtherMonthLunarTextColor() {
        return this.mOtherMonthLunarTextColor;
    }

    int getSchemeThemeColor() {
        return this.mSchemeThemeColor;
    }

    int getSelectedThemeColor() {
        return this.mSelectedThemeColor;
    }

    int getWeekBackground() {
        return this.mWeekBackground;
    }

    int getYearViewBackground() {
        return this.mYearViewBackground;
    }

    int getWeekLineBackground() {
        return this.mWeekLineBackground;
    }

    int getWeekLineMargin() {
        return this.mWeekLineMargin;
    }

    Class<?> getMonthViewClass() {
        return this.mMonthViewClass;
    }

    Class<?> getWeekViewClass() {
        return this.mWeekViewClass;
    }

    Class<?> getWeekBarClass() {
        return this.mWeekBarClass;
    }

    Class<?> getYearViewClass() {
        return this.mYearViewClass;
    }

    String getYearViewClassPath() {
        return this.mYearViewClassPath;
    }

    int getWeekBarHeight() {
        return this.mWeekBarHeight;
    }

    int getMinYear() {
        return this.mMinYear;
    }

    int getMaxYear() {
        return this.mMaxYear;
    }

    int getDayTextSize() {
        return this.mDayTextSize;
    }

    int getLunarTextSize() {
        return this.mLunarTextSize;
    }

    int getCalendarItemHeight() {
        return this.mCalendarItemHeight;
    }

    void setCalendarItemHeight(int i) {
        this.mCalendarItemHeight = i;
    }

    int getMinYearMonth() {
        return this.mMinYearMonth;
    }

    int getMaxYearMonth() {
        return this.mMaxYearMonth;
    }

    int getYearViewMonthTextSize() {
        return this.mYearViewMonthTextSize;
    }

    int getYearViewMonthTextColor() {
        return this.mYearViewMonthTextColor;
    }

    int getYearViewWeekTextSize() {
        return this.mYearViewWeekTextSize;
    }

    int getYearViewWeekTextColor() {
        return this.mYearViewWeekTextColor;
    }

    int getYearViewSelectTextColor() {
        return this.mYearViewSelectTextColor;
    }

    int getYearViewCurDayTextColor() {
        return this.mYearViewCurDayTextColor;
    }

    int getYearViewPadding() {
        return this.mYearViewPadding;
    }

    int getYearViewPaddingLeft() {
        return this.mYearViewPaddingLeft;
    }

    int getYearViewPaddingRight() {
        return this.mYearViewPaddingRight;
    }

    int getYearViewMonthPaddingLeft() {
        return this.mYearViewMonthPaddingLeft;
    }

    int getYearViewMonthPaddingRight() {
        return this.mYearViewMonthPaddingRight;
    }

    int getYearViewMonthPaddingTop() {
        return this.mYearViewMonthPaddingTop;
    }

    int getYearViewMonthPaddingBottom() {
        return this.mYearViewMonthPaddingBottom;
    }

    int getYearViewWeekHeight() {
        return this.mYearViewWeekHeight;
    }

    int getYearViewMonthHeight() {
        return this.mYearViewMonthHeight;
    }

    int getYearViewDayTextColor() {
        return this.mYearViewDayTextColor;
    }

    int getYearViewDayTextSize() {
        return this.mYearViewDayTextSize;
    }

    int getYearViewSchemeTextColor() {
        return this.mYearViewSchemeTextColor;
    }

    int getMonthViewShowMode() {
        return this.mMonthViewShowMode;
    }

    void setMonthViewShowMode(int i) {
        this.mMonthViewShowMode = i;
    }

    void setTextColor(int i, int i2, int i3, int i4, int i5) {
        this.mCurDayTextColor = i;
        this.mOtherMonthTextColor = i3;
        this.mCurrentMonthTextColor = i2;
        this.mCurMonthLunarTextColor = i4;
        this.mOtherMonthLunarTextColor = i5;
    }

    void setSchemeColor(int i, int i2, int i3) {
        this.mSchemeThemeColor = i;
        this.mSchemeTextColor = i2;
        this.mSchemeLunarTextColor = i3;
    }

    void setYearViewTextColor(int i, int i2, int i3) {
        this.mYearViewMonthTextColor = i;
        this.mYearViewDayTextColor = i2;
        this.mYearViewSchemeTextColor = i3;
    }

    void setSelectColor(int i, int i2, int i3) {
        this.mSelectedThemeColor = i;
        this.mSelectedTextColor = i2;
        this.mSelectedLunarTextColor = i3;
    }

    void setThemeColor(int i, int i2) {
        this.mSelectedThemeColor = i;
        this.mSchemeThemeColor = i2;
    }

    boolean isMonthViewScrollable() {
        return this.mMonthViewScrollable;
    }

    boolean isWeekViewScrollable() {
        return this.mWeekViewScrollable;
    }

    boolean isYearViewScrollable() {
        return this.mYearViewScrollable;
    }

    void setMonthViewScrollable(boolean z) {
        this.mMonthViewScrollable = z;
    }

    void setWeekViewScrollable(boolean z) {
        this.mWeekViewScrollable = z;
    }

    void setYearViewScrollable(boolean z) {
        this.mYearViewScrollable = z;
    }

    int getWeekStart() {
        return this.mWeekStart;
    }

    void setWeekStart(int i) {
        this.mWeekStart = i;
    }

    void setDefaultCalendarSelectDay(int i) {
        this.mDefaultCalendarSelectDay = i;
    }

    int getDefaultCalendarSelectDay() {
        return this.mDefaultCalendarSelectDay;
    }

    int getWeekTextSize() {
        return this.mWeekTextSize;
    }

    int getSelectMode() {
        return this.mSelectMode;
    }

    void setSelectMode(int i) {
        this.mSelectMode = i;
    }

    int getMinSelectRange() {
        return this.mMinSelectRange;
    }

    int getMaxSelectRange() {
        return this.mMaxSelectRange;
    }

    int getMaxMultiSelectSize() {
        return this.mMaxMultiSelectSize;
    }

    void setMaxMultiSelectSize(int i) {
        this.mMaxMultiSelectSize = i;
    }

    final void setSelectRange(int i, int i2) {
        if (i > i2 && i2 > 0) {
            this.mMaxSelectRange = i;
            this.mMinSelectRange = i;
            return;
        }
        if (i <= 0) {
            this.mMinSelectRange = -1;
        } else {
            this.mMinSelectRange = i;
        }
        if (i2 <= 0) {
            this.mMaxSelectRange = -1;
        } else {
            this.mMaxSelectRange = i2;
        }
    }

    Calendar getCurrentDay() {
        return this.mCurrentDate;
    }

    void updateCurrentDay() {
        Date date = new Date();
        this.mCurrentDate.setYear(CalendarUtil.getDate("yyyy", date));
        this.mCurrentDate.setMonth(CalendarUtil.getDate("MM", date));
        this.mCurrentDate.setDay(CalendarUtil.getDate("dd", date));
        LunarCalendar.setupLunarCalendar(this.mCurrentDate);
    }

    int getCalendarPadding() {
        return this.mCalendarPadding;
    }

    void setCalendarPadding(int i) {
        this.mCalendarPadding = i;
        this.mCalendarPaddingLeft = i;
        this.mCalendarPaddingRight = i;
    }

    int getCalendarPaddingLeft() {
        return this.mCalendarPaddingLeft;
    }

    void setCalendarPaddingLeft(int i) {
        this.mCalendarPaddingLeft = i;
    }

    int getCalendarPaddingRight() {
        return this.mCalendarPaddingRight;
    }

    void setCalendarPaddingRight(int i) {
        this.mCalendarPaddingRight = i;
    }

    void setPreventLongPressedSelected(boolean z) {
        this.preventLongPressedSelected = z;
    }

    void setMonthViewClass(Class<?> cls) {
        this.mMonthViewClass = cls;
    }

    void setWeekBarClass(Class<?> cls) {
        this.mWeekBarClass = cls;
    }

    void setWeekViewClass(Class<?> cls) {
        this.mWeekViewClass = cls;
    }

    boolean isPreventLongPressedSelected() {
        return this.preventLongPressedSelected;
    }

    void clearSelectedScheme() {
        this.mSelectedCalendar.clearScheme();
    }

    int getMinYearDay() {
        return this.mMinYearDay;
    }

    int getMaxYearDay() {
        return this.mMaxYearDay;
    }

    boolean isFullScreenCalendar() {
        return this.isFullScreenCalendar;
    }

    final void updateSelectCalendarScheme() {
        Map<String, Calendar> map = this.mSchemeDatesMap;
        if (map != null && map.size() > 0) {
            String string = this.mSelectedCalendar.toString();
            if (this.mSchemeDatesMap.containsKey(string)) {
                this.mSelectedCalendar.mergeScheme(this.mSchemeDatesMap.get(string), getSchemeText());
                return;
            }
            return;
        }
        clearSelectedScheme();
    }

    final void updateCalendarScheme(Calendar calendar) {
        Map<String, Calendar> map;
        if (calendar == null || (map = this.mSchemeDatesMap) == null || map.size() == 0) {
            return;
        }
        String string = calendar.toString();
        if (this.mSchemeDatesMap.containsKey(string)) {
            calendar.mergeScheme(this.mSchemeDatesMap.get(string), getSchemeText());
        }
    }

    Calendar createCurrentDate() {
        Calendar calendar = new Calendar();
        calendar.setYear(this.mCurrentDate.getYear());
        calendar.setWeek(this.mCurrentDate.getWeek());
        calendar.setMonth(this.mCurrentDate.getMonth());
        calendar.setDay(this.mCurrentDate.getDay());
        calendar.setCurrentDay(true);
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    final Calendar getMinRangeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setYear(this.mMinYear);
        calendar.setMonth(this.mMinYearMonth);
        calendar.setDay(this.mMinYearDay);
        calendar.setCurrentDay(calendar.equals(this.mCurrentDate));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    final Calendar getMaxRangeCalendar() {
        Calendar calendar = new Calendar();
        calendar.setYear(this.mMaxYear);
        calendar.setMonth(this.mMaxYearMonth);
        calendar.setDay(this.mMaxYearDay);
        calendar.setCurrentDay(calendar.equals(this.mCurrentDate));
        LunarCalendar.setupLunarCalendar(calendar);
        return calendar;
    }

    final void addSchemesFromMap(List<Calendar> list) {
        Map<String, Calendar> map = this.mSchemeDatesMap;
        if (map == null || map.size() == 0) {
            return;
        }
        for (Calendar calendar : list) {
            if (this.mSchemeDatesMap.containsKey(calendar.toString())) {
                Calendar calendar2 = this.mSchemeDatesMap.get(calendar.toString());
                if (calendar2 != null) {
                    calendar.setScheme(TextUtils.isEmpty(calendar2.getScheme()) ? getSchemeText() : calendar2.getScheme());
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

    final void addSchemes(Map<String, Calendar> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        if (this.mSchemeDatesMap == null) {
            this.mSchemeDatesMap = new HashMap();
        }
        for (String str : map.keySet()) {
            this.mSchemeDatesMap.remove(str);
            Calendar calendar = map.get(str);
            if (calendar != null) {
                this.mSchemeDatesMap.put(str, calendar);
            }
        }
    }

    final void clearSelectRange() {
        this.mSelectedStartRangeCalendar = null;
        this.mSelectedEndRangeCalendar = null;
    }

    final List<Calendar> getSelectCalendarRange() {
        if (this.mSelectMode != 2) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (this.mSelectedStartRangeCalendar != null && this.mSelectedEndRangeCalendar != null) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(this.mSelectedStartRangeCalendar.getYear(), this.mSelectedStartRangeCalendar.getMonth() - 1, this.mSelectedStartRangeCalendar.getDay());
            calendar.set(this.mSelectedEndRangeCalendar.getYear(), this.mSelectedEndRangeCalendar.getMonth() - 1, this.mSelectedEndRangeCalendar.getDay());
            long timeInMillis = calendar.getTimeInMillis();
            for (long timeInMillis2 = calendar.getTimeInMillis(); timeInMillis2 <= timeInMillis; timeInMillis2 += 86400000) {
                calendar.setTimeInMillis(timeInMillis2);
                Calendar calendar2 = new Calendar();
                calendar2.setYear(calendar.get(1));
                calendar2.setMonth(calendar.get(2) + 1);
                calendar2.setDay(calendar.get(5));
                LunarCalendar.setupLunarCalendar(calendar2);
                updateCalendarScheme(calendar2);
                CalendarView.OnCalendarInterceptListener onCalendarInterceptListener = this.mCalendarInterceptListener;
                if (onCalendarInterceptListener == null || !onCalendarInterceptListener.onCalendarIntercept(calendar2)) {
                    arrayList.add(calendar2);
                }
            }
            addSchemesFromMap(arrayList);
        }
        return arrayList;
    }
}
