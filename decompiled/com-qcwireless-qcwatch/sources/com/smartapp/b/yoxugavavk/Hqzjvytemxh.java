package com.smartapp.b.yoxugavavk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Hqzjvytemxh extends ShapeDrawable {
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

    public Hqzjvytemxh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(45.0f, 415.225f);
        path.lineTo(155.0f, 945.575f);
        path.lineTo(905.0f, 5.435f);
        path.lineTo(595.0f, 315.785f);
        path.lineTo(405.0f, 795.775f);
        path.lineTo(595.0f, 145.705f);
        path.lineTo(925.0f, 605.445f);
        path.lineTo(785.0f, 865.335f);
        path.lineTo(385.0f, 605.385f);
        path.lineTo(665.0f, 935.415f);
        path.lineTo(985.0f, 925.195f);
        path.lineTo(755.0f, 705.735f);
        path.lineTo(835.0f, 345.725f);
        path.lineTo(325.0f, 585.435f);
        path.lineTo(575.0f, 55.695f);
        path.lineTo(755.0f, 585.175f);
        path.lineTo(675.0f, 495.125f);
        path.lineTo(935.0f, 355.525f);
        path.lineTo(705.0f, 785.715f);
        path.lineTo(735.0f, 965.465f);
        path.lineTo(175.0f, 255.305f);
        path.lineTo(305.0f, 275.895f);
        path.lineTo(515.0f, 715.345f);
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
