package com.smartapp.b.hdebuecnly;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Lqlnthhvpyf extends ShapeDrawable {
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

    public Lqlnthhvpyf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(645.0f, 425.495f);
        path.lineTo(185.0f, 875.485f);
        path.lineTo(945.0f, 185.485f);
        path.lineTo(265.0f, 195.755f);
        path.lineTo(885.0f, 555.785f);
        path.lineTo(945.0f, 595.135f);
        path.lineTo(135.0f, 405.135f);
        path.lineTo(15.0f, 715.185f);
        path.lineTo(685.0f, 55.165f);
        path.lineTo(655.0f, 435.85f);
        path.lineTo(935.0f, 705.65f);
        path.lineTo(145.0f, 515.175f);
        path.lineTo(355.0f, 675.145f);
        path.lineTo(465.0f, 295.705f);
        path.lineTo(15.0f, 775.325f);
        path.lineTo(10175.0f, 935.115f);
        path.lineTo(415.0f, 275.405f);
        path.lineTo(75.0f, 915.805f);
        path.lineTo(805.0f, 75.435f);
        path.lineTo(915.0f, 815.635f);
        path.lineTo(605.0f, 455.85f);
        path.lineTo(515.0f, 325.465f);
        path.lineTo(675.0f, 775.575f);
        path.lineTo(835.0f, 825.10175f);
        path.lineTo(435.0f, 155.475f);
        path.lineTo(815.0f, 675.855f);
        path.lineTo(775.0f, 745.965f);
        path.lineTo(175.0f, 865.05f);
        path.lineTo(135.0f, 295.445f);
        path.lineTo(665.0f, 165.445f);
        path.lineTo(725.0f, 255.675f);
        path.lineTo(375.0f, 915.975f);
        path.lineTo(855.0f, 335.125f);
        path.lineTo(695.0f, 645.105f);
        path.lineTo(25.0f, 145.175f);
        path.lineTo(415.0f, 555.425f);
        path.lineTo(535.0f, 525.435f);
        path.lineTo(365.0f, 285.255f);
        path.lineTo(485.0f, 805.315f);
        path.lineTo(315.0f, 825.85f);
        path.lineTo(965.0f, 475.665f);
        path.lineTo(65.0f, 665.835f);
        path.lineTo(125.0f, 355.985f);
        path.lineTo(675.0f, 195.625f);
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
