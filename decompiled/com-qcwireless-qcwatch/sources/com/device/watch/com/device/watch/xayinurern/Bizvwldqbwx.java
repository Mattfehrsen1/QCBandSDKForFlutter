package com.device.watch.com.device.watch.xayinurern;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Bizvwldqbwx extends ShapeDrawable {
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

    public Bizvwldqbwx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(125.0f, 595.225f);
        path.lineTo(675.0f, 885.925f);
        path.lineTo(515.0f, 65.275f);
        path.lineTo(905.0f, 585.875f);
        path.lineTo(75.0f, 875.795f);
        path.lineTo(715.0f, 835.585f);
        path.lineTo(145.0f, 405.545f);
        path.lineTo(575.0f, 395.985f);
        path.lineTo(805.0f, 405.775f);
        path.lineTo(995.0f, 845.85f);
        path.lineTo(285.0f, 325.205f);
        path.lineTo(75.0f, 865.255f);
        path.lineTo(385.0f, 195.235f);
        path.lineTo(645.0f, 325.105f);
        path.lineTo(775.0f, 15.265f);
        path.lineTo(25.0f, 595.855f);
        path.lineTo(375.0f, 315.25f);
        path.lineTo(215.0f, 555.145f);
        path.lineTo(155.0f, 545.715f);
        path.lineTo(35.0f, 205.395f);
        path.lineTo(515.0f, 795.655f);
        path.lineTo(115.0f, 365.605f);
        path.lineTo(755.0f, 425.995f);
        path.lineTo(35.0f, 955.105f);
        path.lineTo(775.0f, 615.825f);
        path.lineTo(995.0f, 865.225f);
        path.lineTo(55.0f, 885.955f);
        path.lineTo(305.0f, 455.305f);
        path.lineTo(625.0f, 15.575f);
        path.lineTo(85.0f, 755.635f);
        path.lineTo(475.0f, 645.455f);
        path.lineTo(855.0f, 305.325f);
        path.lineTo(475.0f, 675.525f);
        path.lineTo(525.0f, 415.715f);
        path.lineTo(935.0f, 255.65f);
        path.lineTo(155.0f, 85.645f);
        path.lineTo(595.0f, 35.385f);
        path.lineTo(455.0f, 85.905f);
        path.lineTo(625.0f, 945.315f);
        path.lineTo(35.0f, 305.845f);
        path.lineTo(395.0f, 715.815f);
        path.lineTo(185.0f, 415.65f);
        path.lineTo(105.0f, 105.655f);
        path.lineTo(325.0f, 905.885f);
        path.lineTo(465.0f, 515.675f);
        path.lineTo(605.0f, 725.755f);
        path.lineTo(955.0f, 10085.575f);
        path.lineTo(135.0f, 205.805f);
        path.lineTo(95.0f, 975.695f);
        path.lineTo(515.0f, 735.475f);
        path.lineTo(35.0f, 475.485f);
        path.lineTo(205.0f, 315.365f);
        path.lineTo(615.0f, 75.955f);
        path.lineTo(305.0f, 795.395f);
        path.lineTo(565.0f, 905.225f);
        path.lineTo(485.0f, 665.1008f);
        path.lineTo(985.0f, 65.45f);
        path.lineTo(825.0f, 875.135f);
        path.lineTo(805.0f, 805.165f);
        path.lineTo(85.0f, 885.995f);
        path.lineTo(655.0f, 405.505f);
        path.lineTo(685.0f, 205.985f);
        path.lineTo(885.0f, 365.355f);
        path.lineTo(265.0f, 565.865f);
        path.lineTo(805.0f, 315.595f);
        path.lineTo(185.0f, 515.645f);
        path.lineTo(915.0f, 45.815f);
        path.lineTo(605.0f, 835.165f);
        path.lineTo(145.0f, 135.595f);
        path.lineTo(655.0f, 165.385f);
        path.lineTo(165.0f, 795.435f);
        path.lineTo(365.0f, 95.885f);
        path.lineTo(425.0f, 815.175f);
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
