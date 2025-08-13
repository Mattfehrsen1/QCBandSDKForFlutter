package com.qcwireless.qcwatch.ui.plate.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.util.ToastUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceImageUtils.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u0016\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"makeRectWithCornerAndLight", "Landroid/graphics/Bitmap;", "sourceImg", ToastUtils.MODE.LIGHT, "", "corner", "makeRoundAndLight", "app_qwatch_proRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceImageUtilsKt {
    public static final Bitmap makeRoundAndLight(Bitmap sourceImg, int i) {
        int i2;
        int i3;
        float f;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(sourceImg, "sourceImg");
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i6 = 0; i6 < width; i6++) {
            iArr[i6] = (((((iArr[i6] >> 0) & 255) * i) / 100) << 0) | (iArr[i6] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i6] >> 8) & 255) * i) / 100) << 8) | (((((iArr[i6] >> 16) & 255) * i) / 100) << 16);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n        ar…ap.Config.ARGB_8888\n    )");
        int width2 = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        float f2 = height / 2;
        if (width2 > height) {
            i5 = (width2 - height) / 2;
            i3 = height;
            i2 = i5 + height;
            f = f2;
            i4 = 0;
        } else {
            if (height > width2) {
                i4 = (height - width2) / 2;
                f = width2 / 2;
                i2 = width2;
                i3 = i4 + width2;
            } else {
                i2 = width2;
                i3 = height;
                f = f2;
                i4 = 0;
            }
            i5 = 0;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(i5, i4, i2, i3);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateBitmap, rect, rect, paint);
        return bitmapCreateBitmap2;
    }

    public static final Bitmap makeRectWithCornerAndLight(Bitmap sourceImg, int i, int i2) {
        Intrinsics.checkNotNullParameter(sourceImg, "sourceImg");
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i3 = 0; i3 < width; i3++) {
            iArr[i3] = (((((iArr[i3] >> 0) & 255) * i) / 100) << 0) | (iArr[i3] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i3] >> 8) & 255) * i) / 100) << 8) | (((((iArr[i3] >> 16) & 255) * i) / 100) << 16);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n        ar…ap.Config.ARGB_8888\n    )");
        int width2 = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width2, height);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        float f = i2;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateBitmap, rect, rect, paint);
        return bitmapCreateBitmap2;
    }
}
