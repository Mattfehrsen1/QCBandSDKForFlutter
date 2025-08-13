package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class QHeartSportDetailLineChartView extends View implements SkinCompatSupportable {
    protected int bottomOffset;
    protected int colorBall;
    protected int colorBgSelected;
    protected int colorLine;
    protected int colorValue;
    protected int colorValueSelected;
    int[] colors;
    int[] colorsLine;
    private Context context;
    protected int diameterDefault;
    private Paint dotPaint;
    private long endTime;
    private Path fillPath;
    private HeartDataBean firstBean;
    private List<HeartDataBean> heartList;
    protected int height;
    private List<XTextChange> labelsList;
    private int leftRightOffset;
    protected int lineHeight;
    protected Paint linePaint;
    private Paint paint;
    private Paint paintFill;
    private Paint paintY;
    private Path path;
    float[] position;
    private long startTime;
    protected int textDownColor;
    private int textDownColorId;
    protected Paint textDownPaint;
    protected float textSelectedSize;
    protected float textSize;
    protected int width;
    private int xMax;
    private int xMin;
    private Path yLinePath;
    private int yMax;

    public QHeartSportDetailLineChartView(Context context) {
        super(context);
        this.xMax = 1440;
        this.xMin = 0;
        this.yMax = 240;
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 50;
        this.diameterDefault = 30;
        this.textSize = 12.0f;
        this.textSelectedSize = 14.0f;
        this.context = context;
        init(context, null);
    }

    public QHeartSportDetailLineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.xMax = 1440;
        this.xMin = 0;
        this.yMax = 240;
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 50;
        this.diameterDefault = 30;
        this.textSize = 12.0f;
        this.textSelectedSize = 14.0f;
        this.context = context;
        init(context, attrs);
    }

    public QHeartSportDetailLineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.xMax = 1440;
        this.xMin = 0;
        this.yMax = 240;
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 50;
        this.diameterDefault = 30;
        this.textSize = 12.0f;
        this.textSelectedSize = 14.0f;
        this.context = context;
        init(context, attrs);
    }

    public QHeartSportDetailLineChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.xMax = 1440;
        this.xMin = 0;
        this.yMax = 240;
        this.labelsList = new ArrayList();
        this.heartList = new ArrayList();
        this.leftRightOffset = 100;
        this.position = new float[]{0.1f, 0.1f, 2.0f};
        this.colors = new int[0];
        this.colorsLine = new int[0];
        this.bottomOffset = 50;
        this.diameterDefault = 30;
        this.textSize = 12.0f;
        this.textSelectedSize = 14.0f;
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.colors = new int[]{ContextCompat.getColor(context, R.color.q_view_sport_detail_1), ContextCompat.getColor(context, R.color.q_view_sport_detail_2), ContextCompat.getColor(context, R.color.q_view_sport_detail_3)};
        this.colorsLine = new int[]{ContextCompat.getColor(context, R.color.q_view_sport_detail_3), ContextCompat.getColor(context, R.color.q_view_sport_detail_1), ContextCompat.getColor(context, R.color.q_view_sport_detail_3)};
        Paint paint = new Paint();
        this.paint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setPathEffect(new CornerPathEffect(10.0f));
        this.paint.setColor(ContextCompat.getColor(context, R.color.q_view_sport_detail_2));
        this.paint.setStrokeWidth(dp2px(context, 0.5f));
        Paint paint2 = new Paint();
        this.paintFill = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.paintFill.setStyle(Paint.Style.FILL);
        this.paintFill.setStrokeJoin(Paint.Join.ROUND);
        this.paintFill.setPathEffect(new CornerPathEffect(10.0f));
        this.paintFill.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.paintY = paint3;
        paint3.setAntiAlias(true);
        this.paintY.setColor(ContextCompat.getColor(context, R.color.q_view_sport_detail_4));
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
        applyTextColor();
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
        filterData(this.heartList);
        initXLabels();
        postInvalidate();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.qc_chart);
            this.colorBall = typedArrayObtainStyledAttributes.getColor(1, ViewCompat.MEASURED_STATE_MASK);
            this.colorLine = typedArrayObtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
            this.textDownColor = typedArrayObtainStyledAttributes.getColor(8, ViewCompat.MEASURED_STATE_MASK);
            this.textDownColorId = typedArrayObtainStyledAttributes.getResourceId(8, 0);
            this.colorValue = typedArrayObtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
            this.colorValueSelected = typedArrayObtainStyledAttributes.getColor(6, -1);
            this.colorBgSelected = typedArrayObtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
            this.lineHeight = (int) dp2px(context, typedArrayObtainStyledAttributes.getInteger(7, 185));
            this.bottomOffset = (int) dp2px(context, typedArrayObtainStyledAttributes.getInteger(0, 30));
            typedArrayObtainStyledAttributes.recycle();
            initPublic(context, attrs);
            applyTextColor();
        }
    }

    private void applyTextColor() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.textDownColorId);
        if (iCheckResourceId != 0) {
            this.textDownPaint.setColor(SkinCompatResources.getColor(this.context, iCheckResourceId));
        }
    }

    public void initPublic(Context context, AttributeSet attrs) {
        this.context = context;
        Paint paint = new Paint();
        this.linePaint = paint;
        paint.setAntiAlias(true);
        this.linePaint.setStyle(Paint.Style.FILL);
        this.linePaint.setColor(this.colorValue);
        this.linePaint.setStrokeWidth(2.0f);
        this.linePaint.setTextSize(this.textSelectedSize);
        Paint paint2 = new Paint();
        this.textDownPaint = paint2;
        paint2.setAntiAlias(true);
        this.textDownPaint.setStyle(Paint.Style.FILL);
        this.textDownPaint.setStrokeWidth(2.0f);
        this.textDownPaint.setTextSize(dp2px(context, 12.0f));
    }

    @Override // android.view.View
    protected void onLayout(boolean changed, int left, int top2, int right, int bottom) {
        super.onLayout(changed, left, top2, right, bottom);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (XTextChange xTextChange : this.labelsList) {
            canvas.drawText(xTextChange.label, xTextChange.x, xTextChange.y, this.textDownPaint);
        }
        int i = this.width;
        this.paintFill.setShader(new LinearGradient(i, 0.0f, i, this.height - (this.bottomOffset * 2), this.colors, this.position, Shader.TileMode.CLAMP));
        canvas.drawPath(this.fillPath, this.paintFill);
        canvas.drawPath(this.path, this.paint);
        canvas.drawPath(this.yLinePath, this.paintY);
        for (int i2 = 0; i2 < this.heartList.size(); i2++) {
            if (this.heartList.get(i2).isBegin()) {
                canvas.drawCircle(r1.x, r1.y, dp2px(this.context, 0.05f), this.paint);
            }
        }
        this.textDownPaint.setTextSize(dp2px(this.context, 10.0f));
        float textWidth = this.leftRightOffset - getTextWidth(this.textDownPaint, "000");
        int i3 = this.lineHeight;
        int i4 = this.bottomOffset;
        canvas.drawText("50", textWidth, ((i3 - i4) - (((this.height - i4) * 50) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        float textWidth2 = this.leftRightOffset - getTextWidth(this.textDownPaint, "0000");
        int i5 = this.lineHeight;
        int i6 = this.bottomOffset;
        canvas.drawText("100", textWidth2, ((i5 - i6) - (((this.height - i6) * 100) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        float textWidth3 = this.leftRightOffset - getTextWidth(this.textDownPaint, "0000");
        int i7 = this.lineHeight;
        int i8 = this.bottomOffset;
        canvas.drawText("150", textWidth3, ((i7 - i8) - (((this.height - i8) * 150) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
        float textWidth4 = this.leftRightOffset - getTextWidth(this.textDownPaint, "0000");
        int i9 = this.lineHeight;
        int i10 = this.bottomOffset;
        canvas.drawText("200", textWidth4, ((i9 - i10) - (((this.height - i10) * 200) / this.yMax)) + (getTextHeight(this.textDownPaint, "00") / 2.0f), this.textDownPaint);
    }

    private void initXLabels() {
        if (this.startTime <= 0 || this.endTime <= 0) {
            return;
        }
        this.labelsList.clear();
        int i = (this.width - (this.leftRightOffset * 2)) / 8;
        XTextChange xTextChange = new XTextChange();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        xTextChange.setLabel(simpleDateFormat.format(new Date(this.startTime * 1000)));
        xTextChange.setX(this.leftRightOffset - (getTextWidth(this.textDownPaint, "00:000") / 2.0f));
        xTextChange.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange.setMin(1);
        xTextChange.setMax(this.leftRightOffset + i);
        this.labelsList.add(xTextChange);
        XTextChange xTextChange2 = new XTextChange();
        xTextChange2.setLabel(simpleDateFormat.format(new Date(this.endTime * 1000)));
        xTextChange2.setX((this.width - (getTextWidth(this.textDownPaint, "00:000") / 2.0f)) - this.leftRightOffset);
        xTextChange2.setY(this.lineHeight - (this.textSize / 2.0f));
        xTextChange2.setMin(this.leftRightOffset + i);
        xTextChange2.setMax(this.leftRightOffset + (i * 3));
        this.labelsList.add(xTextChange2);
    }

    public void setData(long stat, long end, List<HeartDataBean> list) {
        this.heartList = list;
        this.startTime = stat;
        this.endTime = end;
        initXLabels();
        if (list.size() > 0) {
            this.xMin = list.get(0).getMin();
            int min = list.get(list.size() - 1).getMin();
            this.xMax = min;
            if (this.xMin == min) {
                this.xMax = 2;
                this.xMin = 1;
            }
            this.path.reset();
            this.fillPath.reset();
            if (this.width > 0) {
                filterData(list);
            }
        }
        postInvalidate();
    }

    private void filterData(List<HeartDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            HeartDataBean heartDataBean = list.get(i);
            int min = heartDataBean.getMin();
            int i2 = this.xMin;
            int i3 = this.width;
            int i4 = this.leftRightOffset;
            heartDataBean.x = (((min - i2) * (i3 - (i4 * 2))) / (this.xMax - i2)) + i4;
            heartDataBean.y = (this.lineHeight - this.bottomOffset) - ((heartDataBean.getValue() * (this.height - this.bottomOffset)) / this.yMax);
            if (i <= 0) {
                heartDataBean.setBegin(true);
            } else if (list.get(i - 1).value == 0 && list.get(i).value > 0) {
                heartDataBean.begin = true;
            }
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.heartList.size(); i6++) {
            HeartDataBean heartDataBean2 = this.heartList.get(0);
            HeartDataBean heartDataBean3 = this.heartList.get(i6);
            if (heartDataBean3.begin) {
                this.firstBean = heartDataBean3;
                this.path.moveTo(heartDataBean3.x, heartDataBean3.y);
                this.fillPath.moveTo(heartDataBean3.x, heartDataBean3.y);
                this.path.lineTo(heartDataBean3.x, heartDataBean3.y);
                this.fillPath.lineTo(heartDataBean3.x, heartDataBean3.y);
            } else {
                if (heartDataBean3.value > 0) {
                    this.path.lineTo(heartDataBean3.x, heartDataBean3.y);
                    this.fillPath.lineTo(heartDataBean3.x, heartDataBean3.y);
                    i5 = heartDataBean3.x;
                }
                if ((i6 > 0 && i6 < this.heartList.size() - 1 && list.get(i6 - 1).value > 0 && list.get(i6).value == 0) || i6 == this.heartList.size() - 1) {
                    int i7 = i6 - 1;
                    HeartDataBean heartDataBean4 = (list.get(i7).value <= 0 || list.get(i6).value != 0) ? heartDataBean3 : list.get(i7);
                    if (heartDataBean4.value == 0 && this.firstBean.getX() != heartDataBean2.getX()) {
                        heartDataBean4 = this.firstBean;
                        this.fillPath.lineTo(heartDataBean4.x + 5, heartDataBean4.y);
                    }
                    if (heartDataBean3.value > 0) {
                        this.fillPath.lineTo(heartDataBean4.x, this.height + this.bottomOffset);
                    } else if (heartDataBean4.x > this.firstBean.x) {
                        this.fillPath.lineTo(i5, this.height + this.bottomOffset);
                    }
                    this.fillPath.lineTo(this.firstBean.x, this.height + this.bottomOffset);
                    this.fillPath.lineTo(this.firstBean.x, heartDataBean4.y);
                    this.fillPath.close();
                }
            }
        }
        Path path = this.yLinePath;
        float f = this.leftRightOffset;
        int i8 = this.lineHeight;
        int i9 = this.bottomOffset;
        path.moveTo(f, (i8 - i9) - (((this.height - i9) * 50) / this.yMax));
        Path path2 = this.yLinePath;
        float f2 = this.width - this.leftRightOffset;
        int i10 = this.lineHeight;
        int i11 = this.bottomOffset;
        path2.lineTo(f2, (i10 - i11) - (((this.height - i11) * 50) / this.yMax));
        Path path3 = this.yLinePath;
        float f3 = this.leftRightOffset;
        int i12 = this.lineHeight;
        int i13 = this.bottomOffset;
        path3.moveTo(f3, (i12 - i13) - (((this.height - i13) * 100) / this.yMax));
        Path path4 = this.yLinePath;
        float f4 = this.width - this.leftRightOffset;
        int i14 = this.lineHeight;
        int i15 = this.bottomOffset;
        path4.lineTo(f4, (i14 - i15) - (((this.height - i15) * 100) / this.yMax));
        Path path5 = this.yLinePath;
        float f5 = this.leftRightOffset;
        int i16 = this.lineHeight;
        int i17 = this.bottomOffset;
        path5.moveTo(f5, (i16 - i17) - (((this.height - i17) * 150) / this.yMax));
        Path path6 = this.yLinePath;
        float f6 = this.width - this.leftRightOffset;
        int i18 = this.lineHeight;
        int i19 = this.bottomOffset;
        path6.lineTo(f6, (i18 - i19) - (((this.height - i19) * 150) / this.yMax));
        Path path7 = this.yLinePath;
        float f7 = this.leftRightOffset;
        int i20 = this.lineHeight;
        int i21 = this.bottomOffset;
        path7.moveTo(f7, (i20 - i21) - (((this.height - i21) * 200) / this.yMax));
        Path path8 = this.yLinePath;
        float f8 = this.width - this.leftRightOffset;
        int i22 = this.lineHeight;
        int i23 = this.bottomOffset;
        path8.lineTo(f8, (i22 - i23) - (((this.height - i23) * 200) / this.yMax));
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
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

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        applyTextColor();
    }

    public static class HeartDataBean {
        private boolean begin;
        private int min;
        private int value;
        private int x;
        private int y;

        public HeartDataBean(int min, int value, boolean begin) {
            this.min = min;
            this.value = value;
            this.begin = begin;
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
    }

    public class XTextChange {
        public String label;
        public int max;
        public int min;
        public float x;
        public float y;

        public XTextChange() {
        }

        public int getMin() {
            return this.min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return this.max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

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
    }
}
