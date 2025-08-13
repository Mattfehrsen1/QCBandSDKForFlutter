package com.smartapp.b.pmfyhglpoj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ydjzybxwtjw extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ydjzybxwtjw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(295.0f, 765.465f);
        path.lineTo(455.0f, 115.165f);
        path.lineTo(555.0f, 745.1005f);
        path.lineTo(475.0f, 265.865f);
        path.lineTo(445.0f, 75.10055f);
        path.lineTo(365.0f, 205.195f);
        path.lineTo(35.0f, 375.365f);
        path.lineTo(795.0f, 585.95f);
        path.lineTo(265.0f, 235.915f);
        path.lineTo(595.0f, 495.115f);
        path.lineTo(845.0f, 35.10055f);
        path.lineTo(375.0f, 195.745f);
        path.lineTo(695.0f, 845.645f);
        path.lineTo(325.0f, 435.495f);
        path.lineTo(815.0f, 275.45f);
        path.lineTo(985.0f, 25.965f);
        path.lineTo(765.0f, 275.65f);
        path.lineTo(155.0f, 75.805f);
        path.lineTo(835.0f, 615.935f);
        path.lineTo(165.0f, 545.305f);
        path.lineTo(5.0f, 125.35f);
        path.lineTo(135.0f, 505.455f);
        path.lineTo(895.0f, 395.25f);
        path.lineTo(725.0f, 695.915f);
        path.lineTo(585.0f, 445.705f);
        path.lineTo(335.0f, 915.265f);
        path.lineTo(485.0f, 585.55f);
        path.lineTo(965.0f, 615.885f);
        path.lineTo(505.0f, 805.625f);
        path.lineTo(165.0f, 905.1005f);
        path.lineTo(145.0f, 325.845f);
        path.lineTo(765.0f, 825.85f);
        path.lineTo(45.0f, 725.575f);
        path.lineTo(365.0f, 325.445f);
        path.lineTo(995.0f, 125.665f);
        path.lineTo(625.0f, 225.925f);
        path.lineTo(735.0f, 105.525f);
        path.lineTo(355.0f, 165.945f);
        path.lineTo(565.0f, 45.485f);
        path.lineTo(895.0f, 315.905f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
