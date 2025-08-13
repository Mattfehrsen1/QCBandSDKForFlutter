package androidx.camera.core;

import android.os.Handler;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class CameraXConfig implements TargetConfig<CameraX> {
    private final OptionsBundle mConfig;
    static final Config.Option<CameraFactory.Provider> OPTION_CAMERA_FACTORY_PROVIDER = Config.Option.create("camerax.core.appConfig.cameraFactoryProvider", CameraFactory.Provider.class);
    static final Config.Option<CameraDeviceSurfaceManager.Provider> OPTION_DEVICE_SURFACE_MANAGER_PROVIDER = Config.Option.create("camerax.core.appConfig.deviceSurfaceManagerProvider", CameraDeviceSurfaceManager.Provider.class);
    static final Config.Option<UseCaseConfigFactory.Provider> OPTION_USECASE_CONFIG_FACTORY_PROVIDER = Config.Option.create("camerax.core.appConfig.useCaseConfigFactoryProvider", UseCaseConfigFactory.Provider.class);
    static final Config.Option<Executor> OPTION_CAMERA_EXECUTOR = Config.Option.create("camerax.core.appConfig.cameraExecutor", Executor.class);
    static final Config.Option<Handler> OPTION_SCHEDULER_HANDLER = Config.Option.create("camerax.core.appConfig.schedulerHandler", Handler.class);
    static final Config.Option<Integer> OPTION_MIN_LOGGING_LEVEL = Config.Option.create("camerax.core.appConfig.minimumLoggingLevel", Integer.TYPE);
    static final Config.Option<CameraSelector> OPTION_AVAILABLE_CAMERAS_LIMITER = Config.Option.create("camerax.core.appConfig.availableCamerasLimiter", CameraSelector.class);

    public interface Provider {
        CameraXConfig getCameraXConfig();
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ boolean containsOption(Config.Option option) {
        return getConfig().containsOption(option);
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ void findOptions(String str, Config.OptionMatcher optionMatcher) {
        getConfig().findOptions(str, optionMatcher);
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ Config.OptionPriority getOptionPriority(Config.Option option) {
        return getConfig().getOptionPriority(option);
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ Set getPriorities(Config.Option option) {
        return getConfig().getPriorities(option);
    }

    @Override // androidx.camera.core.internal.TargetConfig
    public /* synthetic */ Class<CameraX> getTargetClass() {
        return TargetConfig.CC.$default$getTargetClass(this);
    }

    @Override // androidx.camera.core.internal.TargetConfig
    public /* synthetic */ Class<CameraX> getTargetClass(Class<CameraX> cls) {
        return TargetConfig.CC.$default$getTargetClass(this, cls);
    }

    @Override // androidx.camera.core.internal.TargetConfig
    public /* synthetic */ String getTargetName() {
        return TargetConfig.CC.$default$getTargetName(this);
    }

    @Override // androidx.camera.core.internal.TargetConfig
    public /* synthetic */ String getTargetName(String str) {
        return TargetConfig.CC.$default$getTargetName(this, str);
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ Set listOptions() {
        return getConfig().listOptions();
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ Object retrieveOption(Config.Option option) {
        return getConfig().retrieveOption(option);
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ Object retrieveOption(Config.Option option, Object obj) {
        return getConfig().retrieveOption(option, obj);
    }

    @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
    public /* synthetic */ Object retrieveOptionWithPriority(Config.Option option, Config.OptionPriority optionPriority) {
        return getConfig().retrieveOptionWithPriority(option, optionPriority);
    }

    CameraXConfig(OptionsBundle options) {
        this.mConfig = options;
    }

    public CameraFactory.Provider getCameraFactoryProvider(CameraFactory.Provider valueIfMissing) {
        return (CameraFactory.Provider) this.mConfig.retrieveOption(OPTION_CAMERA_FACTORY_PROVIDER, valueIfMissing);
    }

    public CameraDeviceSurfaceManager.Provider getDeviceSurfaceManagerProvider(CameraDeviceSurfaceManager.Provider valueIfMissing) {
        return (CameraDeviceSurfaceManager.Provider) this.mConfig.retrieveOption(OPTION_DEVICE_SURFACE_MANAGER_PROVIDER, valueIfMissing);
    }

    public UseCaseConfigFactory.Provider getUseCaseConfigFactoryProvider(UseCaseConfigFactory.Provider valueIfMissing) {
        return (UseCaseConfigFactory.Provider) this.mConfig.retrieveOption(OPTION_USECASE_CONFIG_FACTORY_PROVIDER, valueIfMissing);
    }

    public Executor getCameraExecutor(Executor valueIfMissing) {
        return (Executor) this.mConfig.retrieveOption(OPTION_CAMERA_EXECUTOR, valueIfMissing);
    }

    public Handler getSchedulerHandler(Handler valueIfMissing) {
        return (Handler) this.mConfig.retrieveOption(OPTION_SCHEDULER_HANDLER, valueIfMissing);
    }

    public int getMinimumLoggingLevel() {
        return ((Integer) this.mConfig.retrieveOption(OPTION_MIN_LOGGING_LEVEL, 3)).intValue();
    }

    public CameraSelector getAvailableCamerasLimiter(CameraSelector valueIfMissing) {
        return (CameraSelector) this.mConfig.retrieveOption(OPTION_AVAILABLE_CAMERAS_LIMITER, valueIfMissing);
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    public Config getConfig() {
        return this.mConfig;
    }

    public static final class Builder implements TargetConfig.Builder<CameraX, Builder> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableConfig) {
            this.mMutableConfig = mutableConfig;
            Class cls = (Class) mutableConfig.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls != null && !cls.equals(CameraX.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            setTargetClass(CameraX.class);
        }

        public static Builder fromConfig(CameraXConfig configuration) {
            return new Builder(MutableOptionsBundle.from((Config) configuration));
        }

        public Builder setCameraFactoryProvider(CameraFactory.Provider cameraFactory) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_FACTORY_PROVIDER, cameraFactory);
            return this;
        }

        public Builder setDeviceSurfaceManagerProvider(CameraDeviceSurfaceManager.Provider surfaceManagerProvider) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_DEVICE_SURFACE_MANAGER_PROVIDER, surfaceManagerProvider);
            return this;
        }

        public Builder setUseCaseConfigFactoryProvider(UseCaseConfigFactory.Provider configFactoryProvider) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_USECASE_CONFIG_FACTORY_PROVIDER, configFactoryProvider);
            return this;
        }

        public Builder setCameraExecutor(Executor executor) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_EXECUTOR, executor);
            return this;
        }

        public Builder setSchedulerHandler(Handler handler) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_SCHEDULER_HANDLER, handler);
            return this;
        }

        public Builder setMinimumLoggingLevel(int logLevel) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_MIN_LOGGING_LEVEL, Integer.valueOf(logLevel));
            return this;
        }

        public Builder setAvailableCamerasLimiter(CameraSelector availableCameraSelector) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_AVAILABLE_CAMERAS_LIMITER, availableCameraSelector);
            return this;
        }

        private MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        public CameraXConfig build() {
            return new CameraXConfig(OptionsBundle.from(this.mMutableConfig));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public Builder setTargetClass(Class<CameraX> targetClass) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, targetClass);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(targetClass.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public Builder setTargetName(String targetName) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, targetName);
            return this;
        }
    }
}
