package io.fotoapparat.parameter;

import com.google.android.gms.fitness.FitnessActivities;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Zoom.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/fotoapparat/parameter/Zoom;", "", "()V", "FixedZoom", "VariableZoom", "Lio/fotoapparat/parameter/Zoom$FixedZoom;", "Lio/fotoapparat/parameter/Zoom$VariableZoom;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class Zoom {
    private Zoom() {
    }

    public /* synthetic */ Zoom(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: Zoom.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/parameter/Zoom$FixedZoom;", "Lio/fotoapparat/parameter/Zoom;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class FixedZoom extends Zoom {
        public static final FixedZoom INSTANCE = new FixedZoom();

        public String toString() {
            return "Zoom.FixedZoom";
        }

        private FixedZoom() {
            super(null);
        }
    }

    /* compiled from: Zoom.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lio/fotoapparat/parameter/Zoom$VariableZoom;", "Lio/fotoapparat/parameter/Zoom;", "maxZoom", "", "zoomRatios", "", "(ILjava/util/List;)V", "getMaxZoom", "()I", "getZoomRatios", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final /* data */ class VariableZoom extends Zoom {
        private final int maxZoom;
        private final List<Integer> zoomRatios;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VariableZoom copy$default(VariableZoom variableZoom, int i, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = variableZoom.maxZoom;
            }
            if ((i2 & 2) != 0) {
                list = variableZoom.zoomRatios;
            }
            return variableZoom.copy(i, list);
        }

        /* renamed from: component1, reason: from getter */
        public final int getMaxZoom() {
            return this.maxZoom;
        }

        public final List<Integer> component2() {
            return this.zoomRatios;
        }

        public final VariableZoom copy(int maxZoom, List<Integer> zoomRatios) {
            Intrinsics.checkParameterIsNotNull(zoomRatios, "zoomRatios");
            return new VariableZoom(maxZoom, zoomRatios);
        }

        public boolean equals(Object other) {
            if (this != other) {
                if (other instanceof VariableZoom) {
                    VariableZoom variableZoom = (VariableZoom) other;
                    if (!(this.maxZoom == variableZoom.maxZoom) || !Intrinsics.areEqual(this.zoomRatios, variableZoom.zoomRatios)) {
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.maxZoom * 31;
            List<Integer> list = this.zoomRatios;
            return i + (list != null ? list.hashCode() : 0);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VariableZoom(int i, List<Integer> zoomRatios) {
            super(null);
            Intrinsics.checkParameterIsNotNull(zoomRatios, "zoomRatios");
            this.maxZoom = i;
            this.zoomRatios = zoomRatios;
        }

        public final int getMaxZoom() {
            return this.maxZoom;
        }

        public final List<Integer> getZoomRatios() {
            return this.zoomRatios;
        }

        public String toString() {
            return "Zoom.VariableZoom(maxZoom=" + this.maxZoom + ", zoomRatios=" + this.zoomRatios + ')';
        }
    }
}
