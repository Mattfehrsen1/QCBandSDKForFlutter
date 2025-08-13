package com.device.watch.com.device.watch.nvgnrxbtvq;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Qpuxlwkdjob extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Qpuxlwkdjob() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(185.0f, 25.405f);
        path.lineTo(405.0f, 225.105f);
        path.lineTo(345.0f, 585.105f);
        path.lineTo(975.0f, 5.175f);
        path.lineTo(465.0f, 465.55f);
        path.lineTo(115.0f, 275.565f);
        path.lineTo(895.0f, 985.615f);
        path.lineTo(855.0f, 675.565f);
        path.lineTo(535.0f, 575.425f);
        path.lineTo(205.0f, 915.925f);
        path.lineTo(385.0f, 615.405f);
        path.lineTo(95.0f, 945.85f);
        path.lineTo(645.0f, 665.975f);
        path.lineTo(225.0f, 375.315f);
        path.lineTo(265.0f, 655.25f);
        path.lineTo(465.0f, 535.155f);
        path.lineTo(485.0f, 175.715f);
        path.lineTo(375.0f, 525.195f);
        path.lineTo(415.0f, 905.275f);
        path.lineTo(945.0f, 225.365f);
        path.lineTo(905.0f, 475.195f);
        path.lineTo(125.0f, 975.215f);
        path.lineTo(655.0f, 735.565f);
        path.lineTo(75.0f, 985.995f);
        path.lineTo(145.0f, 725.655f);
        path.lineTo(105.0f, 265.805f);
        path.lineTo(285.0f, 625.915f);
        path.lineTo(575.0f, 115.675f);
        path.lineTo(115.0f, 655.645f);
        path.lineTo(545.0f, 605.835f);
        path.lineTo(855.0f, 555.125f);
        path.lineTo(65.0f, 625.115f);
        path.lineTo(115.0f, 15.605f);
        path.lineTo(855.0f, 955.145f);
        path.lineTo(515.0f, 5.925f);
        path.lineTo(505.0f, 975.485f);
        path.lineTo(25.0f, 855.605f);
        path.lineTo(65.0f, 605.675f);
        path.lineTo(385.0f, 835.755f);
        path.lineTo(265.0f, 595.615f);
        path.lineTo(605.0f, 95.205f);
        path.lineTo(335.0f, 275.105f);
        path.lineTo(715.0f, 455.175f);
        path.lineTo(75.0f, 125.905f);
        path.lineTo(485.0f, 745.555f);
        path.lineTo(185.0f, 435.35f);
        path.lineTo(195.0f, 75.935f);
        path.lineTo(935.0f, 475.485f);
        path.lineTo(795.0f, 655.285f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1014.0f, this.bounds.height() / 1014.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
