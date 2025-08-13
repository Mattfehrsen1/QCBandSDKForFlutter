package com.device.watch.com.device.watch.cpowbcedid;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes.dex */
public class Wulyoraojko extends ShapeDrawable {
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

    public Wulyoraojko() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(405.0f, 905.315f);
        path.lineTo(825.0f, 545.655f);
        path.lineTo(735.0f, 695.10187f);
        path.lineTo(285.0f, 165.525f);
        path.lineTo(205.0f, 125.10185f);
        path.lineTo(995.0f, 545.335f);
        path.lineTo(355.0f, 825.765f);
        path.lineTo(75.0f, 845.335f);
        path.lineTo(545.0f, 135.145f);
        path.lineTo(685.0f, 705.325f);
        path.lineTo(45.0f, 285.155f);
        path.lineTo(385.0f, 415.275f);
        path.lineTo(315.0f, 285.655f);
        path.lineTo(205.0f, 715.265f);
        path.lineTo(225.0f, 465.175f);
        path.lineTo(275.0f, 735.395f);
        path.lineTo(485.0f, 695.585f);
        path.lineTo(485.0f, 675.955f);
        path.lineTo(355.0f, 525.775f);
        path.lineTo(825.0f, 175.745f);
        path.lineTo(475.0f, 95.95f);
        path.lineTo(15.0f, 535.295f);
        path.lineTo(815.0f, 765.665f);
        path.lineTo(615.0f, 115.315f);
        path.lineTo(665.0f, 315.315f);
        path.lineTo(695.0f, 665.925f);
        path.lineTo(465.0f, 475.145f);
        path.lineTo(875.0f, 565.55f);
        path.lineTo(45.0f, 305.985f);
        path.lineTo(725.0f, 65.165f);
        path.lineTo(615.0f, 645.505f);
        path.lineTo(395.0f, 315.455f);
        path.lineTo(415.0f, 385.315f);
        path.lineTo(975.0f, 575.715f);
        path.lineTo(775.0f, 735.335f);
        path.lineTo(485.0f, 525.545f);
        path.lineTo(675.0f, 395.555f);
        path.lineTo(425.0f, 625.10187f);
        path.lineTo(555.0f, 45.625f);
        path.lineTo(275.0f, 715.495f);
        path.lineTo(725.0f, 685.235f);
        path.lineTo(455.0f, 835.965f);
        path.lineTo(555.0f, 505.775f);
        path.lineTo(175.0f, 405.805f);
        path.lineTo(85.0f, 935.95f);
        path.lineTo(585.0f, 85.85f);
        path.lineTo(555.0f, 295.105f);
        path.lineTo(565.0f, 595.65f);
        path.lineTo(515.0f, 825.745f);
        path.lineTo(915.0f, 465.765f);
        path.lineTo(315.0f, 275.575f);
        path.lineTo(55.0f, 945.825f);
        path.lineTo(875.0f, 625.375f);
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
