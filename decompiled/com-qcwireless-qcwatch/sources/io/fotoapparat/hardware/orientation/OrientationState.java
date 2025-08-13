package io.fotoapparat.hardware.orientation;

import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OrientationState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\n\u0010\u0005\u001a\u00060\u0003j\u0002`\u0006¢\u0006\u0002\u0010\u0007J\r\u0010\u000b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\r\u0010\f\u001a\u00060\u0003j\u0002`\u0006HÆ\u0003J%\u0010\r\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\f\b\u0002\u0010\u0005\u001a\u00060\u0003j\u0002`\u0006HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u00060\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lio/fotoapparat/hardware/orientation/OrientationState;", "", "deviceOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "Lio/fotoapparat/hardware/orientation/DeviceOrientation;", "screenOrientation", "Lio/fotoapparat/hardware/orientation/ScreenOrientation;", "(Lio/fotoapparat/hardware/orientation/Orientation;Lio/fotoapparat/hardware/orientation/Orientation;)V", "getDeviceOrientation", "()Lio/fotoapparat/hardware/orientation/Orientation;", "getScreenOrientation", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class OrientationState {
    private final Orientation deviceOrientation;
    private final Orientation screenOrientation;

    public static /* synthetic */ OrientationState copy$default(OrientationState orientationState, Orientation orientation, Orientation orientation2, int i, Object obj) {
        if ((i & 1) != 0) {
            orientation = orientationState.deviceOrientation;
        }
        if ((i & 2) != 0) {
            orientation2 = orientationState.screenOrientation;
        }
        return orientationState.copy(orientation, orientation2);
    }

    /* renamed from: component1, reason: from getter */
    public final Orientation getDeviceOrientation() {
        return this.deviceOrientation;
    }

    /* renamed from: component2, reason: from getter */
    public final Orientation getScreenOrientation() {
        return this.screenOrientation;
    }

    public final OrientationState copy(Orientation deviceOrientation, Orientation screenOrientation) {
        Intrinsics.checkParameterIsNotNull(deviceOrientation, "deviceOrientation");
        Intrinsics.checkParameterIsNotNull(screenOrientation, "screenOrientation");
        return new OrientationState(deviceOrientation, screenOrientation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrientationState)) {
            return false;
        }
        OrientationState orientationState = (OrientationState) other;
        return Intrinsics.areEqual(this.deviceOrientation, orientationState.deviceOrientation) && Intrinsics.areEqual(this.screenOrientation, orientationState.screenOrientation);
    }

    public int hashCode() {
        Orientation orientation = this.deviceOrientation;
        int iHashCode = (orientation != null ? orientation.hashCode() : 0) * 31;
        Orientation orientation2 = this.screenOrientation;
        return iHashCode + (orientation2 != null ? orientation2.hashCode() : 0);
    }

    public String toString() {
        return "OrientationState(deviceOrientation=" + this.deviceOrientation + ", screenOrientation=" + this.screenOrientation + ")";
    }

    public OrientationState(Orientation deviceOrientation, Orientation screenOrientation) {
        Intrinsics.checkParameterIsNotNull(deviceOrientation, "deviceOrientation");
        Intrinsics.checkParameterIsNotNull(screenOrientation, "screenOrientation");
        this.deviceOrientation = deviceOrientation;
        this.screenOrientation = screenOrientation;
    }

    public final Orientation getDeviceOrientation() {
        return this.deviceOrientation;
    }

    public final Orientation getScreenOrientation() {
        return this.screenOrientation;
    }
}
