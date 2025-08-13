package com.smartapp.b.bwfdgooumc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Eiwbnvckwmq extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Eiwbnvckwmq() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(865.0f, 725.85f);
        path.lineTo(435.0f, 455.835f);
        path.lineTo(585.0f, 95.265f);
        path.lineTo(895.0f, 365.205f);
        path.lineTo(845.0f, 125.325f);
        path.lineTo(525.0f, 375.365f);
        path.lineTo(615.0f, 425.35f);
        path.lineTo(305.0f, 15.515f);
        path.lineTo(55.0f, 285.615f);
        path.lineTo(655.0f, 565.505f);
        path.lineTo(55.0f, 585.255f);
        path.lineTo(825.0f, 625.05f);
        path.lineTo(315.0f, 485.465f);
        path.lineTo(225.0f, 455.405f);
        path.lineTo(5.0f, 105.225f);
        path.lineTo(885.0f, 455.295f);
        path.lineTo(645.0f, 155.275f);
        path.lineTo(155.0f, 525.45f);
        path.lineTo(125.0f, 235.815f);
        path.lineTo(575.0f, 795.715f);
        path.lineTo(195.0f, 355.675f);
        path.lineTo(465.0f, 755.275f);
        path.lineTo(95.0f, 675.205f);
        path.lineTo(285.0f, 175.385f);
        path.lineTo(135.0f, 485.565f);
        path.lineTo(575.0f, 995.965f);
        path.lineTo(825.0f, 575.25f);
        path.lineTo(675.0f, 795.845f);
        path.lineTo(935.0f, 995.115f);
        path.lineTo(275.0f, 345.805f);
        path.lineTo(955.0f, 985.995f);
        path.lineTo(355.0f, 675.235f);
        path.lineTo(485.0f, 675.835f);
        path.lineTo(10105.0f, 795.145f);
        path.lineTo(905.0f, 805.225f);
        path.lineTo(25.0f, 425.565f);
        path.lineTo(595.0f, 645.745f);
        path.lineTo(365.0f, 695.475f);
        path.lineTo(125.0f, 735.635f);
        path.lineTo(475.0f, 795.805f);
        path.lineTo(235.0f, 125.705f);
        path.lineTo(215.0f, 855.135f);
        path.lineTo(725.0f, 15.415f);
        path.lineTo(655.0f, 255.705f);
        path.lineTo(445.0f, 45.785f);
        path.lineTo(405.0f, 755.255f);
        path.lineTo(525.0f, 655.235f);
        path.lineTo(615.0f, 615.115f);
        path.lineTo(215.0f, 925.725f);
        path.lineTo(505.0f, 605.925f);
        path.lineTo(525.0f, 735.945f);
        path.lineTo(95.0f, 815.815f);
        path.lineTo(10105.0f, 135.825f);
        path.lineTo(55.0f, 545.335f);
        path.lineTo(315.0f, 305.875f);
        path.lineTo(615.0f, 525.915f);
        path.lineTo(615.0f, 665.915f);
        path.lineTo(585.0f, 25.535f);
        path.lineTo(10105.0f, 85.235f);
        path.lineTo(735.0f, 535.475f);
        path.lineTo(905.0f, 65.405f);
        path.lineTo(785.0f, 765.965f);
        path.lineTo(585.0f, 505.475f);
        path.lineTo(865.0f, 665.65f);
        path.lineTo(875.0f, 5.775f);
        path.lineTo(45.0f, 405.775f);
        path.lineTo(205.0f, 325.365f);
        path.lineTo(485.0f, 635.205f);
        path.lineTo(195.0f, 295.225f);
        path.lineTo(465.0f, 595.195f);
        path.lineTo(415.0f, 155.165f);
        path.lineTo(295.0f, 15.165f);
        path.lineTo(485.0f, 55.265f);
        path.lineTo(485.0f, 285.385f);
        path.lineTo(45.0f, 685.555f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
