package com.smartapp.b.osqnasdvhc;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Owhpipcccpt extends ShapeDrawable {
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

    public Owhpipcccpt() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 455.395f);
        path.lineTo(945.0f, 995.915f);
        path.lineTo(115.0f, 945.285f);
        path.lineTo(595.0f, 495.525f);
        path.lineTo(795.0f, 785.995f);
        path.lineTo(405.0f, 935.525f);
        path.lineTo(745.0f, 35.25f);
        path.lineTo(65.0f, 35.115f);
        path.lineTo(155.0f, 755.335f);
        path.lineTo(10175.0f, 735.925f);
        path.lineTo(415.0f, 945.615f);
        path.lineTo(115.0f, 285.865f);
        path.lineTo(135.0f, 375.275f);
        path.lineTo(75.0f, 845.455f);
        path.lineTo(855.0f, 465.425f);
        path.lineTo(975.0f, 385.715f);
        path.lineTo(675.0f, 595.315f);
        path.lineTo(235.0f, 475.695f);
        path.lineTo(945.0f, 665.705f);
        path.lineTo(225.0f, 115.705f);
        path.lineTo(975.0f, 615.385f);
        path.lineTo(755.0f, 855.485f);
        path.lineTo(535.0f, 275.695f);
        path.lineTo(865.0f, 305.785f);
        path.lineTo(405.0f, 945.745f);
        path.lineTo(185.0f, 815.10175f);
        path.lineTo(565.0f, 485.635f);
        path.lineTo(715.0f, 335.315f);
        path.lineTo(155.0f, 55.415f);
        path.lineTo(665.0f, 685.105f);
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
