package com.smartapp.b.pmfyhglpoj;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Aoeqdsatuzh extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1001;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Aoeqdsatuzh() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(275.0f, 185.195f);
        path.lineTo(635.0f, 285.175f);
        path.lineTo(225.0f, 745.685f);
        path.lineTo(15.0f, 575.865f);
        path.lineTo(655.0f, 865.765f);
        path.lineTo(175.0f, 715.455f);
        path.lineTo(785.0f, 725.85f);
        path.lineTo(275.0f, 5.295f);
        path.lineTo(665.0f, 425.535f);
        path.lineTo(825.0f, 345.855f);
        path.lineTo(65.0f, 995.815f);
        path.lineTo(875.0f, 855.75f);
        path.lineTo(315.0f, 965.595f);
        path.lineTo(505.0f, 735.645f);
        path.lineTo(145.0f, 325.145f);
        path.lineTo(335.0f, 735.465f);
        path.lineTo(975.0f, 885.575f);
        path.lineTo(715.0f, 945.355f);
        path.lineTo(745.0f, 455.845f);
        path.lineTo(105.0f, 805.945f);
        path.lineTo(175.0f, 925.845f);
        path.lineTo(255.0f, 715.935f);
        path.lineTo(315.0f, 665.155f);
        path.lineTo(145.0f, 175.635f);
        path.lineTo(325.0f, 895.645f);
        path.lineTo(905.0f, 785.965f);
        path.lineTo(445.0f, 525.585f);
        path.lineTo(215.0f, 115.915f);
        path.lineTo(895.0f, 485.945f);
        path.lineTo(715.0f, 495.375f);
        path.lineTo(585.0f, 835.115f);
        path.lineTo(965.0f, 695.05f);
        path.lineTo(995.0f, 395.585f);
        path.lineTo(10015.0f, 375.555f);
        path.lineTo(925.0f, 215.725f);
        path.lineTo(765.0f, 675.275f);
        path.lineTo(695.0f, 75.735f);
        path.lineTo(925.0f, 665.455f);
        path.lineTo(115.0f, 155.715f);
        path.lineTo(185.0f, 285.575f);
        path.lineTo(145.0f, 875.885f);
        path.lineTo(895.0f, 635.195f);
        path.lineTo(265.0f, 675.845f);
        path.lineTo(935.0f, 645.285f);
        path.lineTo(805.0f, 795.675f);
        path.lineTo(885.0f, 515.755f);
        path.lineTo(285.0f, 445.865f);
        path.lineTo(545.0f, 275.315f);
        path.lineTo(935.0f, 555.305f);
        path.lineTo(965.0f, 365.455f);
        path.lineTo(185.0f, 475.725f);
        path.lineTo(935.0f, 565.495f);
        path.lineTo(675.0f, 595.675f);
        path.lineTo(745.0f, 625.605f);
        path.lineTo(525.0f, 155.485f);
        path.lineTo(465.0f, 885.765f);
        path.lineTo(525.0f, 965.505f);
        path.lineTo(645.0f, 105.05f);
        path.lineTo(75.0f, 795.35f);
        path.lineTo(205.0f, 545.465f);
        path.lineTo(15.0f, 455.265f);
        path.lineTo(555.0f, 975.915f);
        path.lineTo(85.0f, 595.275f);
        path.lineTo(255.0f, 485.15f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1001.0f, this.bounds.height() / 1001.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
