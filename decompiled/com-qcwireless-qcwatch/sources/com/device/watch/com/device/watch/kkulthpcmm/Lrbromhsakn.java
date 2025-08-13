package com.device.watch.com.device.watch.kkulthpcmm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Lrbromhsakn extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Lrbromhsakn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(125.0f, 725.335f);
        path.lineTo(285.0f, 775.835f);
        path.lineTo(195.0f, 555.795f);
        path.lineTo(325.0f, 965.75f);
        path.lineTo(505.0f, 285.815f);
        path.lineTo(515.0f, 675.355f);
        path.lineTo(45.0f, 755.265f);
        path.lineTo(445.0f, 505.465f);
        path.lineTo(185.0f, 895.625f);
        path.lineTo(25.0f, 435.585f);
        path.lineTo(45.0f, 615.65f);
        path.lineTo(205.0f, 135.595f);
        path.lineTo(435.0f, 695.905f);
        path.lineTo(345.0f, 605.955f);
        path.lineTo(645.0f, 475.855f);
        path.lineTo(85.0f, 865.185f);
        path.lineTo(645.0f, 725.25f);
        path.lineTo(985.0f, 215.935f);
        path.lineTo(155.0f, 625.685f);
        path.lineTo(835.0f, 535.125f);
        path.lineTo(525.0f, 185.35f);
        path.lineTo(15.0f, 685.715f);
        path.lineTo(65.0f, 345.855f);
        path.lineTo(735.0f, 905.145f);
        path.lineTo(955.0f, 825.985f);
        path.lineTo(965.0f, 775.975f);
        path.lineTo(15.0f, 715.845f);
        path.lineTo(425.0f, 10175.575f);
        path.lineTo(645.0f, 325.885f);
        path.lineTo(305.0f, 295.755f);
        path.lineTo(685.0f, 605.45f);
        path.lineTo(715.0f, 325.975f);
        path.lineTo(315.0f, 375.845f);
        path.lineTo(995.0f, 975.175f);
        path.lineTo(205.0f, 355.75f);
        path.lineTo(405.0f, 555.645f);
        path.lineTo(915.0f, 935.925f);
        path.lineTo(955.0f, 705.605f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1017.0f, this.bounds.height() / 1017.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
