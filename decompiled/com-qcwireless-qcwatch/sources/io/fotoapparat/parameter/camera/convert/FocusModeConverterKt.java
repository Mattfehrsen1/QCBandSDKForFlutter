package io.fotoapparat.parameter.camera.convert;

import io.fotoapparat.parameter.FocusMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: FocusModeConverter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u000e\u0010\u0003\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"toCode", "", "Lio/fotoapparat/parameter/FocusMode;", "toFocusMode", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class FocusModeConverterKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final FocusMode toFocusMode(String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        switch (receiver$0.hashCode()) {
            case -194628547:
                if (receiver$0.equals("continuous-video")) {
                    return FocusMode.ContinuousFocusVideo.INSTANCE;
                }
                return null;
            case 3005871:
                if (receiver$0.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                    return FocusMode.Auto.INSTANCE;
                }
                return null;
            case 3108534:
                if (receiver$0.equals("edof")) {
                    return FocusMode.Edof.INSTANCE;
                }
                return null;
            case 97445748:
                if (receiver$0.equals("fixed")) {
                    return FocusMode.Fixed.INSTANCE;
                }
                return null;
            case 103652300:
                if (receiver$0.equals("macro")) {
                    return FocusMode.Macro.INSTANCE;
                }
                return null;
            case 173173288:
                if (receiver$0.equals("infinity")) {
                    return FocusMode.Infinity.INSTANCE;
                }
                return null;
            case 910005312:
                if (receiver$0.equals("continuous-picture")) {
                    return FocusMode.ContinuousFocusPicture.INSTANCE;
                }
                return null;
            default:
                return null;
        }
    }

    public static final String toCode(FocusMode receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (Intrinsics.areEqual(receiver$0, FocusMode.Edof.INSTANCE)) {
            return "edof";
        }
        if (Intrinsics.areEqual(receiver$0, FocusMode.Auto.INSTANCE)) {
            return DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        }
        if (Intrinsics.areEqual(receiver$0, FocusMode.Macro.INSTANCE)) {
            return "macro";
        }
        if (Intrinsics.areEqual(receiver$0, FocusMode.Fixed.INSTANCE)) {
            return "fixed";
        }
        if (Intrinsics.areEqual(receiver$0, FocusMode.Infinity.INSTANCE)) {
            return "infinity";
        }
        if (Intrinsics.areEqual(receiver$0, FocusMode.ContinuousFocusVideo.INSTANCE)) {
            return "continuous-video";
        }
        if (Intrinsics.areEqual(receiver$0, FocusMode.ContinuousFocusPicture.INSTANCE)) {
            return "continuous-picture";
        }
        throw new NoWhenBranchMatchedException();
    }
}
