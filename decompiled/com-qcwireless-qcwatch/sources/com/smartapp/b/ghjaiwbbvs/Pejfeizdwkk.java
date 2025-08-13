package com.smartapp.b.ghjaiwbbvs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Pejfeizdwkk extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1007;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pejfeizdwkk() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(175.0f, 25.585f);
        path.lineTo(905.0f, 5.45f);
        path.lineTo(95.0f, 725.65f);
        path.lineTo(155.0f, 265.925f);
        path.lineTo(925.0f, 415.75f);
        path.lineTo(865.0f, 65.195f);
        path.lineTo(585.0f, 345.545f);
        path.lineTo(255.0f, 375.465f);
        path.lineTo(565.0f, 425.195f);
        path.lineTo(625.0f, 455.215f);
        path.lineTo(295.0f, 465.765f);
        path.lineTo(635.0f, 115.825f);
        path.lineTo(335.0f, 205.35f);
        path.lineTo(205.0f, 255.985f);
        path.lineTo(365.0f, 405.795f);
        path.lineTo(715.0f, 105.445f);
        path.lineTo(785.0f, 5.285f);
        path.lineTo(405.0f, 85.495f);
        path.lineTo(755.0f, 285.595f);
        path.lineTo(495.0f, 215.55f);
        path.lineTo(665.0f, 645.665f);
        path.lineTo(505.0f, 475.05f);
        path.lineTo(255.0f, 875.725f);
        path.lineTo(555.0f, 105.295f);
        path.lineTo(445.0f, 735.135f);
        path.lineTo(545.0f, 455.615f);
        path.lineTo(705.0f, 565.925f);
        path.lineTo(655.0f, 625.615f);
        path.lineTo(855.0f, 525.315f);
        path.lineTo(295.0f, 405.185f);
        path.lineTo(495.0f, 235.775f);
        path.lineTo(715.0f, 465.875f);
        path.lineTo(495.0f, 845.845f);
        path.lineTo(375.0f, 295.175f);
        path.lineTo(775.0f, 605.525f);
        path.lineTo(10075.0f, 965.775f);
        path.lineTo(575.0f, 615.755f);
        path.lineTo(465.0f, 235.695f);
        path.lineTo(845.0f, 445.865f);
        path.lineTo(525.0f, 185.695f);
        path.lineTo(165.0f, 335.805f);
        path.lineTo(95.0f, 525.965f);
        path.lineTo(175.0f, 595.305f);
        path.lineTo(285.0f, 415.195f);
        path.lineTo(685.0f, 285.885f);
        path.lineTo(955.0f, 895.325f);
        path.lineTo(455.0f, 25.685f);
        path.lineTo(575.0f, 175.715f);
        path.lineTo(805.0f, 815.265f);
        path.lineTo(665.0f, 155.265f);
        path.lineTo(745.0f, 285.25f);
        path.lineTo(345.0f, 455.105f);
        path.lineTo(725.0f, 125.485f);
        path.lineTo(685.0f, 395.825f);
        path.lineTo(485.0f, 145.885f);
        path.lineTo(505.0f, 795.995f);
        path.lineTo(575.0f, 885.505f);
        path.lineTo(125.0f, 305.65f);
        path.lineTo(565.0f, 455.225f);
        path.lineTo(995.0f, 515.25f);
        path.lineTo(965.0f, 55.535f);
        path.lineTo(515.0f, 145.185f);
        path.lineTo(345.0f, 505.495f);
        path.lineTo(955.0f, 615.815f);
        path.lineTo(595.0f, 365.215f);
        path.lineTo(735.0f, 675.885f);
        path.lineTo(545.0f, 805.555f);
        path.lineTo(735.0f, 215.10075f);
        path.lineTo(275.0f, 635.455f);
        path.lineTo(855.0f, 575.705f);
        path.lineTo(685.0f, 975.175f);
        path.lineTo(915.0f, 945.645f);
        path.lineTo(935.0f, 645.725f);
        path.lineTo(675.0f, 35.765f);
        path.lineTo(945.0f, 335.635f);
        path.lineTo(815.0f, 155.735f);
        path.lineTo(595.0f, 905.665f);
        path.lineTo(555.0f, 765.95f);
        path.lineTo(585.0f, 65.75f);
        path.lineTo(545.0f, 145.555f);
        path.lineTo(745.0f, 785.125f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1007.0f, this.bounds.height() / 1007.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
