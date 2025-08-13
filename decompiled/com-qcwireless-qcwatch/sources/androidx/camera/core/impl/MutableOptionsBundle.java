package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.camera.core.impl.Config;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes.dex */
public final class MutableOptionsBundle extends OptionsBundle implements MutableConfig {
    private static final Config.OptionPriority DEFAULT_PRIORITY = Config.OptionPriority.OPTIONAL;

    private MutableOptionsBundle(TreeMap<Config.Option<?>, Map<Config.OptionPriority, Object>> persistentOptions) {
        super(persistentOptions);
    }

    public static MutableOptionsBundle create() {
        return new MutableOptionsBundle(new TreeMap(ID_COMPARE));
    }

    public static MutableOptionsBundle from(Config otherConfig) {
        TreeMap treeMap = new TreeMap(ID_COMPARE);
        for (Config.Option<?> option : otherConfig.listOptions()) {
            Set<Config.OptionPriority> priorities = otherConfig.getPriorities(option);
            ArrayMap arrayMap = new ArrayMap();
            for (Config.OptionPriority optionPriority : priorities) {
                arrayMap.put(optionPriority, otherConfig.retrieveOptionWithPriority(option, optionPriority));
            }
            treeMap.put(option, arrayMap);
        }
        return new MutableOptionsBundle(treeMap);
    }

    @Override // androidx.camera.core.impl.MutableConfig
    public <ValueT> ValueT removeOption(Config.Option<ValueT> option) {
        return (ValueT) this.mOptions.remove(option);
    }

    @Override // androidx.camera.core.impl.MutableConfig
    public <ValueT> void insertOption(Config.Option<ValueT> opt, ValueT value) {
        insertOption(opt, DEFAULT_PRIORITY, value);
    }

    @Override // androidx.camera.core.impl.MutableConfig
    public <ValueT> void insertOption(Config.Option<ValueT> opt, Config.OptionPriority priority, ValueT value) {
        Map<Config.OptionPriority, Object> map = this.mOptions.get(opt);
        if (map == null) {
            ArrayMap arrayMap = new ArrayMap();
            this.mOptions.put(opt, arrayMap);
            arrayMap.put(priority, value);
            return;
        }
        Config.OptionPriority optionPriority = (Config.OptionPriority) Collections.min(map.keySet());
        if (!map.get(optionPriority).equals(value) && Config.CC.hasConflict(optionPriority, priority)) {
            throw new IllegalArgumentException("Option values conflicts: " + opt.getId() + ", existing value (" + optionPriority + ")=" + map.get(optionPriority) + ", conflicting (" + priority + ")=" + value);
        }
        map.put(priority, value);
    }
}
