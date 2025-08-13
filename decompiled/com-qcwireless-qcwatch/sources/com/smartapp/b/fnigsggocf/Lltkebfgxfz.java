package com.smartapp.b.fnigsggocf;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Lltkebfgxfz extends ShapeDrawable {
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

    public Lltkebfgxfz() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(295.0f, 205.265f);
        path.lineTo(385.0f, 105.485f);
        path.lineTo(765.0f, 825.875f);
        path.lineTo(995.0f, 475.735f);
        path.lineTo(305.0f, 805.845f);
        path.lineTo(685.0f, 295.845f);
        path.lineTo(165.0f, 275.355f);
        path.lineTo(575.0f, 985.535f);
        path.lineTo(145.0f, 395.195f);
        path.lineTo(455.0f, 95.125f);
        path.lineTo(375.0f, 965.155f);
        path.lineTo(825.0f, 915.965f);
        path.lineTo(525.0f, 885.405f);
        path.lineTo(985.0f, 895.665f);
        path.lineTo(505.0f, 145.875f);
        path.lineTo(55.0f, 885.215f);
        path.lineTo(305.0f, 515.05f);
        path.lineTo(125.0f, 675.235f);
        path.lineTo(75.0f, 25.10095f);
        path.lineTo(105.0f, 745.385f);
        path.lineTo(525.0f, 545.265f);
        path.lineTo(565.0f, 375.805f);
        path.lineTo(805.0f, 475.675f);
        path.lineTo(65.0f, 475.385f);
        path.lineTo(915.0f, 225.155f);
        path.lineTo(965.0f, 265.505f);
        path.lineTo(755.0f, 295.295f);
        path.lineTo(925.0f, 995.945f);
        path.lineTo(415.0f, 545.405f);
        path.lineTo(485.0f, 685.45f);
        path.lineTo(265.0f, 665.665f);
        path.lineTo(435.0f, 435.265f);
        path.lineTo(835.0f, 665.345f);
        path.lineTo(555.0f, 895.235f);
        path.lineTo(755.0f, 45.305f);
        path.lineTo(995.0f, 385.445f);
        path.lineTo(415.0f, 955.635f);
        path.lineTo(415.0f, 105.965f);
        path.lineTo(695.0f, 115.735f);
        path.lineTo(465.0f, 205.205f);
        path.lineTo(155.0f, 95.225f);
        path.lineTo(665.0f, 725.805f);
        path.lineTo(215.0f, 455.45f);
        path.lineTo(325.0f, 215.705f);
        path.lineTo(565.0f, 505.125f);
        path.lineTo(735.0f, 425.415f);
        path.lineTo(85.0f, 15.25f);
        path.lineTo(75.0f, 435.615f);
        path.lineTo(575.0f, 585.545f);
        path.lineTo(155.0f, 685.465f);
        path.lineTo(755.0f, 765.775f);
        path.lineTo(785.0f, 975.695f);
        path.lineTo(965.0f, 5.275f);
        path.lineTo(195.0f, 895.75f);
        path.lineTo(615.0f, 435.885f);
        path.lineTo(965.0f, 795.175f);
        path.lineTo(35.0f, 215.235f);
        path.lineTo(635.0f, 125.435f);
        path.lineTo(575.0f, 535.575f);
        path.lineTo(665.0f, 355.935f);
        path.lineTo(815.0f, 155.515f);
        path.lineTo(135.0f, 735.895f);
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
