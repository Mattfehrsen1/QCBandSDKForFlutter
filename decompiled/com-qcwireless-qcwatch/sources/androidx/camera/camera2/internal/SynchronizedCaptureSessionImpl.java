package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
class SynchronizedCaptureSessionImpl extends SynchronizedCaptureSessionBaseImpl {
    private static final String TAG = "SyncCaptureSessionImpl";
    private final CameraCaptureSession.CaptureCallback mCaptureCallback;
    private List<DeferrableSurface> mDeferrableSurfaces;
    private final Set<String> mEnabledFeature;
    private boolean mHasSubmittedRepeating;
    private final Object mObjectLock;
    ListenableFuture<Void> mOpeningCaptureSession;
    CallbackToFutureAdapter.Completer<Void> mStartStreamingCompleter;
    private final ListenableFuture<Void> mStartStreamingFuture;
    ListenableFuture<List<Surface>> mStartingSurface;

    SynchronizedCaptureSessionImpl(Set<String> enabledFeature, CaptureSessionRepository repository, Executor executor, ScheduledExecutorService scheduledExecutorService, Handler compatHandler) {
        super(repository, executor, scheduledExecutorService, compatHandler);
        this.mObjectLock = new Object();
        this.mCaptureCallback = new CameraCaptureSession.CaptureCallback() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionImpl.1
            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
                if (SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter != null) {
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter.set(null);
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter = null;
                }
            }

