package io.fotoapparat.hardware.metering;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;

/* compiled from: PointF.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/fotoapparat/hardware/metering/PointF;", "", "x", "", "y", "(FF)V", "getX", "()F", "getY", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class PointF {
    private final float x;
    private final float y;

    public static /* synthetic */ PointF copy$default(PointF pointF, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = pointF.x;
        }
        if ((i & 2) != 0) {
            f2 = pointF.y;
        }
        return pointF.copy(f, f2);
    }

    /* renamed from: component1, reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* renamed from: component2, reason: from getter */
    public final float getY() {
        return this.y;
    }

    public final PointF copy(float x, float y) {
        return new PointF(x, y);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointF)) {
            return false;
        }
        PointF pointF = (PointF) other;
        return Float.compare(this.x, pointF.x) == 0 && Float.compare(this.y, pointF.y) == 0;
    }

    public int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public String toString() {
        return "PointF(x=" + this.x + ", y=" + this.y + ")";
    }

    public PointF(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }
}
