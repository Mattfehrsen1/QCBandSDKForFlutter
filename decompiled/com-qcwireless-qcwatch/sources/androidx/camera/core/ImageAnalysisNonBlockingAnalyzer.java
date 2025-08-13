package androidx.camera.core;

import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class ImageAnalysisNonBlockingAnalyzer extends ImageAnalysisAbstractAnalyzer {
    final Executor mBackgroundExecutor;
    ImageProxy mCachedImage;
    private final Object mLock = new Object();
    private CacheAnalyzingImageProxy mPostedImage;

    ImageAnalysisNonBlockingAnalyzer(Executor executor) {
        this.mBackgroundExecutor = executor;
    }

    @Override // androidx.camera.core.ImageAnalysisAbstractAnalyzer
    ImageProxy acquireImage(ImageReaderProxy imageReaderProxy) {
        return imageReaderProxy.acquireLatestImage();
    }

    @Override // androidx.camera.core.ImageAnalysisAbstractAnalyzer
    void onValidImageAvailable(ImageProxy imageProxy) {
        synchronized (this.mLock) {
            if (!this.mIsAttached) {
                imageProxy.close();
                return;
            }
            if (this.mPostedImage == null) {
                final CacheAnalyzingImageProxy cacheAnalyzingImageProxy = new CacheAnalyzingImageProxy(imageProxy, this);
                this.mPostedImage = cacheAnalyzingImageProxy;
                Futures.addCallback(analyzeImage(cacheAnalyzingImageProxy), new FutureCallback<Void>() { // from class: androidx.camera.core.ImageAnalysisNonBlockingAnalyzer.1
                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onSuccess(Void result) {
                    }

                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onFailure(Throwable t) {
                        cacheAnalyzingImageProxy.close();
                    }
                }, CameraXExecutors.directExecutor());
            } else {
                if (imageProxy.getImageInfo().getTimestamp() <= this.mPostedImage.getImageInfo().getTimestamp()) {
                    imageProxy.close();
                } else {
                    ImageProxy imageProxy2 = this.mCachedImage;
                    if (imageProxy2 != null) {
                        imageProxy2.close();
                    }
                    this.mCachedImage = imageProxy;
                }
            }
        }
    }

    @Override // androidx.camera.core.ImageAnalysisAbstractAnalyzer
    void clearCache() {
        synchronized (this.mLock) {
            ImageProxy imageProxy = this.mCachedImage;
            if (imageProxy != null) {
                imageProxy.close();
                this.mCachedImage = null;
            }
        }
    }

    void analyzeCachedImage() {
        synchronized (this.mLock) {
            this.mPostedImage = null;
            ImageProxy imageProxy = this.mCachedImage;
            if (imageProxy != null) {
                this.mCachedImage = null;
                onValidImageAvailable(imageProxy);
            }
        }
    }

    static class CacheAnalyzingImageProxy extends ForwardingImageProxy {
        final WeakReference<ImageAnalysisNonBlockingAnalyzer> mNonBlockingAnalyzerWeakReference;

        CacheAnalyzingImageProxy(ImageProxy image, ImageAnalysisNonBlockingAnalyzer nonBlockingAnalyzer) {
            super(image);
            this.mNonBlockingAnalyzerWeakReference = new WeakReference<>(nonBlockingAnalyzer);
            addOnImageCloseListener(new ForwardingImageProxy.OnImageCloseListener() { // from class: androidx.camera.core.ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy$$ExternalSyntheticLambda0
                @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
                public final void onImageClose(ImageProxy imageProxy) {
                    this.f$0.m73x3a140058(imageProxy);
                }
            });
        }

        /* renamed from: lambda$new$0$androidx-camera-core-ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy, reason: not valid java name */
        public /* synthetic */ void m73x3a140058(ImageProxy imageProxy) {
            final ImageAnalysisNonBlockingAnalyzer imageAnalysisNonBlockingAnalyzer = this.mNonBlockingAnalyzerWeakReference.get();
            if (imageAnalysisNonBlockingAnalyzer != null) {
                Executor executor = imageAnalysisNonBlockingAnalyzer.mBackgroundExecutor;
                Objects.requireNonNull(imageAnalysisNonBlockingAnalyzer);
                executor.execute(new Runnable() { // from class: androidx.camera.core.ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        imageAnalysisNonBlockingAnalyzer.analyzeCachedImage();
                    }
                });
            }
        }
    }
}
