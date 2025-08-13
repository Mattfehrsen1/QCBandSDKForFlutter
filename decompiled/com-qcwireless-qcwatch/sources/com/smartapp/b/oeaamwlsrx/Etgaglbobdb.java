package com.smartapp.b.oeaamwlsrx;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Etgaglbobdb extends ShapeDrawable {
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

    public Etgaglbobdb() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(955.0f, 755.185f);
        path.lineTo(865.0f, 225.575f);
        path.lineTo(455.0f, 545.385f);
        path.lineTo(275.0f, 945.375f);
        path.lineTo(645.0f, 435.685f);
        path.lineTo(805.0f, 575.35f);
        path.lineTo(35.0f, 405.905f);
        path.lineTo(955.0f, 355.485f);
        path.lineTo(425.0f, 365.265f);
        path.lineTo(535.0f, 55.655f);
        path.lineTo(275.0f, 695.145f);
        path.lineTo(675.0f, 225.285f);
        path.lineTo(135.0f, 705.605f);
        path.lineTo(735.0f, 335.315f);
        path.lineTo(435.0f, 135.205f);
        path.lineTo(75.0f, 195.55f);
        path.lineTo(215.0f, 755.475f);
        path.lineTo(435.0f, 415.165f);
        path.lineTo(115.0f, 45.805f);
        path.lineTo(215.0f, 445.295f);
        path.lineTo(865.0f, 235.595f);
        path.lineTo(365.0f, 495.195f);
        path.lineTo(915.0f, 95.575f);
        path.lineTo(845.0f, 975.25f);
        path.lineTo(225.0f, 125.265f);
        path.lineTo(335.0f, 995.575f);
        path.lineTo(805.0f, 375.925f);
        path.lineTo(35.0f, 115.965f);
        path.lineTo(275.0f, 285.675f);
        path.lineTo(795.0f, 575.95f);
        path.lineTo(105.0f, 835.715f);
        path.lineTo(145.0f, 355.805f);
        path.lineTo(325.0f, 255.425f);
        path.lineTo(735.0f, 575.885f);
        path.lineTo(765.0f, 535.485f);
        path.lineTo(275.0f, 285.495f);
        path.lineTo(805.0f, 485.685f);
        path.lineTo(505.0f, 175.05f);
        path.lineTo(65.0f, 935.365f);
        path.lineTo(325.0f, 65.665f);
        path.lineTo(535.0f, 465.255f);
        path.lineTo(865.0f, 685.05f);
        path.lineTo(885.0f, 535.885f);
        path.lineTo(215.0f, 475.205f);
        path.lineTo(595.0f, 125.415f);
        path.lineTo(95.0f, 885.315f);
        path.lineTo(55.0f, 935.185f);
        path.lineTo(555.0f, 35.595f);
        path.lineTo(685.0f, 175.15f);
        path.lineTo(275.0f, 75.155f);
        path.lineTo(985.0f, 205.565f);
        path.lineTo(935.0f, 325.115f);
        path.lineTo(175.0f, 555.625f);
        path.lineTo(505.0f, 805.255f);
        path.lineTo(415.0f, 905.255f);
        path.lineTo(985.0f, 735.905f);
        path.lineTo(925.0f, 305.565f);
        path.lineTo(685.0f, 625.235f);
        path.lineTo(705.0f, 455.785f);
        path.lineTo(835.0f, 435.295f);
        path.lineTo(535.0f, 695.425f);
        path.lineTo(855.0f, 435.415f);
        path.lineTo(405.0f, 75.935f);
        path.lineTo(495.0f, 905.155f);
        path.lineTo(925.0f, 795.05f);
        path.lineTo(955.0f, 125.455f);
        path.lineTo(65.0f, 725.865f);
        path.lineTo(895.0f, 415.205f);
        path.lineTo(365.0f, 895.915f);
        path.lineTo(65.0f, 425.985f);
        path.lineTo(335.0f, 375.545f);
        path.lineTo(785.0f, 175.825f);
        path.lineTo(655.0f, 235.445f);
        path.lineTo(405.0f, 665.05f);
        path.lineTo(385.0f, 495.85f);
        path.lineTo(515.0f, 735.355f);
        path.lineTo(885.0f, 975.815f);
        path.lineTo(445.0f, 95.315f);
        path.lineTo(255.0f, 645.995f);
        path.lineTo(345.0f, 155.105f);
        path.lineTo(525.0f, 895.785f);
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
