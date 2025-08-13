package com.qcwireless.qcwatch.ui.device.camera;

import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.selector.AspectRatioSelectorsKt;
import io.fotoapparat.selector.FlashSelectorsKt;
import io.fotoapparat.selector.FocusModeSelectorsKt;
import io.fotoapparat.selector.LensPositionSelectorsKt;
import io.fotoapparat.selector.PreviewFpsRangeSelectorsKt;
import io.fotoapparat.selector.ResolutionSelectorsKt;
import io.fotoapparat.selector.SelectorsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraActivity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000f\u0010B4\b\u0004\u0012#\u0010\u0002\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\u0002\b\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR.\u0010\u0002\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003j\u0002`\u0006¢\u0006\u0002\b\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0001\u0002\u0011\u0012¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/camera/Camera;", "", "lensPosition", "Lkotlin/Function1;", "", "Lio/fotoapparat/characteristic/LensPosition;", "Lio/fotoapparat/selector/LensPositionSelector;", "Lkotlin/ExtensionFunctionType;", "configuration", "Lio/fotoapparat/configuration/CameraConfiguration;", "(Lkotlin/jvm/functions/Function1;Lio/fotoapparat/configuration/CameraConfiguration;)V", "getConfiguration", "()Lio/fotoapparat/configuration/CameraConfiguration;", "getLensPosition", "()Lkotlin/jvm/functions/Function1;", "Back", "Front", "Lcom/qcwireless/qcwatch/ui/device/camera/Camera$Back;", "Lcom/qcwireless/qcwatch/ui/device/camera/Camera$Front;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Camera {
    private final CameraConfiguration configuration;
    private final Function1<Iterable<? extends LensPosition>, LensPosition> lensPosition;

    public /* synthetic */ Camera(Function1 function1, CameraConfiguration cameraConfiguration, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, cameraConfiguration);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Camera(Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> function1, CameraConfiguration cameraConfiguration) {
        this.lensPosition = function1;
        this.configuration = cameraConfiguration;
    }

    public final Function1<Iterable<? extends LensPosition>, LensPosition> getLensPosition() {
        return this.lensPosition;
    }

    public final CameraConfiguration getConfiguration() {
        return this.configuration;
    }

    /* compiled from: CameraActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/camera/Camera$Back;", "Lcom/qcwireless/qcwatch/ui/device/camera/Camera;", "()V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Back extends Camera {
        public static final Back INSTANCE = new Back();

        /* JADX WARN: Illegal instructions before constructor call */
        private Back() {
            Function1<Iterable<? extends LensPosition>, LensPosition> function1Back = LensPositionSelectorsKt.back();
            Function1 function1FirstAvailable = SelectorsKt.firstAvailable(AspectRatioSelectorsKt.wideRatio$default(ResolutionSelectorsKt.highestResolution(), 0.0d, 2, null), AspectRatioSelectorsKt.standardRatio$default(ResolutionSelectorsKt.highestResolution(), 0.0d, 2, null));
            super(function1Back, new CameraConfiguration(FlashSelectorsKt.off(), SelectorsKt.firstAvailable(FocusModeSelectorsKt.continuousFocusPicture(), FocusModeSelectorsKt.autoFocus()), null, null, new Function1<Frame, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.Camera.Back.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Frame it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Frame frame) {
                    invoke2(frame);
                    return Unit.INSTANCE;
                }
            }, PreviewFpsRangeSelectorsKt.highestFps(), null, null, null, function1FirstAvailable, 460, null), null);
        }
    }

    /* compiled from: CameraActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/camera/Camera$Front;", "Lcom/qcwireless/qcwatch/ui/device/camera/Camera;", "()V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Front extends Camera {
        public static final Front INSTANCE = new Front();

        /* JADX WARN: Illegal instructions before constructor call */
        private Front() {
            Function1<Iterable<? extends LensPosition>, LensPosition> function1Front = LensPositionSelectorsKt.front();
            Function1 function1FirstAvailable = SelectorsKt.firstAvailable(AspectRatioSelectorsKt.wideRatio$default(ResolutionSelectorsKt.highestResolution(), 0.0d, 2, null), AspectRatioSelectorsKt.standardRatio$default(ResolutionSelectorsKt.highestResolution(), 0.0d, 2, null));
            super(function1Front, new CameraConfiguration(FlashSelectorsKt.off(), SelectorsKt.firstAvailable(FocusModeSelectorsKt.fixed(), FocusModeSelectorsKt.autoFocus()), null, null, null, PreviewFpsRangeSelectorsKt.highestFps(), null, null, null, function1FirstAvailable, 476, null), null);
        }
    }
}
