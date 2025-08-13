package io.fotoapparat;

import io.fotoapparat.hardware.Device;
import io.fotoapparat.result.FocusResult;
import io.fotoapparat.routine.focus.FocusRoutineKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: Fotoapparat.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/fotoapparat/result/FocusResult;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class Fotoapparat$focus$future$1 extends FunctionReference implements Function0<FocusResult> {
    Fotoapparat$focus$future$1(Device device) {
        super(0, device);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "focus";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinPackage(FocusRoutineKt.class, "fotoapparat_release");
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "focus(Lio/fotoapparat/hardware/Device;)Lio/fotoapparat/result/FocusResult;";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final FocusResult invoke() {
        return FocusRoutineKt.focus((Device) this.receiver);
    }
}
