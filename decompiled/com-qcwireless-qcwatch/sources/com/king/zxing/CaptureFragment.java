package com.king.zxing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.camera.view.PreviewView;
import androidx.fragment.app.Fragment;
import com.google.zxing.Result;
import com.hjq.permissions.Permission;
import com.king.zxing.CameraScan;
import com.king.zxing.util.LogUtils;
import com.king.zxing.util.PermissionUtils;

/* loaded from: classes3.dex */
public class CaptureFragment extends Fragment implements CameraScan.OnScanResultCallback {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 134;
    protected View ivFlashlight;
    private CameraScan mCameraScan;
    private View mRootView;
    protected PreviewView previewView;
    protected ViewfinderView viewfinderView;

    public boolean isContentView(int i) {
        return true;
    }

    @Override // com.king.zxing.CameraScan.OnScanResultCallback
    public boolean onScanResultCallback(Result result) {
        return false;
    }

    @Override // com.king.zxing.CameraScan.OnScanResultCallback
    public /* synthetic */ void onScanResultFailure() {
        CameraScan.OnScanResultCallback.CC.$default$onScanResultFailure(this);
    }

    public static CaptureFragment newInstance() {
        Bundle bundle = new Bundle();
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setArguments(bundle);
        return captureFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (isContentView(getLayoutId())) {
            this.mRootView = createRootView(layoutInflater, viewGroup);
        }
        initUI();
        return this.mRootView;
    }

    public void initUI() {
        this.previewView = (PreviewView) this.mRootView.findViewById(getPreviewViewId());
        int viewfinderViewId = getViewfinderViewId();
        if (viewfinderViewId != 0) {
            this.viewfinderView = (ViewfinderView) this.mRootView.findViewById(viewfinderViewId);
        }
        int flashlightId = getFlashlightId();
        if (flashlightId != 0) {
            View viewFindViewById = this.mRootView.findViewById(flashlightId);
            this.ivFlashlight = viewFindViewById;
            if (viewFindViewById != null) {
                viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.king.zxing.CaptureFragment$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.m230lambda$initUI$0$comkingzxingCaptureFragment(view);
                    }
                });
            }
        }
        initCameraScan();
        startCamera();
    }

    /* renamed from: lambda$initUI$0$com-king-zxing-CaptureFragment, reason: not valid java name */
    public /* synthetic */ void m230lambda$initUI$0$comkingzxingCaptureFragment(View view) {
        onClickFlashlight();
    }

    protected void onClickFlashlight() {
        toggleTorchState();
    }

    public void initCameraScan() {
        DefaultCameraScan defaultCameraScan = new DefaultCameraScan(this, this.previewView);
        this.mCameraScan = defaultCameraScan;
        defaultCameraScan.setOnScanResultCallback(this);
    }

    public void startCamera() {
        if (this.mCameraScan != null) {
            if (PermissionUtils.checkPermission(getContext(), Permission.CAMERA)) {
                this.mCameraScan.startCamera();
            } else {
                LogUtils.d("checkPermissionResult != PERMISSION_GRANTED");
                PermissionUtils.requestPermission(this, Permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void releaseCamera() {
        CameraScan cameraScan = this.mCameraScan;
        if (cameraScan != null) {
            cameraScan.release();
        }
    }

    protected void toggleTorchState() {
        CameraScan cameraScan = this.mCameraScan;
        if (cameraScan != null) {
            boolean zIsTorchEnabled = cameraScan.isTorchEnabled();
            this.mCameraScan.enableTorch(!zIsTorchEnabled);
            View view = this.ivFlashlight;
            if (view != null) {
                view.setSelected(!zIsTorchEnabled);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == CAMERA_PERMISSION_REQUEST_CODE) {
            requestCameraPermissionResult(strArr, iArr);
        }
    }

    public void requestCameraPermissionResult(String[] strArr, int[] iArr) {
        if (PermissionUtils.requestPermissionsResult(Permission.CAMERA, strArr, iArr)) {
            startCamera();
        } else {
            getActivity().finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        releaseCamera();
        super.onDestroy();
    }

    public View createRootView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(getLayoutId(), viewGroup, false);
    }

    public int getLayoutId() {
        return R.layout.zxl_capture;
    }

    public int getViewfinderViewId() {
        return R.id.viewfinderView;
    }

    public int getPreviewViewId() {
        return R.id.previewView;
    }

    public int getFlashlightId() {
        return R.id.ivFlashlight;
    }

    public CameraScan getCameraScan() {
        return this.mCameraScan;
    }

    public View getRootView() {
        return this.mRootView;
    }
}
