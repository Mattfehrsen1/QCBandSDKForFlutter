package androidx.camera.core;

import android.graphics.Rect;
import androidx.camera.core.SurfaceRequest;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_SurfaceRequest_TransformationInfo extends SurfaceRequest.TransformationInfo {
    private final Rect cropRect;
    private final int rotationDegrees;
    private final int targetRotation;

    AutoValue_SurfaceRequest_TransformationInfo(Rect cropRect, int rotationDegrees, int targetRotation) {
        Objects.requireNonNull(cropRect, "Null cropRect");
        this.cropRect = cropRect;
        this.rotationDegrees = rotationDegrees;
        this.targetRotation = targetRotation;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public Rect getCropRect() {
        return this.cropRect;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    @Override // androidx.camera.core.SurfaceRequest.TransformationInfo
    public int getTargetRotation() {
        return this.targetRotation;
    }

    public String toString() {
        return "TransformationInfo{cropRect=" + this.cropRect + ", rotationDegrees=" + this.rotationDegrees + ", targetRotation=" + this.targetRotation + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SurfaceRequest.TransformationInfo)) {
            return false;
        }
        SurfaceRequest.TransformationInfo transformationInfo = (SurfaceRequest.TransformationInfo) o;
        return this.cropRect.equals(transformationInfo.getCropRect()) && this.rotationDegrees == transformationInfo.getRotationDegrees() && this.targetRotation == transformationInfo.getTargetRotation();
    }

    public int hashCode() {
        return ((((this.cropRect.hashCode() ^ 1000003) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.targetRotation;
    }
}
