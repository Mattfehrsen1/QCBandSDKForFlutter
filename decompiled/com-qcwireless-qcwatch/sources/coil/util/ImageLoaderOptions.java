package coil.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: ImageLoaderOptions.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ.\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0010"}, d2 = {"Lcoil/util/ImageLoaderOptions;", "", "addLastModifiedToFileCacheKey", "", "networkObserverEnabled", "respectCacheHeaders", "bitmapFactoryMaxParallelism", "", "(ZZZI)V", "getAddLastModifiedToFileCacheKey", "()Z", "getBitmapFactoryMaxParallelism", "()I", "getNetworkObserverEnabled", "getRespectCacheHeaders", "copy", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageLoaderOptions {
    private final boolean addLastModifiedToFileCacheKey;
    private final int bitmapFactoryMaxParallelism;
    private final boolean networkObserverEnabled;
    private final boolean respectCacheHeaders;

    public ImageLoaderOptions() {
        this(false, false, false, 0, 15, null);
    }

    public ImageLoaderOptions(boolean z, boolean z2, boolean z3, int i) {
        this.addLastModifiedToFileCacheKey = z;
        this.networkObserverEnabled = z2;
        this.respectCacheHeaders = z3;
        this.bitmapFactoryMaxParallelism = i;
    }

    public /* synthetic */ ImageLoaderOptions(boolean z, boolean z2, boolean z3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? true : z, (i2 & 2) != 0 ? true : z2, (i2 & 4) != 0 ? true : z3, (i2 & 8) != 0 ? 4 : i);
    }

    public final boolean getAddLastModifiedToFileCacheKey() {
        return this.addLastModifiedToFileCacheKey;
    }

    public final boolean getNetworkObserverEnabled() {
        return this.networkObserverEnabled;
    }

    public final boolean getRespectCacheHeaders() {
        return this.respectCacheHeaders;
    }

    public final int getBitmapFactoryMaxParallelism() {
        return this.bitmapFactoryMaxParallelism;
    }

    public static /* synthetic */ ImageLoaderOptions copy$default(ImageLoaderOptions imageLoaderOptions, boolean z, boolean z2, boolean z3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = imageLoaderOptions.addLastModifiedToFileCacheKey;
        }
        if ((i2 & 2) != 0) {
            z2 = imageLoaderOptions.networkObserverEnabled;
        }
        if ((i2 & 4) != 0) {
            z3 = imageLoaderOptions.respectCacheHeaders;
        }
        if ((i2 & 8) != 0) {
            i = imageLoaderOptions.bitmapFactoryMaxParallelism;
        }
        return imageLoaderOptions.copy(z, z2, z3, i);
    }

    public final ImageLoaderOptions copy(boolean addLastModifiedToFileCacheKey, boolean networkObserverEnabled, boolean respectCacheHeaders, int bitmapFactoryMaxParallelism) {
        return new ImageLoaderOptions(addLastModifiedToFileCacheKey, networkObserverEnabled, respectCacheHeaders, bitmapFactoryMaxParallelism);
    }
}
