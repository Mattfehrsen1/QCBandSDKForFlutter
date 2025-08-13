package androidx.camera.core.impl;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.UseCaseEventConfig;
import com.android.tools.r8.annotations.SynthesizedClass;

/* loaded from: classes.dex */
public interface UseCaseConfig<T extends UseCase> extends TargetConfig<T>, UseCaseEventConfig, ImageInputConfig {
    public static final Config.Option<SessionConfig> OPTION_DEFAULT_SESSION_CONFIG = Config.Option.create("camerax.core.useCase.defaultSessionConfig", SessionConfig.class);
    public static final Config.Option<CaptureConfig> OPTION_DEFAULT_CAPTURE_CONFIG = Config.Option.create("camerax.core.useCase.defaultCaptureConfig", CaptureConfig.class);
    public static final Config.Option<SessionConfig.OptionUnpacker> OPTION_SESSION_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.sessionConfigUnpacker", SessionConfig.OptionUnpacker.class);
    public static final Config.Option<CaptureConfig.OptionUnpacker> OPTION_CAPTURE_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.captureConfigUnpacker", CaptureConfig.OptionUnpacker.class);
    public static final Config.Option<Integer> OPTION_SURFACE_OCCUPANCY_PRIORITY = Config.Option.create("camerax.core.useCase.surfaceOccupancyPriority", Integer.TYPE);
    public static final Config.Option<CameraSelector> OPTION_CAMERA_SELECTOR = Config.Option.create("camerax.core.useCase.cameraSelector", CameraSelector.class);

    public interface Builder<T extends UseCase, C extends UseCaseConfig<T>, B> extends TargetConfig.Builder<T, B>, ExtendableBuilder<T>, UseCaseEventConfig.Builder<B> {
        C getUseCaseConfig();

        B setCameraSelector(CameraSelector cameraSelector);

        B setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker);

        B setDefaultCaptureConfig(CaptureConfig captureConfig);

        B setDefaultSessionConfig(SessionConfig sessionConfig);

        B setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker);

        B setSurfaceOccupancyPriority(int priority);
    }

    CameraSelector getCameraSelector();

    CameraSelector getCameraSelector(CameraSelector valueIfMissing);

    CaptureConfig.OptionUnpacker getCaptureOptionUnpacker();

    CaptureConfig.OptionUnpacker getCaptureOptionUnpacker(CaptureConfig.OptionUnpacker valueIfMissing);

    CaptureConfig getDefaultCaptureConfig();

    CaptureConfig getDefaultCaptureConfig(CaptureConfig valueIfMissing);

    SessionConfig getDefaultSessionConfig();

    SessionConfig getDefaultSessionConfig(SessionConfig valueIfMissing);

    SessionConfig.OptionUnpacker getSessionOptionUnpacker();

    SessionConfig.OptionUnpacker getSessionOptionUnpacker(SessionConfig.OptionUnpacker valueIfMissing);

    int getSurfaceOccupancyPriority();

    int getSurfaceOccupancyPriority(int valueIfMissing);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: androidx.camera.core.impl.UseCaseConfig$-CC, reason: invalid class name */
    public final /* synthetic */ class CC<T extends UseCase> {
        public static SessionConfig $default$getDefaultSessionConfig(UseCaseConfig _this, SessionConfig valueIfMissing) {
            return (SessionConfig) _this.retrieveOption(UseCaseConfig.OPTION_DEFAULT_SESSION_CONFIG, valueIfMissing);
        }

        public static SessionConfig $default$getDefaultSessionConfig(UseCaseConfig _this) {
            return (SessionConfig) _this.retrieveOption(UseCaseConfig.OPTION_DEFAULT_SESSION_CONFIG);
        }

        public static CaptureConfig $default$getDefaultCaptureConfig(UseCaseConfig _this, CaptureConfig valueIfMissing) {
            return (CaptureConfig) _this.retrieveOption(UseCaseConfig.OPTION_DEFAULT_CAPTURE_CONFIG, valueIfMissing);
        }

        public static CaptureConfig $default$getDefaultCaptureConfig(UseCaseConfig _this) {
            return (CaptureConfig) _this.retrieveOption(UseCaseConfig.OPTION_DEFAULT_CAPTURE_CONFIG);
        }

        public static SessionConfig.OptionUnpacker $default$getSessionOptionUnpacker(UseCaseConfig _this, SessionConfig.OptionUnpacker valueIfMissing) {
            return (SessionConfig.OptionUnpacker) _this.retrieveOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER, valueIfMissing);
        }

        public static SessionConfig.OptionUnpacker $default$getSessionOptionUnpacker(UseCaseConfig _this) {
            return (SessionConfig.OptionUnpacker) _this.retrieveOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER);
        }

        public static CaptureConfig.OptionUnpacker $default$getCaptureOptionUnpacker(UseCaseConfig _this, CaptureConfig.OptionUnpacker valueIfMissing) {
            return (CaptureConfig.OptionUnpacker) _this.retrieveOption(UseCaseConfig.OPTION_CAPTURE_CONFIG_UNPACKER, valueIfMissing);
        }

        public static CaptureConfig.OptionUnpacker $default$getCaptureOptionUnpacker(UseCaseConfig _this) {
            return (CaptureConfig.OptionUnpacker) _this.retrieveOption(UseCaseConfig.OPTION_CAPTURE_CONFIG_UNPACKER);
        }

        public static CameraSelector $default$getCameraSelector(UseCaseConfig _this, CameraSelector valueIfMissing) {
            return (CameraSelector) _this.retrieveOption(UseCaseConfig.OPTION_CAMERA_SELECTOR, valueIfMissing);
        }

        public static CameraSelector $default$getCameraSelector(UseCaseConfig _this) {
            return (CameraSelector) _this.retrieveOption(UseCaseConfig.OPTION_CAMERA_SELECTOR);
        }
    }
}
