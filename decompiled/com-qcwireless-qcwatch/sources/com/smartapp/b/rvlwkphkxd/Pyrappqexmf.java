package com.smartapp.b.rvlwkphkxd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Pyrappqexmf extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pyrappqexmf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(355.0f, 165.165f);
        path.lineTo(95.0f, 35.655f);
        path.lineTo(825.0f, 215.255f);
        path.lineTo(25.0f, 835.845f);
        path.lineTo(115.0f, 815.65f);
        path.lineTo(125.0f, 795.205f);
        path.lineTo(725.0f, 585.535f);
        path.lineTo(565.0f, 385.145f);
        path.lineTo(465.0f, 445.615f);
        path.lineTo(535.0f, 5.405f);
        path.lineTo(55.0f, 535.10016f);
        path.lineTo(285.0f, 995.535f);
        path.lineTo(845.0f, 745.565f);
        path.lineTo(515.0f, 175.605f);
        path.lineTo(965.0f, 835.485f);
        path.lineTo(475.0f, 765.355f);
        path.lineTo(55.0f, 905.765f);
        path.lineTo(975.0f, 765.595f);
        path.lineTo(165.0f, 25.105f);
        path.lineTo(35.0f, 905.955f);
        path.lineTo(495.0f, 855.65f);
        path.lineTo(415.0f, 855.645f);
        path.lineTo(105.0f, 445.25f);
        path.lineTo(405.0f, 405.415f);
        path.lineTo(95.0f, 75.25f);
        path.lineTo(265.0f, 435.85f);
        path.lineTo(215.0f, 705.375f);
        path.lineTo(135.0f, 785.375f);
        path.lineTo(275.0f, 655.115f);
        path.lineTo(35.0f, 615.705f);
        path.lineTo(945.0f, 965.195f);
        path.lineTo(175.0f, 435.695f);
        path.lineTo(845.0f, 215.45f);
        path.lineTo(825.0f, 75.145f);
        path.lineTo(195.0f, 635.585f);
        path.lineTo(625.0f, 165.305f);
        path.lineTo(455.0f, 985.445f);
        path.lineTo(795.0f, 545.915f);
        path.lineTo(385.0f, 745.745f);
        path.lineTo(575.0f, 505.745f);
        path.lineTo(295.0f, 405.325f);
        path.lineTo(395.0f, 385.415f);
        path.lineTo(545.0f, 355.595f);
        path.lineTo(535.0f, 305.235f);
        path.lineTo(845.0f, 775.515f);
        path.lineTo(95.0f, 555.595f);
        path.lineTo(555.0f, 175.615f);
        path.lineTo(655.0f, 155.845f);
        path.lineTo(405.0f, 225.555f);
        path.lineTo(695.0f, 765.675f);
        path.lineTo(845.0f, 715.35f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1001.0f, this.bounds.height() / 1001.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
