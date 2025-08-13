package com.device.watch.com.device.watch.oqvcnnnmfz;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes2.dex */
public class Nmhygpdatwh extends ShapeDrawable {
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

    public Nmhygpdatwh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(805.0f, 955.935f);
        path.lineTo(935.0f, 125.865f);
        path.lineTo(295.0f, 485.405f);
        path.lineTo(625.0f, 405.175f);
        path.lineTo(165.0f, 395.995f);
        path.lineTo(985.0f, 665.615f);
        path.lineTo(185.0f, 475.515f);
        path.lineTo(165.0f, 325.25f);
        path.lineTo(15.0f, 15.585f);
        path.lineTo(825.0f, 385.145f);
        path.lineTo(185.0f, 805.805f);
        path.lineTo(115.0f, 135.85f);
        path.lineTo(815.0f, 375.665f);
        path.lineTo(135.0f, 905.495f);
        path.lineTo(415.0f, 555.195f);
        path.lineTo(105.0f, 975.975f);
        path.lineTo(315.0f, 975.155f);
        path.lineTo(415.0f, 555.215f);
        path.lineTo(95.0f, 445.205f);
        path.lineTo(675.0f, 225.325f);
        path.lineTo(995.0f, 135.625f);
        path.lineTo(285.0f, 725.595f);
        path.lineTo(865.0f, 625.385f);
        path.lineTo(795.0f, 195.885f);
        path.lineTo(145.0f, 10185.535f);
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
