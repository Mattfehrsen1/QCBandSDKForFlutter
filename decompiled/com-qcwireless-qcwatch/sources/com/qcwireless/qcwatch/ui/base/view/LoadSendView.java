package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class LoadSendView extends View implements SkinCompatSupportable {
    private static final String TAG = "LoadSendView";
    private Context context;
    private RectF mCrop;
    private float mDegree;
    private float mDesti;
    private Paint mPaint;
    private RectF mRect;
    private float mStart;

    public LoadSendView(Context context) {
        super(context);
        this.mStart = 15.0f;
        this.context = context;
        init(context);
    }

    public LoadSendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStart = 15.0f;
        this.context = context;
        init(context);
    }

    public LoadSendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mStart = 15.0f;
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        this.mDesti = context.getResources().getDisplayMetrics().density;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SkinCompatResources.getColor(context, R.color.watch_face_send_progressbar));
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged.. width: " + getWidth());
        this.mRect = new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
        float f = this.mStart;
        float f2 = this.mDesti;
        this.mCrop = new RectF(f * f2, f * f2, getWidth() - (this.mStart * this.mDesti), getHeight() - (this.mStart * this.mDesti));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipRect(this.mCrop);
        RectF rectF = this.mRect;
        float f = this.mDegree;
        canvas.drawArc(rectF, f - 90.0f, 360.0f - f, true, this.mPaint);
        canvas.restore();
    }

    public void updateUI(float degree) {
        this.mDegree = degree;
        postInvalidate();
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        this.mPaint.setColor(SkinCompatResources.getColor(this.context, R.color.watch_face_send_progressbar));
    }
}
