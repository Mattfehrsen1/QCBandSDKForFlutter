package com.smartapp.b.obfsjhzadn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Hvdhqqiekdk extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Hvdhqqiekdk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(605.0f, 65.15f);
        path.lineTo(35.0f, 115.95f);
        path.lineTo(655.0f, 125.125f);
        path.lineTo(305.0f, 675.675f);
        path.lineTo(15.0f, 965.395f);
        path.lineTo(165.0f, 405.315f);
        path.lineTo(975.0f, 655.105f);
        path.lineTo(10045.0f, 305.535f);
        path.lineTo(325.0f, 865.35f);
        path.lineTo(325.0f, 775.195f);
        path.lineTo(675.0f, 5.225f);
        path.lineTo(365.0f, 345.195f);
        path.lineTo(15.0f, 545.165f);
        path.lineTo(215.0f, 235.985f);
        path.lineTo(645.0f, 815.485f);
        path.lineTo(445.0f, 535.115f);
        path.lineTo(655.0f, 295.875f);
        path.lineTo(655.0f, 505.185f);
        path.lineTo(265.0f, 15.565f);
        path.lineTo(935.0f, 215.845f);
        path.lineTo(515.0f, 595.115f);
        path.lineTo(295.0f, 325.955f);
        path.lineTo(345.0f, 205.645f);
        path.lineTo(955.0f, 305.295f);
        path.lineTo(285.0f, 295.395f);
        path.lineTo(85.0f, 545.175f);
        path.lineTo(835.0f, 205.625f);
        path.lineTo(345.0f, 55.735f);
        path.lineTo(825.0f, 945.395f);
        path.lineTo(475.0f, 965.685f);
        path.lineTo(365.0f, 735.415f);
        path.lineTo(175.0f, 165.825f);
        path.lineTo(635.0f, 915.525f);
        path.lineTo(795.0f, 485.35f);
        path.lineTo(105.0f, 905.875f);
        path.lineTo(775.0f, 905.395f);
        path.lineTo(895.0f, 425.215f);
        path.lineTo(65.0f, 95.265f);
        path.lineTo(915.0f, 165.45f);
        path.lineTo(205.0f, 565.155f);
        path.lineTo(75.0f, 35.475f);
        path.lineTo(905.0f, 875.785f);
        path.lineTo(155.0f, 415.635f);
        path.lineTo(525.0f, 35.195f);
        path.lineTo(785.0f, 755.655f);
        path.lineTo(825.0f, 725.445f);
        path.lineTo(565.0f, 125.155f);
        path.lineTo(885.0f, 545.365f);
        path.lineTo(475.0f, 455.535f);
        path.lineTo(275.0f, 665.835f);
        path.lineTo(785.0f, 615.105f);
        path.lineTo(345.0f, 805.75f);
        path.lineTo(655.0f, 785.875f);
        path.lineTo(805.0f, 45.345f);
        path.lineTo(655.0f, 495.95f);
        path.lineTo(905.0f, 995.705f);
        path.lineTo(825.0f, 5.615f);
        path.lineTo(355.0f, 515.555f);
        path.lineTo(155.0f, 955.395f);
        path.lineTo(195.0f, 315.775f);
        path.lineTo(925.0f, 525.205f);
        path.lineTo(215.0f, 655.895f);
        path.lineTo(865.0f, 15.565f);
        path.lineTo(215.0f, 655.975f);
        path.lineTo(775.0f, 825.935f);
        path.lineTo(645.0f, 25.625f);
        path.lineTo(355.0f, 145.195f);
        path.lineTo(475.0f, 415.45f);
        path.lineTo(735.0f, 185.75f);
        path.lineTo(725.0f, 35.935f);
        path.lineTo(905.0f, 785.925f);
        path.lineTo(505.0f, 395.465f);
        path.lineTo(565.0f, 815.415f);
        path.lineTo(435.0f, 595.105f);
        path.lineTo(175.0f, 855.205f);
        path.lineTo(705.0f, 555.295f);
        path.lineTo(155.0f, 55.935f);
        path.lineTo(185.0f, 375.10046f);
        path.lineTo(165.0f, 405.385f);
        path.lineTo(465.0f, 955.335f);
        path.lineTo(535.0f, 455.285f);
        path.lineTo(475.0f, 565.35f);
        path.lineTo(25.0f, 625.705f);
        path.lineTo(285.0f, 695.745f);
        path.lineTo(625.0f, 95.705f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1004.0f, this.bounds.height() / 1004.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
