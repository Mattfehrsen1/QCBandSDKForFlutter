package io.fotoapparat.result;

import com.qcwireless.qcwatch.ui.mine.ai.bean.ChatEnumAction;
import io.fotoapparat.hardware.ExecutorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: PendingResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0002*\u0016\u0010\u0004\"\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0012\u0004\u0012\u00020\u00060\u0005*\u0016\u0010\u0007\"\b\u0012\u0004\u0012\u00020\b0\u00052\b\u0012\u0004\u0012\u00020\b0\u0005¨\u0006\t"}, d2 = {"notifyOnMainThread", "", ChatEnumAction.FUNCTION, "Lkotlin/Function0;", "CapabilitiesResult", "Lio/fotoapparat/result/PendingResult;", "Lio/fotoapparat/capability/Capabilities;", "ParametersResult", "Lio/fotoapparat/parameter/camera/CameraParameters;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class PendingResultKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyOnMainThread(final Function0<Unit> function0) {
        ExecutorKt.executeMainThread(new Function0<Unit>() { // from class: io.fotoapparat.result.PendingResultKt.notifyOnMainThread.1
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
                function0.invoke();
            }
        });
    }
}
