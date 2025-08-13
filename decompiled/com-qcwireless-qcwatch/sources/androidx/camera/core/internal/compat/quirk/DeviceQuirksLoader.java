package androidx.camera.core.internal.compat.quirk;

import androidx.camera.core.impl.Quirk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceQuirksLoader {
    private DeviceQuirksLoader() {
    }

    static List<Quirk> loadQuirks() {
        ArrayList arrayList = new ArrayList();
        if (IncompleteCameraListQuirk.load()) {
            arrayList.add(new IncompleteCameraListQuirk());
        }
        if (ImageCaptureRotationOptionQuirk.load()) {
            arrayList.add(new ImageCaptureRotationOptionQuirk());
        }
        if (ImageCaptureWashedOutImageQuirk.load()) {
            arrayList.add(new ImageCaptureWashedOutImageQuirk());
        }
        return arrayList;
    }
}
