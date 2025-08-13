package com.qcwireless.qcwatch.ui.base.view;

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
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.constant.CacheConstants;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class QStepWeekHistoryBarChart extends BaseChartView {
    int[] colors;
    private StepDataBean currBean;
    private List<StepDataBean> data;
    DecimalFormat df;
    private Map<String, StepDataBean> hashData;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    protected OnSelectedListener listener;
    private int maxStep;
    private int[] timeArray;

    public interface OnSelectedListener {
        void onSelected(StepDataBean bean);
    }

    public QStepWeekHistoryBarChart(Context context) {
        super(context);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 5000;
        this.timeArray = new int[7];
        this.leftRightOffset = 100;
        init(context, null);
    }

    public QStepWeekHistoryBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 5000;
        this.timeArray = new int[7];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QStepWeekHistoryBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 5000;
        this.timeArray = new int[7];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QStepWeekHistoryBarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.maxStep = 5000;
        this.timeArray = new int[7];
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public List<StepDataBean> getData() {
        return this.data;
    }

    public void setData(List<StepDataBean> data) {
        this.maxStep = 5000;
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
        this.colors = new int[]{ContextCompat.getColor(context, R.color.q_view_step_3), ContextCompat.getColor(context, R.color.q_view_step_2), ContextCompat.getColor(context, R.color.q_view_step_3)};
        initAttr(context, attrs);
    }

    public void initXLabel(List<StepDataBean> data) {
        int textWidth = ((this.width - (this.leftRightOffset * 2)) - ((int) getTextWidth(this.textDownPaint, "00"))) / 6;
        this.labelsList.clear();
        for (int i = 0; i < data.size(); i++) {
            if (i % 2 == 0) {
                BaseChartView.XTextChange xTextChange = new BaseChartView.XTextChange();
                xTextChange.setLabel(new DateUtil(data.get(i).getTimeStamp(), true).getMMddDate());
                xTextChange.setX(((textWidth * i) + this.leftRightOffset) - (getTextWidth(this.avgLinePaint, "0000") / 2.0f));
                xTextChange.setY(this.lineHeight - (this.textSize / 2.0f));
                if (i == 0) {
                    xTextChange.setMin(1);
                    xTextChange.setMax(this.leftRightOffset + textWidth);
                } else {
                    xTextChange.setMin(this.leftRightOffset + ((i - 1) * textWidth));
                    xTextChange.setMax(this.leftRightOffset + ((i + 1) * textWidth));
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
        int textWidth = (int) (((this.width - (this.leftRightOffset * 2)) - getTextWidth(this.avgLinePaint, "00")) / 6.0f);
        Calendar.getInstance().setTime(new Date());
        this.fingerXDefault = this.leftRightOffset + ((r4.get(7) - 1) * textWidth);
        this.fingerX = this.fingerXDefault;
        int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerX)).intValue();
        if (this.hashData.get(iIntValue + "") != null) {
            Map<String, StepDataBean> map = this.hashData;
            this.fingerX = map.get(iIntValue + "").x;
            this.currBean = this.hashData.get(iIntValue + "");
        }
        initXLabel(this.data);
        if (this.data.size() > 0) {
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
        canvas.drawLine(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.maxStep / 3) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.maxStep / 3) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.avgLinePaint);
        canvas.drawText(this.df.format(((this.maxStep / 3) * 1.0f) / 1000.0f) + "K", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - (((this.maxStep / 3) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.textDownPaint);
        canvas.drawLine((float) this.leftRightOffset, (float) ((this.lineHeight - this.bottomOffset) - ((((this.maxStep / 3) * 2) * (this.lineHeight - this.bottomOffset)) / this.maxStep)), (float) (this.width - this.leftRightOffset), (float) ((this.lineHeight - this.bottomOffset) - ((((this.maxStep / 3) * 2) * (this.lineHeight - this.bottomOffset)) / this.maxStep)), this.avgLinePaint);
        canvas.drawText(this.df.format((double) ((((float) ((this.maxStep / 3) * 2)) * 1.0f) / 1000.0f)) + "K", this.width - getTextWidth(this.avgLinePaint, "0000"), (this.lineHeight - this.bottomOffset) - ((((this.maxStep / 3) * 2) * (this.lineHeight - this.bottomOffset)) / this.maxStep), this.textDownPaint);
        this.stepPaint.setStrokeCap(Paint.Cap.ROUND);
        this.stepPaint.setStrokeWidth(dp2px(this.context, 8.0f));
        this.linePaint.setStrokeCap(Paint.Cap.ROUND);
        this.linePaint.setStrokeWidth(dp2px(this.context, 8.0f));
        for (StepDataBean stepDataBean : this.data) {
            if (stepDataBean.getX() == this.fingerX) {
                if (stepDataBean.getSteps() > 0) {
                    if (stepDataBean.getY() - dp2px(this.context, 2.0f) == (this.lineHeight - this.bottomOffset) - dp2px(this.context, 4.0f)) {
                        stepDataBean.setY(stepDataBean.getY() - ((int) dp2px(this.context, 1.0f)));
                    }
                    canvas.drawLine(stepDataBean.getX(), stepDataBean.getY() - dp2px(this.context, 2.0f), stepDataBean.getX(), (this.lineHeight - this.bottomOffset) - dp2px(this.context, 4.0f), this.linePaint);
                }
            } else if (stepDataBean.getSteps() > 0) {
                if (stepDataBean.getY() - dp2px(this.context, 2.0f) == (this.lineHeight - this.bottomOffset) - dp2px(this.context, 4.0f)) {
                    stepDataBean.setY(stepDataBean.getY() - ((int) dp2px(this.context, 1.0f)));
                }
                canvas.drawLine(stepDataBean.getX(), stepDataBean.getY() - dp2px(this.context, 2.0f), stepDataBean.getX(), (this.lineHeight - this.bottomOffset) - dp2px(this.context, 4.0f), this.stepPaint);
            }
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.colors, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(6.0f);
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
            if (stepDataBean.steps > this.maxStep) {
                this.maxStep = stepDataBean.getSteps();
            }
        }
        int i = this.maxStep;
        if (i > 50000) {
            this.maxStep = i + LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL;
        } else {
            this.maxStep = i + 2000;
        }
        int textWidth = (int) (((this.width - (this.leftRightOffset * 2)) - getTextWidth(this.avgLinePaint, "00")) / 6.0f);
        for (int i2 = 0; i2 < data.size(); i2++) {
            StepDataBean stepDataBean2 = data.get(i2);
            int i3 = i2 * textWidth;
            stepDataBean2.setX(this.leftRightOffset + i3);
            this.timeArray[i2] = this.leftRightOffset + i3;
            stepDataBean2.setY((this.lineHeight - this.bottomOffset) - ((stepDataBean2.getSteps() * (this.lineHeight - this.bottomOffset)) / this.maxStep));
            this.hashData.put((this.leftRightOffset + i3) + "", stepDataBean2);
        }
        initXLabel(data);
        Calendar.getInstance().setTime(new Date());
        this.fingerXDefault = this.leftRightOffset + ((r10.get(7) - 1) * textWidth);
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
