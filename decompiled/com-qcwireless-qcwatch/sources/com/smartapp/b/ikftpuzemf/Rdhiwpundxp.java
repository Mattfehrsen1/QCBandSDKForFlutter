package com.smartapp.b.ikftpuzemf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Rdhiwpundxp extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rdhiwpundxp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(495.0f, 795.855f);
        path.lineTo(185.0f, 445.495f);
        path.lineTo(55.0f, 745.395f);
        path.lineTo(345.0f, 625.195f);
        path.lineTo(995.0f, 235.465f);
        path.lineTo(555.0f, 425.885f);
        path.lineTo(495.0f, 175.975f);
        path.lineTo(705.0f, 755.795f);
        path.lineTo(105.0f, 695.195f);
        path.lineTo(195.0f, 755.795f);
        path.lineTo(555.0f, 155.815f);
        path.lineTo(485.0f, 135.65f);
        path.lineTo(205.0f, 635.575f);
        path.lineTo(535.0f, 355.65f);
        path.lineTo(705.0f, 345.575f);
        path.lineTo(555.0f, 355.565f);
        path.lineTo(915.0f, 475.145f);
        path.lineTo(325.0f, 255.195f);
        path.lineTo(755.0f, 535.455f);
        path.lineTo(655.0f, 525.165f);
        path.lineTo(855.0f, 565.665f);
        path.lineTo(345.0f, 425.295f);
        path.lineTo(645.0f, 545.375f);
        path.lineTo(405.0f, 235.865f);
        path.lineTo(195.0f, 475.535f);
        path.lineTo(385.0f, 95.185f);
        path.lineTo(5.0f, 815.815f);
        path.lineTo(635.0f, 835.985f);
        path.lineTo(845.0f, 405.575f);
        path.lineTo(635.0f, 895.405f);
        path.lineTo(745.0f, 365.565f);
        path.lineTo(75.0f, 705.785f);
        path.lineTo(365.0f, 825.95f);
        path.lineTo(665.0f, 605.835f);
        path.lineTo(95.0f, 865.05f);
        path.lineTo(135.0f, 675.755f);
        path.lineTo(565.0f, 605.615f);
        path.lineTo(905.0f, 855.135f);
        path.lineTo(895.0f, 135.285f);
        path.lineTo(625.0f, 925.655f);
        path.lineTo(735.0f, 815.545f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
