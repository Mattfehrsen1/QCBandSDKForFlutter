package com.smartapp.b.cluknmwhtl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes3.dex */
public class Pdefiathniq extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1005;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Pdefiathniq() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(95.0f, 695.185f);
        path.lineTo(255.0f, 765.755f);
        path.lineTo(785.0f, 525.445f);
        path.lineTo(65.0f, 905.255f);
        path.lineTo(585.0f, 775.765f);
        path.lineTo(975.0f, 585.145f);
        path.lineTo(315.0f, 905.45f);
        path.lineTo(565.0f, 735.535f);
        path.lineTo(785.0f, 815.735f);
        path.lineTo(315.0f, 235.745f);
        path.lineTo(45.0f, 805.785f);
        path.lineTo(675.0f, 655.475f);
        path.lineTo(425.0f, 155.535f);
        path.lineTo(695.0f, 825.815f);
        path.lineTo(675.0f, 995.325f);
        path.lineTo(955.0f, 175.25f);
        path.lineTo(795.0f, 165.175f);
        path.lineTo(75.0f, 835.855f);
        path.lineTo(125.0f, 185.675f);
        path.lineTo(255.0f, 945.325f);
        path.lineTo(775.0f, 15.75f);
        path.lineTo(305.0f, 845.75f);
        path.lineTo(525.0f, 325.715f);
        path.lineTo(235.0f, 695.445f);
        path.lineTo(105.0f, 795.315f);
        path.lineTo(195.0f, 475.05f);
        path.lineTo(115.0f, 705.845f);
        path.lineTo(475.0f, 715.715f);
        path.lineTo(10055.0f, 355.775f);
        path.lineTo(10055.0f, 35.825f);
        path.lineTo(185.0f, 10055.425f);
        path.lineTo(225.0f, 285.255f);
        path.lineTo(285.0f, 375.255f);
        path.lineTo(895.0f, 685.445f);
        path.lineTo(45.0f, 95.485f);
        path.lineTo(255.0f, 505.175f);
        path.lineTo(755.0f, 15.255f);
        path.lineTo(95.0f, 755.255f);
        path.lineTo(685.0f, 385.265f);
        path.lineTo(65.0f, 605.935f);
        path.lineTo(805.0f, 485.395f);
        path.lineTo(935.0f, 265.885f);
        path.lineTo(15.0f, 655.175f);
        path.lineTo(375.0f, 195.755f);
        path.lineTo(605.0f, 15.725f);
        path.lineTo(85.0f, 365.215f);
        path.lineTo(785.0f, 655.725f);
        path.lineTo(585.0f, 575.715f);
        path.lineTo(145.0f, 205.495f);
        path.lineTo(15.0f, 705.735f);
        path.lineTo(455.0f, 225.645f);
        path.lineTo(555.0f, 615.665f);
        path.lineTo(565.0f, 885.925f);
        path.lineTo(175.0f, 465.715f);
        path.lineTo(515.0f, 75.485f);
        path.lineTo(755.0f, 995.685f);
        path.lineTo(385.0f, 205.265f);
        path.lineTo(725.0f, 135.505f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1005.0f, this.bounds.height() / 1005.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
