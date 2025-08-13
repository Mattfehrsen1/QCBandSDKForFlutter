package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class QHomeMenstruationView extends View {
    private Context context;
    private int height;
    private int moveX;
    private Paint paint;
    private int type;
    private int width;

    public QHomeMenstruationView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public QHomeMenstruationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public QHomeMenstruationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public QHomeMenstruationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();
    }

    private void initView() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(dp2px(this.context, 10.0f));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setType(int r4) {
        /*
            r3 = this;
            int r0 = r3.width
            int r0 = r0 + (-40)
            int r0 = r0 / 30
            r3.type = r4
            r1 = 2
            if (r4 == 0) goto L29
            r2 = 1
            if (r4 == r2) goto L20
            if (r4 == r1) goto L17
            r2 = 3
            if (r4 == r2) goto L17
            r2 = 4
            if (r4 == r2) goto L29
            goto L31
        L17:
            int r4 = r0 * 4
            int r0 = r0 / r1
            int r4 = r4 + r0
            int r4 = r4 + (-20)
            r3.moveX = r4
            goto L31
        L20:
            int r4 = r0 * 11
            int r0 = r0 / r1
            int r4 = r4 + r0
            int r4 = r4 + (-20)
            r3.moveX = r4
            goto L31
        L29:
            int r4 = r0 * 23
            int r0 = r0 / r1
            int r4 = r4 + r0
            int r4 = r4 + (-20)
            r3.moveX = r4
        L31:
            r3.postInvalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.view.QHomeMenstruationView.setType(int):void");
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = (this.width - 40) / 30;
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setColor(SkinCompatResources.getColor(this.context, R.color.menstruation_show_2));
        float f = i * 8;
        canvas.drawLine(20.0f, 20.0f, f, 20.0f, this.paint);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setColor(SkinCompatResources.getColor(this.context, R.color.menstruation_show_4));
        float f2 = i * 15;
        canvas.drawLine(f2, 20.0f, i * 30, 20.0f, this.paint);
        this.paint.setStrokeCap(Paint.Cap.SQUARE);
        this.paint.setColor(SkinCompatResources.getColor(this.context, R.color.menstruation_show_3));
        canvas.drawLine(f, 20.0f, f2, 20.0f, this.paint);
        if (this.moveX > 0) {
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.menstruation_move);
            int i2 = this.moveX;
            canvas.drawBitmap(bitmapDecodeResource, (Rect) null, new Rect(i2, 20, bitmapDecodeResource.getWidth() + i2, bitmapDecodeResource.getHeight() + 20), this.paint);
            return;
        }
        setType(this.type);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.width = getMySize(200, widthMeasureSpec);
        if (getLayoutParams().height == -2) {
            this.height = (int) dp2px(this.context, 20.0f);
        } else {
            this.height = getMySize(200, heightMeasureSpec);
        }
        setMeasuredDimension(this.width, this.height);
        postInvalidate();
    }

    public float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public int getMySize(int defaultSize, int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(measureSpec) : defaultSize;
    }
}
