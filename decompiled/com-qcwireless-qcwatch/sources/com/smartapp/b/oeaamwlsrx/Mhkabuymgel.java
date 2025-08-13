package com.smartapp.b.oeaamwlsrx;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Mhkabuymgel extends ShapeDrawable {
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

    public Mhkabuymgel() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(775.0f, 25.795f);
        path.lineTo(595.0f, 805.825f);
        path.lineTo(365.0f, 875.115f);
        path.lineTo(665.0f, 985.295f);
        path.lineTo(735.0f, 635.125f);
        path.lineTo(945.0f, 565.415f);
        path.lineTo(745.0f, 85.905f);
        path.lineTo(835.0f, 345.10086f);
        path.lineTo(165.0f, 35.145f);
        path.lineTo(895.0f, 5.595f);
        path.lineTo(595.0f, 215.295f);
        path.lineTo(105.0f, 755.735f);
        path.lineTo(615.0f, 975.315f);
        path.lineTo(695.0f, 955.405f);
        path.lineTo(665.0f, 995.315f);
        path.lineTo(575.0f, 745.285f);
        path.lineTo(515.0f, 15.325f);
        path.lineTo(735.0f, 475.195f);
        path.lineTo(925.0f, 275.15f);
        path.lineTo(715.0f, 805.105f);
        path.lineTo(805.0f, 595.225f);
        path.lineTo(765.0f, 325.285f);
        path.lineTo(135.0f, 145.875f);
        path.lineTo(475.0f, 795.185f);
        path.lineTo(65.0f, 875.955f);
        path.lineTo(715.0f, 985.85f);
        path.lineTo(855.0f, 105.465f);
        path.lineTo(275.0f, 805.25f);
        path.lineTo(355.0f, 405.845f);
        path.lineTo(945.0f, 515.465f);
        path.lineTo(765.0f, 365.915f);
        path.lineTo(375.0f, 525.825f);
        path.lineTo(725.0f, 785.585f);
        path.lineTo(255.0f, 415.685f);
        path.lineTo(725.0f, 55.725f);
        path.lineTo(65.0f, 385.835f);
        path.lineTo(25.0f, 115.755f);
        path.lineTo(685.0f, 975.415f);
        path.lineTo(955.0f, 595.415f);
        path.lineTo(155.0f, 835.115f);
        path.lineTo(515.0f, 625.375f);
        path.lineTo(875.0f, 545.545f);
        path.lineTo(735.0f, 15.315f);
        path.lineTo(425.0f, 295.745f);
        path.lineTo(685.0f, 55.505f);
        path.lineTo(35.0f, 855.15f);
        path.lineTo(215.0f, 505.895f);
        path.lineTo(825.0f, 345.535f);
        path.lineTo(645.0f, 855.875f);
        path.lineTo(815.0f, 225.75f);
        path.lineTo(415.0f, 885.355f);
        path.lineTo(135.0f, 475.315f);
        path.lineTo(55.0f, 975.275f);
        path.lineTo(985.0f, 885.745f);
        path.lineTo(75.0f, 375.485f);
        path.lineTo(465.0f, 95.885f);
        path.lineTo(185.0f, 205.765f);
        path.lineTo(135.0f, 655.185f);
        path.lineTo(675.0f, 65.605f);
        path.lineTo(545.0f, 365.795f);
        path.lineTo(695.0f, 495.465f);
        path.lineTo(775.0f, 45.535f);
        path.lineTo(555.0f, 15.675f);
        path.lineTo(755.0f, 535.365f);
        path.lineTo(515.0f, 655.595f);
        path.lineTo(115.0f, 925.905f);
        path.lineTo(935.0f, 205.375f);
        path.lineTo(85.0f, 945.275f);
        path.lineTo(785.0f, 715.955f);
        path.lineTo(45.0f, 955.795f);
        path.lineTo(485.0f, 15.525f);
        path.lineTo(365.0f, 575.685f);
        path.lineTo(395.0f, 845.645f);
        path.lineTo(845.0f, 495.355f);
        path.lineTo(395.0f, 735.85f);
        path.lineTo(425.0f, 105.475f);
        path.lineTo(265.0f, 775.145f);
        path.lineTo(105.0f, 10085.995f);
        path.lineTo(595.0f, 365.185f);
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
