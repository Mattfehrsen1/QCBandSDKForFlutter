package com.device.watch.com.device.watch.yklzqtoumd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Xlmoumsicov extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Xlmoumsicov() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(955.0f, 285.05f);
        path.lineTo(875.0f, 935.395f);
        path.lineTo(665.0f, 275.815f);
        path.lineTo(845.0f, 845.405f);
        path.lineTo(785.0f, 165.905f);
        path.lineTo(865.0f, 425.125f);
        path.lineTo(655.0f, 935.165f);
        path.lineTo(685.0f, 775.765f);
        path.lineTo(435.0f, 685.745f);
        path.lineTo(325.0f, 465.255f);
        path.lineTo(915.0f, 365.85f);
        path.lineTo(375.0f, 885.115f);
        path.lineTo(575.0f, 35.775f);
        path.lineTo(575.0f, 955.295f);
        path.lineTo(45.0f, 995.35f);
        path.lineTo(565.0f, 645.355f);
        path.lineTo(895.0f, 105.735f);
        path.lineTo(175.0f, 915.675f);
        path.lineTo(485.0f, 615.825f);
        path.lineTo(185.0f, 65.625f);
        path.lineTo(995.0f, 25.415f);
        path.lineTo(625.0f, 5.25f);
        path.lineTo(925.0f, 555.315f);
        path.lineTo(905.0f, 965.305f);
        path.lineTo(845.0f, 855.895f);
        path.lineTo(55.0f, 645.755f);
        path.lineTo(235.0f, 85.35f);
        path.lineTo(235.0f, 105.845f);
        path.lineTo(305.0f, 395.325f);
        path.lineTo(35.0f, 305.965f);
        path.lineTo(415.0f, 555.935f);
        path.lineTo(895.0f, 345.35f);
        path.lineTo(985.0f, 475.995f);
        path.lineTo(815.0f, 455.765f);
        path.lineTo(515.0f, 195.655f);
        path.lineTo(545.0f, 755.405f);
        path.lineTo(175.0f, 355.795f);
        path.lineTo(785.0f, 785.985f);
        path.lineTo(205.0f, 745.835f);
        path.lineTo(485.0f, 955.635f);
        path.lineTo(965.0f, 615.995f);
        path.lineTo(645.0f, 655.115f);
        path.lineTo(105.0f, 625.435f);
        path.lineTo(545.0f, 705.485f);
        path.lineTo(495.0f, 535.35f);
        path.lineTo(595.0f, 205.85f);
        path.lineTo(175.0f, 125.935f);
        path.lineTo(435.0f, 795.165f);
        path.lineTo(655.0f, 485.605f);
        path.lineTo(65.0f, 115.105f);
        path.lineTo(875.0f, 735.315f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1014.0f, this.bounds.height() / 1014.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
