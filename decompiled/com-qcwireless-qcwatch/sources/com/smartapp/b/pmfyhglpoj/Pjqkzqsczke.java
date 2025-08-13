package com.smartapp.b.pmfyhglpoj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Pjqkzqsczke extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pjqkzqsczke() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(715.0f, 325.585f);
        path.lineTo(235.0f, 685.375f);
        path.lineTo(45.0f, 345.955f);
        path.lineTo(395.0f, 785.395f);
        path.lineTo(235.0f, 825.595f);
        path.lineTo(135.0f, 475.925f);
        path.lineTo(135.0f, 405.235f);
        path.lineTo(735.0f, 345.345f);
        path.lineTo(925.0f, 525.855f);
        path.lineTo(145.0f, 155.55f);
        path.lineTo(325.0f, 885.705f);
        path.lineTo(735.0f, 765.815f);
        path.lineTo(75.0f, 85.195f);
        path.lineTo(585.0f, 315.535f);
        path.lineTo(995.0f, 65.485f);
        path.lineTo(295.0f, 475.825f);
        path.lineTo(685.0f, 95.515f);
        path.lineTo(455.0f, 335.265f);
        path.lineTo(495.0f, 765.895f);
        path.lineTo(495.0f, 635.225f);
        path.lineTo(195.0f, 475.455f);
        path.lineTo(735.0f, 135.05f);
        path.lineTo(745.0f, 945.895f);
        path.lineTo(915.0f, 125.875f);
        path.lineTo(875.0f, 855.535f);
        path.lineTo(325.0f, 765.805f);
        path.lineTo(975.0f, 805.865f);
        path.lineTo(995.0f, 595.865f);
        path.lineTo(285.0f, 175.995f);
        path.lineTo(765.0f, 415.785f);
        path.lineTo(395.0f, 635.895f);
        path.lineTo(295.0f, 95.815f);
        path.lineTo(555.0f, 645.545f);
        path.lineTo(765.0f, 315.345f);
        path.lineTo(175.0f, 965.835f);
        path.lineTo(525.0f, 415.935f);
        path.lineTo(185.0f, 5.745f);
        path.lineTo(995.0f, 935.835f);
        path.lineTo(465.0f, 435.345f);
        path.lineTo(65.0f, 135.955f);
        path.lineTo(195.0f, 285.755f);
        path.lineTo(335.0f, 995.455f);
        path.lineTo(385.0f, 10025.645f);
        path.lineTo(625.0f, 175.735f);
        path.lineTo(775.0f, 745.35f);
        path.lineTo(385.0f, 405.745f);
        path.lineTo(995.0f, 335.255f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1002.0f, this.bounds.height() / 1002.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
