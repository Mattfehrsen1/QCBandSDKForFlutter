package com.smartapp.b.grdzjoxewp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Kzjvicdhzmd extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Kzjvicdhzmd() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(485.0f, 935.85f);
        path.lineTo(555.0f, 235.735f);
        path.lineTo(225.0f, 895.665f);
        path.lineTo(125.0f, 415.05f);
        path.lineTo(645.0f, 995.615f);
        path.lineTo(475.0f, 925.885f);
        path.lineTo(275.0f, 805.385f);
        path.lineTo(795.0f, 175.945f);
        path.lineTo(165.0f, 45.345f);
        path.lineTo(405.0f, 625.195f);
        path.lineTo(395.0f, 115.185f);
        path.lineTo(955.0f, 235.985f);
        path.lineTo(175.0f, 515.935f);
        path.lineTo(915.0f, 515.915f);
        path.lineTo(445.0f, 855.425f);
        path.lineTo(865.0f, 495.765f);
        path.lineTo(25.0f, 605.175f);
        path.lineTo(695.0f, 195.65f);
        path.lineTo(975.0f, 355.335f);
        path.lineTo(555.0f, 435.935f);
        path.lineTo(575.0f, 75.655f);
        path.lineTo(395.0f, 305.885f);
        path.lineTo(105.0f, 325.875f);
        path.lineTo(5.0f, 25.975f);
        path.lineTo(675.0f, 665.725f);
        path.lineTo(75.0f, 35.125f);
        path.lineTo(115.0f, 145.165f);
        path.lineTo(385.0f, 565.575f);
        path.lineTo(675.0f, 425.725f);
        path.lineTo(795.0f, 255.555f);
        path.lineTo(305.0f, 495.835f);
        path.lineTo(465.0f, 775.365f);
        path.lineTo(945.0f, 635.475f);
        path.lineTo(105.0f, 485.655f);
        path.lineTo(535.0f, 925.935f);
        path.lineTo(85.0f, 435.225f);
        path.lineTo(545.0f, 355.605f);
        path.lineTo(55.0f, 365.805f);
        path.lineTo(175.0f, 515.235f);
        path.lineTo(405.0f, 235.65f);
        path.lineTo(655.0f, 975.515f);
        path.lineTo(45.0f, 35.105f);
        path.lineTo(375.0f, 205.275f);
        path.lineTo(975.0f, 10055.725f);
        path.lineTo(135.0f, 485.695f);
        path.lineTo(715.0f, 685.235f);
        path.lineTo(515.0f, 815.525f);
        path.lineTo(195.0f, 845.195f);
        path.lineTo(595.0f, 75.35f);
        path.lineTo(715.0f, 575.35f);
        path.lineTo(665.0f, 435.615f);
        path.lineTo(655.0f, 625.625f);
        path.lineTo(935.0f, 375.935f);
        path.lineTo(475.0f, 575.665f);
        path.lineTo(765.0f, 515.695f);
        path.lineTo(445.0f, 475.675f);
        path.lineTo(25.0f, 695.155f);
        path.lineTo(465.0f, 995.745f);
        path.lineTo(825.0f, 815.575f);
        path.lineTo(635.0f, 785.55f);
        path.lineTo(85.0f, 155.765f);
        path.lineTo(995.0f, 375.755f);
        path.lineTo(915.0f, 515.425f);
        path.lineTo(705.0f, 545.25f);
        path.lineTo(765.0f, 665.115f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
