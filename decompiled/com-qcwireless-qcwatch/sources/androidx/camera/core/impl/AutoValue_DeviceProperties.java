package androidx.camera.core.impl;

import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_DeviceProperties extends DeviceProperties {
    private final String manufacturer;
    private final String model;
    private final int sdkVersion;

    AutoValue_DeviceProperties(String manufacturer, String model, int sdkVersion) {
        Objects.requireNonNull(manufacturer, "Null manufacturer");
        this.manufacturer = manufacturer;
        Objects.requireNonNull(model, "Null model");
        this.model = model;
        this.sdkVersion = sdkVersion;
    }

    @Override // androidx.camera.core.impl.DeviceProperties
    public String manufacturer() {
        return this.manufacturer;
    }

    @Override // androidx.camera.core.impl.DeviceProperties
    public String model() {
        return this.model;
    }

    @Override // androidx.camera.core.impl.DeviceProperties
    public int sdkVersion() {
        return this.sdkVersion;
    }

    public String toString() {
        return "DeviceProperties{manufacturer=" + this.manufacturer + ", model=" + this.model + ", sdkVersion=" + this.sdkVersion + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeviceProperties)) {
            return false;
        }
        DeviceProperties deviceProperties = (DeviceProperties) o;
        return this.manufacturer.equals(deviceProperties.manufacturer()) && this.model.equals(deviceProperties.model()) && this.sdkVersion == deviceProperties.sdkVersion();
    }

    public int hashCode() {
        return ((((this.manufacturer.hashCode() ^ 1000003) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.sdkVersion;
    }
}
