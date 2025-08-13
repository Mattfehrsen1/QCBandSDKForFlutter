package androidx.camera.core.impl;

import java.util.Set;

/* loaded from: classes.dex */
public interface Config {

    public interface OptionMatcher {
        boolean onOptionMatched(Option<?> option);
    }

    public enum OptionPriority {
        ALWAYS_OVERRIDE,
        REQUIRED,
        OPTIONAL
    }

    boolean containsOption(Option<?> id);

    void findOptions(String idSearchString, OptionMatcher matcher);

    OptionPriority getOptionPriority(Option<?> opt);

    Set<OptionPriority> getPriorities(Option<?> option);

    Set<Option<?>> listOptions();

    <ValueT> ValueT retrieveOption(Option<ValueT> id);

    <ValueT> ValueT retrieveOption(Option<ValueT> id, ValueT valueIfMissing);

    <ValueT> ValueT retrieveOptionWithPriority(Option<ValueT> id, OptionPriority priority);

    public static abstract class Option<T> {
        public abstract String getId();

        public abstract Object getToken();

        public abstract Class<T> getValueClass();

        Option() {
        }

        public static <T> Option<T> create(String id, Class<?> valueClass) {
            return create(id, valueClass, null);
        }

        public static <T> Option<T> create(String id, Class<?> valueClass, Object token) {
            return new AutoValue_Config_Option(id, valueClass, token);
        }
    }

    /* renamed from: androidx.camera.core.impl.Config$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean hasConflict(OptionPriority priority1, OptionPriority priority2) {
            if (priority1 == OptionPriority.ALWAYS_OVERRIDE && priority2 == OptionPriority.ALWAYS_OVERRIDE) {
                return true;
            }
            return priority1 == OptionPriority.REQUIRED && priority2 == OptionPriority.REQUIRED;
        }

        public static Config mergeConfigs(Config extendedConfig, Config baseConfig) {
            MutableOptionsBundle mutableOptionsBundleCreate;
            if (extendedConfig == null && baseConfig == null) {
                return OptionsBundle.emptyBundle();
            }
            if (baseConfig != null) {
                mutableOptionsBundleCreate = MutableOptionsBundle.from(baseConfig);
            } else {
                mutableOptionsBundleCreate = MutableOptionsBundle.create();
            }
            if (extendedConfig != null) {
                for (Option<?> option : extendedConfig.listOptions()) {
                    mutableOptionsBundleCreate.insertOption(option, extendedConfig.getOptionPriority(option), extendedConfig.retrieveOption(option));
                }
            }
            return OptionsBundle.from(mutableOptionsBundleCreate);
        }
    }
}
