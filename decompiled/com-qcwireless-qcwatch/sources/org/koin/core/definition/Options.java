package org.koin.core.definition;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Options.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/koin/core/definition/Options;", "", "isCreatedAtStart", "", "override", "(ZZ)V", "()Z", "setCreatedAtStart", "(Z)V", "getOverride", "setOverride", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "koin-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes5.dex */
public final /* data */ class Options {
    private boolean isCreatedAtStart;
    private boolean override;

    /* JADX WARN: Illegal instructions before constructor call */
    public Options() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public static /* synthetic */ Options copy$default(Options options, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = options.isCreatedAtStart;
        }
        if ((i & 2) != 0) {
            z2 = options.override;
        }
        return options.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsCreatedAtStart() {
        return this.isCreatedAtStart;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getOverride() {
        return this.override;
    }

    public final Options copy(boolean isCreatedAtStart, boolean override) {
        return new Options(isCreatedAtStart, override);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof Options) {
                Options options = (Options) other;
                if (this.isCreatedAtStart == options.isCreatedAtStart) {
                    if (this.override == options.override) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.isCreatedAtStart;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        boolean z2 = this.override;
        return i + (z2 ? 1 : z2 ? 1 : 0);
    }

    public String toString() {
        return "Options(isCreatedAtStart=" + this.isCreatedAtStart + ", override=" + this.override + ")";
    }

    public Options(boolean z, boolean z2) {
        this.isCreatedAtStart = z;
        this.override = z2;
    }

    public /* synthetic */ Options(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getOverride() {
        return this.override;
    }

    public final boolean isCreatedAtStart() {
        return this.isCreatedAtStart;
    }

    public final void setCreatedAtStart(boolean z) {
        this.isCreatedAtStart = z;
    }

    public final void setOverride(boolean z) {
        this.override = z;
    }
}
