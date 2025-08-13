package androidx.camera.core;

import androidx.camera.core.impl.ImageReaderProxy;

/* loaded from: classes.dex */
public interface ImageReaderProxyProvider {
    ImageReaderProxy newInstance(int width, int height, int format, int queueDepth, long usage);
}
