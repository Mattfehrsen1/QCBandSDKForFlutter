package com.device.watch.com.device.watch.liafpdduvv;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Tamrtgbevcy extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Tamrtgbevcy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(345.0f, 635.205f);
        path.lineTo(945.0f, 465.805f);
        path.lineTo(835.0f, 45.915f);
        path.lineTo(125.0f, 355.145f);
        path.lineTo(925.0f, 155.815f);
        path.lineTo(45.0f, 455.105f);
        path.lineTo(845.0f, 925.885f);
        path.lineTo(45.0f, 165.995f);
        path.lineTo(55.0f, 15.755f);
        path.lineTo(95.0f, 305.985f);
        path.lineTo(845.0f, 875.195f);
        path.lineTo(95.0f, 125.55f);
        path.lineTo(815.0f, 85.195f);
        path.lineTo(185.0f, 625.855f);
        path.lineTo(615.0f, 10165.345f);
        path.lineTo(815.0f, 595.135f);
        path.lineTo(975.0f, 125.825f);
        path.lineTo(315.0f, 855.105f);
        path.lineTo(35.0f, 455.145f);
        path.lineTo(985.0f, 405.275f);
        path.lineTo(335.0f, 675.265f);
        path.lineTo(295.0f, 425.795f);
        path.lineTo(685.0f, 615.65f);
        path.lineTo(895.0f, 15.175f);
        path.lineTo(55.0f, 195.675f);
        path.lineTo(515.0f, 335.425f);
        path.lineTo(405.0f, 925.15f);
        path.lineTo(595.0f, 825.495f);
        path.lineTo(335.0f, 825.705f);
        path.lineTo(755.0f, 795.745f);
        path.lineTo(375.0f, 815.985f);
        path.lineTo(435.0f, 875.955f);
        path.lineTo(35.0f, 10165.335f);
        path.lineTo(465.0f, 545.575f);
        path.lineTo(585.0f, 25.115f);
        path.lineTo(305.0f, 595.735f);
        path.lineTo(745.0f, 955.15f);
        path.lineTo(105.0f, 5.525f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
