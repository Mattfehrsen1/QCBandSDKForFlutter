package androidx.camera.core.impl;

import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.UseCase;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public interface CameraInternal extends Camera, UseCase.StateChangeCallback {
    void attachUseCases(Collection<UseCase> useCases);

    void close();

    void detachUseCases(Collection<UseCase> useCases);

    @Override // androidx.camera.core.Camera
    CameraControl getCameraControl();

    CameraControlInternal getCameraControlInternal();

    @Override // androidx.camera.core.Camera
    CameraInfo getCameraInfo();

    CameraInfoInternal getCameraInfoInternal();

    @Override // androidx.camera.core.Camera
    LinkedHashSet<CameraInternal> getCameraInternals();

    Observable<State> getCameraState();

    @Override // androidx.camera.core.Camera
    CameraConfig getExtendedConfig();

    void open();

    ListenableFuture<Void> release();

    @Override // androidx.camera.core.Camera
    void setExtendedConfig(CameraConfig cameraConfig) throws CameraUseCaseAdapter.CameraException;

    public enum State {
        PENDING_OPEN(false),
        OPENING(true),
        OPEN(true),
        CLOSING(true),
        CLOSED(false),
        RELEASING(true),
        RELEASED(false);

        private final boolean mHoldsCameraSlot;

        State(boolean holdsCameraSlot) {
            this.mHoldsCameraSlot = holdsCameraSlot;
        }

        boolean holdsCameraSlot() {
            return this.mHoldsCameraSlot;
        }
    }

    /* renamed from: androidx.camera.core.impl.CameraInternal$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$setExtendedConfig(CameraInternal _this, CameraConfig cameraConfig) throws CameraUseCaseAdapter.CameraException {
        }

        public static LinkedHashSet $default$getCameraInternals(CameraInternal _this) {
            return new LinkedHashSet(Collections.singleton(_this));
        }
    }
}
