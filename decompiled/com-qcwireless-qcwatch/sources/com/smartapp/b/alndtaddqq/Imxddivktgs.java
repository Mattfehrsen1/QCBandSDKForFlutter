package com.smartapp.b.alndtaddqq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Imxddivktgs extends ShapeDrawable {
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

    public Imxddivktgs() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(145.0f, 375.185f);
        path.lineTo(145.0f, 915.235f);
        path.lineTo(275.0f, 125.355f);
        path.lineTo(825.0f, 235.635f);
        path.lineTo(425.0f, 615.985f);
        path.lineTo(185.0f, 475.875f);
        path.lineTo(35.0f, 425.485f);
        path.lineTo(965.0f, 975.305f);
        path.lineTo(965.0f, 195.165f);
        path.lineTo(325.0f, 325.825f);
        path.lineTo(825.0f, 945.525f);
        path.lineTo(475.0f, 185.335f);
        path.lineTo(735.0f, 205.555f);
        path.lineTo(435.0f, 905.95f);
        path.lineTo(825.0f, 765.805f);
        path.lineTo(825.0f, 785.345f);
        path.lineTo(845.0f, 205.885f);
        path.lineTo(975.0f, 515.195f);
        path.lineTo(85.0f, 935.485f);
        path.lineTo(785.0f, 235.615f);
        path.lineTo(145.0f, 305.475f);
        path.lineTo(345.0f, 365.10144f);
        path.lineTo(375.0f, 865.685f);
        path.lineTo(375.0f, 105.995f);
        path.lineTo(445.0f, 975.865f);
        path.lineTo(785.0f, 845.15f);
        path.lineTo(385.0f, 805.45f);
        path.lineTo(155.0f, 45.315f);
        path.lineTo(175.0f, 555.45f);
        path.lineTo(405.0f, 135.805f);
        path.lineTo(535.0f, 585.285f);
        path.lineTo(575.0f, 515.845f);
        path.lineTo(705.0f, 345.375f);
        path.lineTo(155.0f, 955.825f);
        path.lineTo(10145.0f, 735.255f);
        path.lineTo(985.0f, 675.545f);
        path.lineTo(335.0f, 185.95f);
        path.lineTo(175.0f, 895.05f);
        path.lineTo(55.0f, 895.505f);
        path.lineTo(985.0f, 205.115f);
        path.lineTo(265.0f, 485.275f);
        path.lineTo(315.0f, 575.635f);
        path.lineTo(315.0f, 105.105f);
        path.lineTo(315.0f, 975.495f);
        path.lineTo(665.0f, 325.45f);
        path.lineTo(615.0f, 845.195f);
        path.lineTo(705.0f, 365.275f);
        path.lineTo(355.0f, 5.575f);
        path.lineTo(15.0f, 505.895f);
        path.lineTo(575.0f, 895.845f);
        path.lineTo(585.0f, 485.335f);
        path.lineTo(885.0f, 675.685f);
        path.lineTo(65.0f, 655.265f);
        path.lineTo(5.0f, 125.755f);
        path.lineTo(465.0f, 855.655f);
        path.lineTo(625.0f, 595.865f);
        path.lineTo(995.0f, 865.455f);
        path.lineTo(135.0f, 355.425f);
        path.lineTo(715.0f, 955.945f);
        path.lineTo(145.0f, 315.655f);
        path.lineTo(745.0f, 205.275f);
        path.lineTo(225.0f, 685.65f);
        path.lineTo(745.0f, 85.65f);
        path.lineTo(105.0f, 225.475f);
        path.lineTo(125.0f, 765.345f);
        path.lineTo(775.0f, 335.405f);
        path.lineTo(775.0f, 655.85f);
        path.lineTo(375.0f, 105.395f);
        path.lineTo(205.0f, 585.215f);
        path.lineTo(125.0f, 865.45f);
        path.lineTo(475.0f, 435.625f);
        path.lineTo(815.0f, 385.995f);
        path.lineTo(975.0f, 315.855f);
        path.lineTo(215.0f, 215.145f);
        path.lineTo(925.0f, 625.465f);
        path.lineTo(725.0f, 875.475f);
        path.lineTo(345.0f, 315.335f);
        path.lineTo(535.0f, 335.375f);
        path.lineTo(115.0f, 275.915f);
        path.lineTo(635.0f, 755.995f);
        path.lineTo(545.0f, 685.855f);
        path.lineTo(615.0f, 485.545f);
        path.lineTo(335.0f, 515.465f);
        path.lineTo(355.0f, 475.865f);
        path.lineTo(605.0f, 375.315f);
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
