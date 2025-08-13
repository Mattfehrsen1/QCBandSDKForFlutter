package io.fotoapparat;

import io.fotoapparat.hardware.Device;
import io.fotoapparat.result.Photo;
import io.fotoapparat.routine.photo.TakePhotoRoutineKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: Fotoapparat.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/fotoapparat/result/Photo;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class Fotoapparat$takePicture$future$1 extends FunctionReference implements Function0<Photo> {
    Fotoapparat$takePicture$future$1(Device device) {
        super(0, device);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "takePhoto";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinPackage(TakePhotoRoutineKt.class, "fotoapparat_release");
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "takePhoto(Lio/fotoapparat/hardware/Device;)Lio/fotoapparat/result/Photo;";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Photo invoke() {
        return TakePhotoRoutineKt.takePhoto((Device) this.receiver);
    }
}
