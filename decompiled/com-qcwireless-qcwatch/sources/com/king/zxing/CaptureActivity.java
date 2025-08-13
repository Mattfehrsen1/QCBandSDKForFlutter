package com.king.zxing;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;
import com.google.zxing.Result;
import com.hjq.permissions.Permission;
import com.king.zxing.CameraScan;
import com.king.zxing.util.LogUtils;
import com.king.zxing.util.PermissionUtils;

/* loaded from: classes3.dex */
public class CaptureActivity extends AppCompatActivity implements CameraScan.OnScanResultCallback {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 134;
    protected View ivFlashlight;
    private CameraScan mCameraScan;
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

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = getLayoutId();
        if (isContentView(layoutId)) {
            setContentView(layoutId);
        }
        initUI();
    }

    public void initUI() {
        this.previewView = (PreviewView) findViewById(getPreviewViewId());
        int viewfinderViewId = getViewfinderViewId();
        if (viewfinderViewId != 0) {
            this.viewfinderView = (ViewfinderView) findViewById(viewfinderViewId);
        }
        int flashlightId = getFlashlightId();
        if (flashlightId != 0) {
            View viewFindViewById = findViewById(flashlightId);
            this.ivFlashlight = viewFindViewById;
            if (viewFindViewById != null) {
                viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.king.zxing.CaptureActivity$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.m229lambda$initUI$0$comkingzxingCaptureActivity(view);
                    }
                });
            }
        }
        initCameraScan();
        startCamera();
    }

    /* renamed from: lambda$initUI$0$com-king-zxing-CaptureActivity, reason: not valid java name */
    public /* synthetic */ void m229lambda$initUI$0$comkingzxingCaptureActivity(View view) {
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
            if (PermissionUtils.checkPermission(this, Permission.CAMERA)) {
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
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
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        releaseCamera();
        super.onDestroy();
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
}
