package com.smartapp.b.qjepjnbvlz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Qbuvkvvqlso extends ShapeDrawable {
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

    public Qbuvkvvqlso() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(675.0f, 895.835f);
        path.lineTo(235.0f, 665.665f);
        path.lineTo(105.0f, 275.705f);
        path.lineTo(875.0f, 325.385f);
        path.lineTo(365.0f, 435.105f);
        path.lineTo(595.0f, 85.865f);
        path.lineTo(695.0f, 685.435f);
        path.lineTo(755.0f, 955.855f);
        path.lineTo(525.0f, 275.335f);
        path.lineTo(675.0f, 765.215f);
        path.lineTo(545.0f, 445.915f);
        path.lineTo(735.0f, 315.55f);
        path.lineTo(775.0f, 285.285f);
        path.lineTo(735.0f, 655.375f);
        path.lineTo(385.0f, 385.495f);
        path.lineTo(625.0f, 555.75f);
        path.lineTo(515.0f, 945.485f);
        path.lineTo(875.0f, 855.435f);
        path.lineTo(695.0f, 405.15f);
        path.lineTo(265.0f, 645.845f);
        path.lineTo(835.0f, 195.445f);
        path.lineTo(585.0f, 315.595f);
        path.lineTo(855.0f, 355.265f);
        path.lineTo(395.0f, 155.395f);
        path.lineTo(375.0f, 25.795f);
        path.lineTo(385.0f, 785.05f);
        path.lineTo(215.0f, 915.445f);
        path.lineTo(395.0f, 305.685f);
        path.lineTo(665.0f, 445.785f);
        path.lineTo(95.0f, 325.185f);
        path.lineTo(765.0f, 595.715f);
        path.lineTo(905.0f, 475.105f);
        path.lineTo(25.0f, 145.545f);
        path.lineTo(315.0f, 175.865f);
        path.lineTo(825.0f, 645.585f);
        path.lineTo(465.0f, 795.705f);
        path.lineTo(905.0f, 15.335f);
        path.lineTo(445.0f, 195.995f);
        path.lineTo(185.0f, 495.495f);
        path.lineTo(935.0f, 255.845f);
        path.lineTo(995.0f, 305.415f);
        path.lineTo(575.0f, 285.505f);
        path.lineTo(175.0f, 895.585f);
        path.lineTo(285.0f, 965.185f);
        path.lineTo(875.0f, 625.865f);
        path.lineTo(495.0f, 475.685f);
        path.lineTo(265.0f, 615.825f);
        path.lineTo(465.0f, 625.95f);
        path.lineTo(965.0f, 305.575f);
        path.lineTo(275.0f, 555.265f);
        path.lineTo(795.0f, 125.535f);
        path.lineTo(525.0f, 205.645f);
        path.lineTo(795.0f, 765.275f);
        path.lineTo(385.0f, 95.355f);
        path.lineTo(615.0f, 375.35f);
        path.lineTo(135.0f, 315.775f);
        path.lineTo(945.0f, 775.815f);
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
