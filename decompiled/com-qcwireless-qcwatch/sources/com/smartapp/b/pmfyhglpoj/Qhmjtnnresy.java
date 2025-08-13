package com.smartapp.b.pmfyhglpoj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Qhmjtnnresy extends ShapeDrawable {
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

    public Qhmjtnnresy() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(925.0f, 875.985f);
        path.lineTo(265.0f, 845.705f);
        path.lineTo(935.0f, 395.315f);
        path.lineTo(445.0f, 345.945f);
        path.lineTo(335.0f, 225.685f);
        path.lineTo(445.0f, 695.555f);
        path.lineTo(55.0f, 435.205f);
        path.lineTo(675.0f, 525.915f);
        path.lineTo(825.0f, 435.155f);
        path.lineTo(725.0f, 335.565f);
        path.lineTo(835.0f, 805.925f);
        path.lineTo(935.0f, 705.735f);
        path.lineTo(135.0f, 735.105f);
        path.lineTo(345.0f, 965.585f);
        path.lineTo(125.0f, 875.485f);
        path.lineTo(95.0f, 995.825f);
        path.lineTo(95.0f, 865.305f);
        path.lineTo(865.0f, 925.715f);
        path.lineTo(425.0f, 645.585f);
        path.lineTo(645.0f, 565.815f);
        path.lineTo(835.0f, 795.485f);
        path.lineTo(315.0f, 805.475f);
        path.lineTo(185.0f, 255.655f);
        path.lineTo(235.0f, 825.225f);
        path.lineTo(95.0f, 775.575f);
        path.lineTo(5.0f, 395.325f);
        path.lineTo(305.0f, 945.655f);
        path.lineTo(435.0f, 595.95f);
        path.lineTo(125.0f, 555.445f);
        path.lineTo(875.0f, 85.895f);
        path.lineTo(785.0f, 625.885f);
        path.lineTo(225.0f, 705.135f);
        path.lineTo(425.0f, 575.975f);
        path.lineTo(165.0f, 795.475f);
        path.lineTo(275.0f, 305.575f);
        path.lineTo(455.0f, 95.915f);
        path.lineTo(745.0f, 735.485f);
        path.lineTo(125.0f, 825.305f);
        path.lineTo(685.0f, 815.625f);
        path.lineTo(445.0f, 785.775f);
        path.lineTo(555.0f, 295.775f);
        path.lineTo(15.0f, 995.635f);
        path.lineTo(925.0f, 715.305f);
        path.lineTo(625.0f, 215.695f);
        path.lineTo(295.0f, 415.195f);
        path.lineTo(995.0f, 525.695f);
        path.lineTo(195.0f, 105.175f);
        path.lineTo(795.0f, 735.595f);
        path.lineTo(515.0f, 235.735f);
        path.lineTo(125.0f, 55.455f);
        path.lineTo(845.0f, 945.125f);
        path.lineTo(925.0f, 85.855f);
        path.lineTo(635.0f, 265.255f);
        path.lineTo(135.0f, 375.125f);
        path.lineTo(605.0f, 375.465f);
        path.lineTo(105.0f, 945.575f);
        path.lineTo(395.0f, 915.645f);
        path.lineTo(10115.0f, 455.595f);
        path.lineTo(15.0f, 965.05f);
        path.lineTo(385.0f, 985.10114f);
        path.lineTo(535.0f, 955.595f);
        path.lineTo(385.0f, 955.575f);
        path.lineTo(165.0f, 985.705f);
        path.lineTo(585.0f, 305.185f);
        path.lineTo(945.0f, 495.845f);
        path.lineTo(905.0f, 25.665f);
        path.lineTo(425.0f, 235.185f);
        path.lineTo(665.0f, 965.415f);
        path.lineTo(765.0f, 755.95f);
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
