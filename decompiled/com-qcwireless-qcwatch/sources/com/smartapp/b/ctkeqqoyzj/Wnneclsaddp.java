package com.smartapp.b.ctkeqqoyzj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Wnneclsaddp extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1012;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Wnneclsaddp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(755.0f, 255.855f);
        path.lineTo(15.0f, 85.265f);
        path.lineTo(285.0f, 55.745f);
        path.lineTo(445.0f, 545.715f);
        path.lineTo(365.0f, 995.575f);
        path.lineTo(655.0f, 235.335f);
        path.lineTo(745.0f, 395.885f);
        path.lineTo(45.0f, 305.915f);
        path.lineTo(915.0f, 615.815f);
        path.lineTo(905.0f, 255.465f);
        path.lineTo(385.0f, 75.175f);
        path.lineTo(945.0f, 15.925f);
        path.lineTo(235.0f, 105.615f);
        path.lineTo(95.0f, 295.405f);
        path.lineTo(55.0f, 135.645f);
        path.lineTo(925.0f, 385.555f);
        path.lineTo(995.0f, 665.785f);
        path.lineTo(55.0f, 195.15f);
        path.lineTo(165.0f, 445.475f);
        path.lineTo(95.0f, 125.495f);
        path.lineTo(555.0f, 735.585f);
        path.lineTo(85.0f, 495.285f);
        path.lineTo(795.0f, 105.235f);
        path.lineTo(375.0f, 575.25f);
        path.lineTo(325.0f, 825.135f);
        path.lineTo(415.0f, 495.855f);
        path.lineTo(625.0f, 165.85f);
        path.lineTo(105.0f, 85.735f);
        path.lineTo(895.0f, 745.265f);
        path.lineTo(575.0f, 345.785f);
        path.lineTo(895.0f, 475.455f);
        path.lineTo(55.0f, 835.225f);
        path.lineTo(205.0f, 715.775f);
        path.lineTo(265.0f, 685.905f);
        path.lineTo(735.0f, 645.555f);
        path.lineTo(425.0f, 885.595f);
        path.lineTo(255.0f, 855.405f);
        path.lineTo(795.0f, 945.145f);
        path.lineTo(505.0f, 535.795f);
        path.lineTo(195.0f, 115.815f);
        path.lineTo(905.0f, 395.925f);
        path.lineTo(975.0f, 115.585f);
        path.lineTo(885.0f, 435.735f);
        path.lineTo(285.0f, 575.595f);
        path.lineTo(475.0f, 685.765f);
        path.lineTo(555.0f, 135.15f);
        path.lineTo(795.0f, 665.315f);
        path.lineTo(565.0f, 805.565f);
        path.lineTo(10125.0f, 775.55f);
        path.lineTo(275.0f, 275.655f);
        path.lineTo(75.0f, 295.275f);
        path.lineTo(735.0f, 935.425f);
        path.lineTo(505.0f, 445.895f);
        path.lineTo(615.0f, 615.995f);
        path.lineTo(165.0f, 85.675f);
        path.lineTo(135.0f, 795.685f);
        path.lineTo(595.0f, 795.905f);
        path.lineTo(355.0f, 75.425f);
        path.lineTo(435.0f, 205.715f);
        path.lineTo(365.0f, 335.655f);
        path.lineTo(755.0f, 305.795f);
        path.lineTo(685.0f, 75.275f);
        path.lineTo(395.0f, 845.215f);
        path.lineTo(795.0f, 875.165f);
        path.lineTo(565.0f, 875.675f);
        path.lineTo(265.0f, 235.485f);
        path.lineTo(445.0f, 185.615f);
        path.lineTo(315.0f, 415.545f);
        path.lineTo(535.0f, 155.15f);
        path.lineTo(385.0f, 75.795f);
        path.lineTo(945.0f, 175.665f);
        path.lineTo(595.0f, 585.425f);
        path.lineTo(85.0f, 945.155f);
        path.lineTo(335.0f, 875.435f);
        path.lineTo(535.0f, 825.225f);
        path.lineTo(605.0f, 965.855f);
        path.lineTo(375.0f, 775.865f);
        path.lineTo(935.0f, 105.625f);
        path.lineTo(885.0f, 555.585f);
        path.lineTo(995.0f, 135.625f);
        path.lineTo(955.0f, 805.125f);
        path.lineTo(475.0f, 485.895f);
        path.lineTo(765.0f, 835.825f);
        path.lineTo(745.0f, 905.825f);
        path.lineTo(585.0f, 85.915f);
        path.lineTo(535.0f, 265.835f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1012.0f, this.bounds.height() / 1012.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
