package androidx.camera.camera2.internal;

import androidx.camera.core.ZoomState;
import androidx.core.math.MathUtils;

/* loaded from: classes.dex */
class ZoomStateImpl implements ZoomState {
    private float mLinearZoom;
    private final float mMaxZoomRatio;
    private final float mMinZoomRatio;
    private float mZoomRatio;

    ZoomStateImpl(float maxZoomRatio, float minZoomRatio) {
        this.mMaxZoomRatio = maxZoomRatio;
        this.mMinZoomRatio = minZoomRatio;
    }

    void setZoomRatio(float zoomRatio) throws IllegalArgumentException {
        if (zoomRatio > this.mMaxZoomRatio || zoomRatio < this.mMinZoomRatio) {
            throw new IllegalArgumentException("Requested zoomRatio " + zoomRatio + " is not within valid range [" + this.mMinZoomRatio + " , " + this.mMaxZoomRatio + "]");
        }
        this.mZoomRatio = zoomRatio;
        this.mLinearZoom = getPercentageByRatio(zoomRatio);
    }

    void setLinearZoom(float linearZoom) throws IllegalArgumentException {
        if (linearZoom > 1.0f || linearZoom < 0.0f) {
            throw new IllegalArgumentException("Requested linearZoom " + linearZoom + " is not within valid range [0..1]");
        }
        this.mLinearZoom = linearZoom;
        this.mZoomRatio = getRatioByPercentage(linearZoom);
    }

    @Override // androidx.camera.core.ZoomState
    public float getZoomRatio() {
        return this.mZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMaxZoomRatio() {
        return this.mMaxZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getMinZoomRatio() {
        return this.mMinZoomRatio;
    }

    @Override // androidx.camera.core.ZoomState
    public float getLinearZoom() {
        return this.mLinearZoom;
    }

    private float getRatioByPercentage(float percentage) {
        if (percentage == 1.0f) {
            return this.mMaxZoomRatio;
        }
        if (percentage == 0.0f) {
            return this.mMinZoomRatio;
        }
        float f = this.mMaxZoomRatio;
        float f2 = this.mMinZoomRatio;
        double d = 1.0f / f2;
        return (float) MathUtils.clamp(1.0d / (d + (((1.0f / f) - d) * percentage)), f2, f);
    }

    private float getPercentageByRatio(float ratio) {
        float f = this.mMaxZoomRatio;
        float f2 = this.mMinZoomRatio;
        if (f == f2) {
            return 0.0f;
        }
        if (ratio == f) {
            return 1.0f;
        }
        if (ratio == f2) {
            return 0.0f;
        }
        float f3 = 1.0f / f2;
        return ((1.0f / ratio) - f3) / ((1.0f / f) - f3);
    }
}
