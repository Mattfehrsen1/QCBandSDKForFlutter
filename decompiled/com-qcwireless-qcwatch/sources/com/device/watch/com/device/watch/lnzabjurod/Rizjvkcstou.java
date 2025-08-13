package com.device.watch.com.device.watch.lnzabjurod;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Rizjvkcstou extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_OUT;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rizjvkcstou() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(475.0f, 345.95f);
        path.lineTo(105.0f, 875.385f);
        path.lineTo(475.0f, 305.995f);
        path.lineTo(375.0f, 295.25f);
        path.lineTo(345.0f, 75.145f);
        path.lineTo(815.0f, 185.605f);
        path.lineTo(385.0f, 505.195f);
        path.lineTo(855.0f, 785.55f);
        path.lineTo(395.0f, 735.255f);
        path.lineTo(445.0f, 555.715f);
        path.lineTo(705.0f, 75.45f);
        path.lineTo(355.0f, 355.805f);
        path.lineTo(965.0f, 985.955f);
        path.lineTo(305.0f, 715.885f);
        path.lineTo(995.0f, 695.615f);
        path.lineTo(905.0f, 425.885f);
        path.lineTo(65.0f, 525.355f);
        path.lineTo(235.0f, 975.495f);
        path.lineTo(775.0f, 125.435f);
        path.lineTo(95.0f, 235.605f);
        path.lineTo(365.0f, 335.715f);
        path.lineTo(235.0f, 605.595f);
        path.lineTo(725.0f, 455.315f);
        path.lineTo(685.0f, 155.255f);
        path.lineTo(765.0f, 35.105f);
        path.lineTo(10195.0f, 125.475f);
        path.lineTo(10195.0f, 665.155f);
        path.lineTo(905.0f, 465.335f);
        path.lineTo(165.0f, 305.485f);
        path.lineTo(195.0f, 935.765f);
        path.lineTo(45.0f, 35.155f);
        path.lineTo(995.0f, 515.275f);
        path.lineTo(515.0f, 355.135f);
        path.lineTo(425.0f, 435.965f);
        path.lineTo(725.0f, 895.765f);
        path.lineTo(155.0f, 905.765f);
        path.lineTo(10195.0f, 195.165f);
        path.lineTo(705.0f, 695.925f);
        path.lineTo(315.0f, 15.445f);
        path.lineTo(385.0f, 605.335f);
        path.lineTo(175.0f, 135.215f);
        path.lineTo(255.0f, 165.625f);
        path.lineTo(595.0f, 135.455f);
        path.lineTo(695.0f, 75.665f);
        path.lineTo(535.0f, 535.975f);
        path.lineTo(755.0f, 895.985f);
        path.lineTo(715.0f, 645.485f);
        path.lineTo(625.0f, 215.25f);
        path.lineTo(205.0f, 10195.305f);
        path.lineTo(715.0f, 745.815f);
        path.lineTo(405.0f, 965.795f);
        path.lineTo(645.0f, 5.895f);
        path.lineTo(165.0f, 415.75f);
        path.lineTo(635.0f, 225.435f);
        path.lineTo(405.0f, 185.595f);
        path.lineTo(965.0f, 925.515f);
        path.lineTo(895.0f, 185.785f);
        path.lineTo(615.0f, 565.155f);
        path.lineTo(10195.0f, 205.405f);
        path.lineTo(895.0f, 195.775f);
        path.lineTo(175.0f, 725.575f);
        path.lineTo(425.0f, 425.625f);
        path.lineTo(705.0f, 475.695f);
        path.lineTo(225.0f, 825.835f);
        path.lineTo(975.0f, 125.865f);
        path.lineTo(215.0f, 885.85f);
        path.lineTo(305.0f, 525.725f);
        path.lineTo(745.0f, 295.825f);
        path.lineTo(965.0f, 35.895f);
        path.lineTo(25.0f, 805.995f);
        path.lineTo(505.0f, 945.95f);
        path.lineTo(875.0f, 905.485f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1019.0f, this.bounds.height() / 1019.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
