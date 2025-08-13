package com.smartapp.b.kyiupxpigi;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ysnsbuziznr extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ysnsbuziznr() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(835.0f, 705.475f);
        path.lineTo(115.0f, 865.395f);
        path.lineTo(915.0f, 195.975f);
        path.lineTo(515.0f, 995.295f);
        path.lineTo(815.0f, 295.915f);
        path.lineTo(175.0f, 745.435f);
        path.lineTo(735.0f, 45.195f);
        path.lineTo(555.0f, 995.175f);
        path.lineTo(895.0f, 515.235f);
        path.lineTo(155.0f, 885.735f);
        path.lineTo(475.0f, 685.05f);
        path.lineTo(95.0f, 605.525f);
        path.lineTo(685.0f, 45.665f);
        path.lineTo(755.0f, 975.555f);
        path.lineTo(435.0f, 25.415f);
        path.lineTo(905.0f, 575.575f);
        path.lineTo(115.0f, 235.465f);
        path.lineTo(995.0f, 45.85f);
        path.lineTo(715.0f, 795.325f);
        path.lineTo(485.0f, 95.955f);
        path.lineTo(275.0f, 305.805f);
        path.lineTo(915.0f, 745.395f);
        path.lineTo(255.0f, 105.295f);
        path.lineTo(665.0f, 725.935f);
        path.lineTo(685.0f, 315.675f);
        path.lineTo(895.0f, 395.455f);
        path.lineTo(475.0f, 485.795f);
        path.lineTo(15.0f, 655.135f);
        path.lineTo(675.0f, 835.645f);
        path.lineTo(735.0f, 955.845f);
        path.lineTo(15.0f, 705.775f);
        path.lineTo(595.0f, 715.905f);
        path.lineTo(985.0f, 495.675f);
        path.lineTo(175.0f, 235.485f);
        path.lineTo(215.0f, 885.615f);
        path.lineTo(915.0f, 895.65f);
        path.lineTo(275.0f, 5.10105f);
        path.lineTo(695.0f, 85.935f);
        path.lineTo(265.0f, 385.15f);
        path.lineTo(815.0f, 15.275f);
        path.lineTo(125.0f, 865.15f);
        path.lineTo(65.0f, 425.705f);
        path.lineTo(765.0f, 265.645f);
        path.lineTo(275.0f, 775.415f);
        path.lineTo(665.0f, 385.925f);
        path.lineTo(775.0f, 825.935f);
        path.lineTo(725.0f, 695.795f);
        path.lineTo(735.0f, 705.815f);
        path.lineTo(565.0f, 145.935f);
        path.lineTo(915.0f, 655.85f);
        path.lineTo(10105.0f, 845.295f);
        path.lineTo(805.0f, 405.895f);
        path.lineTo(755.0f, 735.915f);
        path.lineTo(515.0f, 915.175f);
        path.lineTo(225.0f, 555.55f);
        path.lineTo(45.0f, 465.805f);
        path.lineTo(575.0f, 595.605f);
        path.lineTo(465.0f, 15.435f);
        path.lineTo(945.0f, 435.845f);
        path.lineTo(255.0f, 235.665f);
        path.lineTo(605.0f, 355.545f);
        path.lineTo(935.0f, 645.805f);
        path.lineTo(465.0f, 815.445f);
        path.lineTo(445.0f, 115.685f);
        path.lineTo(855.0f, 795.285f);
        path.lineTo(335.0f, 885.955f);
        path.lineTo(75.0f, 915.435f);
        path.lineTo(745.0f, 615.15f);
        path.lineTo(495.0f, 195.235f);
        path.lineTo(85.0f, 45.425f);
        path.lineTo(985.0f, 525.335f);
        path.lineTo(355.0f, 905.295f);
        path.lineTo(835.0f, 555.525f);
        path.lineTo(725.0f, 805.615f);
        path.lineTo(695.0f, 175.295f);
        path.lineTo(765.0f, 395.05f);
        path.lineTo(205.0f, 345.705f);
        path.lineTo(55.0f, 775.945f);
        path.lineTo(5.0f, 185.505f);
        path.lineTo(925.0f, 195.765f);
        path.lineTo(685.0f, 655.45f);
        path.lineTo(855.0f, 685.625f);
        path.lineTo(105.0f, 605.115f);
        path.lineTo(815.0f, 565.845f);
        path.lineTo(675.0f, 65.805f);
        path.lineTo(485.0f, 625.455f);
        path.lineTo(175.0f, 885.845f);
        path.lineTo(145.0f, 925.05f);
        path.lineTo(615.0f, 355.435f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
