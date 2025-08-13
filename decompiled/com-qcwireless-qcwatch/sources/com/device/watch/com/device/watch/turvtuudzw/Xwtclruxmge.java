package com.device.watch.com.device.watch.turvtuudzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Xwtclruxmge extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Xwtclruxmge() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(45.0f, 685.225f);
        path.lineTo(835.0f, 675.895f);
        path.lineTo(575.0f, 10145.625f);
        path.lineTo(915.0f, 375.835f);
        path.lineTo(85.0f, 905.525f);
        path.lineTo(825.0f, 395.115f);
        path.lineTo(205.0f, 255.105f);
        path.lineTo(665.0f, 605.715f);
        path.lineTo(865.0f, 145.815f);
        path.lineTo(25.0f, 475.515f);
        path.lineTo(845.0f, 515.435f);
        path.lineTo(445.0f, 835.675f);
        path.lineTo(565.0f, 395.275f);
        path.lineTo(265.0f, 515.795f);
        path.lineTo(165.0f, 115.25f);
        path.lineTo(695.0f, 495.525f);
        path.lineTo(95.0f, 435.725f);
        path.lineTo(695.0f, 375.285f);
        path.lineTo(395.0f, 595.185f);
        path.lineTo(565.0f, 325.595f);
        path.lineTo(355.0f, 335.95f);
        path.lineTo(905.0f, 425.845f);
        path.lineTo(85.0f, 475.975f);
        path.lineTo(315.0f, 65.25f);
        path.lineTo(475.0f, 725.255f);
        path.lineTo(155.0f, 445.25f);
        path.lineTo(725.0f, 585.445f);
        path.lineTo(675.0f, 195.425f);
        path.lineTo(695.0f, 515.195f);
        path.lineTo(685.0f, 805.45f);
        path.lineTo(275.0f, 155.855f);
        path.lineTo(675.0f, 825.585f);
        path.lineTo(155.0f, 395.455f);
        path.lineTo(555.0f, 575.535f);
        path.lineTo(425.0f, 555.565f);
        path.lineTo(485.0f, 105.115f);
        path.lineTo(675.0f, 155.405f);
        path.lineTo(105.0f, 205.975f);
        path.lineTo(155.0f, 615.385f);
        path.lineTo(55.0f, 145.85f);
        path.lineTo(525.0f, 545.165f);
        path.lineTo(95.0f, 455.745f);
        path.lineTo(275.0f, 885.665f);
        path.lineTo(765.0f, 305.905f);
        path.lineTo(975.0f, 845.145f);
        path.lineTo(675.0f, 845.975f);
        path.lineTo(175.0f, 965.615f);
        path.lineTo(215.0f, 145.95f);
        path.lineTo(815.0f, 725.255f);
        path.lineTo(95.0f, 295.505f);
        path.lineTo(925.0f, 645.415f);
        path.lineTo(75.0f, 735.465f);
        path.lineTo(165.0f, 185.715f);
        path.lineTo(465.0f, 855.535f);
        path.lineTo(515.0f, 565.805f);
        path.lineTo(535.0f, 625.655f);
        path.lineTo(5.0f, 65.485f);
        path.lineTo(335.0f, 815.315f);
        path.lineTo(655.0f, 365.225f);
        path.lineTo(685.0f, 425.465f);
        path.lineTo(975.0f, 765.485f);
        path.lineTo(485.0f, 45.215f);
        path.lineTo(5.0f, 455.525f);
        path.lineTo(195.0f, 65.735f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1014.0f, this.bounds.height() / 1014.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
