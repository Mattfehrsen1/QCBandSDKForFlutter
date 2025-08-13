package io.fotoapparat.routine.camera;

import io.fotoapparat.configuration.Configuration;
import io.fotoapparat.hardware.CameraDevice;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.parameter.camera.CameraParameters;
import io.fotoapparat.preview.Frame;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UpdateConfigurationRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\b"}, d2 = {"updateCameraConfiguration", "", "Lio/fotoapparat/hardware/Device;", "cameraDevice", "Lio/fotoapparat/hardware/CameraDevice;", "updateDeviceConfiguration", "newConfiguration", "Lio/fotoapparat/configuration/Configuration;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UpdateConfigurationRoutineKt {
    public static final void updateDeviceConfiguration(Device receiver$0, Configuration newConfiguration) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(newConfiguration, "newConfiguration");
        CameraDevice selectedCamera = receiver$0.getSelectedCamera();
        receiver$0.updateConfiguration(newConfiguration);
        updateCameraConfiguration(receiver$0, selectedCamera);
    }

    /* compiled from: UpdateConfigurationRoutine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/routine/camera/UpdateConfigurationRoutineKt$updateCameraConfiguration$1", f = "UpdateConfigurationRoutine.kt", i = {1, 1}, l = {24, 26, 32}, m = "invokeSuspend", n = {"cameraParameters", "frameProcessor"}, s = {"L$0", "L$1"})
    /* renamed from: io.fotoapparat.routine.camera.UpdateConfigurationRoutineKt$updateCameraConfiguration$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CameraDevice $cameraDevice;
        final /* synthetic */ Device $this_updateCameraConfiguration;
        Object L$0;
        Object L$1;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Device device, CameraDevice cameraDevice, Continuation continuation) {
            super(2, continuation);
            this.$this_updateCameraConfiguration = device;
            this.$cameraDevice = cameraDevice;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_updateCameraConfiguration, this.$cameraDevice, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Function1<Frame, Unit> function1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        function1 = (Function1) this.L$1;
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                        this.$cameraDevice.updateFrameProcessor(function1);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } else {
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
                Device device = this.$this_updateCameraConfiguration;
                CameraDevice cameraDevice = this.$cameraDevice;
                this.label = 1;
                obj = device.getCameraParameters(cameraDevice, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            CameraParameters cameraParameters = (CameraParameters) obj;
            Function1<Frame, Unit> frameProcessor = this.$this_updateCameraConfiguration.getFrameProcessor();
            CameraDevice cameraDevice2 = this.$cameraDevice;
            this.L$0 = cameraParameters;
            this.L$1 = frameProcessor;
            this.label = 2;
            if (cameraDevice2.updateParameters(cameraParameters, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            function1 = frameProcessor;
            this.$cameraDevice.updateFrameProcessor(function1);
            return Unit.INSTANCE;
        }
    }

    public static final void updateCameraConfiguration(Device receiver$0, CameraDevice cameraDevice) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(cameraDevice, "cameraDevice");
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(receiver$0, cameraDevice, null), 1, null);
    }
}
