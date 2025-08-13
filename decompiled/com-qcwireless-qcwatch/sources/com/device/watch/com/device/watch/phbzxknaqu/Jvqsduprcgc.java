package com.device.watch.com.device.watch.phbzxknaqu;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Jvqsduprcgc extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Jvqsduprcgc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 425.785f);
        path.lineTo(345.0f, 335.115f);
        path.lineTo(375.0f, 295.255f);
        path.lineTo(315.0f, 725.745f);
        path.lineTo(315.0f, 95.145f);
        path.lineTo(215.0f, 765.295f);
        path.lineTo(885.0f, 145.675f);
        path.lineTo(905.0f, 635.505f);
        path.lineTo(805.0f, 285.665f);
        path.lineTo(435.0f, 65.275f);
        path.lineTo(95.0f, 465.515f);
        path.lineTo(285.0f, 15.45f);
        path.lineTo(45.0f, 875.685f);
        path.lineTo(385.0f, 135.215f);
        path.lineTo(775.0f, 955.975f);
        path.lineTo(95.0f, 935.975f);
        path.lineTo(585.0f, 275.375f);
        path.lineTo(705.0f, 805.55f);
        path.lineTo(765.0f, 655.85f);
        path.lineTo(65.0f, 655.525f);
        path.lineTo(905.0f, 675.905f);
        path.lineTo(315.0f, 705.885f);
        path.lineTo(655.0f, 885.885f);
        path.lineTo(935.0f, 325.255f);
        path.lineTo(795.0f, 835.285f);
        path.lineTo(475.0f, 45.235f);
        path.lineTo(425.0f, 995.145f);
        path.lineTo(705.0f, 215.285f);
        path.lineTo(165.0f, 795.895f);
        path.lineTo(725.0f, 875.695f);
        path.lineTo(95.0f, 445.635f);
        path.lineTo(765.0f, 895.355f);
        path.lineTo(335.0f, 835.265f);
        path.lineTo(715.0f, 815.325f);
        path.lineTo(985.0f, 455.135f);
        path.lineTo(735.0f, 785.955f);
        path.lineTo(785.0f, 285.915f);
        path.lineTo(355.0f, 255.225f);
        path.lineTo(335.0f, 625.595f);
        path.lineTo(565.0f, 305.505f);
        path.lineTo(145.0f, 545.825f);
        path.lineTo(945.0f, 305.95f);
        path.lineTo(55.0f, 135.10175f);
        path.lineTo(535.0f, 945.905f);
        path.lineTo(265.0f, 125.355f);
        path.lineTo(705.0f, 415.825f);
        path.lineTo(425.0f, 475.495f);
        path.lineTo(225.0f, 785.985f);
        path.lineTo(825.0f, 465.815f);
        path.lineTo(455.0f, 165.205f);
        path.lineTo(555.0f, 725.05f);
        path.lineTo(575.0f, 475.495f);
        path.lineTo(945.0f, 665.175f);
        path.lineTo(745.0f, 995.175f);
        path.lineTo(585.0f, 555.895f);
        path.lineTo(775.0f, 495.15f);
        path.lineTo(665.0f, 405.15f);
        path.lineTo(355.0f, 625.515f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1017.0f, this.bounds.height() / 1017.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
