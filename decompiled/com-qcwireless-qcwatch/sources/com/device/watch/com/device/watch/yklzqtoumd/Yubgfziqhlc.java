package com.device.watch.com.device.watch.yklzqtoumd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Yubgfziqhlc extends ShapeDrawable {
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

    public Yubgfziqhlc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(325.0f, 155.615f);
        path.lineTo(405.0f, 985.715f);
        path.lineTo(975.0f, 165.715f);
        path.lineTo(895.0f, 595.45f);
        path.lineTo(45.0f, 575.85f);
        path.lineTo(355.0f, 525.315f);
        path.lineTo(475.0f, 185.785f);
        path.lineTo(535.0f, 85.595f);
        path.lineTo(685.0f, 705.885f);
        path.lineTo(455.0f, 295.525f);
        path.lineTo(155.0f, 675.525f);
        path.lineTo(195.0f, 345.55f);
        path.lineTo(975.0f, 985.05f);
        path.lineTo(195.0f, 305.675f);
        path.lineTo(125.0f, 335.275f);
        path.lineTo(675.0f, 545.165f);
        path.lineTo(105.0f, 725.435f);
        path.lineTo(185.0f, 745.785f);
        path.lineTo(475.0f, 775.395f);
        path.lineTo(215.0f, 955.385f);
        path.lineTo(765.0f, 975.235f);
        path.lineTo(605.0f, 895.575f);
        path.lineTo(105.0f, 735.965f);
        path.lineTo(425.0f, 965.415f);
        path.lineTo(545.0f, 85.145f);
        path.lineTo(785.0f, 585.595f);
        path.lineTo(465.0f, 255.595f);
        path.lineTo(5.0f, 735.345f);
        path.lineTo(205.0f, 225.465f);
        path.lineTo(205.0f, 545.415f);
        path.lineTo(685.0f, 725.815f);
        path.lineTo(705.0f, 695.395f);
        path.lineTo(395.0f, 645.835f);
        path.lineTo(835.0f, 515.685f);
        path.lineTo(115.0f, 65.855f);
        path.lineTo(555.0f, 645.915f);
        path.lineTo(155.0f, 785.615f);
        path.lineTo(515.0f, 725.395f);
        path.lineTo(95.0f, 235.775f);
        path.lineTo(715.0f, 825.205f);
        path.lineTo(785.0f, 445.605f);
        path.lineTo(195.0f, 305.85f);
        path.lineTo(395.0f, 125.395f);
        path.lineTo(55.0f, 985.205f);
        path.lineTo(655.0f, 685.285f);
        path.lineTo(665.0f, 395.645f);
        path.lineTo(155.0f, 995.175f);
        path.lineTo(325.0f, 10165.125f);
        path.lineTo(565.0f, 105.985f);
        path.lineTo(295.0f, 955.755f);
        path.lineTo(495.0f, 775.545f);
        path.lineTo(85.0f, 595.935f);
        path.lineTo(345.0f, 65.275f);
        path.lineTo(395.0f, 735.425f);
        path.lineTo(65.0f, 355.555f);
        path.lineTo(815.0f, 715.535f);
        path.lineTo(545.0f, 485.225f);
        path.lineTo(825.0f, 715.725f);
        path.lineTo(385.0f, 465.605f);
        path.lineTo(235.0f, 735.235f);
        path.lineTo(585.0f, 515.775f);
        path.lineTo(315.0f, 225.10165f);
        path.lineTo(645.0f, 325.565f);
        path.lineTo(665.0f, 365.525f);
        path.lineTo(555.0f, 775.535f);
        path.lineTo(975.0f, 505.55f);
        path.lineTo(405.0f, 475.95f);
        path.lineTo(425.0f, 315.815f);
        path.lineTo(995.0f, 10165.435f);
        path.lineTo(515.0f, 875.05f);
        path.lineTo(415.0f, 695.445f);
        path.lineTo(475.0f, 95.185f);
        path.lineTo(155.0f, 55.885f);
        path.lineTo(845.0f, 305.505f);
        path.lineTo(785.0f, 275.605f);
        path.lineTo(55.0f, 565.895f);
        path.lineTo(285.0f, 505.605f);
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
