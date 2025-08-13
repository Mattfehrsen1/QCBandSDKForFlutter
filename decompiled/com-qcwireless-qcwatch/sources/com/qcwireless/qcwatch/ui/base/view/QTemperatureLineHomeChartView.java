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
import com.qcwireless.qcwatch.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QTemperatureLineHomeChartView extends View {
    private int bottomOffset;
    int[] colors;
    int[] colorsLine;
    private Context context;
    private Paint dotPaint;
    private Path fillPath;
    private TemperatureDataBean firstBean;
    private Map<String, TemperatureDataBean> hashData;
    private List<TemperatureDataBean> heartList;
    private int height;
    private int leftRightOffset;
    private int lineHeight;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    float[] position;
    private int[] timeArray;
    private int width;
    private int xMax;
    private int yMax;

    public QTemperatureLineHomeChartView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[1440];
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, null);
    }

    public QTemperatureLineHomeChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[1440];
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, attrs);
    }

    public QTemperatureLineHomeChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[1440];
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, attrs);
    }

    public QTemperatureLineHomeChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[1440];
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.colors = new int[]{ContextCompat.getColor(context, R.color.q_view_temperature_2), ContextCompat.getColor(context, R.color.q_view_temperature_1), ContextCompat.getColor(context, R.color.q_view_temperature_3)};
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_temperature_3), ContextCompat.getColor(context, R.color.q_view_temperature_2), ContextCompat.getColor(context, R.color.q_view_temperature_3)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setPathEffect(new CornerPathEffect(10.0f));
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_temperature_2));
        this.paint.setStrokeWidth(DpUtils.dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setPathEffect(new CornerPathEffect(10.0f));
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_temperature_4));
        this.paintY.setStrokeWidth(DpUtils.dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint();
        this.dotPaint = paint4;
        paint4.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.path = new Path();
        this.fillPath = new Path();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(200, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) DpUtils.dp2px(this.context, 70.0f);
        } else {
            this.height = getMySize(200, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        filterData(this.heartList);
        postInvalidate();
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.width;
        this.paintFill.setShader(new LinearGradient(i, 0.0f, i, this.height - (this.bottomOffset * 2), this.colors, this.position, Shader.TileMode.CLAMP));
        if (this.heartList.size() > 1) {
            canvas.drawPath(this.fillPath, this.paintFill);
            canvas.drawPath(this.path, this.paint);
        }
        for (int i2 = 0; i2 < this.heartList.size(); i2++) {
            if (this.heartList.get(i2).isBegin()) {
                canvas.drawCircle(r1.x, r1.y, dp2px(this.context, 0.1f), this.paint);
            }
        }
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public void setData(List<TemperatureDataBean> list) {
        this.heartList = list;
        this.path.reset();
        this.fillPath.reset();
        this.hashData.clear();
        if (this.width > 0) {
            filterData(list);
        }
        postInvalidate();
    }

    private void filterData(List<TemperatureDataBean> list) {
        int i = (this.lineHeight - this.bottomOffset) / 50;
        if (list.size() > 0) {
            this.timeArray = new int[list.size()];
        } else {
            this.timeArray = new int[1440];
            for (int i2 = 0; i2 < 1440; i2++) {
                this.timeArray[i2] = i2;
            }
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            TemperatureDataBean temperatureDataBean = list.get(i3);
            int min = temperatureDataBean.getMin();
            int i4 = this.width;
            int i5 = this.leftRightOffset;
            temperatureDataBean.x = ((min * (i4 - (i5 * 2))) / this.xMax) + i5;
            temperatureDataBean.y = ((int) (50.0f - ((temperatureDataBean.getValue() - 35.0f) * 10.0f))) * i;
            if (i3 > 0) {
                if (list.get(i3).getMin() - list.get(i3 - 1).getMin() > 30) {
                    temperatureDataBean.setBegin(true);
                }
            } else {
                temperatureDataBean.setBegin(true);
            }
            this.timeArray[i3] = temperatureDataBean.getMin();
            this.hashData.put(temperatureDataBean.getMin() + "", temperatureDataBean);
        }
        for (int i6 = 0; i6 < this.heartList.size(); i6++) {
            TemperatureDataBean temperatureDataBean2 = this.heartList.get(i6);
            if (temperatureDataBean2.value > 35.0f && temperatureDataBean2.value < 40.0f) {
                if (temperatureDataBean2.begin) {
                    this.firstBean = temperatureDataBean2;
                    this.path.moveTo(temperatureDataBean2.x, temperatureDataBean2.y);
                    this.fillPath.moveTo(temperatureDataBean2.x, temperatureDataBean2.y);
                    this.path.lineTo(temperatureDataBean2.x, temperatureDataBean2.y);
                    this.fillPath.lineTo(temperatureDataBean2.x, temperatureDataBean2.y);
                } else {
                    this.path.lineTo(temperatureDataBean2.x, temperatureDataBean2.y);
                    this.fillPath.lineTo(temperatureDataBean2.x, temperatureDataBean2.y);
                    if ((i6 < this.heartList.size() - 1 && this.heartList.get(i6 + 1).isBegin()) || i6 == this.heartList.size() - 1) {
                        this.fillPath.lineTo(temperatureDataBean2.x, this.height - this.bottomOffset);
                        this.fillPath.lineTo(this.firstBean.x, this.height - this.bottomOffset);
                        this.fillPath.lineTo(this.firstBean.x, temperatureDataBean2.y);
                        this.fillPath.close();
                    }
                }
            }
        }
        postInvalidate();
    }

    public static class TemperatureDataBean {
        private boolean begin;
        private int min;
        private long unixTime;
        private float value;
        public int x;
        public int y;

        public TemperatureDataBean(int min, float value, boolean begin) {
            this.min = min;
            this.value = value;
            this.begin = begin;
        }

        public TemperatureDataBean(int min, float value, boolean begin, long unixTime) {
            this.min = min;
            this.value = value;
            this.begin = begin;
            this.unixTime = unixTime;
        }

        public long getUnixTime() {
            return this.unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public float getValue() {
            return this.value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public int getX() {
            return this.x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isBegin() {
            return this.begin;
        }

        public void setBegin(boolean begin) {
            this.begin = begin;
        }
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }
}
