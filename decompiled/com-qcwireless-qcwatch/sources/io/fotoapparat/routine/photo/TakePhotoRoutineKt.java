package io.fotoapparat.routine.photo;

import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.hardware.CameraDevice;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.result.Photo;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TakePhotoRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"startPreviewSafely", "", "Lio/fotoapparat/hardware/CameraDevice;", "takePhoto", "Lio/fotoapparat/result/Photo;", "Lio/fotoapparat/hardware/Device;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class TakePhotoRoutineKt {

    /* compiled from: TakePhotoRoutine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lio/fotoapparat/result/Photo;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/routine/photo/TakePhotoRoutineKt$takePhoto$1", f = "TakePhotoRoutine.kt", i = {}, l = {12, 15}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.fotoapparat.routine.photo.TakePhotoRoutineKt$takePhoto$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Photo>, Object> {
        final /* synthetic */ Device $this_takePhoto;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Device device, Continuation continuation) {
            super(2, continuation);
            this.$this_takePhoto = device;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_takePhoto, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Photo> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } else {
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
                Device device = this.$this_takePhoto;
                this.label = 1;
                obj = device.awaitSelectedCamera(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            CameraDevice cameraDevice = (CameraDevice) obj;
            Photo photoTakePhoto = cameraDevice.takePhoto();
            TakePhotoRoutineKt.startPreviewSafely(cameraDevice);
            return photoTakePhoto;
        }
    }

    public static final Photo takePhoto(Device receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return (Photo) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(receiver$0, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startPreviewSafely(CameraDevice cameraDevice) {
        try {
            cameraDevice.startPreview();
        } catch (CameraException unused) {
        }
    }
}
