package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.qcwireless.qcwatch.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QHrvChartHomeView extends View {
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
    private Paint textDownPaint;
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

    public QHrvChartHomeView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 60;
        this.lineHeight = 190;
        init(context, null);
    }

    public QHrvChartHomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 60;
        this.lineHeight = 190;
        init(context, attrs);
    }

    public QHrvChartHomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 60;
        this.lineHeight = 190;
        init(context, attrs);
    }

    public QHrvChartHomeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 200;
        this.timeArray = new int[1440];
        this.bpList = new ArrayList();
        this.leftRightOffset = 20;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.bottomOffset = 60;
        this.lineHeight = 190;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_hrv_1), ContextCompat.getColor(context, R.color.q_view_hrv_1), ContextCompat.getColor(context, R.color.q_view_hrv_1)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_hrv_1));
        this.paint.setStrokeWidth(dp2px(context, 1.0f));
        Paint paint2 = new Paint();
        this.paintY = paint2;
        paint2.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_hrv_1));
        this.paintY.setStrokeWidth(dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        this.lineHeight = (int) dp2px(context, 188.0f);
        this.bottomOffset = (int) dp2px(context, 8.0f);
        Paint paint3 = new Paint();
        this.dotPaint = paint3;
        paint3.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint4 = new Paint();
        this.textDownPaint = paint4;
        paint4.setAntiAlias(true);
        this.textDownPaint.setStyle(Paint.Style.FILL);
        this.textDownPaint.setColor(SkinCompatResources.getColor(context, R.color.blood_pressure_common_4));
        this.textDownPaint.setStrokeWidth(2.0f);
        this.textDownPaint.setTextSize(dp2px(context, 10.0f));
        this.path = new Path();
        this.path1 = new Path();
        this.yLinePath = new Path();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(100, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) dp2px(this.context, 100.0f);
        } else {
            this.height = getMySize(100, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        setData(this.bpList);
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
            canvas.drawPath(this.path, this.paint);
        }
        canvas.drawPath(this.yLinePath, this.paintY);
        for (BpDataBean bpDataBean : this.bpList) {
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_hrv_1));
            canvas.drawCircle(bpDataBean.hrvX, bpDataBean.hrvY, dp2px(this.context, 2.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_hrv_2));
            canvas.drawCircle(bpDataBean.hrvX, bpDataBean.hrvY, dp2px(this.context, 1.0f), this.dotPaint);
        }
    }

    public void setData(List<BpDataBean> list) {
        this.bpList = list;
        this.path.reset();
        this.hashData.clear();
        if (this.width > 0) {
            filterData(list);
        }
        postInvalidate();
    }

    private void filterData(List<BpDataBean> list) {
        int min = 1025;
        int min2 = 0;
        for (int i = 0; i < list.size(); i++) {
            BpDataBean bpDataBean = list.get(i);
            if (bpDataBean.getMin() < min) {
                min = bpDataBean.getMin();
            }
            if (bpDataBean.getMin() > min2) {
                min2 = bpDataBean.getMin();
            }
            int min3 = bpDataBean.getMin();
            int i2 = this.width;
            int i3 = this.leftRightOffset;
            bpDataBean.hrvX = ((min3 * (i2 - (i3 * 2))) / this.xMax) + i3;
            bpDataBean.hrvY = (this.lineHeight - this.bottomOffset) - ((bpDataBean.getHrv() * (this.height - this.bottomOffset)) / this.yMax);
        }
        this.bpList = list;
        this.path.reset();
        this.path1.reset();
        this.hashData.clear();
        for (int i4 = 0; i4 < this.bpList.size(); i4++) {
            BpDataBean bpDataBean2 = this.bpList.get(i4);
            this.timeArray[i4] = bpDataBean2.getMin();
            this.hashData.put(bpDataBean2.getMin() + "", bpDataBean2);
            if (i4 == 0) {
                this.path.moveTo(bpDataBean2.hrvX, bpDataBean2.hrvY);
                this.path.lineTo(bpDataBean2.hrvX, bpDataBean2.hrvY);
            } else {
                this.path.lineTo(bpDataBean2.hrvX, bpDataBean2.hrvY);
            }
            this.path1.lineTo(bpDataBean2.hrvX, bpDataBean2.hrvY);
        }
        postInvalidate();
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }

    public static class BpDataBean {
        private int hrv;
        private int hrvX;
        private int hrvY;
        private int min;
        private long unixTime;

        public BpDataBean(int min, int hrv) {
            this.min = min;
            this.hrv = hrv;
        }

        public BpDataBean(int min, int hrv, long unixTime) {
            this.min = min;
            this.hrv = hrv;
            this.unixTime = unixTime;
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getHrv() {
            return this.hrv;
        }

        public void setHrv(int hrv) {
            this.hrv = hrv;
        }

        public int getHrvX() {
            return this.hrvX;
        }

        public void setHrvX(int hrvX) {
            this.hrvX = hrvX;
        }

        public int getHrvY() {
            return this.hrvY;
        }

        public void setHrvY(int hrvY) {
            this.hrvY = hrvY;
        }

        public long getUnixTime() {
            return this.unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
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

    public float getTextHeight(Paint paint, String str) {
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return r0.height();
    }
}
