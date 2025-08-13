package com.smartapp.b.fidhydxcod;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Tdlwtujbrbn extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Tdlwtujbrbn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(385.0f, 75.415f);
        path.lineTo(175.0f, 415.545f);
        path.lineTo(925.0f, 285.645f);
        path.lineTo(485.0f, 635.815f);
        path.lineTo(415.0f, 585.85f);
        path.lineTo(145.0f, 185.195f);
        path.lineTo(725.0f, 975.995f);
        path.lineTo(285.0f, 305.785f);
        path.lineTo(605.0f, 605.435f);
        path.lineTo(85.0f, 215.555f);
        path.lineTo(995.0f, 825.165f);
        path.lineTo(945.0f, 535.595f);
        path.lineTo(815.0f, 905.125f);
        path.lineTo(465.0f, 465.915f);
        path.lineTo(905.0f, 125.865f);
        path.lineTo(795.0f, 305.615f);
        path.lineTo(655.0f, 195.905f);
        path.lineTo(425.0f, 775.225f);
        path.lineTo(125.0f, 675.505f);
        path.lineTo(785.0f, 655.885f);
        path.lineTo(495.0f, 605.05f);
        path.lineTo(305.0f, 595.95f);
        path.lineTo(185.0f, 155.225f);
        path.lineTo(745.0f, 225.825f);
        path.lineTo(265.0f, 15.525f);
        path.lineTo(435.0f, 815.545f);
        path.lineTo(35.0f, 555.535f);
        path.lineTo(615.0f, 515.1016f);
        path.lineTo(825.0f, 505.595f);
        path.lineTo(715.0f, 405.255f);
        path.lineTo(205.0f, 65.725f);
        path.lineTo(985.0f, 955.305f);
        path.lineTo(995.0f, 345.925f);
        path.lineTo(355.0f, 525.405f);
        path.lineTo(675.0f, 165.865f);
        path.lineTo(185.0f, 935.495f);
        path.lineTo(505.0f, 955.405f);
        path.lineTo(635.0f, 525.465f);
        path.lineTo(565.0f, 595.225f);
        path.lineTo(145.0f, 385.355f);
        path.lineTo(55.0f, 385.315f);
        path.lineTo(705.0f, 435.485f);
        path.lineTo(305.0f, 195.785f);
        path.lineTo(575.0f, 125.945f);
        path.lineTo(555.0f, 195.895f);
        path.lineTo(395.0f, 775.565f);
        path.lineTo(565.0f, 325.215f);
        path.lineTo(115.0f, 915.655f);
        path.lineTo(735.0f, 455.415f);
        path.lineTo(275.0f, 375.825f);
        path.lineTo(485.0f, 745.775f);
        path.lineTo(175.0f, 435.325f);
        path.lineTo(545.0f, 585.475f);
        path.lineTo(45.0f, 985.765f);
        path.lineTo(185.0f, 965.835f);
        path.lineTo(445.0f, 815.115f);
        path.lineTo(5.0f, 335.215f);
        path.lineTo(865.0f, 975.1016f);
        path.lineTo(655.0f, 865.965f);
        path.lineTo(365.0f, 445.915f);
        path.lineTo(145.0f, 885.635f);
        path.lineTo(895.0f, 175.865f);
        path.lineTo(75.0f, 805.655f);
        path.lineTo(765.0f, 85.25f);
        path.lineTo(305.0f, 615.695f);
        path.lineTo(605.0f, 395.895f);
        path.lineTo(995.0f, 455.415f);
        path.lineTo(225.0f, 985.535f);
        path.lineTo(315.0f, 5.515f);
        path.lineTo(465.0f, 505.85f);
        path.lineTo(525.0f, 695.745f);
        path.lineTo(425.0f, 585.115f);
        path.lineTo(445.0f, 605.645f);
        path.lineTo(505.0f, 485.515f);
        path.lineTo(235.0f, 365.985f);
        path.lineTo(10165.0f, 345.45f);
        path.lineTo(365.0f, 485.485f);
        path.lineTo(205.0f, 955.905f);
        path.lineTo(925.0f, 235.315f);
        path.lineTo(985.0f, 235.35f);
        path.lineTo(745.0f, 805.195f);
        path.lineTo(475.0f, 895.745f);
        path.lineTo(655.0f, 485.745f);
        path.lineTo(915.0f, 865.825f);
        path.lineTo(65.0f, 735.905f);
        path.lineTo(105.0f, 155.185f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1016.0f, this.bounds.height() / 1016.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
