package com.smartapp.b.dnefzlpwkz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Gauklahvoqa extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Gauklahvoqa() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(725.0f, 925.695f);
        path.lineTo(985.0f, 205.995f);
        path.lineTo(145.0f, 285.905f);
        path.lineTo(285.0f, 485.05f);
        path.lineTo(985.0f, 775.145f);
        path.lineTo(305.0f, 935.715f);
        path.lineTo(895.0f, 255.495f);
        path.lineTo(545.0f, 685.195f);
        path.lineTo(385.0f, 825.185f);
        path.lineTo(815.0f, 15.625f);
        path.lineTo(75.0f, 555.795f);
        path.lineTo(665.0f, 10065.805f);
        path.lineTo(775.0f, 695.865f);
        path.lineTo(765.0f, 265.95f);
        path.lineTo(895.0f, 635.35f);
        path.lineTo(455.0f, 585.685f);
        path.lineTo(565.0f, 555.605f);
        path.lineTo(105.0f, 615.955f);
        path.lineTo(675.0f, 675.595f);
        path.lineTo(755.0f, 235.985f);
        path.lineTo(415.0f, 715.435f);
        path.lineTo(195.0f, 675.85f);
        path.lineTo(635.0f, 75.175f);
        path.lineTo(25.0f, 255.405f);
        path.lineTo(585.0f, 605.05f);
        path.lineTo(285.0f, 515.935f);
        path.lineTo(305.0f, 225.195f);
        path.lineTo(335.0f, 165.755f);
        path.lineTo(785.0f, 895.765f);
        path.lineTo(205.0f, 945.45f);
        path.lineTo(395.0f, 5.105f);
        path.lineTo(475.0f, 935.605f);
        path.lineTo(265.0f, 35.895f);
        path.lineTo(25.0f, 565.585f);
        path.lineTo(885.0f, 285.375f);
        path.lineTo(725.0f, 385.235f);
        path.lineTo(555.0f, 455.965f);
        path.lineTo(255.0f, 915.225f);
        path.lineTo(455.0f, 625.125f);
        path.lineTo(485.0f, 385.755f);
        path.lineTo(945.0f, 125.105f);
        path.lineTo(655.0f, 25.485f);
        path.lineTo(915.0f, 455.555f);
        path.lineTo(715.0f, 395.705f);
        path.lineTo(145.0f, 65.235f);
        path.lineTo(155.0f, 35.705f);
        path.lineTo(825.0f, 285.715f);
        path.lineTo(185.0f, 515.675f);
        path.lineTo(625.0f, 655.685f);
        path.lineTo(335.0f, 825.15f);
        path.lineTo(875.0f, 435.365f);
        path.lineTo(935.0f, 835.745f);
        path.lineTo(275.0f, 125.765f);
        path.lineTo(515.0f, 945.715f);
        path.lineTo(10065.0f, 965.185f);
        path.lineTo(165.0f, 25.695f);
        path.lineTo(10065.0f, 985.335f);
        path.lineTo(305.0f, 715.705f);
        path.lineTo(75.0f, 995.465f);
        path.lineTo(715.0f, 415.345f);
        path.lineTo(305.0f, 385.945f);
        path.lineTo(165.0f, 145.495f);
        path.lineTo(425.0f, 115.185f);
        path.lineTo(345.0f, 525.765f);
        path.lineTo(965.0f, 725.255f);
        path.lineTo(25.0f, 935.305f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
