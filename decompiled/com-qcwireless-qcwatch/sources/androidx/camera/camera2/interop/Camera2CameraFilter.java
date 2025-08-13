package androidx.camera.camera2.interop;

import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class Camera2CameraFilter {

    public interface Camera2Filter {
        List<Camera2CameraInfo> filter(List<Camera2CameraInfo> cameraInfos);
    }

    public static CameraFilter createCameraFilter(final Camera2Filter filter) {
        return new CameraFilter() { // from class: androidx.camera.camera2.interop.Camera2CameraFilter$$ExternalSyntheticLambda0
            @Override // androidx.camera.core.CameraFilter
            public final List filter(List list) {
                return Camera2CameraFilter.lambda$createCameraFilter$0(filter, list);
            }
        };
    }

    static /* synthetic */ List lambda$createCameraFilter$0(Camera2Filter camera2Filter, List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Camera2CameraInfo.from((CameraInfo) it.next()));
        }
        List<Camera2CameraInfo> listFilter = camera2Filter.filter(Collections.unmodifiableList(arrayList));
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            CameraInfo cameraInfo = (CameraInfo) it2.next();
            if (listFilter.contains(Camera2CameraInfo.from(cameraInfo))) {
                arrayList2.add(cameraInfo);
            }
        }
        return arrayList2;
    }

    private Camera2CameraFilter() {
    }
}
