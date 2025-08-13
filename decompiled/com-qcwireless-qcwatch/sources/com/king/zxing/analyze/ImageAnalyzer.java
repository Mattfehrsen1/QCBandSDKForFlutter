package com.king.zxing.analyze;

import androidx.camera.core.ImageProxy;
import com.google.zxing.Result;
import com.king.zxing.util.LogUtils;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public abstract class ImageAnalyzer implements Analyzer {
    public abstract Result analyze(byte[] bArr, int i, int i2);

    @Override // com.king.zxing.analyze.Analyzer
    public Result analyze(ImageProxy imageProxy, int i) {
        if (imageProxy.getFormat() == 35) {
            ByteBuffer buffer = imageProxy.getPlanes()[0].getBuffer();
            int iRemaining = buffer.remaining();
            byte[] bArr = new byte[iRemaining];
            buffer.get(bArr);
            int width = imageProxy.getWidth();
            int height = imageProxy.getHeight();
            if (i == 1) {
                byte[] bArr2 = new byte[iRemaining];
                for (int i2 = 0; i2 < height; i2++) {
                    for (int i3 = 0; i3 < width; i3++) {
                        bArr2[(((i3 * height) + height) - i2) - 1] = bArr[(i2 * width) + i3];
                    }
                }
                return analyze(bArr2, height, width);
            }
            return analyze(bArr, width, height);
        }
        LogUtils.w("imageFormat: " + imageProxy.getFormat());
        return null;
    }
}
