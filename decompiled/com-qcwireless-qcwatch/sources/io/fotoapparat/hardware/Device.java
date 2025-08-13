package io.fotoapparat.hardware;

import android.hardware.Camera;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.fotoapparat.characteristic.CameraInfoProviderKt;
import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.concurrent.CameraExecutor;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.configuration.Configuration;
import io.fotoapparat.exception.camera.UnsupportedLensException;
import io.fotoapparat.hardware.display.Display;
import io.fotoapparat.hardware.orientation.Orientation;
import io.fotoapparat.log.Logger;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.parameter.camera.CameraParameters;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.view.CameraRenderer;
import io.fotoapparat.view.FocalPointSelector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;

/* compiled from: Device.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u00002\u00020\u0001Bn\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012#\u0010\u0012\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013j\u0002`\u0016¢\u0006\u0002\b\u0017¢\u0006\u0002\u0010\u0018J\u0011\u0010*\u001a\u00020\u001dH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010+J-\u0010,\u001a\u00020-2#\u0010\"\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013j\u0002`\u0016¢\u0006\u0002\b\u0017H\u0016J\b\u0010.\u001a\u00020/H\u0016J\u0019\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u001dH\u0096@ø\u0001\u0000¢\u0006\u0002\u00103J\b\u00104\u001a\u00020\u0011H\u0016J+\u00105\u001a%\u0012\u0013\u0012\u001106¢\u0006\f\b7\u0012\b\b8\u0012\u0004\b\b(9\u0012\u0004\u0012\u00020/\u0018\u00010\u0013j\u0004\u0018\u0001`:H\u0016J%\u0010;\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013j\u0002`\u0016¢\u0006\u0002\b\u0017H\u0016J\b\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020\u001dH\u0016J\b\u0010?\u001a\u00020-H\u0016J\b\u0010@\u001a\u00020/H\u0016J\u0010\u0010A\u001a\u00020/2\u0006\u0010B\u001a\u00020CH\u0016J-\u0010D\u001a\u00020/2#\u0010E\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013j\u0002`\u0016¢\u0006\u0002\b\u0017H\u0016R\u0014\u0010\b\u001a\u00020\tX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R+\u0010\"\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0013j\u0002`\u0016¢\u0006\u0002\b\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001d0)X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006F"}, d2 = {"Lio/fotoapparat/hardware/Device;", "", "logger", "Lio/fotoapparat/log/Logger;", "display", "Lio/fotoapparat/hardware/display/Display;", "scaleType", "Lio/fotoapparat/parameter/ScaleType;", "cameraRenderer", "Lio/fotoapparat/view/CameraRenderer;", "focusPointSelector", "Lio/fotoapparat/view/FocalPointSelector;", "executor", "Lio/fotoapparat/concurrent/CameraExecutor;", "numberOfCameras", "", "initialConfiguration", "Lio/fotoapparat/configuration/CameraConfiguration;", "initialLensPositionSelector", "Lkotlin/Function1;", "", "Lio/fotoapparat/characteristic/LensPosition;", "Lio/fotoapparat/selector/LensPositionSelector;", "Lkotlin/ExtensionFunctionType;", "(Lio/fotoapparat/log/Logger;Lio/fotoapparat/hardware/display/Display;Lio/fotoapparat/parameter/ScaleType;Lio/fotoapparat/view/CameraRenderer;Lio/fotoapparat/view/FocalPointSelector;Lio/fotoapparat/concurrent/CameraExecutor;ILio/fotoapparat/configuration/CameraConfiguration;Lkotlin/jvm/functions/Function1;)V", "getCameraRenderer$fotoapparat_release", "()Lio/fotoapparat/view/CameraRenderer;", "cameras", "", "Lio/fotoapparat/hardware/CameraDevice;", "getExecutor$fotoapparat_release", "()Lio/fotoapparat/concurrent/CameraExecutor;", "getFocusPointSelector$fotoapparat_release", "()Lio/fotoapparat/view/FocalPointSelector;", "lensPositionSelector", "getLogger$fotoapparat_release", "()Lio/fotoapparat/log/Logger;", "savedConfiguration", "getScaleType$fotoapparat_release", "()Lio/fotoapparat/parameter/ScaleType;", "selectedCameraDevice", "Lkotlinx/coroutines/CompletableDeferred;", "awaitSelectedCamera", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canSelectCamera", "", "clearSelectedCamera", "", "getCameraParameters", "Lio/fotoapparat/parameter/camera/CameraParameters;", "cameraDevice", "(Lio/fotoapparat/hardware/CameraDevice;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConfiguration", "getFrameProcessor", "Lio/fotoapparat/preview/Frame;", "Lkotlin/ParameterName;", "name", TypedValues.AttributesType.S_FRAME, "Lio/fotoapparat/util/FrameProcessor;", "getLensPositionSelector", "getScreenOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "getSelectedCamera", "hasSelectedCamera", "selectCamera", "updateConfiguration", "newConfiguration", "Lio/fotoapparat/configuration/Configuration;", "updateLensPositionSelector", "newLensPosition", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public class Device {
    private final CameraRenderer cameraRenderer;
    private final List<CameraDevice> cameras;
    private final Display display;
    private final CameraExecutor executor;
    private final FocalPointSelector focusPointSelector;
    private Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> lensPositionSelector;
    private final Logger logger;
    private CameraConfiguration savedConfiguration;
    private final ScaleType scaleType;
    private CompletableDeferred<CameraDevice> selectedCameraDevice;

    /* compiled from: Device.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@ø\u0001\u0000"}, d2 = {"getCameraParameters", "", "cameraDevice", "Lio/fotoapparat/hardware/CameraDevice;", "continuation", "Lkotlin/coroutines/Continuation;", "Lio/fotoapparat/parameter/camera/CameraParameters;"}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/hardware/Device", f = "Device.kt", i = {0, 0}, l = {143, 144}, m = "getCameraParameters$suspendImpl", n = {"this", "cameraDevice"}, s = {"L$0", "L$1"})
    /* renamed from: io.fotoapparat.hardware.Device$getCameraParameters$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Device.getCameraParameters$suspendImpl(Device.this, null, this);
        }
    }

    public Object awaitSelectedCamera(Continuation<? super CameraDevice> continuation) {
        return awaitSelectedCamera$suspendImpl(this, continuation);
    }

    public Object getCameraParameters(CameraDevice cameraDevice, Continuation<? super CameraParameters> continuation) {
        return getCameraParameters$suspendImpl(this, cameraDevice, continuation);
    }

    public Device(Logger logger, Display display, ScaleType scaleType, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, CameraExecutor executor, int i, CameraConfiguration initialConfiguration, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> initialLensPositionSelector) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(display, "display");
        Intrinsics.checkParameterIsNotNull(scaleType, "scaleType");
        Intrinsics.checkParameterIsNotNull(cameraRenderer, "cameraRenderer");
        Intrinsics.checkParameterIsNotNull(executor, "executor");
        Intrinsics.checkParameterIsNotNull(initialConfiguration, "initialConfiguration");
        Intrinsics.checkParameterIsNotNull(initialLensPositionSelector, "initialLensPositionSelector");
        this.logger = logger;
        this.display = display;
        this.scaleType = scaleType;
        this.cameraRenderer = cameraRenderer;
        this.focusPointSelector = focalPointSelector;
        this.executor = executor;
        IntRange intRangeUntil = RangesKt.until(0, i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(new CameraDevice(getLogger(), CameraInfoProviderKt.getCharacteristics(((IntIterator) it).nextInt())));
        }
        this.cameras = arrayList;
        this.lensPositionSelector = initialLensPositionSelector;
        this.selectedCameraDevice = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.savedConfiguration = CameraConfiguration.INSTANCE.m1099default();
        updateLensPositionSelector(initialLensPositionSelector);
        this.savedConfiguration = initialConfiguration;
    }

    /* renamed from: getLogger$fotoapparat_release, reason: from getter */
    public Logger getLogger() {
        return this.logger;
    }

    /* renamed from: getScaleType$fotoapparat_release, reason: from getter */
    public ScaleType getScaleType() {
        return this.scaleType;
    }

    /* renamed from: getCameraRenderer$fotoapparat_release, reason: from getter */
    public CameraRenderer getCameraRenderer() {
        return this.cameraRenderer;
    }

    /* renamed from: getFocusPointSelector$fotoapparat_release, reason: from getter */
    public final FocalPointSelector getFocusPointSelector() {
        return this.focusPointSelector;
    }

    /* renamed from: getExecutor$fotoapparat_release, reason: from getter */
    public final CameraExecutor getExecutor() {
        return this.executor;
    }

    public /* synthetic */ Device(Logger logger, Display display, ScaleType scaleType, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, CameraExecutor cameraExecutor, int i, CameraConfiguration cameraConfiguration, Function1 function1, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(logger, display, scaleType, cameraRenderer, focalPointSelector, cameraExecutor, (i2 & 64) != 0 ? Camera.getNumberOfCameras() : i, cameraConfiguration, function1);
    }

    public boolean canSelectCamera(Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> lensPositionSelector) {
        Intrinsics.checkParameterIsNotNull(lensPositionSelector, "lensPositionSelector");
        return DeviceKt.selectCamera(this.cameras, lensPositionSelector) != null;
    }

    public void selectCamera() {
        getLogger().recordMethod();
        CameraDevice cameraDeviceSelectCamera = DeviceKt.selectCamera(this.cameras, this.lensPositionSelector);
        if (cameraDeviceSelectCamera != null) {
            this.selectedCameraDevice.complete(cameraDeviceSelectCamera);
        } else {
            this.selectedCameraDevice.completeExceptionally(new UnsupportedLensException());
        }
    }

    public void clearSelectedCamera() {
        this.selectedCameraDevice = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    static /* synthetic */ Object awaitSelectedCamera$suspendImpl(Device device, Continuation continuation) {
        return device.selectedCameraDevice.await(continuation);
    }

    public CameraDevice getSelectedCamera() {
        try {
            return this.selectedCameraDevice.getCompleted();
        } catch (IllegalStateException unused) {
            throw new IllegalStateException("Camera has not started!");
        }
    }

    public boolean hasSelectedCamera() {
        return this.selectedCameraDevice.isCompleted();
    }

    public Orientation getScreenOrientation() {
        return this.display.getOrientation();
    }

    public void updateLensPositionSelector(Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> newLensPosition) {
        Intrinsics.checkParameterIsNotNull(newLensPosition, "newLensPosition");
        getLogger().recordMethod();
        this.lensPositionSelector = newLensPosition;
    }

    public void updateConfiguration(Configuration newConfiguration) {
        Intrinsics.checkParameterIsNotNull(newConfiguration, "newConfiguration");
        getLogger().recordMethod();
        this.savedConfiguration = DeviceKt.updateConfiguration(this.savedConfiguration, newConfiguration);
    }

    /* renamed from: getConfiguration, reason: from getter */
    public CameraConfiguration getSavedConfiguration() {
        return this.savedConfiguration;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getCameraParameters$suspendImpl(io.fotoapparat.hardware.Device r5, io.fotoapparat.hardware.CameraDevice r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof io.fotoapparat.hardware.Device.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r7
            io.fotoapparat.hardware.Device$getCameraParameters$1 r0 = (io.fotoapparat.hardware.Device.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.fotoapparat.hardware.Device$getCameraParameters$1 r0 = new io.fotoapparat.hardware.Device$getCameraParameters$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L45
            if (r2 != r3) goto L3c
            java.lang.Object r5 = r0.L$2
            io.fotoapparat.configuration.CameraConfiguration r5 = (io.fotoapparat.configuration.CameraConfiguration) r5
            java.lang.Object r6 = r0.L$1
            io.fotoapparat.hardware.CameraDevice r6 = (io.fotoapparat.hardware.CameraDevice) r6
            java.lang.Object r6 = r0.L$0
            io.fotoapparat.hardware.Device r6 = (io.fotoapparat.hardware.Device) r6
            boolean r6 = r7 instanceof kotlin.Result.Failure
            if (r6 != 0) goto L37
            goto L5d
        L37:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L45:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L64
            io.fotoapparat.configuration.CameraConfiguration r7 = r5.savedConfiguration
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r5 = r6.getCapabilities(r0)
            if (r5 != r1) goto L5a
            return r1
        L5a:
            r4 = r7
            r7 = r5
            r5 = r4
        L5d:
            io.fotoapparat.capability.Capabilities r7 = (io.fotoapparat.capability.Capabilities) r7
            io.fotoapparat.parameter.camera.CameraParameters r5 = io.fotoapparat.parameter.camera.provide.CameraParametersProviderKt.getCameraParameters(r7, r5)
            return r5
        L64:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fotoapparat.hardware.Device.getCameraParameters$suspendImpl(io.fotoapparat.hardware.Device, io.fotoapparat.hardware.CameraDevice, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Function1<Frame, Unit> getFrameProcessor() {
        return this.savedConfiguration.getFrameProcessor();
    }

    public Function1<Iterable<? extends LensPosition>, LensPosition> getLensPositionSelector() {
        return this.lensPositionSelector;
    }
}
