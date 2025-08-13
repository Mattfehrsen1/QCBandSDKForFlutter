package com.device.watch.com.device.watch.ohbsxxwnzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Naybibsxxui extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Naybibsxxui() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(975.0f, 355.145f);
        path.lineTo(195.0f, 715.135f);
        path.lineTo(995.0f, 765.25f);
        path.lineTo(825.0f, 165.735f);
        path.lineTo(95.0f, 995.05f);
        path.lineTo(185.0f, 645.825f);
        path.lineTo(775.0f, 175.695f);
        path.lineTo(475.0f, 295.965f);
        path.lineTo(645.0f, 285.10056f);
        path.lineTo(35.0f, 825.505f);
        path.lineTo(575.0f, 225.765f);
        path.lineTo(85.0f, 895.695f);
        path.lineTo(405.0f, 155.795f);
        path.lineTo(185.0f, 525.335f);
        path.lineTo(835.0f, 695.855f);
        path.lineTo(555.0f, 625.795f);
        path.lineTo(85.0f, 425.485f);
        path.lineTo(645.0f, 715.135f);
        path.lineTo(375.0f, 225.425f);
        path.lineTo(535.0f, 725.445f);
        path.lineTo(885.0f, 115.585f);
        path.lineTo(815.0f, 875.185f);
        path.lineTo(205.0f, 65.805f);
        path.lineTo(585.0f, 415.145f);
        path.lineTo(845.0f, 815.985f);
        path.lineTo(435.0f, 445.415f);
        path.lineTo(875.0f, 395.385f);
        path.lineTo(755.0f, 315.955f);
        path.lineTo(265.0f, 265.735f);
        path.lineTo(415.0f, 495.575f);
        path.lineTo(155.0f, 415.975f);
        path.lineTo(75.0f, 995.85f);
        path.lineTo(25.0f, 615.575f);
        path.lineTo(165.0f, 515.175f);
        path.lineTo(435.0f, 165.465f);
        path.lineTo(185.0f, 695.445f);
        path.lineTo(555.0f, 765.665f);
        path.lineTo(275.0f, 35.795f);
        path.lineTo(395.0f, 495.305f);
        path.lineTo(695.0f, 875.525f);
        path.lineTo(305.0f, 925.75f);
        path.lineTo(995.0f, 195.785f);
        path.lineTo(95.0f, 325.135f);
        path.lineTo(955.0f, 845.915f);
        path.lineTo(735.0f, 225.795f);
        path.lineTo(805.0f, 55.125f);
        path.lineTo(645.0f, 525.535f);
        path.lineTo(105.0f, 875.975f);
        path.lineTo(985.0f, 455.235f);
        path.lineTo(435.0f, 155.755f);
        path.lineTo(95.0f, 105.515f);
        path.lineTo(465.0f, 335.335f);
        path.lineTo(725.0f, 865.75f);
        path.lineTo(225.0f, 15.185f);
        path.lineTo(75.0f, 645.865f);
        path.lineTo(515.0f, 285.505f);
        path.lineTo(295.0f, 115.185f);
        path.lineTo(665.0f, 645.575f);
        path.lineTo(25.0f, 465.525f);
        path.lineTo(425.0f, 895.925f);
        path.lineTo(415.0f, 165.505f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
