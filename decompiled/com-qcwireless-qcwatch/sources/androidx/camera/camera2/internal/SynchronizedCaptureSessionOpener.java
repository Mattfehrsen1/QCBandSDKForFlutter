package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.impl.DeferrableSurface;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
final class SynchronizedCaptureSessionOpener {
    static final String FEATURE_DEFERRABLE_SURFACE_CLOSE = "deferrableSurface_close";
    static final String FEATURE_FORCE_CLOSE = "force_close";
    static final String FEATURE_WAIT_FOR_REQUEST = "wait_for_request";
    private final OpenerImpl mImpl;

    interface OpenerImpl {
        SessionConfigurationCompat createSessionConfigurationCompat(int sessionType, List<OutputConfigurationCompat> outputsCompat, SynchronizedCaptureSession.StateCallback stateCallback);

        Executor getExecutor();

        ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> deferrableSurfaces);

        ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> deferrableSurfaces, long timeout);

        boolean stop();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SynchronizedSessionFeature {
    }

    SynchronizedCaptureSessionOpener(OpenerImpl impl) {
        this.mImpl = impl;
    }

    ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List<DeferrableSurface> deferrableSurfaces) {
        return this.mImpl.openCaptureSession(cameraDevice, sessionConfigurationCompat, deferrableSurfaces);
    }

    SessionConfigurationCompat createSessionConfigurationCompat(int sessionType, List<OutputConfigurationCompat> outputsCompat, SynchronizedCaptureSession.StateCallback stateCallback) {
        return this.mImpl.createSessionConfigurationCompat(sessionType, outputsCompat, stateCallback);
    }

    ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> deferrableSurfaces, long timeout) {
        return this.mImpl.startWithDeferrableSurface(deferrableSurfaces, timeout);
    }

    boolean stop() {
        return this.mImpl.stop();
    }

    public Executor getExecutor() {
        return this.mImpl.getExecutor();
    }

    static class Builder {
        private final CaptureSessionRepository mCaptureSessionRepository;
        private final Handler mCompatHandler;
        private final Set<String> mEnableFeature;
        private final Executor mExecutor;
        private final ScheduledExecutorService mScheduledExecutorService;
        private final int mSupportedHardwareLevel;

        Builder(Executor executor, ScheduledExecutorService scheduledExecutorService, Handler compatHandler, CaptureSessionRepository captureSessionRepository, int supportedHardwareLevel) {
            HashSet hashSet = new HashSet();
            this.mEnableFeature = hashSet;
            this.mExecutor = executor;
            this.mScheduledExecutorService = scheduledExecutorService;
            this.mCompatHandler = compatHandler;
            this.mCaptureSessionRepository = captureSessionRepository;
            this.mSupportedHardwareLevel = supportedHardwareLevel;
            if (Build.VERSION.SDK_INT < 23) {
                hashSet.add(SynchronizedCaptureSessionOpener.FEATURE_FORCE_CLOSE);
            }
            if (supportedHardwareLevel == 2 || Build.VERSION.SDK_INT <= 23) {
                hashSet.add(SynchronizedCaptureSessionOpener.FEATURE_DEFERRABLE_SURFACE_CLOSE);
            }
            if (supportedHardwareLevel == 2) {
                hashSet.add(SynchronizedCaptureSessionOpener.FEATURE_WAIT_FOR_REQUEST);
            }
        }

        SynchronizedCaptureSessionOpener build() {
            if (this.mEnableFeature.isEmpty()) {
                return new SynchronizedCaptureSessionOpener(new SynchronizedCaptureSessionBaseImpl(this.mCaptureSessionRepository, this.mExecutor, this.mScheduledExecutorService, this.mCompatHandler));
            }
            return new SynchronizedCaptureSessionOpener(new SynchronizedCaptureSessionImpl(this.mEnableFeature, this.mCaptureSessionRepository, this.mExecutor, this.mScheduledExecutorService, this.mCompatHandler));
        }
    }
}
