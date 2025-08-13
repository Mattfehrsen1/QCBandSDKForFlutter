package androidx.camera.core.impl;

/* loaded from: classes.dex */
public abstract class SurfaceConfig {

    public enum ConfigType {
        PRIV,
        YUV,
        JPEG,
        RAW
    }

    public abstract ConfigSize getConfigSize();

    public abstract ConfigType getConfigType();

    SurfaceConfig() {
    }

    public static SurfaceConfig create(ConfigType type, ConfigSize size) {
        return new AutoValue_SurfaceConfig(type, size);
    }

    public final boolean isSupported(SurfaceConfig surfaceConfig) {
        return surfaceConfig.getConfigSize().getId() <= getConfigSize().getId() && surfaceConfig.getConfigType() == getConfigType();
    }

    public enum ConfigSize {
        ANALYSIS(0),
        PREVIEW(1),
        RECORD(2),
        MAXIMUM(3),
        NOT_SUPPORT(4);

        final int mId;

        ConfigSize(int id) {
            this.mId = id;
        }

        int getId() {
            return this.mId;
        }
    }
}
