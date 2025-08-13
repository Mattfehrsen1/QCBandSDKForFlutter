package com.king.zxing.config;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;

/* loaded from: classes3.dex */
public class CameraConfig {
    public Preview options(Preview.Builder builder) {
        return builder.build();
    }

    public CameraSelector options(CameraSelector.Builder builder) {
        return builder.build();
    }

    public ImageAnalysis options(ImageAnalysis.Builder builder) {
        return builder.build();
    }
}
