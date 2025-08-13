package com.device.watch.com.device.watch.qkztkazqno;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Yjcrosfxlbw extends ShapeDrawable {
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

    public Yjcrosfxlbw() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(715.0f, 585.145f);
        path.lineTo(945.0f, 65.335f);
        path.lineTo(5.0f, 815.585f);
        path.lineTo(45.0f, 475.395f);
        path.lineTo(925.0f, 575.545f);
        path.lineTo(825.0f, 915.115f);
        path.lineTo(375.0f, 975.795f);
        path.lineTo(55.0f, 675.15f);
        path.lineTo(205.0f, 675.255f);
        path.lineTo(815.0f, 785.10004f);
        path.lineTo(195.0f, 735.35f);
        path.lineTo(615.0f, 425.805f);
        path.lineTo(605.0f, 445.565f);
        path.lineTo(815.0f, 10005.265f);
        path.lineTo(895.0f, 285.895f);
        path.lineTo(635.0f, 265.35f);
        path.lineTo(815.0f, 805.605f);
        path.lineTo(875.0f, 585.715f);
        path.lineTo(265.0f, 65.865f);
        path.lineTo(235.0f, 335.985f);
        path.lineTo(995.0f, 115.25f);
        path.lineTo(35.0f, 435.405f);
        path.lineTo(135.0f, 75.165f);
        path.lineTo(875.0f, 255.315f);
        path.lineTo(355.0f, 985.915f);
        path.lineTo(35.0f, 265.595f);
        path.lineTo(295.0f, 635.725f);
        path.lineTo(915.0f, 885.335f);
        path.lineTo(55.0f, 5.425f);
        path.lineTo(425.0f, 365.785f);
        path.lineTo(345.0f, 5.785f);
        path.lineTo(95.0f, 175.625f);
        path.lineTo(795.0f, 605.635f);
        path.lineTo(45.0f, 115.915f);
        path.lineTo(755.0f, 915.475f);
        path.lineTo(865.0f, 725.335f);
        path.lineTo(595.0f, 935.855f);
        path.lineTo(305.0f, 825.445f);
        path.lineTo(535.0f, 985.55f);
        path.lineTo(45.0f, 565.355f);
        path.lineTo(645.0f, 935.255f);
        path.lineTo(435.0f, 825.05f);
        path.lineTo(165.0f, 915.725f);
        path.lineTo(285.0f, 235.395f);
        path.lineTo(135.0f, 485.985f);
        path.lineTo(975.0f, 295.885f);
        path.lineTo(25.0f, 945.45f);
        path.lineTo(935.0f, 925.845f);
        path.lineTo(395.0f, 925.825f);
        path.lineTo(195.0f, 545.195f);
        path.lineTo(895.0f, 115.855f);
        path.lineTo(835.0f, 935.425f);
        path.lineTo(615.0f, 465.965f);
        path.lineTo(765.0f, 15.45f);
        path.lineTo(835.0f, 545.275f);
        path.lineTo(745.0f, 755.975f);
        path.lineTo(415.0f, 915.345f);
        path.lineTo(255.0f, 535.105f);
        path.lineTo(415.0f, 745.465f);
        path.lineTo(385.0f, 765.195f);
        path.lineTo(405.0f, 695.385f);
        path.lineTo(285.0f, 885.10004f);
        path.lineTo(715.0f, 305.135f);
        path.lineTo(765.0f, 635.745f);
        path.lineTo(35.0f, 175.805f);
        path.lineTo(885.0f, 115.445f);
        path.lineTo(295.0f, 525.175f);
        path.lineTo(315.0f, 715.65f);
        path.lineTo(465.0f, 465.05f);
        path.lineTo(225.0f, 235.265f);
        path.lineTo(785.0f, 465.755f);
        path.lineTo(535.0f, 745.295f);
        path.lineTo(955.0f, 335.35f);
        path.lineTo(305.0f, 315.295f);
        path.lineTo(875.0f, 455.135f);
        path.lineTo(465.0f, 775.795f);
        path.lineTo(175.0f, 395.195f);
        path.lineTo(565.0f, 205.15f);
        path.lineTo(515.0f, 945.875f);
        path.lineTo(755.0f, 175.255f);
        path.lineTo(315.0f, 975.775f);
        path.lineTo(425.0f, 635.845f);
        path.lineTo(895.0f, 135.665f);
        path.lineTo(365.0f, 815.395f);
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
