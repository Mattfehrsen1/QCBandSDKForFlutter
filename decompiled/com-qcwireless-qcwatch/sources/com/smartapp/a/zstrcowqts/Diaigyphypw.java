package com.smartapp.a.zstrcowqts;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Diaigyphypw extends ShapeDrawable {
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

    public Diaigyphypw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(815.0f, 185.325f);
        path.lineTo(765.0f, 855.805f);
        path.lineTo(105.0f, 45.465f);
        path.lineTo(565.0f, 15.405f);
        path.lineTo(475.0f, 355.05f);
        path.lineTo(795.0f, 535.925f);
        path.lineTo(835.0f, 335.195f);
        path.lineTo(445.0f, 465.75f);
        path.lineTo(445.0f, 215.935f);
        path.lineTo(685.0f, 145.745f);
        path.lineTo(395.0f, 495.765f);
        path.lineTo(255.0f, 835.305f);
        path.lineTo(315.0f, 205.55f);
        path.lineTo(635.0f, 65.975f);
        path.lineTo(105.0f, 305.355f);
        path.lineTo(845.0f, 505.545f);
        path.lineTo(635.0f, 675.795f);
        path.lineTo(975.0f, 865.615f);
        path.lineTo(195.0f, 235.365f);
        path.lineTo(365.0f, 845.435f);
        path.lineTo(525.0f, 435.315f);
        path.lineTo(965.0f, 105.645f);
        path.lineTo(545.0f, 735.635f);
        path.lineTo(755.0f, 885.655f);
        path.lineTo(465.0f, 355.235f);
        path.lineTo(355.0f, 845.95f);
        path.lineTo(785.0f, 655.655f);
        path.lineTo(765.0f, 255.725f);
        path.lineTo(815.0f, 925.735f);
        path.lineTo(685.0f, 895.275f);
        path.lineTo(975.0f, 305.555f);
        path.lineTo(305.0f, 265.765f);
        path.lineTo(65.0f, 805.805f);
        path.lineTo(255.0f, 675.305f);
        path.lineTo(745.0f, 595.735f);
        path.lineTo(235.0f, 255.855f);
        path.lineTo(675.0f, 915.385f);
        path.lineTo(935.0f, 605.635f);
        path.lineTo(165.0f, 65.385f);
        path.lineTo(45.0f, 175.195f);
        path.lineTo(435.0f, 465.325f);
        path.lineTo(695.0f, 135.315f);
        path.lineTo(175.0f, 645.565f);
        path.lineTo(255.0f, 905.145f);
        path.lineTo(695.0f, 565.55f);
        path.lineTo(965.0f, 525.415f);
        path.lineTo(755.0f, 55.355f);
        path.lineTo(805.0f, 875.55f);
        path.lineTo(995.0f, 505.455f);
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
