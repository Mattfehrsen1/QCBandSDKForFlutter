package io.fotoapparat.hardware;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.view.Surface;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.fotoapparat.capability.Capabilities;
import io.fotoapparat.capability.provide.CapabilitiesProviderKt;
import io.fotoapparat.characteristic.Characteristics;
import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.characteristic.LensPositionCharacteristicKt;
import io.fotoapparat.coroutines.AwaitBroadcastChannel;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.hardware.metering.FocalRequest;
import io.fotoapparat.hardware.orientation.Orientation;
import io.fotoapparat.hardware.orientation.OrientationResolverKt;
import io.fotoapparat.hardware.orientation.OrientationState;
import io.fotoapparat.log.Logger;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.camera.CameraParameters;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.PreviewStream;
import io.fotoapparat.result.FocusResult;
import io.fotoapparat.result.Photo;
import io.fotoapparat.util.StringExtensionsKt;
import io.fotoapparat.view.Preview;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;

/* compiled from: CameraDevice.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0010\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u001cH\u0016J\b\u0010\"\u001a\u00020\u001cH\u0016J\u0011\u0010#\u001a\u00020\u0010H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010$J\u0011\u0010%\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010$J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u001cH\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/H\u0016J\u0019\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u000202H\u0096@ø\u0001\u0000¢\u0006\u0002\u00103J\u0012\u00104\u001a\u00020\u001c2\b\b\u0001\u00105\u001a\u000206H\u0016J\u0012\u00107\u001a\u00020\u001c2\b\b\u0001\u00105\u001a\u000206H\u0002J\u0012\u00108\u001a\u00020\u001c2\b\b\u0001\u00105\u001a\u000206H\u0002J\b\u00109\u001a\u00020\u001cH\u0016J\b\u0010:\u001a\u00020\u001cH\u0016J\b\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020\u001cH\u0016J3\u0010>\u001a\u00020\u001c2)\u0010?\u001a%\u0012\u0013\u0012\u00110A¢\u0006\f\bB\u0012\b\bC\u0012\u0004\b\b(D\u0012\u0004\u0012\u00020\u001c\u0018\u00010@j\u0004\u0018\u0001`EH\u0016J\u0019\u0010F\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0014\u0010H\u001a\u00060\bR\u00020\t*\u00060\bR\u00020\tH\u0002J\f\u0010I\u001a\u00020\u001c*\u00020\tH\u0002J\f\u0010J\u001a\u00020 *\u00020\tH\u0002J\u0014\u0010K\u001a\u00060\bR\u00020\t*\u00060\bR\u00020\tH\u0002J\u001d\u0010L\u001a\u00020\u001c*\u00020\t2\u0006\u00101\u001a\u000202H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010MR\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lio/fotoapparat/hardware/CameraDevice;", "", "logger", "Lio/fotoapparat/log/Logger;", "characteristics", "Lio/fotoapparat/characteristic/Characteristics;", "(Lio/fotoapparat/log/Logger;Lio/fotoapparat/characteristic/Characteristics;)V", "cachedCameraParameters", "Landroid/hardware/Camera$Parameters;", "Landroid/hardware/Camera;", "camera", "cameraParameters", "Lio/fotoapparat/coroutines/AwaitBroadcastChannel;", "Lio/fotoapparat/parameter/camera/CameraParameters;", "capabilities", "Lkotlinx/coroutines/CompletableDeferred;", "Lio/fotoapparat/capability/Capabilities;", "getCharacteristics", "()Lio/fotoapparat/characteristic/Characteristics;", "displayOrientation", "Lio/fotoapparat/hardware/orientation/Orientation;", "imageOrientation", "previewOrientation", "previewStream", "Lio/fotoapparat/preview/PreviewStream;", "surface", "Landroid/view/Surface;", "attachRecordingCamera", "", "mediaRecorder", "Landroid/media/MediaRecorder;", "autoFocus", "Lio/fotoapparat/result/FocusResult;", "clearFocalPoint", "close", "getCapabilities", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getParameters", "getPreviewResolution", "Lio/fotoapparat/parameter/Resolution;", "lock", "open", "setDisplayOrientation", "orientationState", "Lio/fotoapparat/hardware/orientation/OrientationState;", "setDisplaySurface", "preview", "Lio/fotoapparat/view/Preview;", "setFocalPoint", "focalRequest", "Lio/fotoapparat/hardware/metering/FocalRequest;", "(Lio/fotoapparat/hardware/metering/FocalRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setZoom", "level", "", "setZoomSafely", "setZoomUnsafe", "startPreview", "stopPreview", "takePhoto", "Lio/fotoapparat/result/Photo;", "unlock", "updateFrameProcessor", "frameProcessor", "Lkotlin/Function1;", "Lio/fotoapparat/preview/Frame;", "Lkotlin/ParameterName;", "name", TypedValues.AttributesType.S_FRAME, "Lio/fotoapparat/util/FrameProcessor;", "updateParameters", "(Lio/fotoapparat/parameter/camera/CameraParameters;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cacheLocally", "clearFocusingAreas", "focusSafely", "setInCamera", "updateFocusingAreas", "(Landroid/hardware/Camera;Lio/fotoapparat/hardware/metering/FocalRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public class CameraDevice {
    private Camera.Parameters cachedCameraParameters;
    private Camera camera;
    private final AwaitBroadcastChannel<CameraParameters> cameraParameters;
    private final CompletableDeferred<Capabilities> capabilities;
    private final Characteristics characteristics;
    private Orientation displayOrientation;
    private Orientation imageOrientation;
    private final Logger logger;
    private Orientation previewOrientation;
    private PreviewStream previewStream;
    private Surface surface;

    /* compiled from: CameraDevice.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@ø\u0001\u0000"}, d2 = {"setFocalPoint", "", "focalRequest", "Lio/fotoapparat/hardware/metering/FocalRequest;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/hardware/CameraDevice", f = "CameraDevice.kt", i = {0, 0, 1, 1}, l = {248, 252, 254}, m = "setFocalPoint$suspendImpl", n = {"this", "focalRequest", "this", "focalRequest"}, s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: io.fotoapparat.hardware.CameraDevice$setFocalPoint$1, reason: invalid class name and case insensitive filesystem */
    static final class C06901 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C06901(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CameraDevice.setFocalPoint$suspendImpl(CameraDevice.this, null, this);
        }
    }

    /* compiled from: CameraDevice.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0082@ø\u0001\u0000"}, d2 = {"updateFocusingAreas", "", "Landroid/hardware/Camera;", "focalRequest", "Lio/fotoapparat/hardware/metering/FocalRequest;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/hardware/CameraDevice", f = "CameraDevice.kt", i = {0, 0, 0, 0, 0}, l = {342, 350}, m = "updateFocusingAreas", n = {"this", "$receiver", "focalRequest", "focusingAreas", "$receiver"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5"})
    /* renamed from: io.fotoapparat.hardware.CameraDevice$updateFocusingAreas$1, reason: invalid class name and case insensitive filesystem */
    static final class C06911 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C06911(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CameraDevice.this.updateFocusingAreas(null, null, this);
        }
    }

    /* compiled from: CameraDevice.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@ø\u0001\u0000"}, d2 = {"updateParameters", "", "cameraParameters", "Lio/fotoapparat/parameter/camera/CameraParameters;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
    @DebugMetadata(c = "io/fotoapparat/hardware/CameraDevice", f = "CameraDevice.kt", i = {0, 0}, l = {163, 168}, m = "updateParameters$suspendImpl", n = {"this", "cameraParameters"}, s = {"L$0", "L$1"})
    /* renamed from: io.fotoapparat.hardware.CameraDevice$updateParameters$1, reason: invalid class name and case insensitive filesystem */
    static final class C06921 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C06921(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CameraDevice.updateParameters$suspendImpl(CameraDevice.this, null, this);
        }
    }

    public Object getCapabilities(Continuation<? super Capabilities> continuation) {
        return getCapabilities$suspendImpl(this, continuation);
    }

    public Object getParameters(Continuation<? super CameraParameters> continuation) {
        return getParameters$suspendImpl(this, continuation);
    }

    public Object setFocalPoint(FocalRequest focalRequest, Continuation<? super Unit> continuation) {
        return setFocalPoint$suspendImpl(this, focalRequest, continuation);
    }

    public Object updateParameters(CameraParameters cameraParameters, Continuation<? super Unit> continuation) {
        return updateParameters$suspendImpl(this, cameraParameters, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CameraDevice(Logger logger, Characteristics characteristics) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(characteristics, "characteristics");
        this.logger = logger;
        this.characteristics = characteristics;
        this.capabilities = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.cameraParameters = new AwaitBroadcastChannel<>(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final Characteristics getCharacteristics() {
        return this.characteristics;
    }

    public void open() {
        this.logger.recordMethod();
        LensPosition lensPosition = this.characteristics.getLensPosition();
        int cameraId = LensPositionCharacteristicKt.toCameraId(lensPosition);
        try {
            Camera cameraOpen = Camera.open(cameraId);
            Intrinsics.checkExpressionValueIsNotNull(cameraOpen, "Camera.open(cameraId)");
            this.camera = cameraOpen;
            CompletableDeferred<Capabilities> completableDeferred = this.capabilities;
            if (cameraOpen == null) {
                Intrinsics.throwUninitializedPropertyAccessException("camera");
            }
            completableDeferred.complete(CapabilitiesProviderKt.getCapabilities(cameraOpen));
            Camera camera = this.camera;
            if (camera == null) {
                Intrinsics.throwUninitializedPropertyAccessException("camera");
            }
            this.previewStream = new PreviewStream(camera);
        } catch (RuntimeException e) {
            throw new CameraException("Failed to open camera with lens position: " + lensPosition + " and id: " + cameraId, e);
        }
    }

    public void close() {
        this.logger.recordMethod();
        Surface surface = this.surface;
        if (surface == null) {
            Intrinsics.throwUninitializedPropertyAccessException("surface");
        }
        surface.release();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        camera.release();
    }

    public void startPreview() {
        this.logger.recordMethod();
        try {
            Camera camera = this.camera;
            if (camera == null) {
                Intrinsics.throwUninitializedPropertyAccessException("camera");
            }
            camera.startPreview();
        } catch (RuntimeException e) {
            throw new CameraException("Failed to start preview for camera with lens position: " + this.characteristics.getLensPosition() + " and id: " + this.characteristics.getCameraId(), e);
        }
    }

    public void stopPreview() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        camera.stopPreview();
    }

    public void unlock() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        camera.unlock();
    }

    public void lock() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        camera.lock();
    }

    public Photo takePhoto() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        Orientation orientation = this.imageOrientation;
        if (orientation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageOrientation");
        }
        return CameraDeviceKt.takePhoto(camera, orientation.getDegrees());
    }

    static /* synthetic */ Object getCapabilities$suspendImpl(CameraDevice cameraDevice, Continuation continuation) {
        cameraDevice.logger.recordMethod();
        return cameraDevice.capabilities.await(continuation);
    }

    static /* synthetic */ Object getParameters$suspendImpl(CameraDevice cameraDevice, Continuation continuation) {
        cameraDevice.logger.recordMethod();
        return cameraDevice.cameraParameters.getValue(continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object updateParameters$suspendImpl(io.fotoapparat.hardware.CameraDevice r4, io.fotoapparat.parameter.camera.CameraParameters r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof io.fotoapparat.hardware.CameraDevice.C06921
            if (r0 == 0) goto L14
            r0 = r6
            io.fotoapparat.hardware.CameraDevice$updateParameters$1 r0 = (io.fotoapparat.hardware.CameraDevice.C06921) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L19
        L14:
            io.fotoapparat.hardware.CameraDevice$updateParameters$1 r0 = new io.fotoapparat.hardware.CameraDevice$updateParameters$1
            r0.<init>(r6)
        L19:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L39
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.fotoapparat.parameter.camera.CameraParameters r5 = (io.fotoapparat.parameter.camera.CameraParameters) r5
            java.lang.Object r4 = r0.L$0
            io.fotoapparat.hardware.CameraDevice r4 = (io.fotoapparat.hardware.CameraDevice) r4
            boolean r0 = r6 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L34
            goto L5a
        L34:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        L39:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L42:
            boolean r2 = r6 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L97
            io.fotoapparat.log.Logger r6 = r4.logger
            r6.recordMethod()
            io.fotoapparat.coroutines.AwaitBroadcastChannel<io.fotoapparat.parameter.camera.CameraParameters> r6 = r4.cameraParameters
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r6 = r6.send(r5, r0)
            if (r6 != r1) goto L5a
            return r1
        L5a:
            io.fotoapparat.log.Logger r6 = r4.logger
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "New camera parameters are: "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            r6.log(r0)
            android.hardware.Camera$Parameters r6 = r4.cachedCameraParameters
            if (r6 == 0) goto L75
            goto L89
        L75:
            android.hardware.Camera r6 = r4.camera
            if (r6 != 0) goto L7f
            java.lang.String r0 = "camera"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
        L7f:
            android.hardware.Camera$Parameters r6 = r6.getParameters()
            java.lang.String r0 = "camera.parameters"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
        L89:
            android.hardware.Camera$Parameters r5 = io.fotoapparat.parameter.camera.apply.CameraParametersApplicatorKt.applyInto(r5, r6)
            android.hardware.Camera$Parameters r5 = r4.cacheLocally(r5)
            r4.setInCamera(r5)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L97:
            kotlin.Result$Failure r6 = (kotlin.Result.Failure) r6
            java.lang.Throwable r4 = r6.exception
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fotoapparat.hardware.CameraDevice.updateParameters$suspendImpl(io.fotoapparat.hardware.CameraDevice, io.fotoapparat.parameter.camera.CameraParameters, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void updateFrameProcessor(Function1<? super Frame, Unit> frameProcessor) {
        this.logger.recordMethod();
        PreviewStream previewStream = this.previewStream;
        if (previewStream == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewStream");
        }
        previewStream.updateProcessorSafely(frameProcessor);
    }

    public void setDisplayOrientation(OrientationState orientationState) {
        Intrinsics.checkParameterIsNotNull(orientationState, "orientationState");
        this.logger.recordMethod();
        this.imageOrientation = OrientationResolverKt.computeImageOrientation(orientationState.getDeviceOrientation(), this.characteristics.getCameraOrientation(), this.characteristics.isMirrored());
        this.displayOrientation = OrientationResolverKt.computeDisplayOrientation(orientationState.getScreenOrientation(), this.characteristics.getCameraOrientation(), this.characteristics.isMirrored());
        this.previewOrientation = OrientationResolverKt.computePreviewOrientation(orientationState.getScreenOrientation(), this.characteristics.getCameraOrientation(), this.characteristics.isMirrored());
        Logger logger = this.logger;
        StringBuilder sb = new StringBuilder();
        sb.append("Orientations: ");
        sb.append(StringExtensionsKt.getLineSeparator());
        sb.append("Screen orientation (preview) is: ");
        sb.append(orientationState.getScreenOrientation());
        sb.append(". ");
        sb.append(StringExtensionsKt.getLineSeparator());
        sb.append("Camera sensor orientation is always at: ");
        sb.append(this.characteristics.getCameraOrientation());
        sb.append(". ");
        sb.append(StringExtensionsKt.getLineSeparator());
        sb.append("Camera is ");
        sb.append(this.characteristics.isMirrored() ? "mirrored." : "not mirrored.");
        logger.log(sb.toString());
        Logger logger2 = this.logger;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Orientation adjustments: ");
        sb2.append(StringExtensionsKt.getLineSeparator());
        sb2.append("Image orientation will be adjusted by: ");
        Orientation orientation = this.imageOrientation;
        if (orientation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageOrientation");
        }
        sb2.append(orientation.getDegrees());
        sb2.append(" degrees. ");
        sb2.append(StringExtensionsKt.getLineSeparator());
        sb2.append("Display orientation will be adjusted by: ");
        Orientation orientation2 = this.displayOrientation;
        if (orientation2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayOrientation");
        }
        sb2.append(orientation2.getDegrees());
        sb2.append(" degrees. ");
        sb2.append(StringExtensionsKt.getLineSeparator());
        sb2.append("Preview orientation will be adjusted by: ");
        Orientation orientation3 = this.previewOrientation;
        if (orientation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewOrientation");
        }
        sb2.append(orientation3.getDegrees());
        sb2.append(" degrees.");
        logger2.log(sb2.toString());
        PreviewStream previewStream = this.previewStream;
        if (previewStream == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewStream");
        }
        Orientation orientation4 = this.previewOrientation;
        if (orientation4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewOrientation");
        }
        previewStream.setFrameOrientation(orientation4);
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        Orientation orientation5 = this.displayOrientation;
        if (orientation5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayOrientation");
        }
        camera.setDisplayOrientation(orientation5.getDegrees());
    }

    public void setZoom(float level) {
        this.logger.recordMethod();
        setZoomSafely(level);
    }

    public FocusResult autoFocus() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        return focusSafely(camera);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object setFocalPoint$suspendImpl(io.fotoapparat.hardware.CameraDevice r5, io.fotoapparat.hardware.metering.FocalRequest r6, kotlin.coroutines.Continuation r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof io.fotoapparat.hardware.CameraDevice.C06901
            if (r0 == 0) goto L14
            r0 = r7
            io.fotoapparat.hardware.CameraDevice$setFocalPoint$1 r0 = (io.fotoapparat.hardware.CameraDevice.C06901) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L19
        L14:
            io.fotoapparat.hardware.CameraDevice$setFocalPoint$1 r0 = new io.fotoapparat.hardware.CameraDevice$setFocalPoint$1
            r0.<init>(r7)
        L19:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L57
            if (r2 == r4) goto L44
            if (r2 != r3) goto L3b
            java.lang.Object r5 = r0.L$1
            io.fotoapparat.hardware.metering.FocalRequest r5 = (io.fotoapparat.hardware.metering.FocalRequest) r5
            java.lang.Object r5 = r0.L$0
            io.fotoapparat.hardware.CameraDevice r5 = (io.fotoapparat.hardware.CameraDevice) r5
            boolean r5 = r7 instanceof kotlin.Result.Failure
            if (r5 != 0) goto L36
            goto L8e
        L36:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L3b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L44:
            java.lang.Object r5 = r0.L$1
            r6 = r5
            io.fotoapparat.hardware.metering.FocalRequest r6 = (io.fotoapparat.hardware.metering.FocalRequest) r6
            java.lang.Object r5 = r0.L$0
            io.fotoapparat.hardware.CameraDevice r5 = (io.fotoapparat.hardware.CameraDevice) r5
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L52
            goto L6f
        L52:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        L57:
            boolean r2 = r7 instanceof kotlin.Result.Failure
            if (r2 != 0) goto L92
            io.fotoapparat.log.Logger r7 = r5.logger
            r7.recordMethod()
            kotlinx.coroutines.CompletableDeferred<io.fotoapparat.capability.Capabilities> r7 = r5.capabilities
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r7.await(r0)
            if (r7 != r1) goto L6f
            return r1
        L6f:
            io.fotoapparat.capability.Capabilities r7 = (io.fotoapparat.capability.Capabilities) r7
            boolean r7 = io.fotoapparat.hardware.CameraDeviceKt.access$canSetFocusingAreas(r7)
            if (r7 == 0) goto L8f
            android.hardware.Camera r7 = r5.camera
            if (r7 != 0) goto L81
            java.lang.String r2 = "camera"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L81:
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r5.updateFocusingAreas(r7, r6, r0)
            if (r7 != r1) goto L8e
            return r1
        L8e:
            return r7
        L8f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L92:
            kotlin.Result$Failure r7 = (kotlin.Result.Failure) r7
            java.lang.Throwable r5 = r7.exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fotoapparat.hardware.CameraDevice.setFocalPoint$suspendImpl(io.fotoapparat.hardware.CameraDevice, io.fotoapparat.hardware.metering.FocalRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void clearFocalPoint() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        clearFocusingAreas(camera);
    }

    public void setDisplaySurface(Preview preview) throws IOException {
        Intrinsics.checkParameterIsNotNull(preview, "preview");
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        this.surface = CameraDeviceKt.setDisplaySurface(camera, preview);
    }

    public void attachRecordingCamera(MediaRecorder mediaRecorder) {
        Intrinsics.checkParameterIsNotNull(mediaRecorder, "mediaRecorder");
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        mediaRecorder.setCamera(camera);
    }

    public Resolution getPreviewResolution() {
        this.logger.recordMethod();
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        Orientation orientation = this.previewOrientation;
        if (orientation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewOrientation");
        }
        Resolution previewResolution = CameraDeviceKt.getPreviewResolution(camera, orientation);
        this.logger.log("Preview resolution is: " + previewResolution);
        return previewResolution;
    }

    private final void setZoomSafely(float level) {
        try {
            setZoomUnsafe(level);
        } catch (Exception e) {
            this.logger.log("Unable to change zoom level to " + level + " e: " + e.getMessage());
        }
    }

    private final void setZoomUnsafe(float level) {
        Camera.Parameters parameters = this.cachedCameraParameters;
        if (parameters == null) {
            Camera camera = this.camera;
            if (camera == null) {
                Intrinsics.throwUninitializedPropertyAccessException("camera");
            }
            parameters = camera.getParameters();
        }
        parameters.setZoom((int) (parameters.getMaxZoom() * level));
        Intrinsics.checkExpressionValueIsNotNull(parameters, "(cachedCameraParameters …toInt()\n                }");
        setInCamera(cacheLocally(parameters));
    }

    private final Camera.Parameters cacheLocally(Camera.Parameters parameters) {
        this.cachedCameraParameters = parameters;
        return parameters;
    }

    private final Camera.Parameters setInCamera(Camera.Parameters parameters) {
        Camera camera = this.camera;
        if (camera == null) {
            Intrinsics.throwUninitializedPropertyAccessException("camera");
        }
        camera.setParameters(parameters);
        return parameters;
    }

    private final FocusResult focusSafely(Camera camera) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            camera.autoFocus(new Camera.AutoFocusCallback() { // from class: io.fotoapparat.hardware.CameraDevice.focusSafely.1
                @Override // android.hardware.Camera.AutoFocusCallback
                public final void onAutoFocus(boolean z, Camera camera2) {
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await(3L, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
            }
            return FocusResult.Focused.INSTANCE;
        } catch (Exception e) {
            this.logger.log("Failed to perform autofocus using device " + this.characteristics.getCameraId() + " e: " + e.getMessage());
            return FocusResult.UnableToFocus.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final /* synthetic */ java.lang.Object updateFocusingAreas(android.hardware.Camera r6, io.fotoapparat.hardware.metering.FocalRequest r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.fotoapparat.hardware.CameraDevice.C06911
            if (r0 == 0) goto L14
            r0 = r8
            io.fotoapparat.hardware.CameraDevice$updateFocusingAreas$1 r0 = (io.fotoapparat.hardware.CameraDevice.C06911) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            io.fotoapparat.hardware.CameraDevice$updateFocusingAreas$1 r0 = new io.fotoapparat.hardware.CameraDevice$updateFocusingAreas$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L55
            if (r2 != r3) goto L4c
            java.lang.Object r6 = r0.L$6
            android.hardware.Camera r6 = (android.hardware.Camera) r6
            java.lang.Object r7 = r0.L$5
            android.hardware.Camera$Parameters r7 = (android.hardware.Camera.Parameters) r7
            java.lang.Object r1 = r0.L$4
            android.hardware.Camera$Parameters r1 = (android.hardware.Camera.Parameters) r1
            java.lang.Object r2 = r0.L$3
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r3 = r0.L$2
            io.fotoapparat.hardware.metering.FocalRequest r3 = (io.fotoapparat.hardware.metering.FocalRequest) r3
            java.lang.Object r3 = r0.L$1
            android.hardware.Camera r3 = (android.hardware.Camera) r3
            java.lang.Object r0 = r0.L$0
            io.fotoapparat.hardware.CameraDevice r0 = (io.fotoapparat.hardware.CameraDevice) r0
            boolean r0 = r8 instanceof kotlin.Result.Failure
            if (r0 != 0) goto L47
            goto L91
        L47:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        L4c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L55:
            boolean r2 = r8 instanceof kotlin.Result.Failure
            if (r2 != 0) goto Lc2
            io.fotoapparat.hardware.orientation.Orientation r8 = r5.displayOrientation
            if (r8 != 0) goto L63
            java.lang.String r2 = "displayOrientation"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L63:
            int r8 = r8.getDegrees()
            io.fotoapparat.characteristic.Characteristics r2 = r5.characteristics
            boolean r2 = r2.isMirrored()
            java.util.List r2 = io.fotoapparat.hardware.metering.convert.FocalPointConverterKt.toFocusAreas(r7, r8, r2)
            android.hardware.Camera$Parameters r8 = r6.getParameters()
            kotlinx.coroutines.CompletableDeferred<io.fotoapparat.capability.Capabilities> r4 = r5.capabilities
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r2
            r0.L$4 = r8
            r0.L$5 = r8
            r0.L$6 = r6
            r0.label = r3
            java.lang.Object r7 = r4.await(r0)
            if (r7 != r1) goto L8e
            return r1
        L8e:
            r1 = r8
            r8 = r7
            r7 = r1
        L91:
            io.fotoapparat.capability.Capabilities r8 = (io.fotoapparat.capability.Capabilities) r8
            int r0 = r8.getMaxMeteringAreas()
            if (r0 <= 0) goto L9c
            r7.setMeteringAreas(r2)
        L9c:
            int r0 = r8.getMaxFocusAreas()
            if (r0 <= 0) goto Lbc
            java.util.Set r8 = r8.getFocusModes()
            io.fotoapparat.parameter.FocusMode$Auto r0 = io.fotoapparat.parameter.FocusMode.Auto.INSTANCE
            boolean r8 = r8.contains(r0)
            if (r8 == 0) goto Lb9
            io.fotoapparat.parameter.FocusMode$Auto r8 = io.fotoapparat.parameter.FocusMode.Auto.INSTANCE
            io.fotoapparat.parameter.FocusMode r8 = (io.fotoapparat.parameter.FocusMode) r8
            java.lang.String r8 = io.fotoapparat.parameter.camera.convert.FocusModeConverterKt.toCode(r8)
            r7.setFocusMode(r8)
        Lb9:
            r7.setFocusAreas(r2)
        Lbc:
            r6.setParameters(r1)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        Lc2:
            kotlin.Result$Failure r8 = (kotlin.Result.Failure) r8
            java.lang.Throwable r6 = r8.exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fotoapparat.hardware.CameraDevice.updateFocusingAreas(android.hardware.Camera, io.fotoapparat.hardware.metering.FocalRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void clearFocusingAreas(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setMeteringAreas(null);
        parameters.setFocusAreas(null);
        camera.setParameters(parameters);
    }
}
