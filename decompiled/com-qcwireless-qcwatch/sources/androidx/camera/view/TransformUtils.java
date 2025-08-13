package androidx.camera.view;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;

/* loaded from: classes.dex */
public class TransformUtils {
    public static final RectF NORMALIZED_RECT = new RectF(-1.0f, -1.0f, 1.0f, 1.0f);

    private TransformUtils() {
    }

    public static Size rectToSize(Rect rect) {
        return new Size(rect.width(), rect.height());
    }

    public static RectF verticesToRect(float[] vertices) {
        return new RectF(min(vertices[0], vertices[2], vertices[4], vertices[6]), min(vertices[1], vertices[3], vertices[5], vertices[7]), max(vertices[0], vertices[2], vertices[4], vertices[6]), max(vertices[1], vertices[3], vertices[5], vertices[7]));
    }

    public static float max(float value1, float value2, float value3, float value4) {
        return Math.max(Math.max(value1, value2), Math.max(value3, value4));
    }

    public static float min(float value1, float value2, float value3, float value4) {
        return Math.min(Math.min(value1, value2), Math.min(value3, value4));
    }

    public static int surfaceRotationToRotationDegrees(int rotationValue) {
        if (rotationValue == 0) {
            return 0;
        }
        if (rotationValue == 1) {
            return 90;
        }
        if (rotationValue == 2) {
            return 180;
        }
        if (rotationValue == 3) {
            return 270;
        }
        throw new IllegalStateException("Unexpected rotation value " + rotationValue);
    }

    public static boolean is90or270(int rotationDegrees) {
        if (rotationDegrees == 90 || rotationDegrees == 270) {
            return true;
        }
        if (rotationDegrees == 0 || rotationDegrees == 180) {
            return false;
        }
        throw new IllegalArgumentException("Invalid rotation degrees: " + rotationDegrees);
    }

    public static float[] sizeToVertices(Size size) {
        return new float[]{0.0f, 0.0f, size.getWidth(), 0.0f, size.getWidth(), size.getHeight(), 0.0f, size.getHeight()};
    }

    public static float[] rectToVertices(RectF rectF) {
        return new float[]{rectF.left, rectF.top, rectF.right, rectF.top, rectF.right, rectF.bottom, rectF.left, rectF.bottom};
    }

    public static boolean isAspectRatioMatchingWithRoundingError(Size size1, boolean isAccurate1, Size size2, boolean isAccurate2) {
        float width;
        float width2;
        float width3;
        float width4;
        if (isAccurate1) {
            width = size1.getWidth() / size1.getHeight();
            width2 = width;
        } else {
            width = (size1.getWidth() + 1.0f) / (size1.getHeight() - 1.0f);
            width2 = (size1.getWidth() - 1.0f) / (size1.getHeight() + 1.0f);
        }
        if (isAccurate2) {
            width3 = size2.getWidth() / size2.getHeight();
            width4 = width3;
        } else {
            width3 = (size2.getWidth() - 1.0f) / (size2.getHeight() + 1.0f);
            width4 = (size2.getWidth() + 1.0f) / (size2.getHeight() - 1.0f);
        }
        return width >= width3 && width4 >= width2;
    }

    public static Matrix getRectToRect(RectF source, RectF target, int rotationDegrees) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(source, NORMALIZED_RECT, Matrix.ScaleToFit.FILL);
        matrix.postRotate(rotationDegrees);
        matrix.postConcat(getNormalizedToBuffer(target));
        return matrix;
    }

    public static Matrix getNormalizedToBuffer(Rect viewPortRect) {
        return getNormalizedToBuffer(new RectF(viewPortRect));
    }

    private static Matrix getNormalizedToBuffer(RectF viewPortRect) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(NORMALIZED_RECT, viewPortRect, Matrix.ScaleToFit.FILL);
        return matrix;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Matrix getExifTransform(int exifOrientation, int width, int height) {
        Matrix matrix = new Matrix();
        float f = width;
        float f2 = height;
        RectF rectF = new RectF(0.0f, 0.0f, f, f2);
        RectF rectF2 = NORMALIZED_RECT;
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
        boolean z = true;
        switch (exifOrientation) {
            case 2:
                matrix.postScale(-1.0f, 1.0f);
                z = false;
                break;
            case 3:
                matrix.postRotate(180.0f);
                z = false;
                break;
            case 4:
                matrix.postScale(1.0f, -1.0f);
                z = false;
                break;
            case 5:
                matrix.postScale(-1.0f, 1.0f);
                matrix.postRotate(270.0f);
                break;
            case 6:
                matrix.postRotate(90.0f);
                break;
            case 7:
                matrix.postScale(-1.0f, 1.0f);
                matrix.postRotate(90.0f);
                break;
            case 8:
                matrix.postRotate(270.0f);
                break;
            default:
                z = false;
                break;
        }
        if (z) {
            rectF = new RectF(0.0f, 0.0f, f2, f);
        }
        Matrix matrix2 = new Matrix();
        matrix2.setRectToRect(rectF2, rectF, Matrix.ScaleToFit.FILL);
        matrix.postConcat(matrix2);
        return matrix;
    }
}
