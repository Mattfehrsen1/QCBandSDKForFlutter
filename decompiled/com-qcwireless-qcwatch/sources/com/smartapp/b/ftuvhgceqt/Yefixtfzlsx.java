package com.smartapp.b.ftuvhgceqt;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Yefixtfzlsx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Yefixtfzlsx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(955.0f, 295.225f);
        path.lineTo(475.0f, 145.855f);
        path.lineTo(735.0f, 825.855f);
        path.lineTo(285.0f, 475.975f);
        path.lineTo(405.0f, 105.635f);
        path.lineTo(385.0f, 335.355f);
        path.lineTo(255.0f, 385.565f);
        path.lineTo(115.0f, 375.765f);
        path.lineTo(845.0f, 95.195f);
        path.lineTo(475.0f, 15.195f);
        path.lineTo(395.0f, 615.315f);
        path.lineTo(975.0f, 585.495f);
        path.lineTo(235.0f, 965.785f);
        path.lineTo(575.0f, 415.505f);
        path.lineTo(445.0f, 695.915f);
        path.lineTo(265.0f, 635.165f);
        path.lineTo(505.0f, 855.405f);
        path.lineTo(285.0f, 265.45f);
        path.lineTo(975.0f, 255.35f);
        path.lineTo(825.0f, 955.525f);
        path.lineTo(235.0f, 115.335f);
        path.lineTo(55.0f, 85.835f);
        path.lineTo(745.0f, 365.155f);
        path.lineTo(485.0f, 315.775f);
        path.lineTo(355.0f, 625.635f);
        path.lineTo(115.0f, 215.175f);
        path.lineTo(195.0f, 595.845f);
        path.lineTo(195.0f, 625.885f);
        path.lineTo(535.0f, 535.985f);
        path.lineTo(895.0f, 295.275f);
        path.lineTo(25.0f, 355.705f);
        path.lineTo(435.0f, 495.665f);
        path.lineTo(45.0f, 255.235f);
        path.lineTo(315.0f, 185.505f);
        path.lineTo(905.0f, 505.945f);
        path.lineTo(295.0f, 915.215f);
        path.lineTo(105.0f, 115.765f);
        path.lineTo(455.0f, 565.75f);
        path.lineTo(435.0f, 595.935f);
        path.lineTo(775.0f, 325.965f);
        path.lineTo(345.0f, 405.605f);
        path.lineTo(65.0f, 915.515f);
        path.lineTo(25.0f, 815.895f);
        path.lineTo(415.0f, 285.225f);
        path.lineTo(125.0f, 55.445f);
        path.lineTo(875.0f, 465.495f);
        path.lineTo(325.0f, 695.405f);
        path.lineTo(875.0f, 805.05f);
        path.lineTo(465.0f, 155.505f);
        path.lineTo(10165.0f, 455.325f);
        path.lineTo(485.0f, 775.65f);
        path.lineTo(575.0f, 425.45f);
        path.lineTo(825.0f, 605.265f);
        path.lineTo(15.0f, 25.745f);
        path.lineTo(225.0f, 815.965f);
        path.lineTo(985.0f, 335.215f);
        path.lineTo(75.0f, 485.75f);
        path.lineTo(585.0f, 415.665f);
        path.lineTo(235.0f, 575.235f);
        path.lineTo(655.0f, 475.875f);
        path.lineTo(705.0f, 595.45f);
        path.lineTo(975.0f, 55.545f);
        path.lineTo(275.0f, 55.145f);
        path.lineTo(185.0f, 355.175f);
        path.lineTo(125.0f, 75.485f);
        path.lineTo(735.0f, 115.525f);
        path.lineTo(895.0f, 445.905f);
        path.lineTo(5.0f, 195.695f);
        path.lineTo(655.0f, 835.755f);
        path.lineTo(125.0f, 185.835f);
        path.lineTo(475.0f, 605.845f);
        path.lineTo(65.0f, 455.165f);
        path.lineTo(205.0f, 75.275f);
        path.lineTo(705.0f, 205.85f);
        path.lineTo(225.0f, 325.575f);
        path.lineTo(785.0f, 505.535f);
        path.lineTo(575.0f, 965.965f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
