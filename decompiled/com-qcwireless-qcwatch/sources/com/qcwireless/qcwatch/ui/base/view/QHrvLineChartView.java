package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QHrvLineChartView extends BaseChartView {
    int[] colors;
    int[] colorsLine;
    private HeartDataBean currBean;
    private Paint dotPaint;
    private HeartDataBean firstBean;
    private Map<String, HeartDataBean> hashData;
    private List<HeartDataBean> heartList;
    private int interval;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    private OnSelectedListener listener;
    private Paint paint;
    private Paint paintY;
    private Path path;
    float[] position;
    private int[] timeArray;
    private List<Integer> timeArrayList;
    private boolean today;
    private int xMax;
    private Path yLinePath;
    private int yMax;

    public interface OnSelectedListener {
        void onSelected(HeartDataBean bean);
    }

    private void initTimeStamp() {
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public QHrvLineChartView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 220;
        this.timeArray = new int[288];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.interval = 5;
        init(context, null);
    }

    public QHrvLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 220;
        this.timeArray = new int[288];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.interval = 5;
        init(context, attrs);
    }

    public QHrvLineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 220;
        this.timeArray = new int[288];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.interval = 5;
        init(context, attrs);
    }

    public QHrvLineChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 220;
        this.timeArray = new int[288];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        this.interval = 5;
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
        this.paintY = paint2;
        paint2.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_heart_8));
        this.paintY.setStrokeWidth(dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.dotPaint = paint3;
        paint3.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.path = new Path();
        this.yLinePath = new Path();
        initAttr(context, attrs);
        initPublic(context, attrs);
        initTimeStamp();
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
        setData(this.heartList, this.today);
        initXLabels();
        postInvalidate();
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
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
        this.mPaddingStart = getPaddingStart();
        this.mPaddingEnd = getPaddingEnd();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        this.bezierHeight = 0.0f;
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
        if (this.heartList.size() > 1) {
            canvas.drawPath(this.path, this.paint);
        }
        for (int i = 0; i < this.heartList.size(); i++) {
            HeartDataBean heartDataBean = this.heartList.get(i);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_hrv_3));
            canvas.drawCircle(heartDataBean.x, heartDataBean.y, dp2px(this.context, 2.0f), this.dotPaint);
            this.dotPaint.setColor(SkinCompatResources.getColor(this.context, R.color.q_view_hrv_4));
            canvas.drawCircle(heartDataBean.x, heartDataBean.y, dp2px(this.context, 1.0f), this.dotPaint);
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.colorsLine, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(3.0f);
        canvas.drawPath(this.yLinePath, this.paintY);
        this.textDownPaint.setTextSize(dp2px(this.context, 10.0f));
        canvas.drawText("0", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 1) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("59", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 59) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("119", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 119) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("178", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 178) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
        if (this.currBean != null) {
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_common_7));
            canvas.drawCircle(this.currBean.x, this.currBean.y + dp2px(this.context, 2.0f), dp2px(this.context, 4.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_hrv_3));
            canvas.drawCircle(this.currBean.x, this.currBean.y + dp2px(this.context, 2.0f), dp2px(this.context, 3.0f), this.dotPaint);
        }
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
        xTextChange5.setLabel("23:59");
        xTextChange5.setX((this.leftRightOffset + (i * 8)) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange5.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange5.setMin(this.leftRightOffset + i4);
        xTextChange5.setMax(this.leftRightOffset + (i * 9));
        this.labelsList.add(xTextChange5);
    }

    public void setData(List<HeartDataBean> list, boolean today) {
        this.today = today;
        initTimeStamp();
        this.heartList = list;
        this.currBean = null;
        this.timeArrayList.clear();
        this.path.reset();
        this.hashData.clear();
        if (this.width > 0) {
            filterData(list, today);
        }
        postInvalidate();
    }

    private void filterData(List<HeartDataBean> list, boolean today) {
        for (int i = 0; i < 288; i++) {
            this.timeArrayList.add(Integer.valueOf(i * 5));
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            HeartDataBean heartDataBean = list.get(i2);
            this.timeArrayList.add(Integer.valueOf(heartDataBean.getMin()));
            int min = heartDataBean.getMin();
            int i3 = this.width;
            int i4 = this.leftRightOffset;
            heartDataBean.x = ((min * (i3 - (i4 * 2))) / this.xMax) + i4;
            heartDataBean.y = (this.lineHeight - this.bottomOffset) - ((heartDataBean.getValue() * (this.height - this.bottomOffset)) / this.yMax);
            if (i2 <= 0) {
                heartDataBean.setBegin(true);
            }
            this.hashData.put(heartDataBean.getMin() + "", heartDataBean);
        }
        this.timeArray = new int[this.timeArrayList.size()];
        for (int i5 = 0; i5 < this.timeArrayList.size(); i5++) {
            this.timeArray[i5] = this.timeArrayList.get(i5).intValue();
        }
        for (int i6 = 0; i6 < this.heartList.size(); i6++) {
            HeartDataBean heartDataBean2 = this.heartList.get(i6);
            this.hashData.put(heartDataBean2.getMin() + "", heartDataBean2);
            if (heartDataBean2.isBegin()) {
                this.firstBean = heartDataBean2;
                this.path.moveTo(heartDataBean2.x, heartDataBean2.y);
                this.path.lineTo(heartDataBean2.x, heartDataBean2.y);
            } else {
                this.path.lineTo(heartDataBean2.x, heartDataBean2.y);
            }
        }
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 1) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 1) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 59) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 59) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 119) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 119) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 178) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 178) / this.yMax));
        if (this.heartList.size() > 0) {
            if (today) {
                try {
                    if (this.heartList.size() == 1) {
                        int min2 = this.heartList.get(0).getMin();
                        int i7 = this.width;
                        int i8 = this.leftRightOffset;
                        this.fingerX = ((min2 * (i7 - (i8 * 2))) / 1440) + i8;
                        HeartDataBean heartDataBean3 = this.heartList.get(0);
                        this.currBean = heartDataBean3;
                        OnSelectedListener onSelectedListener = this.listener;
                        if (onSelectedListener != null) {
                            onSelectedListener.onSelected(heartDataBean3);
                        }
                    } else {
                        List<HeartDataBean> list2 = this.heartList;
                        this.fingerXDefault = list2.get(list2.size() - 1).x;
                        int[] iArr = this.timeArray;
                        List<HeartDataBean> list3 = this.heartList;
                        int iIntValue = getNumberThree(iArr, Integer.valueOf(list3.get(list3.size() - 1).min)).intValue();
                        int i9 = this.width;
                        int i10 = this.leftRightOffset;
                        this.fingerX = (((i9 - (i10 * 2)) * iIntValue) / 1440) + i10;
                        if (this.listener != null) {
                            HeartDataBean heartDataBean4 = this.hashData.get(iIntValue + "");
                            this.currBean = heartDataBean4;
                            this.listener.onSelected(heartDataBean4);
                        }
                    }
                } catch (Exception unused) {
                }
            } else {
                this.fingerXDefault = 720.0f;
                int iIntValue2 = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerXDefault)).intValue();
                int i11 = this.width;
                int i12 = this.leftRightOffset;
                this.fingerX = (((i11 - (i12 * 2)) * iIntValue2) / 1440) + i12;
                if (this.listener != null) {
                    HeartDataBean heartDataBean5 = this.hashData.get(iIntValue2 + "");
                    this.currBean = heartDataBean5;
                    this.listener.onSelected(heartDataBean5);
                }
            }
        } else if (today) {
            if (this.listener != null) {
                int todayMinNoPlus = new DateUtil().getTodayMinNoPlus();
                HeartDataBean heartDataBean6 = new HeartDataBean(todayMinNoPlus, 0, false, 5);
                this.currBean = heartDataBean6;
                int i13 = this.width;
                int i14 = this.leftRightOffset;
                heartDataBean6.x = (((i13 - (i14 * 2)) * todayMinNoPlus) / this.xMax) + i14;
                this.currBean.y = (this.lineHeight - this.bottomOffset) - ((todayMinNoPlus * (this.height - this.bottomOffset)) / this.yMax);
                this.fingerX = this.currBean.x;
                this.listener.onSelected(this.currBean);
            }
        } else if (this.listener != null) {
            HeartDataBean heartDataBean7 = new HeartDataBean(720, 0, false, 5);
            this.currBean = heartDataBean7;
            int i15 = this.width;
            int i16 = this.leftRightOffset;
            heartDataBean7.x = (((i15 - (i16 * 2)) * 720) / this.xMax) + i16;
            this.currBean.y = (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 720) / this.yMax);
            this.fingerX = this.currBean.x;
            this.listener.onSelected(this.currBean);
        }
        postInvalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.fingerX = event.getX();
            getParent().requestDisallowInterceptTouchEvent(true);
            this.animatorFingerIn.start();
            this.valueSelected = (int) ((this.fingerX * 1440.0f) / this.width);
            int iIntValue = getNumberThree(this.timeArray, Integer.valueOf(this.valueSelected)).intValue();
            int i = this.width;
            int i2 = this.leftRightOffset;
            this.fingerX = (((i - (i2 * 2)) * iIntValue) / 1440) + i2;
            HeartDataBean heartDataBean = this.hashData.get(iIntValue + "");
            this.currBean = heartDataBean;
            OnSelectedListener onSelectedListener = this.listener;
            if (onSelectedListener != null) {
                if (heartDataBean == null) {
                    onSelectedListener.onSelected(new HeartDataBean(iIntValue, 0, false, 5));
                } else {
                    onSelectedListener.onSelected(heartDataBean);
                }
            }
            postInvalidate();
        } else if (action == 1) {
            this.animatorFingerOut.start();
        } else if (action == 2) {
            this.fingerX = event.getX();
            this.valueSelected = (int) ((this.fingerX * 1440.0f) / this.width);
            int iIntValue2 = getNumberThree(this.timeArray, Integer.valueOf(this.valueSelected)).intValue();
            int i3 = this.width;
            int i4 = this.leftRightOffset;
            this.fingerX = (((i3 - (i4 * 2)) * iIntValue2) / 1440) + i4;
            HeartDataBean heartDataBean2 = this.hashData.get(iIntValue2 + "");
            this.currBean = heartDataBean2;
            OnSelectedListener onSelectedListener2 = this.listener;
            if (onSelectedListener2 != null) {
                if (heartDataBean2 == null) {
                    onSelectedListener2.onSelected(new HeartDataBean(iIntValue2, 0, false, 5));
                } else {
                    onSelectedListener2.onSelected(heartDataBean2);
                }
            }
            postInvalidate();
        }
        return true;
    }

    public static class HeartDataBean {
        private boolean begin;
        private int min;
        private int range;
        private long unixTime;
        private int value;
        public int x;
        public int y;

        public HeartDataBean(int min, int value, boolean begin, int range) {
            this.min = min;
            this.value = value;
            this.begin = begin;
            this.range = range;
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

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
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

        public int getRange() {
            return this.range;
        }

        public void setRange(int range) {
            this.range = range;
        }
    }
}
