package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QSleepBarChart;
import java.util.ArrayList;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QSleepHomeBarChart extends View {
    public static final int Type_Sleep_Awake = 3;
    public static final int Type_Sleep_Deep = 1;
    public static final int Type_Sleep_Light = 2;
    public static final int Type_Sleep_Rapid = 5;
    protected int bottomOffset;
    private Context context;
    private List<QSleepBarChart.SleepDataBean> data;
    private float height;
    private int leftRightOffset;
    private long sleepEnd;
    private Paint sleepPaint;
    private long sleepStart;
    private float width;

    public QSleepHomeBarChart(Context context) {
        super(context);
        this.data = new ArrayList();
        this.leftRightOffset = 10;
        this.bottomOffset = 50;
        this.context = context;
        init(context, null);
    }

    public QSleepHomeBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.data = new ArrayList();
        this.leftRightOffset = 10;
        this.bottomOffset = 50;
        this.context = context;
        init(context, attrs);
    }

    public QSleepHomeBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.data = new ArrayList();
        this.leftRightOffset = 10;
        this.bottomOffset = 50;
        this.context = context;
        init(context, attrs);
    }

    public QSleepHomeBarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.data = new ArrayList();
        this.leftRightOffset = 10;
        this.bottomOffset = 50;
        this.context = context;
        init(context, attrs);
    }

    public List<QSleepBarChart.SleepDataBean> getData() {
        return this.data;
    }

    public void setData(long sleepStart, long sleepEnd, List<QSleepBarChart.SleepDataBean> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        this.data = data;
        this.sleepStart = sleepStart;
        this.sleepEnd = sleepEnd;
        postInvalidate();
    }

    private void init(Context context, AttributeSet attrs) {
        Paint paint = new Paint();
        this.sleepPaint = paint;
        paint.setAntiAlias(true);
        this.sleepPaint.setStyle(Paint.Style.FILL);
        this.sleepPaint.setStrokeWidth(1.5f);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(200, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = 100.0f;
        } else {
            this.height = getMySize(100, heightMeasureSpec);
        }
        postInvalidate();
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = (int) ((this.height - this.bottomOffset) / 5.0f);
        for (QSleepBarChart.SleepDataBean sleepDataBean : this.data) {
            if (sleepDataBean.getType() == 1) {
                this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_1));
                float f = this.leftRightOffset;
                float sleepStart = sleepDataBean.getSleepStart() - this.sleepStart;
                float f2 = this.width;
                int i2 = this.leftRightOffset;
                long sleepEnd = sleepDataBean.getSleepEnd();
                long j = this.sleepStart;
                canvas.drawRect(f + ((sleepStart * (f2 - (i2 * 2))) / (this.sleepEnd - r8)), i, i2 + (((sleepEnd - j) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - j)), i * 5, this.sleepPaint);
            } else if (sleepDataBean.getType() == 2) {
                this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_2));
                float f3 = this.leftRightOffset;
                float sleepStart2 = sleepDataBean.getSleepStart() - this.sleepStart;
                float f4 = this.width;
                int i3 = this.leftRightOffset;
                long sleepEnd2 = sleepDataBean.getSleepEnd();
                long j2 = this.sleepStart;
                canvas.drawRect(f3 + ((sleepStart2 * (f4 - (i3 * 2))) / (this.sleepEnd - r8)), i * 2, i3 + (((sleepEnd2 - j2) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - j2)), i * 5, this.sleepPaint);
            } else if (sleepDataBean.getType() == 3) {
                this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_3));
                float f5 = this.leftRightOffset;
                float sleepStart3 = sleepDataBean.getSleepStart() - this.sleepStart;
                float f6 = this.width;
                int i4 = this.leftRightOffset;
                long sleepEnd3 = sleepDataBean.getSleepEnd();
                long j3 = this.sleepStart;
                canvas.drawRect(f5 + ((sleepStart3 * (f6 - (i4 * 2))) / (this.sleepEnd - r8)), i * 3, i4 + (((sleepEnd3 - j3) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - j3)), i * 5, this.sleepPaint);
            } else if (sleepDataBean.getType() == 5) {
                this.sleepPaint.setColor(SkinCompatResources.getColor(this.context, R.color.q_view_sleep_6));
                float f7 = this.leftRightOffset;
                float sleepStart4 = sleepDataBean.getSleepStart() - this.sleepStart;
                float f8 = this.width;
                int i5 = this.leftRightOffset;
                long sleepEnd4 = sleepDataBean.getSleepEnd();
                long j4 = this.sleepStart;
                canvas.drawRect(f7 + ((sleepStart4 * (f8 - (i5 * 2))) / (this.sleepEnd - r8)), i * 2, i5 + (((sleepEnd4 - j4) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - j4)), i * 5, this.sleepPaint);
            }
        }
    }

    public long getNumberRecent(long[] intarray, long number) {
        long jAbs = Math.abs(number - intarray[0]);
        long j = intarray[0];
        for (long j2 : intarray) {
            long jAbs2 = Math.abs(number - j2);
            if (jAbs2 <= jAbs) {
                j = j2;
                jAbs = jAbs2;
            }
        }
        return j;
    }
}
