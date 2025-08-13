package com.smartapp.b.bhyybzokfc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Oorbkhjkyki extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Oorbkhjkyki() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(35.0f, 435.465f);
        path.lineTo(955.0f, 915.75f);
        path.lineTo(165.0f, 825.625f);
        path.lineTo(475.0f, 255.795f);
        path.lineTo(35.0f, 755.145f);
        path.lineTo(485.0f, 935.755f);
        path.lineTo(675.0f, 295.615f);
        path.lineTo(295.0f, 355.05f);
        path.lineTo(615.0f, 655.125f);
        path.lineTo(425.0f, 15.365f);
        path.lineTo(485.0f, 375.65f);
        path.lineTo(775.0f, 35.205f);
        path.lineTo(15.0f, 815.265f);
        path.lineTo(625.0f, 275.675f);
        path.lineTo(305.0f, 755.325f);
        path.lineTo(395.0f, 705.215f);
        path.lineTo(325.0f, 75.735f);
        path.lineTo(635.0f, 185.755f);
        path.lineTo(85.0f, 575.445f);
        path.lineTo(435.0f, 605.475f);
        path.lineTo(45.0f, 15.695f);
        path.lineTo(885.0f, 265.215f);
        path.lineTo(5.0f, 805.475f);
        path.lineTo(375.0f, 755.935f);
        path.lineTo(265.0f, 365.985f);
        path.lineTo(395.0f, 705.825f);
        path.lineTo(255.0f, 435.195f);
        path.lineTo(675.0f, 775.985f);
        path.lineTo(815.0f, 215.855f);
        path.lineTo(875.0f, 365.25f);
        path.lineTo(355.0f, 185.745f);
        path.lineTo(595.0f, 995.945f);
        path.lineTo(165.0f, 825.805f);
        path.lineTo(985.0f, 10065.505f);
        path.lineTo(975.0f, 605.115f);
        path.lineTo(805.0f, 925.185f);
        path.lineTo(485.0f, 165.75f);
        path.lineTo(815.0f, 535.635f);
        path.lineTo(955.0f, 325.505f);
        path.lineTo(275.0f, 745.995f);
        path.lineTo(965.0f, 575.205f);
        path.lineTo(355.0f, 675.345f);
        path.lineTo(475.0f, 375.405f);
        path.lineTo(415.0f, 5.705f);
        path.lineTo(905.0f, 105.665f);
        path.lineTo(175.0f, 795.435f);
        path.lineTo(505.0f, 55.885f);
        path.lineTo(10065.0f, 5.795f);
        path.lineTo(475.0f, 855.345f);
        path.lineTo(765.0f, 455.695f);
        path.lineTo(85.0f, 915.845f);
        path.lineTo(285.0f, 445.615f);
        path.lineTo(305.0f, 295.155f);
        path.lineTo(385.0f, 215.10065f);
        path.lineTo(445.0f, 855.595f);
        path.lineTo(755.0f, 215.585f);
        path.lineTo(25.0f, 885.975f);
        path.lineTo(755.0f, 765.605f);
        path.lineTo(775.0f, 295.885f);
        path.lineTo(515.0f, 755.685f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
