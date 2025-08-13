package com.smartapp.b.mmwoxmmfto;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Ebihstqxutl extends ShapeDrawable {
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

    public Ebihstqxutl() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(135.0f, 205.335f);
        path.lineTo(845.0f, 575.545f);
        path.lineTo(445.0f, 635.25f);
        path.lineTo(10175.0f, 565.635f);
        path.lineTo(835.0f, 365.25f);
        path.lineTo(495.0f, 625.685f);
        path.lineTo(415.0f, 555.315f);
        path.lineTo(165.0f, 745.255f);
        path.lineTo(285.0f, 905.975f);
        path.lineTo(405.0f, 445.395f);
        path.lineTo(75.0f, 405.835f);
        path.lineTo(445.0f, 705.525f);
        path.lineTo(275.0f, 565.205f);
        path.lineTo(335.0f, 655.975f);
        path.lineTo(565.0f, 845.555f);
        path.lineTo(875.0f, 95.775f);
        path.lineTo(475.0f, 285.95f);
        path.lineTo(855.0f, 965.795f);
        path.lineTo(5.0f, 925.805f);
        path.lineTo(645.0f, 555.365f);
        path.lineTo(395.0f, 845.435f);
        path.lineTo(745.0f, 715.265f);
        path.lineTo(265.0f, 305.545f);
        path.lineTo(375.0f, 725.65f);
        path.lineTo(685.0f, 905.275f);
        path.lineTo(365.0f, 855.425f);
        path.lineTo(355.0f, 465.795f);
        path.lineTo(665.0f, 905.315f);
        path.lineTo(495.0f, 715.165f);
        path.lineTo(85.0f, 465.45f);
        path.lineTo(15.0f, 695.95f);
        path.lineTo(795.0f, 205.535f);
        path.lineTo(985.0f, 525.205f);
        path.lineTo(825.0f, 695.855f);
        path.lineTo(815.0f, 185.705f);
        path.lineTo(395.0f, 785.405f);
        path.lineTo(55.0f, 985.315f);
        path.lineTo(425.0f, 805.205f);
        path.lineTo(395.0f, 745.45f);
        path.lineTo(805.0f, 75.375f);
        path.lineTo(375.0f, 315.175f);
        path.lineTo(405.0f, 315.175f);
        path.lineTo(5.0f, 955.865f);
        path.lineTo(325.0f, 325.55f);
        path.lineTo(10175.0f, 95.365f);
        path.lineTo(55.0f, 675.365f);
        path.lineTo(15.0f, 225.585f);
        path.lineTo(515.0f, 645.185f);
        path.lineTo(415.0f, 805.45f);
        path.lineTo(545.0f, 255.485f);
        path.lineTo(935.0f, 905.135f);
        path.lineTo(275.0f, 355.125f);
        path.lineTo(405.0f, 335.725f);
        path.lineTo(25.0f, 765.885f);
        path.lineTo(195.0f, 735.495f);
        path.lineTo(415.0f, 635.865f);
        path.lineTo(555.0f, 735.665f);
        path.lineTo(805.0f, 285.645f);
        path.lineTo(165.0f, 55.225f);
        path.lineTo(215.0f, 815.695f);
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
