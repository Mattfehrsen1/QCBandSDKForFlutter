package com.smartapp.b.fxqukyurem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Lqioaifzdok extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1004;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lqioaifzdok() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(295.0f, 655.375f);
        path.lineTo(175.0f, 665.165f);
        path.lineTo(115.0f, 945.315f);
        path.lineTo(975.0f, 95.575f);
        path.lineTo(385.0f, 155.355f);
        path.lineTo(725.0f, 615.955f);
        path.lineTo(895.0f, 65.95f);
        path.lineTo(505.0f, 575.95f);
        path.lineTo(575.0f, 835.675f);
        path.lineTo(85.0f, 935.985f);
        path.lineTo(285.0f, 105.305f);
        path.lineTo(565.0f, 155.155f);
        path.lineTo(915.0f, 865.215f);
        path.lineTo(645.0f, 595.865f);
        path.lineTo(695.0f, 615.65f);
        path.lineTo(685.0f, 10045.915f);
        path.lineTo(425.0f, 235.455f);
        path.lineTo(15.0f, 825.435f);
        path.lineTo(675.0f, 825.865f);
        path.lineTo(35.0f, 145.165f);
        path.lineTo(865.0f, 935.95f);
        path.lineTo(715.0f, 105.15f);
        path.lineTo(115.0f, 835.595f);
        path.lineTo(845.0f, 935.45f);
        path.lineTo(585.0f, 215.265f);
        path.lineTo(785.0f, 185.465f);
        path.lineTo(385.0f, 115.10045f);
        path.lineTo(225.0f, 295.535f);
        path.lineTo(135.0f, 905.545f);
        path.lineTo(315.0f, 695.945f);
        path.lineTo(725.0f, 25.295f);
        path.lineTo(835.0f, 115.615f);
        path.lineTo(115.0f, 835.485f);
        path.lineTo(125.0f, 855.975f);
        path.lineTo(145.0f, 395.735f);
        path.lineTo(425.0f, 155.485f);
        path.lineTo(415.0f, 815.725f);
        path.lineTo(375.0f, 545.845f);
        path.lineTo(455.0f, 115.955f);
        path.lineTo(195.0f, 445.10046f);
        path.lineTo(725.0f, 865.555f);
        path.lineTo(805.0f, 865.275f);
        path.lineTo(985.0f, 125.185f);
        path.lineTo(825.0f, 195.85f);
        path.lineTo(855.0f, 355.585f);
        path.lineTo(925.0f, 65.625f);
        path.lineTo(975.0f, 105.805f);
        path.lineTo(725.0f, 375.825f);
        path.lineTo(285.0f, 495.275f);
        path.lineTo(265.0f, 595.945f);
        path.lineTo(255.0f, 695.85f);
        path.lineTo(715.0f, 585.805f);
        path.lineTo(965.0f, 635.15f);
        path.lineTo(775.0f, 725.955f);
        path.lineTo(475.0f, 45.485f);
        path.lineTo(10045.0f, 155.755f);
        path.lineTo(435.0f, 845.825f);
        path.lineTo(205.0f, 825.255f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1004.0f, this.bounds.height() / 1004.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
