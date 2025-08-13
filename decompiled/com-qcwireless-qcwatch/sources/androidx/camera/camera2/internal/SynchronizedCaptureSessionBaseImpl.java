package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener;
import androidx.camera.camera2.internal.compat.CameraCaptureSessionCompat;
import androidx.camera.camera2.internal.compat.CameraDeviceCompat;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.DeferrableSurfaces;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
class SynchronizedCaptureSessionBaseImpl extends SynchronizedCaptureSession.StateCallback implements SynchronizedCaptureSession, SynchronizedCaptureSessionOpener.OpenerImpl {
    private static final String TAG = "SyncCaptureSessionBase";
    CameraCaptureSessionCompat mCameraCaptureSessionCompat;
    final CaptureSessionRepository mCaptureSessionRepository;
    SynchronizedCaptureSession.StateCallback mCaptureSessionStateCallback;
    final Handler mCompatHandler;
    final Executor mExecutor;
    CallbackToFutureAdapter.Completer<Void> mOpenCaptureSessionCompleter;
    ListenableFuture<Void> mOpenCaptureSessionFuture;
    private final ScheduledExecutorService mScheduledExecutorService;
    private ListenableFuture<List<Surface>> mStartingSurface;
    final Object mLock = new Object();
    private List<DeferrableSurface> mHeldDeferrableSurfaces = null;
    private boolean mClosed = false;
    private boolean mOpenerDisabled = false;
    private boolean mSessionFinished = false;

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public SynchronizedCaptureSession.StateCallback getStateCallback() {
        return this;
    }

