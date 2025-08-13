package com.smartapp.b.caoolastbk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Jiepovpikjc extends ShapeDrawable {
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

    public Jiepovpikjc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(35.0f, 615.685f);
        path.lineTo(85.0f, 595.845f);
        path.lineTo(265.0f, 815.25f);
        path.lineTo(865.0f, 335.855f);
        path.lineTo(725.0f, 535.835f);
        path.lineTo(65.0f, 465.565f);
        path.lineTo(365.0f, 445.75f);
        path.lineTo(845.0f, 355.535f);
        path.lineTo(865.0f, 425.225f);
        path.lineTo(95.0f, 575.965f);
        path.lineTo(135.0f, 665.745f);
        path.lineTo(175.0f, 15.115f);
        path.lineTo(825.0f, 435.785f);
        path.lineTo(10015.0f, 225.685f);
        path.lineTo(235.0f, 835.25f);
        path.lineTo(775.0f, 865.315f);
        path.lineTo(185.0f, 285.95f);
        path.lineTo(435.0f, 365.605f);
        path.lineTo(185.0f, 55.285f);
        path.lineTo(675.0f, 575.965f);
        path.lineTo(835.0f, 845.235f);
        path.lineTo(315.0f, 915.865f);
        path.lineTo(355.0f, 135.645f);
        path.lineTo(905.0f, 225.545f);
        path.lineTo(395.0f, 295.115f);
        path.lineTo(615.0f, 685.145f);
        path.lineTo(945.0f, 575.905f);
        path.lineTo(195.0f, 575.855f);
        path.lineTo(725.0f, 545.15f);
        path.lineTo(835.0f, 525.215f);
        path.lineTo(395.0f, 765.755f);
        path.lineTo(315.0f, 25.275f);
        path.lineTo(965.0f, 95.905f);
        path.lineTo(965.0f, 25.975f);
        path.lineTo(565.0f, 105.85f);
        path.lineTo(5.0f, 325.135f);
        path.lineTo(965.0f, 645.575f);
        path.lineTo(805.0f, 405.875f);
        path.lineTo(795.0f, 435.215f);
        path.lineTo(235.0f, 75.685f);
        path.lineTo(755.0f, 515.485f);
        path.lineTo(215.0f, 315.655f);
        path.lineTo(935.0f, 275.405f);
        path.lineTo(625.0f, 495.65f);
        path.lineTo(395.0f, 765.745f);
        path.lineTo(335.0f, 265.875f);
        path.lineTo(395.0f, 685.45f);
        path.lineTo(635.0f, 695.655f);
        path.lineTo(595.0f, 585.645f);
        path.lineTo(455.0f, 165.215f);
        path.lineTo(595.0f, 765.405f);
        path.lineTo(425.0f, 975.355f);
        path.lineTo(55.0f, 185.85f);
        path.lineTo(745.0f, 955.925f);
        path.lineTo(805.0f, 695.935f);
        path.lineTo(195.0f, 25.935f);
        path.lineTo(395.0f, 485.315f);
        path.lineTo(985.0f, 695.565f);
        path.lineTo(685.0f, 995.125f);
        path.lineTo(565.0f, 115.945f);
        path.lineTo(695.0f, 65.675f);
        path.lineTo(55.0f, 205.345f);
        path.lineTo(585.0f, 305.585f);
        path.lineTo(315.0f, 155.365f);
        path.lineTo(695.0f, 725.885f);
        path.lineTo(155.0f, 585.465f);
        path.lineTo(115.0f, 825.215f);
        path.lineTo(235.0f, 175.275f);
        path.lineTo(395.0f, 215.485f);
        path.lineTo(835.0f, 455.355f);
        path.lineTo(735.0f, 485.445f);
        path.lineTo(655.0f, 105.615f);
        path.lineTo(615.0f, 145.905f);
        path.lineTo(545.0f, 455.345f);
        path.lineTo(25.0f, 25.275f);
        path.lineTo(185.0f, 935.145f);
        path.lineTo(595.0f, 505.205f);
        path.lineTo(635.0f, 605.295f);
        path.lineTo(165.0f, 275.305f);
        path.lineTo(765.0f, 875.965f);
        path.lineTo(405.0f, 15.735f);
        path.lineTo(665.0f, 805.625f);
        path.lineTo(355.0f, 325.855f);
        path.lineTo(75.0f, 95.115f);
        path.lineTo(765.0f, 655.545f);
        path.lineTo(35.0f, 285.935f);
        path.lineTo(905.0f, 515.405f);
        path.lineTo(295.0f, 875.685f);
        path.lineTo(65.0f, 265.535f);
        path.lineTo(455.0f, 735.845f);
        path.lineTo(915.0f, 385.765f);
        path.lineTo(665.0f, 325.835f);
        path.lineTo(145.0f, 185.165f);
        path.lineTo(735.0f, 875.415f);
        path.lineTo(615.0f, 565.655f);
        path.lineTo(435.0f, 775.85f);
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
