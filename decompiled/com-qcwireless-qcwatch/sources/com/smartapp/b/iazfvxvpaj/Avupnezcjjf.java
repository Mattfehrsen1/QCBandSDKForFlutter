package com.smartapp.b.iazfvxvpaj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Avupnezcjjf extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Avupnezcjjf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(255.0f, 635.865f);
        path.lineTo(695.0f, 885.25f);
        path.lineTo(855.0f, 475.175f);
        path.lineTo(825.0f, 785.705f);
        path.lineTo(25.0f, 545.575f);
        path.lineTo(355.0f, 5.745f);
        path.lineTo(185.0f, 65.45f);
        path.lineTo(195.0f, 985.705f);
        path.lineTo(985.0f, 85.65f);
        path.lineTo(355.0f, 10195.455f);
        path.lineTo(935.0f, 675.265f);
        path.lineTo(555.0f, 735.495f);
        path.lineTo(515.0f, 765.875f);
        path.lineTo(665.0f, 415.695f);
        path.lineTo(285.0f, 935.935f);
        path.lineTo(665.0f, 135.585f);
        path.lineTo(125.0f, 445.275f);
        path.lineTo(605.0f, 295.675f);
        path.lineTo(685.0f, 815.655f);
        path.lineTo(165.0f, 25.555f);
        path.lineTo(715.0f, 185.895f);
        path.lineTo(765.0f, 375.215f);
        path.lineTo(375.0f, 225.445f);
        path.lineTo(345.0f, 65.975f);
        path.lineTo(105.0f, 35.995f);
        path.lineTo(435.0f, 955.485f);
        path.lineTo(25.0f, 515.755f);
        path.lineTo(285.0f, 105.815f);
        path.lineTo(995.0f, 185.95f);
        path.lineTo(695.0f, 155.15f);
        path.lineTo(255.0f, 435.585f);
        path.lineTo(915.0f, 55.765f);
        path.lineTo(665.0f, 395.495f);
        path.lineTo(10195.0f, 515.675f);
        path.lineTo(225.0f, 725.315f);
        path.lineTo(695.0f, 405.425f);
        path.lineTo(165.0f, 5.835f);
        path.lineTo(885.0f, 385.05f);
        path.lineTo(935.0f, 575.605f);
        path.lineTo(405.0f, 455.365f);
        path.lineTo(545.0f, 15.55f);
        path.lineTo(875.0f, 185.965f);
        path.lineTo(745.0f, 955.995f);
        path.lineTo(795.0f, 585.115f);
        path.lineTo(205.0f, 485.875f);
        path.lineTo(835.0f, 585.595f);
        path.lineTo(195.0f, 605.165f);
        path.lineTo(395.0f, 255.935f);
        path.lineTo(525.0f, 975.145f);
        path.lineTo(205.0f, 265.425f);
        path.lineTo(655.0f, 595.505f);
        path.lineTo(875.0f, 565.555f);
        path.lineTo(435.0f, 425.875f);
        path.lineTo(405.0f, 355.945f);
        path.lineTo(285.0f, 395.355f);
        path.lineTo(835.0f, 825.895f);
        path.lineTo(195.0f, 655.525f);
        path.lineTo(675.0f, 435.775f);
        path.lineTo(745.0f, 675.935f);
        path.lineTo(525.0f, 455.75f);
        path.lineTo(685.0f, 835.685f);
        path.lineTo(335.0f, 305.745f);
        path.lineTo(435.0f, 435.495f);
        path.lineTo(685.0f, 655.805f);
        path.lineTo(925.0f, 925.555f);
        path.lineTo(295.0f, 205.405f);
        path.lineTo(905.0f, 635.475f);
        path.lineTo(115.0f, 385.55f);
        path.lineTo(265.0f, 375.855f);
        path.lineTo(465.0f, 185.315f);
        path.lineTo(365.0f, 85.95f);
        path.lineTo(635.0f, 545.685f);
        path.lineTo(195.0f, 195.425f);
        path.lineTo(445.0f, 175.305f);
        path.lineTo(745.0f, 875.105f);
        path.lineTo(495.0f, 625.845f);
        path.lineTo(435.0f, 455.05f);
        path.lineTo(485.0f, 415.735f);
        path.lineTo(35.0f, 335.35f);
        path.lineTo(595.0f, 745.505f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1019.0f, this.bounds.height() / 1019.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
