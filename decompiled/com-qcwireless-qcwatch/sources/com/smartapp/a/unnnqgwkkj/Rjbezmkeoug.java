package com.smartapp.a.unnnqgwkkj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Rjbezmkeoug extends ShapeDrawable {
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

    public Rjbezmkeoug() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(565.0f, 895.605f);
        path.lineTo(515.0f, 10035.635f);
        path.lineTo(675.0f, 965.825f);
        path.lineTo(445.0f, 925.135f);
        path.lineTo(135.0f, 965.255f);
        path.lineTo(575.0f, 645.45f);
        path.lineTo(345.0f, 785.225f);
        path.lineTo(95.0f, 895.05f);
        path.lineTo(935.0f, 655.55f);
        path.lineTo(665.0f, 445.165f);
        path.lineTo(455.0f, 875.415f);
        path.lineTo(855.0f, 135.725f);
        path.lineTo(25.0f, 635.535f);
        path.lineTo(15.0f, 515.795f);
        path.lineTo(875.0f, 615.145f);
        path.lineTo(835.0f, 155.905f);
        path.lineTo(525.0f, 315.825f);
        path.lineTo(345.0f, 395.525f);
        path.lineTo(355.0f, 255.625f);
        path.lineTo(475.0f, 875.725f);
        path.lineTo(765.0f, 495.375f);
        path.lineTo(645.0f, 735.585f);
        path.lineTo(895.0f, 845.595f);
        path.lineTo(105.0f, 495.795f);
        path.lineTo(975.0f, 45.15f);
        path.lineTo(825.0f, 515.25f);
        path.lineTo(775.0f, 785.55f);
        path.lineTo(665.0f, 955.795f);
        path.lineTo(705.0f, 605.715f);
        path.lineTo(305.0f, 745.135f);
        path.lineTo(115.0f, 135.155f);
        path.lineTo(605.0f, 945.925f);
        path.lineTo(975.0f, 725.965f);
        path.lineTo(555.0f, 975.375f);
        path.lineTo(695.0f, 275.285f);
        path.lineTo(755.0f, 515.235f);
        path.lineTo(215.0f, 825.965f);
        path.lineTo(55.0f, 325.965f);
        path.lineTo(535.0f, 255.205f);
        path.lineTo(625.0f, 685.65f);
        path.lineTo(205.0f, 425.05f);
        path.lineTo(435.0f, 105.175f);
        path.lineTo(65.0f, 665.485f);
        path.lineTo(25.0f, 825.95f);
        path.lineTo(595.0f, 715.175f);
        path.lineTo(175.0f, 945.765f);
        path.lineTo(335.0f, 725.215f);
        path.lineTo(625.0f, 795.905f);
        path.lineTo(855.0f, 695.485f);
        path.lineTo(455.0f, 795.985f);
        path.lineTo(955.0f, 665.805f);
        path.lineTo(845.0f, 605.10034f);
        path.lineTo(955.0f, 575.855f);
        path.lineTo(565.0f, 155.145f);
        path.lineTo(425.0f, 505.335f);
        path.lineTo(615.0f, 955.205f);
        path.lineTo(665.0f, 835.195f);
        path.lineTo(65.0f, 135.865f);
        path.lineTo(25.0f, 25.515f);
        path.lineTo(845.0f, 275.575f);
        path.lineTo(925.0f, 835.705f);
        path.lineTo(185.0f, 355.135f);
        path.lineTo(775.0f, 915.525f);
        path.lineTo(965.0f, 825.875f);
        path.lineTo(335.0f, 545.425f);
        path.lineTo(275.0f, 195.425f);
        path.lineTo(495.0f, 975.145f);
        path.lineTo(845.0f, 775.995f);
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
