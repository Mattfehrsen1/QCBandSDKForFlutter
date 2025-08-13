package androidx.camera.view;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.Size;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.impl.utils.Threads;

/* loaded from: classes.dex */
class PreviewViewMeteringPointFactory extends MeteringPointFactory {
    static final PointF INVALID_POINT = new PointF(2.0f, 2.0f);
    private Matrix mMatrix;
    private final PreviewTransformation mPreviewTransformation;

    PreviewViewMeteringPointFactory(PreviewTransformation previewTransformation) {
        this.mPreviewTransformation = previewTransformation;
    }

    @Override // androidx.camera.core.MeteringPointFactory
    protected PointF convertPoint(float x, float y) {
        float[] fArr = {x, y};
        synchronized (this) {
            Matrix matrix = this.mMatrix;
            if (matrix == null) {
                return INVALID_POINT;
            }
            matrix.mapPoints(fArr);
            return new PointF(fArr[0], fArr[1]);
        }
    }

    void recalculate(Size previewViewSize, int layoutDirection) {
        Threads.checkMainThread();
        synchronized (this) {
            if (previewViewSize.getWidth() != 0 && previewViewSize.getHeight() != 0) {
                this.mMatrix = this.mPreviewTransformation.getPreviewViewToNormalizedSurfaceMatrix(previewViewSize, layoutDirection);
                return;
            }
            this.mMatrix = null;
        }
    }
}
