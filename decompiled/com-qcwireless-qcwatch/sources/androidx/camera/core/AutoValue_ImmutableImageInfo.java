package androidx.camera.core;

import androidx.camera.core.impl.TagBundle;
import java.util.Objects;

/* loaded from: classes.dex */
final class AutoValue_ImmutableImageInfo extends ImmutableImageInfo {
    private final int rotationDegrees;
    private final TagBundle tagBundle;
    private final long timestamp;

    AutoValue_ImmutableImageInfo(TagBundle tagBundle, long timestamp, int rotationDegrees) {
        Objects.requireNonNull(tagBundle, "Null tagBundle");
        this.tagBundle = tagBundle;
        this.timestamp = timestamp;
        this.rotationDegrees = rotationDegrees;
    }

    @Override // androidx.camera.core.ImmutableImageInfo, androidx.camera.core.ImageInfo
    public TagBundle getTagBundle() {
        return this.tagBundle;
    }

    @Override // androidx.camera.core.ImmutableImageInfo, androidx.camera.core.ImageInfo
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // androidx.camera.core.ImmutableImageInfo, androidx.camera.core.ImageInfo
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public String toString() {
        return "ImmutableImageInfo{tagBundle=" + this.tagBundle + ", timestamp=" + this.timestamp + ", rotationDegrees=" + this.rotationDegrees + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ImmutableImageInfo)) {
            return false;
        }
        ImmutableImageInfo immutableImageInfo = (ImmutableImageInfo) o;
        return this.tagBundle.equals(immutableImageInfo.getTagBundle()) && this.timestamp == immutableImageInfo.getTimestamp() && this.rotationDegrees == immutableImageInfo.getRotationDegrees();
    }

    public int hashCode() {
        int iHashCode = (this.tagBundle.hashCode() ^ 1000003) * 1000003;
        long j = this.timestamp;
        return ((iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.rotationDegrees;
    }
}
