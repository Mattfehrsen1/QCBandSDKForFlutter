package androidx.camera.core;

import android.media.ImageReader;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class ProcessingImageReader implements ImageReaderProxy {
    private static final String TAG = "ProcessingImageReader";
    private final List<Integer> mCaptureIdList;
    final CaptureProcessor mCaptureProcessor;
    private FutureCallback<List<ImageProxy>> mCaptureStageReadyCallback;
    CallbackToFutureAdapter.Completer<Void> mCloseCompleter;
    private ListenableFuture<Void> mCloseFuture;
    boolean mClosed;
    Executor mExecutor;
    private ImageReaderProxy.OnImageAvailableListener mImageProcessedListener;
    final MetadataImageReader mInputImageReader;
    ImageReaderProxy.OnImageAvailableListener mListener;
    final Object mLock;
    final ImageReaderProxy mOutputImageReader;
    final Executor mPostProcessExecutor;
    boolean mProcessing;
    SettableImageProxyBundle mSettableImageProxyBundle;
    private String mTagBundleKey;
    private ImageReaderProxy.OnImageAvailableListener mTransformedListener;

    /* renamed from: androidx.camera.core.ProcessingImageReader$2, reason: invalid class name */
    class AnonymousClass2 implements ImageReaderProxy.OnImageAvailableListener {
        AnonymousClass2() {
        }

        @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
        public void onImageAvailable(ImageReaderProxy reader) {
            final ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
            Executor executor;
            synchronized (ProcessingImageReader.this.mLock) {
                onImageAvailableListener = ProcessingImageReader.this.mListener;
                executor = ProcessingImageReader.this.mExecutor;
                ProcessingImageReader.this.mSettableImageProxyBundle.reset();
                ProcessingImageReader.this.setupSettableImageProxyBundleCallbacks();
            }
            if (onImageAvailableListener != null) {
                if (executor != null) {
                    executor.execute(new Runnable() { // from class: androidx.camera.core.ProcessingImageReader$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m95x3c329d5d(onImageAvailableListener);
                        }
                    });
                } else {
                    onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
                }
            }
        }

        /* renamed from: lambda$onImageAvailable$0$androidx-camera-core-ProcessingImageReader$2, reason: not valid java name */
        public /* synthetic */ void m95x3c329d5d(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
            onImageAvailableListener.onImageAvailable(ProcessingImageReader.this);
        }
    }

    ProcessingImageReader(int width, int height, int format, int maxImages, Executor postProcessExecutor, CaptureBundle captureBundle, CaptureProcessor captureProcessor) {
        this(width, height, format, maxImages, postProcessExecutor, captureBundle, captureProcessor, format);
    }

    ProcessingImageReader(int width, int height, int inputFormat, int maxImages, Executor postProcessExecutor, CaptureBundle captureBundle, CaptureProcessor captureProcessor, int outputFormat) {
        this(new MetadataImageReader(width, height, inputFormat, maxImages), postProcessExecutor, captureBundle, captureProcessor, outputFormat);
    }

    ProcessingImageReader(MetadataImageReader imageReader, Executor postProcExecutor, CaptureBundle capBundle, CaptureProcessor capProcessor) {
        this(imageReader, postProcExecutor, capBundle, capProcessor, imageReader.getImageFormat());
    }

    ProcessingImageReader(MetadataImageReader imageReader, Executor postProcessExecutor, CaptureBundle captureBundle, CaptureProcessor captureProcessor, int outputFormat) {
        this.mLock = new Object();
        this.mTransformedListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.ProcessingImageReader.1
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public void onImageAvailable(ImageReaderProxy reader) {
                ProcessingImageReader.this.imageIncoming(reader);
            }
        };
        this.mImageProcessedListener = new AnonymousClass2();
        this.mCaptureStageReadyCallback = new FutureCallback<List<ImageProxy>>() { // from class: androidx.camera.core.ProcessingImageReader.3
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable throwable) {
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(List<ImageProxy> imageProxyList) {
                synchronized (ProcessingImageReader.this.mLock) {
                    if (ProcessingImageReader.this.mClosed) {
                        return;
                    }
                    ProcessingImageReader.this.mProcessing = true;
                    ProcessingImageReader.this.mCaptureProcessor.process(ProcessingImageReader.this.mSettableImageProxyBundle);
                    synchronized (ProcessingImageReader.this.mLock) {
                        ProcessingImageReader.this.mProcessing = false;
                        if (ProcessingImageReader.this.mClosed) {
                            ProcessingImageReader.this.mInputImageReader.close();
                            ProcessingImageReader.this.mSettableImageProxyBundle.close();
                            ProcessingImageReader.this.mOutputImageReader.close();
                            if (ProcessingImageReader.this.mCloseCompleter != null) {
                                ProcessingImageReader.this.mCloseCompleter.set(null);
                            }
                        }
                    }
                }
            }
        };
        this.mClosed = false;
        this.mProcessing = false;
        this.mTagBundleKey = new String();
        this.mSettableImageProxyBundle = new SettableImageProxyBundle(Collections.emptyList(), this.mTagBundleKey);
        this.mCaptureIdList = new ArrayList();
        if (imageReader.getMaxImages() < captureBundle.getCaptureStages().size()) {
            throw new IllegalArgumentException("MetadataImageReader is smaller than CaptureBundle.");
        }
        this.mInputImageReader = imageReader;
        int width = imageReader.getWidth();
        int height = imageReader.getHeight();
        if (outputFormat == 256) {
            width = imageReader.getWidth() * imageReader.getHeight();
            height = 1;
        }
        AndroidImageReaderProxy androidImageReaderProxy = new AndroidImageReaderProxy(ImageReader.newInstance(width, height, outputFormat, imageReader.getMaxImages()));
        this.mOutputImageReader = androidImageReaderProxy;
        this.mPostProcessExecutor = postProcessExecutor;
        this.mCaptureProcessor = captureProcessor;
        captureProcessor.onOutputSurface(androidImageReaderProxy.getSurface(), outputFormat);
        captureProcessor.onResolutionUpdate(new Size(imageReader.getWidth(), imageReader.getHeight()));
        setCaptureBundle(captureBundle);
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireLatestImage() {
        ImageProxy imageProxyAcquireLatestImage;
        synchronized (this.mLock) {
            imageProxyAcquireLatestImage = this.mOutputImageReader.acquireLatestImage();
        }
        return imageProxyAcquireLatestImage;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public ImageProxy acquireNextImage() {
        ImageProxy imageProxyAcquireNextImage;
        synchronized (this.mLock) {
            imageProxyAcquireNextImage = this.mOutputImageReader.acquireNextImage();
        }
        return imageProxyAcquireNextImage;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void close() {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            this.mOutputImageReader.clearOnImageAvailableListener();
            if (!this.mProcessing) {
                this.mInputImageReader.close();
                this.mSettableImageProxyBundle.close();
                this.mOutputImageReader.close();
                CallbackToFutureAdapter.Completer<Void> completer = this.mCloseCompleter;
                if (completer != null) {
                    completer.set(null);
                }
            }
            this.mClosed = true;
        }
    }

    ListenableFuture<Void> getCloseFuture() {
        ListenableFuture<Void> listenableFutureNonCancellationPropagating;
        synchronized (this.mLock) {
            if (this.mClosed && !this.mProcessing) {
                listenableFutureNonCancellationPropagating = Futures.immediateFuture(null);
            } else {
                if (this.mCloseFuture == null) {
                    this.mCloseFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.ProcessingImageReader$$ExternalSyntheticLambda0
                        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                        public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                            return this.f$0.m94x6bf6f317(completer);
                        }
                    });
                }
                listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(this.mCloseFuture);
            }
        }
        return listenableFutureNonCancellationPropagating;
    }

    /* renamed from: lambda$getCloseFuture$0$androidx-camera-core-ProcessingImageReader, reason: not valid java name */
    public /* synthetic */ Object m94x6bf6f317(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.mLock) {
            this.mCloseCompleter = completer;
        }
        return "ProcessingImageReader-close";
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getHeight() {
        int height;
        synchronized (this.mLock) {
            height = this.mInputImageReader.getHeight();
        }
        return height;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getWidth() {
        int width;
        synchronized (this.mLock) {
            width = this.mInputImageReader.getWidth();
        }
        return width;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getImageFormat() {
        int imageFormat;
        synchronized (this.mLock) {
            imageFormat = this.mOutputImageReader.getImageFormat();
        }
        return imageFormat;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public int getMaxImages() {
        int maxImages;
        synchronized (this.mLock) {
            maxImages = this.mInputImageReader.getMaxImages();
        }
        return maxImages;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public Surface getSurface() {
        Surface surface;
        synchronized (this.mLock) {
            surface = this.mInputImageReader.getSurface();
        }
        return surface;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener listener, Executor executor) {
        synchronized (this.mLock) {
            this.mListener = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(listener);
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
            this.mInputImageReader.setOnImageAvailableListener(this.mTransformedListener, executor);
            this.mOutputImageReader.setOnImageAvailableListener(this.mImageProcessedListener, executor);
        }
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy
    public void clearOnImageAvailableListener() {
        synchronized (this.mLock) {
            this.mListener = null;
            this.mExecutor = null;
            this.mInputImageReader.clearOnImageAvailableListener();
            this.mOutputImageReader.clearOnImageAvailableListener();
            if (!this.mProcessing) {
                this.mSettableImageProxyBundle.close();
            }
        }
    }

    public void setCaptureBundle(CaptureBundle captureBundle) {
        synchronized (this.mLock) {
            if (captureBundle.getCaptureStages() != null) {
                if (this.mInputImageReader.getMaxImages() < captureBundle.getCaptureStages().size()) {
                    throw new IllegalArgumentException("CaptureBundle is larger than InputImageReader.");
                }
                this.mCaptureIdList.clear();
                for (CaptureStage captureStage : captureBundle.getCaptureStages()) {
                    if (captureStage != null) {
                        this.mCaptureIdList.add(Integer.valueOf(captureStage.getId()));
                    }
                }
            }
            String string = Integer.toString(captureBundle.hashCode());
            this.mTagBundleKey = string;
            this.mSettableImageProxyBundle = new SettableImageProxyBundle(this.mCaptureIdList, string);
            setupSettableImageProxyBundleCallbacks();
        }
    }

    public String getTagBundleKey() {
        return this.mTagBundleKey;
    }

    CameraCaptureCallback getCameraCaptureCallback() {
        CameraCaptureCallback cameraCaptureCallback;
        synchronized (this.mLock) {
            cameraCaptureCallback = this.mInputImageReader.getCameraCaptureCallback();
        }
        return cameraCaptureCallback;
    }

    void setupSettableImageProxyBundleCallbacks() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.mCaptureIdList.iterator();
        while (it.hasNext()) {
            arrayList.add(this.mSettableImageProxyBundle.getImageProxy(it.next().intValue()));
        }
        Futures.addCallback(Futures.allAsList(arrayList), this.mCaptureStageReadyCallback, this.mPostProcessExecutor);
    }

    void imageIncoming(ImageReaderProxy imageReader) {
        synchronized (this.mLock) {
            if (this.mClosed) {
                return;
            }
            try {
                ImageProxy imageProxyAcquireNextImage = imageReader.acquireNextImage();
                if (imageProxyAcquireNextImage != null) {
                    Integer tag = imageProxyAcquireNextImage.getImageInfo().getTagBundle().getTag(this.mTagBundleKey);
                    if (!this.mCaptureIdList.contains(tag)) {
                        Logger.w(TAG, "ImageProxyBundle does not contain this id: " + tag);
                        imageProxyAcquireNextImage.close();
                    } else {
                        this.mSettableImageProxyBundle.addImageProxy(imageProxyAcquireNextImage);
                    }
                }
            } catch (IllegalStateException e) {
                Logger.e(TAG, "Failed to acquire latest image.", e);
            }
        }
    }
}
