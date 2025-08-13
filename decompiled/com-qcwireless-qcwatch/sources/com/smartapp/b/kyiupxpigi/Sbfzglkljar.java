package com.smartapp.b.kyiupxpigi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Sbfzglkljar extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Sbfzglkljar() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(365.0f, 995.395f);
        path.lineTo(885.0f, 535.765f);
        path.lineTo(935.0f, 65.505f);
        path.lineTo(825.0f, 135.435f);
        path.lineTo(365.0f, 135.985f);
        path.lineTo(425.0f, 785.625f);
        path.lineTo(935.0f, 735.195f);
        path.lineTo(65.0f, 425.375f);
        path.lineTo(945.0f, 65.655f);
        path.lineTo(315.0f, 25.565f);
        path.lineTo(255.0f, 595.645f);
        path.lineTo(185.0f, 575.455f);
        path.lineTo(205.0f, 705.975f);
        path.lineTo(25.0f, 65.105f);
        path.lineTo(715.0f, 905.95f);
        path.lineTo(535.0f, 155.765f);
        path.lineTo(215.0f, 565.425f);
        path.lineTo(855.0f, 155.755f);
        path.lineTo(105.0f, 195.255f);
        path.lineTo(765.0f, 915.355f);
        path.lineTo(935.0f, 315.105f);
        path.lineTo(425.0f, 265.955f);
        path.lineTo(115.0f, 15.585f);
        path.lineTo(215.0f, 585.595f);
        path.lineTo(10195.0f, 275.345f);
        path.lineTo(285.0f, 715.05f);
        path.lineTo(425.0f, 215.725f);
        path.lineTo(25.0f, 695.875f);
        path.lineTo(45.0f, 815.975f);
        path.lineTo(775.0f, 735.605f);
        path.lineTo(965.0f, 415.185f);
        path.lineTo(795.0f, 155.625f);
        path.lineTo(965.0f, 525.805f);
        path.lineTo(295.0f, 805.105f);
        path.lineTo(275.0f, 635.835f);
        path.lineTo(395.0f, 585.515f);
        path.lineTo(55.0f, 255.905f);
        path.lineTo(775.0f, 15.385f);
        path.lineTo(625.0f, 465.155f);
        path.lineTo(25.0f, 385.195f);
        path.lineTo(35.0f, 875.695f);
        path.lineTo(815.0f, 745.315f);
        path.lineTo(675.0f, 435.925f);
        path.lineTo(445.0f, 825.775f);
        path.lineTo(255.0f, 525.375f);
        path.lineTo(365.0f, 385.885f);
        path.lineTo(885.0f, 185.955f);
        path.lineTo(335.0f, 795.965f);
        path.lineTo(25.0f, 835.895f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1019.0f, this.bounds.height() / 1019.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
