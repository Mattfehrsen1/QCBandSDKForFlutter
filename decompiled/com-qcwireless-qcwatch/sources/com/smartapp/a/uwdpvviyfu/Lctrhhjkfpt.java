package com.smartapp.a.uwdpvviyfu;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Lctrhhjkfpt extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lctrhhjkfpt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(705.0f, 545.125f);
        path.lineTo(815.0f, 995.25f);
        path.lineTo(915.0f, 405.445f);
        path.lineTo(205.0f, 475.525f);
        path.lineTo(765.0f, 935.55f);
        path.lineTo(475.0f, 815.515f);
        path.lineTo(45.0f, 465.785f);
        path.lineTo(455.0f, 315.985f);
        path.lineTo(825.0f, 325.725f);
        path.lineTo(335.0f, 725.455f);
        path.lineTo(25.0f, 335.525f);
        path.lineTo(225.0f, 55.495f);
        path.lineTo(825.0f, 115.85f);
        path.lineTo(325.0f, 375.155f);
        path.lineTo(25.0f, 375.65f);
        path.lineTo(55.0f, 575.215f);
        path.lineTo(925.0f, 415.375f);
        path.lineTo(335.0f, 265.515f);
        path.lineTo(785.0f, 615.325f);
        path.lineTo(265.0f, 105.345f);
        path.lineTo(865.0f, 585.585f);
        path.lineTo(555.0f, 255.525f);
        path.lineTo(715.0f, 915.325f);
        path.lineTo(405.0f, 855.635f);
        path.lineTo(925.0f, 935.605f);
        path.lineTo(75.0f, 815.335f);
        path.lineTo(795.0f, 445.735f);
        path.lineTo(955.0f, 105.525f);
        path.lineTo(25.0f, 545.355f);
        path.lineTo(665.0f, 15.205f);
        path.lineTo(655.0f, 135.975f);
        path.lineTo(535.0f, 145.445f);
        path.lineTo(915.0f, 275.645f);
        path.lineTo(95.0f, 125.225f);
        path.lineTo(155.0f, 905.685f);
        path.lineTo(615.0f, 575.775f);
        path.lineTo(345.0f, 515.755f);
        path.lineTo(685.0f, 45.415f);
        path.lineTo(635.0f, 615.475f);
        path.lineTo(815.0f, 35.685f);
        path.lineTo(405.0f, 725.705f);
        path.lineTo(35.0f, 745.125f);
        path.lineTo(945.0f, 995.805f);
        path.lineTo(965.0f, 515.615f);
        path.lineTo(905.0f, 595.545f);
        path.lineTo(845.0f, 45.125f);
        path.lineTo(305.0f, 75.595f);
        path.lineTo(735.0f, 485.315f);
        path.lineTo(775.0f, 385.495f);
        path.lineTo(65.0f, 525.205f);
        path.lineTo(745.0f, 675.395f);
        path.lineTo(285.0f, 275.745f);
        path.lineTo(965.0f, 745.105f);
        path.lineTo(415.0f, 665.355f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1000.0f, this.bounds.height() / 1000.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
