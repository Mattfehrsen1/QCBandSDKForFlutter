package androidx.camera.view.transform;

import android.graphics.Matrix;
import android.util.Size;

/* loaded from: classes.dex */
public final class OutputTransform {
    final Matrix mMatrix;
    final Size mViewPortSize;

    public OutputTransform(Matrix matrix, Size viewPortSize) {
        this.mMatrix = matrix;
        this.mViewPortSize = viewPortSize;
    }

    Matrix getMatrix() {
        return this.mMatrix;
    }

    Size getViewPortSize() {
        return this.mViewPortSize;
    }
}
