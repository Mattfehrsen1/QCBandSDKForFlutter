package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class QBloodPressureChartView extends BaseChartView {
    private List<BpDataBean> bpList;
    int[] colorsLine;
    private BpDataBean currBean;
    private Paint dotPaint;
    private Map<String, BpDataBean> hashData;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    private OnSelectedListener listener;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    private Path path1;
    private List<Integer> timeArrayList;
    private boolean today;
    private int xMax;
    private Path yLinePath;
    private int yMax;

    public interface OnSelectedListener {
        void onSelected(BpDataBean bean);
    }

    public void setSelectedListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public QBloodPressureChartView(Context context) {
        super(context);
        this.xMax = 1440;
        this.yMax = 240;
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.bpList = new ArrayList();
        this.leftRightOffset = 100;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, null);
    }

    public QBloodPressureChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.yMax = 240;
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.bpList = new ArrayList();
        this.leftRightOffset = 100;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QBloodPressureChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.yMax = 240;
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.bpList = new ArrayList();
        this.leftRightOffset = 100;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    public QBloodPressureChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.yMax = 240;
        this.timeArrayList = new ArrayList();
        this.labelsList = new ArrayList();
        this.bpList = new ArrayList();
        this.leftRightOffset = 100;
        this.colorsLine = new int[0];
        this.hashData = new ArrayMap();
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_blood_pressure_6), ContextCompat.getColor(context, R.color.q_view_blood_pressure_2), ContextCompat.getColor(context, R.color.q_view_blood_pressure_6)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_blood_pressure_2));
        this.paint.setStrokeWidth(dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.paintFill.setAntiAlias(true);
        this.paintFill.setStrokeWidth(dp2px(context, 0.5f));
        this.paintFill.setColor(ContextCompat.getColor(context, R.color.q_view_blood_pressure_4));
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_blood_pressure_5));
        this.paintY.setStrokeWidth(dp2px(context, 0.05f));
        this.paintY.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f));
        this.paintY.setStyle(Paint.Style.STROKE);
        Paint paint4 = new Paint();
        this.dotPaint = paint4;
        paint4.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);
        this.dotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.path = new Path();
        this.path1 = new Path();
        this.yLinePath = new Path();
        initAttr(context, attrs);
        initPublic(context, attrs);
        initBpDefaultData();
    }

    private void initBpDefaultData() {
        for (int i = 0; i < 1440; i++) {
            this.hashData.put(i + "", null);
        }
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
        filterData(this.bpList, this.today);
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
        if (this.bpList.size() > 1) {
            canvas.drawPath(this.path1, this.paintFill);
            canvas.drawPath(this.path, this.paint);
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.colorsLine, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(3.0f);
        canvas.drawPath(this.yLinePath, this.paintY);
        this.textDownPaint.setTextSize(dp2px(this.context, 10.0f));
        canvas.drawText("60", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 60) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("90", this.leftRightOffset - getTextWidth(this.textDownPaint, "000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 90) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("120", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 120) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("150", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 150) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawText("180", this.leftRightOffset - getTextWidth(this.textDownPaint, "0000"), ((this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 180) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
        for (BpDataBean bpDataBean : this.bpList) {
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.color_FFFFFF));
            canvas.drawCircle(bpDataBean.sbpX, bpDataBean.sbpY, dp2px(this.context, 3.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_blood_pressure_2));
            canvas.drawCircle(bpDataBean.sbpX, bpDataBean.sbpY, dp2px(this.context, 2.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.color_FFFFFF));
            canvas.drawCircle(bpDataBean.dbpX, bpDataBean.dbpY, dp2px(this.context, 3.0f), this.dotPaint);
            this.dotPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_blood_pressure_4));
            canvas.drawCircle(bpDataBean.dbpX, bpDataBean.dbpY, dp2px(this.context, 2.0f), this.dotPaint);
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

    public void setData(List<BpDataBean> list, boolean today) {
        this.today = today;
        this.bpList = list;
        this.path.reset();
        this.hashData.clear();
        this.timeArrayList.clear();
        if (this.width > 0) {
            filterData(list, today);
        } else {
            this.listener.onSelected(null);
        }
        postInvalidate();
    }

    private void filterData(List<BpDataBean> list, boolean today) {
        this.currBean = null;
        for (int i = 0; i < list.size(); i++) {
            BpDataBean bpDataBean = list.get(i);
            int min = bpDataBean.getMin();
            int i2 = this.width;
            int i3 = this.leftRightOffset;
            bpDataBean.dbpX = ((min * (i2 - (i3 * 2))) / this.xMax) + i3;
            bpDataBean.dbpY = (this.lineHeight - this.bottomOffset) - ((bpDataBean.getDbp() * (this.height - this.bottomOffset)) / this.yMax);
            int min2 = bpDataBean.getMin();
            int i4 = this.width;
            int i5 = this.leftRightOffset;
            bpDataBean.sbpX = ((min2 * (i4 - (i5 * 2))) / this.xMax) + i5;
            bpDataBean.sbpY = (this.lineHeight - this.bottomOffset) - ((bpDataBean.getSbp() * (this.height - this.bottomOffset)) / this.yMax);
        }
        this.path.reset();
        this.path1.reset();
        int min3 = 1025;
        int min4 = 0;
        for (int i6 = 0; i6 < this.bpList.size(); i6++) {
            BpDataBean bpDataBean2 = this.bpList.get(i6);
            if (bpDataBean2.getMin() < min3) {
                min3 = bpDataBean2.getMin();
            }
            if (bpDataBean2.getMin() > min4) {
                min4 = bpDataBean2.getMin();
            }
        }
        for (int i7 = 0; i7 < 24; i7++) {
            this.timeArrayList.add(Integer.valueOf(i7 * 60));
        }
        for (int i8 = 0; i8 < this.bpList.size(); i8++) {
            BpDataBean bpDataBean3 = this.bpList.get(i8);
            if (bpDataBean3.dbp > 40 && bpDataBean3.sbp < 220) {
                this.hashData.put(bpDataBean3.getMin() + "", bpDataBean3);
                this.timeArrayList.add(Integer.valueOf(bpDataBean3.getMin()));
                if (i8 == 0) {
                    this.path.moveTo(bpDataBean3.sbpX, bpDataBean3.sbpY);
                    this.path.lineTo(bpDataBean3.sbpX, bpDataBean3.sbpY);
                    this.path1.moveTo(bpDataBean3.dbpX, bpDataBean3.dbpY);
                    this.path1.lineTo(bpDataBean3.dbpX, bpDataBean3.dbpY);
                } else {
                    this.path.lineTo(bpDataBean3.sbpX, bpDataBean3.sbpY);
                }
                this.path1.lineTo(bpDataBean3.dbpX, bpDataBean3.dbpY);
            }
        }
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 60) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 60) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 90) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 90) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 120) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 120) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 150) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 150) / this.yMax));
        this.yLinePath.moveTo(this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 180) / this.yMax));
        this.yLinePath.lineTo(this.width - this.leftRightOffset, (this.lineHeight - this.bottomOffset) - (((this.height - this.bottomOffset) * 180) / this.yMax));
        if (today) {
            DateUtil dateUtil = new DateUtil();
            if (this.bpList.size() > 0) {
                try {
                    BpDataBean bpDataBean4 = list.get(0);
                    this.fingerX = bpDataBean4.sbpX;
                    OnSelectedListener onSelectedListener = this.listener;
                    if (onSelectedListener != null) {
                        this.currBean = bpDataBean4;
                        onSelectedListener.onSelected(bpDataBean4);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                int[] iArr = new int[this.timeArrayList.size()];
                for (int i9 = 0; i9 < this.timeArrayList.size(); i9++) {
                    iArr[i9] = this.timeArrayList.get(i9).intValue();
                }
                int iIntValue = getNumberThree(iArr, Integer.valueOf((dateUtil.getHour() * 60) + dateUtil.getMinute())).intValue();
                int i10 = this.width;
                int i11 = this.leftRightOffset;
                this.fingerX = ((iIntValue * (i10 - (i11 * 2))) / 1440) + i11;
                if (this.listener != null) {
                    BpDataBean bpDataBean5 = new BpDataBean((dateUtil.getHour() * 60) + dateUtil.getMinute(), 0, 0);
                    this.currBean = bpDataBean5;
                    this.listener.onSelected(bpDataBean5);
                }
            }
        } else if (this.bpList.size() > 0) {
            this.fingerXDefault = 720.0f;
            int[] iArr2 = new int[this.timeArrayList.size()];
            for (int i12 = 0; i12 < this.timeArrayList.size(); i12++) {
                iArr2[i12] = this.timeArrayList.get(i12).intValue();
            }
            int iIntValue2 = getNumberThree(iArr2, Integer.valueOf((int) this.fingerXDefault)).intValue();
            int i13 = this.width;
            int i14 = this.leftRightOffset;
            this.fingerX = (((i13 - (i14 * 2)) * iIntValue2) / 1440) + i14;
            if (this.listener != null) {
                BpDataBean bpDataBean6 = this.hashData.get(iIntValue2 + "");
                this.currBean = bpDataBean6;
                XLog.i(bpDataBean6);
                if (this.currBean == null) {
                    this.currBean = new BpDataBean(720, 0, 0);
                }
                this.listener.onSelected(this.currBean);
            }
        } else {
            this.fingerXDefault = this.width / 2;
            this.fingerX = this.width / 2;
            if (this.listener != null) {
                BpDataBean bpDataBean7 = new BpDataBean(720, 0, 0);
                this.currBean = bpDataBean7;
                this.listener.onSelected(bpDataBean7);
            }
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
            int[] iArr = new int[this.timeArrayList.size()];
            for (int i = 0; i < this.timeArrayList.size(); i++) {
                iArr[i] = this.timeArrayList.get(i).intValue();
            }
            int iIntValue = getNumberThree(iArr, Integer.valueOf(this.valueSelected)).intValue();
            int i2 = this.width;
            int i3 = this.leftRightOffset;
            this.fingerX = (((i2 - (i3 * 2)) * iIntValue) / 1440) + i3;
            BpDataBean bpDataBean = this.hashData.get(iIntValue + "");
            this.currBean = bpDataBean;
            OnSelectedListener onSelectedListener = this.listener;
            if (onSelectedListener != null) {
                if (bpDataBean == null) {
                    onSelectedListener.onSelected(new BpDataBean(iIntValue, 0, 0));
                } else {
                    onSelectedListener.onSelected(bpDataBean);
                }
            }
            postInvalidate();
        } else if (action == 1) {
            this.animatorFingerOut.start();
        } else if (action == 2) {
            this.fingerX = event.getX();
            this.valueSelected = (int) ((this.fingerX * 1440.0f) / this.width);
            int[] iArr2 = new int[this.timeArrayList.size()];
            for (int i4 = 0; i4 < this.timeArrayList.size(); i4++) {
                iArr2[i4] = this.timeArrayList.get(i4).intValue();
            }
            int iIntValue2 = getNumberThree(iArr2, Integer.valueOf(this.valueSelected)).intValue();
            int i5 = this.width;
            int i6 = this.leftRightOffset;
            this.fingerX = (((i5 - (i6 * 2)) * iIntValue2) / 1440) + i6;
            this.currBean = this.hashData.get(iIntValue2 + "");
            XLog.i(Integer.valueOf(iIntValue2));
            OnSelectedListener onSelectedListener2 = this.listener;
            if (onSelectedListener2 != null) {
                BpDataBean bpDataBean2 = this.currBean;
                if (bpDataBean2 == null) {
                    onSelectedListener2.onSelected(new BpDataBean(iIntValue2, 0, 0));
                } else {
                    onSelectedListener2.onSelected(bpDataBean2);
                }
            }
            postInvalidate();
        }
        return true;
    }

    public static class BpDataBean {
        private int dbp;
        public int dbpX;
        public int dbpY;
        private int min;
        private int sbp;
        public int sbpX;
        public int sbpY;
        private long unixTime;

        public BpDataBean(int min, int sbp, int dbp) {
            this.min = min;
            this.sbp = sbp;
            this.dbp = dbp;
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getSbp() {
            return this.sbp;
        }

        public void setSbp(int sbp) {
            this.sbp = sbp;
        }

        public int getDbp() {
            return this.dbp;
        }

        public void setDbp(int dbp) {
            this.dbp = dbp;
        }

        public int getSbpX() {
            return this.sbpX;
        }

        public void setSbpX(int sbpX) {
            this.sbpX = sbpX;
        }

        public int getSbpY() {
            return this.sbpY;
        }

        public void setSbpY(int sbpY) {
            this.sbpY = sbpY;
        }

        public int getDbpX() {
            return this.dbpX;
        }

        public void setDbpX(int dbpX) {
            this.dbpX = dbpX;
        }

        public int getDbpY() {
            return this.dbpY;
        }

        public void setDbpY(int dbpY) {
            this.dbpY = dbpY;
        }

        public long getUnixTime() {
            return this.unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }
    }
}
