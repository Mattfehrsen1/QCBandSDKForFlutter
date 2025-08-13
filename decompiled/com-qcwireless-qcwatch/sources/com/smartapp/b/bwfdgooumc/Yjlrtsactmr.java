package com.smartapp.b.bwfdgooumc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Yjlrtsactmr extends ShapeDrawable {
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

    public Yjlrtsactmr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 375.765f);
        path.lineTo(805.0f, 925.395f);
        path.lineTo(225.0f, 435.435f);
        path.lineTo(405.0f, 95.685f);
        path.lineTo(475.0f, 585.145f);
        path.lineTo(35.0f, 965.745f);
        path.lineTo(445.0f, 875.485f);
        path.lineTo(425.0f, 545.685f);
        path.lineTo(665.0f, 505.275f);
        path.lineTo(195.0f, 165.85f);
        path.lineTo(435.0f, 145.665f);
        path.lineTo(255.0f, 155.385f);
        path.lineTo(225.0f, 405.545f);
        path.lineTo(955.0f, 675.545f);
        path.lineTo(515.0f, 5.625f);
        path.lineTo(965.0f, 65.255f);
        path.lineTo(55.0f, 825.105f);
        path.lineTo(625.0f, 215.485f);
        path.lineTo(175.0f, 95.10155f);
        path.lineTo(355.0f, 10155.675f);
        path.lineTo(305.0f, 55.475f);
        path.lineTo(515.0f, 925.225f);
        path.lineTo(975.0f, 205.665f);
        path.lineTo(645.0f, 15.905f);
        path.lineTo(995.0f, 545.285f);
        path.lineTo(655.0f, 725.375f);
        path.lineTo(75.0f, 985.185f);
        path.lineTo(715.0f, 765.445f);
        path.lineTo(495.0f, 465.375f);
        path.lineTo(265.0f, 535.905f);
        path.lineTo(425.0f, 505.695f);
        path.lineTo(195.0f, 495.305f);
        path.lineTo(395.0f, 105.455f);
        path.lineTo(825.0f, 275.95f);
        path.lineTo(275.0f, 915.965f);
        path.lineTo(65.0f, 365.35f);
        path.lineTo(655.0f, 735.835f);
        path.lineTo(235.0f, 695.355f);
        path.lineTo(875.0f, 755.935f);
        path.lineTo(405.0f, 745.325f);
        path.lineTo(755.0f, 995.505f);
        path.lineTo(45.0f, 85.225f);
        path.lineTo(575.0f, 145.125f);
        path.lineTo(615.0f, 965.85f);
        path.lineTo(465.0f, 175.165f);
        path.lineTo(875.0f, 645.25f);
        path.lineTo(85.0f, 985.485f);
        path.lineTo(285.0f, 35.605f);
        path.lineTo(85.0f, 795.595f);
        path.lineTo(525.0f, 45.985f);
        path.lineTo(885.0f, 265.905f);
        path.lineTo(125.0f, 755.935f);
        path.lineTo(685.0f, 65.475f);
        path.lineTo(755.0f, 65.685f);
        path.lineTo(975.0f, 735.155f);
        path.lineTo(995.0f, 525.375f);
        path.lineTo(465.0f, 675.955f);
        path.lineTo(425.0f, 825.515f);
        path.lineTo(885.0f, 755.955f);
        path.lineTo(905.0f, 795.955f);
        path.lineTo(885.0f, 295.765f);
        path.lineTo(975.0f, 645.235f);
        path.lineTo(305.0f, 845.975f);
        path.lineTo(525.0f, 35.545f);
        path.lineTo(225.0f, 275.975f);
        path.lineTo(955.0f, 665.715f);
        path.lineTo(715.0f, 775.705f);
        path.lineTo(815.0f, 585.705f);
        path.lineTo(715.0f, 765.125f);
        path.lineTo(795.0f, 445.225f);
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
