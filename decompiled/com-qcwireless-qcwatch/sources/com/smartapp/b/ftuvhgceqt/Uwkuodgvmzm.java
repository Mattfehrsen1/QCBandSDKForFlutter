package com.smartapp.b.ftuvhgceqt;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Uwkuodgvmzm extends ShapeDrawable {
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

    public Uwkuodgvmzm() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(815.0f, 565.385f);
        path.lineTo(395.0f, 805.925f);
        path.lineTo(935.0f, 945.35f);
        path.lineTo(105.0f, 675.155f);
        path.lineTo(145.0f, 425.545f);
        path.lineTo(885.0f, 505.85f);
        path.lineTo(665.0f, 105.835f);
        path.lineTo(195.0f, 575.185f);
        path.lineTo(5.0f, 65.335f);
        path.lineTo(145.0f, 155.995f);
        path.lineTo(765.0f, 655.845f);
        path.lineTo(85.0f, 385.355f);
        path.lineTo(675.0f, 195.515f);
        path.lineTo(585.0f, 605.465f);
        path.lineTo(85.0f, 195.305f);
        path.lineTo(235.0f, 115.615f);
        path.lineTo(535.0f, 175.955f);
        path.lineTo(175.0f, 515.665f);
        path.lineTo(785.0f, 875.715f);
        path.lineTo(575.0f, 865.525f);
        path.lineTo(725.0f, 805.315f);
        path.lineTo(455.0f, 725.995f);
        path.lineTo(125.0f, 435.965f);
        path.lineTo(515.0f, 945.305f);
        path.lineTo(135.0f, 225.465f);
        path.lineTo(975.0f, 295.405f);
        path.lineTo(665.0f, 995.685f);
        path.lineTo(595.0f, 385.805f);
        path.lineTo(915.0f, 725.455f);
        path.lineTo(695.0f, 985.155f);
        path.lineTo(515.0f, 275.345f);
        path.lineTo(455.0f, 875.535f);
        path.lineTo(705.0f, 975.25f);
        path.lineTo(615.0f, 195.605f);
        path.lineTo(435.0f, 535.485f);
        path.lineTo(105.0f, 465.535f);
        path.lineTo(285.0f, 825.405f);
        path.lineTo(895.0f, 265.825f);
        path.lineTo(925.0f, 865.585f);
        path.lineTo(95.0f, 725.25f);
        path.lineTo(625.0f, 825.795f);
        path.lineTo(265.0f, 265.225f);
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
