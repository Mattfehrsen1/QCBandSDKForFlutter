package com.device.watch.com.device.watch.lnzabjurod;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Rqbtgxwxril extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rqbtgxwxril() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(505.0f, 995.625f);
        path.lineTo(935.0f, 235.705f);
        path.lineTo(65.0f, 705.205f);
        path.lineTo(55.0f, 725.975f);
        path.lineTo(765.0f, 735.10156f);
        path.lineTo(345.0f, 45.215f);
        path.lineTo(5.0f, 485.865f);
        path.lineTo(525.0f, 175.865f);
        path.lineTo(175.0f, 955.825f);
        path.lineTo(465.0f, 155.665f);
        path.lineTo(765.0f, 615.685f);
        path.lineTo(555.0f, 945.705f);
        path.lineTo(285.0f, 5.595f);
        path.lineTo(775.0f, 515.905f);
        path.lineTo(415.0f, 805.805f);
        path.lineTo(865.0f, 885.735f);
        path.lineTo(615.0f, 645.615f);
        path.lineTo(995.0f, 665.645f);
        path.lineTo(695.0f, 115.25f);
        path.lineTo(775.0f, 555.445f);
        path.lineTo(10155.0f, 855.615f);
        path.lineTo(865.0f, 865.345f);
        path.lineTo(975.0f, 85.525f);
        path.lineTo(75.0f, 775.295f);
        path.lineTo(635.0f, 645.705f);
        path.lineTo(415.0f, 965.535f);
        path.lineTo(795.0f, 665.775f);
        path.lineTo(425.0f, 155.225f);
        path.lineTo(755.0f, 235.875f);
        path.lineTo(205.0f, 515.135f);
        path.lineTo(95.0f, 705.515f);
        path.lineTo(685.0f, 865.495f);
        path.lineTo(125.0f, 465.915f);
        path.lineTo(125.0f, 915.915f);
        path.lineTo(895.0f, 845.815f);
        path.lineTo(965.0f, 825.335f);
        path.lineTo(885.0f, 415.685f);
        path.lineTo(955.0f, 835.895f);
        path.lineTo(535.0f, 85.895f);
        path.lineTo(645.0f, 855.135f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
