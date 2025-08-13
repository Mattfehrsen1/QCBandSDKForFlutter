package androidx.camera.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Size;
import android.view.Display;
import android.view.TextureView;
import android.view.View;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.view.PreviewView;
import androidx.camera.view.internal.compat.quirk.DeviceQuirks;
import androidx.camera.view.internal.compat.quirk.PreviewOneThirdWiderQuirk;
import androidx.camera.view.internal.compat.quirk.TextureViewRotationQuirk;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
final class PreviewTransformation {
    private static final PreviewView.ScaleType DEFAULT_SCALE_TYPE = PreviewView.ScaleType.FILL_CENTER;
    private static final String TAG = "PreviewTransform";
    private boolean mIsFrontCamera;
    private int mPreviewRotationDegrees;
    private Size mResolution;
    private PreviewView.ScaleType mScaleType = DEFAULT_SCALE_TYPE;
    private Rect mSurfaceCropRect;
    private int mTargetRotation;
    private Rect mViewportRect;

    PreviewTransformation() {
    }

    void setTransformationInfo(SurfaceRequest.TransformationInfo transformationInfo, Size resolution, boolean isFrontCamera) {
        Logger.d(TAG, "Transformation info set: " + transformationInfo + " " + resolution + " " + isFrontCamera);
        this.mSurfaceCropRect = getCorrectedCropRect(transformationInfo.getCropRect());
        this.mViewportRect = transformationInfo.getCropRect();
        this.mPreviewRotationDegrees = transformationInfo.getRotationDegrees();
        this.mTargetRotation = transformationInfo.getTargetRotation();
        this.mResolution = resolution;
        this.mIsFrontCamera = isFrontCamera;
    }

