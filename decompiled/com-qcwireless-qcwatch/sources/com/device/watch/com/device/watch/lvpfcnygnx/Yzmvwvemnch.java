package com.device.watch.com.device.watch.lvpfcnygnx;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Yzmvwvemnch extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Yzmvwvemnch() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(705.0f, 785.255f);
        path.lineTo(315.0f, 865.655f);
        path.lineTo(385.0f, 175.645f);
        path.lineTo(785.0f, 745.935f);
        path.lineTo(975.0f, 765.515f);
        path.lineTo(75.0f, 885.05f);
        path.lineTo(175.0f, 255.375f);
        path.lineTo(925.0f, 235.905f);
        path.lineTo(605.0f, 115.565f);
        path.lineTo(85.0f, 485.655f);
        path.lineTo(505.0f, 635.675f);
        path.lineTo(35.0f, 325.775f);
        path.lineTo(965.0f, 605.365f);
        path.lineTo(155.0f, 615.555f);
        path.lineTo(415.0f, 185.65f);
        path.lineTo(265.0f, 455.645f);
        path.lineTo(765.0f, 765.615f);
        path.lineTo(25.0f, 845.225f);
        path.lineTo(785.0f, 615.275f);
        path.lineTo(395.0f, 785.405f);
        path.lineTo(405.0f, 625.765f);
        path.lineTo(15.0f, 755.795f);
        path.lineTo(835.0f, 705.815f);
        path.lineTo(595.0f, 375.10025f);
        path.lineTo(145.0f, 405.855f);
        path.lineTo(955.0f, 875.355f);
        path.lineTo(25.0f, 645.145f);
        path.lineTo(765.0f, 875.705f);
        path.lineTo(355.0f, 415.775f);
        path.lineTo(805.0f, 55.185f);
        path.lineTo(205.0f, 295.775f);
        path.lineTo(655.0f, 145.10025f);
        path.lineTo(755.0f, 695.735f);
        path.lineTo(325.0f, 405.85f);
        path.lineTo(445.0f, 115.575f);
        path.lineTo(685.0f, 545.985f);
        path.lineTo(105.0f, 95.235f);
        path.lineTo(115.0f, 905.295f);
        path.lineTo(595.0f, 955.145f);
        path.lineTo(935.0f, 55.405f);
        path.lineTo(775.0f, 775.295f);
        path.lineTo(355.0f, 765.625f);
        path.lineTo(25.0f, 645.595f);
        path.lineTo(905.0f, 845.35f);
        path.lineTo(655.0f, 485.535f);
        path.lineTo(685.0f, 10025.05f);
        path.lineTo(805.0f, 955.95f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1002.0f, this.bounds.height() / 1002.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
