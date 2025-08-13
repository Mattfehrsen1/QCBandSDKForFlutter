package androidx.camera.view;

import androidx.arch.core.util.Function;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.view.PreviewView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class PreviewStreamStateObserver implements Observable.Observer<CameraInternal.State> {
    private static final String TAG = "StreamStateObserver";
    private final CameraInfoInternal mCameraInfoInternal;
    ListenableFuture<Void> mFlowFuture;
    private boolean mHasStartedPreviewStreamFlow = false;
    private PreviewView.StreamState mPreviewStreamState;
    private final MutableLiveData<PreviewView.StreamState> mPreviewStreamStateLiveData;
    private final PreviewViewImplementation mPreviewViewImplementation;

    PreviewStreamStateObserver(CameraInfoInternal cameraInfoInternal, MutableLiveData<PreviewView.StreamState> previewStreamLiveData, PreviewViewImplementation implementation) {
        this.mCameraInfoInternal = cameraInfoInternal;
        this.mPreviewStreamStateLiveData = previewStreamLiveData;
        this.mPreviewViewImplementation = implementation;
        synchronized (this) {
            this.mPreviewStreamState = previewStreamLiveData.getValue();
        }
    }

    @Override // androidx.camera.core.impl.Observable.Observer
    public void onNewData(CameraInternal.State value) {
        if (value == CameraInternal.State.CLOSING || value == CameraInternal.State.CLOSED || value == CameraInternal.State.RELEASING || value == CameraInternal.State.RELEASED) {
            updatePreviewStreamState(PreviewView.StreamState.IDLE);
            if (this.mHasStartedPreviewStreamFlow) {
                this.mHasStartedPreviewStreamFlow = false;
                cancelFlow();
                return;
            }
            return;
        }
        if ((value == CameraInternal.State.OPENING || value == CameraInternal.State.OPEN || value == CameraInternal.State.PENDING_OPEN) && !this.mHasStartedPreviewStreamFlow) {
            startPreviewStreamStateFlow(this.mCameraInfoInternal);
            this.mHasStartedPreviewStreamFlow = true;
        }
    }

    @Override // androidx.camera.core.impl.Observable.Observer
    public void onError(Throwable t) {
        clear();
        updatePreviewStreamState(PreviewView.StreamState.IDLE);
    }

    void clear() {
        cancelFlow();
    }

    private void cancelFlow() {
        ListenableFuture<Void> listenableFuture = this.mFlowFuture;
        if (listenableFuture != null) {
            listenableFuture.cancel(false);
            this.mFlowFuture = null;
        }
    }

    private void startPreviewStreamStateFlow(final CameraInfo cameraInfo) {
        updatePreviewStreamState(PreviewView.StreamState.IDLE);
        final ArrayList arrayList = new ArrayList();
        FutureChain futureChainTransform = FutureChain.from(waitForCaptureResult(cameraInfo, arrayList)).transformAsync(new AsyncFunction() { // from class: androidx.camera.view.PreviewStreamStateObserver$$ExternalSyntheticLambda1
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return this.f$0.m115xafb6b767((Void) obj);
            }
        }, CameraXExecutors.directExecutor()).transform(new Function() { // from class: androidx.camera.view.PreviewStreamStateObserver$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m116xaf405168((Void) obj);
            }
        }, CameraXExecutors.directExecutor());
        this.mFlowFuture = futureChainTransform;
        Futures.addCallback(futureChainTransform, new FutureCallback<Void>() { // from class: androidx.camera.view.PreviewStreamStateObserver.1
            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onSuccess(Void result) {
                PreviewStreamStateObserver.this.mFlowFuture = null;
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable t) {
                PreviewStreamStateObserver.this.mFlowFuture = null;
                if (arrayList.isEmpty()) {
                    return;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((CameraInfoInternal) cameraInfo).removeSessionCaptureCallback((CameraCaptureCallback) it.next());
                }
                arrayList.clear();
            }
        }, CameraXExecutors.directExecutor());
    }

    /* renamed from: lambda$startPreviewStreamStateFlow$0$androidx-camera-view-PreviewStreamStateObserver, reason: not valid java name */
    public /* synthetic */ ListenableFuture m115xafb6b767(Void r1) throws Exception {
        return this.mPreviewViewImplementation.waitForNextFrame();
    }

    /* renamed from: lambda$startPreviewStreamStateFlow$1$androidx-camera-view-PreviewStreamStateObserver, reason: not valid java name */
    public /* synthetic */ Void m116xaf405168(Void r1) {
        updatePreviewStreamState(PreviewView.StreamState.STREAMING);
        return null;
    }

    void updatePreviewStreamState(PreviewView.StreamState streamState) {
        synchronized (this) {
            if (this.mPreviewStreamState.equals(streamState)) {
                return;
            }
            this.mPreviewStreamState = streamState;
            Logger.d(TAG, "Update Preview stream state to " + streamState);
            this.mPreviewStreamStateLiveData.postValue(streamState);
        }
    }

    private ListenableFuture<Void> waitForCaptureResult(final CameraInfo cameraInfo, final List<CameraCaptureCallback> callbacksToClear) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.view.PreviewStreamStateObserver$$ExternalSyntheticLambda2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m117xb52cbf9(cameraInfo, callbacksToClear, completer);
            }
        });
    }

    /* renamed from: lambda$waitForCaptureResult$2$androidx-camera-view-PreviewStreamStateObserver, reason: not valid java name */
    public /* synthetic */ Object m117xb52cbf9(final CameraInfo cameraInfo, List list, final CallbackToFutureAdapter.Completer completer) throws Exception {
        CameraCaptureCallback cameraCaptureCallback = new CameraCaptureCallback() { // from class: androidx.camera.view.PreviewStreamStateObserver.2
            @Override // androidx.camera.core.impl.CameraCaptureCallback
            public void onCaptureCompleted(CameraCaptureResult result) {
                completer.set(null);
                ((CameraInfoInternal) cameraInfo).removeSessionCaptureCallback(this);
            }
        };
        list.add(cameraCaptureCallback);
        ((CameraInfoInternal) cameraInfo).addSessionCaptureCallback(CameraXExecutors.directExecutor(), cameraCaptureCallback);
        return "waitForCaptureResult";
    }
}
