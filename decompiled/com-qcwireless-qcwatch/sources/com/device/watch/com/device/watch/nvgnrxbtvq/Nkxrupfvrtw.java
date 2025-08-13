package com.device.watch.com.device.watch.nvgnrxbtvq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Nkxrupfvrtw extends ShapeDrawable {
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

    public Nkxrupfvrtw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(255.0f, 905.355f);
        path.lineTo(405.0f, 645.535f);
        path.lineTo(395.0f, 825.835f);
        path.lineTo(225.0f, 105.975f);
        path.lineTo(455.0f, 855.885f);
        path.lineTo(235.0f, 495.865f);
        path.lineTo(175.0f, 375.185f);
        path.lineTo(785.0f, 335.925f);
        path.lineTo(535.0f, 975.605f);
        path.lineTo(855.0f, 455.865f);
        path.lineTo(115.0f, 885.965f);
        path.lineTo(905.0f, 495.625f);
        path.lineTo(985.0f, 655.35f);
        path.lineTo(965.0f, 995.865f);
        path.lineTo(15.0f, 965.435f);
        path.lineTo(15.0f, 655.635f);
        path.lineTo(135.0f, 485.235f);
        path.lineTo(725.0f, 305.525f);
        path.lineTo(525.0f, 335.275f);
        path.lineTo(25.0f, 825.405f);
        path.lineTo(855.0f, 305.485f);
        path.lineTo(815.0f, 225.715f);
        path.lineTo(615.0f, 675.655f);
        path.lineTo(775.0f, 745.485f);
        path.lineTo(85.0f, 565.415f);
        path.lineTo(595.0f, 935.305f);
        path.lineTo(235.0f, 815.195f);
        path.lineTo(405.0f, 985.645f);
        path.lineTo(435.0f, 275.935f);
        path.lineTo(255.0f, 755.865f);
        path.lineTo(205.0f, 965.685f);
        path.lineTo(445.0f, 235.585f);
        path.lineTo(485.0f, 725.325f);
        path.lineTo(675.0f, 475.685f);
        path.lineTo(815.0f, 305.255f);
        path.lineTo(165.0f, 925.105f);
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
