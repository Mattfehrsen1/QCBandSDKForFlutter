package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class TorchControl {
    static final int DEFAULT_TORCH_STATE = 0;
    private static final String TAG = "TorchControl";
    private final Camera2CameraControlImpl mCamera2CameraControlImpl;
    private final Camera2CameraControlImpl.CaptureResultListener mCaptureResultListener;
    CallbackToFutureAdapter.Completer<Void> mEnableTorchCompleter;
    private final Executor mExecutor;
    private final boolean mHasFlashUnit;
    private boolean mIsActive;
    boolean mTargetTorchEnabled;
    private final MutableLiveData<Integer> mTorchState;

    TorchControl(Camera2CameraControlImpl camera2CameraControlImpl, CameraCharacteristicsCompat cameraCharacteristics, Executor executor) {
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.TorchControl.1
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public boolean onCaptureResult(TotalCaptureResult captureResult) {
                if (TorchControl.this.mEnableTorchCompleter != null) {
                    Integer num = (Integer) captureResult.getRequest().get(CaptureRequest.FLASH_MODE);
                    if ((num != null && num.intValue() == 2) == TorchControl.this.mTargetTorchEnabled) {
                        TorchControl.this.mEnableTorchCompleter.set(null);
                        TorchControl.this.mEnableTorchCompleter = null;
                    }
                }
                return false;
            }
        };
        this.mCaptureResultListener = captureResultListener;
        this.mCamera2CameraControlImpl = camera2CameraControlImpl;
        this.mExecutor = executor;
        Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        this.mHasFlashUnit = bool != null && bool.booleanValue();
        this.mTorchState = new MutableLiveData<>(0);
        camera2CameraControlImpl.addCaptureResultListener(captureResultListener);
    }

    void setActive(boolean isActive) {
        if (this.mIsActive == isActive) {
            return;
        }
        this.mIsActive = isActive;
        if (isActive) {
            return;
        }
        if (this.mTargetTorchEnabled) {
            this.mTargetTorchEnabled = false;
            this.mCamera2CameraControlImpl.enableTorchInternal(false);
            setLiveDataValue(this.mTorchState, 0);
        }
        CallbackToFutureAdapter.Completer<Void> completer = this.mEnableTorchCompleter;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            this.mEnableTorchCompleter = null;
        }
    }

    ListenableFuture<Void> enableTorch(final boolean z) {
        if (!this.mHasFlashUnit) {
            Logger.d(TAG, "Unable to enableTorch due to there is no flash unit.");
            return Futures.immediateFailedFuture(new IllegalStateException("No flash unit"));
        }
        setLiveDataValue(this.mTorchState, Integer.valueOf(z ? 1 : 0));
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                return this.f$0.m49xbd10d892(z, completer);
            }
        });
    }

    /* renamed from: lambda$enableTorch$1$androidx-camera-camera2-internal-TorchControl, reason: not valid java name */
    public /* synthetic */ Object m49xbd10d892(final boolean z, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new Runnable() { // from class: androidx.camera.camera2.internal.TorchControl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m48x4796b251(completer, z);
            }
        });
        return "enableTorch: " + z;
    }

    LiveData<Integer> getTorchState() {
        return this.mTorchState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: enableTorchInternal, reason: merged with bridge method [inline-methods] */
    public void m48x4796b251(CallbackToFutureAdapter.Completer<Void> completer, boolean z) {
        if (!this.mIsActive) {
            setLiveDataValue(this.mTorchState, 0);
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
            return;
        }
        this.mTargetTorchEnabled = z;
        this.mCamera2CameraControlImpl.enableTorchInternal(z);
        setLiveDataValue(this.mTorchState, Integer.valueOf(z ? 1 : 0));
        CallbackToFutureAdapter.Completer<Void> completer2 = this.mEnableTorchCompleter;
        if (completer2 != null) {
            completer2.setException(new CameraControl.OperationCanceledException("There is a new enableTorch being set"));
        }
        this.mEnableTorchCompleter = completer;
    }

    private <T> void setLiveDataValue(MutableLiveData<T> liveData, T value) {
        if (Threads.isMainThread()) {
            liveData.setValue(value);
        } else {
            liveData.postValue(value);
        }
    }
}
