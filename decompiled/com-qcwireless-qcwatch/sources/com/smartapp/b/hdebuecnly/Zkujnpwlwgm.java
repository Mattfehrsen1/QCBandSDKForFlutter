package com.smartapp.b.hdebuecnly;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Zkujnpwlwgm extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Zkujnpwlwgm() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(685.0f, 195.295f);
        path.lineTo(535.0f, 365.95f);
        path.lineTo(385.0f, 545.605f);
        path.lineTo(395.0f, 615.415f);
        path.lineTo(385.0f, 935.595f);
        path.lineTo(695.0f, 15.905f);
        path.lineTo(915.0f, 165.95f);
        path.lineTo(65.0f, 995.105f);
        path.lineTo(465.0f, 685.305f);
        path.lineTo(555.0f, 735.25f);
        path.lineTo(675.0f, 75.805f);
        path.lineTo(435.0f, 685.885f);
        path.lineTo(855.0f, 435.185f);
        path.lineTo(805.0f, 575.425f);
        path.lineTo(205.0f, 85.835f);
        path.lineTo(585.0f, 125.525f);
        path.lineTo(325.0f, 115.255f);
        path.lineTo(365.0f, 925.715f);
        path.lineTo(205.0f, 695.195f);
        path.lineTo(75.0f, 705.865f);
        path.lineTo(895.0f, 215.175f);
        path.lineTo(545.0f, 35.625f);
        path.lineTo(235.0f, 275.185f);
        path.lineTo(365.0f, 125.445f);
        path.lineTo(955.0f, 375.665f);
        path.lineTo(10085.0f, 25.285f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1008.0f, this.bounds.height() / 1008.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
