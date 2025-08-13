package androidx.camera.core;

import android.media.Image;
import android.media.ImageReader;
import android.view.Surface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class AndroidImageReaderProxy implements ImageReaderProxy {
    private final ImageReader mImageReader;

    AndroidImageReaderProxy(ImageReader imageReader) {
        this.mImageReader = imageReader;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized ImageProxy acquireLatestImage() {
        Image imageAcquireLatestImage;
        try {
            imageAcquireLatestImage = this.mImageReader.acquireLatestImage();
        } catch (RuntimeException e) {
            if (!isImageReaderContextNotInitializedException(e)) {
                throw e;
            }
            imageAcquireLatestImage = null;
        }
        if (imageAcquireLatestImage == null) {
            return null;
        }
        return new AndroidImageProxy(imageAcquireLatestImage);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized ImageProxy acquireNextImage() {
        Image imageAcquireNextImage;
        try {
            imageAcquireNextImage = this.mImageReader.acquireNextImage();
        } catch (RuntimeException e) {
            if (!isImageReaderContextNotInitializedException(e)) {
                throw e;
            }
            imageAcquireNextImage = null;
        }
        if (imageAcquireNextImage == null) {
            return null;
        }
        return new AndroidImageProxy(imageAcquireNextImage);
    }

    private boolean isImageReaderContextNotInitializedException(RuntimeException e) {
        return "ImageReaderContext is not initialized".equals(e.getMessage());
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized void close() {
        this.mImageReader.close();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getHeight() {
        return this.mImageReader.getHeight();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getWidth() {
        return this.mImageReader.getWidth();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getImageFormat() {
        return this.mImageReader.getImageFormat();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized int getMaxImages() {
        return this.mImageReader.getMaxImages();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized Surface getSurface() {
        return this.mImageReader.getSurface();
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized void setOnImageAvailableListener(final ImageReaderProxy.OnImageAvailableListener listener, final Executor executor) {
        this.mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: androidx.camera.core.AndroidImageReaderProxy$$ExternalSyntheticLambda0
            @Override // android.media.ImageReader.OnImageAvailableListener
            public final void onImageAvailable(ImageReader imageReader) {
                this.f$0.m63x2fd48a6(executor, listener, imageReader);
            }
        }, MainThreadAsyncHandler.getInstance());
    }

    /* renamed from: lambda$setOnImageAvailableListener$0$androidx-camera-core-AndroidImageReaderProxy, reason: not valid java name */
    public /* synthetic */ void m62x4985bb07(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        onImageAvailableListener.onImageAvailable(this);
    }

    /* renamed from: lambda$setOnImageAvailableListener$1$androidx-camera-core-AndroidImageReaderProxy, reason: not valid java name */
    public /* synthetic */ void m63x2fd48a6(Executor executor, final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, ImageReader imageReader) {
        executor.execute(new Runnable() { // from class: androidx.camera.core.AndroidImageReaderProxy$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m62x4985bb07(onImageAvailableListener);
            }
        });
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public synchronized void clearOnImageAvailableListener() {
        this.mImageReader.setOnImageAvailableListener(null, null);
    }
}
