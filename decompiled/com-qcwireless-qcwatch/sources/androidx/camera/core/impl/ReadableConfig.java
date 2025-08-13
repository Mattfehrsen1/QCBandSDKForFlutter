package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.Set;

/* loaded from: classes.dex */
public interface ReadableConfig extends Config {
    @Override // androidx.camera.core.impl.Config
    boolean containsOption(Config.Option<?> id);

    @Override // androidx.camera.core.impl.Config
    void findOptions(String idSearchString, Config.OptionMatcher matcher);

    Config getConfig();

    @Override // androidx.camera.core.impl.Config
    Config.OptionPriority getOptionPriority(Config.Option<?> opt);

    @Override // androidx.camera.core.impl.Config
    Set<Config.OptionPriority> getPriorities(Config.Option<?> option);

    @Override // androidx.camera.core.impl.Config
    Set<Config.Option<?>> listOptions();

    @Override // androidx.camera.core.impl.Config
    <ValueT> ValueT retrieveOption(Config.Option<ValueT> id);

    @Override // androidx.camera.core.impl.Config
    <ValueT> ValueT retrieveOption(Config.Option<ValueT> id, ValueT valueIfMissing);

    @Override // androidx.camera.core.impl.Config
    <ValueT> ValueT retrieveOptionWithPriority(Config.Option<ValueT> id, Config.OptionPriority priority);

    /* renamed from: androidx.camera.core.impl.ReadableConfig$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
