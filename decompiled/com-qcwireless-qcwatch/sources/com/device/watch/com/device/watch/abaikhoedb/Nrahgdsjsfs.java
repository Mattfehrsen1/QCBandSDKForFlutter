package com.device.watch.com.device.watch.abaikhoedb;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Nrahgdsjsfs extends ShapeDrawable {
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

    public Nrahgdsjsfs() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(575.0f, 745.415f);
        path.lineTo(825.0f, 515.985f);
        path.lineTo(275.0f, 795.85f);
        path.lineTo(575.0f, 685.935f);
        path.lineTo(935.0f, 145.235f);
        path.lineTo(655.0f, 745.885f);
        path.lineTo(445.0f, 515.55f);
        path.lineTo(315.0f, 415.585f);
        path.lineTo(865.0f, 165.865f);
        path.lineTo(965.0f, 415.555f);
        path.lineTo(835.0f, 415.545f);
        path.lineTo(565.0f, 565.255f);
        path.lineTo(295.0f, 755.15f);
        path.lineTo(765.0f, 775.975f);
        path.lineTo(455.0f, 195.785f);
        path.lineTo(805.0f, 145.205f);
        path.lineTo(635.0f, 195.355f);
        path.lineTo(355.0f, 85.525f);
        path.lineTo(645.0f, 885.935f);
        path.lineTo(355.0f, 595.485f);
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
