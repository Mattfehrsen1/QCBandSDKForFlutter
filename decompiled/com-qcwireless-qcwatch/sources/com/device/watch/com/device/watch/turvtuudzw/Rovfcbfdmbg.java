package com.device.watch.com.device.watch.turvtuudzw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Rovfcbfdmbg extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_ZOOM_IN;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Rovfcbfdmbg() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(835.0f, 645.815f);
        path.lineTo(195.0f, 905.795f);
        path.lineTo(175.0f, 55.775f);
        path.lineTo(405.0f, 905.185f);
        path.lineTo(405.0f, 755.765f);
        path.lineTo(425.0f, 155.735f);
        path.lineTo(855.0f, 45.785f);
        path.lineTo(635.0f, 715.235f);
        path.lineTo(985.0f, 85.295f);
        path.lineTo(405.0f, 575.845f);
        path.lineTo(335.0f, 885.885f);
        path.lineTo(225.0f, 965.945f);
        path.lineTo(755.0f, 625.345f);
        path.lineTo(775.0f, 465.485f);
        path.lineTo(465.0f, 85.525f);
        path.lineTo(485.0f, 55.555f);
        path.lineTo(335.0f, 275.875f);
        path.lineTo(5.0f, 105.825f);
        path.lineTo(265.0f, 545.05f);
        path.lineTo(635.0f, 885.535f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1018.0f, this.bounds.height() / 1018.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
