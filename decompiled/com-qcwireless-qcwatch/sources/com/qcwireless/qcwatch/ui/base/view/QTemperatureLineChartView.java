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
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QTemperatureLineChartView extends BaseChartView {
    int[] colors;
    int[] colorsLine;
    private TemperatureDataBean currBean;
    private Paint dotPaint;
    private Path fillPath;
    private TemperatureDataBean firstBean;
    private Map<String, TemperatureDataBean> hashData;
    private List<TemperatureDataBean> heartList;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    private OnSelectedListener listener;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    float[] position;
    private boolean temperature;
    private int[] timeArray;
    private List<Integer> timeArrayList;
    private boolean today;
    private int xMax;
    private Path yLinePath;
    private int yMax;

    public interface OnSelectedListener {
        void onSelected(TemperatureDataBean bean);
    }

    public void setSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public QTemperatureLineChartView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[48];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, null);
    }

    public QTemperatureLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[48];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QTemperatureLineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[48];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QTemperatureLineChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 42;
        this.timeArray = new int[48];
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
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
        this.paint.setStrokeWidth(dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setPathEffect(new CornerPathEffect(10.0f));
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_temperature_4));
        this.paintY.setStrokeWidth(dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint();
        this.dotPaint = paint4;
        paint4.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.path = new Path();
        this.fillPath = new Path();
        this.yLinePath = new Path();
        initAttr(context, attrs);
        initPublic(context, attrs);
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
        this.fingerXDefault = 720.0f;
        initXLabels();
        filterData(this.heartList, this.today);
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
        this.paintFill.setShader(new LinearGradient(this.width, 0.0f, this.width, this.height - (this.bottomOffset * 2), this.colors, this.position, Shader.TileMode.CLAMP));
        if (this.heartList.size() > 1) {
            canvas.drawPath(this.fillPath, this.paintFill);
            canvas.drawPath(this.path, this.paint);
        }
        for (int i = 0; i < this.heartList.size(); i++) {
            if (this.heartList.get(i).isBegin()) {
                canvas.drawCircle(r2.x, r2.y, dp2px(this.context, 0.1f), this.paint);
            }
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.colorsLine, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(3.0f);
        canvas.drawPath(this.yLinePath, this.paintY);
        this.textDownPaint.setTextSize(dp2px(this.context, 10.0f));
        int i2 = (this.lineHeight - this.bottomOffset) / 50;
        if (this.temperature) {
            canvas.drawText("95", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 50) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("97", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 40) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("99", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 30) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("101", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), (i2 * 20) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("103", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), (i2 * 10) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        } else {
            canvas.drawText("35", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 50) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("36", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 40) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("37", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 30) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("38", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 20) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
            canvas.drawText("39", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), (i2 * 10) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        }
        canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
        if (this.currBean != null) {
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.color_FFFFFF));
            canvas.drawCircle(this.currBean.x, this.currBean.y + dp2px(this.context, 1.0f), dp2px(this.context, 4.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_temperature_2));
            canvas.drawCircle(this.currBean.x, this.currBean.y + dp2px(this.context, 1.0f), dp2px(this.context, 3.0f), this.dotPaint);
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
        xTextChange5.setLabel("00:00");
        xTextChange5.setX((this.leftRightOffset + (i * 8)) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange5.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange5.setMin(this.leftRightOffset + i4);
        xTextChange5.setMax(this.leftRightOffset + (i * 9));
        this.labelsList.add(xTextChange5);
    }

    public void setData(List<TemperatureDataBean> list, boolean today, boolean temperature) {
        this.today = today;
        this.temperature = temperature;
        this.heartList = list;
        this.currBean = null;
        this.path.reset();
        this.timeArrayList.clear();
        this.fillPath.reset();
        this.hashData.clear();
        if (this.width > 0) {
            filterData(list, today);
        }
        postInvalidate();
    }

    private void filterData(List<TemperatureDataBean> list, boolean today) {
        int i = (this.lineHeight - this.bottomOffset) / 50;
        for (int i2 = 0; i2 < 48; i2++) {
            this.timeArrayList.add(Integer.valueOf(i2 * 30));
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            TemperatureDataBean temperatureDataBean = list.get(i3);
            this.timeArrayList.add(Integer.valueOf(temperatureDataBean.getMin()));
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
            this.hashData.put(temperatureDataBean.getMin() + "", temperatureDataBean);
        }
        this.timeArray = new int[this.timeArrayList.size()];
        for (int i6 = 0; i6 < this.timeArrayList.size(); i6++) {
            this.timeArray[i6] = this.timeArrayList.get(i6).intValue();
        }
        for (int i7 = 0; i7 < this.heartList.size(); i7++) {
            TemperatureDataBean temperatureDataBean2 = this.heartList.get(i7);
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
                    if ((i7 < this.heartList.size() - 1 && this.heartList.get(i7 + 1).isBegin()) || i7 == this.heartList.size() - 1) {
                        this.fillPath.lineTo(temperatureDataBean2.x, this.height - this.bottomOffset);
                        this.fillPath.lineTo(this.firstBean.x, this.height - this.bottomOffset);
                        this.fillPath.lineTo(this.firstBean.x, temperatureDataBean2.y);
                        this.fillPath.close();
                    }
                }
            }
        }
        float f = i * 50;
        this.yLinePath.moveTo(this.leftRightOffset, f);
        this.yLinePath.lineTo(this.width - this.leftRightOffset, f);
        float f2 = i * 40;
        this.yLinePath.moveTo(this.leftRightOffset, f2);
        this.yLinePath.lineTo(this.width - this.leftRightOffset, f2);
        float f3 = i * 30;
        this.yLinePath.moveTo(this.leftRightOffset, f3);
        this.yLinePath.lineTo(this.width - this.leftRightOffset, f3);
        float f4 = i * 20;
        this.yLinePath.moveTo(this.leftRightOffset, f4);
        this.yLinePath.lineTo(this.width - this.leftRightOffset, f4);
        float f5 = i * 10;
        this.yLinePath.moveTo(this.leftRightOffset, f5);
        this.yLinePath.lineTo(this.width - this.leftRightOffset, f5);
        if (this.heartList.size() > 0) {
            if (today) {
                try {
                    List<TemperatureDataBean> list2 = this.heartList;
                    this.fingerXDefault = list2.get(list2.size() - 1).x;
                    int[] iArr = this.timeArray;
                    List<TemperatureDataBean> list3 = this.heartList;
                    int iIntValue = getNumberThree(iArr, Integer.valueOf(list3.get(list3.size() - 1).min)).intValue();
                    this.valueSelected = (int) ((this.fingerX * 1440.0f) / (this.width - (this.leftRightOffset * 2)));
                    int i8 = this.width;
                    int i9 = this.leftRightOffset;
                    this.fingerX = (((i8 - (i9 * 2)) * iIntValue) / 1440) + i9;
                    if (this.listener != null) {
                        TemperatureDataBean temperatureDataBean3 = this.hashData.get(iIntValue + "");
                        this.currBean = temperatureDataBean3;
                        this.listener.onSelected(temperatureDataBean3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                this.fingerXDefault = 720.0f;
                int iIntValue2 = getNumberThree(this.timeArray, Integer.valueOf((int) this.fingerXDefault)).intValue();
                if (this.listener != null) {
                    TemperatureDataBean temperatureDataBean4 = this.hashData.get(iIntValue2 + "");
                    this.currBean = temperatureDataBean4;
                    XLog.i(temperatureDataBean4);
                    if (this.currBean != null) {
                        this.fingerX = r12.x;
                        this.listener.onSelected(this.currBean);
                    } else {
                        TemperatureDataBean temperatureDataBean5 = new TemperatureDataBean(720, 0.0f, false);
                        this.currBean = temperatureDataBean5;
                        int i10 = this.width;
                        int i11 = this.leftRightOffset;
                        temperatureDataBean5.x = (((i10 - (i11 * 2)) * 720) / this.xMax) + i11;
                        this.currBean.y = (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 720) / this.yMax);
                        this.fingerX = this.currBean.x;
                        this.listener.onSelected(this.currBean);
                    }
                }
            }
        } else if (today) {
            if (this.listener != null) {
                int todayMinNoPlus = new DateUtil().getTodayMinNoPlus();
                TemperatureDataBean temperatureDataBean6 = new TemperatureDataBean(todayMinNoPlus, 0.0f, false);
                this.currBean = temperatureDataBean6;
                int i12 = this.width;
                int i13 = this.leftRightOffset;
                temperatureDataBean6.x = (((i12 - (i13 * 2)) * todayMinNoPlus) / this.xMax) + i13;
                this.currBean.y = (this.lineHeight - this.bottomOffset) - ((todayMinNoPlus * (this.height - this.bottomOffset)) / this.yMax);
                this.fingerX = this.currBean.x;
                this.listener.onSelected(this.currBean);
            }
        } else if (this.listener != null) {
            TemperatureDataBean temperatureDataBean7 = new TemperatureDataBean(720, 0.0f, false);
            this.currBean = temperatureDataBean7;
            int i14 = this.width;
            int i15 = this.leftRightOffset;
            temperatureDataBean7.x = (((i14 - (i15 * 2)) * 720) / this.xMax) + i15;
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
            TemperatureDataBean temperatureDataBean = this.hashData.get(iIntValue + "");
            this.currBean = temperatureDataBean;
            OnSelectedListener onSelectedListener = this.listener;
            if (onSelectedListener != null) {
                if (temperatureDataBean == null) {
                    onSelectedListener.onSelected(new TemperatureDataBean(iIntValue, 0.0f, false));
                } else {
                    onSelectedListener.onSelected(temperatureDataBean);
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
            TemperatureDataBean temperatureDataBean2 = this.hashData.get(iIntValue2 + "");
            this.currBean = temperatureDataBean2;
            OnSelectedListener onSelectedListener2 = this.listener;
            if (onSelectedListener2 != null) {
                if (temperatureDataBean2 == null) {
                    onSelectedListener2.onSelected(new TemperatureDataBean(iIntValue2, 0.0f, false));
                } else {
                    onSelectedListener2.onSelected(temperatureDataBean2);
                }
            }
            postInvalidate();
        }
        return true;
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
}
