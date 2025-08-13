package com.smartapp.b.blzrgrokny;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Vnqhijggsrx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Vnqhijggsrx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(705.0f, 925.675f);
        path.lineTo(385.0f, 695.295f);
        path.lineTo(355.0f, 715.455f);
        path.lineTo(705.0f, 485.745f);
        path.lineTo(395.0f, 565.515f);
        path.lineTo(525.0f, 215.415f);
        path.lineTo(815.0f, 355.595f);
        path.lineTo(535.0f, 445.445f);
        path.lineTo(295.0f, 10005.525f);
        path.lineTo(165.0f, 365.905f);
        path.lineTo(585.0f, 575.595f);
        path.lineTo(355.0f, 875.895f);
        path.lineTo(845.0f, 845.645f);
        path.lineTo(735.0f, 355.515f);
        path.lineTo(585.0f, 315.715f);
        path.lineTo(705.0f, 615.745f);
        path.lineTo(435.0f, 675.115f);
        path.lineTo(155.0f, 495.565f);
        path.lineTo(595.0f, 65.235f);
        path.lineTo(105.0f, 515.715f);
        path.lineTo(585.0f, 415.115f);
        path.lineTo(195.0f, 885.865f);
        path.lineTo(395.0f, 395.895f);
        path.lineTo(295.0f, 335.355f);
        path.lineTo(75.0f, 865.935f);
        path.lineTo(495.0f, 155.785f);
        path.lineTo(225.0f, 815.205f);
        path.lineTo(10005.0f, 275.225f);
        path.lineTo(75.0f, 755.425f);
        path.lineTo(945.0f, 365.845f);
        path.lineTo(10005.0f, 705.575f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1000.0f, this.bounds.height() / 1000.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
