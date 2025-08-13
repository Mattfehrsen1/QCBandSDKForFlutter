package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;

/* loaded from: classes.dex */
public interface MutableConfig extends Config {
    <ValueT> void insertOption(Config.Option<ValueT> opt, Config.OptionPriority priority, ValueT value);

    <ValueT> void insertOption(Config.Option<ValueT> opt, ValueT value);

    <ValueT> ValueT removeOption(Config.Option<ValueT> opt);
}
