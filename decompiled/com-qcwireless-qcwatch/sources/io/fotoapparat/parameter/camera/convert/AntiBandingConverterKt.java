package io.fotoapparat.parameter.camera.convert;

import io.fotoapparat.parameter.AntiBandingMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: AntiBandingConverter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"toAntiBandingMode", "Lio/fotoapparat/parameter/AntiBandingMode;", "", "toCode", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class AntiBandingConverterKt {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final AntiBandingMode toAntiBandingMode(String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        switch (receiver$0.hashCode()) {
            case 109935:
                if (receiver$0.equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                    return AntiBandingMode.None.INSTANCE;
                }
                return null;
            case 1628397:
                if (receiver$0.equals("50hz")) {
                    return AntiBandingMode.HZ50.INSTANCE;
                }
                return null;
            case 1658188:
                if (receiver$0.equals("60hz")) {
                    return AntiBandingMode.HZ60.INSTANCE;
                }
                return null;
            case 3005871:
                if (receiver$0.equals(DebugKt.DEBUG_PROPERTY_VALUE_AUTO)) {
                    return AntiBandingMode.Auto.INSTANCE;
                }
                return null;
            default:
                return null;
        }
    }

    public static final String toCode(AntiBandingMode receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        if (Intrinsics.areEqual(receiver$0, AntiBandingMode.Auto.INSTANCE)) {
            return DebugKt.DEBUG_PROPERTY_VALUE_AUTO;
        }
        if (Intrinsics.areEqual(receiver$0, AntiBandingMode.HZ50.INSTANCE)) {
            return "50hz";
        }
        if (Intrinsics.areEqual(receiver$0, AntiBandingMode.HZ60.INSTANCE)) {
            return "60hz";
        }
        if (Intrinsics.areEqual(receiver$0, AntiBandingMode.None.INSTANCE)) {
            return DebugKt.DEBUG_PROPERTY_VALUE_OFF;
        }
        throw new NoWhenBranchMatchedException();
    }
}
