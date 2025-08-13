package io.fotoapparat.parameter;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Flash.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f¨\u0006\r"}, d2 = {"Lio/fotoapparat/parameter/Flash;", "Lio/fotoapparat/parameter/Parameter;", "()V", "Auto", "AutoRedEye", "Off", "On", "Torch", "Lio/fotoapparat/parameter/Flash$Off;", "Lio/fotoapparat/parameter/Flash$On;", "Lio/fotoapparat/parameter/Flash$Auto;", "Lio/fotoapparat/parameter/Flash$AutoRedEye;", "Lio/fotoapparat/parameter/Flash$Torch;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class Flash implements Parameter {
    private Flash() {
    }

    public /* synthetic */ Flash(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: Flash.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/parameter/Flash$Off;", "Lio/fotoapparat/parameter/Flash;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Off extends Flash {
        public static final Off INSTANCE = new Off();

        public String toString() {
            return "Flash.Off";
        }

        private Off() {
            super(null);
        }
    }

    /* compiled from: Flash.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/parameter/Flash$On;", "Lio/fotoapparat/parameter/Flash;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class On extends Flash {
        public static final On INSTANCE = new On();

        public String toString() {
            return "Flash.On";
        }

        private On() {
            super(null);
        }
    }

    /* compiled from: Flash.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/parameter/Flash$Auto;", "Lio/fotoapparat/parameter/Flash;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Auto extends Flash {
        public static final Auto INSTANCE = new Auto();

        public String toString() {
            return "Flash.Auto";
        }

        private Auto() {
            super(null);
        }
    }

    /* compiled from: Flash.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/parameter/Flash$AutoRedEye;", "Lio/fotoapparat/parameter/Flash;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class AutoRedEye extends Flash {
        public static final AutoRedEye INSTANCE = new AutoRedEye();

        public String toString() {
            return "Flash.AutoRedEye";
        }

        private AutoRedEye() {
            super(null);
        }
    }

    /* compiled from: Flash.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/parameter/Flash$Torch;", "Lio/fotoapparat/parameter/Flash;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Torch extends Flash {
        public static final Torch INSTANCE = new Torch();

        public String toString() {
            return "Flash.Torch";
        }

        private Torch() {
            super(null);
        }
    }
}
