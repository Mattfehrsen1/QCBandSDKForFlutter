package com.smartapp.a.lgjdagosrc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Etpgiwsolwa extends ShapeDrawable {
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

    public Etpgiwsolwa() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(695.0f, 605.225f);
        path.lineTo(445.0f, 555.395f);
        path.lineTo(75.0f, 385.765f);
        path.lineTo(95.0f, 25.895f);
        path.lineTo(385.0f, 495.965f);
        path.lineTo(555.0f, 35.695f);
        path.lineTo(10045.0f, 975.75f);
        path.lineTo(975.0f, 355.335f);
        path.lineTo(255.0f, 295.545f);
        path.lineTo(405.0f, 905.615f);
        path.lineTo(885.0f, 475.25f);
        path.lineTo(755.0f, 855.465f);
        path.lineTo(345.0f, 155.425f);
        path.lineTo(295.0f, 345.545f);
        path.lineTo(625.0f, 95.535f);
        path.lineTo(205.0f, 155.895f);
        path.lineTo(475.0f, 835.635f);
        path.lineTo(345.0f, 765.375f);
        path.lineTo(385.0f, 575.785f);
        path.lineTo(675.0f, 255.945f);
        path.lineTo(335.0f, 145.605f);
        path.lineTo(745.0f, 315.195f);
        path.lineTo(895.0f, 185.615f);
        path.lineTo(655.0f, 815.155f);
        path.lineTo(635.0f, 825.415f);
        path.lineTo(835.0f, 85.75f);
        path.lineTo(345.0f, 705.675f);
        path.lineTo(265.0f, 495.745f);
        path.lineTo(785.0f, 195.225f);
        path.lineTo(615.0f, 85.875f);
        path.lineTo(645.0f, 165.105f);
        path.lineTo(925.0f, 875.735f);
        path.lineTo(495.0f, 975.945f);
        path.lineTo(195.0f, 325.505f);
        path.lineTo(125.0f, 155.345f);
        path.lineTo(25.0f, 225.515f);
        path.lineTo(805.0f, 695.705f);
        path.lineTo(495.0f, 415.925f);
        path.lineTo(275.0f, 535.905f);
        path.lineTo(605.0f, 615.935f);
        path.lineTo(255.0f, 195.445f);
        path.lineTo(115.0f, 935.435f);
        path.lineTo(575.0f, 865.975f);
        path.lineTo(135.0f, 55.335f);
        path.lineTo(315.0f, 865.235f);
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
