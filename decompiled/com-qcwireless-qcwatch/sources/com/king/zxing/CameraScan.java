package com.king.zxing;

import android.content.Intent;
import android.view.View;
import com.google.zxing.Result;
import com.king.zxing.analyze.Analyzer;
import com.king.zxing.config.CameraConfig;

/* loaded from: classes3.dex */
public abstract class CameraScan implements ICamera, ICameraControl {
    public static int LENS_FACING_BACK = 1;
    public static int LENS_FACING_FRONT = 0;
    public static String SCAN_RESULT = "SCAN_RESULT";
    private boolean isNeedAutoZoom = false;
    private boolean isNeedTouchZoom = true;

    public interface OnScanResultCallback {

        /* renamed from: com.king.zxing.CameraScan$OnScanResultCallback$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onScanResultFailure(OnScanResultCallback _this) {
            }
        }

        boolean onScanResultCallback(Result result);

        void onScanResultFailure();
    }

    public abstract CameraScan bindFlashlightView(View view);

    public abstract CameraScan setAnalyzeImage(boolean z);

    public abstract CameraScan setAnalyzer(Analyzer analyzer);

    public abstract CameraScan setBrightLightLux(float f);

    public abstract CameraScan setCameraConfig(CameraConfig cameraConfig);

    public abstract CameraScan setDarkLightLux(float f);

    public abstract CameraScan setOnScanResultCallback(OnScanResultCallback onScanResultCallback);

    public abstract CameraScan setPlayBeep(boolean z);

    public abstract CameraScan setVibrate(boolean z);

    protected boolean isNeedTouchZoom() {
        return this.isNeedTouchZoom;
    }

    public CameraScan setNeedTouchZoom(boolean z) {
        this.isNeedTouchZoom = z;
        return this;
    }

    protected boolean isNeedAutoZoom() {
        return this.isNeedAutoZoom;
    }

    public CameraScan setNeedAutoZoom(boolean z) {
        this.isNeedAutoZoom = z;
        return this;
    }

    public static String parseScanResult(Intent intent) {
        if (intent != null) {
            return intent.getStringExtra(SCAN_RESULT);
        }
        return null;
    }
}
