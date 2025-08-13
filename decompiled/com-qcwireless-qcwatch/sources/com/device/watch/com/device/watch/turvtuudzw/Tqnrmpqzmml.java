package com.device.watch.com.device.watch.turvtuudzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Tqnrmpqzmml extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Tqnrmpqzmml() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(335.0f, 835.785f);
        path.lineTo(335.0f, 675.15f);
        path.lineTo(35.0f, 175.645f);
        path.lineTo(865.0f, 145.145f);
        path.lineTo(65.0f, 985.805f);
        path.lineTo(825.0f, 725.635f);
        path.lineTo(465.0f, 435.625f);
        path.lineTo(775.0f, 925.205f);
        path.lineTo(935.0f, 965.925f);
        path.lineTo(505.0f, 515.555f);
        path.lineTo(135.0f, 255.25f);
        path.lineTo(475.0f, 145.915f);
        path.lineTo(595.0f, 585.375f);
        path.lineTo(825.0f, 75.825f);
        path.lineTo(765.0f, 745.145f);
        path.lineTo(5.0f, 275.935f);
        path.lineTo(965.0f, 655.595f);
        path.lineTo(825.0f, 215.235f);
        path.lineTo(155.0f, 735.95f);
        path.lineTo(355.0f, 735.765f);
        path.lineTo(455.0f, 885.315f);
        path.lineTo(365.0f, 735.655f);
        path.lineTo(975.0f, 885.675f);
        path.lineTo(675.0f, 605.05f);
        path.lineTo(615.0f, 645.495f);
        path.lineTo(145.0f, 915.1008f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1008.0f, this.bounds.height() / 1008.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
