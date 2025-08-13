package com.smartapp.b.kyiupxpigi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Mdntilzscwt extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Mdntilzscwt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(465.0f, 165.265f);
        path.lineTo(405.0f, 355.785f);
        path.lineTo(865.0f, 845.845f);
        path.lineTo(525.0f, 515.15f);
        path.lineTo(275.0f, 215.535f);
        path.lineTo(415.0f, 945.885f);
        path.lineTo(355.0f, 445.235f);
        path.lineTo(375.0f, 585.525f);
        path.lineTo(545.0f, 155.625f);
        path.lineTo(185.0f, 525.95f);
        path.lineTo(965.0f, 275.785f);
        path.lineTo(485.0f, 435.285f);
        path.lineTo(625.0f, 735.135f);
        path.lineTo(325.0f, 625.815f);
        path.lineTo(295.0f, 755.445f);
        path.lineTo(365.0f, 675.965f);
        path.lineTo(375.0f, 285.285f);
        path.lineTo(815.0f, 115.115f);
        path.lineTo(325.0f, 555.495f);
        path.lineTo(605.0f, 745.695f);
        path.lineTo(15.0f, 775.35f);
        path.lineTo(185.0f, 905.255f);
        path.lineTo(115.0f, 595.195f);
        path.lineTo(415.0f, 105.675f);
        path.lineTo(375.0f, 155.155f);
        path.lineTo(735.0f, 335.345f);
        path.lineTo(125.0f, 805.225f);
        path.lineTo(145.0f, 295.555f);
        path.lineTo(905.0f, 535.505f);
        path.lineTo(165.0f, 275.445f);
        path.lineTo(685.0f, 395.425f);
        path.lineTo(45.0f, 555.845f);
        path.lineTo(965.0f, 95.355f);
        path.lineTo(295.0f, 945.615f);
        path.lineTo(295.0f, 875.155f);
        path.lineTo(505.0f, 505.405f);
        path.lineTo(765.0f, 605.275f);
        path.lineTo(375.0f, 395.265f);
        path.lineTo(925.0f, 785.575f);
        path.lineTo(965.0f, 715.475f);
        path.lineTo(355.0f, 315.685f);
        path.lineTo(565.0f, 885.675f);
        path.lineTo(765.0f, 135.305f);
        path.lineTo(825.0f, 505.475f);
        path.lineTo(745.0f, 695.785f);
        path.lineTo(665.0f, 545.345f);
        path.lineTo(855.0f, 395.25f);
        path.lineTo(55.0f, 145.115f);
        path.lineTo(785.0f, 605.565f);
        path.lineTo(715.0f, 525.395f);
        path.lineTo(495.0f, 10175.895f);
        path.lineTo(345.0f, 475.105f);
        path.lineTo(715.0f, 145.775f);
        path.lineTo(10175.0f, 515.145f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1017.0f, this.bounds.height() / 1017.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
