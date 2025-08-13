package io.fotoapparat.routine.camera;

import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.hardware.CameraDevice;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.hardware.orientation.OrientationSensor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwitchCameraRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bj\u0002`\nH\u0000\u001aY\u0010\u000b\u001a\u00020\u0001*\u00020\u00022#\u0010\f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\bj\u0002`\u000f¢\u0006\u0002\b\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bj\u0002`\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0013"}, d2 = {"restartPreview", "", "Lio/fotoapparat/hardware/Device;", "oldCameraDevice", "Lio/fotoapparat/hardware/CameraDevice;", "orientationSensor", "Lio/fotoapparat/hardware/orientation/OrientationSensor;", "mainThreadErrorCallback", "Lkotlin/Function1;", "Lio/fotoapparat/exception/camera/CameraException;", "Lio/fotoapparat/error/CameraErrorCallback;", "switchCamera", "newLensPositionSelector", "", "Lio/fotoapparat/characteristic/LensPosition;", "Lio/fotoapparat/selector/LensPositionSelector;", "Lkotlin/ExtensionFunctionType;", "newConfiguration", "Lio/fotoapparat/configuration/CameraConfiguration;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class SwitchCameraRoutineKt {
    public static final void switchCamera(Device receiver$0, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> newLensPositionSelector, CameraConfiguration newConfiguration, Function1<? super CameraException, Unit> mainThreadErrorCallback, OrientationSensor orientationSensor) throws InterruptedException {
        CameraDevice selectedCamera;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(newLensPositionSelector, "newLensPositionSelector");
        Intrinsics.checkParameterIsNotNull(newConfiguration, "newConfiguration");
        Intrinsics.checkParameterIsNotNull(mainThreadErrorCallback, "mainThreadErrorCallback");
        Intrinsics.checkParameterIsNotNull(orientationSensor, "orientationSensor");
        try {
            selectedCamera = receiver$0.getSelectedCamera();
        } catch (IllegalStateException unused) {
            selectedCamera = null;
        }
        if (selectedCamera == null) {
            receiver$0.updateLensPositionSelector(newLensPositionSelector);
            receiver$0.updateConfiguration(newConfiguration);
        } else if (!Intrinsics.areEqual(receiver$0.getLensPositionSelector(), newLensPositionSelector)) {
            receiver$0.updateLensPositionSelector(newLensPositionSelector);
            receiver$0.updateConfiguration(newConfiguration);
            restartPreview(receiver$0, selectedCamera, orientationSensor, mainThreadErrorCallback);
        }
    }

    public static final void restartPreview(Device receiver$0, CameraDevice oldCameraDevice, OrientationSensor orientationSensor, Function1<? super CameraException, Unit> mainThreadErrorCallback) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(oldCameraDevice, "oldCameraDevice");
        Intrinsics.checkParameterIsNotNull(orientationSensor, "orientationSensor");
        Intrinsics.checkParameterIsNotNull(mainThreadErrorCallback, "mainThreadErrorCallback");
        StopRoutineKt.stop(receiver$0, oldCameraDevice);
        try {
            StartRoutineKt.start(receiver$0, orientationSensor);
        } catch (CameraException e) {
            mainThreadErrorCallback.invoke(e);
        }
    }
}
