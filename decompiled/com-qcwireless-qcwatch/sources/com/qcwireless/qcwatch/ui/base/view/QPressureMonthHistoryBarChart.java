package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.constant.CacheConstants;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QPressureMonthHistoryBarChart extends BaseChartView {
    private int[] colors;
    private StepDataBean currBean;
    private List<StepDataBean> data;
    DecimalFormat df;
    private Map<String, StepDataBean> hashData;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    protected OnSelectedListener listener;
    private int max;
    private int maxPressure;
    private int[] timeArray;

    public interface OnSelectedListener {
        void onSelected(StepDataBean bean);
    }

    public QPressureMonthHistoryBarChart(Context context) {
        super(context);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.max = 110;
        this.maxPressure = 100;
        this.timeArray = new int[31];
        this.leftRightOffset = 100;
        init(context, null);
    }

    public QPressureMonthHistoryBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.max = 110;
        this.maxPressure = 100;
        this.timeArray = new int[31];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QPressureMonthHistoryBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.max = 110;
        this.maxPressure = 100;
        this.timeArray = new int[31];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QPressureMonthHistoryBarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.max = 110;
        this.maxPressure = 100;
        this.timeArray = new int[31];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public List<StepDataBean> getData() {
        return this.data;
    }

    public void setData(List<StepDataBean> data) {
        this.maxPressure = 100;
        this.data.clear();
        this.data = data;
        if (this.width > 0) {
            filterData(this.data);
        }
    }

    public OnSelectedListener getListener() {
        return this.listener;
    }

    public void setListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    private void init(Context context, AttributeSet attrs) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        this.colors = new int[]{SkinCompatResources.getColor(context, R.color.pressure_common_16), SkinCompatResources.getColor(context, R.color.pressure_common_15), SkinCompatResources.getColor(context, R.color.pressure_common_16)};
        initAttr(context, attrs);
        this.avgLinePaint.setStrokeWidth(dp2px(context, 0.05f));
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f);
        this.avgLinePaint.setColor(ContextCompat.getColor(context, R.color.pressure_common_17));
        this.avgLinePaint.setPathEffect(dashPathEffect);
        this.avgLinePaint.setStyle(Paint.Style.STROKE);
    }

    public void initXLabel(List<StepDataBean> data, int days) {
        if (this.width == 0) {
            this.width = getDisplay().getWidth();
        }
        int i = (this.width - (this.leftRightOffset * 2)) / (days - 1);
        this.labelsList.clear();
        for (int i2 = 0; i2 < data.size(); i2++) {
            if (i2 % 6 == 0 || i2 == data.size() - 1) {
                BaseChartView.XTextChange xTextChange = new BaseChartView.XTextChange();
                xTextChange.setLabel(new DateUtil(data.get(i2).getTimeStamp(), true).getMMddDate());
                if (new DateUtil(data.get(i2).getTimeStamp(), true).getDay() == 28) {
                    xTextChange.setX((((i * i2) + this.leftRightOffset) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f)) + dp2px(this.context, 8.0f));
                } else {
                    xTextChange.setX(((i * i2) + this.leftRightOffset) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
                }
                xTextChange.setY(this.lineHeight - (this.textSize / 2.0f));
                if (i2 == 0) {
                    xTextChange.setMin(1);
                    xTextChange.setMax((this.leftRightOffset + (i * 5)) - 30);
                } else {
                    xTextChange.setMin(this.leftRightOffset + ((i2 - 5) * i) + 30);
                    xTextChange.setMax((this.leftRightOffset + ((i2 + 5) * i)) - 30);
                }
                this.labelsList.add(xTextChange);
            }
        }
        this.fingerXmin = this.leftRightOffset;
        this.fingerXMax = this.width - this.leftRightOffset;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(this.diameterDefault, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = this.diameterDefault;
        } else {
            this.height = getMySize(this.diameterDefault, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        this.diameterDefault = (int) dp2px(this.context, 200.0f);
        this.fingerXDefault = this.width / 2;
        this.fingerX = this.fingerXDefault;
        int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerX)).intValue();
        if (this.hashData.get(iIntValue + "") != null) {
            Map<String, StepDataBean> map = this.hashData;
            this.fingerX = map.get(iIntValue + "").x;
            this.currBean = this.hashData.get(iIntValue + "");
        }
        if (this.data.size() > 0) {
            initXLabel(this.data, DateUtil.getDaysOfMonth(new DateUtil(this.data.get(0).getTimeStamp(), true).toDate()));
            setData(this.data);
        }
        this.fingerXMax = this.width - this.circleRadiusMin;
        postInvalidate();
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.BaseChartView, android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
        this.mPaddingStart = getPaddingStart();
        this.mPaddingEnd = getPaddingEnd();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        this.bezierHeight = 0.0f;
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.qc_chart);
            this.colorBall = typedArrayObtainStyledAttributes.getColor(1, ViewCompat.MEASURED_STATE_MASK);
            this.colorLine = typedArrayObtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
            this.textDownColor = typedArrayObtainStyledAttributes.getColor(8, ViewCompat.MEASURED_STATE_MASK);
            this.colorValue = typedArrayObtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
            this.colorValueSelected = typedArrayObtainStyledAttributes.getColor(6, -1);
            this.colorBgSelected = typedArrayObtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
            this.valueMin = typedArrayObtainStyledAttributes.getInteger(11, this.offset);
            this.valueMax = typedArrayObtainStyledAttributes.getInteger(10, CacheConstants.DAY);
            this.lineHeight = (int) dp2px(context, typedArrayObtainStyledAttributes.getInteger(7, 185));
            this.bottomOffset = (int) dp2px(context, typedArrayObtainStyledAttributes.getInteger(0, 30));
            typedArrayObtainStyledAttributes.recycle();
            initPublic(context, attrs);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.BaseChartView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.bezierHeight = (this.circleRadiusMax * 2.0f) + 5.0f;
        this.circleRadius = this.circleRadiusMin + (this.circleRadiusMax - this.circleRadiusMin);
        this.spaceToLine = this.circleRadiusMin * 2.0f;
        for (BaseChartView.XTextChange xTextChange : this.labelsList) {
            if (this.fingerX > xTextChange.getMin() && this.fingerX <= xTextChange.getMax()) {
                canvas.drawText(xTextChange.label, xTextChange.x, this.lineHeight - this.spaceToLine, this.textDownPaint);
            } else {
                canvas.drawText(xTextChange.label, xTextChange.x, xTextChange.y, this.textDownPaint);
            }
        }
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) + 2, this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) + 2, this.avgLinePaint);
        canvas.drawText("0", this.width - getTextWidth(this.avgLinePaint, "000"), (this.lineHeight - this.bottomOffset) + 8, this.textDownPaint);
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.maxPressure / 5) * (this.lineHeight - this.bottomOffset)) / this.max), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.maxPressure / 5) * (this.lineHeight - this.bottomOffset)) / this.max), this.avgLinePaint);
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 2) * (this.lineHeight - this.bottomOffset)) / this.max), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 2) * (this.lineHeight - this.bottomOffset)) / this.max), this.avgLinePaint);
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 3) * (this.lineHeight - this.bottomOffset)) / this.max), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 3) * (this.lineHeight - this.bottomOffset)) / this.max), this.avgLinePaint);
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 4) * (this.lineHeight - this.bottomOffset)) / this.max), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 4) * (this.lineHeight - this.bottomOffset)) / this.max), this.avgLinePaint);
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 5) * (this.lineHeight - this.bottomOffset)) / this.max), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 5) * (this.lineHeight - this.bottomOffset)) / this.max), this.avgLinePaint);
        canvas.drawText("20", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - (((this.maxPressure / 5) * (this.lineHeight - this.bottomOffset)) / this.max), this.textDownPaint);
        canvas.drawText("40", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 2) * (this.lineHeight - this.bottomOffset)) / this.max), this.textDownPaint);
        canvas.drawText("60", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 3) * (this.lineHeight - this.bottomOffset)) / this.max), this.textDownPaint);
        canvas.drawText("80", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 4) * (this.lineHeight - this.bottomOffset)) / this.max), this.textDownPaint);
        canvas.drawText("100", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - ((((this.maxPressure / 5) * 5) * (this.lineHeight - this.bottomOffset)) / this.max), this.textDownPaint);
        this.stepPaint.setStrokeCap(Paint.Cap.ROUND);
        this.stepPaint.setStrokeWidth(dp2px(this.context, 2.0f));
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        this.linePaint.setStrokeWidth(dp2px(this.context, 2.0f));
        Iterator<StepDataBean> it = this.data.iterator();
        while (it.hasNext()) {
            if (it.next().getSteps() > 0) {
                canvas.drawLine(r1.getX(), r1.getY(), r1.getX(), this.lineHeight - this.bottomOffset, this.stepPaint);
            }
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.colors, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(2.0f);
        canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.fingerX = event.getX();
            if (this.fingerX < this.fingerXmin) {
                this.fingerX = this.fingerXmin;
            }
            if (this.fingerX > this.fingerXMax) {
                this.fingerX = this.fingerXMax;
            }
            this.animatorFingerIn.start();
            int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerX)).intValue();
            if (this.hashData.get(iIntValue + "") != null) {
                this.currBean = this.hashData.get(iIntValue + "");
                Map<String, StepDataBean> map = this.hashData;
                this.fingerX = map.get(iIntValue + "").x;
            }
            postInvalidate();
        } else if (action == 1) {
            this.animatorFingerOut.start();
        } else if (action == 2) {
            this.fingerX = event.getX();
            if (this.fingerX < this.fingerXmin) {
                this.fingerX = this.fingerXmin;
            }
            if (this.fingerX > this.fingerXMax) {
                this.fingerX = this.fingerXMax;
            }
            int iIntValue2 = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerX)).intValue();
            if (this.hashData.get(iIntValue2 + "") != null) {
                this.currBean = this.hashData.get(iIntValue2 + "");
                Map<String, StepDataBean> map2 = this.hashData;
                this.fingerX = map2.get(iIntValue2 + "").x;
            }
            postInvalidate();
        }
        OnSelectedListener onSelectedListener = this.listener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(this.currBean);
        }
        return true;
    }

    public static class StepDataBean {
        private int calorie;
        private int distance;
        private int steps;
        private long timeStamp;
        private int x;
        private int y;

        public int getDistance() {
            return this.distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getCalorie() {
            return this.calorie;
        }

        public void setCalorie(int calorie) {
            this.calorie = calorie;
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

        public StepDataBean(long timeStamp, int steps) {
            this.timeStamp = timeStamp;
            this.steps = steps;
        }

        public long getTimeStamp() {
            return this.timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public int getSteps() {
            return this.steps;
        }

        public void setSteps(int steps) {
            this.steps = steps;
        }
    }

    private void filterData(List<StepDataBean> data) {
        for (StepDataBean stepDataBean : data) {
            if (stepDataBean.steps > this.maxPressure) {
                this.maxPressure = stepDataBean.getSteps();
            }
        }
        int daysOfMonth = 31;
        if (data.size() > 0) {
            daysOfMonth = DateUtil.getDaysOfMonth(new DateUtil(data.get(0).getTimeStamp(), true).toDate());
            this.hashData.clear();
            this.timeArray = new int[daysOfMonth];
        }
        int textWidth = ((this.width - (this.leftRightOffset * 2)) - ((int) getTextWidth(this.avgLinePaint, "0"))) / (daysOfMonth - 1);
        for (int i = 0; i < data.size(); i++) {
            StepDataBean stepDataBean2 = data.get(i);
            int i2 = i * textWidth;
            stepDataBean2.setX(this.leftRightOffset + i2);
            this.timeArray[i] = this.leftRightOffset + i2;
            stepDataBean2.setY((this.lineHeight - this.bottomOffset) - ((stepDataBean2.getSteps() * (this.lineHeight - this.bottomOffset)) / this.max));
            this.hashData.put((this.leftRightOffset + i2) + "", stepDataBean2);
        }
        initXLabel(data, daysOfMonth);
        this.fingerXDefault = this.leftRightOffset + (DateUtil.getDayOfMonth() * textWidth);
        this.fingerX = this.fingerXDefault;
        int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerX)).intValue();
        if (this.hashData.get(iIntValue + "") != null) {
            Map<String, StepDataBean> map = this.hashData;
            this.fingerX = map.get(iIntValue + "").x;
            this.currBean = this.hashData.get(iIntValue + "");
        }
        OnSelectedListener onSelectedListener = this.listener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(this.currBean);
        }
        postInvalidate();
    }
}
