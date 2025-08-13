package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
public class QPressureLineChartHomeView extends View {
    private Paint avgLinePaint;
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
    private int max;
    private int maxPressure;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Paint stepPaint;
    private Paint textDownPaint;
    private int[] timeArray;
    private int width;

    public interface OnSelectedListener {
        void onSelected(DataBean bean);
    }

    public QPressureLineChartHomeView(Context context) {
        super(context);
        this.timeArray = new int[48];
        this.boList = new ArrayList();
        this.leftRightOffset = 30;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 0;
        this.lineHeight = 120;
        this.maxPressure = 120;
        this.max = 110;
        this.hashData = new ArrayMap();
        init(context, null);
    }

    public QPressureLineChartHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.timeArray = new int[48];
        this.boList = new ArrayList();
        this.leftRightOffset = 30;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 0;
        this.lineHeight = 120;
        this.maxPressure = 120;
        this.max = 110;
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QPressureLineChartHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.timeArray = new int[48];
        this.boList = new ArrayList();
        this.leftRightOffset = 30;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 0;
        this.lineHeight = 120;
        this.maxPressure = 120;
        this.max = 110;
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QPressureLineChartHomeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.timeArray = new int[48];
        this.boList = new ArrayList();
        this.leftRightOffset = 30;
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.colorsBoLine = new int[0];
        this.bottomOffset = 0;
        this.lineHeight = 120;
        this.maxPressure = 120;
        this.max = 110;
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        for (int i = 0; i < 48; i++) {
            this.timeArray[i] = i * CacheConstants.HOUR;
        }
        this.colors = new int[]{ContextCompat.getColor(context, R.color.pressure_common_1), ContextCompat.getColor(context, R.color.pressure_common_1), ContextCompat.getColor(context, R.color.pressure_common_1)};
        this.colorsBoLine = new int[]{ContextCompat.getColor(context, R.color.pressure_common_1), ContextCompat.getColor(context, R.color.pressure_common_1), ContextCompat.getColor(context, R.color.pressure_common_1)};
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.pressure_common_1), ContextCompat.getColor(context, R.color.pressure_common_1), ContextCompat.getColor(context, R.color.pressure_common_1)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setPathEffect(new CornerPathEffect(30.0f));
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_blood_oxygen_7));
        this.paint.setStrokeWidth(DpUtils.dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.stepPaint = paint2;
        paint2.setAntiAlias(true);
        this.stepPaint.setStyle(Paint.Style.STROKE);
        this.stepPaint.setStrokeCap(Paint.Cap.ROUND);
        this.stepPaint.setColor(ContextCompat.getColor(context, R.color.pressure_common_18));
        this.stepPaint.setStrokeWidth(DpUtils.dp2px(context, 1.0f));
        Paint paint3 = new Paint();
        this.paintFill = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setPathEffect(new CornerPathEffect(30.0f));
        Paint paint4 = new Paint();
        this.paintY = paint4;
        paint4.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_blood_oxygen_8));
        this.paintY.setStrokeWidth(DpUtils.dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint5 = new Paint();
        this.dotPaint = paint5;
        paint5.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.textDownPaint = paint6;
        paint6.setAntiAlias(true);
        this.textDownPaint.setStyle(Paint.Style.FILL);
        this.textDownPaint.setStrokeWidth(2.0f);
        this.textDownPaint.setTextSize(DpUtils.dp2px(context, 8.0f));
        this.textDownPaint.setColor(ContextCompat.getColor(context, R.color.pressure_common_2));
        Paint paint7 = new Paint();
        this.linePaint = paint7;
        paint7.setAntiAlias(true);
        this.linePaint.setStyle(Paint.Style.FILL);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.linePaint.setStrokeWidth(2.0f);
        this.lineHeight = (int) DpUtils.dp2px(context, 100.0f);
        this.bottomOffset = (int) DpUtils.dp2px(context, 2.0f);
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        int i2 = this.width;
        this.linePaint.setShader(new LinearGradient(i2, this.lineHeight - this.bottomOffset, i2, 90.0f, this.colorsBoLine, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(DpUtils.dp2px(context, 2.0f));
        Paint paint8 = new Paint();
        this.avgLinePaint = paint8;
        paint8.setStrokeWidth(DpUtils.dp2px(context, 0.05f));
        this.avgLinePaint.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.avgLinePaint.setColor(ContextCompat.getColor(context, R.color.pressure_common_17));
        this.avgLinePaint.setStyle(Paint.Style.STROKE);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(120, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) DpUtils.dp2px(this.context, 120.0f);
        } else {
            this.height = getMySize(120, heightMeasureSpec);
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
            if (this.width > 0) {
                for (DataBean dataBean : this.boList) {
                    this.hashData.put(dataBean.seconds + "", dataBean);
                    if (dataBean.getMinValue() > 0 && dataBean.getMaxValue() > 0) {
                        int seconds = dataBean.getSeconds();
                        int i = this.width;
                        int i2 = this.leftRightOffset;
                        float f = ((seconds * (i - (i2 * 2))) / CacheConstants.DAY) + i2;
                        float f2 = this.lineHeight - this.bottomOffset;
                        int seconds2 = dataBean.getSeconds();
                        int i3 = this.width;
                        int i4 = this.leftRightOffset;
                        canvas.drawLine(f, f2, ((seconds2 * (i3 - (i4 * 2))) / CacheConstants.DAY) + i4, (this.lineHeight - this.bottomOffset) - ((dataBean.getMaxValue() * (this.lineHeight - this.bottomOffset)) / this.maxPressure), this.stepPaint);
                    }
                }
                return;
            }
            XLog.i("width=0");
            return;
        }
        XLog.i("size 0");
    }

    public float getTextWidth(Paint paint, String str) {
        float fCeil = 0.0f;
        if (str != null && str.length() > 0) {
            int length = str.length();
            paint.getTextWidths(str, new float[length]);
            for (int i = 0; i < length; i++) {
                fCeil += (float) Math.ceil(r2[i]);
            }
        }
        return fCeil;
    }

    public void setData(List<DataBean> data) {
        this.boList = data;
        this.hashData.clear();
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
