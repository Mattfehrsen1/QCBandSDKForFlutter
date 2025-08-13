package com.smartapp.b.blzrgrokny;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Cnzkooznfde extends ShapeDrawable {
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

    public Cnzkooznfde() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(945.0f, 435.225f);
        path.lineTo(735.0f, 495.635f);
        path.lineTo(115.0f, 265.545f);
        path.lineTo(525.0f, 265.85f);
        path.lineTo(45.0f, 495.365f);
        path.lineTo(655.0f, 55.745f);
        path.lineTo(285.0f, 705.355f);
        path.lineTo(285.0f, 65.995f);
        path.lineTo(355.0f, 635.465f);
        path.lineTo(775.0f, 355.755f);
        path.lineTo(905.0f, 425.535f);
        path.lineTo(835.0f, 145.935f);
        path.lineTo(745.0f, 915.65f);
        path.lineTo(885.0f, 535.595f);
        path.lineTo(675.0f, 645.685f);
        path.lineTo(505.0f, 405.645f);
        path.lineTo(695.0f, 495.175f);
        path.lineTo(835.0f, 505.855f);
        path.lineTo(175.0f, 805.395f);
        path.lineTo(135.0f, 805.35f);
        path.lineTo(585.0f, 75.395f);
        path.lineTo(795.0f, 615.835f);
        path.lineTo(525.0f, 985.405f);
        path.lineTo(225.0f, 115.745f);
        path.lineTo(505.0f, 855.315f);
        path.lineTo(905.0f, 785.795f);
        path.lineTo(285.0f, 365.395f);
        path.lineTo(35.0f, 935.195f);
        path.lineTo(925.0f, 485.975f);
        path.lineTo(35.0f, 985.155f);
        path.lineTo(875.0f, 185.85f);
        path.lineTo(315.0f, 385.545f);
        path.lineTo(345.0f, 895.365f);
        path.lineTo(735.0f, 875.375f);
        path.lineTo(595.0f, 155.55f);
        path.lineTo(895.0f, 705.865f);
        path.lineTo(465.0f, 575.435f);
        path.lineTo(285.0f, 925.415f);
        path.lineTo(745.0f, 265.905f);
        path.lineTo(565.0f, 865.765f);
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
