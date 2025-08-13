package com.device.watch.com.device.watch.lnzabjurod;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Okepzgxpqaf extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Okepzgxpqaf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(155.0f, 335.115f);
        path.lineTo(705.0f, 475.455f);
        path.lineTo(375.0f, 195.825f);
        path.lineTo(955.0f, 325.585f);
        path.lineTo(115.0f, 905.75f);
        path.lineTo(915.0f, 5.345f);
        path.lineTo(415.0f, 635.885f);
        path.lineTo(195.0f, 635.825f);
        path.lineTo(105.0f, 55.465f);
        path.lineTo(635.0f, 105.10095f);
        path.lineTo(545.0f, 955.435f);
        path.lineTo(815.0f, 645.615f);
        path.lineTo(265.0f, 155.955f);
        path.lineTo(415.0f, 975.195f);
        path.lineTo(315.0f, 805.415f);
        path.lineTo(155.0f, 465.405f);
        path.lineTo(155.0f, 855.645f);
        path.lineTo(545.0f, 375.955f);
        path.lineTo(875.0f, 45.545f);
        path.lineTo(135.0f, 695.25f);
        path.lineTo(315.0f, 395.905f);
        path.lineTo(85.0f, 105.335f);
        path.lineTo(935.0f, 65.255f);
        path.lineTo(985.0f, 265.05f);
        path.lineTo(915.0f, 715.465f);
        path.lineTo(855.0f, 805.615f);
        path.lineTo(565.0f, 10095.585f);
        path.lineTo(105.0f, 375.125f);
        path.lineTo(445.0f, 305.555f);
        path.lineTo(185.0f, 675.195f);
        path.lineTo(65.0f, 55.05f);
        path.lineTo(415.0f, 205.555f);
        path.lineTo(265.0f, 935.395f);
        path.lineTo(235.0f, 335.45f);
        path.lineTo(695.0f, 815.85f);
        path.lineTo(175.0f, 515.315f);
        path.lineTo(445.0f, 95.25f);
        path.lineTo(905.0f, 255.255f);
        path.lineTo(135.0f, 225.65f);
        path.lineTo(145.0f, 645.195f);
        path.lineTo(415.0f, 645.315f);
        path.lineTo(755.0f, 815.75f);
        path.lineTo(875.0f, 695.765f);
        path.lineTo(125.0f, 575.255f);
        path.lineTo(625.0f, 445.135f);
        path.lineTo(805.0f, 805.795f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1009.0f, this.bounds.height() / 1009.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
