package com.smartapp.b.ctkeqqoyzj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Pbsanyyknnj extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pbsanyyknnj() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(955.0f, 15.975f);
        path.lineTo(545.0f, 385.195f);
        path.lineTo(265.0f, 815.425f);
        path.lineTo(935.0f, 845.75f);
        path.lineTo(205.0f, 795.455f);
        path.lineTo(815.0f, 655.295f);
        path.lineTo(145.0f, 475.845f);
        path.lineTo(655.0f, 545.165f);
        path.lineTo(455.0f, 625.555f);
        path.lineTo(755.0f, 335.185f);
        path.lineTo(5.0f, 685.285f);
        path.lineTo(195.0f, 785.305f);
        path.lineTo(765.0f, 835.645f);
        path.lineTo(315.0f, 605.375f);
        path.lineTo(265.0f, 315.55f);
        path.lineTo(455.0f, 525.595f);
        path.lineTo(645.0f, 955.365f);
        path.lineTo(15.0f, 275.175f);
        path.lineTo(825.0f, 845.465f);
        path.lineTo(755.0f, 655.15f);
        path.lineTo(235.0f, 325.285f);
        path.lineTo(135.0f, 695.315f);
        path.lineTo(755.0f, 835.855f);
        path.lineTo(235.0f, 355.265f);
        path.lineTo(115.0f, 335.165f);
        path.lineTo(845.0f, 955.35f);
        path.lineTo(825.0f, 515.345f);
        path.lineTo(755.0f, 405.415f);
        path.lineTo(475.0f, 145.105f);
        path.lineTo(715.0f, 965.315f);
        path.lineTo(975.0f, 665.265f);
        path.lineTo(475.0f, 15.655f);
        path.lineTo(685.0f, 785.985f);
        path.lineTo(875.0f, 335.625f);
        path.lineTo(965.0f, 265.845f);
        path.lineTo(415.0f, 65.25f);
        path.lineTo(625.0f, 495.715f);
        path.lineTo(325.0f, 35.315f);
        path.lineTo(5.0f, 875.95f);
        path.lineTo(365.0f, 855.805f);
        path.lineTo(105.0f, 845.105f);
        path.lineTo(185.0f, 765.615f);
        path.lineTo(945.0f, 845.165f);
        path.lineTo(205.0f, 5.365f);
        path.lineTo(975.0f, 925.235f);
        path.lineTo(185.0f, 395.645f);
        path.lineTo(565.0f, 655.05f);
        path.lineTo(925.0f, 225.635f);
        path.lineTo(985.0f, 485.185f);
        path.lineTo(635.0f, 605.35f);
        path.lineTo(205.0f, 5.955f);
        path.lineTo(505.0f, 545.855f);
        path.lineTo(815.0f, 185.05f);
        path.lineTo(595.0f, 615.135f);
        path.lineTo(465.0f, 5.365f);
        path.lineTo(695.0f, 35.625f);
        path.lineTo(645.0f, 595.315f);
        path.lineTo(505.0f, 275.955f);
        path.lineTo(995.0f, 395.375f);
        path.lineTo(495.0f, 5.335f);
        path.lineTo(925.0f, 5.865f);
        path.lineTo(905.0f, 775.915f);
        path.lineTo(725.0f, 35.115f);
        path.lineTo(205.0f, 435.105f);
        path.lineTo(785.0f, 575.635f);
        path.lineTo(335.0f, 825.825f);
        path.lineTo(375.0f, 275.915f);
        path.lineTo(65.0f, 625.795f);
        path.lineTo(435.0f, 505.445f);
        path.lineTo(335.0f, 745.365f);
        path.lineTo(565.0f, 735.625f);
        path.lineTo(505.0f, 135.35f);
        path.lineTo(705.0f, 855.255f);
        path.lineTo(365.0f, 95.255f);
        path.lineTo(385.0f, 45.535f);
        path.lineTo(475.0f, 675.775f);
        path.lineTo(605.0f, 705.805f);
        path.lineTo(895.0f, 125.415f);
        path.lineTo(645.0f, 535.755f);
        path.lineTo(65.0f, 675.565f);
        path.lineTo(195.0f, 435.855f);
        path.lineTo(755.0f, 615.855f);
        path.lineTo(825.0f, 25.955f);
        path.lineTo(655.0f, 555.55f);
        path.lineTo(445.0f, 5.425f);
        path.lineTo(575.0f, 445.775f);
        path.lineTo(65.0f, 225.25f);
        path.lineTo(145.0f, 695.875f);
        path.lineTo(145.0f, 475.125f);
        path.lineTo(965.0f, 755.735f);
        path.lineTo(135.0f, 935.105f);
        path.lineTo(555.0f, 685.555f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1013.0f, this.bounds.height() / 1013.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
