package com.king.zxing.analyze;

import androidx.camera.core.ImageProxy;
import com.google.zxing.Result;

/* loaded from: classes3.dex */
public interface Analyzer {
    Result analyze(ImageProxy imageProxy, int i);
}
