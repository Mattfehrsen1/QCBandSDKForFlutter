package io.fotoapparat.error;

import android.os.Looper;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.hardware.ExecutorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ErrorCallbacks.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004*\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u0002`\u0004*\"\u0010\u0005\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\u0006"}, d2 = {"onMainThread", "Lkotlin/Function1;", "Lio/fotoapparat/exception/camera/CameraException;", "", "Lio/fotoapparat/error/CameraErrorCallback;", "CameraErrorCallback", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class ErrorCallbacksKt {
    public static final Function1<CameraException, Unit> onMainThread(final Function1<? super CameraException, Unit> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new Function1<CameraException, Unit>() { // from class: io.fotoapparat.error.ErrorCallbacksKt.onMainThread.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CameraException cameraException) {
                invoke2(cameraException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final CameraException cameraException) {
                Intrinsics.checkParameterIsNotNull(cameraException, "cameraException");
                if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                    receiver$0.invoke(cameraException);
                } else {
                    ExecutorKt.executeMainThread(new Function0<Unit>() { // from class: io.fotoapparat.error.ErrorCallbacksKt.onMainThread.1.1
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
                            receiver$0.invoke(cameraException);
                        }
                    });
                }
            }
        };
    }
}
