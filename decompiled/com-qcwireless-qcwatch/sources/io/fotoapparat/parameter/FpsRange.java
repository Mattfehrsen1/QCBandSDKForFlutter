package io.fotoapparat.parameter;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;

/* compiled from: FpsRange.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0003H\u0096\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u000bH\u0096\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0012\u0010\u0007\u001a\u00020\u0003X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0012\u0010\u0010\u001a\u00020\u0003X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\t¨\u0006\u001e"}, d2 = {"Lio/fotoapparat/parameter/FpsRange;", "Lio/fotoapparat/parameter/Parameter;", "Lkotlin/ranges/ClosedRange;", "", "min", "max", "(II)V", "endInclusive", "getEndInclusive", "()Ljava/lang/Integer;", "isFixed", "", "()Z", "getMax", "()I", "getMin", "start", "getStart", "component1", "component2", "contains", "value", "copy", "equals", FitnessActivities.OTHER, "", "hashCode", "isEmpty", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class FpsRange implements Parameter, ClosedRange<Integer> {
    private final /* synthetic */ IntRange $$delegate_0;
    private final int max;
    private final int min;

    public static /* synthetic */ FpsRange copy$default(FpsRange fpsRange, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = fpsRange.min;
        }
        if ((i3 & 2) != 0) {
            i2 = fpsRange.max;
        }
        return fpsRange.copy(i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMin() {
        return this.min;
    }

    /* renamed from: component2, reason: from getter */
    public final int getMax() {
        return this.max;
    }

    public boolean contains(int value) {
        return this.$$delegate_0.contains(value);
    }

    public final FpsRange copy(int min, int max) {
        return new FpsRange(min, max);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof FpsRange) {
                FpsRange fpsRange = (FpsRange) other;
                if (this.min == fpsRange.min) {
                    if (this.max == fpsRange.max) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // kotlin.ranges.ClosedRange
    public Integer getEndInclusive() {
        return this.$$delegate_0.getEndInclusive();
    }

    @Override // kotlin.ranges.ClosedRange
    public Integer getStart() {
        return this.$$delegate_0.getStart();
    }

    public int hashCode() {
        return (this.min * 31) + this.max;
    }

    @Override // kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return this.$$delegate_0.isEmpty();
    }

    public String toString() {
        return "FpsRange(min=" + this.min + ", max=" + this.max + ")";
    }

    public FpsRange(int i, int i2) {
        this.$$delegate_0 = new IntRange(i, i2);
        this.min = i;
        this.max = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return contains(((Number) comparable).intValue());
    }

    public final int getMin() {
        return this.min;
    }

    public final int getMax() {
        return this.max;
    }

    public final boolean isFixed() {
        return this.max == this.min;
    }
}
