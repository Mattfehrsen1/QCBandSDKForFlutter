package com.king.zxing;

import androidx.camera.core.Camera;

/* loaded from: classes3.dex */
public interface ICamera {
    Camera getCamera();

    void release();

    void startCamera();

    void stopCamera();
}
