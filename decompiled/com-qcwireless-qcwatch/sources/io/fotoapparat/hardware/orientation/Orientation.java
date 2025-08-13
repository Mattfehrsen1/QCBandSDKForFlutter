package io.fotoapparat.hardware.orientation;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Orientation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation;", "", "degrees", "", "(I)V", "getDegrees", "()I", "Horizontal", "Vertical", "Lio/fotoapparat/hardware/orientation/Orientation$Vertical;", "Lio/fotoapparat/hardware/orientation/Orientation$Horizontal;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class Orientation {
    private final int degrees;

    private Orientation(int i) {
        this.degrees = i;
    }

    public /* synthetic */ Orientation(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public final int getDegrees() {
        return this.degrees;
    }

    /* compiled from: Orientation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0005\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation$Vertical;", "Lio/fotoapparat/hardware/orientation/Orientation;", "degrees", "", "(I)V", "Portrait", "ReversePortrait", "Lio/fotoapparat/hardware/orientation/Orientation$Vertical$Portrait;", "Lio/fotoapparat/hardware/orientation/Orientation$Vertical$ReversePortrait;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static abstract class Vertical extends Orientation {
        private Vertical(int i) {
            super(i, null);
        }

        public /* synthetic */ Vertical(int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(i);
        }

        /* compiled from: Orientation.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation$Vertical$Portrait;", "Lio/fotoapparat/hardware/orientation/Orientation$Vertical;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
        public static final class Portrait extends Vertical {
            public static final Portrait INSTANCE = new Portrait();

            public String toString() {
                return "Orientation.Vertical.Portrait";
            }

            private Portrait() {
                super(0, null);
            }
        }

        /* compiled from: Orientation.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation$Vertical$ReversePortrait;", "Lio/fotoapparat/hardware/orientation/Orientation$Vertical;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
        public static final class ReversePortrait extends Vertical {
            public static final ReversePortrait INSTANCE = new ReversePortrait();

            public String toString() {
                return "Orientation.Vertical.ReversePortrait";
            }

            private ReversePortrait() {
                super(180, null);
            }
        }
    }

    /* compiled from: Orientation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0005\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation$Horizontal;", "Lio/fotoapparat/hardware/orientation/Orientation;", "degrees", "", "(I)V", "Landscape", "ReverseLandscape", "Lio/fotoapparat/hardware/orientation/Orientation$Horizontal$Landscape;", "Lio/fotoapparat/hardware/orientation/Orientation$Horizontal$ReverseLandscape;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static abstract class Horizontal extends Orientation {
        private Horizontal(int i) {
            super(i, null);
        }

        public /* synthetic */ Horizontal(int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(i);
        }

        /* compiled from: Orientation.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation$Horizontal$Landscape;", "Lio/fotoapparat/hardware/orientation/Orientation$Horizontal;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
        public static final class Landscape extends Horizontal {
            public static final Landscape INSTANCE = new Landscape();

            public String toString() {
                return "Orientation.Horizontal.Landscape";
            }

            private Landscape() {
                super(90, null);
            }
        }

        /* compiled from: Orientation.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/hardware/orientation/Orientation$Horizontal$ReverseLandscape;", "Lio/fotoapparat/hardware/orientation/Orientation$Horizontal;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
        public static final class ReverseLandscape extends Horizontal {
            public static final ReverseLandscape INSTANCE = new ReverseLandscape();

            public String toString() {
                return "Orientation.Horizontal.ReverseLandscape";
            }

            private ReverseLandscape() {
                super(270, null);
            }
        }
    }
}
