package io.fotoapparat.routine.zoom;

import io.fotoapparat.exception.LevelOutOfRangeException;
import io.fotoapparat.hardware.Device;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UpdateZoomLevelRoutine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0016\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0002H\u0000¨\u0006\u0006"}, d2 = {"ensureInBounds", "", "", "updateZoomLevel", "Lio/fotoapparat/hardware/Device;", "zoomLevel", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class UpdateZoomLevelRoutineKt {

    /* compiled from: UpdateZoomLevelRoutine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/routine/zoom/UpdateZoomLevelRoutineKt$updateZoomLevel$1", f = "UpdateZoomLevelRoutine.kt", i = {1}, l = {16, 20, 21}, m = "invokeSuspend", n = {"cameraDevice"}, s = {"L$0"})
    /* renamed from: io.fotoapparat.routine.zoom.UpdateZoomLevelRoutineKt$updateZoomLevel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Device $this_updateZoomLevel;
        final /* synthetic */ float $zoomLevel;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Device device, float f, Continuation continuation) {
            super(2, continuation);
            this.$this_updateZoomLevel = device;
            this.$zoomLevel = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_updateZoomLevel, this.$zoomLevel, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x005c  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) throws java.lang.Throwable {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L2f
                if (r1 == r3) goto L25
                if (r1 != r2) goto L1c
                java.lang.Object r0 = r4.L$0
                io.fotoapparat.hardware.CameraDevice r0 = (io.fotoapparat.hardware.CameraDevice) r0
                boolean r1 = r5 instanceof kotlin.Result.Failure
                if (r1 != 0) goto L17
                goto L52
            L17:
                kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
                java.lang.Throwable r5 = r5.exception
                throw r5
            L1c:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L25:
                boolean r1 = r5 instanceof kotlin.Result.Failure
                if (r1 != 0) goto L2a
                goto L43
            L2a:
                kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
                java.lang.Throwable r5 = r5.exception
                throw r5
            L2f:
                boolean r1 = r5 instanceof kotlin.Result.Failure
                if (r1 != 0) goto L64
                float r5 = r4.$zoomLevel
                io.fotoapparat.routine.zoom.UpdateZoomLevelRoutineKt.access$ensureInBounds(r5)
                io.fotoapparat.hardware.Device r5 = r4.$this_updateZoomLevel
                r4.label = r3
                java.lang.Object r5 = r5.awaitSelectedCamera(r4)
                if (r5 != r0) goto L43
                return r0
            L43:
                io.fotoapparat.hardware.CameraDevice r5 = (io.fotoapparat.hardware.CameraDevice) r5
                r4.L$0 = r5
                r4.label = r2
                java.lang.Object r1 = r5.getCapabilities(r4)
                if (r1 != r0) goto L50
                return r0
            L50:
                r0 = r5
                r5 = r1
            L52:
                io.fotoapparat.capability.Capabilities r5 = (io.fotoapparat.capability.Capabilities) r5
                io.fotoapparat.parameter.Zoom r5 = r5.getZoom()
                boolean r5 = r5 instanceof io.fotoapparat.parameter.Zoom.VariableZoom
                if (r5 == 0) goto L61
                float r5 = r4.$zoomLevel
                r0.setZoom(r5)
            L61:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            L64:
                kotlin.Result$Failure r5 = (kotlin.Result.Failure) r5
                java.lang.Throwable r5 = r5.exception
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fotoapparat.routine.zoom.UpdateZoomLevelRoutineKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void updateZoomLevel(Device receiver$0, float f) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(receiver$0, f, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ensureInBounds(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new LevelOutOfRangeException(f);
        }
    }
}
