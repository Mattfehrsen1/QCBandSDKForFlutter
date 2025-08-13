package androidx.camera.core;

import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.OperationCanceledException;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
abstract class ImageAnalysisAbstractAnalyzer implements ImageReaderProxy.OnImageAvailableListener {
    private static final String TAG = "ImageAnalysisAnalyzer";
    private final Object mAnalyzerLock = new Object();
    protected boolean mIsAttached = true;
    private volatile int mRelativeRotation;
    private ImageAnalysis.Analyzer mSubscribedAnalyzer;
    private Executor mUserExecutor;

    abstract ImageProxy acquireImage(ImageReaderProxy imageReaderProxy);

    abstract void clearCache();

    abstract void onValidImageAvailable(ImageProxy imageProxy);

    ImageAnalysisAbstractAnalyzer() {
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
    public void onImageAvailable(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy imageProxyAcquireImage = acquireImage(imageReaderProxy);
            if (imageProxyAcquireImage != null) {
                onValidImageAvailable(imageProxyAcquireImage);
            }
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire image.", e);
        }
    }

    ListenableFuture<Void> analyzeImage(final ImageProxy imageProxy) {
        final Executor executor;
        final ImageAnalysis.Analyzer analyzer;
        synchronized (this.mAnalyzerLock) {
            executor = this.mUserExecutor;
            analyzer = this.mSubscribedAnalyzer;
        }
        if (analyzer != null && executor != null) {
            return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.ImageAnalysisAbstractAnalyzer$$ExternalSyntheticLambda0
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m72x9959ff20(executor, imageProxy, analyzer, completer);
                }
            });
        }
        return Futures.immediateFailedFuture(new OperationCanceledException("No analyzer or executor currently set."));
    }

    /* renamed from: lambda$analyzeImage$1$androidx-camera-core-ImageAnalysisAbstractAnalyzer, reason: not valid java name */
    public /* synthetic */ Object m72x9959ff20(Executor executor, final ImageProxy imageProxy, final ImageAnalysis.Analyzer analyzer, final CallbackToFutureAdapter.Completer completer) throws Exception {
        executor.execute(new Runnable() { // from class: androidx.camera.core.ImageAnalysisAbstractAnalyzer$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m71x6b8164c1(imageProxy, analyzer, completer);
            }
        });
        return "analyzeImage";
    }

    /* renamed from: lambda$analyzeImage$0$androidx-camera-core-ImageAnalysisAbstractAnalyzer, reason: not valid java name */
    public /* synthetic */ void m71x6b8164c1(ImageProxy imageProxy, ImageAnalysis.Analyzer analyzer, CallbackToFutureAdapter.Completer completer) {
        if (this.mIsAttached) {
            analyzer.analyze(new SettableImageProxy(imageProxy, ImmutableImageInfo.create(imageProxy.getImageInfo().getTagBundle(), imageProxy.getImageInfo().getTimestamp(), this.mRelativeRotation)));
            completer.set(null);
        } else {
            completer.setException(new OperationCanceledException("ImageAnalysis is detached"));
        }
    }

    void setRelativeRotation(int relativeRotation) {
        this.mRelativeRotation = relativeRotation;
    }

    void setAnalyzer(Executor userExecutor, ImageAnalysis.Analyzer subscribedAnalyzer) {
        synchronized (this.mAnalyzerLock) {
            if (subscribedAnalyzer == null) {
                clearCache();
                this.mSubscribedAnalyzer = subscribedAnalyzer;
                this.mUserExecutor = userExecutor;
            } else {
                this.mSubscribedAnalyzer = subscribedAnalyzer;
                this.mUserExecutor = userExecutor;
            }
        }
    }

    void attach() {
        this.mIsAttached = true;
    }

    void detach() {
        this.mIsAttached = false;
        clearCache();
    }
}
