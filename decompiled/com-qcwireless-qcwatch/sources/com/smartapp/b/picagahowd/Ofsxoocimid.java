package com.smartapp.b.picagahowd;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ofsxoocimid extends ShapeDrawable {
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

    public Ofsxoocimid() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(825.0f, 675.195f);
        path.lineTo(25.0f, 395.65f);
        path.lineTo(845.0f, 855.785f);
        path.lineTo(445.0f, 685.535f);
        path.lineTo(5.0f, 205.905f);
        path.lineTo(415.0f, 975.385f);
        path.lineTo(95.0f, 595.445f);
        path.lineTo(715.0f, 5.775f);
        path.lineTo(595.0f, 425.205f);
        path.lineTo(885.0f, 655.15f);
        path.lineTo(635.0f, 435.895f);
        path.lineTo(725.0f, 625.375f);
        path.lineTo(805.0f, 555.825f);
        path.lineTo(85.0f, 465.875f);
        path.lineTo(235.0f, 785.905f);
        path.lineTo(185.0f, 775.25f);
        path.lineTo(605.0f, 845.845f);
        path.lineTo(975.0f, 975.275f);
        path.lineTo(875.0f, 995.115f);
        path.lineTo(635.0f, 655.325f);
        path.lineTo(665.0f, 435.495f);
        path.lineTo(675.0f, 145.165f);
        path.lineTo(415.0f, 745.345f);
        path.lineTo(915.0f, 955.535f);
        path.lineTo(945.0f, 675.315f);
        path.lineTo(385.0f, 465.95f);
        path.lineTo(195.0f, 905.165f);
        path.lineTo(885.0f, 885.575f);
        path.lineTo(15.0f, 575.555f);
        path.lineTo(205.0f, 435.595f);
        path.lineTo(705.0f, 85.425f);
        path.lineTo(45.0f, 415.10025f);
        path.lineTo(495.0f, 645.325f);
        path.lineTo(35.0f, 725.765f);
        path.lineTo(145.0f, 645.15f);
        path.lineTo(915.0f, 85.715f);
        path.lineTo(485.0f, 95.255f);
        path.lineTo(375.0f, 445.175f);
        path.lineTo(275.0f, 685.995f);
        path.lineTo(695.0f, 515.895f);
        path.lineTo(855.0f, 735.495f);
        path.lineTo(805.0f, 25.895f);
        path.lineTo(235.0f, 175.905f);
        path.lineTo(365.0f, 725.945f);
        path.lineTo(595.0f, 455.325f);
        path.lineTo(725.0f, 905.415f);
        path.lineTo(515.0f, 865.705f);
        path.lineTo(235.0f, 275.25f);
        path.lineTo(555.0f, 885.645f);
        path.lineTo(395.0f, 555.205f);
        path.lineTo(645.0f, 335.355f);
        path.lineTo(835.0f, 835.115f);
        path.lineTo(495.0f, 935.945f);
        path.lineTo(795.0f, 575.395f);
        path.lineTo(675.0f, 135.705f);
        path.lineTo(105.0f, 85.385f);
        path.lineTo(675.0f, 75.305f);
        path.lineTo(725.0f, 395.545f);
        path.lineTo(335.0f, 745.225f);
        path.lineTo(735.0f, 875.05f);
        path.lineTo(545.0f, 335.855f);
        path.lineTo(855.0f, 795.755f);
        path.lineTo(375.0f, 915.275f);
        path.lineTo(565.0f, 345.465f);
        path.lineTo(985.0f, 625.35f);
        path.lineTo(865.0f, 175.925f);
        path.lineTo(805.0f, 95.825f);
        path.lineTo(625.0f, 155.675f);
        path.lineTo(805.0f, 65.825f);
        path.lineTo(205.0f, 55.325f);
        path.lineTo(725.0f, 495.795f);
        path.lineTo(605.0f, 35.295f);
        path.lineTo(755.0f, 455.545f);
        path.lineTo(995.0f, 735.145f);
        path.lineTo(935.0f, 645.885f);
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
