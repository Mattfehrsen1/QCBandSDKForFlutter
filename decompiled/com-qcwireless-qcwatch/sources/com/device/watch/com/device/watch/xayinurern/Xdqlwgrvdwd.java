package com.device.watch.com.device.watch.xayinurern;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Xdqlwgrvdwd extends ShapeDrawable {
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

    public Xdqlwgrvdwd() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(665.0f, 985.595f);
        path.lineTo(575.0f, 555.885f);
        path.lineTo(755.0f, 515.725f);
        path.lineTo(375.0f, 25.45f);
        path.lineTo(645.0f, 745.775f);
        path.lineTo(375.0f, 265.15f);
        path.lineTo(935.0f, 625.705f);
        path.lineTo(525.0f, 675.25f);
        path.lineTo(455.0f, 835.185f);
        path.lineTo(845.0f, 435.185f);
        path.lineTo(575.0f, 945.515f);
        path.lineTo(305.0f, 755.755f);
        path.lineTo(945.0f, 835.935f);
        path.lineTo(365.0f, 425.135f);
        path.lineTo(495.0f, 995.585f);
        path.lineTo(355.0f, 545.10095f);
        path.lineTo(35.0f, 10095.935f);
        path.lineTo(935.0f, 375.45f);
        path.lineTo(995.0f, 385.835f);
        path.lineTo(275.0f, 295.675f);
        path.lineTo(65.0f, 715.845f);
        path.lineTo(345.0f, 735.815f);
        path.lineTo(355.0f, 725.185f);
        path.lineTo(305.0f, 885.725f);
        path.lineTo(265.0f, 715.585f);
        path.lineTo(825.0f, 425.345f);
        path.lineTo(605.0f, 835.945f);
        path.lineTo(275.0f, 745.865f);
        path.lineTo(155.0f, 485.225f);
        path.lineTo(675.0f, 975.85f);
        path.lineTo(405.0f, 965.495f);
        path.lineTo(445.0f, 305.685f);
        path.lineTo(355.0f, 575.25f);
        path.lineTo(265.0f, 605.705f);
        path.lineTo(145.0f, 65.705f);
        path.lineTo(355.0f, 275.155f);
        path.lineTo(615.0f, 265.365f);
        path.lineTo(575.0f, 125.815f);
        path.lineTo(135.0f, 625.55f);
        path.lineTo(895.0f, 305.10095f);
        path.lineTo(745.0f, 665.255f);
        path.lineTo(95.0f, 315.135f);
        path.lineTo(965.0f, 10095.385f);
        path.lineTo(35.0f, 425.765f);
        path.lineTo(545.0f, 635.985f);
        path.lineTo(675.0f, 55.715f);
        path.lineTo(695.0f, 295.215f);
        path.lineTo(845.0f, 615.595f);
        path.lineTo(215.0f, 445.105f);
        path.lineTo(355.0f, 775.455f);
        path.lineTo(295.0f, 65.115f);
        path.lineTo(775.0f, 815.335f);
        path.lineTo(665.0f, 775.95f);
        path.lineTo(935.0f, 295.955f);
        path.lineTo(655.0f, 545.625f);
        path.lineTo(965.0f, 855.835f);
        path.lineTo(935.0f, 545.445f);
        path.lineTo(565.0f, 995.595f);
        path.lineTo(595.0f, 725.85f);
        path.lineTo(715.0f, 405.705f);
        path.lineTo(10095.0f, 525.665f);
        path.lineTo(165.0f, 755.55f);
        path.lineTo(195.0f, 5.415f);
        path.lineTo(525.0f, 305.595f);
        path.lineTo(455.0f, 775.645f);
        path.lineTo(45.0f, 145.255f);
        path.lineTo(135.0f, 775.875f);
        path.lineTo(45.0f, 435.45f);
        path.lineTo(635.0f, 435.745f);
        path.lineTo(175.0f, 95.695f);
        path.lineTo(935.0f, 165.115f);
        path.lineTo(615.0f, 345.435f);
        path.lineTo(115.0f, 495.615f);
        path.lineTo(835.0f, 125.65f);
        path.lineTo(255.0f, 525.175f);
        path.lineTo(615.0f, 835.745f);
        path.lineTo(635.0f, 745.55f);
        path.lineTo(505.0f, 665.405f);
        path.lineTo(85.0f, 825.705f);
        path.lineTo(495.0f, 515.105f);
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
