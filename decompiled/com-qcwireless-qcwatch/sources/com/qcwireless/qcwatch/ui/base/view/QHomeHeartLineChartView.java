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
import com.qcwireless.qcwatch.ui.base.view.QHeartLineChartView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QHomeHeartLineChartView extends View {
    private int bottomOffset;
    int[] colors;
    int[] colorsLine;
    private Context context;
    private Paint dotPaint;
    private Path fillPath;
    private QHeartLineChartView.HeartDataBean firstBean;
    private Map<String, QHeartLineChartView.HeartDataBean> hashData;
    private List<QHeartLineChartView.HeartDataBean> heartList;
    private int height;
    private int leftRightOffset;
    private int lineHeight;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    float[] position;
    private int range;
    private int[] timeArray;
    private int width;
    private int xMax;
    private Path yLinePath;
    private int yMax;

    public QHomeHeartLineChartView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 240;
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.01f, 0.01f, 5.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.range = 5;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, null);
    }

    public QHomeHeartLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 240;
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.01f, 0.01f, 5.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.range = 5;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, attrs);
    }

    public QHomeHeartLineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 240;
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.01f, 0.01f, 5.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.range = 5;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, attrs);
    }

    public QHomeHeartLineChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 240;
        this.heartList = new ArrayList();
        this.leftRightOffset = 10;
        this.position = new float[]{0.01f, 0.01f, 5.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 30;
        this.lineHeight = 120;
        this.range = 5;
        this.hashData = new ArrayMap();
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.colors = new int[]{ContextCompat.getColor(context, R.color.q_view_heart_4), ContextCompat.getColor(context, R.color.q_view_heart_5), ContextCompat.getColor(context, R.color.q_view_heart_6)};
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_heart_6), ContextCompat.getColor(context, R.color.q_view_heart_4), ContextCompat.getColor(context, R.color.q_view_heart_6)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setPathEffect(new CornerPathEffect(10.0f));
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_heart_7));
        this.paint.setStrokeWidth(dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setPathEffect(new CornerPathEffect(10.0f));
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_heart_8));
        this.paintY.setStrokeWidth(dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        this.path = new Path();
        this.fillPath = new Path();
        initTimeStamp();
    }

    private void initTimeStamp() {
        if (this.range <= 0) {
            this.range = 5;
        }
        this.timeArray = new int[1440 / this.range];
        int i = 0;
        while (true) {
            int i2 = this.range;
            if (i >= 1440 / i2) {
                break;
            }
            this.timeArray[i] = i2 * i;
            i++;
        }
        for (int i3 = 0; i3 < 1440 / this.range; i3++) {
            this.hashData.put(i3 + "", null);
        }
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(200, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) dp2px(this.context, 60.0f);
        } else {
            this.height = getMySize(200, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        filterData(this.heartList);
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.width;
        this.paintFill.setShader(new LinearGradient(i, 0.0f, i, dp2px(this.context, 50.0f), this.colors, this.position, Shader.TileMode.CLAMP));
        canvas.drawPath(this.fillPath, this.paintFill);
        canvas.drawPath(this.path, this.paint);
        for (int i2 = 0; i2 < this.heartList.size(); i2++) {
            if (this.heartList.get(i2).isBegin()) {
                canvas.drawCircle(r1.x, r1.y, dp2px(this.context, 0.1f), this.paint);
            }
        }
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public void setData(List<QHeartLineChartView.HeartDataBean> list) {
        if (list.size() > 0) {
            this.range = list.get(0).getRange();
        }
        initTimeStamp();
        this.heartList.clear();
        if (list != null) {
            this.heartList.addAll(list);
        }
        this.path.reset();
        this.fillPath.reset();
        this.hashData.clear();
        if (this.width > 0 && list != null) {
            filterData(list);
        }
        postInvalidate();
    }

    private void filterData(List<QHeartLineChartView.HeartDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            QHeartLineChartView.HeartDataBean heartDataBean = list.get(i);
            int min = heartDataBean.getMin();
            int i2 = this.width;
            int i3 = this.leftRightOffset;
            heartDataBean.x = ((min * (i2 - (i3 * 2))) / this.xMax) + i3;
            heartDataBean.y = (this.lineHeight - this.bottomOffset) - ((heartDataBean.getValue() * (this.height - this.bottomOffset)) / this.yMax);
            if (i > 0) {
                if (list.get(i).getMin() - list.get(i - 1).getMin() > heartDataBean.getRange()) {
                    heartDataBean.setBegin(true);
                }
            } else {
                heartDataBean.setBegin(true);
            }
            this.hashData.put(heartDataBean.getMin() + "", heartDataBean);
        }
        for (int i4 = 0; i4 < this.heartList.size(); i4++) {
            QHeartLineChartView.HeartDataBean heartDataBean2 = this.heartList.get(i4);
            if (heartDataBean2.getValue() > 35 && heartDataBean2.getValue() < 220) {
                this.hashData.put(heartDataBean2.getMin() + "", heartDataBean2);
                if (heartDataBean2.isBegin()) {
                    this.firstBean = heartDataBean2;
                    this.path.moveTo(heartDataBean2.x, heartDataBean2.y);
                    this.fillPath.moveTo(heartDataBean2.x, heartDataBean2.y);
                    this.path.lineTo(heartDataBean2.x, heartDataBean2.y);
                    this.fillPath.lineTo(heartDataBean2.x, heartDataBean2.y);
                } else {
                    this.path.lineTo(heartDataBean2.x, heartDataBean2.y);
                    this.fillPath.lineTo(heartDataBean2.x, heartDataBean2.y);
                    if ((i4 < this.heartList.size() - 1 && this.heartList.get(i4 + 1).isBegin()) || i4 == this.heartList.size() - 1) {
                        this.fillPath.lineTo(heartDataBean2.x, this.height - this.bottomOffset);
                        this.fillPath.lineTo(this.firstBean.x, this.height - this.bottomOffset);
                        this.fillPath.lineTo(this.firstBean.x, heartDataBean2.y);
                        this.fillPath.close();
                    }
                }
            }
        }
        postInvalidate();
    }
}
