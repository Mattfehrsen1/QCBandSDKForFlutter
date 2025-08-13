package androidx.camera.view.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;

/* loaded from: classes.dex */
public class TextureViewRotationQuirk implements Quirk {
    private static final String FAIRPHONE = "Fairphone";
    private static final String FAIRPHONE_2_MODEL = "FP2";

    static boolean load() {
        return isFairphone2();
    }

    public int getCorrectionRotation(boolean isFrontCamera) {
        return (isFairphone2() && isFrontCamera) ? 180 : 0;
    }

    private static boolean isFairphone2() {
        return FAIRPHONE.equalsIgnoreCase(Build.MANUFACTURER) && FAIRPHONE_2_MODEL.equalsIgnoreCase(Build.MODEL);
    }
}
