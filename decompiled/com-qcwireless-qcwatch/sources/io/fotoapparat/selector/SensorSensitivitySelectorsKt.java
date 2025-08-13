package io.fotoapparat.selector;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: SensorSensitivitySelectors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a#\u0010\u0000\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a#\u0010\u0006\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a+\u0010\u0007\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u00052\u0006\u0010\b\u001a\u00020\u0003*<\u0010\t\"\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001¢\u0006\u0002\b\u00052\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001¢\u0006\u0002\b\u0005¨\u0006\n"}, d2 = {"highestSensorSensitivity", "Lkotlin/Function1;", "", "", "Lio/fotoapparat/selector/SensorSensitivitySelector;", "Lkotlin/ExtensionFunctionType;", "lowestSensorSensitivity", "manualSensorSensitivity", "iso", "SensorSensitivitySelector", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class SensorSensitivitySelectorsKt {
    public static final Function1<Iterable<Integer>, Integer> manualSensorSensitivity(int i) {
        return SelectorsKt.single(Integer.valueOf(i));
    }

    public static final Function1<Iterable<Integer>, Integer> highestSensorSensitivity() {
        return SelectorsKt.highest();
    }

    public static final Function1<Iterable<Integer>, Integer> lowestSensorSensitivity() {
        return SelectorsKt.lowest();
    }
}
