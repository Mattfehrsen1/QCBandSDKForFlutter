package com.device.watch.com.device.watch.tkmtkwgmvf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Kfwkwrspphh extends ShapeDrawable {
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

    public Kfwkwrspphh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(675.0f, 25.375f);
        path.lineTo(465.0f, 805.725f);
        path.lineTo(10165.0f, 655.715f);
        path.lineTo(275.0f, 975.195f);
        path.lineTo(785.0f, 835.15f);
        path.lineTo(745.0f, 735.665f);
        path.lineTo(665.0f, 855.745f);
        path.lineTo(755.0f, 745.335f);
        path.lineTo(195.0f, 195.705f);
        path.lineTo(75.0f, 675.435f);
        path.lineTo(655.0f, 205.885f);
        path.lineTo(985.0f, 855.865f);
        path.lineTo(635.0f, 855.875f);
        path.lineTo(355.0f, 475.65f);
        path.lineTo(935.0f, 515.35f);
        path.lineTo(975.0f, 985.355f);
        path.lineTo(305.0f, 615.435f);
        path.lineTo(535.0f, 725.635f);
        path.lineTo(735.0f, 485.915f);
        path.lineTo(605.0f, 685.225f);
        path.lineTo(775.0f, 785.735f);
        path.lineTo(135.0f, 375.385f);
        path.lineTo(685.0f, 35.985f);
        path.lineTo(255.0f, 55.645f);
        path.lineTo(795.0f, 55.985f);
        path.lineTo(385.0f, 185.975f);
        path.lineTo(275.0f, 975.365f);
        path.lineTo(665.0f, 135.535f);
        path.lineTo(895.0f, 385.615f);
        path.lineTo(895.0f, 215.155f);
        path.lineTo(165.0f, 585.425f);
        path.lineTo(145.0f, 605.335f);
        path.lineTo(385.0f, 325.305f);
        path.lineTo(305.0f, 665.305f);
        path.lineTo(885.0f, 465.735f);
        path.lineTo(45.0f, 675.965f);
        path.lineTo(225.0f, 295.855f);
        path.lineTo(965.0f, 935.165f);
        path.lineTo(405.0f, 605.85f);
        path.lineTo(705.0f, 555.95f);
        path.lineTo(795.0f, 85.395f);
        path.lineTo(305.0f, 755.305f);
        path.lineTo(665.0f, 445.205f);
        path.lineTo(425.0f, 685.715f);
        path.lineTo(425.0f, 325.545f);
        path.lineTo(685.0f, 925.1016f);
        path.lineTo(765.0f, 565.205f);
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
