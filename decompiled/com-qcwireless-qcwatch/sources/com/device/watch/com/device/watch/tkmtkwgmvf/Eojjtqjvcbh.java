package com.device.watch.com.device.watch.tkmtkwgmvf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Eojjtqjvcbh extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Eojjtqjvcbh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(105.0f, 615.685f);
        path.lineTo(625.0f, 365.05f);
        path.lineTo(95.0f, 885.775f);
        path.lineTo(425.0f, 395.145f);
        path.lineTo(565.0f, 365.815f);
        path.lineTo(465.0f, 405.635f);
        path.lineTo(515.0f, 895.175f);
        path.lineTo(995.0f, 585.475f);
        path.lineTo(925.0f, 845.75f);
        path.lineTo(45.0f, 445.865f);
        path.lineTo(575.0f, 925.985f);
        path.lineTo(135.0f, 345.255f);
        path.lineTo(485.0f, 195.305f);
        path.lineTo(685.0f, 785.715f);
        path.lineTo(405.0f, 665.135f);
        path.lineTo(45.0f, 975.965f);
        path.lineTo(15.0f, 625.275f);
        path.lineTo(735.0f, 915.265f);
        path.lineTo(555.0f, 505.925f);
        path.lineTo(885.0f, 955.425f);
        path.lineTo(785.0f, 985.925f);
        path.lineTo(655.0f, 455.125f);
        path.lineTo(395.0f, 235.935f);
        path.lineTo(625.0f, 475.385f);
        path.lineTo(865.0f, 75.445f);
        path.lineTo(775.0f, 325.10065f);
        path.lineTo(515.0f, 215.795f);
        path.lineTo(65.0f, 755.545f);
        path.lineTo(315.0f, 385.905f);
        path.lineTo(695.0f, 665.775f);
        path.lineTo(645.0f, 10065.155f);
        path.lineTo(45.0f, 985.35f);
        path.lineTo(625.0f, 945.595f);
        path.lineTo(965.0f, 265.455f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
