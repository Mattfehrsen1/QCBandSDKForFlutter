package com.smartapp.b.mmwoxmmfto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Cenamdsixdl extends ShapeDrawable {
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

    public Cenamdsixdl() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(15.0f, 605.775f);
        path.lineTo(935.0f, 405.865f);
        path.lineTo(635.0f, 815.465f);
        path.lineTo(605.0f, 25.345f);
        path.lineTo(515.0f, 695.115f);
        path.lineTo(235.0f, 615.845f);
        path.lineTo(595.0f, 955.535f);
        path.lineTo(785.0f, 725.445f);
        path.lineTo(175.0f, 625.365f);
        path.lineTo(535.0f, 955.945f);
        path.lineTo(485.0f, 475.935f);
        path.lineTo(305.0f, 315.255f);
        path.lineTo(495.0f, 25.945f);
        path.lineTo(135.0f, 155.585f);
        path.lineTo(415.0f, 325.535f);
        path.lineTo(185.0f, 715.935f);
        path.lineTo(955.0f, 965.235f);
        path.lineTo(595.0f, 55.885f);
        path.lineTo(235.0f, 225.385f);
        path.lineTo(595.0f, 705.275f);
        path.lineTo(25.0f, 655.75f);
        path.lineTo(675.0f, 885.495f);
        path.lineTo(225.0f, 775.235f);
        path.lineTo(835.0f, 905.665f);
        path.lineTo(645.0f, 95.485f);
        path.lineTo(185.0f, 505.515f);
        path.lineTo(665.0f, 735.135f);
        path.lineTo(825.0f, 775.845f);
        path.lineTo(515.0f, 175.545f);
        path.lineTo(95.0f, 535.625f);
        path.lineTo(955.0f, 435.385f);
        path.lineTo(435.0f, 675.555f);
        path.lineTo(675.0f, 5.395f);
        path.lineTo(285.0f, 435.815f);
        path.lineTo(495.0f, 215.985f);
        path.lineTo(975.0f, 955.615f);
        path.lineTo(985.0f, 235.125f);
        path.lineTo(585.0f, 415.665f);
        path.lineTo(205.0f, 345.785f);
        path.lineTo(205.0f, 55.705f);
        path.lineTo(585.0f, 325.785f);
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
