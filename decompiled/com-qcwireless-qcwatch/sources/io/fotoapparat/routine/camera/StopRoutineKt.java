package io.fotoapparat.routine.camera;

import io.fotoapparat.hardware.CameraDevice;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.hardware.metering.FocalRequest;
import io.fotoapparat.hardware.orientation.OrientationSensor;
import io.fotoapparat.routine.orientation.StopOrientationRoutineKt;
import io.fotoapparat.view.FocalPointSelector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StopRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\b"}, d2 = {"shutDown", "", "Lio/fotoapparat/hardware/Device;", "orientationSensor", "Lio/fotoapparat/hardware/orientation/OrientationSensor;", "stop", "cameraDevice", "Lio/fotoapparat/hardware/CameraDevice;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StopRoutineKt {
    public static final void shutDown(Device receiver$0, OrientationSensor orientationSensor) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(orientationSensor, "orientationSensor");
        FocalPointSelector focusPointSelector = receiver$0.getFocusPointSelector();
        if (focusPointSelector != null) {
            focusPointSelector.setFocalPointListener(new Function1<FocalRequest, Unit>() { // from class: io.fotoapparat.routine.camera.StopRoutineKt.shutDown.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FocalRequest focalRequest) {
                    invoke2(focalRequest);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FocalRequest it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }
            });
        }
        StopOrientationRoutineKt.stopMonitoring(orientationSensor);
        stop(receiver$0, receiver$0.getSelectedCamera());
    }

    public static final void stop(Device receiver$0, CameraDevice cameraDevice) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cameraDevice, "cameraDevice");
        cameraDevice.stopPreview();
        cameraDevice.close();
        receiver$0.clearSelectedCamera();
    }
}
