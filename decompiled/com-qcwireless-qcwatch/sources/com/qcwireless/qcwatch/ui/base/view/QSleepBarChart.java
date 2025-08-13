package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.constant.CacheConstants;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.BaseChartView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QSleepBarChart extends BaseChartView {
    public static final int Type_Sleep_Awake = 3;
    public static final int Type_Sleep_Deep = 1;
    public static final int Type_Sleep_Light = 2;
    public static final int Type_Sleep_No_Data = 4;
    public static final int Type_Sleep_Rapid = 5;
    private SleepDataBean currBean;
    private List<SleepDataBean> data;
    private Map<String, SleepDataBean> hashData;
    private List<BaseChartView.XTextChange> labelsList;
    private int leftRightOffset;
    protected OnSelectedListener listener;
    private long sleepEnd;
    private Paint sleepPaint;
    private long sleepStart;
    private long[] startTimeArray;

    public interface OnSelectedListener {
        void onSelected(SleepDataBean value);
    }

    public OnSelectedListener getListener() {
        return this.listener;
    }

    public void setListener(OnSelectedListener listener) {
        this.listener = listener;
    }

    public QSleepBarChart(Context context) {
        super(context);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.leftRightOffset = 100;
        init(context, null);
    }

    public QSleepBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QSleepBarChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public QSleepBarChart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.data = new ArrayList();
        this.hashData = new ArrayMap();
        this.labelsList = new ArrayList();
        this.leftRightOffset = 100;
        init(context, attrs);
    }

    public List<SleepDataBean> getData() {
        return this.data;
    }

    public void setData(long sleepStart, long sleepEnd, List<SleepDataBean> data) {
        long j = sleepStart;
        this.data = data;
        this.sleepStart = j;
        this.sleepEnd = sleepEnd;
        if (this.width == 0) {
            this.width = getDisplay().getWidth();
        }
        int i = 0;
        if (data.size() > 0) {
            this.startTimeArray = new long[data.size()];
        } else {
            this.startTimeArray = new long[]{this.width / 2};
            this.fingerX = this.width / 2;
        }
        this.hashData.clear();
        this.currBean = null;
        long j2 = sleepEnd - j;
        this.fingerXDefault = (j2 / 2) + j;
        while (i < data.size()) {
            long j3 = this.leftRightOffset;
            long sleepEnd2 = data.get(i).getSleepEnd() - j;
            int i2 = this.width;
            long sleepStart2 = this.leftRightOffset + (((data.get(i).getSleepStart() - j) * (this.width - (this.leftRightOffset * 2))) / j2);
            long j4 = sleepStart2 + (((j3 + ((sleepEnd2 * (i2 - (r15 * 2))) / j2)) - sleepStart2) / 2);
            this.startTimeArray[i] = j4;
            this.hashData.put(j4 + "", data.get(i));
            i++;
            j = sleepStart;
        }
        long numberRecent = getNumberRecent(this.startTimeArray, this.width / 2);
        this.fingerX = numberRecent;
        if (this.hashData.get(numberRecent + "") != null) {
            SleepDataBean sleepDataBean = this.hashData.get(numberRecent + "");
            this.currBean = sleepDataBean;
            OnSelectedListener onSelectedListener = this.listener;
            if (onSelectedListener != null) {
                onSelectedListener.onSelected(sleepDataBean);
            }
        }
        initXLabels();
        postInvalidate();
    }

    private void init(Context context, AttributeSet attrs) {
        Paint paint = new Paint();
        this.sleepPaint = paint;
        paint.setAntiAlias(true);
        this.sleepPaint.setStyle(Paint.Style.FILL);
        this.sleepPaint.setStrokeWidth(1.5f);
        initAttr(context, attrs);
    }

    private void initXLabels() {
        this.labelsList.clear();
        int i = (this.width - (this.leftRightOffset * 2)) / 8;
        BaseChartView.XTextChange xTextChange = new BaseChartView.XTextChange();
        xTextChange.setLabel(new DateUtil(this.sleepStart, true).getHHmmDate());
        xTextChange.setX(this.leftRightOffset - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange.setMin(1);
        xTextChange.setMax(this.leftRightOffset + i);
        this.labelsList.add(xTextChange);
        BaseChartView.XTextChange xTextChange2 = new BaseChartView.XTextChange();
        xTextChange2.setLabel(new DateUtil(this.sleepEnd, true).getHHmmDate());
        xTextChange2.setX((this.leftRightOffset + r3) - (getTextWidth(this.textDownPaint, "00:00") / 2.0f));
        xTextChange2.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange2.setMin(this.leftRightOffset + (i * 7));
        xTextChange2.setMax(this.leftRightOffset + (i * 8) + 10);
        this.labelsList.add(xTextChange2);
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
        this.diameterDefault = (int) dp2px(this.context, 200.0f);
        setMeasuredDimension(this.width, this.height);
        this.fingerXMax = this.width - this.circleRadiusMin;
        this.fingerX = this.width / 2;
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
        initXLabels();
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
        int i;
        Iterator<SleepDataBean> it;
        super.onDraw(canvas);
        this.bezierHeight = (this.circleRadiusMax * 2.0f) + 5.0f;
        this.circleRadius = this.circleRadiusMin + (this.circleRadiusMax - this.circleRadiusMin);
        this.spaceToLine = this.circleRadiusMin * 2.0f;
        for (BaseChartView.XTextChange xTextChange : this.labelsList) {
            if (TextUtils.isEmpty(xTextChange.getLabel())) {
                break;
            }
            if (this.fingerX > xTextChange.getMin() && this.fingerX <= xTextChange.getMax()) {
                canvas.drawText(xTextChange.label, xTextChange.x, this.lineHeight - this.spaceToLine, this.textDownPaint);
            } else {
                canvas.drawText(xTextChange.label, xTextChange.x, xTextChange.y, this.textDownPaint);
            }
        }
        int i2 = (this.height - this.bottomOffset) / 5;
        for (Iterator<SleepDataBean> it2 = this.data.iterator(); it2.hasNext(); it2 = it) {
            SleepDataBean next = it2.next();
            this.stepPaint.setStrokeCap(Paint.Cap.ROUND);
            if (next.getType() == 1) {
                this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_1));
                long j = this.leftRightOffset;
                long sleepStart = next.getSleepStart() - this.sleepStart;
                int i3 = this.width;
                int i4 = this.leftRightOffset;
                i = i2;
                it = it2;
                canvas.drawRect(j + ((sleepStart * (i3 - (i4 * 2))) / (this.sleepEnd - this.sleepStart)), i, i4 + (((next.getSleepEnd() - this.sleepStart) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - this.sleepStart)), i * 5, this.sleepPaint);
            } else {
                i = i2;
                it = it2;
                if (next.getType() == 2) {
                    this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_2));
                    long j2 = this.leftRightOffset;
                    long sleepStart2 = next.getSleepStart() - this.sleepStart;
                    int i5 = this.width;
                    int i6 = this.leftRightOffset;
                    canvas.drawRect(j2 + ((sleepStart2 * (i5 - (i6 * 2))) / (this.sleepEnd - this.sleepStart)), i * 3, i6 + (((next.getSleepEnd() - this.sleepStart) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - this.sleepStart)), i * 5, this.sleepPaint);
                } else if (next.getType() == 3) {
                    this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.q_view_sleep_3));
                    long j3 = this.leftRightOffset;
                    long sleepStart3 = next.getSleepStart() - this.sleepStart;
                    int i7 = this.width;
                    int i8 = this.leftRightOffset;
                    canvas.drawRect(j3 + ((sleepStart3 * (i7 - (i8 * 2))) / (this.sleepEnd - this.sleepStart)), i * 4, i8 + (((next.getSleepEnd() - this.sleepStart) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - this.sleepStart)), i * 5, this.sleepPaint);
                } else if (next.type == 4) {
                    this.sleepPaint.setColor(ContextCompat.getColor(this.context, R.color.color_F9F9F9));
                    long j4 = this.leftRightOffset;
                    long sleepStart4 = next.getSleepStart() - this.sleepStart;
                    int i9 = this.width;
                    int i10 = this.leftRightOffset;
                    float f = j4 + ((sleepStart4 * (i9 - (i10 * 2))) / (this.sleepEnd - this.sleepStart));
                    float f2 = i * 5;
                    canvas.drawRect(f, f2, i10 + (((next.getSleepEnd() - this.sleepStart) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - this.sleepStart)), f2, this.sleepPaint);
                } else {
                    if (next.type == 5) {
                        this.sleepPaint.setColor(SkinCompatResources.getColor(this.context, R.color.q_view_sleep_6));
                        long j5 = this.leftRightOffset;
                        long sleepStart5 = next.getSleepStart() - this.sleepStart;
                        int i11 = this.width;
                        int i12 = this.leftRightOffset;
                        canvas.drawRect(j5 + ((sleepStart5 * (i11 - (i12 * 2))) / (this.sleepEnd - this.sleepStart)), i * 2, i12 + (((next.getSleepEnd() - this.sleepStart) * (this.width - (this.leftRightOffset * 2))) / (this.sleepEnd - this.sleepStart)), i * 5, this.sleepPaint);
                    }
                    i2 = i;
                }
            }
            i2 = i;
        }
        this.linePaint.setShader(new LinearGradient(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, new int[]{ContextCompat.getColor(this.context, R.color.q_view_sleep_5), ContextCompat.getColor(this.context, R.color.q_view_sleep_4), ContextCompat.getColor(this.context, R.color.q_view_sleep_5)}, (float[]) null, Shader.TileMode.CLAMP));
        this.linePaint.setStrokeWidth(dp2px(this.context, 1.0f));
        canvas.drawLine(this.fingerX, this.lineHeight - this.bottomOffset, this.fingerX, 60.0f, this.linePaint);
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
            if (this.data.size() <= 0) {
                this.startTimeArray = new long[]{this.width / 2};
                this.fingerX = this.width / 2;
            } else {
                if (this.fingerX < this.fingerXmin) {
                    this.fingerX = this.fingerXmin;
                }
                if (this.fingerX > this.fingerXMax) {
                    this.fingerX = this.fingerXMax;
                }
                long numberRecent = getNumberRecent(this.startTimeArray, (long) this.fingerX);
                this.fingerX = numberRecent;
                if (this.hashData.get(numberRecent + "") != null) {
                    this.currBean = this.hashData.get(numberRecent + "");
                }
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
            long numberRecent2 = getNumberRecent(this.startTimeArray, (long) this.fingerX);
            this.fingerX = numberRecent2;
            if (this.hashData.get(numberRecent2 + "") != null) {
                this.currBean = this.hashData.get(numberRecent2 + "");
            }
            postInvalidate();
        }
        OnSelectedListener onSelectedListener = this.listener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(this.currBean);
        }
        return true;
    }

    public long getNumberRecent(long[] intarray, long number) {
        long jAbs = Math.abs(number - intarray[0]);
        long j = intarray[0];
        for (long j2 : intarray) {
            long jAbs2 = Math.abs(number - j2);
            if (jAbs2 <= jAbs) {
                j = j2;
                jAbs = jAbs2;
            }
        }
        return j;
    }

    public static class SleepDataBean {
        private long sleepEnd;
        private long sleepStart;
        private int type;

        public SleepDataBean() {
        }

        public SleepDataBean(long sleepStart, long sleepEnd, int type) {
            this.sleepStart = sleepStart;
            this.sleepEnd = sleepEnd;
            this.type = type;
        }

        public long getSleepStart() {
            return this.sleepStart;
        }

        public void setSleepStart(long sleepStart) {
            this.sleepStart = sleepStart;
        }

        public long getSleepEnd() {
            return this.sleepEnd;
        }

        public void setSleepEnd(long sleepEnd) {
            this.sleepEnd = sleepEnd;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
