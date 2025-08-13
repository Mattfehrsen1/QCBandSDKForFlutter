package com.smartapp.b.nirmvamfyl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ccrpzzrktkc extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1009;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ccrpzzrktkc() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(685.0f, 425.445f);
        path.lineTo(45.0f, 15.465f);
        path.lineTo(405.0f, 495.895f);
        path.lineTo(625.0f, 205.765f);
        path.lineTo(505.0f, 995.665f);
        path.lineTo(615.0f, 535.525f);
        path.lineTo(565.0f, 15.45f);
        path.lineTo(585.0f, 715.835f);
        path.lineTo(535.0f, 625.225f);
        path.lineTo(585.0f, 295.405f);
        path.lineTo(95.0f, 165.345f);
        path.lineTo(295.0f, 975.415f);
        path.lineTo(235.0f, 785.655f);
        path.lineTo(215.0f, 795.775f);
        path.lineTo(775.0f, 795.415f);
        path.lineTo(405.0f, 945.655f);
        path.lineTo(825.0f, 125.735f);
        path.lineTo(295.0f, 285.975f);
        path.lineTo(985.0f, 395.525f);
        path.lineTo(235.0f, 195.865f);
        path.lineTo(645.0f, 945.815f);
        path.lineTo(95.0f, 285.555f);
        path.lineTo(155.0f, 985.665f);
        path.lineTo(905.0f, 305.535f);
        path.lineTo(805.0f, 605.105f);
        path.lineTo(525.0f, 705.705f);
        path.lineTo(995.0f, 125.295f);
        path.lineTo(975.0f, 25.525f);
        path.lineTo(365.0f, 745.275f);
        path.lineTo(655.0f, 935.905f);
        path.lineTo(445.0f, 575.165f);
        path.lineTo(425.0f, 895.665f);
        path.lineTo(935.0f, 885.925f);
        path.lineTo(645.0f, 395.815f);
        path.lineTo(115.0f, 235.825f);
        path.lineTo(805.0f, 125.985f);
        path.lineTo(265.0f, 495.855f);
        path.lineTo(885.0f, 265.35f);
        path.lineTo(795.0f, 735.285f);
        path.lineTo(55.0f, 335.675f);
        path.lineTo(375.0f, 435.705f);
        path.lineTo(845.0f, 495.775f);
        path.lineTo(715.0f, 595.255f);
        path.lineTo(45.0f, 425.65f);
        path.lineTo(195.0f, 295.445f);
        path.lineTo(585.0f, 15.45f);
        path.lineTo(585.0f, 65.605f);
        path.lineTo(575.0f, 295.635f);
        path.lineTo(115.0f, 875.835f);
        path.lineTo(455.0f, 555.55f);
        path.lineTo(305.0f, 85.675f);
        path.lineTo(195.0f, 765.145f);
        path.lineTo(915.0f, 795.735f);
        path.lineTo(285.0f, 935.685f);
        path.lineTo(575.0f, 925.965f);
        path.lineTo(725.0f, 665.55f);
        path.lineTo(205.0f, 925.385f);
        path.lineTo(275.0f, 705.665f);
        path.lineTo(775.0f, 795.785f);
        path.lineTo(165.0f, 515.115f);
        path.lineTo(75.0f, 485.455f);
        path.lineTo(355.0f, 165.335f);
        path.lineTo(995.0f, 595.545f);
        path.lineTo(475.0f, 895.175f);
        path.lineTo(695.0f, 475.395f);
        path.lineTo(395.0f, 315.905f);
        path.lineTo(195.0f, 745.315f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1009.0f, this.bounds.height() / 1009.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
