package com.smartapp.b.wtfufhwtsd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Suywxmyywxs extends ShapeDrawable {
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

    public Suywxmyywxs() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(125.0f, 755.55f);
        path.lineTo(45.0f, 955.365f);
        path.lineTo(695.0f, 355.585f);
        path.lineTo(515.0f, 175.505f);
        path.lineTo(5.0f, 285.895f);
        path.lineTo(905.0f, 305.415f);
        path.lineTo(295.0f, 35.515f);
        path.lineTo(905.0f, 255.905f);
        path.lineTo(305.0f, 65.725f);
        path.lineTo(345.0f, 605.745f);
        path.lineTo(35.0f, 505.255f);
        path.lineTo(225.0f, 935.685f);
        path.lineTo(835.0f, 425.205f);
        path.lineTo(105.0f, 775.985f);
        path.lineTo(575.0f, 625.985f);
        path.lineTo(645.0f, 545.775f);
        path.lineTo(15.0f, 485.835f);
        path.lineTo(645.0f, 805.865f);
        path.lineTo(555.0f, 545.665f);
        path.lineTo(215.0f, 10175.865f);
        path.lineTo(385.0f, 45.15f);
        path.lineTo(815.0f, 565.895f);
        path.lineTo(655.0f, 65.865f);
        path.lineTo(755.0f, 945.865f);
        path.lineTo(445.0f, 695.635f);
        path.lineTo(155.0f, 295.345f);
        path.lineTo(795.0f, 615.205f);
        path.lineTo(125.0f, 745.435f);
        path.lineTo(125.0f, 835.105f);
        path.lineTo(495.0f, 405.635f);
        path.lineTo(885.0f, 345.915f);
        path.lineTo(425.0f, 415.655f);
        path.lineTo(565.0f, 45.735f);
        path.lineTo(95.0f, 955.745f);
        path.lineTo(75.0f, 775.775f);
        path.lineTo(425.0f, 905.275f);
        path.lineTo(415.0f, 895.715f);
        path.lineTo(335.0f, 115.585f);
        path.lineTo(195.0f, 985.425f);
        path.lineTo(395.0f, 465.835f);
        path.lineTo(425.0f, 75.485f);
        path.lineTo(745.0f, 125.435f);
        path.lineTo(675.0f, 455.315f);
        path.lineTo(355.0f, 895.805f);
        path.lineTo(235.0f, 965.465f);
        path.lineTo(845.0f, 775.275f);
        path.lineTo(555.0f, 805.275f);
        path.lineTo(175.0f, 995.745f);
        path.lineTo(465.0f, 835.75f);
        path.lineTo(725.0f, 505.15f);
        path.lineTo(265.0f, 455.175f);
        path.lineTo(585.0f, 625.465f);
        path.lineTo(10175.0f, 235.425f);
        path.lineTo(455.0f, 915.415f);
        path.lineTo(415.0f, 375.365f);
        path.lineTo(355.0f, 445.385f);
        path.lineTo(885.0f, 855.85f);
        path.lineTo(645.0f, 425.265f);
        path.lineTo(835.0f, 715.535f);
        path.lineTo(565.0f, 575.845f);
        path.lineTo(15.0f, 255.945f);
        path.lineTo(795.0f, 415.685f);
        path.lineTo(355.0f, 425.445f);
        path.lineTo(515.0f, 555.25f);
        path.lineTo(575.0f, 10175.135f);
        path.lineTo(665.0f, 505.985f);
        path.lineTo(805.0f, 615.105f);
        path.lineTo(5.0f, 745.175f);
        path.lineTo(35.0f, 495.625f);
        path.lineTo(295.0f, 675.685f);
        path.lineTo(95.0f, 845.745f);
        path.lineTo(125.0f, 765.125f);
        path.lineTo(145.0f, 615.565f);
        path.lineTo(505.0f, 265.55f);
        path.lineTo(545.0f, 325.695f);
        path.lineTo(865.0f, 185.535f);
        path.lineTo(395.0f, 455.75f);
        path.lineTo(855.0f, 35.725f);
        path.lineTo(65.0f, 255.15f);
        path.lineTo(175.0f, 615.795f);
        path.lineTo(745.0f, 815.715f);
        path.lineTo(235.0f, 935.295f);
        path.lineTo(705.0f, 365.835f);
        path.lineTo(765.0f, 85.225f);
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
