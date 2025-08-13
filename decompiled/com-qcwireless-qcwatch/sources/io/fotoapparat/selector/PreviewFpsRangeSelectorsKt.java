package io.fotoapparat.selector;

import io.fotoapparat.parameter.FpsRange;
import io.fotoapparat.util.CompareFpsRangeByBounds;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PreviewFpsRangeSelectors.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a+\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a#\u0010\t\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a#\u0010\n\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a#\u0010\u000b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a%\u0010\f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005H\u0002\u001a#\u0010\r\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a#\u0010\u000e\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a#\u0010\u000f\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005\u001a%\u0010\u0010\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0002`\u0004¢\u0006\u0002\b\u0005H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0007H\u0002*<\u0010\u0013\"\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001¢\u0006\u0002\b\u00052\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001¢\u0006\u0002\b\u0005¨\u0006\u0014"}, d2 = {"containsFps", "Lkotlin/Function1;", "", "Lio/fotoapparat/parameter/FpsRange;", "Lio/fotoapparat/selector/FpsRangeSelector;", "Lkotlin/ExtensionFunctionType;", "fps", "", "exactFixedFps", "highestFixedFps", "highestFps", "highestNonFixedFps", "highestRangeFps", "lowestFixedFps", "lowestFps", "lowestNonFixedFps", "lowestRangeFps", "toFpsIntRepresentation", "", "FpsRangeSelector", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class PreviewFpsRangeSelectorsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int toFpsIntRepresentation(float f) {
        return (int) (f * 1000);
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> containsFps(final float f) {
        return SelectorsKt.firstAvailable(exactFixedFps(f), SelectorsKt.filtered(highestNonFixedFps(), new Function1<FpsRange, Boolean>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.containsFps.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FpsRange fpsRange) {
                return Boolean.valueOf(invoke2(fpsRange));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(FpsRange range) {
                Intrinsics.checkParameterIsNotNull(range, "range");
                return range.contains(PreviewFpsRangeSelectorsKt.toFpsIntRepresentation(f));
            }
        }));
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> exactFixedFps(final float f) {
        return SelectorsKt.filtered(highestFixedFps(), new Function1<FpsRange, Boolean>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.exactFixedFps.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FpsRange fpsRange) {
                return Boolean.valueOf(invoke2(fpsRange));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(FpsRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getMin() == PreviewFpsRangeSelectorsKt.toFpsIntRepresentation(f);
            }
        });
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> highestFps() {
        return SelectorsKt.firstAvailable(highestNonFixedFps(), highestFixedFps());
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> highestNonFixedFps() {
        return SelectorsKt.filtered(highestRangeFps(), new Function1<FpsRange, Boolean>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.highestNonFixedFps.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FpsRange fpsRange) {
                return Boolean.valueOf(invoke2(fpsRange));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(FpsRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return !it.isFixed();
            }
        });
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> highestFixedFps() {
        return SelectorsKt.filtered(highestRangeFps(), new Function1<FpsRange, Boolean>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.highestFixedFps.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FpsRange fpsRange) {
                return Boolean.valueOf(invoke2(fpsRange));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(FpsRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.isFixed();
            }
        });
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> lowestFps() {
        return SelectorsKt.firstAvailable(lowestNonFixedFps(), lowestFixedFps());
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> lowestNonFixedFps() {
        return SelectorsKt.filtered(lowestRangeFps(), new Function1<FpsRange, Boolean>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.lowestNonFixedFps.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FpsRange fpsRange) {
                return Boolean.valueOf(invoke2(fpsRange));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(FpsRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return !it.isFixed();
            }
        });
    }

    public static final Function1<Iterable<FpsRange>, FpsRange> lowestFixedFps() {
        return SelectorsKt.filtered(lowestRangeFps(), new Function1<FpsRange, Boolean>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.lowestFixedFps.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(FpsRange fpsRange) {
                return Boolean.valueOf(invoke2(fpsRange));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(FpsRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.isFixed();
            }
        });
    }

    private static final Function1<Iterable<FpsRange>, FpsRange> highestRangeFps() {
        return new Function1<Iterable<? extends FpsRange>, FpsRange>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.highestRangeFps.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ FpsRange invoke(Iterable<? extends FpsRange> iterable) {
                return invoke2((Iterable<FpsRange>) iterable);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final FpsRange invoke2(Iterable<FpsRange> receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                return (FpsRange) CollectionsKt.maxWith(receiver$0, CompareFpsRangeByBounds.INSTANCE);
            }
        };
    }

    private static final Function1<Iterable<FpsRange>, FpsRange> lowestRangeFps() {
        return new Function1<Iterable<? extends FpsRange>, FpsRange>() { // from class: io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.lowestRangeFps.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ FpsRange invoke(Iterable<? extends FpsRange> iterable) {
                return invoke2((Iterable<FpsRange>) iterable);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final FpsRange invoke2(Iterable<FpsRange> receiver$0) {
                Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                return (FpsRange) CollectionsKt.minWith(receiver$0, CompareFpsRangeByBounds.INSTANCE);
            }
        };
    }
}
