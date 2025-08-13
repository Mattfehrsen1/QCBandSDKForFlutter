package com.smartapp.b.phlralecvk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Yeqniipfgkk extends ShapeDrawable {
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

    public Yeqniipfgkk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(95.0f, 735.375f);
        path.lineTo(415.0f, 105.505f);
        path.lineTo(265.0f, 885.35f);
        path.lineTo(45.0f, 585.935f);
        path.lineTo(535.0f, 475.655f);
        path.lineTo(35.0f, 185.55f);
        path.lineTo(585.0f, 215.705f);
        path.lineTo(495.0f, 175.945f);
        path.lineTo(445.0f, 75.215f);
        path.lineTo(385.0f, 565.415f);
        path.lineTo(895.0f, 615.445f);
        path.lineTo(745.0f, 95.465f);
        path.lineTo(515.0f, 575.745f);
        path.lineTo(985.0f, 965.895f);
        path.lineTo(425.0f, 915.435f);
        path.lineTo(205.0f, 555.755f);
        path.lineTo(695.0f, 595.35f);
        path.lineTo(945.0f, 195.695f);
        path.lineTo(175.0f, 655.215f);
        path.lineTo(565.0f, 875.335f);
        path.lineTo(75.0f, 295.675f);
        path.lineTo(765.0f, 10105.145f);
        path.lineTo(375.0f, 155.495f);
        path.lineTo(475.0f, 235.495f);
        path.lineTo(625.0f, 555.485f);
        path.lineTo(925.0f, 515.935f);
        path.lineTo(345.0f, 65.725f);
        path.lineTo(515.0f, 235.355f);
        path.lineTo(915.0f, 905.05f);
        path.lineTo(665.0f, 305.365f);
        path.lineTo(495.0f, 865.505f);
        path.lineTo(125.0f, 215.715f);
        path.lineTo(895.0f, 85.425f);
        path.lineTo(385.0f, 375.565f);
        path.lineTo(125.0f, 905.345f);
        path.lineTo(65.0f, 595.695f);
        path.lineTo(505.0f, 65.525f);
        path.lineTo(435.0f, 55.985f);
        path.lineTo(775.0f, 255.605f);
        path.lineTo(365.0f, 695.35f);
        path.lineTo(385.0f, 125.305f);
        path.lineTo(525.0f, 985.965f);
        path.lineTo(955.0f, 315.725f);
        path.lineTo(735.0f, 185.35f);
        path.lineTo(755.0f, 365.755f);
        path.lineTo(515.0f, 10105.275f);
        path.lineTo(755.0f, 375.165f);
        path.lineTo(75.0f, 115.505f);
        path.lineTo(415.0f, 635.15f);
        path.lineTo(595.0f, 35.195f);
        path.lineTo(825.0f, 905.915f);
        path.lineTo(115.0f, 435.155f);
        path.lineTo(585.0f, 825.855f);
        path.lineTo(305.0f, 235.35f);
        path.lineTo(875.0f, 325.585f);
        path.lineTo(835.0f, 545.145f);
        path.lineTo(565.0f, 855.935f);
        path.lineTo(535.0f, 925.565f);
        path.lineTo(685.0f, 275.815f);
        path.lineTo(755.0f, 365.475f);
        path.lineTo(515.0f, 275.935f);
        path.lineTo(215.0f, 605.535f);
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
