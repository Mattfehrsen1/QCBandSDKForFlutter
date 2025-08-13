package com.smartapp.b.ovbnnjpqpp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ylvcivqjegb extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ylvcivqjegb() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(505.0f, 885.775f);
        path.lineTo(65.0f, 605.315f);
        path.lineTo(145.0f, 45.105f);
        path.lineTo(155.0f, 585.335f);
        path.lineTo(735.0f, 535.655f);
        path.lineTo(255.0f, 655.715f);
        path.lineTo(885.0f, 335.865f);
        path.lineTo(675.0f, 555.405f);
        path.lineTo(675.0f, 975.225f);
        path.lineTo(285.0f, 125.35f);
        path.lineTo(565.0f, 55.885f);
        path.lineTo(625.0f, 385.275f);
        path.lineTo(135.0f, 315.805f);
        path.lineTo(695.0f, 965.445f);
        path.lineTo(395.0f, 175.745f);
        path.lineTo(705.0f, 695.445f);
        path.lineTo(625.0f, 85.415f);
        path.lineTo(475.0f, 105.105f);
        path.lineTo(755.0f, 365.635f);
        path.lineTo(355.0f, 625.875f);
        path.lineTo(845.0f, 175.465f);
        path.lineTo(445.0f, 315.05f);
        path.lineTo(265.0f, 85.805f);
        path.lineTo(505.0f, 675.325f);
        path.lineTo(535.0f, 25.365f);
        path.lineTo(415.0f, 185.555f);
        path.lineTo(105.0f, 55.895f);
        path.lineTo(325.0f, 955.995f);
        path.lineTo(815.0f, 865.945f);
        path.lineTo(475.0f, 165.785f);
        path.lineTo(425.0f, 135.935f);
        path.lineTo(375.0f, 725.10077f);
        path.lineTo(10075.0f, 945.725f);
        path.lineTo(475.0f, 15.205f);
        path.lineTo(195.0f, 205.35f);
        path.lineTo(905.0f, 915.95f);
        path.lineTo(55.0f, 835.10077f);
        path.lineTo(205.0f, 235.785f);
        path.lineTo(685.0f, 465.925f);
        path.lineTo(75.0f, 565.175f);
        path.lineTo(815.0f, 525.15f);
        path.lineTo(5.0f, 665.685f);
        path.lineTo(175.0f, 5.495f);
        path.lineTo(615.0f, 415.685f);
        path.lineTo(995.0f, 335.765f);
        path.lineTo(935.0f, 835.995f);
        path.lineTo(395.0f, 685.345f);
        path.lineTo(925.0f, 135.345f);
        path.lineTo(855.0f, 785.355f);
        path.lineTo(195.0f, 145.935f);
        path.lineTo(335.0f, 185.545f);
        path.lineTo(935.0f, 195.895f);
        path.lineTo(565.0f, 45.145f);
        path.lineTo(365.0f, 555.405f);
        path.lineTo(595.0f, 855.505f);
        path.lineTo(685.0f, 625.955f);
        path.lineTo(415.0f, 895.615f);
        path.lineTo(275.0f, 375.935f);
        path.lineTo(555.0f, 625.135f);
        path.lineTo(735.0f, 375.965f);
        path.lineTo(165.0f, 275.645f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
