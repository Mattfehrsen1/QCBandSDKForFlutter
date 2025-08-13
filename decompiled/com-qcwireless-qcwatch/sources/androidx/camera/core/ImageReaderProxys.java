package androidx.camera.core;

import android.media.ImageReader;
import androidx.camera.core.impl.ImageReaderProxy;

/* loaded from: classes.dex */
final class ImageReaderProxys {
    private ImageReaderProxys() {
    }

    static ImageReaderProxy createIsolatedReader(int width, int height, int format, int maxImages) {
        return new AndroidImageReaderProxy(ImageReader.newInstance(width, height, format, maxImages));
    }
}
