package com.smartapp.b.ghjaiwbbvs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Wysfcamojsu extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Wysfcamojsu() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(845.0f, 195.895f);
        path.lineTo(795.0f, 375.545f);
        path.lineTo(675.0f, 415.305f);
        path.lineTo(165.0f, 985.445f);
        path.lineTo(705.0f, 515.485f);
        path.lineTo(575.0f, 555.395f);
        path.lineTo(645.0f, 485.845f);
        path.lineTo(225.0f, 185.265f);
        path.lineTo(605.0f, 785.945f);
        path.lineTo(835.0f, 145.585f);
        path.lineTo(315.0f, 895.25f);
        path.lineTo(755.0f, 25.255f);
        path.lineTo(815.0f, 445.65f);
        path.lineTo(395.0f, 655.615f);
        path.lineTo(505.0f, 175.615f);
        path.lineTo(485.0f, 685.815f);
        path.lineTo(215.0f, 345.145f);
        path.lineTo(85.0f, 255.725f);
        path.lineTo(225.0f, 445.05f);
        path.lineTo(235.0f, 785.315f);
        path.lineTo(235.0f, 905.25f);
        path.lineTo(105.0f, 85.995f);
        path.lineTo(825.0f, 445.835f);
        path.lineTo(225.0f, 125.235f);
        path.lineTo(165.0f, 855.365f);
        path.lineTo(175.0f, 435.375f);
        path.lineTo(115.0f, 95.965f);
        path.lineTo(905.0f, 495.335f);
        path.lineTo(585.0f, 945.795f);
        path.lineTo(475.0f, 715.485f);
        path.lineTo(735.0f, 125.305f);
        path.lineTo(975.0f, 865.415f);
        path.lineTo(665.0f, 725.265f);
        path.lineTo(945.0f, 555.985f);
        path.lineTo(655.0f, 25.805f);
        path.lineTo(415.0f, 385.45f);
        path.lineTo(10065.0f, 705.215f);
        path.lineTo(115.0f, 415.865f);
        path.lineTo(195.0f, 335.665f);
        path.lineTo(65.0f, 525.425f);
        path.lineTo(495.0f, 35.845f);
        path.lineTo(175.0f, 425.335f);
        path.lineTo(195.0f, 895.835f);
        path.lineTo(735.0f, 825.215f);
        path.lineTo(585.0f, 145.365f);
        path.lineTo(585.0f, 885.165f);
        path.lineTo(855.0f, 55.565f);
        path.lineTo(115.0f, 195.575f);
        path.lineTo(485.0f, 115.705f);
        path.lineTo(525.0f, 845.825f);
        path.lineTo(865.0f, 585.455f);
        path.lineTo(685.0f, 935.605f);
        path.lineTo(805.0f, 885.705f);
        path.lineTo(865.0f, 965.315f);
        path.lineTo(145.0f, 545.65f);
        path.lineTo(525.0f, 895.615f);
        path.lineTo(455.0f, 715.935f);
        path.lineTo(215.0f, 395.485f);
        path.lineTo(85.0f, 715.845f);
        path.lineTo(855.0f, 525.05f);
        path.lineTo(255.0f, 45.605f);
        path.lineTo(985.0f, 725.275f);
        path.lineTo(455.0f, 515.105f);
        path.lineTo(205.0f, 25.05f);
        path.lineTo(535.0f, 775.225f);
        path.lineTo(855.0f, 335.865f);
        path.lineTo(575.0f, 675.75f);
        path.lineTo(785.0f, 65.10065f);
        path.lineTo(265.0f, 935.85f);
        path.lineTo(675.0f, 265.355f);
        path.lineTo(715.0f, 235.665f);
        path.lineTo(835.0f, 145.825f);
        path.lineTo(255.0f, 625.85f);
        path.lineTo(665.0f, 905.35f);
        path.lineTo(365.0f, 10065.535f);
        path.lineTo(895.0f, 935.505f);
        path.lineTo(345.0f, 795.675f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
