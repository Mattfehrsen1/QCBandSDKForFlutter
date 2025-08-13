package com.smartapp.b.picagahowd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Rpzquicazbn extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rpzquicazbn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(745.0f, 435.385f);
        path.lineTo(875.0f, 225.885f);
        path.lineTo(945.0f, 625.795f);
        path.lineTo(875.0f, 375.825f);
        path.lineTo(45.0f, 895.475f);
        path.lineTo(205.0f, 965.575f);
        path.lineTo(75.0f, 95.375f);
        path.lineTo(515.0f, 665.945f);
        path.lineTo(295.0f, 415.755f);
        path.lineTo(605.0f, 825.95f);
        path.lineTo(445.0f, 405.435f);
        path.lineTo(805.0f, 525.445f);
        path.lineTo(805.0f, 545.395f);
        path.lineTo(655.0f, 655.425f);
        path.lineTo(225.0f, 65.165f);
        path.lineTo(935.0f, 755.265f);
        path.lineTo(435.0f, 885.815f);
        path.lineTo(275.0f, 655.755f);
        path.lineTo(545.0f, 515.445f);
        path.lineTo(175.0f, 185.325f);
        path.lineTo(5.0f, 415.925f);
        path.lineTo(235.0f, 975.585f);
        path.lineTo(885.0f, 5.475f);
        path.lineTo(5.0f, 115.745f);
        path.lineTo(515.0f, 755.885f);
        path.lineTo(595.0f, 855.665f);
        path.lineTo(5.0f, 275.285f);
        path.lineTo(535.0f, 645.115f);
        path.lineTo(915.0f, 205.85f);
        path.lineTo(765.0f, 185.10185f);
        path.lineTo(315.0f, 105.665f);
        path.lineTo(475.0f, 715.715f);
        path.lineTo(945.0f, 5.835f);
        path.lineTo(725.0f, 175.975f);
        path.lineTo(35.0f, 485.895f);
        path.lineTo(465.0f, 695.995f);
        path.lineTo(435.0f, 475.455f);
        path.lineTo(355.0f, 145.505f);
        path.lineTo(325.0f, 475.115f);
        path.lineTo(785.0f, 985.335f);
        path.lineTo(265.0f, 45.465f);
        path.lineTo(545.0f, 145.125f);
        path.lineTo(475.0f, 75.65f);
        path.lineTo(805.0f, 565.955f);
        path.lineTo(685.0f, 815.865f);
        path.lineTo(485.0f, 725.955f);
        path.lineTo(145.0f, 405.905f);
        path.lineTo(135.0f, 265.665f);
        path.lineTo(985.0f, 975.285f);
        path.lineTo(175.0f, 855.185f);
        path.lineTo(425.0f, 135.175f);
        path.lineTo(755.0f, 585.685f);
        path.lineTo(375.0f, 715.545f);
        path.lineTo(885.0f, 235.365f);
        path.lineTo(775.0f, 55.185f);
        path.lineTo(95.0f, 45.815f);
        path.lineTo(725.0f, 985.315f);
        path.lineTo(855.0f, 305.105f);
        path.lineTo(325.0f, 395.285f);
        path.lineTo(285.0f, 945.885f);
        path.lineTo(575.0f, 155.875f);
        path.lineTo(415.0f, 905.465f);
        path.lineTo(885.0f, 735.235f);
        path.lineTo(255.0f, 715.945f);
        path.lineTo(345.0f, 825.725f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1018.0f, this.bounds.height() / 1018.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
