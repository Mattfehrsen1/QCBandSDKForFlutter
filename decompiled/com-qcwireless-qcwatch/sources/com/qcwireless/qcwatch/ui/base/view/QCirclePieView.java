package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QCirclePieView extends View {
    private int a;
    private Context context;
    private int height;
    private List<CakeValue> mPieItems;
    private int mSweep;
    private int pieCenterX;
    private int pieCenterY;
    private RectF pieOval;
    private RectF pieOvalIn;
    private RectF pieOvalLine;
    private RectF pieOvalLineIn;
    private Paint piePaint;
    private Paint piePaintIn;
    private Paint piePaintLine;
    private int sweep;
    private float totalValue;
    private int width;

    public QCirclePieView(Context context) {
        super(context);
        this.a = 3;
        this.mSweep = 0;
        this.sweep = 0;
        this.context = context;
        init(context);
    }

    public QCirclePieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.a = 3;
        this.mSweep = 0;
        this.sweep = 0;
        this.context = context;
        init(context);
    }

    public QCirclePieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.a = 3;
        this.mSweep = 0;
        this.sweep = 0;
        this.context = context;
        init(context);
    }

    public QCirclePieView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.a = 3;
        this.mSweep = 0;
        this.sweep = 0;
        this.context = context;
        init(context);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize((int) dp2px(this.context, 150.0f), widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) dp2px(this.context, 150.0f);
        } else {
            this.height = getMySize((int) dp2px(this.context, 150.0f), heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        init(this.context);
    }

    private void init(Context context) {
        int iDp2px = (int) dp2px(context, 18.0f);
        int iDp2px2 = (int) dp2px(context, 50.0f);
        int iDp2px3 = (int) dp2px(context, 50.5f);
        this.pieCenterX = this.width / 2;
        this.pieCenterY = this.height / 2;
        RectF rectF = new RectF();
        this.pieOval = rectF;
        rectF.left = this.pieCenterX - iDp2px2;
        this.pieOval.top = this.pieCenterY - iDp2px2;
        this.pieOval.right = this.pieCenterX + iDp2px2;
        this.pieOval.bottom = this.pieCenterY + iDp2px2;
        RectF rectF2 = new RectF();
        this.pieOvalLine = rectF2;
        rectF2.left = this.pieCenterX - iDp2px3;
        this.pieOvalLine.top = this.pieCenterY - iDp2px3;
        this.pieOvalLine.right = this.pieCenterX + iDp2px3;
        this.pieOvalLine.bottom = this.pieCenterY + iDp2px3;
        RectF rectF3 = new RectF();
        this.pieOvalIn = rectF3;
        float f = iDp2px;
        rectF3.left = this.pieOval.left + f;
        this.pieOvalIn.top = this.pieOval.top + f;
        this.pieOvalIn.right = this.pieOval.right - f;
        this.pieOvalIn.bottom = this.pieOval.bottom - f;
        Paint paint = new Paint();
        this.piePaint = paint;
        paint.setAntiAlias(true);
        this.piePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.piePaintIn = paint2;
        paint2.setAntiAlias(true);
        this.piePaintIn.setStyle(Paint.Style.FILL);
        Paint paint3 = new Paint();
        this.piePaintLine = paint3;
        paint3.setAntiAlias(true);
        this.piePaintLine.setStyle(Paint.Style.STROKE);
        this.piePaintLine.setStrokeWidth(3.0f);
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<CakeValue> list = this.mPieItems;
        if (list != null && list.size() > 0) {
            this.mSweep = this.sweep + this.a;
            for (int i = 0; i < this.mPieItems.size(); i++) {
                this.piePaint.setColor(this.mPieItems.get(i).getColors());
                this.piePaintLine.setColor(this.mPieItems.get(i).getLineColors());
                canvas.drawArc(this.pieOvalLine, this.mSweep, this.mPieItems.get(i).getSw(), true, this.piePaintLine);
                canvas.drawArc(this.pieOval, this.mSweep, this.mPieItems.get(i).getSw(), true, this.piePaint);
                canvas.drawArc(this.pieOvalIn, this.mSweep, this.mPieItems.get(i).getSw(), true, this.piePaintLine);
                this.mSweep = this.mSweep + this.mPieItems.get(i).getSw() + this.a;
            }
        } else {
            List<CakeValue> list2 = this.mPieItems;
            if (list2 != null && list2.size() == 0) {
                this.piePaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_circle_pie_1));
                canvas.drawArc(this.pieOval, 0.0f, 360.0f, true, this.piePaint);
            }
        }
        this.piePaintIn.setColor(SkinCompatResources.getColor(this.context, R.color.q_view_circle_pie_12));
        canvas.drawArc(this.pieOvalIn, 0.0f, 360.0f, true, this.piePaintIn);
    }

    public void setData(List<CakeValue> pieItems) {
        ArrayList arrayList = new ArrayList();
        this.totalValue = 0.0f;
        Iterator<CakeValue> it = pieItems.iterator();
        while (it.hasNext()) {
            this.totalValue = (float) (this.totalValue + it.next().getItemValue());
        }
        for (int i = 0; i < pieItems.size(); i++) {
            if (pieItems.get(i).getItemValue() != 0.0d) {
                arrayList.add(pieItems.get(i));
            }
        }
        this.mPieItems = arrayList;
        int size = arrayList.size() > 1 ? this.a * this.mPieItems.size() : 0;
        for (int i2 = 0; i2 < this.mPieItems.size(); i2++) {
            this.piePaint.setColor(this.mPieItems.get(i2).getColors());
            this.piePaintLine.setColor(this.mPieItems.get(i2).getLineColors());
            this.mPieItems.get(i2).setSw(Integer.parseInt(new DecimalFormat("0").format((this.mPieItems.get(i2).getItemValue() / this.totalValue) * (360 - size))));
        }
        invalidate();
    }

    public class CakeValue {
        private int colors;
        private int itemType;
        private int lineColors;
        private int sw;
        private double value;

        public CakeValue(int itemType, double itemValue, int color, int lineColors) {
            this.itemType = itemType;
            this.value = itemValue;
            this.colors = color;
            this.lineColors = lineColors;
        }

        public int getLineColors() {
            return this.lineColors;
        }

        public void setLineColors(int lineColors) {
            this.lineColors = lineColors;
        }

        public int getItemType() {
            return this.itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public double getItemValue() {
            return this.value;
        }

        public void setItemValue(double itemValue) {
            this.value = itemValue;
        }

        public int getColors() {
            return this.colors;
        }

        public void setColors(int color) {
            this.colors = color;
        }

        public int getSw() {
            return this.sw;
        }

        public void setSw(int sw) {
            this.sw = sw;
        }
    }

    public float px2dp(Context context, float px) {
        return (px / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public void dataPressureInit(int[] data) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CakeValue(1, data[0], ContextCompat.getColor(this.context, R.color.pressure_common_8), ContextCompat.getColor(this.context, R.color.pressure_common_7)));
        arrayList.add(new CakeValue(2, data[1], ContextCompat.getColor(this.context, R.color.pressure_common_10), ContextCompat.getColor(this.context, R.color.pressure_common_9)));
        arrayList.add(new CakeValue(3, data[2], ContextCompat.getColor(this.context, R.color.pressure_common_12), ContextCompat.getColor(this.context, R.color.pressure_common_11)));
        arrayList.add(new CakeValue(4, data[3], ContextCompat.getColor(this.context, R.color.pressure_common_14), ContextCompat.getColor(this.context, R.color.pressure_common_13)));
        arrayList.add(new CakeValue(5, data[4], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_10), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_11)));
        setData(arrayList);
    }

    public void dataInit(int[] data) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CakeValue(1, data[0], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_2), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_3)));
        arrayList.add(new CakeValue(2, data[1], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_4), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_5)));
        arrayList.add(new CakeValue(3, data[2], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_6), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_7)));
        arrayList.add(new CakeValue(4, data[3], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_8), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_9)));
        arrayList.add(new CakeValue(5, data[4], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_10), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_11)));
        setData(arrayList);
    }

    public void dataHrvInit(int[] data) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CakeValue(1, data[0], ContextCompat.getColor(this.context, R.color.q_view_hrv_5), ContextCompat.getColor(this.context, R.color.q_view_hrv_5)));
        arrayList.add(new CakeValue(2, data[1], ContextCompat.getColor(this.context, R.color.q_view_hrv_6), ContextCompat.getColor(this.context, R.color.q_view_hrv_6)));
        arrayList.add(new CakeValue(3, data[2], ContextCompat.getColor(this.context, R.color.q_view_hrv_7), ContextCompat.getColor(this.context, R.color.q_view_hrv_7)));
        arrayList.add(new CakeValue(4, data[3], ContextCompat.getColor(this.context, R.color.q_view_hrv_8), ContextCompat.getColor(this.context, R.color.q_view_hrv_8)));
        arrayList.add(new CakeValue(5, data[4], ContextCompat.getColor(this.context, R.color.q_view_circle_pie_10), ContextCompat.getColor(this.context, R.color.q_view_circle_pie_11)));
        setData(arrayList);
    }
}
