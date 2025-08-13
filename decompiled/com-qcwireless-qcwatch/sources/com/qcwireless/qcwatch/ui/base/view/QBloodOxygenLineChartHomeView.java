package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.constant.CacheConstants;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QBloodOxygenLineChartHomeView extends View {
    private List<DataBean> boList;
    private int bottomOffset;
    int[] colors;
    int[] colorsBoLine;
    int[] colorsLine;
    private Context context;
    private Paint dotPaint;
    private Map<String, DataBean> hashData;
    private int height;
    private int leftRightOffset;
    private int lineHeight;
    private Paint linePaint;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    private int[] timeArray;
    private int width;
    private Path yLinePath;

    public interface OnSelectedListener {
        void onSelected(DataBean bean);
    }

    public QBloodOxygenLineChartHomeView(Context context) {
        super(context);
        this.timeArray = new int[24];
        this.boList = new ArrayList();
        this.leftRightOffset = 20;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 50;
        this.lineHeight = 185;
        this.hashData = new ArrayMap();
        init(context, null);
    }

    public QBloodOxygenLineChartHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.timeArray = new int[24];
        this.boList = new ArrayList();
        this.leftRightOffset = 20;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 50;
        this.lineHeight = 185;
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QBloodOxygenLineChartHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.timeArray = new int[24];
        this.boList = new ArrayList();
        this.leftRightOffset = 20;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 50;
        this.lineHeight = 185;
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QBloodOxygenLineChartHomeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.timeArray = new int[24];
        this.boList = new ArrayList();
        this.leftRightOffset = 20;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 50;
        this.lineHeight = 185;
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        for (int i = 0; i < 24; i++) {
            this.timeArray[i] = i * CacheConstants.HOUR;
        }
        this.colors = new int[]{ContextCompat.getColor(context, R.color.q_view_blood_oxygen_2), ContextCompat.getColor(context, R.color.q_view_blood_oxygen_2), ContextCompat.getColor(context, R.color.color_FFFFFF)};
        this.colorsBoLine = new int[]{ContextCompat.getColor(context, R.color.q_view_blood_oxygen_3), ContextCompat.getColor(context, R.color.q_view_blood_oxygen_4), ContextCompat.getColor(context, R.color.q_view_blood_oxygen_4)};
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_blood_oxygen_5), ContextCompat.getColor(context, R.color.q_view_blood_oxygen_6), ContextCompat.getColor(context, R.color.q_view_blood_oxygen_5)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setPathEffect(new CornerPathEffect(30.0f));
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_blood_oxygen_7));
        this.paint.setStrokeWidth(DpUtils.dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setPathEffect(new CornerPathEffect(30.0f));
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_blood_oxygen_8));
        this.paintY.setStrokeWidth(DpUtils.dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint();
        this.dotPaint = paint4;
        paint4.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.path = new Path();
        this.yLinePath = new Path();
        Paint paint5 = new Paint();
        this.linePaint = paint5;
        paint5.setAntiAlias(true);
        this.linePaint.setStyle(Paint.Style.FILL);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeWidth(2.0f);
        this.lineHeight = (int) DpUtils.dp2px(context, 60.0f);
        this.bottomOffset = (int) DpUtils.dp2px(context, 10.0f);
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        int i2 = this.width;
        this.linePaint.setShader(new LinearGradient(i2, this.lineHeight - this.bottomOffset, i2, 90.0f, this.colorsBoLine, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(DpUtils.dp2px(context, 3.0f));
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
        if (this.boList.size() > 0) {
            setData(this.boList);
        }
        setMeasuredDimension(this.width, this.height);
        postInvalidate();
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.boList.size() > 0) {
            canvas.drawPath(this.path, this.linePaint);
        }
    }

    public void setData(List<DataBean> data) {
        this.boList = data;
        this.hashData.clear();
        this.path.reset();
        int i = (this.lineHeight - this.bottomOffset) / 12;
        if (this.width > 0) {
            for (DataBean dataBean : data) {
                this.hashData.put(dataBean.seconds + "", dataBean);
                if (dataBean.getMinValue() > 0 && dataBean.getMaxValue() > 0 && dataBean.getMinValue() >= 51 && dataBean.getMaxValue() >= 51) {
                    dataBean.setMinValue(dataBean.getMinValue() - 50);
                    dataBean.setMaxValue(dataBean.getMaxValue() - 50);
                    Path path = this.path;
                    int seconds = dataBean.getSeconds();
                    int i2 = this.width;
                    int i3 = this.leftRightOffset;
                    path.moveTo(((seconds * (i2 - (i3 * 2))) / CacheConstants.DAY) + i3, (this.lineHeight - this.bottomOffset) - (((dataBean.getMinValue() - 40) + 1) * i));
                    Path path2 = this.path;
                    int seconds2 = dataBean.getSeconds();
                    int i4 = this.width;
                    int i5 = this.leftRightOffset;
                    path2.lineTo(((seconds2 * (i4 - (i5 * 2))) / CacheConstants.DAY) + i5, (this.lineHeight - this.bottomOffset) - (((dataBean.getMaxValue() - 40) + 1) * i));
                }
            }
        } else {
            XLog.i("width=0");
        }
        postInvalidate();
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    public static class DataBean {
        private int maxValue;
        private int minValue;
        private int seconds;
        private int unixTime;

        public DataBean(int seconds, int minValue, int maxValue) {
            this.seconds = seconds;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public DataBean(int seconds, int minValue, int maxValue, int unixTime) {
            this.seconds = seconds;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.unixTime = unixTime;
        }

        public int getUnixTime() {
            return this.unixTime;
        }

        public void setUnixTime(int unixTime) {
            this.unixTime = unixTime;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getMinValue() {
            return this.minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public int getMaxValue() {
            return this.maxValue;
        }

        public void setMaxValue(int maxValue) {
            this.maxValue = maxValue;
        }
    }
}
