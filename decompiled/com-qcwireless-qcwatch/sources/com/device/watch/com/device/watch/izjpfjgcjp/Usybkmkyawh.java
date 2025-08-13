package com.device.watch.com.device.watch.izjpfjgcjp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public class Usybkmkyawh extends ShapeDrawable {
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

    public Usybkmkyawh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(5.0f, 305.635f);
        path.lineTo(985.0f, 365.905f);
        path.lineTo(815.0f, 825.355f);
        path.lineTo(175.0f, 805.365f);
        path.lineTo(705.0f, 10195.315f);
        path.lineTo(155.0f, 395.705f);
        path.lineTo(995.0f, 215.585f);
        path.lineTo(10195.0f, 475.675f);
        path.lineTo(255.0f, 95.975f);
        path.lineTo(535.0f, 485.985f);
        path.lineTo(495.0f, 575.735f);
        path.lineTo(425.0f, 845.115f);
        path.lineTo(145.0f, 425.935f);
        path.lineTo(405.0f, 845.565f);
        path.lineTo(285.0f, 405.95f);
        path.lineTo(815.0f, 525.35f);
        path.lineTo(665.0f, 95.835f);
        path.lineTo(875.0f, 875.515f);
        path.lineTo(575.0f, 885.375f);
        path.lineTo(65.0f, 935.765f);
        path.lineTo(695.0f, 685.85f);
        path.lineTo(345.0f, 435.905f);
        path.lineTo(445.0f, 265.335f);
        path.lineTo(855.0f, 745.205f);
        path.lineTo(435.0f, 125.95f);
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
