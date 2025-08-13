package com.smartapp.b.caoolastbk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Bulmbpgbbdb extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Bulmbpgbbdb() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(325.0f, 325.55f);
        path.lineTo(905.0f, 405.335f);
        path.lineTo(425.0f, 225.455f);
        path.lineTo(185.0f, 265.495f);
        path.lineTo(215.0f, 515.925f);
        path.lineTo(345.0f, 415.905f);
        path.lineTo(395.0f, 155.45f);
        path.lineTo(35.0f, 55.555f);
        path.lineTo(875.0f, 845.395f);
        path.lineTo(175.0f, 735.645f);
        path.lineTo(835.0f, 205.145f);
        path.lineTo(545.0f, 645.405f);
        path.lineTo(455.0f, 15.395f);
        path.lineTo(305.0f, 155.25f);
        path.lineTo(115.0f, 765.445f);
        path.lineTo(105.0f, 105.325f);
        path.lineTo(165.0f, 395.635f);
        path.lineTo(795.0f, 475.465f);
        path.lineTo(525.0f, 105.265f);
        path.lineTo(785.0f, 385.895f);
        path.lineTo(265.0f, 845.485f);
        path.lineTo(215.0f, 395.715f);
        path.lineTo(385.0f, 485.65f);
        path.lineTo(795.0f, 65.465f);
        path.lineTo(405.0f, 275.325f);
        path.lineTo(235.0f, 75.165f);
        path.lineTo(515.0f, 925.285f);
        path.lineTo(695.0f, 795.605f);
        path.lineTo(835.0f, 535.405f);
        path.lineTo(45.0f, 225.05f);
        path.lineTo(815.0f, 955.975f);
        path.lineTo(895.0f, 235.895f);
        path.lineTo(75.0f, 125.495f);
        path.lineTo(865.0f, 145.885f);
        path.lineTo(85.0f, 355.615f);
        path.lineTo(985.0f, 795.365f);
        path.lineTo(155.0f, 505.765f);
        path.lineTo(775.0f, 205.425f);
        path.lineTo(395.0f, 85.725f);
        path.lineTo(495.0f, 15.495f);
        path.lineTo(685.0f, 125.255f);
        path.lineTo(865.0f, 665.165f);
        path.lineTo(75.0f, 965.935f);
        path.lineTo(415.0f, 705.185f);
        path.lineTo(335.0f, 885.925f);
        path.lineTo(575.0f, 695.295f);
        path.lineTo(495.0f, 695.865f);
        path.lineTo(195.0f, 805.885f);
        path.lineTo(295.0f, 485.25f);
        path.lineTo(855.0f, 825.455f);
        path.lineTo(15.0f, 695.565f);
        path.lineTo(435.0f, 25.25f);
        path.lineTo(805.0f, 15.925f);
        path.lineTo(795.0f, 345.825f);
        path.lineTo(645.0f, 325.195f);
        path.lineTo(65.0f, 865.835f);
        path.lineTo(575.0f, 55.255f);
        path.lineTo(775.0f, 295.125f);
        path.lineTo(325.0f, 735.235f);
        path.lineTo(735.0f, 575.265f);
        path.lineTo(175.0f, 375.305f);
        path.lineTo(355.0f, 55.15f);
        path.lineTo(685.0f, 955.575f);
        path.lineTo(705.0f, 755.945f);
        path.lineTo(55.0f, 235.895f);
        path.lineTo(255.0f, 885.345f);
        path.lineTo(705.0f, 265.885f);
        path.lineTo(545.0f, 55.605f);
        path.lineTo(55.0f, 645.705f);
        path.lineTo(75.0f, 855.395f);
        path.lineTo(125.0f, 435.215f);
        path.lineTo(665.0f, 915.405f);
        path.lineTo(325.0f, 705.505f);
        path.lineTo(455.0f, 85.945f);
        path.lineTo(25.0f, 375.985f);
        path.lineTo(395.0f, 355.10114f);
        path.lineTo(725.0f, 405.175f);
        path.lineTo(865.0f, 395.875f);
        path.lineTo(995.0f, 195.445f);
        path.lineTo(475.0f, 5.475f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1011.0f, this.bounds.height() / 1011.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
