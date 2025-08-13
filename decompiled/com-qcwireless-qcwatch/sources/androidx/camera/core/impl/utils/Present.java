package androidx.camera.core.impl.utils;

import androidx.core.util.Preconditions;
import androidx.core.util.Supplier;

/* loaded from: classes.dex */
final class Present<T> extends Optional<T> {
    private static final long serialVersionUID = 0;
    private final T mReference;

    @Override // androidx.camera.core.impl.utils.Optional
    public boolean isPresent() {
        return true;
    }

    Present(T reference) {
        this.mReference = reference;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public T get() {
        return this.mReference;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public T or(T defaultValue) {
        Preconditions.checkNotNull(defaultValue, "use Optional.orNull() instead of Optional.or(null)");
        return this.mReference;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public Optional<T> or(Optional<? extends T> secondChoice) {
        Preconditions.checkNotNull(secondChoice);
        return this;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public T or(Supplier<? extends T> supplier) {
        Preconditions.checkNotNull(supplier);
        return this.mReference;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public T orNull() {
        return this.mReference;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public boolean equals(Object object) {
        if (object instanceof Present) {
            return this.mReference.equals(((Present) object).mReference);
        }
        return false;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public int hashCode() {
        return this.mReference.hashCode() + 1502476572;
    }

    @Override // androidx.camera.core.impl.utils.Optional
    public String toString() {
        return "Optional.of(" + this.mReference + ")";
    }
}
