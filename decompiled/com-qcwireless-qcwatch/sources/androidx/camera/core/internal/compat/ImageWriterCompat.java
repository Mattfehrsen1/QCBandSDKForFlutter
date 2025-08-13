package androidx.camera.core.internal.compat;

import android.media.ImageWriter;
import android.os.Build;
import android.view.Surface;

/* loaded from: classes.dex */
public final class ImageWriterCompat {
    public static ImageWriter newInstance(Surface surface, int maxImages, int format) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ImageWriterCompatApi26Impl.newInstance(surface, maxImages, format);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return ImageWriterCompatApi29Impl.newInstance(surface, maxImages, format);
        }
        throw new RuntimeException("Unable to call newInstance(Surface, int, int) on API " + Build.VERSION.SDK_INT + ". Version 26 or higher required.");
    }

    private ImageWriterCompat() {
    }
}
