package androidx.camera.core.impl;

import android.util.Size;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_SurfaceSizeDefinition extends SurfaceSizeDefinition {
    private final Size analysisSize;
    private final Size previewSize;
    private final Size recordSize;

    AutoValue_SurfaceSizeDefinition(Size analysisSize, Size previewSize, Size recordSize) {
        Objects.requireNonNull(analysisSize, "Null analysisSize");
        this.analysisSize = analysisSize;
        Objects.requireNonNull(previewSize, "Null previewSize");
        this.previewSize = previewSize;
        Objects.requireNonNull(recordSize, "Null recordSize");
        this.recordSize = recordSize;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getAnalysisSize() {
        return this.analysisSize;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getPreviewSize() {
        return this.previewSize;
    }

    @Override // androidx.camera.core.impl.SurfaceSizeDefinition
    public Size getRecordSize() {
        return this.recordSize;
    }

    public String toString() {
        return "SurfaceSizeDefinition{analysisSize=" + this.analysisSize + ", previewSize=" + this.previewSize + ", recordSize=" + this.recordSize + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SurfaceSizeDefinition)) {
            return false;
        }
        SurfaceSizeDefinition surfaceSizeDefinition = (SurfaceSizeDefinition) o;
        return this.analysisSize.equals(surfaceSizeDefinition.getAnalysisSize()) && this.previewSize.equals(surfaceSizeDefinition.getPreviewSize()) && this.recordSize.equals(surfaceSizeDefinition.getRecordSize());
    }

    public int hashCode() {
        return ((((this.analysisSize.hashCode() ^ 1000003) * 1000003) ^ this.previewSize.hashCode()) * 1000003) ^ this.recordSize.hashCode();
    }
}
