package com.smartapp.b.iazfvxvpaj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Zfreyypvnvl extends ShapeDrawable {
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

    public Zfreyypvnvl() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(745.0f, 595.645f);
        path.lineTo(825.0f, 385.435f);
        path.lineTo(785.0f, 315.475f);
        path.lineTo(465.0f, 115.15f);
        path.lineTo(905.0f, 795.85f);
        path.lineTo(595.0f, 625.805f);
        path.lineTo(815.0f, 375.715f);
        path.lineTo(955.0f, 985.805f);
        path.lineTo(85.0f, 645.135f);
        path.lineTo(795.0f, 915.445f);
        path.lineTo(385.0f, 765.445f);
        path.lineTo(985.0f, 855.125f);
        path.lineTo(25.0f, 865.295f);
        path.lineTo(275.0f, 935.725f);
        path.lineTo(595.0f, 365.635f);
        path.lineTo(385.0f, 665.825f);
        path.lineTo(265.0f, 145.345f);
        path.lineTo(835.0f, 465.75f);
        path.lineTo(5.0f, 935.85f);
        path.lineTo(235.0f, 215.755f);
        path.lineTo(465.0f, 35.35f);
        path.lineTo(705.0f, 125.395f);
        path.lineTo(5.0f, 195.485f);
        path.lineTo(125.0f, 345.615f);
        path.lineTo(865.0f, 965.965f);
        path.lineTo(755.0f, 945.25f);
        path.lineTo(645.0f, 845.985f);
        path.lineTo(635.0f, 995.985f);
        path.lineTo(535.0f, 255.975f);
        path.lineTo(745.0f, 65.465f);
        path.lineTo(535.0f, 275.315f);
        path.lineTo(495.0f, 215.815f);
        path.lineTo(375.0f, 815.525f);
        path.lineTo(15.0f, 215.315f);
        path.lineTo(775.0f, 185.65f);
        path.lineTo(355.0f, 925.595f);
        path.lineTo(905.0f, 945.595f);
        path.lineTo(175.0f, 665.645f);
        path.lineTo(235.0f, 845.135f);
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
