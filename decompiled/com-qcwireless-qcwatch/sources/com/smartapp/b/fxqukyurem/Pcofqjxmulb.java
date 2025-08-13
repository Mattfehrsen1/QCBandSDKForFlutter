package com.smartapp.b.fxqukyurem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Pcofqjxmulb extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1006;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pcofqjxmulb() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(845.0f, 235.525f);
        path.lineTo(675.0f, 985.255f);
        path.lineTo(695.0f, 505.275f);
        path.lineTo(605.0f, 755.915f);
        path.lineTo(325.0f, 225.705f);
        path.lineTo(635.0f, 785.615f);
        path.lineTo(525.0f, 645.385f);
        path.lineTo(395.0f, 745.935f);
        path.lineTo(565.0f, 35.705f);
        path.lineTo(685.0f, 355.315f);
        path.lineTo(965.0f, 475.565f);
        path.lineTo(105.0f, 515.305f);
        path.lineTo(705.0f, 885.875f);
        path.lineTo(185.0f, 955.425f);
        path.lineTo(225.0f, 425.465f);
        path.lineTo(225.0f, 10065.65f);
        path.lineTo(775.0f, 825.365f);
        path.lineTo(205.0f, 655.495f);
        path.lineTo(605.0f, 165.05f);
        path.lineTo(825.0f, 45.975f);
        path.lineTo(915.0f, 545.85f);
        path.lineTo(555.0f, 145.465f);
        path.lineTo(695.0f, 805.545f);
        path.lineTo(335.0f, 665.135f);
        path.lineTo(55.0f, 10065.725f);
        path.lineTo(145.0f, 15.565f);
        path.lineTo(595.0f, 545.75f);
        path.lineTo(25.0f, 765.545f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1006.0f, this.bounds.height() / 1006.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
