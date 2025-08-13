package io.fotoapparat.characteristic;

import android.hardware.Camera;
import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.exception.camera.CameraException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: LensPositionCharacteristic.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0003\u001a\f\u0010\u0004\u001a\u00020\u0003*\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"CAMERA_FACING_EXTERNAL", "", "toCameraId", "Lio/fotoapparat/characteristic/LensPosition;", "toLensPosition", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class LensPositionCharacteristicKt {
    private static final int CAMERA_FACING_EXTERNAL = 2;

    public static final LensPosition toLensPosition(int i) {
        if (i == 0) {
            return LensPosition.Back.INSTANCE;
        }
        if (i == 1) {
            return LensPosition.Front.INSTANCE;
        }
        if (i == 2) {
            return LensPosition.External.INSTANCE;
        }
        throw new IllegalArgumentException("Lens position " + i + " is not supported.");
    }

    public static final int toCameraId(LensPosition receiver$0) {
        Integer next;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Iterator<Integer> it = RangesKt.until(0, Camera.getNumberOfCameras()).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(receiver$0, CameraInfoProviderKt.getCharacteristics(next.intValue()).getLensPosition())) {
                break;
            }
        }
        Integer num = next;
        if (num != null) {
            return num.intValue();
        }
        throw new CameraException("Device has no camera for the desired lens position(s).", null, 2, null);
    }
}
