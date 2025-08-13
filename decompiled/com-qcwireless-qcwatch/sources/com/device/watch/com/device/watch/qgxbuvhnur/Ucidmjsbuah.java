package com.device.watch.com.device.watch.qgxbuvhnur;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Ucidmjsbuah extends ShapeDrawable {
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

    public Ucidmjsbuah() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(635.0f, 615.95f);
        path.lineTo(645.0f, 665.215f);
        path.lineTo(845.0f, 295.935f);
        path.lineTo(995.0f, 395.475f);
        path.lineTo(825.0f, 735.65f);
        path.lineTo(745.0f, 445.95f);
        path.lineTo(895.0f, 935.975f);
        path.lineTo(365.0f, 585.505f);
        path.lineTo(205.0f, 385.995f);
        path.lineTo(185.0f, 85.125f);
        path.lineTo(355.0f, 995.35f);
        path.lineTo(635.0f, 325.435f);
        path.lineTo(15.0f, 695.25f);
        path.lineTo(695.0f, 255.225f);
        path.lineTo(895.0f, 685.275f);
        path.lineTo(865.0f, 85.125f);
        path.lineTo(125.0f, 455.555f);
        path.lineTo(545.0f, 355.805f);
        path.lineTo(955.0f, 505.295f);
        path.lineTo(35.0f, 205.485f);
        path.lineTo(795.0f, 205.605f);
        path.lineTo(525.0f, 725.395f);
        path.lineTo(945.0f, 995.985f);
        path.lineTo(275.0f, 755.965f);
        path.lineTo(155.0f, 545.505f);
        path.lineTo(675.0f, 495.255f);
        path.lineTo(635.0f, 175.45f);
        path.lineTo(195.0f, 575.55f);
        path.lineTo(45.0f, 535.615f);
        path.lineTo(235.0f, 545.235f);
        path.lineTo(625.0f, 445.75f);
        path.lineTo(575.0f, 525.745f);
        path.lineTo(435.0f, 605.745f);
        path.lineTo(865.0f, 535.155f);
        path.lineTo(325.0f, 325.765f);
        path.lineTo(195.0f, 425.985f);
        path.lineTo(755.0f, 5.95f);
        path.lineTo(145.0f, 635.235f);
        path.lineTo(205.0f, 25.375f);
        path.lineTo(775.0f, 885.15f);
        path.lineTo(595.0f, 895.905f);
        path.lineTo(645.0f, 835.595f);
        path.lineTo(25.0f, 305.515f);
        path.lineTo(525.0f, 135.825f);
        path.lineTo(895.0f, 745.475f);
        path.lineTo(10095.0f, 755.05f);
        path.lineTo(145.0f, 995.35f);
        path.lineTo(225.0f, 405.465f);
        path.lineTo(855.0f, 415.445f);
        path.lineTo(805.0f, 345.465f);
        path.lineTo(365.0f, 675.845f);
        path.lineTo(145.0f, 835.665f);
        path.lineTo(445.0f, 625.395f);
        path.lineTo(475.0f, 925.205f);
        path.lineTo(425.0f, 255.595f);
        path.lineTo(305.0f, 175.495f);
        path.lineTo(805.0f, 105.905f);
        path.lineTo(805.0f, 435.755f);
        path.lineTo(635.0f, 775.505f);
        path.lineTo(885.0f, 325.135f);
        path.lineTo(785.0f, 555.255f);
        path.lineTo(735.0f, 145.705f);
        path.lineTo(825.0f, 795.795f);
        path.lineTo(385.0f, 895.345f);
        path.lineTo(955.0f, 195.25f);
        path.lineTo(325.0f, 795.415f);
        path.lineTo(465.0f, 255.595f);
        path.lineTo(685.0f, 575.105f);
        path.lineTo(655.0f, 605.335f);
        path.lineTo(325.0f, 875.135f);
        path.lineTo(575.0f, 805.545f);
        path.lineTo(55.0f, 885.05f);
        path.lineTo(925.0f, 10095.275f);
        path.lineTo(325.0f, 535.875f);
        path.lineTo(855.0f, 465.905f);
        path.lineTo(275.0f, 645.155f);
        path.lineTo(815.0f, 375.625f);
        path.lineTo(445.0f, 255.75f);
        path.lineTo(765.0f, 575.55f);
        path.lineTo(635.0f, 285.905f);
        path.lineTo(345.0f, 115.345f);
        path.lineTo(525.0f, 305.625f);
        path.lineTo(155.0f, 35.275f);
        path.lineTo(375.0f, 735.315f);
        path.lineTo(215.0f, 625.35f);
        path.lineTo(635.0f, 955.215f);
        path.lineTo(215.0f, 465.405f);
        path.lineTo(605.0f, 985.815f);
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
