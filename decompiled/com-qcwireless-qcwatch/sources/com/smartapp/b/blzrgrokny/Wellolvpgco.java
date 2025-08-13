package com.smartapp.b.blzrgrokny;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Wellolvpgco extends ShapeDrawable {
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

    public Wellolvpgco() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(585.0f, 945.55f);
        path.lineTo(115.0f, 335.225f);
        path.lineTo(935.0f, 925.415f);
        path.lineTo(115.0f, 975.185f);
        path.lineTo(495.0f, 205.335f);
        path.lineTo(335.0f, 475.605f);
        path.lineTo(415.0f, 505.495f);
        path.lineTo(275.0f, 465.855f);
        path.lineTo(115.0f, 625.565f);
        path.lineTo(905.0f, 10035.215f);
        path.lineTo(315.0f, 615.235f);
        path.lineTo(795.0f, 805.525f);
        path.lineTo(375.0f, 505.465f);
        path.lineTo(995.0f, 45.615f);
        path.lineTo(955.0f, 545.305f);
        path.lineTo(35.0f, 895.905f);
        path.lineTo(365.0f, 295.275f);
        path.lineTo(575.0f, 465.475f);
        path.lineTo(855.0f, 65.665f);
        path.lineTo(895.0f, 355.185f);
        path.lineTo(175.0f, 215.355f);
        path.lineTo(655.0f, 815.785f);
        path.lineTo(775.0f, 925.775f);
        path.lineTo(985.0f, 485.105f);
        path.lineTo(225.0f, 895.155f);
        path.lineTo(115.0f, 525.675f);
        path.lineTo(305.0f, 975.135f);
        path.lineTo(615.0f, 165.755f);
        path.lineTo(785.0f, 855.115f);
        path.lineTo(335.0f, 375.35f);
        path.lineTo(425.0f, 575.105f);
        path.lineTo(365.0f, 265.165f);
        path.lineTo(105.0f, 85.605f);
        path.lineTo(205.0f, 855.205f);
        path.lineTo(595.0f, 865.115f);
        path.lineTo(875.0f, 835.255f);
        path.lineTo(85.0f, 585.535f);
        path.lineTo(535.0f, 505.325f);
        path.lineTo(375.0f, 715.685f);
        path.lineTo(835.0f, 475.625f);
        path.lineTo(165.0f, 935.75f);
        path.lineTo(475.0f, 975.675f);
        path.lineTo(15.0f, 345.10034f);
        path.lineTo(685.0f, 925.965f);
        path.lineTo(255.0f, 515.825f);
        path.lineTo(215.0f, 875.465f);
        path.lineTo(575.0f, 995.685f);
        path.lineTo(755.0f, 975.705f);
        path.lineTo(625.0f, 735.595f);
        path.lineTo(565.0f, 85.685f);
        path.lineTo(705.0f, 375.655f);
        path.lineTo(545.0f, 605.355f);
        path.lineTo(325.0f, 505.195f);
        path.lineTo(995.0f, 555.355f);
        path.lineTo(835.0f, 165.465f);
        path.lineTo(355.0f, 135.535f);
        path.lineTo(575.0f, 345.625f);
        path.lineTo(515.0f, 825.305f);
        path.lineTo(795.0f, 655.385f);
        path.lineTo(45.0f, 525.915f);
        path.lineTo(905.0f, 405.475f);
        path.lineTo(715.0f, 785.755f);
        path.lineTo(505.0f, 65.95f);
        path.lineTo(985.0f, 895.485f);
        path.lineTo(655.0f, 165.995f);
        path.lineTo(315.0f, 25.10035f);
        path.lineTo(135.0f, 945.385f);
        path.lineTo(535.0f, 945.435f);
        path.lineTo(255.0f, 825.145f);
        path.lineTo(415.0f, 625.585f);
        path.lineTo(415.0f, 645.465f);
        path.lineTo(475.0f, 425.715f);
        path.lineTo(195.0f, 355.935f);
        path.lineTo(405.0f, 705.415f);
        path.lineTo(345.0f, 645.515f);
        path.lineTo(525.0f, 305.985f);
        path.lineTo(315.0f, 185.495f);
        path.lineTo(505.0f, 895.125f);
        path.lineTo(215.0f, 685.685f);
        path.lineTo(215.0f, 665.745f);
        path.lineTo(895.0f, 835.545f);
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
