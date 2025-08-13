package com.smartapp.b.mattqajkje;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Jvzoxajsncy extends ShapeDrawable {
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

    public Jvzoxajsncy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(105.0f, 695.295f);
        path.lineTo(905.0f, 85.595f);
        path.lineTo(325.0f, 355.615f);
        path.lineTo(515.0f, 585.485f);
        path.lineTo(285.0f, 505.865f);
        path.lineTo(445.0f, 335.75f);
        path.lineTo(145.0f, 395.205f);
        path.lineTo(445.0f, 15.895f);
        path.lineTo(935.0f, 515.465f);
        path.lineTo(895.0f, 585.375f);
        path.lineTo(95.0f, 985.145f);
        path.lineTo(175.0f, 445.175f);
        path.lineTo(735.0f, 925.535f);
        path.lineTo(985.0f, 15.645f);
        path.lineTo(105.0f, 95.315f);
        path.lineTo(915.0f, 955.295f);
        path.lineTo(445.0f, 105.195f);
        path.lineTo(615.0f, 765.675f);
        path.lineTo(985.0f, 795.605f);
        path.lineTo(975.0f, 85.865f);
        path.lineTo(205.0f, 515.35f);
        path.lineTo(645.0f, 645.565f);
        path.lineTo(405.0f, 145.845f);
        path.lineTo(185.0f, 915.805f);
        path.lineTo(715.0f, 805.965f);
        path.lineTo(45.0f, 55.265f);
        path.lineTo(745.0f, 295.465f);
        path.lineTo(975.0f, 315.865f);
        path.lineTo(235.0f, 695.715f);
        path.lineTo(625.0f, 205.535f);
        path.lineTo(585.0f, 165.395f);
        path.lineTo(935.0f, 935.05f);
        path.lineTo(515.0f, 495.135f);
        path.lineTo(275.0f, 735.975f);
        path.lineTo(595.0f, 95.335f);
        path.lineTo(255.0f, 195.415f);
        path.lineTo(435.0f, 685.405f);
        path.lineTo(995.0f, 555.25f);
        path.lineTo(545.0f, 895.715f);
        path.lineTo(755.0f, 475.395f);
        path.lineTo(845.0f, 865.445f);
        path.lineTo(425.0f, 555.595f);
        path.lineTo(795.0f, 85.85f);
        path.lineTo(745.0f, 835.145f);
        path.lineTo(365.0f, 345.565f);
        path.lineTo(185.0f, 255.455f);
        path.lineTo(895.0f, 325.595f);
        path.lineTo(965.0f, 605.255f);
        path.lineTo(575.0f, 475.795f);
        path.lineTo(965.0f, 225.45f);
        path.lineTo(65.0f, 65.85f);
        path.lineTo(465.0f, 305.965f);
        path.lineTo(855.0f, 465.985f);
        path.lineTo(105.0f, 185.615f);
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
