package io.fotoapparat.routine.orientation;

import io.fotoapparat.concurrent.CameraExecutor;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.hardware.orientation.OrientationSensor;
import io.fotoapparat.hardware.orientation.OrientationState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StartOrientationRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"startOrientationMonitoring", "", "Lio/fotoapparat/hardware/Device;", "orientationSensor", "Lio/fotoapparat/hardware/orientation/OrientationSensor;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StartOrientationRoutineKt {
    public static final void startOrientationMonitoring(final Device receiver$0, OrientationSensor orientationSensor) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(orientationSensor, "orientationSensor");
        orientationSensor.start(new Function1<OrientationState, Unit>() { // from class: io.fotoapparat.routine.orientation.StartOrientationRoutineKt.startOrientationMonitoring.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OrientationState orientationState) {
                invoke2(orientationState);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final OrientationState orientationState) {
                Intrinsics.checkParameterIsNotNull(orientationState, "orientationState");
                receiver$0.getExecutor().execute(new CameraExecutor.Operation(true, new Function0<Unit>() { // from class: io.fotoapparat.routine.orientation.StartOrientationRoutineKt.startOrientationMonitoring.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        receiver$0.getSelectedCamera().setDisplayOrientation(orientationState);
                    }
                }));
            }
        });
    }
}
