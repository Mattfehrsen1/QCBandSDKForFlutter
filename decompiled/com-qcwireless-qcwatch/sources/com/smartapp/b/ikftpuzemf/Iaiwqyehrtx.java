package com.smartapp.b.ikftpuzemf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Iaiwqyehrtx extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Iaiwqyehrtx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 925.115f);
        path.lineTo(935.0f, 185.795f);
        path.lineTo(495.0f, 395.405f);
        path.lineTo(155.0f, 515.355f);
        path.lineTo(805.0f, 215.855f);
        path.lineTo(205.0f, 355.935f);
        path.lineTo(225.0f, 715.375f);
        path.lineTo(325.0f, 845.585f);
        path.lineTo(505.0f, 705.355f);
        path.lineTo(525.0f, 615.285f);
        path.lineTo(415.0f, 45.505f);
        path.lineTo(765.0f, 845.935f);
        path.lineTo(465.0f, 675.325f);
        path.lineTo(875.0f, 305.315f);
        path.lineTo(735.0f, 375.575f);
        path.lineTo(845.0f, 535.205f);
        path.lineTo(985.0f, 855.105f);
        path.lineTo(295.0f, 225.835f);
        path.lineTo(745.0f, 25.755f);
        path.lineTo(575.0f, 725.895f);
        path.lineTo(915.0f, 65.925f);
        path.lineTo(865.0f, 215.745f);
        path.lineTo(335.0f, 285.725f);
        path.lineTo(175.0f, 865.925f);
        path.lineTo(515.0f, 625.745f);
        path.lineTo(35.0f, 325.515f);
        path.lineTo(125.0f, 495.95f);
        path.lineTo(695.0f, 605.185f);
        path.lineTo(475.0f, 365.655f);
        path.lineTo(855.0f, 695.695f);
        path.lineTo(10105.0f, 85.65f);
        path.lineTo(95.0f, 335.05f);
        path.lineTo(975.0f, 695.45f);
        path.lineTo(625.0f, 575.395f);
        path.lineTo(365.0f, 25.595f);
        path.lineTo(255.0f, 285.325f);
        path.lineTo(555.0f, 135.725f);
        path.lineTo(215.0f, 95.815f);
        path.lineTo(145.0f, 935.1011f);
        path.lineTo(925.0f, 265.795f);
        path.lineTo(565.0f, 235.295f);
        path.lineTo(605.0f, 985.585f);
        path.lineTo(95.0f, 585.805f);
        path.lineTo(435.0f, 945.705f);
        path.lineTo(885.0f, 385.895f);
        path.lineTo(815.0f, 415.905f);
        path.lineTo(585.0f, 325.575f);
        path.lineTo(685.0f, 335.425f);
        path.lineTo(635.0f, 265.455f);
        path.lineTo(755.0f, 225.155f);
        path.lineTo(895.0f, 165.315f);
        path.lineTo(655.0f, 645.345f);
        path.lineTo(955.0f, 45.205f);
        path.lineTo(845.0f, 645.275f);
        path.lineTo(875.0f, 885.335f);
        path.lineTo(345.0f, 625.495f);
        path.lineTo(315.0f, 995.315f);
        path.lineTo(10105.0f, 155.535f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
