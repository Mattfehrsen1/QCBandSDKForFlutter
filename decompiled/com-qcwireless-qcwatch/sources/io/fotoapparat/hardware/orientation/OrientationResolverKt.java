package io.fotoapparat.hardware.orientation;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrientationResolver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001e\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\t"}, d2 = {"computeDisplayOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "screenOrientation", "cameraOrientation", "cameraIsMirrored", "", "computeImageOrientation", "deviceOrientation", "computePreviewOrientation", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class OrientationResolverKt {
    public static final Orientation computePreviewOrientation(Orientation screenOrientation, Orientation cameraOrientation, boolean z) {
        Intrinsics.checkParameterIsNotNull(screenOrientation, "screenOrientation");
        Intrinsics.checkParameterIsNotNull(cameraOrientation, "cameraOrientation");
        return OrientationKt.toOrientation(((((z ? -1 : 1) * screenOrientation.getDegrees()) + 720) - cameraOrientation.getDegrees()) % 360);
    }

    public static final Orientation computeImageOrientation(Orientation deviceOrientation, Orientation cameraOrientation, boolean z) {
        int i;
        Intrinsics.checkParameterIsNotNull(deviceOrientation, "deviceOrientation");
        Intrinsics.checkParameterIsNotNull(cameraOrientation, "cameraOrientation");
        int degrees = deviceOrientation.getDegrees();
        int degrees2 = cameraOrientation.getDegrees();
        if (z) {
            i = ((degrees2 - degrees) + 360) % 360;
        } else {
            i = (degrees2 + degrees) % 360;
        }
        return OrientationKt.toOrientation(360 - i);
    }

    public static final Orientation computeDisplayOrientation(Orientation screenOrientation, Orientation cameraOrientation, boolean z) {
        int i;
        Intrinsics.checkParameterIsNotNull(screenOrientation, "screenOrientation");
        Intrinsics.checkParameterIsNotNull(cameraOrientation, "cameraOrientation");
        int degrees = screenOrientation.getDegrees();
        int degrees2 = cameraOrientation.getDegrees();
        if (z) {
            i = (360 - ((degrees2 + degrees) % 360)) % 360;
        } else {
            i = ((degrees2 - degrees) + 360) % 360;
        }
        return OrientationKt.toOrientation(i);
    }
}
