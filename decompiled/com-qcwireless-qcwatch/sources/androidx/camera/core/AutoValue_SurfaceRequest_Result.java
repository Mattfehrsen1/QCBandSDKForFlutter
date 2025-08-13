package androidx.camera.core;

import android.view.Surface;
import androidx.camera.core.SurfaceRequest;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_SurfaceRequest_Result extends SurfaceRequest.Result {
    private final int resultCode;
    private final Surface surface;

    AutoValue_SurfaceRequest_Result(int resultCode, Surface surface) {
        this.resultCode = resultCode;
        Objects.requireNonNull(surface, "Null surface");
        this.surface = surface;
    }

    @Override // androidx.camera.core.SurfaceRequest.Result
    public int getResultCode() {
        return this.resultCode;
    }

    @Override // androidx.camera.core.SurfaceRequest.Result
    public Surface getSurface() {
        return this.surface;
    }

    public String toString() {
        return "Result{resultCode=" + this.resultCode + ", surface=" + this.surface + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SurfaceRequest.Result)) {
            return false;
        }
        SurfaceRequest.Result result = (SurfaceRequest.Result) o;
        return this.resultCode == result.getResultCode() && this.surface.equals(result.getSurface());
    }

    public int hashCode() {
        return ((this.resultCode ^ 1000003) * 1000003) ^ this.surface.hashCode();
    }
}
