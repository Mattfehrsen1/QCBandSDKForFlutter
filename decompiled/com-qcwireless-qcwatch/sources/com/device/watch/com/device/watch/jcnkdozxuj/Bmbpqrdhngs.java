package com.device.watch.com.device.watch.jcnkdozxuj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Bmbpqrdhngs extends ShapeDrawable {
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

    public Bmbpqrdhngs() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(635.0f, 925.855f);
        path.lineTo(545.0f, 725.365f);
        path.lineTo(855.0f, 15.155f);
        path.lineTo(935.0f, 385.255f);
        path.lineTo(965.0f, 735.145f);
        path.lineTo(215.0f, 575.05f);
        path.lineTo(585.0f, 55.105f);
        path.lineTo(115.0f, 585.415f);
        path.lineTo(25.0f, 365.335f);
        path.lineTo(355.0f, 555.45f);
        path.lineTo(955.0f, 785.45f);
        path.lineTo(865.0f, 635.845f);
        path.lineTo(785.0f, 745.725f);
        path.lineTo(755.0f, 525.665f);
        path.lineTo(275.0f, 285.255f);
        path.lineTo(565.0f, 655.365f);
        path.lineTo(215.0f, 895.995f);
        path.lineTo(885.0f, 255.225f);
        path.lineTo(845.0f, 215.555f);
        path.lineTo(185.0f, 25.745f);
        path.lineTo(975.0f, 565.85f);
        path.lineTo(705.0f, 855.715f);
        path.lineTo(325.0f, 695.265f);
        path.lineTo(275.0f, 85.755f);
        path.lineTo(75.0f, 35.495f);
        path.lineTo(355.0f, 695.55f);
        path.lineTo(405.0f, 275.485f);
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
