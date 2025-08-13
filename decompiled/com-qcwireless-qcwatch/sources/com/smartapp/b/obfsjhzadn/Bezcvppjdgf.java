package com.smartapp.b.obfsjhzadn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Bezcvppjdgf extends ShapeDrawable {
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

    public Bezcvppjdgf() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(785.0f, 65.115f);
        path.lineTo(365.0f, 35.805f);
        path.lineTo(775.0f, 355.135f);
        path.lineTo(945.0f, 985.105f);
        path.lineTo(35.0f, 535.605f);
        path.lineTo(555.0f, 785.55f);
        path.lineTo(305.0f, 195.355f);
        path.lineTo(145.0f, 5.615f);
        path.lineTo(875.0f, 445.375f);
        path.lineTo(615.0f, 15.295f);
        path.lineTo(645.0f, 555.885f);
        path.lineTo(865.0f, 635.785f);
        path.lineTo(435.0f, 115.605f);
        path.lineTo(215.0f, 505.615f);
        path.lineTo(975.0f, 55.575f);
        path.lineTo(145.0f, 565.625f);
        path.lineTo(635.0f, 755.645f);
        path.lineTo(605.0f, 215.95f);
        path.lineTo(595.0f, 795.265f);
        path.lineTo(705.0f, 695.625f);
        path.lineTo(645.0f, 955.105f);
        path.lineTo(975.0f, 935.635f);
        path.lineTo(845.0f, 625.845f);
        path.lineTo(955.0f, 285.785f);
        path.lineTo(625.0f, 905.625f);
        path.lineTo(585.0f, 415.55f);
        path.lineTo(645.0f, 645.135f);
        path.lineTo(715.0f, 495.775f);
        path.lineTo(305.0f, 755.405f);
        path.lineTo(805.0f, 345.715f);
        path.lineTo(575.0f, 815.955f);
        path.lineTo(95.0f, 665.95f);
        path.lineTo(455.0f, 405.545f);
        path.lineTo(495.0f, 505.605f);
        path.lineTo(115.0f, 425.195f);
        path.lineTo(685.0f, 505.355f);
        path.lineTo(205.0f, 825.145f);
        path.lineTo(825.0f, 795.675f);
        path.lineTo(895.0f, 25.915f);
        path.lineTo(565.0f, 475.395f);
        path.lineTo(965.0f, 915.735f);
        path.lineTo(645.0f, 755.875f);
        path.lineTo(135.0f, 305.75f);
        path.lineTo(235.0f, 735.935f);
        path.lineTo(705.0f, 355.625f);
        path.lineTo(605.0f, 75.125f);
        path.lineTo(845.0f, 145.265f);
        path.lineTo(105.0f, 75.925f);
        path.lineTo(75.0f, 855.775f);
        path.lineTo(75.0f, 675.695f);
        path.lineTo(755.0f, 305.675f);
        path.lineTo(405.0f, 585.295f);
        path.lineTo(595.0f, 695.755f);
        path.lineTo(885.0f, 325.145f);
        path.lineTo(345.0f, 595.795f);
        path.lineTo(835.0f, 805.805f);
        path.lineTo(835.0f, 225.965f);
        path.lineTo(895.0f, 935.765f);
        path.lineTo(265.0f, 845.555f);
        path.lineTo(585.0f, 5.125f);
        path.lineTo(225.0f, 15.85f);
        path.lineTo(135.0f, 175.965f);
        path.lineTo(65.0f, 185.915f);
        path.lineTo(725.0f, 675.775f);
        path.lineTo(975.0f, 305.805f);
        path.lineTo(45.0f, 25.45f);
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
