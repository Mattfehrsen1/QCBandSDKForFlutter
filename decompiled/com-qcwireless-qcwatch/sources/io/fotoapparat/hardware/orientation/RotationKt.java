package io.fotoapparat.hardware.orientation;

import kotlin.Metadata;

/* compiled from: Rotation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0000*\n\u0010\u0002\"\u00020\u00012\u00020\u0001¨\u0006\u0003"}, d2 = {"toClosestRightAngle", "", "DeviceRotationDegrees", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class RotationKt {
    public static final int toClosestRightAngle(int i) {
        return (((i / 90) + (i % 90 > 45 ? 1 : 0)) * 90) % 360;
    }
}
