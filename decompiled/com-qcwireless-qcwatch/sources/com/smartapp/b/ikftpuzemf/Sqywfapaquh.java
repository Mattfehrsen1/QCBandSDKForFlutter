package com.smartapp.b.ikftpuzemf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Sqywfapaquh extends ShapeDrawable {
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

    public Sqywfapaquh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(655.0f, 305.10104f);
        path.lineTo(555.0f, 595.415f);
        path.lineTo(175.0f, 455.485f);
        path.lineTo(565.0f, 45.615f);
        path.lineTo(195.0f, 5.445f);
        path.lineTo(685.0f, 615.935f);
        path.lineTo(475.0f, 625.425f);
        path.lineTo(855.0f, 975.495f);
        path.lineTo(645.0f, 315.425f);
        path.lineTo(535.0f, 525.895f);
        path.lineTo(15.0f, 555.635f);
        path.lineTo(15.0f, 585.635f);
        path.lineTo(665.0f, 395.845f);
        path.lineTo(175.0f, 625.705f);
        path.lineTo(65.0f, 205.195f);
        path.lineTo(625.0f, 215.325f);
        path.lineTo(725.0f, 775.05f);
        path.lineTo(455.0f, 375.945f);
        path.lineTo(795.0f, 295.455f);
        path.lineTo(815.0f, 805.165f);
        path.lineTo(995.0f, 705.275f);
        path.lineTo(85.0f, 315.705f);
        path.lineTo(225.0f, 235.895f);
        path.lineTo(285.0f, 185.575f);
        path.lineTo(435.0f, 865.705f);
        path.lineTo(715.0f, 975.915f);
        path.lineTo(945.0f, 745.675f);
        path.lineTo(255.0f, 955.265f);
        path.lineTo(465.0f, 365.985f);
        path.lineTo(235.0f, 595.855f);
        path.lineTo(945.0f, 495.335f);
        path.lineTo(335.0f, 805.325f);
        path.lineTo(585.0f, 25.145f);
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
