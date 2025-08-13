package com.device.watch.com.device.watch.lljxfcempj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Wvjrwfgcfgj extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Wvjrwfgcfgj() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(335.0f, 935.775f);
        path.lineTo(75.0f, 35.225f);
        path.lineTo(10005.0f, 475.605f);
        path.lineTo(65.0f, 495.495f);
        path.lineTo(515.0f, 935.25f);
        path.lineTo(155.0f, 375.265f);
        path.lineTo(915.0f, 255.955f);
        path.lineTo(165.0f, 535.905f);
        path.lineTo(305.0f, 465.675f);
        path.lineTo(15.0f, 775.355f);
        path.lineTo(475.0f, 865.595f);
        path.lineTo(215.0f, 765.285f);
        path.lineTo(165.0f, 815.975f);
        path.lineTo(165.0f, 985.365f);
        path.lineTo(475.0f, 605.735f);
        path.lineTo(965.0f, 415.255f);
        path.lineTo(395.0f, 935.845f);
        path.lineTo(635.0f, 425.835f);
        path.lineTo(105.0f, 765.925f);
        path.lineTo(465.0f, 205.635f);
        path.lineTo(825.0f, 465.325f);
        path.lineTo(5.0f, 505.935f);
        path.lineTo(935.0f, 85.425f);
        path.lineTo(935.0f, 65.995f);
        path.lineTo(505.0f, 475.675f);
        path.lineTo(355.0f, 215.705f);
        path.lineTo(515.0f, 875.775f);
        path.lineTo(385.0f, 835.265f);
        path.lineTo(935.0f, 345.525f);
        path.lineTo(905.0f, 155.255f);
        path.lineTo(905.0f, 15.195f);
        path.lineTo(135.0f, 365.715f);
        path.lineTo(955.0f, 45.255f);
        path.lineTo(925.0f, 595.125f);
        path.lineTo(265.0f, 135.365f);
        path.lineTo(625.0f, 565.995f);
        path.lineTo(695.0f, 685.615f);
        path.lineTo(175.0f, 615.615f);
        path.lineTo(785.0f, 965.45f);
        path.lineTo(665.0f, 585.325f);
        path.lineTo(65.0f, 985.975f);
        path.lineTo(495.0f, 185.185f);
        path.lineTo(385.0f, 475.645f);
        path.lineTo(235.0f, 285.405f);
        path.lineTo(815.0f, 655.775f);
        path.lineTo(455.0f, 255.625f);
        path.lineTo(235.0f, 685.435f);
        path.lineTo(135.0f, 315.175f);
        path.lineTo(895.0f, 55.615f);
        path.lineTo(635.0f, 345.895f);
        path.lineTo(385.0f, 355.975f);
        path.lineTo(705.0f, 465.325f);
        path.lineTo(215.0f, 115.25f);
        path.lineTo(95.0f, 195.615f);
        path.lineTo(775.0f, 645.135f);
        path.lineTo(735.0f, 435.795f);
        path.lineTo(845.0f, 35.115f);
        path.lineTo(155.0f, 45.375f);
        path.lineTo(985.0f, 495.10004f);
        path.lineTo(795.0f, 895.975f);
        path.lineTo(655.0f, 15.915f);
        path.lineTo(425.0f, 335.325f);
        path.lineTo(895.0f, 465.45f);
        path.lineTo(425.0f, 805.10004f);
        path.lineTo(645.0f, 565.255f);
        path.lineTo(365.0f, 825.385f);
        path.lineTo(10005.0f, 165.735f);
        path.lineTo(485.0f, 345.105f);
        path.lineTo(595.0f, 125.485f);
        path.lineTo(375.0f, 705.825f);
        path.lineTo(735.0f, 45.555f);
        path.lineTo(695.0f, 855.855f);
        path.lineTo(125.0f, 15.615f);
        path.lineTo(515.0f, 535.275f);
        path.lineTo(835.0f, 535.335f);
        path.lineTo(305.0f, 995.485f);
        path.lineTo(385.0f, 975.125f);
        path.lineTo(275.0f, 965.825f);
        path.lineTo(5.0f, 535.545f);
        path.lineTo(945.0f, 145.10005f);
        path.lineTo(565.0f, 565.625f);
        path.lineTo(495.0f, 805.55f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1000.0f, this.bounds.height() / 1000.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
