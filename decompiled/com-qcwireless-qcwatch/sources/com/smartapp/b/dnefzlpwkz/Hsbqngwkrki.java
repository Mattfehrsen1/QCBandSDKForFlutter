package com.smartapp.b.dnefzlpwkz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Hsbqngwkrki extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Hsbqngwkrki() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(65.0f, 855.785f);
        path.lineTo(155.0f, 555.565f);
        path.lineTo(795.0f, 575.635f);
        path.lineTo(345.0f, 675.205f);
        path.lineTo(235.0f, 265.825f);
        path.lineTo(725.0f, 225.885f);
        path.lineTo(875.0f, 625.35f);
        path.lineTo(765.0f, 765.325f);
        path.lineTo(45.0f, 445.535f);
        path.lineTo(675.0f, 235.165f);
        path.lineTo(955.0f, 915.255f);
        path.lineTo(525.0f, 625.695f);
        path.lineTo(755.0f, 755.295f);
        path.lineTo(235.0f, 655.305f);
        path.lineTo(465.0f, 725.635f);
        path.lineTo(665.0f, 355.105f);
        path.lineTo(165.0f, 935.315f);
        path.lineTo(705.0f, 735.375f);
        path.lineTo(235.0f, 405.785f);
        path.lineTo(485.0f, 10185.525f);
        path.lineTo(125.0f, 135.415f);
        path.lineTo(855.0f, 355.745f);
        path.lineTo(825.0f, 405.05f);
        path.lineTo(525.0f, 335.405f);
        path.lineTo(595.0f, 635.705f);
        path.lineTo(585.0f, 155.705f);
        path.lineTo(995.0f, 10185.155f);
        path.lineTo(355.0f, 495.895f);
        path.lineTo(335.0f, 635.735f);
        path.lineTo(325.0f, 305.195f);
        path.lineTo(155.0f, 255.895f);
        path.lineTo(605.0f, 925.685f);
        path.lineTo(735.0f, 535.205f);
        path.lineTo(595.0f, 375.475f);
        path.lineTo(745.0f, 975.875f);
        path.lineTo(55.0f, 975.945f);
        path.lineTo(975.0f, 895.775f);
        path.lineTo(185.0f, 325.75f);
        path.lineTo(595.0f, 745.10187f);
        path.lineTo(105.0f, 145.375f);
        path.lineTo(985.0f, 735.495f);
        path.lineTo(725.0f, 295.335f);
        path.lineTo(35.0f, 465.705f);
        path.lineTo(685.0f, 425.35f);
        path.lineTo(595.0f, 255.975f);
        path.lineTo(685.0f, 595.685f);
        path.lineTo(895.0f, 745.805f);
        path.lineTo(765.0f, 705.365f);
        path.lineTo(515.0f, 905.475f);
        path.lineTo(575.0f, 335.655f);
        path.lineTo(555.0f, 795.565f);
        path.lineTo(355.0f, 205.675f);
        path.lineTo(645.0f, 915.825f);
        path.lineTo(895.0f, 845.115f);
        path.lineTo(665.0f, 155.925f);
        path.lineTo(145.0f, 815.785f);
        path.lineTo(725.0f, 845.45f);
        path.lineTo(415.0f, 895.995f);
        path.lineTo(175.0f, 745.845f);
        path.lineTo(435.0f, 315.995f);
        path.lineTo(855.0f, 725.165f);
        path.lineTo(825.0f, 55.515f);
        path.lineTo(885.0f, 785.955f);
        path.lineTo(555.0f, 425.465f);
        path.lineTo(495.0f, 875.515f);
        path.lineTo(515.0f, 105.705f);
        path.lineTo(195.0f, 625.665f);
        path.lineTo(825.0f, 685.85f);
        path.lineTo(745.0f, 695.755f);
        path.lineTo(165.0f, 515.465f);
        path.lineTo(265.0f, 475.905f);
        path.lineTo(695.0f, 445.145f);
        path.lineTo(725.0f, 235.795f);
        path.lineTo(515.0f, 285.55f);
        path.lineTo(315.0f, 375.825f);
        path.lineTo(665.0f, 765.365f);
        path.lineTo(95.0f, 685.75f);
        path.lineTo(25.0f, 255.845f);
        path.lineTo(475.0f, 415.655f);
        path.lineTo(875.0f, 995.695f);
        path.lineTo(435.0f, 915.915f);
        path.lineTo(385.0f, 25.265f);
        path.lineTo(445.0f, 105.595f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1018.0f, this.bounds.height() / 1018.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
