package com.device.watch.com.device.watch.ohbsxxwnzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Bozhcoknxcx extends ShapeDrawable {
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

    public Bozhcoknxcx() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(775.0f, 975.885f);
        path.lineTo(735.0f, 415.05f);
        path.lineTo(865.0f, 665.445f);
        path.lineTo(685.0f, 35.525f);
        path.lineTo(645.0f, 705.865f);
        path.lineTo(895.0f, 405.985f);
        path.lineTo(605.0f, 65.255f);
        path.lineTo(925.0f, 215.335f);
        path.lineTo(395.0f, 545.235f);
        path.lineTo(835.0f, 875.665f);
        path.lineTo(5.0f, 5.865f);
        path.lineTo(625.0f, 405.805f);
        path.lineTo(965.0f, 225.635f);
        path.lineTo(525.0f, 485.735f);
        path.lineTo(445.0f, 825.765f);
        path.lineTo(855.0f, 365.305f);
        path.lineTo(215.0f, 175.155f);
        path.lineTo(195.0f, 395.485f);
        path.lineTo(925.0f, 665.315f);
        path.lineTo(965.0f, 85.695f);
        path.lineTo(915.0f, 295.05f);
        path.lineTo(875.0f, 85.455f);
        path.lineTo(205.0f, 325.115f);
        path.lineTo(375.0f, 645.135f);
        path.lineTo(575.0f, 225.435f);
        path.lineTo(585.0f, 175.215f);
        path.lineTo(715.0f, 725.85f);
        path.lineTo(325.0f, 365.225f);
        path.lineTo(375.0f, 595.455f);
        path.lineTo(675.0f, 275.165f);
        path.lineTo(725.0f, 75.75f);
        path.lineTo(325.0f, 955.845f);
        path.lineTo(985.0f, 625.555f);
        path.lineTo(995.0f, 965.695f);
        path.lineTo(65.0f, 925.655f);
        path.lineTo(335.0f, 295.835f);
        path.lineTo(165.0f, 675.35f);
        path.lineTo(815.0f, 995.515f);
        path.lineTo(345.0f, 995.575f);
        path.lineTo(685.0f, 765.455f);
        path.lineTo(475.0f, 645.395f);
        path.lineTo(685.0f, 995.95f);
        path.lineTo(815.0f, 25.695f);
        path.lineTo(525.0f, 305.755f);
        path.lineTo(415.0f, 285.125f);
        path.lineTo(625.0f, 95.275f);
        path.lineTo(735.0f, 55.105f);
        path.lineTo(185.0f, 295.865f);
        path.lineTo(955.0f, 875.315f);
        path.lineTo(465.0f, 695.605f);
        path.lineTo(135.0f, 565.865f);
        path.lineTo(415.0f, 215.965f);
        path.lineTo(625.0f, 795.875f);
        path.lineTo(265.0f, 975.455f);
        path.lineTo(655.0f, 795.885f);
        path.lineTo(495.0f, 865.545f);
        path.lineTo(325.0f, 125.545f);
        path.lineTo(845.0f, 135.585f);
        path.lineTo(925.0f, 825.515f);
        path.lineTo(25.0f, 735.725f);
        path.lineTo(275.0f, 425.175f);
        path.lineTo(275.0f, 725.875f);
        path.lineTo(535.0f, 835.985f);
        path.lineTo(795.0f, 605.325f);
        path.lineTo(775.0f, 195.845f);
        path.lineTo(115.0f, 405.295f);
        path.lineTo(165.0f, 585.915f);
        path.lineTo(765.0f, 915.285f);
        path.lineTo(155.0f, 645.685f);
        path.lineTo(265.0f, 805.405f);
        path.lineTo(815.0f, 75.815f);
        path.lineTo(625.0f, 715.685f);
        path.lineTo(345.0f, 545.845f);
        path.lineTo(815.0f, 425.755f);
        path.lineTo(445.0f, 915.935f);
        path.lineTo(725.0f, 745.755f);
        path.lineTo(525.0f, 145.355f);
        path.lineTo(525.0f, 815.475f);
        path.lineTo(655.0f, 455.825f);
        path.lineTo(555.0f, 215.905f);
        path.lineTo(55.0f, 635.35f);
        path.lineTo(495.0f, 665.535f);
        path.lineTo(325.0f, 5.925f);
        path.lineTo(985.0f, 145.395f);
        path.lineTo(905.0f, 835.365f);
        path.lineTo(685.0f, 345.665f);
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
