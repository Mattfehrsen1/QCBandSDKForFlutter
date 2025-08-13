package androidx.camera.view;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Display;
import androidx.arch.core.util.Function;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.ViewPort;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.video.OnVideoSavedCallback;
import androidx.camera.view.video.OutputFileOptions;
import androidx.camera.view.video.OutputFileResults;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public abstract class CameraController {
    private static final float AE_SIZE = 0.25f;
    private static final float AF_SIZE = 0.16666667f;
    private static final String CAMERA_NOT_ATTACHED = "Use cases not attached to camera.";
    private static final String CAMERA_NOT_INITIALIZED = "Camera not initialized.";
    public static final int IMAGE_ANALYSIS = 2;
    public static final int IMAGE_CAPTURE = 1;
    private static final String IMAGE_CAPTURE_DISABLED = "ImageCapture disabled.";
    private static final String PREVIEW_VIEW_NOT_ATTACHED = "PreviewView not attached.";
    private static final String TAG = "CameraController";
    public static final int TAP_TO_FOCUS_FAILED = 4;
    public static final int TAP_TO_FOCUS_NOT_STARTED = 0;
    public static final int TAP_TO_FOCUS_STARTED = 1;
    public static final int TAP_TO_FOCUS_SUCCESSFUL = 2;
    public static final int TAP_TO_FOCUS_UNSUCCESSFUL = 3;
    public static final int VIDEO_CAPTURE = 4;
    private static final String VIDEO_CAPTURE_DISABLED = "VideoCapture disabled.";
    private ImageAnalysis.Analyzer mAnalysisAnalyzer;
    private Executor mAnalysisBackgroundExecutor;
    private Executor mAnalysisExecutor;
    private final Context mAppContext;
    Camera mCamera;
    ProcessCameraProvider mCameraProvider;
    private final DisplayRotationListener mDisplayRotationListener;
    ImageAnalysis mImageAnalysis;
    OutputSize mImageAnalysisTargetSize;
    ImageCapture mImageCapture;
    Executor mImageCaptureIoExecutor;
    OutputSize mImageCaptureTargetSize;
    private final ListenableFuture<Void> mInitializationFuture;
    Preview mPreview;
    Display mPreviewDisplay;
    OutputSize mPreviewTargetSize;
    final RotationReceiver mRotationReceiver;
    Preview.SurfaceProvider mSurfaceProvider;
    VideoCapture mVideoCapture;
    OutputSize mVideoCaptureOutputSize;
    ViewPort mViewPort;
    CameraSelector mCameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
    private int mEnabledUseCases = 3;
    final AtomicBoolean mVideoIsRecording = new AtomicBoolean(false);
    private boolean mPinchToZoomEnabled = true;
    private boolean mTapToFocusEnabled = true;
    private final ForwardingLiveData<ZoomState> mZoomState = new ForwardingLiveData<>();
    private final ForwardingLiveData<Integer> mTorchState = new ForwardingLiveData<>();
    final MutableLiveData<Integer> mTapToFocusState = new MutableLiveData<>(0);

    @Retention(RetentionPolicy.SOURCE)
    public @interface TapToFocusStates {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UseCases {
    }

    private float speedUpZoomBy2X(float scaleFactor) {
        return scaleFactor > 1.0f ? ((scaleFactor - 1.0f) * 2.0f) + 1.0f : 1.0f - ((1.0f - scaleFactor) * 2.0f);
    }

    abstract Camera startCamera();

    CameraController(Context context) {
        Context applicationContext = getApplicationContext(context);
        this.mAppContext = applicationContext;
        this.mPreview = new Preview.Builder().build();
        this.mImageCapture = new ImageCapture.Builder().build();
        this.mImageAnalysis = new ImageAnalysis.Builder().build();
        this.mVideoCapture = new VideoCapture.Builder().build();
        this.mInitializationFuture = Futures.transform(ProcessCameraProvider.getInstance(applicationContext), new Function() { // from class: androidx.camera.view.CameraController$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m112lambda$new$0$androidxcameraviewCameraController((ProcessCameraProvider) obj);
            }
        }, CameraXExecutors.mainThreadExecutor());
        this.mDisplayRotationListener = new DisplayRotationListener();
        this.mRotationReceiver = new RotationReceiver(applicationContext) { // from class: androidx.camera.view.CameraController.1
            @Override // androidx.camera.view.RotationReceiver
            public void onRotationChanged(int rotation) {
                CameraController.this.mImageAnalysis.setTargetRotation(rotation);
                CameraController.this.mImageCapture.setTargetRotation(rotation);
                CameraController.this.mVideoCapture.setTargetRotation(rotation);
            }
        };
    }

    /* renamed from: lambda$new$0$androidx-camera-view-CameraController, reason: not valid java name */
    public /* synthetic */ Void m112lambda$new$0$androidxcameraviewCameraController(ProcessCameraProvider processCameraProvider) {
        this.mCameraProvider = processCameraProvider;
        startCameraAndTrackStates();
        return null;
    }

    private static Context getApplicationContext(Context context) {
        String attributionTag;
        Context applicationContext = context.getApplicationContext();
        return (Build.VERSION.SDK_INT < 30 || (attributionTag = Api30Impl.getAttributionTag(context)) == null) ? applicationContext : Api30Impl.createAttributionContext(applicationContext, attributionTag);
    }

    public ListenableFuture<Void> getInitializationFuture() {
        return this.mInitializationFuture;
    }

    private boolean isCameraInitialized() {
        return this.mCameraProvider != null;
    }

    private boolean isPreviewViewAttached() {
        return (this.mSurfaceProvider == null || this.mViewPort == null || this.mPreviewDisplay == null) ? false : true;
    }

    private boolean isCameraAttached() {
        return this.mCamera != null;
    }

    public void setEnabledUseCases(int enabledUseCases) {
        Threads.checkMainThread();
        final int i = this.mEnabledUseCases;
        if (enabledUseCases == i) {
            return;
        }
        this.mEnabledUseCases = enabledUseCases;
        if (!isVideoCaptureEnabled()) {
            stopRecording();
        }
        startCameraAndTrackStates(new Runnable() { // from class: androidx.camera.view.CameraController$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m114x5a17d266(i);
            }
        });
    }

    /* renamed from: lambda$setEnabledUseCases$1$androidx-camera-view-CameraController, reason: not valid java name */
    public /* synthetic */ void m114x5a17d266(int i) {
        this.mEnabledUseCases = i;
    }

    private boolean isUseCaseEnabled(int useCaseMask) {
        return (useCaseMask & this.mEnabledUseCases) != 0;
    }

    private void setTargetOutputSize(ImageOutputConfig.Builder<?> builder, OutputSize outputSize) {
        if (outputSize == null) {
            return;
        }
        if (outputSize.getResolution() != null) {
            builder.setTargetResolution(outputSize.getResolution());
            return;
        }
        if (outputSize.getAspectRatio() != -1) {
            builder.setTargetAspectRatio(outputSize.getAspectRatio());
            return;
        }
        Logger.e(TAG, "Invalid target surface size. " + outputSize);
    }

    private boolean isOutputSizeEqual(OutputSize currentSize, OutputSize newSize) {
        if (currentSize == newSize) {
            return true;
        }
        return currentSize != null && currentSize.equals(newSize);
    }

    void attachPreviewSurface(Preview.SurfaceProvider surfaceProvider, ViewPort viewPort, Display display) {
        Threads.checkMainThread();
        if (this.mSurfaceProvider != surfaceProvider) {
            this.mSurfaceProvider = surfaceProvider;
            this.mPreview.setSurfaceProvider(surfaceProvider);
        }
        this.mViewPort = viewPort;
        this.mPreviewDisplay = display;
        startListeningToRotationEvents();
        startCameraAndTrackStates();
    }

    void clearPreviewSurface() {
        Threads.checkMainThread();
        ProcessCameraProvider processCameraProvider = this.mCameraProvider;
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
        this.mPreview.setSurfaceProvider(null);
        this.mCamera = null;
        this.mSurfaceProvider = null;
        this.mViewPort = null;
        this.mPreviewDisplay = null;
        stopListeningToRotationEvents();
    }

    private void startListeningToRotationEvents() {
        getDisplayManager().registerDisplayListener(this.mDisplayRotationListener, new Handler(Looper.getMainLooper()));
        if (this.mRotationReceiver.canDetectOrientation()) {
            this.mRotationReceiver.enable();
        }
    }

    private void stopListeningToRotationEvents() {
        getDisplayManager().unregisterDisplayListener(this.mDisplayRotationListener);
        this.mRotationReceiver.disable();
    }

    private DisplayManager getDisplayManager() {
        return (DisplayManager) this.mAppContext.getSystemService("display");
    }

    public void setPreviewTargetSize(OutputSize targetSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.mPreviewTargetSize, targetSize)) {
            return;
        }
        this.mPreviewTargetSize = targetSize;
        unbindPreviewAndRecreate();
        startCameraAndTrackStates();
    }

    public OutputSize getPreviewTargetSize() {
        Threads.checkMainThread();
        return this.mPreviewTargetSize;
    }

    private void unbindPreviewAndRecreate() {
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mPreview);
        }
        Preview.Builder builder = new Preview.Builder();
        setTargetOutputSize(builder, this.mPreviewTargetSize);
        this.mPreview = builder.build();
    }

    public boolean isImageCaptureEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(1);
    }

    public int getImageCaptureFlashMode() {
        Threads.checkMainThread();
        return this.mImageCapture.getFlashMode();
    }

    public void setImageCaptureFlashMode(int flashMode) {
        Threads.checkMainThread();
        this.mImageCapture.setFlashMode(flashMode);
    }

    public void takePicture(ImageCapture.OutputFileOptions outputFileOptions, Executor executor, ImageCapture.OnImageSavedCallback imageSavedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isImageCaptureEnabled(), IMAGE_CAPTURE_DISABLED);
        updateMirroringFlagInOutputFileOptions(outputFileOptions);
        this.mImageCapture.m82lambda$takePicture$5$androidxcameracoreImageCapture(outputFileOptions, executor, imageSavedCallback);
    }

    void updateMirroringFlagInOutputFileOptions(ImageCapture.OutputFileOptions outputFileOptions) {
        if (this.mCameraSelector.getLensFacing() == null || outputFileOptions.getMetadata().isReversedHorizontalSet()) {
            return;
        }
        outputFileOptions.getMetadata().setReversedHorizontal(this.mCameraSelector.getLensFacing().intValue() == 0);
    }

    public void takePicture(Executor executor, ImageCapture.OnImageCapturedCallback callback) {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isImageCaptureEnabled(), IMAGE_CAPTURE_DISABLED);
        this.mImageCapture.m81lambda$takePicture$4$androidxcameracoreImageCapture(executor, callback);
    }

    public void setImageCaptureMode(int captureMode) {
        Threads.checkMainThread();
        if (this.mImageCapture.getCaptureMode() == captureMode) {
            return;
        }
        unbindImageCaptureAndRecreate(captureMode);
        startCameraAndTrackStates();
    }

    public int getImageCaptureMode() {
        Threads.checkMainThread();
        return this.mImageCapture.getCaptureMode();
    }

    public void setImageCaptureTargetSize(OutputSize targetSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.mImageCaptureTargetSize, targetSize)) {
            return;
        }
        this.mImageCaptureTargetSize = targetSize;
        unbindImageCaptureAndRecreate(getImageCaptureMode());
        startCameraAndTrackStates();
    }

    public OutputSize getImageCaptureTargetSize() {
        Threads.checkMainThread();
        return this.mImageCaptureTargetSize;
    }

    public void setImageCaptureIoExecutor(Executor executor) {
        Threads.checkMainThread();
        if (this.mImageCaptureIoExecutor == executor) {
            return;
        }
        this.mImageCaptureIoExecutor = executor;
        unbindImageCaptureAndRecreate(this.mImageCapture.getCaptureMode());
        startCameraAndTrackStates();
    }

    public Executor getImageCaptureIoExecutor() {
        Threads.checkMainThread();
        return this.mImageCaptureIoExecutor;
    }

    private void unbindImageCaptureAndRecreate(int imageCaptureMode) {
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mImageCapture);
        }
        ImageCapture.Builder captureMode = new ImageCapture.Builder().setCaptureMode(imageCaptureMode);
        setTargetOutputSize(captureMode, this.mImageCaptureTargetSize);
        Executor executor = this.mImageCaptureIoExecutor;
        if (executor != null) {
            captureMode.setIoExecutor(executor);
        }
        this.mImageCapture = captureMode.build();
    }

    public boolean isImageAnalysisEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(2);
    }

    public void setImageAnalysisAnalyzer(Executor executor, ImageAnalysis.Analyzer analyzer) {
        Threads.checkMainThread();
        if (this.mAnalysisAnalyzer == analyzer && this.mAnalysisExecutor == executor) {
            return;
        }
        this.mAnalysisExecutor = executor;
        this.mAnalysisAnalyzer = analyzer;
        this.mImageAnalysis.setAnalyzer(executor, analyzer);
    }

    public void clearImageAnalysisAnalyzer() {
        Threads.checkMainThread();
        this.mAnalysisExecutor = null;
        this.mAnalysisAnalyzer = null;
        this.mImageAnalysis.clearAnalyzer();
    }

    public int getImageAnalysisBackpressureStrategy() {
        Threads.checkMainThread();
        return this.mImageAnalysis.getBackpressureStrategy();
    }

    public void setImageAnalysisBackpressureStrategy(int strategy) {
        Threads.checkMainThread();
        if (this.mImageAnalysis.getBackpressureStrategy() == strategy) {
            return;
        }
        unbindImageAnalysisAndRecreate(strategy, this.mImageAnalysis.getImageQueueDepth());
        startCameraAndTrackStates();
    }

    public void setImageAnalysisImageQueueDepth(int depth) {
        Threads.checkMainThread();
        if (this.mImageAnalysis.getImageQueueDepth() == depth) {
            return;
        }
        unbindImageAnalysisAndRecreate(this.mImageAnalysis.getBackpressureStrategy(), depth);
        startCameraAndTrackStates();
    }

    public int getImageAnalysisImageQueueDepth() {
        Threads.checkMainThread();
        return this.mImageAnalysis.getImageQueueDepth();
    }

    public void setImageAnalysisTargetSize(OutputSize targetSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.mImageAnalysisTargetSize, targetSize)) {
            return;
        }
        this.mImageAnalysisTargetSize = targetSize;
        unbindImageAnalysisAndRecreate(this.mImageAnalysis.getBackpressureStrategy(), this.mImageAnalysis.getImageQueueDepth());
        startCameraAndTrackStates();
    }

    public OutputSize getImageAnalysisTargetSize() {
        Threads.checkMainThread();
        return this.mImageAnalysisTargetSize;
    }

    public void setImageAnalysisBackgroundExecutor(Executor executor) {
        Threads.checkMainThread();
        if (this.mAnalysisBackgroundExecutor == executor) {
            return;
        }
        this.mAnalysisBackgroundExecutor = executor;
        unbindImageAnalysisAndRecreate(this.mImageAnalysis.getBackpressureStrategy(), this.mImageAnalysis.getImageQueueDepth());
        startCameraAndTrackStates();
    }

    public Executor getImageAnalysisBackgroundExecutor() {
        Threads.checkMainThread();
        return this.mAnalysisBackgroundExecutor;
    }

    private void unbindImageAnalysisAndRecreate(int strategy, int imageQueueDepth) {
        ImageAnalysis.Analyzer analyzer;
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mImageAnalysis);
        }
        ImageAnalysis.Builder imageQueueDepth2 = new ImageAnalysis.Builder().setBackpressureStrategy(strategy).setImageQueueDepth(imageQueueDepth);
        setTargetOutputSize(imageQueueDepth2, this.mImageAnalysisTargetSize);
        Executor executor = this.mAnalysisBackgroundExecutor;
        if (executor != null) {
            imageQueueDepth2.setBackgroundExecutor(executor);
        }
        ImageAnalysis imageAnalysisBuild = imageQueueDepth2.build();
        this.mImageAnalysis = imageAnalysisBuild;
        Executor executor2 = this.mAnalysisExecutor;
        if (executor2 == null || (analyzer = this.mAnalysisAnalyzer) == null) {
            return;
        }
        imageAnalysisBuild.setAnalyzer(executor2, analyzer);
    }

    public boolean isVideoCaptureEnabled() {
        Threads.checkMainThread();
        return isUseCaseEnabled(4);
    }

    public void startRecording(OutputFileOptions outputFileOptions, Executor executor, final OnVideoSavedCallback callback) throws IllegalStateException {
        Threads.checkMainThread();
        Preconditions.checkState(isCameraInitialized(), CAMERA_NOT_INITIALIZED);
        Preconditions.checkState(isVideoCaptureEnabled(), VIDEO_CAPTURE_DISABLED);
        this.mVideoCapture.m101lambda$startRecording$0$androidxcameracoreVideoCapture(outputFileOptions.toVideoCaptureOutputFileOptions(), executor, new VideoCapture.OnVideoSavedCallback() { // from class: androidx.camera.view.CameraController.2
            @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
            public void onVideoSaved(VideoCapture.OutputFileResults outputFileResults) {
                CameraController.this.mVideoIsRecording.set(false);
                callback.onVideoSaved(OutputFileResults.create(outputFileResults.getSavedUri()));
            }

            @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
            public void onError(int videoCaptureError, String message, Throwable cause) {
                CameraController.this.mVideoIsRecording.set(false);
                callback.onError(videoCaptureError, message, cause);
            }
        });
        this.mVideoIsRecording.set(true);
    }

    public void stopRecording() {
        Threads.checkMainThread();
        if (this.mVideoIsRecording.get()) {
            this.mVideoCapture.m105lambda$stopRecording$5$androidxcameracoreVideoCapture();
        }
    }

    public boolean isRecording() {
        Threads.checkMainThread();
        return this.mVideoIsRecording.get();
    }

    public void setVideoCaptureTargetSize(OutputSize targetSize) {
        Threads.checkMainThread();
        if (isOutputSizeEqual(this.mVideoCaptureOutputSize, targetSize)) {
            return;
        }
        this.mVideoCaptureOutputSize = targetSize;
        unbindVideoAndRecreate();
        startCameraAndTrackStates();
    }

    public OutputSize getVideoCaptureTargetSize() {
        Threads.checkMainThread();
        return this.mVideoCaptureOutputSize;
    }

    private void unbindVideoAndRecreate() {
        if (isCameraInitialized()) {
            this.mCameraProvider.unbind(this.mVideoCapture);
        }
        VideoCapture.Builder builder = new VideoCapture.Builder();
        setTargetOutputSize(builder, this.mVideoCaptureOutputSize);
        this.mVideoCapture = builder.build();
    }

    public void setCameraSelector(CameraSelector cameraSelector) {
        Threads.checkMainThread();
        final CameraSelector cameraSelector2 = this.mCameraSelector;
        if (cameraSelector2 == cameraSelector) {
            return;
        }
        this.mCameraSelector = cameraSelector;
        ProcessCameraProvider processCameraProvider = this.mCameraProvider;
        if (processCameraProvider == null) {
            return;
        }
        processCameraProvider.unbindAll();
        startCameraAndTrackStates(new Runnable() { // from class: androidx.camera.view.CameraController$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m113lambda$setCameraSelector$2$androidxcameraviewCameraController(cameraSelector2);
            }
        });
    }

    /* renamed from: lambda$setCameraSelector$2$androidx-camera-view-CameraController, reason: not valid java name */
    public /* synthetic */ void m113lambda$setCameraSelector$2$androidxcameraviewCameraController(CameraSelector cameraSelector) {
        this.mCameraSelector = cameraSelector;
    }

    public boolean hasCamera(CameraSelector cameraSelector) {
        Threads.checkMainThread();
        Preconditions.checkNotNull(cameraSelector);
        ProcessCameraProvider processCameraProvider = this.mCameraProvider;
        if (processCameraProvider == null) {
            throw new IllegalStateException("Camera not initialized. Please wait for the initialization future to finish. See #getInitializationFuture().");
        }
        try {
            return processCameraProvider.hasCamera(cameraSelector);
        } catch (CameraInfoUnavailableException e) {
            Logger.w(TAG, "Failed to check camera availability", e);
            return false;
        }
    }

    public CameraSelector getCameraSelector() {
        Threads.checkMainThread();
        return this.mCameraSelector;
    }

    public boolean isPinchToZoomEnabled() {
        Threads.checkMainThread();
        return this.mPinchToZoomEnabled;
    }

    public void setPinchToZoomEnabled(boolean enabled) {
        Threads.checkMainThread();
        this.mPinchToZoomEnabled = enabled;
    }

    void onPinchToZoom(float pinchToZoomScale) {
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
            return;
        }
        if (!this.mPinchToZoomEnabled) {
            Logger.d(TAG, "Pinch to zoom disabled.");
            return;
        }
        Logger.d(TAG, "Pinch to zoom with scale: " + pinchToZoomScale);
        ZoomState value = getZoomState().getValue();
        if (value == null) {
            return;
        }
        setZoomRatio(Math.min(Math.max(value.getZoomRatio() * speedUpZoomBy2X(pinchToZoomScale), value.getMinZoomRatio()), value.getMaxZoomRatio()));
    }

    void onTapToFocus(MeteringPointFactory meteringPointFactory, float x, float y) {
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
            return;
        }
        if (!this.mTapToFocusEnabled) {
            Logger.d(TAG, "Tap to focus disabled. ");
            return;
        }
        Logger.d(TAG, "Tap to focus started: " + x + ", " + y);
        this.mTapToFocusState.postValue(1);
        Futures.addCallback(this.mCamera.getCameraControl().startFocusAndMetering(new FocusMeteringAction.Builder(meteringPointFactory.createPoint(x, y, AF_SIZE), 1).addPoint(meteringPointFactory.createPoint(x, y, AE_SIZE), 2).build()), new FutureCallback<FocusMeteringResult>() { // from class: androidx.camera.view.CameraController.3
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(FocusMeteringResult result) {
                if (result == null) {
                    return;
                }
                Logger.d(CameraController.TAG, "Tap to focus onSuccess: " + result.isFocusSuccessful());
                CameraController.this.mTapToFocusState.postValue(Integer.valueOf(result.isFocusSuccessful() ? 2 : 3));
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable t) {
                if (t instanceof CameraControl.OperationCanceledException) {
                    Logger.d(CameraController.TAG, "Tap-to-focus is canceled by new action.");
                } else {
                    Logger.d(CameraController.TAG, "Tap to focus failed.", t);
                    CameraController.this.mTapToFocusState.postValue(4);
                }
            }
        }, CameraXExecutors.directExecutor());
    }

    public boolean isTapToFocusEnabled() {
        Threads.checkMainThread();
        return this.mTapToFocusEnabled;
    }

    public void setTapToFocusEnabled(boolean enabled) {
        Threads.checkMainThread();
        this.mTapToFocusEnabled = enabled;
    }

    public LiveData<Integer> getTapToFocusState() {
        Threads.checkMainThread();
        return this.mTapToFocusState;
    }

    public LiveData<ZoomState> getZoomState() {
        Threads.checkMainThread();
        return this.mZoomState;
    }

    public CameraInfo getCameraInfo() {
        Threads.checkMainThread();
        Camera camera = this.mCamera;
        if (camera == null) {
            return null;
        }
        return camera.getCameraInfo();
    }

    public CameraControl getCameraControl() {
        Threads.checkMainThread();
        Camera camera = this.mCamera;
        if (camera == null) {
            return null;
        }
        return camera.getCameraControl();
    }

    public ListenableFuture<Void> setZoomRatio(float zoomRatio) {
        Threads.checkMainThread();
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
            return Futures.immediateFuture(null);
        }
        return this.mCamera.getCameraControl().setZoomRatio(zoomRatio);
    }

    public ListenableFuture<Void> setLinearZoom(float linearZoom) {
        Threads.checkMainThread();
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
            return Futures.immediateFuture(null);
        }
        return this.mCamera.getCameraControl().setLinearZoom(linearZoom);
    }

    public LiveData<Integer> getTorchState() {
        Threads.checkMainThread();
        return this.mTorchState;
    }

    public ListenableFuture<Void> enableTorch(boolean torchEnabled) {
        Threads.checkMainThread();
        if (!isCameraAttached()) {
            Logger.w(TAG, CAMERA_NOT_ATTACHED);
            return Futures.immediateFuture(null);
        }
        return this.mCamera.getCameraControl().enableTorch(torchEnabled);
    }

    void startCameraAndTrackStates() {
        startCameraAndTrackStates(null);
    }

    void startCameraAndTrackStates(Runnable restoreStateRunnable) {
        try {
            this.mCamera = startCamera();
            if (!isCameraAttached()) {
                Logger.d(TAG, CAMERA_NOT_ATTACHED);
            } else {
                this.mZoomState.setSource(this.mCamera.getCameraInfo().getZoomState());
                this.mTorchState.setSource(this.mCamera.getCameraInfo().getTorchState());
            }
        } catch (IllegalArgumentException e) {
            if (restoreStateRunnable != null) {
                restoreStateRunnable.run();
            }
            throw new IllegalStateException("The selected camera does not support the enabled use cases. Please disable use case and/or select a different camera. e.g. #setVideoCaptureEnabled(false)", e);
        }
    }

    protected UseCaseGroup createUseCaseGroup() {
        if (!isCameraInitialized()) {
            Logger.d(TAG, CAMERA_NOT_INITIALIZED);
            return null;
        }
        if (!isPreviewViewAttached()) {
            Logger.d(TAG, PREVIEW_VIEW_NOT_ATTACHED);
            return null;
        }
        UseCaseGroup.Builder builderAddUseCase = new UseCaseGroup.Builder().addUseCase(this.mPreview);
        if (isImageCaptureEnabled()) {
            builderAddUseCase.addUseCase(this.mImageCapture);
        } else {
            this.mCameraProvider.unbind(this.mImageCapture);
        }
        if (isImageAnalysisEnabled()) {
            builderAddUseCase.addUseCase(this.mImageAnalysis);
        } else {
            this.mCameraProvider.unbind(this.mImageAnalysis);
        }
        if (isVideoCaptureEnabled()) {
            builderAddUseCase.addUseCase(this.mVideoCapture);
        } else {
            this.mCameraProvider.unbind(this.mVideoCapture);
        }
        builderAddUseCase.setViewPort(this.mViewPort);
        return builderAddUseCase.build();
    }

    class DisplayRotationListener implements DisplayManager.DisplayListener {
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int displayId) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int displayId) {
        }

        DisplayRotationListener() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int displayId) {
            if (CameraController.this.mPreviewDisplay == null || CameraController.this.mPreviewDisplay.getDisplayId() != displayId) {
                return;
            }
            CameraController.this.mPreview.setTargetRotation(CameraController.this.mPreviewDisplay.getRotation());
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static Context createAttributionContext(Context context, String attributeTag) {
            return context.createAttributionContext(attributeTag);
        }

        static String getAttributionTag(Context context) {
            return context.getAttributionTag();
        }
    }

    public static class OutputSize {
        public static final int UNASSIGNED_ASPECT_RATIO = -1;
        private final int mAspectRatio;
        private final Size mResolution;

        @Retention(RetentionPolicy.SOURCE)
        public @interface OutputAspectRatio {
        }

        public OutputSize(int aspectRatio) {
            Preconditions.checkArgument(aspectRatio != -1);
            this.mAspectRatio = aspectRatio;
            this.mResolution = null;
        }

        public OutputSize(Size resolution) {
            Preconditions.checkNotNull(resolution);
            this.mAspectRatio = -1;
            this.mResolution = resolution;
        }

        public int getAspectRatio() {
            return this.mAspectRatio;
        }

        public Size getResolution() {
            return this.mResolution;
        }
    }
}
