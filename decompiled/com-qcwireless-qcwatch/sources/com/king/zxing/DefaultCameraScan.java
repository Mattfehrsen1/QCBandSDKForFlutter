package com.king.zxing;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.ZoomState;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.detector.MathUtils;
import com.king.zxing.CameraScan;
import com.king.zxing.analyze.Analyzer;
import com.king.zxing.analyze.MultiFormatAnalyzer;
import com.king.zxing.config.CameraConfig;
import com.king.zxing.manager.AmbientLightManager;
import com.king.zxing.manager.BeepManager;
import com.king.zxing.util.LogUtils;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class DefaultCameraScan extends CameraScan {
    private static final int HOVER_TAP_SLOP = 20;
    private static final int HOVER_TAP_TIMEOUT = 150;
    private View flashlightView;
    private volatile boolean isAnalyzeResult;
    private boolean isClickTap;
    private AmbientLightManager mAmbientLightManager;
    private Analyzer mAnalyzer;
    private BeepManager mBeepManager;
    private Camera mCamera;
    private CameraConfig mCameraConfig;
    private ListenableFuture<ProcessCameraProvider> mCameraProviderFuture;
    private Context mContext;
    private float mDownX;
    private float mDownY;
    private FragmentActivity mFragmentActivity;
    private long mLastAutoZoomTime;
    private long mLastHoveTapTime;
    private LifecycleOwner mLifecycleOwner;
    private CameraScan.OnScanResultCallback mOnScanResultCallback;
    private int mOrientation;
    private PreviewView mPreviewView;
    private MutableLiveData<Result> mResultLiveData;
    private int mScreenHeight;
    private int mScreenWidth;
    private volatile boolean isAnalyze = true;
    private ScaleGestureDetector.OnScaleGestureListener mOnScaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.king.zxing.DefaultCameraScan.1
        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (DefaultCameraScan.this.mCamera == null) {
                return true;
            }
            DefaultCameraScan.this.zoomTo(DefaultCameraScan.this.mCamera.getCameraInfo().getZoomState().getValue().getZoomRatio() * scaleFactor);
            return true;
        }
    };

    public DefaultCameraScan(FragmentActivity fragmentActivity, PreviewView previewView) {
        this.mFragmentActivity = fragmentActivity;
        this.mLifecycleOwner = fragmentActivity;
        this.mContext = fragmentActivity;
        this.mPreviewView = previewView;
        initData();
    }

    public DefaultCameraScan(Fragment fragment, PreviewView previewView) {
        this.mFragmentActivity = fragment.getActivity();
        this.mLifecycleOwner = fragment;
        this.mContext = fragment.getContext();
        this.mPreviewView = previewView;
        initData();
    }

    private void initData() {
        MutableLiveData<Result> mutableLiveData = new MutableLiveData<>();
        this.mResultLiveData = mutableLiveData;
        mutableLiveData.observe(this.mLifecycleOwner, new Observer() { // from class: com.king.zxing.DefaultCameraScan$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m231lambda$initData$0$comkingzxingDefaultCameraScan((Result) obj);
            }
        });
        this.mOrientation = this.mContext.getResources().getConfiguration().orientation;
        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(this.mContext, this.mOnScaleGestureListener);
        this.mPreviewView.setOnTouchListener(new View.OnTouchListener() { // from class: com.king.zxing.DefaultCameraScan$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.m232lambda$initData$1$comkingzxingDefaultCameraScan(scaleGestureDetector, view, motionEvent);
            }
        });
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
        LogUtils.d(String.format("displayMetrics:%dx%d", Integer.valueOf(this.mScreenWidth), Integer.valueOf(this.mScreenHeight)));
        this.mBeepManager = new BeepManager(this.mContext);
        AmbientLightManager ambientLightManager = new AmbientLightManager(this.mContext);
        this.mAmbientLightManager = ambientLightManager;
        ambientLightManager.register();
        this.mAmbientLightManager.setOnLightSensorEventListener(new AmbientLightManager.OnLightSensorEventListener() { // from class: com.king.zxing.DefaultCameraScan$$ExternalSyntheticLambda3
            @Override // com.king.zxing.manager.AmbientLightManager.OnLightSensorEventListener
            public /* synthetic */ void onSensorChanged(float f) {
                AmbientLightManager.OnLightSensorEventListener.CC.$default$onSensorChanged(this, f);
            }

            @Override // com.king.zxing.manager.AmbientLightManager.OnLightSensorEventListener
            public final void onSensorChanged(boolean z, float f) {
                this.f$0.m233lambda$initData$2$comkingzxingDefaultCameraScan(z, f);
            }
        });
    }

    /* renamed from: lambda$initData$0$com-king-zxing-DefaultCameraScan, reason: not valid java name */
    public /* synthetic */ void m231lambda$initData$0$comkingzxingDefaultCameraScan(Result result) {
        if (result != null) {
            handleAnalyzeResult(result);
            return;
        }
        CameraScan.OnScanResultCallback onScanResultCallback = this.mOnScanResultCallback;
        if (onScanResultCallback != null) {
            onScanResultCallback.onScanResultFailure();
        }
    }

    /* renamed from: lambda$initData$1$com-king-zxing-DefaultCameraScan, reason: not valid java name */
    public /* synthetic */ boolean m232lambda$initData$1$comkingzxingDefaultCameraScan(ScaleGestureDetector scaleGestureDetector, View view, MotionEvent motionEvent) {
        handlePreviewViewClickTap(motionEvent);
        if (isNeedTouchZoom()) {
            return scaleGestureDetector.onTouchEvent(motionEvent);
        }
        return false;
    }

    /* renamed from: lambda$initData$2$com-king-zxing-DefaultCameraScan, reason: not valid java name */
    public /* synthetic */ void m233lambda$initData$2$comkingzxingDefaultCameraScan(boolean z, float f) {
        View view = this.flashlightView;
        if (view != null) {
            if (z) {
                if (view.getVisibility() != 0) {
                    this.flashlightView.setVisibility(0);
                    this.flashlightView.setSelected(isTorchEnabled());
                    return;
                }
                return;
            }
            if (view.getVisibility() != 0 || isTorchEnabled()) {
                return;
            }
            this.flashlightView.setVisibility(4);
            this.flashlightView.setSelected(false);
        }
    }

    private void handlePreviewViewClickTap(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 1) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.isClickTap = true;
                this.mDownX = motionEvent.getX();
                this.mDownY = motionEvent.getY();
                this.mLastHoveTapTime = System.currentTimeMillis();
                return;
            }
            if (action != 1) {
                if (action != 2) {
                    return;
                }
                this.isClickTap = MathUtils.distance(this.mDownX, this.mDownY, motionEvent.getX(), motionEvent.getY()) < 20.0f;
            } else {
                if (!this.isClickTap || this.mLastHoveTapTime + 150 <= System.currentTimeMillis()) {
                    return;
                }
                startFocusAndMetering(motionEvent.getX(), motionEvent.getY());
            }
        }
    }

    private void startFocusAndMetering(float f, float f2) {
        if (this.mCamera != null) {
            LogUtils.d("startFocusAndMetering:" + f + "," + f2);
            this.mCamera.getCameraControl().startFocusAndMetering(new FocusMeteringAction.Builder(this.mPreviewView.getMeteringPointFactory().createPoint(f, f2)).build());
        }
    }

    private void initConfig() {
        if (this.mCameraConfig == null) {
            this.mCameraConfig = new CameraConfig();
        }
        if (this.mAnalyzer == null) {
            this.mAnalyzer = new MultiFormatAnalyzer();
        }
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setCameraConfig(CameraConfig cameraConfig) {
        if (cameraConfig != null) {
            this.mCameraConfig = cameraConfig;
        }
        return this;
    }

    @Override // com.king.zxing.ICamera
    public void startCamera() {
        initConfig();
        ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(this.mContext);
        this.mCameraProviderFuture = processCameraProvider;
        processCameraProvider.addListener(new Runnable() { // from class: com.king.zxing.DefaultCameraScan$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m235lambda$startCamera$4$comkingzxingDefaultCameraScan();
            }
        }, ContextCompat.getMainExecutor(this.mContext));
    }

    /* renamed from: lambda$startCamera$4$com-king-zxing-DefaultCameraScan, reason: not valid java name */
    public /* synthetic */ void m235lambda$startCamera$4$comkingzxingDefaultCameraScan() {
        try {
            Preview previewOptions = this.mCameraConfig.options(new Preview.Builder());
            CameraSelector cameraSelectorOptions = this.mCameraConfig.options(new CameraSelector.Builder());
            previewOptions.setSurfaceProvider(this.mPreviewView.getSurfaceProvider());
            ImageAnalysis imageAnalysisOptions = this.mCameraConfig.options(new ImageAnalysis.Builder().setBackpressureStrategy(0));
            imageAnalysisOptions.setAnalyzer(Executors.newSingleThreadExecutor(), new ImageAnalysis.Analyzer() { // from class: com.king.zxing.DefaultCameraScan$$ExternalSyntheticLambda1
                @Override // androidx.camera.core.ImageAnalysis.Analyzer
                public final void analyze(ImageProxy imageProxy) {
                    this.f$0.m234lambda$null$3$comkingzxingDefaultCameraScan(imageProxy);
                }
            });
            if (this.mCamera != null) {
                this.mCameraProviderFuture.get().unbindAll();
            }
            this.mCamera = this.mCameraProviderFuture.get().bindToLifecycle(this.mLifecycleOwner, cameraSelectorOptions, previewOptions, imageAnalysisOptions);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /* renamed from: lambda$null$3$com-king-zxing-DefaultCameraScan, reason: not valid java name */
    public /* synthetic */ void m234lambda$null$3$comkingzxingDefaultCameraScan(ImageProxy imageProxy) {
        Analyzer analyzer;
        if (this.isAnalyze && !this.isAnalyzeResult && (analyzer = this.mAnalyzer) != null) {
            this.mResultLiveData.postValue(analyzer.analyze(imageProxy, this.mOrientation));
        }
        imageProxy.close();
    }

    private synchronized void handleAnalyzeResult(Result result) {
        ResultPoint[] resultPoints;
        if (!this.isAnalyzeResult && this.isAnalyze) {
            this.isAnalyzeResult = true;
            BeepManager beepManager = this.mBeepManager;
            if (beepManager != null) {
                beepManager.playBeepSoundAndVibrate();
            }
            if (result.getBarcodeFormat() == BarcodeFormat.QR_CODE && isNeedAutoZoom() && this.mLastAutoZoomTime + 100 < System.currentTimeMillis() && (resultPoints = result.getResultPoints()) != null && resultPoints.length >= 2) {
                float fDistance = ResultPoint.distance(resultPoints[0], resultPoints[1]);
                if (resultPoints.length >= 3) {
                    fDistance = Math.max(Math.max(fDistance, ResultPoint.distance(resultPoints[1], resultPoints[2])), ResultPoint.distance(resultPoints[0], resultPoints[2]));
                }
                if (handleAutoZoom((int) fDistance, result)) {
                    return;
                }
            }
            scanResultCallback(result);
        }
    }

    private boolean handleAutoZoom(int i, Result result) {
        if (i * 4 >= Math.min(this.mScreenWidth, this.mScreenHeight)) {
            return false;
        }
        this.mLastAutoZoomTime = System.currentTimeMillis();
        zoomIn();
        scanResultCallback(result);
        return true;
    }

    private void scanResultCallback(Result result) {
        CameraScan.OnScanResultCallback onScanResultCallback = this.mOnScanResultCallback;
        if (onScanResultCallback != null && onScanResultCallback.onScanResultCallback(result)) {
            this.isAnalyzeResult = false;
        } else if (this.mFragmentActivity != null) {
            Intent intent = new Intent();
            intent.putExtra(SCAN_RESULT, result.getText());
            this.mFragmentActivity.setResult(-1, intent);
            this.mFragmentActivity.finish();
        }
    }

    @Override // com.king.zxing.ICamera
    public void stopCamera() {
        ListenableFuture<ProcessCameraProvider> listenableFuture = this.mCameraProviderFuture;
        if (listenableFuture != null) {
            try {
                listenableFuture.get().unbindAll();
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setAnalyzeImage(boolean z) {
        this.isAnalyze = z;
        return this;
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setAnalyzer(Analyzer analyzer) {
        this.mAnalyzer = analyzer;
        return this;
    }

    @Override // com.king.zxing.ICameraControl
    public void zoomIn() {
        Camera camera = this.mCamera;
        if (camera != null) {
            float zoomRatio = camera.getCameraInfo().getZoomState().getValue().getZoomRatio() + 0.1f;
            if (zoomRatio <= this.mCamera.getCameraInfo().getZoomState().getValue().getMaxZoomRatio()) {
                this.mCamera.getCameraControl().setZoomRatio(zoomRatio);
            }
        }
    }

    @Override // com.king.zxing.ICameraControl
    public void zoomOut() {
        Camera camera = this.mCamera;
        if (camera != null) {
            float zoomRatio = camera.getCameraInfo().getZoomState().getValue().getZoomRatio() - 0.1f;
            if (zoomRatio >= this.mCamera.getCameraInfo().getZoomState().getValue().getMinZoomRatio()) {
                this.mCamera.getCameraControl().setZoomRatio(zoomRatio);
            }
        }
    }

    @Override // com.king.zxing.ICameraControl
    public void zoomTo(float f) {
        Camera camera = this.mCamera;
        if (camera != null) {
            ZoomState value = camera.getCameraInfo().getZoomState().getValue();
            float maxZoomRatio = value.getMaxZoomRatio();
            this.mCamera.getCameraControl().setZoomRatio(Math.max(Math.min(f, maxZoomRatio), value.getMinZoomRatio()));
        }
    }

    @Override // com.king.zxing.ICameraControl
    public void lineZoomIn() {
        Camera camera = this.mCamera;
        if (camera != null) {
            float linearZoom = camera.getCameraInfo().getZoomState().getValue().getLinearZoom() + 0.1f;
            if (linearZoom <= 1.0f) {
                this.mCamera.getCameraControl().setLinearZoom(linearZoom);
            }
        }
    }

    @Override // com.king.zxing.ICameraControl
    public void lineZoomOut() {
        Camera camera = this.mCamera;
        if (camera != null) {
            float linearZoom = camera.getCameraInfo().getZoomState().getValue().getLinearZoom() - 0.1f;
            if (linearZoom >= 0.0f) {
                this.mCamera.getCameraControl().setLinearZoom(linearZoom);
            }
        }
    }

    @Override // com.king.zxing.ICameraControl
    public void lineZoomTo(float f) {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.getCameraControl().setLinearZoom(f);
        }
    }

    @Override // com.king.zxing.ICameraControl
    public void enableTorch(boolean z) {
        if (this.mCamera == null || !hasFlashUnit()) {
            return;
        }
        this.mCamera.getCameraControl().enableTorch(z);
    }

    @Override // com.king.zxing.ICameraControl
    public boolean isTorchEnabled() {
        Camera camera = this.mCamera;
        return camera != null && camera.getCameraInfo().getTorchState().getValue().intValue() == 1;
    }

    @Override // com.king.zxing.ICameraControl
    public boolean hasFlashUnit() {
        Camera camera = this.mCamera;
        if (camera != null) {
            return camera.getCameraInfo().hasFlashUnit();
        }
        return false;
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setVibrate(boolean z) {
        BeepManager beepManager = this.mBeepManager;
        if (beepManager != null) {
            beepManager.setVibrate(z);
        }
        return this;
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setPlayBeep(boolean z) {
        BeepManager beepManager = this.mBeepManager;
        if (beepManager != null) {
            beepManager.setPlayBeep(z);
        }
        return this;
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setOnScanResultCallback(CameraScan.OnScanResultCallback onScanResultCallback) {
        this.mOnScanResultCallback = onScanResultCallback;
        return this;
    }

    @Override // com.king.zxing.ICamera
    public Camera getCamera() {
        return this.mCamera;
    }

    @Override // com.king.zxing.ICamera
    public void release() {
        this.isAnalyze = false;
        this.flashlightView = null;
        AmbientLightManager ambientLightManager = this.mAmbientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.unregister();
        }
        BeepManager beepManager = this.mBeepManager;
        if (beepManager != null) {
            beepManager.close();
        }
        stopCamera();
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan bindFlashlightView(View view) {
        this.flashlightView = view;
        AmbientLightManager ambientLightManager = this.mAmbientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setLightSensorEnabled(view != null);
        }
        return this;
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setDarkLightLux(float f) {
        AmbientLightManager ambientLightManager = this.mAmbientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setDarkLightLux(f);
        }
        return this;
    }

    @Override // com.king.zxing.CameraScan
    public CameraScan setBrightLightLux(float f) {
        AmbientLightManager ambientLightManager = this.mAmbientLightManager;
        if (ambientLightManager != null) {
            ambientLightManager.setBrightLightLux(f);
        }
        return this;
    }
}
