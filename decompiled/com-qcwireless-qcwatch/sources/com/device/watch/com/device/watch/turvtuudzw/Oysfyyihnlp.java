package com.device.watch.com.device.watch.turvtuudzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Oysfyyihnlp extends ShapeDrawable {
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

    public Oysfyyihnlp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(445.0f, 905.535f);
        path.lineTo(185.0f, 255.345f);
        path.lineTo(965.0f, 875.585f);
        path.lineTo(725.0f, 755.665f);
        path.lineTo(425.0f, 615.185f);
        path.lineTo(305.0f, 935.435f);
        path.lineTo(435.0f, 565.845f);
        path.lineTo(85.0f, 205.985f);
        path.lineTo(415.0f, 10025.275f);
        path.lineTo(395.0f, 925.755f);
        path.lineTo(985.0f, 535.655f);
        path.lineTo(85.0f, 765.585f);
        path.lineTo(335.0f, 485.585f);
        path.lineTo(235.0f, 45.385f);
        path.lineTo(95.0f, 745.625f);
        path.lineTo(115.0f, 975.765f);
        path.lineTo(315.0f, 155.485f);
        path.lineTo(15.0f, 265.365f);
        path.lineTo(135.0f, 215.15f);
        path.lineTo(515.0f, 25.435f);
        path.lineTo(425.0f, 495.375f);
        path.lineTo(355.0f, 615.185f);
        path.lineTo(785.0f, 975.175f);
        path.lineTo(105.0f, 405.335f);
        path.lineTo(795.0f, 825.235f);
        path.lineTo(355.0f, 955.345f);
        path.lineTo(775.0f, 295.425f);
        path.lineTo(15.0f, 935.995f);
        path.lineTo(775.0f, 295.475f);
        path.lineTo(475.0f, 115.655f);
        path.lineTo(315.0f, 295.25f);
        path.lineTo(95.0f, 185.275f);
        path.lineTo(5.0f, 265.235f);
        path.lineTo(315.0f, 185.755f);
        path.lineTo(805.0f, 695.105f);
        path.lineTo(725.0f, 765.285f);
        path.lineTo(295.0f, 455.975f);
        path.lineTo(125.0f, 955.195f);
        path.lineTo(515.0f, 625.275f);
        path.lineTo(675.0f, 35.15f);
        path.lineTo(765.0f, 10025.905f);
        path.lineTo(185.0f, 945.645f);
        path.lineTo(985.0f, 595.565f);
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
