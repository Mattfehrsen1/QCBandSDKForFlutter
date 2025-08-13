package com.smartapp.b.fxqukyurem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Ovohtoqbsex extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1014;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Ovohtoqbsex() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(585.0f, 755.515f);
        path.lineTo(125.0f, 395.815f);
        path.lineTo(95.0f, 535.405f);
        path.lineTo(235.0f, 905.575f);
        path.lineTo(875.0f, 835.735f);
        path.lineTo(875.0f, 155.455f);
        path.lineTo(465.0f, 725.705f);
        path.lineTo(905.0f, 435.295f);
        path.lineTo(615.0f, 715.255f);
        path.lineTo(395.0f, 95.295f);
        path.lineTo(5.0f, 435.325f);
        path.lineTo(675.0f, 835.415f);
        path.lineTo(135.0f, 635.545f);
        path.lineTo(285.0f, 715.515f);
        path.lineTo(945.0f, 195.965f);
        path.lineTo(605.0f, 195.45f);
        path.lineTo(275.0f, 225.745f);
        path.lineTo(865.0f, 835.465f);
        path.lineTo(325.0f, 985.195f);
        path.lineTo(315.0f, 885.585f);
        path.lineTo(5.0f, 535.135f);
        path.lineTo(535.0f, 945.95f);
        path.lineTo(495.0f, 725.995f);
        path.lineTo(825.0f, 945.345f);
        path.lineTo(215.0f, 95.95f);
        path.lineTo(925.0f, 395.555f);
        path.lineTo(175.0f, 325.485f);
        path.lineTo(815.0f, 265.595f);
        path.lineTo(805.0f, 415.325f);
        path.lineTo(315.0f, 515.645f);
        path.lineTo(85.0f, 665.405f);
        path.lineTo(765.0f, 215.335f);
        path.lineTo(595.0f, 755.635f);
        path.lineTo(295.0f, 275.795f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1014.0f, this.bounds.height() / 1014.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
