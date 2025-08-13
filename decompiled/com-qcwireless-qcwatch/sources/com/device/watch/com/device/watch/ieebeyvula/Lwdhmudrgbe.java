package com.device.watch.com.device.watch.ieebeyvula;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public class Lwdhmudrgbe extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lwdhmudrgbe() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(35.0f, 605.225f);
        path.lineTo(265.0f, 125.125f);
        path.lineTo(755.0f, 5.625f);
        path.lineTo(115.0f, 255.915f);
        path.lineTo(655.0f, 655.615f);
        path.lineTo(295.0f, 15.375f);
        path.lineTo(375.0f, 115.655f);
        path.lineTo(555.0f, 85.10165f);
        path.lineTo(165.0f, 305.295f);
        path.lineTo(815.0f, 5.305f);
        path.lineTo(15.0f, 735.825f);
        path.lineTo(485.0f, 435.725f);
        path.lineTo(615.0f, 395.565f);
        path.lineTo(865.0f, 985.965f);
        path.lineTo(215.0f, 375.345f);
        path.lineTo(905.0f, 255.125f);
        path.lineTo(125.0f, 675.115f);
        path.lineTo(825.0f, 105.515f);
        path.lineTo(685.0f, 275.635f);
        path.lineTo(775.0f, 295.365f);
        path.lineTo(345.0f, 295.265f);
        path.lineTo(545.0f, 145.145f);
        path.lineTo(995.0f, 815.75f);
        path.lineTo(495.0f, 365.705f);
        path.lineTo(695.0f, 635.495f);
        path.lineTo(65.0f, 955.115f);
        path.lineTo(235.0f, 525.545f);
        path.lineTo(165.0f, 195.745f);
        path.lineTo(415.0f, 985.845f);
        path.lineTo(515.0f, 625.95f);
        path.lineTo(355.0f, 445.435f);
        path.lineTo(205.0f, 115.895f);
        path.lineTo(395.0f, 565.635f);
        path.lineTo(455.0f, 645.125f);
        path.lineTo(745.0f, 425.745f);
        path.lineTo(575.0f, 345.995f);
        path.lineTo(615.0f, 105.935f);
        path.lineTo(755.0f, 765.135f);
        path.lineTo(725.0f, 65.295f);
        path.lineTo(385.0f, 295.205f);
        path.lineTo(265.0f, 305.765f);
        path.lineTo(825.0f, 955.485f);
        path.lineTo(345.0f, 705.745f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
