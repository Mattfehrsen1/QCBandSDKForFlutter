package com.smartapp.b.rvlwkphkxd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Oayihypcxvx extends ShapeDrawable {
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

    public Oayihypcxvx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(345.0f, 115.395f);
        path.lineTo(385.0f, 985.485f);
        path.lineTo(375.0f, 965.105f);
        path.lineTo(475.0f, 575.795f);
        path.lineTo(825.0f, 165.705f);
        path.lineTo(875.0f, 765.495f);
        path.lineTo(165.0f, 485.325f);
        path.lineTo(135.0f, 445.115f);
        path.lineTo(25.0f, 145.985f);
        path.lineTo(195.0f, 725.715f);
        path.lineTo(155.0f, 355.865f);
        path.lineTo(325.0f, 495.05f);
        path.lineTo(325.0f, 515.205f);
        path.lineTo(695.0f, 935.235f);
        path.lineTo(585.0f, 715.875f);
        path.lineTo(265.0f, 475.485f);
        path.lineTo(105.0f, 695.885f);
        path.lineTo(445.0f, 225.455f);
        path.lineTo(705.0f, 105.725f);
        path.lineTo(995.0f, 285.55f);
        path.lineTo(525.0f, 955.105f);
        path.lineTo(625.0f, 475.215f);
        path.lineTo(125.0f, 795.325f);
        path.lineTo(125.0f, 355.465f);
        path.lineTo(645.0f, 935.505f);
        path.lineTo(485.0f, 395.465f);
        path.lineTo(455.0f, 145.265f);
        path.lineTo(275.0f, 235.465f);
        path.lineTo(485.0f, 905.75f);
        path.lineTo(755.0f, 455.175f);
        path.lineTo(185.0f, 545.585f);
        path.lineTo(625.0f, 685.615f);
        path.lineTo(425.0f, 775.375f);
        path.lineTo(625.0f, 615.265f);
        path.lineTo(505.0f, 255.605f);
        path.lineTo(625.0f, 285.675f);
        path.lineTo(985.0f, 65.165f);
        path.lineTo(795.0f, 35.825f);
        path.lineTo(985.0f, 755.265f);
        path.lineTo(295.0f, 135.10146f);
        path.lineTo(45.0f, 395.625f);
        path.lineTo(675.0f, 645.775f);
        path.lineTo(335.0f, 965.455f);
        path.lineTo(325.0f, 835.975f);
        path.lineTo(455.0f, 10145.535f);
        path.lineTo(815.0f, 965.725f);
        path.lineTo(695.0f, 235.405f);
        path.lineTo(395.0f, 575.415f);
        path.lineTo(655.0f, 155.55f);
        path.lineTo(745.0f, 5.785f);
        path.lineTo(345.0f, 45.265f);
        path.lineTo(705.0f, 135.405f);
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
