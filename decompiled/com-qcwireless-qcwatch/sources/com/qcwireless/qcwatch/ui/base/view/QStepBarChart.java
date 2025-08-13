package com.qcwireless.qcwatch.ui.base.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.constant.CacheConstants;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import com.qcwireless.qcwatch.ui.base.view.animator.ChartAnimator;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class QStepBarChart extends BaseChartView {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private StepDataBean currBean;
    private List<StepDataBean> data;
    DecimalFormat df;
    private Map<String, StepDataBean> hashData;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    protected OnSelectedListener listener;
    private ChartAnimator mAnimator;
    private int maxStep;
    private int[] timeArray;

    public interface OnSelectedListener {
        void onSelected(StepDataBean bean);
    }

    public QStepBarChart(Context context) {
        super(context);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 1000;
        this.timeArray = new int[24];
        this.leftRightOffset = 100;
        init(context, null);
    }

    public QStepBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 1000;
        this.timeArray = new int[24];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QStepBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 1000;
        this.timeArray = new int[24];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QStepBarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 1000;
        this.timeArray = new int[24];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public OnSelectedListener getListener() {
        return this.listener;
    }

    public void setListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public List<StepDataBean> getData() {
        return this.data;
    }

    public void setData(List<StepDataBean> data) {
        this.data = data;
        this.maxStep = 1000;
        for (int i = 0; i < data.size(); i++) {
            int steps = data.get(i).getSteps();
            if (steps > this.maxStep) {
                this.maxStep = ((steps / 100) + 5) * 100;
            }
            this.hashData.put(data.get(i).getTimeStamp() + "", data.get(i));
        }
        this.fingerXDefault = new DateUtil().getHour() * CacheConstants.HOUR;
        int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerXDefault)).intValue();
        int i2 = this.width;
        int i3 = this.leftRightOffset;
        this.fingerX = (((i2 - (i3 * 2)) * iIntValue) / CacheConstants.DAY) + i3;
        if (this.hashData.get(iIntValue + "") != null) {
            StepDataBean stepDataBean = this.hashData.get(iIntValue + "");
            this.currBean = stepDataBean;
            this.valueSelected = stepDataBean.getSteps();
        } else {
            this.currBean = new StepDataBean(43200L, 0, 0, 0);
            this.valueSelected = 0;
        }
        OnSelectedListener onSelectedListener = this.listener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(this.currBean);
        }
        postInvalidate();
        this.mAnimator.animateY(1000);
    }

    private void init(Context context, AttributeSet attrs) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        for (int i = 0; i < 24; i++) {
            this.timeArray[i] = i * CacheConstants.HOUR;
        }
        initAttr(context, attrs);
        this.mAnimator = new ChartAnimator(new ValueAnimator.AnimatorUpdateListener() { // from class: com.qcwireless.qcwatch.ui.base.view.QStepBarChart.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                QStepBarChart.this.postInvalidate();
            }
        });
    }

    private void initXLabels() {
        this.labelsList.clear();
        int i = (this.width - (this.leftRightOffset * 2)) / 8;
        BaseChartView.XTextChange xTextChange = new BaseChartView.XTextChange();
        xTextChange.setLabel("00:00");
        xTextChange.setX(this.leftRightOffset - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange.setMin(1);
        xTextChange.setMax(this.leftRightOffset + i);
        this.labelsList.add(xTextChange);
        BaseChartView.XTextChange xTextChange2 = new BaseChartView.XTextChange();
        xTextChange2.setLabel("06:00");
        xTextChange2.setX((this.leftRightOffset + (i * 2)) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange2.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange2.setMin(this.leftRightOffset + i);
        int i2 = i * 3;
        xTextChange2.setMax(this.leftRightOffset + i2);
        this.labelsList.add(xTextChange2);
        BaseChartView.XTextChange xTextChange3 = new BaseChartView.XTextChange();
        xTextChange3.setLabel("12:00");
        xTextChange3.setX((this.leftRightOffset + (i * 4)) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange3.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange3.setMin(this.leftRightOffset + i2);
        int i3 = i * 5;
        xTextChange3.setMax(this.leftRightOffset + i3);
        this.labelsList.add(xTextChange3);
        BaseChartView.XTextChange xTextChange4 = new BaseChartView.XTextChange();
        xTextChange4.setLabel("18:00");
        xTextChange4.setX((this.leftRightOffset + (i * 6)) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange4.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange4.setMin(this.leftRightOffset + i3);
        int i4 = i * 7;
        xTextChange4.setMax(this.leftRightOffset + i4);
        this.labelsList.add(xTextChange4);
        BaseChartView.XTextChange xTextChange5 = new BaseChartView.XTextChange();
        xTextChange5.setLabel("00:00");
        xTextChange5.setX((this.leftRightOffset + r0) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange5.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange5.setMin(this.leftRightOffset + i4);
        xTextChange5.setMax(this.leftRightOffset + (i * 8));
        this.labelsList.add(xTextChange5);
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
        DateUtil dateUtil = new DateUtil();
        this.fingerXDefault = dateUtil.getHour() * CacheConstants.HOUR;
        int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerXDefault)).intValue();
        int i = this.width;
        int i2 = this.leftRightOffset;
        this.fingerX = (((i - (i2 * 2)) * iIntValue) / CacheConstants.DAY) + i2;
        if (this.hashData.get(iIntValue + "") != null) {
            StepDataBean stepDataBean = this.hashData.get(iIntValue + "");
            this.currBean = stepDataBean;
            this.valueSelected = stepDataBean.getSteps();
        } else {
            this.currBean = new StepDataBean(dateUtil.getHour() * CacheConstants.HOUR, 0, 0, 0);
            this.valueSelected = 0;
        }
        initXLabels();
        this.fingerXMax = (this.width - this.circleRadiusMin) - getTextWidth(this.avgLinePaint, "000");
        if (this.data.size() > 0) {
            setData(this.data);
        }
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
            this.valueSelected = typedArrayObtainStyledAttributes.getInteger(12, 43200);
            this.lineHeight = (int) dp2px(context, typedArrayObtainStyledAttributes.getInteger(7, 185));
            this.bottomOffset = (int) dp2px(context, typedArrayObtainStyledAttributes.getInteger(0, 30));
            typedArrayObtainStyledAttributes.recycle();
            initPublic(context, attrs);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.BaseChartView, android.view.View
    protected void onDraw(Canvas canvas) {
        Iterator<StepDataBean> it;
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
        String str = this.valueSelected + "";
        float textWidth = this.fingerX - (getTextWidth(this.textPaint, str) / 2.0f);
        float textWidth2 = this.fingerX + (getTextWidth(this.textPaint, str) / 2.0f);
        if (textWidth <= 0.0f) {
            textWidth2 = getTextWidth(this.textPaint, str);
            textWidth = 0.0f;
        }
        if (textWidth2 >= this.width) {
            textWidth = this.width - getTextWidth(this.textPaint, str);
        }
        float f = textWidth;
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) + 2, this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) + 2, this.avgLinePaint);
        canvas.drawText("0", this.width - getTextWidth(this.avgLinePaint, "000"), (this.lineHeight - this.bottomOffset) + 8, this.textDownPaint);
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.maxStep / 3) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.maxStep / 3) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.avgLinePaint);
        canvas.drawText(this.df.format(((this.maxStep / 3) * 1.0f) / 1000.0f) + "K", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - (((this.maxStep / 3) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.textDownPaint);
        canvas.drawLine((float) this.leftRightOffset, (float) ((this.lineHeight - this.bottomOffset) - ((((this.maxStep / 3) * 2) * (this.lineHeight - this.bottomOffset)) / this.maxStep)), (float) (this.width - this.leftRightOffset), (float) ((this.lineHeight - this.bottomOffset) - ((((this.maxStep / 3) * 2) * (this.lineHeight - this.bottomOffset)) / this.maxStep)), this.avgLinePaint);
        canvas.drawText(this.df.format((double) ((((float) ((this.maxStep / 3) * 2)) * 1.0f) / 1000.0f)) + "K", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - ((((this.maxStep / 3) * 2) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.textDownPaint);
        Iterator<StepDataBean> it2 = this.data.iterator();
        while (true) {
            if (it2.hasNext()) {
                StepDataBean next = it2.next();
                if (next.getSteps() != 0) {
                    this.stepPaint.setStrokeCap(Paint.Cap.ROUND);
                    int phaseY = (int) (((next.getSteps() * (this.lineHeight - this.bottomOffset)) / this.maxStep >= 1 ? r3 : 1) * this.mAnimator.getPhaseY());
                    long timeStamp = next.getTimeStamp();
                    int i = this.width;
                    int i2 = this.leftRightOffset;
                    if (((timeStamp * (i - (i2 * 2))) / 86400) + i2 == this.fingerX) {
                        long timeStamp2 = next.getTimeStamp();
                        int i3 = this.width;
                        float f2 = ((timeStamp2 * (i3 - (r6 * 2))) / 86400) + this.leftRightOffset;
                        float f3 = this.lineHeight - this.bottomOffset;
                        long timeStamp3 = next.getTimeStamp();
                        int i4 = this.width;
                        int i5 = this.leftRightOffset;
                        it = it2;
                        canvas.drawLine(f2, f3, ((timeStamp3 * (i4 - (i5 * 2))) / 86400) + i5, (this.lineHeight - this.bottomOffset) - phaseY, this.linePaint);
                    } else {
                        it = it2;
                        long timeStamp4 = next.getTimeStamp();
                        int i6 = this.width;
                        float f4 = ((timeStamp4 * (i6 - (r6 * 2))) / 86400) + this.leftRightOffset;
                        float f5 = this.lineHeight - this.bottomOffset;
                        long timeStamp5 = next.getTimeStamp();
                        int i7 = this.width;
                        int i8 = this.leftRightOffset;
                        canvas.drawLine(f4, f5, ((timeStamp5 * (i7 - (i8 * 2))) / 86400) + i8, (this.lineHeight - this.bottomOffset) - phaseY, this.stepPaint);
                    }
                    it2 = it;
                }
            } else {
                this.textPaint.setColor(this.colorValueSelected);
                canvas.drawText(str, f, 50.0f, this.textPaint);
                this.textPaint.setStrokeWidth(6.0f);
                this.textPaint.setStrokeCap(Paint.Cap.ROUND);
                this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, new int[]{ContextCompat.getColor(this.context, R.color.q_view_step_3), ContextCompat.getColor(this.context, R.color.q_view_step_2), ContextCompat.getColor(this.context, R.color.q_view_step_3)}, (float[]) null, Shader.TileMode.CLAMP));
                this.linePaint.setStrokeWidth(6.0f);
                canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
                return;
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.fingerX = event.getX();
            float f = this.fingerX;
            int i = this.leftRightOffset;
            if (f < i) {
                this.fingerX = i;
            }
            if (this.fingerX > this.fingerXMax) {
                this.fingerX = this.fingerXMax;
            }
            this.animatorFingerIn.start();
            this.valueSelected = (int) (((this.fingerX - this.leftRightOffset) * 86400.0f) / (this.width - (this.leftRightOffset * 2)));
            int iIntValue = getNumberThree(this.timeArray, Integer.valueOf(this.valueSelected)).intValue();
            int i2 = this.width;
            int i3 = this.leftRightOffset;
            this.fingerX = (((i2 - (i3 * 2)) * iIntValue) / CacheConstants.DAY) + i3;
            if (this.hashData.get(iIntValue + "") != null) {
                StepDataBean stepDataBean = this.hashData.get(iIntValue + "");
                this.currBean = stepDataBean;
                this.valueSelected = stepDataBean.getSteps();
            } else {
                this.valueSelected = 0;
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
            this.valueSelected = (int) (((this.fingerX - this.leftRightOffset) * 86400.0f) / (this.width - (this.leftRightOffset * 2)));
            int iIntValue2 = getNumberThree(this.timeArray, Integer.valueOf(this.valueSelected)).intValue();
            int i4 = this.width;
            int i5 = this.leftRightOffset;
            this.fingerX = (((i4 - (i5 * 2)) * iIntValue2) / CacheConstants.DAY) + i5;
            if (this.hashData.get(iIntValue2 + "") != null) {
                StepDataBean stepDataBean2 = this.hashData.get(iIntValue2 + "");
                this.currBean = stepDataBean2;
                this.valueSelected = stepDataBean2.getSteps();
            } else {
                this.valueSelected = 0;
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

        public StepDataBean(long timeStamp, int steps) {
            this.timeStamp = timeStamp;
            this.steps = steps;
        }

        public StepDataBean(long timeStamp, int steps, int distance, int calorie) {
            this.timeStamp = timeStamp;
            this.steps = steps;
            this.distance = distance;
            this.calorie = calorie;
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
    }
}
