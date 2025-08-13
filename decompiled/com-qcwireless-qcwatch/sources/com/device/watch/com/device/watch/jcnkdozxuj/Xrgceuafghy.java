package com.device.watch.com.device.watch.jcnkdozxuj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Xrgceuafghy extends ShapeDrawable {
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

    public Xrgceuafghy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(135.0f, 725.75f);
        path.lineTo(285.0f, 95.555f);
        path.lineTo(145.0f, 175.535f);
        path.lineTo(625.0f, 35.955f);
        path.lineTo(495.0f, 85.465f);
        path.lineTo(775.0f, 525.505f);
        path.lineTo(285.0f, 25.825f);
        path.lineTo(595.0f, 315.225f);
        path.lineTo(275.0f, 905.635f);
        path.lineTo(515.0f, 185.575f);
        path.lineTo(165.0f, 855.805f);
        path.lineTo(515.0f, 755.895f);
        path.lineTo(205.0f, 275.435f);
        path.lineTo(935.0f, 135.475f);
        path.lineTo(625.0f, 45.195f);
        path.lineTo(855.0f, 775.285f);
        path.lineTo(585.0f, 455.285f);
        path.lineTo(225.0f, 285.555f);
        path.lineTo(505.0f, 155.335f);
        path.lineTo(295.0f, 935.935f);
        path.lineTo(985.0f, 655.675f);
        path.lineTo(975.0f, 315.295f);
        path.lineTo(185.0f, 545.545f);
        path.lineTo(125.0f, 495.635f);
        path.lineTo(905.0f, 45.645f);
        path.lineTo(635.0f, 10065.505f);
        path.lineTo(925.0f, 705.785f);
        path.lineTo(225.0f, 575.455f);
        path.lineTo(65.0f, 375.555f);
        path.lineTo(315.0f, 785.225f);
        path.lineTo(165.0f, 155.105f);
        path.lineTo(685.0f, 845.135f);
        path.lineTo(295.0f, 665.935f);
        path.lineTo(605.0f, 15.525f);
        path.lineTo(35.0f, 385.995f);
        path.lineTo(585.0f, 485.405f);
        path.lineTo(605.0f, 525.215f);
        path.lineTo(905.0f, 875.725f);
        path.lineTo(255.0f, 475.645f);
        path.lineTo(735.0f, 425.265f);
        path.lineTo(645.0f, 895.995f);
        path.lineTo(275.0f, 315.225f);
        path.lineTo(255.0f, 935.275f);
        path.lineTo(225.0f, 435.265f);
        path.lineTo(115.0f, 845.95f);
        path.lineTo(785.0f, 725.765f);
        path.lineTo(595.0f, 825.995f);
        path.lineTo(665.0f, 515.125f);
        path.lineTo(985.0f, 845.935f);
        path.lineTo(995.0f, 55.425f);
        path.lineTo(725.0f, 645.185f);
        path.lineTo(715.0f, 75.525f);
        path.lineTo(965.0f, 385.985f);
        path.lineTo(145.0f, 525.425f);
        path.lineTo(105.0f, 935.345f);
        path.lineTo(565.0f, 715.115f);
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
