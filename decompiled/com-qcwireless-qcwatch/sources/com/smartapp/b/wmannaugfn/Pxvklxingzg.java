package com.smartapp.b.wmannaugfn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Pxvklxingzg extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pxvklxingzg() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(495.0f, 65.55f);
        path.lineTo(295.0f, 955.135f);
        path.lineTo(955.0f, 795.75f);
        path.lineTo(355.0f, 215.515f);
        path.lineTo(335.0f, 515.705f);
        path.lineTo(915.0f, 725.325f);
        path.lineTo(35.0f, 145.975f);
        path.lineTo(825.0f, 385.125f);
        path.lineTo(395.0f, 815.235f);
        path.lineTo(165.0f, 365.265f);
        path.lineTo(815.0f, 565.315f);
        path.lineTo(905.0f, 155.725f);
        path.lineTo(705.0f, 65.515f);
        path.lineTo(355.0f, 585.255f);
        path.lineTo(875.0f, 985.855f);
        path.lineTo(65.0f, 925.315f);
        path.lineTo(525.0f, 305.35f);
        path.lineTo(655.0f, 955.585f);
        path.lineTo(645.0f, 345.275f);
        path.lineTo(385.0f, 305.475f);
        path.lineTo(675.0f, 45.155f);
        path.lineTo(965.0f, 25.05f);
        path.lineTo(315.0f, 725.925f);
        path.lineTo(745.0f, 35.525f);
        path.lineTo(115.0f, 985.605f);
        path.lineTo(725.0f, 475.135f);
        path.lineTo(755.0f, 395.115f);
        path.lineTo(45.0f, 355.715f);
        path.lineTo(195.0f, 465.635f);
        path.lineTo(645.0f, 275.725f);
        path.lineTo(55.0f, 355.685f);
        path.lineTo(95.0f, 685.535f);
        path.lineTo(585.0f, 285.35f);
        path.lineTo(465.0f, 25.645f);
        path.lineTo(795.0f, 125.915f);
        path.lineTo(145.0f, 595.195f);
        path.lineTo(55.0f, 675.355f);
        path.lineTo(905.0f, 575.315f);
        path.lineTo(705.0f, 695.405f);
        path.lineTo(755.0f, 5.545f);
        path.lineTo(595.0f, 355.415f);
        path.lineTo(675.0f, 685.785f);
        path.lineTo(165.0f, 305.595f);
        path.lineTo(25.0f, 295.655f);
        path.lineTo(845.0f, 215.235f);
        path.lineTo(455.0f, 55.415f);
        path.lineTo(695.0f, 615.395f);
        path.lineTo(375.0f, 435.875f);
        path.lineTo(995.0f, 655.45f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
