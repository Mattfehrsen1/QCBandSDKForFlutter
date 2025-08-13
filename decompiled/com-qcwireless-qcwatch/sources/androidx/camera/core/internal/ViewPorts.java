package androidx.camera.core.internal;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.UseCase;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class ViewPorts {
    private static float flipX(float x, float flipLineX) {
        return (flipLineX + flipLineX) - x;
    }

    private static float flipY(float y, float flipLineY) {
        return (flipLineY + flipLineY) - y;
    }

    private static boolean shouldMirrorStartAndEnd(boolean isFrontCamera, int layoutDirection) {
        return isFrontCamera ^ (layoutDirection == 1);
    }

    private ViewPorts() {
    }

    public static Map<UseCase, Rect> calculateViewPortRects(Rect fullSensorRect, boolean isFrontCamera, Rational viewPortAspectRatio, int outputRotationDegrees, int scaleType, int layoutDirection, Map<UseCase, Size> useCaseSizes) {
        Preconditions.checkArgument(fullSensorRect.width() > 0 && fullSensorRect.height() > 0, "Cannot compute viewport crop rects zero sized sensor rect.");
        RectF rectF = new RectF(fullSensorRect);
        HashMap map = new HashMap();
        RectF rectF2 = new RectF(fullSensorRect);
        for (Map.Entry<UseCase, Size> entry : useCaseSizes.entrySet()) {
            Matrix matrix = new Matrix();
            RectF rectF3 = new RectF(0.0f, 0.0f, entry.getValue().getWidth(), entry.getValue().getHeight());
            matrix.setRectToRect(rectF3, rectF, Matrix.ScaleToFit.CENTER);
            map.put(entry.getKey(), matrix);
            RectF rectF4 = new RectF();
            matrix.mapRect(rectF4, rectF3);
            rectF2.intersect(rectF4);
        }
        RectF scaledRect = getScaledRect(rectF2, ImageUtil.getRotatedAspectRatio(outputRotationDegrees, viewPortAspectRatio), scaleType, isFrontCamera, layoutDirection, outputRotationDegrees);
        HashMap map2 = new HashMap();
        RectF rectF5 = new RectF();
        Matrix matrix2 = new Matrix();
        for (Map.Entry entry2 : map.entrySet()) {
            ((Matrix) entry2.getValue()).invert(matrix2);
            matrix2.mapRect(rectF5, scaledRect);
            Rect rect = new Rect();
            rectF5.round(rect);
            map2.put((UseCase) entry2.getKey(), rect);
        }
        return map2;
    }

    public static RectF getScaledRect(RectF fittingRect, Rational containerAspectRatio, int scaleType, boolean isFrontCamera, int layoutDirection, int rotationDegrees) {
        if (scaleType == 3) {
            return fittingRect;
        }
        Matrix matrix = new Matrix();
        RectF rectF = new RectF(0.0f, 0.0f, containerAspectRatio.getNumerator(), containerAspectRatio.getDenominator());
        if (scaleType == 0) {
            matrix.setRectToRect(rectF, fittingRect, Matrix.ScaleToFit.START);
        } else if (scaleType == 1) {
            matrix.setRectToRect(rectF, fittingRect, Matrix.ScaleToFit.CENTER);
        } else if (scaleType == 2) {
            matrix.setRectToRect(rectF, fittingRect, Matrix.ScaleToFit.END);
        } else {
            throw new IllegalStateException("Unexpected scale type: " + scaleType);
        }
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        return correctStartOrEnd(shouldMirrorStartAndEnd(isFrontCamera, layoutDirection), rotationDegrees, fittingRect, rectF2);
    }

    private static RectF correctStartOrEnd(boolean isMirrored, int rotationDegrees, RectF containerRect, RectF cropRect) {
        boolean z = rotationDegrees == 0 && !isMirrored;
        boolean z2 = rotationDegrees == 90 && isMirrored;
        if (z || z2) {
            return cropRect;
        }
        boolean z3 = rotationDegrees == 0 && isMirrored;
        boolean z4 = rotationDegrees == 270 && !isMirrored;
        if (z3 || z4) {
            return flipHorizontally(cropRect, containerRect.centerX());
        }
        boolean z5 = rotationDegrees == 90 && !isMirrored;
        boolean z6 = rotationDegrees == 180 && isMirrored;
        if (z5 || z6) {
            return flipVertically(cropRect, containerRect.centerY());
        }
        boolean z7 = rotationDegrees == 180 && !isMirrored;
        boolean z8 = rotationDegrees == 270 && isMirrored;
        if (z7 || z8) {
            return flipHorizontally(flipVertically(cropRect, containerRect.centerY()), containerRect.centerX());
        }
        throw new IllegalArgumentException("Invalid argument: mirrored " + isMirrored + " rotation " + rotationDegrees);
    }

    private static RectF flipHorizontally(RectF original, float flipLineX) {
        return new RectF(flipX(original.right, flipLineX), original.top, flipX(original.left, flipLineX), original.bottom);
    }

    private static RectF flipVertically(RectF original, float flipLineY) {
        return new RectF(original.left, flipY(original.bottom, flipLineY), original.right, flipY(original.top, flipLineY));
    }
}
