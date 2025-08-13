package com.device.watch.com.device.watch.vkjjheempy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes2.dex */
public class Uoxxpkwgbxe extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1013;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Uoxxpkwgbxe() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(665.0f, 545.115f);
        path.lineTo(985.0f, 735.355f);
        path.lineTo(195.0f, 815.335f);
        path.lineTo(895.0f, 645.1014f);
        path.lineTo(725.0f, 5.55f);
        path.lineTo(175.0f, 35.715f);
        path.lineTo(465.0f, 805.95f);
        path.lineTo(755.0f, 775.645f);
        path.lineTo(675.0f, 55.235f);
        path.lineTo(695.0f, 965.345f);
        path.lineTo(975.0f, 145.615f);
        path.lineTo(425.0f, 15.515f);
        path.lineTo(855.0f, 495.605f);
        path.lineTo(875.0f, 515.65f);
        path.lineTo(815.0f, 5.375f);
        path.lineTo(685.0f, 805.565f);
        path.lineTo(695.0f, 325.305f);
        path.lineTo(515.0f, 115.145f);
        path.lineTo(10135.0f, 715.255f);
        path.lineTo(615.0f, 315.335f);
        path.lineTo(825.0f, 345.505f);
        path.lineTo(55.0f, 315.75f);
        path.lineTo(35.0f, 105.815f);
        path.lineTo(935.0f, 415.195f);
        path.lineTo(655.0f, 685.485f);
        path.lineTo(825.0f, 335.15f);
        path.lineTo(595.0f, 725.995f);
        path.lineTo(555.0f, 715.345f);
        path.lineTo(55.0f, 415.805f);
        path.lineTo(385.0f, 475.515f);
        path.lineTo(255.0f, 465.715f);
        path.lineTo(205.0f, 175.305f);
        path.lineTo(825.0f, 335.455f);
        path.lineTo(175.0f, 875.605f);
        path.lineTo(225.0f, 675.535f);
        path.lineTo(485.0f, 635.705f);
        path.lineTo(745.0f, 935.645f);
        path.lineTo(365.0f, 255.725f);
        path.lineTo(535.0f, 475.525f);
        path.lineTo(25.0f, 545.845f);
        path.lineTo(115.0f, 345.905f);
        path.lineTo(945.0f, 765.925f);
        path.lineTo(805.0f, 105.85f);
        path.lineTo(515.0f, 25.545f);
        path.lineTo(605.0f, 115.995f);
        path.lineTo(155.0f, 545.545f);
        path.lineTo(265.0f, 725.305f);
        path.lineTo(545.0f, 775.685f);
        path.lineTo(45.0f, 175.665f);
        path.lineTo(675.0f, 365.595f);
        path.lineTo(915.0f, 465.695f);
        path.lineTo(565.0f, 695.465f);
        path.lineTo(965.0f, 305.615f);
        path.lineTo(445.0f, 405.985f);
        path.lineTo(865.0f, 435.285f);
        path.lineTo(615.0f, 765.135f);
        path.lineTo(695.0f, 495.105f);
        path.lineTo(755.0f, 465.515f);
        path.lineTo(765.0f, 335.75f);
        path.lineTo(325.0f, 555.595f);
        path.lineTo(615.0f, 625.295f);
        path.lineTo(945.0f, 515.855f);
        path.lineTo(105.0f, 505.225f);
        path.lineTo(625.0f, 105.55f);
        path.lineTo(395.0f, 465.425f);
        path.lineTo(95.0f, 995.385f);
        path.lineTo(15.0f, 965.05f);
        path.lineTo(425.0f, 515.975f);
        path.lineTo(145.0f, 195.275f);
        path.lineTo(825.0f, 945.615f);
        path.lineTo(235.0f, 615.945f);
        path.lineTo(225.0f, 425.165f);
        path.lineTo(715.0f, 715.115f);
        path.lineTo(345.0f, 455.295f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1013.0f, this.bounds.height() / 1013.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
