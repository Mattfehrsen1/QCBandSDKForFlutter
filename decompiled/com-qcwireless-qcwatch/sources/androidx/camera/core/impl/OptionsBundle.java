package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.camera.core.impl.Config;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class OptionsBundle implements Config {
    private static final OptionsBundle EMPTY_BUNDLE;
    protected static final Comparator<Config.Option<?>> ID_COMPARE;
    protected final TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> mOptions;

    static {
        OptionsBundle$$ExternalSyntheticLambda0 optionsBundle$$ExternalSyntheticLambda0 = new Comparator() { // from class: androidx.camera.core.impl.OptionsBundle$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Config.Option) obj).getId().compareTo(((Config.Option) obj2).getId());
            }
        };
        ID_COMPARE = optionsBundle$$ExternalSyntheticLambda0;
        EMPTY_BUNDLE = new OptionsBundle(new TreeMap(optionsBundle$$ExternalSyntheticLambda0));
    }

    OptionsBundle(TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> options) {
        this.mOptions = options;
    }

    public static OptionsBundle from(Config otherConfig) {
        if (OptionsBundle.class.equals(otherConfig.getClass())) {
            return (OptionsBundle) otherConfig;
        }
        TreeMap treeMap = new TreeMap(ID_COMPARE);
        for (Config.Option<?> option : otherConfig.listOptions()) {
            Set<Config.OptionPriority> priorities = otherConfig.getPriorities(option);
            ArrayMap arrayMap = new ArrayMap();
            for (Config.OptionPriority optionPriority : priorities) {
                arrayMap.put(optionPriority, otherConfig.retrieveOptionWithPriority(option, optionPriority));
            }
            treeMap.put(option, arrayMap);
        }
        return new OptionsBundle(treeMap);
    }

    public static OptionsBundle emptyBundle() {
        return EMPTY_BUNDLE;
    }

    @Override // androidx.camera.core.impl.Config
    public Set<Config.Option<?>> listOptions() {
        return Collections.unmodifiableSet(this.mOptions.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    public boolean containsOption(Config.Option<?> id) {
        return this.mOptions.containsKey(id);
    }

    @Override // androidx.camera.core.impl.Config
    public <ValueT> ValueT retrieveOption(Config.Option<ValueT> option) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(option);
        if (map == null) {
            throw new IllegalArgumentException("Option does not exist: " + option);
        }
        return (ValueT) map.get((Config.OptionPriority) Collections.min(map.keySet()));
    }

    @Override // androidx.camera.core.impl.Config
    public <ValueT> ValueT retrieveOption(Config.Option<ValueT> option, ValueT valuet) {
        try {
            return (ValueT) retrieveOption(option);
        } catch (IllegalArgumentException unused) {
            return valuet;
        }
    }

    @Override // androidx.camera.core.impl.Config
    public <ValueT> ValueT retrieveOptionWithPriority(Config.Option<ValueT> option, Config.OptionPriority optionPriority) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(option);
        if (map == null) {
            throw new IllegalArgumentException("Option does not exist: " + option);
        }
        if (!map.containsKey(optionPriority)) {
            throw new IllegalArgumentException("Option does not exist: " + option + " with priority=" + optionPriority);
        }
        return (ValueT) map.get(optionPriority);
    }

    @Override // androidx.camera.core.impl.Config
    public Config.OptionPriority getOptionPriority(Config.Option<?> opt) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(opt);
        if (map == null) {
            throw new IllegalArgumentException("Option does not exist: " + opt);
        }
        return (Config.OptionPriority) Collections.min(map.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    public void findOptions(String idStem, Config.OptionMatcher matcher) {
        for (Map.Entry<Config.Option<?>, Map<Config.OptionPriority, Object>> entry : this.mOptions.tailMap(Config.Option.create(idStem, Void.class)).entrySet()) {
            if (!entry.getKey().getId().startsWith(idStem) || !matcher.onOptionMatched(entry.getKey())) {
                return;
            }
        }
    }

    @Override // androidx.camera.core.impl.Config
    public Set<Config.OptionPriority> getPriorities(Config.Option<?> opt) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(opt);
        if (map == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(map.keySet());
    }
}
