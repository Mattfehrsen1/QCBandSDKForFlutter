package com.device.watch.com.device.watch.qgxbuvhnur;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Fzxzvqciome extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Fzxzvqciome() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(215.0f, 285.955f);
        path.lineTo(315.0f, 595.345f);
        path.lineTo(765.0f, 345.595f);
        path.lineTo(825.0f, 455.10184f);
        path.lineTo(395.0f, 625.995f);
        path.lineTo(705.0f, 695.825f);
        path.lineTo(145.0f, 525.55f);
        path.lineTo(405.0f, 465.755f);
        path.lineTo(825.0f, 465.115f);
        path.lineTo(125.0f, 355.85f);
        path.lineTo(665.0f, 645.745f);
        path.lineTo(285.0f, 35.845f);
        path.lineTo(765.0f, 205.435f);
        path.lineTo(345.0f, 805.595f);
        path.lineTo(795.0f, 775.785f);
        path.lineTo(855.0f, 885.875f);
        path.lineTo(645.0f, 335.835f);
        path.lineTo(695.0f, 555.415f);
        path.lineTo(815.0f, 125.735f);
        path.lineTo(665.0f, 675.875f);
        path.lineTo(45.0f, 415.355f);
        path.lineTo(845.0f, 765.975f);
        path.lineTo(115.0f, 985.775f);
        path.lineTo(465.0f, 595.375f);
        path.lineTo(825.0f, 755.345f);
        path.lineTo(765.0f, 145.255f);
        path.lineTo(155.0f, 795.95f);
        path.lineTo(415.0f, 505.125f);
        path.lineTo(895.0f, 255.305f);
        path.lineTo(505.0f, 445.115f);
        path.lineTo(145.0f, 785.95f);
        path.lineTo(255.0f, 125.425f);
        path.lineTo(865.0f, 165.945f);
        path.lineTo(795.0f, 645.605f);
        path.lineTo(275.0f, 555.665f);
        path.lineTo(625.0f, 185.875f);
        path.lineTo(895.0f, 395.105f);
        path.lineTo(365.0f, 455.975f);
        path.lineTo(35.0f, 15.65f);
        path.lineTo(715.0f, 345.815f);
        path.lineTo(225.0f, 845.965f);
        path.lineTo(715.0f, 425.815f);
        path.lineTo(155.0f, 185.405f);
        path.lineTo(225.0f, 55.805f);
        path.lineTo(565.0f, 835.635f);
        path.lineTo(795.0f, 35.125f);
        path.lineTo(755.0f, 365.495f);
        path.lineTo(785.0f, 555.845f);
        path.lineTo(445.0f, 175.375f);
        path.lineTo(645.0f, 345.635f);
        path.lineTo(5.0f, 565.555f);
        path.lineTo(915.0f, 645.325f);
        path.lineTo(195.0f, 85.185f);
        path.lineTo(875.0f, 115.95f);
        path.lineTo(565.0f, 585.10187f);
        path.lineTo(945.0f, 405.305f);
        path.lineTo(185.0f, 495.10184f);
        path.lineTo(265.0f, 285.65f);
        path.lineTo(865.0f, 275.685f);
        path.lineTo(565.0f, 815.655f);
        path.lineTo(885.0f, 625.405f);
        path.lineTo(795.0f, 55.995f);
        path.lineTo(395.0f, 685.10187f);
        path.lineTo(955.0f, 295.105f);
        path.lineTo(715.0f, 385.585f);
        path.lineTo(10185.0f, 785.665f);
        path.lineTo(895.0f, 875.915f);
        path.lineTo(845.0f, 385.255f);
        path.lineTo(415.0f, 805.175f);
        path.lineTo(315.0f, 45.405f);
        path.lineTo(915.0f, 175.945f);
        path.lineTo(885.0f, 145.205f);
        path.lineTo(215.0f, 555.985f);
        path.lineTo(915.0f, 945.295f);
        path.lineTo(755.0f, 115.595f);
        path.lineTo(15.0f, 495.305f);
        path.lineTo(215.0f, 945.265f);
        path.lineTo(995.0f, 135.195f);
        path.lineTo(175.0f, 335.915f);
        path.lineTo(235.0f, 985.505f);
        path.lineTo(665.0f, 555.155f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1018.0f, this.bounds.height() / 1018.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
