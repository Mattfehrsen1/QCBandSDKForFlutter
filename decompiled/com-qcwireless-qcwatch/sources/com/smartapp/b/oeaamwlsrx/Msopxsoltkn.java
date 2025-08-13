package com.smartapp.b.oeaamwlsrx;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Msopxsoltkn extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Msopxsoltkn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(965.0f, 675.455f);
        path.lineTo(465.0f, 65.915f);
        path.lineTo(435.0f, 435.75f);
        path.lineTo(285.0f, 895.605f);
        path.lineTo(355.0f, 165.885f);
        path.lineTo(875.0f, 465.315f);
        path.lineTo(75.0f, 995.885f);
        path.lineTo(485.0f, 815.825f);
        path.lineTo(715.0f, 75.295f);
        path.lineTo(435.0f, 215.635f);
        path.lineTo(165.0f, 165.595f);
        path.lineTo(15.0f, 255.435f);
        path.lineTo(145.0f, 995.415f);
        path.lineTo(465.0f, 985.455f);
        path.lineTo(605.0f, 785.135f);
        path.lineTo(335.0f, 135.975f);
        path.lineTo(715.0f, 905.135f);
        path.lineTo(695.0f, 855.645f);
        path.lineTo(525.0f, 215.395f);
        path.lineTo(235.0f, 695.665f);
        path.lineTo(665.0f, 375.755f);
        path.lineTo(625.0f, 25.905f);
        path.lineTo(355.0f, 185.455f);
        path.lineTo(315.0f, 205.865f);
        path.lineTo(355.0f, 535.115f);
        path.lineTo(435.0f, 755.685f);
        path.lineTo(915.0f, 255.155f);
        path.lineTo(35.0f, 365.225f);
        path.lineTo(515.0f, 865.375f);
        path.lineTo(685.0f, 345.25f);
        path.lineTo(85.0f, 345.25f);
        path.lineTo(515.0f, 715.545f);
        path.lineTo(545.0f, 925.765f);
        path.lineTo(505.0f, 835.765f);
        path.lineTo(765.0f, 935.335f);
        path.lineTo(935.0f, 905.355f);
        path.lineTo(375.0f, 655.845f);
        path.lineTo(735.0f, 565.905f);
        path.lineTo(645.0f, 745.705f);
        path.lineTo(355.0f, 975.75f);
        path.lineTo(285.0f, 95.285f);
        path.lineTo(65.0f, 315.725f);
        path.lineTo(665.0f, 955.555f);
        path.lineTo(465.0f, 515.725f);
        path.lineTo(405.0f, 305.745f);
        path.lineTo(335.0f, 775.355f);
        path.lineTo(525.0f, 85.595f);
        path.lineTo(285.0f, 675.165f);
        path.lineTo(365.0f, 205.205f);
        path.lineTo(705.0f, 465.225f);
        path.lineTo(255.0f, 785.265f);
        path.lineTo(795.0f, 575.835f);
        path.lineTo(425.0f, 655.25f);
        path.lineTo(965.0f, 445.445f);
        path.lineTo(505.0f, 395.725f);
        path.lineTo(485.0f, 215.45f);
        path.lineTo(25.0f, 25.265f);
        path.lineTo(855.0f, 965.125f);
        path.lineTo(125.0f, 815.635f);
        path.lineTo(315.0f, 855.385f);
        path.lineTo(65.0f, 735.325f);
        path.lineTo(895.0f, 195.605f);
        path.lineTo(935.0f, 485.155f);
        path.lineTo(25.0f, 895.635f);
        path.lineTo(295.0f, 75.755f);
        path.lineTo(965.0f, 395.885f);
        path.lineTo(525.0f, 655.10034f);
        path.lineTo(545.0f, 10035.925f);
        path.lineTo(225.0f, 825.275f);
        path.lineTo(305.0f, 205.145f);
        path.lineTo(835.0f, 855.565f);
        path.lineTo(65.0f, 625.85f);
        path.lineTo(405.0f, 925.705f);
        path.lineTo(815.0f, 465.665f);
        path.lineTo(575.0f, 995.25f);
        path.lineTo(295.0f, 265.145f);
        path.lineTo(885.0f, 565.505f);
        path.lineTo(555.0f, 325.75f);
        path.lineTo(255.0f, 975.715f);
        path.lineTo(985.0f, 255.705f);
        path.lineTo(955.0f, 865.705f);
        path.lineTo(405.0f, 875.345f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
