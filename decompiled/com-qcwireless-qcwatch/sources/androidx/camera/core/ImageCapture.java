package androidx.camera.core;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.arch.core.util.Function;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageSaver;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.IoConfig;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.YuvToJpegProcessor;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.ImageCaptureWashedOutImageQuirk;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class ImageCapture extends UseCase {
    public static final int CAPTURE_MODE_MAXIMIZE_QUALITY = 0;
    public static final int CAPTURE_MODE_MINIMIZE_LATENCY = 1;
    private static final long CHECK_3A_TIMEOUT_IN_MS = 1000;
    private static final int DEFAULT_CAPTURE_MODE = 1;
    public static final Defaults DEFAULT_CONFIG = new Defaults();
    private static final int DEFAULT_FLASH_MODE = 2;
    public static final int ERROR_CAMERA_CLOSED = 3;
    public static final int ERROR_CAPTURE_FAILED = 2;
    public static final int ERROR_FILE_IO = 1;
    public static final int ERROR_INVALID_CAMERA = 4;
    public static final int ERROR_UNKNOWN = 0;
    public static final int FLASH_MODE_AUTO = 0;
    public static final int FLASH_MODE_OFF = 2;
    public static final int FLASH_MODE_ON = 1;
    private static final int FLASH_MODE_UNKNOWN = -1;
    private static final byte JPEG_QUALITY_MAXIMIZE_QUALITY_MODE = 100;
    private static final byte JPEG_QUALITY_MINIMIZE_LATENCY_MODE = 95;
    private static final int MAX_IMAGES = 2;
    private static final String TAG = "ImageCapture";
    private CaptureBundle mCaptureBundle;
    private CaptureConfig mCaptureConfig;
    private final int mCaptureMode;
    private CaptureProcessor mCaptureProcessor;
    private final ImageReaderProxy.OnImageAvailableListener mClosingListener;
    private Rational mCropAspectRatio;
    private DeferrableSurface mDeferrableSurface;
    private final boolean mEnableCheck3AConverged;
    private ExecutorService mExecutor;
    private int mFlashMode;
    private ImageCaptureRequestProcessor mImageCaptureRequestProcessor;
    SafeCloseImageReaderProxy mImageReader;
    final Executor mIoExecutor;
    private final AtomicReference<Integer> mLockedFlashMode;
    private int mMaxCaptureStages;
    private CameraCaptureCallback mMetadataMatchingCaptureCallback;
    ProcessingImageReader mProcessingImageReader;
    final Executor mSequentialIoExecutor;
    private final CaptureCallbackChecker mSessionCallbackChecker;
    SessionConfig.Builder mSessionConfigBuilder;
    private boolean mUseSoftwareJpeg;
    private final boolean mUseTorchFlash;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CaptureMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FlashMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ImageCaptureError {
    }

    public static abstract class OnImageCapturedCallback {
        public void onCaptureSuccess(ImageProxy image) {
        }

        public void onError(final ImageCaptureException exception) {
        }
    }

    public interface OnImageSavedCallback {
        void onError(ImageCaptureException exception);

        void onImageSaved(OutputFileResults outputFileResults);
    }

    static /* synthetic */ void lambda$closeTorch$16() {
    }

    static /* synthetic */ Void lambda$issueTakePicture$20(List list) {
        return null;
    }

    static /* synthetic */ Void lambda$preTakePicture$13(Boolean bool) {
        return null;
    }

    static /* synthetic */ Void lambda$triggerAePrecapture$18(CameraCaptureResult cameraCaptureResult) {
        return null;
    }

    static /* synthetic */ void lambda$triggerAf$17() {
    }

    static /* synthetic */ void lambda$new$0(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy imageProxyAcquireLatestImage = imageReaderProxy.acquireLatestImage();
            try {
                Log.d(TAG, "Discarding ImageProxy which was inadvertently acquired: " + imageProxyAcquireLatestImage);
                if (imageProxyAcquireLatestImage != null) {
                    imageProxyAcquireLatestImage.close();
                }
            } finally {
            }
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to acquire latest image.", e);
        }
    }

    ImageCapture(ImageCaptureConfig userConfig) {
        super(userConfig);
        this.mSessionCallbackChecker = new CaptureCallbackChecker();
        this.mClosingListener = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda16
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                ImageCapture.lambda$new$0(imageReaderProxy);
            }
        };
        this.mLockedFlashMode = new AtomicReference<>(null);
        this.mFlashMode = -1;
        this.mCropAspectRatio = null;
        this.mUseSoftwareJpeg = false;
        ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) getCurrentConfig();
        if (imageCaptureConfig.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)) {
            this.mCaptureMode = imageCaptureConfig.getCaptureMode();
        } else {
            this.mCaptureMode = 1;
        }
        Executor executor = (Executor) Preconditions.checkNotNull(imageCaptureConfig.getIoExecutor(CameraXExecutors.ioExecutor()));
        this.mIoExecutor = executor;
        this.mSequentialIoExecutor = CameraXExecutors.newSequentialExecutor(executor);
        if (this.mCaptureMode == 0) {
            this.mEnableCheck3AConverged = true;
        } else {
            this.mEnableCheck3AConverged = false;
        }
        boolean z = DeviceQuirks.get(ImageCaptureWashedOutImageQuirk.class) != null;
        this.mUseTorchFlash = z;
        if (z) {
            Logger.d(TAG, "Open and close torch to replace the flash fired by flash mode.");
        }
    }

    SessionConfig.Builder createPipeline(final String cameraId, final ImageCaptureConfig config, final Size resolution) {
        CaptureProcessor captureProcessor;
        int i;
        Threads.checkMainThread();
        SessionConfig.Builder builderCreateFrom = SessionConfig.Builder.createFrom(config);
        builderCreateFrom.addRepeatingCameraCaptureCallback(this.mSessionCallbackChecker);
        if (config.getImageReaderProxyProvider() != null) {
            this.mImageReader = new SafeCloseImageReaderProxy(config.getImageReaderProxyProvider().newInstance(resolution.getWidth(), resolution.getHeight(), getImageFormat(), 2, 0L));
            this.mMetadataMatchingCaptureCallback = new CameraCaptureCallback() { // from class: androidx.camera.core.ImageCapture.1
            };
        } else {
            CaptureProcessor captureProcessor2 = this.mCaptureProcessor;
            if (captureProcessor2 != null || this.mUseSoftwareJpeg) {
                final YuvToJpegProcessor yuvToJpegProcessor = null;
                int imageFormat = getImageFormat();
                int imageFormat2 = getImageFormat();
                if (this.mUseSoftwareJpeg) {
                    Preconditions.checkState(this.mCaptureProcessor == null, "CaptureProcessor should not be set if software JPEG is to be used.");
                    if (Build.VERSION.SDK_INT >= 26) {
                        Logger.i(TAG, "Using software JPEG encoder.");
                        yuvToJpegProcessor = new YuvToJpegProcessor(getJpegQuality(), this.mMaxCaptureStages);
                        captureProcessor = yuvToJpegProcessor;
                        i = 256;
                    } else {
                        throw new IllegalStateException("Software JPEG only supported on API 26+");
                    }
                } else {
                    captureProcessor = captureProcessor2;
                    i = imageFormat2;
                }
                ProcessingImageReader processingImageReader = new ProcessingImageReader(resolution.getWidth(), resolution.getHeight(), imageFormat, this.mMaxCaptureStages, this.mExecutor, getCaptureBundle(CaptureBundles.singleDefaultCaptureBundle()), captureProcessor, i);
                this.mProcessingImageReader = processingImageReader;
                this.mMetadataMatchingCaptureCallback = processingImageReader.getCameraCaptureCallback();
                this.mImageReader = new SafeCloseImageReaderProxy(this.mProcessingImageReader);
                if (yuvToJpegProcessor != null) {
                    this.mProcessingImageReader.getCloseFuture().addListener(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            ImageCapture.lambda$createPipeline$1(yuvToJpegProcessor);
                        }
                    }, CameraXExecutors.directExecutor());
                }
            } else {
                MetadataImageReader metadataImageReader = new MetadataImageReader(resolution.getWidth(), resolution.getHeight(), getImageFormat(), 2);
                this.mMetadataMatchingCaptureCallback = metadataImageReader.getCameraCaptureCallback();
                this.mImageReader = new SafeCloseImageReaderProxy(metadataImageReader);
            }
        }
        this.mImageCaptureRequestProcessor = new ImageCaptureRequestProcessor(2, new ImageCaptureRequestProcessor.ImageCaptor() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda14
            @Override // androidx.camera.core.ImageCapture.ImageCaptureRequestProcessor.ImageCaptor
            public final ListenableFuture capture(ImageCapture.ImageCaptureRequest imageCaptureRequest) {
                return this.f$0.m74lambda$createPipeline$2$androidxcameracoreImageCapture(imageCaptureRequest);
            }
        });
        this.mImageReader.setOnImageAvailableListener(this.mClosingListener, CameraXExecutors.mainThreadExecutor());
        SafeCloseImageReaderProxy safeCloseImageReaderProxy = this.mImageReader;
        DeferrableSurface deferrableSurface = this.mDeferrableSurface;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        ImmediateSurface immediateSurface = new ImmediateSurface(this.mImageReader.getSurface());
        this.mDeferrableSurface = immediateSurface;
        ListenableFuture<Void> terminationFuture = immediateSurface.getTerminationFuture();
        Objects.requireNonNull(safeCloseImageReaderProxy);
        terminationFuture.addListener(new ImageAnalysis$$ExternalSyntheticLambda2(safeCloseImageReaderProxy), CameraXExecutors.mainThreadExecutor());
        builderCreateFrom.addNonRepeatingSurface(this.mDeferrableSurface);
        builderCreateFrom.addErrorListener(new SessionConfig.ErrorListener() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda17
            @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
            public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
                this.f$0.m75lambda$createPipeline$3$androidxcameracoreImageCapture(cameraId, config, resolution, sessionConfig, sessionError);
            }
        });
        return builderCreateFrom;
    }

    static /* synthetic */ void lambda$createPipeline$1(YuvToJpegProcessor yuvToJpegProcessor) {
        if (Build.VERSION.SDK_INT >= 26) {
            yuvToJpegProcessor.close();
        }
    }

    /* renamed from: lambda$createPipeline$3$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ void m75lambda$createPipeline$3$androidxcameracoreImageCapture(String str, ImageCaptureConfig imageCaptureConfig, Size size, SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        clearPipeline();
        if (isCurrentCamera(str)) {
            SessionConfig.Builder builderCreatePipeline = createPipeline(str, imageCaptureConfig, size);
            this.mSessionConfigBuilder = builderCreatePipeline;
            updateSessionConfig(builderCreatePipeline.build());
            notifyReset();
        }
    }

    void clearPipeline() {
        Threads.checkMainThread();
        DeferrableSurface deferrableSurface = this.mDeferrableSurface;
        this.mDeferrableSurface = null;
        this.mImageReader = null;
        this.mProcessingImageReader = null;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> getDefaultConfig(boolean applyDefaultConfig, UseCaseConfigFactory factory) {
        Config config = factory.getConfig(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE);
        if (applyDefaultConfig) {
            config = Config.CC.mergeConfigs(config, DEFAULT_CONFIG.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config config) {
        return Builder.fromConfig(config);
    }

    @Override // androidx.camera.core.UseCase
    UseCaseConfig<?> onMergeConfig(CameraInfoInternal cameraInfo, UseCaseConfig.Builder<?, ?, ?> builder) {
        if (cameraInfo.getCameraQuirks().contains(SoftwareJpegEncodingPreferredQuirk.class)) {
            if (!((Boolean) builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, true)).booleanValue()) {
                Logger.w(TAG, "Device quirk suggests software JPEG encoder, but it has been explicitly disabled.");
            } else {
                Logger.i(TAG, "Requesting software JPEG due to device quirk.");
                builder.getMutableConfig().insertOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, true);
            }
        }
        boolean zEnforceSoftwareJpegConstraints = enforceSoftwareJpegConstraints(builder.getMutableConfig());
        Integer num = (Integer) builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
        if (num != null) {
            Preconditions.checkArgument(builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) == null, "Cannot set buffer format with CaptureProcessor defined.");
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, Integer.valueOf(zEnforceSoftwareJpegConstraints ? 35 : num.intValue()));
        } else if (builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) != null || zEnforceSoftwareJpegConstraints) {
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
        } else {
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
        }
        Preconditions.checkArgument(((Integer) builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_MAX_CAPTURE_STAGES, 2)).intValue() >= 1, "Maximum outstanding image count must be at least 1");
        return builder.getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    protected void onCameraControlReady() {
        trySetFlashModeToCameraControl();
    }

    public int getFlashMode() {
        int flashMode;
        synchronized (this.mLockedFlashMode) {
            flashMode = this.mFlashMode;
            if (flashMode == -1) {
                flashMode = ((ImageCaptureConfig) getCurrentConfig()).getFlashMode(2);
            }
        }
        return flashMode;
    }

    public void setFlashMode(int flashMode) {
        if (flashMode != 0 && flashMode != 1 && flashMode != 2) {
            throw new IllegalArgumentException("Invalid flash mode: " + flashMode);
        }
        synchronized (this.mLockedFlashMode) {
            this.mFlashMode = flashMode;
            trySetFlashModeToCameraControl();
        }
    }

    public void setCropAspectRatio(Rational aspectRatio) {
        this.mCropAspectRatio = aspectRatio;
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    public void setTargetRotation(int rotation) {
        int targetRotation = getTargetRotation();
        if (!setTargetRotationInternal(rotation) || this.mCropAspectRatio == null) {
            return;
        }
        this.mCropAspectRatio = ImageUtil.getRotatedAspectRatio(Math.abs(CameraOrientationUtil.surfaceRotationToDegrees(rotation) - CameraOrientationUtil.surfaceRotationToDegrees(targetRotation)), this.mCropAspectRatio);
    }

    public int getCaptureMode() {
        return this.mCaptureMode;
    }

    /* renamed from: takePicture, reason: merged with bridge method [inline-methods] */
    public void m81lambda$takePicture$4$androidxcameracoreImageCapture(final Executor executor, final OnImageCapturedCallback callback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m81lambda$takePicture$4$androidxcameracoreImageCapture(executor, callback);
                }
            });
        } else {
            sendImageCaptureRequest(executor, callback);
        }
    }

    /* renamed from: takePicture, reason: merged with bridge method [inline-methods] */
    public void m82lambda$takePicture$5$androidxcameracoreImageCapture(final OutputFileOptions outputFileOptions, final Executor executor, final OnImageSavedCallback imageSavedCallback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m82lambda$takePicture$5$androidxcameracoreImageCapture(outputFileOptions, executor, imageSavedCallback);
                }
            });
            return;
        }
        final ImageSaver.OnImageSavedCallback onImageSavedCallback = new ImageSaver.OnImageSavedCallback() { // from class: androidx.camera.core.ImageCapture.2
            @Override // androidx.camera.core.ImageSaver.OnImageSavedCallback
            public void onImageSaved(OutputFileResults outputFileResults) {
                imageSavedCallback.onImageSaved(outputFileResults);
            }

            @Override // androidx.camera.core.ImageSaver.OnImageSavedCallback
            public void onError(ImageSaver.SaveError error, String message, Throwable cause) {
                imageSavedCallback.onError(new ImageCaptureException(AnonymousClass9.$SwitchMap$androidx$camera$core$ImageSaver$SaveError[error.ordinal()] != 1 ? 0 : 1, message, cause));
            }
        };
        sendImageCaptureRequest(CameraXExecutors.mainThreadExecutor(), new OnImageCapturedCallback() { // from class: androidx.camera.core.ImageCapture.3
            @Override // androidx.camera.core.ImageCapture.OnImageCapturedCallback
            public void onCaptureSuccess(ImageProxy image) {
                ImageCapture.this.mIoExecutor.execute(new ImageSaver(image, outputFileOptions, image.getImageInfo().getRotationDegrees(), executor, ImageCapture.this.mSequentialIoExecutor, onImageSavedCallback));
            }

            @Override // androidx.camera.core.ImageCapture.OnImageCapturedCallback
            public void onError(final ImageCaptureException exception) {
                imageSavedCallback.onError(exception);
            }
        });
    }

    /* renamed from: androidx.camera.core.ImageCapture$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$ImageSaver$SaveError;

        static {
            int[] iArr = new int[ImageSaver.SaveError.values().length];
            $SwitchMap$androidx$camera$core$ImageSaver$SaveError = iArr;
            try {
                iArr[ImageSaver.SaveError.FILE_IO_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @Override // androidx.camera.core.UseCase
    public void onStateDetached() {
        abortImageCaptureRequests();
    }

    private void abortImageCaptureRequests() {
        this.mImageCaptureRequestProcessor.cancelRequests(new CameraClosedException("Camera is closed."));
    }

    private void sendImageCaptureRequest(Executor callbackExecutor, final OnImageCapturedCallback callback) {
        CameraInternal camera = getCamera();
        if (camera == null) {
            callbackExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m80x828e3ec2(callback);
                }
            });
        } else {
            this.mImageCaptureRequestProcessor.sendRequest(new ImageCaptureRequest(getRelativeRotation(camera), getJpegQuality(), this.mCropAspectRatio, getViewPortCropRect(), callbackExecutor, callback));
        }
    }

    /* renamed from: lambda$sendImageCaptureRequest$6$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ void m80x828e3ec2(OnImageCapturedCallback onImageCapturedCallback) {
        onImageCapturedCallback.onError(new ImageCaptureException(4, "Not bound to a valid Camera [" + this + "]", null));
    }

    private void lockFlashMode() {
        synchronized (this.mLockedFlashMode) {
            if (this.mLockedFlashMode.get() != null) {
                return;
            }
            this.mLockedFlashMode.set(Integer.valueOf(getFlashMode()));
        }
    }

    private void unlockFlashMode() {
        synchronized (this.mLockedFlashMode) {
            Integer andSet = this.mLockedFlashMode.getAndSet(null);
            if (andSet == null) {
                return;
            }
            if (andSet.intValue() != getFlashMode()) {
                trySetFlashModeToCameraControl();
            }
        }
    }

    private void trySetFlashModeToCameraControl() {
        synchronized (this.mLockedFlashMode) {
            if (this.mLockedFlashMode.get() != null) {
                return;
            }
            getCameraControl().setFlashMode(getFlashMode());
        }
    }

    private int getJpegQuality() {
        int i = this.mCaptureMode;
        if (i == 0) {
            return 100;
        }
        if (i == 1) {
            return 95;
        }
        throw new IllegalStateException("CaptureMode " + this.mCaptureMode + " is invalid");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: takePictureInternal, reason: merged with bridge method [inline-methods] */
    public ListenableFuture<ImageProxy> m74lambda$createPipeline$2$androidxcameracoreImageCapture(final ImageCaptureRequest imageCaptureRequest) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m83lambda$takePictureInternal$10$androidxcameracoreImageCapture(imageCaptureRequest, completer);
            }
        });
    }

    /* renamed from: lambda$takePictureInternal$10$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ Object m83lambda$takePictureInternal$10$androidxcameracoreImageCapture(final ImageCaptureRequest imageCaptureRequest, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mImageReader.setOnImageAvailableListener(new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda15
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                ImageCapture.lambda$takePictureInternal$7(completer, imageReaderProxy);
            }
        }, CameraXExecutors.mainThreadExecutor());
        final TakePictureState takePictureState = new TakePictureState();
        final FutureChain futureChainTransformAsync = FutureChain.from(preTakePicture(takePictureState)).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda18
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return this.f$0.m84lambda$takePictureInternal$8$androidxcameracoreImageCapture(imageCaptureRequest, (Void) obj);
            }
        }, this.mExecutor);
        Futures.addCallback(futureChainTransformAsync, new FutureCallback<Void>() { // from class: androidx.camera.core.ImageCapture.4
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(Void result) {
                ImageCapture.this.postTakePicture(takePictureState);
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable throwable) {
                ImageCapture.this.postTakePicture(takePictureState);
                completer.setException(throwable);
            }
        }, this.mExecutor);
        completer.addCancellationListener(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                futureChainTransformAsync.cancel(true);
            }
        }, CameraXExecutors.directExecutor());
        return "takePictureInternal";
    }

    static /* synthetic */ void lambda$takePictureInternal$7(CallbackToFutureAdapter.Completer completer, ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy imageProxyAcquireLatestImage = imageReaderProxy.acquireLatestImage();
            if (imageProxyAcquireLatestImage != null) {
                if (!completer.set(imageProxyAcquireLatestImage)) {
                    imageProxyAcquireLatestImage.close();
                }
            } else {
                completer.setException(new IllegalStateException("Unable to acquire image"));
            }
        } catch (IllegalStateException e) {
            completer.setException(e);
        }
    }

    /* renamed from: lambda$takePictureInternal$8$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ ListenableFuture m84lambda$takePictureInternal$8$androidxcameracoreImageCapture(ImageCaptureRequest imageCaptureRequest, Void r2) throws Exception {
        return issueTakePicture(imageCaptureRequest);
    }

    static class ImageCaptureRequestProcessor implements ForwardingImageProxy.OnImageCloseListener {
        private final ImageCaptor mImageCaptor;
        private final int mMaxImages;
        private final Deque<ImageCaptureRequest> mPendingRequests = new ArrayDeque();
        ImageCaptureRequest mCurrentRequest = null;
        ListenableFuture<ImageProxy> mCurrentRequestFuture = null;
        int mOutstandingImages = 0;
        final Object mLock = new Object();

        interface ImageCaptor {
            ListenableFuture<ImageProxy> capture(ImageCaptureRequest imageCaptureRequest);
        }

        ImageCaptureRequestProcessor(int maxImages, ImageCaptor imageCaptor) {
            this.mMaxImages = maxImages;
            this.mImageCaptor = imageCaptor;
        }

        public void sendRequest(ImageCaptureRequest imageCaptureRequest) {
            synchronized (this.mLock) {
                this.mPendingRequests.offer(imageCaptureRequest);
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(this.mCurrentRequest != null ? 1 : 0);
                objArr[1] = Integer.valueOf(this.mPendingRequests.size());
                Logger.d(ImageCapture.TAG, String.format(locale, "Send image capture request [current, pending] = [%d, %d]", objArr));
                processNextRequest();
            }
        }

        public void cancelRequests(Throwable throwable) {
            ImageCaptureRequest imageCaptureRequest;
            ListenableFuture<ImageProxy> listenableFuture;
            ArrayList arrayList;
            synchronized (this.mLock) {
                imageCaptureRequest = this.mCurrentRequest;
                this.mCurrentRequest = null;
                listenableFuture = this.mCurrentRequestFuture;
                this.mCurrentRequestFuture = null;
                arrayList = new ArrayList(this.mPendingRequests);
                this.mPendingRequests.clear();
            }
            if (imageCaptureRequest != null && listenableFuture != null) {
                imageCaptureRequest.notifyCallbackError(ImageCapture.getError(throwable), throwable.getMessage(), throwable);
                listenableFuture.cancel(true);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ImageCaptureRequest) it.next()).notifyCallbackError(ImageCapture.getError(throwable), throwable.getMessage(), throwable);
            }
        }

        @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
        public void onImageClose(ImageProxy image) {
            synchronized (this.mLock) {
                this.mOutstandingImages--;
                processNextRequest();
            }
        }

        void processNextRequest() {
            synchronized (this.mLock) {
                if (this.mCurrentRequest != null) {
                    return;
                }
                if (this.mOutstandingImages >= this.mMaxImages) {
                    Logger.w(ImageCapture.TAG, "Too many acquire images. Close image to be able to process next.");
                    return;
                }
                final ImageCaptureRequest imageCaptureRequestPoll = this.mPendingRequests.poll();
                if (imageCaptureRequestPoll == null) {
                    return;
                }
                this.mCurrentRequest = imageCaptureRequestPoll;
                ListenableFuture<ImageProxy> listenableFutureCapture = this.mImageCaptor.capture(imageCaptureRequestPoll);
                this.mCurrentRequestFuture = listenableFutureCapture;
                Futures.addCallback(listenableFutureCapture, new FutureCallback<ImageProxy>() { // from class: androidx.camera.core.ImageCapture.ImageCaptureRequestProcessor.1
                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onSuccess(ImageProxy image) {
                        synchronized (ImageCaptureRequestProcessor.this.mLock) {
                            Preconditions.checkNotNull(image);
                            SingleCloseImageProxy singleCloseImageProxy = new SingleCloseImageProxy(image);
                            singleCloseImageProxy.addOnImageCloseListener(ImageCaptureRequestProcessor.this);
                            ImageCaptureRequestProcessor.this.mOutstandingImages++;
                            imageCaptureRequestPoll.dispatchImage(singleCloseImageProxy);
                            ImageCaptureRequestProcessor.this.mCurrentRequest = null;
                            ImageCaptureRequestProcessor.this.mCurrentRequestFuture = null;
                            ImageCaptureRequestProcessor.this.processNextRequest();
                        }
                    }

                    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                    public void onFailure(Throwable t) {
                        synchronized (ImageCaptureRequestProcessor.this.mLock) {
                            if (!(t instanceof CancellationException)) {
                                imageCaptureRequestPoll.notifyCallbackError(ImageCapture.getError(t), t != null ? t.getMessage() : "Unknown error", t);
                            }
                            ImageCaptureRequestProcessor.this.mCurrentRequest = null;
                            ImageCaptureRequestProcessor.this.mCurrentRequestFuture = null;
                            ImageCaptureRequestProcessor.this.processNextRequest();
                        }
                    }
                }, CameraXExecutors.directExecutor());
            }
        }
    }

    public String toString() {
        return "ImageCapture:" + getName();
    }

    static int getError(Throwable throwable) {
        if (throwable instanceof CameraClosedException) {
            return 3;
        }
        return throwable instanceof CaptureFailedException ? 2 : 0;
    }

    static boolean enforceSoftwareJpegConstraints(MutableConfig mutableConfig) {
        boolean z = false;
        if (((Boolean) mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, false)).booleanValue()) {
            boolean z2 = true;
            if (Build.VERSION.SDK_INT < 26) {
                Logger.w(TAG, "Software JPEG only supported on API 26+, but current API level is " + Build.VERSION.SDK_INT);
                z2 = false;
            }
            Integer num = (Integer) mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (num != null && num.intValue() != 256) {
                Logger.w(TAG, "Software JPEG cannot be used with non-JPEG output buffer format.");
                z2 = false;
            }
            if (mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) != null) {
                Logger.w(TAG, "CaptureProcessor is set, unable to use software JPEG.");
            } else {
                z = z2;
            }
            if (!z) {
                Logger.w(TAG, "Unable to support software JPEG. Disabling.");
                mutableConfig.insertOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, false);
            }
        }
        return z;
    }

    @Override // androidx.camera.core.UseCase
    public void onDetached() {
        abortImageCaptureRequests();
        clearPipeline();
        this.mUseSoftwareJpeg = false;
        this.mExecutor.shutdown();
    }

    @Override // androidx.camera.core.UseCase
    public void onAttached() {
        ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) getCurrentConfig();
        this.mCaptureConfig = CaptureConfig.Builder.createFrom(imageCaptureConfig).build();
        this.mCaptureProcessor = imageCaptureConfig.getCaptureProcessor(null);
        this.mMaxCaptureStages = imageCaptureConfig.getMaxCaptureStages(2);
        this.mCaptureBundle = imageCaptureConfig.getCaptureBundle(CaptureBundles.singleDefaultCaptureBundle());
        this.mUseSoftwareJpeg = imageCaptureConfig.isSoftwareJpegEncoderRequested();
        this.mExecutor = Executors.newFixedThreadPool(1, new ThreadFactory() { // from class: androidx.camera.core.ImageCapture.5
            private final AtomicInteger mId = new AtomicInteger(0);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable r) {
                return new Thread(r, "CameraX-image_capture_" + this.mId.getAndIncrement());
            }
        });
    }

    @Override // androidx.camera.core.UseCase
    protected Size onSuggestedResolutionUpdated(Size suggestedResolution) {
        SessionConfig.Builder builderCreatePipeline = createPipeline(getCameraId(), (ImageCaptureConfig) getCurrentConfig(), suggestedResolution);
        this.mSessionConfigBuilder = builderCreatePipeline;
        updateSessionConfig(builderCreatePipeline.build());
        notifyActive();
        return suggestedResolution;
    }

    private ListenableFuture<Void> preTakePicture(final TakePictureState state) {
        lockFlashMode();
        return FutureChain.from(getPreCaptureStateIfNeeded()).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda19
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return this.f$0.m78lambda$preTakePicture$11$androidxcameracoreImageCapture(state, (CameraCaptureResult) obj);
            }
        }, this.mExecutor).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda20
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return this.f$0.m79lambda$preTakePicture$12$androidxcameracoreImageCapture(state, (Void) obj);
            }
        }, this.mExecutor).transform(new Function() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda10
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return ImageCapture.lambda$preTakePicture$13((Boolean) obj);
            }
        }, this.mExecutor);
    }

    /* renamed from: lambda$preTakePicture$11$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ ListenableFuture m78lambda$preTakePicture$11$androidxcameracoreImageCapture(TakePictureState takePictureState, CameraCaptureResult cameraCaptureResult) throws Exception {
        takePictureState.mPreCaptureState = cameraCaptureResult;
        triggerAfIfNeeded(takePictureState);
        if (isFlashRequired(takePictureState)) {
            if (this.mUseTorchFlash) {
                return openTorch(takePictureState);
            }
            return triggerAePrecapture(takePictureState);
        }
        return Futures.immediateFuture(null);
    }

    /* renamed from: lambda$preTakePicture$12$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ ListenableFuture m79lambda$preTakePicture$12$androidxcameracoreImageCapture(TakePictureState takePictureState, Void r2) throws Exception {
        return check3AConverged(takePictureState);
    }

    void postTakePicture(final TakePictureState state) {
        closeTorch(state);
        cancelAfAeTrigger(state);
        unlockFlashMode();
    }

    private ListenableFuture<Void> openTorch(final TakePictureState state) {
        CameraInternal camera = getCamera();
        if (camera != null && camera.getCameraInfo().getTorchState().getValue().intValue() == 1) {
            return Futures.immediateFuture(null);
        }
        Logger.d(TAG, "openTorch");
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m77lambda$openTorch$15$androidxcameracoreImageCapture(state, completer);
            }
        });
    }

    /* renamed from: lambda$openTorch$15$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ Object m77lambda$openTorch$15$androidxcameracoreImageCapture(TakePictureState takePictureState, final CallbackToFutureAdapter.Completer completer) throws Exception {
        CameraControlInternal cameraControl = getCameraControl();
        takePictureState.mIsTorchOpened = true;
        cameraControl.enableTorch(true).addListener(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                completer.set(null);
            }
        }, CameraXExecutors.directExecutor());
        return "openTorch";
    }

    private void closeTorch(TakePictureState state) {
        if (state.mIsTorchOpened) {
            CameraControlInternal cameraControl = getCameraControl();
            state.mIsTorchOpened = false;
            cameraControl.enableTorch(false).addListener(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda11
                @Override // java.lang.Runnable
                public final void run() {
                    ImageCapture.lambda$closeTorch$16();
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    private ListenableFuture<CameraCaptureResult> getPreCaptureStateIfNeeded() {
        if (this.mEnableCheck3AConverged || getFlashMode() == 0) {
            return this.mSessionCallbackChecker.checkCaptureResult(new CaptureCallbackChecker.CaptureResultChecker<CameraCaptureResult>() { // from class: androidx.camera.core.ImageCapture.6
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // androidx.camera.core.ImageCapture.CaptureCallbackChecker.CaptureResultChecker
                public CameraCaptureResult check(CameraCaptureResult captureResult) {
                    if (Logger.isDebugEnabled(ImageCapture.TAG)) {
                        Logger.d(ImageCapture.TAG, "preCaptureState, AE=" + captureResult.getAeState() + " AF =" + captureResult.getAfState() + " AWB=" + captureResult.getAwbState());
                    }
                    return captureResult;
                }
            });
        }
        return Futures.immediateFuture(null);
    }

    boolean isFlashRequired(TakePictureState state) {
        int flashMode = getFlashMode();
        if (flashMode == 0) {
            return state.mPreCaptureState.getAeState() == CameraCaptureMetaData.AeState.FLASH_REQUIRED;
        }
        if (flashMode == 1) {
            return true;
        }
        if (flashMode == 2) {
            return false;
        }
        throw new AssertionError(getFlashMode());
    }

    ListenableFuture<Boolean> check3AConverged(TakePictureState state) {
        if (!this.mEnableCheck3AConverged && !state.mIsAePrecaptureTriggered && !state.mIsTorchOpened) {
            return Futures.immediateFuture(false);
        }
        return this.mSessionCallbackChecker.checkCaptureResult(new CaptureCallbackChecker.CaptureResultChecker<Boolean>() { // from class: androidx.camera.core.ImageCapture.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // androidx.camera.core.ImageCapture.CaptureCallbackChecker.CaptureResultChecker
            public Boolean check(CameraCaptureResult captureResult) {
                if (Logger.isDebugEnabled(ImageCapture.TAG)) {
                    Logger.d(ImageCapture.TAG, "checkCaptureResult, AE=" + captureResult.getAeState() + " AF =" + captureResult.getAfState() + " AWB=" + captureResult.getAwbState());
                }
                return ImageCapture.this.is3AConverged(captureResult) ? true : null;
            }
        }, 1000L, false);
    }

    boolean is3AConverged(CameraCaptureResult captureResult) {
        if (captureResult == null) {
            return false;
        }
        return (captureResult.getAfMode() == CameraCaptureMetaData.AfMode.ON_CONTINUOUS_AUTO || captureResult.getAfMode() == CameraCaptureMetaData.AfMode.OFF || captureResult.getAfMode() == CameraCaptureMetaData.AfMode.UNKNOWN || captureResult.getAfState() == CameraCaptureMetaData.AfState.FOCUSED || captureResult.getAfState() == CameraCaptureMetaData.AfState.LOCKED_FOCUSED || captureResult.getAfState() == CameraCaptureMetaData.AfState.LOCKED_NOT_FOCUSED) && (captureResult.getAeState() == CameraCaptureMetaData.AeState.CONVERGED || captureResult.getAeState() == CameraCaptureMetaData.AeState.FLASH_REQUIRED || captureResult.getAeState() == CameraCaptureMetaData.AeState.UNKNOWN) && (captureResult.getAwbState() == CameraCaptureMetaData.AwbState.CONVERGED || captureResult.getAwbState() == CameraCaptureMetaData.AwbState.UNKNOWN);
    }

    void triggerAfIfNeeded(TakePictureState state) {
        if (this.mEnableCheck3AConverged && state.mPreCaptureState.getAfMode() == CameraCaptureMetaData.AfMode.ON_MANUAL_AUTO && state.mPreCaptureState.getAfState() == CameraCaptureMetaData.AfState.INACTIVE) {
            triggerAf(state);
        }
    }

    private void triggerAf(TakePictureState state) {
        Logger.d(TAG, "triggerAf");
        state.mIsAfTriggered = true;
        getCameraControl().triggerAf().addListener(new Runnable() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                ImageCapture.lambda$triggerAf$17();
            }
        }, CameraXExecutors.directExecutor());
    }

    ListenableFuture<Void> triggerAePrecapture(TakePictureState state) {
        Logger.d(TAG, "triggerAePrecapture");
        state.mIsAePrecaptureTriggered = true;
        return Futures.transform(getCameraControl().triggerAePrecapture(), new Function() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return ImageCapture.lambda$triggerAePrecapture$18((CameraCaptureResult) obj);
            }
        }, CameraXExecutors.directExecutor());
    }

    void cancelAfAeTrigger(TakePictureState state) {
        if (state.mIsAfTriggered || state.mIsAePrecaptureTriggered) {
            getCameraControl().cancelAfAeTrigger(state.mIsAfTriggered, state.mIsAePrecaptureTriggered);
            state.mIsAfTriggered = false;
            state.mIsAePrecaptureTriggered = false;
        }
    }

    ListenableFuture<Void> issueTakePicture(ImageCaptureRequest imageCaptureRequest) {
        CaptureBundle captureBundle;
        Logger.d(TAG, "issueTakePicture");
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        String tagBundleKey = null;
        if (this.mProcessingImageReader != null) {
            if (this.mUseSoftwareJpeg) {
                captureBundle = getCaptureBundle(CaptureBundles.singleDefaultCaptureBundle());
                if (captureBundle.getCaptureStages().size() > 1) {
                    return Futures.immediateFailedFuture(new IllegalArgumentException("Software JPEG not supported with CaptureBundle size > 1."));
                }
            } else {
                captureBundle = getCaptureBundle(null);
            }
            if (captureBundle == null) {
                return Futures.immediateFailedFuture(new IllegalArgumentException("ImageCapture cannot set empty CaptureBundle."));
            }
            if (captureBundle.getCaptureStages().size() > this.mMaxCaptureStages) {
                return Futures.immediateFailedFuture(new IllegalArgumentException("ImageCapture has CaptureStages > Max CaptureStage size"));
            }
            this.mProcessingImageReader.setCaptureBundle(captureBundle);
            tagBundleKey = this.mProcessingImageReader.getTagBundleKey();
        } else {
            captureBundle = getCaptureBundle(CaptureBundles.singleDefaultCaptureBundle());
            if (captureBundle.getCaptureStages().size() > 1) {
                return Futures.immediateFailedFuture(new IllegalArgumentException("ImageCapture have no CaptureProcess set with CaptureBundle size > 1."));
            }
        }
        for (final CaptureStage captureStage : captureBundle.getCaptureStages()) {
            final CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.mCaptureConfig.getTemplateType());
            builder.addImplementationOptions(this.mCaptureConfig.getImplementationOptions());
            builder.addAllCameraCaptureCallbacks(this.mSessionConfigBuilder.getSingleCameraCaptureCallbacks());
            builder.addSurface(this.mDeferrableSurface);
            if (new ExifRotationAvailability().isRotationOptionSupported()) {
                builder.addImplementationOption(CaptureConfig.OPTION_ROTATION, Integer.valueOf(imageCaptureRequest.mRotationDegrees));
            }
            builder.addImplementationOption(CaptureConfig.OPTION_JPEG_QUALITY, Integer.valueOf(imageCaptureRequest.mJpegQuality));
            builder.addImplementationOptions(captureStage.getCaptureConfig().getImplementationOptions());
            if (tagBundleKey != null) {
                builder.addTag(tagBundleKey, Integer.valueOf(captureStage.getId()));
            }
            builder.addCameraCaptureCallback(this.mMetadataMatchingCaptureCallback);
            arrayList.add(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda3
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m76lambda$issueTakePicture$19$androidxcameracoreImageCapture(builder, arrayList2, captureStage, completer);
                }
            }));
        }
        getCameraControl().submitCaptureRequests(arrayList2);
        return Futures.transform(Futures.allAsList(arrayList), new Function() { // from class: androidx.camera.core.ImageCapture$$ExternalSyntheticLambda13
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return ImageCapture.lambda$issueTakePicture$20((List) obj);
            }
        }, CameraXExecutors.directExecutor());
    }

    /* renamed from: lambda$issueTakePicture$19$androidx-camera-core-ImageCapture, reason: not valid java name */
    public /* synthetic */ Object m76lambda$issueTakePicture$19$androidxcameracoreImageCapture(CaptureConfig.Builder builder, List list, CaptureStage captureStage, final CallbackToFutureAdapter.Completer completer) throws Exception {
        builder.addCameraCaptureCallback(new CameraCaptureCallback() { // from class: androidx.camera.core.ImageCapture.8
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(CameraCaptureResult result) {
                completer.set(null);
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureFailed(CameraCaptureFailure failure) {
                completer.setException(new CaptureFailedException("Capture request failed with reason " + failure.getReason()));
            }

            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCancelled() {
                completer.setException(new CameraClosedException("Capture request is cancelled because camera is closed"));
            }
        });
        list.add(builder.build());
        return "issueTakePicture[stage=" + captureStage.getId() + "]";
    }

    static final class CaptureFailedException extends RuntimeException {
        CaptureFailedException(String s, Throwable e) {
            super(s, e);
        }

        CaptureFailedException(String s) {
            super(s);
        }
    }

    private CaptureBundle getCaptureBundle(CaptureBundle defaultCaptureBundle) {
        List<CaptureStage> captureStages = this.mCaptureBundle.getCaptureStages();
        return (captureStages == null || captureStages.isEmpty()) ? defaultCaptureBundle : CaptureBundles.createCaptureBundle(captureStages);
    }

    public static final class Defaults implements ConfigProvider<ImageCaptureConfig> {
        private static final int DEFAULT_ASPECT_RATIO = 0;
        private static final ImageCaptureConfig DEFAULT_CONFIG = new Builder().setSurfaceOccupancyPriority(4).setTargetAspectRatio(0).getUseCaseConfig();
        private static final int DEFAULT_SURFACE_OCCUPANCY_PRIORITY = 4;

        @Override // androidx.camera.core.impl.ConfigProvider
        public ImageCaptureConfig getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    public static final class OutputFileOptions {
        private final ContentResolver mContentResolver;
        private final ContentValues mContentValues;
        private final File mFile;
        private final Metadata mMetadata;
        private final OutputStream mOutputStream;
        private final Uri mSaveCollection;

        OutputFileOptions(File file, ContentResolver contentResolver, Uri saveCollection, ContentValues contentValues, OutputStream outputStream, Metadata metadata) {
            this.mFile = file;
            this.mContentResolver = contentResolver;
            this.mSaveCollection = saveCollection;
            this.mContentValues = contentValues;
            this.mOutputStream = outputStream;
            this.mMetadata = metadata == null ? new Metadata() : metadata;
        }

        File getFile() {
            return this.mFile;
        }

        ContentResolver getContentResolver() {
            return this.mContentResolver;
        }

        Uri getSaveCollection() {
            return this.mSaveCollection;
        }

        ContentValues getContentValues() {
            return this.mContentValues;
        }

        OutputStream getOutputStream() {
            return this.mOutputStream;
        }

        public Metadata getMetadata() {
            return this.mMetadata;
        }

        public static final class Builder {
            private ContentResolver mContentResolver;
            private ContentValues mContentValues;
            private File mFile;
            private Metadata mMetadata;
            private OutputStream mOutputStream;
            private Uri mSaveCollection;

            public Builder(File file) {
                this.mFile = file;
            }

            public Builder(ContentResolver contentResolver, Uri saveCollection, ContentValues contentValues) {
                this.mContentResolver = contentResolver;
                this.mSaveCollection = saveCollection;
                this.mContentValues = contentValues;
            }

            public Builder(OutputStream outputStream) {
                this.mOutputStream = outputStream;
            }

            public Builder setMetadata(Metadata metadata) {
                this.mMetadata = metadata;
                return this;
            }

            public OutputFileOptions build() {
                return new OutputFileOptions(this.mFile, this.mContentResolver, this.mSaveCollection, this.mContentValues, this.mOutputStream, this.mMetadata);
            }
        }
    }

    public static class OutputFileResults {
        private Uri mSavedUri;

        OutputFileResults(Uri savedUri) {
            this.mSavedUri = savedUri;
        }

        public Uri getSavedUri() {
            return this.mSavedUri;
        }
    }

    public static final class Metadata {
        private boolean mIsReversedHorizontal;
        private boolean mIsReversedHorizontalSet = false;
        private boolean mIsReversedVertical;
        private Location mLocation;

        public boolean isReversedHorizontal() {
            return this.mIsReversedHorizontal;
        }

        public boolean isReversedHorizontalSet() {
            return this.mIsReversedHorizontalSet;
        }

        public void setReversedHorizontal(boolean isReversedHorizontal) {
            this.mIsReversedHorizontal = isReversedHorizontal;
            this.mIsReversedHorizontalSet = true;
        }

        public boolean isReversedVertical() {
            return this.mIsReversedVertical;
        }

        public void setReversedVertical(boolean isReversedVertical) {
            this.mIsReversedVertical = isReversedVertical;
        }

        public Location getLocation() {
            return this.mLocation;
        }

        public void setLocation(Location location) {
            this.mLocation = location;
        }
    }

    static final class TakePictureState {
        CameraCaptureResult mPreCaptureState = CameraCaptureResult.EmptyCameraCaptureResult.create();
        boolean mIsTorchOpened = false;
        boolean mIsAfTriggered = false;
        boolean mIsAePrecaptureTriggered = false;

        TakePictureState() {
        }
    }

    static final class CaptureCallbackChecker extends CameraCaptureCallback {
        private static final long NO_TIMEOUT = 0;
        private final Set<CaptureResultListener> mCaptureResultListeners = new HashSet();

        public interface CaptureResultChecker<T> {
            T check(CameraCaptureResult captureResult);
        }

        private interface CaptureResultListener {
            boolean onCaptureResult(CameraCaptureResult captureResult);
        }

        CaptureCallbackChecker() {
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
            deliverCaptureResultToListeners(cameraCaptureResult);
        }

        <T> ListenableFuture<T> checkCaptureResult(CaptureResultChecker<T> checker) {
            return checkCaptureResult(checker, 0L, null);
        }

        <T> ListenableFuture<T> checkCaptureResult(final CaptureResultChecker<T> checker, final long timeoutInMs, final T defValue) {
            if (timeoutInMs < 0) {
                throw new IllegalArgumentException("Invalid timeout value: " + timeoutInMs);
            }
            final long jElapsedRealtime = timeoutInMs != 0 ? SystemClock.elapsedRealtime() : 0L;
            return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.ImageCapture$CaptureCallbackChecker$$ExternalSyntheticLambda0
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m85x26a9d6b9(checker, jElapsedRealtime, timeoutInMs, defValue, completer);
                }
            });
        }

        /* renamed from: lambda$checkCaptureResult$0$androidx-camera-core-ImageCapture$CaptureCallbackChecker, reason: not valid java name */
        public /* synthetic */ Object m85x26a9d6b9(final CaptureResultChecker captureResultChecker, final long j, final long j2, final Object obj, final CallbackToFutureAdapter.Completer completer) throws Exception {
            addListener(new CaptureResultListener() { // from class: androidx.camera.core.ImageCapture.CaptureCallbackChecker.1
                @Override // androidx.camera.core.ImageCapture.CaptureCallbackChecker.CaptureResultListener
                public boolean onCaptureResult(CameraCaptureResult captureResult) {
                    Object objCheck = captureResultChecker.check(captureResult);
                    if (objCheck != null) {
                        completer.set(objCheck);
                        return true;
                    }
                    if (j <= 0 || SystemClock.elapsedRealtime() - j <= j2) {
                        return false;
                    }
                    completer.set(obj);
                    return true;
                }
            });
            return "checkCaptureResult";
        }

        private void deliverCaptureResultToListeners(CameraCaptureResult captureResult) {
            synchronized (this.mCaptureResultListeners) {
                HashSet hashSet = null;
                Iterator it = new HashSet(this.mCaptureResultListeners).iterator();
                while (it.hasNext()) {
                    CaptureResultListener captureResultListener = (CaptureResultListener) it.next();
                    if (captureResultListener.onCaptureResult(captureResult)) {
                        if (hashSet == null) {
                            hashSet = new HashSet();
                        }
                        hashSet.add(captureResultListener);
                    }
                }
                if (hashSet != null) {
                    this.mCaptureResultListeners.removeAll(hashSet);
                }
            }
        }

        void addListener(CaptureResultListener listener) {
            synchronized (this.mCaptureResultListeners) {
                this.mCaptureResultListeners.add(listener);
            }
        }
    }

    static class ImageCaptureRequest {
        private final OnImageCapturedCallback mCallback;
        AtomicBoolean mDispatched = new AtomicBoolean(false);
        final int mJpegQuality;
        private final Executor mListenerExecutor;
        final int mRotationDegrees;
        private final Rational mTargetRatio;
        private final Rect mViewPortCropRect;

        ImageCaptureRequest(int rotationDegrees, int jpegQuality, Rational targetRatio, Rect viewPortCropRect, Executor executor, OnImageCapturedCallback callback) {
            this.mRotationDegrees = rotationDegrees;
            this.mJpegQuality = jpegQuality;
            if (targetRatio != null) {
                Preconditions.checkArgument(!targetRatio.isZero(), "Target ratio cannot be zero");
                Preconditions.checkArgument(targetRatio.floatValue() > 0.0f, "Target ratio must be positive");
            }
            this.mTargetRatio = targetRatio;
            this.mViewPortCropRect = viewPortCropRect;
            this.mListenerExecutor = executor;
            this.mCallback = callback;
        }

        void dispatchImage(final ImageProxy image) {
            Size size;
            int rotation;
            if (!this.mDispatched.compareAndSet(false, true)) {
                image.close();
                return;
            }
            if (new ExifRotationAvailability().shouldUseExifOrientation(image)) {
                try {
                    ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                    buffer.rewind();
                    byte[] bArr = new byte[buffer.capacity()];
                    buffer.get(bArr);
                    Exif exifCreateFromInputStream = Exif.createFromInputStream(new ByteArrayInputStream(bArr));
                    buffer.rewind();
                    size = new Size(exifCreateFromInputStream.getWidth(), exifCreateFromInputStream.getHeight());
                    rotation = exifCreateFromInputStream.getRotation();
                } catch (IOException e) {
                    notifyCallbackError(1, "Unable to parse JPEG exif", e);
                    image.close();
                    return;
                }
            } else {
                size = new Size(image.getWidth(), image.getHeight());
                rotation = this.mRotationDegrees;
            }
            final SettableImageProxy settableImageProxy = new SettableImageProxy(image, size, ImmutableImageInfo.create(image.getImageInfo().getTagBundle(), image.getImageInfo().getTimestamp(), rotation));
            Rect rect = this.mViewPortCropRect;
            if (rect != null) {
                settableImageProxy.setCropRect(getDispatchCropRect(rect, this.mRotationDegrees, size, rotation));
            } else {
                Rational rational = this.mTargetRatio;
                if (rational != null) {
                    if (rotation % 180 != 0) {
                        rational = new Rational(this.mTargetRatio.getDenominator(), this.mTargetRatio.getNumerator());
                    }
                    Size size2 = new Size(settableImageProxy.getWidth(), settableImageProxy.getHeight());
                    if (ImageUtil.isAspectRatioValid(size2, rational)) {
                        settableImageProxy.setCropRect(ImageUtil.computeCropRectFromAspectRatio(size2, rational));
                    }
                }
            }
            try {
                this.mListenerExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageCapture$ImageCaptureRequest$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m86xf35699e1(settableImageProxy);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Logger.e(ImageCapture.TAG, "Unable to post to the supplied executor.");
                image.close();
            }
        }

        /* renamed from: lambda$dispatchImage$0$androidx-camera-core-ImageCapture$ImageCaptureRequest, reason: not valid java name */
        public /* synthetic */ void m86xf35699e1(ImageProxy imageProxy) {
            this.mCallback.onCaptureSuccess(imageProxy);
        }

        static Rect getDispatchCropRect(Rect surfaceCropRect, int surfaceToOutputDegrees, Size dispatchResolution, int dispatchToOutputDegrees) {
            Matrix matrix = new Matrix();
            matrix.setRotate(dispatchToOutputDegrees - surfaceToOutputDegrees);
            float[] fArrSizeToVertexes = ImageUtil.sizeToVertexes(dispatchResolution);
            matrix.mapPoints(fArrSizeToVertexes);
            matrix.postTranslate(-ImageUtil.min(fArrSizeToVertexes[0], fArrSizeToVertexes[2], fArrSizeToVertexes[4], fArrSizeToVertexes[6]), -ImageUtil.min(fArrSizeToVertexes[1], fArrSizeToVertexes[3], fArrSizeToVertexes[5], fArrSizeToVertexes[7]));
            matrix.invert(matrix);
            RectF rectF = new RectF();
            matrix.mapRect(rectF, new RectF(surfaceCropRect));
            rectF.sort();
            Rect rect = new Rect();
            rectF.round(rect);
            return rect;
        }

        void notifyCallbackError(final int imageCaptureError, final String message, final Throwable cause) {
            if (this.mDispatched.compareAndSet(false, true)) {
                try {
                    this.mListenerExecutor.execute(new Runnable() { // from class: androidx.camera.core.ImageCapture$ImageCaptureRequest$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m87xe8eba4db(imageCaptureError, message, cause);
                        }
                    });
                } catch (RejectedExecutionException unused) {
                    Logger.e(ImageCapture.TAG, "Unable to post to the supplied executor.");
                }
            }
        }

        /* renamed from: lambda$notifyCallbackError$1$androidx-camera-core-ImageCapture$ImageCaptureRequest, reason: not valid java name */
        public /* synthetic */ void m87xe8eba4db(int i, String str, Throwable th) {
            this.mCallback.onError(new ImageCaptureException(i, str, th));
        }
    }

    public static final class Builder implements UseCaseConfig.Builder<ImageCapture, ImageCaptureConfig, Builder>, ImageOutputConfig.Builder<Builder>, IoConfig.Builder<Builder> {
        private final MutableOptionsBundle mMutableConfig;

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public /* bridge */ /* synthetic */ Builder setSupportedResolutions(List resolutions) {
            return setSupportedResolutions((List<Pair<Integer, Size[]>>) resolutions);
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public /* bridge */ /* synthetic */ Object setTargetClass(Class targetClass) {
            return setTargetClass((Class<ImageCapture>) targetClass);
        }

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableConfig) {
            this.mMutableConfig = mutableConfig;
            Class cls = (Class) mutableConfig.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls != null && !cls.equals(ImageCapture.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            setTargetClass(ImageCapture.class);
        }

        public static Builder fromConfig(Config configuration) {
            return new Builder(MutableOptionsBundle.from(configuration));
        }

        static Builder fromConfig(ImageCaptureConfig configuration) {
            return new Builder(MutableOptionsBundle.from((Config) configuration));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public ImageCaptureConfig getUseCaseConfig() {
            return new ImageCaptureConfig(OptionsBundle.from(this.mMutableConfig));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public ImageCapture build() {
            int iIntValue;
            if (getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_TARGET_ASPECT_RATIO, null) != null && getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_TARGET_RESOLUTION, null) != null) {
                throw new IllegalArgumentException("Cannot use both setTargetResolution and setTargetAspectRatio on the same config.");
            }
            Integer num = (Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (num != null) {
                Preconditions.checkArgument(getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) == null, "Cannot set buffer format with CaptureProcessor defined.");
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, num);
            } else if (getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) != null) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
            } else {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
            }
            ImageCapture imageCapture = new ImageCapture(getUseCaseConfig());
            Size size = (Size) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_TARGET_RESOLUTION, null);
            if (size != null) {
                imageCapture.setCropAspectRatio(new Rational(size.getWidth(), size.getHeight()));
            }
            Preconditions.checkArgument(((Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_MAX_CAPTURE_STAGES, 2)).intValue() >= 1, "Maximum outstanding image count must be at least 1");
            Preconditions.checkNotNull((Executor) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_IO_EXECUTOR, CameraXExecutors.ioExecutor()), "The IO executor can't be null");
            if (!getMutableConfig().containsOption(ImageCaptureConfig.OPTION_FLASH_MODE) || (iIntValue = ((Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_FLASH_MODE)).intValue()) == 0 || iIntValue == 1 || iIntValue == 2) {
                return imageCapture;
            }
            throw new IllegalArgumentException("The flash mode is not allowed to set: " + iIntValue);
        }

        public Builder setCaptureMode(int captureMode) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE, Integer.valueOf(captureMode));
            return this;
        }

        public Builder setFlashMode(int flashMode) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_FLASH_MODE, Integer.valueOf(flashMode));
            return this;
        }

        public Builder setCaptureBundle(CaptureBundle captureBundle) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_CAPTURE_BUNDLE, captureBundle);
            return this;
        }

        public Builder setCaptureProcessor(CaptureProcessor captureProcessor) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, captureProcessor);
            return this;
        }

        public Builder setBufferFormat(int bufferImageFormat) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, Integer.valueOf(bufferImageFormat));
            return this;
        }

        public Builder setMaxCaptureStages(int maxCaptureStages) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_MAX_CAPTURE_STAGES, Integer.valueOf(maxCaptureStages));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setSupportedResolutions(List<Pair<Integer, Size[]>> resolutions) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SUPPORTED_RESOLUTIONS, resolutions);
            return this;
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public Builder setTargetClass(Class<ImageCapture> targetClass) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_CLASS, targetClass);
            if (getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(targetClass.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public Builder setTargetName(String targetName) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_NAME, targetName);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetAspectRatio(int aspectRatio) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(aspectRatio));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetRotation(int rotation) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_ROTATION, Integer.valueOf(rotation));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetResolution(Size resolution) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_TARGET_RESOLUTION, resolution);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setDefaultResolution(Size resolution) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_DEFAULT_RESOLUTION, resolution);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setMaxResolution(Size resolution) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_MAX_RESOLUTION, resolution);
            return this;
        }

        public Builder setImageReaderProxyProvider(ImageReaderProxyProvider imageReaderProxyProvider) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IMAGE_READER_PROXY_PROVIDER, imageReaderProxyProvider);
            return this;
        }

        public Builder setSoftwareJpegEncoderRequested(boolean requestSoftwareJpeg) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, Boolean.valueOf(requestSoftwareJpeg));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.IoConfig.Builder
        public Builder setIoExecutor(Executor executor) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IO_EXECUTOR, executor);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setDefaultSessionConfig(SessionConfig sessionConfig) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setDefaultCaptureConfig(CaptureConfig captureConfig) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setCameraSelector(CameraSelector cameraSelector) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAMERA_SELECTOR, cameraSelector);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setSurfaceOccupancyPriority(int priority) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(priority));
            return this;
        }

        @Override // androidx.camera.core.internal.UseCaseEventConfig.Builder
        public Builder setUseCaseEventCallback(UseCase.EventCallback useCaseEventCallback) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_USE_CASE_EVENT_CALLBACK, useCaseEventCallback);
            return this;
        }
    }
}
