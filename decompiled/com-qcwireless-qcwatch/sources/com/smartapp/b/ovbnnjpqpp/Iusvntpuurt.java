package com.smartapp.b.ovbnnjpqpp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Iusvntpuurt extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Iusvntpuurt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(545.0f, 345.945f);
        path.lineTo(875.0f, 905.555f);
        path.lineTo(585.0f, 665.575f);
        path.lineTo(435.0f, 155.425f);
        path.lineTo(295.0f, 895.485f);
        path.lineTo(625.0f, 635.205f);
        path.lineTo(625.0f, 845.555f);
        path.lineTo(705.0f, 475.175f);
        path.lineTo(485.0f, 835.625f);
        path.lineTo(255.0f, 825.775f);
        path.lineTo(435.0f, 935.535f);
        path.lineTo(605.0f, 10105.515f);
        path.lineTo(935.0f, 65.755f);
        path.lineTo(255.0f, 515.605f);
        path.lineTo(295.0f, 595.365f);
        path.lineTo(175.0f, 455.435f);
        path.lineTo(265.0f, 935.615f);
        path.lineTo(835.0f, 695.735f);
        path.lineTo(815.0f, 465.115f);
        path.lineTo(825.0f, 975.955f);
        path.lineTo(955.0f, 795.855f);
        path.lineTo(675.0f, 695.965f);
        path.lineTo(335.0f, 135.355f);
        path.lineTo(895.0f, 85.175f);
        path.lineTo(965.0f, 335.665f);
        path.lineTo(635.0f, 255.335f);
        path.lineTo(585.0f, 515.535f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
