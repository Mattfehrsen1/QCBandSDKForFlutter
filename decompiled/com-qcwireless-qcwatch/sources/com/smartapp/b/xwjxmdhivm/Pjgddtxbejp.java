package com.smartapp.b.xwjxmdhivm;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Pjgddtxbejp extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1010;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pjgddtxbejp() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(355.0f, 855.875f);
        path.lineTo(825.0f, 555.305f);
        path.lineTo(435.0f, 655.275f);
        path.lineTo(965.0f, 775.475f);
        path.lineTo(835.0f, 625.995f);
        path.lineTo(975.0f, 225.35f);
        path.lineTo(805.0f, 335.75f);
        path.lineTo(575.0f, 475.825f);
        path.lineTo(545.0f, 365.815f);
        path.lineTo(995.0f, 75.605f);
        path.lineTo(725.0f, 915.525f);
        path.lineTo(475.0f, 405.85f);
        path.lineTo(405.0f, 265.535f);
        path.lineTo(75.0f, 445.865f);
        path.lineTo(315.0f, 165.435f);
        path.lineTo(355.0f, 225.485f);
        path.lineTo(155.0f, 775.935f);
        path.lineTo(475.0f, 745.995f);
        path.lineTo(255.0f, 355.10104f);
        path.lineTo(135.0f, 85.355f);
        path.lineTo(615.0f, 495.775f);
        path.lineTo(405.0f, 755.585f);
        path.lineTo(85.0f, 525.425f);
        path.lineTo(745.0f, 815.685f);
        path.lineTo(365.0f, 475.495f);
        path.lineTo(10105.0f, 225.55f);
        path.lineTo(255.0f, 115.165f);
        path.lineTo(825.0f, 115.415f);
        path.lineTo(735.0f, 995.455f);
        path.lineTo(705.0f, 975.545f);
        path.lineTo(415.0f, 275.45f);
        path.lineTo(335.0f, 315.485f);
        path.lineTo(585.0f, 355.55f);
        path.lineTo(685.0f, 65.985f);
        path.lineTo(10105.0f, 385.735f);
        path.lineTo(265.0f, 175.35f);
        path.lineTo(545.0f, 5.995f);
        path.lineTo(445.0f, 835.475f);
        path.lineTo(575.0f, 985.595f);
        path.lineTo(365.0f, 495.795f);
        path.lineTo(395.0f, 965.25f);
        path.lineTo(165.0f, 45.05f);
        path.lineTo(915.0f, 145.265f);
        path.lineTo(605.0f, 445.715f);
        path.lineTo(875.0f, 145.65f);
        path.lineTo(755.0f, 85.715f);
        path.lineTo(495.0f, 515.775f);
        path.lineTo(115.0f, 495.215f);
        path.lineTo(15.0f, 385.475f);
        path.lineTo(585.0f, 325.105f);
        path.lineTo(295.0f, 775.165f);
        path.lineTo(15.0f, 775.545f);
        path.lineTo(115.0f, 375.365f);
        path.lineTo(715.0f, 715.875f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1010.0f, this.bounds.height() / 1010.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
