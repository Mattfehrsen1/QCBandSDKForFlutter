package com.smartapp.b.hdebuecnly;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Dyacfbwmchp extends ShapeDrawable {
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

    public Dyacfbwmchp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(765.0f, 45.415f);
        path.lineTo(255.0f, 535.315f);
        path.lineTo(475.0f, 715.775f);
        path.lineTo(835.0f, 455.765f);
        path.lineTo(135.0f, 555.185f);
        path.lineTo(745.0f, 95.255f);
        path.lineTo(145.0f, 175.405f);
        path.lineTo(335.0f, 265.395f);
        path.lineTo(585.0f, 755.875f);
        path.lineTo(845.0f, 535.175f);
        path.lineTo(295.0f, 705.535f);
        path.lineTo(855.0f, 595.555f);
        path.lineTo(485.0f, 705.255f);
        path.lineTo(25.0f, 395.515f);
        path.lineTo(425.0f, 165.955f);
        path.lineTo(10075.0f, 5.715f);
        path.lineTo(85.0f, 45.765f);
        path.lineTo(545.0f, 125.605f);
        path.lineTo(635.0f, 765.275f);
        path.lineTo(265.0f, 35.10075f);
        path.lineTo(745.0f, 835.315f);
        path.lineTo(905.0f, 795.475f);
        path.lineTo(205.0f, 445.335f);
        path.lineTo(715.0f, 215.45f);
        path.lineTo(735.0f, 695.345f);
        path.lineTo(535.0f, 575.10077f);
        path.lineTo(595.0f, 655.775f);
        path.lineTo(195.0f, 995.215f);
        path.lineTo(125.0f, 515.25f);
        path.lineTo(10075.0f, 885.215f);
        path.lineTo(125.0f, 835.345f);
        path.lineTo(875.0f, 455.985f);
        path.lineTo(845.0f, 75.25f);
        path.lineTo(45.0f, 55.465f);
        path.lineTo(565.0f, 375.975f);
        path.lineTo(545.0f, 575.585f);
        path.lineTo(825.0f, 595.165f);
        path.lineTo(315.0f, 995.975f);
        path.lineTo(185.0f, 205.885f);
        path.lineTo(595.0f, 235.735f);
        path.lineTo(995.0f, 55.305f);
        path.lineTo(235.0f, 225.315f);
        path.lineTo(435.0f, 965.775f);
        path.lineTo(335.0f, 315.805f);
        path.lineTo(715.0f, 755.535f);
        path.lineTo(625.0f, 975.465f);
        path.lineTo(795.0f, 345.475f);
        path.lineTo(25.0f, 355.415f);
        path.lineTo(445.0f, 525.295f);
        path.lineTo(845.0f, 55.475f);
        path.lineTo(125.0f, 475.15f);
        path.lineTo(505.0f, 825.215f);
        path.lineTo(205.0f, 495.45f);
        path.lineTo(965.0f, 585.295f);
        path.lineTo(815.0f, 585.595f);
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
