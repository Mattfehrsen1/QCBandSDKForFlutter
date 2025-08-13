package com.device.watch.com.device.watch.itrflprgee;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public class Wselqqatnse extends ShapeDrawable {
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

    public Wselqqatnse() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(385.0f, 635.725f);
        path.lineTo(135.0f, 605.175f);
        path.lineTo(285.0f, 175.775f);
        path.lineTo(565.0f, 695.05f);
        path.lineTo(55.0f, 605.195f);
        path.lineTo(845.0f, 295.725f);
        path.lineTo(225.0f, 635.815f);
        path.lineTo(935.0f, 635.305f);
        path.lineTo(555.0f, 645.615f);
        path.lineTo(535.0f, 25.215f);
        path.lineTo(675.0f, 795.375f);
        path.lineTo(565.0f, 955.945f);
        path.lineTo(135.0f, 765.485f);
        path.lineTo(15.0f, 855.755f);
        path.lineTo(295.0f, 385.965f);
        path.lineTo(385.0f, 895.435f);
        path.lineTo(865.0f, 15.405f);
        path.lineTo(485.0f, 565.115f);
        path.lineTo(5.0f, 805.195f);
        path.lineTo(845.0f, 465.345f);
        path.lineTo(205.0f, 605.275f);
        path.lineTo(755.0f, 695.225f);
        path.lineTo(145.0f, 855.205f);
        path.lineTo(975.0f, 5.385f);
        path.lineTo(285.0f, 715.915f);
        path.lineTo(195.0f, 175.675f);
        path.lineTo(975.0f, 605.345f);
        path.lineTo(695.0f, 655.835f);
        path.lineTo(515.0f, 455.805f);
        path.lineTo(495.0f, 865.525f);
        path.lineTo(95.0f, 45.545f);
        path.lineTo(375.0f, 5.635f);
        path.lineTo(15.0f, 765.895f);
        path.lineTo(235.0f, 485.945f);
        path.lineTo(455.0f, 525.415f);
        path.lineTo(165.0f, 505.365f);
        path.lineTo(295.0f, 855.35f);
        path.lineTo(645.0f, 15.265f);
        path.lineTo(475.0f, 25.615f);
        path.lineTo(535.0f, 35.825f);
        path.lineTo(375.0f, 845.845f);
        path.lineTo(335.0f, 205.405f);
        path.lineTo(45.0f, 475.955f);
        path.lineTo(665.0f, 45.125f);
        path.lineTo(135.0f, 855.475f);
        path.lineTo(605.0f, 555.665f);
        path.lineTo(585.0f, 855.725f);
        path.lineTo(625.0f, 305.505f);
        path.lineTo(385.0f, 985.925f);
        path.lineTo(15.0f, 435.255f);
        path.lineTo(10165.0f, 335.845f);
        path.lineTo(435.0f, 955.275f);
        path.lineTo(965.0f, 335.605f);
        path.lineTo(515.0f, 505.15f);
        path.lineTo(445.0f, 845.645f);
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
