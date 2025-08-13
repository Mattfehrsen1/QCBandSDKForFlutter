package androidx.camera.camera2.internal.compat.workaround;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.internal.compat.quirk.PreviewPixelHDRnetQuirk;
import androidx.camera.core.impl.SessionConfig;

/* loaded from: classes.dex */
public class PreviewPixelHDRnet {
    private PreviewPixelHDRnet() {
    }

    public static void setHDRnet(SessionConfig.Builder sessionBuilder) {
        if (((PreviewPixelHDRnetQuirk) DeviceQuirks.get(PreviewPixelHDRnetQuirk.class)) == null) {
            return;
        }
        Camera2ImplConfig.Builder builder = new Camera2ImplConfig.Builder();
        builder.setCaptureRequestOption(CaptureRequest.TONEMAP_MODE, 2);
        sessionBuilder.addImplementationOptions(builder.build());
    }
}
