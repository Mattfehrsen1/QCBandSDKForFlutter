package io.fotoapparat;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.king.zxing.util.CodeUtils;
import io.fotoapparat.capability.Capabilities;
import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.concurrent.CameraExecutor;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.configuration.Configuration;
import io.fotoapparat.error.ErrorCallbacksKt;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.hardware.Device;
import io.fotoapparat.hardware.display.Display;
import io.fotoapparat.hardware.orientation.OrientationSensor;
import io.fotoapparat.log.Logger;
import io.fotoapparat.log.LoggersKt;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.parameter.camera.CameraParameters;
import io.fotoapparat.result.FocusResult;
import io.fotoapparat.result.PendingResult;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.routine.camera.StartRoutineKt;
import io.fotoapparat.routine.camera.StopRoutineKt;
import io.fotoapparat.routine.camera.SwitchCameraRoutineKt;
import io.fotoapparat.routine.camera.UpdateConfigurationRoutineKt;
import io.fotoapparat.routine.zoom.UpdateZoomLevelRoutineKt;
import io.fotoapparat.selector.LensPositionSelectorsKt;
import io.fotoapparat.selector.SelectorsKt;
import io.fotoapparat.view.CameraRenderer;
import io.fotoapparat.view.FocalPointSelector;
import java.util.concurrent.Future;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: Fotoapparat.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ?2\u00020\u0001:\u0001?B\u008c\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012%\b\u0002\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tj\u0002`\f¢\u0006\u0002\b\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\tj\u0002`\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u0006\u0010&\u001a\u00020\u0000J\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(J\u0010\u0010*\u001a\f\u0012\u0004\u0012\u00020+0(j\u0002`,J\u0010\u0010-\u001a\f\u0012\u0004\u0012\u00020.0(j\u0002`/J+\u00100\u001a\u0002012#\u00102\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tj\u0002`\f¢\u0006\u0002\b\rJ\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\u0014042\b\b\u0001\u00105\u001a\u000206J\u0006\u00107\u001a\u00020\u0014J\u0006\u00108\u001a\u00020\u0014J3\u00109\u001a\u00020\u00142#\u0010\b\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tj\u0002`\f¢\u0006\u0002\b\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010:\u001a\u00020;J\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020\u0014042\u0006\u0010=\u001a\u00020>R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\tj\u0002`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#¨\u0006@"}, d2 = {"Lio/fotoapparat/Fotoapparat;", "", "context", "Landroid/content/Context;", "view", "Lio/fotoapparat/view/CameraRenderer;", "focusView", "Lio/fotoapparat/view/FocalPointSelector;", "lensPosition", "Lkotlin/Function1;", "", "Lio/fotoapparat/characteristic/LensPosition;", "Lio/fotoapparat/selector/LensPositionSelector;", "Lkotlin/ExtensionFunctionType;", "scaleType", "Lio/fotoapparat/parameter/ScaleType;", "cameraConfiguration", "Lio/fotoapparat/configuration/CameraConfiguration;", "cameraErrorCallback", "Lio/fotoapparat/exception/camera/CameraException;", "", "Lio/fotoapparat/error/CameraErrorCallback;", "executor", "Lio/fotoapparat/concurrent/CameraExecutor;", "logger", "Lio/fotoapparat/log/Logger;", "(Landroid/content/Context;Lio/fotoapparat/view/CameraRenderer;Lio/fotoapparat/view/FocalPointSelector;Lkotlin/jvm/functions/Function1;Lio/fotoapparat/parameter/ScaleType;Lio/fotoapparat/configuration/CameraConfiguration;Lkotlin/jvm/functions/Function1;Lio/fotoapparat/concurrent/CameraExecutor;Lio/fotoapparat/log/Logger;)V", "device", "Lio/fotoapparat/hardware/Device;", "display", "Lio/fotoapparat/hardware/display/Display;", "mainThreadErrorCallback", "orientationSensor", "Lio/fotoapparat/hardware/orientation/OrientationSensor;", "getOrientationSensor", "()Lio/fotoapparat/hardware/orientation/OrientationSensor;", "orientationSensor$delegate", "Lkotlin/Lazy;", "autoFocus", "focus", "Lio/fotoapparat/result/PendingResult;", "Lio/fotoapparat/result/FocusResult;", "getCapabilities", "Lio/fotoapparat/capability/Capabilities;", "Lio/fotoapparat/result/CapabilitiesResult;", "getCurrentParameters", "Lio/fotoapparat/parameter/camera/CameraParameters;", "Lio/fotoapparat/result/ParametersResult;", "isAvailable", "", "selector", "setZoom", "Ljava/util/concurrent/Future;", "zoomLevel", "", "start", "stop", "switchTo", "takePicture", "Lio/fotoapparat/result/PhotoResult;", "updateConfiguration", "newConfiguration", "Lio/fotoapparat/configuration/Configuration;", "Companion", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class Fotoapparat {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Fotoapparat.class), "orientationSensor", "getOrientationSensor()Lio/fotoapparat/hardware/orientation/OrientationSensor;"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CameraExecutor EXECUTOR = new CameraExecutor(null, 1, null);
    private final Device device;
    private final Display display;
    private final CameraExecutor executor;
    private final Logger logger;
    private final Function1<CameraException, Unit> mainThreadErrorCallback;

    /* renamed from: orientationSensor$delegate, reason: from kotlin metadata */
    private final Lazy orientationSensor;

    public Fotoapparat(Context context, CameraRenderer cameraRenderer) {
        this(context, cameraRenderer, null, null, null, null, null, null, null, TypedValues.PositionType.TYPE_CURVE_FIT, null);
    }

    public Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector) {
        this(context, cameraRenderer, focalPointSelector, null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
    }

    public Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> function1) {
        this(context, cameraRenderer, focalPointSelector, function1, null, null, null, null, null, 496, null);
    }

    public Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> function1, ScaleType scaleType) {
        this(context, cameraRenderer, focalPointSelector, function1, scaleType, null, null, null, null, CodeUtils.DEFAULT_REQ_WIDTH, null);
    }

    public Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> function1, ScaleType scaleType, CameraConfiguration cameraConfiguration) {
        this(context, cameraRenderer, focalPointSelector, function1, scaleType, cameraConfiguration, null, null, null, 448, null);
    }

    public Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> function1, ScaleType scaleType, CameraConfiguration cameraConfiguration, Function1<? super CameraException, Unit> function12) {
        this(context, cameraRenderer, focalPointSelector, function1, scaleType, cameraConfiguration, function12, null, null, 384, null);
    }

    public Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> function1, ScaleType scaleType, CameraConfiguration cameraConfiguration, Function1<? super CameraException, Unit> function12, CameraExecutor cameraExecutor) {
        this(context, cameraRenderer, focalPointSelector, function1, scaleType, cameraConfiguration, function12, cameraExecutor, null, 256, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OrientationSensor getOrientationSensor() {
        Lazy lazy = this.orientationSensor;
        KProperty kProperty = $$delegatedProperties[0];
        return (OrientationSensor) lazy.getValue();
    }

    @JvmStatic
    public static final FotoapparatBuilder with(Context context) {
        return INSTANCE.with(context);
    }

    public Fotoapparat(final Context context, CameraRenderer view, FocalPointSelector focalPointSelector, Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> lensPosition, ScaleType scaleType, CameraConfiguration cameraConfiguration, Function1<? super CameraException, Unit> cameraErrorCallback, CameraExecutor executor, Logger logger) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(lensPosition, "lensPosition");
        Intrinsics.checkParameterIsNotNull(scaleType, "scaleType");
        Intrinsics.checkParameterIsNotNull(cameraConfiguration, "cameraConfiguration");
        Intrinsics.checkParameterIsNotNull(cameraErrorCallback, "cameraErrorCallback");
        Intrinsics.checkParameterIsNotNull(executor, "executor");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.executor = executor;
        this.logger = logger;
        this.mainThreadErrorCallback = ErrorCallbacksKt.onMainThread(cameraErrorCallback);
        Display display = new Display(context);
        this.display = display;
        this.device = new Device(logger, display, scaleType, view, focalPointSelector, executor, 0, cameraConfiguration, lensPosition, 64, null);
        this.orientationSensor = LazyKt.lazy(new Function0<OrientationSensor>() { // from class: io.fotoapparat.Fotoapparat$orientationSensor$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final OrientationSensor invoke() {
                return new OrientationSensor(context, this.this$0.device);
            }
        });
        logger.recordMethod();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Fotoapparat(Context context, CameraRenderer cameraRenderer, FocalPointSelector focalPointSelector, Function1 function1, ScaleType scaleType, CameraConfiguration cameraConfiguration, Function1 function12, CameraExecutor cameraExecutor, Logger logger, int i, DefaultConstructorMarker defaultConstructorMarker) {
        FocalPointSelector focalPointSelector2;
        if ((i & 4) != 0) {
            focalPointSelector2 = null;
        } else {
            focalPointSelector2 = focalPointSelector;
        }
        this(context, cameraRenderer, focalPointSelector2, (i & 8) != 0 ? SelectorsKt.firstAvailable(LensPositionSelectorsKt.back(), LensPositionSelectorsKt.front(), LensPositionSelectorsKt.external()) : function1, (i & 16) != 0 ? ScaleType.CenterCrop : scaleType, (i & 32) != 0 ? CameraConfiguration.INSTANCE.m1099default() : cameraConfiguration, (i & 64) != 0 ? new Function1<CameraException, Unit>() { // from class: io.fotoapparat.Fotoapparat.1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CameraException it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CameraException cameraException) {
                invoke2(cameraException);
                return Unit.INSTANCE;
            }
        } : function12, (i & 128) != 0 ? EXECUTOR : cameraExecutor, (i & 256) != 0 ? LoggersKt.none() : logger);
    }

    public final void start() {
        this.logger.recordMethod();
        this.executor.execute(new CameraExecutor.Operation(false, new Function0<Unit>() { // from class: io.fotoapparat.Fotoapparat.start.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws InterruptedException {
                StartRoutineKt.bootStart(Fotoapparat.this.device, Fotoapparat.this.getOrientationSensor(), Fotoapparat.this.mainThreadErrorCallback);
            }
        }, 1, null));
    }

    public final void stop() {
        this.logger.recordMethod();
        this.executor.cancelTasks();
        this.executor.execute(new CameraExecutor.Operation(false, new Function0<Unit>() { // from class: io.fotoapparat.Fotoapparat.stop.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                StopRoutineKt.shutDown(Fotoapparat.this.device, Fotoapparat.this.getOrientationSensor());
            }
        }, 1, null));
    }

    public final PhotoResult takePicture() {
        this.logger.recordMethod();
        return PhotoResult.INSTANCE.fromFuture$fotoapparat_release(this.executor.execute(new CameraExecutor.Operation(true, new Fotoapparat$takePicture$future$1(this.device))), this.logger);
    }

    public final PendingResult<Capabilities> getCapabilities() {
        this.logger.recordMethod();
        return PendingResult.INSTANCE.fromFuture$fotoapparat_release(this.executor.execute(new CameraExecutor.Operation(true, new Fotoapparat$getCapabilities$future$1(this.device))), this.logger);
    }

    public final PendingResult<CameraParameters> getCurrentParameters() {
        this.logger.recordMethod();
        return PendingResult.INSTANCE.fromFuture$fotoapparat_release(this.executor.execute(new CameraExecutor.Operation(true, new Fotoapparat$getCurrentParameters$future$1(this.device))), this.logger);
    }

    public final Future<Unit> updateConfiguration(final Configuration newConfiguration) {
        Intrinsics.checkParameterIsNotNull(newConfiguration, "newConfiguration");
        return this.executor.execute(new CameraExecutor.Operation(true, new Function0<Unit>() { // from class: io.fotoapparat.Fotoapparat.updateConfiguration.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws InterruptedException {
                Fotoapparat.this.logger.recordMethod();
                UpdateConfigurationRoutineKt.updateDeviceConfiguration(Fotoapparat.this.device, newConfiguration);
            }
        }));
    }

    public final Future<Unit> setZoom(final float zoomLevel) {
        return this.executor.execute(new CameraExecutor.Operation(true, new Function0<Unit>() { // from class: io.fotoapparat.Fotoapparat.setZoom.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws InterruptedException {
                Fotoapparat.this.logger.recordMethod();
                UpdateZoomLevelRoutineKt.updateZoomLevel(Fotoapparat.this.device, zoomLevel);
            }
        }));
    }

    public final Fotoapparat autoFocus() {
        this.logger.recordMethod();
        focus();
        return this;
    }

    public final PendingResult<FocusResult> focus() {
        this.logger.recordMethod();
        return PendingResult.INSTANCE.fromFuture$fotoapparat_release(this.executor.execute(new CameraExecutor.Operation(true, new Fotoapparat$focus$future$1(this.device))), this.logger);
    }

    public final void switchTo(final Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> lensPosition, final CameraConfiguration cameraConfiguration) {
        Intrinsics.checkParameterIsNotNull(lensPosition, "lensPosition");
        Intrinsics.checkParameterIsNotNull(cameraConfiguration, "cameraConfiguration");
        this.logger.recordMethod();
        this.executor.execute(new CameraExecutor.Operation(true, new Function0<Unit>() { // from class: io.fotoapparat.Fotoapparat.switchTo.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() throws InterruptedException {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() throws InterruptedException {
                SwitchCameraRoutineKt.switchCamera(Fotoapparat.this.device, lensPosition, cameraConfiguration, Fotoapparat.this.mainThreadErrorCallback, Fotoapparat.this.getOrientationSensor());
            }
        }));
    }

    public final boolean isAvailable(Function1<? super Iterable<? extends LensPosition>, ? extends LensPosition> selector) {
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        return this.device.canSelectCamera(selector);
    }

    /* compiled from: Fotoapparat.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/fotoapparat/Fotoapparat$Companion;", "", "()V", "EXECUTOR", "Lio/fotoapparat/concurrent/CameraExecutor;", "with", "Lio/fotoapparat/FotoapparatBuilder;", "context", "Landroid/content/Context;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FotoapparatBuilder with(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new FotoapparatBuilder(context);
        }
    }
}
