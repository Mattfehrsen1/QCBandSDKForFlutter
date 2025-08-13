package com.haibin.calendarview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes2.dex */
final class YearViewAdapter extends BaseRecyclerAdapter<Month> {
    private CalendarViewDelegate mDelegate;
    private int mItemHeight;
    private int mItemWidth;

    YearViewAdapter(Context context) {
        super(context);
    }

    final void setup(CalendarViewDelegate calendarViewDelegate) {
        this.mDelegate = calendarViewDelegate;
    }

    final void setYearViewSize(int i, int i2) {
        this.mItemWidth = i;
        this.mItemHeight = i2;
    }

    @Override // com.haibin.calendarview.BaseRecyclerAdapter
    RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup viewGroup, int i) {
        YearView defaultYearView;
        if (TextUtils.isEmpty(this.mDelegate.getYearViewClassPath())) {
            defaultYearView = new DefaultYearView(this.mContext);
        } else {
            try {
                defaultYearView = (YearView) this.mDelegate.getYearViewClass().getConstructor(Context.class).newInstance(this.mContext);
            } catch (Exception e) {
                e.printStackTrace();
                defaultYearView = new DefaultYearView(this.mContext);
            }
        }
        defaultYearView.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
        return new YearViewHolder(defaultYearView, this.mDelegate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.haibin.calendarview.BaseRecyclerAdapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Month month, int i) {
        YearView yearView = ((YearViewHolder) viewHolder).mYearView;
        yearView.init(month.getYear(), month.getMonth());
        yearView.measureSize(this.mItemWidth, this.mItemHeight);
    }

    private static class YearViewHolder extends RecyclerView.ViewHolder {
        YearView mYearView;

        YearViewHolder(View view, CalendarViewDelegate calendarViewDelegate) {
            super(view);
            YearView yearView = (YearView) view;
            this.mYearView = yearView;
            yearView.setup(calendarViewDelegate);
        }
    }
}
