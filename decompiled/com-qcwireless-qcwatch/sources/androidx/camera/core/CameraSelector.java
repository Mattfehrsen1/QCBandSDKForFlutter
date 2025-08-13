package androidx.camera.core;

import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.LensFacingCameraFilter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes.dex */
public final class CameraSelector {
    public static final int LENS_FACING_BACK = 1;
    public static final int LENS_FACING_FRONT = 0;
    private LinkedHashSet<CameraFilter> mCameraFilterSet;
    public static final CameraSelector DEFAULT_FRONT_CAMERA = new Builder().requireLensFacing(0).build();
    public static final CameraSelector DEFAULT_BACK_CAMERA = new Builder().requireLensFacing(1).build();

    @Retention(RetentionPolicy.SOURCE)
    public @interface LensFacing {
    }

    CameraSelector(LinkedHashSet<CameraFilter> cameraFilterSet) {
        this.mCameraFilterSet = cameraFilterSet;
    }

    public CameraInternal select(LinkedHashSet<CameraInternal> cameras) {
        return filter(cameras).iterator().next();
    }

    public List<CameraInfo> filter(List<CameraInfo> cameraInfos) {
        ArrayList arrayList = new ArrayList(cameraInfos);
        List<CameraInfo> arrayList2 = new ArrayList<>(cameraInfos);
        Iterator<CameraFilter> it = this.mCameraFilterSet.iterator();
        while (it.hasNext()) {
            arrayList2 = it.next().filter(Collections.unmodifiableList(arrayList2));
            if (arrayList2.isEmpty()) {
                throw new IllegalArgumentException("No available camera can be found.");
            }
            if (!arrayList.containsAll(arrayList2)) {
                throw new IllegalArgumentException("The output isn't contained in the input.");
            }
            arrayList.retainAll(arrayList2);
        }
        return arrayList2;
    }

    public LinkedHashSet<CameraInternal> filter(LinkedHashSet<CameraInternal> cameras) {
        ArrayList arrayList = new ArrayList();
        Iterator<CameraInternal> it = cameras.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getCameraInfo());
        }
        List<CameraInfo> listFilter = filter(arrayList);
        LinkedHashSet<CameraInternal> linkedHashSet = new LinkedHashSet<>();
        Iterator<CameraInternal> it2 = cameras.iterator();
        while (it2.hasNext()) {
            CameraInternal next = it2.next();
            if (listFilter.contains(next.getCameraInfo())) {
                linkedHashSet.add(next);
            }
        }
        return linkedHashSet;
    }

    public LinkedHashSet<CameraFilter> getCameraFilterSet() {
        return this.mCameraFilterSet;
    }

    public Integer getLensFacing() {
        Iterator<CameraFilter> it = this.mCameraFilterSet.iterator();
        Integer num = null;
        while (it.hasNext()) {
            CameraFilter next = it.next();
            if (next instanceof LensFacingCameraFilter) {
                Integer numValueOf = Integer.valueOf(((LensFacingCameraFilter) next).getLensFacing());
                if (num == null) {
                    num = numValueOf;
                } else if (!num.equals(numValueOf)) {
                    throw new IllegalStateException("Multiple conflicting lens facing requirements exist.");
                }
            }
        }
        return num;
    }

    public static final class Builder {
        private final LinkedHashSet<CameraFilter> mCameraFilterSet;

        public Builder() {
            this.mCameraFilterSet = new LinkedHashSet<>();
        }

        private Builder(LinkedHashSet<CameraFilter> cameraFilterSet) {
            this.mCameraFilterSet = new LinkedHashSet<>(cameraFilterSet);
        }

        public Builder requireLensFacing(int lensFacing) {
            this.mCameraFilterSet.add(new LensFacingCameraFilter(lensFacing));
            return this;
        }

        public Builder addCameraFilter(CameraFilter cameraFilter) {
            this.mCameraFilterSet.add(cameraFilter);
            return this;
        }

        public static Builder fromSelector(CameraSelector cameraSelector) {
            return new Builder(cameraSelector.getCameraFilterSet());
        }

        public CameraSelector build() {
            return new CameraSelector(this.mCameraFilterSet);
        }
    }
}
