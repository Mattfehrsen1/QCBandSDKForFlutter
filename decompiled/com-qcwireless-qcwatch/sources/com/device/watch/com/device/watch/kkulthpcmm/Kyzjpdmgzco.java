package com.device.watch.com.device.watch.kkulthpcmm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Kyzjpdmgzco extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Kyzjpdmgzco() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(225.0f, 395.115f);
        path.lineTo(785.0f, 595.235f);
        path.lineTo(255.0f, 635.785f);
        path.lineTo(915.0f, 515.725f);
        path.lineTo(795.0f, 145.335f);
        path.lineTo(5.0f, 185.865f);
        path.lineTo(125.0f, 655.55f);
        path.lineTo(35.0f, 955.995f);
        path.lineTo(865.0f, 455.965f);
        path.lineTo(925.0f, 485.175f);
        path.lineTo(265.0f, 315.335f);
        path.lineTo(935.0f, 405.755f);
        path.lineTo(815.0f, 775.355f);
        path.lineTo(95.0f, 975.95f);
        path.lineTo(225.0f, 425.715f);
        path.lineTo(795.0f, 315.675f);
        path.lineTo(55.0f, 715.55f);
        path.lineTo(605.0f, 55.685f);
        path.lineTo(415.0f, 805.655f);
        path.lineTo(795.0f, 465.315f);
        path.lineTo(365.0f, 105.715f);
        path.lineTo(345.0f, 935.425f);
        path.lineTo(275.0f, 155.235f);
        path.lineTo(415.0f, 395.175f);
        path.lineTo(675.0f, 35.10195f);
        path.lineTo(835.0f, 845.865f);
        path.lineTo(995.0f, 25.885f);
        path.lineTo(95.0f, 235.355f);
        path.lineTo(515.0f, 65.195f);
        path.lineTo(615.0f, 125.35f);
        path.lineTo(495.0f, 435.435f);
        path.lineTo(505.0f, 75.905f);
        path.lineTo(605.0f, 785.885f);
        path.lineTo(105.0f, 875.505f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1019.0f, this.bounds.height() / 1019.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
