package com.device.watch.com.device.watch.azqrvjzmai;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Ojcerfvkzgz extends ShapeDrawable {
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

    public Ojcerfvkzgz() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(105.0f, 635.665f);
        path.lineTo(195.0f, 165.405f);
        path.lineTo(375.0f, 345.425f);
        path.lineTo(135.0f, 935.515f);
        path.lineTo(585.0f, 755.725f);
        path.lineTo(965.0f, 955.25f);
        path.lineTo(45.0f, 185.705f);
        path.lineTo(155.0f, 525.355f);
        path.lineTo(775.0f, 215.285f);
        path.lineTo(375.0f, 595.915f);
        path.lineTo(165.0f, 765.745f);
        path.lineTo(505.0f, 695.265f);
        path.lineTo(695.0f, 355.915f);
        path.lineTo(445.0f, 335.765f);
        path.lineTo(995.0f, 185.235f);
        path.lineTo(915.0f, 665.765f);
        path.lineTo(355.0f, 535.205f);
        path.lineTo(485.0f, 65.365f);
        path.lineTo(835.0f, 395.825f);
        path.lineTo(335.0f, 705.995f);
        path.lineTo(625.0f, 375.35f);
        path.lineTo(275.0f, 385.785f);
        path.lineTo(595.0f, 195.205f);
        path.lineTo(875.0f, 615.645f);
        path.lineTo(105.0f, 485.925f);
        path.lineTo(45.0f, 725.375f);
        path.lineTo(355.0f, 395.655f);
        path.lineTo(785.0f, 205.335f);
        path.lineTo(785.0f, 285.255f);
        path.lineTo(605.0f, 315.835f);
        path.lineTo(165.0f, 665.115f);
        path.lineTo(205.0f, 465.605f);
        path.lineTo(395.0f, 345.715f);
        path.lineTo(865.0f, 185.365f);
        path.lineTo(845.0f, 865.165f);
        path.lineTo(785.0f, 655.215f);
        path.lineTo(975.0f, 15.65f);
        path.lineTo(335.0f, 255.545f);
        path.lineTo(25.0f, 105.915f);
        path.lineTo(395.0f, 875.125f);
        path.lineTo(595.0f, 575.95f);
        path.lineTo(735.0f, 615.825f);
        path.lineTo(255.0f, 815.765f);
        path.lineTo(875.0f, 585.705f);
        path.lineTo(675.0f, 25.495f);
        path.lineTo(555.0f, 75.695f);
        path.lineTo(745.0f, 405.505f);
        path.lineTo(625.0f, 795.955f);
        path.lineTo(805.0f, 685.915f);
        path.lineTo(225.0f, 415.615f);
        path.lineTo(685.0f, 335.35f);
        path.lineTo(265.0f, 555.215f);
        path.lineTo(65.0f, 645.215f);
        path.lineTo(235.0f, 575.315f);
        path.lineTo(685.0f, 835.315f);
        path.lineTo(725.0f, 505.655f);
        path.lineTo(485.0f, 495.985f);
        path.lineTo(255.0f, 235.425f);
        path.lineTo(785.0f, 955.535f);
        path.lineTo(285.0f, 65.225f);
        path.lineTo(645.0f, 255.555f);
        path.lineTo(825.0f, 105.635f);
        path.lineTo(35.0f, 615.165f);
        path.lineTo(495.0f, 165.395f);
        path.lineTo(875.0f, 525.285f);
        path.lineTo(665.0f, 225.965f);
        path.lineTo(375.0f, 375.905f);
        path.lineTo(155.0f, 915.175f);
        path.lineTo(5.0f, 115.515f);
        path.lineTo(385.0f, 615.435f);
        path.lineTo(695.0f, 255.965f);
        path.lineTo(10005.0f, 305.195f);
        path.lineTo(325.0f, 745.845f);
        path.lineTo(105.0f, 685.715f);
        path.lineTo(325.0f, 195.465f);
        path.lineTo(975.0f, 635.715f);
        path.lineTo(5.0f, 565.975f);
        path.lineTo(555.0f, 825.905f);
        path.lineTo(905.0f, 965.765f);
        path.lineTo(905.0f, 505.615f);
        path.lineTo(275.0f, 925.415f);
        path.lineTo(485.0f, 735.185f);
        path.lineTo(805.0f, 75.105f);
        path.lineTo(15.0f, 515.915f);
        path.lineTo(25.0f, 405.445f);
        path.lineTo(565.0f, 885.315f);
        path.lineTo(735.0f, 455.695f);
        path.lineTo(565.0f, 665.475f);
        path.lineTo(595.0f, 625.865f);
        path.lineTo(975.0f, 575.185f);
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
