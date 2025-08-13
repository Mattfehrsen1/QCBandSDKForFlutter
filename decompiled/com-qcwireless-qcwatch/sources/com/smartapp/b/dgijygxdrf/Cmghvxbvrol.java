package com.smartapp.b.dgijygxdrf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Cmghvxbvrol extends ShapeDrawable {
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

    public Cmghvxbvrol() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(25.0f, 965.395f);
        path.lineTo(215.0f, 775.145f);
        path.lineTo(965.0f, 425.565f);
        path.lineTo(585.0f, 305.405f);
        path.lineTo(275.0f, 235.545f);
        path.lineTo(605.0f, 475.975f);
        path.lineTo(255.0f, 795.525f);
        path.lineTo(255.0f, 885.875f);
        path.lineTo(565.0f, 675.585f);
        path.lineTo(645.0f, 385.195f);
        path.lineTo(195.0f, 395.05f);
        path.lineTo(575.0f, 585.655f);
        path.lineTo(545.0f, 925.535f);
        path.lineTo(115.0f, 375.945f);
        path.lineTo(125.0f, 915.455f);
        path.lineTo(25.0f, 645.675f);
        path.lineTo(545.0f, 335.355f);
        path.lineTo(815.0f, 95.745f);
        path.lineTo(65.0f, 785.235f);
        path.lineTo(655.0f, 475.615f);
        path.lineTo(25.0f, 985.205f);
        path.lineTo(95.0f, 335.475f);
        path.lineTo(885.0f, 395.875f);
        path.lineTo(185.0f, 855.305f);
        path.lineTo(325.0f, 765.805f);
        path.lineTo(765.0f, 565.155f);
        path.lineTo(705.0f, 135.215f);
        path.lineTo(545.0f, 75.955f);
        path.lineTo(685.0f, 25.175f);
        path.lineTo(255.0f, 395.305f);
        path.lineTo(905.0f, 385.225f);
        path.lineTo(535.0f, 475.315f);
        path.lineTo(45.0f, 975.435f);
        path.lineTo(425.0f, 55.445f);
        path.lineTo(10105.0f, 665.745f);
        path.lineTo(25.0f, 695.585f);
        path.lineTo(455.0f, 95.675f);
        path.lineTo(975.0f, 925.135f);
        path.lineTo(175.0f, 845.375f);
        path.lineTo(165.0f, 55.685f);
        path.lineTo(645.0f, 145.855f);
        path.lineTo(365.0f, 135.75f);
        path.lineTo(925.0f, 345.85f);
        path.lineTo(145.0f, 545.465f);
        path.lineTo(75.0f, 25.175f);
        path.lineTo(315.0f, 315.335f);
        path.lineTo(355.0f, 315.475f);
        path.lineTo(965.0f, 175.05f);
        path.lineTo(315.0f, 935.905f);
        path.lineTo(605.0f, 415.445f);
        path.lineTo(655.0f, 615.25f);
        path.lineTo(575.0f, 595.695f);
        path.lineTo(355.0f, 565.595f);
        path.lineTo(705.0f, 425.995f);
        path.lineTo(455.0f, 955.615f);
        path.lineTo(735.0f, 225.425f);
        path.lineTo(915.0f, 365.25f);
        path.lineTo(745.0f, 675.295f);
        path.lineTo(145.0f, 735.425f);
        path.lineTo(805.0f, 725.515f);
        path.lineTo(35.0f, 105.65f);
        path.lineTo(475.0f, 535.955f);
        path.lineTo(605.0f, 65.215f);
        path.lineTo(375.0f, 515.215f);
        path.lineTo(675.0f, 165.645f);
        path.lineTo(135.0f, 365.325f);
        path.lineTo(365.0f, 155.305f);
        path.lineTo(575.0f, 265.995f);
        path.lineTo(645.0f, 505.185f);
        path.lineTo(165.0f, 405.605f);
        path.lineTo(975.0f, 775.875f);
        path.lineTo(955.0f, 35.645f);
        path.lineTo(95.0f, 955.335f);
        path.lineTo(85.0f, 745.735f);
        path.lineTo(855.0f, 545.295f);
        path.lineTo(365.0f, 155.605f);
        path.lineTo(35.0f, 285.465f);
        path.lineTo(615.0f, 605.945f);
        path.lineTo(675.0f, 305.915f);
        path.lineTo(215.0f, 905.195f);
        path.lineTo(755.0f, 385.405f);
        path.lineTo(785.0f, 105.745f);
        path.lineTo(105.0f, 365.95f);
        path.lineTo(425.0f, 85.455f);
        path.lineTo(955.0f, 725.135f);
        path.lineTo(435.0f, 975.335f);
        path.lineTo(705.0f, 125.675f);
        path.lineTo(755.0f, 545.55f);
        path.lineTo(10105.0f, 205.865f);
        path.lineTo(915.0f, 915.665f);
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
