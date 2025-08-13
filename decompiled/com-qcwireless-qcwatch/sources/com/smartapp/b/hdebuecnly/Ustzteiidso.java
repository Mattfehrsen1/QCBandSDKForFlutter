package com.smartapp.b.hdebuecnly;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ustzteiidso extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ustzteiidso() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(65.0f, 105.65f);
        path.lineTo(10055.0f, 925.915f);
        path.lineTo(10055.0f, 515.105f);
        path.lineTo(855.0f, 665.205f);
        path.lineTo(915.0f, 565.385f);
        path.lineTo(395.0f, 75.995f);
        path.lineTo(995.0f, 165.05f);
        path.lineTo(15.0f, 485.765f);
        path.lineTo(275.0f, 725.815f);
        path.lineTo(325.0f, 375.965f);
        path.lineTo(275.0f, 10055.305f);
        path.lineTo(965.0f, 65.25f);
        path.lineTo(335.0f, 815.655f);
        path.lineTo(915.0f, 175.535f);
        path.lineTo(515.0f, 155.75f);
        path.lineTo(155.0f, 815.945f);
        path.lineTo(85.0f, 585.255f);
        path.lineTo(835.0f, 925.165f);
        path.lineTo(705.0f, 5.155f);
        path.lineTo(205.0f, 345.935f);
        path.lineTo(235.0f, 885.655f);
        path.lineTo(705.0f, 985.645f);
        path.lineTo(835.0f, 445.165f);
        path.lineTo(535.0f, 295.405f);
        path.lineTo(905.0f, 345.15f);
        path.lineTo(515.0f, 955.415f);
        path.lineTo(485.0f, 545.205f);
        path.lineTo(995.0f, 455.655f);
        path.lineTo(645.0f, 905.135f);
        path.lineTo(285.0f, 935.165f);
        path.lineTo(265.0f, 255.825f);
        path.lineTo(645.0f, 345.335f);
        path.lineTo(985.0f, 415.805f);
        path.lineTo(715.0f, 825.235f);
        path.lineTo(765.0f, 905.475f);
        path.lineTo(145.0f, 10055.405f);
        path.lineTo(755.0f, 455.115f);
        path.lineTo(165.0f, 825.205f);
        path.lineTo(10055.0f, 625.165f);
        path.lineTo(835.0f, 935.985f);
        path.lineTo(25.0f, 485.535f);
        path.lineTo(975.0f, 75.575f);
        path.lineTo(205.0f, 795.225f);
        path.lineTo(785.0f, 415.625f);
        path.lineTo(185.0f, 695.865f);
        path.lineTo(935.0f, 815.395f);
        path.lineTo(965.0f, 235.505f);
        path.lineTo(525.0f, 585.475f);
        path.lineTo(785.0f, 645.655f);
        path.lineTo(325.0f, 865.375f);
        path.lineTo(345.0f, 345.915f);
        path.lineTo(805.0f, 805.645f);
        path.lineTo(225.0f, 755.765f);
        path.lineTo(495.0f, 635.15f);
        path.lineTo(755.0f, 935.185f);
        path.lineTo(545.0f, 475.335f);
        path.lineTo(135.0f, 35.905f);
        path.lineTo(435.0f, 15.345f);
        path.lineTo(575.0f, 655.125f);
        path.lineTo(905.0f, 455.775f);
        path.lineTo(565.0f, 15.365f);
        path.lineTo(145.0f, 755.165f);
        path.lineTo(465.0f, 865.385f);
        path.lineTo(575.0f, 465.835f);
        path.lineTo(435.0f, 495.495f);
        path.lineTo(135.0f, 435.485f);
        path.lineTo(555.0f, 95.585f);
        path.lineTo(365.0f, 665.925f);
        path.lineTo(365.0f, 745.235f);
        path.lineTo(605.0f, 85.845f);
        path.lineTo(555.0f, 725.755f);
        path.lineTo(655.0f, 745.165f);
        path.lineTo(785.0f, 565.365f);
        path.lineTo(995.0f, 685.365f);
        path.lineTo(305.0f, 605.885f);
        path.lineTo(495.0f, 605.455f);
        path.lineTo(925.0f, 795.815f);
        path.lineTo(625.0f, 125.885f);
        path.lineTo(675.0f, 395.385f);
        path.lineTo(965.0f, 745.815f);
        path.lineTo(955.0f, 495.595f);
        path.lineTo(545.0f, 265.855f);
        path.lineTo(305.0f, 95.605f);
        path.lineTo(5.0f, 615.485f);
        path.lineTo(565.0f, 995.325f);
        path.lineTo(105.0f, 215.235f);
        path.lineTo(595.0f, 135.35f);
        path.lineTo(845.0f, 955.285f);
        path.lineTo(995.0f, 265.135f);
        path.lineTo(595.0f, 765.495f);
        path.lineTo(405.0f, 965.395f);
        path.lineTo(735.0f, 105.145f);
        path.lineTo(705.0f, 565.825f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
