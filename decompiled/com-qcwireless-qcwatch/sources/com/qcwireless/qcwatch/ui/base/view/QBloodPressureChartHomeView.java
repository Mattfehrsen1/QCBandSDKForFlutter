package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QBloodPressureChartHomeView extends View {
    private int bottomOffset;
    private List<BpDataBean> bpList;
    int[] colorsLine;
    private Context context;
    private BpDataBean currBean;
    private Paint dotPaint;
    private Map<String, BpDataBean> hashData;
    private int height;
    private int leftRightOffset;
    private int lineHeight;
    private OnSelectedListener listener;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    private Path path1;
    private int[] timeArray;
    private int width;
    private int xMax;
    private Path yLinePath;
    private int yMax;

    public interface OnSelectedListener {
        void onSelected(BpDataBean bean);
    }

    public void setSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public QBloodPressureChartHomeView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 30;
        this.lineHeight = 120;
        init(context, null);
    }

    public QBloodPressureChartHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 30;
        this.lineHeight = 120;
        init(context, attrs);
    }

    public QBloodPressureChartHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 30;
        this.lineHeight = 120;
        init(context, attrs);
    }

    public QBloodPressureChartHomeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 30;
        this.lineHeight = 120;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_blood_pressure_3), ContextCompat.getColor(context, R.color.q_view_blood_pressure_2), ContextCompat.getColor(context, R.color.q_view_blood_pressure_3)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_blood_pressure_2));
        this.paint.setStrokeWidth(DpUtils.dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setStrokeWidth(DpUtils.dp2px(context, 0.5f));
        this.paintFill.setColor(ContextCompat.getColor(context, R.color.q_view_blood_pressure_4));
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_blood_pressure_5));
        this.paintY.setStrokeWidth(DpUtils.dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint();
        this.dotPaint = paint4;
        paint4.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.path = new Path();
        this.path1 = new Path();
        this.yLinePath = new Path();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(200, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) DpUtils.dp2px(this.context, 60.0f);
        } else {
            this.height = getMySize(200, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        if (this.bpList.size() > 0) {
            setData(this.bpList);
        }
        postInvalidate();
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.bpList.size() > 1) {
            canvas.drawPath(this.path1, this.paintFill);
            canvas.drawPath(this.path, this.paint);
        }
        for (BpDataBean bpDataBean : this.bpList) {
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.color_FFFFFF));
            canvas.drawCircle(bpDataBean.sbpX, bpDataBean.sbpY, DpUtils.dp2px(this.context, 1.5f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_blood_pressure_2));
            canvas.drawCircle(bpDataBean.sbpX, bpDataBean.sbpY, DpUtils.dp2px(this.context, 1.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.color_FFFFFF));
            canvas.drawCircle(bpDataBean.dbpX, bpDataBean.dbpY, DpUtils.dp2px(this.context, 1.5f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_blood_pressure_4));
            canvas.drawCircle(bpDataBean.dbpX, bpDataBean.dbpY, DpUtils.dp2px(this.context, 1.0f), this.dotPaint);
        }
    }

    public void setData(List<BpDataBean> list) {
        this.bpList = list;
        this.path.reset();
        this.hashData.clear();
        if (this.width > 0) {
            filterData(list);
        } else {
            XLog.i("width=0");
        }
        postInvalidate();
    }

    private void filterData(List<BpDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            BpDataBean bpDataBean = list.get(i);
            bpDataBean.sbp -= 50;
            bpDataBean.dbp -= 50;
            int min = bpDataBean.getMin();
            int i2 = this.width;
            int i3 = this.leftRightOffset;
            bpDataBean.dbpX = ((min * (i2 - (i3 * 2))) / this.xMax) + i3;
            bpDataBean.dbpY = (this.lineHeight - this.bottomOffset) - ((bpDataBean.getDbp() * (this.height - this.bottomOffset)) / this.yMax);
            int min2 = bpDataBean.getMin();
            int i4 = this.width;
            int i5 = this.leftRightOffset;
            bpDataBean.sbpX = ((min2 * (i4 - (i5 * 2))) / this.xMax) + i5;
            bpDataBean.sbpY = (this.lineHeight - this.bottomOffset) - ((bpDataBean.getSbp() * (this.height - this.bottomOffset)) / this.yMax);
        }
        this.bpList = list;
        this.path.reset();
        this.path1.reset();
        this.hashData.clear();
        int min3 = 1025;
        int min4 = 0;
        for (int i6 = 0; i6 < this.bpList.size(); i6++) {
            BpDataBean bpDataBean2 = this.bpList.get(i6);
            if (bpDataBean2.getMin() < min3) {
                min3 = bpDataBean2.getMin();
            }
            if (bpDataBean2.getMin() > min4) {
                min4 = bpDataBean2.getMin();
            }
        }
        if (this.bpList.size() > 0) {
            this.timeArray = new int[this.bpList.size()];
        } else {
            this.timeArray = new int[1440];
            for (int i7 = 0; i7 < 1440; i7++) {
                this.timeArray[i7] = i7;
            }
        }
        for (int i8 = 0; i8 < this.bpList.size(); i8++) {
            BpDataBean bpDataBean3 = this.bpList.get(i8);
            this.timeArray[i8] = bpDataBean3.getMin();
            this.hashData.put(bpDataBean3.getMin() + "", bpDataBean3);
            if (i8 == 0) {
                this.path.moveTo(bpDataBean3.sbpX, bpDataBean3.sbpY);
                this.path.lineTo(bpDataBean3.sbpX, bpDataBean3.sbpY);
                this.path1.moveTo(bpDataBean3.dbpX, bpDataBean3.dbpY);
            } else {
                this.path.lineTo(bpDataBean3.sbpX, bpDataBean3.sbpY);
            }
            this.path1.lineTo(bpDataBean3.dbpX, bpDataBean3.dbpY);
        }
        postInvalidate();
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    public static class BpDataBean {
        private int dbp;
        public int dbpX;
        public int dbpY;
        private int min;
        private int sbp;
        public int sbpX;
        public int sbpY;
        private long unixTime;

        public BpDataBean(int min, int sbp, int dbp) {
            this.min = min;
            this.sbp = sbp;
            this.dbp = dbp;
        }

        public BpDataBean(int min, int sbp, int dbp, long unixTime) {
            this.min = min;
            this.sbp = sbp;
            this.dbp = dbp;
            this.unixTime = unixTime;
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getSbp() {
            return this.sbp;
        }

        public void setSbp(int sbp) {
            this.sbp = sbp;
        }

        public int getDbp() {
            return this.dbp;
        }

        public void setDbp(int dbp) {
            this.dbp = dbp;
        }

        public int getSbpX() {
            return this.sbpX;
        }

        public void setSbpX(int sbpX) {
            this.sbpX = sbpX;
        }

        public int getSbpY() {
            return this.sbpY;
        }

        public void setSbpY(int sbpY) {
            this.sbpY = sbpY;
        }

        public int getDbpX() {
            return this.dbpX;
        }

        public void setDbpX(int dbpX) {
            this.dbpX = dbpX;
        }

        public int getDbpY() {
            return this.dbpY;
        }

        public void setDbpY(int dbpY) {
            this.dbpY = dbpY;
        }

        public long getUnixTime() {
            return this.unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }
    }
}
