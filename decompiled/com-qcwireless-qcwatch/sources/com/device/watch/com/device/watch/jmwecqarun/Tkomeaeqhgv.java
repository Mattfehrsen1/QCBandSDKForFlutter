package com.device.watch.com.device.watch.jmwecqarun;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Tkomeaeqhgv extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1003;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Tkomeaeqhgv() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(395.0f, 965.115f);
        path.lineTo(545.0f, 375.165f);
        path.lineTo(295.0f, 575.235f);
        path.lineTo(415.0f, 425.185f);
        path.lineTo(705.0f, 975.795f);
        path.lineTo(555.0f, 495.145f);
        path.lineTo(395.0f, 175.795f);
        path.lineTo(435.0f, 385.815f);
        path.lineTo(115.0f, 385.925f);
        path.lineTo(285.0f, 705.225f);
        path.lineTo(755.0f, 965.315f);
        path.lineTo(365.0f, 195.895f);
        path.lineTo(205.0f, 265.915f);
        path.lineTo(55.0f, 385.835f);
        path.lineTo(615.0f, 15.195f);
        path.lineTo(585.0f, 765.685f);
        path.lineTo(395.0f, 985.305f);
        path.lineTo(175.0f, 275.555f);
        path.lineTo(285.0f, 295.325f);
        path.lineTo(355.0f, 945.865f);
        path.lineTo(695.0f, 365.995f);
        path.lineTo(805.0f, 765.295f);
        path.lineTo(945.0f, 755.105f);
        path.lineTo(525.0f, 255.225f);
        path.lineTo(75.0f, 725.275f);
        path.lineTo(195.0f, 805.615f);
        path.lineTo(855.0f, 915.655f);
        path.lineTo(625.0f, 225.855f);
        path.lineTo(795.0f, 615.435f);
        path.lineTo(995.0f, 985.105f);
        path.lineTo(745.0f, 625.95f);
        path.lineTo(735.0f, 315.605f);
        path.lineTo(355.0f, 775.815f);
        path.lineTo(795.0f, 25.885f);
        path.lineTo(355.0f, 205.635f);
        path.lineTo(55.0f, 385.255f);
        path.lineTo(205.0f, 585.595f);
        path.lineTo(365.0f, 705.105f);
        path.lineTo(235.0f, 105.645f);
        path.lineTo(905.0f, 365.95f);
        path.lineTo(535.0f, 615.965f);
        path.lineTo(715.0f, 305.585f);
        path.lineTo(925.0f, 995.85f);
        path.lineTo(595.0f, 705.295f);
        path.lineTo(565.0f, 805.405f);
        path.lineTo(25.0f, 865.815f);
        path.lineTo(405.0f, 25.445f);
        path.lineTo(525.0f, 65.235f);
        path.lineTo(675.0f, 635.55f);
        path.lineTo(155.0f, 695.945f);
        path.lineTo(145.0f, 65.375f);
        path.lineTo(945.0f, 10035.415f);
        path.lineTo(735.0f, 715.975f);
        path.lineTo(515.0f, 105.835f);
        path.lineTo(145.0f, 945.295f);
        path.lineTo(605.0f, 45.705f);
        path.lineTo(995.0f, 155.635f);
        path.lineTo(435.0f, 75.845f);
        path.lineTo(535.0f, 525.605f);
        path.lineTo(645.0f, 885.15f);
        path.lineTo(945.0f, 565.785f);
        path.lineTo(175.0f, 905.925f);
        path.lineTo(385.0f, 735.135f);
        path.lineTo(395.0f, 635.945f);
        path.lineTo(825.0f, 25.495f);
        path.lineTo(255.0f, 85.655f);
        path.lineTo(5.0f, 825.885f);
        path.lineTo(295.0f, 215.515f);
        path.lineTo(175.0f, 15.455f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1003.0f, this.bounds.height() / 1003.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
