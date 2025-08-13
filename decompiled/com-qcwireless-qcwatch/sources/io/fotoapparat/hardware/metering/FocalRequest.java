package io.fotoapparat.hardware.metering;

import com.google.android.gms.fitness.FitnessActivities;
import io.fotoapparat.parameter.Resolution;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocalRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lio/fotoapparat/hardware/metering/FocalRequest;", "", "point", "Lio/fotoapparat/hardware/metering/PointF;", "previewResolution", "Lio/fotoapparat/parameter/Resolution;", "(Lio/fotoapparat/hardware/metering/PointF;Lio/fotoapparat/parameter/Resolution;)V", "getPoint", "()Lio/fotoapparat/hardware/metering/PointF;", "getPreviewResolution", "()Lio/fotoapparat/parameter/Resolution;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final /* data */ class FocalRequest {
    private final PointF point;
    private final Resolution previewResolution;

    public static /* synthetic */ FocalRequest copy$default(FocalRequest focalRequest, PointF pointF, Resolution resolution, int i, Object obj) {
        if ((i & 1) != 0) {
            pointF = focalRequest.point;
        }
        if ((i & 2) != 0) {
            resolution = focalRequest.previewResolution;
        }
        return focalRequest.copy(pointF, resolution);
    }

    /* renamed from: component1, reason: from getter */
    public final PointF getPoint() {
        return this.point;
    }

    /* renamed from: component2, reason: from getter */
    public final Resolution getPreviewResolution() {
        return this.previewResolution;
    }

    public final FocalRequest copy(PointF point, Resolution previewResolution) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        Intrinsics.checkParameterIsNotNull(previewResolution, "previewResolution");
        return new FocalRequest(point, previewResolution);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FocalRequest)) {
            return false;
        }
        FocalRequest focalRequest = (FocalRequest) other;
        return Intrinsics.areEqual(this.point, focalRequest.point) && Intrinsics.areEqual(this.previewResolution, focalRequest.previewResolution);
    }

    public int hashCode() {
        PointF pointF = this.point;
        int iHashCode = (pointF != null ? pointF.hashCode() : 0) * 31;
        Resolution resolution = this.previewResolution;
        return iHashCode + (resolution != null ? resolution.hashCode() : 0);
    }

    public String toString() {
        return "FocalRequest(point=" + this.point + ", previewResolution=" + this.previewResolution + ")";
    }

    public FocalRequest(PointF point, Resolution previewResolution) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        Intrinsics.checkParameterIsNotNull(previewResolution, "previewResolution");
        this.point = point;
        this.previewResolution = previewResolution;
    }

    public final PointF getPoint() {
        return this.point;
    }

    public final Resolution getPreviewResolution() {
        return this.previewResolution;
    }
}
