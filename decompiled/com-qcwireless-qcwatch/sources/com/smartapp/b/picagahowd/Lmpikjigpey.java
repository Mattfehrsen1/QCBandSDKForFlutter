package com.smartapp.b.picagahowd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Lmpikjigpey extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lmpikjigpey() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(865.0f, 415.985f);
        path.lineTo(15.0f, 735.325f);
        path.lineTo(265.0f, 75.525f);
        path.lineTo(185.0f, 95.685f);
        path.lineTo(985.0f, 635.975f);
        path.lineTo(195.0f, 925.575f);
        path.lineTo(165.0f, 825.765f);
        path.lineTo(925.0f, 965.105f);
        path.lineTo(825.0f, 165.635f);
        path.lineTo(905.0f, 845.695f);
        path.lineTo(995.0f, 275.805f);
        path.lineTo(405.0f, 305.565f);
        path.lineTo(505.0f, 265.435f);
        path.lineTo(55.0f, 815.585f);
        path.lineTo(505.0f, 305.525f);
        path.lineTo(165.0f, 745.675f);
        path.lineTo(865.0f, 895.75f);
        path.lineTo(905.0f, 415.115f);
        path.lineTo(985.0f, 435.535f);
        path.lineTo(55.0f, 55.725f);
        path.lineTo(575.0f, 645.355f);
        path.lineTo(145.0f, 325.505f);
        path.lineTo(845.0f, 945.335f);
        path.lineTo(445.0f, 35.625f);
        path.lineTo(805.0f, 585.295f);
        path.lineTo(715.0f, 95.755f);
        path.lineTo(165.0f, 865.365f);
        path.lineTo(865.0f, 845.635f);
        path.lineTo(725.0f, 805.495f);
        path.lineTo(25.0f, 845.495f);
        path.lineTo(225.0f, 615.405f);
        path.lineTo(235.0f, 145.675f);
        path.lineTo(125.0f, 155.265f);
        path.lineTo(155.0f, 885.465f);
        path.lineTo(675.0f, 345.285f);
        path.lineTo(235.0f, 225.885f);
        path.lineTo(995.0f, 825.185f);
        path.lineTo(65.0f, 555.455f);
        path.lineTo(605.0f, 10015.485f);
        path.lineTo(445.0f, 235.855f);
        path.lineTo(155.0f, 605.315f);
        path.lineTo(45.0f, 935.455f);
        path.lineTo(885.0f, 915.735f);
        path.lineTo(645.0f, 95.835f);
        path.lineTo(425.0f, 285.745f);
        path.lineTo(185.0f, 825.575f);
        path.lineTo(10015.0f, 55.415f);
        path.lineTo(235.0f, 565.265f);
        path.lineTo(905.0f, 455.595f);
        path.lineTo(35.0f, 535.975f);
        path.lineTo(735.0f, 865.455f);
        path.lineTo(215.0f, 845.285f);
        path.lineTo(935.0f, 775.725f);
        path.lineTo(535.0f, 25.485f);
        path.lineTo(655.0f, 415.235f);
        path.lineTo(425.0f, 855.895f);
        path.lineTo(265.0f, 75.175f);
        path.lineTo(625.0f, 515.285f);
        path.lineTo(505.0f, 795.35f);
        path.lineTo(395.0f, 215.505f);
        path.lineTo(515.0f, 815.895f);
        path.lineTo(925.0f, 595.655f);
        path.lineTo(295.0f, 225.25f);
        path.lineTo(135.0f, 545.855f);
        path.lineTo(645.0f, 495.945f);
        path.lineTo(945.0f, 365.45f);
        path.lineTo(115.0f, 35.635f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1001.0f, this.bounds.height() / 1001.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
