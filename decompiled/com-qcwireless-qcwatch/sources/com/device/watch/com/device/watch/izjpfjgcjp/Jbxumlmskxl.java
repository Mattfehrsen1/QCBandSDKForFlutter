package com.device.watch.com.device.watch.izjpfjgcjp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Jbxumlmskxl extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Jbxumlmskxl() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(725.0f, 465.775f);
        path.lineTo(775.0f, 995.435f);
        path.lineTo(485.0f, 485.995f);
        path.lineTo(295.0f, 105.965f);
        path.lineTo(265.0f, 535.965f);
        path.lineTo(985.0f, 285.345f);
        path.lineTo(655.0f, 835.405f);
        path.lineTo(385.0f, 765.455f);
        path.lineTo(5.0f, 625.445f);
        path.lineTo(125.0f, 5.505f);
        path.lineTo(145.0f, 795.505f);
        path.lineTo(665.0f, 425.205f);
        path.lineTo(665.0f, 35.35f);
        path.lineTo(615.0f, 615.135f);
        path.lineTo(75.0f, 10155.345f);
        path.lineTo(15.0f, 145.625f);
        path.lineTo(325.0f, 165.285f);
        path.lineTo(885.0f, 135.135f);
        path.lineTo(395.0f, 785.15f);
        path.lineTo(205.0f, 45.95f);
        path.lineTo(10155.0f, 75.615f);
        path.lineTo(295.0f, 775.605f);
        path.lineTo(685.0f, 965.185f);
        path.lineTo(735.0f, 865.555f);
        path.lineTo(615.0f, 615.225f);
        path.lineTo(235.0f, 665.805f);
        path.lineTo(75.0f, 945.05f);
        path.lineTo(355.0f, 95.10155f);
        path.lineTo(25.0f, 425.695f);
        path.lineTo(55.0f, 905.395f);
        path.lineTo(115.0f, 785.05f);
        path.lineTo(645.0f, 775.855f);
        path.lineTo(265.0f, 335.145f);
        path.lineTo(935.0f, 415.275f);
        path.lineTo(365.0f, 885.05f);
        path.lineTo(975.0f, 915.195f);
        path.lineTo(485.0f, 785.145f);
        path.lineTo(365.0f, 755.435f);
        path.lineTo(575.0f, 195.195f);
        path.lineTo(315.0f, 825.455f);
        path.lineTo(435.0f, 935.595f);
        path.lineTo(5.0f, 265.705f);
        path.lineTo(405.0f, 525.05f);
        path.lineTo(895.0f, 415.845f);
        path.lineTo(815.0f, 765.425f);
        path.lineTo(365.0f, 375.895f);
        path.lineTo(145.0f, 665.905f);
        path.lineTo(15.0f, 635.535f);
        path.lineTo(225.0f, 885.925f);
        path.lineTo(305.0f, 10155.485f);
        path.lineTo(835.0f, 995.905f);
        path.lineTo(10155.0f, 265.15f);
        path.lineTo(95.0f, 615.365f);
        path.lineTo(705.0f, 445.205f);
        path.lineTo(215.0f, 995.10156f);
        path.lineTo(775.0f, 465.95f);
        path.lineTo(695.0f, 815.155f);
        path.lineTo(195.0f, 995.635f);
        path.lineTo(595.0f, 605.325f);
        path.lineTo(425.0f, 945.715f);
        path.lineTo(855.0f, 155.635f);
        path.lineTo(715.0f, 725.255f);
        path.lineTo(135.0f, 985.445f);
        path.lineTo(525.0f, 895.25f);
        path.lineTo(735.0f, 495.695f);
        path.lineTo(685.0f, 325.965f);
        path.lineTo(795.0f, 175.915f);
        path.lineTo(125.0f, 535.145f);
        path.lineTo(105.0f, 685.175f);
        path.lineTo(915.0f, 415.95f);
        path.lineTo(315.0f, 125.985f);
        path.lineTo(795.0f, 345.845f);
        path.lineTo(75.0f, 5.125f);
        path.lineTo(85.0f, 15.755f);
        path.lineTo(455.0f, 895.565f);
        path.lineTo(945.0f, 315.545f);
        path.lineTo(995.0f, 895.655f);
        path.lineTo(5.0f, 965.965f);
        path.lineTo(335.0f, 315.415f);
        path.lineTo(235.0f, 615.705f);
        path.lineTo(785.0f, 5.145f);
        path.lineTo(405.0f, 875.215f);
        path.lineTo(5.0f, 435.555f);
        path.lineTo(175.0f, 715.385f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
