package com.smartapp.b.cluknmwhtl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes3.dex */
public class Xigtcgduqhf extends ShapeDrawable {
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

    public Xigtcgduqhf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(165.0f, 375.105f);
        path.lineTo(695.0f, 285.765f);
        path.lineTo(975.0f, 455.925f);
        path.lineTo(65.0f, 85.565f);
        path.lineTo(335.0f, 865.25f);
        path.lineTo(75.0f, 675.55f);
        path.lineTo(75.0f, 5.975f);
        path.lineTo(685.0f, 295.205f);
        path.lineTo(585.0f, 155.765f);
        path.lineTo(335.0f, 55.385f);
        path.lineTo(885.0f, 175.195f);
        path.lineTo(335.0f, 465.85f);
        path.lineTo(305.0f, 945.55f);
        path.lineTo(25.0f, 385.05f);
        path.lineTo(165.0f, 375.225f);
        path.lineTo(845.0f, 665.445f);
        path.lineTo(685.0f, 715.15f);
        path.lineTo(85.0f, 855.705f);
        path.lineTo(195.0f, 465.35f);
        path.lineTo(545.0f, 115.885f);
        path.lineTo(895.0f, 645.655f);
        path.lineTo(535.0f, 435.255f);
        path.lineTo(825.0f, 275.605f);
        path.lineTo(765.0f, 75.145f);
        path.lineTo(95.0f, 615.195f);
        path.lineTo(35.0f, 595.865f);
        path.lineTo(605.0f, 205.885f);
        path.lineTo(975.0f, 475.555f);
        path.lineTo(745.0f, 175.655f);
        path.lineTo(865.0f, 175.185f);
        path.lineTo(45.0f, 905.565f);
        path.lineTo(10185.0f, 625.295f);
        path.lineTo(85.0f, 45.205f);
        path.lineTo(105.0f, 615.775f);
        path.lineTo(745.0f, 915.105f);
        path.lineTo(545.0f, 555.815f);
        path.lineTo(10185.0f, 495.255f);
        path.lineTo(95.0f, 985.215f);
        path.lineTo(35.0f, 605.85f);
        path.lineTo(175.0f, 75.695f);
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
