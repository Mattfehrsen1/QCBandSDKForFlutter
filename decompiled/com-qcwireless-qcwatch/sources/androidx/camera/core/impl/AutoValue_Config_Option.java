package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_Config_Option<T> extends Config.Option<T> {
    private final String id;
    private final Object token;
    private final Class<T> valueClass;

    AutoValue_Config_Option(String id, Class<T> valueClass, Object token) {
        Objects.requireNonNull(id, "Null id");
        this.id = id;
        Objects.requireNonNull(valueClass, "Null valueClass");
        this.valueClass = valueClass;
        this.token = token;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public String getId() {
        return this.id;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public Class<T> getValueClass() {
        return this.valueClass;
    }

    @Override // androidx.camera.core.impl.Config.Option
    public Object getToken() {
        return this.token;
    }

    public String toString() {
        return "Option{id=" + this.id + ", valueClass=" + this.valueClass + ", token=" + this.token + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Config.Option)) {
            return false;
        }
        Config.Option option = (Config.Option) o;
        if (this.id.equals(option.getId()) && this.valueClass.equals(option.getValueClass())) {
            Object obj = this.token;
            if (obj == null) {
                if (option.getToken() == null) {
                    return true;
                }
            } else if (obj.equals(option.getToken())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (((this.id.hashCode() ^ 1000003) * 1000003) ^ this.valueClass.hashCode()) * 1000003;
        Object obj = this.token;
        return iHashCode ^ (obj == null ? 0 : obj.hashCode());
    }
}
