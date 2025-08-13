package com.smartapp.b.blzrgrokny;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Uugxvueiica extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1011;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Uugxvueiica() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(255.0f, 595.285f);
        path.lineTo(305.0f, 605.625f);
        path.lineTo(995.0f, 665.595f);
        path.lineTo(275.0f, 215.615f);
        path.lineTo(75.0f, 215.375f);
        path.lineTo(965.0f, 335.225f);
        path.lineTo(75.0f, 315.895f);
        path.lineTo(65.0f, 755.75f);
        path.lineTo(705.0f, 565.95f);
        path.lineTo(495.0f, 985.415f);
        path.lineTo(895.0f, 935.785f);
        path.lineTo(835.0f, 995.185f);
        path.lineTo(315.0f, 235.515f);
        path.lineTo(855.0f, 765.285f);
        path.lineTo(645.0f, 975.325f);
        path.lineTo(235.0f, 325.545f);
        path.lineTo(795.0f, 45.25f);
        path.lineTo(675.0f, 935.755f);
        path.lineTo(115.0f, 425.355f);
        path.lineTo(285.0f, 95.945f);
        path.lineTo(585.0f, 285.785f);
        path.lineTo(285.0f, 745.405f);
        path.lineTo(975.0f, 75.255f);
        path.lineTo(775.0f, 845.865f);
        path.lineTo(255.0f, 675.235f);
        path.lineTo(285.0f, 455.845f);
        path.lineTo(765.0f, 355.675f);
        path.lineTo(5.0f, 95.905f);
        path.lineTo(255.0f, 225.465f);
        path.lineTo(55.0f, 385.65f);
        path.lineTo(195.0f, 645.335f);
        path.lineTo(875.0f, 995.485f);
        path.lineTo(675.0f, 215.65f);
        path.lineTo(735.0f, 265.955f);
        path.lineTo(25.0f, 335.805f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1011.0f, this.bounds.height() / 1011.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
