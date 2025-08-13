package com.smartapp.a.kdrekvphcl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Qxeedzjwqxx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Qxeedzjwqxx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(675.0f, 645.105f);
        path.lineTo(10035.0f, 115.475f);
        path.lineTo(145.0f, 965.215f);
        path.lineTo(555.0f, 955.785f);
        path.lineTo(185.0f, 555.965f);
        path.lineTo(795.0f, 605.645f);
        path.lineTo(365.0f, 395.135f);
        path.lineTo(445.0f, 855.155f);
        path.lineTo(35.0f, 305.955f);
        path.lineTo(715.0f, 145.745f);
        path.lineTo(45.0f, 465.605f);
        path.lineTo(75.0f, 35.335f);
        path.lineTo(295.0f, 455.15f);
        path.lineTo(405.0f, 385.765f);
        path.lineTo(495.0f, 335.695f);
        path.lineTo(215.0f, 975.635f);
        path.lineTo(755.0f, 75.145f);
        path.lineTo(785.0f, 75.155f);
        path.lineTo(715.0f, 545.555f);
        path.lineTo(735.0f, 815.225f);
        path.lineTo(10035.0f, 705.85f);
        path.lineTo(125.0f, 515.845f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
