package com.smartapp.b.wmannaugfn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Gupletfftzl extends ShapeDrawable {
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

    public Gupletfftzl() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(215.0f, 815.875f);
        path.lineTo(295.0f, 485.855f);
        path.lineTo(275.0f, 645.535f);
        path.lineTo(365.0f, 185.325f);
        path.lineTo(985.0f, 185.185f);
        path.lineTo(415.0f, 735.365f);
        path.lineTo(315.0f, 405.885f);
        path.lineTo(405.0f, 365.335f);
        path.lineTo(815.0f, 10045.705f);
        path.lineTo(55.0f, 325.785f);
        path.lineTo(215.0f, 385.595f);
        path.lineTo(825.0f, 665.775f);
        path.lineTo(895.0f, 115.65f);
        path.lineTo(505.0f, 865.205f);
        path.lineTo(905.0f, 715.915f);
        path.lineTo(155.0f, 285.75f);
        path.lineTo(375.0f, 265.175f);
        path.lineTo(515.0f, 335.585f);
        path.lineTo(305.0f, 205.195f);
        path.lineTo(625.0f, 995.45f);
        path.lineTo(285.0f, 845.205f);
        path.lineTo(135.0f, 595.495f);
        path.lineTo(75.0f, 565.865f);
        path.lineTo(45.0f, 835.355f);
        path.lineTo(75.0f, 805.295f);
        path.lineTo(345.0f, 465.135f);
        path.lineTo(125.0f, 815.545f);
        path.lineTo(455.0f, 355.835f);
        path.lineTo(605.0f, 515.185f);
        path.lineTo(645.0f, 635.375f);
        path.lineTo(815.0f, 755.755f);
        path.lineTo(345.0f, 915.235f);
        path.lineTo(175.0f, 445.265f);
        path.lineTo(255.0f, 485.965f);
        path.lineTo(935.0f, 995.595f);
        path.lineTo(535.0f, 195.765f);
        path.lineTo(145.0f, 155.825f);
        path.lineTo(395.0f, 905.445f);
        path.lineTo(135.0f, 465.345f);
        path.lineTo(255.0f, 465.605f);
        path.lineTo(165.0f, 395.695f);
        path.lineTo(495.0f, 855.985f);
        path.lineTo(615.0f, 105.95f);
        path.lineTo(505.0f, 925.05f);
        path.lineTo(915.0f, 925.10046f);
        path.lineTo(655.0f, 625.335f);
        path.lineTo(175.0f, 705.285f);
        path.lineTo(345.0f, 385.85f);
        path.lineTo(755.0f, 615.765f);
        path.lineTo(545.0f, 745.10046f);
        path.lineTo(55.0f, 535.625f);
        path.lineTo(275.0f, 25.515f);
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
