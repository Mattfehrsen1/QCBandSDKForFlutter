package com.device.watch.com.device.watch.qgxbuvhnur;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Qhdvlzyxjsq extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Qhdvlzyxjsq() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(595.0f, 675.555f);
        path.lineTo(225.0f, 725.975f);
        path.lineTo(145.0f, 725.345f);
        path.lineTo(855.0f, 45.875f);
        path.lineTo(955.0f, 205.455f);
        path.lineTo(105.0f, 755.945f);
        path.lineTo(915.0f, 265.635f);
        path.lineTo(685.0f, 115.835f);
        path.lineTo(585.0f, 835.825f);
        path.lineTo(785.0f, 215.35f);
        path.lineTo(105.0f, 995.655f);
        path.lineTo(585.0f, 15.225f);
        path.lineTo(365.0f, 945.625f);
        path.lineTo(935.0f, 165.495f);
        path.lineTo(485.0f, 455.445f);
        path.lineTo(585.0f, 435.185f);
        path.lineTo(935.0f, 795.165f);
        path.lineTo(785.0f, 685.715f);
        path.lineTo(15.0f, 465.995f);
        path.lineTo(465.0f, 155.485f);
        path.lineTo(95.0f, 725.665f);
        path.lineTo(575.0f, 5.815f);
        path.lineTo(995.0f, 265.465f);
        path.lineTo(365.0f, 645.395f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
