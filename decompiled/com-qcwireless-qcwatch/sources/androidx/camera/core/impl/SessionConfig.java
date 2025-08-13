package androidx.camera.core.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class SessionConfig {
    private final List<CameraDevice.StateCallback> mDeviceStateCallbacks;
    private final List<ErrorListener> mErrorListeners;
    private final CaptureConfig mRepeatingCaptureConfig;
    private final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks;
    private final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks;
    private final List<DeferrableSurface> mSurfaces;

    public interface ErrorListener {
        void onError(SessionConfig sessionConfig, SessionError error);
    }

    public interface OptionUnpacker {
        void unpack(UseCaseConfig<?> config, Builder builder);
    }

    public enum SessionError {
        SESSION_ERROR_SURFACE_NEEDS_RESET,
        SESSION_ERROR_UNKNOWN
    }

    SessionConfig(List<DeferrableSurface> surfaces, List<CameraDevice.StateCallback> deviceStateCallbacks, List<CameraCaptureSession.StateCallback> sessionStateCallbacks, List<CameraCaptureCallback> singleCameraCaptureCallbacks, List<ErrorListener> errorListeners, CaptureConfig repeatingCaptureConfig) {
        this.mSurfaces = surfaces;
        this.mDeviceStateCallbacks = Collections.unmodifiableList(deviceStateCallbacks);
        this.mSessionStateCallbacks = Collections.unmodifiableList(sessionStateCallbacks);
        this.mSingleCameraCaptureCallbacks = Collections.unmodifiableList(singleCameraCaptureCallbacks);
        this.mErrorListeners = Collections.unmodifiableList(errorListeners);
        this.mRepeatingCaptureConfig = repeatingCaptureConfig;
    }

    public static SessionConfig defaultEmptySessionConfig() {
        return new SessionConfig(new ArrayList(), new ArrayList(0), new ArrayList(0), new ArrayList(0), new ArrayList(0), new CaptureConfig.Builder().build());
    }

    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.mSurfaces);
    }

    public Config getImplementationOptions() {
        return this.mRepeatingCaptureConfig.getImplementationOptions();
    }

    public int getTemplateType() {
        return this.mRepeatingCaptureConfig.getTemplateType();
    }

    public List<CameraDevice.StateCallback> getDeviceStateCallbacks() {
        return this.mDeviceStateCallbacks;
    }

    public List<CameraCaptureSession.StateCallback> getSessionStateCallbacks() {
        return this.mSessionStateCallbacks;
    }

    public List<CameraCaptureCallback> getRepeatingCameraCaptureCallbacks() {
        return this.mRepeatingCaptureConfig.getCameraCaptureCallbacks();
    }

    public List<ErrorListener> getErrorListeners() {
        return this.mErrorListeners;
    }

    public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
        return this.mSingleCameraCaptureCallbacks;
    }

    public CaptureConfig getRepeatingCaptureConfig() {
        return this.mRepeatingCaptureConfig;
    }

    static class BaseBuilder {
        final Set<DeferrableSurface> mSurfaces = new HashSet();
        final CaptureConfig.Builder mCaptureConfigBuilder = new CaptureConfig.Builder();
        final List<CameraDevice.StateCallback> mDeviceStateCallbacks = new ArrayList();
        final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks = new ArrayList();
        final List<ErrorListener> mErrorListeners = new ArrayList();
        final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks = new ArrayList();

        BaseBuilder() {
        }
    }

    public static class Builder extends BaseBuilder {
        public static Builder createFrom(UseCaseConfig<?> config) {
            OptionUnpacker sessionOptionUnpacker = config.getSessionOptionUnpacker(null);
            if (sessionOptionUnpacker == null) {
                throw new IllegalStateException("Implementation is missing option unpacker for " + config.getTargetName(config.toString()));
            }
            Builder builder = new Builder();
            sessionOptionUnpacker.unpack(config, builder);
            return builder;
        }

        public void setTemplateType(int templateType) {
            this.mCaptureConfigBuilder.setTemplateType(templateType);
        }

        public void addTag(String key, Integer tag) {
            this.mCaptureConfigBuilder.addTag(key, tag);
        }

        public void addDeviceStateCallback(CameraDevice.StateCallback deviceStateCallback) {
            if (this.mDeviceStateCallbacks.contains(deviceStateCallback)) {
                throw new IllegalArgumentException("Duplicate device state callback.");
            }
            this.mDeviceStateCallbacks.add(deviceStateCallback);
        }

        public void addAllDeviceStateCallbacks(Collection<CameraDevice.StateCallback> deviceStateCallbacks) {
            Iterator<CameraDevice.StateCallback> it = deviceStateCallbacks.iterator();
            while (it.hasNext()) {
                addDeviceStateCallback(it.next());
            }
        }

        public void addSessionStateCallback(CameraCaptureSession.StateCallback sessionStateCallback) {
            if (this.mSessionStateCallbacks.contains(sessionStateCallback)) {
                throw new IllegalArgumentException("Duplicate session state callback.");
            }
            this.mSessionStateCallbacks.add(sessionStateCallback);
        }

        public void addAllSessionStateCallbacks(List<CameraCaptureSession.StateCallback> sessionStateCallbacks) {
            Iterator<CameraCaptureSession.StateCallback> it = sessionStateCallbacks.iterator();
            while (it.hasNext()) {
                addSessionStateCallback(it.next());
            }
        }

        public void addRepeatingCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCaptureConfigBuilder.addCameraCaptureCallback(cameraCaptureCallback);
        }

        public void addAllRepeatingCameraCaptureCallbacks(Collection<CameraCaptureCallback> cameraCaptureCallbacks) {
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(cameraCaptureCallbacks);
        }

        public void addCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCaptureConfigBuilder.addCameraCaptureCallback(cameraCaptureCallback);
            this.mSingleCameraCaptureCallbacks.add(cameraCaptureCallback);
        }

        public void addAllCameraCaptureCallbacks(Collection<CameraCaptureCallback> cameraCaptureCallbacks) {
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(cameraCaptureCallbacks);
            this.mSingleCameraCaptureCallbacks.addAll(cameraCaptureCallbacks);
        }

        public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
            return Collections.unmodifiableList(this.mSingleCameraCaptureCallbacks);
        }

        public void addErrorListener(ErrorListener errorListener) {
            this.mErrorListeners.add(errorListener);
        }

        public void addSurface(DeferrableSurface surface) {
            this.mSurfaces.add(surface);
            this.mCaptureConfigBuilder.addSurface(surface);
        }

        public void addNonRepeatingSurface(DeferrableSurface surface) {
            this.mSurfaces.add(surface);
        }

        public void removeSurface(DeferrableSurface surface) {
            this.mSurfaces.remove(surface);
            this.mCaptureConfigBuilder.removeSurface(surface);
        }

        public void clearSurfaces() {
            this.mSurfaces.clear();
            this.mCaptureConfigBuilder.clearSurfaces();
        }

        public void setImplementationOptions(Config config) {
            this.mCaptureConfigBuilder.setImplementationOptions(config);
        }

        public void addImplementationOptions(Config config) {
            this.mCaptureConfigBuilder.addImplementationOptions(config);
        }

        public SessionConfig build() {
            return new SessionConfig(new ArrayList(this.mSurfaces), this.mDeviceStateCallbacks, this.mSessionStateCallbacks, this.mSingleCameraCaptureCallbacks, this.mErrorListeners, this.mCaptureConfigBuilder.build());
        }
    }

    public static final class ValidatingBuilder extends BaseBuilder {
        private static final String TAG = "ValidatingBuilder";
        private boolean mValid = true;
        private boolean mTemplateSet = false;

        public void add(SessionConfig sessionConfig) {
            CaptureConfig repeatingCaptureConfig = sessionConfig.getRepeatingCaptureConfig();
            if (repeatingCaptureConfig.getTemplateType() != -1) {
                if (!this.mTemplateSet) {
                    this.mCaptureConfigBuilder.setTemplateType(repeatingCaptureConfig.getTemplateType());
                    this.mTemplateSet = true;
                } else if (this.mCaptureConfigBuilder.getTemplateType() != repeatingCaptureConfig.getTemplateType()) {
                    Logger.d(TAG, "Invalid configuration due to template type: " + this.mCaptureConfigBuilder.getTemplateType() + " != " + repeatingCaptureConfig.getTemplateType());
                    this.mValid = false;
                }
            }
            this.mCaptureConfigBuilder.addAllTags(sessionConfig.getRepeatingCaptureConfig().getTagBundle());
            this.mDeviceStateCallbacks.addAll(sessionConfig.getDeviceStateCallbacks());
            this.mSessionStateCallbacks.addAll(sessionConfig.getSessionStateCallbacks());
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(sessionConfig.getRepeatingCameraCaptureCallbacks());
            this.mSingleCameraCaptureCallbacks.addAll(sessionConfig.getSingleCameraCaptureCallbacks());
            this.mErrorListeners.addAll(sessionConfig.getErrorListeners());
            this.mSurfaces.addAll(sessionConfig.getSurfaces());
            this.mCaptureConfigBuilder.getSurfaces().addAll(repeatingCaptureConfig.getSurfaces());
            if (!this.mSurfaces.containsAll(this.mCaptureConfigBuilder.getSurfaces())) {
                Logger.d(TAG, "Invalid configuration due to capture request surfaces are not a subset of surfaces");
                this.mValid = false;
            }
            this.mCaptureConfigBuilder.addImplementationOptions(repeatingCaptureConfig.getImplementationOptions());
        }

        public boolean isValid() {
            return this.mTemplateSet && this.mValid;
        }

        public SessionConfig build() {
            if (!this.mValid) {
                throw new IllegalArgumentException("Unsupported session configuration combination");
            }
            return new SessionConfig(new ArrayList(this.mSurfaces), this.mDeviceStateCallbacks, this.mSessionStateCallbacks, this.mSingleCameraCaptureCallbacks, this.mErrorListeners, this.mCaptureConfigBuilder.build());
        }
    }
}
