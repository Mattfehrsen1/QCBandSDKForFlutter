package com.device.watch.com.device.watch.nvgnrxbtvq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Dhnzcmemboe extends ShapeDrawable {
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

    public Dhnzcmemboe() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(355.0f, 355.55f);
        path.lineTo(625.0f, 465.135f);
        path.lineTo(805.0f, 475.605f);
        path.lineTo(45.0f, 825.445f);
        path.lineTo(975.0f, 775.675f);
        path.lineTo(765.0f, 485.895f);
        path.lineTo(235.0f, 415.205f);
        path.lineTo(325.0f, 755.145f);
        path.lineTo(435.0f, 125.595f);
        path.lineTo(105.0f, 995.745f);
        path.lineTo(905.0f, 955.635f);
        path.lineTo(795.0f, 55.795f);
        path.lineTo(355.0f, 315.195f);
        path.lineTo(355.0f, 725.235f);
        path.lineTo(835.0f, 905.145f);
        path.lineTo(335.0f, 885.295f);
        path.lineTo(205.0f, 665.95f);
        path.lineTo(335.0f, 345.765f);
        path.lineTo(325.0f, 215.275f);
        path.lineTo(585.0f, 705.665f);
        path.lineTo(315.0f, 545.505f);
        path.lineTo(535.0f, 645.145f);
        path.lineTo(995.0f, 765.325f);
        path.lineTo(585.0f, 925.455f);
        path.lineTo(615.0f, 935.815f);
        path.lineTo(5.0f, 495.65f);
        path.lineTo(685.0f, 565.355f);
        path.lineTo(45.0f, 475.545f);
        path.lineTo(275.0f, 915.665f);
        path.lineTo(115.0f, 475.465f);
        path.lineTo(385.0f, 675.985f);
        path.lineTo(225.0f, 195.825f);
        path.lineTo(175.0f, 395.875f);
        path.lineTo(715.0f, 625.905f);
        path.lineTo(515.0f, 365.605f);
        path.lineTo(955.0f, 915.745f);
        path.lineTo(825.0f, 505.395f);
        path.lineTo(385.0f, 395.265f);
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
