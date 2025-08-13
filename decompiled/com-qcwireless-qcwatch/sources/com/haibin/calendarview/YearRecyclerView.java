package com.haibin.calendarview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.haibin.calendarview.BaseRecyclerAdapter;

/* loaded from: classes2.dex */
public final class YearRecyclerView extends RecyclerView {
    private YearViewAdapter mAdapter;
    private CalendarViewDelegate mDelegate;
    private OnMonthSelectedListener mListener;

    interface OnMonthSelectedListener {
        void onMonthSelected(int i, int i2);
    }

    public YearRecyclerView(Context context) {
        this(context, null);
    }

    public YearRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdapter = new YearViewAdapter(context);
        setLayoutManager(new GridLayoutManager(context, 3));
        setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: com.haibin.calendarview.YearRecyclerView.1
            @Override // com.haibin.calendarview.BaseRecyclerAdapter.OnItemClickListener
            public void onItemClick(int i, long j) {
                Month item;
                if (YearRecyclerView.this.mListener == null || YearRecyclerView.this.mDelegate == null || (item = YearRecyclerView.this.mAdapter.getItem(i)) == null || !CalendarUtil.isMonthInRange(item.getYear(), item.getMonth(), YearRecyclerView.this.mDelegate.getMinYear(), YearRecyclerView.this.mDelegate.getMinYearMonth(), YearRecyclerView.this.mDelegate.getMaxYear(), YearRecyclerView.this.mDelegate.getMaxYearMonth())) {
                    return;
                }
                YearRecyclerView.this.mListener.onMonthSelected(item.getYear(), item.getMonth());
                if (YearRecyclerView.this.mDelegate.mYearViewChangeListener != null) {
                    YearRecyclerView.this.mDelegate.mYearViewChangeListener.onYearViewChange(true);
                }
            }
        });
    }

    final void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
        this.mAdapter.setup(calendarViewDelegate);
    }

    final void init(int i) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        for (int i2 = 1; i2 <= 12; i2++) {
            calendar.set(i, i2 - 1, 1);
            int monthDaysCount = CalendarUtil.getMonthDaysCount(i, i2);
            Month month = new Month();
            month.setDiff(CalendarUtil.getMonthViewStartDiff(i, i2, this.mDelegate.getWeekStart()));
            month.setCount(monthDaysCount);
            month.setMonth(i2);
            month.setYear(i);
            this.mAdapter.addItem(month);
        }
    }

    final void updateWeekStart() {
        for (Month month : this.mAdapter.getItems()) {
            month.setDiff(CalendarUtil.getMonthViewStartDiff(month.getYear(), month.getMonth(), this.mDelegate.getWeekStart()));
        }
    }

    final void updateStyle() {
        for (int i = 0; i < getChildCount(); i++) {
            YearView yearView = (YearView) getChildAt(i);
            yearView.updateStyle();
            yearView.invalidate();
        }
    }

    final void setOnMonthSelectedListener(OnMonthSelectedListener onMonthSelectedListener) {
        this.mListener = onMonthSelectedListener;
    }

    void notifyAdapterDataSetChanged() {
        if (getAdapter() == null) {
            return;
        }
        getAdapter().notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i2);
        this.mAdapter.setYearViewSize(View.MeasureSpec.getSize(i) / 3, size / 4);
    }
}
