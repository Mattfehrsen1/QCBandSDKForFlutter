package com.smartapp.b.obfsjhzadn;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;

/* loaded from: classes4.dex */
public class Mpxbimtonik extends ShapeDrawable {
    private Rect bounds;
    private final Paint paint;
    private final Path path;

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return 1015;
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public Mpxbimtonik() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Path path = new Path();
        this.path = path;
        path.lineTo(165.0f, 255.125f);
        path.lineTo(725.0f, 595.195f);
        path.lineTo(5.0f, 565.185f);
        path.lineTo(265.0f, 895.425f);
        path.lineTo(5.0f, 515.215f);
        path.lineTo(115.0f, 925.885f);
        path.lineTo(45.0f, 635.865f);
        path.lineTo(715.0f, 955.915f);
        path.lineTo(815.0f, 425.895f);
        path.lineTo(655.0f, 745.585f);
        path.lineTo(455.0f, 215.595f);
        path.lineTo(975.0f, 835.985f);
        path.lineTo(475.0f, 5.725f);
        path.lineTo(735.0f, 445.155f);
        path.lineTo(575.0f, 915.875f);
        path.lineTo(335.0f, 275.395f);
        path.lineTo(465.0f, 75.515f);
        path.lineTo(905.0f, 145.165f);
        path.lineTo(55.0f, 715.45f);
        path.lineTo(515.0f, 835.515f);
        path.lineTo(965.0f, 525.805f);
        path.lineTo(935.0f, 385.505f);
        path.lineTo(105.0f, 485.505f);
        path.lineTo(535.0f, 55.705f);
        path.lineTo(575.0f, 575.985f);
        path.lineTo(975.0f, 575.485f);
        path.lineTo(325.0f, 645.385f);
        path.lineTo(335.0f, 195.235f);
        path.lineTo(835.0f, 555.275f);
        path.lineTo(775.0f, 305.875f);
        path.lineTo(475.0f, 485.715f);
        path.lineTo(695.0f, 925.05f);
        path.lineTo(165.0f, 265.165f);
        path.lineTo(935.0f, 935.595f);
        path.lineTo(175.0f, 825.35f);
        path.lineTo(85.0f, 35.425f);
        path.lineTo(615.0f, 5.455f);
        path.lineTo(305.0f, 995.575f);
        path.lineTo(925.0f, 505.275f);
        path.lineTo(405.0f, 15.855f);
        path.lineTo(575.0f, 505.345f);
        path.lineTo(795.0f, 825.825f);
        path.lineTo(175.0f, 345.535f);
        path.lineTo(265.0f, 95.455f);
        path.lineTo(555.0f, 825.265f);
        path.lineTo(665.0f, 895.405f);
        path.lineTo(805.0f, 765.725f);
        path.lineTo(105.0f, 115.815f);
        path.lineTo(295.0f, 635.475f);
        path.lineTo(495.0f, 555.885f);
        path.lineTo(435.0f, 425.585f);
        path.lineTo(165.0f, 795.775f);
        path.lineTo(385.0f, 785.235f);
        path.lineTo(235.0f, 735.315f);
        path.lineTo(425.0f, 135.25f);
        path.lineTo(335.0f, 855.25f);
        path.lineTo(915.0f, 415.385f);
        path.lineTo(905.0f, 865.505f);
        path.lineTo(455.0f, 205.405f);
        path.lineTo(635.0f, 195.65f);
        path.lineTo(35.0f, 555.285f);
        path.lineTo(535.0f, 345.735f);
        path.lineTo(395.0f, 845.555f);
        path.lineTo(185.0f, 65.705f);
        path.lineTo(95.0f, 275.155f);
        path.lineTo(525.0f, 835.165f);
        path.lineTo(185.0f, 855.615f);
        path.lineTo(195.0f, 725.125f);
        path.lineTo(315.0f, 715.545f);
        path.lineTo(965.0f, 375.815f);
        path.lineTo(45.0f, 165.825f);
        path.lineTo(605.0f, 425.505f);
        path.lineTo(85.0f, 735.935f);
        path.lineTo(305.0f, 845.585f);
        path.lineTo(855.0f, 645.35f);
        path.lineTo(805.0f, 265.385f);
        path.lineTo(265.0f, 445.255f);
        path.lineTo(175.0f, 405.145f);
        path.lineTo(435.0f, 235.145f);
        path.lineTo(305.0f, 465.15f);
        path.lineTo(425.0f, 175.715f);
        path.lineTo(915.0f, 455.445f);
        path.lineTo(955.0f, 415.405f);
        path.lineTo(5.0f, 935.35f);
        path.lineTo(105.0f, 975.425f);
        path.lineTo(855.0f, 295.685f);
        path.lineTo(745.0f, 525.985f);
        path.lineTo(895.0f, 905.165f);
        path.lineTo(425.0f, 825.65f);
        path.lineTo(705.0f, 955.825f);
        path.lineTo(365.0f, 795.535f);
        path.lineTo(995.0f, 685.525f);
        path.close();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.bounds == null) {
            return;
        }
        canvas.save();
        canvas.scale(r0.width() / 1015.0f, this.bounds.height() / 1015.0f);
        canvas.drawPath(this.path, this.paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.ShapeDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.bounds = bounds;
    }
}
