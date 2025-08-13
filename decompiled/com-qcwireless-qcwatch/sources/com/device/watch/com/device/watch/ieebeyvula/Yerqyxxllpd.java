package com.device.watch.com.device.watch.ieebeyvula;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes.dex */
public class Yerqyxxllpd extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Yerqyxxllpd() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(775.0f, 25.525f);
        path.lineTo(915.0f, 385.135f);
        path.lineTo(75.0f, 475.455f);
        path.lineTo(645.0f, 45.305f);
        path.lineTo(125.0f, 725.565f);
        path.lineTo(865.0f, 705.45f);
        path.lineTo(375.0f, 365.75f);
        path.lineTo(555.0f, 985.435f);
        path.lineTo(395.0f, 615.345f);
        path.lineTo(975.0f, 985.325f);
        path.lineTo(695.0f, 565.435f);
        path.lineTo(185.0f, 35.465f);
        path.lineTo(415.0f, 505.215f);
        path.lineTo(225.0f, 425.95f);
        path.lineTo(755.0f, 85.845f);
        path.lineTo(885.0f, 845.595f);
        path.lineTo(505.0f, 485.255f);
        path.lineTo(575.0f, 965.975f);
        path.lineTo(735.0f, 435.235f);
        path.lineTo(115.0f, 195.765f);
        path.lineTo(875.0f, 435.905f);
        path.lineTo(495.0f, 635.515f);
        path.lineTo(935.0f, 835.995f);
        path.lineTo(985.0f, 345.725f);
        path.lineTo(545.0f, 435.225f);
        path.lineTo(835.0f, 365.165f);
        path.lineTo(165.0f, 565.235f);
        path.lineTo(385.0f, 395.255f);
        path.lineTo(495.0f, 445.865f);
        path.lineTo(495.0f, 845.265f);
        path.lineTo(345.0f, 305.865f);
        path.lineTo(375.0f, 425.765f);
        path.lineTo(25.0f, 445.05f);
        path.lineTo(805.0f, 165.305f);
        path.lineTo(885.0f, 305.705f);
        path.lineTo(995.0f, 545.135f);
        path.lineTo(635.0f, 125.695f);
        path.lineTo(905.0f, 885.965f);
        path.lineTo(715.0f, 965.635f);
        path.lineTo(495.0f, 255.205f);
        path.lineTo(195.0f, 405.195f);
        path.lineTo(325.0f, 915.55f);
        path.lineTo(95.0f, 735.595f);
        path.lineTo(635.0f, 95.105f);
        path.lineTo(925.0f, 525.145f);
        path.lineTo(595.0f, 975.885f);
        path.lineTo(25.0f, 165.905f);
        path.lineTo(95.0f, 395.45f);
        path.lineTo(545.0f, 805.925f);
        path.lineTo(115.0f, 295.545f);
        path.lineTo(65.0f, 925.715f);
        path.lineTo(665.0f, 835.505f);
        path.lineTo(735.0f, 155.125f);
        path.lineTo(735.0f, 265.905f);
        path.lineTo(785.0f, 975.75f);
        path.lineTo(25.0f, 795.285f);
        path.lineTo(275.0f, 555.715f);
        path.lineTo(65.0f, 935.815f);
        path.lineTo(875.0f, 555.955f);
        path.lineTo(505.0f, 265.165f);
        path.lineTo(775.0f, 575.135f);
        path.lineTo(425.0f, 445.995f);
        path.lineTo(255.0f, 45.625f);
        path.lineTo(995.0f, 375.755f);
        path.lineTo(835.0f, 455.515f);
        path.lineTo(735.0f, 85.585f);
        path.lineTo(815.0f, 685.685f);
        path.lineTo(85.0f, 405.175f);
        path.lineTo(725.0f, 665.115f);
        path.lineTo(745.0f, 615.725f);
        path.lineTo(105.0f, 515.255f);
        path.lineTo(575.0f, 995.695f);
        path.lineTo(715.0f, 845.345f);
        path.lineTo(575.0f, 365.175f);
        path.lineTo(255.0f, 605.985f);
        path.lineTo(455.0f, 595.505f);
        path.lineTo(565.0f, 425.435f);
        path.lineTo(275.0f, 335.995f);
        path.lineTo(705.0f, 225.865f);
        path.lineTo(355.0f, 645.415f);
        path.lineTo(715.0f, 765.05f);
        path.lineTo(545.0f, 105.25f);
        path.lineTo(425.0f, 135.75f);
        path.lineTo(295.0f, 945.195f);
        path.lineTo(995.0f, 455.335f);
        path.lineTo(275.0f, 965.545f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
