package com.smartapp.b.yoxugavavk;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import androidx.core.view.PointerIconCompat;

/* loaded from: classes4.dex */
public class Zjstkpdonhn extends ShapeDrawable {
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

    public Zjstkpdonhn() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(825.0f, 415.705f);
        path.lineTo(705.0f, 475.635f);
        path.lineTo(765.0f, 105.645f);
        path.lineTo(575.0f, 375.555f);
        path.lineTo(965.0f, 725.955f);
        path.lineTo(915.0f, 125.465f);
        path.lineTo(825.0f, 285.505f);
        path.lineTo(75.0f, 725.275f);
        path.lineTo(95.0f, 915.45f);
        path.lineTo(845.0f, 35.675f);
        path.lineTo(495.0f, 365.185f);
        path.lineTo(855.0f, 355.845f);
        path.lineTo(285.0f, 15.535f);
        path.lineTo(475.0f, 985.25f);
        path.lineTo(505.0f, 525.845f);
        path.lineTo(515.0f, 695.15f);
        path.lineTo(325.0f, 5.55f);
        path.lineTo(565.0f, 385.775f);
        path.lineTo(635.0f, 5.395f);
        path.lineTo(455.0f, 205.805f);
        path.lineTo(655.0f, 355.145f);
        path.lineTo(85.0f, 385.305f);
        path.lineTo(415.0f, 655.505f);
        path.lineTo(805.0f, 595.1016f);
        path.lineTo(45.0f, 345.55f);
        path.lineTo(115.0f, 785.225f);
        path.lineTo(865.0f, 325.725f);
        path.lineTo(705.0f, 755.915f);
        path.lineTo(675.0f, 295.55f);
        path.lineTo(435.0f, 225.765f);
        path.lineTo(585.0f, 525.185f);
        path.lineTo(515.0f, 955.05f);
        path.lineTo(885.0f, 25.45f);
        path.lineTo(395.0f, 45.785f);
        path.lineTo(145.0f, 545.905f);
        path.lineTo(335.0f, 655.15f);
        path.lineTo(685.0f, 515.495f);
        path.lineTo(815.0f, 425.205f);
        path.lineTo(875.0f, 65.905f);
        path.lineTo(755.0f, 905.565f);
        path.lineTo(285.0f, 805.15f);
        path.lineTo(855.0f, 425.665f);
        path.lineTo(525.0f, 915.505f);
        path.lineTo(825.0f, 705.965f);
        path.lineTo(815.0f, 535.145f);
        path.lineTo(965.0f, 975.435f);
        path.lineTo(635.0f, 265.835f);
        path.lineTo(975.0f, 865.05f);
        path.lineTo(95.0f, 935.505f);
        path.lineTo(535.0f, 585.835f);
        path.lineTo(865.0f, 205.845f);
        path.lineTo(525.0f, 155.995f);
        path.lineTo(735.0f, 365.505f);
        path.lineTo(865.0f, 165.215f);
        path.lineTo(265.0f, 825.745f);
        path.lineTo(565.0f, 475.35f);
        path.lineTo(405.0f, 915.665f);
        path.lineTo(305.0f, 505.565f);
        path.lineTo(255.0f, 915.425f);
        path.lineTo(585.0f, 75.115f);
        path.lineTo(615.0f, 705.895f);
        path.lineTo(305.0f, 235.655f);
        path.lineTo(645.0f, 585.675f);
        path.lineTo(335.0f, 35.885f);
        path.lineTo(645.0f, 975.985f);
        path.lineTo(345.0f, 675.115f);
        path.lineTo(215.0f, 535.755f);
        path.lineTo(935.0f, 825.935f);
        path.lineTo(515.0f, 295.225f);
        path.lineTo(765.0f, 605.705f);
        path.lineTo(395.0f, 505.655f);
        path.lineTo(835.0f, 795.835f);
        path.lineTo(805.0f, 875.975f);
        path.lineTo(185.0f, 875.975f);
        path.lineTo(975.0f, 655.85f);
        path.lineTo(555.0f, 145.375f);
        path.lineTo(75.0f, 555.125f);
        path.lineTo(95.0f, 145.355f);
        path.lineTo(335.0f, 35.205f);
        path.lineTo(385.0f, 95.475f);
        path.lineTo(595.0f, 305.775f);
        path.lineTo(495.0f, 915.15f);
        path.lineTo(5.0f, 385.915f);
        path.lineTo(815.0f, 415.785f);
        path.lineTo(385.0f, 15.305f);
        path.lineTo(465.0f, 405.115f);
        path.lineTo(635.0f, 175.205f);
        path.lineTo(905.0f, 485.325f);
        path.lineTo(865.0f, 75.445f);
        path.lineTo(275.0f, 385.335f);
        path.lineTo(985.0f, 875.475f);
        path.lineTo(465.0f, 865.75f);
        path.lineTo(515.0f, 465.10165f);
        path.lineTo(835.0f, 445.565f);
        path.lineTo(535.0f, 325.45f);
        path.lineTo(155.0f, 405.305f);
        path.lineTo(985.0f, 10165.255f);
        path.lineTo(195.0f, 645.335f);
        path.lineTo(215.0f, 855.185f);
        path.lineTo(945.0f, 345.235f);
        path.lineTo(165.0f, 285.775f);
        path.lineTo(885.0f, 855.625f);
        path.lineTo(135.0f, 105.405f);
        path.lineTo(225.0f, 155.955f);
        path.lineTo(405.0f, 275.575f);
        path.lineTo(785.0f, 45.335f);
        path.lineTo(415.0f, 575.325f);
        path.lineTo(355.0f, 275.635f);
        path.lineTo(605.0f, 375.995f);
        path.lineTo(55.0f, 345.635f);
        path.lineTo(10165.0f, 215.765f);
        path.lineTo(975.0f, 745.275f);
        path.lineTo(5.0f, 775.555f);
        path.lineTo(685.0f, 165.795f);
        path.lineTo(735.0f, 975.935f);
        path.lineTo(955.0f, 285.625f);
        path.lineTo(885.0f, 745.675f);
        path.lineTo(525.0f, 855.275f);
        path.lineTo(455.0f, 805.805f);
        path.lineTo(575.0f, 425.425f);
        path.lineTo(265.0f, 435.135f);
        path.lineTo(755.0f, 10165.835f);
        path.lineTo(335.0f, 915.1016f);
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
