package androidx.camera.core;

import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import android.util.Size;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageInfoProcessor;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.ThreadConfig;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class Preview extends UseCase {
    public static final Defaults DEFAULT_CONFIG = new Defaults();
    private static final Executor DEFAULT_SURFACE_PROVIDER_EXECUTOR = CameraXExecutors.mainThreadExecutor();
    private static final String TAG = "Preview";
    SurfaceRequest mCurrentSurfaceRequest;
    private boolean mHasUnsentSurfaceRequest;
    private DeferrableSurface mSessionDeferrableSurface;
    private SurfaceProvider mSurfaceProvider;
    private Executor mSurfaceProviderExecutor;
    private Size mSurfaceSize;

    public interface SurfaceProvider {
        void onSurfaceRequested(SurfaceRequest request);
    }

    Preview(PreviewConfig config) {
        super(config);
        this.mSurfaceProviderExecutor = DEFAULT_SURFACE_PROVIDER_EXECUTOR;
        this.mHasUnsentSurfaceRequest = false;
    }

    SessionConfig.Builder createPipeline(final String cameraId, final PreviewConfig config, final Size resolution) {
        Threads.checkMainThread();
        SessionConfig.Builder builderCreateFrom = SessionConfig.Builder.createFrom(config);
        CaptureProcessor captureProcessor = config.getCaptureProcessor(null);
        DeferrableSurface deferrableSurface = this.mSessionDeferrableSurface;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        SurfaceRequest surfaceRequest = new SurfaceRequest(resolution, getCamera(), captureProcessor != null);
        this.mCurrentSurfaceRequest = surfaceRequest;
        if (sendSurfaceRequestIfReady()) {
            sendTransformationInfoIfReady();
        } else {
            this.mHasUnsentSurfaceRequest = true;
        }
        if (captureProcessor != null) {
            CaptureStage.DefaultCaptureStage defaultCaptureStage = new CaptureStage.DefaultCaptureStage();
            final HandlerThread handlerThread = new HandlerThread("CameraX-preview_processing");
            handlerThread.start();
            String string = Integer.toString(defaultCaptureStage.hashCode());
            ProcessingSurface processingSurface = new ProcessingSurface(resolution.getWidth(), resolution.getHeight(), config.getInputFormat(), new Handler(handlerThread.getLooper()), defaultCaptureStage, captureProcessor, surfaceRequest.getDeferrableSurface(), string);
            builderCreateFrom.addCameraCaptureCallback(processingSurface.getCameraCaptureCallback());
            processingSurface.getTerminationFuture().addListener(new Runnable() { // from class: androidx.camera.core.Preview$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    handlerThread.quitSafely();
                }
            }, CameraXExecutors.directExecutor());
            this.mSessionDeferrableSurface = processingSurface;
            builderCreateFrom.addTag(string, Integer.valueOf(defaultCaptureStage.getId()));
        } else {
            final ImageInfoProcessor imageInfoProcessor = config.getImageInfoProcessor(null);
            if (imageInfoProcessor != null) {
                builderCreateFrom.addCameraCaptureCallback(new CameraCaptureCallback() { // from class: androidx.camera.core.Preview.1
                    @Override // androidx.camera.core.impl.CameraCaptureCallback
                    public void onCaptureCompleted(CameraCaptureResult cameraCaptureResult) {
                        super.onCaptureCompleted(cameraCaptureResult);
                        if (imageInfoProcessor.process(new CameraCaptureResultImageInfo(cameraCaptureResult))) {
                            Preview.this.notifyUpdated();
                        }
                    }
                });
            }
            this.mSessionDeferrableSurface = surfaceRequest.getDeferrableSurface();
        }
        builderCreateFrom.addSurface(this.mSessionDeferrableSurface);
        builderCreateFrom.addErrorListener(new SessionConfig.ErrorListener() { // from class: androidx.camera.core.Preview$$ExternalSyntheticLambda0
            @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
            public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
                this.f$0.m93lambda$createPipeline$0$androidxcameracorePreview(cameraId, config, resolution, sessionConfig, sessionError);
            }
        });
        return builderCreateFrom;
    }

    /* renamed from: lambda$createPipeline$0$androidx-camera-core-Preview, reason: not valid java name */
    public /* synthetic */ void m93lambda$createPipeline$0$androidxcameracorePreview(String str, PreviewConfig previewConfig, Size size, SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        if (isCurrentCamera(str)) {
            updateSessionConfig(createPipeline(str, previewConfig, size).build());
            notifyReset();
        }
    }

    public void setTargetRotation(int targetRotation) {
        if (setTargetRotationInternal(targetRotation)) {
            sendTransformationInfoIfReady();
        }
    }

    private void sendTransformationInfoIfReady() {
        CameraInternal camera = getCamera();
        SurfaceProvider surfaceProvider = this.mSurfaceProvider;
        Rect cropRect = getCropRect(this.mSurfaceSize);
        SurfaceRequest surfaceRequest = this.mCurrentSurfaceRequest;
        if (camera == null || surfaceProvider == null || cropRect == null) {
            return;
        }
        surfaceRequest.updateTransformationInfo(SurfaceRequest.TransformationInfo.of(cropRect, getRelativeRotation(camera), getTargetRotation()));
    }

    private Rect getCropRect(Size surfaceResolution) {
        if (getViewPortCropRect() != null) {
            return getViewPortCropRect();
        }
        if (surfaceResolution != null) {
            return new Rect(0, 0, surfaceResolution.getWidth(), surfaceResolution.getHeight());
        }
        return null;
    }

    public void setSurfaceProvider(Executor executor, SurfaceProvider surfaceProvider) {
        Threads.checkMainThread();
        if (surfaceProvider == null) {
            this.mSurfaceProvider = null;
            notifyInactive();
            return;
        }
        this.mSurfaceProvider = surfaceProvider;
        this.mSurfaceProviderExecutor = executor;
        notifyActive();
        if (this.mHasUnsentSurfaceRequest) {
            if (sendSurfaceRequestIfReady()) {
                sendTransformationInfoIfReady();
                this.mHasUnsentSurfaceRequest = false;
                return;
            }
            return;
        }
        if (getAttachedSurfaceResolution() != null) {
            updateConfigAndOutput(getCameraId(), (PreviewConfig) getCurrentConfig(), getAttachedSurfaceResolution());
            notifyReset();
        }
    }

    private boolean sendSurfaceRequestIfReady() {
        final SurfaceRequest surfaceRequest = this.mCurrentSurfaceRequest;
        final SurfaceProvider surfaceProvider = this.mSurfaceProvider;
        if (surfaceProvider == null || surfaceRequest == null) {
            return false;
        }
        this.mSurfaceProviderExecutor.execute(new Runnable() { // from class: androidx.camera.core.Preview$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                surfaceProvider.onSurfaceRequested(surfaceRequest);
            }
        });
        return true;
    }

    public void setSurfaceProvider(SurfaceProvider surfaceProvider) {
        setSurfaceProvider(DEFAULT_SURFACE_PROVIDER_EXECUTOR, surfaceProvider);
    }

    private void updateConfigAndOutput(String cameraId, PreviewConfig config, Size resolution) {
        updateSessionConfig(createPipeline(cameraId, config, resolution).build());
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    public String toString() {
        return "Preview:" + getName();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> getDefaultConfig(boolean applyDefaultConfig, UseCaseConfigFactory factory) {
        Config config = factory.getConfig(UseCaseConfigFactory.CaptureType.PREVIEW);
        if (applyDefaultConfig) {
            config = Config.CC.mergeConfigs(config, DEFAULT_CONFIG.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    UseCaseConfig<?> onMergeConfig(CameraInfoInternal cameraInfo, UseCaseConfig.Builder<?, ?, ?> builder) {
        if (builder.getMutableConfig().retrieveOption(PreviewConfig.OPTION_PREVIEW_CAPTURE_PROCESSOR, null) != null) {
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
        } else {
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 34);
        }
        return builder.getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config config) {
        return Builder.fromConfig(config);
    }

    @Override // androidx.camera.core.UseCase
    public void onDetached() {
        DeferrableSurface deferrableSurface = this.mSessionDeferrableSurface;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        this.mCurrentSurfaceRequest = null;
    }

    @Override // androidx.camera.core.UseCase
    protected Size onSuggestedResolutionUpdated(Size suggestedResolution) {
        this.mSurfaceSize = suggestedResolution;
        updateConfigAndOutput(getCameraId(), (PreviewConfig) getCurrentConfig(), this.mSurfaceSize);
        return suggestedResolution;
    }

    @Override // androidx.camera.core.UseCase
    public void setViewPortCropRect(Rect viewPortCropRect) {
        super.setViewPortCropRect(viewPortCropRect);
        sendTransformationInfoIfReady();
    }

    public static final class Defaults implements ConfigProvider<PreviewConfig> {
        private static final int DEFAULT_ASPECT_RATIO = 0;
        private static final PreviewConfig DEFAULT_CONFIG = new Builder().setSurfaceOccupancyPriority(2).setTargetAspectRatio(0).getUseCaseConfig();
        private static final int DEFAULT_SURFACE_OCCUPANCY_PRIORITY = 2;

        @Override // androidx.camera.core.impl.ConfigProvider
        public PreviewConfig getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    public static final class Builder implements UseCaseConfig.Builder<Preview, PreviewConfig, Builder>, ImageOutputConfig.Builder<Builder>, ThreadConfig.Builder<Builder> {
        private final MutableOptionsBundle mMutableConfig;

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public /* bridge */ /* synthetic */ Builder setSupportedResolutions(List resolutions) {
            return setSupportedResolutions((List<Pair<Integer, Size[]>>) resolutions);
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public /* bridge */ /* synthetic */ Object setTargetClass(Class targetClass) {
            return setTargetClass((Class<Preview>) targetClass);
        }

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableConfig) {
            this.mMutableConfig = mutableConfig;
            Class cls = (Class) mutableConfig.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls != null && !cls.equals(Preview.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            setTargetClass(Preview.class);
        }

        static Builder fromConfig(Config configuration) {
            return new Builder(MutableOptionsBundle.from(configuration));
        }

        public static Builder fromConfig(PreviewConfig configuration) {
            return new Builder(MutableOptionsBundle.from((Config) configuration));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public PreviewConfig getUseCaseConfig() {
            return new PreviewConfig(OptionsBundle.from(this.mMutableConfig));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public Preview build() {
            if (getMutableConfig().retrieveOption(PreviewConfig.OPTION_TARGET_ASPECT_RATIO, null) != null && getMutableConfig().retrieveOption(PreviewConfig.OPTION_TARGET_RESOLUTION, null) != null) {
                throw new IllegalArgumentException("Cannot use both setTargetResolution and setTargetAspectRatio on the same config.");
            }
            return new Preview(getUseCaseConfig());
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public Builder setTargetClass(Class<Preview> targetClass) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_TARGET_CLASS, targetClass);
            if (getMutableConfig().retrieveOption(PreviewConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(targetClass.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        public Builder setTargetName(String targetName) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_TARGET_NAME, targetName);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetAspectRatio(int aspectRatio) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(aspectRatio));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetRotation(int rotation) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_TARGET_ROTATION, Integer.valueOf(rotation));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetResolution(Size resolution) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, resolution);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setDefaultResolution(Size resolution) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_DEFAULT_RESOLUTION, resolution);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setMaxResolution(Size resolution) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_MAX_RESOLUTION, resolution);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setSupportedResolutions(List<Pair<Integer, Size[]>> resolutions) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_SUPPORTED_RESOLUTIONS, resolutions);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.ThreadConfig.Builder
        public Builder setBackgroundExecutor(Executor executor) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_BACKGROUND_EXECUTOR, executor);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setDefaultSessionConfig(SessionConfig sessionConfig) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setDefaultCaptureConfig(CaptureConfig captureConfig) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setSurfaceOccupancyPriority(int priority) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(priority));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public Builder setCameraSelector(CameraSelector cameraSelector) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAMERA_SELECTOR, cameraSelector);
            return this;
        }

        @Override // androidx.camera.core.internal.UseCaseEventConfig.Builder
        public Builder setUseCaseEventCallback(UseCase.EventCallback useCaseEventCallback) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_USE_CASE_EVENT_CALLBACK, useCaseEventCallback);
            return this;
        }

        public Builder setImageInfoProcessor(ImageInfoProcessor processor) {
            getMutableConfig().insertOption(PreviewConfig.IMAGE_INFO_PROCESSOR, processor);
            return this;
        }

        public Builder setCaptureProcessor(CaptureProcessor captureProcessor) {
            getMutableConfig().insertOption(PreviewConfig.OPTION_PREVIEW_CAPTURE_PROCESSOR, captureProcessor);
            return this;
        }
    }
}
