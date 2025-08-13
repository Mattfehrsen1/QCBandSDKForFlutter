package io.fotoapparat.routine.camera;

import io.fotoapparat.concurrent.CameraExecutor;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.hardware.CameraDevice;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.hardware.metering.FocalRequest;
import io.fotoapparat.hardware.orientation.OrientationSensor;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.result.FocusResult;
import io.fotoapparat.routine.focus.FocusOnPointRoutineKt;
import io.fotoapparat.routine.orientation.StartOrientationRoutineKt;
import io.fotoapparat.view.CameraRenderer;
import io.fotoapparat.view.FocalPointSelector;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StartRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006j\u0002`\bH\u0000\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\n"}, d2 = {"bootStart", "", "Lio/fotoapparat/hardware/Device;", "orientationSensor", "Lio/fotoapparat/hardware/orientation/OrientationSensor;", "mainThreadErrorCallback", "Lkotlin/Function1;", "Lio/fotoapparat/exception/camera/CameraException;", "Lio/fotoapparat/error/CameraErrorCallback;", "start", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class StartRoutineKt {
    public static final void bootStart(Device receiver$0, OrientationSensor orientationSensor, Function1<? super CameraException, Unit> mainThreadErrorCallback) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(orientationSensor, "orientationSensor");
        Intrinsics.checkParameterIsNotNull(mainThreadErrorCallback, "mainThreadErrorCallback");
        if (receiver$0.hasSelectedCamera()) {
            throw new IllegalStateException("Camera has already started!");
        }
        try {
            start(receiver$0, orientationSensor);
            StartOrientationRoutineKt.startOrientationMonitoring(receiver$0, orientationSensor);
        } catch (CameraException e) {
            mainThreadErrorCallback.invoke(e);
        }
    }

    public static final void start(final Device receiver$0, OrientationSensor orientationSensor) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(orientationSensor, "orientationSensor");
        receiver$0.selectCamera();
        CameraDevice selectedCamera = receiver$0.getSelectedCamera();
        selectedCamera.open();
        UpdateConfigurationRoutineKt.updateCameraConfiguration(receiver$0, selectedCamera);
        selectedCamera.setDisplayOrientation(orientationSensor.getLastKnownOrientationState());
        Resolution previewResolution = selectedCamera.getPreviewResolution();
        CameraRenderer cameraRenderer = receiver$0.getCameraRenderer();
        cameraRenderer.setScaleType(receiver$0.getScaleType());
        cameraRenderer.setPreviewResolution(previewResolution);
        FocalPointSelector focusPointSelector = receiver$0.getFocusPointSelector();
        if (focusPointSelector != null) {
            focusPointSelector.setFocalPointListener(new Function1<FocalRequest, Unit>() { // from class: io.fotoapparat.routine.camera.StartRoutineKt.start.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FocalRequest focalRequest) {
                    invoke2(focalRequest);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final FocalRequest focalRequest) {
                    Intrinsics.checkParameterIsNotNull(focalRequest, "focalRequest");
                    receiver$0.getExecutor().execute(new CameraExecutor.Operation(true, new Function0<FocusResult>() { // from class: io.fotoapparat.routine.camera.StartRoutineKt.start.2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // kotlin.jvm.functions.Function0
                        public final FocusResult invoke() {
                            return FocusOnPointRoutineKt.focusOnPoint(receiver$0, focalRequest);
                        }
                    }));
                }
            });
        }
        try {
            selectedCamera.setDisplaySurface(receiver$0.getCameraRenderer().getPreview());
            selectedCamera.startPreview();
        } catch (IOException e) {
            receiver$0.getLogger().log("Can't start preview because of the exception: " + e);
        }
    }
}
