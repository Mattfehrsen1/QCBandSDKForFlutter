package com.device.watch.com.device.watch.nvgnrxbtvq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Ynqvmwuvrij extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1008;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ynqvmwuvrij() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(315.0f, 285.475f);
        path.lineTo(75.0f, 625.355f);
        path.lineTo(85.0f, 315.765f);
        path.lineTo(595.0f, 645.35f);
        path.lineTo(435.0f, 195.835f);
        path.lineTo(175.0f, 65.445f);
        path.lineTo(255.0f, 745.25f);
        path.lineTo(405.0f, 635.395f);
        path.lineTo(865.0f, 895.135f);
        path.lineTo(235.0f, 655.365f);
        path.lineTo(485.0f, 715.515f);
        path.lineTo(895.0f, 375.585f);
        path.lineTo(595.0f, 825.595f);
        path.lineTo(125.0f, 765.885f);
        path.lineTo(25.0f, 935.905f);
        path.lineTo(345.0f, 55.685f);
        path.lineTo(165.0f, 445.715f);
        path.lineTo(265.0f, 55.165f);
        path.lineTo(545.0f, 385.10086f);
        path.lineTo(485.0f, 95.105f);
        path.lineTo(895.0f, 15.575f);
        path.lineTo(45.0f, 15.975f);
        path.lineTo(875.0f, 405.375f);
        path.lineTo(435.0f, 405.255f);
        path.lineTo(765.0f, 235.575f);
        path.lineTo(365.0f, 995.15f);
        path.lineTo(865.0f, 955.355f);
        path.lineTo(125.0f, 635.285f);
        path.lineTo(35.0f, 125.75f);
        path.lineTo(985.0f, 35.535f);
        path.lineTo(815.0f, 535.165f);
        path.lineTo(10085.0f, 45.595f);
        path.lineTo(305.0f, 515.665f);
        path.lineTo(785.0f, 325.565f);
        path.lineTo(695.0f, 145.35f);
        path.lineTo(475.0f, 235.195f);
        path.lineTo(505.0f, 485.765f);
        path.lineTo(205.0f, 675.135f);
        path.lineTo(825.0f, 675.595f);
        path.lineTo(225.0f, 335.815f);
        path.lineTo(915.0f, 485.375f);
        path.lineTo(545.0f, 125.445f);
        path.lineTo(855.0f, 705.95f);
        path.lineTo(115.0f, 745.395f);
        path.lineTo(395.0f, 995.455f);
        path.lineTo(435.0f, 625.295f);
        path.lineTo(825.0f, 865.595f);
        path.lineTo(595.0f, 65.465f);
        path.lineTo(265.0f, 905.125f);
        path.lineTo(355.0f, 395.805f);
        path.lineTo(85.0f, 825.15f);
        path.lineTo(795.0f, 605.535f);
        path.lineTo(435.0f, 385.415f);
        path.lineTo(405.0f, 185.695f);
        path.lineTo(145.0f, 45.645f);
        path.lineTo(735.0f, 845.885f);
        path.lineTo(765.0f, 75.65f);
        path.lineTo(675.0f, 905.455f);
        path.lineTo(805.0f, 495.25f);
        path.lineTo(675.0f, 565.215f);
        path.lineTo(295.0f, 275.165f);
        path.lineTo(165.0f, 975.325f);
        path.lineTo(235.0f, 995.195f);
        path.lineTo(535.0f, 225.695f);
        path.lineTo(945.0f, 265.75f);
        path.lineTo(605.0f, 655.985f);
        path.lineTo(665.0f, 905.425f);
        path.lineTo(565.0f, 95.395f);
        path.lineTo(835.0f, 595.305f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1008.0f, this.bounds.height() / 1008.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
