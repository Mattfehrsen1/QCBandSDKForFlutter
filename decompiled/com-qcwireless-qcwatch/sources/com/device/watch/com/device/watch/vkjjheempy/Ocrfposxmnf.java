package com.device.watch.com.device.watch.vkjjheempy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Ocrfposxmnf extends ShapeDrawable {
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

    public Ocrfposxmnf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 645.965f);
        path.lineTo(325.0f, 995.295f);
        path.lineTo(215.0f, 915.1019f);
        path.lineTo(645.0f, 175.625f);
        path.lineTo(25.0f, 375.195f);
        path.lineTo(155.0f, 785.875f);
        path.lineTo(935.0f, 955.645f);
        path.lineTo(925.0f, 835.585f);
        path.lineTo(835.0f, 155.745f);
        path.lineTo(345.0f, 635.575f);
        path.lineTo(365.0f, 755.495f);
        path.lineTo(775.0f, 825.755f);
        path.lineTo(585.0f, 955.145f);
        path.lineTo(175.0f, 735.555f);
        path.lineTo(225.0f, 395.595f);
        path.lineTo(995.0f, 975.95f);
        path.lineTo(685.0f, 935.535f);
        path.lineTo(305.0f, 805.665f);
        path.lineTo(315.0f, 875.955f);
        path.lineTo(365.0f, 725.155f);
        path.lineTo(595.0f, 745.125f);
        path.lineTo(935.0f, 325.935f);
        path.lineTo(135.0f, 185.955f);
        path.lineTo(85.0f, 65.385f);
        path.lineTo(645.0f, 945.145f);
        path.lineTo(385.0f, 495.815f);
        path.lineTo(885.0f, 525.815f);
        path.lineTo(515.0f, 805.865f);
        path.lineTo(745.0f, 965.765f);
        path.lineTo(625.0f, 525.785f);
        path.lineTo(385.0f, 595.825f);
        path.lineTo(695.0f, 475.105f);
        path.lineTo(65.0f, 75.815f);
        path.lineTo(405.0f, 445.525f);
        path.lineTo(605.0f, 595.395f);
        path.lineTo(125.0f, 675.375f);
        path.lineTo(785.0f, 105.35f);
        path.lineTo(885.0f, 515.815f);
        path.lineTo(825.0f, 525.475f);
        path.lineTo(995.0f, 705.315f);
        path.lineTo(665.0f, 95.835f);
        path.lineTo(785.0f, 285.615f);
        path.lineTo(915.0f, 425.15f);
        path.lineTo(715.0f, 295.585f);
        path.lineTo(785.0f, 355.305f);
        path.lineTo(815.0f, 345.535f);
        path.lineTo(375.0f, 445.725f);
        path.lineTo(795.0f, 385.455f);
        path.lineTo(705.0f, 725.345f);
        path.lineTo(235.0f, 235.65f);
        path.lineTo(775.0f, 735.505f);
        path.lineTo(455.0f, 445.915f);
        path.lineTo(715.0f, 205.25f);
        path.lineTo(55.0f, 205.295f);
        path.lineTo(985.0f, 435.895f);
        path.lineTo(375.0f, 885.225f);
        path.lineTo(45.0f, 665.15f);
        path.lineTo(645.0f, 445.125f);
        path.lineTo(535.0f, 275.695f);
        path.lineTo(595.0f, 695.05f);
        path.lineTo(665.0f, 355.55f);
        path.lineTo(385.0f, 475.435f);
        path.lineTo(765.0f, 635.945f);
        path.lineTo(605.0f, 185.915f);
        path.lineTo(605.0f, 185.285f);
        path.lineTo(45.0f, 485.615f);
        path.lineTo(85.0f, 75.295f);
        path.lineTo(265.0f, 275.05f);
        path.lineTo(145.0f, 685.645f);
        path.lineTo(385.0f, 685.835f);
        path.lineTo(585.0f, 185.915f);
        path.lineTo(165.0f, 115.415f);
        path.lineTo(175.0f, 305.855f);
        path.lineTo(255.0f, 615.145f);
        path.lineTo(575.0f, 405.915f);
        path.lineTo(95.0f, 225.935f);
        path.lineTo(55.0f, 605.155f);
        path.lineTo(495.0f, 385.655f);
        path.lineTo(915.0f, 215.955f);
        path.lineTo(735.0f, 845.985f);
        path.lineTo(605.0f, 505.585f);
        path.lineTo(815.0f, 525.665f);
        path.lineTo(215.0f, 265.375f);
        path.lineTo(515.0f, 645.505f);
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
