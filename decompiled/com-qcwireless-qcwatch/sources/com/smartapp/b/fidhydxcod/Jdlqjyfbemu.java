package com.smartapp.b.fidhydxcod;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Jdlqjyfbemu extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Jdlqjyfbemu() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(65.0f, 235.325f);
        path.lineTo(645.0f, 305.25f);
        path.lineTo(335.0f, 225.155f);
        path.lineTo(835.0f, 515.745f);
        path.lineTo(945.0f, 235.835f);
        path.lineTo(345.0f, 685.105f);
        path.lineTo(525.0f, 605.175f);
        path.lineTo(615.0f, 465.865f);
        path.lineTo(335.0f, 785.625f);
        path.lineTo(305.0f, 275.795f);
        path.lineTo(545.0f, 925.635f);
        path.lineTo(285.0f, 105.675f);
        path.lineTo(905.0f, 155.915f);
        path.lineTo(425.0f, 105.375f);
        path.lineTo(855.0f, 975.175f);
        path.lineTo(605.0f, 305.815f);
        path.lineTo(445.0f, 125.535f);
        path.lineTo(135.0f, 875.275f);
        path.lineTo(585.0f, 545.785f);
        path.lineTo(565.0f, 635.225f);
        path.lineTo(485.0f, 75.725f);
        path.lineTo(535.0f, 55.445f);
        path.lineTo(355.0f, 925.595f);
        path.lineTo(345.0f, 865.155f);
        path.lineTo(585.0f, 285.35f);
        path.lineTo(145.0f, 775.205f);
        path.lineTo(515.0f, 375.495f);
        path.lineTo(475.0f, 845.145f);
        path.lineTo(85.0f, 175.85f);
        path.lineTo(85.0f, 105.495f);
        path.lineTo(785.0f, 905.445f);
        path.lineTo(205.0f, 565.565f);
        path.lineTo(715.0f, 75.445f);
        path.lineTo(665.0f, 135.45f);
        path.lineTo(405.0f, 125.205f);
        path.lineTo(645.0f, 135.145f);
        path.lineTo(305.0f, 315.205f);
        path.lineTo(345.0f, 605.875f);
        path.lineTo(645.0f, 195.285f);
        path.lineTo(305.0f, 165.855f);
        path.lineTo(465.0f, 725.45f);
        path.lineTo(835.0f, 725.865f);
        path.lineTo(595.0f, 565.775f);
        path.lineTo(385.0f, 445.985f);
        path.lineTo(165.0f, 915.515f);
        path.lineTo(695.0f, 825.605f);
        path.lineTo(5.0f, 415.735f);
        path.lineTo(885.0f, 215.445f);
        path.lineTo(495.0f, 135.295f);
        path.lineTo(195.0f, 665.525f);
        path.lineTo(215.0f, 195.845f);
        path.lineTo(375.0f, 85.125f);
        path.lineTo(715.0f, 735.965f);
        path.lineTo(305.0f, 505.205f);
        path.lineTo(115.0f, 10165.185f);
        path.lineTo(125.0f, 695.665f);
        path.lineTo(55.0f, 325.345f);
        path.lineTo(225.0f, 265.55f);
        path.lineTo(285.0f, 755.575f);
        path.lineTo(455.0f, 795.515f);
        path.lineTo(785.0f, 775.255f);
        path.lineTo(605.0f, 385.775f);
        path.lineTo(195.0f, 745.345f);
        path.lineTo(205.0f, 655.875f);
        path.lineTo(965.0f, 345.105f);
        path.lineTo(845.0f, 65.835f);
        path.lineTo(105.0f, 555.105f);
        path.lineTo(805.0f, 215.545f);
        path.lineTo(765.0f, 895.675f);
        path.lineTo(525.0f, 225.815f);
        path.lineTo(755.0f, 515.55f);
        path.lineTo(475.0f, 185.875f);
        path.lineTo(785.0f, 445.675f);
        path.lineTo(575.0f, 925.415f);
        path.lineTo(485.0f, 565.905f);
        path.lineTo(235.0f, 685.615f);
        path.lineTo(985.0f, 125.165f);
        path.lineTo(455.0f, 795.295f);
        path.lineTo(735.0f, 215.405f);
        path.lineTo(635.0f, 955.935f);
        path.lineTo(295.0f, 485.965f);
        path.lineTo(275.0f, 235.965f);
        path.lineTo(375.0f, 525.65f);
        path.lineTo(885.0f, 385.405f);
        path.lineTo(825.0f, 985.665f);
        path.lineTo(315.0f, 485.115f);
        path.lineTo(95.0f, 355.625f);
        path.lineTo(585.0f, 415.425f);
        path.lineTo(625.0f, 475.405f);
        path.lineTo(425.0f, 615.645f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
