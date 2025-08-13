package io.fotoapparat.result;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FocusResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/fotoapparat/result/FocusResult;", "", "()V", "Focused", "UnableToFocus", "Lio/fotoapparat/result/FocusResult$UnableToFocus;", "Lio/fotoapparat/result/FocusResult$Focused;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public abstract class FocusResult {
    private FocusResult() {
    }

    public /* synthetic */ FocusResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: FocusResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/result/FocusResult$UnableToFocus;", "Lio/fotoapparat/result/FocusResult;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class UnableToFocus extends FocusResult {
        public static final UnableToFocus INSTANCE = new UnableToFocus();

        public String toString() {
            return "FocusResult.UnableToFocus";
        }

        private UnableToFocus() {
            super(null);
        }
    }

    /* compiled from: FocusResult.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lio/fotoapparat/result/FocusResult$Focused;", "Lio/fotoapparat/result/FocusResult;", "()V", "toString", "", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Focused extends FocusResult {
        public static final Focused INSTANCE = new Focused();

        public String toString() {
            return "FocusResult.Focused";
        }

        private Focused() {
            super(null);
        }
    }
}
