package com.device.watch.com.device.watch.tsmihfhjrs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Egaaccaoovq extends ShapeDrawable {
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

    public Egaaccaoovq() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(675.0f, 755.75f);
        path.lineTo(195.0f, 365.995f);
        path.lineTo(435.0f, 995.985f);
        path.lineTo(595.0f, 85.525f);
        path.lineTo(885.0f, 305.325f);
        path.lineTo(805.0f, 505.405f);
        path.lineTo(475.0f, 725.465f);
        path.lineTo(685.0f, 15.155f);
        path.lineTo(875.0f, 615.485f);
        path.lineTo(705.0f, 765.715f);
        path.lineTo(185.0f, 745.495f);
        path.lineTo(155.0f, 355.805f);
        path.lineTo(235.0f, 125.355f);
        path.lineTo(625.0f, 305.135f);
        path.lineTo(275.0f, 165.95f);
        path.lineTo(10015.0f, 515.505f);
        path.lineTo(205.0f, 605.75f);
        path.lineTo(225.0f, 655.115f);
        path.lineTo(595.0f, 425.265f);
        path.lineTo(405.0f, 375.235f);
        path.lineTo(205.0f, 725.415f);
        path.lineTo(295.0f, 305.765f);
        path.lineTo(855.0f, 545.845f);
        path.lineTo(425.0f, 395.155f);
        path.lineTo(95.0f, 775.255f);
        path.lineTo(355.0f, 805.565f);
        path.lineTo(435.0f, 555.165f);
        path.lineTo(325.0f, 195.315f);
        path.lineTo(165.0f, 855.145f);
        path.lineTo(735.0f, 795.745f);
        path.lineTo(995.0f, 405.835f);
        path.lineTo(655.0f, 305.295f);
        path.lineTo(975.0f, 875.445f);
        path.lineTo(675.0f, 255.255f);
        path.lineTo(225.0f, 905.655f);
        path.lineTo(955.0f, 885.365f);
        path.lineTo(455.0f, 915.155f);
        path.lineTo(925.0f, 375.545f);
        path.lineTo(905.0f, 485.785f);
        path.lineTo(715.0f, 265.585f);
        path.lineTo(945.0f, 255.615f);
        path.lineTo(115.0f, 435.265f);
        path.lineTo(915.0f, 995.205f);
        path.lineTo(385.0f, 365.905f);
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