    SynchronizedCaptureSessionBaseImpl(CaptureSessionRepository repository, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler compatHandler) {
        this.mCaptureSessionRepository = repository;
        this.mCompatHandler = compatHandler;
        this.mExecutor = executor;
        this.mScheduledExecutorService = scheduledExecutorService;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public ListenableFuture<Void> getSynchronizedBlocker(String feature) {
        return Futures.immediateFuture(null);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public ListenableFuture<Void> openCaptureSession(CameraDevice cameraDevice, final SessionConfigurationCompat sessionConfigurationCompat, final List<DeferrableSurface> deferrableSurfaces) {
        synchronized (this.mLock) {
            if (this.mOpenerDisabled) {
                return Futures.immediateFailedFuture(new CancellationException("Opener is disabled"));
            }
            this.mCaptureSessionRepository.onCreateCaptureSession(this);
            final CameraDeviceCompat cameraDeviceCompat = CameraDeviceCompat.toCameraDeviceCompat(cameraDevice, this.mCompatHandler);
            ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda1
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m43x73bfea20(deferrableSurfaces, cameraDeviceCompat, sessionConfigurationCompat, completer);
                }
            });
            this.mOpenCaptureSessionFuture = future;
            Futures.addCallback(future, new FutureCallback<Void>() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl.1
                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onSuccess(Void result) {
                }

                @Override // androidx.camera.core.impl.utils.futures.FutureCallback
                public void onFailure(Throwable t) {
                    SynchronizedCaptureSessionBaseImpl.this.finishClose();
                    SynchronizedCaptureSessionBaseImpl.this.mCaptureSessionRepository.onCaptureSessionConfigureFail(SynchronizedCaptureSessionBaseImpl.this);
                }
            }, CameraXExecutors.directExecutor());
            return Futures.nonCancellationPropagating(this.mOpenCaptureSessionFuture);
        }
    }

    /* renamed from: lambda$openCaptureSession$0$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl, reason: not valid java name */
    public /* synthetic */ Object m43x73bfea20(List list, CameraDeviceCompat cameraDeviceCompat, SessionConfigurationCompat sessionConfigurationCompat, CallbackToFutureAdapter.Completer completer) throws Exception {
        String str;
        synchronized (this.mLock) {
            holdDeferrableSurfaces(list);
            Preconditions.checkState(this.mOpenCaptureSessionCompleter == null, "The openCaptureSessionCompleter can only set once!");
            this.mOpenCaptureSessionCompleter = completer;
            cameraDeviceCompat.createCaptureSession(sessionConfigurationCompat);
            str = "openCaptureSession[session=" + this + "]";
        }
        return str;
    }

    boolean isCameraCaptureSessionOpen() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mOpenCaptureSessionFuture != null;
        }
        return z;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public SessionConfigurationCompat createSessionConfigurationCompat(int sessionType, List<OutputConfigurationCompat> outputsCompat, SynchronizedCaptureSession.StateCallback stateCallback) {
        this.mCaptureSessionStateCallback = stateCallback;
        return new SessionConfigurationCompat(sessionType, outputsCompat, getExecutor(), new CameraCaptureSession.StateCallback() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl.2
            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onReady(CameraCaptureSession session) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onReady(synchronizedCaptureSessionBaseImpl);
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onActive(CameraCaptureSession session) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onActive(synchronizedCaptureSessionBaseImpl);
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onCaptureQueueEmpty(CameraCaptureSession session) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onCaptureQueueEmpty(synchronizedCaptureSessionBaseImpl);
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onSurfacePrepared(CameraCaptureSession session, Surface surface) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onSurfacePrepared(synchronizedCaptureSessionBaseImpl, surface);
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onConfigured(CameraCaptureSession session) {
                CallbackToFutureAdapter.Completer<Void> completer;
                try {
                    SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                    SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                    synchronizedCaptureSessionBaseImpl.onConfigured(synchronizedCaptureSessionBaseImpl);
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        completer = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                    }
                    completer.set(null);
                } catch (Throwable th) {
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        CallbackToFutureAdapter.Completer<Void> completer2 = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                        completer2.set(null);
                        throw th;
                    }
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onConfigureFailed(CameraCaptureSession session) {
                CallbackToFutureAdapter.Completer<Void> completer;
                try {
                    SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                    SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                    synchronizedCaptureSessionBaseImpl.onConfigureFailed(synchronizedCaptureSessionBaseImpl);
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        completer = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                    }
                    completer.setException(new IllegalStateException("onConfigureFailed"));
                } catch (Throwable th) {
                    synchronized (SynchronizedCaptureSessionBaseImpl.this.mLock) {
                        Preconditions.checkNotNull(SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter, "OpenCaptureSession completer should not null");
                        CallbackToFutureAdapter.Completer<Void> completer2 = SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter;
                        SynchronizedCaptureSessionBaseImpl.this.mOpenCaptureSessionCompleter = null;
                        completer2.setException(new IllegalStateException("onConfigureFailed"));
                        throw th;
                    }
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
            public void onClosed(CameraCaptureSession session) {
                SynchronizedCaptureSessionBaseImpl.this.createCaptureSessionCompat(session);
                SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl = SynchronizedCaptureSessionBaseImpl.this;
                synchronizedCaptureSessionBaseImpl.onClosed(synchronizedCaptureSessionBaseImpl);
            }
        });
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public Executor getExecutor() {
        return this.mExecutor;
    }

    void createCaptureSessionCompat(CameraCaptureSession session) {
        if (this.mCameraCaptureSessionCompat == null) {
            this.mCameraCaptureSessionCompat = CameraCaptureSessionCompat.toCameraCaptureSessionCompat(session, this.mCompatHandler);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public ListenableFuture<List<Surface>> startWithDeferrableSurface(final List<DeferrableSurface> deferrableSurfaces, long timeout) {
        synchronized (this.mLock) {
            if (this.mOpenerDisabled) {
                return Futures.immediateFailedFuture(new CancellationException("Opener is disabled"));
            }
            FutureChain futureChainTransformAsync = FutureChain.from(DeferrableSurfaces.surfaceListWithTimeout(deferrableSurfaces, false, timeout, getExecutor(), this.mScheduledExecutorService)).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda0
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return this.f$0.m44x2071bdd2(deferrableSurfaces, (List) obj);
                }
            }, getExecutor());
            this.mStartingSurface = futureChainTransformAsync;
            return Futures.nonCancellationPropagating(futureChainTransformAsync);
        }
    }

    /* renamed from: lambda$startWithDeferrableSurface$1$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl, reason: not valid java name */
    public /* synthetic */ ListenableFuture m44x2071bdd2(List list, List list2) throws Exception {
        Logger.d(TAG, "[" + this + "] getSurface...done");
        if (list2.contains(null)) {
            return Futures.immediateFailedFuture(new DeferrableSurface.SurfaceClosedException("Surface closed", (DeferrableSurface) list.get(list2.indexOf(null))));
        }
        if (list2.isEmpty()) {
            return Futures.immediateFailedFuture(new IllegalArgumentException("Unable to open capture session without surfaces"));
        }
        return Futures.immediateFuture(list2);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public boolean stop() {
        boolean z;
        try {
            synchronized (this.mLock) {
                if (!this.mOpenerDisabled) {
                    ListenableFuture<List<Surface>> listenableFuture = this.mStartingSurface;
                    listenableFuture = listenableFuture != null ? listenableFuture : null;
                    this.mOpenerDisabled = true;
                }
                z = !isCameraCaptureSessionOpen();
            }
            return z;
        } finally {
            if (listenableFuture != null) {
                listenableFuture.cancel(true);
            }
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public CameraCaptureSessionCompat toCameraCaptureSessionCompat() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat);
        return this.mCameraCaptureSessionCompat;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public CameraDevice getDevice() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat);
        return this.mCameraCaptureSessionCompat.toCameraCaptureSession().getDevice();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int captureSingleRequest(CaptureRequest request, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureSingleRequest(request, getExecutor(), listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int captureBurstRequests(List<CaptureRequest> requests, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureBurstRequests(requests, getExecutor(), listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setSingleRepeatingRequest(CaptureRequest request, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setSingleRepeatingRequest(request, getExecutor(), listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setRepeatingBurstRequests(List<CaptureRequest> requests, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setRepeatingBurstRequests(requests, getExecutor(), listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int captureSingleRequest(CaptureRequest request, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureSingleRequest(request, executor, listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int captureBurstRequests(List<CaptureRequest> requests, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.captureBurstRequests(requests, executor, listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setSingleRepeatingRequest(CaptureRequest request, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setSingleRepeatingRequest(request, executor, listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setRepeatingBurstRequests(List<CaptureRequest> requests, Executor executor, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        return this.mCameraCaptureSessionCompat.setRepeatingBurstRequests(requests, executor, listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void stopRepeating() throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        this.mCameraCaptureSessionCompat.toCameraCaptureSession().stopRepeating();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void abortCaptures() throws CameraAccessException {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        this.mCameraCaptureSessionCompat.toCameraCaptureSession().abortCaptures();
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void close() {
        Preconditions.checkNotNull(this.mCameraCaptureSessionCompat, "Need to call openCaptureSession before using this API.");
        this.mCaptureSessionRepository.onCaptureSessionClosing(this);
        this.mCameraCaptureSessionCompat.toCameraCaptureSession().close();
        getExecutor().execute(new Runnable() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m40x23111ba6();
            }
        });
    }

    /* renamed from: lambda$close$2$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl, reason: not valid java name */
    public /* synthetic */ void m40x23111ba6() {
        onSessionFinished(this);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onReady(SynchronizedCaptureSession session) {
        this.mCaptureSessionStateCallback.onReady(session);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onActive(SynchronizedCaptureSession session) {
        this.mCaptureSessionStateCallback.onActive(session);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onCaptureQueueEmpty(SynchronizedCaptureSession session) {
        this.mCaptureSessionStateCallback.onCaptureQueueEmpty(session);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onSurfacePrepared(SynchronizedCaptureSession session, Surface surface) {
        this.mCaptureSessionStateCallback.onSurfacePrepared(session, surface);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigured(SynchronizedCaptureSession session) {
        this.mCaptureSessionRepository.onCaptureSessionCreated(this);
        this.mCaptureSessionStateCallback.onConfigured(session);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigureFailed(SynchronizedCaptureSession session) {
        finishClose();
        this.mCaptureSessionRepository.onCaptureSessionConfigureFail(this);
        this.mCaptureSessionStateCallback.onConfigureFailed(session);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onClosed(final SynchronizedCaptureSession session) {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (this.mClosed) {
                listenableFuture = null;
            } else {
                this.mClosed = true;
                Preconditions.checkNotNull(this.mOpenCaptureSessionFuture, "Need to call openCaptureSession before using this API.");
                listenableFuture = this.mOpenCaptureSessionFuture;
            }
        }
        finishClose();
        if (listenableFuture != null) {
            listenableFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m41x46877ef4(session);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    /* renamed from: lambda$onClosed$3$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl, reason: not valid java name */
    public /* synthetic */ void m41x46877ef4(SynchronizedCaptureSession synchronizedCaptureSession) {
        this.mCaptureSessionRepository.onCaptureSessionClosed(this);
        onSessionFinished(synchronizedCaptureSession);
        this.mCaptureSessionStateCallback.onClosed(synchronizedCaptureSession);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    void onSessionFinished(final SynchronizedCaptureSession session) {
        ListenableFuture<Void> listenableFuture;
        synchronized (this.mLock) {
            if (this.mSessionFinished) {
                listenableFuture = null;
            } else {
                this.mSessionFinished = true;
                Preconditions.checkNotNull(this.mOpenCaptureSessionFuture, "Need to call openCaptureSession before using this API.");
                listenableFuture = this.mOpenCaptureSessionFuture;
            }
        }
        if (listenableFuture != null) {
            listenableFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m42x5333f8b9(session);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    /* renamed from: lambda$onSessionFinished$4$androidx-camera-camera2-internal-SynchronizedCaptureSessionBaseImpl, reason: not valid java name */
    public /* synthetic */ void m42x5333f8b9(SynchronizedCaptureSession synchronizedCaptureSession) {
        this.mCaptureSessionStateCallback.onSessionFinished(synchronizedCaptureSession);
    }

    void holdDeferrableSurfaces(List<DeferrableSurface> deferrableSurfaces) throws DeferrableSurface.SurfaceClosedException {
        synchronized (this.mLock) {
            releaseDeferrableSurfaces();
            DeferrableSurfaces.incrementAll(deferrableSurfaces);
            this.mHeldDeferrableSurfaces = deferrableSurfaces;
        }
    }

    void releaseDeferrableSurfaces() {
        synchronized (this.mLock) {
            List<DeferrableSurface> list = this.mHeldDeferrableSurfaces;
            if (list != null) {
                DeferrableSurfaces.decrementAll(list);
                this.mHeldDeferrableSurfaces = null;
            }
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void finishClose() {
        releaseDeferrableSurfaces();
    }
}
