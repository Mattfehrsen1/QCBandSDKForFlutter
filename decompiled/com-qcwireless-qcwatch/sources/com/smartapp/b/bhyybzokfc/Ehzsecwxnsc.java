package com.smartapp.b.bhyybzokfc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Ehzsecwxnsc extends ShapeDrawable {
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

    public Ehzsecwxnsc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(775.0f, 365.75f);
        path.lineTo(225.0f, 405.495f);
        path.lineTo(595.0f, 845.625f);
        path.lineTo(355.0f, 485.625f);
        path.lineTo(315.0f, 235.375f);
        path.lineTo(335.0f, 75.355f);
        path.lineTo(955.0f, 195.35f);
        path.lineTo(985.0f, 295.985f);
        path.lineTo(875.0f, 955.105f);
        path.lineTo(605.0f, 485.775f);
        path.lineTo(625.0f, 115.705f);
        path.lineTo(745.0f, 5.95f);
        path.lineTo(185.0f, 345.235f);
        path.lineTo(855.0f, 605.175f);
        path.lineTo(675.0f, 385.615f);
        path.lineTo(225.0f, 315.455f);
        path.lineTo(495.0f, 475.505f);
        path.lineTo(955.0f, 945.445f);
        path.lineTo(35.0f, 585.995f);
        path.lineTo(475.0f, 425.85f);
        path.lineTo(935.0f, 425.725f);
        path.lineTo(305.0f, 215.285f);
        path.lineTo(825.0f, 675.205f);
        path.lineTo(835.0f, 195.255f);
        path.lineTo(755.0f, 885.685f);
        path.lineTo(895.0f, 345.585f);
        path.lineTo(545.0f, 305.575f);
        path.lineTo(45.0f, 915.765f);
        path.lineTo(235.0f, 775.465f);
        path.lineTo(75.0f, 595.655f);
        path.lineTo(95.0f, 875.445f);
        path.lineTo(345.0f, 565.125f);
        path.lineTo(365.0f, 305.375f);
        path.lineTo(155.0f, 375.965f);
        path.lineTo(465.0f, 5.775f);
        path.lineTo(915.0f, 835.965f);
        path.lineTo(10105.0f, 345.815f);
        path.lineTo(985.0f, 165.635f);
        path.lineTo(845.0f, 375.775f);
        path.lineTo(395.0f, 945.335f);
        path.lineTo(35.0f, 105.695f);
        path.lineTo(835.0f, 215.925f);
        path.lineTo(915.0f, 325.565f);
        path.lineTo(35.0f, 365.65f);
        path.lineTo(775.0f, 745.975f);
        path.lineTo(345.0f, 45.995f);
        path.lineTo(845.0f, 585.585f);
        path.lineTo(5.0f, 415.955f);
        path.lineTo(695.0f, 835.165f);
        path.lineTo(515.0f, 45.965f);
        path.lineTo(885.0f, 125.225f);
        path.lineTo(425.0f, 325.335f);
        path.lineTo(565.0f, 785.455f);
        path.lineTo(555.0f, 845.805f);
        path.lineTo(895.0f, 565.605f);
        path.lineTo(195.0f, 355.405f);
        path.lineTo(605.0f, 875.355f);
        path.lineTo(725.0f, 45.835f);
        path.lineTo(655.0f, 525.825f);
        path.lineTo(135.0f, 265.265f);
        path.lineTo(5.0f, 675.285f);
        path.lineTo(815.0f, 735.45f);
        path.lineTo(965.0f, 175.105f);
        path.lineTo(635.0f, 815.445f);
        path.lineTo(455.0f, 485.745f);
        path.lineTo(855.0f, 665.205f);
        path.lineTo(725.0f, 455.275f);
        path.lineTo(485.0f, 325.875f);
        path.lineTo(705.0f, 155.25f);
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
