package androidx.camera.view.internal.compat.quirk;

import androidx.camera.core.impl.Quirk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceQuirksLoader {
    private DeviceQuirksLoader() {
    }

    static List<Quirk> loadQuirks() {
        ArrayList arrayList = new ArrayList();
        if (PreviewOneThirdWiderQuirk.load()) {
            arrayList.add(new PreviewOneThirdWiderQuirk());
        }
        if (SurfaceViewStretchedQuirk.load()) {
            arrayList.add(new SurfaceViewStretchedQuirk());
        }
        if (TextureViewRotationQuirk.load()) {
            arrayList.add(new TextureViewRotationQuirk());
        }
        return arrayList;
    }
}
