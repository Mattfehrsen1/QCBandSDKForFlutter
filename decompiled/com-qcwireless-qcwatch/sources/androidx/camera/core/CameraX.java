package androidx.camera.core;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import androidx.arch.core.util.Function;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.CameraValidator;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.IncompleteCameraListQuirk;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.HandlerCompat;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class CameraX {
    private static final long RETRY_SLEEP_MILLIS = 500;
    private static final String RETRY_TOKEN = "retry_token";
    private static final String TAG = "CameraX";
    private static final long WAIT_INITIALIZED_TIMEOUT_MILLIS = 3000;
    private static CameraXConfig.Provider sConfigProvider;
    static CameraX sInstance;
    private Context mAppContext;
    private final Executor mCameraExecutor;
    private CameraFactory mCameraFactory;
    private final CameraXConfig mCameraXConfig;
    private UseCaseConfigFactory mDefaultConfigFactory;
    private final Handler mSchedulerHandler;
    private final HandlerThread mSchedulerThread;
    private CameraDeviceSurfaceManager mSurfaceManager;
    static final Object INSTANCE_LOCK = new Object();
    private static ListenableFuture<Void> sInitializeFuture = Futures.immediateFailedFuture(new IllegalStateException("CameraX is not initialized."));
    private static ListenableFuture<Void> sShutdownFuture = Futures.immediateFuture(null);
    final CameraRepository mCameraRepository = new CameraRepository();
    private final Object mInitializeLock = new Object();
    private InternalInitState mInitState = InternalInitState.UNINITIALIZED;
    private ListenableFuture<Void> mShutdownInternalFuture = Futures.immediateFuture(null);

    private enum InternalInitState {
        UNINITIALIZED,
        INITIALIZING,
        INITIALIZED,
        SHUTDOWN
    }

    static /* synthetic */ CameraXConfig lambda$configureInstance$1(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    static /* synthetic */ CameraX lambda$getInstanceLocked$6(CameraX cameraX, Void r1) {
        return cameraX;
    }

    static /* synthetic */ CameraXConfig lambda$initialize$0(CameraXConfig cameraXConfig) {
        return cameraXConfig;
    }

    CameraX(CameraXConfig cameraXConfig) {
        this.mCameraXConfig = (CameraXConfig) Preconditions.checkNotNull(cameraXConfig);
        Executor cameraExecutor = cameraXConfig.getCameraExecutor(null);
        Handler schedulerHandler = cameraXConfig.getSchedulerHandler(null);
        this.mCameraExecutor = cameraExecutor == null ? new CameraExecutor() : cameraExecutor;
        if (schedulerHandler == null) {
            HandlerThread handlerThread = new HandlerThread("CameraX-scheduler", 10);
            this.mSchedulerThread = handlerThread;
            handlerThread.start();
            this.mSchedulerHandler = HandlerCompat.createAsync(handlerThread.getLooper());
            return;
        }
        this.mSchedulerThread = null;
        this.mSchedulerHandler = schedulerHandler;
    }

    public static CameraInternal getCameraWithCameraSelector(CameraSelector cameraSelector) {
        return cameraSelector.select(checkInitialized().getCameraRepository().getCameras());
    }

    public static ListenableFuture<Void> initialize(Context context, final CameraXConfig cameraXConfig) {
        ListenableFuture<Void> listenableFuture;
        synchronized (INSTANCE_LOCK) {
            Preconditions.checkNotNull(context);
            configureInstanceLocked(new CameraXConfig.Provider() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda4
                @Override // androidx.camera.core.CameraXConfig.Provider
                public final CameraXConfig getCameraXConfig() {
                    return CameraX.lambda$initialize$0(cameraXConfig);
                }
            });
            initializeInstanceLocked(context);
            listenableFuture = sInitializeFuture;
        }
        return listenableFuture;
    }

    public static void configureInstance(final CameraXConfig cameraXConfig) {
        synchronized (INSTANCE_LOCK) {
            configureInstanceLocked(new CameraXConfig.Provider() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda3
                @Override // androidx.camera.core.CameraXConfig.Provider
                public final CameraXConfig getCameraXConfig() {
                    return CameraX.lambda$configureInstance$1(cameraXConfig);
                }
            });
        }
    }

    private static void configureInstanceLocked(CameraXConfig.Provider configProvider) {
        Preconditions.checkNotNull(configProvider);
        Preconditions.checkState(sConfigProvider == null, "CameraX has already been configured. To use a different configuration, shutdown() must be called.");
        sConfigProvider = configProvider;
        Integer num = (Integer) configProvider.getCameraXConfig().retrieveOption(CameraXConfig.OPTION_MIN_LOGGING_LEVEL, null);
        if (num != null) {
            Logger.setMinLogLevel(num.intValue());
        }
    }

    private static void initializeInstanceLocked(final Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkState(sInstance == null, "CameraX already initialized.");
        Preconditions.checkNotNull(sConfigProvider);
        final CameraX cameraX = new CameraX(sConfigProvider.getCameraXConfig());
        sInstance = cameraX;
        sInitializeFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda9
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return CameraX.lambda$initializeInstanceLocked$3(this.f$0, context, completer);
            }
        });
    }

    static /* synthetic */ Object lambda$initializeInstanceLocked$3(final CameraX cameraX, final Context context, final CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (INSTANCE_LOCK) {
            Futures.addCallback(FutureChain.from(sShutdownFuture).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda5
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return this.f$0.initInternal(context);
                }
            }, CameraXExecutors.directExecutor()), new FutureCallback<Void>() { // from class: androidx.camera.core.CameraX.1
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(Void result) {
                    completer.set(null);
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable t) {
                    Logger.w(CameraX.TAG, "CameraX initialize() failed", t);
                    synchronized (CameraX.INSTANCE_LOCK) {
                        if (CameraX.sInstance == cameraX) {
                            CameraX.shutdownLocked();
                        }
                    }
                    completer.setException(t);
                }
            }, CameraXExecutors.directExecutor());
        }
        return "CameraX-initialize";
    }

    public static ListenableFuture<Void> shutdown() {
        ListenableFuture<Void> listenableFutureShutdownLocked;
        synchronized (INSTANCE_LOCK) {
            sConfigProvider = null;
            Logger.resetMinLogLevel();
            listenableFutureShutdownLocked = shutdownLocked();
        }
        return listenableFutureShutdownLocked;
    }

    static ListenableFuture<Void> shutdownLocked() {
        final CameraX cameraX = sInstance;
        if (cameraX == null) {
            return sShutdownFuture;
        }
        sInstance = null;
        ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda7
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return CameraX.lambda$shutdownLocked$5(this.f$0, completer);
            }
        });
        sShutdownFuture = future;
        return future;
    }

    static /* synthetic */ Object lambda$shutdownLocked$5(final CameraX cameraX, final CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (INSTANCE_LOCK) {
            sInitializeFuture.addListener(new Runnable() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Futures.propagate(this.f$0.shutdownInternal(), completer);
                }
            }, CameraXExecutors.directExecutor());
        }
        return "CameraX shutdown";
    }

    @Deprecated
    public static Context getContext() {
        return checkInitialized().mAppContext;
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (INSTANCE_LOCK) {
            CameraX cameraX = sInstance;
            z = cameraX != null && cameraX.isInitializedInternal();
        }
        return z;
    }

    public CameraFactory getCameraFactory() {
        CameraFactory cameraFactory = this.mCameraFactory;
        if (cameraFactory != null) {
            return cameraFactory;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    private static CameraX checkInitialized() {
        CameraX cameraXWaitInitialized = waitInitialized();
        Preconditions.checkState(cameraXWaitInitialized.isInitializedInternal(), "Must call CameraX.initialize() first");
        return cameraXWaitInitialized;
    }

    public static ListenableFuture<CameraX> getOrCreateInstance(Context context) {
        ListenableFuture<CameraX> instanceLocked;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (INSTANCE_LOCK) {
            boolean z = sConfigProvider != null;
            instanceLocked = getInstanceLocked();
            if (instanceLocked.isDone()) {
                try {
                    instanceLocked.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Unexpected thread interrupt. Should not be possible since future is already complete.", e);
                } catch (ExecutionException unused) {
                    shutdownLocked();
                    instanceLocked = null;
                }
            }
            if (instanceLocked == null) {
                if (!z) {
                    CameraXConfig.Provider configProvider = getConfigProvider(context);
                    if (configProvider == null) {
                        throw new IllegalStateException("CameraX is not configured properly. The most likely cause is you did not include a default implementation in your build such as 'camera-camera2'.");
                    }
                    configureInstanceLocked(configProvider);
                }
                initializeInstanceLocked(context);
                instanceLocked = getInstanceLocked();
            }
        }
        return instanceLocked;
    }

    private static CameraXConfig.Provider getConfigProvider(Context context) {
        ComponentCallbacks2 applicationFromContext = getApplicationFromContext(context);
        if (applicationFromContext instanceof CameraXConfig.Provider) {
            return (CameraXConfig.Provider) applicationFromContext;
        }
        try {
            return (CameraXConfig.Provider) Class.forName(context.getApplicationContext().getResources().getString(R.string.androidx_camera_default_config_provider)).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Resources.NotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            Logger.e(TAG, "Failed to retrieve default CameraXConfig.Provider from resources", e);
            return null;
        }
    }

    private static Application getApplicationFromContext(Context context) {
        for (Context applicationContext = context.getApplicationContext(); applicationContext instanceof ContextWrapper; applicationContext = ((ContextWrapper) applicationContext).getBaseContext()) {
            if (applicationContext instanceof Application) {
                return (Application) applicationContext;
            }
        }
        return null;
    }

    private static ListenableFuture<CameraX> getInstance() {
        ListenableFuture<CameraX> instanceLocked;
        synchronized (INSTANCE_LOCK) {
            instanceLocked = getInstanceLocked();
        }
        return instanceLocked;
    }

    private static ListenableFuture<CameraX> getInstanceLocked() {
        final CameraX cameraX = sInstance;
        if (cameraX == null) {
            return Futures.immediateFailedFuture(new IllegalStateException("Must call CameraX.initialize() first"));
        }
        return Futures.transform(sInitializeFuture, new Function() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return CameraX.lambda$getInstanceLocked$6(this.f$0, (Void) obj);
            }
        }, CameraXExecutors.directExecutor());
    }

    private static CameraX waitInitialized() {
        try {
            return getInstance().get(WAIT_INITIALIZED_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }

    public CameraDeviceSurfaceManager getCameraDeviceSurfaceManager() {
        CameraDeviceSurfaceManager cameraDeviceSurfaceManager = this.mSurfaceManager;
        if (cameraDeviceSurfaceManager != null) {
            return cameraDeviceSurfaceManager;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    public CameraRepository getCameraRepository() {
        return this.mCameraRepository;
    }

    public UseCaseConfigFactory getDefaultConfigFactory() {
        UseCaseConfigFactory useCaseConfigFactory = this.mDefaultConfigFactory;
        if (useCaseConfigFactory != null) {
            return useCaseConfigFactory;
        }
        throw new IllegalStateException("CameraX not initialized yet.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ListenableFuture<Void> initInternal(final Context context) {
        ListenableFuture<Void> future;
        synchronized (this.mInitializeLock) {
            Preconditions.checkState(this.mInitState == InternalInitState.UNINITIALIZED, "CameraX.initInternal() should only be called once per instance");
            this.mInitState = InternalInitState.INITIALIZING;
            future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda8
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m66lambda$initInternal$7$androidxcameracoreCameraX(context, completer);
                }
            });
        }
        return future;
    }

    /* renamed from: lambda$initInternal$7$androidx-camera-core-CameraX, reason: not valid java name */
    public /* synthetic */ Object m66lambda$initInternal$7$androidxcameracoreCameraX(Context context, CallbackToFutureAdapter.Completer completer) throws Exception {
        initAndRetryRecursively(this.mCameraExecutor, SystemClock.elapsedRealtime(), context, completer);
        return "CameraX initInternal";
    }

    private void initAndRetryRecursively(final Executor cameraExecutor, final long startMs, final Context context, final CallbackToFutureAdapter.Completer<Void> completer) {
        cameraExecutor.execute(new Runnable() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() throws InitializationException {
                this.f$0.m65lambda$initAndRetryRecursively$9$androidxcameracoreCameraX(context, cameraExecutor, completer, startMs);
            }
        });
    }

    /* renamed from: lambda$initAndRetryRecursively$9$androidx-camera-core-CameraX, reason: not valid java name */
    public /* synthetic */ void m65lambda$initAndRetryRecursively$9$androidxcameracoreCameraX(Context context, final Executor executor, final CallbackToFutureAdapter.Completer completer, final long j) throws InitializationException {
        try {
            Application applicationFromContext = getApplicationFromContext(context);
            this.mAppContext = applicationFromContext;
            if (applicationFromContext == null) {
                this.mAppContext = context.getApplicationContext();
            }
            CameraFactory.Provider cameraFactoryProvider = this.mCameraXConfig.getCameraFactoryProvider(null);
            if (cameraFactoryProvider == null) {
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraFactory."));
            }
            CameraThreadConfig cameraThreadConfigCreate = CameraThreadConfig.create(this.mCameraExecutor, this.mSchedulerHandler);
            CameraSelector availableCamerasLimiter = this.mCameraXConfig.getAvailableCamerasLimiter(null);
            this.mCameraFactory = cameraFactoryProvider.newInstance(this.mAppContext, cameraThreadConfigCreate, availableCamerasLimiter);
            CameraDeviceSurfaceManager.Provider deviceSurfaceManagerProvider = this.mCameraXConfig.getDeviceSurfaceManagerProvider(null);
            if (deviceSurfaceManagerProvider == null) {
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing CameraDeviceSurfaceManager."));
            }
            this.mSurfaceManager = deviceSurfaceManagerProvider.newInstance(this.mAppContext, this.mCameraFactory.getCameraManager(), this.mCameraFactory.getAvailableCameraIds());
            UseCaseConfigFactory.Provider useCaseConfigFactoryProvider = this.mCameraXConfig.getUseCaseConfigFactoryProvider(null);
            if (useCaseConfigFactoryProvider == null) {
                throw new InitializationException(new IllegalArgumentException("Invalid app configuration provided. Missing UseCaseConfigFactory."));
            }
            this.mDefaultConfigFactory = useCaseConfigFactoryProvider.newInstance(this.mAppContext);
            if (executor instanceof CameraExecutor) {
                ((CameraExecutor) executor).init(this.mCameraFactory);
            }
            this.mCameraRepository.init(this.mCameraFactory);
            if (DeviceQuirks.get(IncompleteCameraListQuirk.class) != null) {
                CameraValidator.validateCameras(this.mAppContext, this.mCameraRepository, availableCamerasLimiter);
            }
            setStateToInitialized();
            completer.set(null);
        } catch (InitializationException | CameraValidator.CameraIdListIncorrectException | RuntimeException e) {
            if (SystemClock.elapsedRealtime() - j < 2500) {
                Logger.w(TAG, "Retry init. Start time " + j + " current time " + SystemClock.elapsedRealtime(), e);
                HandlerCompat.postDelayed(this.mSchedulerHandler, new Runnable() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m64lambda$initAndRetryRecursively$8$androidxcameracoreCameraX(executor, j, completer);
                    }
                }, RETRY_TOKEN, RETRY_SLEEP_MILLIS);
                return;
            }
            setStateToInitialized();
            if (e instanceof CameraValidator.CameraIdListIncorrectException) {
                Logger.e(TAG, "The device might underreport the amount of the cameras. Finish the initialize task since we are already reaching the maximum number of retries.");
                completer.set(null);
            } else if (e instanceof InitializationException) {
                completer.setException(e);
            } else {
                completer.setException(new InitializationException(e));
            }
        }
    }

    /* renamed from: lambda$initAndRetryRecursively$8$androidx-camera-core-CameraX, reason: not valid java name */
    public /* synthetic */ void m64lambda$initAndRetryRecursively$8$androidxcameracoreCameraX(Executor executor, long j, CallbackToFutureAdapter.Completer completer) {
        initAndRetryRecursively(executor, j, this.mAppContext, completer);
    }

    private void setStateToInitialized() {
        synchronized (this.mInitializeLock) {
            this.mInitState = InternalInitState.INITIALIZED;
        }
    }

    private ListenableFuture<Void> shutdownInternal() {
        synchronized (this.mInitializeLock) {
            this.mSchedulerHandler.removeCallbacksAndMessages(RETRY_TOKEN);
            int i = AnonymousClass2.$SwitchMap$androidx$camera$core$CameraX$InternalInitState[this.mInitState.ordinal()];
            if (i == 1) {
                this.mInitState = InternalInitState.SHUTDOWN;
                return Futures.immediateFuture(null);
            }
            if (i == 2) {
                throw new IllegalStateException("CameraX could not be shutdown when it is initializing.");
            }
            if (i == 3) {
                this.mInitState = InternalInitState.SHUTDOWN;
                this.mShutdownInternalFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda6
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        return this.f$0.m68lambda$shutdownInternal$11$androidxcameracoreCameraX(completer);
                    }
                });
            }
            return this.mShutdownInternalFuture;
        }
    }

    /* renamed from: androidx.camera.core.CameraX$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$camera$core$CameraX$InternalInitState;

        static {
            int[] iArr = new int[InternalInitState.values().length];
            $SwitchMap$androidx$camera$core$CameraX$InternalInitState = iArr;
            try {
                iArr[InternalInitState.UNINITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$camera$core$CameraX$InternalInitState[InternalInitState.INITIALIZING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$camera$core$CameraX$InternalInitState[InternalInitState.INITIALIZED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$camera$core$CameraX$InternalInitState[InternalInitState.SHUTDOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: lambda$shutdownInternal$11$androidx-camera-core-CameraX, reason: not valid java name */
    public /* synthetic */ Object m68lambda$shutdownInternal$11$androidxcameracoreCameraX(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mCameraRepository.deinit().addListener(new Runnable() { // from class: androidx.camera.core.CameraX$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m67lambda$shutdownInternal$10$androidxcameracoreCameraX(completer);
            }
        }, this.mCameraExecutor);
        return "CameraX shutdownInternal";
    }

    /* renamed from: lambda$shutdownInternal$10$androidx-camera-core-CameraX, reason: not valid java name */
    public /* synthetic */ void m67lambda$shutdownInternal$10$androidxcameracoreCameraX(CallbackToFutureAdapter.Completer completer) {
        if (this.mSchedulerThread != null) {
            Executor executor = this.mCameraExecutor;
            if (executor instanceof CameraExecutor) {
                ((CameraExecutor) executor).deinit();
            }
            this.mSchedulerThread.quit();
            completer.set(null);
        }
    }

    private boolean isInitializedInternal() {
        boolean z;
        synchronized (this.mInitializeLock) {
            z = this.mInitState == InternalInitState.INITIALIZED;
        }
        return z;
    }
}
