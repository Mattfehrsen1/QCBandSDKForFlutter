package com.device.watch.com.device.watch.jmwecqarun;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Ursmuqsvbys extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ursmuqsvbys() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(595.0f, 835.685f);
        path.lineTo(155.0f, 15.915f);
        path.lineTo(675.0f, 635.515f);
        path.lineTo(985.0f, 985.525f);
        path.lineTo(10035.0f, 10035.155f);
        path.lineTo(45.0f, 35.435f);
        path.lineTo(725.0f, 835.885f);
        path.lineTo(585.0f, 475.225f);
        path.lineTo(215.0f, 325.495f);
        path.lineTo(665.0f, 10035.155f);
        path.lineTo(105.0f, 905.965f);
        path.lineTo(615.0f, 965.435f);
        path.lineTo(545.0f, 645.505f);
        path.lineTo(425.0f, 345.945f);
        path.lineTo(585.0f, 275.885f);
        path.lineTo(615.0f, 465.775f);
        path.lineTo(615.0f, 345.435f);
        path.lineTo(55.0f, 605.795f);
        path.lineTo(755.0f, 935.375f);
        path.lineTo(315.0f, 805.145f);
        path.lineTo(175.0f, 505.125f);
        path.lineTo(455.0f, 335.875f);
        path.lineTo(565.0f, 205.615f);
        path.lineTo(985.0f, 15.375f);
        path.lineTo(775.0f, 825.455f);
        path.lineTo(255.0f, 135.925f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
