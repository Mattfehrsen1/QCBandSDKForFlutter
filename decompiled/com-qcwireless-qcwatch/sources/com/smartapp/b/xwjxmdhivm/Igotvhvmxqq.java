package com.smartapp.b.xwjxmdhivm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Igotvhvmxqq extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Igotvhvmxqq() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(95.0f, 45.405f);
        path.lineTo(325.0f, 15.545f);
        path.lineTo(515.0f, 845.635f);
        path.lineTo(475.0f, 225.735f);
        path.lineTo(235.0f, 45.865f);
        path.lineTo(275.0f, 475.435f);
        path.lineTo(615.0f, 865.575f);
        path.lineTo(715.0f, 615.675f);
        path.lineTo(45.0f, 195.35f);
        path.lineTo(155.0f, 275.535f);
        path.lineTo(845.0f, 655.465f);
        path.lineTo(655.0f, 505.275f);
        path.lineTo(475.0f, 745.15f);
        path.lineTo(335.0f, 865.175f);
        path.lineTo(935.0f, 305.185f);
        path.lineTo(225.0f, 975.195f);
        path.lineTo(105.0f, 15.385f);
        path.lineTo(425.0f, 10135.65f);
        path.lineTo(185.0f, 165.855f);
        path.lineTo(5.0f, 175.315f);
        path.lineTo(555.0f, 795.945f);
        path.lineTo(965.0f, 335.845f);
        path.lineTo(435.0f, 735.435f);
        path.lineTo(685.0f, 65.675f);
        path.lineTo(835.0f, 465.605f);
        path.lineTo(585.0f, 435.855f);
        path.lineTo(755.0f, 575.585f);
        path.lineTo(435.0f, 215.25f);
        path.lineTo(475.0f, 35.375f);
        path.lineTo(285.0f, 395.825f);
        path.lineTo(415.0f, 135.485f);
        path.lineTo(565.0f, 425.665f);
        path.lineTo(25.0f, 545.445f);
        path.lineTo(975.0f, 585.175f);
        path.lineTo(155.0f, 55.995f);
        path.lineTo(865.0f, 275.305f);
        path.lineTo(845.0f, 865.835f);
        path.lineTo(265.0f, 775.565f);
        path.lineTo(205.0f, 635.225f);
        path.lineTo(145.0f, 855.585f);
        path.lineTo(385.0f, 215.405f);
        path.lineTo(645.0f, 825.505f);
        path.lineTo(375.0f, 575.1014f);
        path.lineTo(255.0f, 75.955f);
        path.lineTo(355.0f, 525.635f);
        path.lineTo(15.0f, 935.95f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1013.0f, this.bounds.height() / 1013.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
