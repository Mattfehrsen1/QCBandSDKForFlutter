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
import com.blankj.utilcode.constant.CacheConstants;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import com.realsil.sdk.core.bluetooth.connection.le.GattClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QSleepMonthBarView extends BaseChartView {
    private SleepDataBean currBean;
    private List<SleepDataBean> data;
    private Map<String, SleepDataBean> hashData;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    private OnSelectedListener listener;
    private int maxSleepTime;
    private Paint sleepPaint;
    private int[] timeArray;

    public interface OnSelectedListener {
        void onSelected(SleepDataBean bean);
    }

    public OnSelectedListener getListener() {
        return this.listener;
    }

    public void setListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public QSleepMonthBarView(Context context) {
        super(context);
        this.leftRightOffset = 100;
        this.maxSleepTime = GattClient.STATE_READ_PROTOCOL_TYPE;
        this.data = new ArrayList();
        this.labelsList = new ArrayList();
        this.timeArray = new int[32];
        this.hashData = new ArrayMap();
        this.context = context;
        initPaint(context, null);
    }

    public QSleepMonthBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.leftRightOffset = 100;
        this.maxSleepTime = GattClient.STATE_READ_PROTOCOL_TYPE;
        this.data = new ArrayList();
        this.labelsList = new ArrayList();
        this.timeArray = new int[32];
        this.hashData = new ArrayMap();
        this.context = context;
        initPaint(context, attrs);
    }

    public QSleepMonthBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.leftRightOffset = 100;
        this.maxSleepTime = GattClient.STATE_READ_PROTOCOL_TYPE;
        this.data = new ArrayList();
        this.labelsList = new ArrayList();
        this.timeArray = new int[32];
        this.hashData = new ArrayMap();
        this.context = context;
        initPaint(context, attrs);
    }

    public QSleepMonthBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.leftRightOffset = 100;
        this.maxSleepTime = GattClient.STATE_READ_PROTOCOL_TYPE;
        this.data = new ArrayList();
        this.labelsList = new ArrayList();
        this.timeArray = new int[32];
        this.hashData = new ArrayMap();
        this.context = context;
        initPaint(context, attrs);
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
        for (int i = 0; i < this.data.size(); i++) {
            SleepDataBean sleepDataBean = this.data.get(i);
            float totalTime = (sleepDataBean.getTotalTime() * (this.lineHeight - this.bottomOffset)) / this.maxSleepTime;
            int deepScale = (int) (sleepDataBean.getDeepScale() * totalTime);
            int lightScale = (int) (sleepDataBean.getLightScale() * totalTime);
            int awakeScale = (int) (sleepDataBean.getAwakeScale() * totalTime);
            int rapidScale = (int) (sleepDataBean.getRapidScale() * totalTime);
            this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_1));
            canvas.drawLine(sleepDataBean.getX(), this.lineHeight - this.bottomOffset, sleepDataBean.getX(), (this.lineHeight - this.bottomOffset) - deepScale, this.sleepPaint);
            this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_2));
            canvas.drawLine(sleepDataBean.getX(), (this.lineHeight - this.bottomOffset) - deepScale, sleepDataBean.getX(), ((this.lineHeight - this.bottomOffset) - deepScale) - lightScale, this.sleepPaint);
            this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_6));
            canvas.drawLine(sleepDataBean.getX(), ((this.lineHeight - this.bottomOffset) - deepScale) - lightScale, sleepDataBean.getX(), (((this.lineHeight - this.bottomOffset) - deepScale) - lightScale) - rapidScale, this.sleepPaint);
            this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_3));
            canvas.drawLine(sleepDataBean.getX(), (((this.lineHeight - this.bottomOffset) - deepScale) - lightScale) - rapidScale, sleepDataBean.getX(), ((((this.lineHeight - this.bottomOffset) - deepScale) - lightScale) - rapidScale) - awakeScale, this.sleepPaint);
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, new int[]{ContextCompat.getColor(this.context, R.color.q_view_sleep_5), ContextCompat.getColor(this.context, R.color.q_view_sleep_4), ContextCompat.getColor(this.context, R.color.q_view_sleep_5)}, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(dp2px(this.context, 1.0f));
        canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
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
        setMeasuredDimension(this.width, this.height);
        this.fingerXMax = this.width - this.circleRadiusMin;
        this.fingerX = this.width / 2;
        setData(this.data);
        if (this.data.size() > 0) {
            initXLabel(this.data, DateUtil.getDaysOfMonth(new DateUtil(this.data.get(0).getUnixTime(), true).toDate()));
        }
        setData(this.data);
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

    public void initPaint(Context context, AttributeSet attrs) {
        Paint paint = new Paint();
        this.sleepPaint = paint;
        paint.setAntiAlias(true);
        this.sleepPaint.setStyle(Paint.Style.STROKE);
        this.sleepPaint.setStrokeWidth(10.0f);
        initAttr(context, attrs);
    }

    public void initXLabel(List<SleepDataBean> data, int days) {
        if (this.width == 0) {
            this.width = 1080;
        }
        int i = (this.width - (this.leftRightOffset * 2)) / (days - 1);
        this.labelsList.clear();
        for (int i2 = 0; i2 < data.size(); i2++) {
            if (i2 % 6 == 0 || i2 == data.size() - 1) {
                BaseChartView.XTextChange xTextChange = new BaseChartView.XTextChange();
                xTextChange.setLabel(new DateUtil(data.get(i2).getUnixTime(), true).getMMddDate());
                if (new DateUtil(data.get(i2).getUnixTime(), true).getDay() == 28) {
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

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
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
                this.fingerX = this.hashData.get(iIntValue + "").x;
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
                this.fingerX = this.hashData.get(iIntValue2 + "").x;
            }
            postInvalidate();
        }
        OnSelectedListener onSelectedListener = this.listener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(this.currBean);
        }
        return true;
    }

    public void setData(List<SleepDataBean> data) {
        this.data.clear();
        this.data.addAll(data);
        for (SleepDataBean sleepDataBean : data) {
            if (sleepDataBean.getTotalTime() >= this.maxSleepTime) {
                this.maxSleepTime = sleepDataBean.getTotalTime();
            }
        }
        this.maxSleepTime += 60;
        int daysOfMonth = 31;
        if (data.size() > 0) {
            daysOfMonth = DateUtil.getDaysOfMonth(new DateUtil(data.get(0).getUnixTime(), true).toDate());
            this.hashData.clear();
        }
        int i = (this.width - (this.leftRightOffset * 2)) / (daysOfMonth - 1);
        for (int i2 = 0; i2 < data.size(); i2++) {
            SleepDataBean sleepDataBean2 = data.get(i2);
            int i3 = i2 * i;
            sleepDataBean2.setX(this.leftRightOffset + i3);
            this.timeArray[i2] = this.leftRightOffset + i3;
            this.hashData.put((this.leftRightOffset + i3) + "", sleepDataBean2);
        }
        initXLabel(data, daysOfMonth);
        this.fingerXDefault = this.leftRightOffset + (DateUtil.getDayOfMonth() * i);
        this.fingerX = this.fingerXDefault;
        int iIntValue = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerX)).intValue();
        if (this.hashData.get(iIntValue + "") != null) {
            this.fingerX = this.hashData.get(iIntValue + "").x;
            this.currBean = this.hashData.get(iIntValue + "");
        }
        OnSelectedListener onSelectedListener = this.listener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(this.currBean);
        }
        postInvalidate();
    }

    public static class SleepDataBean {
        private int awake;
        private float awakeScale;
        private float deepScale;
        private int deepTime;
        private float lightScale;
        private int lightTime;
        private int rapid;
        private float rapidScale;
        private int totalTime;
        private long unixTime;
        private float x;
        private float y;

        public float getX() {
            return this.x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return this.y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public SleepDataBean(long unixTime, int totalTime, int deepTime, int lightTime, int awake, int rapid, float deepScale, float lightScale, float awakeScale, float rapidScale) {
            this.unixTime = unixTime;
            this.totalTime = totalTime;
            this.deepTime = deepTime;
            this.lightTime = lightTime;
            this.awake = awake;
            this.rapid = rapid;
            this.deepScale = deepScale;
            this.lightScale = lightScale;
            this.awakeScale = awakeScale;
            this.rapidScale = rapidScale;
        }

        public int getRapid() {
            return this.rapid;
        }

        public void setRapid(int rapid) {
            this.rapid = rapid;
        }

        public float getRapidScale() {
            return this.rapidScale;
        }

        public void setRapidScale(float rapidScale) {
            this.rapidScale = rapidScale;
        }

        public float getDeepScale() {
            return this.deepScale;
        }

        public void setDeepScale(float deepScale) {
            this.deepScale = deepScale;
        }

        public float getLightScale() {
            return this.lightScale;
        }

        public void setLightScale(float lightScale) {
            this.lightScale = lightScale;
        }

        public float getAwakeScale() {
            return this.awakeScale;
        }

        public void setAwakeScale(float awakeScale) {
            this.awakeScale = awakeScale;
        }

        public long getUnixTime() {
            return this.unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }

        public int getTotalTime() {
            return this.totalTime;
        }

        public void setTotalTime(int totalTime) {
            this.totalTime = totalTime;
        }

        public int getDeepTime() {
            return this.deepTime;
        }

        public void setDeepTime(int deepTime) {
            this.deepTime = deepTime;
        }

        public int getLightTime() {
            return this.lightTime;
        }

        public void setLightTime(int lightTime) {
            this.lightTime = lightTime;
        }

        public int getAwake() {
            return this.awake;
        }

        public void setAwake(int awake) {
            this.awake = awake;
        }
    }
}
