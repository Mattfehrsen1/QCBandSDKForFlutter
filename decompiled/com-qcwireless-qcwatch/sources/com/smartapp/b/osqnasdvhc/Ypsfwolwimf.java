package com.smartapp.b.osqnasdvhc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ypsfwolwimf extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ypsfwolwimf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(625.0f, 5.875f);
        path.lineTo(665.0f, 725.765f);
        path.lineTo(565.0f, 715.385f);
        path.lineTo(415.0f, 705.745f);
        path.lineTo(185.0f, 645.655f);
        path.lineTo(655.0f, 685.275f);
        path.lineTo(345.0f, 735.75f);
        path.lineTo(715.0f, 755.345f);
        path.lineTo(685.0f, 555.45f);
        path.lineTo(785.0f, 325.405f);
        path.lineTo(315.0f, 745.25f);
        path.lineTo(545.0f, 595.535f);
        path.lineTo(575.0f, 685.295f);
        path.lineTo(665.0f, 885.525f);
        path.lineTo(965.0f, 275.925f);
        path.lineTo(595.0f, 325.325f);
        path.lineTo(705.0f, 595.255f);
        path.lineTo(815.0f, 265.05f);
        path.lineTo(465.0f, 185.275f);
        path.lineTo(295.0f, 285.615f);
        path.lineTo(945.0f, 165.495f);
        path.lineTo(475.0f, 995.55f);
        path.lineTo(765.0f, 685.725f);
        path.lineTo(955.0f, 545.55f);
        path.lineTo(135.0f, 785.885f);
        path.lineTo(445.0f, 675.295f);
        path.lineTo(25.0f, 595.125f);
        path.lineTo(645.0f, 365.275f);
        path.lineTo(455.0f, 505.975f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1004.0f, this.bounds.height() / 1004.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
