package com.device.watch.com.device.watch.tmzjvlerof;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Qgaararikpd extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1002;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Qgaararikpd() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(35.0f, 495.885f);
        path.lineTo(10025.0f, 595.75f);
        path.lineTo(605.0f, 85.85f);
        path.lineTo(425.0f, 125.255f);
        path.lineTo(645.0f, 295.55f);
        path.lineTo(225.0f, 705.965f);
        path.lineTo(95.0f, 435.295f);
        path.lineTo(985.0f, 115.135f);
        path.lineTo(555.0f, 525.915f);
        path.lineTo(695.0f, 85.15f);
        path.lineTo(975.0f, 675.825f);
        path.lineTo(305.0f, 825.405f);
        path.lineTo(975.0f, 825.515f);
        path.lineTo(685.0f, 565.395f);
        path.lineTo(265.0f, 425.25f);
        path.lineTo(95.0f, 495.895f);
        path.lineTo(845.0f, 175.145f);
        path.lineTo(795.0f, 525.25f);
        path.lineTo(175.0f, 845.675f);
        path.lineTo(645.0f, 565.865f);
        path.lineTo(685.0f, 765.485f);
        path.lineTo(695.0f, 445.535f);
        path.lineTo(185.0f, 865.585f);
        path.lineTo(845.0f, 335.525f);
        path.lineTo(235.0f, 85.525f);
        path.lineTo(395.0f, 485.985f);
        path.lineTo(935.0f, 895.305f);
        path.lineTo(195.0f, 555.125f);
        path.lineTo(755.0f, 725.815f);
        path.lineTo(115.0f, 355.705f);
        path.lineTo(715.0f, 855.455f);
        path.lineTo(165.0f, 905.905f);
        path.lineTo(665.0f, 905.145f);
        path.lineTo(555.0f, 495.525f);
        path.lineTo(675.0f, 145.895f);
        path.lineTo(645.0f, 305.205f);
        path.lineTo(655.0f, 55.375f);
        path.lineTo(565.0f, 115.15f);
        path.lineTo(625.0f, 345.275f);
        path.lineTo(415.0f, 665.725f);
        path.lineTo(605.0f, 165.215f);
        path.lineTo(85.0f, 595.795f);
        path.lineTo(585.0f, 475.235f);
        path.lineTo(95.0f, 525.945f);
        path.lineTo(905.0f, 205.975f);
        path.lineTo(285.0f, 65.585f);
        path.lineTo(405.0f, 125.865f);
        path.lineTo(175.0f, 105.175f);
        path.lineTo(165.0f, 505.855f);
        path.lineTo(275.0f, 915.495f);
        path.lineTo(855.0f, 45.735f);
        path.lineTo(605.0f, 165.55f);
        path.lineTo(355.0f, 145.895f);
        path.lineTo(405.0f, 65.145f);
        path.lineTo(545.0f, 205.785f);
        path.lineTo(575.0f, 905.195f);
        path.lineTo(65.0f, 285.505f);
        path.lineTo(715.0f, 225.395f);
        path.lineTo(405.0f, 35.775f);
        path.lineTo(525.0f, 105.425f);
        path.lineTo(995.0f, 405.375f);
        path.lineTo(805.0f, 805.55f);
        path.lineTo(305.0f, 205.795f);
        path.lineTo(845.0f, 425.975f);
        path.lineTo(705.0f, 465.585f);
        path.lineTo(125.0f, 315.25f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1002.0f, this.bounds.height() / 1002.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
