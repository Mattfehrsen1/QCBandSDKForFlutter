package com.smartapp.b.evvwvegpbt;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Wtxvurinstt extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Wtxvurinstt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(645.0f, 435.185f);
        path.lineTo(395.0f, 675.625f);
        path.lineTo(15.0f, 955.805f);
        path.lineTo(455.0f, 435.785f);
        path.lineTo(255.0f, 10075.655f);
        path.lineTo(775.0f, 145.145f);
        path.lineTo(575.0f, 635.55f);
        path.lineTo(65.0f, 625.85f);
        path.lineTo(165.0f, 655.615f);
        path.lineTo(335.0f, 855.675f);
        path.lineTo(45.0f, 295.965f);
        path.lineTo(965.0f, 655.445f);
        path.lineTo(855.0f, 735.125f);
        path.lineTo(795.0f, 975.995f);
        path.lineTo(105.0f, 815.575f);
        path.lineTo(515.0f, 105.665f);
        path.lineTo(655.0f, 575.25f);
        path.lineTo(615.0f, 5.685f);
        path.lineTo(875.0f, 155.625f);
        path.lineTo(495.0f, 585.875f);
        path.lineTo(885.0f, 925.955f);
        path.lineTo(765.0f, 715.905f);
        path.lineTo(925.0f, 805.755f);
        path.lineTo(605.0f, 5.955f);
        path.lineTo(275.0f, 965.145f);
        path.lineTo(715.0f, 385.45f);
        path.lineTo(85.0f, 45.585f);
        path.lineTo(965.0f, 295.115f);
        path.lineTo(215.0f, 175.395f);
        path.lineTo(655.0f, 695.85f);
        path.lineTo(755.0f, 975.215f);
        path.lineTo(845.0f, 625.635f);
        path.lineTo(225.0f, 705.10077f);
        path.lineTo(215.0f, 685.235f);
        path.lineTo(10075.0f, 355.335f);
        path.lineTo(325.0f, 645.05f);
        path.lineTo(885.0f, 135.495f);
        path.lineTo(195.0f, 885.745f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
