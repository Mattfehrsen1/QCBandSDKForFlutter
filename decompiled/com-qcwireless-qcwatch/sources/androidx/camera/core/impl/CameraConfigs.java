package androidx.camera.core.impl;

import androidx.camera.core.CameraFilter;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.Set;

/* loaded from: classes.dex */
public class CameraConfigs {
    private static final CameraConfig EMPTY_CONFIG = new EmptyCameraConfig();

    public static CameraConfig emptyConfig() {
        return EMPTY_CONFIG;
    }

    static final class EmptyCameraConfig implements CameraConfig {
        private final UseCaseConfigFactory mUseCaseConfigFactory = new UseCaseConfigFactory() { // from class: androidx.camera.core.impl.CameraConfigs.EmptyCameraConfig.1
            @Override // androidx.camera.core.impl.UseCaseConfigFactory
            public Config getConfig(UseCaseConfigFactory.CaptureType captureType) {
                return null;
            }
        };

        @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
        public /* synthetic */ boolean containsOption(Config.Option option) {
            return getConfig().containsOption(option);
        }

        @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
        public /* synthetic */ void findOptions(String str, Config.OptionMatcher optionMatcher) {
            getConfig().findOptions(str, optionMatcher);
        }

        @Override // androidx.camera.core.impl.CameraConfig
        public /* synthetic */ CameraFilter getCameraFilter() {
            return CameraConfig.CC.$default$getCameraFilter(this);
        }

        @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
        public /* synthetic */ Config.OptionPriority getOptionPriority(Config.Option option) {
            return getConfig().getOptionPriority(option);
        }

        @Override // androidx.camera.core.impl.ReadableConfig, androidx.camera.core.impl.Config
        public /* synthetic */ Set getPriorities(Config.Option option) {
            return getConfig().getPriorities(option);
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

        EmptyCameraConfig() {
        }

        @Override // androidx.camera.core.impl.CameraConfig
        public UseCaseConfigFactory getUseCaseConfigFactory() {
            return this.mUseCaseConfigFactory;
        }

        @Override // androidx.camera.core.impl.ReadableConfig
        public Config getConfig() {
            return OptionsBundle.emptyBundle();
        }
    }

    private CameraConfigs() {
    }
}