    Matrix getTextureViewCorrectionMatrix() {
        Preconditions.checkState(isTransformationInfoReady());
        RectF rectF = new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight());
        int correctionRotation = -TransformUtils.surfaceRotationToRotationDegrees(this.mTargetRotation);
        TextureViewRotationQuirk textureViewRotationQuirk = (TextureViewRotationQuirk) DeviceQuirks.get(TextureViewRotationQuirk.class);
        if (textureViewRotationQuirk != null) {
            correctionRotation += textureViewRotationQuirk.getCorrectionRotation(this.mIsFrontCamera);
        }
        return TransformUtils.getRectToRect(rectF, rectF, correctionRotation);
    }

    void transformView(Size previewViewSize, int layoutDirection, View preview) {
        if (previewViewSize.getHeight() == 0 || previewViewSize.getWidth() == 0) {
            Logger.w(TAG, "Transform not applied due to PreviewView size: " + previewViewSize);
            return;
        }
        if (isTransformationInfoReady()) {
            if (preview instanceof TextureView) {
                ((TextureView) preview).setTransform(getTextureViewCorrectionMatrix());
            } else {
                Display display = preview.getDisplay();
                if (display != null && display.getRotation() != this.mTargetRotation) {
                    Logger.e(TAG, "Non-display rotation not supported with SurfaceView / PERFORMANCE mode.");
                }
            }
            RectF transformedSurfaceRect = getTransformedSurfaceRect(previewViewSize, layoutDirection);
            preview.setPivotX(0.0f);
            preview.setPivotY(0.0f);
            preview.setScaleX(transformedSurfaceRect.width() / this.mResolution.getWidth());
            preview.setScaleY(transformedSurfaceRect.height() / this.mResolution.getHeight());
            preview.setTranslationX(transformedSurfaceRect.left - preview.getLeft());
            preview.setTranslationY(transformedSurfaceRect.top - preview.getTop());
        }
    }

    void setScaleType(PreviewView.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    PreviewView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    private RectF getTransformedSurfaceRect(Size previewViewSize, int layoutDirection) {
        Preconditions.checkState(isTransformationInfoReady());
        Matrix surfaceToPreviewViewMatrix = getSurfaceToPreviewViewMatrix(previewViewSize, layoutDirection);
        RectF rectF = new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight());
        surfaceToPreviewViewMatrix.mapRect(rectF);
        return rectF;
    }

    Matrix getSurfaceToPreviewViewMatrix(Size previewViewSize, int layoutDirection) {
        RectF previewViewViewportRectForMismatchedAspectRatios;
        Preconditions.checkState(isTransformationInfoReady());
        if (isViewportAspectRatioMatchPreviewView(previewViewSize)) {
            previewViewViewportRectForMismatchedAspectRatios = new RectF(0.0f, 0.0f, previewViewSize.getWidth(), previewViewSize.getHeight());
        } else {
            previewViewViewportRectForMismatchedAspectRatios = getPreviewViewViewportRectForMismatchedAspectRatios(previewViewSize, layoutDirection);
        }
        Matrix rectToRect = TransformUtils.getRectToRect(new RectF(this.mSurfaceCropRect), previewViewViewportRectForMismatchedAspectRatios, this.mPreviewRotationDegrees);
        if (this.mIsFrontCamera) {
            if (TransformUtils.is90or270(this.mPreviewRotationDegrees)) {
                rectToRect.preScale(1.0f, -1.0f, this.mSurfaceCropRect.centerX(), this.mSurfaceCropRect.centerY());
            } else {
                rectToRect.preScale(-1.0f, 1.0f, this.mSurfaceCropRect.centerX(), this.mSurfaceCropRect.centerY());
            }
        }
        return rectToRect;
    }

    private Rect getCorrectedCropRect(Rect surfaceCropRect) {
        PreviewOneThirdWiderQuirk previewOneThirdWiderQuirk = (PreviewOneThirdWiderQuirk) DeviceQuirks.get(PreviewOneThirdWiderQuirk.class);
        if (previewOneThirdWiderQuirk == null) {
            return surfaceCropRect;
        }
        RectF rectF = new RectF(surfaceCropRect);
        Matrix matrix = new Matrix();
        matrix.setScale(previewOneThirdWiderQuirk.getCropRectScaleX(), 1.0f, surfaceCropRect.centerX(), surfaceCropRect.centerY());
        matrix.mapRect(rectF);
        Rect rect = new Rect();
        rectF.round(rect);
        return rect;
    }

    RectF getPreviewViewViewportRectForMismatchedAspectRatios(Size previewViewSize, int layoutDirection) {
        RectF rectF = new RectF(0.0f, 0.0f, previewViewSize.getWidth(), previewViewSize.getHeight());
        Size rotatedViewportSize = getRotatedViewportSize();
        RectF rectF2 = new RectF(0.0f, 0.0f, rotatedViewportSize.getWidth(), rotatedViewportSize.getHeight());
        Matrix matrix = new Matrix();
        setMatrixRectToRect(matrix, rectF2, rectF, this.mScaleType);
        matrix.mapRect(rectF2);
        return layoutDirection == 1 ? flipHorizontally(rectF2, previewViewSize.getWidth() / 2.0f) : rectF2;
    }

    /* renamed from: androidx.camera.view.PreviewTransformation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$view$PreviewView$ScaleType;

        static {
            int[] iArr = new int[PreviewView.ScaleType.values().length];
            $SwitchMap$androidx$camera$view$PreviewView$ScaleType = iArr;
            try {
                iArr[PreviewView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$view$PreviewView$ScaleType[PreviewView.ScaleType.FILL_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$view$PreviewView$ScaleType[PreviewView.ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$camera$view$PreviewView$ScaleType[PreviewView.ScaleType.FILL_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$camera$view$PreviewView$ScaleType[PreviewView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$camera$view$PreviewView$ScaleType[PreviewView.ScaleType.FILL_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static void setMatrixRectToRect(Matrix matrix, RectF source, RectF destination, PreviewView.ScaleType scaleType) {
        Matrix.ScaleToFit scaleToFit;
        switch (AnonymousClass1.$SwitchMap$androidx$camera$view$PreviewView$ScaleType[scaleType.ordinal()]) {
            case 1:
            case 2:
                scaleToFit = Matrix.ScaleToFit.CENTER;
                break;
            case 3:
            case 4:
                scaleToFit = Matrix.ScaleToFit.END;
                break;
            case 5:
            case 6:
                scaleToFit = Matrix.ScaleToFit.START;
                break;
            default:
                Logger.e(TAG, "Unexpected crop rect: " + scaleType);
                scaleToFit = Matrix.ScaleToFit.FILL;
                break;
        }
        if (scaleType == PreviewView.ScaleType.FIT_CENTER || scaleType == PreviewView.ScaleType.FIT_START || scaleType == PreviewView.ScaleType.FIT_END) {
            matrix.setRectToRect(source, destination, scaleToFit);
        } else {
            matrix.setRectToRect(destination, source, scaleToFit);
            matrix.invert(matrix);
        }
    }

    private static RectF flipHorizontally(RectF original, float flipLineX) {
        float f = flipLineX + flipLineX;
        return new RectF(f - original.right, original.top, f - original.left, original.bottom);
    }

    private Size getRotatedViewportSize() {
        if (TransformUtils.is90or270(this.mPreviewRotationDegrees)) {
            return new Size(this.mViewportRect.height(), this.mViewportRect.width());
        }
        return new Size(this.mViewportRect.width(), this.mViewportRect.height());
    }

    boolean isViewportAspectRatioMatchPreviewView(Size previewViewSize) {
        return TransformUtils.isAspectRatioMatchingWithRoundingError(previewViewSize, true, getRotatedViewportSize(), false);
    }

    Rect getSurfaceCropRect() {
        return this.mSurfaceCropRect;
    }

    Bitmap createTransformedBitmap(Bitmap original, Size previewViewSize, int layoutDirection) {
        if (!isTransformationInfoReady()) {
            return original;
        }
        Matrix textureViewCorrectionMatrix = getTextureViewCorrectionMatrix();
        RectF transformedSurfaceRect = getTransformedSurfaceRect(previewViewSize, layoutDirection);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(previewViewSize.getWidth(), previewViewSize.getHeight(), original.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Matrix matrix = new Matrix();
        matrix.postConcat(textureViewCorrectionMatrix);
        matrix.postScale(transformedSurfaceRect.width() / this.mResolution.getWidth(), transformedSurfaceRect.height() / this.mResolution.getHeight());
        matrix.postTranslate(transformedSurfaceRect.left, transformedSurfaceRect.top);
        canvas.drawBitmap(original, matrix, new Paint(7));
        return bitmapCreateBitmap;
    }

    Matrix getPreviewViewToNormalizedSurfaceMatrix(Size previewViewSize, int layoutDirection) {
        if (!isTransformationInfoReady()) {
            return null;
        }
        Matrix matrix = new Matrix();
        getSurfaceToPreviewViewMatrix(previewViewSize, layoutDirection).invert(matrix);
        Matrix matrix2 = new Matrix();
        matrix2.setRectToRect(new RectF(0.0f, 0.0f, this.mResolution.getWidth(), this.mResolution.getHeight()), new RectF(0.0f, 0.0f, 1.0f, 1.0f), Matrix.ScaleToFit.FILL);
        matrix.postConcat(matrix2);
        return matrix;
    }

    private boolean isTransformationInfoReady() {
        return (this.mSurfaceCropRect == null || this.mResolution == null) ? false : true;
    }
}
