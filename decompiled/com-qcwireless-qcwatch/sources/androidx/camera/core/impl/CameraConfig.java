package androidx.camera.core.impl;

import androidx.camera.core.CameraFilter;
import androidx.camera.core.impl.Config;

/* loaded from: classes.dex */
public interface CameraConfig extends ReadableConfig {
    public static final Config.Option<CameraFilter> OPTION_CAMERA_FILTER = Config.Option.create("camerax.core.camera.cameraFilter", CameraFilter.class);
    public static final Config.Option<UseCaseConfigFactory> OPTION_USECASE_CONFIG_FACTORY = Config.Option.create("camerax.core.camera.useCaseConfigFactory", UseCaseConfigFactory.class);

    public interface Builder<B> {
        B setCameraFilter(CameraFilter cameraFilter);

        B setUseCaseConfigFactory(UseCaseConfigFactory factory);
    }

    CameraFilter getCameraFilter();

    UseCaseConfigFactory getUseCaseConfigFactory();

    /* renamed from: androidx.camera.core.impl.CameraConfig$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static CameraFilter $default$getCameraFilter(CameraConfig _this) {
            return (CameraFilter) _this.retrieveOption(CameraConfig.OPTION_CAMERA_FILTER, CameraFilters.ANY);
        }
    }
}