            @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
            public void onCaptureSequenceAborted(CameraCaptureSession session, int sequenceId) {
                if (SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter != null) {
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter.setCancelled();
                    SynchronizedCaptureSessionImpl.this.mStartStreamingCompleter = null;
                }
            }
        };
        this.mEnabledFeature = enabledFeature;
        if (enabledFeature.contains("wait_for_request")) {
            this.mStartStreamingFuture = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda1
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    return this.f$0.m46x847c421b(completer);
                }
            });
        } else {
            this.mStartStreamingFuture = Futures.immediateFuture(null);
        }
    }

    /* renamed from: lambda$new$0$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl, reason: not valid java name */
    public /* synthetic */ Object m46x847c421b(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mStartStreamingCompleter = completer;
        return "StartStreamingFuture[session=" + this + "]";
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public ListenableFuture<Void> openCaptureSession(final CameraDevice cameraDevice, final SessionConfigurationCompat sessionConfigurationCompat, final List<DeferrableSurface> deferrableSurfaces) {
        ListenableFuture<Void> listenableFutureNonCancellationPropagating;
        synchronized (this.mObjectLock) {
            FutureChain futureChainTransformAsync = FutureChain.from(Futures.successfulAsList(getBlockerFuture("wait_for_request", this.mCaptureSessionRepository.getClosingCaptureSession()))).transformAsync(new AsyncFunction() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda0
                @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return this.f$0.m47x5bc4d730(cameraDevice, sessionConfigurationCompat, deferrableSurfaces, (List) obj);
                }
            }, CameraXExecutors.directExecutor());
            this.mOpeningCaptureSession = futureChainTransformAsync;
            listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(futureChainTransformAsync);
        }
        return listenableFutureNonCancellationPropagating;
    }

    /* renamed from: lambda$openCaptureSession$1$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl, reason: not valid java name */
    public /* synthetic */ ListenableFuture m47x5bc4d730(CameraDevice cameraDevice, SessionConfigurationCompat sessionConfigurationCompat, List list, List list2) throws Exception {
        return super.openCaptureSession(cameraDevice, sessionConfigurationCompat, list);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public ListenableFuture<Void> getSynchronizedBlocker(String feature) {
        feature.hashCode();
        if (feature.equals("wait_for_request")) {
            return Futures.nonCancellationPropagating(this.mStartStreamingFuture);
        }
        return super.getSynchronizedBlocker(feature);
    }

    private List<ListenableFuture<Void>> getBlockerFuture(String feature, List<SynchronizedCaptureSession> sessions) {
        ArrayList arrayList = new ArrayList();
        Iterator<SynchronizedCaptureSession> it = sessions.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSynchronizedBlocker(feature));
        }
        return arrayList;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public ListenableFuture<List<Surface>> startWithDeferrableSurface(List<DeferrableSurface> deferrableSurfaces, long timeout) {
        ListenableFuture<List<Surface>> listenableFutureNonCancellationPropagating;
        synchronized (this.mObjectLock) {
            this.mDeferrableSurfaces = deferrableSurfaces;
            listenableFutureNonCancellationPropagating = Futures.nonCancellationPropagating(super.startWithDeferrableSurface(deferrableSurfaces, timeout));
        }
        return listenableFutureNonCancellationPropagating;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSessionOpener.OpenerImpl
    public boolean stop() {
        boolean zStop;
        synchronized (this.mObjectLock) {
            if (isCameraCaptureSessionOpen()) {
                closeConfiguredDeferrableSurfaces();
            } else {
                ListenableFuture<Void> listenableFuture = this.mOpeningCaptureSession;
                if (listenableFuture != null) {
                    listenableFuture.cancel(true);
                }
                ListenableFuture<List<Surface>> listenableFuture2 = this.mStartingSurface;
                if (listenableFuture2 != null) {
                    listenableFuture2.cancel(true);
                }
            }
            zStop = super.stop();
        }
        return zStop;
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public int setSingleRepeatingRequest(CaptureRequest request, CameraCaptureSession.CaptureCallback listener) throws CameraAccessException {
        int singleRepeatingRequest;
        if (this.mEnabledFeature.contains("wait_for_request")) {
            synchronized (this.mObjectLock) {
                this.mHasSubmittedRepeating = true;
                singleRepeatingRequest = super.setSingleRepeatingRequest(request, Camera2CaptureCallbacks.createComboCallback(this.mCaptureCallback, listener));
            }
            return singleRepeatingRequest;
        }
        return super.setSingleRepeatingRequest(request, listener);
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onConfigured(SynchronizedCaptureSession session) {
        SynchronizedCaptureSession next;
        SynchronizedCaptureSession next2;
        debugLog("Session onConfigured()");
        if (this.mEnabledFeature.contains("force_close")) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<SynchronizedCaptureSession> it = this.mCaptureSessionRepository.getCreatingCaptureSessions().iterator();
            while (it.hasNext() && (next2 = it.next()) != session) {
                linkedHashSet.add(next2);
            }
            forceOnConfigureFailed(linkedHashSet);
        }
        super.onConfigured(session);
        if (this.mEnabledFeature.contains("force_close")) {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator<SynchronizedCaptureSession> it2 = this.mCaptureSessionRepository.getCaptureSessions().iterator();
            while (it2.hasNext() && (next = it2.next()) != session) {
                linkedHashSet2.add(next);
            }
            forceOnClosed(linkedHashSet2);
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession
    public void close() {
        debugLog("Session call close()");
        if (this.mEnabledFeature.contains("wait_for_request")) {
            synchronized (this.mObjectLock) {
                if (!this.mHasSubmittedRepeating) {
                    this.mStartStreamingFuture.cancel(true);
                }
            }
        }
        this.mStartStreamingFuture.addListener(new Runnable() { // from class: androidx.camera.camera2.internal.SynchronizedCaptureSessionImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m45xfa0843b5();
            }
        }, getExecutor());
    }

    /* renamed from: lambda$close$2$androidx-camera-camera2-internal-SynchronizedCaptureSessionImpl, reason: not valid java name */
    public /* synthetic */ void m45xfa0843b5() {
        debugLog("Session call super.close()");
        super.close();
    }

    void closeConfiguredDeferrableSurfaces() {
        synchronized (this.mObjectLock) {
            if (this.mDeferrableSurfaces == null) {
                debugLog("deferrableSurface == null, maybe forceClose, skip close");
                return;
            }
            if (this.mEnabledFeature.contains("deferrableSurface_close")) {
                Iterator<DeferrableSurface> it = this.mDeferrableSurfaces.iterator();
                while (it.hasNext()) {
                    it.next().close();
                }
                debugLog("deferrableSurface closed");
            }
        }
    }

    @Override // androidx.camera.camera2.internal.SynchronizedCaptureSessionBaseImpl, androidx.camera.camera2.internal.SynchronizedCaptureSession.StateCallback
    public void onClosed(SynchronizedCaptureSession session) {
        closeConfiguredDeferrableSurfaces();
        debugLog("onClosed()");
        super.onClosed(session);
    }

    static void forceOnClosed(Set<SynchronizedCaptureSession> sessions) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : sessions) {
            synchronizedCaptureSession.getStateCallback().onClosed(synchronizedCaptureSession);
        }
    }

    private void forceOnConfigureFailed(Set<SynchronizedCaptureSession> sessions) {
        for (SynchronizedCaptureSession synchronizedCaptureSession : sessions) {
            synchronizedCaptureSession.getStateCallback().onConfigureFailed(synchronizedCaptureSession);
        }
    }

    void debugLog(String message) {
        Logger.d(TAG, "[" + this + "] " + message);
    }
}
