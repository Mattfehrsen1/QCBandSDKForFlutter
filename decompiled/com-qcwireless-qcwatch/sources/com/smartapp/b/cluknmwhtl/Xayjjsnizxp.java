package com.smartapp.b.cluknmwhtl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Xayjjsnizxp extends ShapeDrawable {
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

    public Xayjjsnizxp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(755.0f, 255.915f);
        path.lineTo(515.0f, 355.405f);
        path.lineTo(585.0f, 75.915f);
        path.lineTo(665.0f, 25.575f);
        path.lineTo(175.0f, 455.05f);
        path.lineTo(845.0f, 275.265f);
        path.lineTo(595.0f, 175.965f);
        path.lineTo(835.0f, 65.715f);
        path.lineTo(585.0f, 405.05f);
        path.lineTo(965.0f, 665.475f);
        path.lineTo(815.0f, 435.05f);
        path.lineTo(815.0f, 145.815f);
        path.lineTo(295.0f, 255.825f);
        path.lineTo(515.0f, 365.325f);
        path.lineTo(595.0f, 575.685f);
        path.lineTo(115.0f, 565.555f);
        path.lineTo(845.0f, 145.695f);
        path.lineTo(815.0f, 205.55f);
        path.lineTo(375.0f, 685.845f);
        path.lineTo(775.0f, 815.445f);
        path.lineTo(875.0f, 645.375f);
        path.lineTo(975.0f, 625.425f);
        path.lineTo(605.0f, 675.695f);
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
