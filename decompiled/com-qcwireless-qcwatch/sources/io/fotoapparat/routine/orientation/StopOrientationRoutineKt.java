package io.fotoapparat.routine.orientation;

import io.fotoapparat.hardware.orientation.OrientationSensor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StopOrientationRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"stopMonitoring", "", "Lio/fotoapparat/hardware/orientation/OrientationSensor;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StopOrientationRoutineKt {
    public static final void stopMonitoring(OrientationSensor receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        receiver$0.stop();
    }
}
