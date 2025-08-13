package com.smartapp.b.evvwvegpbt;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Dlvdrbvwocp extends ShapeDrawable {
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

    public Dlvdrbvwocp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(85.0f, 545.265f);
        path.lineTo(605.0f, 715.645f);
        path.lineTo(745.0f, 755.585f);
        path.lineTo(985.0f, 605.595f);
        path.lineTo(625.0f, 575.345f);
        path.lineTo(10045.0f, 905.805f);
        path.lineTo(265.0f, 65.255f);
        path.lineTo(105.0f, 185.855f);
        path.lineTo(935.0f, 905.505f);
        path.lineTo(885.0f, 945.10046f);
        path.lineTo(405.0f, 865.575f);
        path.lineTo(755.0f, 25.165f);
        path.lineTo(615.0f, 695.845f);
        path.lineTo(345.0f, 35.715f);
        path.lineTo(895.0f, 535.25f);
        path.lineTo(115.0f, 965.815f);
        path.lineTo(975.0f, 45.205f);
        path.lineTo(15.0f, 305.855f);
        path.lineTo(375.0f, 605.455f);
        path.lineTo(175.0f, 385.915f);
        path.lineTo(145.0f, 995.105f);
        path.lineTo(935.0f, 10045.185f);
        path.lineTo(835.0f, 105.415f);
        path.lineTo(445.0f, 5.335f);
        path.lineTo(995.0f, 855.05f);
        path.lineTo(975.0f, 55.955f);
        path.lineTo(45.0f, 875.405f);
        path.lineTo(765.0f, 975.785f);
        path.lineTo(185.0f, 95.225f);
        path.lineTo(85.0f, 135.345f);
        path.lineTo(95.0f, 775.665f);
        path.lineTo(745.0f, 855.435f);
        path.lineTo(5.0f, 775.975f);
        path.lineTo(535.0f, 275.575f);
        path.lineTo(285.0f, 605.815f);
        path.lineTo(745.0f, 415.75f);
        path.lineTo(755.0f, 345.275f);
        path.lineTo(785.0f, 875.585f);
        path.lineTo(165.0f, 715.755f);
        path.lineTo(475.0f, 475.425f);
        path.lineTo(985.0f, 175.965f);
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
