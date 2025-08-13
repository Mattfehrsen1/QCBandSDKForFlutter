package io.fotoapparat.characteristic;

import android.hardware.Camera;
import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.hardware.orientation.OrientationKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraInfoProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"getCharacteristics", "Lio/fotoapparat/characteristic/Characteristics;", "cameraId", "", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraInfoProviderKt {
    public static final Characteristics getCharacteristics(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        LensPosition lensPosition = LensPositionCharacteristicKt.toLensPosition(cameraInfo.facing);
        return new Characteristics(i, lensPosition, OrientationKt.toOrientation(cameraInfo.orientation), Intrinsics.areEqual(lensPosition, LensPosition.Front.INSTANCE));
    }
}
