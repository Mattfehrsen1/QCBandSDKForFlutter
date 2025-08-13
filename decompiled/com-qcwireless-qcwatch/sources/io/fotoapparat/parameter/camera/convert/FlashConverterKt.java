package io.fotoapparat.parameter.camera.convert;

import io.fotoapparat.parameter.Flash;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: FlashConverter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"toCode", "", "Lio/fotoapparat/parameter/Flash;", "toFlash", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class FlashConverterKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final Flash toFlash(String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        switch (receiver$0.hashCode()) {
            case 3551:
                if (receiver$0.equals(DebugKt.DEBUG_PROPERTY_VALUE_ON)) {
                    return Flash.On.INSTANCE;
                }
                return null;
            case 109935:
                if (receiver$0.equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                    return Flash.Off.INSTANCE;
                }
                return null;
            case 3005871:
                if (receiver$0.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                    return Flash.Auto.INSTANCE;
                }
                return null;
            case 110547964:
                if (receiver$0.equals("torch")) {
                    return Flash.Torch.INSTANCE;
                }
                return null;
            case 1081542389:
                if (receiver$0.equals("red-eye")) {
                    return Flash.AutoRedEye.INSTANCE;
                }
                return null;
            default:
                return null;
        }
    }

    public static final String toCode(Flash receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (Intrinsics.areEqual(receiver$0, Flash.On.INSTANCE)) {
            return DebugKt.DEBUG_PROPERTY_VALUE_ON;
        }
        if (Intrinsics.areEqual(receiver$0, Flash.Off.INSTANCE)) {
            return DebugKt.DEBUG_PROPERTY_VALUE_OFF;
        }
        if (Intrinsics.areEqual(receiver$0, Flash.Auto.INSTANCE)) {
            return DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        }
        if (Intrinsics.areEqual(receiver$0, Flash.Torch.INSTANCE)) {
            return "torch";
        }
        if (Intrinsics.areEqual(receiver$0, Flash.AutoRedEye.INSTANCE)) {
            return "red-eye";
        }
        throw new NoWhenBranchMatchedException();
    }
}
