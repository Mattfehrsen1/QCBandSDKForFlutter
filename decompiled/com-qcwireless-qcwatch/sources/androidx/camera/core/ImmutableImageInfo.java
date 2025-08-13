package androidx.camera.core;

import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.ExifData;

/* loaded from: classes.dex */
abstract class ImmutableImageInfo implements ImageInfo {
    @Override // androidx.camera.core.ImageInfo
    public abstract int getRotationDegrees();

    @Override // androidx.camera.core.ImageInfo
    public abstract TagBundle getTagBundle();

    @Override // androidx.camera.core.ImageInfo
    public abstract long getTimestamp();

    ImmutableImageInfo() {
    }

    public static ImageInfo create(TagBundle tag, long timestamp, int rotationDegrees) {
        return new AutoValue_ImmutableImageInfo(tag, timestamp, rotationDegrees);
    }

    @Override // androidx.camera.core.ImageInfo
    public void populateExifData(ExifData.Builder exifBuilder) {
        exifBuilder.setOrientationDegrees(getRotationDegrees());
    }
}
