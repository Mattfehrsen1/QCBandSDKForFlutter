package androidx.camera.core.impl;

import android.os.Handler;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class AutoValue_CameraThreadConfig extends CameraThreadConfig {
    private final Executor cameraExecutor;
    private final Handler schedulerHandler;

    AutoValue_CameraThreadConfig(Executor cameraExecutor, Handler schedulerHandler) {
        Objects.requireNonNull(cameraExecutor, "Null cameraExecutor");
        this.cameraExecutor = cameraExecutor;
        Objects.requireNonNull(schedulerHandler, "Null schedulerHandler");
        this.schedulerHandler = schedulerHandler;
    }

    @Override // androidx.camera.core.impl.CameraThreadConfig
    public Executor getCameraExecutor() {
        return this.cameraExecutor;
    }

    @Override // androidx.camera.core.impl.CameraThreadConfig
    public Handler getSchedulerHandler() {
        return this.schedulerHandler;
    }

    public String toString() {
        return "CameraThreadConfig{cameraExecutor=" + this.cameraExecutor + ", schedulerHandler=" + this.schedulerHandler + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CameraThreadConfig)) {
            return false;
        }
        CameraThreadConfig cameraThreadConfig = (CameraThreadConfig) o;
        return this.cameraExecutor.equals(cameraThreadConfig.getCameraExecutor()) && this.schedulerHandler.equals(cameraThreadConfig.getSchedulerHandler());
    }

    public int hashCode() {
        return ((this.cameraExecutor.hashCode() ^ 1000003) * 1000003) ^ this.schedulerHandler.hashCode();
    }
}
