package com.king.zxing.config;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import com.king.zxing.util.LogUtils;

/* loaded from: classes3.dex */
public final class AspectRatioCameraConfig extends CameraConfig {
    private int mAspectRatio;

    public AspectRatioCameraConfig(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mAspectRatio = aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels);
        LogUtils.d("aspectRatio:" + this.mAspectRatio);
    }

    private int aspectRatio(float f, float f2) {
        float fMax = Math.max(f, f2) / Math.min(f, f2);
        return Math.abs(fMax - 1.3333334f) < Math.abs(fMax - 1.7777778f) ? 0 : 1;
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
        builder.setTargetAspectRatio(this.mAspectRatio);
        return super.options(builder);
    }
}
