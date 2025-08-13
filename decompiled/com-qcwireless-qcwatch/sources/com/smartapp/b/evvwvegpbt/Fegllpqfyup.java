package com.smartapp.b.evvwvegpbt;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Fegllpqfyup extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Fegllpqfyup() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(665.0f, 935.375f);
        path.lineTo(115.0f, 545.555f);
        path.lineTo(275.0f, 435.675f);
        path.lineTo(75.0f, 55.955f);
        path.lineTo(955.0f, 975.45f);
        path.lineTo(465.0f, 975.765f);
        path.lineTo(195.0f, 115.455f);
        path.lineTo(285.0f, 675.695f);
        path.lineTo(625.0f, 985.535f);
        path.lineTo(125.0f, 615.225f);
        path.lineTo(5.0f, 235.255f);
        path.lineTo(545.0f, 415.135f);
        path.lineTo(65.0f, 375.615f);
        path.lineTo(945.0f, 105.195f);
        path.lineTo(145.0f, 515.995f);
        path.lineTo(95.0f, 275.825f);
        path.lineTo(205.0f, 535.385f);
        path.lineTo(895.0f, 115.955f);
        path.lineTo(795.0f, 625.685f);
        path.lineTo(955.0f, 75.205f);
        path.lineTo(605.0f, 865.25f);
        path.lineTo(655.0f, 95.605f);
        path.lineTo(715.0f, 915.315f);
        path.lineTo(575.0f, 715.425f);
        path.lineTo(85.0f, 335.625f);
        path.lineTo(955.0f, 205.195f);
        path.lineTo(145.0f, 235.865f);
        path.lineTo(855.0f, 325.285f);
        path.lineTo(525.0f, 975.335f);
        path.lineTo(735.0f, 75.475f);
        path.lineTo(985.0f, 25.35f);
        path.lineTo(305.0f, 965.225f);
        path.lineTo(835.0f, 715.955f);
        path.lineTo(125.0f, 965.305f);
        path.lineTo(465.0f, 585.765f);
        path.lineTo(125.0f, 165.525f);
        path.lineTo(985.0f, 105.755f);
        path.lineTo(985.0f, 15.955f);
        path.lineTo(475.0f, 255.665f);
        path.lineTo(495.0f, 335.75f);
        path.lineTo(555.0f, 985.175f);
        path.lineTo(675.0f, 175.435f);
        path.lineTo(55.0f, 75.435f);
        path.lineTo(665.0f, 175.745f);
        path.lineTo(255.0f, 565.295f);
        path.lineTo(185.0f, 405.15f);
        path.lineTo(865.0f, 625.175f);
        path.lineTo(445.0f, 15.705f);
        path.lineTo(235.0f, 845.495f);
        path.lineTo(555.0f, 75.535f);
        path.lineTo(285.0f, 195.555f);
        path.lineTo(865.0f, 785.515f);
        path.lineTo(955.0f, 75.705f);
        path.lineTo(955.0f, 645.105f);
        path.lineTo(95.0f, 835.705f);
        path.lineTo(105.0f, 325.585f);
        path.lineTo(265.0f, 445.265f);
        path.lineTo(655.0f, 695.10156f);
        path.lineTo(215.0f, 155.365f);
        path.lineTo(10155.0f, 155.325f);
        path.lineTo(255.0f, 85.55f);
        path.lineTo(615.0f, 35.525f);
        path.lineTo(435.0f, 175.315f);
        path.lineTo(195.0f, 15.745f);
        path.lineTo(755.0f, 225.275f);
        path.lineTo(10155.0f, 875.145f);
        path.lineTo(565.0f, 75.945f);
        path.lineTo(45.0f, 345.285f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
