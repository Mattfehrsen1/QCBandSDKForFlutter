package com.smartapp.b.dgijygxdrf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Dvacxbebcct extends ShapeDrawable {
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

    public Dvacxbebcct() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(905.0f, 505.525f);
        path.lineTo(85.0f, 785.825f);
        path.lineTo(855.0f, 815.475f);
        path.lineTo(415.0f, 415.465f);
        path.lineTo(345.0f, 665.355f);
        path.lineTo(465.0f, 75.655f);
        path.lineTo(795.0f, 995.325f);
        path.lineTo(595.0f, 85.935f);
        path.lineTo(35.0f, 10135.305f);
        path.lineTo(475.0f, 695.405f);
        path.lineTo(375.0f, 735.785f);
        path.lineTo(355.0f, 10135.705f);
        path.lineTo(545.0f, 665.45f);
        path.lineTo(925.0f, 115.205f);
        path.lineTo(345.0f, 665.625f);
        path.lineTo(695.0f, 845.545f);
        path.lineTo(925.0f, 315.775f);
        path.lineTo(265.0f, 735.785f);
        path.lineTo(235.0f, 905.765f);
        path.lineTo(225.0f, 215.675f);
        path.lineTo(745.0f, 15.635f);
        path.lineTo(825.0f, 975.985f);
        path.lineTo(95.0f, 435.775f);
        path.lineTo(25.0f, 895.175f);
        path.lineTo(105.0f, 695.975f);
        path.lineTo(755.0f, 615.335f);
        path.lineTo(925.0f, 585.895f);
        path.lineTo(45.0f, 305.65f);
        path.lineTo(585.0f, 285.75f);
        path.lineTo(75.0f, 695.525f);
        path.lineTo(335.0f, 225.10135f);
        path.lineTo(55.0f, 345.725f);
        path.lineTo(175.0f, 185.515f);
        path.lineTo(115.0f, 745.625f);
        path.lineTo(645.0f, 715.675f);
        path.lineTo(975.0f, 225.895f);
        path.lineTo(755.0f, 825.635f);
        path.lineTo(325.0f, 65.965f);
        path.lineTo(665.0f, 165.605f);
        path.lineTo(875.0f, 25.325f);
        path.lineTo(165.0f, 875.895f);
        path.lineTo(565.0f, 445.765f);
        path.lineTo(385.0f, 205.05f);
        path.lineTo(405.0f, 995.885f);
        path.lineTo(705.0f, 305.615f);
        path.lineTo(365.0f, 205.635f);
        path.lineTo(385.0f, 525.295f);
        path.lineTo(925.0f, 375.985f);
        path.lineTo(755.0f, 445.705f);
        path.lineTo(325.0f, 835.405f);
        path.lineTo(615.0f, 665.445f);
        path.lineTo(815.0f, 215.805f);
        path.lineTo(495.0f, 545.65f);
        path.lineTo(495.0f, 615.515f);
        path.lineTo(645.0f, 725.605f);
        path.lineTo(295.0f, 435.715f);
        path.lineTo(125.0f, 885.125f);
        path.lineTo(955.0f, 835.875f);
        path.lineTo(585.0f, 515.925f);
        path.lineTo(75.0f, 255.55f);
        path.lineTo(25.0f, 835.405f);
        path.lineTo(45.0f, 605.895f);
        path.lineTo(305.0f, 515.605f);
        path.lineTo(465.0f, 725.705f);
        path.lineTo(35.0f, 925.985f);
        path.lineTo(875.0f, 535.15f);
        path.lineTo(5.0f, 415.265f);
        path.lineTo(835.0f, 205.665f);
        path.lineTo(5.0f, 745.455f);
        path.lineTo(625.0f, 545.25f);
        path.lineTo(285.0f, 165.985f);
        path.lineTo(365.0f, 475.775f);
        path.lineTo(25.0f, 125.185f);
        path.lineTo(805.0f, 845.585f);
        path.lineTo(185.0f, 135.125f);
        path.lineTo(935.0f, 905.115f);
        path.lineTo(815.0f, 485.135f);
        path.lineTo(10135.0f, 405.765f);
        path.lineTo(225.0f, 955.975f);
        path.lineTo(955.0f, 635.545f);
        path.lineTo(555.0f, 405.375f);
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
