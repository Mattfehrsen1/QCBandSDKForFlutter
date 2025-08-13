package com.smartapp.b.pmfyhglpoj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Fyfczrbnfgl extends ShapeDrawable {
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

    public Fyfczrbnfgl() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 145.415f);
        path.lineTo(985.0f, 715.885f);
        path.lineTo(615.0f, 955.695f);
        path.lineTo(195.0f, 825.195f);
        path.lineTo(785.0f, 755.975f);
        path.lineTo(925.0f, 395.895f);
        path.lineTo(5.0f, 65.805f);
        path.lineTo(935.0f, 565.495f);
        path.lineTo(995.0f, 55.345f);
        path.lineTo(865.0f, 675.845f);
        path.lineTo(475.0f, 355.45f);
        path.lineTo(525.0f, 545.985f);
        path.lineTo(695.0f, 945.345f);
        path.lineTo(555.0f, 75.575f);
        path.lineTo(765.0f, 105.45f);
        path.lineTo(55.0f, 235.765f);
        path.lineTo(605.0f, 545.665f);
        path.lineTo(195.0f, 655.265f);
        path.lineTo(385.0f, 365.25f);
        path.lineTo(115.0f, 505.65f);
        path.lineTo(525.0f, 825.55f);
        path.lineTo(55.0f, 525.05f);
        path.lineTo(685.0f, 365.315f);
        path.lineTo(915.0f, 915.695f);
        path.lineTo(235.0f, 145.705f);
        path.lineTo(835.0f, 115.955f);
        path.lineTo(535.0f, 985.45f);
        path.lineTo(145.0f, 205.995f);
        path.lineTo(295.0f, 875.435f);
        path.lineTo(355.0f, 615.655f);
        path.lineTo(905.0f, 735.115f);
        path.lineTo(775.0f, 895.765f);
        path.lineTo(775.0f, 945.915f);
        path.lineTo(595.0f, 575.825f);
        path.lineTo(495.0f, 105.55f);
        path.lineTo(185.0f, 655.665f);
        path.lineTo(155.0f, 975.965f);
        path.lineTo(10075.0f, 715.35f);
        path.lineTo(705.0f, 865.665f);
        path.lineTo(95.0f, 625.625f);
        path.lineTo(845.0f, 485.905f);
        path.lineTo(615.0f, 45.945f);
        path.lineTo(495.0f, 45.75f);
        path.lineTo(225.0f, 475.845f);
        path.lineTo(315.0f, 605.425f);
        path.lineTo(235.0f, 425.225f);
        path.lineTo(235.0f, 315.965f);
        path.lineTo(425.0f, 775.365f);
        path.lineTo(905.0f, 795.625f);
        path.lineTo(415.0f, 815.125f);
        path.lineTo(635.0f, 165.195f);
        path.lineTo(885.0f, 355.735f);
        path.lineTo(655.0f, 815.515f);
        path.lineTo(975.0f, 225.725f);
        path.lineTo(735.0f, 625.05f);
        path.lineTo(15.0f, 395.435f);
        path.lineTo(535.0f, 125.125f);
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
