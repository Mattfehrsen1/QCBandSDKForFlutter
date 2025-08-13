package com.device.watch.com.device.watch.vbyhaufars;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Dwlrtjjcsed extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Dwlrtjjcsed() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(45.0f, 615.115f);
        path.lineTo(505.0f, 725.965f);
        path.lineTo(165.0f, 575.935f);
        path.lineTo(455.0f, 855.905f);
        path.lineTo(785.0f, 325.55f);
        path.lineTo(725.0f, 425.715f);
        path.lineTo(645.0f, 825.175f);
        path.lineTo(405.0f, 95.815f);
        path.lineTo(75.0f, 385.805f);
        path.lineTo(975.0f, 10185.55f);
        path.lineTo(415.0f, 885.835f);
        path.lineTo(145.0f, 445.425f);
        path.lineTo(365.0f, 185.985f);
        path.lineTo(155.0f, 445.425f);
        path.lineTo(205.0f, 335.775f);
        path.lineTo(195.0f, 205.265f);
        path.lineTo(315.0f, 10185.775f);
        path.lineTo(185.0f, 645.965f);
        path.lineTo(85.0f, 55.115f);
        path.lineTo(925.0f, 15.755f);
        path.lineTo(875.0f, 35.435f);
        path.lineTo(265.0f, 75.125f);
        path.lineTo(735.0f, 625.15f);
        path.lineTo(15.0f, 585.805f);
        path.lineTo(385.0f, 575.05f);
        path.lineTo(285.0f, 735.765f);
        path.lineTo(735.0f, 725.85f);
        path.lineTo(885.0f, 915.545f);
        path.lineTo(805.0f, 525.675f);
        path.lineTo(235.0f, 475.975f);
        path.lineTo(655.0f, 995.495f);
        path.lineTo(15.0f, 265.335f);
        path.lineTo(845.0f, 75.105f);
        path.lineTo(10185.0f, 505.545f);
        path.lineTo(965.0f, 845.495f);
        path.lineTo(665.0f, 575.305f);
        path.lineTo(25.0f, 615.225f);
        path.lineTo(25.0f, 515.515f);
        path.lineTo(395.0f, 795.105f);
        path.lineTo(5.0f, 615.385f);
        path.lineTo(165.0f, 235.555f);
        path.lineTo(455.0f, 695.10187f);
        path.lineTo(585.0f, 555.515f);
        path.lineTo(85.0f, 65.45f);
        path.lineTo(705.0f, 215.495f);
        path.lineTo(425.0f, 765.295f);
        path.lineTo(945.0f, 365.115f);
        path.lineTo(325.0f, 585.405f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1018.0f, this.bounds.height() / 1018.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
