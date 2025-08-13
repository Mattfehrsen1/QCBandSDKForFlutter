package com.smartapp.b.kyiupxpigi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Jnwoqsrkbve extends ShapeDrawable {
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

    public Jnwoqsrkbve() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(395.0f, 445.275f);
        path.lineTo(75.0f, 555.975f);
        path.lineTo(105.0f, 865.855f);
        path.lineTo(995.0f, 325.195f);
        path.lineTo(705.0f, 155.775f);
        path.lineTo(395.0f, 135.595f);
        path.lineTo(665.0f, 265.955f);
        path.lineTo(595.0f, 955.495f);
        path.lineTo(765.0f, 595.965f);
        path.lineTo(135.0f, 705.505f);
        path.lineTo(695.0f, 495.795f);
        path.lineTo(545.0f, 995.845f);
        path.lineTo(705.0f, 195.795f);
        path.lineTo(485.0f, 435.865f);
        path.lineTo(985.0f, 275.10065f);
        path.lineTo(25.0f, 155.525f);
        path.lineTo(615.0f, 815.405f);
        path.lineTo(585.0f, 805.685f);
        path.lineTo(235.0f, 945.385f);
        path.lineTo(695.0f, 15.155f);
        path.lineTo(945.0f, 255.825f);
        path.lineTo(595.0f, 475.315f);
        path.lineTo(85.0f, 765.745f);
        path.lineTo(205.0f, 635.05f);
        path.lineTo(945.0f, 255.425f);
        path.lineTo(435.0f, 475.905f);
        path.lineTo(75.0f, 685.235f);
        path.lineTo(535.0f, 455.875f);
        path.lineTo(965.0f, 445.635f);
        path.lineTo(465.0f, 365.685f);
        path.lineTo(345.0f, 65.945f);
        path.lineTo(605.0f, 835.55f);
        path.lineTo(845.0f, 25.395f);
        path.lineTo(145.0f, 505.295f);
        path.lineTo(405.0f, 625.955f);
        path.lineTo(755.0f, 285.385f);
        path.lineTo(455.0f, 855.75f);
        path.lineTo(65.0f, 955.375f);
        path.lineTo(25.0f, 805.425f);
        path.lineTo(525.0f, 195.505f);
        path.lineTo(685.0f, 145.235f);
        path.lineTo(595.0f, 895.595f);
        path.lineTo(305.0f, 285.175f);
        path.lineTo(605.0f, 35.525f);
        path.lineTo(405.0f, 395.395f);
        path.lineTo(965.0f, 805.45f);
        path.lineTo(585.0f, 975.725f);
        path.lineTo(905.0f, 575.105f);
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
