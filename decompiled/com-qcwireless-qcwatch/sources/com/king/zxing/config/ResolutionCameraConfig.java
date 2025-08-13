package com.king.zxing.config;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Size;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import com.king.zxing.util.LogUtils;

/* loaded from: classes3.dex */
public class ResolutionCameraConfig extends CameraConfig {
    private Size mTargetSize;

    public ResolutionCameraConfig(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        LogUtils.d(String.format("displayMetrics:%d x %d", Integer.valueOf(i), Integer.valueOf(i2)));
        if (i < i2) {
            int iMin = Math.min(i, 1080);
            if (i / i2 > 0.7d) {
                this.mTargetSize = new Size(iMin, (int) ((iMin / 3.0f) * 4.0f));
            } else {
                this.mTargetSize = new Size(iMin, (int) ((iMin / 9.0f) * 16.0f));
            }
        } else {
            int iMin2 = Math.min(i2, 1080);
            if (i2 / i > 0.7d) {
                this.mTargetSize = new Size((int) ((iMin2 / 3.0f) * 4.0f), iMin2);
            } else {
                this.mTargetSize = new Size((int) ((iMin2 / 9.0f) * 16.0d), iMin2);
            }
        }
        LogUtils.d("targetSize:" + this.mTargetSize);
    }

    @Override // com.king.zxing.config.CameraConfig
    public Preview options(Preview.Builder builder) {
        return super.options(builder);
    }

    @Override // com.king.zxing.config.CameraConfig
    public CameraSelector options(CameraSelector.Builder builder) {
        return super.options(builder);
    }

    @Override // com.king.zxing.config.CameraConfig
    public ImageAnalysis options(ImageAnalysis.Builder builder) {
        builder.setTargetResolution(this.mTargetSize);
        return super.options(builder);
    }
}
