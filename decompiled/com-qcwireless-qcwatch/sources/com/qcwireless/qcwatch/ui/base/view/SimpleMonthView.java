package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

/* loaded from: classes3.dex */
public class SimpleMonthView extends MonthView {
    private int mRadius;

    public SimpleMonthView(Context context) {
        super(context);
    }

    @Override // com.haibin.calendarview.BaseMonthView, com.haibin.calendarview.BaseView
    protected void onPreviewHook() {
        this.mRadius = (Math.min(this.mItemWidth, this.mItemHeight) / 5) * 2;
    }

    @Override // com.haibin.calendarview.MonthView
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int i = x + (this.mItemWidth / 2);
        int i2 = y + (this.mItemHeight / 2);
        this.mSelectedPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(i, i2, this.mRadius + 2, this.mSelectedPaint);
        return true;
    }

    @Override // com.haibin.calendarview.MonthView
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        canvas.drawCircle(x + (this.mItemWidth / 2), y + (this.mItemHeight / 2), this.mRadius, this.mSchemePaint);
    }

    @Override // com.haibin.calendarview.MonthView
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        Paint paint;
        Paint paint2;
        float f = this.mTextBaseLine + y;
        int i = x + (this.mItemWidth / 2);
        if (isSelected) {
            canvas.drawText(String.valueOf(calendar.getDay()), i, f, this.mSelectTextPaint);
            return;
        }
        if (hasScheme) {
            String strValueOf = String.valueOf(calendar.getDay());
            float f2 = i;
            if (calendar.isCurrentDay()) {
                paint2 = this.mCurDayTextPaint;
            } else {
                paint2 = calendar.isCurrentMonth() ? this.mSchemeTextPaint : this.mOtherMonthTextPaint;
            }
            canvas.drawText(strValueOf, f2, f, paint2);
            return;
        }
        String strValueOf2 = String.valueOf(calendar.getDay());
        float f3 = i;
        if (calendar.isCurrentDay()) {
            paint = this.mCurDayTextPaint;
        } else {
            paint = calendar.isCurrentMonth() ? this.mCurMonthTextPaint : this.mOtherMonthTextPaint;
        }
        canvas.drawText(strValueOf2, f3, f, paint);
    }
}
