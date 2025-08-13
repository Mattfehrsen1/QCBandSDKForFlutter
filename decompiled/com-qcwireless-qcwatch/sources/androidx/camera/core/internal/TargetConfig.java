package androidx.camera.core.internal;

import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ReadableConfig;
import com.android.tools.r8.annotations.SynthesizedClass;

/* loaded from: classes.dex */
public interface TargetConfig<T> extends ReadableConfig {
    public static final Config.Option<String> OPTION_TARGET_NAME = Config.Option.create("camerax.core.target.name", String.class);
    public static final Config.Option<Class<?>> OPTION_TARGET_CLASS = Config.Option.create("camerax.core.target.class", Class.class);

    public interface Builder<T, B> {
        B setTargetClass(Class<T> targetClass);

        B setTargetName(String targetName);
    }

    Class<T> getTargetClass();

    Class<T> getTargetClass(Class<T> valueIfMissing);

    String getTargetName();

    String getTargetName(String valueIfMissing);

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: androidx.camera.core.internal.TargetConfig$-CC, reason: invalid class name */
    public final /* synthetic */ class CC<T> {
        public static Class $default$getTargetClass(TargetConfig _this, Class valueIfMissing) {
            return (Class) _this.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, valueIfMissing);
        }

        public static Class $default$getTargetClass(TargetConfig _this) {
            return (Class) _this.retrieveOption(TargetConfig.OPTION_TARGET_CLASS);
        }

        public static String $default$getTargetName(TargetConfig _this, String valueIfMissing) {
            return (String) _this.retrieveOption(TargetConfig.OPTION_TARGET_NAME, valueIfMissing);
        }

        public static String $default$getTargetName(TargetConfig _this) {
            return (String) _this.retrieveOption(TargetConfig.OPTION_TARGET_NAME);
        }
    }
}
