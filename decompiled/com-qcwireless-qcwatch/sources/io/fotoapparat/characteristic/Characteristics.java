package io.fotoapparat.characteristic;

import com.google.android.gms.fitness.FitnessActivities;
import io.fotoapparat.hardware.orientation.Orientation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Characteristics.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\tHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lio/fotoapparat/characteristic/Characteristics;", "", "cameraId", "", "lensPosition", "Lio/fotoapparat/characteristic/LensPosition;", "cameraOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "isMirrored", "", "(ILio/fotoapparat/characteristic/LensPosition;Lio/fotoapparat/hardware/orientation/Orientation;Z)V", "getCameraId", "()I", "getCameraOrientation", "()Lio/fotoapparat/hardware/orientation/Orientation;", "()Z", "getLensPosition", "()Lio/fotoapparat/characteristic/LensPosition;", "component1", "component2", "component3", "component4", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class Characteristics {
    private final int cameraId;
    private final Orientation cameraOrientation;
    private final boolean isMirrored;
    private final LensPosition lensPosition;

    public static /* synthetic */ Characteristics copy$default(Characteristics characteristics, int i, LensPosition lensPosition, Orientation orientation, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = characteristics.cameraId;
        }
        if ((i2 & 2) != 0) {
            lensPosition = characteristics.lensPosition;
        }
        if ((i2 & 4) != 0) {
            orientation = characteristics.cameraOrientation;
        }
        if ((i2 & 8) != 0) {
            z = characteristics.isMirrored;
        }
        return characteristics.copy(i, lensPosition, orientation, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getCameraId() {
        return this.cameraId;
    }

    /* renamed from: component2, reason: from getter */
    public final LensPosition getLensPosition() {
        return this.lensPosition;
    }

    /* renamed from: component3, reason: from getter */
    public final Orientation getCameraOrientation() {
        return this.cameraOrientation;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsMirrored() {
        return this.isMirrored;
    }

    public final Characteristics copy(int cameraId, LensPosition lensPosition, Orientation cameraOrientation, boolean isMirrored) {
        Intrinsics.checkParameterIsNotNull(lensPosition, "lensPosition");
        Intrinsics.checkParameterIsNotNull(cameraOrientation, "cameraOrientation");
        return new Characteristics(cameraId, lensPosition, cameraOrientation, isMirrored);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof Characteristics) {
                Characteristics characteristics = (Characteristics) other;
                if ((this.cameraId == characteristics.cameraId) && Intrinsics.areEqual(this.lensPosition, characteristics.lensPosition) && Intrinsics.areEqual(this.cameraOrientation, characteristics.cameraOrientation)) {
                    if (this.isMirrored == characteristics.isMirrored) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int i = this.cameraId * 31;
        LensPosition lensPosition = this.lensPosition;
        int iHashCode = (i + (lensPosition != null ? lensPosition.hashCode() : 0)) * 31;
        Orientation orientation = this.cameraOrientation;
        int iHashCode2 = (iHashCode + (orientation != null ? orientation.hashCode() : 0)) * 31;
        boolean z = this.isMirrored;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return iHashCode2 + i2;
    }

    public String toString() {
        return "Characteristics(cameraId=" + this.cameraId + ", lensPosition=" + this.lensPosition + ", cameraOrientation=" + this.cameraOrientation + ", isMirrored=" + this.isMirrored + ")";
    }

    public Characteristics(int i, LensPosition lensPosition, Orientation cameraOrientation, boolean z) {
        Intrinsics.checkParameterIsNotNull(lensPosition, "lensPosition");
        Intrinsics.checkParameterIsNotNull(cameraOrientation, "cameraOrientation");
        this.cameraId = i;
        this.lensPosition = lensPosition;
        this.cameraOrientation = cameraOrientation;
        this.isMirrored = z;
    }

    public final int getCameraId() {
        return this.cameraId;
    }

    public final LensPosition getLensPosition() {
        return this.lensPosition;
    }

    public final Orientation getCameraOrientation() {
        return this.cameraOrientation;
    }

    public final boolean isMirrored() {
        return this.isMirrored;
    }
}
