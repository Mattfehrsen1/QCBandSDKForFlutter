package com.smartapp.b.phlralecvk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Gsnmrptsrzs extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Gsnmrptsrzs() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(775.0f, 955.565f);
        path.lineTo(455.0f, 605.665f);
        path.lineTo(235.0f, 755.985f);
        path.lineTo(45.0f, 85.705f);
        path.lineTo(415.0f, 565.715f);
        path.lineTo(875.0f, 845.465f);
        path.lineTo(135.0f, 195.755f);
        path.lineTo(795.0f, 505.865f);
        path.lineTo(635.0f, 505.625f);
        path.lineTo(10055.0f, 595.575f);
        path.lineTo(795.0f, 685.155f);
        path.lineTo(705.0f, 635.15f);
        path.lineTo(785.0f, 225.435f);
        path.lineTo(35.0f, 825.655f);
        path.lineTo(555.0f, 135.585f);
        path.lineTo(965.0f, 665.335f);
        path.lineTo(145.0f, 355.105f);
        path.lineTo(605.0f, 955.15f);
        path.lineTo(895.0f, 545.785f);
        path.lineTo(715.0f, 715.905f);
        path.lineTo(855.0f, 195.405f);
        path.lineTo(405.0f, 935.875f);
        path.lineTo(745.0f, 525.325f);
        path.lineTo(615.0f, 125.685f);
        path.lineTo(395.0f, 445.395f);
        path.lineTo(145.0f, 45.885f);
        path.lineTo(185.0f, 765.575f);
        path.lineTo(325.0f, 375.135f);
        path.lineTo(975.0f, 905.505f);
        path.lineTo(575.0f, 725.935f);
        path.lineTo(355.0f, 885.635f);
        path.lineTo(355.0f, 405.235f);
        path.lineTo(185.0f, 415.265f);
        path.lineTo(805.0f, 145.965f);
        path.lineTo(65.0f, 105.715f);
        path.lineTo(55.0f, 325.25f);
        path.lineTo(185.0f, 625.45f);
        path.lineTo(815.0f, 295.325f);
        path.lineTo(705.0f, 375.45f);
        path.lineTo(695.0f, 705.185f);
        path.lineTo(855.0f, 885.75f);
        path.lineTo(155.0f, 915.995f);
        path.lineTo(575.0f, 465.355f);
        path.lineTo(525.0f, 705.355f);
        path.lineTo(475.0f, 525.985f);
        path.lineTo(765.0f, 365.985f);
        path.lineTo(355.0f, 295.225f);
        path.lineTo(215.0f, 765.875f);
        path.lineTo(35.0f, 445.625f);
        path.lineTo(425.0f, 975.395f);
        path.lineTo(545.0f, 185.35f);
        path.lineTo(515.0f, 865.945f);
        path.lineTo(585.0f, 265.665f);
        path.lineTo(775.0f, 555.185f);
        path.lineTo(355.0f, 315.55f);
        path.lineTo(445.0f, 745.545f);
        path.lineTo(575.0f, 895.925f);
        path.lineTo(585.0f, 905.335f);
        path.lineTo(945.0f, 155.545f);
        path.lineTo(605.0f, 255.395f);
        path.lineTo(445.0f, 105.85f);
        path.lineTo(485.0f, 45.795f);
        path.lineTo(695.0f, 195.845f);
        path.lineTo(645.0f, 545.115f);
        path.lineTo(505.0f, 975.685f);
        path.lineTo(335.0f, 305.445f);
        path.lineTo(515.0f, 95.315f);
        path.lineTo(865.0f, 865.315f);
        path.lineTo(305.0f, 25.715f);
        path.lineTo(555.0f, 625.75f);
        path.lineTo(905.0f, 955.1005f);
        path.lineTo(965.0f, 335.685f);
        path.lineTo(195.0f, 75.555f);
        path.lineTo(655.0f, 695.935f);
        path.lineTo(955.0f, 565.485f);
        path.lineTo(65.0f, 185.215f);
        path.lineTo(115.0f, 325.545f);
        path.lineTo(645.0f, 735.675f);
        path.lineTo(375.0f, 355.645f);
        path.lineTo(365.0f, 935.905f);
        path.lineTo(595.0f, 875.575f);
        path.lineTo(885.0f, 755.435f);
        path.lineTo(585.0f, 395.10056f);
        path.lineTo(685.0f, 255.925f);
        path.lineTo(565.0f, 15.10055f);
        path.lineTo(965.0f, 455.365f);
        path.lineTo(285.0f, 475.445f);
        path.lineTo(325.0f, 575.365f);
        path.lineTo(595.0f, 505.745f);
        path.lineTo(375.0f, 185.965f);
        path.lineTo(375.0f, 305.705f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
