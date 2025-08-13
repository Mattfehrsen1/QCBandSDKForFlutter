package io.fotoapparat.hardware.display;

import android.content.Context;
import io.fotoapparat.hardware.orientation.Orientation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Display.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/fotoapparat/hardware/display/Display;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "display", "Landroid/view/Display;", "kotlin.jvm.PlatformType", "getOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public class Display {
    private final android.view.Display display;

    public Display(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.display = DisplayKt.getDisplay(context);
    }

    public Orientation getOrientation() {
        android.view.Display display = this.display;
        Intrinsics.checkExpressionValueIsNotNull(display, "display");
        int rotation = display.getRotation();
        if (rotation == 0) {
            return Orientation.Vertical.Portrait.INSTANCE;
        }
        if (rotation == 1) {
            return Orientation.Horizontal.Landscape.INSTANCE;
        }
        if (rotation == 2) {
            return Orientation.Vertical.ReversePortrait.INSTANCE;
        }
        if (rotation == 3) {
            return Orientation.Horizontal.ReverseLandscape.INSTANCE;
        }
        return Orientation.Vertical.Portrait.INSTANCE;
    }
}
