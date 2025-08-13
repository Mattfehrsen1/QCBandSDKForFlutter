package com.smartapp.a.ingdebwzaj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Qthbxsmyqxy extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1000;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Qthbxsmyqxy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(655.0f, 175.25f);
        path.lineTo(895.0f, 975.125f);
        path.lineTo(655.0f, 165.755f);
        path.lineTo(135.0f, 195.345f);
        path.lineTo(75.0f, 935.35f);
        path.lineTo(325.0f, 845.485f);
        path.lineTo(805.0f, 845.505f);
        path.lineTo(325.0f, 965.625f);
        path.lineTo(465.0f, 725.905f);
        path.lineTo(345.0f, 715.825f);
        path.lineTo(415.0f, 685.705f);
        path.lineTo(375.0f, 375.685f);
        path.lineTo(595.0f, 825.205f);
        path.lineTo(895.0f, 35.855f);
        path.lineTo(175.0f, 735.235f);
        path.lineTo(55.0f, 745.345f);
        path.lineTo(595.0f, 605.575f);
        path.lineTo(405.0f, 285.10004f);
        path.lineTo(895.0f, 315.875f);
        path.lineTo(925.0f, 705.205f);
        path.lineTo(25.0f, 395.395f);
        path.lineTo(455.0f, 915.155f);
        path.lineTo(655.0f, 705.15f);
        path.lineTo(855.0f, 645.885f);
        path.lineTo(75.0f, 465.855f);
        path.lineTo(645.0f, 35.555f);
        path.lineTo(75.0f, 595.385f);
        path.lineTo(345.0f, 405.215f);
        path.lineTo(385.0f, 405.455f);
        path.lineTo(735.0f, 595.785f);
        path.lineTo(505.0f, 55.45f);
        path.lineTo(45.0f, 555.515f);
        path.lineTo(345.0f, 335.05f);
        path.lineTo(725.0f, 785.305f);
        path.lineTo(645.0f, 925.115f);
        path.lineTo(625.0f, 345.645f);
        path.lineTo(395.0f, 595.535f);
        path.lineTo(395.0f, 105.885f);
        path.lineTo(695.0f, 525.905f);
        path.lineTo(675.0f, 75.55f);
        path.lineTo(635.0f, 225.575f);
        path.lineTo(735.0f, 285.375f);
        path.lineTo(125.0f, 545.965f);
        path.lineTo(25.0f, 75.775f);
        path.lineTo(825.0f, 975.535f);
        path.lineTo(995.0f, 25.35f);
        path.lineTo(845.0f, 645.195f);
        path.lineTo(435.0f, 295.505f);
        path.lineTo(955.0f, 285.05f);
        path.lineTo(735.0f, 365.955f);
        path.lineTo(595.0f, 545.145f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1000.0f, this.bounds.height() / 1000.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
