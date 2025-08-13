package com.device.watch.com.device.watch.xayinurern;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Eysfcsrorxz extends ShapeDrawable {
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

    public Eysfcsrorxz() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(805.0f, 425.655f);
        path.lineTo(125.0f, 545.945f);
        path.lineTo(215.0f, 995.435f);
        path.lineTo(485.0f, 295.845f);
        path.lineTo(935.0f, 645.205f);
        path.lineTo(915.0f, 65.815f);
        path.lineTo(25.0f, 585.135f);
        path.lineTo(695.0f, 135.645f);
        path.lineTo(675.0f, 595.855f);
        path.lineTo(935.0f, 945.775f);
        path.lineTo(85.0f, 995.265f);
        path.lineTo(415.0f, 225.705f);
        path.lineTo(725.0f, 45.55f);
        path.lineTo(755.0f, 695.495f);
        path.lineTo(15.0f, 395.755f);
        path.lineTo(175.0f, 695.405f);
        path.lineTo(475.0f, 915.305f);
        path.lineTo(935.0f, 795.395f);
        path.lineTo(325.0f, 715.585f);
        path.lineTo(405.0f, 545.495f);
        path.lineTo(315.0f, 945.145f);
        path.lineTo(10045.0f, 915.925f);
        path.lineTo(115.0f, 405.405f);
        path.lineTo(355.0f, 235.985f);
        path.lineTo(865.0f, 845.705f);
        path.lineTo(545.0f, 965.115f);
        path.lineTo(175.0f, 545.215f);
        path.lineTo(35.0f, 715.985f);
        path.lineTo(215.0f, 325.555f);
        path.lineTo(10045.0f, 885.775f);
        path.lineTo(625.0f, 405.335f);
        path.lineTo(635.0f, 45.925f);
        path.lineTo(155.0f, 955.225f);
        path.lineTo(55.0f, 335.825f);
        path.lineTo(565.0f, 415.10046f);
        path.lineTo(195.0f, 545.945f);
        path.lineTo(365.0f, 995.555f);
        path.lineTo(135.0f, 965.785f);
        path.lineTo(585.0f, 855.625f);
        path.lineTo(875.0f, 95.595f);
        path.lineTo(475.0f, 725.95f);
        path.lineTo(295.0f, 155.35f);
        path.lineTo(765.0f, 195.705f);
        path.lineTo(295.0f, 905.895f);
        path.lineTo(45.0f, 65.785f);
        path.lineTo(165.0f, 785.565f);
        path.lineTo(925.0f, 665.705f);
        path.lineTo(15.0f, 895.855f);
        path.lineTo(85.0f, 265.115f);
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
