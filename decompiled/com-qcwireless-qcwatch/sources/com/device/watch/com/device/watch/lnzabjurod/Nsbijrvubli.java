package com.device.watch.com.device.watch.lnzabjurod;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Nsbijrvubli extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Nsbijrvubli() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(25.0f, 735.465f);
        path.lineTo(55.0f, 85.735f);
        path.lineTo(435.0f, 925.815f);
        path.lineTo(495.0f, 415.715f);
        path.lineTo(185.0f, 115.155f);
        path.lineTo(735.0f, 635.05f);
        path.lineTo(645.0f, 705.705f);
        path.lineTo(755.0f, 375.175f);
        path.lineTo(85.0f, 485.275f);
        path.lineTo(375.0f, 645.635f);
        path.lineTo(405.0f, 945.655f);
        path.lineTo(955.0f, 165.65f);
        path.lineTo(985.0f, 465.955f);
        path.lineTo(825.0f, 295.155f);
        path.lineTo(345.0f, 155.895f);
        path.lineTo(995.0f, 125.225f);
        path.lineTo(855.0f, 945.325f);
        path.lineTo(765.0f, 115.345f);
        path.lineTo(895.0f, 525.265f);
        path.lineTo(25.0f, 535.315f);
        path.lineTo(455.0f, 55.795f);
        path.lineTo(575.0f, 35.785f);
        path.lineTo(405.0f, 615.325f);
        path.lineTo(45.0f, 735.155f);
        path.lineTo(415.0f, 515.195f);
        path.lineTo(155.0f, 85.185f);
        path.lineTo(155.0f, 355.415f);
        path.lineTo(325.0f, 655.555f);
        path.lineTo(855.0f, 15.85f);
        path.lineTo(405.0f, 65.465f);
        path.lineTo(15.0f, 345.795f);
        path.lineTo(695.0f, 825.705f);
        path.lineTo(65.0f, 885.185f);
        path.lineTo(165.0f, 915.695f);
        path.lineTo(355.0f, 605.755f);
        path.lineTo(755.0f, 635.05f);
        path.lineTo(475.0f, 845.915f);
        path.lineTo(105.0f, 315.905f);
        path.lineTo(375.0f, 305.445f);
        path.lineTo(655.0f, 265.305f);
        path.lineTo(665.0f, 185.825f);
        path.lineTo(525.0f, 665.305f);
        path.lineTo(215.0f, 865.405f);
        path.lineTo(555.0f, 545.505f);
        path.lineTo(335.0f, 935.935f);
        path.lineTo(445.0f, 505.655f);
        path.lineTo(535.0f, 85.925f);
        path.lineTo(55.0f, 455.605f);
        path.lineTo(75.0f, 75.625f);
        path.lineTo(75.0f, 865.295f);
        path.lineTo(155.0f, 105.55f);
        path.lineTo(275.0f, 365.75f);
        path.lineTo(315.0f, 295.715f);
        path.lineTo(975.0f, 955.685f);
        path.lineTo(725.0f, 835.895f);
        path.lineTo(425.0f, 335.765f);
        path.lineTo(295.0f, 985.845f);
        path.lineTo(685.0f, 815.145f);
        path.lineTo(255.0f, 435.815f);
        path.lineTo(975.0f, 155.615f);
        path.lineTo(575.0f, 95.985f);
        path.lineTo(795.0f, 385.695f);
        path.lineTo(195.0f, 15.695f);
        path.lineTo(55.0f, 175.335f);
        path.lineTo(975.0f, 315.775f);
        path.lineTo(185.0f, 845.425f);
        path.lineTo(935.0f, 795.545f);
        path.lineTo(665.0f, 335.445f);
        path.lineTo(465.0f, 185.985f);
        path.lineTo(715.0f, 895.545f);
        path.lineTo(405.0f, 965.575f);
        path.lineTo(935.0f, 605.975f);
        path.lineTo(315.0f, 885.235f);
        path.lineTo(655.0f, 495.185f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1011.0f, this.bounds.height() / 1011.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
